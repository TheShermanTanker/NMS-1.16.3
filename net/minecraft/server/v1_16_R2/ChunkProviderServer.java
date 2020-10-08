/*      */ package net.minecraft.server.v1_16_R2;
/*      */ 
/*      */ import co.aikar.timings.MinecraftTimings;
/*      */ import co.aikar.timings.Timing;
/*      */ import com.destroystokyo.paper.PaperConfig;
/*      */ import com.destroystokyo.paper.event.entity.PlayerNaturallySpawnCreaturesEvent;
/*      */ import com.destroystokyo.paper.io.SyncLoadFinder;
/*      */ import com.destroystokyo.paper.io.chunk.ChunkTaskManager;
/*      */ import com.destroystokyo.paper.util.concurrent.WeakSeqLock;
/*      */ import com.google.common.annotations.VisibleForTesting;
/*      */ import com.mojang.datafixers.DataFixer;
/*      */ import com.mojang.datafixers.util.Either;
/*      */ import com.tuinity.tuinity.util.TickThread;
/*      */ import com.tuinity.tuinity.util.maplist.IteratorSafeOrderedReferenceSet;
/*      */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.concurrent.CompletableFuture;
/*      */ import java.util.concurrent.CompletionStage;
/*      */ import java.util.concurrent.Executor;
/*      */ import java.util.function.BooleanSupplier;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.Function;
/*      */ import java.util.function.Supplier;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.entity.Player;
/*      */ 
/*      */ public class ChunkProviderServer extends IChunkProvider {
/*   34 */   private static final List<ChunkStatus> b = ChunkStatus.a(); private final ChunkMapDistance chunkMapDistance; public final ChunkGenerator chunkGenerator; private final WorldServer world; public final Thread serverThread; private final LightEngineThreaded lightEngine; public final a serverThreadQueue; public final PlayerChunkMap playerChunkMap; private final WorldPersistentData worldPersistentData; private long lastTickTime; static final List<ChunkStatus> getPossibleChunkStatuses() { return b; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean allowMonsters = true;
/*      */ 
/*      */   
/*      */   public boolean allowAnimals = true;
/*      */ 
/*      */   
/*   46 */   private final long[] cachePos = new long[4];
/*   47 */   private final ChunkStatus[] cacheStatus = new ChunkStatus[4];
/*   48 */   private final IChunkAccess[] cacheChunk = new IChunkAccess[4];
/*      */   
/*      */   @Nullable
/*      */   private SpawnerCreature.d p;
/*   52 */   final WeakSeqLock loadedChunkMapSeqLock = new WeakSeqLock();
/*   53 */   final Long2ObjectOpenHashMap<Chunk> loadedChunkMap = new Long2ObjectOpenHashMap(8192, 0.5F);
/*      */   
/*   55 */   private final Chunk[] lastLoadedChunks = new Chunk[16];
/*   56 */   private final long[] lastLoadedChunkKeys = new long[16]; long chunkFutureAwaitCounter; final IteratorSafeOrderedReferenceSet<Chunk> tickingChunks; final IteratorSafeOrderedReferenceSet<Chunk> entityTickingChunks; private long asyncLoadSeqCounter;
/*      */   private long syncLoadCounter;
/*      */   
/*   59 */   public ChunkProviderServer(WorldServer worldserver, Convertable.ConversionSession convertable_conversionsession, DataFixer datafixer, DefinedStructureManager definedstructuremanager, Executor executor, ChunkGenerator chunkgenerator, int i, boolean flag, WorldLoadListener worldloadlistener, Supplier<WorldPersistentData> supplier) { Arrays.fill(this.lastLoadedChunkKeys, MCUtil.INVALID_CHUNK_KEY);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  377 */     this.tickingChunks = new IteratorSafeOrderedReferenceSet(4096, 0.75F, 4096, 0.15D, true);
/*  378 */     this.entityTickingChunks = new IteratorSafeOrderedReferenceSet(4096, 0.75F, 4096, 0.15D, true);
/*      */ 
/*      */ 
/*      */     
/*  382 */     this.world = worldserver;
/*  383 */     this.serverThreadQueue = new a(worldserver);
/*  384 */     this.chunkGenerator = chunkgenerator;
/*  385 */     this.serverThread = Thread.currentThread();
/*  386 */     File file = convertable_conversionsession.a(worldserver.getDimensionKey());
/*  387 */     File file1 = new File(file, "data");
/*      */     
/*  389 */     file1.mkdirs();
/*  390 */     this.worldPersistentData = new WorldPersistentData(file1, datafixer);
/*  391 */     this.playerChunkMap = new PlayerChunkMap(worldserver, convertable_conversionsession, datafixer, definedstructuremanager, executor, this.serverThreadQueue, this, getChunkGenerator(), worldloadlistener, supplier, i, flag);
/*  392 */     this.lightEngine = this.playerChunkMap.a();
/*  393 */     this.chunkMapDistance = this.playerChunkMap.e();
/*  394 */     clearCache(); } private static int getCacheKey(int x, int z) { return x & 0x3 | (z & 0x3) << 2; } void addLoadedChunk(Chunk chunk) { this.loadedChunkMapSeqLock.acquireWrite(); try { this.loadedChunkMap.put(chunk.coordinateKey, chunk); } finally { this.loadedChunkMapSeqLock.releaseWrite(); }  int cacheKey = getCacheKey((chunk.getPos()).x, (chunk.getPos()).z); long cachedKey = this.lastLoadedChunkKeys[cacheKey]; if (cachedKey == chunk.coordinateKey) this.lastLoadedChunks[cacheKey] = chunk;  } void removeLoadedChunk(Chunk chunk) { this.loadedChunkMapSeqLock.acquireWrite(); try { this.loadedChunkMap.remove(chunk.coordinateKey); } finally { this.loadedChunkMapSeqLock.releaseWrite(); }  int cacheKey = getCacheKey((chunk.getPos()).x, (chunk.getPos()).z); long cachedKey = this.lastLoadedChunkKeys[cacheKey]; if (cachedKey == chunk.coordinateKey) this.lastLoadedChunks[cacheKey] = null;  } public Chunk getChunkAtIfLoadedMainThread(int x, int z) { int cacheKey = getCacheKey(x, z); long chunkKey = MCUtil.getCoordinateKey(x, z); long cachedKey = this.lastLoadedChunkKeys[cacheKey]; if (cachedKey == chunkKey) return this.lastLoadedChunks[cacheKey];  Chunk ret = (Chunk)this.loadedChunkMap.get(chunkKey); this.lastLoadedChunkKeys[cacheKey] = chunkKey; this.lastLoadedChunks[cacheKey] = ret; return ret; }
/*      */   public Chunk getChunkAtIfLoadedMainThreadNoCache(int x, int z) { return (Chunk)this.loadedChunkMap.get(MCUtil.getCoordinateKey(x, z)); }
/*      */   public Chunk getChunkAtMainThread(int x, int z) { Chunk ret = getChunkAtIfLoadedMainThread(x, z); if (ret != null) return ret;  return (Chunk)getChunkAt(x, z, ChunkStatus.FULL, true); }
/*      */   public void getEntityTickingChunkAsync(int x, int z, Consumer<Chunk> onLoad) { if (Thread.currentThread() != this.serverThread) { this.serverThreadQueue.execute(() -> getEntityTickingChunkAsync(x, z, onLoad)); return; }  getChunkFutureAsynchronously(x, z, 31, PlayerChunk::getEntityTickingFuture, onLoad); }
/*      */   public void getTickingChunkAsync(int x, int z, Consumer<Chunk> onLoad) { if (Thread.currentThread() != this.serverThread) { this.serverThreadQueue.execute(() -> getTickingChunkAsync(x, z, onLoad)); return; }  getChunkFutureAsynchronously(x, z, 32, PlayerChunk::getTickingFuture, onLoad); }
/*  399 */   public boolean isChunkLoaded(int chunkX, int chunkZ) { PlayerChunk chunk = this.playerChunkMap.getUpdatingChunk(ChunkCoordIntPair.pair(chunkX, chunkZ));
/*  400 */     if (chunk == null) {
/*  401 */       return false;
/*      */     }
/*  403 */     return (chunk.getFullChunk() != null); }
/*      */   public void getFullChunkAsync(int x, int z, Consumer<Chunk> onLoad) { if (Thread.currentThread() != this.serverThread) { this.serverThreadQueue.execute(() -> getFullChunkAsync(x, z, onLoad)); return; }  getChunkFutureAsynchronously(x, z, 33, PlayerChunk::getFullChunkFuture, onLoad); }
/*      */   private void getChunkFutureAsynchronously(int x, int z, int ticketLevel, Function<PlayerChunk, CompletableFuture<Either<Chunk, PlayerChunk.Failure>>> futureGet, Consumer<Chunk> onLoad) { if (Thread.currentThread() != this.serverThread) throw new IllegalStateException();  ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(x, z); Long identifier = Long.valueOf(this.chunkFutureAwaitCounter++); this.chunkMapDistance.addTicketAtLevel(TicketType.FUTURE_AWAIT, chunkPos, ticketLevel, identifier); tickDistanceManager(); PlayerChunk chunk = this.playerChunkMap.getUpdatingChunk(chunkPos.pair()); if (chunk == null) throw new IllegalStateException("Expected playerchunk " + chunkPos + " in world '" + this.world.getWorld().getName() + "'");  CompletableFuture<Either<Chunk, PlayerChunk.Failure>> future = futureGet.apply(chunk); future.whenCompleteAsync((either, throwable) -> { try { if (throwable != null) { if (throwable instanceof ThreadDeath) throw (ThreadDeath)throwable;  MinecraftServer.LOGGER.fatal("Failed to complete future await for chunk " + chunkPos.toString() + " in world '" + this.world.getWorld().getName() + "'", throwable); } else if (either.right().isPresent()) { MinecraftServer.LOGGER.fatal("Failed to complete future await for chunk " + chunkPos.toString() + " in world '" + this.world.getWorld().getName() + "': " + ((PlayerChunk.Failure)either.right().get()).toString()); }  try { if (onLoad != null) onLoad.accept((either == null) ? null : either.left().orElse(null));  } catch (Throwable thr) { if (thr instanceof ThreadDeath) throw (ThreadDeath)thr;  MinecraftServer.LOGGER.fatal("Load callback for future await failed " + chunkPos.toString() + " in world '" + this.world.getWorld().getName() + "'", thr); return; }  } finally { this.chunkMapDistance.addTicketAtLevel(TicketType.UNKNOWN, chunkPos, ticketLevel, chunkPos); this.chunkMapDistance.removeTicketAtLevel(TicketType.FUTURE_AWAIT, chunkPos, ticketLevel, identifier); }  }this.serverThreadQueue); }
/*      */   public final boolean isTickingReadyMainThread(BlockPosition pos) { PlayerChunk chunk = this.playerChunkMap.getUpdatingChunk(MCUtil.getCoordinateKey(pos)); return (chunk != null && chunk.isTickingReady()); }
/*      */   public final IChunkAccess getFullStatusChunkAt(int chunkX, int chunkZ) { Chunk ifLoaded = getChunkAtIfLoadedImmediately(chunkX, chunkZ); if (ifLoaded != null)
/*      */       return ifLoaded;  IChunkAccess empty = getChunkAt(chunkX, chunkZ, ChunkStatus.EMPTY, true); if (empty != null && empty.getChunkStatus() == ChunkStatus.FULL)
/*  409 */       return empty;  return getChunkAt(chunkX, chunkZ, ChunkStatus.FULL, true); } public LightEngineThreaded getLightEngine() { return this.lightEngine; }
/*      */   public final IChunkAccess getFullStatusChunkAtIfLoaded(int chunkX, int chunkZ) { Chunk ifLoaded = getChunkAtIfLoadedImmediately(chunkX, chunkZ); if (ifLoaded != null) return ifLoaded;  IChunkAccess ret = getChunkAtImmediately(chunkX, chunkZ); if (ret != null && ret.getChunkStatus() == ChunkStatus.FULL) return ret;  return null; }
/*      */   void getChunkAtAsynchronously(int chunkX, int chunkZ, int ticketLevel, Consumer<IChunkAccess> consumer) { getChunkAtAsynchronously(chunkX, chunkZ, ticketLevel, playerChunk -> (ticketLevel <= 33) ? playerChunk.getFullChunkFuture() : playerChunk.getOrCreateFuture(PlayerChunk.getChunkStatus(ticketLevel), this.playerChunkMap), consumer); }
/*      */   void getChunkAtAsynchronously(int chunkX, int chunkZ, int ticketLevel, Function<PlayerChunk, CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>>> function, Consumer<IChunkAccess> consumer) { if (Thread.currentThread() != this.serverThread) throw new IllegalStateException();  ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(chunkX, chunkZ); Long identifier = Long.valueOf(this.chunkFutureAwaitCounter++); addTicketAtLevel(TicketType.FUTURE_AWAIT, chunkPos, ticketLevel, identifier); tickDistanceManager(); PlayerChunk chunk = this.playerChunkMap.getUpdatingChunk(chunkPos.pair()); if (chunk == null) throw new IllegalStateException("Expected playerchunk " + chunkPos + " in world '" + this.world.getWorld().getName() + "'");  CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> future = function.apply(chunk); future.whenCompleteAsync((either, throwable) -> { try { if (throwable != null) { if (throwable instanceof ThreadDeath) throw (ThreadDeath)throwable;  MinecraftServer.LOGGER.fatal("Failed to complete future await for chunk " + chunkPos.toString() + " in world '" + this.world.getWorld().getName() + "'", throwable); } else if (either.right().isPresent()) { MinecraftServer.LOGGER.fatal("Failed to complete future await for chunk " + chunkPos.toString() + " in world '" + this.world.getWorld().getName() + "': " + ((PlayerChunk.Failure)either.right().get()).toString()); }  try { if (consumer != null) consumer.accept((either == null) ? null : either.left().orElse(null));  } catch (Throwable thr) { if (thr instanceof ThreadDeath) throw (ThreadDeath)thr;  MinecraftServer.LOGGER.fatal("Load callback for future await failed " + chunkPos.toString() + " in world '" + this.world.getWorld().getName() + "'", thr); return; }  } finally { addTicketAtLevel(TicketType.UNKNOWN, chunkPos, ticketLevel, chunkPos); removeTicketAtLevel(TicketType.FUTURE_AWAIT, chunkPos, ticketLevel, identifier); }  }this.serverThreadQueue); }
/*      */   void chunkLoadAccept(int chunkX, int chunkZ, IChunkAccess chunk, Consumer<IChunkAccess> consumer) { try { consumer.accept(chunk); } catch (Throwable throwable) { if (throwable instanceof ThreadDeath) throw (ThreadDeath)throwable;  MinecraftServer.LOGGER.error("Load callback for chunk " + chunkX + "," + chunkZ + " in world '" + this.world.getWorld().getName() + "' threw an exception", throwable); }  }
/*  414 */   public final void getChunkAtAsynchronously(int chunkX, int chunkZ, ChunkStatus status, boolean gen, boolean allowSubTicketLevel, Consumer<IChunkAccess> onLoad) { int chunkStatusTicketLevel = 33 + ChunkStatus.getTicketLevelOffset(status); IChunkAccess immediate = getChunkAtImmediately(chunkX, chunkZ); if (immediate != null) { if (allowSubTicketLevel || this.playerChunkMap.getUpdatingChunk(MCUtil.getCoordinateKey(chunkX, chunkZ)).getTicketLevel() <= chunkStatusTicketLevel) { if (immediate.getChunkStatus().isAtLeastStatus(status)) { chunkLoadAccept(chunkX, chunkZ, immediate, onLoad); } else if (gen) { getChunkAtAsynchronously(chunkX, chunkZ, chunkStatusTicketLevel, onLoad); } else { chunkLoadAccept(chunkX, chunkZ, null, onLoad); }  } else if (gen || immediate.getChunkStatus().isAtLeastStatus(status)) { getChunkAtAsynchronously(chunkX, chunkZ, chunkStatusTicketLevel, onLoad); } else { chunkLoadAccept(chunkX, chunkZ, null, onLoad); }  return; }  if (gen && !allowSubTicketLevel) { getChunkAtAsynchronously(chunkX, chunkZ, chunkStatusTicketLevel, onLoad); return; }  getChunkAtAsynchronously(chunkX, chunkZ, 33 + ChunkStatus.getTicketLevelOffset(ChunkStatus.EMPTY), chunk -> { if (chunk == null) throw new IllegalStateException("Chunk cannot be null");  if (!chunk.getChunkStatus().isAtLeastStatus(status)) { if (gen) { getChunkAtAsynchronously(chunkX, chunkZ, chunkStatusTicketLevel, onLoad); return; }  chunkLoadAccept(chunkX, chunkZ, null, onLoad); return; }  if (allowSubTicketLevel) { chunkLoadAccept(chunkX, chunkZ, chunk, onLoad); return; }  getChunkAtAsynchronously(chunkX, chunkZ, chunkStatusTicketLevel, onLoad); }); } @Nullable private PlayerChunk getChunk(long i) { return this.playerChunkMap.getVisibleChunk(i); }
/*      */ 
/*      */   
/*      */   public int b() {
/*  418 */     return this.playerChunkMap.c();
/*      */   }
/*      */   
/*      */   private void a(long i, IChunkAccess ichunkaccess, ChunkStatus chunkstatus) {
/*  422 */     for (int j = 3; j > 0; j--) {
/*  423 */       this.cachePos[j] = this.cachePos[j - 1];
/*  424 */       this.cacheStatus[j] = this.cacheStatus[j - 1];
/*  425 */       this.cacheChunk[j] = this.cacheChunk[j - 1];
/*      */     } 
/*      */     
/*  428 */     this.cachePos[0] = i;
/*  429 */     this.cacheStatus[0] = chunkstatus;
/*  430 */     this.cacheChunk[0] = ichunkaccess;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public Chunk getChunkAtIfCachedImmediately(int x, int z) {
/*  437 */     long k = ChunkCoordIntPair.pair(x, z);
/*      */ 
/*      */ 
/*      */     
/*  441 */     PlayerChunk playerChunk = getChunk(k);
/*  442 */     if (playerChunk == null) {
/*  443 */       return null;
/*      */     }
/*      */     
/*  446 */     return playerChunk.getFullChunkIfCached();
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Chunk getChunkAtIfLoadedImmediately(int x, int z) {
/*  451 */     long readlock, k = ChunkCoordIntPair.pair(x, z);
/*      */     
/*  453 */     if (Thread.currentThread() == this.serverThread) {
/*  454 */       return getChunkAtIfLoadedMainThread(x, z);
/*      */     }
/*      */     
/*  457 */     Chunk ret = null;
/*      */     
/*      */     do {
/*  460 */       readlock = this.loadedChunkMapSeqLock.acquireRead();
/*      */       try {
/*  462 */         ret = (Chunk)this.loadedChunkMap.get(k);
/*  463 */       } catch (Throwable thr) {
/*  464 */         if (thr instanceof ThreadDeath) {
/*  465 */           throw (ThreadDeath)thr;
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*  470 */     while (!this.loadedChunkMapSeqLock.tryReleaseRead(readlock));
/*      */     
/*  472 */     return ret;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public IChunkAccess getChunkAtImmediately(int x, int z) {
/*  477 */     long k = ChunkCoordIntPair.pair(x, z);
/*      */ 
/*      */ 
/*      */     
/*  481 */     PlayerChunk playerChunk = getChunk(k);
/*  482 */     if (playerChunk == null) {
/*  483 */       return null;
/*      */     }
/*      */     
/*  486 */     return playerChunk.getAvailableChunkNow();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> getChunkAtAsynchronously(int x, int z, boolean gen, boolean isUrgent) {
/*  493 */     if (Thread.currentThread() != this.serverThread) {
/*  494 */       CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> future = new CompletableFuture<>();
/*  495 */       this.serverThreadQueue.execute(() -> getChunkAtAsynchronously(x, z, gen, isUrgent).whenComplete(()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  504 */       return future;
/*      */     } 
/*      */     
/*  507 */     if (!PaperConfig.asyncChunks) {
/*  508 */       this.world.getWorld().loadChunk(x, z, gen);
/*  509 */       Chunk chunk = getChunkAtIfLoadedMainThread(x, z);
/*  510 */       return CompletableFuture.completedFuture((chunk != null) ? Either.left(chunk) : PlayerChunk.UNLOADED_CHUNK_ACCESS);
/*      */     } 
/*      */     
/*  513 */     long k = ChunkCoordIntPair.pair(x, z);
/*  514 */     ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(x, z);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  519 */     for (int l = 0; l < 4; l++) {
/*  520 */       if (k == this.cachePos[l] && ChunkStatus.FULL == this.cacheStatus[l]) {
/*  521 */         IChunkAccess ichunkaccess = this.cacheChunk[l];
/*  522 */         if (ichunkaccess != null) {
/*      */ 
/*      */ 
/*      */           
/*  526 */           for (int i1 = 3; i1 > 0; i1--) {
/*  527 */             this.cachePos[i1] = this.cachePos[i1 - 1];
/*  528 */             this.cacheStatus[i1] = this.cacheStatus[i1 - 1];
/*  529 */             this.cacheChunk[i1] = this.cacheChunk[i1 - 1];
/*      */           } 
/*      */           
/*  532 */           this.cachePos[0] = k;
/*  533 */           this.cacheStatus[0] = ChunkStatus.FULL;
/*  534 */           this.cacheChunk[0] = ichunkaccess;
/*      */           
/*  536 */           return CompletableFuture.completedFuture(Either.left(ichunkaccess));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  541 */     if (gen) {
/*  542 */       return bringToFullStatusAsync(x, z, chunkPos, isUrgent);
/*      */     }
/*      */     
/*  545 */     IChunkAccess current = getChunkAtImmediately(x, z);
/*  546 */     if (current != null) {
/*  547 */       if (!(current instanceof ProtoChunkExtension) && !(current instanceof Chunk)) {
/*  548 */         return CompletableFuture.completedFuture(PlayerChunk.UNLOADED_CHUNK_ACCESS);
/*      */       }
/*      */       
/*  551 */       return bringToFullStatusAsync(x, z, chunkPos, isUrgent);
/*      */     } 
/*      */     
/*  554 */     ChunkStatus status = (this.world.getChunkProvider()).playerChunkMap.getStatusOnDiskNoLoad(x, z);
/*      */     
/*  556 */     if (status != null && status != ChunkStatus.FULL)
/*      */     {
/*  558 */       return CompletableFuture.completedFuture(PlayerChunk.UNLOADED_CHUNK_ACCESS);
/*      */     }
/*      */     
/*  561 */     if (status == ChunkStatus.FULL) {
/*  562 */       return bringToFullStatusAsync(x, z, chunkPos, isUrgent);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  569 */     return bringToStatusAsync(x, z, chunkPos, ChunkStatus.EMPTY, isUrgent).thenCompose(either -> {
/*      */           IChunkAccess chunk = either.left().orElse(null);
/*  571 */           return (!(chunk instanceof ProtoChunkExtension) && !(chunk instanceof Chunk)) ? CompletableFuture.completedFuture(PlayerChunk.UNLOADED_CHUNK_ACCESS) : bringToFullStatusAsync(x, z, chunkPos, isUrgent);
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> bringToFullStatusAsync(int x, int z, ChunkCoordIntPair chunkPos, boolean isUrgent) {
/*  581 */     return bringToStatusAsync(x, z, chunkPos, ChunkStatus.FULL, isUrgent);
/*      */   }
/*      */   
/*      */   private CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> bringToStatusAsync(int x, int z, ChunkCoordIntPair chunkPos, ChunkStatus status, boolean isUrgent) {
/*  585 */     CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> future = getChunkFutureMainThread(x, z, status, true, isUrgent);
/*  586 */     Long identifier = Long.valueOf(this.asyncLoadSeqCounter++);
/*  587 */     int ticketLevel = MCUtil.getTicketLevelFor(status);
/*  588 */     addTicketAtLevel(TicketType.ASYNC_LOAD, chunkPos, ticketLevel, identifier);
/*      */     
/*  590 */     return future.thenComposeAsync(either -> { removeTicketAtLevel(TicketType.ASYNC_LOAD, chunkPos, ticketLevel, identifier); addTicketAtLevel(TicketType.UNKNOWN, chunkPos, ticketLevel, chunkPos); Optional<PlayerChunk.Failure> failure = either.right(); if (failure.isPresent()) throw new IllegalStateException("Chunk failed to load: " + ((PlayerChunk.Failure)failure.get()).toString());  return CompletableFuture.completedFuture(either); }this.serverThreadQueue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> void addTicketAtLevel(TicketType<T> ticketType, ChunkCoordIntPair chunkPos, int ticketLevel, T identifier) {
/*  609 */     this.chunkMapDistance.addTicketAtLevel(ticketType, chunkPos, ticketLevel, identifier);
/*      */   }
/*      */   
/*      */   public <T> void removeTicketAtLevel(TicketType<T> ticketType, ChunkCoordIntPair chunkPos, int ticketLevel, T identifier) {
/*  613 */     this.chunkMapDistance.removeTicketAtLevel(ticketType, chunkPos, ticketLevel, identifier);
/*      */   }
/*      */   
/*      */   public boolean markUrgent(ChunkCoordIntPair coords) {
/*  617 */     return this.chunkMapDistance.markUrgent(coords);
/*      */   }
/*      */   
/*      */   public boolean markHighPriority(ChunkCoordIntPair coords, int priority) {
/*  621 */     return this.chunkMapDistance.markHighPriority(coords, priority);
/*      */   }
/*      */   
/*      */   public void markAreaHighPriority(ChunkCoordIntPair center, int priority, int radius) {
/*  625 */     this.chunkMapDistance.markAreaHighPriority(center, priority, radius);
/*      */   }
/*      */   
/*      */   public void clearAreaPriorityTickets(ChunkCoordIntPair center, int radius) {
/*  629 */     this.chunkMapDistance.clearAreaPriorityTickets(center, radius);
/*      */   }
/*      */   
/*      */   public void clearPriorityTickets(ChunkCoordIntPair coords) {
/*  633 */     this.chunkMapDistance.clearPriorityTickets(coords);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public IChunkAccess getChunkAt(int i, int j, ChunkStatus chunkstatus, boolean flag) {
/*  640 */     int x = i, z = j;
/*  641 */     if (Thread.currentThread() != this.serverThread) {
/*  642 */       return CompletableFuture.<IChunkAccess>supplyAsync(() -> getChunkAt(i, j, chunkstatus, flag), this.serverThreadQueue)
/*      */         
/*  644 */         .join();
/*      */     }
/*      */     
/*  647 */     Chunk ifLoaded = getChunkAtIfLoadedMainThread(i, j);
/*  648 */     if (ifLoaded != null) {
/*  649 */       return ifLoaded;
/*      */     }
/*      */     
/*  652 */     GameProfilerFiller gameprofilerfiller = this.world.getMethodProfiler();
/*      */     
/*  654 */     gameprofilerfiller.c("getChunk");
/*  655 */     long k = ChunkCoordIntPair.pair(i, j);
/*      */ 
/*      */ 
/*      */     
/*  659 */     for (int l = 0; l < 4; l++) {
/*  660 */       if (k == this.cachePos[l] && chunkstatus == this.cacheStatus[l]) {
/*  661 */         IChunkAccess iChunkAccess = this.cacheChunk[l];
/*  662 */         if (iChunkAccess != null) {
/*  663 */           return iChunkAccess;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  668 */     gameprofilerfiller.c("getChunkCacheMiss");
/*  669 */     CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> completablefuture = getChunkFutureMainThread(i, j, chunkstatus, flag, true);
/*      */     
/*  671 */     if (!completablefuture.isDone()) {
/*      */       
/*  673 */       ChunkCoordIntPair pair = new ChunkCoordIntPair(x, z);
/*  674 */       this.chunkMapDistance.markUrgent(pair);
/*  675 */       this.world.asyncChunkTaskManager.raisePriority(x, z, 0);
/*  676 */       ChunkTaskManager.pushChunkWait(this.world, x, z);
/*      */       
/*  678 */       SyncLoadFinder.logSyncLoad(this.world, x, z);
/*  679 */       this.world.timings.syncChunkLoad.startTiming();
/*  680 */       Objects.requireNonNull(completablefuture); this.serverThreadQueue.awaitTasks(completablefuture::isDone);
/*  681 */       ChunkTaskManager.popChunkWait();
/*  682 */       this.world.timings.syncChunkLoad.stopTiming();
/*  683 */       this.chunkMapDistance.clearPriorityTickets(pair);
/*  684 */       this.chunkMapDistance.clearUrgent(pair);
/*      */     } 
/*  686 */     IChunkAccess ichunkaccess = (IChunkAccess)((Either)completablefuture.join()).map(ichunkaccess1 -> ichunkaccess1, playerchunk_failure -> {
/*      */           if (flag) {
/*      */             throw (IllegalStateException)SystemUtils.c(new IllegalStateException("Chunk not there when requested: " + playerchunk_failure));
/*      */           }
/*      */ 
/*      */           
/*      */           return null;
/*      */         });
/*      */     
/*  695 */     a(k, ichunkaccess, chunkstatus);
/*  696 */     return ichunkaccess;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public Chunk a(int i, int j) {
/*  703 */     if (Thread.currentThread() != this.serverThread) {
/*  704 */       return null;
/*      */     }
/*  706 */     return getChunkAtIfLoadedMainThread(i, j);
/*      */   }
/*      */ 
/*      */   
/*      */   private void clearCache() {
/*  711 */     Arrays.fill(this.cachePos, ChunkCoordIntPair.a);
/*  712 */     Arrays.fill((Object[])this.cacheStatus, (Object)null);
/*  713 */     Arrays.fill((Object[])this.cacheChunk, (Object)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> getChunkFutureMainThread(int i, int j, ChunkStatus chunkstatus, boolean flag) {
/*  720 */     return getChunkFutureMainThread(i, j, chunkstatus, flag, false);
/*      */   }
/*      */   private CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> getChunkFutureMainThread(int i, int j, ChunkStatus chunkstatus, boolean flag, boolean isUrgent) {
/*      */     Long identifier;
/*  724 */     ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);
/*  725 */     long k = chunkcoordintpair.pair();
/*  726 */     int l = 33 + ChunkStatus.a(chunkstatus);
/*  727 */     PlayerChunk playerchunk = getChunk(k);
/*      */ 
/*      */     
/*  730 */     boolean currentlyUnloading = false;
/*  731 */     if (playerchunk != null) {
/*  732 */       PlayerChunk.State oldChunkState = PlayerChunk.getChunkState(playerchunk.oldTicketLevel);
/*  733 */       PlayerChunk.State currentChunkState = PlayerChunk.getChunkState(playerchunk.getTicketLevel());
/*  734 */       currentlyUnloading = (oldChunkState.isAtLeast(PlayerChunk.State.BORDER) && !currentChunkState.isAtLeast(PlayerChunk.State.BORDER));
/*      */     } 
/*      */     
/*  737 */     if (flag && !currentlyUnloading)
/*      */     
/*  739 */     { this.chunkMapDistance.a(TicketType.UNKNOWN, chunkcoordintpair, l, chunkcoordintpair);
/*  740 */       identifier = Long.valueOf(this.syncLoadCounter++);
/*  741 */       this.chunkMapDistance.addTicketAtLevel(TicketType.REQUIRED_LOAD, chunkcoordintpair, l, identifier);
/*  742 */       if (isUrgent) this.chunkMapDistance.markUrgent(chunkcoordintpair); 
/*  743 */       if (a(playerchunk, l)) {
/*  744 */         GameProfilerFiller gameprofilerfiller = this.world.getMethodProfiler();
/*      */         
/*  746 */         gameprofilerfiller.enter("chunkLoad");
/*  747 */         this.chunkMapDistance.delayDistanceManagerTick = false;
/*  748 */         tickDistanceManager();
/*  749 */         playerchunk = getChunk(k);
/*  750 */         gameprofilerfiller.exit();
/*  751 */         if (a(playerchunk, l)) {
/*  752 */           this.chunkMapDistance.removeTicketAtLevel(TicketType.REQUIRED_LOAD, chunkcoordintpair, l, identifier);
/*  753 */           throw (IllegalStateException)SystemUtils.c(new IllegalStateException("No chunk holder after ticket has been added"));
/*      */         } 
/*      */       }  }
/*  756 */     else { identifier = null; }
/*      */     
/*  758 */     CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> future = a(playerchunk, l) ? PlayerChunk.UNLOADED_CHUNK_ACCESS_FUTURE : playerchunk.a(chunkstatus, this.playerChunkMap);
/*      */     
/*  760 */     if (flag && !currentlyUnloading) {
/*  761 */       future.thenAcceptAsync(either -> this.chunkMapDistance.removeTicketAtLevel(TicketType.REQUIRED_LOAD, chunkcoordintpair, l, identifier), this.serverThreadQueue);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  766 */     if (isUrgent) {
/*  767 */       future.thenAccept(either -> this.chunkMapDistance.clearUrgent(chunkcoordintpair));
/*      */     }
/*  769 */     return future;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean a(@Nullable PlayerChunk playerchunk, int i) {
/*  774 */     return (playerchunk == null || playerchunk.oldTicketLevel > i);
/*      */   }
/*      */   
/*      */   public boolean isLoaded(int i, int j) {
/*  778 */     PlayerChunk playerchunk = getChunk((new ChunkCoordIntPair(i, j)).pair());
/*  779 */     int k = 33 + ChunkStatus.a(ChunkStatus.FULL);
/*      */     
/*  781 */     return !a(playerchunk, k);
/*      */   }
/*      */   public final IBlockAccess getFeaturesReadyChunk(int x, int z) {
/*  784 */     return c(x, z);
/*      */   } public IBlockAccess c(int i, int j) {
/*  786 */     long k = ChunkCoordIntPair.pair(i, j);
/*  787 */     PlayerChunk playerchunk = getChunk(k);
/*      */     
/*  789 */     if (playerchunk == null) {
/*  790 */       return null;
/*      */     }
/*  792 */     int l = b.size() - 1;
/*      */     
/*      */     while (true) {
/*  795 */       ChunkStatus chunkstatus = b.get(l);
/*  796 */       Optional<IChunkAccess> optional = ((Either)playerchunk.getStatusFutureUnchecked(chunkstatus).getNow(PlayerChunk.UNLOADED_CHUNK_ACCESS)).left();
/*      */       
/*  798 */       if (optional.isPresent()) {
/*  799 */         return optional.get();
/*      */       }
/*      */       
/*  802 */       if (chunkstatus == ChunkStatus.LIGHT.e()) {
/*  803 */         return null;
/*      */       }
/*      */       
/*  806 */       l--;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public World getWorld() {
/*  813 */     return this.world;
/*      */   }
/*      */   
/*      */   public boolean runTasks() {
/*  817 */     return this.serverThreadQueue.executeNext();
/*      */   }
/*      */   
/*      */   public boolean tickDistanceManager() {
/*  821 */     if (this.chunkMapDistance.delayDistanceManagerTick) return false; 
/*  822 */     if (this.playerChunkMap.unloadingPlayerChunk) { MinecraftServer.LOGGER.fatal("Cannot tick distance manager while unloading playerchunks", new Throwable()); throw new IllegalStateException("Cannot tick distance manager while unloading playerchunks"); }
/*  823 */      MinecraftTimings.distanceManagerTick.startTiming(); try {
/*  824 */       boolean flag = this.chunkMapDistance.a(this.playerChunkMap);
/*  825 */       boolean flag1 = this.playerChunkMap.b();
/*      */       
/*  827 */       if (!flag && !flag1) {
/*  828 */         return false;
/*      */       }
/*  830 */       clearCache();
/*  831 */       return true;
/*      */     } finally {
/*  833 */       MinecraftTimings.distanceManagerTick.stopTiming();
/*      */     } 
/*      */   } public final boolean isInEntityTickingChunk(Entity entity) {
/*  836 */     return a(entity);
/*      */   }
/*      */   
/*      */   public boolean a(Entity entity) {
/*  840 */     PlayerChunk playerChunk = getChunk(MCUtil.getCoordinateKey(entity));
/*  841 */     return (playerChunk != null && playerChunk.isEntityTickingReady());
/*      */   }
/*      */   
/*      */   public final boolean isEntityTickingChunk(ChunkCoordIntPair chunkcoordintpair) {
/*  845 */     return a(chunkcoordintpair);
/*      */   }
/*      */   
/*      */   public boolean a(ChunkCoordIntPair chunkcoordintpair) {
/*  849 */     PlayerChunk playerChunk = getChunk(MCUtil.getCoordinateKey(chunkcoordintpair));
/*  850 */     return (playerChunk != null && playerChunk.isEntityTickingReady());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean a(BlockPosition blockposition) {
/*  858 */     PlayerChunk playerChunk = getChunk(MCUtil.getCoordinateKey(blockposition));
/*  859 */     return (playerChunk != null && playerChunk.isTickingReady());
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean a(long i, Function<PlayerChunk, CompletableFuture<Either<Chunk, PlayerChunk.Failure>>> function) {
/*  864 */     PlayerChunk playerchunk = getChunk(i);
/*      */     
/*  866 */     if (playerchunk == null) {
/*  867 */       return false;
/*      */     }
/*  869 */     Either<Chunk, PlayerChunk.Failure> either = ((CompletableFuture<Either<Chunk, PlayerChunk.Failure>>)function.apply(playerchunk)).getNow(PlayerChunk.UNLOADED_CHUNK);
/*      */     
/*  871 */     return either.left().isPresent();
/*      */   }
/*      */   
/*      */   public void save(boolean flag)
/*      */   {
/*  876 */     tickDistanceManager();
/*  877 */     Timing timed = this.world.timings.chunkSaveData.startTiming(); 
/*  878 */     try { this.playerChunkMap.save(flag);
/*  879 */       if (timed != null) timed.close();  }
/*      */     catch (Throwable throwable) { if (timed != null)
/*      */         try { timed.close(); }
/*      */         catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */           throw throwable; }
/*  884 */      } public void saveIncrementally() { tickDistanceManager();
/*  885 */     Timing timed = this.world.timings.chunkSaveData.startTiming(); 
/*  886 */     try { this.playerChunkMap.saveIncrementally();
/*  887 */       if (timed != null) timed.close();  }
/*      */     catch (Throwable throwable) { if (timed != null)
/*      */         try {
/*      */           timed.close();
/*      */         } catch (Throwable throwable1) {
/*      */           throwable.addSuppressed(throwable1);
/*      */         }   throw throwable; }
/*  894 */      } public void close() throws IOException { close(true); }
/*      */ 
/*      */   
/*      */   public void close(boolean save) throws IOException {
/*  898 */     if (save) {
/*  899 */       save(true);
/*      */     }
/*      */     
/*  902 */     this.lightEngine.close();
/*  903 */     this.playerChunkMap.close();
/*      */   }
/*      */ 
/*      */   
/*      */   public void purgeUnload() {
/*  908 */     this.world.getMethodProfiler().enter("purge");
/*  909 */     this.chunkMapDistance.purgeTickets();
/*  910 */     tickDistanceManager();
/*  911 */     this.world.getMethodProfiler().exitEnter("unload");
/*  912 */     this.playerChunkMap.unloadChunks(() -> true);
/*  913 */     this.world.getMethodProfiler().exit();
/*  914 */     clearCache();
/*      */   }
/*      */ 
/*      */   
/*      */   public void tick(BooleanSupplier booleansupplier) {
/*  919 */     this.world.getMethodProfiler().enter("purge");
/*  920 */     this.world.timings.doChunkMap.startTiming();
/*  921 */     this.chunkMapDistance.purgeTickets();
/*      */     
/*  923 */     tickDistanceManager();
/*  924 */     this.world.timings.doChunkMap.stopTiming();
/*  925 */     this.world.getMethodProfiler().exitEnter("chunks");
/*  926 */     this.world.timings.chunks.startTiming();
/*  927 */     tickChunks();
/*  928 */     this.world.timings.chunks.stopTiming();
/*  929 */     this.world.timings.doChunkUnload.startTiming();
/*  930 */     this.world.getMethodProfiler().exitEnter("unload");
/*  931 */     this.playerChunkMap.unloadChunks(booleansupplier);
/*      */     
/*  933 */     this.world.timings.doChunkUnload.stopTiming();
/*  934 */     this.world.getMethodProfiler().exit();
/*  935 */     clearCache();
/*      */   }
/*      */   
/*      */   private void tickChunks() {
/*  939 */     long i = this.world.getTime();
/*  940 */     long j = i - this.lastTickTime;
/*      */     
/*  942 */     this.lastTickTime = i;
/*  943 */     WorldData worlddata = this.world.getWorldData();
/*  944 */     boolean flag = this.world.isDebugWorld();
/*  945 */     boolean flag1 = (this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && !this.world.getPlayers().isEmpty());
/*      */     
/*  947 */     if (!flag) {
/*      */       SpawnerCreature.d spawnercreature_d;
/*  949 */       PlayerChunkMap playerChunkMap = this.playerChunkMap;
/*  950 */       for (EntityPlayer player : this.world.players) {
/*  951 */         if (!player.affectsSpawning || player.isSpectator()) {
/*  952 */           playerChunkMap.playerMobSpawnMap.remove(player);
/*      */           
/*      */           continue;
/*      */         } 
/*  956 */         int viewDistance = this.playerChunkMap.getEffectiveViewDistance();
/*      */ 
/*      */         
/*  959 */         int chunkRange = this.world.spigotConfig.mobSpawnRange;
/*  960 */         chunkRange = (chunkRange > viewDistance) ? (byte)viewDistance : chunkRange;
/*  961 */         chunkRange = (chunkRange > 8) ? 8 : chunkRange;
/*      */         
/*  963 */         PlayerNaturallySpawnCreaturesEvent event = new PlayerNaturallySpawnCreaturesEvent((Player)player.getBukkitEntity(), (byte)chunkRange);
/*  964 */         event.callEvent();
/*  965 */         if (event.isCancelled() || event.getSpawnRadius() < 0 || playerChunkMap.playerChunkTickRangeMap.getLastViewDistance(player) == -1) {
/*  966 */           playerChunkMap.playerMobSpawnMap.remove(player);
/*      */           
/*      */           continue;
/*      */         } 
/*  970 */         int range = Math.min(event.getSpawnRadius(), 32);
/*  971 */         int chunkX = MCUtil.getChunkCoordinate(player.locX());
/*  972 */         int chunkZ = MCUtil.getChunkCoordinate(player.locZ());
/*      */         
/*  974 */         playerChunkMap.playerMobSpawnMap.addOrUpdate(player, chunkX, chunkZ, range);
/*  975 */         player.lastEntitySpawnRadiusSquared = ((range << 4) * (range << 4));
/*  976 */         player.playerNaturallySpawnedEvent = event;
/*      */       } 
/*      */       
/*  979 */       this.world.getMethodProfiler().enter("pollingChunks");
/*  980 */       int k = this.world.getGameRules().getInt(GameRules.RANDOM_TICK_SPEED);
/*  981 */       boolean flag2 = (this.world.ticksPerAnimalSpawns != 0L && worlddata.getTime() % this.world.ticksPerAnimalSpawns == 0L);
/*      */       
/*  983 */       this.world.getMethodProfiler().enter("naturalSpawnCount");
/*  984 */       this.world.timings.countNaturalMobs.startTiming();
/*  985 */       int l = this.chunkMapDistance.b();
/*      */ 
/*      */       
/*  988 */       if (this.playerChunkMap.playerMobDistanceMap != null) {
/*      */         
/*  990 */         this.world.timings.playerMobDistanceMapUpdate.startTiming();
/*  991 */         this.playerChunkMap.playerMobDistanceMap.update(this.world.players, this.playerChunkMap.viewDistance);
/*  992 */         this.world.timings.playerMobDistanceMapUpdate.stopTiming();
/*      */         
/*  994 */         for (EntityPlayer player : this.world.players) {
/*  995 */           Arrays.fill(player.mobCounts, 0);
/*      */         }
/*  997 */         spawnercreature_d = SpawnerCreature.countMobs(l, this.world.A(), this::a, true);
/*      */       } else {
/*  999 */         spawnercreature_d = SpawnerCreature.countMobs(l, this.world.A(), this::a, false);
/*      */       } 
/*      */       
/* 1002 */       this.world.timings.countNaturalMobs.stopTiming();
/*      */       
/* 1004 */       this.p = spawnercreature_d;
/* 1005 */       this.world.getMethodProfiler().exit();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1010 */       IteratorSafeOrderedReferenceSet.Iterator<Chunk> iterator = this.entityTickingChunks.iterator();
/*      */       try {
/* 1012 */         while (iterator.hasNext()) {
/* 1013 */           Chunk chunk = (Chunk)iterator.next();
/* 1014 */           PlayerChunk playerchunk = chunk.playerChunk;
/* 1015 */           if (playerchunk != null) {
/*      */             
/* 1017 */             this.world.getMethodProfiler().enter("broadcast");
/* 1018 */             this.world.timings.broadcastChunkUpdates.startTiming();
/* 1019 */             playerchunk.a(chunk);
/* 1020 */             this.world.timings.broadcastChunkUpdates.stopTiming();
/* 1021 */             this.world.getMethodProfiler().exit();
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1026 */             ChunkCoordIntPair chunkcoordintpair = playerchunk.i();
/*      */             
/* 1028 */             if (!this.playerChunkMap.isOutsideOfRange(playerchunk, chunkcoordintpair, false)) {
/* 1029 */               chunk.setInhabitedTime(chunk.getInhabitedTime() + j);
/* 1030 */               if (flag1 && (this.allowMonsters || this.allowAnimals) && this.world.getWorldBorder().isInBounds(chunk.getPos()) && !this.playerChunkMap.isOutsideOfRange(playerchunk, chunkcoordintpair, true)) {
/* 1031 */                 SpawnerCreature.a(this.world, chunk, spawnercreature_d, this.allowAnimals, this.allowMonsters, flag2);
/*      */               }
/*      */               
/* 1034 */               this.world.timings.chunkTicks.startTiming();
/* 1035 */               this.world.a(chunk, k);
/* 1036 */               this.world.timings.chunkTicks.stopTiming();
/* 1037 */               MinecraftServer.getServer().executeMidTickTasks();
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } finally {
/*      */         
/* 1043 */         iterator.finishedIterating();
/*      */       } 
/*      */       
/* 1046 */       this.world.getMethodProfiler().enter("customSpawners");
/* 1047 */       if (flag1) {
/* 1048 */         Timing ignored = this.world.timings.miscMobSpawning.startTiming(); 
/* 1049 */         try { this.world.doMobSpawning(this.allowMonsters, this.allowAnimals);
/* 1050 */           if (ignored != null) ignored.close();  } catch (Throwable throwable) { if (ignored != null)
/*      */             try { ignored.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }
/*      */       
/* 1053 */       }  this.world.getMethodProfiler().exit();
/* 1054 */       this.world.getMethodProfiler().exit();
/*      */     } 
/*      */ 
/*      */     
/* 1058 */     List<NetworkManager> disabledFlushes = new ArrayList<>(this.world.getPlayers().size());
/* 1059 */     for (EntityPlayer player : this.world.getPlayers()) {
/* 1060 */       PlayerConnection connection = player.playerConnection;
/* 1061 */       if (connection != null) {
/* 1062 */         connection.networkManager.disableAutomaticFlush();
/* 1063 */         disabledFlushes.add(connection.networkManager);
/*      */       } 
/*      */     } 
/*      */     
/*      */     try {
/* 1068 */       this.playerChunkMap.g();
/*      */     } finally {
/*      */       
/* 1071 */       for (NetworkManager networkManager : disabledFlushes) {
/* 1072 */         networkManager.enableAutomaticFlush();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(long i, Consumer<Chunk> consumer) {
/* 1079 */     PlayerChunk playerchunk = getChunk(i);
/*      */     
/* 1081 */     if (playerchunk != null) {
/* 1082 */       ((Either)playerchunk.c().getNow(PlayerChunk.UNLOADED_CHUNK)).left().ifPresent(consumer);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() {
/* 1089 */     return "ServerChunkCache: " + h();
/*      */   }
/*      */   
/*      */   @VisibleForTesting
/*      */   public int f() {
/* 1094 */     return this.serverThreadQueue.bh();
/*      */   }
/*      */   
/*      */   public ChunkGenerator getChunkGenerator() {
/* 1098 */     return this.chunkGenerator;
/*      */   }
/*      */   
/*      */   public int h() {
/* 1102 */     return this.playerChunkMap.d();
/*      */   }
/*      */   
/*      */   public void flagDirty(BlockPosition blockposition) {
/* 1106 */     int i = blockposition.getX() >> 4;
/* 1107 */     int j = blockposition.getZ() >> 4;
/* 1108 */     PlayerChunk playerchunk = getChunk(ChunkCoordIntPair.pair(i, j));
/*      */     
/* 1110 */     if (playerchunk != null) {
/* 1111 */       playerchunk.a(blockposition);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(EnumSkyBlock enumskyblock, SectionPosition sectionposition) {
/* 1118 */     this.serverThreadQueue.execute(() -> {
/*      */           PlayerChunk playerchunk = getChunk(sectionposition.r().pair());
/*      */           if (playerchunk != null) {
/*      */             playerchunk.a(enumskyblock, sectionposition.b());
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> void addTicket(TicketType<T> tickettype, ChunkCoordIntPair chunkcoordintpair, int i, T t0) {
/* 1129 */     this.chunkMapDistance.addTicket(tickettype, chunkcoordintpair, i, t0);
/*      */   }
/*      */   
/*      */   public <T> void removeTicket(TicketType<T> tickettype, ChunkCoordIntPair chunkcoordintpair, int i, T t0) {
/* 1133 */     this.chunkMapDistance.removeTicket(tickettype, chunkcoordintpair, i, t0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(ChunkCoordIntPair chunkcoordintpair, boolean flag) {
/* 1138 */     this.chunkMapDistance.a(chunkcoordintpair, flag);
/*      */   }
/*      */   
/*      */   public void movePlayer(EntityPlayer entityplayer) {
/* 1142 */     this.playerChunkMap.movePlayer(entityplayer);
/*      */   }
/*      */   
/*      */   public void removeEntity(Entity entity) {
/* 1146 */     this.playerChunkMap.removeEntity(entity);
/*      */   }
/*      */   
/*      */   public void addEntity(Entity entity) {
/* 1150 */     this.playerChunkMap.addEntity(entity);
/*      */   }
/*      */   
/*      */   public void broadcastIncludingSelf(Entity entity, Packet<?> packet) {
/* 1154 */     this.playerChunkMap.broadcastIncludingSelf(entity, packet);
/*      */   }
/*      */   
/*      */   public void broadcast(Entity entity, Packet<?> packet) {
/* 1158 */     this.playerChunkMap.broadcast(entity, packet);
/*      */   }
/*      */   
/*      */   public void setViewDistance(int i) {
/* 1162 */     this.playerChunkMap.setViewDistance(i);
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(boolean flag, boolean flag1) {
/* 1167 */     this.allowMonsters = flag;
/* 1168 */     this.allowAnimals = flag1;
/*      */   }
/*      */   
/*      */   public WorldPersistentData getWorldPersistentData() {
/* 1172 */     return this.worldPersistentData;
/*      */   }
/*      */   
/*      */   public VillagePlace j() {
/* 1176 */     return this.playerChunkMap.h();
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public SpawnerCreature.d k() {
/* 1181 */     return this.p;
/*      */   }
/*      */   
/*      */   final class a
/*      */     extends IAsyncTaskHandler<Runnable> {
/*      */     private a(World world) {
/* 1187 */       super("Chunk source main thread executor for " + world.getDimensionKey().a());
/*      */     }
/*      */ 
/*      */     
/*      */     protected Runnable postToMainThread(Runnable runnable) {
/* 1192 */       return runnable;
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean canExecute(Runnable runnable) {
/* 1197 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean isNotMainThread() {
/* 1202 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     protected Thread getThread() {
/* 1207 */       return ChunkProviderServer.this.serverThread;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void executeTask(Runnable runnable) {
/* 1212 */       ChunkProviderServer.this.world.getMethodProfiler().c("runTask");
/* 1213 */       super.executeTask(runnable);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean executeNext() {
/* 1219 */       TickThread.softEnsureTickThread("Cannot execute chunk tasks off-main thread");
/*      */       
/*      */       try {
/* 1222 */         boolean execChunkTask = (ChunkTaskManager.pollChunkWaitQueue() || ChunkProviderServer.this.world.asyncChunkTaskManager.pollNextChunkTask());
/* 1223 */         if (ChunkProviderServer.this.tickDistanceManager()) {
/* 1224 */           return true;
/*      */         }
/*      */         
/* 1227 */         return (super.executeNext() || execChunkTask);
/*      */       } finally {
/*      */         
/* 1230 */         ChunkProviderServer.this.playerChunkMap.chunkLoadConversionCallbackExecutor.run();
/* 1231 */         ChunkProviderServer.this.playerChunkMap.callbackExecutor.run();
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkProviderServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */