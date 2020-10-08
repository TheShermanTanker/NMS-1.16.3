/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import co.aikar.timings.Timing;
/*      */ import com.destroystokyo.paper.PaperWorldConfig;
/*      */ import com.destroystokyo.paper.io.IOUtil;
/*      */ import com.destroystokyo.paper.io.PaperFileIOThread;
/*      */ import com.destroystokyo.paper.io.chunk.ChunkTaskManager;
/*      */ import com.destroystokyo.paper.util.PlayerMobDistanceMap;
/*      */ import com.destroystokyo.paper.util.SneakyThrow;
/*      */ import com.destroystokyo.paper.util.map.Long2ObjectLinkedOpenHashMapFastCopy;
/*      */ import com.destroystokyo.paper.util.misc.PlayerAreaMap;
/*      */ import com.destroystokyo.paper.util.misc.PooledLinkedHashSets;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.mojang.datafixers.DataFixer;
/*      */ import com.mojang.datafixers.util.Either;
/*      */ import com.tuinity.tuinity.chunk.SingleThreadChunkRegionManager;
/*      */ import com.tuinity.tuinity.util.TickThread;
/*      */ import com.tuinity.tuinity.util.maplist.IteratorSafeOrderedReferenceSet;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*      */ import it.unimi.dsi.fastutil.longs.Long2ByteMap;
/*      */ import it.unimi.dsi.fastutil.longs.Long2IntMap;
/*      */ import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*      */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*      */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*      */ import it.unimi.dsi.fastutil.longs.LongSet;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectRBTreeSet;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.Writer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.Queue;
/*      */ import java.util.concurrent.CompletableFuture;
/*      */ import java.util.concurrent.CompletionException;
/*      */ import java.util.concurrent.CompletionStage;
/*      */ import java.util.concurrent.Executor;
/*      */ import java.util.concurrent.atomic.AtomicInteger;
/*      */ import java.util.function.BiFunction;
/*      */ import java.util.function.BooleanSupplier;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.IntFunction;
/*      */ import java.util.function.Supplier;
/*      */ import java.util.stream.Stream;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableBoolean;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*      */ import org.spigotmc.AsyncCatcher;
/*      */ import org.spigotmc.SlackActivityAccountant;
/*      */ import org.spigotmc.SpigotWorldConfig;
/*      */ import org.spigotmc.TrackingRange;
/*      */ 
/*      */ public class PlayerChunkMap extends IChunkLoader implements PlayerChunk.d {
/*   60 */   private static final Logger LOGGER = LogManager.getLogger();
/*   61 */   public static final int GOLDEN_TICKET = 33 + ChunkStatus.b();
/*      */   
/*   63 */   public final Long2ObjectLinkedOpenHashMap<PlayerChunk> updatingChunks = (Long2ObjectLinkedOpenHashMap<PlayerChunk>)new Long2ObjectLinkedOpenHashMapFastCopy();
/*   64 */   public final Long2ObjectLinkedOpenHashMap<PlayerChunk> visibleChunks = (Long2ObjectLinkedOpenHashMap<PlayerChunk>)new ProtectedVisibleChunksMap();
/*      */   
/*      */   private class ProtectedVisibleChunksMap extends Long2ObjectLinkedOpenHashMapFastCopy<PlayerChunk> { private ProtectedVisibleChunksMap() {}
/*      */     
/*      */     public PlayerChunk put(long k, PlayerChunk playerChunk) {
/*   69 */       throw new UnsupportedOperationException("Updating visible Chunks");
/*      */     }
/*      */ 
/*      */     
/*      */     public PlayerChunk remove(long k) {
/*   74 */       throw new UnsupportedOperationException("Removing visible Chunks");
/*      */     }
/*      */ 
/*      */     
/*      */     public PlayerChunk get(long k) {
/*   79 */       return PlayerChunkMap.this.getVisibleChunk(k);
/*      */     }
/*      */     
/*      */     public PlayerChunk safeGet(long k) {
/*   83 */       return (PlayerChunk)super.get(k);
/*      */     } }
/*      */ 
/*      */   
/*   87 */   public final Long2ObjectLinkedOpenHashMapFastCopy<PlayerChunk> pendingVisibleChunks = new Long2ObjectLinkedOpenHashMapFastCopy(); public transient Long2ObjectLinkedOpenHashMapFastCopy<PlayerChunk> visibleChunksClone; private final Long2ObjectLinkedOpenHashMap<PlayerChunk> pendingUnload; final LongSet loadedChunks; public final WorldServer world; private final LightEngineThreaded lightEngine; private final IAsyncTaskHandler<Runnable> executor; final Executor mainInvokingExecutor; public final ChunkGenerator chunkGenerator; private final Supplier<WorldPersistentData> l; private final VillagePlace m; public final LongSet unloadQueue; private boolean updatingChunksModified; private final ChunkTaskQueueSorter p; private final Mailbox<ChunkTaskQueueSorter.a<Runnable>> mailboxWorldGen; final Mailbox<ChunkTaskQueueSorter.a<Runnable>> mailboxMain; final Mailbox<ChunkTaskQueueSorter.a<Runnable>> mailboxLight; public final WorldLoadListener worldLoadListener; public final a chunkDistanceManager; private final AtomicInteger u; public final DefinedStructureManager definedStructureManager; private final File w;
/*      */   private final PlayerMap playerMap;
/*      */   public final Int2ObjectMap<EntityTracker> trackedEntities;
/*      */   private final Long2ByteMap z;
/*      */   private final Queue<Runnable> A;
/*      */   int viewDistance;
/*      */   public final PlayerMobDistanceMap playerMobDistanceMap;
/*      */   
/*      */   public final Supplier<WorldPersistentData> getWorldPersistentDataSupplier() {
/*   96 */     return this.l;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addLightTask(PlayerChunk playerchunk, Runnable run) {
/*  106 */     this.mailboxLight.a(ChunkTaskQueueSorter.a(playerchunk, run));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final Queue<Runnable> getUnloadQueueTasks() {
/*  117 */     return this.A;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  122 */   public final CallbackExecutor callbackExecutor = new CallbackExecutor();
/*      */   
/*      */   public static final class CallbackExecutor
/*      */     implements Executor, Runnable {
/*      */     private Runnable queued;
/*      */     
/*      */     public void execute(Runnable runnable) {
/*  129 */       AsyncCatcher.catchOp("Callback Executor execute");
/*  130 */       if (this.queued != null) {
/*  131 */         MinecraftServer.LOGGER.fatal("Failed to schedule runnable", new IllegalStateException("Already queued"));
/*  132 */         throw new IllegalStateException("Already queued");
/*      */       } 
/*  134 */       this.queued = runnable;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*  140 */       AsyncCatcher.catchOp("Callback Executor run");
/*      */       
/*  142 */       Runnable task = this.queued;
/*  143 */       this.queued = null;
/*  144 */       if (task != null)
/*      */       {
/*  146 */         task.run();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  153 */   final CallbackExecutor chunkLoadConversionCallbackExecutor = new CallbackExecutor();
/*      */ 
/*      */   
/*  156 */   private final PooledLinkedHashSets<EntityPlayer> pooledLinkedPlayerHashSets = new PooledLinkedHashSets();
/*      */   
/*      */   public static boolean isLegacyTrackingEntity(Entity entity) {
/*  159 */     return entity.isLegacyTrackingEntity;
/*      */   }
/*      */ 
/*      */   
/*  163 */   static final TrackingRange.TrackingRangeType[] TRACKING_RANGE_TYPES = TrackingRange.TrackingRangeType.values(); final PlayerAreaMap[] playerEntityTrackerTrackMaps; final int[] entityTrackerTrackRanges; public final PlayerAreaMap playerMobSpawnMap; public final PlayerAreaMap playerChunkTickRangeMap; int noTickViewDistance; public final PlayerAreaMap playerViewDistanceBroadcastMap; public final PlayerAreaMap playerViewDistanceTickMap; public final PlayerAreaMap playerViewDistanceNoTickMap;
/*      */   public final SingleThreadChunkRegionManager<RegionData> dataRegionManager;
/*      */   private final ExecutorService lightThread;
/*      */   
/*      */   private int convertSpigotRangeToVanilla(int vanilla) {
/*  168 */     return MinecraftServer.getServer().applyTrackingRangeScale(vanilla);
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
/*      */   public final int getRawNoTickViewDistance() {
/*  185 */     return this.noTickViewDistance;
/*      */   }
/*      */   public final int getEffectiveNoTickViewDistance() {
/*  188 */     return (this.noTickViewDistance == -1) ? getEffectiveViewDistance() : this.noTickViewDistance;
/*      */   }
/*      */   public final int getLoadViewDistance() {
/*  191 */     return Math.max(getEffectiveViewDistance(), getEffectiveNoTickViewDistance());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addPlayerToDistanceMaps(EntityPlayer player) {
/*  200 */     TickThread.softEnsureTickThread("Cannot update distance maps off of the main thread");
/*  201 */     int chunkX = MCUtil.getChunkCoordinate(player.locX());
/*  202 */     int chunkZ = MCUtil.getChunkCoordinate(player.locZ());
/*      */ 
/*      */     
/*  205 */     for (int i = 0, len = TRACKING_RANGE_TYPES.length; i < len; i++) {
/*  206 */       PlayerAreaMap trackMap = this.playerEntityTrackerTrackMaps[i];
/*  207 */       int trackRange = this.entityTrackerTrackRanges[i];
/*      */       
/*  209 */       trackMap.add(player, chunkX, chunkZ, Math.min(trackRange, getEffectiveViewDistance()));
/*      */     } 
/*      */ 
/*      */     
/*  213 */     this.playerChunkTickRangeMap.add(player, chunkX, chunkZ, 8);
/*      */ 
/*      */     
/*  216 */     int effectiveTickViewDistance = getEffectiveViewDistance();
/*  217 */     int effectiveNoTickViewDistance = Math.max(getEffectiveNoTickViewDistance(), effectiveTickViewDistance);
/*      */     
/*  219 */     if (!cannotLoadChunks(player)) {
/*  220 */       this.playerViewDistanceTickMap.add(player, chunkX, chunkZ, effectiveTickViewDistance);
/*  221 */       this.playerViewDistanceNoTickMap.add(player, chunkX, chunkZ, effectiveNoTickViewDistance + 2);
/*      */     } 
/*      */     
/*  224 */     player.needsChunkCenterUpdate = true;
/*  225 */     this.playerViewDistanceBroadcastMap.add(player, chunkX, chunkZ, effectiveNoTickViewDistance + 1);
/*  226 */     player.needsChunkCenterUpdate = false;
/*      */   }
/*      */ 
/*      */   
/*      */   void removePlayerFromDistanceMaps(EntityPlayer player) {
/*  231 */     TickThread.softEnsureTickThread("Cannot update distance maps off of the main thread");
/*      */     
/*  233 */     for (int i = 0, len = TRACKING_RANGE_TYPES.length; i < len; i++) {
/*  234 */       this.playerEntityTrackerTrackMaps[i].remove(player);
/*      */     }
/*      */ 
/*      */     
/*  238 */     this.playerMobSpawnMap.remove(player);
/*  239 */     this.playerChunkTickRangeMap.remove(player);
/*      */ 
/*      */     
/*  242 */     this.playerViewDistanceBroadcastMap.remove(player);
/*  243 */     this.playerViewDistanceTickMap.remove(player);
/*  244 */     this.playerViewDistanceNoTickMap.remove(player);
/*      */   }
/*      */ 
/*      */   
/*      */   void updateMaps(EntityPlayer player) {
/*  249 */     TickThread.softEnsureTickThread("Cannot update distance maps off of the main thread");
/*  250 */     int chunkX = MCUtil.getChunkCoordinate(player.locX());
/*  251 */     int chunkZ = MCUtil.getChunkCoordinate(player.locZ());
/*      */ 
/*      */     
/*  254 */     for (int i = 0, len = TRACKING_RANGE_TYPES.length; i < len; i++) {
/*  255 */       PlayerAreaMap trackMap = this.playerEntityTrackerTrackMaps[i];
/*  256 */       int trackRange = this.entityTrackerTrackRanges[i];
/*      */       
/*  258 */       trackMap.update(player, chunkX, chunkZ, Math.min(trackRange, getEffectiveViewDistance()));
/*      */     } 
/*      */ 
/*      */     
/*  262 */     this.playerChunkTickRangeMap.update(player, chunkX, chunkZ, 8);
/*      */ 
/*      */     
/*  265 */     int effectiveTickViewDistance = getEffectiveViewDistance();
/*  266 */     int effectiveNoTickViewDistance = Math.max(getEffectiveNoTickViewDistance(), effectiveTickViewDistance);
/*      */     
/*  268 */     if (!cannotLoadChunks(player)) {
/*  269 */       this.playerViewDistanceTickMap.update(player, chunkX, chunkZ, effectiveTickViewDistance);
/*  270 */       this.playerViewDistanceNoTickMap.update(player, chunkX, chunkZ, effectiveNoTickViewDistance + 2);
/*      */     } 
/*      */     
/*  273 */     player.needsChunkCenterUpdate = true;
/*  274 */     this.playerViewDistanceBroadcastMap.update(player, chunkX, chunkZ, effectiveNoTickViewDistance + 1);
/*  275 */     player.needsChunkCenterUpdate = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public enum RegionData
/*      */     implements SingleThreadChunkRegionManager.RegionDataCreator<RegionData>
/*      */   {
/*      */     public Object createData(SingleThreadChunkRegionManager.RegionSection<RegionData> section, SingleThreadChunkRegionManager<RegionData> regionManager) {
/*  288 */       throw new AbstractMethodError();
/*      */     } } public void queueHolderUpdate(PlayerChunk playerchunk) { Runnable runnable = () -> { if (isUnloading(playerchunk)) return;  this.chunkDistanceManager.pendingChunkUpdates.add(playerchunk); if (!this.chunkDistanceManager.pollingPendingChunkUpdates) this.world.getChunkProvider().tickDistanceManager();  }; if (MCUtil.isMainThread()) { runnable.run(); } else { this.executor.execute(runnable); }  } private boolean isUnloading(PlayerChunk playerchunk) { return (playerchunk == null || this.unloadQueue.contains(playerchunk.location.pair())); }
/*      */   private void updateChunkPriorityMap(Long2IntOpenHashMap map, long chunk, int level) { int prev = map.getOrDefault(chunk, -1); if (level > prev) map.put(chunk, level);  }
/*      */   public void checkHighPriorityChunks(EntityPlayer player) { int currentTick = MinecraftServer.currentTick; if (currentTick - player.lastHighPriorityChecked < 20L || !player.isRealPlayer) return;  player.lastHighPriorityChecked = currentTick; Long2IntOpenHashMap priorities = new Long2IntOpenHashMap(); int viewDistance = getEffectiveNoTickViewDistance(); BlockPosition.MutableBlockPosition pos = new BlockPosition.MutableBlockPosition(); double playerChunkX = (MathHelper.floor(player.locX()) >> 4); double playerChunkZ = (MathHelper.floor(player.locZ()) >> 4); pos.setValues(player.locX(), 0.0D, player.locZ()); double twoThirdModifier = 0.6666666666666666D; MCUtil.getSpiralOutChunks(pos, Math.min(6, viewDistance)).forEach(coord -> { if (shouldSkipPrioritization(coord)) return;  double dist = MCUtil.distance(playerChunkX, 0.0D, playerChunkZ, coord.x, 0.0D, coord.z); if (dist <= 16.0D) { updateChunkPriorityMap(priorities, coord.pair(), (int)(27.0D - Math.sqrt(dist))); return; }  updateChunkPriorityMap(priorities, coord.pair(), (int)(20.0D - Math.sqrt(dist) * twoThirdModifier)); }); ChunkCoordIntPair front3 = player.getChunkInFront(3.0D); pos.setValues(front3.x << 4, 0, front3.z << 4); MCUtil.getSpiralOutChunks(pos, Math.min(5, viewDistance)).forEach(coord -> { if (shouldSkipPrioritization(coord)) return;  double dist = MCUtil.distance(playerChunkX, 0.0D, playerChunkZ, coord.x, 0.0D, coord.z); updateChunkPriorityMap(priorities, coord.pair(), (int)(25.0D - Math.sqrt(dist) * twoThirdModifier)); }); if (viewDistance > 4) { ChunkCoordIntPair front5 = player.getChunkInFront(5.0D); pos.setValues(front5.x << 4, 0, front5.z << 4); MCUtil.getSpiralOutChunks(pos, 4).forEach(coord -> { if (shouldSkipPrioritization(coord)) return;  double dist = MCUtil.distance(playerChunkX, 0.0D, playerChunkZ, coord.x, 0.0D, coord.z); updateChunkPriorityMap(priorities, coord.pair(), (int)(25.0D - Math.sqrt(dist) * twoThirdModifier)); }); }  if (viewDistance > 6) { ChunkCoordIntPair front7 = player.getChunkInFront(7.0D); pos.setValues(front7.x << 4, 0, front7.z << 4); MCUtil.getSpiralOutChunks(pos, 3).forEach(coord -> { if (shouldSkipPrioritization(coord)) return;  double dist = MCUtil.distance(playerChunkX, 0.0D, playerChunkZ, coord.x, 0.0D, coord.z); updateChunkPriorityMap(priorities, coord.pair(), (int)(25.0D - Math.sqrt(dist) * twoThirdModifier)); }); }  if (priorities.isEmpty()) return;  this.chunkDistanceManager.delayDistanceManagerTick = true; priorities.long2IntEntrySet().fastForEach(entry -> this.chunkDistanceManager.markHighPriority(new ChunkCoordIntPair(entry.getLongKey()), entry.getIntValue())); this.chunkDistanceManager.delayDistanceManagerTick = false; this.world.getChunkProvider().tickDistanceManager(); }
/*      */   private boolean shouldSkipPrioritization(ChunkCoordIntPair coord) { if (this.playerViewDistanceNoTickMap.getObjectsInRange(coord.pair()) == null) return true;  PlayerChunk chunk = getUpdatingChunk(coord.pair()); return (chunk != null && chunk.isFullChunkReady()); }
/*      */   public void updatePlayerMobTypeMap(Entity entity) { if (!this.world.paperConfig.perPlayerMobSpawns) return;  int chunkX = (int)Math.floor(entity.locX()) >> 4; int chunkZ = (int)Math.floor(entity.locZ()) >> 4; int index = entity.getEntityType().getEnumCreatureType().ordinal(); for (EntityPlayer player : this.playerMobDistanceMap.getPlayersInRange(chunkX, chunkZ)) player.mobCounts[index] = player.mobCounts[index] + 1;  }
/*      */   public int getMobCountNear(EntityPlayer entityPlayer, EnumCreatureType enumCreatureType) { return entityPlayer.mobCounts[enumCreatureType.ordinal()]; }
/*      */   private static double getDistanceSquaredFromChunk(ChunkCoordIntPair chunkPos, Entity entity) { return a(chunkPos, entity); }
/*      */   private static double a(ChunkCoordIntPair chunkcoordintpair, Entity entity) { double d0 = (chunkcoordintpair.x * 16 + 8); double d1 = (chunkcoordintpair.z * 16 + 8); double d2 = d0 - entity.locX(); double d3 = d1 - entity.locZ(); return d2 * d2 + d3 * d3; }
/*  297 */   public PlayerChunkMap(WorldServer worldserver, Convertable.ConversionSession convertable_conversionsession, DataFixer datafixer, DefinedStructureManager definedstructuremanager, Executor executor, IAsyncTaskHandler<Runnable> iasynctaskhandler, ILightAccess ilightaccess, ChunkGenerator chunkgenerator, WorldLoadListener worldloadlistener, Supplier<WorldPersistentData> supplier, int i, boolean flag) { super(new File(convertable_conversionsession.a(worldserver.getDimensionKey()), "region"), datafixer, flag);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  638 */     this.isIterating = false;
/*  639 */     this.hasPendingVisibleUpdate = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  826 */     this.autoSaveQueue = new ObjectRBTreeSet((playerchunk1, playerchunk2) -> {
/*      */           int timeCompare = Long.compare(playerchunk1.lastAutoSaveTime, playerchunk2.lastAutoSaveTime);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           return (timeCompare != 0) ? timeCompare : Long.compare(MCUtil.getCoordinateKey(playerchunk1.location), MCUtil.getCoordinateKey(playerchunk2.location));
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1036 */     this.unloadingPlayerChunk = false; this.pendingUnload = new Long2ObjectLinkedOpenHashMap(); this.loadedChunks = (LongSet)new LongOpenHashSet(); this.unloadQueue = (LongSet)new LongOpenHashSet(); this.u = new AtomicInteger(); this.playerMap = new PlayerMap(); this.trackedEntities = (Int2ObjectMap<EntityTracker>)new Int2ObjectOpenHashMap(); this.z = (Long2ByteMap)new Long2ByteOpenHashMap(); this.A = (Queue<Runnable>)new CachedSizeConcurrentLinkedQueue(); this.definedStructureManager = definedstructuremanager; this.w = convertable_conversionsession.a(worldserver.getDimensionKey()); this.world = worldserver; this.chunkGenerator = chunkgenerator; this.executor = iasynctaskhandler; this.mainInvokingExecutor = (run -> { if (MCUtil.isMainThread()) { run.run(); } else { iasynctaskhandler.execute(run); }  }); ThreadedMailbox<Runnable> threadedmailbox = ThreadedMailbox.a(executor, "worldgen"); iasynctaskhandler.getClass(); Objects.requireNonNull(iasynctaskhandler); Mailbox<Runnable> mailbox = Mailbox.a("main", iasynctaskhandler::a); this.worldLoadListener = worldloadlistener; ThreadedMailbox<Runnable> lightthreaded = ThreadedMailbox.a(this.lightThread = Executors.newSingleThreadExecutor(r -> { Thread thread = new Thread(r); thread.setName(((WorldDataServer)this.world.getWorldData()).getName() + " - Light"); thread.setDaemon(true); thread.setPriority(6); return thread; }), "light"), threadedmailbox1 = lightthreaded; this.p = new ChunkTaskQueueSorter((List<Mailbox<?>>)ImmutableList.of(threadedmailbox, mailbox, threadedmailbox1), executor, 2147483647); this.mailboxWorldGen = this.p.a(threadedmailbox, false); this.mailboxMain = this.p.a(mailbox, false); this.mailboxLight = this.p.a(lightthreaded, false); this.lightEngine = new LightEngineThreaded(ilightaccess, this, this.world.getDimensionManager().hasSkyLight(), threadedmailbox1, this.p.a(threadedmailbox1, false)); this.chunkDistanceManager = new a(executor, iasynctaskhandler); this.chunkDistanceManager.chunkMap = this; this.l = supplier; this.m = new VillagePlace(new File(this.w, "poi"), datafixer, flag, this.world); setViewDistance(i); this.playerMobDistanceMap = this.world.paperConfig.perPlayerMobSpawns ? new PlayerMobDistanceMap() : null; this.playerEntityTrackerTrackMaps = new PlayerAreaMap[TRACKING_RANGE_TYPES.length]; this.entityTrackerTrackRanges = new int[TRACKING_RANGE_TYPES.length]; SpigotWorldConfig spigotWorldConfig = this.world.spigotConfig; for (int ordinal = 0, len = TRACKING_RANGE_TYPES.length; ordinal < len; ordinal++) { TrackingRange.TrackingRangeType trackingRangeType = TRACKING_RANGE_TYPES[ordinal]; switch (trackingRangeType) { case SAFE_REGEN: configuredSpigotValue = spigotWorldConfig.playerTrackingRange; break;case DELETE: configuredSpigotValue = spigotWorldConfig.animalTrackingRange; break;case null: configuredSpigotValue = spigotWorldConfig.monsterTrackingRange; break;case null: configuredSpigotValue = spigotWorldConfig.miscTrackingRange; break;case null: configuredSpigotValue = spigotWorldConfig.otherTrackingRange; break;case null: configuredSpigotValue = EntityTypes.ENDER_DRAGON.getChunkRange() * 16; break;default: throw new IllegalStateException("Missing case for enum " + trackingRangeType); }  int configuredSpigotValue = convertSpigotRangeToVanilla(configuredSpigotValue); int trackRange = (configuredSpigotValue >>> 4) + (((configuredSpigotValue & 0xF) != 0) ? 1 : 0); this.entityTrackerTrackRanges[ordinal] = trackRange; this.playerEntityTrackerTrackMaps[ordinal] = new PlayerAreaMap(this.pooledLinkedPlayerHashSets); }  this.playerChunkTickRangeMap = new PlayerAreaMap(this.pooledLinkedPlayerHashSets, (player, rangeX, rangeZ, currPosX, currPosZ, prevPosX, prevPosZ, newState) -> { PlayerChunk playerChunk = getUpdatingChunk(MCUtil.getCoordinateKey(rangeX, rangeZ)); if (playerChunk != null) playerChunk.playersInChunkTickRange = newState;  }(player, rangeX, rangeZ, currPosX, currPosZ, prevPosX, prevPosZ, newState) -> { PlayerChunk playerChunk = getUpdatingChunk(MCUtil.getCoordinateKey(rangeX, rangeZ)); if (playerChunk != null) playerChunk.playersInChunkTickRange = newState;  }); this.playerMobSpawnMap = new PlayerAreaMap(this.pooledLinkedPlayerHashSets, (player, rangeX, rangeZ, currPosX, currPosZ, prevPosX, prevPosZ, newState) -> { PlayerChunk playerChunk = getUpdatingChunk(MCUtil.getCoordinateKey(rangeX, rangeZ)); if (playerChunk != null) playerChunk.playersInMobSpawnRange = newState;  }(player, rangeX, rangeZ, currPosX, currPosZ, prevPosX, prevPosZ, newState) -> { PlayerChunk playerChunk = getUpdatingChunk(MCUtil.getCoordinateKey(rangeX, rangeZ)); if (playerChunk != null) playerChunk.playersInMobSpawnRange = newState;  }); setNoTickViewDistance(this.world.paperConfig.noTickViewDistance); this.playerViewDistanceTickMap = new PlayerAreaMap(this.pooledLinkedPlayerHashSets, (player, rangeX, rangeZ, currPosX, currPosZ, prevPosX, prevPosZ, newState) -> { checkHighPriorityChunks(player); if (newState.size() != 1) return;  Chunk chunk = this.world.getChunkProvider().getChunkAtIfLoadedMainThreadNoCache(rangeX, rangeZ); if (chunk == null || !chunk.areNeighboursLoaded(2)) return;  ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(rangeX, rangeZ); this.world.getChunkProvider().addTicketAtLevel(TicketType.PLAYER, chunkPos, 31, chunkPos); }(player, rangeX, rangeZ, currPosX, currPosZ, prevPosX, prevPosZ, newState) -> { if (newState != null) return;  ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(rangeX, rangeZ); this.world.getChunkProvider().removeTicketAtLevel(TicketType.PLAYER, chunkPos, 31, chunkPos); this.world.getChunkProvider().clearPriorityTickets(chunkPos); }(player, prevPos, newPos) -> { player.lastHighPriorityChecked = -1L; checkHighPriorityChunks(player); }); this.playerViewDistanceNoTickMap = new PlayerAreaMap(this.pooledLinkedPlayerHashSets); this.playerViewDistanceBroadcastMap = new PlayerAreaMap(this.pooledLinkedPlayerHashSets, (player, rangeX, rangeZ, currPosX, currPosZ, prevPosX, prevPosZ, newState) -> { if (player.needsChunkCenterUpdate) { player.needsChunkCenterUpdate = false; player.playerConnection.sendPacket(new PacketPlayOutViewCentre(currPosX, currPosZ)); }  sendChunk(player, new ChunkCoordIntPair(rangeX, rangeZ), (Packet<?>[])new Packet[2], false, true); }(player, rangeX, rangeZ, currPosX, currPosZ, prevPosX, prevPosZ, newState) -> sendChunk(player, new ChunkCoordIntPair(rangeX, rangeZ), (Packet<?>[])null, true, false)); this.dataRegionManager = null; } private static int b(ChunkCoordIntPair chunkcoordintpair, EntityPlayer entityplayer, boolean flag) { int i; int j; if (flag) { SectionPosition sectionposition = entityplayer.O(); i = sectionposition.a(); j = sectionposition.c(); } else { i = MathHelper.floor(entityplayer.locX() / 16.0D); j = MathHelper.floor(entityplayer.locZ() / 16.0D); }  return a(chunkcoordintpair, i, j); } private static int a(ChunkCoordIntPair chunkcoordintpair, int i, int j) { int k = chunkcoordintpair.x - i; int l = chunkcoordintpair.z - j; return Math.max(Math.abs(k), Math.abs(l)); } protected LightEngineThreaded a() { return this.lightEngine; }
/*      */   @Nullable public PlayerChunk getUpdatingChunk(long i) { return (PlayerChunk)this.updatingChunks.get(i); }
/*      */   private static final boolean DEBUG_ASYNC_VISIBLE_CHUNKS = Boolean.getBoolean("paper.debug-async-visible-chunks"); private boolean isIterating; private boolean hasPendingVisibleUpdate; final ObjectRBTreeSet<PlayerChunk> autoSaveQueue;
/* 1039 */   private void a(long i, PlayerChunk playerchunk) { CompletableFuture<IChunkAccess> completablefuture = playerchunk.getChunkSave();
/* 1040 */     Consumer<IChunkAccess> consumer = ichunkaccess -> {
/*      */         CompletableFuture<IChunkAccess> completablefuture1 = playerchunk.getChunkSave();
/*      */ 
/*      */         
/*      */         if (completablefuture1 != completablefuture) {
/*      */           a(i, playerchunk);
/*      */         } else {
/*      */           AsyncCatcher.catchOp("playerchunk unload");
/*      */ 
/*      */           
/*      */           boolean unloadingBefore = this.unloadingPlayerChunk;
/*      */ 
/*      */           
/*      */           this.unloadingPlayerChunk = true;
/*      */           
/*      */           try {
/*      */             boolean removed;
/*      */             
/*      */             if ((removed = this.pendingUnload.remove(i, playerchunk)) && ichunkaccess != null) {
/*      */               if (ichunkaccess instanceof Chunk) {
/*      */                 ((Chunk)ichunkaccess).setLoaded(false);
/*      */               }
/*      */               
/*      */               if (this.loadedChunks.remove(i) && ichunkaccess instanceof Chunk) {
/*      */                 Chunk chunk = (Chunk)ichunkaccess;
/*      */                 
/*      */                 this.world.unloadChunk(chunk);
/*      */               } 
/*      */               
/*      */               this.autoSaveQueue.remove(playerchunk);
/*      */               
/*      */               try {
/*      */                 asyncSave(ichunkaccess);
/* 1073 */               } catch (Throwable ex) {
/*      */                 LOGGER.fatal("Failed to prepare async save, attempting synchronous save", ex);
/*      */                 
/*      */                 saveChunk(ichunkaccess);
/*      */               } 
/*      */               this.lightEngine.a(ichunkaccess.getPos());
/*      */               this.lightEngine.queueUpdate();
/*      */               this.worldLoadListener.a(ichunkaccess.getPos(), (ChunkStatus)null);
/*      */             } 
/*      */           } finally {
/*      */             this.unloadingPlayerChunk = unloadingBefore;
/*      */           } 
/*      */         } 
/*      */       };
/* 1087 */     Queue<Runnable> queue = this.A;
/*      */     
/* 1089 */     this.A.getClass();
/* 1090 */     Objects.requireNonNull(queue); completablefuture.thenAcceptAsync(consumer, queue::add).whenComplete((ovoid, throwable) -> { if (throwable != null) LOGGER.error("Failed to save chunk " + playerchunk.i(), throwable);  }); } private static final double UNLOAD_QUEUE_RESIZE_FACTOR = 0.9D; boolean unloadingPlayerChunk; public void forEachVisibleChunk(Consumer<PlayerChunk> consumer) { AsyncCatcher.catchOp("forEachVisibleChunk"); boolean prev = this.isIterating; this.isIterating = true; try { for (ObjectIterator<PlayerChunk> objectIterator = this.visibleChunks.values().iterator(); objectIterator.hasNext(); ) { PlayerChunk value = objectIterator.next(); consumer.accept(value); }  } finally { this.isIterating = prev; if (!this.isIterating && this.hasPendingVisibleUpdate) { ((ProtectedVisibleChunksMap)this.visibleChunks).copyFrom(this.pendingVisibleChunks); this.pendingVisibleChunks.clear(); this.hasPendingVisibleUpdate = false; }  }  }
/*      */   public Long2ObjectLinkedOpenHashMap<PlayerChunk> getVisibleChunks() { if (Thread.currentThread() == this.world.serverThread) return this.visibleChunks;  synchronized (this.visibleChunks) { if (DEBUG_ASYNC_VISIBLE_CHUNKS)
/*      */         (new Throwable("Async getVisibleChunks")).printStackTrace();  if (this.visibleChunksClone == null)
/*      */         this.visibleChunksClone = this.hasPendingVisibleUpdate ? this.pendingVisibleChunks.clone() : ((ProtectedVisibleChunksMap)this.visibleChunks).clone();  return (Long2ObjectLinkedOpenHashMap<PlayerChunk>)this.visibleChunksClone; }  }
/*      */   @Nullable public PlayerChunk getVisibleChunk(long i) { if (Thread.currentThread() != this.world.serverThread)
/*      */       synchronized (this.visibleChunks) { return this.hasPendingVisibleUpdate ? (PlayerChunk)this.pendingVisibleChunks.get(i) : ((ProtectedVisibleChunksMap)this.visibleChunks).safeGet(i); }   return this.hasPendingVisibleUpdate ? (PlayerChunk)this.pendingVisibleChunks.get(i) : ((ProtectedVisibleChunksMap)this.visibleChunks).safeGet(i); }
/*      */   protected final IntSupplier getPrioritySupplier(long i) { return c(i); }
/*      */   protected IntSupplier c(long i) { return () -> { PlayerChunk playerchunk = getVisibleChunk(i); return (playerchunk == null) ? (ChunkTaskQueue.a - 1) : Math.min(playerchunk.k(), ChunkTaskQueue.a - 1); }; }
/*      */   public final int getEffectiveViewDistance() { return this.viewDistance - 1; }
/* 1099 */   protected boolean b() { TickThread.softEnsureTickThread("Cannot update visibleChunks off of the main thread");
/* 1100 */     if (!this.updatingChunksModified) {
/* 1101 */       return false;
/*      */     }
/*      */     
/* 1104 */     synchronized (this.visibleChunks) {
/* 1105 */       if (this.isIterating) {
/* 1106 */         this.hasPendingVisibleUpdate = true;
/* 1107 */         this.pendingVisibleChunks.copyFrom((Long2ObjectLinkedOpenHashMapFastCopy)this.updatingChunks);
/*      */       } else {
/* 1109 */         this.hasPendingVisibleUpdate = false;
/* 1110 */         this.pendingVisibleChunks.clear();
/* 1111 */         ((ProtectedVisibleChunksMap)this.visibleChunks).copyFrom((Long2ObjectLinkedOpenHashMapFastCopy)this.updatingChunks);
/* 1112 */         this.visibleChunksClone = null;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1117 */     this.updatingChunksModified = false;
/* 1118 */     return true; } private CompletableFuture<Either<List<IChunkAccess>, PlayerChunk.Failure>> a(ChunkCoordIntPair chunkcoordintpair, final int i, IntFunction<ChunkStatus> intfunction) { List<CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>>> list = Lists.newArrayList(); final int j = chunkcoordintpair.x; final int k = chunkcoordintpair.z; PlayerChunk requestingNeighbor = getUpdatingChunk(chunkcoordintpair.pair()); for (int l = -i; l <= i; l++) { for (int i1 = -i; i1 <= i; i1++) { int j1 = Math.max(Math.abs(i1), Math.abs(l)); final ChunkCoordIntPair chunkcoordintpair1 = new ChunkCoordIntPair(j + i1, k + l); long k1 = chunkcoordintpair1.pair(); PlayerChunk playerchunk = getUpdatingChunk(k1); if (playerchunk == null) return CompletableFuture.completedFuture(Either.right(new PlayerChunk.Failure() { public String toString() { return "Unloaded " + chunkcoordintpair1.toString(); } }
/*      */               ));  ChunkStatus chunkstatus = intfunction.apply(j1); CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> completablefuture = playerchunk.a(chunkstatus, this); if (requestingNeighbor != null && requestingNeighbor != playerchunk && !completablefuture.isDone()) { requestingNeighbor.onNeighborRequest(playerchunk, chunkstatus); completablefuture.thenAccept(either -> requestingNeighbor.onNeighborDone(playerchunk, chunkstatus, either.left().orElse(null))); }  list.add(completablefuture); }  }  CompletableFuture<List<Either<IChunkAccess, PlayerChunk.Failure>>> completablefuture1 = SystemUtils.b(list); return completablefuture1.thenApply(list1 -> { List<IChunkAccess> list2 = Lists.newArrayList(); int cnt = 0; Iterator<Either<IChunkAccess, PlayerChunk.Failure>> iterator = list1.iterator(); while (iterator.hasNext()) { final int l1 = cnt; final Either<IChunkAccess, PlayerChunk.Failure> either = iterator.next(); Optional<IChunkAccess> optional = either.left(); if (!optional.isPresent()) return Either.right(new PlayerChunk.Failure() { public String toString() { return "Unloaded " + new ChunkCoordIntPair(j + l1 % (i * 2 + 1), k + l1 / (i * 2 + 1)) + " " + ((PlayerChunk.Failure)either.right().get()).toString(); } }
/*      */                 );  list2.add(optional.get()); cnt++; }  return Either.left(list2); }); } public CompletableFuture<Either<Chunk, PlayerChunk.Failure>> b(ChunkCoordIntPair chunkcoordintpair) { return a(chunkcoordintpair, 2, i -> ChunkStatus.FULL).thenApplyAsync(either -> either.mapLeft(()), this.mainInvokingExecutor); }
/*      */   @Nullable private PlayerChunk a(long i, int j, @Nullable PlayerChunk playerchunk, int k) { TickThread.softEnsureTickThread("Chunk holder update"); if (this.unloadingPlayerChunk) { MinecraftServer.LOGGER.fatal("Cannot tick distance manager while unloading playerchunks", new Throwable()); throw new IllegalStateException("Cannot tick distance manager while unloading playerchunks"); }  if (k > GOLDEN_TICKET && j > GOLDEN_TICKET) return playerchunk;  if (playerchunk != null) { playerchunk.a(j); playerchunk.updateRanges(); }  if (playerchunk != null) if (j > GOLDEN_TICKET) { this.unloadQueue.add(i); } else { this.unloadQueue.remove(i); }   if (j <= GOLDEN_TICKET && playerchunk == null) { playerchunk = (PlayerChunk)this.pendingUnload.remove(i); if (playerchunk != null) { playerchunk.a(j); } else { playerchunk = new PlayerChunk(new ChunkCoordIntPair(i), j, this.lightEngine, this.p, this); }  this.updatingChunks.put(i, playerchunk); this.updatingChunksModified = true; }  return playerchunk; }
/*      */   public void close() throws IOException { try { this.lightThread.shutdown(); this.p.close(); this.world.asyncChunkTaskManager.close(true); this.m.close(); } finally { super.close(); }  }
/* 1123 */   public CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> a(PlayerChunk playerchunk, ChunkStatus chunkstatus) { ChunkCoordIntPair chunkcoordintpair = playerchunk.i();
/*      */     
/* 1125 */     if (chunkstatus == ChunkStatus.EMPTY) {
/* 1126 */       return f(chunkcoordintpair);
/*      */     }
/* 1128 */     CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> completablefuture = playerchunk.a(chunkstatus.e(), this);
/*      */     
/* 1130 */     return completablefuture.thenComposeAsync(either -> { Optional<IChunkAccess> optional = either.left(); if (!optional.isPresent()) return CompletableFuture.completedFuture(either);  if (chunkstatus == ChunkStatus.LIGHT) this.chunkDistanceManager.a(TicketType.LIGHT, chunkcoordintpair, 33 + ChunkStatus.a(ChunkStatus.FEATURES), chunkcoordintpair);  IChunkAccess ichunkaccess = optional.get(); if (ichunkaccess.getChunkStatus().b(chunkstatus)) { CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> completablefuture1; if (chunkstatus == ChunkStatus.LIGHT) { completablefuture1 = b(playerchunk, chunkstatus); } else { completablefuture1 = chunkstatus.a(this.world, this.definedStructureManager, this.lightEngine, (), ichunkaccess); }  this.worldLoadListener.a(chunkcoordintpair, chunkstatus); return completablefuture1; }  return b(playerchunk, chunkstatus); }this.mainInvokingExecutor)
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1159 */       .thenComposeAsync(CompletableFuture::completedFuture, this.mainInvokingExecutor); }
/*      */   protected void saveIncrementally() { int savedThisTick = 0; List<PlayerChunk> reschedule = new ArrayList<>(this.world.paperConfig.maxAutoSaveChunksPerTick); long currentTick = this.world.getTime(); long maxSaveTime = currentTick - this.world.paperConfig.autoSavePeriod; for (ObjectBidirectionalIterator<PlayerChunk> objectBidirectionalIterator = this.autoSaveQueue.iterator(); objectBidirectionalIterator.hasNext(); ) { PlayerChunk playerchunk = objectBidirectionalIterator.next(); if (playerchunk.lastAutoSaveTime > maxSaveTime)
/*      */         break;  objectBidirectionalIterator.remove(); IChunkAccess ichunkaccess = playerchunk.getChunkSave().getNow(null); if (ichunkaccess instanceof Chunk) { boolean shouldSave = (((Chunk)ichunkaccess).lastSaved <= maxSaveTime); if (shouldSave && saveChunk(ichunkaccess)) { savedThisTick++; if (!playerchunk.setHasBeenLoaded()) { playerchunk.inactiveTimeStart = currentTick; if (savedThisTick >= this.world.paperConfig.maxAutoSaveChunksPerTick)
/*      */               break;  continue; }  }  }  reschedule.add(playerchunk); if (savedThisTick >= this.world.paperConfig.maxAutoSaveChunksPerTick)
/*      */         break;  }  for (int i = 0, len = reschedule.size(); i < len; i++) { PlayerChunk playerchunk = reschedule.get(i); playerchunk.lastAutoSaveTime = this.world.getTime(); this.autoSaveQueue.add(playerchunk); }  }
/*      */   protected void save(boolean flag) { Long2ObjectLinkedOpenHashMap<PlayerChunk> visibleChunks = getVisibleChunks(); if (flag) { List<PlayerChunk> list = (List<PlayerChunk>)visibleChunks.values().stream().filter(PlayerChunk::hasBeenLoaded).peek(PlayerChunk::m).collect(Collectors.toList()); MutableBoolean mutableboolean = new MutableBoolean(); do { boolean isShuttingDown = this.world.getMinecraftServer().hasStopped(); mutableboolean.setFalse(); list.stream().map(playerchunk -> { CompletableFuture<IChunkAccess> completablefuture; do { completablefuture = playerchunk.getChunkSave(); Objects.requireNonNull(completablefuture); this.executor.awaitTasks(completablefuture::isDone); } while (completablefuture != playerchunk.getChunkSave()); return completablefuture.join(); }).filter(ichunkaccess -> (ichunkaccess instanceof ProtoChunkExtension || ichunkaccess instanceof Chunk)).filter(this::saveChunk).forEach(ichunkaccess -> mutableboolean.setTrue()); } while (mutableboolean.isTrue()); b(() -> true); this.world.asyncChunkTaskManager.flush(); LOGGER.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", this.w.getName()); } else { visibleChunks.values().stream().filter(PlayerChunk::hasBeenLoaded).forEach(playerchunk -> { IChunkAccess ichunkaccess = playerchunk.getChunkSave().getNow(null); if (ichunkaccess instanceof ProtoChunkExtension || ichunkaccess instanceof Chunk) { saveChunk(ichunkaccess); playerchunk.m(); }  }); }
/* 1165 */      } private CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> f(ChunkCoordIntPair chunkcoordintpair) { BiFunction<ChunkRegionLoader.InProgressChunkHolder, Throwable, Either<IChunkAccess, PlayerChunk.Failure>> syncLoadComplete = (chunkHolder, ioThrowable) -> {
/*      */         try {
/*      */           Timing ignored = this.world.timings.chunkLoad.startTimingIfSync(); 
/*      */           try { this.world.getMethodProfiler().c("chunkLoad"); if (ioThrowable != null)
/*      */               SneakyThrow.sneaky(ioThrowable);  getVillagePlace().loadInData(chunkcoordintpair, chunkHolder.poiData); chunkHolder.tasks.forEach(Runnable::run); if (chunkHolder.protoChunk != null) {
/*      */               Timing ignored2 = this.world.timings.chunkLoadLevelTimer.startTimingIfSync(); 
/*      */               try { ProtoChunk protochunk = chunkHolder.protoChunk; protochunk.setLastSaved(this.world.getTime()); a(chunkcoordintpair, protochunk.getChunkStatus().getType()); Either either = Either.left(protochunk);
/*      */                 if (ignored2 != null)
/*      */                   ignored2.close(); 
/*      */                 if (ignored != null)
/*      */                   ignored.close(); 
/*      */                 return either; }
/* 1177 */               catch (Throwable throwable) { if (ignored2 != null) try { ignored2.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */                     throw throwable; }
/*      */             
/*      */             }  if (ignored != null)
/*      */               ignored.close();  }
/*      */           catch (Throwable throwable) { if (ignored != null)
/*      */               try {
/*      */                 ignored.close();
/*      */               } catch (Throwable throwable1) {
/*      */                 throwable.addSuppressed(throwable1);
/*      */               }   throw throwable; }
/*      */         
/* 1189 */         } catch (ReportedException reportedexception) {
/*      */           Throwable throwable = reportedexception.getCause();
/*      */           
/*      */           if (!(throwable instanceof IOException)) {
/*      */             g(chunkcoordintpair);
/*      */             
/*      */             throw reportedexception;
/*      */           } 
/*      */           LOGGER.error("Couldn't load chunk {}", chunkcoordintpair, throwable);
/* 1198 */         } catch (Exception exception) {
/*      */           LOGGER.error("Couldn't load chunk {}", chunkcoordintpair, exception);
/*      */         } 
/*      */         
/*      */         g(chunkcoordintpair);
/*      */         
/*      */         return Either.left(new ProtoChunk(chunkcoordintpair, ChunkConverter.a, this.world));
/*      */       };
/* 1206 */     CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> ret = new CompletableFuture<>();
/*      */     
/* 1208 */     Consumer<ChunkRegionLoader.InProgressChunkHolder> chunkHolderConsumer = holder -> ChunkTaskManager.queueChunkWaitTask(());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1219 */     CompletableFuture<NBTTagCompound> chunkSaveFuture = this.world.asyncChunkTaskManager.getChunkSaveFuture(chunkcoordintpair.x, chunkcoordintpair.z);
/* 1220 */     PlayerChunk playerChunk = getUpdatingChunk(chunkcoordintpair.pair());
/* 1221 */     int chunkPriority = (playerChunk != null) ? playerChunk.getCurrentPriority() : 33;
/* 1222 */     int priority = 3;
/*      */     
/* 1224 */     if (chunkPriority <= 10) {
/* 1225 */       priority = 0;
/* 1226 */     } else if (chunkPriority <= 20) {
/* 1227 */       priority = 2;
/*      */     } 
/* 1229 */     boolean isHighestPriority = (priority == 0);
/* 1230 */     if (chunkSaveFuture != null) {
/* 1231 */       this.world.asyncChunkTaskManager.scheduleChunkLoad(chunkcoordintpair.x, chunkcoordintpair.z, priority, chunkHolderConsumer, isHighestPriority, chunkSaveFuture);
/*      */     } else {
/* 1233 */       this.world.asyncChunkTaskManager.scheduleChunkLoad(chunkcoordintpair.x, chunkcoordintpair.z, priority, chunkHolderConsumer, isHighestPriority);
/*      */     } 
/* 1235 */     this.world.asyncChunkTaskManager.raisePriority(chunkcoordintpair.x, chunkcoordintpair.z, priority);
/* 1236 */     return ret; }
/*      */   protected void unloadChunks(BooleanSupplier booleansupplier) { GameProfilerFiller gameprofilerfiller = this.world.getMethodProfiler(); Timing ignored = this.world.timings.poiUnload.startTiming(); try { gameprofilerfiller.enter("poi"); this.m.a(booleansupplier); if (ignored != null) ignored.close();  } catch (Throwable throwable) { if (ignored != null) try { ignored.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  gameprofilerfiller.exitEnter("chunk_unload"); if (!this.world.isSavingDisabled()) { ignored = this.world.timings.chunkUnload.startTiming(); try { b(booleansupplier); if (ignored != null) ignored.close();  } catch (Throwable throwable) { if (ignored != null) try { ignored.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  }  gameprofilerfiller.exit(); }
/*      */   private void b(BooleanSupplier booleansupplier) { LongIterator longiterator = this.unloadQueue.iterator(); SlackActivityAccountant activityAccountant = (this.world.getMinecraftServer()).slackActivityAccountant; activityAccountant.startActivity(0.5D); int targetSize = Math.min(this.unloadQueue.size() - 100, (int)(this.unloadQueue.size() * 0.9D)); while (longiterator.hasNext()) { long j = longiterator.nextLong(); longiterator.remove(); PlayerChunk playerchunk = (PlayerChunk)this.updatingChunks.remove(j); if (playerchunk != null) { this.pendingUnload.put(j, playerchunk); this.updatingChunksModified = true; a(j, playerchunk); if (!booleansupplier.getAsBoolean() && this.unloadQueue.size() <= targetSize && activityAccountant.activityTimeIsExhausted()) break;  }  }  activityAccountant.endActivity(); int queueTarget = Math.min(getUnloadQueueTasks().size() - 100, (int)(getUnloadQueueTasks().size() * 0.9D)); Runnable runnable; while ((booleansupplier.getAsBoolean() || getUnloadQueueTasks().size() > queueTarget) && (runnable = getUnloadQueueTasks().poll()) != null) runnable.run();  }
/*      */   private void asyncSave(IChunkAccess chunk) { NBTTagCompound poiData; ChunkRegionLoader.AsyncSaveData asyncSaveData; ChunkCoordIntPair chunkPos = chunk.getPos(); Timing ignored = this.world.timings.chunkUnloadPOISerialization.startTiming(); try { poiData = getVillagePlace().getData(chunk.getPos()); if (ignored != null) ignored.close();  } catch (Throwable throwable) { if (ignored != null) try { ignored.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  PaperFileIOThread.Holder.INSTANCE.scheduleSave(this.world, chunkPos.x, chunkPos.z, poiData, null, 3); if (!chunk.isNeedsSaving()) return;  ChunkStatus chunkstatus = chunk.getChunkStatus(); if (chunkstatus.getType() != ChunkStatus.Type.LEVELCHUNK) { Timing ignored1 = this.world.timings.chunkSaveOverwriteCheck.startTiming(); try { ChunkStatus statusOnDisk = getChunkStatusOnDisk(chunkPos); if (statusOnDisk != null && statusOnDisk.getType() == ChunkStatus.Type.LEVELCHUNK) { if (ignored1 != null) ignored1.close();  return; }  if (chunkstatus == ChunkStatus.EMPTY && chunk.h().values().stream().noneMatch(StructureStart::e)) { if (ignored1 != null) ignored1.close();  return; }  if (ignored1 != null) ignored1.close();  } catch (IOException ex) { ex.printStackTrace(); if (ignored1 != null) ignored1.close();  return; } catch (Throwable throwable) { if (ignored1 != null) try { ignored1.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  }  Timing timing1 = this.world.timings.chunkUnloadPrepareSave.startTiming(); try { asyncSaveData = ChunkRegionLoader.getAsyncSaveData(this.world, chunk); if (timing1 != null)
/*      */         timing1.close();  } catch (Throwable throwable) { if (timing1 != null)
/* 1241 */         try { timing1.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  this.world.asyncChunkTaskManager.scheduleChunkSave(chunkPos.x, chunkPos.z, 3, asyncSaveData, chunk); chunk.setLastSaved(this.world.getTime()); chunk.setNeedsSaving(false); } private void g(ChunkCoordIntPair chunkcoordintpair) { this.z.put(chunkcoordintpair.pair(), (byte)-1); }
/*      */ 
/*      */   
/*      */   private byte a(ChunkCoordIntPair chunkcoordintpair, ChunkStatus.Type chunkstatus_type) {
/* 1245 */     return this.z.put(chunkcoordintpair.pair(), (byte)((chunkstatus_type == ChunkStatus.Type.PROTOCHUNK) ? -1 : 1));
/*      */   }
/*      */   
/*      */   private CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> b(PlayerChunk playerchunk, ChunkStatus chunkstatus) {
/* 1249 */     ChunkCoordIntPair chunkcoordintpair = playerchunk.i();
/* 1250 */     CompletableFuture<Either<List<IChunkAccess>, PlayerChunk.Failure>> completablefuture = a(chunkcoordintpair, chunkstatus.f(), i -> a(chunkstatus, i));
/*      */ 
/*      */ 
/*      */     
/* 1254 */     this.world.getMethodProfiler().c(() -> "chunkGenerate " + chunkstatus.d());
/*      */ 
/*      */     
/* 1257 */     return completablefuture.thenComposeAsync(either -> (CompletionStage)either.map((), ()), runnable -> {
/*      */           if (playerchunk.canAdvanceStatus()) {
/*      */             this.mainInvokingExecutor.execute(runnable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             return;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           this.mailboxWorldGen.a(ChunkTaskQueueSorter.a(playerchunk, runnable));
/* 1287 */         }).thenComposeAsync(either -> CompletableFuture.completedFuture(either), this.mainInvokingExecutor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void c(ChunkCoordIntPair chunkcoordintpair) {
/* 1294 */     this.executor.a(SystemUtils.a(() -> this.chunkDistanceManager.b(TicketType.LIGHT, chunkcoordintpair, 33 + ChunkStatus.a(ChunkStatus.FEATURES), chunkcoordintpair), () -> "release light ticket " + chunkcoordintpair));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ChunkStatus a(ChunkStatus chunkstatus, int i) {
/*      */     ChunkStatus chunkstatus1;
/* 1304 */     if (i == 0) {
/* 1305 */       chunkstatus1 = chunkstatus.e();
/*      */     } else {
/* 1307 */       chunkstatus1 = ChunkStatus.a(ChunkStatus.a(chunkstatus) + i);
/*      */     } 
/*      */     
/* 1310 */     return chunkstatus1;
/*      */   }
/*      */   
/*      */   private CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> c(PlayerChunk playerchunk) {
/* 1314 */     CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> completablefuture = playerchunk.getStatusFutureUnchecked(ChunkStatus.FULL.e());
/*      */     
/* 1316 */     return completablefuture.thenApplyAsync(either -> {
/*      */           ChunkStatus chunkstatus = PlayerChunk.getChunkStatus(playerchunk.getTicketLevel());
/*      */           return !chunkstatus.b(ChunkStatus.FULL) ? PlayerChunk.UNLOADED_CHUNK_ACCESS : either.mapLeft(());
/*      */         }runnable -> this.mailboxMain.a(ChunkTaskQueueSorter.a(runnable, playerchunk.i().pair(), ())));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void loadChunk(Chunk chunk) {
/* 1350 */     ChunkCoordIntPair chunkcoordintpair = chunk.getPos();
/*      */     
/* 1352 */     if (this.loadedChunks.add(chunkcoordintpair.pair())) {
/* 1353 */       chunk.setLoaded(true);
/* 1354 */       this.world.a(chunk.getTileEntities().values());
/* 1355 */       List<Entity> list = null;
/* 1356 */       List[] arrayOfList = (List[])chunk.getEntitySlices();
/* 1357 */       int i = arrayOfList.length;
/*      */       
/* 1359 */       for (int j = 0; j < i; j++) {
/* 1360 */         List<Entity> entityslice = arrayOfList[j];
/* 1361 */         Iterator<Entity> iterator = entityslice.iterator();
/*      */         
/* 1363 */         while (iterator.hasNext()) {
/* 1364 */           Entity entity = iterator.next();
/*      */           
/* 1366 */           boolean needsRemoval = false;
/* 1367 */           if (chunk.needsDecoration && !this.world.getServer().getServer().getSpawnNPCs() && entity instanceof NPC) {
/* 1368 */             entity.dead = true;
/* 1369 */             needsRemoval = true;
/*      */           } 
/*      */           
/* 1372 */           checkDupeUUID(entity);
/* 1373 */           if (!(entity instanceof EntityHuman) && (entity.dead || !this.world.addEntityChunk(entity))) {
/* 1374 */             if (list == null) {
/* 1375 */               list = Lists.newArrayList((Object[])new Entity[] { entity }); continue;
/*      */             } 
/* 1377 */             list.add(entity);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1383 */       if (list != null) {
/* 1384 */         Objects.requireNonNull(chunk); list.forEach(chunk::b);
/*      */       } 
/*      */     } 
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
/*      */   
/*      */   private void checkDupeUUID(Entity entity) {
/* 1406 */     PaperWorldConfig.DuplicateUUIDMode mode = this.world.paperConfig.duplicateUUIDMode;
/* 1407 */     if (mode != PaperWorldConfig.DuplicateUUIDMode.WARN && mode != PaperWorldConfig.DuplicateUUIDMode.DELETE && mode != PaperWorldConfig.DuplicateUUIDMode.SAFE_REGEN) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1412 */     Entity other = this.world.getEntity(entity.uniqueID);
/*      */     
/* 1414 */     if (mode == PaperWorldConfig.DuplicateUUIDMode.SAFE_REGEN && other != null && !other.dead && 
/* 1415 */       Objects.equals(other.getSaveID(), entity.getSaveID()) && entity
/* 1416 */       .getBukkitEntity().getLocation().distance(other.getBukkitEntity().getLocation()) < this.world.paperConfig.duplicateUUIDDeleteRange) {
/*      */       
/* 1418 */       if (World.DEBUG_ENTITIES) LOGGER.warn("[DUPE-UUID] Duplicate UUID found used by " + other + ", deleted entity " + entity + " because it was near the duplicate and likely an actual duplicate. See https://github.com/PaperMC/Paper/issues/1223 for discussion on what this is about."); 
/* 1419 */       entity.dead = true;
/*      */       return;
/*      */     } 
/* 1422 */     if (other != null && !other.dead) {
/* 1423 */       switch (mode) {
/*      */         case SAFE_REGEN:
/* 1425 */           entity.setUUID(UUID.randomUUID());
/* 1426 */           if (World.DEBUG_ENTITIES) LOGGER.warn("[DUPE-UUID] Duplicate UUID found used by " + other + ", regenerated UUID for " + entity + ". See https://github.com/PaperMC/Paper/issues/1223 for discussion on what this is about.");
/*      */           
/*      */           return;
/*      */         case DELETE:
/* 1430 */           if (World.DEBUG_ENTITIES) LOGGER.warn("[DUPE-UUID] Duplicate UUID found used by " + other + ", deleted entity " + entity + ". See https://github.com/PaperMC/Paper/issues/1223 for discussion on what this is about."); 
/* 1431 */           entity.dead = true;
/*      */           return;
/*      */       } 
/*      */       
/* 1435 */       if (World.DEBUG_ENTITIES) LOGGER.warn("[DUPE-UUID] Duplicate UUID found used by " + other + ", doing nothing to " + entity + ". See https://github.com/PaperMC/Paper/issues/1223 for discussion on what this is about.");
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public CompletableFuture<Either<Chunk, PlayerChunk.Failure>> a(PlayerChunk playerchunk) {
/* 1443 */     ChunkCoordIntPair chunkcoordintpair = playerchunk.i();
/* 1444 */     CompletableFuture<Either<List<IChunkAccess>, PlayerChunk.Failure>> completablefuture = a(chunkcoordintpair, 1, i -> ChunkStatus.FULL);
/*      */ 
/*      */     
/* 1447 */     CompletableFuture<Either<Chunk, PlayerChunk.Failure>> completablefuture1 = completablefuture.thenApplyAsync(either -> either.flatMap(()), runnable -> this.mailboxMain.a(ChunkTaskQueueSorter.a(playerchunk, ())));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1458 */     completablefuture1.thenAcceptAsync(either -> either.mapLeft(()), runnable -> this.mailboxMain.a(ChunkTaskQueueSorter.a(playerchunk, runnable)));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1467 */     return completablefuture1;
/*      */   }
/*      */   
/*      */   public CompletableFuture<Either<Chunk, PlayerChunk.Failure>> b(PlayerChunk playerchunk) {
/* 1471 */     return playerchunk.a(ChunkStatus.FULL, this).thenApplyAsync(either -> either.mapLeft(()), runnable -> this.mailboxMain.a(ChunkTaskQueueSorter.a(playerchunk, runnable)));
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
/*      */   public int c() {
/* 1484 */     return this.u.get();
/*      */   }
/*      */   
/*      */   public boolean saveChunk(IChunkAccess ichunkaccess) {
/* 1488 */     Timing ignored = this.world.timings.chunkSave.startTiming(); 
/* 1489 */     try { this.m.a(ichunkaccess.getPos());
/* 1490 */       if (!ichunkaccess.isNeedsSaving())
/* 1491 */       { boolean bool = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1531 */         if (ignored != null) ignored.close();  return bool; }  ichunkaccess.setLastSaved(this.world.getTime()); ichunkaccess.setNeedsSaving(false); ChunkCoordIntPair chunkcoordintpair = ichunkaccess.getPos(); try { NBTTagCompound nbttagcompound; ChunkStatus chunkstatus = ichunkaccess.getChunkStatus(); if (chunkstatus.getType() != ChunkStatus.Type.LEVELCHUNK) { Timing timing = this.world.timings.chunkSaveOverwriteCheck.startTiming(); try { if (h(chunkcoordintpair)) { boolean bool1 = false; if (timing != null) timing.close();  if (ignored != null) ignored.close();  return bool1; }  if (chunkstatus == ChunkStatus.EMPTY && ichunkaccess.h().values().stream().noneMatch(StructureStart::e)) { boolean bool1 = false; if (timing != null) timing.close();  if (ignored != null) ignored.close();  return bool1; }  if (timing != null) timing.close();  } catch (Throwable throwable) { if (timing != null) try { timing.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  }  this.world.getMethodProfiler().c("chunkSave"); Timing ignored1 = this.world.timings.chunkSaveDataSerialization.startTiming(); try { nbttagcompound = ChunkRegionLoader.saveChunk(this.world, ichunkaccess); if (ignored1 != null) ignored1.close();  } catch (Throwable throwable) { if (ignored1 != null) try { ignored1.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  PaperFileIOThread.Holder.INSTANCE.scheduleSave(this.world, chunkcoordintpair.x, chunkcoordintpair.z, null, nbttagcompound, 3); a(chunkcoordintpair, chunkstatus.getType()); boolean bool = true; if (ignored != null) ignored.close();  return bool; } catch (Exception exception) { LOGGER.error("Failed to save chunk {},{}", Integer.valueOf(chunkcoordintpair.x), Integer.valueOf(chunkcoordintpair.z), exception); ServerInternalException.reportInternalException(exception); boolean bool = false; if (ignored != null) ignored.close();  return bool; }  } catch (Throwable throwable) { if (ignored != null)
/*      */         try { ignored.close(); }
/*      */         catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */           throw throwable; }
/* 1535 */      } private boolean h(ChunkCoordIntPair chunkcoordintpair) { NBTTagCompound nbttagcompound; byte b0 = this.z.get(chunkcoordintpair.pair());
/*      */     
/* 1537 */     if (b0 != 0) {
/* 1538 */       return (b0 == 1);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1543 */       nbttagcompound = readChunkData(chunkcoordintpair);
/* 1544 */       if (nbttagcompound == null) {
/* 1545 */         g(chunkcoordintpair);
/* 1546 */         return false;
/*      */       } 
/* 1548 */     } catch (Exception exception) {
/* 1549 */       LOGGER.error("Failed to read chunk {}", chunkcoordintpair, exception);
/* 1550 */       g(chunkcoordintpair);
/* 1551 */       return false;
/*      */     } 
/*      */     
/* 1554 */     ChunkStatus.Type chunkstatus_type = ChunkRegionLoader.a(nbttagcompound);
/*      */     
/* 1556 */     return (a(chunkcoordintpair, chunkstatus_type) == 1); }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setViewDistance(int i) {
/* 1561 */     TickThread.softEnsureTickThread("Cannot update view distance off of the main thread");
/* 1562 */     int j = MathHelper.clamp(i + 1, 3, 33);
/*      */     
/* 1564 */     if (j != this.viewDistance) {
/* 1565 */       int k = this.viewDistance;
/*      */       
/* 1567 */       this.viewDistance = j;
/* 1568 */       setNoTickViewDistance(getRawNoTickViewDistance());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setNoTickViewDistance(int viewDistance) {
/* 1575 */     TickThread.softEnsureTickThread("Cannot update view distance off of the main thread");
/* 1576 */     viewDistance = (viewDistance == -1) ? -1 : MathHelper.clamp(viewDistance, 2, 32);
/*      */     
/* 1578 */     this.noTickViewDistance = viewDistance;
/* 1579 */     int loadViewDistance = getLoadViewDistance();
/* 1580 */     this.chunkDistanceManager.setNoTickViewDistance(loadViewDistance + 2 + 2);
/*      */     
/* 1582 */     if (this.world != null && this.world.players != null) {
/* 1583 */       for (EntityPlayer player : this.world.players) {
/* 1584 */         PlayerConnection connection = player.playerConnection;
/* 1585 */         if (connection != null)
/*      */         {
/* 1587 */           connection.sendPacket(new PacketPlayOutViewDistance(loadViewDistance));
/*      */         }
/* 1589 */         updateMaps(player);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void sendChunk(EntityPlayer entityplayer, ChunkCoordIntPair chunkcoordintpair, Packet<?>[] apacket, boolean flag, boolean flag1) {
/* 1596 */     if (entityplayer.world == this.world) {
/* 1597 */       if (flag1 && !flag) {
/* 1598 */         PlayerChunk playerchunk = getVisibleChunk(chunkcoordintpair.pair());
/*      */         
/* 1600 */         if (playerchunk != null) {
/* 1601 */           Chunk chunk = playerchunk.getSendingChunk();
/*      */           
/* 1603 */           if (chunk != null) {
/* 1604 */             a(entityplayer, apacket, chunk);
/*      */           }
/*      */           
/* 1607 */           PacketDebug.a(this.world, chunkcoordintpair);
/*      */         } 
/*      */       } 
/*      */       
/* 1611 */       if (!flag1 && flag) {
/* 1612 */         entityplayer.a(chunkcoordintpair);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int d() {
/* 1619 */     return this.visibleChunks.size();
/*      */   }
/*      */   
/*      */   protected a e() {
/* 1623 */     return this.chunkDistanceManager;
/*      */   }
/*      */   
/*      */   protected Iterable<PlayerChunk> f() {
/* 1627 */     return Iterables.unmodifiableIterable((Iterable)getVisibleChunks().values());
/*      */   }
/*      */   
/*      */   void a(Writer writer) throws IOException {
/* 1631 */     CSVWriter csvwriter = CSVWriter.a().a("x").a("z").a("level").a("in_memory").a("status").a("full_status").a("accessible_ready").a("ticking_ready").a("entity_ticking_ready").a("ticket").a("spawning").a("entity_count").a("block_entity_count").a(writer);
/* 1632 */     ObjectBidirectionalIterator objectbidirectionaliterator = getVisibleChunks().long2ObjectEntrySet().iterator();
/*      */     
/* 1634 */     while (objectbidirectionaliterator.hasNext()) {
/* 1635 */       Long2ObjectMap.Entry<PlayerChunk> entry = (Long2ObjectMap.Entry<PlayerChunk>)objectbidirectionaliterator.next();
/* 1636 */       ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(entry.getLongKey());
/* 1637 */       PlayerChunk playerchunk = (PlayerChunk)entry.getValue();
/* 1638 */       Optional<IChunkAccess> optional = Optional.ofNullable(playerchunk.f());
/* 1639 */       Optional<Chunk> optional1 = optional.flatMap(ichunkaccess -> (ichunkaccess instanceof Chunk) ? Optional.<Chunk>of((Chunk)ichunkaccess) : Optional.empty());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1644 */       csvwriter.a(new Object[] { Integer.valueOf(chunkcoordintpair.x), Integer.valueOf(chunkcoordintpair.z), Integer.valueOf(playerchunk.getTicketLevel()), Boolean.valueOf(optional.isPresent()), optional.map(IChunkAccess::getChunkStatus).orElse(null), optional1.map(Chunk::getState).orElse(null), a(playerchunk.c()), a(playerchunk.a()), a(playerchunk.b()), this.chunkDistanceManager.c(entry.getLongKey()), Boolean.valueOf(!isOutsideOfRange(chunkcoordintpair)), optional1.<Integer>map(chunk -> Integer.valueOf(Stream.<List<Entity>>of(chunk.getEntitySlices()).mapToInt(List::size).sum()))
/*      */             
/* 1646 */             .orElse(Integer.valueOf(0)), optional1.<Integer>map(chunk -> Integer.valueOf(chunk.getTileEntities().size()))
/*      */             
/* 1648 */             .orElse(Integer.valueOf(0)) });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static String a(CompletableFuture<Either<Chunk, PlayerChunk.Failure>> completablefuture) {
/*      */     try {
/* 1655 */       Either<Chunk, PlayerChunk.Failure> either = completablefuture.getNow(null);
/*      */       
/* 1657 */       return (either != null) ? (String)either.map(chunk -> "done", playerchunk_failure -> "unloaded") : 
/*      */ 
/*      */ 
/*      */         
/* 1661 */         "not completed";
/* 1662 */     } catch (CompletionException completionexception) {
/* 1663 */       return "failed " + completionexception.getCause().getMessage();
/* 1664 */     } catch (CancellationException cancellationexception) {
/* 1665 */       return "cancelled";
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public NBTTagCompound read(ChunkCoordIntPair chunkcoordintpair) throws IOException {
/* 1673 */     if (Thread.currentThread() != PaperFileIOThread.Holder.INSTANCE) {
/*      */ 
/*      */       
/* 1676 */       NBTTagCompound ret = ((PaperFileIOThread.ChunkData)PaperFileIOThread.Holder.INSTANCE.loadChunkDataAsyncFuture(this.world, chunkcoordintpair.x, chunkcoordintpair.z, IOUtil.getPriorityForCurrentThread(), false, true, true).join()).chunkData;
/*      */       
/* 1678 */       if (ret == PaperFileIOThread.FAILURE_VALUE) {
/* 1679 */         throw new IOException("See logs for further detail");
/*      */       }
/* 1681 */       return ret;
/*      */     } 
/* 1683 */     return super.read(chunkcoordintpair);
/*      */   }
/*      */ 
/*      */   
/*      */   public void write(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) throws IOException {
/* 1688 */     if (Thread.currentThread() != PaperFileIOThread.Holder.INSTANCE) {
/* 1689 */       PaperFileIOThread.Holder.INSTANCE.scheduleSave(this.world, chunkcoordintpair.x, chunkcoordintpair.z, null, nbttagcompound, 
/*      */           
/* 1691 */           IOUtil.getPriorityForCurrentThread());
/*      */       return;
/*      */     } 
/* 1694 */     super.write(chunkcoordintpair, nbttagcompound);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public NBTTagCompound readChunkData(ChunkCoordIntPair chunkcoordintpair) throws IOException {
/* 1700 */     NBTTagCompound nbttagcompound = read(chunkcoordintpair);
/*      */     
/* 1702 */     if (nbttagcompound == null) {
/* 1703 */       return null;
/*      */     }
/*      */     
/* 1706 */     nbttagcompound = getChunkData(this.world.getTypeKey(), this.l, nbttagcompound, chunkcoordintpair, this.world);
/* 1707 */     if (nbttagcompound == null) {
/* 1708 */       return null;
/*      */     }
/*      */     
/* 1711 */     updateChunkStatusOnDisk(chunkcoordintpair, nbttagcompound);
/*      */     
/* 1713 */     return nbttagcompound;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ChunkStatus getChunkStatusOnDiskIfCached(ChunkCoordIntPair chunkPos) {
/* 1719 */     synchronized (this) {
/* 1720 */       RegionFile regionFile = this.regionFileCache.getRegionFileIfLoaded(chunkPos);
/*      */       
/* 1722 */       return (regionFile == null) ? null : regionFile.getStatusIfCached(chunkPos.x, chunkPos.z);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public ChunkStatus getChunkStatusOnDisk(ChunkCoordIntPair chunkPos) throws IOException {
/* 1728 */     IChunkAccess unloadingChunk = this.world.asyncChunkTaskManager.getChunkInSaveProgress(chunkPos.x, chunkPos.z);
/* 1729 */     if (unloadingChunk != null) {
/* 1730 */       return unloadingChunk.getChunkStatus();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1735 */     NBTTagCompound inProgressWrite = PaperFileIOThread.Holder.INSTANCE.getPendingWrite(this.world, chunkPos.x, chunkPos.z, false);
/*      */     
/* 1737 */     if (inProgressWrite != null) {
/* 1738 */       return ChunkRegionLoader.getStatus(inProgressWrite);
/*      */     }
/*      */     
/* 1741 */     synchronized (this) {
/* 1742 */       RegionFile regionFile = this.regionFileCache.getFile(chunkPos, true);
/*      */       
/* 1744 */       if (regionFile == null || !regionFile.chunkExists(chunkPos)) {
/* 1745 */         return null;
/*      */       }
/*      */       
/* 1748 */       ChunkStatus status = regionFile.getStatusIfCached(chunkPos.x, chunkPos.z);
/*      */       
/* 1750 */       if (status != null) {
/* 1751 */         return status;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1756 */     NBTTagCompound compound = readChunkData(chunkPos);
/*      */     
/* 1758 */     return ChunkRegionLoader.getStatus(compound);
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateChunkStatusOnDisk(ChunkCoordIntPair chunkPos, @Nullable NBTTagCompound compound) throws IOException {
/* 1763 */     synchronized (this) {
/* 1764 */       RegionFile regionFile = this.regionFileCache.getFile(chunkPos, false);
/*      */       
/* 1766 */       regionFile.setStatus(chunkPos.x, chunkPos.z, ChunkRegionLoader.getStatus(compound));
/*      */     } 
/*      */   }
/*      */   
/*      */   public IChunkAccess getUnloadingChunk(int chunkX, int chunkZ) {
/* 1771 */     PlayerChunk chunkHolder = (PlayerChunk)this.pendingUnload.get(ChunkCoordIntPair.pair(chunkX, chunkZ));
/* 1772 */     return (chunkHolder == null) ? null : chunkHolder.getAvailableChunkNow();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ChunkStatus getStatusOnDiskNoLoad(int x, int z) {
/* 1782 */     IChunkAccess unloadingChunk = this.world.asyncChunkTaskManager.getChunkInSaveProgress(x, z);
/* 1783 */     if (unloadingChunk != null) {
/* 1784 */       return unloadingChunk.getChunkStatus();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1789 */     NBTTagCompound inProgressWrite = PaperFileIOThread.Holder.INSTANCE.getPendingWrite(this.world, x, z, false);
/*      */     
/* 1791 */     if (inProgressWrite != null) {
/* 1792 */       return ChunkRegionLoader.getStatus(inProgressWrite);
/*      */     }
/*      */ 
/*      */     
/* 1796 */     ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(x, z);
/* 1797 */     synchronized ((this.world.getChunkProvider()).playerChunkMap) {
/*      */       RegionFile file;
/*      */       try {
/* 1800 */         file = (this.world.getChunkProvider()).playerChunkMap.regionFileCache.getFile(chunkPos, false);
/* 1801 */       } catch (IOException ex) {
/* 1802 */         throw new RuntimeException(ex);
/*      */       } 
/*      */       
/* 1805 */       return !file.chunkExists(chunkPos) ? ChunkStatus.EMPTY : file.getStatusIfCached(x, z);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   boolean isOutsideOfRange(ChunkCoordIntPair chunkcoordintpair) {
/* 1811 */     return isOutsideOfRange(chunkcoordintpair, false);
/*      */   }
/*      */ 
/*      */   
/*      */   final boolean isOutsideOfRange(ChunkCoordIntPair chunkcoordintpair, boolean reducedRange) {
/* 1816 */     return isOutsideOfRange(getUpdatingChunk(chunkcoordintpair.pair()), chunkcoordintpair, reducedRange);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   final boolean isOutsideOfRange(PlayerChunk playerchunk, ChunkCoordIntPair chunkcoordintpair, boolean reducedRange) {
/* 1822 */     PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> playersInRange = reducedRange ? playerchunk.playersInMobSpawnRange : playerchunk.playersInChunkTickRange;
/*      */     
/* 1824 */     if (playersInRange == null) {
/* 1825 */       return true;
/*      */     }
/*      */     
/* 1828 */     Object[] backingSet = playersInRange.getBackingSet();
/*      */     
/* 1830 */     if (reducedRange) {
/* 1831 */       for (int i = 0, len = backingSet.length; i < len; i++) {
/* 1832 */         Object raw = backingSet[i];
/* 1833 */         if (raw instanceof EntityPlayer) {
/*      */ 
/*      */           
/* 1836 */           EntityPlayer player = (EntityPlayer)raw;
/*      */           
/* 1838 */           if (player.lastEntitySpawnRadiusSquared > getDistanceSquaredFromChunk(chunkcoordintpair, player))
/* 1839 */             return false; 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1843 */       double range = 16384.0D;
/*      */       
/* 1845 */       for (int i = 0, len = backingSet.length; i < len; i++) {
/* 1846 */         Object raw = backingSet[i];
/* 1847 */         if (raw instanceof EntityPlayer) {
/*      */ 
/*      */           
/* 1850 */           EntityPlayer player = (EntityPlayer)raw;
/*      */           
/* 1852 */           if (16384.0D > getDistanceSquaredFromChunk(chunkcoordintpair, player)) {
/* 1853 */             return false;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/* 1858 */     return true;
/*      */   }
/*      */   
/*      */   private boolean cannotLoadChunks(EntityPlayer entityplayer) {
/* 1862 */     return b(entityplayer);
/*      */   } private boolean b(EntityPlayer entityplayer) {
/* 1864 */     return (entityplayer.isSpectator() && !this.world.getGameRules().getBoolean(GameRules.SPECTATORS_GENERATE_CHUNKS));
/*      */   }
/*      */   
/*      */   void a(EntityPlayer entityplayer, boolean flag) {
/* 1868 */     boolean flag1 = b(entityplayer);
/* 1869 */     boolean flag2 = this.playerMap.c(entityplayer);
/* 1870 */     int i = MathHelper.floor(entityplayer.locX()) >> 4;
/* 1871 */     int j = MathHelper.floor(entityplayer.locZ()) >> 4;
/*      */     
/* 1873 */     if (flag) {
/* 1874 */       this.playerMap.a(ChunkCoordIntPair.pair(i, j), entityplayer, flag1);
/* 1875 */       c(entityplayer);
/* 1876 */       if (!flag1) {
/* 1877 */         this.chunkDistanceManager.a(SectionPosition.a(entityplayer), entityplayer);
/*      */       }
/* 1879 */       addPlayerToDistanceMaps(entityplayer);
/*      */     } else {
/* 1881 */       SectionPosition sectionposition = entityplayer.O();
/*      */       
/* 1883 */       this.playerMap.a(sectionposition.r().pair(), entityplayer);
/* 1884 */       if (!flag2) {
/* 1885 */         this.chunkDistanceManager.b(sectionposition, entityplayer);
/*      */       }
/* 1887 */       removePlayerFromDistanceMaps(entityplayer);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private SectionPosition c(EntityPlayer entityplayer) {
/* 1895 */     SectionPosition sectionposition = SectionPosition.a(entityplayer);
/*      */     
/* 1897 */     entityplayer.a(sectionposition);
/*      */     
/* 1899 */     return sectionposition;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void movePlayer(EntityPlayer entityplayer) {
/* 1905 */     int i = MathHelper.floor(entityplayer.locX()) >> 4;
/* 1906 */     int j = MathHelper.floor(entityplayer.locZ()) >> 4;
/* 1907 */     SectionPosition sectionposition = entityplayer.O();
/* 1908 */     SectionPosition sectionposition1 = SectionPosition.a(entityplayer);
/* 1909 */     long k = sectionposition.r().pair();
/* 1910 */     long l = sectionposition1.r().pair();
/* 1911 */     boolean flag = this.playerMap.d(entityplayer);
/* 1912 */     boolean flag1 = b(entityplayer);
/* 1913 */     boolean flag2 = (sectionposition.s() != sectionposition1.s());
/*      */     
/* 1915 */     if (flag2 || flag != flag1) {
/* 1916 */       c(entityplayer);
/* 1917 */       if (!flag) {
/* 1918 */         this.chunkDistanceManager.b(sectionposition, entityplayer);
/*      */       }
/*      */       
/* 1921 */       if (!flag1) {
/* 1922 */         this.chunkDistanceManager.a(sectionposition1, entityplayer);
/*      */       }
/*      */       
/* 1925 */       if (!flag && flag1) {
/* 1926 */         this.playerMap.a(entityplayer);
/*      */       }
/*      */       
/* 1929 */       if (flag && !flag1) {
/* 1930 */         this.playerMap.b(entityplayer);
/*      */       }
/*      */       
/* 1933 */       if (k != l) {
/* 1934 */         this.playerMap.a(k, l, entityplayer);
/*      */       }
/*      */     } 
/*      */     
/* 1938 */     int i1 = sectionposition.a();
/* 1939 */     int j1 = sectionposition.c();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1983 */     updateMaps(entityplayer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Stream<EntityPlayer> a(ChunkCoordIntPair chunkcoordintpair, boolean flag) {
/* 1992 */     PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> inRange = this.playerViewDistanceBroadcastMap.getObjectsInRange(chunkcoordintpair);
/*      */     
/* 1994 */     if (inRange == null) {
/* 1995 */       return Stream.empty();
/*      */     }
/*      */     
/* 1998 */     List<EntityPlayer> players = new ArrayList<>();
/* 1999 */     Object[] backingSet = inRange.getBackingSet();
/*      */     
/* 2001 */     if (flag) {
/* 2002 */       for (int i = 0, len = backingSet.length; i < len; i++) {
/* 2003 */         Object temp = backingSet[i];
/* 2004 */         if (temp instanceof EntityPlayer) {
/*      */ 
/*      */           
/* 2007 */           EntityPlayer player = (EntityPlayer)temp;
/* 2008 */           int viewDistance = this.playerViewDistanceBroadcastMap.getLastViewDistance(player);
/* 2009 */           long lastPosition = this.playerViewDistanceBroadcastMap.getLastCoordinate(player);
/*      */           
/* 2011 */           int distX = Math.abs(MCUtil.getCoordinateX(lastPosition) - chunkcoordintpair.x);
/* 2012 */           int distZ = Math.abs(MCUtil.getCoordinateZ(lastPosition) - chunkcoordintpair.z);
/* 2013 */           if (Math.max(distX, distZ) == viewDistance)
/* 2014 */             players.add(player); 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 2018 */       for (int i = 0, len = backingSet.length; i < len; i++) {
/* 2019 */         Object temp = backingSet[i];
/* 2020 */         if (temp instanceof EntityPlayer) {
/*      */ 
/*      */           
/* 2023 */           EntityPlayer player = (EntityPlayer)temp;
/* 2024 */           players.add(player);
/*      */         } 
/*      */       } 
/* 2027 */     }  return players.stream();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addEntity(Entity entity) {
/* 2032 */     AsyncCatcher.catchOp("entity track");
/*      */     
/* 2034 */     if (!entity.valid || entity.world != this.world || this.trackedEntities.containsKey(entity.getId())) {
/* 2035 */       (new Throwable("[ERROR] Illegal PlayerChunkMap::addEntity for world " + this.world.getWorld().getName() + ": " + entity + (
/* 2036 */           this.trackedEntities.containsKey(entity.getId()) ? " ALREADY CONTAINED (This would have crashed your server)" : "")))
/* 2037 */         .printStackTrace();
/*      */       return;
/*      */     } 
/* 2040 */     if (entity instanceof EntityPlayer && ((EntityPlayer)entity).supressTrackerForLogin)
/*      */       return; 
/* 2042 */     if (!(entity instanceof EntityComplexPart)) {
/* 2043 */       EntityTypes<?> entitytypes = entity.getEntityType();
/* 2044 */       int i = entitytypes.getChunkRange() * 16;
/* 2045 */       i = TrackingRange.getEntityTrackingRange(entity, i);
/* 2046 */       int j = entitytypes.getUpdateInterval();
/*      */       
/* 2048 */       if (this.trackedEntities.containsKey(entity.getId())) {
/* 2049 */         throw (IllegalStateException)SystemUtils.c(new IllegalStateException("Entity is already tracked!"));
/*      */       }
/* 2051 */       EntityTracker playerchunkmap_entitytracker = new EntityTracker(entity, i, j, entitytypes.isDeltaTracking());
/*      */       
/* 2053 */       entity.tracker = playerchunkmap_entitytracker;
/* 2054 */       this.trackedEntities.put(entity.getId(), playerchunkmap_entitytracker);
/* 2055 */       playerchunkmap_entitytracker.updatePlayers(entity.getPlayersInTrackRange());
/* 2056 */       if (entity instanceof EntityPlayer) {
/* 2057 */         EntityPlayer entityplayer = (EntityPlayer)entity;
/*      */         
/* 2059 */         a(entityplayer, true);
/* 2060 */         ObjectIterator objectiterator = this.trackedEntities.values().iterator();
/*      */         
/* 2062 */         while (objectiterator.hasNext()) {
/* 2063 */           EntityTracker playerchunkmap_entitytracker1 = (EntityTracker)objectiterator.next();
/*      */           
/* 2065 */           if (playerchunkmap_entitytracker1.tracker != entityplayer) {
/* 2066 */             playerchunkmap_entitytracker1.updatePlayer(entityplayer);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeEntity(Entity entity) {
/* 2076 */     AsyncCatcher.catchOp("entity untrack");
/* 2077 */     if (entity instanceof EntityPlayer) {
/* 2078 */       EntityPlayer entityplayer = (EntityPlayer)entity;
/*      */       
/* 2080 */       a(entityplayer, false);
/* 2081 */       ObjectIterator objectiterator = this.trackedEntities.values().iterator();
/*      */       
/* 2083 */       while (objectiterator.hasNext()) {
/* 2084 */         EntityTracker playerchunkmap_entitytracker = (EntityTracker)objectiterator.next();
/*      */         
/* 2086 */         playerchunkmap_entitytracker.clear(entityplayer);
/*      */       } 
/*      */     } 
/*      */     
/* 2090 */     EntityTracker playerchunkmap_entitytracker1 = (EntityTracker)this.trackedEntities.remove(entity.getId());
/*      */     
/* 2092 */     if (playerchunkmap_entitytracker1 != null) {
/* 2093 */       playerchunkmap_entitytracker1.a();
/*      */     }
/* 2095 */     entity.tracker = null;
/*      */   }
/*      */ 
/*      */   
/*      */   private final void processTrackQueue() {
/* 2100 */     this.world.timings.tracker1.startTiming();
/*      */     try {
/* 2102 */       IteratorSafeOrderedReferenceSet.Iterator<Chunk> iterator = (this.world.getChunkProvider()).entityTickingChunks.iterator();
/*      */       try {
/* 2104 */         while (iterator.hasNext()) {
/* 2105 */           Chunk chunk = (Chunk)iterator.next();
/* 2106 */           Entity[] entities = chunk.entities.getRawData();
/* 2107 */           for (int i = 0, len = chunk.entities.size(); i < len; i++) {
/* 2108 */             Entity entity = entities[i];
/* 2109 */             EntityTracker tracker = (EntityTracker)this.trackedEntities.get(entity.getId());
/* 2110 */             if (tracker != null) {
/* 2111 */               tracker.updatePlayers(tracker.tracker.getPlayersInTrackRange());
/* 2112 */               tracker.trackerEntry.tick();
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } finally {
/* 2117 */         iterator.finishedIterating();
/*      */       } 
/*      */     } finally {
/* 2120 */       this.world.timings.tracker1.stopTiming();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void g() {
/* 2128 */     processTrackQueue();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void broadcast(Entity entity, Packet<?> packet) {
/* 2172 */     EntityTracker playerchunkmap_entitytracker = (EntityTracker)this.trackedEntities.get(entity.getId());
/*      */     
/* 2174 */     if (playerchunkmap_entitytracker != null) {
/* 2175 */       playerchunkmap_entitytracker.broadcast(packet);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void broadcastIncludingSelf(Entity entity, Packet<?> packet) {
/* 2181 */     EntityTracker playerchunkmap_entitytracker = (EntityTracker)this.trackedEntities.get(entity.getId());
/*      */     
/* 2183 */     if (playerchunkmap_entitytracker != null) {
/* 2184 */       playerchunkmap_entitytracker.broadcastIncludingSelf(packet);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getLightMask(Chunk chunk) {
/* 2191 */     ChunkSection[] chunkSections = chunk.getSections();
/* 2192 */     int mask = 0;
/*      */     
/* 2194 */     for (int i = 0; i < chunkSections.length; i++)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2202 */       mask |= (ChunkSection.isEmpty(chunkSections[i]) ? 0 : 7) << i;
/*      */     }
/*      */     
/* 2205 */     return mask;
/*      */   }
/*      */   
/*      */   private static int getCeilingLightMask(Chunk chunk) {
/* 2209 */     int mask = getLightMask(chunk);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2220 */     mask |= mask >> 1;
/* 2221 */     mask |= mask >> 2;
/* 2222 */     mask |= mask >> 4;
/* 2223 */     mask |= mask >> 8;
/* 2224 */     mask |= mask >> 16;
/*      */     
/* 2226 */     return mask;
/*      */   }
/*      */   
/*      */   final void sendChunk(EntityPlayer entityplayer, Packet<?>[] apacket, Chunk chunk) {
/* 2230 */     a(entityplayer, apacket, chunk); } private void a(EntityPlayer entityplayer, Packet<?>[] apacket, Chunk chunk) {
/*      */     Packet[] arrayOfPacket;
/* 2232 */     if (apacket[0] == null) {
/*      */       
/* 2234 */       if (apacket.length != 10) {
/* 2235 */         arrayOfPacket = new Packet[10];
/*      */       }
/*      */       
/* 2238 */       arrayOfPacket[0] = new PacketPlayOutMapChunk(chunk, 65535);
/* 2239 */       arrayOfPacket[1] = new PacketPlayOutLightUpdate(chunk.getPos(), this.lightEngine, true);
/*      */ 
/*      */       
/* 2242 */       int lightMask = getLightMask(chunk);
/* 2243 */       int k = 1;
/* 2244 */       for (int m = -1; m <= 1; m++) {
/* 2245 */         for (int z = -1; z <= 1; z++) {
/* 2246 */           if (m != 0 || z != 0) {
/*      */ 
/*      */ 
/*      */             
/* 2250 */             k++;
/*      */             
/* 2252 */             if (chunk.isNeighbourLoaded(m, z)) {
/*      */ 
/*      */ 
/*      */               
/* 2256 */               Chunk neighbor = chunk.getRelativeNeighbourIfLoaded(m, z);
/* 2257 */               int updateLightMask = lightMask & (getCeilingLightMask(neighbor) ^ 0xFFFFFFFF);
/*      */               
/* 2259 */               if (updateLightMask != 0)
/*      */               {
/*      */ 
/*      */                 
/* 2263 */                 arrayOfPacket[k] = new PacketPlayOutLightUpdate(new ChunkCoordIntPair((chunk.getPos()).x + m, (chunk.getPos()).z + z), this.lightEngine, updateLightMask, 0, true); } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 2268 */     }  int viewDistance = this.playerViewDistanceBroadcastMap.getLastViewDistance(entityplayer);
/* 2269 */     long lastPosition = this.playerViewDistanceBroadcastMap.getLastCoordinate(entityplayer);
/*      */     
/* 2271 */     int j = 1;
/* 2272 */     for (int x = -1; x <= 1; x++) {
/* 2273 */       for (int z = -1; z <= 1; z++) {
/* 2274 */         if (x != 0 || z != 0) {
/*      */ 
/*      */ 
/*      */           
/* 2278 */           j++;
/*      */           
/* 2280 */           Packet<?> packet = arrayOfPacket[j];
/* 2281 */           if (packet != null) {
/*      */ 
/*      */ 
/*      */             
/* 2285 */             int distX = Math.abs(MCUtil.getCoordinateX(lastPosition) - (chunk.getPos()).x + x);
/* 2286 */             int distZ = Math.abs(MCUtil.getCoordinateZ(lastPosition) - (chunk.getPos()).z + z);
/*      */             
/* 2288 */             if (Math.max(distX, distZ) <= viewDistance)
/*      */             {
/*      */               
/* 2291 */               entityplayer.playerConnection.sendPacket(packet); } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2296 */     entityplayer.a(chunk.getPos(), arrayOfPacket[0], arrayOfPacket[1]);
/* 2297 */     PacketDebug.a(this.world, chunk.getPos());
/* 2298 */     List<Entity> list = Lists.newArrayList();
/* 2299 */     List<Entity> list1 = Lists.newArrayList();
/*      */ 
/*      */     
/* 2302 */     Entity[] entities = chunk.entities.getRawData();
/* 2303 */     for (int i = 0, size = chunk.entities.size(); i < size; i++) {
/* 2304 */       Entity entity = entities[i];
/* 2305 */       if (entity != entityplayer) {
/*      */ 
/*      */         
/* 2308 */         EntityTracker tracker = (EntityTracker)this.trackedEntities.get(entity.getId());
/* 2309 */         if (tracker != null) {
/* 2310 */           tracker.updatePlayer(entityplayer);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2316 */         if (entity instanceof EntityInsentient && ((EntityInsentient)entity).getLeashHolder() != null) {
/* 2317 */           list.add(entity);
/*      */         }
/*      */         
/* 2320 */         if (!entity.getPassengers().isEmpty()) {
/* 2321 */           list1.add(entity);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2329 */     if (!list.isEmpty()) {
/* 2330 */       Iterator<Entity> iterator = list.iterator();
/*      */       
/* 2332 */       while (iterator.hasNext()) {
/* 2333 */         Entity entity1 = iterator.next();
/* 2334 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutAttachEntity(entity1, ((EntityInsentient)entity1).getLeashHolder()));
/*      */       } 
/*      */     } 
/*      */     
/* 2338 */     if (!list1.isEmpty()) {
/* 2339 */       Iterator<Entity> iterator = list1.iterator();
/*      */       
/* 2341 */       while (iterator.hasNext()) {
/* 2342 */         Entity entity1 = iterator.next();
/* 2343 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutMount(entity1));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public VillagePlace getVillagePlace() {
/* 2349 */     return h();
/*      */   } protected VillagePlace h() {
/* 2351 */     return this.m;
/*      */   }
/*      */   
/*      */   public CompletableFuture<Void> a(Chunk chunk) {
/* 2355 */     return this.executor.f(() -> chunk.a(this.world));
/*      */   }
/*      */ 
/*      */   
/*      */   public class EntityTracker
/*      */   {
/*      */     final EntityTrackerEntry trackerEntry;
/*      */     
/*      */     private final Entity tracker;
/*      */     
/*      */     private final int trackingDistance;
/*      */     
/*      */     private SectionPosition e;
/*      */     
/* 2369 */     public Map<EntityPlayer, Boolean> trackedPlayerMap = new HashMap<>();
/* 2370 */     public Set<EntityPlayer> trackedPlayers = this.trackedPlayerMap.keySet(); PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> lastTrackerCandidates;
/*      */     
/*      */     public EntityTracker(Entity entity, int i, int j, boolean flag) {
/* 2373 */       this.trackerEntry = new EntityTrackerEntry(PlayerChunkMap.this.world, entity, j, flag, this::broadcast, this.trackedPlayerMap);
/* 2374 */       this.tracker = entity;
/* 2375 */       this.trackingDistance = i;
/* 2376 */       this.e = SectionPosition.a(entity);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final void updatePlayers(PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> newTrackerCandidates) {
/* 2383 */       PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> oldTrackerCandidates = this.lastTrackerCandidates;
/* 2384 */       this.lastTrackerCandidates = newTrackerCandidates;
/*      */       
/* 2386 */       if (newTrackerCandidates != null) {
/* 2387 */         Object[] rawData = newTrackerCandidates.getBackingSet();
/* 2388 */         for (int i = 0, len = rawData.length; i < len; i++) {
/* 2389 */           Object raw = rawData[i];
/* 2390 */           if (raw instanceof EntityPlayer) {
/*      */ 
/*      */             
/* 2393 */             EntityPlayer player = (EntityPlayer)raw;
/* 2394 */             updatePlayer(player);
/*      */           } 
/*      */         } 
/*      */       } 
/* 2398 */       if (oldTrackerCandidates == newTrackerCandidates) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2407 */       for (EntityPlayer player : (EntityPlayer[])this.trackedPlayers.<EntityPlayer>toArray(new EntityPlayer[0])) {
/* 2408 */         if (newTrackerCandidates == null || !newTrackerCandidates.contains(player)) {
/* 2409 */           updatePlayer(player);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object object) {
/* 2416 */       return (object instanceof EntityTracker) ? ((((EntityTracker)object).tracker.getId() == this.tracker.getId())) : false;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 2420 */       return this.tracker.getId();
/*      */     }
/*      */     
/*      */     public void broadcast(Packet<?> packet) {
/* 2424 */       Iterator<EntityPlayer> iterator = this.trackedPlayers.iterator();
/*      */       
/* 2426 */       while (iterator.hasNext()) {
/* 2427 */         EntityPlayer entityplayer = iterator.next();
/*      */         
/* 2429 */         entityplayer.playerConnection.sendPacket(packet);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void broadcastIncludingSelf(Packet<?> packet) {
/* 2435 */       broadcast(packet);
/* 2436 */       if (this.tracker instanceof EntityPlayer) {
/* 2437 */         ((EntityPlayer)this.tracker).playerConnection.sendPacket(packet);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void a() {
/* 2443 */       Iterator<EntityPlayer> iterator = this.trackedPlayers.iterator();
/*      */       
/* 2445 */       while (iterator.hasNext()) {
/* 2446 */         EntityPlayer entityplayer = iterator.next();
/*      */         
/* 2448 */         this.trackerEntry.a(entityplayer);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void clear(EntityPlayer entityplayer) {
/* 2454 */       AsyncCatcher.catchOp("player tracker clear");
/* 2455 */       if (this.trackedPlayers.remove(entityplayer)) {
/* 2456 */         this.trackerEntry.a(entityplayer);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void updatePlayer(EntityPlayer entityplayer) {
/* 2462 */       AsyncCatcher.catchOp("player tracker update");
/* 2463 */       if (entityplayer != this.tracker) {
/*      */ 
/*      */         
/* 2466 */         double vec3d_dx = entityplayer.locX() - this.tracker.locX();
/* 2467 */         double vec3d_dy = entityplayer.locY() - this.tracker.locY();
/* 2468 */         double vec3d_dz = entityplayer.locZ() - this.tracker.locZ();
/*      */         
/* 2470 */         int i = Math.min(b(), (PlayerChunkMap.this.viewDistance - 1) * 16);
/* 2471 */         boolean flag = (vec3d_dx >= -i && vec3d_dx <= i && vec3d_dz >= -i && vec3d_dz <= i && this.tracker.a(entityplayer));
/*      */         
/* 2473 */         if (flag) {
/* 2474 */           boolean flag1 = this.tracker.attachedToPlayer;
/*      */           
/* 2476 */           if (!flag1) {
/* 2477 */             ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(this.tracker.chunkX, this.tracker.chunkZ);
/* 2478 */             PlayerChunk playerchunk = PlayerChunkMap.this.getVisibleChunk(chunkcoordintpair.pair());
/*      */             
/* 2480 */             if (playerchunk != null && playerchunk.getSendingChunk() != null) {
/* 2481 */               flag1 = (PlayerChunkMap.b(chunkcoordintpair, entityplayer, false) <= PlayerChunkMap.this.viewDistance);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 2486 */           if (this.tracker instanceof EntityPlayer) {
/* 2487 */             CraftPlayer craftPlayer = ((EntityPlayer)this.tracker).getBukkitEntity();
/* 2488 */             if (!entityplayer.getBukkitEntity().canSee((Player)craftPlayer)) {
/* 2489 */               flag1 = false;
/*      */             }
/*      */           } 
/*      */           
/* 2493 */           entityplayer.removeQueue.remove(Integer.valueOf(this.tracker.getId()));
/*      */ 
/*      */           
/* 2496 */           if (flag1 && this.trackedPlayerMap.putIfAbsent(entityplayer, Boolean.valueOf(true)) == null) {
/* 2497 */             this.trackerEntry.b(entityplayer);
/*      */           }
/* 2499 */         } else if (this.trackedPlayers.remove(entityplayer)) {
/* 2500 */           this.trackerEntry.a(entityplayer);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private int a(int i) {
/* 2507 */       return PlayerChunkMap.this.world.getMinecraftServer().b(i);
/*      */     }
/*      */     
/*      */     private int b() {
/* 2511 */       Collection<Entity> collection = this.tracker.getAllPassengers();
/* 2512 */       int i = this.trackingDistance;
/* 2513 */       Iterator<Entity> iterator = collection.iterator();
/*      */       
/* 2515 */       while (iterator.hasNext()) {
/* 2516 */         Entity entity = iterator.next();
/* 2517 */         int j = entity.getEntityType().getChunkRange() * 16;
/* 2518 */         j = TrackingRange.getEntityTrackingRange(entity, j);
/*      */         
/* 2520 */         if (j < i) {
/* 2521 */           i = j;
/*      */         }
/*      */       } 
/*      */       
/* 2525 */       return a(i);
/*      */     }
/*      */     
/*      */     public void track(List<EntityPlayer> list) {
/* 2529 */       Iterator<EntityPlayer> iterator = list.iterator();
/*      */       
/* 2531 */       while (iterator.hasNext()) {
/* 2532 */         EntityPlayer entityplayer = iterator.next();
/*      */         
/* 2534 */         updatePlayer(entityplayer);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   class a
/*      */     extends ChunkMapDistance
/*      */   {
/*      */     protected a(Executor executor, Executor executor1) {
/* 2543 */       super(executor, executor1);
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean a(long i) {
/* 2548 */       return PlayerChunkMap.this.unloadQueue.contains(i);
/*      */     }
/*      */ 
/*      */     
/*      */     @Nullable
/*      */     protected PlayerChunk b(long i) {
/* 2554 */       return PlayerChunkMap.this.getUpdatingChunk(i);
/*      */     }
/*      */ 
/*      */     
/*      */     @Nullable
/*      */     protected PlayerChunk a(long i, int j, @Nullable PlayerChunk playerchunk, int k) {
/* 2560 */       return PlayerChunkMap.this.a(i, j, playerchunk, k);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PlayerChunkMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */