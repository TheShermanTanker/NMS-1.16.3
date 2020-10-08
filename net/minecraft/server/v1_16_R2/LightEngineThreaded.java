/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.function.IntSupplier;
/*     */ import java.util.function.LongFunction;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class LightEngineThreaded extends LightEngine implements AutoCloseable {
/*  17 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final ThreadedMailbox<Runnable> b;
/*  20 */   private static final int MAX_PRIORITIES = PlayerChunkMap.GOLDEN_TICKET + 2;
/*     */   
/*     */   private boolean isChunkLightStatus(long pair) {
/*  23 */     PlayerChunk playerChunk = this.playerChunkMap.getVisibleChunk(pair);
/*  24 */     if (playerChunk == null) {
/*  25 */       return false;
/*     */     }
/*  27 */     ChunkStatus status = PlayerChunk.getChunkStatus(playerChunk.getTicketLevel());
/*  28 */     return (status != null && status.isAtLeastStatus(ChunkStatus.LIGHT));
/*     */   }
/*     */   
/*     */   static class ChunkLightQueue {
/*     */     public boolean shouldFastUpdate;
/*  33 */     ArrayDeque<Runnable> pre = new ArrayDeque<>();
/*  34 */     ArrayDeque<Runnable> post = new ArrayDeque<>();
/*     */     
/*     */     ChunkLightQueue(long chunk) {}
/*     */   }
/*     */   
/*     */   static class PendingLightTask {
/*     */     long chunkId;
/*     */     IntSupplier priority;
/*     */     Runnable pre;
/*     */     Runnable post;
/*     */     boolean fastUpdate;
/*     */     
/*     */     public PendingLightTask(long chunkId, IntSupplier priority, Runnable pre, Runnable post, boolean fastUpdate) {
/*  47 */       this.chunkId = chunkId;
/*  48 */       this.priority = priority;
/*  49 */       this.pre = pre;
/*  50 */       this.post = post;
/*  51 */       this.fastUpdate = fastUpdate;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class LightQueue
/*     */   {
/*  58 */     private int size = 0;
/*  59 */     private final Long2ObjectLinkedOpenHashMap<LightEngineThreaded.ChunkLightQueue>[] buckets = (Long2ObjectLinkedOpenHashMap<LightEngineThreaded.ChunkLightQueue>[])new Long2ObjectLinkedOpenHashMap[LightEngineThreaded.MAX_PRIORITIES];
/*  60 */     private final ConcurrentLinkedQueue<LightEngineThreaded.PendingLightTask> pendingTasks = new ConcurrentLinkedQueue<>();
/*  61 */     private final ConcurrentLinkedQueue<Runnable> priorityChanges = new ConcurrentLinkedQueue<>();
/*     */     
/*     */     private LightQueue() {
/*  64 */       for (int i = 0; i < this.buckets.length; i++) {
/*  65 */         this.buckets[i] = new Long2ObjectLinkedOpenHashMap();
/*     */       }
/*     */     }
/*     */     
/*     */     public void changePriority(long pair, int currentPriority, int priority) {
/*  70 */       this.priorityChanges.add(() -> {
/*     */             LightEngineThreaded.ChunkLightQueue remove = (LightEngineThreaded.ChunkLightQueue)this.buckets[currentPriority].remove(pair);
/*     */             if (remove != null) {
/*     */               LightEngineThreaded.ChunkLightQueue existing = (LightEngineThreaded.ChunkLightQueue)this.buckets[priority].put(pair, remove);
/*     */               if (existing != null) {
/*     */                 remove.pre.addAll(existing.pre);
/*     */                 remove.post.addAll(existing.post);
/*     */               } 
/*     */             } 
/*     */           });
/*     */     }
/*     */     
/*     */     public final void addChunk(long chunkId, IntSupplier priority, Runnable pre, Runnable post) {
/*  83 */       this.pendingTasks.add(new LightEngineThreaded.PendingLightTask(chunkId, priority, pre, post, true));
/*  84 */       LightEngineThreaded.this.queueUpdate();
/*     */     }
/*     */     
/*     */     public final void add(long chunkId, IntSupplier priority, LightEngineThreaded.Update type, Runnable run) {
/*  88 */       this.pendingTasks.add(new LightEngineThreaded.PendingLightTask(chunkId, priority, (type == LightEngineThreaded.Update.PRE_UPDATE) ? run : null, (type == LightEngineThreaded.Update.POST_UPDATE) ? run : null, false));
/*     */     }
/*     */     public final void add(LightEngineThreaded.PendingLightTask update) {
/*  91 */       int priority = update.priority.getAsInt();
/*  92 */       LightEngineThreaded.ChunkLightQueue lightQueue = (LightEngineThreaded.ChunkLightQueue)this.buckets[priority].computeIfAbsent(update.chunkId, ChunkLightQueue::new);
/*     */       
/*  94 */       if (update.pre != null) {
/*  95 */         this.size++;
/*  96 */         lightQueue.pre.add(update.pre);
/*     */       } 
/*  98 */       if (update.post != null) {
/*  99 */         this.size++;
/* 100 */         lightQueue.post.add(update.post);
/*     */       } 
/* 102 */       if (update.fastUpdate) {
/* 103 */         lightQueue.shouldFastUpdate = true;
/*     */       }
/*     */     }
/*     */     
/*     */     public final boolean isEmpty() {
/* 108 */       return (this.size == 0 && this.pendingTasks.isEmpty());
/*     */     }
/*     */     
/*     */     public final int size() {
/* 112 */       return this.size;
/*     */     }
/*     */     
/*     */     public boolean poll(List<Runnable> pre, List<Runnable> post) {
/*     */       LightEngineThreaded.PendingLightTask pending;
/* 117 */       while ((pending = this.pendingTasks.poll()) != null) {
/* 118 */         add(pending);
/*     */       }
/*     */       Runnable run;
/* 121 */       while ((run = this.priorityChanges.poll()) != null) {
/* 122 */         run.run();
/*     */       }
/* 124 */       boolean hasWork = false;
/* 125 */       Long2ObjectLinkedOpenHashMap<LightEngineThreaded.ChunkLightQueue>[] buckets = this.buckets;
/* 126 */       int lowestPriority = 0;
/* 127 */       while (lowestPriority < LightEngineThreaded.MAX_PRIORITIES && !isEmpty()) {
/* 128 */         Long2ObjectLinkedOpenHashMap<LightEngineThreaded.ChunkLightQueue> bucket = buckets[lowestPriority];
/* 129 */         if (bucket.isEmpty()) {
/* 130 */           lowestPriority++;
/* 131 */           if (hasWork && lowestPriority <= 5) {
/* 132 */             return true;
/*     */           }
/*     */           
/*     */           continue;
/*     */         } 
/* 137 */         LightEngineThreaded.ChunkLightQueue queue = (LightEngineThreaded.ChunkLightQueue)bucket.removeFirst();
/* 138 */         this.size -= queue.pre.size() + queue.post.size();
/* 139 */         pre.addAll(queue.pre);
/* 140 */         post.addAll(queue.post);
/* 141 */         queue.pre.clear();
/* 142 */         queue.post.clear();
/* 143 */         hasWork = true;
/* 144 */         if (queue.shouldFastUpdate) {
/* 145 */           return true;
/*     */         }
/*     */       } 
/* 148 */       return hasWork;
/*     */     }
/*     */   }
/*     */   
/* 152 */   final LightQueue queue = new LightQueue();
/*     */   private final PlayerChunkMap d;
/*     */   private final PlayerChunkMap playerChunkMap;
/*     */   private final Mailbox<ChunkTaskQueueSorter.a<Runnable>> e;
/* 156 */   private volatile int f = 5;
/* 157 */   private final AtomicBoolean g = new AtomicBoolean(); private final List<Runnable> pre;
/*     */   private final List<Runnable> post;
/*     */   
/* 160 */   public LightEngineThreaded(ILightAccess ilightaccess, PlayerChunkMap playerchunkmap, boolean flag, ThreadedMailbox<Runnable> threadedmailbox, Mailbox<ChunkTaskQueueSorter.a<Runnable>> mailbox) { super(ilightaccess, true, flag);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 325 */     this.pre = new ArrayList<>();
/* 326 */     this.post = new ArrayList<>(); this.playerChunkMap = this.d = playerchunkmap; this.e = mailbox; this.b = threadedmailbox; } public void close() {} public int a(int i, boolean flag, boolean flag1) { throw (UnsupportedOperationException)SystemUtils.c(new UnsupportedOperationException("Ran authomatically on a different thread!")); } public void a(BlockPosition blockposition, int i) { throw (UnsupportedOperationException)SystemUtils.c(new UnsupportedOperationException("Ran authomatically on a different thread!")); } public void a(BlockPosition blockposition) { BlockPosition blockposition1 = blockposition.immutableCopy(); a(blockposition.getX() >> 4, blockposition.getZ() >> 4, Update.POST_UPDATE, SystemUtils.a(() -> super.a(blockposition1), () -> "checkBlock " + blockposition1)); } protected void a(ChunkCoordIntPair chunkcoordintpair) { a(chunkcoordintpair.x, chunkcoordintpair.z, () -> 0, Update.PRE_UPDATE, SystemUtils.a(() -> { super.b(chunkcoordintpair, false); super.a(chunkcoordintpair, false); int i; for (i = -1; i < 17; i++) { super.a(EnumSkyBlock.BLOCK, SectionPosition.a(chunkcoordintpair, i), (NibbleArray)null, true); super.a(EnumSkyBlock.SKY, SectionPosition.a(chunkcoordintpair, i), (NibbleArray)null, true); }  for (i = 0; i < 16; i++) super.a(SectionPosition.a(chunkcoordintpair, i), true);  }() -> "updateChunkStatus " + chunkcoordintpair + " " + '\001')); }
/*     */   public void a(SectionPosition sectionposition, boolean flag) { a(sectionposition.a(), sectionposition.c(), () -> 0, Update.PRE_UPDATE, SystemUtils.a(() -> super.a(sectionposition, flag), () -> "updateSectionStatus " + sectionposition + " " + flag)); }
/* 328 */   private void b() { if (this.queue.poll(this.pre, this.post)) {
/* 329 */       this.pre.forEach(Runnable::run);
/* 330 */       this.pre.clear();
/* 331 */       super.a(2147483647, true, true);
/* 332 */       this.post.forEach(Runnable::run);
/* 333 */       this.post.clear();
/*     */     } else {
/*     */       
/* 336 */       super.a(2147483647, true, true);
/*     */     }  }
/*     */   public void a(ChunkCoordIntPair chunkcoordintpair, boolean flag) { a(chunkcoordintpair.x, chunkcoordintpair.z, Update.PRE_UPDATE, SystemUtils.a(() -> super.a(chunkcoordintpair, flag), () -> "enableLight " + chunkcoordintpair + " " + flag)); }
/*     */   public void a(EnumSkyBlock enumskyblock, SectionPosition sectionposition, @Nullable NibbleArray nibblearray, boolean flag) { a(sectionposition.a(), sectionposition.c(), () -> 0, Update.PRE_UPDATE, SystemUtils.a(() -> super.a(enumskyblock, sectionposition, nibblearray, flag), () -> "queueData " + sectionposition)); }
/*     */   private void a(int i, int j, Update lightenginethreaded_update, Runnable runnable) {
/*     */     a(i, j, this.d.c(ChunkCoordIntPair.pair(i, j)), lightenginethreaded_update, runnable);
/* 342 */   } public void a(int i) { this.f = i; }
/*     */   private void a(int i, int j, IntSupplier intsupplier, Update lightenginethreaded_update, Runnable runnable) { this.queue.add(ChunkCoordIntPair.pair(i, j), intsupplier, lightenginethreaded_update, runnable); }
/*     */   public void b(ChunkCoordIntPair chunkcoordintpair, boolean flag) { a(chunkcoordintpair.x, chunkcoordintpair.z, () -> 0, Update.PRE_UPDATE, SystemUtils.a(() -> super.b(chunkcoordintpair, flag), () -> "retainData " + chunkcoordintpair)); } public CompletableFuture<IChunkAccess> a(IChunkAccess ichunkaccess, boolean flag) { ChunkCoordIntPair chunkcoordintpair = ichunkaccess.getPos(); long pair = chunkcoordintpair.pair(); CompletableFuture<IChunkAccess> future = new CompletableFuture<>(); IntSupplier prioritySupplier = this.playerChunkMap.getPrioritySupplier(pair); boolean[] skippedPre = { false }; this.queue.addChunk(pair, prioritySupplier, SystemUtils.a(() -> { if (!isChunkLightStatus(pair)) { future.complete(ichunkaccess); skippedPre[0] = true; return; }  ChunkSection[] achunksection = ichunkaccess.getSections(); for (int i = 0; i < 16; i++) { ChunkSection chunksection = achunksection[i]; if (!ChunkSection.a(chunksection)) super.a(SectionPosition.a(chunkcoordintpair, i), false);  }  super.a(chunkcoordintpair, true); if (!flag) ichunkaccess.m().forEach(());  }() -> "lightChunk " + chunkcoordintpair + " " + flag), () -> { this.d.c(chunkcoordintpair); if (skippedPre[0]) return;  ichunkaccess.b(true); super.b(chunkcoordintpair, false); future.complete(ichunkaccess); }); return future; } public void queueUpdate() { if ((!this.queue.isEmpty() || a()) && this.g.compareAndSet(false, true))
/*     */       this.b.a(() -> { b(); this.g.set(false); queueUpdate(); });  } enum Update
/*     */   {
/* 347 */     PRE_UPDATE, POST_UPDATE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngineThreaded.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */