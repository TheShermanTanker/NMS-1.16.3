/*     */ package com.destroystokyo.paper.io.chunk;
/*     */ 
/*     */ import co.aikar.timings.Timing;
/*     */ import com.destroystokyo.paper.io.IOUtil;
/*     */ import com.destroystokyo.paper.io.PaperFileIOThread;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*     */ import net.minecraft.server.v1_16_R2.ChunkRegionLoader;
/*     */ import net.minecraft.server.v1_16_R2.GeneratorAccess;
/*     */ import net.minecraft.server.v1_16_R2.PlayerChunkMap;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ChunkLoadTask
/*     */   extends ChunkTask
/*     */ {
/*     */   public boolean cancelled;
/*     */   Consumer<ChunkRegionLoader.InProgressChunkHolder> onComplete;
/*     */   public PaperFileIOThread.ChunkData chunkData;
/*     */   private boolean hasCompleted;
/*     */   
/*     */   public ChunkLoadTask(WorldServer world, int chunkX, int chunkZ, int priority, ChunkTaskManager taskManager, Consumer<ChunkRegionLoader.InProgressChunkHolder> onComplete) {
/*  26 */     super(world, chunkX, chunkZ, priority, taskManager);
/*  27 */     this.onComplete = onComplete;
/*     */   }
/*     */   
/*  30 */   private static final ArrayDeque<Runnable> EMPTY_QUEUE = new ArrayDeque<>();
/*     */   
/*     */   private static ChunkRegionLoader.InProgressChunkHolder createEmptyHolder() {
/*  33 */     return new ChunkRegionLoader.InProgressChunkHolder(null, EMPTY_QUEUE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/*  39 */       executeTask();
/*  40 */     } catch (Throwable ex) {
/*  41 */       PaperFileIOThread.LOGGER.error("Failed to execute chunk load task: " + toString(), ex);
/*  42 */       if (!this.hasCompleted) {
/*  43 */         complete(createEmptyHolder());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean checkCancelled() {
/*  49 */     if (this.cancelled)
/*     */     {
/*  51 */       return (this.taskManager.chunkLoadTasks.compute(Long.valueOf(IOUtil.getCoordinateKey(this.chunkX, this.chunkZ)), (keyInMap, valueInMap) -> { if (valueInMap != this) throw new IllegalStateException("Expected this task to be scheduled, but another was! Other: " + valueInMap + ", current: " + this);  return valueInMap.cancelled ? null : valueInMap; }) == null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  62 */     return false;
/*     */   }
/*     */   
/*     */   public void executeTask() {
/*  66 */     if (checkCancelled()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  71 */     PaperFileIOThread.ChunkData chunkData = this.chunkData;
/*     */     
/*  73 */     if (chunkData.poiData == PaperFileIOThread.FAILURE_VALUE || chunkData.chunkData == PaperFileIOThread.FAILURE_VALUE) {
/*  74 */       PaperFileIOThread.LOGGER.error("Could not load chunk for task: " + toString() + ", file IO thread has dumped the relevant exception above");
/*  75 */       complete(createEmptyHolder());
/*     */       
/*     */       return;
/*     */     } 
/*  79 */     if (chunkData.chunkData == null) {
/*     */       
/*  81 */       complete(createEmptyHolder());
/*     */       
/*     */       return;
/*     */     } 
/*  85 */     ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(this.chunkX, this.chunkZ);
/*     */     
/*  87 */     PlayerChunkMap chunkManager = (this.world.getChunkProvider()).playerChunkMap;
/*     */     
/*  89 */     Timing ignored = this.world.timings.chunkLoadLevelTimer.startTimingIfSync();
/*     */ 
/*     */     
/*     */     try { ChunkRegionLoader.InProgressChunkHolder chunkHolder;
/*     */       
/*     */       try {
/*  95 */         chunkData.chunkData = chunkManager.getChunkData(this.world.getTypeKey(), chunkManager
/*  96 */             .getWorldPersistentDataSupplier(), chunkData.chunkData, chunkPos, (GeneratorAccess)this.world);
/*  97 */       } catch (Throwable ex) {
/*  98 */         PaperFileIOThread.LOGGER.error("Could not apply datafixers for chunk task: " + toString(), ex);
/*  99 */         complete(createEmptyHolder());
/*     */       } 
/*     */       
/* 102 */       if (checkCancelled())
/*     */       
/*     */       { 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 124 */         if (ignored != null) ignored.close();  return; }  try { (this.world.getChunkProvider()).playerChunkMap.updateChunkStatusOnDisk(chunkPos, chunkData.chunkData); } catch (Throwable ex) { PaperFileIOThread.LOGGER.warn("Failed to update chunk status cache for task: " + toString(), ex); }  try { chunkHolder = ChunkRegionLoader.loadChunk(this.world, chunkManager.definedStructureManager, chunkManager.getVillagePlace(), chunkPos, chunkData.chunkData, true); } catch (Throwable ex) { PaperFileIOThread.LOGGER.error("Could not de-serialize chunk data for task: " + toString(), ex); complete(createEmptyHolder()); if (ignored != null) ignored.close();  return; }  complete(chunkHolder); if (ignored != null) ignored.close();  } catch (Throwable chunkHolder) { if (ignored != null)
/*     */         try { ignored.close(); }
/*     */         catch (Throwable throwable) { chunkHolder.addSuppressed(throwable); }
/*     */           throw chunkHolder; }
/* 128 */      } private void complete(ChunkRegionLoader.InProgressChunkHolder holder) { this.hasCompleted = true;
/* 129 */     holder.poiData = (this.chunkData == null) ? null : this.chunkData.poiData;
/*     */     
/* 131 */     this.taskManager.chunkLoadTasks.compute(Long.valueOf(IOUtil.getCoordinateKey(this.chunkX, this.chunkZ)), (keyInMap, valueInMap) -> {
/*     */           if (valueInMap != this) {
/*     */             throw new IllegalStateException("Expected this task to be scheduled, but another was! Other: " + valueInMap + ", current: " + this);
/*     */           }
/*     */           if (valueInMap.cancelled) {
/*     */             return null;
/*     */           }
/*     */           try {
/*     */             this.onComplete.accept(holder);
/* 140 */           } catch (Throwable thr) {
/*     */             PaperFileIOThread.LOGGER.error("Failed to complete chunk data for task: " + toString(), thr);
/*     */           } 
/*     */           return null;
/*     */         }); }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\io\chunk\ChunkLoadTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */