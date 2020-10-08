/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.util.misc.PlayerAreaMap;
/*     */ import com.destroystokyo.paper.util.misc.PooledLinkedHashSets;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import com.tuinity.tuinity.util.TickThread;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortSet;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicReferenceArray;
/*     */ import java.util.function.IntConsumer;
/*     */ import java.util.function.IntSupplier;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class PlayerChunk {
/*  18 */   public static final Either<IChunkAccess, Failure> UNLOADED_CHUNK_ACCESS = Either.right(Failure.b);
/*  19 */   public static final CompletableFuture<Either<IChunkAccess, Failure>> UNLOADED_CHUNK_ACCESS_FUTURE = CompletableFuture.completedFuture(UNLOADED_CHUNK_ACCESS);
/*  20 */   public static final Either<Chunk, Failure> UNLOADED_CHUNK = Either.right(Failure.b);
/*  21 */   private static final CompletableFuture<Either<Chunk, Failure>> UNLOADED_CHUNK_FUTURE = CompletableFuture.completedFuture(UNLOADED_CHUNK);
/*  22 */   private static final List<ChunkStatus> CHUNK_STATUSES = ChunkStatus.a(); boolean isUpdateQueued = false; private final AtomicReferenceArray<CompletableFuture<Either<IChunkAccess, Failure>>> statusFutures; private volatile CompletableFuture<Either<Chunk, Failure>> fullChunkFuture; private int fullChunkCreateCount; private volatile boolean isFullChunkReady; private volatile CompletableFuture<Either<Chunk, Failure>> tickingFuture; private volatile boolean isTickingReady;
/*  23 */   private static final State[] CHUNK_STATES = State.values(); private volatile CompletableFuture<Either<Chunk, Failure>> entityTickingFuture;
/*     */   private volatile boolean isEntityTickingReady;
/*     */   private CompletableFuture<IChunkAccess> chunkSave;
/*     */   public int oldTicketLevel;
/*     */   private int ticketLevel;
/*     */   volatile int n;
/*     */   public final ChunkCoordIntPair location;
/*     */   
/*     */   public final int getCurrentPriority() {
/*  32 */     return this.n;
/*     */   }
/*     */   private boolean p; private final ShortSet[] dirtyBlocks; private int r; private int s; private final LightEngine lightEngine; private final c u;
/*     */   public final d players;
/*     */   private boolean hasBeenLoaded;
/*     */   private boolean x;
/*     */   private final PlayerChunkMap chunkMap;
/*     */   long lastAutoSaveTime;
/*     */   long inactiveTimeStart;
/*     */   PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> playersInMobSpawnRange;
/*     */   PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> playersInChunkTickRange;
/*     */   
/*     */   public WorldServer getWorld() {
/*  45 */     return this.chunkMap.world;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateRanges() {
/*  56 */     long key = MCUtil.getCoordinateKey(this.location);
/*  57 */     this.playersInMobSpawnRange = this.chunkMap.playerMobSpawnMap.getObjectsInRange(key);
/*  58 */     this.playersInChunkTickRange = this.chunkMap.playerChunkTickRangeMap.getObjectsInRange(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAdvanceStatus() {
/*  63 */     ChunkStatus status = getChunkHolderStatus();
/*  64 */     IChunkAccess chunk = getAvailableChunkNow();
/*  65 */     return (chunk != null && (status == null || chunk.getChunkStatus().isAtLeastStatus(getNextStatus(status))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Chunk getSendingChunk() {
/*  73 */     Chunk ret = this.chunkMap.world.getChunkProvider().getChunkAtIfLoadedImmediately(this.location.x, this.location.z);
/*  74 */     if (ret != null && ret.areNeighboursLoaded(1)) {
/*  75 */       return ret;
/*     */     }
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */   
/*  81 */   volatile int neighborPriority = -1;
/*  82 */   volatile int priorityBoost = 0;
/*  83 */   public final ConcurrentHashMap<PlayerChunk, ChunkStatus> neighbors = new ConcurrentHashMap<>();
/*  84 */   public final Long2ObjectOpenHashMap<Integer> neighborPriorities = new Long2ObjectOpenHashMap();
/*     */   
/*     */   private int getDemandedPriority() {
/*  87 */     int priority = this.neighborPriority;
/*  88 */     int myPriority = getMyPriority();
/*     */     
/*  90 */     if (priority == -1 || (this.ticketLevel <= 33 && priority > myPriority)) {
/*  91 */       priority = myPriority;
/*     */     }
/*     */     
/*  94 */     return Math.max(1, Math.min(Math.max(this.ticketLevel, PlayerChunkMap.GOLDEN_TICKET), priority));
/*     */   }
/*     */   
/*     */   private int getMyPriority() {
/*  98 */     if (this.priorityBoost == 29) {
/*  99 */       return 2;
/*     */     }
/* 101 */     return this.ticketLevel - this.priorityBoost;
/*     */   }
/*     */   
/*     */   private int getNeighborsPriority() {
/* 105 */     return this.neighborPriorities.isEmpty() ? getMyPriority() : getDemandedPriority();
/*     */   }
/*     */   
/*     */   public void onNeighborRequest(PlayerChunk neighbor, ChunkStatus status) {
/* 109 */     neighbor.setNeighborPriority(this, getNeighborsPriority());
/* 110 */     this.neighbors.compute(neighbor, (playerChunk, currentWantedStatus) -> 
/* 111 */         (currentWantedStatus == null || !currentWantedStatus.isAtLeastStatus(status)) ? status : currentWantedStatus);
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
/*     */   public void onNeighborDone(PlayerChunk neighbor, ChunkStatus chunkstatus, IChunkAccess chunk) {
/* 123 */     this.neighbors.compute(neighbor, (playerChunk, wantedStatus) -> {
/*     */           if (wantedStatus != null && chunkstatus.isAtLeastStatus(wantedStatus)) {
/*     */             neighbor.removeNeighborPriority(this);
/*     */             return null;
/*     */           } 
/*     */           return wantedStatus;
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeNeighborPriority(PlayerChunk requester) {
/* 136 */     synchronized (this.neighborPriorities) {
/* 137 */       this.neighborPriorities.remove(requester.location.pair());
/* 138 */       recalcNeighborPriority();
/*     */     } 
/* 140 */     checkPriority();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setNeighborPriority(PlayerChunk requester, int priority) {
/* 145 */     synchronized (this.neighborPriorities) {
/* 146 */       this.neighborPriorities.put(requester.location.pair(), Integer.valueOf(priority));
/* 147 */       recalcNeighborPriority();
/*     */     } 
/* 149 */     checkPriority();
/*     */   }
/*     */   
/*     */   private void recalcNeighborPriority() {
/* 153 */     this.neighborPriority = -1;
/* 154 */     if (!this.neighborPriorities.isEmpty())
/* 155 */       synchronized (this.neighborPriorities) {
/* 156 */         for (ObjectIterator<Integer> objectIterator = this.neighborPriorities.values().iterator(); objectIterator.hasNext(); ) { Integer neighbor = objectIterator.next();
/* 157 */           if (neighbor.intValue() < this.neighborPriority || this.neighborPriority == -1) {
/* 158 */             this.neighborPriority = neighbor.intValue();
/*     */           } }
/*     */       
/*     */       }  
/*     */   }
/*     */   
/*     */   private void checkPriority() {
/* 165 */     if (getCurrentPriority() != getDemandedPriority()) this.chunkMap.queueHolderUpdate(this); 
/*     */   }
/*     */   
/*     */   public final double getDistance(EntityPlayer player) {
/* 169 */     return getDistance(player.locX(), player.locZ());
/*     */   }
/*     */   public final double getDistance(double blockX, double blockZ) {
/* 172 */     int cx = MCUtil.fastFloor(blockX) >> 4;
/* 173 */     int cz = MCUtil.fastFloor(blockZ) >> 4;
/* 174 */     double x = (this.location.x - cx);
/* 175 */     double z = (this.location.z - cz);
/* 176 */     return x * x + z * z;
/*     */   }
/*     */   
/*     */   public final double getDistanceFrom(BlockPosition pos) {
/* 180 */     return getDistance(pos.getX(), pos.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 185 */     return "PlayerChunk{location=" + this.location + ", ticketLevel=" + this.ticketLevel + "/" + 
/*     */       
/* 187 */       getChunkStatus(this.ticketLevel) + ", chunkHolderStatus=" + 
/* 188 */       getChunkHolderStatus() + ", neighborPriority=" + 
/* 189 */       getNeighborsPriority() + ", priority=(" + this.ticketLevel + " - " + this.priorityBoost + " vs N " + this.neighborPriority + ") = " + 
/* 190 */       getDemandedPriority() + " A " + getCurrentPriority() + '}';
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PlayerChunk(ChunkCoordIntPair chunkcoordintpair, int i, LightEngine lightengine, c playerchunk_c, d playerchunk_d) {
/* 196 */     this.statusFutures = new AtomicReferenceArray<>(CHUNK_STATUSES.size());
/* 197 */     this.fullChunkFuture = UNLOADED_CHUNK_FUTURE;
/* 198 */     this.tickingFuture = UNLOADED_CHUNK_FUTURE;
/* 199 */     this.entityTickingFuture = UNLOADED_CHUNK_FUTURE;
/* 200 */     this.chunkSave = CompletableFuture.completedFuture(null);
/* 201 */     this.dirtyBlocks = new ShortSet[16];
/* 202 */     this.location = chunkcoordintpair;
/* 203 */     this.lightEngine = lightengine;
/* 204 */     this.u = playerchunk_c;
/* 205 */     this.players = playerchunk_d;
/* 206 */     this.oldTicketLevel = PlayerChunkMap.GOLDEN_TICKET + 1;
/* 207 */     this.ticketLevel = this.oldTicketLevel;
/* 208 */     this.n = this.oldTicketLevel;
/* 209 */     a(i);
/* 210 */     this.chunkMap = (PlayerChunkMap)playerchunk_d;
/* 211 */     updateRanges();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public final Chunk getEntityTickingChunk() {
/* 217 */     CompletableFuture<Either<Chunk, Failure>> completablefuture = this.entityTickingFuture;
/* 218 */     Either<Chunk, Failure> either = completablefuture.getNow(null);
/*     */     
/* 220 */     return (either == null) ? null : either.left().orElse(null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final Chunk getTickingChunk() {
/* 225 */     CompletableFuture<Either<Chunk, Failure>> completablefuture = this.tickingFuture;
/* 226 */     Either<Chunk, Failure> either = completablefuture.getNow(null);
/*     */     
/* 228 */     return (either == null) ? null : either.left().orElse(null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final Chunk getFullReadyChunk() {
/* 233 */     CompletableFuture<Either<Chunk, Failure>> completablefuture = this.fullChunkFuture;
/* 234 */     Either<Chunk, Failure> either = completablefuture.getNow(null);
/*     */     
/* 236 */     return (either == null) ? null : either.left().orElse(null);
/*     */   }
/*     */   
/*     */   public final boolean isEntityTickingReady() {
/* 240 */     return this.isEntityTickingReady;
/*     */   }
/*     */   
/*     */   public final boolean isTickingReady() {
/* 244 */     return this.isTickingReady;
/*     */   }
/*     */   
/*     */   public final boolean isFullChunkReady() {
/* 248 */     return this.isFullChunkReady;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Chunk getFullChunk() {
/* 254 */     if (!getChunkState(this.oldTicketLevel).isAtLeast(State.BORDER)) return null; 
/* 255 */     CompletableFuture<Either<IChunkAccess, Failure>> statusFuture = getStatusFutureUnchecked(ChunkStatus.FULL);
/* 256 */     Either<IChunkAccess, Failure> either = statusFuture.getNow(null);
/* 257 */     return (either == null) ? null : either.left().orElse(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Chunk getFullChunkIfCached() {
/* 263 */     CompletableFuture<Either<IChunkAccess, Failure>> statusFuture = getStatusFutureUnchecked(ChunkStatus.FULL);
/* 264 */     Either<IChunkAccess, Failure> either = statusFuture.getNow(null);
/* 265 */     return (either == null) ? null : either.left().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public IChunkAccess getAvailableChunkNow() {
/* 270 */     for (ChunkStatus curr = ChunkStatus.FULL, next = curr.getPreviousStatus(); curr != next; ) {
/* 271 */       CompletableFuture<Either<IChunkAccess, Failure>> future = getStatusFutureUnchecked(curr);
/* 272 */       Either<IChunkAccess, Failure> either = future.getNow(null);
/* 273 */       if (either == null || !either.left().isPresent()) {
/*     */         curr = next; next = next.getPreviousStatus(); continue;
/*     */       } 
/* 276 */       return either.left().get();
/*     */     } 
/* 278 */     return null;
/*     */   }
/*     */   
/*     */   public ChunkStatus getChunkHolderStatus() {
/* 282 */     for (ChunkStatus curr = ChunkStatus.FULL, next = curr.getPreviousStatus(); curr != next; ) {
/* 283 */       CompletableFuture<Either<IChunkAccess, Failure>> future = getStatusFutureUnchecked(curr);
/* 284 */       Either<IChunkAccess, Failure> either = future.getNow(null);
/* 285 */       if (either == null || !either.left().isPresent()) {
/*     */         curr = next; next = next.getPreviousStatus(); continue;
/*     */       } 
/* 288 */       return curr;
/*     */     } 
/* 290 */     return null;
/*     */   }
/*     */   public static ChunkStatus getNextStatus(ChunkStatus status) {
/* 293 */     if (status == ChunkStatus.FULL) {
/* 294 */       return status;
/*     */     }
/* 296 */     return CHUNK_STATUSES.get(status.getStatusIndex() + 1);
/*     */   }
/*     */   public CompletableFuture<Either<IChunkAccess, Failure>> getStatusFutureUncheckedMain(ChunkStatus chunkstatus) {
/* 299 */     return ensureMain(getStatusFutureUnchecked(chunkstatus));
/*     */   }
/*     */   public <T> CompletableFuture<T> ensureMain(CompletableFuture<T> future) {
/* 302 */     return future.thenApplyAsync(r -> r, this.chunkMap.mainInvokingExecutor);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompletableFuture<Either<IChunkAccess, Failure>> getStatusFutureUnchecked(ChunkStatus chunkstatus) {
/* 307 */     CompletableFuture<Either<IChunkAccess, Failure>> completablefuture = this.statusFutures.get(chunkstatus.c());
/*     */     
/* 309 */     return (completablefuture == null) ? UNLOADED_CHUNK_ACCESS_FUTURE : completablefuture;
/*     */   }
/*     */   
/*     */   public CompletableFuture<Either<IChunkAccess, Failure>> b(ChunkStatus chunkstatus) {
/* 313 */     return getChunkStatus(this.ticketLevel).b(chunkstatus) ? getStatusFutureUnchecked(chunkstatus) : UNLOADED_CHUNK_ACCESS_FUTURE;
/*     */   }
/*     */   public final CompletableFuture<Either<Chunk, Failure>> getTickingFuture() {
/* 316 */     return a();
/*     */   } public final CompletableFuture<Either<Chunk, Failure>> a() {
/* 318 */     return this.tickingFuture;
/*     */   }
/*     */   public final CompletableFuture<Either<Chunk, Failure>> getEntityTickingFuture() {
/* 321 */     return b();
/*     */   } public final CompletableFuture<Either<Chunk, Failure>> b() {
/* 323 */     return this.entityTickingFuture;
/*     */   }
/*     */   public final CompletableFuture<Either<Chunk, Failure>> getFullChunkFuture() {
/* 326 */     return c();
/*     */   } public final CompletableFuture<Either<Chunk, Failure>> c() {
/* 328 */     return this.fullChunkFuture;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final Chunk getChunk() {
/* 333 */     CompletableFuture<Either<Chunk, Failure>> completablefuture = a();
/* 334 */     Either<Chunk, Failure> either = completablefuture.getNow(null);
/*     */     
/* 336 */     return (either == null) ? null : either.left().orElse(null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public IChunkAccess f() {
/* 341 */     for (int i = CHUNK_STATUSES.size() - 1; i >= 0; i--) {
/* 342 */       ChunkStatus chunkstatus = CHUNK_STATUSES.get(i);
/* 343 */       CompletableFuture<Either<IChunkAccess, Failure>> completablefuture = getStatusFutureUnchecked(chunkstatus);
/*     */       
/* 345 */       if (!completablefuture.isCompletedExceptionally()) {
/* 346 */         Optional<IChunkAccess> optional = ((Either)completablefuture.getNow(UNLOADED_CHUNK_ACCESS)).left();
/*     */         
/* 348 */         if (optional.isPresent()) {
/* 349 */           return optional.get();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 354 */     return null;
/*     */   }
/*     */   
/*     */   public final CompletableFuture<IChunkAccess> getChunkSave() {
/* 358 */     return this.chunkSave;
/*     */   }
/*     */   
/*     */   public void a(BlockPosition blockposition) {
/* 362 */     Chunk chunk = getSendingChunk();
/*     */     
/* 364 */     if (chunk != null && blockposition.getY() >= 0 && blockposition.getY() <= 255) {
/* 365 */       byte b0 = (byte)SectionPosition.a(blockposition.getY());
/*     */       
/* 367 */       if (b0 >= this.dirtyBlocks.length)
/* 368 */         return;  if (this.dirtyBlocks[b0] == null) {
/* 369 */         this.p = true;
/* 370 */         this.dirtyBlocks[b0] = (ShortSet)new ShortArraySet();
/*     */       } 
/*     */       
/* 373 */       this.dirtyBlocks[b0].add(SectionPosition.b(blockposition));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(EnumSkyBlock enumskyblock, int i) {
/* 378 */     Chunk chunk = getSendingChunk();
/*     */     
/* 380 */     if (chunk != null) {
/* 381 */       chunk.setNeedsSaving(true);
/* 382 */       if (enumskyblock == EnumSkyBlock.SKY) {
/* 383 */         this.s |= 1 << i - -1;
/*     */       } else {
/* 385 */         this.r |= 1 << i - -1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Chunk chunk) {
/* 392 */     if (this.p || this.s != 0 || this.r != 0) {
/* 393 */       World world = chunk.getWorld();
/* 394 */       int i = 0;
/*     */       
/*     */       int j;
/*     */       
/* 398 */       for (j = 0; j < this.dirtyBlocks.length; j++) {
/* 399 */         i += (this.dirtyBlocks[j] != null) ? this.dirtyBlocks[j].size() : 0;
/*     */       }
/*     */       
/* 402 */       this.x |= (i >= 64) ? 1 : 0;
/* 403 */       if (this.s != 0 || this.r != 0) {
/* 404 */         a(new PacketPlayOutLightUpdate(chunk.getPos(), this.lightEngine, this.s, this.r, true), !this.x);
/* 405 */         this.s = 0;
/* 406 */         this.r = 0;
/*     */       } 
/*     */       
/* 409 */       for (j = 0; j < this.dirtyBlocks.length; j++) {
/* 410 */         ShortSet shortset = this.dirtyBlocks[j];
/*     */         
/* 412 */         if (shortset != null) {
/* 413 */           SectionPosition sectionposition = SectionPosition.a(chunk.getPos(), j);
/*     */           
/* 415 */           if (shortset.size() == 1) {
/* 416 */             BlockPosition blockposition = sectionposition.g(shortset.iterator().nextShort());
/* 417 */             IBlockData iblockdata = world.getType(blockposition);
/*     */             
/* 419 */             a(new PacketPlayOutBlockChange(blockposition, iblockdata), false);
/* 420 */             a(world, blockposition, iblockdata);
/*     */           } else {
/* 422 */             ChunkSection chunksection = chunk.getSections()[sectionposition.getY()];
/*     */             
/* 424 */             PacketPlayOutMultiBlockChange packetplayoutmultiblockchange = new PacketPlayOutMultiBlockChange(sectionposition, shortset, chunksection, this.x);
/*     */             
/* 426 */             a(packetplayoutmultiblockchange, false);
/* 427 */             packetplayoutmultiblockchange.a((blockposition1, iblockdata1) -> a(world, blockposition1, iblockdata1));
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 432 */           this.dirtyBlocks[j] = null;
/*     */         } 
/*     */       } 
/*     */       
/* 436 */       this.p = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 441 */     if (iblockdata.getBlock().isTileEntity()) {
/* 442 */       a(world, blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(World world, BlockPosition blockposition) {
/* 448 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 450 */     if (tileentity != null) {
/* 451 */       PacketPlayOutTileEntityData packetplayouttileentitydata = tileentity.getUpdatePacket();
/*     */       
/* 453 */       if (packetplayouttileentitydata != null) {
/* 454 */         a(packetplayouttileentitydata, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sendPacketToTrackedPlayers(Packet<?> packet, boolean flag) {
/* 460 */     a(packet, flag);
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(Packet<?> packet, boolean flag) {
/* 465 */     PlayerAreaMap viewDistanceMap = this.chunkMap.playerViewDistanceBroadcastMap;
/* 466 */     PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> players = viewDistanceMap.getObjectsInRange(this.location);
/* 467 */     if (players == null) {
/*     */       return;
/*     */     }
/*     */     
/* 471 */     if (flag) {
/* 472 */       Object[] backingSet = players.getBackingSet();
/* 473 */       for (int i = 0, len = backingSet.length; i < len; i++) {
/* 474 */         Object temp = backingSet[i];
/* 475 */         if (temp instanceof EntityPlayer) {
/*     */ 
/*     */           
/* 478 */           EntityPlayer player = (EntityPlayer)temp;
/*     */           
/* 480 */           int viewDistance = viewDistanceMap.getLastViewDistance(player);
/* 481 */           long lastPosition = viewDistanceMap.getLastCoordinate(player);
/*     */           
/* 483 */           int distX = Math.abs(MCUtil.getCoordinateX(lastPosition) - this.location.x);
/* 484 */           int distZ = Math.abs(MCUtil.getCoordinateZ(lastPosition) - this.location.z);
/*     */           
/* 486 */           if (Math.max(distX, distZ) == viewDistance)
/* 487 */             player.playerConnection.sendPacket(packet); 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 491 */       Object[] backingSet = players.getBackingSet();
/* 492 */       for (int i = 0, len = backingSet.length; i < len; i++) {
/* 493 */         Object temp = backingSet[i];
/* 494 */         if (temp instanceof EntityPlayer) {
/*     */ 
/*     */           
/* 497 */           EntityPlayer player = (EntityPlayer)temp;
/* 498 */           player.playerConnection.sendPacket(packet);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final CompletableFuture<Either<IChunkAccess, Failure>> getOrCreateFuture(ChunkStatus chunkstatus, PlayerChunkMap playerchunkmap) {
/* 506 */     return a(chunkstatus, playerchunkmap);
/*     */   } public CompletableFuture<Either<IChunkAccess, Failure>> a(ChunkStatus chunkstatus, PlayerChunkMap playerchunkmap) {
/* 508 */     int i = chunkstatus.c();
/* 509 */     CompletableFuture<Either<IChunkAccess, Failure>> completablefuture = this.statusFutures.get(i);
/*     */     
/* 511 */     if (completablefuture != null) {
/* 512 */       Either<IChunkAccess, Failure> either = completablefuture.getNow(null);
/*     */       
/* 514 */       if (either == null || either.left().isPresent()) {
/* 515 */         return completablefuture;
/*     */       }
/*     */     } 
/*     */     
/* 519 */     if (getChunkStatus(this.ticketLevel).b(chunkstatus)) {
/* 520 */       CompletableFuture<Either<IChunkAccess, Failure>> completablefuture1 = playerchunkmap.a(this, chunkstatus);
/*     */       
/* 522 */       a(completablefuture1);
/* 523 */       this.statusFutures.set(i, completablefuture1);
/* 524 */       return completablefuture1;
/*     */     } 
/* 526 */     return (completablefuture == null) ? UNLOADED_CHUNK_ACCESS_FUTURE : completablefuture;
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(CompletableFuture<? extends Either<? extends IChunkAccess, Failure>> completablefuture) {
/* 531 */     this.chunkSave = this.chunkSave.thenCombine(completablefuture, (ichunkaccess, either) -> (IChunkAccess)either.map((), ()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ChunkCoordIntPair i() {
/* 541 */     return this.location;
/*     */   }
/*     */   
/*     */   public final int getTicketLevel() {
/* 545 */     return this.ticketLevel;
/*     */   }
/*     */   
/*     */   public int k() {
/* 549 */     return this.n;
/*     */   }
/*     */   private void setPriority(int i) {
/* 552 */     d(i);
/*     */   } private void d(int i) {
/* 554 */     this.n = i;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 558 */     this.ticketLevel = i;
/*     */   }
/*     */   
/*     */   protected void a(PlayerChunkMap playerchunkmap) {
/* 562 */     TickThread.ensureTickThread("Async ticket level update");
/* 563 */     ChunkStatus chunkstatus = getChunkStatus(this.oldTicketLevel);
/* 564 */     ChunkStatus chunkstatus1 = getChunkStatus(this.ticketLevel);
/* 565 */     boolean flag = (this.oldTicketLevel <= PlayerChunkMap.GOLDEN_TICKET);
/* 566 */     boolean flag1 = (this.ticketLevel <= PlayerChunkMap.GOLDEN_TICKET);
/* 567 */     State playerchunk_state = getChunkState(this.oldTicketLevel);
/* 568 */     State playerchunk_state1 = getChunkState(this.ticketLevel);
/*     */ 
/*     */     
/* 571 */     if (playerchunk_state.isAtLeast(State.BORDER) && !playerchunk_state1.isAtLeast(State.BORDER)) {
/* 572 */       getStatusFutureUnchecked(ChunkStatus.FULL).thenAccept(either -> {
/*     */             TickThread.ensureTickThread("Async full status chunk future completion");
/*     */ 
/*     */ 
/*     */             
/*     */             Chunk chunk = either.left().orElse(null);
/*     */ 
/*     */ 
/*     */             
/*     */             if (chunk != null) {
/*     */               playerchunkmap.callbackExecutor.execute(());
/*     */             }
/* 584 */           }).exceptionally(throwable -> {
/*     */             MinecraftServer.LOGGER.fatal("Failed to schedule unload callback for chunk " + this.location, throwable);
/*     */ 
/*     */             
/*     */             return null;
/*     */           });
/*     */       
/* 591 */       playerchunkmap.callbackExecutor.run();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 596 */     if (flag) {
/* 597 */       Either<IChunkAccess, Failure> either = Either.right(new Failure() {
/*     */             public String toString() {
/* 599 */               return "Unloaded ticket level " + PlayerChunk.this.location.toString();
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 604 */       if (!flag1) {
/* 605 */         playerchunkmap.world.asyncChunkTaskManager.cancelChunkLoad(this.location.x, this.location.z);
/*     */       }
/*     */ 
/*     */       
/* 609 */       for (int i = flag1 ? (chunkstatus1.c() + 1) : 0; i <= chunkstatus.c(); i++) {
/* 610 */         CompletableFuture<Either<IChunkAccess, Failure>> completablefuture = this.statusFutures.get(i);
/* 611 */         if (completablefuture != null) {
/* 612 */           completablefuture.complete(either);
/*     */         } else {
/* 614 */           this.statusFutures.set(i, CompletableFuture.completedFuture(either));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 619 */     boolean flag2 = playerchunk_state.isAtLeast(State.BORDER);
/* 620 */     boolean flag3 = playerchunk_state1.isAtLeast(State.BORDER);
/*     */     
/* 622 */     boolean prevHasBeenLoaded = this.hasBeenLoaded;
/* 623 */     this.hasBeenLoaded |= flag3;
/*     */     
/* 625 */     if ((this.hasBeenLoaded & (!prevHasBeenLoaded ? 1 : 0)) != 0) {
/* 626 */       long timeSinceAutoSave = this.inactiveTimeStart - this.lastAutoSaveTime;
/* 627 */       if (timeSinceAutoSave < 0L)
/*     */       {
/* 629 */         timeSinceAutoSave = this.chunkMap.world.paperConfig.autoSavePeriod;
/*     */       }
/* 631 */       this.lastAutoSaveTime = this.chunkMap.world.getTime() - timeSinceAutoSave;
/* 632 */       this.chunkMap.autoSaveQueue.add(this);
/*     */     } 
/*     */     
/* 635 */     if (!flag2 && flag3) {
/*     */       
/* 637 */       int expectCreateCount = ++this.fullChunkCreateCount;
/* 638 */       this.fullChunkFuture = playerchunkmap.b(this); this.fullChunkFuture.thenAccept(either -> {
/*     */             TickThread.ensureTickThread("Async full chunk future completion");
/*     */             
/*     */             if (either.left().isPresent() && this.fullChunkCreateCount == expectCreateCount) {
/*     */               Chunk fullChunk = either.left().get();
/*     */               
/*     */               this.isFullChunkReady = true;
/*     */               
/*     */               fullChunk.playerChunk = this;
/*     */               
/*     */               this.chunkMap.chunkDistanceManager.clearPriorityTickets(this.location);
/*     */             } 
/*     */           });
/* 651 */       a((CompletableFuture)this.fullChunkFuture);
/*     */     } 
/*     */     
/* 654 */     if (flag2 && !flag3) {
/* 655 */       CompletableFuture<Either<Chunk, Failure>> completablefuture = this.fullChunkFuture;
/* 656 */       this.fullChunkFuture = UNLOADED_CHUNK_FUTURE;
/* 657 */       this.fullChunkCreateCount++;
/* 658 */       this.isFullChunkReady = false;
/* 659 */       a(completablefuture.thenApply(either1 -> {
/*     */               playerchunkmap.getClass();
/*     */               Objects.requireNonNull(playerchunkmap);
/*     */               return either1.ifLeft(playerchunkmap::a);
/*     */             }));
/*     */     } 
/* 665 */     boolean flag4 = playerchunk_state.isAtLeast(State.TICKING);
/* 666 */     boolean flag5 = playerchunk_state1.isAtLeast(State.TICKING);
/*     */     
/* 668 */     if (!flag4 && flag5) {
/*     */       
/* 670 */       this.tickingFuture = playerchunkmap.a(this); this.tickingFuture.thenAccept(either -> {
/*     */             TickThread.ensureTickThread("Async ticking chunk future completion");
/*     */ 
/*     */             
/*     */             if (either.left().isPresent()) {
/*     */               Chunk tickingChunk = either.left().get();
/*     */ 
/*     */               
/*     */               this.isTickingReady = true;
/*     */ 
/*     */               
/*     */               this.chunkMap.world.onChunkSetTicking(this.location.x, this.location.z);
/*     */ 
/*     */               
/*     */               (this.chunkMap.world.getChunkProvider()).tickingChunks.add(tickingChunk);
/*     */             } 
/*     */           });
/*     */       
/* 688 */       a((CompletableFuture)this.tickingFuture);
/*     */     } 
/*     */     
/* 691 */     if (flag4 && !flag5) {
/* 692 */       this.tickingFuture.complete(UNLOADED_CHUNK); this.isTickingReady = false;
/* 693 */       this.tickingFuture = UNLOADED_CHUNK_FUTURE;
/*     */       
/* 695 */       Chunk chunkIfCached = getFullChunkIfCached();
/* 696 */       if (chunkIfCached != null) {
/* 697 */         (this.chunkMap.world.getChunkProvider()).tickingChunks.remove(chunkIfCached);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 702 */     boolean flag6 = playerchunk_state.isAtLeast(State.ENTITY_TICKING);
/* 703 */     boolean flag7 = playerchunk_state1.isAtLeast(State.ENTITY_TICKING);
/*     */     
/* 705 */     if (!flag6 && flag7) {
/* 706 */       if (this.entityTickingFuture != UNLOADED_CHUNK_FUTURE) {
/* 707 */         throw (IllegalStateException)SystemUtils.c(new IllegalStateException());
/*     */       }
/*     */ 
/*     */       
/* 711 */       this.entityTickingFuture = playerchunkmap.b(this.location); this.entityTickingFuture.thenAccept(either -> {
/*     */             TickThread.ensureTickThread("Async entity ticking chunk future completion");
/*     */ 
/*     */             
/*     */             if (either.left().isPresent()) {
/*     */               Chunk entityTickingChunk = either.left().get();
/*     */ 
/*     */               
/*     */               this.isEntityTickingReady = true;
/*     */ 
/*     */               
/*     */               (this.chunkMap.world.getChunkProvider()).entityTickingChunks.add(entityTickingChunk);
/*     */             } 
/*     */           });
/*     */       
/* 726 */       a((CompletableFuture)this.entityTickingFuture);
/*     */     } 
/*     */     
/* 729 */     if (flag6 && !flag7) {
/* 730 */       this.entityTickingFuture.complete(UNLOADED_CHUNK); this.isEntityTickingReady = false;
/* 731 */       this.entityTickingFuture = UNLOADED_CHUNK_FUTURE;
/*     */       
/* 733 */       Chunk chunkIfCached = getFullChunkIfCached();
/* 734 */       if (chunkIfCached != null) {
/* 735 */         (this.chunkMap.world.getChunkProvider()).entityTickingChunks.remove(chunkIfCached);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 741 */     this.priorityBoost = this.chunkMap.chunkDistanceManager.getChunkPriority(this.location);
/* 742 */     int priority = getDemandedPriority();
/* 743 */     if (getCurrentPriority() > priority) {
/* 744 */       int ioPriority = 3;
/* 745 */       if (priority <= 10) {
/* 746 */         ioPriority = 0;
/* 747 */       } else if (priority <= 20) {
/* 748 */         ioPriority = 2;
/*     */       } 
/* 750 */       this.chunkMap.world.asyncChunkTaskManager.raisePriority(this.location.x, this.location.z, ioPriority);
/* 751 */       (this.chunkMap.world.getChunkProvider().getLightEngine()).queue.changePriority(this.location.pair(), getCurrentPriority(), priority);
/*     */     } 
/* 753 */     if (getCurrentPriority() != priority) {
/* 754 */       this.u.a(this.location, this::getCurrentPriority, priority, this::setPriority);
/* 755 */       int neighborsPriority = getNeighborsPriority();
/* 756 */       this.neighbors.forEach((neighbor, neighborDesired) -> neighbor.setNeighborPriority(this, neighborsPriority));
/*     */     } 
/*     */     
/* 759 */     this.oldTicketLevel = this.ticketLevel;
/*     */ 
/*     */     
/* 762 */     if (!playerchunk_state.isAtLeast(State.BORDER) && playerchunk_state1.isAtLeast(State.BORDER)) {
/* 763 */       getStatusFutureUnchecked(ChunkStatus.FULL).thenAccept(either -> {
/*     */             TickThread.ensureTickThread("Async full status chunk future completion");
/*     */             
/*     */             Chunk chunk = either.left().orElse(null);
/*     */             
/*     */             if (chunk != null) {
/*     */               playerchunkmap.callbackExecutor.execute(());
/*     */             }
/* 771 */           }).exceptionally(throwable -> {
/*     */             MinecraftServer.LOGGER.fatal("Failed to schedule load callback for chunk " + this.location, throwable);
/*     */ 
/*     */             
/*     */             return null;
/*     */           });
/*     */       
/* 778 */       playerchunkmap.callbackExecutor.run();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ChunkStatus getChunkStatus(int i) {
/* 784 */     return (i < 33) ? ChunkStatus.FULL : ChunkStatus.a(i - 33);
/*     */   }
/*     */   
/*     */   public static State getChunkState(int i) {
/* 788 */     return CHUNK_STATES[MathHelper.clamp(33 - i + 1, 0, CHUNK_STATES.length - 1)];
/*     */   }
/*     */   
/*     */   public boolean hasBeenLoaded() {
/* 792 */     return this.hasBeenLoaded;
/*     */   }
/*     */   
/*     */   public void m() {
/* 796 */     boolean prev = this.hasBeenLoaded;
/* 797 */     this.hasBeenLoaded = getChunkState(this.ticketLevel).isAtLeast(State.BORDER);
/*     */     
/* 799 */     if (prev != this.hasBeenLoaded) {
/* 800 */       if (this.hasBeenLoaded) {
/* 801 */         long timeSinceAutoSave = this.inactiveTimeStart - this.lastAutoSaveTime;
/* 802 */         if (timeSinceAutoSave < 0L)
/*     */         {
/* 804 */           timeSinceAutoSave = this.chunkMap.world.paperConfig.autoSavePeriod;
/*     */         }
/* 806 */         this.lastAutoSaveTime = this.chunkMap.world.getTime() - timeSinceAutoSave;
/* 807 */         this.chunkMap.autoSaveQueue.add(this);
/*     */       } else {
/* 809 */         this.inactiveTimeStart = this.chunkMap.world.getTime();
/* 810 */         this.chunkMap.autoSaveQueue.remove(this);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setHasBeenLoaded() {
/* 818 */     this.hasBeenLoaded = getChunkState(this.ticketLevel).isAtLeast(State.BORDER);
/* 819 */     return this.hasBeenLoaded;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(ProtoChunkExtension protochunkextension) {
/* 824 */     for (int i = 0; i < this.statusFutures.length(); i++) {
/* 825 */       CompletableFuture<Either<IChunkAccess, Failure>> completablefuture = this.statusFutures.get(i);
/*     */       
/* 827 */       if (completablefuture != null) {
/* 828 */         Optional<IChunkAccess> optional = ((Either)completablefuture.getNow(UNLOADED_CHUNK_ACCESS)).left();
/*     */         
/* 830 */         if (optional.isPresent() && optional.get() instanceof ProtoChunk) {
/* 831 */           this.statusFutures.set(i, CompletableFuture.completedFuture(Either.left(protochunkextension)));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 836 */     a(CompletableFuture.completedFuture(Either.left(protochunkextension.u())));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface c
/*     */   {
/*     */     default void changePriority(ChunkCoordIntPair chunkcoordintpair, IntSupplier intsupplier, int i, IntConsumer intconsumer) {
/* 846 */       a(chunkcoordintpair, intsupplier, i, intconsumer);
/*     */     }
/*     */     
/*     */     void a(ChunkCoordIntPair param1ChunkCoordIntPair, IntSupplier param1IntSupplier, int param1Int, IntConsumer param1IntConsumer); }
/*     */   
/*     */   public static interface Failure {
/* 852 */     public static final Failure b = new Failure() {
/*     */         public String toString() {
/* 854 */           return "UNLOADED";
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public enum State
/*     */   {
/* 861 */     INACCESSIBLE, BORDER, TICKING, ENTITY_TICKING;
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isAtLeast(State playerchunk_state) {
/* 866 */       return (ordinal() >= playerchunk_state.ordinal());
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface d {
/*     */     Stream<EntityPlayer> a(ChunkCoordIntPair param1ChunkCoordIntPair, boolean param1Boolean);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PlayerChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */