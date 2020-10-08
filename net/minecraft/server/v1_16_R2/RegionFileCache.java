/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.destroystokyo.paper.exception.ServerInternalException;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.NullOutputStream;
/*     */ 
/*     */ public class RegionFileCache implements AutoCloseable {
/*  15 */   public final Long2ObjectLinkedOpenHashMap<RegionFile> cache = new Long2ObjectLinkedOpenHashMap(); private final File b; private final boolean c;
/*     */   private final boolean isChunkData;
/*     */   private static final int DEFAULT_SIZE_THRESHOLD = 8192;
/*     */   private static final int OVERZEALOUS_TOTAL_THRESHOLD = 65536;
/*     */   private static final int OVERZEALOUS_THRESHOLD = 1024;
/*     */   
/*     */   RegionFileCache(File file, boolean flag) {
/*  22 */     this(file, flag, false);
/*     */   }
/*     */   RegionFileCache(File file, boolean flag, boolean isChunkData) {
/*  25 */     this.isChunkData = isChunkData;
/*     */     
/*  27 */     this.b = file;
/*  28 */     this.c = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ChunkCoordIntPair getRegionFileCoordinates(File file) {
/*  33 */     String fileName = file.getName();
/*  34 */     if (!fileName.startsWith("r.") || !fileName.endsWith(".mca")) {
/*  35 */       return null;
/*     */     }
/*     */     
/*  38 */     String[] split = fileName.split("\\.");
/*     */     
/*  40 */     if (split.length != 4) {
/*  41 */       return null;
/*     */     }
/*     */     
/*     */     try {
/*  45 */       int x = Integer.parseInt(split[1]);
/*  46 */       int z = Integer.parseInt(split[2]);
/*     */       
/*  48 */       return new ChunkCoordIntPair(x << 5, z << 5);
/*  49 */     } catch (NumberFormatException ex) {
/*  50 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized RegionFile getRegionFileIfLoaded(ChunkCoordIntPair chunkcoordintpair) {
/*  58 */     return (RegionFile)this.cache.getAndMoveToFirst(ChunkCoordIntPair.pair(chunkcoordintpair.getRegionX(), chunkcoordintpair.getRegionZ()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized RegionFile getFile(ChunkCoordIntPair chunkcoordintpair, boolean existingOnly) throws IOException {
/*  64 */     return getFile(chunkcoordintpair, existingOnly, false);
/*     */   }
/*     */   
/*     */   public synchronized RegionFile getFile(ChunkCoordIntPair chunkcoordintpair, boolean existingOnly, boolean lock) throws IOException {
/*  68 */     long i = ChunkCoordIntPair.pair(chunkcoordintpair.getRegionX(), chunkcoordintpair.getRegionZ());
/*  69 */     RegionFile regionfile = (RegionFile)this.cache.getAndMoveToFirst(i);
/*     */     
/*  71 */     if (regionfile != null) {
/*     */       
/*  73 */       if (lock)
/*     */       {
/*  75 */         regionfile.fileLock.lock();
/*     */       }
/*     */       
/*  78 */       return regionfile;
/*     */     } 
/*  80 */     if (this.cache.size() >= PaperConfig.regionFileCacheSize) {
/*  81 */       ((RegionFile)this.cache.removeLast()).close();
/*     */     }
/*     */     
/*  84 */     if (!this.b.exists()) {
/*  85 */       this.b.mkdirs();
/*     */     }
/*     */     
/*  88 */     File file = new File(this.b, "r." + chunkcoordintpair.getRegionX() + "." + chunkcoordintpair.getRegionZ() + ".mca");
/*  89 */     if (existingOnly && !file.exists()) return null; 
/*  90 */     RegionFile regionfile1 = new RegionFile(file, this.b, this.c, this.isChunkData);
/*     */     
/*  92 */     this.cache.putAndMoveToFirst(i, regionfile1);
/*     */     
/*  94 */     if (lock)
/*     */     {
/*  96 */       regionfile1.fileLock.lock();
/*     */     }
/*     */     
/*  99 */     return regionfile1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void printOversizedLog(String msg, File file, int x, int z) {
/* 105 */     LogManager.getLogger().fatal(msg + " (" + file.toString().replaceAll(".+[\\\\/]", "") + " - " + x + "," + z + ") Go clean it up to remove this message. /minecraft:tp " + (x << 4) + " 128 " + (z << 4) + " - DO NOT REPORT THIS TO PAPER - You may ask for help on Discord, but do not file an issue. These error messages can not be removed.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   private static int SIZE_THRESHOLD = 8192;
/*     */   private static void resetFilterThresholds() {
/* 113 */     SIZE_THRESHOLD = Math.max(4096, Integer.getInteger("Paper.FilterThreshhold", 8192).intValue());
/*     */   }
/*     */   static {
/* 116 */     resetFilterThresholds();
/*     */   }
/*     */   
/*     */   static boolean isOverzealous() {
/* 120 */     return (SIZE_THRESHOLD == 1024);
/*     */   }
/*     */ 
/*     */   
/*     */   private static NBTTagCompound readOversizedChunk(RegionFile regionfile, ChunkCoordIntPair chunkCoordinate) throws IOException {
/* 125 */     synchronized (regionfile) {
/* 126 */       DataInputStream datainputstream = regionfile.getReadStream(chunkCoordinate); 
/* 127 */       try { NBTTagCompound oversizedData = regionfile.getOversizedData(chunkCoordinate.x, chunkCoordinate.z);
/* 128 */         NBTTagCompound chunk = NBTCompressedStreamTools.readNBT(datainputstream);
/* 129 */         if (oversizedData == null)
/* 130 */         { NBTTagCompound nBTTagCompound = chunk;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 141 */           if (datainputstream != null) datainputstream.close();  return nBTTagCompound; }  NBTTagCompound oversizedLevel = oversizedData.getCompound("Level"); NBTTagCompound level = chunk.getCompound("Level"); mergeChunkList(level, oversizedLevel, "Entities"); mergeChunkList(level, oversizedLevel, "TileEntities"); chunk.set("Level", level); NBTTagCompound nBTTagCompound1 = chunk; if (datainputstream != null) datainputstream.close();  return nBTTagCompound1; }
/*     */       catch (Throwable throwable) { if (datainputstream != null)
/*     */           try {
/*     */             datainputstream.close();
/*     */           } catch (Throwable throwable1) {
/*     */             throwable.addSuppressed(throwable1);
/*     */           }   throw throwable; }
/*     */     
/* 149 */     }  } private static void mergeChunkList(NBTTagCompound level, NBTTagCompound oversizedLevel, String key) { NBTTagList levelList = level.getList(key, 10);
/* 150 */     NBTTagList oversizedList = oversizedLevel.getList(key, 10);
/*     */     
/* 152 */     if (!oversizedList.isEmpty()) {
/* 153 */       levelList.addAll(oversizedList);
/* 154 */       level.set(key, levelList);
/*     */     }  }
/*     */ 
/*     */   
/*     */   private static int getNBTSize(NBTBase nbtBase) {
/* 159 */     DataOutputStream test = new DataOutputStream((OutputStream)new NullOutputStream());
/*     */     try {
/* 161 */       nbtBase.write(test);
/* 162 */       return test.size();
/* 163 */     } catch (IOException e) {
/* 164 */       e.printStackTrace();
/* 165 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound read(ChunkCoordIntPair chunkcoordintpair) throws IOException {
/* 174 */     RegionFile regionfile = getFile(chunkcoordintpair, true, true);
/* 175 */     if (regionfile == null) {
/* 176 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 180 */     return readFromRegionFile(regionfile, chunkcoordintpair);
/*     */   }
/*     */ 
/*     */   
/*     */   private NBTTagCompound readFromRegionFile(RegionFile regionfile, ChunkCoordIntPair chunkcoordintpair) throws IOException {
/*     */     try {
/*     */       NBTTagCompound nbttagcompound;
/* 187 */       DataInputStream datainputstream = regionfile.a(chunkcoordintpair);
/*     */       
/* 189 */       if (regionfile.isOversized(chunkcoordintpair.x, chunkcoordintpair.z)) {
/* 190 */         printOversizedLog("Loading Oversized Chunk!", regionfile.file, chunkcoordintpair.x, chunkcoordintpair.z);
/* 191 */         return readOversizedChunk(regionfile, chunkcoordintpair);
/*     */       } 
/*     */       
/* 194 */       Throwable throwable = null;
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 199 */         if (datainputstream != null) {
/* 200 */           NBTTagCompound nBTTagCompound = NBTCompressedStreamTools.a(datainputstream);
/*     */           
/* 202 */           if (this.isChunkData) {
/* 203 */             ChunkCoordIntPair chunkPos = ChunkRegionLoader.getChunkCoordinate(nBTTagCompound);
/* 204 */             if (!chunkPos.equals(chunkcoordintpair)) {
/* 205 */               MinecraftServer.LOGGER.error("Attempting to read chunk data at " + chunkcoordintpair.toString() + " but got chunk data for " + chunkPos.toString() + " instead! Attempting regionfile recalculation for regionfile " + regionfile.file.getAbsolutePath());
/* 206 */               regionfile.recalculateHeader();
/* 207 */               regionfile.fileLock.lock();
/* 208 */               return readFromRegionFile(regionfile, chunkcoordintpair);
/*     */             } 
/*     */           } 
/*     */           
/* 212 */           return nBTTagCompound;
/*     */         } 
/*     */         
/* 215 */         nbttagcompound = null;
/* 216 */       } catch (Throwable throwable1) {
/* 217 */         throwable = throwable1;
/* 218 */         throw throwable1;
/*     */       } finally {
/* 220 */         if (datainputstream != null) {
/* 221 */           if (throwable != null) {
/*     */             try {
/* 223 */               datainputstream.close();
/* 224 */             } catch (Throwable throwable2) {
/* 225 */               throwable.addSuppressed(throwable2);
/*     */             } 
/*     */           } else {
/* 228 */             datainputstream.close();
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 234 */       return nbttagcompound;
/*     */     } finally {
/* 236 */       regionfile.fileLock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void write(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) throws IOException {
/* 241 */     RegionFile regionfile = getFile(chunkcoordintpair, false, true); try {
/*     */       Exception laste;
/* 243 */       for (int attempts = 0; attempts++ < 5;) { try {
/* 244 */           DataOutputStream dataoutputstream = regionfile.c(chunkcoordintpair);
/* 245 */           Throwable throwable = null;
/*     */           
/*     */           try {
/* 248 */             NBTCompressedStreamTools.a(nbttagcompound, dataoutputstream);
/* 249 */             regionfile.setStatus(chunkcoordintpair.x, chunkcoordintpair.z, ChunkRegionLoader.getStatus(nbttagcompound));
/* 250 */             regionfile.setOversized(chunkcoordintpair.x, chunkcoordintpair.z, false);
/* 251 */           } catch (Throwable throwable1) {
/* 252 */             throwable = throwable1;
/* 253 */             throw throwable1;
/*     */           } finally {
/* 255 */             if (dataoutputstream != null) {
/* 256 */               if (throwable != null) {
/*     */                 try {
/* 258 */                   dataoutputstream.close();
/* 259 */                 } catch (Throwable throwable2) {
/* 260 */                   throwable.addSuppressed(throwable2);
/*     */                 } 
/*     */               } else {
/* 263 */                 dataoutputstream.close();
/*     */               } 
/*     */             }
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/* 271 */         } catch (Exception ex) {
/* 272 */           laste = ex;
/*     */         }  }
/*     */ 
/*     */       
/* 276 */       if (laste != null) {
/* 277 */         ServerInternalException.reportInternalException(laste);
/* 278 */         MinecraftServer.LOGGER.error("Failed to save chunk", laste);
/*     */       } 
/*     */     } finally {
/*     */       
/* 282 */       regionfile.fileLock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void close() throws IOException {
/* 287 */     ExceptionSuppressor<IOException> exceptionsuppressor = new ExceptionSuppressor<>();
/* 288 */     ObjectIterator objectiterator = this.cache.values().iterator();
/*     */     
/* 290 */     while (objectiterator.hasNext()) {
/* 291 */       RegionFile regionfile = (RegionFile)objectiterator.next();
/*     */       
/*     */       try {
/* 294 */         regionfile.close();
/* 295 */       } catch (IOException ioexception) {
/* 296 */         exceptionsuppressor.a(ioexception);
/*     */       } 
/*     */     } 
/*     */     
/* 300 */     exceptionsuppressor.a();
/*     */   }
/*     */   
/*     */   public void a() throws IOException {
/* 304 */     ObjectIterator objectiterator = this.cache.values().iterator();
/*     */     
/* 306 */     while (objectiterator.hasNext()) {
/* 307 */       RegionFile regionfile = (RegionFile)objectiterator.next();
/*     */       
/* 309 */       regionfile.a();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean chunkExists(ChunkCoordIntPair pos) throws IOException {
/* 316 */     RegionFile regionfile = getFile(pos, true);
/*     */     
/* 318 */     return (regionfile != null) ? regionfile.chunkExists(pos) : false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegionFileCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */