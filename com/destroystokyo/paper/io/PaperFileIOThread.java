/*     */ package com.destroystokyo.paper.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.RegionFile;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ 
/*     */ 
/*     */ public final class PaperFileIOThread
/*     */   extends QueueExecutorThread
/*     */ {
/*  40 */   public static final Logger LOGGER = MinecraftServer.LOGGER;
/*  41 */   public static final NBTTagCompound FAILURE_VALUE = new NBTTagCompound();
/*     */   
/*     */   public static final class Holder
/*     */   {
/*  45 */     public static final PaperFileIOThread INSTANCE = new PaperFileIOThread();
/*     */     
/*     */     static {
/*  48 */       INSTANCE.start();
/*     */     }
/*     */   }
/*     */   
/*  52 */   private final AtomicLong writeCounter = new AtomicLong();
/*     */   
/*     */   private PaperFileIOThread() {
/*  55 */     super((PrioritizedTaskQueue)new PrioritizedTaskQueue<>(), 1000000L);
/*  56 */     setName("Paper RegionFile IO Thread");
/*  57 */     setPriority(4);
/*  58 */     setUncaughtExceptionHandler((unused, thr) -> LOGGER.fatal("Uncaught exception thrown from IO thread, report this!", thr));
/*     */   }
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
/*     */   public void bumpPriority(WorldServer world, int chunkX, int chunkZ, int priority) {
/*  92 */     if (!PrioritizedTaskQueue.validPriority(priority)) {
/*  93 */       throw new IllegalArgumentException("Invalid priority: " + priority);
/*     */     }
/*     */     
/*  96 */     Long key = Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ));
/*     */     
/*  98 */     ChunkDataTask poiTask = world.poiDataController.tasks.get(key);
/*  99 */     ChunkDataTask chunkTask = world.chunkDataController.tasks.get(key);
/*     */     
/* 101 */     if (poiTask != null) {
/* 102 */       poiTask.raisePriority(priority);
/*     */     }
/* 104 */     if (chunkTask != null) {
/* 105 */       chunkTask.raisePriority(priority);
/*     */     }
/*     */   }
/*     */   
/*     */   public NBTTagCompound getPendingWrite(WorldServer world, int chunkX, int chunkZ, boolean poiData) {
/* 110 */     ChunkDataController taskController = poiData ? world.poiDataController : world.chunkDataController;
/*     */     
/* 112 */     ChunkDataTask dataTask = taskController.tasks.get(Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ)));
/*     */     
/* 114 */     if (dataTask == null) {
/* 115 */       return null;
/*     */     }
/*     */     
/* 118 */     ChunkDataController.InProgressWrite write = dataTask.inProgressWrite;
/*     */     
/* 120 */     if (write == null) {
/* 121 */       return null;
/*     */     }
/*     */     
/* 124 */     return write.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPriority(WorldServer world, int chunkX, int chunkZ, int priority) {
/* 135 */     if (!PrioritizedTaskQueue.validPriority(priority)) {
/* 136 */       throw new IllegalArgumentException("Invalid priority: " + priority);
/*     */     }
/*     */     
/* 139 */     Long key = Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ));
/*     */     
/* 141 */     ChunkDataTask poiTask = world.poiDataController.tasks.get(key);
/* 142 */     ChunkDataTask chunkTask = world.chunkDataController.tasks.get(key);
/*     */     
/* 144 */     if (poiTask != null) {
/* 145 */       poiTask.updatePriority(priority);
/*     */     }
/* 147 */     if (chunkTask != null) {
/* 148 */       chunkTask.updatePriority(priority);
/*     */     }
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scheduleSave(WorldServer world, int chunkX, int chunkZ, NBTTagCompound poiData, NBTTagCompound chunkData, int priority) throws IllegalArgumentException {
/* 176 */     if (!PrioritizedTaskQueue.validPriority(priority)) {
/* 177 */       throw new IllegalArgumentException("Invalid priority: " + priority);
/*     */     }
/*     */     
/* 180 */     long writeCounter = this.writeCounter.getAndIncrement();
/*     */     
/* 182 */     if (poiData != null) {
/* 183 */       scheduleWrite(world.poiDataController, world, chunkX, chunkZ, poiData, priority, writeCounter);
/*     */     }
/* 185 */     if (chunkData != null) {
/* 186 */       scheduleWrite(world.chunkDataController, world, chunkX, chunkZ, chunkData, priority, writeCounter);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void scheduleWrite(ChunkDataController dataController, WorldServer world, int chunkX, int chunkZ, NBTTagCompound data, int priority, long writeCounter) {
/* 192 */     dataController.tasks.compute(Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ)), (keyInMap, taskRunning) -> {
/*     */           if (taskRunning == null) {
/*     */             ChunkDataTask newTask = new ChunkDataTask(priority, world, chunkX, chunkZ, dataController);
/*     */             newTask.inProgressWrite = new ChunkDataController.InProgressWrite();
/*     */             newTask.inProgressWrite.writeCounter = writeCounter;
/*     */             newTask.inProgressWrite.data = data;
/*     */             queueTask((T)newTask);
/*     */             return newTask;
/*     */           } 
/*     */           taskRunning.raisePriority(priority);
/*     */           if (taskRunning.inProgressWrite == null) {
/*     */             taskRunning.inProgressWrite = new ChunkDataController.InProgressWrite();
/*     */           }
/*     */           boolean reschedule = (taskRunning.inProgressWrite.writeCounter == -1L);
/*     */           synchronized (taskRunning) {
/*     */             taskRunning.inProgressWrite.data = data;
/*     */             taskRunning.inProgressWrite.writeCounter = writeCounter;
/*     */           } 
/*     */           if (reschedule) {
/*     */             taskRunning.reschedule(priority);
/*     */           }
/*     */           return taskRunning;
/*     */         });
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompletableFuture<ChunkData> loadChunkDataAsyncFuture(WorldServer world, int chunkX, int chunkZ, int priority, boolean readPoiData, boolean readChunkData, boolean intendingToBlock) {
/* 241 */     CompletableFuture<ChunkData> future = new CompletableFuture<>();
/* 242 */     Objects.requireNonNull(future); loadChunkDataAsync(world, chunkX, chunkZ, priority, future::complete, readPoiData, readChunkData, intendingToBlock);
/* 243 */     return future;
/*     */   }
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
/*     */   public void loadChunkDataAsync(WorldServer world, int chunkX, int chunkZ, int priority, Consumer<ChunkData> onComplete, boolean readPoiData, boolean readChunkData, boolean intendingToBlock) {
/* 277 */     if (!PrioritizedTaskQueue.validPriority(priority)) {
/* 278 */       throw new IllegalArgumentException("Invalid priority: " + priority);
/*     */     }
/*     */     
/* 281 */     if (!(readPoiData | readChunkData)) {
/* 282 */       throw new IllegalArgumentException("Must read chunk data or poi data");
/*     */     }
/*     */     
/* 285 */     ChunkData complete = new ChunkData();
/* 286 */     boolean[] requireCompletion = { readPoiData, readChunkData };
/*     */     
/* 288 */     if (readPoiData) {
/* 289 */       scheduleRead(world.poiDataController, world, chunkX, chunkZ, poiData -> { boolean finished; complete.poiData = poiData; synchronized (requireCompletion) { requireCompletion[0] = false; finished = !requireCompletion[1]; }  if (finished) onComplete.accept(complete);  }priority, intendingToBlock);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 307 */     if (readChunkData) {
/* 308 */       scheduleRead(world.chunkDataController, world, chunkX, chunkZ, chunkData -> { boolean finished; complete.chunkData = chunkData; synchronized (requireCompletion) { requireCompletion[1] = false; finished = !requireCompletion[0]; }  if (finished) onComplete.accept(complete);  }priority, intendingToBlock);
/*     */     }
/*     */   }
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
/*     */ 
/*     */   
/*     */   private void scheduleRead(ChunkDataController dataController, WorldServer world, int chunkX, int chunkZ, Consumer<NBTTagCompound> onComplete, int priority, boolean intendingToBlock) {
/* 333 */     Function<RegionFile, Boolean> tryLoadFunction = file -> (file == null) ? Boolean.TRUE : Boolean.valueOf(file.chunkExists(new ChunkCoordIntPair(chunkX, chunkZ)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 340 */     dataController.tasks.compute(Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ)), (keyInMap, running) -> {
/*     */           if (running == null) {
/*     */             Boolean shouldSchedule = intendingToBlock ? dataController.<Boolean>computeForRegionFile(chunkX, chunkZ, tryLoadFunction) : dataController.<Boolean>computeForRegionFileIfLoaded(chunkX, chunkZ, tryLoadFunction);
/*     */             if (shouldSchedule == Boolean.FALSE) {
/*     */               onComplete.accept(null);
/*     */               return null;
/*     */             } 
/*     */             ChunkDataTask newTask = new ChunkDataTask(priority, world, chunkX, chunkZ, dataController);
/*     */             newTask.inProgressRead = new ChunkDataController.InProgressRead();
/*     */             newTask.inProgressRead.readFuture.thenAccept(onComplete);
/*     */             queueTask((T)newTask);
/*     */             return newTask;
/*     */           } 
/*     */           running.raisePriority(priority);
/*     */           if (running.inProgressWrite == null) {
/*     */             running.inProgressRead.readFuture.thenAccept(onComplete);
/*     */             return running;
/*     */           } 
/*     */           onComplete.accept(running.inProgressWrite.data);
/*     */           return running;
/*     */         });
/*     */   }
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
/*     */ 
/*     */   
/*     */   public ChunkData loadChunkData(WorldServer world, int chunkX, int chunkZ, int priority, boolean readPoiData, boolean readChunkData) {
/* 384 */     return loadChunkDataAsyncFuture(world, chunkX, chunkZ, priority, readPoiData, readChunkData, true).join();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void runTask(int priority, Runnable runnable) {
/* 394 */     queueTask((T)new GeneralTask(priority, runnable));
/*     */   }
/*     */   
/*     */   static final class GeneralTask
/*     */     extends PrioritizedTaskQueue.PrioritizedTask implements Runnable {
/*     */     private final Runnable run;
/*     */     
/*     */     public GeneralTask(int priority, Runnable run) {
/* 402 */       super(priority);
/* 403 */       this.run = IOUtil.<Runnable>notNull(run, "Task may not be null");
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/*     */       try {
/* 409 */         this.run.run();
/* 410 */       } catch (Throwable throwable) {
/* 411 */         if (throwable instanceof ThreadDeath) {
/* 412 */           throw (ThreadDeath)throwable;
/*     */         }
/* 414 */         PaperFileIOThread.LOGGER.fatal("Failed to execute general task on IO thread " + IOUtil.genericToString(this.run), throwable);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class ChunkData
/*     */   {
/*     */     public NBTTagCompound poiData;
/*     */     public NBTTagCompound chunkData;
/*     */     
/*     */     public ChunkData() {}
/*     */     
/*     */     public ChunkData(NBTTagCompound poiData, NBTTagCompound chunkData) {
/* 427 */       this.poiData = poiData;
/* 428 */       this.chunkData = chunkData;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static abstract class ChunkDataController
/*     */   {
/* 435 */     public final ConcurrentHashMap<Long, PaperFileIOThread.ChunkDataTask> tasks = new ConcurrentHashMap<>(64, 0.5F);
/*     */     
/*     */     public abstract void writeData(int param1Int1, int param1Int2, NBTTagCompound param1NBTTagCompound) throws IOException;
/*     */     
/*     */     public abstract NBTTagCompound readData(int param1Int1, int param1Int2) throws IOException;
/*     */     
/*     */     public abstract <T> T computeForRegionFile(int param1Int1, int param1Int2, Function<RegionFile, T> param1Function);
/*     */     
/*     */     public abstract <T> T computeForRegionFileIfLoaded(int param1Int1, int param1Int2, Function<RegionFile, T> param1Function);
/*     */     
/*     */     public static final class InProgressWrite {
/*     */       public long writeCounter;
/*     */       public NBTTagCompound data; }
/*     */     
/* 449 */     public static final class InProgressRead { public final CompletableFuture<NBTTagCompound> readFuture = new CompletableFuture<>(); } } public static final class InProgressWrite { public long writeCounter; public NBTTagCompound data; } public static final class InProgressRead { public InProgressRead() { this.readFuture = new CompletableFuture<>(); }
/*     */ 
/*     */     
/*     */     public final CompletableFuture<NBTTagCompound> readFuture; }
/*     */ 
/*     */   
/*     */   public static final class ChunkDataTask extends PrioritizedTaskQueue.PrioritizedTask implements Runnable {
/*     */     public PaperFileIOThread.ChunkDataController.InProgressWrite inProgressWrite;
/*     */     public PaperFileIOThread.ChunkDataController.InProgressRead inProgressRead;
/*     */     private final WorldServer world;
/*     */     private final int x;
/*     */     private final int z;
/*     */     private final PaperFileIOThread.ChunkDataController taskController;
/*     */     
/*     */     public ChunkDataTask(int priority, WorldServer world, int x, int z, PaperFileIOThread.ChunkDataController taskController) {
/* 464 */       super(priority);
/* 465 */       this.world = world;
/* 466 */       this.x = x;
/* 467 */       this.z = z;
/* 468 */       this.taskController = taskController;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 473 */       return "Task for world: '" + this.world.getWorld().getName() + "' at " + this.x + "," + this.z + " poi: " + ((this.taskController == this.world.poiDataController) ? 1 : 0) + ", hash: " + 
/* 474 */         hashCode();
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
/*     */     void reschedule(int priority) {
/* 497 */       this.queue.lazySet(null);
/* 498 */       this.priority.lazySet(priority);
/* 499 */       PaperFileIOThread.Holder.INSTANCE.queueTask((T)this);
/*     */     }
/*     */     
/*     */     public void run() {
/*     */       ChunkDataTask inMap;
/* 504 */       PaperFileIOThread.ChunkDataController.InProgressRead read = this.inProgressRead;
/* 505 */       if (read != null) {
/* 506 */         NBTTagCompound compound = PaperFileIOThread.FAILURE_VALUE;
/*     */         try {
/* 508 */           compound = this.taskController.readData(this.x, this.z);
/* 509 */         } catch (Throwable thr) {
/* 510 */           if (thr instanceof ThreadDeath) {
/* 511 */             throw (ThreadDeath)thr;
/*     */           }
/* 513 */           PaperFileIOThread.LOGGER.fatal("Failed to read chunk data for task: " + toString(), thr);
/*     */         } 
/*     */         
/* 516 */         read.readFuture.complete(compound);
/*     */       } 
/*     */       
/* 519 */       Long chunkKey = Long.valueOf(IOUtil.getCoordinateKey(this.x, this.z));
/*     */       
/* 521 */       PaperFileIOThread.ChunkDataController.InProgressWrite write = this.inProgressWrite;
/*     */       
/* 523 */       if (write == null) {
/*     */         
/* 525 */         ChunkDataTask chunkDataTask = this.taskController.tasks.compute(chunkKey, (keyInMap, valueInMap) -> {
/*     */               if (valueInMap == null) {
/*     */                 throw new IllegalStateException("Write completed concurrently, expected this task: " + toString() + ", report this!");
/*     */               }
/*     */               
/*     */               if (valueInMap != this) {
/*     */                 throw new IllegalStateException("Chunk task mismatch, expected this task: " + toString() + ", got: " + valueInMap.toString() + ", report this!");
/*     */               }
/*     */               return (valueInMap.inProgressWrite == null) ? null : valueInMap;
/*     */             });
/* 535 */         if (chunkDataTask == null) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 540 */         write = this.inProgressWrite;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/*     */         long writeCounter;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         NBTTagCompound data;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 558 */         synchronized (write) {
/* 559 */           writeCounter = write.writeCounter;
/* 560 */           data = write.data;
/*     */         } 
/*     */         
/* 563 */         boolean failedWrite = false;
/*     */         
/*     */         try {
/* 566 */           this.taskController.writeData(this.x, this.z, data);
/* 567 */         } catch (Throwable thr) {
/* 568 */           if (thr instanceof ThreadDeath) {
/* 569 */             throw (ThreadDeath)thr;
/*     */           }
/* 571 */           PaperFileIOThread.LOGGER.fatal("Failed to write chunk data for task: " + toString(), thr);
/* 572 */           failedWrite = true;
/*     */         } 
/*     */         
/* 575 */         boolean finalFailWrite = failedWrite;
/*     */         
/* 577 */         inMap = this.taskController.tasks.compute(chunkKey, (keyInMap, valueInMap) -> {
/*     */               if (valueInMap == null) {
/*     */                 throw new IllegalStateException("Write completed concurrently, expected this task: " + toString() + ", report this!");
/*     */               }
/*     */               
/*     */               if (valueInMap != this) {
/*     */                 throw new IllegalStateException("Chunk task mismatch, expected this task: " + toString() + ", got: " + valueInMap.toString() + ", report this!");
/*     */               }
/*     */               
/*     */               if (valueInMap.inProgressWrite.writeCounter == writeCounter) {
/*     */                 if (finalFailWrite) {
/*     */                   valueInMap.inProgressWrite.writeCounter = -1L;
/*     */                 }
/*     */                 
/*     */                 return null;
/*     */               } 
/*     */               return valueInMap;
/*     */             });
/* 595 */       } while (inMap != null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\io\PaperFileIOThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */