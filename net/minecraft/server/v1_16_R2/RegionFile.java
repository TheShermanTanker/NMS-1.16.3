/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import com.destroystokyo.paper.util.SneakyThrow;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.nio.channels.FileChannel;
/*      */ import java.nio.file.CopyOption;
/*      */ import java.nio.file.Files;
/*      */ import java.nio.file.OpenOption;
/*      */ import java.nio.file.Path;
/*      */ import java.nio.file.StandardCopyOption;
/*      */ import java.nio.file.StandardOpenOption;
/*      */ import java.util.Random;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import java.util.zip.InflaterInputStream;
/*      */ import javax.annotation.Nullable;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ public class RegionFile implements AutoCloseable {
/*   30 */   private static final Logger LOGGER = LogManager.getLogger();
/*   31 */   private static final ByteBuffer c = ByteBuffer.allocateDirect(1);
/*      */   private final FileChannel dataFile; private final Path e; private final RegionFileCompression f; private final ByteBuffer g; private final IntBuffer h; private final IntBuffer i; @VisibleForTesting
/*   33 */   protected final RegionFileBitSet freeSectors; public final File file; private final Path getContainingDataFolder() { return this.e; } private final RegionFileCompression getRegionFileCompression() {
/*   34 */     return this.f;
/*      */   }
/*   36 */   private final IntBuffer getOffsets() { return this.h; } private final IntBuffer getTimestamps() {
/*   37 */     return this.i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static long roundToSectors(long bytes) {
/*   44 */     long sectors = bytes >>> 12L;
/*   45 */     long remainingBytes = bytes & 0xFFFL;
/*   46 */     long sign = -remainingBytes;
/*   47 */     return sectors + (sign >>> 63L);
/*      */   }
/*      */   
/*   50 */   private static final NBTTagCompound OVERSIZED_COMPOUND = new NBTTagCompound(); final boolean canRecalcHeader;
/*      */   
/*      */   private NBTTagCompound attemptRead(long sector, int chunkDataLength, long fileLength) throws IOException {
/*      */     try {
/*   54 */       if (chunkDataLength < 0) {
/*   55 */         return null;
/*      */       }
/*      */       
/*   58 */       long offset = sector * 4096L + 4L;
/*      */       
/*   60 */       if (offset + chunkDataLength > fileLength) {
/*   61 */         return null;
/*      */       }
/*      */       
/*   64 */       ByteBuffer chunkData = ByteBuffer.allocate(chunkDataLength);
/*   65 */       if (chunkDataLength != this.dataFile.read(chunkData, offset)) {
/*   66 */         return null;
/*      */       }
/*      */       
/*   69 */       chunkData.flip();
/*      */       
/*   71 */       byte compressionType = chunkData.get();
/*   72 */       if (compressionType < 0)
/*      */       {
/*   74 */         return OVERSIZED_COMPOUND;
/*      */       }
/*      */       
/*   77 */       RegionFileCompression compression = RegionFileCompression.getByType(compressionType);
/*   78 */       if (compression == null) {
/*   79 */         return null;
/*      */       }
/*      */       
/*   82 */       InputStream input = compression.wrap(new ByteArrayInputStream(chunkData.array(), chunkData.position(), chunkDataLength - chunkData.position()));
/*      */       
/*   84 */       return NBTCompressedStreamTools.readNBT(new DataInputStream(new BufferedInputStream(input)));
/*   85 */     } catch (Exception ex) {
/*   86 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int getLength(long sector) throws IOException {
/*   91 */     ByteBuffer length = ByteBuffer.allocate(4);
/*   92 */     if (4 != this.dataFile.read(length, sector * 4096L)) {
/*   93 */       return -1;
/*      */     }
/*      */     
/*   96 */     return length.getInt(0);
/*      */   }
/*      */   
/*      */   private void backupRegionFile() {
/*  100 */     File backup = new File(this.file.getParent(), this.file.getName() + "." + (new Random()).nextLong() + ".backup");
/*  101 */     backupRegionFile(backup);
/*      */   }
/*      */   
/*      */   private void backupRegionFile(File to) {
/*      */     try {
/*  106 */       this.dataFile.force(true);
/*  107 */       MinecraftServer.LOGGER.warn("Backing up regionfile \"" + this.file.getAbsolutePath() + "\" to " + to.getAbsolutePath());
/*  108 */       Files.copy(this.file.toPath(), to.toPath(), new CopyOption[0]);
/*  109 */       MinecraftServer.LOGGER.warn("Backed up the regionfile to " + to.getAbsolutePath());
/*  110 */     } catch (IOException ex) {
/*  111 */       MinecraftServer.LOGGER.error("Failed to backup to " + to.getAbsolutePath(), ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void recalculateHeader() throws IOException {
/*  117 */     if (!this.canRecalcHeader) {
/*      */       return;
/*      */     }
/*  120 */     synchronized (this) {
/*  121 */       MinecraftServer.LOGGER.warn("Corrupt regionfile header detected! Attempting to re-calculate header offsets for regionfile " + this.file.getAbsolutePath(), new Throwable());
/*      */ 
/*      */ 
/*      */       
/*  125 */       backupRegionFile();
/*  126 */       NBTTagCompound[] compounds = new NBTTagCompound[1024];
/*  127 */       int[] rawLengths = new int[1024];
/*  128 */       int[] sectorOffsets = new int[1024];
/*  129 */       boolean[] hasAikarOversized = new boolean[1024];
/*      */       
/*  131 */       long fileLength = this.dataFile.size();
/*  132 */       long totalSectors = roundToSectors(fileLength);
/*      */       
/*      */       long i, maxSector;
/*      */       
/*  136 */       for (i = 2L, maxSector = Math.min(8388607L, totalSectors); i < maxSector; i++) {
/*  137 */         int chunkDataLength = getLength(i);
/*  138 */         NBTTagCompound compound = attemptRead(i, chunkDataLength, fileLength);
/*  139 */         if (compound != null && compound != OVERSIZED_COMPOUND) {
/*      */ 
/*      */ 
/*      */           
/*  143 */           ChunkCoordIntPair chunkPos = ChunkRegionLoader.getChunkCoordinate(compound);
/*  144 */           int location = chunkPos.x & 0x1F | (chunkPos.z & 0x1F) << 5;
/*      */           
/*  146 */           NBTTagCompound otherCompound = compounds[location];
/*      */           
/*  148 */           if (otherCompound == null || ChunkRegionLoader.getLastWorldSaveTime(otherCompound) <= ChunkRegionLoader.getLastWorldSaveTime(compound)) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  153 */             File aikarOversizedFile = getOversizedFile(chunkPos.x, chunkPos.z);
/*  154 */             boolean isAikarOversized = false;
/*  155 */             if (aikarOversizedFile.exists()) {
/*      */               try {
/*  157 */                 NBTTagCompound aikarOversizedCompound = getOversizedData(chunkPos.x, chunkPos.z);
/*  158 */                 if (ChunkRegionLoader.getLastWorldSaveTime(compound) == ChunkRegionLoader.getLastWorldSaveTime(aikarOversizedCompound))
/*      */                 {
/*  160 */                   isAikarOversized = true;
/*      */                 }
/*  162 */               } catch (Exception ex) {
/*  163 */                 MinecraftServer.LOGGER.error("Failed to read aikar oversized data for absolute chunk (" + chunkPos.x + "," + chunkPos.z + ") in regionfile " + this.file.getAbsolutePath() + ", oversized data for this chunk will be lost", ex);
/*      */               } 
/*      */             }
/*      */ 
/*      */             
/*  168 */             hasAikarOversized[location] = isAikarOversized;
/*  169 */             compounds[location] = compound;
/*  170 */             rawLengths[location] = chunkDataLength + 4;
/*  171 */             sectorOffsets[location] = (int)i;
/*      */             
/*  173 */             int chunkSectorLength = (int)roundToSectors(rawLengths[location]);
/*  174 */             i += chunkSectorLength;
/*  175 */             i--;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  182 */       Path containingFolder = getContainingDataFolder();
/*  183 */       File[] regionFiles = containingFolder.toFile().listFiles();
/*  184 */       boolean[] oversized = new boolean[1024];
/*  185 */       RegionFileCompression[] oversizedCompressionTypes = new RegionFileCompression[1024];
/*      */       
/*  187 */       if (regionFiles != null) {
/*  188 */         ChunkCoordIntPair ourLowerLeftPosition = RegionFileCache.getRegionFileCoordinates(this.file);
/*      */         
/*  190 */         if (ourLowerLeftPosition == null) {
/*  191 */           MinecraftServer.LOGGER.fatal("Unable to get chunk location of regionfile " + this.file.getAbsolutePath() + ", cannot recover oversized chunks");
/*      */         } else {
/*  193 */           int lowerXBound = ourLowerLeftPosition.x;
/*  194 */           int lowerZBound = ourLowerLeftPosition.z;
/*  195 */           int upperXBound = lowerXBound + 32 - 1;
/*  196 */           int upperZBound = lowerZBound + 32 - 1;
/*      */ 
/*      */           
/*  199 */           for (File regionFile : regionFiles) {
/*  200 */             ChunkCoordIntPair oversizedCoords = getOversizedChunkPair(regionFile);
/*  201 */             if (oversizedCoords != null)
/*      */             {
/*      */ 
/*      */               
/*  205 */               if (oversizedCoords.x >= lowerXBound && oversizedCoords.x <= upperXBound && oversizedCoords.z >= lowerZBound && oversizedCoords.z <= upperZBound) {
/*      */                 byte[] chunkData;
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  211 */                 int location = oversizedCoords.x & 0x1F | (oversizedCoords.z & 0x1F) << 5;
/*      */ 
/*      */                 
/*      */                 try {
/*  215 */                   chunkData = Files.readAllBytes(regionFile.toPath());
/*  216 */                 } catch (Exception ex) {
/*  217 */                   MinecraftServer.LOGGER.error("Failed to read oversized chunk data in file " + regionFile.getAbsolutePath() + ", data will be lost", ex);
/*      */                 } 
/*      */ 
/*      */                 
/*  221 */                 NBTTagCompound compound = null;
/*      */ 
/*      */                 
/*  224 */                 RegionFileCompression compression = null;
/*  225 */                 for (ObjectIterator<RegionFileCompression> objectIterator = RegionFileCompression.getCompressionTypes().values().iterator(); objectIterator.hasNext(); ) { RegionFileCompression compressionType = objectIterator.next();
/*      */                   try {
/*  227 */                     DataInputStream in = new DataInputStream(new BufferedInputStream(compressionType.wrap(new ByteArrayInputStream(chunkData))));
/*  228 */                     compound = NBTCompressedStreamTools.readNBT(in);
/*  229 */                     compression = compressionType;
/*      */                     break;
/*  231 */                   } catch (Exception ex) {} }
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  236 */                 if (compound == null) {
/*  237 */                   MinecraftServer.LOGGER.error("Failed to read oversized chunk data in file " + regionFile.getAbsolutePath() + ", it's corrupt. Its data will be lost");
/*      */ 
/*      */                 
/*      */                 }
/*  241 */                 else if (compounds[location] == null || ChunkRegionLoader.getLastWorldSaveTime(compound) > ChunkRegionLoader.getLastWorldSaveTime(compounds[location])) {
/*  242 */                   oversized[location] = true;
/*  243 */                   oversizedCompressionTypes[location] = compression;
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  251 */       int[] calculatedOffsets = new int[1024];
/*  252 */       RegionFileBitSet newSectorAllocations = new RegionFileBitSet();
/*  253 */       newSectorAllocations.allocate(0, 2);
/*      */       
/*      */       int chunkX;
/*      */       
/*  257 */       for (chunkX = 0; chunkX < 32; chunkX++) {
/*  258 */         for (int chunkZ = 0; chunkZ < 32; chunkZ++) {
/*  259 */           int location = chunkX | chunkZ << 5;
/*      */           
/*  261 */           if (!oversized[location]) {
/*      */ 
/*      */ 
/*      */             
/*  265 */             int rawLength = rawLengths[location];
/*  266 */             int sectorOffset = sectorOffsets[location];
/*  267 */             int sectorLength = (int)roundToSectors(rawLength);
/*      */             
/*  269 */             if (newSectorAllocations.tryAllocate(sectorOffset, sectorLength)) {
/*  270 */               calculatedOffsets[location] = sectorOffset << 8 | ((sectorLength > 255) ? 255 : sectorLength);
/*      */             } else {
/*  272 */               MinecraftServer.LOGGER.error("Failed to allocate space for local chunk (overlapping data??) at (" + chunkX + "," + chunkZ + ") in regionfile " + this.file.getAbsolutePath() + ", chunk will be regenerated");
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  279 */       for (chunkX = 0; chunkX < 32; chunkX++) {
/*  280 */         for (int chunkZ = 0; chunkZ < 32; chunkZ++) {
/*  281 */           int location = chunkX | chunkZ << 5;
/*      */           
/*  283 */           if (oversized[location]) {
/*      */ 
/*      */ 
/*      */             
/*  287 */             int sectorOffset = newSectorAllocations.allocateNewSpace(1);
/*  288 */             int sectorLength = 1;
/*      */             
/*      */             try {
/*  291 */               this.dataFile.write(getOversizedChunkHolderData(oversizedCompressionTypes[location]), (sectorOffset * 4096));
/*      */               
/*  293 */               calculatedOffsets[location] = sectorOffset << 8 | ((sectorLength > 255) ? 255 : sectorLength);
/*  294 */             } catch (IOException ex) {
/*  295 */               newSectorAllocations.free(sectorOffset, sectorLength);
/*  296 */               MinecraftServer.LOGGER.error("Failed to write new oversized chunk data holder, local chunk at (" + chunkX + "," + chunkZ + ") in regionfile " + this.file.getAbsolutePath() + " will be regenerated");
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  303 */       this.oversizedCount = 0;
/*  304 */       for (chunkX = 0; chunkX < 32; chunkX++) {
/*  305 */         for (int chunkZ = 0; chunkZ < 32; chunkZ++) {
/*  306 */           int location = chunkX | chunkZ << 5;
/*  307 */           int isAikarOversized = hasAikarOversized[location] ? 1 : 0;
/*      */           
/*  309 */           this.oversizedCount += isAikarOversized;
/*  310 */           this.oversized[location] = (byte)isAikarOversized;
/*      */         } 
/*      */       } 
/*      */       
/*  314 */       if (this.oversizedCount > 0) {
/*      */         try {
/*  316 */           writeOversizedMeta();
/*  317 */         } catch (Exception ex) {
/*  318 */           MinecraftServer.LOGGER.error("Failed to write aikar oversized chunk meta, all aikar style oversized chunk data will be lost for regionfile " + this.file.getAbsolutePath(), ex);
/*  319 */           getOversizedMetaFile().delete();
/*      */         } 
/*      */       } else {
/*  322 */         getOversizedMetaFile().delete();
/*      */       } 
/*      */       
/*  325 */       this.freeSectors.copyFrom(newSectorAllocations);
/*      */ 
/*      */ 
/*      */       
/*  329 */       MinecraftServer.LOGGER.info("Starting summary of changes for regionfile " + this.file.getAbsolutePath());
/*      */       
/*  331 */       for (chunkX = 0; chunkX < 32; chunkX++) {
/*  332 */         for (int chunkZ = 0; chunkZ < 32; chunkZ++) {
/*  333 */           int location = chunkX | chunkZ << 5;
/*      */           
/*  335 */           int oldOffset = getOffsets().get(location);
/*  336 */           int newOffset = calculatedOffsets[location];
/*      */           
/*  338 */           if (oldOffset != newOffset) {
/*      */ 
/*      */ 
/*      */             
/*  342 */             getOffsets().put(location, newOffset);
/*      */             
/*  344 */             if (oldOffset == 0) {
/*      */               
/*  346 */               MinecraftServer.LOGGER.info("Found missing data for local chunk (" + chunkX + "," + chunkZ + ") in regionfile " + this.file.getAbsolutePath());
/*  347 */             } else if (newOffset == 0) {
/*  348 */               MinecraftServer.LOGGER.warn("Data for local chunk (" + chunkX + "," + chunkZ + ") could not be recovered in regionfile " + this.file.getAbsolutePath() + ", it will be regenerated");
/*      */             } else {
/*  350 */               MinecraftServer.LOGGER.info("Local chunk (" + chunkX + "," + chunkZ + ") changed to point to newer data or correct chunk in regionfile " + this.file.getAbsolutePath());
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  355 */       MinecraftServer.LOGGER.info("End of change summary for regionfile " + this.file.getAbsolutePath());
/*      */ 
/*      */ 
/*      */       
/*  359 */       for (int j = 0; j < 1024; j++) {
/*  360 */         getTimestamps().put(j, (calculatedOffsets[j] != 0) ? (int)System.currentTimeMillis() : 0);
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/*  365 */         flushHeader();
/*  366 */         this.dataFile.force(true);
/*  367 */         MinecraftServer.LOGGER.info("Successfully wrote new header to disk for regionfile " + this.file.getAbsolutePath());
/*  368 */       } catch (IOException ex) {
/*  369 */         MinecraftServer.LOGGER.fatal("Failed to write new header to disk for regionfile " + this.file.getAbsolutePath(), ex);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  377 */   public final ReentrantLock fileLock = new ReentrantLock(true);
/*      */ 
/*      */   
/*  380 */   private final ChunkStatus[] statuses = new ChunkStatus[1024];
/*      */   private boolean closed;
/*      */   private final byte[] oversized;
/*      */   private int oversizedCount;
/*      */   
/*      */   public void setStatus(int x, int z, ChunkStatus status) {
/*  386 */     if (this.closed)
/*      */     {
/*  388 */       throw new IllegalStateException("RegionFile is closed");
/*      */     }
/*  390 */     this.statuses[getChunkLocation(x, z)] = status;
/*      */   }
/*      */   
/*      */   public ChunkStatus getStatusIfCached(int x, int z) {
/*  394 */     if (this.closed)
/*      */     {
/*  396 */       throw new IllegalStateException("RegionFile is closed");
/*      */     }
/*  398 */     int location = getChunkLocation(x, z);
/*  399 */     return this.statuses[location];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public RegionFile(File file, File file1, boolean flag) throws IOException {
/*  405 */     this(file.toPath(), file1.toPath(), RegionFileCompression.b, flag);
/*      */   }
/*      */   public RegionFile(File file, File file1, boolean flag, boolean canRecalcHeader) throws IOException {
/*  408 */     this(file.toPath(), file1.toPath(), RegionFileCompression.b, flag, canRecalcHeader);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public RegionFile(Path java_nio_file_path, Path java_nio_file_path1, RegionFileCompression regionfilecompression, boolean flag) throws IOException {
/*  414 */     this(java_nio_file_path, java_nio_file_path1, regionfilecompression, flag, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final Path getOversizedChunkPath(ChunkCoordIntPair chunkcoordintpair) {
/*  531 */     return e(chunkcoordintpair);
/*      */   } private Path e(ChunkCoordIntPair chunkcoordintpair) {
/*  533 */     String s = "c." + chunkcoordintpair.x + "." + chunkcoordintpair.z + ".mcc";
/*      */     
/*  535 */     return this.e.resolve(s);
/*      */   }
/*      */ 
/*      */   
/*      */   private static ChunkCoordIntPair getOversizedChunkPair(File file) {
/*  540 */     String fileName = file.getName();
/*      */     
/*  542 */     if (!fileName.startsWith("c.") || !fileName.endsWith(".mcc")) {
/*  543 */       return null;
/*      */     }
/*      */     
/*  546 */     String[] split = fileName.split("\\.");
/*      */     
/*  548 */     if (split.length != 4) {
/*  549 */       return null;
/*      */     }
/*      */     
/*      */     try {
/*  553 */       int x = Integer.parseInt(split[1]);
/*  554 */       int z = Integer.parseInt(split[2]);
/*      */       
/*  556 */       return new ChunkCoordIntPair(x, z);
/*  557 */     } catch (NumberFormatException ex) {
/*  558 */       return null;
/*      */     } 
/*      */   }
/*      */   @Nullable
/*      */   public synchronized DataInputStream getReadStream(ChunkCoordIntPair chunkCoordIntPair) throws IOException {
/*  563 */     return a(chunkCoordIntPair);
/*      */   } @Nullable
/*      */   public synchronized DataInputStream a(ChunkCoordIntPair chunkcoordintpair) throws IOException {
/*  566 */     int i = getOffset(chunkcoordintpair);
/*      */     
/*  568 */     if (i == 0) {
/*  569 */       return null;
/*      */     }
/*  571 */     int j = b(i);
/*  572 */     int k = a(i);
/*      */     
/*  574 */     if (k == 255) {
/*  575 */       ByteBuffer realLen = ByteBuffer.allocate(4);
/*  576 */       this.dataFile.read(realLen, (j * 4096));
/*  577 */       k = (realLen.getInt(0) + 4) / 4096 + 1;
/*      */     } 
/*      */     
/*  580 */     int l = k * 4096;
/*  581 */     ByteBuffer bytebuffer = ByteBuffer.allocate(l);
/*      */     
/*  583 */     this.dataFile.read(bytebuffer, (j * 4096));
/*  584 */     bytebuffer.flip();
/*  585 */     if (bytebuffer.remaining() < 5) {
/*  586 */       LOGGER.error("Chunk {} header is truncated: expected {} but read {}", chunkcoordintpair, Integer.valueOf(l), Integer.valueOf(bytebuffer.remaining()));
/*      */       
/*  588 */       if (this.canRecalcHeader) {
/*  589 */         recalculateHeader();
/*  590 */         return getReadStream(chunkcoordintpair);
/*      */       } 
/*      */       
/*  593 */       return null;
/*      */     } 
/*  595 */     int i1 = bytebuffer.getInt();
/*  596 */     byte b0 = bytebuffer.get();
/*      */     
/*  598 */     if (i1 == 0) {
/*  599 */       LOGGER.warn("Chunk {} is allocated, but stream is missing", chunkcoordintpair);
/*      */       
/*  601 */       if (this.canRecalcHeader) {
/*  602 */         recalculateHeader();
/*  603 */         return getReadStream(chunkcoordintpair);
/*      */       } 
/*      */       
/*  606 */       return null;
/*      */     } 
/*  608 */     int j1 = i1 - 1;
/*      */     
/*  610 */     if (a(b0)) {
/*  611 */       if (j1 != 0) {
/*  612 */         LOGGER.warn("Chunk has both internal and external streams");
/*      */         
/*  614 */         if (this.canRecalcHeader) {
/*  615 */           recalculateHeader();
/*  616 */           return getReadStream(chunkcoordintpair);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  622 */       DataInputStream dataInputStream = a(chunkcoordintpair, b(b0));
/*  623 */       if (dataInputStream == null && this.canRecalcHeader) {
/*  624 */         recalculateHeader();
/*  625 */         return getReadStream(chunkcoordintpair);
/*      */       } 
/*  627 */       return dataInputStream;
/*      */     } 
/*  629 */     if (j1 > bytebuffer.remaining()) {
/*  630 */       LOGGER.error("Chunk {} stream is truncated: expected {} but read {}", chunkcoordintpair, Integer.valueOf(j1), Integer.valueOf(bytebuffer.remaining()));
/*      */       
/*  632 */       if (this.canRecalcHeader) {
/*  633 */         recalculateHeader();
/*  634 */         return getReadStream(chunkcoordintpair);
/*      */       } 
/*      */       
/*  637 */       return null;
/*  638 */     }  if (j1 < 0) {
/*  639 */       LOGGER.error("Declared size {} of chunk {} is negative", Integer.valueOf(i1), chunkcoordintpair);
/*      */       
/*  641 */       if (this.canRecalcHeader) {
/*  642 */         recalculateHeader();
/*  643 */         return getReadStream(chunkcoordintpair);
/*      */       } 
/*      */       
/*  646 */       return null;
/*      */     } 
/*      */     
/*  649 */     DataInputStream ret = a(chunkcoordintpair, b0, a(bytebuffer, j1));
/*  650 */     if (ret == null && this.canRecalcHeader) {
/*  651 */       recalculateHeader();
/*  652 */       return getReadStream(chunkcoordintpair);
/*      */     } 
/*  654 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean a(byte b0) {
/*  663 */     return ((b0 & 0x80) != 0);
/*      */   }
/*      */   
/*      */   private static byte b(byte b0) {
/*  667 */     return (byte)(b0 & 0xFFFFFF7F);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   private DataInputStream a(ChunkCoordIntPair chunkcoordintpair, byte b0, InputStream inputstream) throws IOException {
/*  672 */     RegionFileCompression regionfilecompression = RegionFileCompression.a(b0);
/*      */     
/*  674 */     if (regionfilecompression == null) {
/*  675 */       LOGGER.error("Chunk {} has invalid chunk stream version {}", chunkcoordintpair, Byte.valueOf(b0));
/*  676 */       return null;
/*      */     } 
/*  678 */     return new DataInputStream(new BufferedInputStream(regionfilecompression.a(inputstream)));
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   private DataInputStream a(ChunkCoordIntPair chunkcoordintpair, byte b0) throws IOException {
/*  684 */     Path java_nio_file_path = e(chunkcoordintpair);
/*      */     
/*  686 */     if (!Files.isRegularFile(java_nio_file_path, new java.nio.file.LinkOption[0])) {
/*  687 */       LOGGER.error("External chunk path {} is not file", java_nio_file_path);
/*  688 */       return null;
/*      */     } 
/*  690 */     return a(chunkcoordintpair, b0, Files.newInputStream(java_nio_file_path, new OpenOption[0]));
/*      */   }
/*      */ 
/*      */   
/*      */   private static ByteArrayInputStream a(ByteBuffer bytebuffer, int i) {
/*  695 */     return new ByteArrayInputStream(bytebuffer.array(), bytebuffer.position(), i);
/*      */   }
/*      */   
/*      */   private int a(int i, int j) {
/*  699 */     return i << 8 | j;
/*      */   }
/*      */   
/*      */   private static int a(int i) {
/*  703 */     return i & 0xFF;
/*      */   }
/*      */   
/*      */   private static int b(int i) {
/*  707 */     return i >> 8 & 0xFFFFFF;
/*      */   }
/*      */   
/*      */   private static int c(int i) {
/*  711 */     return (i + 4096 - 1) / 4096;
/*      */   }
/*      */   
/*      */   public synchronized boolean b(ChunkCoordIntPair chunkcoordintpair) {
/*  715 */     int i = getOffset(chunkcoordintpair);
/*      */     
/*  717 */     if (i == 0) {
/*  718 */       return false;
/*      */     }
/*  720 */     int j = b(i);
/*  721 */     int k = a(i);
/*  722 */     ByteBuffer bytebuffer = ByteBuffer.allocate(5);
/*      */     
/*      */     try {
/*  725 */       this.dataFile.read(bytebuffer, (j * 4096));
/*  726 */       bytebuffer.flip();
/*  727 */       if (bytebuffer.remaining() != 5) {
/*  728 */         return false;
/*      */       }
/*  730 */       int l = bytebuffer.getInt();
/*  731 */       byte b0 = bytebuffer.get();
/*      */       
/*  733 */       if (a(b0)) {
/*  734 */         if (!RegionFileCompression.b(b(b0))) {
/*  735 */           return false;
/*      */         }
/*      */         
/*  738 */         if (!Files.isRegularFile(e(chunkcoordintpair), new java.nio.file.LinkOption[0])) {
/*  739 */           return false;
/*      */         }
/*      */       } else {
/*  742 */         if (!RegionFileCompression.b(b0)) {
/*  743 */           return false;
/*      */         }
/*      */         
/*  746 */         if (l == 0) {
/*  747 */           return false;
/*      */         }
/*      */         
/*  750 */         int i1 = l - 1;
/*      */         
/*  752 */         if (i1 < 0 || i1 > 4096 * k) {
/*  753 */           return false;
/*      */         }
/*      */       } 
/*      */       
/*  757 */       return true;
/*      */     }
/*  759 */     catch (IOException ioexception) {
/*  760 */       SneakyThrow.sneaky(ioexception);
/*  761 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public DataOutputStream c(ChunkCoordIntPair chunkcoordintpair) throws IOException {
/*  767 */     return new DataOutputStream(new BufferedOutputStream(this.f.a(new ChunkBuffer(chunkcoordintpair))));
/*      */   }
/*      */   
/*      */   public void a() throws IOException {
/*  771 */     this.dataFile.force(true);
/*      */   } protected synchronized void a(ChunkCoordIntPair chunkcoordintpair, ByteBuffer bytebuffer) throws IOException {
/*      */     int k1;
/*      */     b regionfile_b;
/*  775 */     int i = g(chunkcoordintpair);
/*  776 */     int j = this.h.get(i);
/*  777 */     int k = b(j);
/*  778 */     int l = a(j);
/*  779 */     int i1 = bytebuffer.remaining();
/*  780 */     int j1 = c(i1);
/*      */ 
/*      */ 
/*      */     
/*  784 */     if (j1 >= 256) {
/*  785 */       Path java_nio_file_path = e(chunkcoordintpair);
/*      */       
/*  787 */       LOGGER.warn("Saving oversized chunk {} ({} bytes} to external file {}", chunkcoordintpair, Integer.valueOf(i1), java_nio_file_path);
/*  788 */       j1 = 1;
/*  789 */       k1 = this.freeSectors.a(j1);
/*  790 */       regionfile_b = a(java_nio_file_path, bytebuffer);
/*  791 */       ByteBuffer bytebuffer1 = b();
/*      */       
/*  793 */       this.dataFile.write(bytebuffer1, (k1 * 4096));
/*      */     } else {
/*  795 */       k1 = this.freeSectors.a(j1);
/*  796 */       regionfile_b = (() -> Files.deleteIfExists(e(chunkcoordintpair)));
/*      */ 
/*      */       
/*  799 */       this.dataFile.write(bytebuffer, (k1 * 4096));
/*      */     } 
/*      */     
/*  802 */     int l1 = (int)(SystemUtils.getTimeMillis() / 1000L);
/*      */     
/*  804 */     this.h.put(i, a(k1, j1));
/*  805 */     this.i.put(i, l1);
/*  806 */     c();
/*  807 */     regionfile_b.run();
/*  808 */     if (k != 0) {
/*  809 */       this.freeSectors.b(k, l);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private ByteBuffer b() {
/*  816 */     return getOversizedChunkHolderData(getRegionFileCompression());
/*      */   }
/*      */   
/*      */   private ByteBuffer getOversizedChunkHolderData(RegionFileCompression compressionType) {
/*  820 */     ByteBuffer bytebuffer = ByteBuffer.allocate(5);
/*      */     
/*  822 */     bytebuffer.putInt(1);
/*  823 */     bytebuffer.put((byte)(compressionType.compressionTypeId() | 0x80));
/*  824 */     bytebuffer.flip();
/*  825 */     return bytebuffer;
/*      */   }
/*      */   
/*      */   private b a(Path java_nio_file_path, ByteBuffer bytebuffer) throws IOException {
/*  829 */     Path java_nio_file_path1 = Files.createTempFile(this.e, "tmp", (String)null, (FileAttribute<?>[])new FileAttribute[0]);
/*  830 */     FileChannel filechannel = FileChannel.open(java_nio_file_path1, new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.WRITE });
/*  831 */     Throwable throwable = null;
/*      */     
/*      */     try {
/*  834 */       bytebuffer.position(5);
/*  835 */       filechannel.write(bytebuffer);
/*  836 */     } catch (Throwable throwable1) {
/*  837 */       throwable = throwable1;
/*  838 */       ServerInternalException.reportInternalException(throwable);
/*  839 */       throw throwable1;
/*      */     } finally {
/*  841 */       if (filechannel != null) {
/*  842 */         if (throwable != null) {
/*      */           try {
/*  844 */             filechannel.close();
/*  845 */           } catch (Throwable throwable2) {
/*  846 */             throwable.addSuppressed(throwable2);
/*      */           } 
/*      */         } else {
/*  849 */           filechannel.close();
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  855 */     return () -> Files.move(java_nio_file_path1, java_nio_file_path, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*      */   }
/*      */ 
/*      */   
/*      */   private final void flushHeader() throws IOException {
/*  860 */     b();
/*      */   } private void c() throws IOException {
/*  862 */     this.g.position(0);
/*  863 */     this.dataFile.write(this.g, 0L);
/*      */   }
/*      */   
/*      */   private int getOffset(ChunkCoordIntPair chunkcoordintpair) {
/*  867 */     return this.h.get(g(chunkcoordintpair));
/*      */   }
/*      */   
/*      */   public boolean chunkExists(ChunkCoordIntPair chunkcoordintpair) {
/*  871 */     return (getOffset(chunkcoordintpair) != 0);
/*      */   }
/*      */   private static int getChunkLocation(int x, int z) {
/*  874 */     return (x & 0x1F) + (z & 0x1F) * 32;
/*      */   } private static int g(ChunkCoordIntPair chunkcoordintpair) {
/*  876 */     return chunkcoordintpair.j() + chunkcoordintpair.k() * 32;
/*      */   }
/*      */ 
/*      */   
/*      */   public void close() throws IOException {
/*  881 */     this.fileLock.lock();
/*  882 */     synchronized (this) {
/*      */       
/*      */       try {
/*  885 */         this.closed = true;
/*      */         try {
/*  887 */           d();
/*      */         } finally {
/*      */           try {
/*  890 */             this.dataFile.force(true);
/*      */           } finally {
/*  892 */             this.dataFile.close();
/*      */           } 
/*      */         } 
/*      */       } finally {
/*  896 */         this.fileLock.unlock();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void d() throws IOException {
/*  903 */     int i = (int)this.dataFile.size();
/*  904 */     int j = c(i) * 4096;
/*      */     
/*  906 */     if (i != j) {
/*  907 */       ByteBuffer bytebuffer = c.duplicate();
/*      */       
/*  909 */       bytebuffer.position(0);
/*  910 */       this.dataFile.write(bytebuffer, (j - 1));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RegionFile(Path java_nio_file_path, Path java_nio_file_path1, RegionFileCompression regionfilecompression, boolean flag, boolean canRecalcHeader) throws IOException {
/*  921 */     this.oversized = new byte[1024];
/*  922 */     this.oversizedCount = 0; this.g = ByteBuffer.allocateDirect(8192); this.canRecalcHeader = canRecalcHeader; this.file = java_nio_file_path.toFile(); initOversizedState(); this.freeSectors = new RegionFileBitSet(); this.f = regionfilecompression; if (!Files.isDirectory(java_nio_file_path1, new java.nio.file.LinkOption[0]))
/*      */       throw new IllegalArgumentException("Expected directory, got " + java_nio_file_path1.toAbsolutePath());  this.e = java_nio_file_path1; this.h = this.g.asIntBuffer(); this.h.limit(1024); this.g.position(4096); this.i = this.g.asIntBuffer(); if (flag) { this.dataFile = FileChannel.open(java_nio_file_path, new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.DSYNC }); } else { this.dataFile = FileChannel.open(java_nio_file_path, new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE }); }  this.freeSectors.a(0, 2); this.g.position(0); int i = this.dataFile.read(this.g, 0L); if (i != -1) { if (i != 8192)
/*      */         LOGGER.warn("Region file {} has truncated header: {}", java_nio_file_path, Integer.valueOf(i));  long j = Files.size(java_nio_file_path), regionFileSize = j; boolean needsHeaderRecalc = false; boolean hasBackedUp = false; for (int k = 0; k < 1024; k++) { int l = this.h.get(k), headerLocation = k; if (l != 0) { int i1 = b(l), offset = i1; int j1 = a(l); if (j1 == 255) { ByteBuffer realLen = ByteBuffer.allocate(4); this.dataFile.read(realLen, (i1 * 4096)); j1 = (realLen.getInt(0) + 4) / 4096 + 1; }  int sectorLength = j1; if (i1 < 2) { LOGGER.warn("Region file {} has invalid sector at index: {}; sector {} overlaps with header", java_nio_file_path, Integer.valueOf(k), Integer.valueOf(i1)); } else if (j1 <= 0) { LOGGER.warn("Region file {} has an invalid sector at index: {}; size has to be > 0", java_nio_file_path, Integer.valueOf(k)); } else if (i1 * 4096L > j) { LOGGER.warn("Region file {} has an invalid sector at index: {}; sector {} is out of bounds", java_nio_file_path, Integer.valueOf(k), Integer.valueOf(i1)); }  if (offset < 2 || sectorLength <= 0 || offset * 4096L > regionFileSize) { if (canRecalcHeader) { MinecraftServer.LOGGER.error("Detected invalid header for regionfile " + this.file.getAbsolutePath() + "! Recalculating header..."); needsHeaderRecalc = true; break; }  MinecraftServer.LOGGER.fatal("Detected invalid header for regionfile " + this.file.getAbsolutePath() + "! Cannot recalculate, removing local chunk (" + (headerLocation & 0x1F) + "," + (headerLocation >>> 5) + ") from header"); if (!hasBackedUp) { hasBackedUp = true; backupRegionFile(); }  getTimestamps().put(headerLocation, 0); getOffsets().put(headerLocation, 0); } else { boolean failedToAllocate = !this.freeSectors.tryAllocate(offset, sectorLength); if (failedToAllocate)
/*  925 */               MinecraftServer.LOGGER.error("Overlapping allocation by local chunk (" + (headerLocation & 0x1F) + "," + (headerLocation >>> 5) + ") in regionfile " + this.file.getAbsolutePath());  if ((failedToAllocate & (!canRecalcHeader ? 1 : 0)) != 0) { MinecraftServer.LOGGER.fatal("Detected invalid header for regionfile " + this.file.getAbsolutePath() + "! Cannot recalculate, removing local chunk (" + (headerLocation & 0x1F) + "," + (headerLocation >>> 5) + ") from header"); if (!hasBackedUp) { hasBackedUp = true; backupRegionFile(); }  getTimestamps().put(headerLocation, 0); getOffsets().put(headerLocation, 0); } else { needsHeaderRecalc |= failedToAllocate; }  }  }  }  if (needsHeaderRecalc) { MinecraftServer.LOGGER.error("Recalculating regionfile " + this.file.getAbsolutePath() + ", header gave erroneous offsets & locations"); recalculateHeader(); }  }  } private synchronized void initOversizedState() throws IOException { File metaFile = getOversizedMetaFile();
/*  926 */     if (metaFile.exists()) {
/*  927 */       byte[] read = Files.readAllBytes(metaFile.toPath());
/*  928 */       System.arraycopy(read, 0, this.oversized, 0, this.oversized.length);
/*  929 */       for (byte temp : this.oversized) {
/*  930 */         this.oversizedCount += temp;
/*      */       }
/*      */     }  }
/*      */ 
/*      */   
/*      */   private static int getChunkIndex(int x, int z) {
/*  936 */     return (x & 0x1F) + (z & 0x1F) * 32;
/*      */   }
/*      */   synchronized boolean isOversized(int x, int z) {
/*  939 */     return (this.oversized[getChunkIndex(x, z)] == 1);
/*      */   }
/*      */   synchronized void setOversized(int x, int z, boolean oversized) throws IOException {
/*  942 */     int offset = getChunkIndex(x, z);
/*  943 */     boolean previous = (this.oversized[offset] == 1);
/*  944 */     this.oversized[offset] = (byte)(oversized ? 1 : 0);
/*  945 */     if (!previous && oversized) {
/*  946 */       this.oversizedCount++;
/*  947 */     } else if (!oversized && previous) {
/*  948 */       this.oversizedCount--;
/*      */     } 
/*  950 */     if (previous && !oversized) {
/*  951 */       File oversizedFile = getOversizedFile(x, z);
/*  952 */       if (oversizedFile.exists()) {
/*  953 */         oversizedFile.delete();
/*      */       }
/*      */     } 
/*  956 */     if (this.oversizedCount > 0) {
/*  957 */       if (previous != oversized) {
/*  958 */         writeOversizedMeta();
/*      */       }
/*  960 */     } else if (previous) {
/*  961 */       File oversizedMetaFile = getOversizedMetaFile();
/*  962 */       if (oversizedMetaFile.exists()) {
/*  963 */         oversizedMetaFile.delete();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeOversizedMeta() throws IOException {
/*  969 */     Files.write(getOversizedMetaFile().toPath(), this.oversized, new OpenOption[0]);
/*      */   }
/*      */   
/*      */   private File getOversizedMetaFile() {
/*  973 */     return new File(this.file.getParentFile(), this.file.getName().replaceAll("\\.mca$", "") + ".oversized.nbt");
/*      */   }
/*      */   
/*      */   private File getOversizedFile(int x, int z) {
/*  977 */     return new File(this.file.getParentFile(), this.file.getName().replaceAll("\\.mca$", "") + "_oversized_" + x + "_" + z + ".nbt");
/*      */   }
/*      */   
/*      */   synchronized NBTTagCompound getOversizedData(int x, int z) throws IOException {
/*  981 */     File file = getOversizedFile(x, z);
/*  982 */     DataInputStream out = new DataInputStream(new BufferedInputStream(new InflaterInputStream(new FileInputStream(file)))); try {
/*  983 */       NBTTagCompound nBTTagCompound = NBTCompressedStreamTools.readNBT(out);
/*  984 */       out.close();
/*      */       return nBTTagCompound;
/*      */     } catch (Throwable throwable) {
/*      */       try {
/*      */         out.close();
/*      */       } catch (Throwable throwable1) {
/*      */         throwable.addSuppressed(throwable1);
/*      */       } 
/*      */       throw throwable;
/*  993 */     }  } class ChunkBuffer extends ByteArrayOutputStream { private final ChunkCoordIntPair b; public ChunkBuffer(ChunkCoordIntPair chunkcoordintpair) { super(8096);
/*  994 */       write(0);
/*  995 */       write(0);
/*  996 */       write(0);
/*  997 */       write(0);
/*  998 */       write(RegionFile.this.f.a());
/*  999 */       this.b = chunkcoordintpair; }
/*      */ 
/*      */     
/*      */     public void close() throws IOException {
/* 1003 */       ByteBuffer bytebuffer = ByteBuffer.wrap(this.buf, 0, this.count);
/*      */       
/* 1005 */       bytebuffer.putInt(0, this.count - 5 + 1);
/* 1006 */       RegionFile.this.a(this.b, bytebuffer);
/*      */     } }
/*      */ 
/*      */   
/*      */   static interface b {
/*      */     void run() throws IOException;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegionFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */