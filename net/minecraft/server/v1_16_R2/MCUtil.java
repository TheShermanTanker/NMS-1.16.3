/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.block.TargetBlockInfo;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.stream.JsonWriter;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectRBTreeSet;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StringWriter;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import java.util.SortedSet;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.apache.commons.lang.exception.ExceptionUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.Waitable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.scheduler.BukkitTask;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ 
/*     */ public final class MCUtil {
/*  42 */   public static final ThreadPoolExecutor asyncExecutor = new ThreadPoolExecutor(0, 2, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), (new ThreadFactoryBuilder())
/*     */ 
/*     */       
/*  45 */       .setNameFormat("Paper Async Task Handler Thread - %1$d").build());
/*     */   public static final double COLLISION_EPSILON = 1.0E-7D;
/*  47 */   public static final ThreadPoolExecutor cleanerExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), (new ThreadFactoryBuilder())
/*     */ 
/*     */       
/*  50 */       .setNameFormat("Paper Object Cleaner").build());
/*     */ 
/*     */   
/*  53 */   public static final long INVALID_CHUNK_KEY = getCoordinateKey(2147483647, 2147483647);
/*     */   public static final Executor MAIN_EXECUTOR;
/*     */   
/*     */   public static Runnable once(Runnable run) {
/*  57 */     AtomicBoolean ran = new AtomicBoolean(false);
/*  58 */     return () -> {
/*     */         if (ran.compareAndSet(false, true)) {
/*     */           run.run();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static <T> Runnable once(List<T> list, Consumer<T> cb) {
/*  66 */     return once(() -> list.forEach(cb));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Runnable makeCleanerCallback(Runnable run) {
/*  72 */     return once(() -> cleanerExecutor.execute(run));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Runnable registerCleaner(Object obj, Runnable run) {
/*  83 */     Runnable cleaner = makeCleanerCallback(run);
/*  84 */     Cleaner.register(obj, cleaner);
/*  85 */     return cleaner;
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
/*     */   public static <T> Runnable registerListCleaner(Object obj, List<T> list, Consumer<T> cleaner) {
/*  97 */     return registerCleaner(obj, () -> {
/*     */           list.forEach(cleaner);
/*     */           list.clear();
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
/*     */   public static <T> Runnable registerCleaner(Object obj, T resource, Consumer<T> cleaner) {
/* 112 */     return registerCleaner(obj, () -> cleaner.accept(resource));
/*     */   }
/*     */   
/*     */   public static List<ChunkCoordIntPair> getSpiralOutChunks(BlockPosition blockposition, int radius) {
/* 116 */     List<ChunkCoordIntPair> list = Lists.newArrayList();
/*     */     
/* 118 */     list.add(new ChunkCoordIntPair(blockposition.getX() >> 4, blockposition.getZ() >> 4));
/* 119 */     for (int r = 1; r <= radius; r++) {
/* 120 */       int x = -r;
/* 121 */       int z = r;
/*     */ 
/*     */       
/* 124 */       while (x <= r && z > -r) {
/* 125 */         list.add(new ChunkCoordIntPair(blockposition.getX() + (x << 4) >> 4, blockposition.getZ() + (z << 4) >> 4));
/* 126 */         list.add(new ChunkCoordIntPair(blockposition.getX() - (x << 4) >> 4, blockposition.getZ() - (z << 4) >> 4));
/*     */         
/* 128 */         if (x < r) {
/* 129 */           x++; continue;
/*     */         } 
/* 131 */         z--;
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     return list;
/*     */   }
/*     */   
/*     */   public static int fastFloor(double x) {
/* 139 */     int truncated = (int)x;
/* 140 */     return (x < truncated) ? (truncated - 1) : truncated;
/*     */   }
/*     */   
/*     */   public static int fastFloor(float x) {
/* 144 */     int truncated = (int)x;
/* 145 */     return (x < truncated) ? (truncated - 1) : truncated;
/*     */   }
/*     */   
/*     */   public static float normalizeYaw(float f) {
/* 149 */     float f1 = f % 360.0F;
/*     */     
/* 151 */     if (f1 >= 180.0F) {
/* 152 */       f1 -= 360.0F;
/*     */     }
/*     */     
/* 155 */     if (f1 < -180.0F) {
/* 156 */       f1 += 360.0F;
/*     */     }
/*     */     
/* 159 */     return f1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String stack() {
/* 168 */     return ExceptionUtils.getFullStackTrace(new Throwable());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String stack(String str) {
/* 178 */     return ExceptionUtils.getFullStackTrace(new Throwable(str));
/*     */   }
/*     */   
/*     */   public static long getCoordinateKey(BlockPosition blockPos) {
/* 182 */     return (blockPos.getZ() >> 4) << 32L | (blockPos.getX() >> 4) & 0xFFFFFFFFL;
/*     */   }
/*     */   
/*     */   public static long getCoordinateKey(Entity entity) {
/* 186 */     return (fastFloor(entity.locZ()) >> 4) << 32L | (fastFloor(entity.locX()) >> 4) & 0xFFFFFFFFL;
/*     */   }
/*     */   
/*     */   public static long getCoordinateKey(ChunkCoordIntPair pair) {
/* 190 */     return pair.z << 32L | pair.x & 0xFFFFFFFFL;
/*     */   }
/*     */   
/*     */   public static long getCoordinateKey(int x, int z) {
/* 194 */     return z << 32L | x & 0xFFFFFFFFL;
/*     */   }
/*     */   
/*     */   public static int getCoordinateX(long key) {
/* 198 */     return (int)key;
/*     */   }
/*     */   
/*     */   public static int getCoordinateZ(long key) {
/* 202 */     return (int)(key >>> 32L);
/*     */   }
/*     */   
/*     */   public static int getChunkCoordinate(double coordinate) {
/* 206 */     return fastFloor(coordinate) >> 4;
/*     */   }
/*     */   
/*     */   public static int getBlockCoordinate(double coordinate) {
/* 210 */     return fastFloor(coordinate);
/*     */   }
/*     */   
/*     */   public static long getBlockKey(int x, int y, int z) {
/* 214 */     return x & 0x7FFFFFFL | (z & 0x7FFFFFFL) << 27L | y << 54L;
/*     */   }
/*     */   
/*     */   public static long getBlockKey(BlockPosition pos) {
/* 218 */     return pos.getX() & 0x7FFFFFFL | (pos.getZ() & 0x7FFFFFFL) << 27L | pos.getY() << 54L;
/*     */   }
/*     */   
/*     */   public static long getBlockKey(Entity entity) {
/* 222 */     return getBlockKey(getBlockCoordinate(entity.locX()), getBlockCoordinate(entity.locY()), getBlockCoordinate(entity.locZ()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> void mergeSortedSets(Consumer<T> consumer, Comparator<? super T> comparator, SortedSet<T>... sets) {
/* 227 */     ObjectRBTreeSet<T> all = new ObjectRBTreeSet(comparator);
/*     */     
/* 229 */     for (SortedSet<T> set : sets) {
/* 230 */       if (set != null) {
/* 231 */         all.addAll(set);
/*     */       }
/*     */     } 
/* 234 */     all.forEach(consumer);
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 239 */     MAIN_EXECUTOR = (run -> {
/*     */         if (!isMainThread()) {
/*     */           MinecraftServer.getServer().execute(run);
/*     */         } else {
/*     */           run.run();
/*     */         } 
/*     */       });
/*     */   }
/*     */   public static <T> CompletableFuture<T> ensureMain(CompletableFuture<T> future) {
/* 248 */     return future.thenApplyAsync(r -> r, MAIN_EXECUTOR);
/*     */   }
/*     */   
/*     */   public static <T> void thenOnMain(CompletableFuture<T> future, Consumer<T> consumer) {
/* 252 */     future.thenAcceptAsync(consumer, MAIN_EXECUTOR);
/*     */   }
/*     */   public static <T> void thenOnMain(CompletableFuture<T> future, BiConsumer<T, Throwable> consumer) {
/* 255 */     future.whenCompleteAsync(consumer, MAIN_EXECUTOR);
/*     */   }
/*     */   
/*     */   public static boolean isMainThread() {
/* 259 */     return MinecraftServer.getServer().isMainThread();
/*     */   }
/*     */   
/*     */   public static BukkitTask scheduleTask(int ticks, Runnable runnable) {
/* 263 */     return scheduleTask(ticks, runnable, null);
/*     */   }
/*     */   
/*     */   public static BukkitTask scheduleTask(int ticks, Runnable runnable, String taskName) {
/* 267 */     return (MinecraftServer.getServer()).server.getScheduler().scheduleInternalTask(runnable, ticks, taskName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processQueue() {
/* 272 */     Queue<Runnable> processQueue = getProcessQueue(); Runnable runnable;
/* 273 */     while ((runnable = processQueue.poll()) != null) {
/*     */       try {
/* 275 */         runnable.run();
/* 276 */       } catch (Exception e) {
/* 277 */         MinecraftServer.LOGGER.error("Error executing task", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public static <T> T processQueueWhileWaiting(CompletableFuture<T> future) {
/*     */     try {
/* 283 */       if (isMainThread()) {
/* 284 */         while (!future.isDone()) {
/*     */           try {
/* 286 */             return future.get(1L, TimeUnit.MILLISECONDS);
/* 287 */           } catch (TimeoutException ignored) {
/* 288 */             processQueue();
/*     */           } 
/*     */         } 
/*     */       }
/* 292 */       return future.get();
/* 293 */     } catch (Exception e) {
/* 294 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void ensureMain(Runnable run) {
/* 299 */     ensureMain((String)null, run);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void ensureMain(String reason, Runnable run) {
/* 308 */     if (AsyncCatcher.enabled && Thread.currentThread() != (MinecraftServer.getServer()).serverThread) {
/* 309 */       if (reason != null) {
/* 310 */         (new IllegalStateException("Asynchronous " + reason + "!")).printStackTrace();
/*     */       }
/* 312 */       getProcessQueue().add(run);
/*     */       return;
/*     */     } 
/* 315 */     run.run();
/*     */   }
/*     */   
/*     */   private static Queue<Runnable> getProcessQueue() {
/* 319 */     return (MinecraftServer.getServer()).processQueue;
/*     */   }
/*     */   
/*     */   public static <T> T ensureMain(Supplier<T> run) {
/* 323 */     return ensureMain((String)null, run);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> T ensureMain(String reason, final Supplier<T> run) {
/* 333 */     if (AsyncCatcher.enabled && Thread.currentThread() != (MinecraftServer.getServer()).serverThread) {
/* 334 */       if (reason != null) {
/* 335 */         (new IllegalStateException("Asynchronous " + reason + "! Blocking thread until it returns ")).printStackTrace();
/*     */       }
/* 337 */       Waitable<T> wait = new Waitable<T>()
/*     */         {
/*     */           protected T evaluate() {
/* 340 */             return run.get();
/*     */           }
/*     */         };
/* 343 */       getProcessQueue().add(wait);
/*     */       try {
/* 345 */         return (T)wait.get();
/* 346 */       } catch (InterruptedException|java.util.concurrent.ExecutionException e) {
/* 347 */         e.printStackTrace();
/*     */         
/* 349 */         return null;
/*     */       } 
/* 351 */     }  return run.get();
/*     */   }
/*     */   
/*     */   public static PlayerProfile toBukkit(GameProfile profile) {
/* 355 */     return CraftPlayerProfile.asBukkitMirror(profile);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double distance(Entity e1, Entity e2) {
/* 365 */     return Math.sqrt(distanceSq(e1, e2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double distance(BlockPosition e1, BlockPosition e2) {
/* 376 */     return Math.sqrt(distanceSq(e1, e2));
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
/*     */   public static double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
/* 390 */     return Math.sqrt(distanceSq(x1, y1, z1, x2, y2, z2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double distanceSq(Entity e1, Entity e2) {
/* 400 */     return distanceSq(e1.locX(), e1.locY(), e1.locZ(), e2.locX(), e2.locY(), e2.locZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double distanceSq(BlockPosition pos1, BlockPosition pos2) {
/* 410 */     return distanceSq(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
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
/*     */   public static double distanceSq(double x1, double y1, double z1, double x2, double y2, double z2) {
/* 424 */     return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2);
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
/*     */   public static Location toLocation(World world, double x, double y, double z) {
/* 436 */     return new Location((World)world.getWorld(), x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Location toLocation(World world, BlockPosition pos) {
/* 446 */     return new Location((World)world.getWorld(), pos.getX(), pos.getY(), pos.getZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Location toLocation(Entity entity) {
/* 455 */     return new Location((World)entity.getWorld().getWorld(), entity.locX(), entity.locY(), entity.locZ());
/*     */   }
/*     */   
/*     */   public static Block toBukkitBlock(World world, BlockPosition pos) {
/* 459 */     return world.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ());
/*     */   }
/*     */   
/*     */   public static BlockPosition toBlockPosition(Location loc) {
/* 463 */     return new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
/*     */   }
/*     */   
/*     */   public static boolean isEdgeOfChunk(BlockPosition pos) {
/* 467 */     int modX = pos.getX() & 0xF;
/* 468 */     int modZ = pos.getZ() & 0xF;
/* 469 */     return (modX == 0 || modX == 15 || modZ == 0 || modZ == 15);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void scheduleAsyncTask(Runnable run) {
/* 477 */     asyncExecutor.execute(run);
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static WorldServer getNMSWorld(@Nonnull World world) {
/* 482 */     return ((CraftWorld)world).getHandle();
/*     */   }
/*     */   
/*     */   public static WorldServer getNMSWorld(@Nonnull Entity entity) {
/* 486 */     return getNMSWorld(entity.getWorld());
/*     */   }
/*     */   
/*     */   public static RayTrace.FluidCollisionOption getNMSFluidCollisionOption(TargetBlockInfo.FluidMode fluidMode) {
/* 490 */     if (fluidMode == TargetBlockInfo.FluidMode.NEVER) {
/* 491 */       return RayTrace.FluidCollisionOption.NONE;
/*     */     }
/* 493 */     if (fluidMode == TargetBlockInfo.FluidMode.SOURCE_ONLY) {
/* 494 */       return RayTrace.FluidCollisionOption.SOURCE_ONLY;
/*     */     }
/* 496 */     if (fluidMode == TargetBlockInfo.FluidMode.ALWAYS) {
/* 497 */       return RayTrace.FluidCollisionOption.ANY;
/*     */     }
/* 499 */     return null;
/*     */   }
/*     */   
/*     */   public static BlockFace toBukkitBlockFace(EnumDirection enumDirection) {
/* 503 */     switch (enumDirection) {
/*     */       case DOWN:
/* 505 */         return BlockFace.DOWN;
/*     */       case UP:
/* 507 */         return BlockFace.UP;
/*     */       case NORTH:
/* 509 */         return BlockFace.NORTH;
/*     */       case SOUTH:
/* 511 */         return BlockFace.SOUTH;
/*     */       case WEST:
/* 513 */         return BlockFace.WEST;
/*     */       case EAST:
/* 515 */         return BlockFace.EAST;
/*     */     } 
/* 517 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static IChatBaseComponent getBaseComponentFromNbt(String key, NBTTagCompound compound) {
/* 523 */     if (!compound.hasKey(key)) {
/* 524 */       return null;
/*     */     }
/* 526 */     String string = compound.getString(key);
/*     */     try {
/* 528 */       return IChatBaseComponent.ChatSerializer.jsonToComponent(string);
/* 529 */     } catch (JsonParseException e) {
/* 530 */       Bukkit.getLogger().warning("Unable to parse " + key + " from " + compound + ": " + e.getMessage());
/*     */ 
/*     */       
/* 533 */       return null;
/*     */     } 
/*     */   }
/*     */   public static ChunkStatus getChunkStatus(PlayerChunk chunk) {
/* 537 */     List<ChunkStatus> statuses = ChunkProviderServer.getPossibleChunkStatuses();
/* 538 */     for (int i = statuses.size() - 1; i >= 0; i--) {
/* 539 */       ChunkStatus curr = statuses.get(i);
/* 540 */       CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> future = chunk.getStatusFutureUnchecked(curr);
/* 541 */       if (future != PlayerChunk.UNLOADED_CHUNK_ACCESS_FUTURE) {
/* 542 */         return curr;
/*     */       }
/*     */     } 
/* 545 */     return null;
/*     */   }
/*     */   
/*     */   public static void dumpChunks(File file) throws IOException {
/* 549 */     file.getParentFile().mkdirs();
/* 550 */     file.createNewFile();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 590 */     List<World> worlds = Bukkit.getWorlds();
/* 591 */     JsonObject data = new JsonObject();
/*     */     
/* 593 */     data.addProperty("server-version", Bukkit.getVersion());
/* 594 */     data.addProperty("data-version", Integer.valueOf(0));
/*     */     
/* 596 */     JsonArray worldsData = new JsonArray();
/*     */     
/* 598 */     for (World bukkitWorld : worlds) {
/* 599 */       JsonObject worldData = new JsonObject();
/*     */       
/* 601 */       WorldServer world = ((CraftWorld)bukkitWorld).getHandle();
/* 602 */       PlayerChunkMap chunkMap = (world.getChunkProvider()).playerChunkMap;
/* 603 */       Long2ObjectLinkedOpenHashMap<PlayerChunk> visibleChunks = chunkMap.getVisibleChunks();
/* 604 */       ChunkMapDistance chunkMapDistance = chunkMap.chunkDistanceManager;
/* 605 */       List<PlayerChunk> allChunks = new ArrayList<>((Collection<? extends PlayerChunk>)visibleChunks.values());
/* 606 */       List<EntityPlayer> players = world.players;
/*     */       
/* 608 */       int fullLoadedChunks = 0;
/*     */       
/* 610 */       for (PlayerChunk chunk : allChunks) {
/* 611 */         if (chunk.getFullChunkIfCached() != null) {
/* 612 */           fullLoadedChunks++;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 617 */       allChunks.sort((v1, v2) -> (v1.location.x != v2.location.x) ? Integer.compare(v1.location.x, v2.location.x) : Integer.compare(v1.location.z, v2.location.z));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 624 */       worldData.addProperty("name", world.getWorld().getName());
/* 625 */       worldData.addProperty("view-distance", Integer.valueOf((world.getChunkProvider()).playerChunkMap.getEffectiveViewDistance()));
/* 626 */       worldData.addProperty("no-view-distance", Integer.valueOf((world.getChunkProvider()).playerChunkMap.getRawNoTickViewDistance()));
/* 627 */       worldData.addProperty("keep-spawn-loaded", Boolean.valueOf(world.keepSpawnInMemory));
/* 628 */       worldData.addProperty("keep-spawn-loaded-range", Short.valueOf(world.paperConfig.keepLoadedRange));
/* 629 */       worldData.addProperty("visible-chunk-count", Integer.valueOf(visibleChunks.size()));
/* 630 */       worldData.addProperty("loaded-chunk-count", Integer.valueOf(chunkMap.loadedChunks.size()));
/* 631 */       worldData.addProperty("verified-fully-loaded-chunks", Integer.valueOf(fullLoadedChunks));
/*     */       
/* 633 */       JsonArray playersData = new JsonArray();
/*     */       
/* 635 */       for (EntityPlayer player : players) {
/* 636 */         JsonObject playerData = new JsonObject();
/*     */         
/* 638 */         playerData.addProperty("name", player.getName());
/* 639 */         playerData.addProperty("x", Double.valueOf(player.locX()));
/* 640 */         playerData.addProperty("y", Double.valueOf(player.locY()));
/* 641 */         playerData.addProperty("z", Double.valueOf(player.locZ()));
/*     */         
/* 643 */         playersData.add((JsonElement)playerData);
/*     */       } 
/*     */ 
/*     */       
/* 647 */       worldData.add("players", (JsonElement)playersData);
/*     */       
/* 649 */       JsonArray chunksData = new JsonArray();
/*     */       
/* 651 */       for (PlayerChunk playerChunk : allChunks) {
/* 652 */         JsonObject chunkData = new JsonObject();
/*     */         
/* 654 */         Set<Ticket<?>> tickets = (Set<Ticket<?>>)chunkMapDistance.tickets.get(playerChunk.location.pair());
/* 655 */         ChunkStatus status = getChunkStatus(playerChunk);
/*     */         
/* 657 */         chunkData.addProperty("x", Integer.valueOf(playerChunk.location.x));
/* 658 */         chunkData.addProperty("z", Integer.valueOf(playerChunk.location.z));
/* 659 */         chunkData.addProperty("ticket-level", Integer.valueOf(playerChunk.getTicketLevel()));
/* 660 */         chunkData.addProperty("priority", Integer.valueOf(playerChunk.getCurrentPriority()));
/* 661 */         chunkData.addProperty("state", PlayerChunk.getChunkState(playerChunk.getTicketLevel()).toString());
/* 662 */         chunkData.addProperty("queued-for-unload", Boolean.valueOf(chunkMap.unloadQueue.contains(playerChunk.location.pair())));
/* 663 */         chunkData.addProperty("status", (status == null) ? "unloaded" : status.toString());
/*     */         
/* 665 */         JsonArray ticketsData = new JsonArray();
/*     */         
/* 667 */         if (tickets != null) {
/* 668 */           for (Ticket<?> ticket : tickets) {
/* 669 */             JsonObject ticketData = new JsonObject();
/*     */             
/* 671 */             ticketData.addProperty("ticket-type", ticket.getTicketType().toString());
/* 672 */             ticketData.addProperty("ticket-level", Integer.valueOf(ticket.getTicketLevel()));
/* 673 */             ticketData.addProperty("object-reason", String.valueOf(ticket.getObjectReason()));
/* 674 */             ticketData.addProperty("add-tick", Long.valueOf(ticket.getCreationTick()));
/*     */             
/* 676 */             ticketsData.add((JsonElement)ticketData);
/*     */           } 
/*     */         }
/*     */         
/* 680 */         chunkData.add("tickets", (JsonElement)ticketsData);
/* 681 */         chunksData.add((JsonElement)chunkData);
/*     */       } 
/*     */ 
/*     */       
/* 685 */       worldData.add("chunk-data", (JsonElement)chunksData);
/* 686 */       worldsData.add((JsonElement)worldData);
/*     */     } 
/*     */     
/* 689 */     data.add("worlds", (JsonElement)worldsData);
/*     */     
/* 691 */     StringWriter stringWriter = new StringWriter();
/* 692 */     JsonWriter jsonWriter = new JsonWriter(stringWriter);
/* 693 */     jsonWriter.setIndent(" ");
/* 694 */     jsonWriter.setLenient(false);
/* 695 */     Streams.write((JsonElement)data, jsonWriter);
/*     */     
/* 697 */     String fileData = stringWriter.toString();
/*     */     
/* 699 */     PrintStream out = new PrintStream(new FileOutputStream(file), false, "UTF-8"); 
/* 700 */     try { out.print(fileData);
/* 701 */       out.close(); }
/*     */     catch (Throwable throwable) { try { out.close(); }
/*     */       catch (Throwable throwable1)
/*     */       { throwable.addSuppressed(throwable1); }
/*     */        throw throwable; }
/* 706 */      } public static int getTicketLevelFor(ChunkStatus status) { return 33 + ChunkStatus.getTicketLevelOffset(status); }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MCUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */