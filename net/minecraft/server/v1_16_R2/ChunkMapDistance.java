/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.util.misc.PooledLinkedHashSets;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import com.tuinity.tuinity.config.TuinityConfig;
/*     */ import com.tuinity.tuinity.util.TickThread;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ByteMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2IntMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2IntMaps;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.LongFunction;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ 
/*     */ public abstract class ChunkMapDistance {
/*  30 */   private static final Logger LOGGER = LogManager.getLogger();
/*  31 */   private static final int b = 33 + ChunkStatus.a(ChunkStatus.FULL) - 2;
/*  32 */   private final Long2ObjectMap<ObjectSet<EntityPlayer>> c = (Long2ObjectMap<ObjectSet<EntityPlayer>>)new Long2ObjectOpenHashMap();
/*  33 */   public final Long2ObjectOpenHashMap<ArraySetSorted<Ticket<?>>> tickets = new Long2ObjectOpenHashMap();
/*  34 */   private final a ticketLevelTracker = new a(); public static final int MOB_SPAWN_RANGE = 8; final a getTicketTracker() { return this.ticketLevelTracker; }
/*     */   
/*  36 */   private final c g = new c(33);
/*     */   
/*  38 */   public final Queue<PlayerChunk> pendingChunkUpdates = new ArrayDeque<PlayerChunk>()
/*     */     {
/*     */       public boolean add(PlayerChunk o) {
/*  41 */         if (o.isUpdateQueued) return true; 
/*  42 */         o.isUpdateQueued = true;
/*  43 */         return super.add(o);
/*     */       }
/*     */     };
/*     */   
/*     */   private final ChunkTaskQueueSorter i;
/*     */   private final Mailbox<ChunkTaskQueueSorter.a<Runnable>> j;
/*     */   private final Mailbox<ChunkTaskQueueSorter.b> k;
/*  50 */   private final LongSet l = (LongSet)new LongOpenHashSet(); private final Executor m; private long currentTick; PlayerChunkMap chunkMap; private long nextUnloadId; public final LongSet getOnPlayerTicketAddQueue() { return this.l; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   private final Long2ObjectOpenHashMap<Ticket<Long>> delayedChunks = new Long2ObjectOpenHashMap();
/*     */   private final LongFunction<Ticket<Long>> computeFuntion; boolean pollingPendingChunkUpdates;
/*  60 */   public final void removeTickets(long chunk, TicketType<?> type) { ArraySetSorted<Ticket<?>> tickets = (ArraySetSorted<Ticket<?>>)this.tickets.get(chunk);
/*  61 */     if (tickets == null) {
/*     */       return;
/*     */     }
/*  64 */     if (type == TicketType.DELAYED_UNLOAD) {
/*  65 */       this.delayedChunks.remove(chunk);
/*     */     }
/*  67 */     boolean changed = tickets.removeIf(ticket -> (ticket.getTicketType() == type));
/*     */ 
/*     */     
/*  70 */     if (changed)
/*  71 */       getTicketTracker().update(chunk, getLowestTicketLevel(tickets), false);  }
/*     */   private void computeDelayedTicketFor(long chunk, int removedLevel, ArraySetSorted<Ticket<?>> tickets) { int lowestLevel = getLowestTicketLevel(tickets); if (removedLevel > lowestLevel) return;  Ticket<Long> ticket = (Ticket<Long>)this.delayedChunks.computeIfAbsent(chunk, this.computeFuntion); if (ticket.getTicketLevel() != -1) tickets.remove(ticket);  ticket.setCreationTick(this.currentTick); ticket.setTicketLevel(removedLevel); tickets.add(ticket); }
/*     */   protected void purgeTickets() { TickThread.softEnsureTickThread("Async purge tickets"); this.currentTick++; ObjectIterator objectiterator = this.tickets.long2ObjectEntrySet().fastIterator(); int[] tempLevel = { PlayerChunkMap.GOLDEN_TICKET + 1 }; while (objectiterator.hasNext()) { Long2ObjectMap.Entry<ArraySetSorted<Ticket<?>>> entry = (Long2ObjectMap.Entry<ArraySetSorted<Ticket<?>>>)objectiterator.next(); if (((ArraySetSorted)entry.getValue()).removeIf(ticket -> { boolean ret = ticket.isExpired(this.currentTick); if (TuinityConfig.delayChunkUnloadsBy <= 0) return ret;  if (ret && (ticket.getTicketType()).delayUnloadViable && ticket.getTicketLevel() < tempLevel[0]) tempLevel[0] = ticket.getTicketLevel();  if (ticket.getTicketType() == TicketType.DELAYED_UNLOAD && ticket.isCached) this.delayedChunks.remove(entry.getLongKey(), ticket);  return ret; })) { if (tempLevel[0] < PlayerChunkMap.GOLDEN_TICKET + 1) computeDelayedTicketFor(entry.getLongKey(), tempLevel[0], (ArraySetSorted<Ticket<?>>)entry.getValue());  this.ticketLevelTracker.update(entry.getLongKey(), getLowestTicketLevel((ArraySetSorted<Ticket<?>>)entry.getValue()), false); }  if (((ArraySetSorted)entry.getValue()).isEmpty()) objectiterator.remove();  }  }
/*     */   private static int getLowestTicketLevel(ArraySetSorted<Ticket<?>> arraysetsorted) { AsyncCatcher.catchOp("ChunkMapDistance::getLowestTicketLevel"); return !arraysetsorted.isEmpty() ? ((Ticket)arraysetsorted.b()).b() : (PlayerChunkMap.GOLDEN_TICKET + 1); }
/*  75 */   public boolean a(PlayerChunkMap playerchunkmap) { TickThread.softEnsureTickThread("Cannot tick ChunkMapDistance off of the main-thread"); AsyncCatcher.catchOp("DistanceManagerTick"); this.g.a(); int i = Integer.MAX_VALUE - this.ticketLevelTracker.a(2147483647); boolean flag = (i != 0); if (flag); if (!this.pendingChunkUpdates.isEmpty()) { this.pollingPendingChunkUpdates = true; try { while (!this.pendingChunkUpdates.isEmpty()) { PlayerChunk remove = this.pendingChunkUpdates.remove(); remove.isUpdateQueued = false; remove.a(playerchunkmap); }  } finally { this.pollingPendingChunkUpdates = false; }  return true; }  if (!this.l.isEmpty()) { LongIterator longiterator = this.l.iterator(); while (longiterator.hasNext()) { long j = longiterator.nextLong(); if (e(j).stream().anyMatch(ticket -> (ticket.getTicketType() == TicketType.PLAYER))) { PlayerChunk playerchunk = playerchunkmap.getUpdatingChunk(j); if (playerchunk == null) throw new IllegalStateException();  CompletableFuture<Either<Chunk, PlayerChunk.Failure>> completablefuture = playerchunk.b(); completablefuture.thenAccept(either -> this.m.execute(())); }  }  this.l.clear(); }  return flag; } private boolean addTicket(long i, Ticket<?> ticket) { AsyncCatcher.catchOp("ChunkMapDistance::addTicket"); ArraySetSorted<Ticket<?>> arraysetsorted = e(i); int j = getLowestTicketLevel(arraysetsorted); Ticket<?> ticket1 = arraysetsorted.a(ticket); ticket1.a(this.currentTick); if (ticket.b() < j) this.ticketLevelTracker.update(i, ticket.b(), true);  return (ticket == ticket1); } protected ChunkMapDistance(Executor executor, Executor executor1) { this.computeFuntion = (key -> {
/*     */         Ticket<Long> ret = new Ticket<>(TicketType.DELAYED_UNLOAD, -1, Long.valueOf(++this.nextUnloadId));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         ret.isCached = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         return ret;
/*     */       });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     this.pollingPendingChunkUpdates = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 264 */     this.delayDistanceManagerTick = false; executor1.getClass(); Objects.requireNonNull(executor1); Mailbox<Runnable> mailbox = Mailbox.a("player ticket throttler", executor1::execute); ChunkTaskQueueSorter chunktaskqueuesorter = new ChunkTaskQueueSorter((List<Mailbox<?>>)ImmutableList.of(mailbox), executor, 4); this.i = chunktaskqueuesorter; this.j = chunktaskqueuesorter.a(mailbox, true); this.k = chunktaskqueuesorter.a(mailbox); this.m = executor1; }
/*     */   private boolean removeTicket(long i, Ticket<?> ticket) { AsyncCatcher.catchOp("ChunkMapDistance::removeTicket"); ArraySetSorted<Ticket<?>> arraysetsorted = e(i); int oldLevel = getLowestTicketLevel(arraysetsorted); boolean removed = false; if (arraysetsorted.remove(ticket)) { removed = true; if (TuinityConfig.delayChunkUnloadsBy > 0 && (ticket.getTicketType()).delayUnloadViable) computeDelayedTicketFor(i, ticket.getTicketLevel(), arraysetsorted);  }  if (arraysetsorted.isEmpty()) this.tickets.remove(i);  int newLevel = getLowestTicketLevel(arraysetsorted); if (newLevel > oldLevel) this.ticketLevelTracker.update(i, newLevel, false);  return removed; }
/* 266 */   public <T> void a(TicketType<T> tickettype, ChunkCoordIntPair chunkcoordintpair, int i, T t0) { addTicketAtLevel(tickettype, chunkcoordintpair, i, t0); } public static final int PRIORITY_TICKET_LEVEL = PlayerChunkMap.GOLDEN_TICKET; public static final int URGENT_PRIORITY = 29; public boolean delayDistanceManagerTick; public boolean markUrgent(ChunkCoordIntPair coords) { return addPriorityTicket(coords, TicketType.URGENT, 29); }
/*     */   
/*     */   public boolean markHighPriority(ChunkCoordIntPair coords, int priority) {
/* 269 */     priority = Math.min(28, Math.max(1, priority));
/* 270 */     return addPriorityTicket(coords, TicketType.PRIORITY, priority);
/*     */   }
/*     */   
/*     */   public void markAreaHighPriority(ChunkCoordIntPair center, int priority, int radius) {
/* 274 */     this.delayDistanceManagerTick = true;
/* 275 */     priority = Math.min(28, Math.max(1, priority));
/* 276 */     int finalPriority = priority;
/* 277 */     MCUtil.getSpiralOutChunks(center.asPosition(), radius).forEach(coords -> addPriorityTicket(coords, TicketType.PRIORITY, finalPriority));
/*     */ 
/*     */     
/* 280 */     this.delayDistanceManagerTick = false;
/* 281 */     this.chunkMap.world.getChunkProvider().tickDistanceManager();
/*     */   }
/*     */   
/*     */   public void clearAreaPriorityTickets(ChunkCoordIntPair center, int radius) {
/* 285 */     this.delayDistanceManagerTick = true;
/* 286 */     MCUtil.getSpiralOutChunks(center.asPosition(), radius).forEach(coords -> removeTicket(coords.pair(), new Ticket(TicketType.PRIORITY, PRIORITY_TICKET_LEVEL, coords)));
/*     */ 
/*     */     
/* 289 */     this.delayDistanceManagerTick = false;
/* 290 */     this.chunkMap.world.getChunkProvider().tickDistanceManager();
/*     */   }
/*     */   
/*     */   private boolean hasPlayerTicket(ChunkCoordIntPair coords, int level) {
/* 294 */     ArraySetSorted<Ticket<?>> tickets = (ArraySetSorted<Ticket<?>>)this.tickets.get(coords.pair());
/* 295 */     if (tickets == null || tickets.isEmpty()) {
/* 296 */       return false;
/*     */     }
/* 298 */     for (Ticket<?> ticket : tickets) {
/* 299 */       if (ticket.getTicketType() == TicketType.PLAYER && ticket.getTicketLevel() == level) {
/* 300 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 304 */     return false;
/*     */   }
/*     */   
/*     */   private boolean addPriorityTicket(ChunkCoordIntPair coords, TicketType<ChunkCoordIntPair> ticketType, int priority) {
/* 308 */     AsyncCatcher.catchOp("ChunkMapDistance::addPriorityTicket");
/* 309 */     long pair = coords.pair();
/* 310 */     PlayerChunk chunk = this.chunkMap.getUpdatingChunk(pair);
/* 311 */     boolean needsTicket = (this.chunkMap.playerViewDistanceNoTickMap.getObjectsInRange(pair) != null && !hasPlayerTicket(coords, 33));
/*     */     
/* 313 */     if (needsTicket) {
/* 314 */       Ticket<?> ticket = new Ticket(TicketType.PLAYER, 33, coords);
/* 315 */       getOnPlayerTicketAddQueue().add(pair);
/* 316 */       addTicket(pair, ticket);
/*     */     } 
/* 318 */     if (chunk != null && chunk.isFullChunkReady()) {
/* 319 */       if (needsTicket) {
/* 320 */         this.chunkMap.world.getChunkProvider().tickDistanceManager();
/*     */       }
/* 322 */       return needsTicket;
/*     */     } 
/*     */     
/*     */     boolean success;
/* 326 */     if (!(success = updatePriorityTicket(coords, ticketType, priority))) {
/* 327 */       Ticket<ChunkCoordIntPair> ticket = new Ticket<>(ticketType, PRIORITY_TICKET_LEVEL, coords);
/* 328 */       ticket.priority = priority;
/* 329 */       success = addTicket(pair, ticket);
/*     */     } else {
/* 331 */       if (chunk == null) {
/* 332 */         chunk = this.chunkMap.getUpdatingChunk(pair);
/*     */       }
/* 334 */       this.chunkMap.queueHolderUpdate(chunk);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 339 */     this.chunkMap.world.getChunkProvider().tickDistanceManager();
/*     */     
/* 341 */     return success;
/*     */   }
/*     */   
/*     */   private boolean updatePriorityTicket(ChunkCoordIntPair coords, TicketType<ChunkCoordIntPair> type, int priority) {
/* 345 */     ArraySetSorted<Ticket<?>> tickets = (ArraySetSorted<Ticket<?>>)this.tickets.get(coords.pair());
/* 346 */     if (tickets == null) {
/* 347 */       return false;
/*     */     }
/* 349 */     for (Ticket<?> ticket : tickets) {
/* 350 */       if (ticket.getTicketType() == type) {
/*     */         
/* 352 */         ticket.setCurrentTick(this.currentTick);
/* 353 */         ticket.priority = Math.max(ticket.priority, priority);
/* 354 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 358 */     return false;
/*     */   }
/*     */   
/*     */   public int getChunkPriority(ChunkCoordIntPair coords) {
/* 362 */     AsyncCatcher.catchOp("ChunkMapDistance::getChunkPriority");
/* 363 */     ArraySetSorted<Ticket<?>> tickets = (ArraySetSorted<Ticket<?>>)this.tickets.get(coords.pair());
/* 364 */     if (tickets == null) {
/* 365 */       return 0;
/*     */     }
/* 367 */     for (Ticket<?> ticket : tickets) {
/* 368 */       if (ticket.getTicketType() == TicketType.URGENT) {
/* 369 */         return 29;
/*     */       }
/*     */     } 
/* 372 */     for (Ticket<?> ticket : tickets) {
/* 373 */       if (ticket.getTicketType() == TicketType.PRIORITY && ticket.priority > 0) {
/* 374 */         return ticket.priority;
/*     */       }
/*     */     } 
/* 377 */     return 0;
/*     */   }
/*     */   
/*     */   public void clearPriorityTickets(ChunkCoordIntPair coords) {
/* 381 */     AsyncCatcher.catchOp("ChunkMapDistance::clearPriority");
/* 382 */     removeTicket(coords.pair(), new Ticket(TicketType.PRIORITY, PRIORITY_TICKET_LEVEL, coords));
/*     */   }
/*     */   
/*     */   public void clearUrgent(ChunkCoordIntPair coords) {
/* 386 */     AsyncCatcher.catchOp("ChunkMapDistance::clearUrgent");
/* 387 */     removeTicket(coords.pair(), new Ticket(TicketType.URGENT, PRIORITY_TICKET_LEVEL, coords));
/*     */   }
/*     */   
/*     */   public <T> boolean addTicketAtLevel(TicketType<T> ticketType, ChunkCoordIntPair chunkcoordintpair, int level, T identifier) {
/* 391 */     return addTicket(chunkcoordintpair.pair(), new Ticket(ticketType, level, identifier));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> void b(TicketType<T> tickettype, ChunkCoordIntPair chunkcoordintpair, int i, T t0) {
/* 397 */     removeTicketAtLevel(tickettype, chunkcoordintpair, i, t0);
/*     */   }
/*     */   
/*     */   public <T> boolean removeTicketAtLevel(TicketType<T> ticketType, ChunkCoordIntPair chunkcoordintpair, int level, T identifier) {
/* 401 */     Ticket<T> ticket = new Ticket<>(ticketType, level, identifier);
/*     */     
/* 403 */     return removeTicket(chunkcoordintpair.pair(), ticket);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void addTicket(TicketType<T> tickettype, ChunkCoordIntPair chunkcoordintpair, int i, T t0) {
/* 408 */     addTicket(chunkcoordintpair.pair(), new Ticket(tickettype, 33 - i, t0));
/*     */   }
/*     */   
/*     */   public <T> void removeTicket(TicketType<T> tickettype, ChunkCoordIntPair chunkcoordintpair, int i, T t0) {
/* 412 */     Ticket<T> ticket = new Ticket<>(tickettype, 33 - i, t0);
/*     */     
/* 414 */     removeTicket(chunkcoordintpair.pair(), ticket);
/*     */   }
/*     */   
/*     */   private ArraySetSorted<Ticket<?>> e(long i) {
/* 418 */     TickThread.softEnsureTickThread("Async tickets compute");
/* 419 */     return (ArraySetSorted<Ticket<?>>)this.tickets.computeIfAbsent(i, j -> ArraySetSorted.a(4));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(ChunkCoordIntPair chunkcoordintpair, boolean flag) {
/* 425 */     Ticket<ChunkCoordIntPair> ticket = new Ticket<>(TicketType.FORCED, 31, chunkcoordintpair);
/*     */     
/* 427 */     if (flag) {
/* 428 */       addTicket(chunkcoordintpair.pair(), ticket);
/*     */     } else {
/* 430 */       removeTicket(chunkcoordintpair.pair(), ticket);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(SectionPosition sectionposition, EntityPlayer entityplayer) {
/* 436 */     TickThread.softEnsureTickThread("Async player add");
/* 437 */     long i = sectionposition.r().pair();
/*     */     
/* 439 */     ((ObjectSet)this.c.computeIfAbsent(i, j -> new ObjectOpenHashSet()))
/*     */       
/* 441 */       .add(entityplayer);
/*     */     
/* 443 */     this.g.update(i, 0, true);
/*     */   }
/*     */   
/*     */   public void b(SectionPosition sectionposition, EntityPlayer entityplayer) {
/* 447 */     TickThread.softEnsureTickThread("Async player remove");
/* 448 */     long i = sectionposition.r().pair();
/* 449 */     ObjectSet<EntityPlayer> objectset = (ObjectSet<EntityPlayer>)this.c.get(i);
/*     */     
/* 451 */     if (objectset != null) objectset.remove(entityplayer); 
/* 452 */     if (objectset == null || objectset.isEmpty()) {
/* 453 */       this.c.remove(i);
/*     */       
/* 455 */       this.g.update(i, 2147483647, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String c(long i) {
/*     */     String s;
/* 461 */     ArraySetSorted<Ticket<?>> arraysetsorted = (ArraySetSorted<Ticket<?>>)this.tickets.get(i);
/*     */ 
/*     */     
/* 464 */     if (arraysetsorted != null && !arraysetsorted.isEmpty()) {
/* 465 */       s = ((Ticket)arraysetsorted.b()).toString();
/*     */     } else {
/* 467 */       s = "no_ticket";
/*     */     } 
/*     */     
/* 470 */     return s;
/*     */   }
/*     */   
/*     */   protected void setNoTickViewDistance(int i) {
/* 474 */     this.g.a(i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 480 */     return this.chunkMap.playerChunkTickRangeMap.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean d(long i) {
/* 487 */     return (this.chunkMap.playerChunkTickRangeMap.getObjectsInRange(i) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String c() {
/* 492 */     return this.i.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void removeAllTicketsFor(TicketType<T> ticketType, int ticketLevel, T ticketIdentifier) {
/* 497 */     TickThread.softEnsureTickThread("Async ticket remove");
/* 498 */     Ticket<T> target = new Ticket<>(ticketType, ticketLevel, ticketIdentifier);
/*     */     
/* 500 */     for (ObjectIterator<Long2ObjectMap.Entry<ArraySetSorted<Ticket<?>>>> objectIterator = this.tickets.long2ObjectEntrySet().fastIterator(); objectIterator.hasNext(); ) {
/* 501 */       Long2ObjectMap.Entry<ArraySetSorted<Ticket<?>>> entry = objectIterator.next();
/* 502 */       ArraySetSorted<Ticket<?>> tickets = (ArraySetSorted<Ticket<?>>)entry.getValue();
/* 503 */       if (tickets.remove(target)) {
/*     */         
/* 505 */         this.ticketLevelTracker.update(entry.getLongKey(), getLowestTicketLevel(tickets), false);
/*     */ 
/*     */         
/* 508 */         if (tickets.isEmpty())
/* 509 */           objectIterator.remove(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   protected abstract boolean a(long paramLong);
/*     */   @Nullable
/*     */   protected abstract PlayerChunk b(long paramLong);
/*     */   @Nullable
/*     */   protected abstract PlayerChunk a(long paramLong, int paramInt1, @Nullable PlayerChunk paramPlayerChunk, int paramInt2);
/*     */   class a extends ChunkMap { public a() {
/* 519 */       super(PlayerChunkMap.GOLDEN_TICKET + 2, 16, 256);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int b(long i) {
/* 524 */       ArraySetSorted<Ticket<?>> arraysetsorted = (ArraySetSorted<Ticket<?>>)ChunkMapDistance.this.tickets.get(i);
/*     */       
/* 526 */       return (arraysetsorted == null) ? Integer.MAX_VALUE : (arraysetsorted.isEmpty() ? Integer.MAX_VALUE : ((Ticket)arraysetsorted.b()).b());
/*     */     }
/*     */ 
/*     */     
/*     */     protected int c(long i) {
/* 531 */       if (!ChunkMapDistance.this.a(i)) {
/* 532 */         PlayerChunk playerchunk = ChunkMapDistance.this.b(i);
/*     */         
/* 534 */         if (playerchunk != null) {
/* 535 */           return playerchunk.getTicketLevel();
/*     */         }
/*     */       } 
/*     */       
/* 539 */       return PlayerChunkMap.GOLDEN_TICKET + 1;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(long i, int j) {
/* 544 */       PlayerChunk playerchunk = ChunkMapDistance.this.b(i);
/* 545 */       int k = (playerchunk == null) ? (PlayerChunkMap.GOLDEN_TICKET + 1) : playerchunk.getTicketLevel();
/*     */       
/* 547 */       if (k != j) {
/* 548 */         playerchunk = ChunkMapDistance.this.a(i, j, playerchunk, k);
/* 549 */         if (playerchunk != null) {
/* 550 */           ChunkMapDistance.this.pendingChunkUpdates.add(playerchunk);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(int i) {
/* 557 */       return b(i);
/*     */     } }
/*     */ 
/*     */   
/*     */   class c
/*     */     extends b {
/* 563 */     private int e = 0;
/* 564 */     private final Long2IntMap f = Long2IntMaps.synchronize((Long2IntMap)new Long2IntOpenHashMap());
/* 565 */     private final LongSet g = (LongSet)new LongOpenHashSet();
/*     */     
/*     */     protected c(int i) {
/* 568 */       super(i);
/* 569 */       this.f.defaultReturnValue(i + 2);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(long i, int j, int k) {
/* 574 */       this.g.add(i);
/*     */     }
/*     */     
/*     */     public void a(int i) {
/* 578 */       ObjectIterator objectiterator = this.a.long2ByteEntrySet().iterator();
/*     */       
/* 580 */       while (objectiterator.hasNext()) {
/* 581 */         Long2ByteMap.Entry it_unimi_dsi_fastutil_longs_long2bytemap_entry = (Long2ByteMap.Entry)objectiterator.next();
/* 582 */         byte b0 = it_unimi_dsi_fastutil_longs_long2bytemap_entry.getByteValue();
/* 583 */         long j = it_unimi_dsi_fastutil_longs_long2bytemap_entry.getLongKey();
/*     */         
/* 585 */         a(j, b0, c(b0), (b0 <= i - 2));
/*     */       } 
/*     */       
/* 588 */       this.e = i;
/*     */     }
/*     */     
/*     */     private void a(long i, int j, boolean flag, boolean flag1) {
/* 592 */       if (flag != flag1) {
/* 593 */         ChunkCoordIntPair coords = new ChunkCoordIntPair(i);
/* 594 */         Ticket<?> ticket = new Ticket(TicketType.PLAYER, 33, coords);
/*     */         
/* 596 */         if (flag1) {
/* 597 */           scheduleChunkLoad(i, MinecraftServer.currentTick, j, priority -> {
/*     */                 if (!isChunkInRange(i)) {
/*     */                   ChunkMapDistance.this.k.a(ChunkTaskQueueSorter.a((), i, false));
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/*     */                   return;
/*     */                 } 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 if (ChunkMapDistance.this.hasPlayerTicket(coords, 33)) {
/*     */                   return;
/*     */                 }
/*     */ 
/*     */ 
/*     */                 
/*     */                 if (priority.intValue() <= 3) {
/*     */                   ChunkMapDistance.this.addTicket(i, ticket);
/*     */ 
/*     */ 
/*     */                   
/*     */                   ChunkMapDistance.this.l.add(i);
/*     */ 
/*     */ 
/*     */                   
/*     */                   return;
/*     */                 } 
/*     */ 
/*     */ 
/*     */                 
/*     */                 ChunkMapDistance.this.j.a(ChunkTaskQueueSorter.a((), i, ()));
/*     */               });
/*     */         } else {
/* 633 */           ChunkMapDistance.this.k.a(ChunkTaskQueueSorter.a(() -> ChunkMapDistance.this.m.execute(()), i, true));
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean isChunkInRange(long i) {
/* 646 */       return isLoadedChunkLevel(getChunkLevel(i));
/*     */     }
/*     */     public void scheduleChunkLoad(long i, long startTick, int initialDistance, Consumer<Integer> task) {
/* 649 */       long elapsed = MinecraftServer.currentTick - startTick;
/* 650 */       ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(i);
/* 651 */       PlayerChunk updatingChunk = ChunkMapDistance.this.chunkMap.getUpdatingChunk(i);
/* 652 */       if ((updatingChunk != null && updatingChunk.isFullChunkReady()) || !isChunkInRange(i) || ChunkMapDistance.this.getChunkPriority(chunkPos) > 0) {
/*     */         
/* 654 */         task.accept(Integer.valueOf(1));
/*     */         
/*     */         return;
/*     */       } 
/* 658 */       int desireDelay = 0;
/* 659 */       double minDist = Double.MAX_VALUE;
/* 660 */       PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> players = ChunkMapDistance.this.chunkMap.playerViewDistanceNoTickMap.getObjectsInRange(i);
/* 661 */       if (elapsed == 0L && initialDistance <= 4) {
/*     */         
/* 663 */         minDist = initialDistance;
/* 664 */       } else if (players != null) {
/* 665 */         Object[] backingSet = players.getBackingSet();
/*     */         
/* 667 */         BlockPosition blockPos = chunkPos.asPosition();
/*     */         
/* 669 */         boolean isFront = false;
/* 670 */         BlockPosition.MutableBlockPosition pos = new BlockPosition.MutableBlockPosition();
/* 671 */         for (int index = 0, len = backingSet.length; index < len; index++) {
/* 672 */           if (backingSet[index] instanceof EntityPlayer) {
/*     */ 
/*     */             
/* 675 */             EntityPlayer player = (EntityPlayer)backingSet[index];
/*     */             
/* 677 */             ChunkCoordIntPair pointInFront = player.getChunkInFront(5.0D);
/* 678 */             pos.setValues(pointInFront.x << 4, 0, pointInFront.z << 4);
/* 679 */             double frontDist = MCUtil.distanceSq(pos, blockPos);
/*     */             
/* 681 */             pos.setValues(player.locX(), 0.0D, player.locZ());
/* 682 */             double center = MCUtil.distanceSq(pos, blockPos);
/*     */             
/* 684 */             double dist = Math.min(frontDist, center);
/* 685 */             if (!isFront) {
/* 686 */               ChunkCoordIntPair pointInBack = player.getChunkInFront(-7.0D);
/* 687 */               pos.setValues(pointInBack.x << 4, 0, pointInBack.z << 4);
/* 688 */               double backDist = MCUtil.distanceSq(pos, blockPos);
/* 689 */               if (frontDist < backDist) {
/* 690 */                 isFront = true;
/*     */               }
/*     */             } 
/* 693 */             if (dist < minDist)
/* 694 */               minDist = dist; 
/*     */           } 
/*     */         } 
/* 697 */         if (minDist == Double.MAX_VALUE) {
/* 698 */           minDist = 15.0D;
/*     */         } else {
/* 700 */           minDist = Math.sqrt(minDist) / 16.0D;
/*     */         } 
/* 702 */         if (minDist > 4.0D) {
/*     */ 
/*     */           
/* 705 */           int desiredTimeDelayMax = isFront ? ((minDist < 10.0D) ? 7 : 15) : ((minDist < 10.0D) ? 15 : 45);
/* 706 */           desireDelay = (int)(desireDelay + (desiredTimeDelayMax * 20) * minDist / 32.0D);
/*     */         } 
/*     */       } else {
/* 709 */         minDist = initialDistance;
/* 710 */         desireDelay = 1;
/*     */       } 
/* 712 */       long delay = desireDelay - elapsed;
/* 713 */       if (delay <= 0L && minDist > 4.0D && minDist < Double.MAX_VALUE) {
/* 714 */         boolean hasAnyNeighbor = false;
/* 715 */         for (int x = -1; x <= 1; x++) {
/* 716 */           for (int z = -1; z <= 1; z++) {
/* 717 */             if (x != 0 || z != 0) {
/* 718 */               long pair = ChunkCoordIntPair.pair(chunkPos.x + x, chunkPos.z + z);
/* 719 */               PlayerChunk neighbor = ChunkMapDistance.this.chunkMap.getUpdatingChunk(pair);
/* 720 */               ChunkStatus current = (neighbor != null) ? neighbor.getChunkHolderStatus() : null;
/* 721 */               if (current != null && current.isAtLeastStatus(ChunkStatus.LIGHT))
/* 722 */                 hasAnyNeighbor = true; 
/*     */             } 
/*     */           } 
/*     */         } 
/* 726 */         if (!hasAnyNeighbor) {
/* 727 */           delay += 20L;
/*     */         }
/*     */       } 
/* 730 */       if (delay <= 0L) {
/* 731 */         task.accept(Integer.valueOf((int)minDist));
/*     */       } else {
/* 733 */         int taskDelay = (int)Math.min(delay, (minDist >= 10.0D) ? 40L : ((minDist < 6.0D) ? 5L : 20L));
/* 734 */         MCUtil.scheduleTask(taskDelay, () -> scheduleChunkLoad(i, startTick, initialDistance, task), "Player Ticket Delayer");
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void a() {
/* 741 */       super.a();
/* 742 */       if (!this.g.isEmpty()) {
/* 743 */         LongIterator longiterator = this.g.iterator();
/*     */         
/* 745 */         while (longiterator.hasNext()) {
/* 746 */           long i = longiterator.nextLong();
/* 747 */           int j = this.f.get(i);
/* 748 */           int k = c(i);
/*     */           
/* 750 */           if (j != k) {
/* 751 */             ChunkMapDistance.this.i.a(new ChunkCoordIntPair(i), () -> this.f.get(i), k, l -> {
/*     */                   if (l >= this.f.defaultReturnValue()) {
/*     */                     this.f.remove(i);
/*     */                   } else {
/*     */                     this.f.put(i, l);
/*     */                   } 
/*     */                 });
/*     */ 
/*     */ 
/*     */             
/* 761 */             a(i, k, c(j), c(k));
/*     */           } 
/*     */         } 
/*     */         
/* 765 */         this.g.clear();
/*     */       } 
/*     */     }
/*     */     
/*     */     private boolean isLoadedChunkLevel(int i) {
/* 770 */       return c(i);
/*     */     } private boolean c(int i) {
/* 772 */       return (i <= this.e - 2);
/*     */     }
/*     */   }
/*     */   
/*     */   class b
/*     */     extends ChunkMap {
/* 778 */     protected final Long2ByteMap a = (Long2ByteMap)new Long2ByteOpenHashMap();
/*     */     protected final int b;
/*     */     
/*     */     protected b(int i) {
/* 782 */       super(i + 2, 16, 256);
/* 783 */       this.b = i;
/* 784 */       this.a.defaultReturnValue((byte)(i + 2));
/*     */     }
/*     */     protected final int getChunkLevel(long i) {
/* 787 */       return c(i);
/*     */     }
/*     */     protected int c(long i) {
/* 790 */       return this.a.get(i);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void a(long i, int j) {
/*     */       byte b0;
/* 797 */       if (j > this.b) {
/* 798 */         b0 = this.a.remove(i);
/*     */       } else {
/* 800 */         b0 = this.a.put(i, (byte)j);
/*     */       } 
/*     */       
/* 803 */       a(i, b0, j);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(long i, int j, int k) {}
/*     */     
/*     */     protected int b(long i) {
/* 810 */       return d(i) ? 0 : Integer.MAX_VALUE;
/*     */     }
/*     */     
/*     */     private boolean d(long i) {
/* 814 */       ObjectSet<EntityPlayer> objectset = (ObjectSet<EntityPlayer>)ChunkMapDistance.this.c.get(i);
/*     */       
/* 816 */       return (objectset != null && !objectset.isEmpty());
/*     */     }
/*     */     
/*     */     public void a() {
/* 820 */       b(2147483647);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkMapDistance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */