/*     */ package com.destroystokyo.paper.io.chunk;
/*     */ 
/*     */ import co.aikar.timings.Timing;
/*     */ import com.destroystokyo.paper.io.IOUtil;
/*     */ import com.destroystokyo.paper.io.PaperFileIOThread;
/*     */ import com.destroystokyo.paper.io.PrioritizedTaskQueue;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import net.minecraft.server.v1_16_R2.ChunkRegionLoader;
/*     */ import net.minecraft.server.v1_16_R2.IChunkAccess;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ 
/*     */ 
/*     */ public final class ChunkSaveTask
/*     */   extends ChunkTask
/*     */ {
/*     */   public final ChunkRegionLoader.AsyncSaveData asyncSaveData;
/*     */   public final IChunkAccess chunk;
/*  20 */   public final CompletableFuture<NBTTagCompound> onComplete = new CompletableFuture<>();
/*     */ 
/*     */   
/*     */   private final AtomicInteger attemptedPriority;
/*     */ 
/*     */   
/*     */   public ChunkSaveTask(WorldServer world, int chunkX, int chunkZ, int priority, ChunkTaskManager taskManager, ChunkRegionLoader.AsyncSaveData asyncSaveData, IChunkAccess chunk) {
/*  27 */     super(world, chunkX, chunkZ, priority, taskManager);
/*  28 */     this.chunk = chunk;
/*  29 */     this.asyncSaveData = asyncSaveData;
/*  30 */     this.attemptedPriority = new AtomicInteger(priority);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     NBTTagCompound compound;
/*     */     
/*  38 */     try { Timing ignored = this.world.timings.chunkUnloadDataSave.startTimingIfSync(); 
/*  39 */       try { compound = ChunkRegionLoader.saveChunk(this.world, this.chunk, this.asyncSaveData);
/*  40 */         if (ignored != null) ignored.close();  } catch (Throwable throwable) { if (ignored != null) try { ignored.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (Throwable ex)
/*     */     
/*  42 */     { PaperFileIOThread.LOGGER.error("Failed to serialize unloading chunk data for task: " + toString() + ", falling back to a synchronous execution", ex);
/*     */ 
/*     */ 
/*     */       
/*  46 */       ChunkTaskManager.queueChunkWaitTask(() -> {
/*     */             Timing ignored = this.world.timings.chunkUnloadDataSave.startTiming();
/*     */             
/*     */             try { NBTTagCompound data = PaperFileIOThread.FAILURE_VALUE;
/*     */               
/*     */               try { data = ChunkRegionLoader.saveChunk(this.world, this.chunk, this.asyncSaveData);
/*     */                 PaperFileIOThread.LOGGER.info("Successfully serialized chunk data for task: " + toString() + " synchronously"); }
/*  53 */               catch (Throwable ex1) { PaperFileIOThread.LOGGER.fatal("Failed to synchronously serialize unloading chunk data for task: " + toString() + "! Chunk data will be lost", ex1); }
/*     */                complete(data); if (ignored != null)
/*     */                 ignored.close();  }
/*     */             catch (Throwable throwable) { if (ignored != null)
/*     */                 try {
/*     */                   ignored.close();
/*     */                 } catch (Throwable throwable1) {
/*     */                   throwable.addSuppressed(throwable1);
/*     */                 }   throw throwable; }
/*     */           
/*     */           }); return; }
/*  64 */      complete(compound);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean raisePriority(int priority) {
/*  69 */     if (!PrioritizedTaskQueue.validPriority(priority)) {
/*  70 */       throw new IllegalStateException("Invalid priority: " + priority);
/*     */     }
/*     */ 
/*     */     
/*  74 */     int curr = this.attemptedPriority.get();
/*  75 */     while (curr > priority) {
/*     */ 
/*     */       
/*  78 */       if (this.attemptedPriority.compareAndSet(curr, priority)) {
/*     */         break;
/*     */       }
/*  81 */       curr = this.attemptedPriority.get();
/*     */     } 
/*     */     
/*  84 */     return super.raisePriority(priority);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updatePriority(int priority) {
/*  89 */     if (!PrioritizedTaskQueue.validPriority(priority)) {
/*  90 */       throw new IllegalStateException("Invalid priority: " + priority);
/*     */     }
/*  92 */     this.attemptedPriority.set(priority);
/*  93 */     return super.updatePriority(priority);
/*     */   }
/*     */   
/*     */   private void complete(NBTTagCompound compound) {
/*     */     try {
/*  98 */       this.onComplete.complete(compound);
/*  99 */     } catch (Throwable thr) {
/* 100 */       PaperFileIOThread.LOGGER.error("Failed to complete chunk data for task: " + toString(), thr);
/*     */     } 
/* 102 */     if (compound != PaperFileIOThread.FAILURE_VALUE) {
/* 103 */       PaperFileIOThread.Holder.INSTANCE.scheduleSave(this.world, this.chunkX, this.chunkZ, null, compound, this.attemptedPriority.get());
/*     */     }
/* 105 */     this.taskManager.chunkSaveTasks.compute(Long.valueOf(IOUtil.getCoordinateKey(this.chunkX, this.chunkZ)), (keyInMap, valueInMap) -> {
/*     */           if (valueInMap != this)
/*     */             throw new IllegalStateException("Expected this task to be scheduled, but another was! Other: " + valueInMap + ", this: " + this); 
/*     */           return null;
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\io\chunk\ChunkSaveTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */