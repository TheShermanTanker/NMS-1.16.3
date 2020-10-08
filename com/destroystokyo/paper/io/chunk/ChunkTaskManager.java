/*     */ package com.destroystokyo.paper.io.chunk;
/*     */ 
/*     */ import com.destroystokyo.paper.io.IOUtil;
/*     */ import com.destroystokyo.paper.io.PaperFileIOThread;
/*     */ import com.destroystokyo.paper.io.PrioritizedTaskQueue;
/*     */ import com.destroystokyo.paper.io.QueueExecutorThread;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.server.v1_16_R2.ChunkRegionLoader;
/*     */ import net.minecraft.server.v1_16_R2.ChunkStatus;
/*     */ import net.minecraft.server.v1_16_R2.IChunkAccess;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.PlayerChunk;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ChunkTaskManager
/*     */ {
/*     */   private final QueueExecutorThread<ChunkTask>[] workers;
/*     */   private final WorldServer world;
/*     */   private final PrioritizedTaskQueue<ChunkTask> queue;
/*     */   private final boolean perWorldQueue;
/*  38 */   final ConcurrentHashMap<Long, ChunkLoadTask> chunkLoadTasks = new ConcurrentHashMap<>(64, 0.5F);
/*  39 */   final ConcurrentHashMap<Long, ChunkSaveTask> chunkSaveTasks = new ConcurrentHashMap<>(64, 0.5F);
/*     */   
/*  41 */   private final PrioritizedTaskQueue<ChunkTask> chunkTasks = new PrioritizedTaskQueue();
/*     */   
/*     */   protected static QueueExecutorThread<ChunkTask>[] globalWorkers;
/*     */   
/*     */   protected static QueueExecutorThread<ChunkTask> globalUrgentWorker;
/*     */   protected static PrioritizedTaskQueue<ChunkTask> globalQueue;
/*     */   protected static PrioritizedTaskQueue<ChunkTask> globalUrgentQueue;
/*  48 */   protected static final ConcurrentLinkedQueue<Runnable> CHUNK_WAIT_QUEUE = new ConcurrentLinkedQueue<>();
/*     */   
/*  50 */   public static final ArrayDeque<ChunkInfo> WAITING_CHUNKS = new ArrayDeque<>();
/*     */   
/*     */   private static final class ChunkInfo
/*     */   {
/*     */     public final int chunkX;
/*     */     public final int chunkZ;
/*     */     public final WorldServer world;
/*     */     
/*     */     public ChunkInfo(int chunkX, int chunkZ, WorldServer world) {
/*  59 */       this.chunkX = chunkX;
/*  60 */       this.chunkZ = chunkZ;
/*  61 */       this.world = world;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  66 */       return "[( " + this.chunkX + "," + this.chunkZ + ") in '" + this.world.getWorld().getName() + "']";
/*     */     }
/*     */   }
/*     */   
/*     */   public static void pushChunkWait(WorldServer world, int chunkX, int chunkZ) {
/*  71 */     synchronized (WAITING_CHUNKS) {
/*  72 */       WAITING_CHUNKS.push(new ChunkInfo(chunkX, chunkZ, world));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void popChunkWait() {
/*  77 */     synchronized (WAITING_CHUNKS) {
/*  78 */       WAITING_CHUNKS.pop();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static ChunkInfo[] getChunkInfos() {
/*     */     ChunkInfo[] chunks;
/*  84 */     synchronized (WAITING_CHUNKS) {
/*  85 */       chunks = WAITING_CHUNKS.<ChunkInfo>toArray(new ChunkInfo[0]);
/*     */     } 
/*  87 */     return chunks;
/*     */   }
/*     */   
/*     */   public static void dumpAllChunkLoadInfo() {
/*  91 */     ChunkInfo[] chunks = getChunkInfos();
/*  92 */     if (chunks.length > 0) {
/*  93 */       PaperFileIOThread.LOGGER.log(Level.ERROR, "Chunk wait task info below: ");
/*     */       
/*  95 */       for (ChunkInfo chunkInfo : chunks) {
/*  96 */         long key = IOUtil.getCoordinateKey(chunkInfo.chunkX, chunkInfo.chunkZ);
/*  97 */         ChunkLoadTask loadTask = chunkInfo.world.asyncChunkTaskManager.chunkLoadTasks.get(Long.valueOf(key));
/*  98 */         ChunkSaveTask saveTask = chunkInfo.world.asyncChunkTaskManager.chunkSaveTasks.get(Long.valueOf(key));
/*     */         
/* 100 */         PaperFileIOThread.LOGGER.log(Level.ERROR, chunkInfo.chunkX + "," + chunkInfo.chunkZ + " in '" + chunkInfo.world.getWorld().getName() + ":");
/* 101 */         PaperFileIOThread.LOGGER.log(Level.ERROR, "Load Task - " + ((loadTask == null) ? "none" : loadTask.toString()));
/* 102 */         PaperFileIOThread.LOGGER.log(Level.ERROR, "Save Task - " + ((saveTask == null) ? "none" : saveTask.toString()));
/*     */         
/* 104 */         PlayerChunk chunkHolder = (chunkInfo.world.getChunkProvider()).playerChunkMap.getVisibleChunk(key);
/*     */         
/* 106 */         dumpChunkInfo(new HashSet<>(), chunkHolder, chunkInfo.chunkX, chunkInfo.chunkZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static void dumpChunkInfo(Set<PlayerChunk> seenChunks, PlayerChunk chunkHolder, int x, int z) {
/* 112 */     dumpChunkInfo(seenChunks, chunkHolder, x, z, 0, 4);
/*     */   }
/*     */   
/*     */   static void dumpChunkInfo(Set<PlayerChunk> seenChunks, PlayerChunk chunkHolder, int x, int z, int indent, int maxDepth) {
/* 116 */     if (seenChunks.contains(chunkHolder)) {
/*     */       return;
/*     */     }
/* 119 */     if (indent > maxDepth) {
/*     */       return;
/*     */     }
/* 122 */     seenChunks.add(chunkHolder);
/* 123 */     String indentStr = StringUtils.repeat("  ", indent);
/* 124 */     if (chunkHolder == null) {
/* 125 */       PaperFileIOThread.LOGGER.log(Level.ERROR, indentStr + "Chunk Holder - null for (" + x + "," + z + ")");
/*     */     } else {
/* 127 */       IChunkAccess chunk = chunkHolder.getAvailableChunkNow();
/* 128 */       ChunkStatus holderStatus = chunkHolder.getChunkHolderStatus();
/* 129 */       PaperFileIOThread.LOGGER.log(Level.ERROR, indentStr + "Chunk Holder - non-null");
/* 130 */       PaperFileIOThread.LOGGER.log(Level.ERROR, indentStr + "Chunk Status - " + ((chunk == null) ? "null chunk" : chunk.getChunkStatus().toString()));
/* 131 */       PaperFileIOThread.LOGGER.log(Level.ERROR, indentStr + "Chunk Ticket Status - " + PlayerChunk.getChunkStatus(chunkHolder.getTicketLevel()));
/* 132 */       PaperFileIOThread.LOGGER.log(Level.ERROR, indentStr + "Chunk Holder Status - " + ((holderStatus == null) ? "null" : holderStatus.toString()));
/* 133 */       PaperFileIOThread.LOGGER.log(Level.ERROR, indentStr + "Chunk Holder Priority - " + chunkHolder.getCurrentPriority());
/*     */       
/* 135 */       if (!chunkHolder.neighbors.isEmpty()) {
/* 136 */         if (indent >= maxDepth) {
/* 137 */           PaperFileIOThread.LOGGER.log(Level.ERROR, indentStr + "Chunk Neighbors: (Can't show, too deeply nested)");
/*     */           return;
/*     */         } 
/* 140 */         PaperFileIOThread.LOGGER.log(Level.ERROR, indentStr + "Chunk Neighbors: ");
/* 141 */         for (PlayerChunk neighbor : chunkHolder.neighbors.keySet()) {
/* 142 */           ChunkStatus status = neighbor.getChunkHolderStatus();
/* 143 */           if (status != null && status.isAtLeastStatus(PlayerChunk.getChunkStatus(neighbor.getTicketLevel()))) {
/*     */             continue;
/*     */           }
/* 146 */           int nx = neighbor.location.x;
/* 147 */           int nz = neighbor.location.z;
/* 148 */           if (seenChunks.contains(neighbor)) {
/* 149 */             PaperFileIOThread.LOGGER.log(Level.ERROR, indentStr + "  " + nx + "," + nz + " in " + chunkHolder.getWorld().getWorld().getName() + " (CIRCULAR)");
/*     */             continue;
/*     */           } 
/* 152 */           PaperFileIOThread.LOGGER.log(Level.ERROR, indentStr + "  " + nx + "," + nz + " in " + chunkHolder.getWorld().getWorld().getName() + ":");
/* 153 */           dumpChunkInfo(seenChunks, neighbor, nx, nz, indent + 1, maxDepth);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initGlobalLoadThreads(int threads) {
/* 161 */     if (threads <= 0 || globalWorkers != null) {
/*     */       return;
/*     */     }
/*     */     
/* 165 */     globalWorkers = (QueueExecutorThread<ChunkTask>[])new QueueExecutorThread[threads];
/* 166 */     globalQueue = new PrioritizedTaskQueue();
/* 167 */     globalUrgentQueue = new PrioritizedTaskQueue();
/*     */     
/* 169 */     for (int i = 0; i < threads; i++) {
/* 170 */       globalWorkers[i] = new QueueExecutorThread(globalQueue, 100000L);
/* 171 */       globalWorkers[i].setName("Paper Async Chunk Task Thread #" + i);
/* 172 */       globalWorkers[i].setPriority(4);
/* 173 */       globalWorkers[i].setUncaughtExceptionHandler((thread, throwable) -> PaperFileIOThread.LOGGER.fatal("Thread '" + thread.getName() + "' threw an uncaught exception!", throwable));
/*     */ 
/*     */ 
/*     */       
/* 177 */       globalWorkers[i].start();
/*     */     } 
/*     */     
/* 180 */     globalUrgentWorker = new QueueExecutorThread(globalUrgentQueue, 100000L);
/* 181 */     globalUrgentWorker.setName("Paper Async Chunk Urgent Task Thread");
/* 182 */     globalUrgentWorker.setPriority(6);
/* 183 */     globalUrgentWorker.setUncaughtExceptionHandler((thread, throwable) -> PaperFileIOThread.LOGGER.fatal("Thread '" + thread.getName() + "' threw an uncaught exception!", throwable));
/*     */ 
/*     */ 
/*     */     
/* 187 */     globalUrgentWorker.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkTaskManager(WorldServer world, int threads) {
/* 198 */     this.world = world;
/* 199 */     this.workers = (threads <= 0) ? null : (QueueExecutorThread<ChunkTask>[])new QueueExecutorThread[threads];
/* 200 */     this.queue = new PrioritizedTaskQueue();
/* 201 */     this.perWorldQueue = true;
/*     */     
/* 203 */     for (int i = 0; i < threads; i++) {
/* 204 */       this.workers[i] = new QueueExecutorThread(this.queue, 100000L);
/* 205 */       this.workers[i].setName("Async chunk loader thread #" + i + " for world: " + world.getWorld().getName());
/* 206 */       this.workers[i].setPriority(4);
/* 207 */       this.workers[i].setUncaughtExceptionHandler((thread, throwable) -> PaperFileIOThread.LOGGER.fatal("Thread '" + thread.getName() + "' threw an uncaught exception!", throwable));
/*     */ 
/*     */ 
/*     */       
/* 211 */       this.workers[i].start();
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
/*     */   public ChunkTaskManager(WorldServer world) {
/* 223 */     this.world = world;
/* 224 */     this.workers = globalWorkers;
/* 225 */     this.queue = globalQueue;
/* 226 */     this.perWorldQueue = false;
/*     */   }
/*     */   
/*     */   public boolean pollNextChunkTask() {
/* 230 */     ChunkTask task = (ChunkTask)this.chunkTasks.poll();
/*     */     
/* 232 */     if (task != null) {
/* 233 */       task.run();
/* 234 */       return true;
/*     */     } 
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean pollChunkWaitQueue() {
/* 244 */     Runnable run = CHUNK_WAIT_QUEUE.poll();
/* 245 */     if (run != null) {
/* 246 */       run.run();
/* 247 */       return true;
/*     */     } 
/* 249 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void queueChunkWaitTask(Runnable runnable) {
/* 257 */     CHUNK_WAIT_QUEUE.add(runnable);
/*     */   }
/*     */   
/*     */   private static void drainChunkWaitQueue() {
/*     */     Runnable run;
/* 262 */     while ((run = CHUNK_WAIT_QUEUE.poll()) != null) {
/* 263 */       run.run();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkLoadTask scheduleChunkLoad(int chunkX, int chunkZ, int priority, Consumer<ChunkRegionLoader.InProgressChunkHolder> onComplete, boolean intendingToBlock, CompletableFuture<NBTTagCompound> dataFuture) {
/* 274 */     WorldServer world = this.world;
/*     */     
/* 276 */     return this.chunkLoadTasks.compute(Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ)), (keyInMap, valueInMap) -> {
/*     */           if (valueInMap != null) {
/*     */             if (!valueInMap.cancelled) {
/*     */               throw new IllegalStateException("Double scheduling chunk load for task: " + valueInMap.toString());
/*     */             }
/*     */             valueInMap.cancelled = false;
/*     */             valueInMap.onComplete = onComplete;
/*     */             return valueInMap;
/*     */           } 
/*     */           ChunkLoadTask ret = new ChunkLoadTask(world, chunkX, chunkZ, priority, this, onComplete);
/*     */           dataFuture.thenAccept(());
/*     */           return ret;
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
/*     */   public void cancelChunkLoad(int chunkX, int chunkZ) {
/* 304 */     this.chunkLoadTasks.compute(Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ)), (keyInMap, valueInMap) -> {
/*     */           if (valueInMap == null) {
/*     */             return null;
/*     */           }
/*     */           if (valueInMap.cancelled) {
/*     */             PaperFileIOThread.LOGGER.warn("Task " + valueInMap.toString() + " is already cancelled!");
/*     */           }
/*     */           valueInMap.cancelled = true;
/*     */           return valueInMap.cancel() ? null : valueInMap;
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
/*     */   public ChunkLoadTask scheduleChunkLoad(int chunkX, int chunkZ, int priority, Consumer<ChunkRegionLoader.InProgressChunkHolder> onComplete, boolean intendingToBlock) {
/* 335 */     WorldServer world = this.world;
/*     */     
/* 337 */     return this.chunkLoadTasks.compute(Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ)), (keyInMap, valueInMap) -> {
/*     */           if (valueInMap != null) {
/*     */             if (!valueInMap.cancelled) {
/*     */               throw new IllegalStateException("Double scheduling chunk load for task: " + valueInMap.toString());
/*     */             }
/*     */             valueInMap.cancelled = false;
/*     */             valueInMap.onComplete = onComplete;
/*     */             return valueInMap;
/*     */           } 
/*     */           ChunkLoadTask ret = new ChunkLoadTask(world, chunkX, chunkZ, priority, this, onComplete);
/*     */           PaperFileIOThread.Holder.INSTANCE.loadChunkDataAsync(world, chunkX, chunkZ, priority, (), true, true, intendingToBlock);
/*     */           return ret;
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
/*     */   public ChunkSaveTask scheduleChunkSave(int chunkX, int chunkZ, int priority, ChunkRegionLoader.AsyncSaveData asyncSaveData, IChunkAccess chunk) {
/* 371 */     AsyncCatcher.catchOp("chunk save schedule");
/*     */     
/* 373 */     WorldServer world = this.world;
/*     */     
/* 375 */     return this.chunkSaveTasks.compute(Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ)), (keyInMap, valueInMap) -> {
/*     */           if (valueInMap != null) {
/*     */             throw new IllegalStateException("Double scheduling chunk save for task: " + valueInMap.toString());
/*     */           }
/*     */           ChunkSaveTask ret = new ChunkSaveTask(world, chunkX, chunkZ, priority, this, asyncSaveData, chunk);
/*     */           internalSchedule(ret);
/*     */           return ret;
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
/*     */   public CompletableFuture<NBTTagCompound> getChunkSaveFuture(int chunkX, int chunkZ) {
/* 395 */     ChunkSaveTask chunkSaveTask = this.chunkSaveTasks.get(Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ)));
/* 396 */     if (chunkSaveTask == null) {
/* 397 */       return null;
/*     */     }
/* 399 */     return chunkSaveTask.onComplete;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChunkAccess getChunkInSaveProgress(int chunkX, int chunkZ) {
/* 410 */     ChunkSaveTask chunkSaveTask = this.chunkSaveTasks.get(Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ)));
/* 411 */     if (chunkSaveTask == null) {
/* 412 */       return null;
/*     */     }
/* 414 */     return chunkSaveTask.chunk;
/*     */   }
/*     */ 
/*     */   
/*     */   public void flush() {
/* 419 */     drainChunkWaitQueue();
/* 420 */     PaperFileIOThread.Holder.INSTANCE.flush();
/* 421 */     drainChunkWaitQueue();
/*     */     
/* 423 */     if (this.workers == null) {
/* 424 */       if (Bukkit.isPrimaryThread() || MinecraftServer.getServer().hasStopped()) {
/* 425 */         (this.world.getChunkProvider()).serverThreadQueue.executeAll();
/*     */       } else {
/* 427 */         CompletableFuture<Void> wait = new CompletableFuture<>();
/* 428 */         MinecraftServer.getServer().scheduleOnMain(() -> (this.world.getChunkProvider()).serverThreadQueue.executeAll());
/*     */ 
/*     */         
/* 431 */         wait.join();
/*     */       } 
/*     */     } else {
/* 434 */       for (QueueExecutorThread<ChunkTask> worker : this.workers) {
/* 435 */         worker.flush();
/*     */       }
/*     */     } 
/* 438 */     if (globalUrgentWorker != null) globalUrgentWorker.flush();
/*     */ 
/*     */     
/* 441 */     drainChunkWaitQueue();
/* 442 */     PaperFileIOThread.Holder.INSTANCE.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close(boolean wait) {
/* 448 */     PaperFileIOThread.Holder.INSTANCE.flush();
/*     */     
/* 450 */     if (this.workers == null) {
/* 451 */       if (wait) {
/* 452 */         flush();
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 457 */     if (this.workers != globalWorkers) {
/* 458 */       for (QueueExecutorThread<ChunkTask> worker : this.workers) {
/* 459 */         worker.close(false, this.perWorldQueue);
/*     */       }
/*     */     }
/*     */     
/* 463 */     if (wait) {
/* 464 */       flush();
/*     */     }
/*     */   }
/*     */   
/*     */   public void raisePriority(int chunkX, int chunkZ, int priority) {
/* 469 */     Long chunkKey = Long.valueOf(IOUtil.getCoordinateKey(chunkX, chunkZ));
/*     */     
/* 471 */     ChunkTask chunkSaveTask = this.chunkSaveTasks.get(chunkKey);
/* 472 */     if (chunkSaveTask != null)
/*     */     {
/* 474 */       raiseTaskPriority(chunkSaveTask, (priority != 0) ? priority : 2);
/*     */     }
/*     */     
/* 477 */     ChunkLoadTask chunkLoadTask = this.chunkLoadTasks.get(chunkKey);
/* 478 */     if (chunkLoadTask != null) {
/* 479 */       raiseTaskPriority(chunkLoadTask, priority);
/*     */     }
/*     */   }
/*     */   
/*     */   private void raiseTaskPriority(ChunkTask task, int priority) {
/* 484 */     boolean raised = task.raisePriority(priority);
/* 485 */     if (task.isScheduled() && raised && this.workers != null)
/*     */     {
/* 487 */       if (priority == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 492 */         globalUrgentQueue.add(task);
/* 493 */         internalScheduleNotifyUrgent();
/*     */       } else {
/* 495 */         internalScheduleNotify();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void internalSchedule(ChunkTask task) {
/* 501 */     if (this.workers == null) {
/* 502 */       this.chunkTasks.add(task);
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 508 */     if (task.getPriority() == 0) {
/* 509 */       globalUrgentQueue.add(task);
/* 510 */       internalScheduleNotifyUrgent();
/*     */     } else {
/* 512 */       this.queue.add(task);
/* 513 */       internalScheduleNotify();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void internalScheduleNotify() {
/* 519 */     if (this.workers == null) {
/*     */       return;
/*     */     }
/* 522 */     for (QueueExecutorThread<ChunkTask> worker : this.workers) {
/* 523 */       if (worker.notifyTasks()) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void internalScheduleNotifyUrgent() {
/* 532 */     if (globalUrgentWorker == null) {
/*     */       return;
/*     */     }
/* 535 */     globalUrgentWorker.notifyTasks();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\io\chunk\ChunkTaskManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */