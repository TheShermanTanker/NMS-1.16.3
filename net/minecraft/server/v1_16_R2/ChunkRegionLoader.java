/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Maps;
/*     */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortList;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortListIterator;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.BitSet;
/*     */ import java.util.Collection;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ 
/*     */ public class ChunkRegionLoader {
/*  25 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getLastWorldSaveTime(NBTTagCompound chunkData) {
/*  30 */     NBTTagCompound levelData = chunkData.getCompound("Level");
/*  31 */     return levelData.getLong("LastUpdate");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ChunkCoordIntPair getChunkCoordinate(NBTTagCompound chunkData) {
/*  38 */     NBTTagCompound levelData = chunkData.getCompound("Level");
/*  39 */     return new ChunkCoordIntPair(levelData.getInt("xPos"), levelData.getInt("zPos"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class InProgressChunkHolder
/*     */   {
/*     */     public final ProtoChunk protoChunk;
/*     */     
/*     */     public final ArrayDeque<Runnable> tasks;
/*     */     public NBTTagCompound poiData;
/*     */     
/*     */     public InProgressChunkHolder(ProtoChunk protoChunk, ArrayDeque<Runnable> tasks) {
/*  51 */       this.protoChunk = protoChunk;
/*  52 */       this.tasks = tasks;
/*     */     }
/*     */   }
/*     */   
/*     */   public static ProtoChunk loadChunk(WorldServer worldserver, DefinedStructureManager definedstructuremanager, VillagePlace villageplace, ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) {
/*  57 */     InProgressChunkHolder holder = loadChunk(worldserver, definedstructuremanager, villageplace, chunkcoordintpair, nbttagcompound, true);
/*  58 */     holder.tasks.forEach(Runnable::run);
/*  59 */     return holder.protoChunk;
/*     */   }
/*     */ 
/*     */   
/*  63 */   private static final int CURRENT_DATA_VERSION = SharedConstants.getGameVersion().getWorldVersion();
/*  64 */   private static final boolean JUST_CORRUPT_IT = Boolean.getBoolean("Paper.ignoreWorldDataVersion");
/*     */   
/*     */   public static InProgressChunkHolder loadChunk(WorldServer worldserver, DefinedStructureManager definedstructuremanager, VillagePlace villageplace, ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound, boolean distinguish) {
/*     */     Object object;
/*  68 */     ArrayDeque<Runnable> tasksToExecuteOnMain = new ArrayDeque<>();
/*     */     
/*  70 */     ChunkGenerator chunkgenerator = worldserver.getChunkProvider().getChunkGenerator();
/*     */     
/*  72 */     if (nbttagcompound.hasKeyOfType("DataVersion", 99)) {
/*  73 */       int dataVersion = nbttagcompound.getInt("DataVersion");
/*  74 */       if (!JUST_CORRUPT_IT && dataVersion > CURRENT_DATA_VERSION) {
/*  75 */         (new RuntimeException("Server attempted to load chunk saved with newer version of minecraft! " + dataVersion + " > " + CURRENT_DATA_VERSION)).printStackTrace();
/*  76 */         System.exit(1);
/*     */       } 
/*     */     } 
/*     */     
/*  80 */     WorldChunkManager worldchunkmanager = chunkgenerator.getWorldChunkManager();
/*  81 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Level");
/*  82 */     ChunkCoordIntPair chunkcoordintpair1 = new ChunkCoordIntPair(nbttagcompound1.getInt("xPos"), nbttagcompound1.getInt("zPos"));
/*     */     
/*  84 */     if (!Objects.equals(chunkcoordintpair, chunkcoordintpair1)) {
/*  85 */       LOGGER.error("Chunk file at {} is in the wrong location; relocating. (Expected {}, got {})", chunkcoordintpair, chunkcoordintpair, chunkcoordintpair1);
/*     */     }
/*     */     
/*  88 */     BiomeStorage biomestorage = new BiomeStorage(worldserver.r().b(IRegistry.ay), chunkcoordintpair, worldchunkmanager, nbttagcompound1.hasKeyOfType("Biomes", 11) ? nbttagcompound1.getIntArray("Biomes") : null);
/*  89 */     ChunkConverter chunkconverter = nbttagcompound1.hasKeyOfType("UpgradeData", 10) ? new ChunkConverter(nbttagcompound1.getCompound("UpgradeData")) : ChunkConverter.a;
/*     */ 
/*     */     
/*  92 */     ProtoChunkTickList<Block> protochunkticklist = new ProtoChunkTickList<>(block -> (block == null || block.getBlockData().isAir()), chunkcoordintpair, nbttagcompound1.getList("ToBeTicked", 9));
/*     */ 
/*     */     
/*  95 */     ProtoChunkTickList<FluidType> protochunkticklist1 = new ProtoChunkTickList<>(fluidtype -> (fluidtype == null || fluidtype == FluidTypes.EMPTY), chunkcoordintpair, nbttagcompound1.getList("LiquidsToBeTicked", 9));
/*  96 */     boolean flag = nbttagcompound1.getBoolean("isLightOn");
/*  97 */     NBTTagList nbttaglist = nbttagcompound1.getList("Sections", 10);
/*  98 */     boolean flag1 = true;
/*  99 */     ChunkSection[] achunksection = new ChunkSection[16];
/* 100 */     boolean flag2 = worldserver.getDimensionManager().hasSkyLight();
/* 101 */     ChunkProviderServer chunkproviderserver = worldserver.getChunkProvider();
/* 102 */     LightEngine lightengine = chunkproviderserver.getLightEngine();
/*     */     
/* 104 */     if (flag) {
/* 105 */       tasksToExecuteOnMain.add(() -> lightengine.b(chunkcoordintpair, true));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 110 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 111 */       NBTTagCompound nbttagcompound2 = nbttaglist.getCompound(i);
/* 112 */       byte b0 = nbttagcompound2.getByte("Y");
/*     */       
/* 114 */       if (nbttagcompound2.hasKeyOfType("Palette", 9) && nbttagcompound2.hasKeyOfType("BlockStates", 12)) {
/* 115 */         ChunkSection chunksection = new ChunkSection(b0 << 4, null, worldserver, false);
/*     */         
/* 117 */         chunksection.getBlocks().a(nbttagcompound2.getList("Palette", 10), nbttagcompound2.getLongArray("BlockStates"));
/* 118 */         chunksection.recalcBlockCounts();
/* 119 */         if (!chunksection.c()) {
/* 120 */           achunksection[b0] = chunksection;
/*     */         }
/*     */         
/* 123 */         tasksToExecuteOnMain.add(() -> villageplace.a(chunkcoordintpair, chunksection));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 128 */       if (flag) {
/* 129 */         if (nbttagcompound2.hasKeyOfType("BlockLight", 7)) {
/*     */           
/* 131 */           NibbleArray blockLight = new NibbleArray(nbttagcompound2.getByteArray("BlockLight"));
/* 132 */           tasksToExecuteOnMain.add(() -> lightengine.a(EnumSkyBlock.BLOCK, SectionPosition.a(chunkcoordintpair, b0), blockLight, true));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 138 */         if (flag2 && nbttagcompound2.hasKeyOfType("SkyLight", 7)) {
/*     */           
/* 140 */           NibbleArray skyLight = new NibbleArray(nbttagcompound2.getByteArray("SkyLight"));
/* 141 */           tasksToExecuteOnMain.add(() -> lightengine.a(EnumSkyBlock.SKY, SectionPosition.a(chunkcoordintpair, b0), skyLight, true));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     long j = nbttagcompound1.getLong("InhabitedTime");
/* 150 */     ChunkStatus.Type chunkstatus_type = a(nbttagcompound);
/*     */ 
/*     */     
/* 153 */     if (chunkstatus_type == ChunkStatus.Type.LEVELCHUNK) {
/*     */       Object<Block> object1;
/*     */ 
/*     */       
/*     */       Object<FluidType> object2;
/*     */       
/* 159 */       if (nbttagcompound1.hasKeyOfType("TileTicks", 9)) {
/* 160 */         NBTTagList nbttaglist1 = nbttagcompound1.getList("TileTicks", 10);
/*     */         
/* 162 */         RegistryBlocks<Block> registryblocks = IRegistry.BLOCK;
/* 163 */         registryblocks.getClass();
/* 164 */         Objects.requireNonNull(IRegistry.BLOCK); Objects.requireNonNull(IRegistry.BLOCK); object1 = TickListChunk.a(nbttaglist1, IRegistry.BLOCK::getKey, IRegistry.BLOCK::get);
/*     */       } else {
/* 166 */         object1 = (Object<Block>)protochunkticklist;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 171 */       if (nbttagcompound1.hasKeyOfType("LiquidTicks", 9)) {
/* 172 */         NBTTagList nbttaglist1 = nbttagcompound1.getList("LiquidTicks", 10);
/*     */         
/* 174 */         RegistryBlocks<FluidType> registryblocks = IRegistry.FLUID;
/* 175 */         registryblocks.getClass();
/* 176 */         Objects.requireNonNull(IRegistry.FLUID); Objects.requireNonNull(IRegistry.FLUID); object2 = TickListChunk.a(nbttaglist1, IRegistry.FLUID::getKey, IRegistry.FLUID::get);
/*     */       } else {
/* 178 */         object2 = (Object<FluidType>)protochunkticklist1;
/*     */       } 
/*     */ 
/*     */       
/* 182 */       object = new Chunk(worldserver.getMinecraftWorld(), chunkcoordintpair, biomestorage, chunkconverter, (TickList<Block>)object1, (TickList<FluidType>)object2, j, achunksection, createLoadEntitiesConsumer(new SafeNBTCopy(nbttagcompound1, new String[] { "TileEntities", "Entities" })));
/*     */     } else {
/*     */       
/* 185 */       ProtoChunk protochunk = new ProtoChunk(chunkcoordintpair, chunkconverter, achunksection, protochunkticklist, protochunkticklist1, worldserver);
/*     */       
/* 187 */       protochunk.a(biomestorage);
/* 188 */       object = protochunk;
/* 189 */       protochunk.setInhabitedTime(j);
/* 190 */       protochunk.a(ChunkStatus.a(nbttagcompound1.getString("Status")));
/* 191 */       if (protochunk.getChunkStatus().b(ChunkStatus.FEATURES)) {
/* 192 */         protochunk.a(lightengine);
/*     */       }
/*     */       
/* 195 */       if (!flag && protochunk.getChunkStatus().b(ChunkStatus.LIGHT)) {
/* 196 */         Iterator<BlockPosition> iterator = BlockPosition.b(chunkcoordintpair.d(), 0, chunkcoordintpair.e(), chunkcoordintpair.f(), 255, chunkcoordintpair.g()).iterator();
/*     */         
/* 198 */         while (iterator.hasNext()) {
/* 199 */           BlockPosition blockposition = iterator.next();
/*     */           
/* 201 */           if (((IChunkAccess)object).getType(blockposition).f() != 0) {
/* 202 */             protochunk.k(blockposition);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 208 */     ((IChunkAccess)object).b(flag);
/* 209 */     NBTTagCompound nbttagcompound3 = nbttagcompound1.getCompound("Heightmaps");
/* 210 */     EnumSet<HeightMap.Type> enumset = EnumSet.noneOf(HeightMap.Type.class);
/* 211 */     Iterator<HeightMap.Type> iterator1 = ((IChunkAccess)object).getChunkStatus().h().iterator();
/*     */     
/* 213 */     while (iterator1.hasNext()) {
/* 214 */       HeightMap.Type heightmap_type = iterator1.next();
/* 215 */       String s = heightmap_type.b();
/*     */       
/* 217 */       if (nbttagcompound3.hasKeyOfType(s, 12)) {
/* 218 */         ((IChunkAccess)object).a(heightmap_type, nbttagcompound3.getLongArray(s)); continue;
/*     */       } 
/* 220 */       enumset.add(heightmap_type);
/*     */     } 
/*     */ 
/*     */     
/* 224 */     HeightMap.a((IChunkAccess)object, enumset);
/* 225 */     NBTTagCompound nbttagcompound4 = nbttagcompound1.getCompound("Structures");
/*     */     
/* 227 */     ((IChunkAccess)object).a(a(definedstructuremanager, nbttagcompound4, worldserver.getSeed()));
/* 228 */     ((IChunkAccess)object).b(a(chunkcoordintpair, nbttagcompound4));
/* 229 */     if (nbttagcompound1.getBoolean("shouldSave")) {
/* 230 */       ((IChunkAccess)object).setNeedsSaving(true);
/*     */     }
/*     */     
/* 233 */     NBTTagList nbttaglist2 = nbttagcompound1.getList("PostProcessing", 9);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     for (int l = 0; l < nbttaglist2.size(); l++) {
/* 239 */       NBTTagList nBTTagList = nbttaglist2.b(l);
/*     */       
/* 241 */       for (int m = 0; m < nBTTagList.size(); m++) {
/* 242 */         ((IChunkAccess)object).a(nBTTagList.d(m), l);
/*     */       }
/*     */     } 
/*     */     
/* 246 */     if (chunkstatus_type == ChunkStatus.Type.LEVELCHUNK) {
/* 247 */       return new InProgressChunkHolder(new ProtoChunkExtension((Chunk)object), tasksToExecuteOnMain);
/*     */     }
/* 249 */     ProtoChunk protochunk1 = (ProtoChunk)object;
/*     */     
/* 251 */     NBTTagList nbttaglist3 = nbttagcompound1.getList("Entities", 10);
/*     */     
/* 253 */     for (int k = 0; k < nbttaglist3.size(); k++) {
/* 254 */       protochunk1.b(nbttaglist3.getCompound(k));
/*     */     }
/*     */     
/* 257 */     NBTTagList nbttaglist4 = nbttagcompound1.getList("TileEntities", 10);
/*     */ 
/*     */ 
/*     */     
/* 261 */     for (int i1 = 0; i1 < nbttaglist4.size(); i1++) {
/* 262 */       NBTTagCompound nBTTagCompound = nbttaglist4.getCompound(i1);
/* 263 */       ((IChunkAccess)object).a(nBTTagCompound);
/*     */     } 
/*     */     
/* 266 */     NBTTagList nbttaglist5 = nbttagcompound1.getList("Lights", 9);
/*     */     
/* 268 */     for (int j1 = 0; j1 < nbttaglist5.size(); j1++) {
/* 269 */       NBTTagList nbttaglist6 = nbttaglist5.b(j1);
/*     */       
/* 271 */       for (int k1 = 0; k1 < nbttaglist6.size(); k1++) {
/* 272 */         protochunk1.b(nbttaglist6.d(k1), j1);
/*     */       }
/*     */     } 
/*     */     
/* 276 */     NBTTagCompound nbttagcompound5 = nbttagcompound1.getCompound("CarvingMasks");
/* 277 */     Iterator<String> iterator2 = nbttagcompound5.getKeys().iterator();
/*     */     
/* 279 */     while (iterator2.hasNext()) {
/* 280 */       String s1 = iterator2.next();
/* 281 */       WorldGenStage.Features worldgenstage_features = WorldGenStage.Features.valueOf(s1);
/*     */       
/* 283 */       protochunk1.a(worldgenstage_features, BitSet.valueOf(nbttagcompound5.getByteArray(s1)));
/*     */     } 
/*     */     
/* 286 */     return new InProgressChunkHolder(protochunk1, tasksToExecuteOnMain);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class SafeNBTCopy
/*     */     extends NBTTagCompound
/*     */   {
/* 295 */     private final Set<String> keys = new HashSet<>();
/*     */     public SafeNBTCopy(NBTTagCompound base, String... keys) {
/* 297 */       for (String key : keys) {
/* 298 */         this.keys.add(key);
/* 299 */         set(key, base.get(key));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasKey(String s) {
/* 305 */       if (this.keys.contains(s)) {
/* 306 */         return true;
/*     */       }
/* 308 */       throw new IllegalStateException("Missing Key " + s + " in SafeNBTCopy");
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasKeyOfType(String s, int i) {
/* 313 */       return (hasKey(s) && super.hasKeyOfType(s, i));
/*     */     } }
/*     */   
/*     */   private static Consumer<Chunk> createLoadEntitiesConsumer(NBTTagCompound nbt) {
/* 317 */     return chunk -> loadEntities(nbt, chunk);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class AsyncSaveData
/*     */   {
/*     */     public final NibbleArray[] blockLight;
/*     */     
/*     */     public final NibbleArray[] skyLight;
/*     */     
/*     */     public final NBTTagList blockTickList;
/*     */     
/*     */     public final NBTTagList fluidTickList;
/*     */     public final long worldTime;
/*     */     
/*     */     public AsyncSaveData(NibbleArray[] blockLight, NibbleArray[] skyLight, NBTTagList blockTickList, NBTTagList fluidTickList, long worldTime) {
/* 333 */       this.blockLight = blockLight;
/* 334 */       this.skyLight = skyLight;
/* 335 */       this.blockTickList = blockTickList;
/* 336 */       this.fluidTickList = fluidTickList;
/* 337 */       this.worldTime = worldTime;
/*     */     }
/*     */   }
/*     */   
/*     */   public static AsyncSaveData getAsyncSaveData(WorldServer world, IChunkAccess chunk) {
/*     */     NBTTagList blockTickListSerialized, fluidTickListSerialized;
/* 343 */     AsyncCatcher.catchOp("preparation of chunk data for async save");
/* 344 */     ChunkCoordIntPair chunkPos = chunk.getPos();
/*     */     
/* 346 */     LightEngineThreaded lightenginethreaded = world.getChunkProvider().getLightEngine();
/*     */     
/* 348 */     NibbleArray[] blockLight = new NibbleArray[18];
/* 349 */     NibbleArray[] skyLight = new NibbleArray[18];
/*     */     
/* 351 */     for (int i = -1; i < 17; i++) {
/* 352 */       NibbleArray blockArray = lightenginethreaded.a(EnumSkyBlock.BLOCK).a(SectionPosition.a(chunkPos, i));
/* 353 */       NibbleArray skyArray = lightenginethreaded.a(EnumSkyBlock.SKY).a(SectionPosition.a(chunkPos, i));
/*     */ 
/*     */       
/* 356 */       if (blockArray != null) {
/* 357 */         blockArray = blockArray.copy();
/*     */       }
/* 359 */       if (skyArray != null) {
/* 360 */         skyArray = skyArray.copy();
/*     */       }
/*     */ 
/*     */       
/* 364 */       blockLight[i + 1] = blockArray;
/* 365 */       skyLight[i + 1] = skyArray;
/*     */     } 
/*     */     
/* 368 */     TickList<Block> blockTickList = chunk.n();
/*     */ 
/*     */     
/* 371 */     if (blockTickList instanceof ProtoChunkTickList || blockTickList instanceof TickListChunk) {
/* 372 */       blockTickListSerialized = null;
/*     */     } else {
/* 374 */       blockTickListSerialized = world.getBlockTickList().a(chunkPos);
/*     */     } 
/*     */     
/* 377 */     TickList<FluidType> fluidTickList = chunk.o();
/*     */ 
/*     */     
/* 380 */     if (fluidTickList instanceof ProtoChunkTickList || fluidTickList instanceof TickListChunk) {
/* 381 */       fluidTickListSerialized = null;
/*     */     } else {
/* 383 */       fluidTickListSerialized = world.getFluidTickList().a(chunkPos);
/*     */     } 
/*     */     
/* 386 */     return new AsyncSaveData(blockLight, skyLight, blockTickListSerialized, fluidTickListSerialized, world.getTime());
/*     */   }
/*     */   
/*     */   public static NBTTagCompound saveChunk(WorldServer worldserver, IChunkAccess ichunkaccess) {
/* 390 */     return saveChunk(worldserver, ichunkaccess, null);
/*     */   }
/*     */   
/*     */   public static NBTTagCompound saveChunk(WorldServer worldserver, IChunkAccess ichunkaccess, AsyncSaveData asyncsavedata) {
/* 394 */     ChunkCoordIntPair chunkcoordintpair = ichunkaccess.getPos();
/* 395 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 396 */     NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */     
/* 398 */     nbttagcompound.setInt("DataVersion", SharedConstants.getGameVersion().getWorldVersion());
/* 399 */     nbttagcompound.set("Level", nbttagcompound1);
/* 400 */     nbttagcompound1.setInt("xPos", chunkcoordintpair.x);
/* 401 */     nbttagcompound1.setInt("zPos", chunkcoordintpair.z);
/* 402 */     nbttagcompound1.setLong("LastUpdate", (asyncsavedata != null) ? asyncsavedata.worldTime : worldserver.getTime());
/* 403 */     nbttagcompound1.setLong("InhabitedTime", ichunkaccess.getInhabitedTime());
/* 404 */     nbttagcompound1.setString("Status", ichunkaccess.getChunkStatus().d());
/* 405 */     ChunkConverter chunkconverter = ichunkaccess.p();
/*     */     
/* 407 */     if (!chunkconverter.a()) {
/* 408 */       nbttagcompound1.set("UpgradeData", chunkconverter.b());
/*     */     }
/*     */     
/* 411 */     ChunkSection[] achunksection = ichunkaccess.getSections();
/* 412 */     NBTTagList nbttaglist = new NBTTagList();
/* 413 */     LightEngineThreaded lightenginethreaded = worldserver.getChunkProvider().getLightEngine();
/* 414 */     boolean flag = ichunkaccess.r();
/*     */ 
/*     */ 
/*     */     
/* 418 */     for (int i = -1; i < 17; i++) {
/* 419 */       NibbleArray nibblearray, nibblearray1; int finalI = i;
/*     */ 
/*     */       
/* 422 */       ChunkSection chunksection = Arrays.<ChunkSection>stream(achunksection).filter(chunksection1 -> (chunksection1 != null && chunksection1.getYPosition() >> 4 == finalI)).findFirst().orElse(Chunk.a);
/*     */ 
/*     */ 
/*     */       
/* 426 */       if (asyncsavedata == null) {
/* 427 */         nibblearray = lightenginethreaded.a(EnumSkyBlock.BLOCK).a(SectionPosition.a(chunkcoordintpair, i));
/* 428 */         nibblearray1 = lightenginethreaded.a(EnumSkyBlock.SKY).a(SectionPosition.a(chunkcoordintpair, i));
/*     */       } else {
/* 430 */         nibblearray = asyncsavedata.blockLight[i + 1];
/* 431 */         nibblearray1 = asyncsavedata.skyLight[i + 1];
/*     */       } 
/*     */       
/* 434 */       if (chunksection != Chunk.a || nibblearray != null || nibblearray1 != null) {
/* 435 */         NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 436 */         nBTTagCompound.setByte("Y", (byte)(i & 0xFF));
/* 437 */         if (chunksection != Chunk.a) {
/* 438 */           chunksection.getBlocks().a(nBTTagCompound, "Palette", "BlockStates");
/*     */         }
/*     */         
/* 441 */         if (nibblearray != null && !nibblearray.c()) {
/* 442 */           nBTTagCompound.setByteArray("BlockLight", (byte[])nibblearray.asBytesPoolSafe().clone());
/*     */         }
/*     */         
/* 445 */         if (nibblearray1 != null && !nibblearray1.c()) {
/* 446 */           nBTTagCompound.setByteArray("SkyLight", (byte[])nibblearray1.asBytesPoolSafe().clone());
/*     */         }
/*     */         
/* 449 */         nbttaglist.add(nBTTagCompound);
/*     */       } 
/*     */     } 
/*     */     
/* 453 */     nbttagcompound1.set("Sections", nbttaglist);
/* 454 */     if (flag) {
/* 455 */       nbttagcompound1.setBoolean("isLightOn", true);
/*     */     }
/*     */     
/* 458 */     BiomeStorage biomestorage = ichunkaccess.getBiomeIndex();
/*     */     
/* 460 */     if (biomestorage != null) {
/* 461 */       nbttagcompound1.setIntArray("Biomes", biomestorage.a());
/*     */     }
/*     */     
/* 464 */     NBTTagList nbttaglist1 = new NBTTagList();
/* 465 */     Iterator<BlockPosition> iterator = ichunkaccess.c().iterator();
/*     */ 
/*     */ 
/*     */     
/* 469 */     while (iterator.hasNext()) {
/* 470 */       BlockPosition blockposition = iterator.next();
/*     */       
/* 472 */       NBTTagCompound nbttagcompound3 = ichunkaccess.j(blockposition);
/* 473 */       if (nbttagcompound3 != null) {
/* 474 */         nbttaglist1.add(nbttagcompound3);
/*     */       }
/*     */     } 
/*     */     
/* 478 */     nbttagcompound1.set("TileEntities", nbttaglist1);
/* 479 */     NBTTagList nbttaglist2 = new NBTTagList();
/*     */     
/* 481 */     List<Entity> toUpdate = new ArrayList<>();
/* 482 */     if (ichunkaccess.getChunkStatus().getType() == ChunkStatus.Type.LEVELCHUNK) {
/* 483 */       Chunk chunk = (Chunk)ichunkaccess;
/*     */       
/* 485 */       chunk.d(false);
/*     */       
/* 487 */       for (int j = 0; j < (chunk.getEntitySlices()).length; j++) {
/* 488 */         Iterator<Entity> iterator1 = chunk.getEntitySlices()[j].iterator();
/*     */         
/* 490 */         while (iterator1.hasNext()) {
/* 491 */           Entity entity = iterator1.next();
/* 492 */           NBTTagCompound nbttagcompound4 = new NBTTagCompound();
/*     */           
/* 494 */           if ((asyncsavedata == null && !entity.dead && (int)Math.floor(entity.locX()) >> 4 != (chunk.getPos()).x) || (int)Math.floor(entity.locZ()) >> 4 != (chunk.getPos()).z) {
/* 495 */             toUpdate.add(entity);
/*     */             continue;
/*     */           } 
/* 498 */           if (entity.dead || hasPlayerPassenger(entity)) {
/*     */             continue;
/*     */           }
/*     */           
/* 502 */           if (entity.d(nbttagcompound4)) {
/* 503 */             chunk.d(true);
/* 504 */             nbttaglist2.add(nbttagcompound4);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 510 */       for (Entity entity : toUpdate) {
/* 511 */         worldserver.chunkCheck(entity);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 516 */       ProtoChunk protochunk = (ProtoChunk)ichunkaccess;
/*     */       
/* 518 */       nbttaglist2.addAll((Collection)protochunk.y());
/* 519 */       nbttagcompound1.set("Lights", a(protochunk.w()));
/* 520 */       NBTTagCompound nbttagcompound3 = new NBTTagCompound();
/* 521 */       WorldGenStage.Features[] aworldgenstage_features = WorldGenStage.Features.values();
/* 522 */       int k = aworldgenstage_features.length;
/*     */       
/* 524 */       for (int l = 0; l < k; l++) {
/* 525 */         WorldGenStage.Features worldgenstage_features = aworldgenstage_features[l];
/* 526 */         BitSet bitset = protochunk.a(worldgenstage_features);
/*     */         
/* 528 */         if (bitset != null) {
/* 529 */           nbttagcompound3.setByteArray(worldgenstage_features.toString(), bitset.toByteArray());
/*     */         }
/*     */       } 
/*     */       
/* 533 */       nbttagcompound1.set("CarvingMasks", nbttagcompound3);
/*     */     } 
/*     */     
/* 536 */     nbttagcompound1.set("Entities", nbttaglist2);
/* 537 */     TickList<Block> ticklist = ichunkaccess.n();
/*     */     
/* 539 */     if (ticklist instanceof ProtoChunkTickList) {
/* 540 */       nbttagcompound1.set("ToBeTicked", ((ProtoChunkTickList)ticklist).b());
/* 541 */     } else if (ticklist instanceof TickListChunk) {
/* 542 */       nbttagcompound1.set("TileTicks", ((TickListChunk)ticklist).b());
/*     */     }
/* 544 */     else if (asyncsavedata != null) {
/* 545 */       nbttagcompound1.set("TileTicks", asyncsavedata.blockTickList);
/*     */     } else {
/*     */       
/* 548 */       nbttagcompound1.set("TileTicks", worldserver.getBlockTickList().a(chunkcoordintpair));
/*     */     } 
/*     */     
/* 551 */     TickList<FluidType> ticklist1 = ichunkaccess.o();
/*     */     
/* 553 */     if (ticklist1 instanceof ProtoChunkTickList) {
/* 554 */       nbttagcompound1.set("LiquidsToBeTicked", ((ProtoChunkTickList)ticklist1).b());
/* 555 */     } else if (ticklist1 instanceof TickListChunk) {
/* 556 */       nbttagcompound1.set("LiquidTicks", ((TickListChunk)ticklist1).b());
/*     */     }
/* 558 */     else if (asyncsavedata != null) {
/* 559 */       nbttagcompound1.set("LiquidTicks", asyncsavedata.fluidTickList);
/*     */     } else {
/*     */       
/* 562 */       nbttagcompound1.set("LiquidTicks", worldserver.getFluidTickList().a(chunkcoordintpair));
/*     */     } 
/*     */     
/* 565 */     nbttagcompound1.set("PostProcessing", a(ichunkaccess.l()));
/* 566 */     NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/* 567 */     Iterator<Map.Entry<HeightMap.Type, HeightMap>> iterator2 = ichunkaccess.f().iterator();
/*     */     
/* 569 */     while (iterator2.hasNext()) {
/* 570 */       Map.Entry<HeightMap.Type, HeightMap> entry = iterator2.next();
/*     */       
/* 572 */       if (ichunkaccess.getChunkStatus().h().contains(entry.getKey())) {
/* 573 */         nbttagcompound2.set(((HeightMap.Type)entry.getKey()).b(), new NBTTagLongArray(((HeightMap)entry.getValue()).a()));
/*     */       }
/*     */     } 
/*     */     
/* 577 */     nbttagcompound1.set("Heightmaps", nbttagcompound2);
/* 578 */     nbttagcompound1.set("Structures", a(chunkcoordintpair, ichunkaccess.h(), ichunkaccess.v()));
/* 579 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   private static boolean hasPlayerPassenger(Entity entity) {
/* 583 */     for (Entity passenger : entity.passengers) {
/* 584 */       if (passenger instanceof EntityPlayer) {
/* 585 */         return true;
/*     */       }
/* 587 */       if (hasPlayerPassenger(passenger)) {
/* 588 */         return true;
/*     */       }
/*     */     } 
/* 591 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ChunkStatus getStatus(NBTTagCompound compound) {
/* 597 */     if (compound == null) {
/* 598 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 602 */     return ChunkStatus.getStatus(compound.getCompound("Level").getString("Status"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ChunkStatus.Type a(@Nullable NBTTagCompound nbttagcompound) {
/* 607 */     if (nbttagcompound != null) {
/* 608 */       ChunkStatus chunkstatus = ChunkStatus.a(nbttagcompound.getCompound("Level").getString("Status"));
/*     */       
/* 610 */       if (chunkstatus != null) {
/* 611 */         return chunkstatus.getType();
/*     */       }
/*     */     } 
/*     */     
/* 615 */     return ChunkStatus.Type.PROTOCHUNK;
/*     */   }
/*     */   
/*     */   private static void loadEntities(NBTTagCompound nbttagcompound, Chunk chunk) {
/* 619 */     NBTTagList nbttaglist = nbttagcompound.getList("Entities", 10);
/* 620 */     World world = chunk.getWorld();
/*     */     
/* 622 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 623 */       NBTTagCompound nbttagcompound1 = nbttaglist.getCompound(i);
/*     */       
/* 625 */       EntityTypes.a(nbttagcompound1, world, entity -> {
/*     */             chunk.a(entity);
/*     */             return entity;
/*     */           });
/* 629 */       chunk.d(true);
/*     */     } 
/*     */     
/* 632 */     NBTTagList nbttaglist1 = nbttagcompound.getList("TileEntities", 10);
/*     */     
/* 634 */     for (int j = 0; j < nbttaglist1.size(); j++) {
/* 635 */       NBTTagCompound nbttagcompound2 = nbttaglist1.getCompound(j);
/* 636 */       boolean flag = nbttagcompound2.getBoolean("keepPacked");
/*     */       
/* 638 */       if (flag) {
/* 639 */         chunk.a(nbttagcompound2);
/*     */       } else {
/* 641 */         BlockPosition blockposition = new BlockPosition(nbttagcompound2.getInt("x"), nbttagcompound2.getInt("y"), nbttagcompound2.getInt("z"));
/* 642 */         TileEntity tileentity = TileEntity.create(chunk.getType(blockposition), nbttagcompound2);
/*     */         
/* 644 */         if (tileentity != null) {
/* 645 */           chunk.a(tileentity);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static NBTTagCompound a(ChunkCoordIntPair chunkcoordintpair, Map<StructureGenerator<?>, StructureStart<?>> map, Map<StructureGenerator<?>, LongSet> map1) {
/* 652 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 653 */     NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 654 */     Iterator<Map.Entry<StructureGenerator<?>, StructureStart<?>>> iterator = map.entrySet().iterator();
/*     */     
/* 656 */     while (iterator.hasNext()) {
/* 657 */       Map.Entry<StructureGenerator<?>, StructureStart<?>> entry = iterator.next();
/*     */       
/* 659 */       nbttagcompound1.set(((StructureGenerator)entry.getKey()).i(), ((StructureStart)entry.getValue()).a(chunkcoordintpair.x, chunkcoordintpair.z));
/*     */     } 
/*     */     
/* 662 */     nbttagcompound.set("Starts", nbttagcompound1);
/* 663 */     NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/* 664 */     Iterator<Map.Entry<StructureGenerator<?>, LongSet>> iterator1 = map1.entrySet().iterator();
/*     */     
/* 666 */     while (iterator1.hasNext()) {
/* 667 */       Map.Entry<StructureGenerator<?>, LongSet> entry1 = iterator1.next();
/*     */       
/* 669 */       nbttagcompound2.set(((StructureGenerator)entry1.getKey()).i(), new NBTTagLongArray(entry1.getValue()));
/*     */     } 
/*     */     
/* 672 */     nbttagcompound.set("References", nbttagcompound2);
/* 673 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   private static Map<StructureGenerator<?>, StructureStart<?>> a(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound, long i) {
/* 677 */     Map<StructureGenerator<?>, StructureStart<?>> map = Maps.newHashMap();
/* 678 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Starts");
/* 679 */     Iterator<String> iterator = nbttagcompound1.getKeys().iterator();
/*     */     
/* 681 */     while (iterator.hasNext()) {
/* 682 */       String s = iterator.next();
/* 683 */       String s1 = s.toLowerCase(Locale.ROOT);
/* 684 */       StructureGenerator<?> structuregenerator = (StructureGenerator)StructureGenerator.a.get(s1);
/*     */       
/* 686 */       if (structuregenerator == null) {
/* 687 */         LOGGER.error("Unknown structure start: {}", s1); continue;
/*     */       } 
/* 689 */       StructureStart<?> structurestart = StructureGenerator.a(definedstructuremanager, nbttagcompound1.getCompound(s), i);
/*     */       
/* 691 */       if (structurestart != null) {
/* 692 */         map.put(structuregenerator, structurestart);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 697 */     return map;
/*     */   }
/*     */   
/*     */   private static Map<StructureGenerator<?>, LongSet> a(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) {
/* 701 */     Map<StructureGenerator<?>, LongSet> map = Maps.newHashMap();
/* 702 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("References");
/* 703 */     Iterator<String> iterator = nbttagcompound1.getKeys().iterator();
/*     */     
/* 705 */     while (iterator.hasNext()) {
/* 706 */       String s = iterator.next();
/*     */       
/* 708 */       map.put((StructureGenerator)StructureGenerator.a.get(s.toLowerCase(Locale.ROOT)), new LongOpenHashSet(Arrays.stream(nbttagcompound1.getLongArray(s)).filter(i -> {
/*     */                 ChunkCoordIntPair chunkcoordintpair1 = new ChunkCoordIntPair(i);
/*     */                 
/*     */                 if (chunkcoordintpair1.a(chunkcoordintpair) > 8) {
/*     */                   LOGGER.warn("Found invalid structure reference [ {} @ {} ] for chunk {}.", s, chunkcoordintpair1, chunkcoordintpair);
/*     */                   
/*     */                   return false;
/*     */                 } 
/*     */                 return true;
/* 717 */               }).toArray()));
/*     */     } 
/*     */     
/* 720 */     return map;
/*     */   }
/*     */   
/*     */   public static NBTTagList a(ShortList[] ashortlist) {
/* 724 */     NBTTagList nbttaglist = new NBTTagList();
/* 725 */     ShortList[] ashortlist1 = ashortlist;
/* 726 */     int i = ashortlist.length;
/*     */     
/* 728 */     for (int j = 0; j < i; j++) {
/* 729 */       ShortList shortlist = ashortlist1[j];
/* 730 */       NBTTagList nbttaglist1 = new NBTTagList();
/*     */       
/* 732 */       if (shortlist != null) {
/* 733 */         ShortListIterator shortlistiterator = shortlist.iterator();
/*     */         
/* 735 */         while (shortlistiterator.hasNext()) {
/* 736 */           Short oshort = shortlistiterator.next();
/*     */           
/* 738 */           nbttaglist1.add(NBTTagShort.a(oshort.shortValue()));
/*     */         } 
/*     */       } 
/*     */       
/* 742 */       nbttaglist.add(nbttaglist1);
/*     */     } 
/*     */     
/* 745 */     return nbttaglist;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkRegionLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */