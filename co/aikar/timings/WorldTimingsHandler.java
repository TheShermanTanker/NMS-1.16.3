/*     */ package co.aikar.timings;
/*     */ 
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import net.minecraft.server.v1_16_R2.WorldDataServer;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldTimingsHandler
/*     */ {
/*     */   public final Timing mobSpawn;
/*     */   public final Timing doChunkUnload;
/*     */   public final Timing doPortalForcer;
/*     */   public final Timing scheduledBlocks;
/*     */   public final Timing scheduledBlocksCleanup;
/*     */   public final Timing scheduledBlocksTicking;
/*     */   public final Timing chunkTicks;
/*     */   public final Timing lightChunk;
/*     */   public final Timing chunkTicksBlocks;
/*     */   public final Timing doVillages;
/*     */   public final Timing doChunkMap;
/*     */   public final Timing doChunkMapUpdate;
/*     */   public final Timing doChunkMapToUpdate;
/*     */   public final Timing doChunkMapSortMissing;
/*     */   public final Timing doChunkMapSortSendToPlayers;
/*     */   public final Timing doChunkMapPlayersNeedingChunks;
/*     */   public final Timing doChunkMapPendingSendToPlayers;
/*     */   public final Timing doChunkMapUnloadChunks;
/*     */   public final Timing doChunkGC;
/*     */   public final Timing doSounds;
/*     */   public final Timing entityRemoval;
/*     */   public final Timing entityTick;
/*     */   public final Timing tileEntityTick;
/*     */   public final Timing tileEntityPending;
/*     */   public final Timing tracker1;
/*     */   public final Timing tracker2;
/*     */   public final Timing doTick;
/*     */   public final Timing tickEntities;
/*     */   public final Timing chunks;
/*     */   public final Timing newEntities;
/*     */   public final Timing raids;
/*     */   public final Timing chunkProviderTick;
/*     */   public final Timing broadcastChunkUpdates;
/*     */   public final Timing countNaturalMobs;
/*     */   public final Timing chunkLoad;
/*     */   public final Timing chunkLoadPopulate;
/*     */   public final Timing syncChunkLoad;
/*     */   public final Timing chunkLoadLevelTimer;
/*     */   public final Timing chunkIO;
/*     */   public final Timing chunkPostLoad;
/*     */   public final Timing worldSave;
/*     */   public final Timing worldSaveChunks;
/*     */   public final Timing worldSaveLevel;
/*     */   public final Timing chunkSaveData;
/*     */   public final Timing miscMobSpawning;
/*     */   public final Timing playerMobDistanceMapUpdate;
/*     */   public final Timing poiUnload;
/*     */   public final Timing chunkUnload;
/*     */   public final Timing poiSaveDataSerialization;
/*     */   public final Timing chunkSave;
/*     */   public final Timing chunkSaveOverwriteCheck;
/*     */   public final Timing chunkSaveDataSerialization;
/*     */   public final Timing chunkSaveIOWait;
/*     */   public final Timing chunkUnloadPrepareSave;
/*     */   public final Timing chunkUnloadPOISerialization;
/*     */   public final Timing chunkUnloadDataSave;
/*     */   
/*     */   public WorldTimingsHandler(World server) {
/*  74 */     String name = ((WorldDataServer)server.getWorldData()).getName() + " - ";
/*     */     
/*  76 */     this.mobSpawn = Timings.ofSafe(name + "mobSpawn");
/*  77 */     this.doChunkUnload = Timings.ofSafe(name + "doChunkUnload");
/*  78 */     this.scheduledBlocks = Timings.ofSafe(name + "Scheduled Blocks");
/*  79 */     this.scheduledBlocksCleanup = Timings.ofSafe(name + "Scheduled Blocks - Cleanup");
/*  80 */     this.scheduledBlocksTicking = Timings.ofSafe(name + "Scheduled Blocks - Ticking");
/*  81 */     this.chunkTicks = Timings.ofSafe(name + "Chunk Ticks");
/*  82 */     this.lightChunk = Timings.ofSafe(name + "Light Chunk");
/*  83 */     this.chunkTicksBlocks = Timings.ofSafe(name + "Chunk Ticks - Blocks");
/*  84 */     this.doVillages = Timings.ofSafe(name + "doVillages");
/*  85 */     this.doChunkMap = Timings.ofSafe(name + "doChunkMap");
/*  86 */     this.doChunkMapUpdate = Timings.ofSafe(name + "doChunkMap - Update");
/*  87 */     this.doChunkMapToUpdate = Timings.ofSafe(name + "doChunkMap - To Update");
/*  88 */     this.doChunkMapSortMissing = Timings.ofSafe(name + "doChunkMap - Sort Missing");
/*  89 */     this.doChunkMapSortSendToPlayers = Timings.ofSafe(name + "doChunkMap - Sort Send To Players");
/*  90 */     this.doChunkMapPlayersNeedingChunks = Timings.ofSafe(name + "doChunkMap - Players Needing Chunks");
/*  91 */     this.doChunkMapPendingSendToPlayers = Timings.ofSafe(name + "doChunkMap - Pending Send To Players");
/*  92 */     this.doChunkMapUnloadChunks = Timings.ofSafe(name + "doChunkMap - Unload Chunks");
/*  93 */     this.doSounds = Timings.ofSafe(name + "doSounds");
/*  94 */     this.doChunkGC = Timings.ofSafe(name + "doChunkGC");
/*  95 */     this.doPortalForcer = Timings.ofSafe(name + "doPortalForcer");
/*  96 */     this.entityTick = Timings.ofSafe(name + "entityTick");
/*  97 */     this.entityRemoval = Timings.ofSafe(name + "entityRemoval");
/*  98 */     this.tileEntityTick = Timings.ofSafe(name + "tileEntityTick");
/*  99 */     this.tileEntityPending = Timings.ofSafe(name + "tileEntityPending");
/*     */     
/* 101 */     this.chunkLoad = Timings.ofSafe(name + "Chunk Load");
/* 102 */     this.chunkLoadPopulate = Timings.ofSafe(name + "Chunk Load - Populate");
/* 103 */     this.syncChunkLoad = Timings.ofSafe(name + "Sync Chunk Load");
/* 104 */     this.chunkLoadLevelTimer = Timings.ofSafe(name + "Chunk Load - Load Level");
/* 105 */     this.chunkIO = Timings.ofSafe(name + "Chunk Load - DiskIO");
/* 106 */     this.chunkPostLoad = Timings.ofSafe(name + "Chunk Load - Post Load");
/* 107 */     this.worldSave = Timings.ofSafe(name + "World Save");
/* 108 */     this.worldSaveLevel = Timings.ofSafe(name + "World Save - Level");
/* 109 */     this.worldSaveChunks = Timings.ofSafe(name + "World Save - Chunks");
/* 110 */     this.chunkSaveData = Timings.ofSafe(name + "Chunk Save - Data");
/*     */     
/* 112 */     this.tracker1 = Timings.ofSafe(name + "tracker stage 1");
/* 113 */     this.tracker2 = Timings.ofSafe(name + "tracker stage 2");
/* 114 */     this.doTick = Timings.ofSafe(name + "doTick");
/* 115 */     this.tickEntities = Timings.ofSafe(name + "tickEntities");
/*     */     
/* 117 */     this.chunks = Timings.ofSafe(name + "Chunks");
/* 118 */     this.newEntities = Timings.ofSafe(name + "New entity registration");
/* 119 */     this.raids = Timings.ofSafe(name + "Raids");
/* 120 */     this.chunkProviderTick = Timings.ofSafe(name + "Chunk provider tick");
/* 121 */     this.broadcastChunkUpdates = Timings.ofSafe(name + "Broadcast chunk updates");
/* 122 */     this.countNaturalMobs = Timings.ofSafe(name + "Count natural mobs");
/*     */ 
/*     */     
/* 125 */     this.miscMobSpawning = Timings.ofSafe(name + "Mob spawning - Misc");
/* 126 */     this.playerMobDistanceMapUpdate = Timings.ofSafe(name + "Per Player Mob Spawning - Distance Map Update");
/*     */     
/* 128 */     this.poiUnload = Timings.ofSafe(name + "Chunk unload - POI");
/* 129 */     this.chunkUnload = Timings.ofSafe(name + "Chunk unload - Chunk");
/* 130 */     this.poiSaveDataSerialization = Timings.ofSafe(name + "Chunk save - POI Data serialization");
/* 131 */     this.chunkSave = Timings.ofSafe(name + "Chunk save - Chunk");
/* 132 */     this.chunkSaveOverwriteCheck = Timings.ofSafe(name + "Chunk save - Chunk Overwrite Check");
/* 133 */     this.chunkSaveDataSerialization = Timings.ofSafe(name + "Chunk save - Chunk Data serialization");
/* 134 */     this.chunkSaveIOWait = Timings.ofSafe(name + "Chunk save - Chunk IO Wait");
/* 135 */     this.chunkUnloadPrepareSave = Timings.ofSafe(name + "Chunk unload - Async Save Prepare");
/* 136 */     this.chunkUnloadPOISerialization = Timings.ofSafe(name + "Chunk unload - POI Data Serialization");
/* 137 */     this.chunkUnloadDataSave = Timings.ofSafe(name + "Chunk unload - Data Serialization");
/*     */   }
/*     */   
/*     */   public static Timing getTickList(WorldServer worldserver, String timingsType) {
/* 141 */     return Timings.ofSafe(((WorldDataServer)worldserver.getWorldData()).getName() + " - Scheduled " + timingsType);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\WorldTimingsHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */