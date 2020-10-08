/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import co.aikar.timings.Timing;
/*      */ import co.aikar.timings.TimingHistory;
/*      */ import com.destroystokyo.paper.io.PaperFileIOThread;
/*      */ import com.destroystokyo.paper.io.chunk.ChunkTaskManager;
/*      */ import com.destroystokyo.paper.server.ticklist.PaperTickList;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.tuinity.tuinity.util.CachedLists;
/*      */ import com.tuinity.tuinity.util.maplist.IteratorSafeOrderedReferenceSet;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*      */ import it.unimi.dsi.fastutil.ints.IntArrayList;
/*      */ import it.unimi.dsi.fastutil.longs.LongSet;
/*      */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.Writer;
/*      */ import java.nio.file.Files;
/*      */ import java.nio.file.Path;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.Executor;
/*      */ import java.util.function.BooleanSupplier;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.Function;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.function.Supplier;
/*      */ import javax.annotation.Nullable;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.WeatherType;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.generator.CustomChunkGenerator;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.BlockStateListPopulator;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.HumanEntity;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*      */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*      */ import org.bukkit.event.server.MapInitializeEvent;
/*      */ import org.bukkit.event.weather.LightningStrikeEvent;
/*      */ import org.bukkit.event.world.PortalCreateEvent;
/*      */ import org.bukkit.event.world.TimeSkipEvent;
/*      */ import org.bukkit.generator.ChunkGenerator;
/*      */ import org.bukkit.map.MapView;
/*      */ import org.spigotmc.ActivationRange;
/*      */ import org.spigotmc.AsyncCatcher;
/*      */ 
/*      */ public class WorldServer extends World implements GeneratorAccessSeed {
/*   58 */   public static final BlockPosition a = new BlockPosition(100, 50, 0);
/*   59 */   private static final Logger LOGGER = LogManager.getLogger(); public final Int2ObjectMap<Entity> entitiesById; final IteratorSafeOrderedReferenceSet<Entity> entitiesForIteration; private final Map<UUID, Entity> entitiesByUUID; private final Queue<Entity> entitiesToAdd; public final List<EntityPlayer> players; public final ChunkProviderServer chunkProvider; boolean tickingEntities; List<Runnable> afterEntityTickingTasks; private final MinecraftServer server; public final WorldDataServer worldDataServer; public boolean savingDisabled; private boolean everyoneSleeping; private int emptyTime; private final PortalTravelAgent portalTravelAgent; private final TickListServer<Block> nextTickListBlock; private final TickListServer<FluidType> nextTickListFluid; private final Set<NavigationAbstract> navigators; final IteratorSafeOrderedReferenceSet<NavigationAbstract> navigatorsForIteration; protected final PersistentRaid persistentRaid; private final ObjectLinkedOpenHashSet<BlockActionData> L; private boolean ticking; private final List<MobSpawner> mobSpawners; @Nullable
/*      */   private final EnderDragonBattle dragonBattle; private final StructureManager structureManager; private final boolean Q; private int tickPosition; public final Convertable.ConversionSession convertable; public final UUID uuid; boolean hasPhysicsEvent;
/*      */   public final PaperFileIOThread.ChunkDataController poiDataController;
/*      */   public final PaperFileIOThread.ChunkDataController chunkDataController;
/*      */   public final ChunkTaskManager asyncChunkTaskManager;
/*      */   long lastMidTickExecuteFailure;
/*      */   private final BlockPosition.MutableBlockPosition chunkTickMutablePosition;
/*      */   private final ThreadUnsafeRandom randomTickRandom;
/*      */   
/*      */   public void doIfNotEntityTicking(Runnable run) {
/*   69 */     if (this.tickingEntities) {
/*   70 */       this.afterEntityTickingTasks.add(run);
/*      */     } else {
/*   72 */       run.run();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Throwable getAddToWorldStackTrace(Entity entity) {
/*  101 */     return new Throwable(entity + " Added to world at " + new Date());
/*      */   }
/*      */   
/*      */   public Chunk getChunkIfLoaded(int x, int z) {
/*  105 */     return this.chunkProvider.getChunkAtIfLoadedImmediately(x, z);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isChunkLoaded(int x, int z) {
/*  183 */     return (getChunkProvider().getChunkAtIfLoadedImmediately(x, z) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public EntityHuman getPlayerByUUID(UUID uuid) {
/*  191 */     Entity player = this.entitiesByUUID.get(uuid);
/*  192 */     return (player instanceof EntityHuman) ? (EntityHuman)player : null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void onChunkSetTicking(int chunkX, int chunkZ) {
/*  198 */     if (PaperConfig.useOptimizedTickList) {
/*  199 */       ((PaperTickList)this.nextTickListBlock).onChunkSetTicking(chunkX, chunkZ);
/*  200 */       ((PaperTickList)this.nextTickListFluid).onChunkSetTicking(chunkX, chunkZ);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean areChunksLoadedForMove(AxisAlignedBB axisalignedbb) {
/*  210 */     int minBlockX = MathHelper.floor(axisalignedbb.minX - 1.0E-7D) - 3;
/*  211 */     int maxBlockX = MathHelper.floor(axisalignedbb.maxX + 1.0E-7D) + 3;
/*      */     
/*  213 */     int minBlockZ = MathHelper.floor(axisalignedbb.minZ - 1.0E-7D) - 3;
/*  214 */     int maxBlockZ = MathHelper.floor(axisalignedbb.maxZ + 1.0E-7D) + 3;
/*      */     
/*  216 */     int minChunkX = minBlockX >> 4;
/*  217 */     int maxChunkX = maxBlockX >> 4;
/*      */     
/*  219 */     int minChunkZ = minBlockZ >> 4;
/*  220 */     int maxChunkZ = maxBlockZ >> 4;
/*      */     
/*  222 */     ChunkProviderServer chunkProvider = getChunkProvider();
/*      */     
/*  224 */     for (int cx = minChunkX; cx <= maxChunkX; cx++) {
/*  225 */       for (int cz = minChunkZ; cz <= maxChunkZ; cz++) {
/*  226 */         if (chunkProvider.getChunkAtIfLoadedImmediately(cx, cz) == null) {
/*  227 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  232 */     return true;
/*      */   }
/*      */   
/*      */   public final void loadChunksForMoveAsync(AxisAlignedBB axisalignedbb, double toX, double toZ, Consumer<List<IChunkAccess>> onLoad)
/*      */   {
/*  237 */     if (Thread.currentThread() != this.serverThread) {
/*  238 */       (getChunkProvider()).serverThreadQueue.execute(() -> loadChunksForMoveAsync(axisalignedbb, toX, toZ, onLoad));
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  243 */     List<IChunkAccess> ret = new ArrayList<>();
/*  244 */     IntArrayList ticketLevels = new IntArrayList();
/*      */     
/*  246 */     int minBlockX = MathHelper.floor(axisalignedbb.minX - 1.0E-7D) - 3;
/*  247 */     int maxBlockX = MathHelper.floor(axisalignedbb.maxX + 1.0E-7D) + 3;
/*      */     
/*  249 */     int minBlockZ = MathHelper.floor(axisalignedbb.minZ - 1.0E-7D) - 3;
/*  250 */     int maxBlockZ = MathHelper.floor(axisalignedbb.maxZ + 1.0E-7D) + 3;
/*      */     
/*  252 */     int minChunkX = minBlockX >> 4;
/*  253 */     int maxChunkX = maxBlockX >> 4;
/*      */     
/*  255 */     int minChunkZ = minBlockZ >> 4;
/*  256 */     int maxChunkZ = maxBlockZ >> 4;
/*      */     
/*  258 */     ChunkProviderServer chunkProvider = getChunkProvider();
/*      */     
/*  260 */     int requiredChunks = (maxChunkX - minChunkX + 1) * (maxChunkZ - minChunkZ + 1);
/*  261 */     int[] loadedChunks = new int[1];
/*      */     
/*  263 */     Long holderIdentifier = Long.valueOf(chunkProvider.chunkFutureAwaitCounter++);
/*      */     
/*  265 */     Consumer<IChunkAccess> consumer = chunk -> {
/*      */         if (chunk != null) {
/*      */           int ticketLevel = Math.max(33, chunkProvider.playerChunkMap.getUpdatingChunk(chunk.getPos().pair()).getTicketLevel()); ret.add(chunk);
/*      */           ticketLevels.add(ticketLevel);
/*      */           chunkProvider.addTicketAtLevel(TicketType.FUTURE_AWAIT, chunk.getPos(), ticketLevel, holderIdentifier);
/*      */         } 
/*      */         loadedChunks[0] = loadedChunks[0] + 1;
/*      */         if (loadedChunks[0] + 1 == requiredChunks)
/*      */           try {
/*      */             onLoad.accept(Collections.unmodifiableList(ret));
/*      */           } finally {
/*      */             int i = 0;
/*      */             int len = ret.size();
/*      */             while (i < len) {
/*      */               ChunkCoordIntPair chunkPos = ((IChunkAccess)ret.get(i)).getPos();
/*      */               int ticketLevel = ticketLevels.getInt(i);
/*      */               chunkProvider.addTicketAtLevel(TicketType.UNKNOWN, chunkPos, ticketLevel, chunkPos);
/*      */               chunkProvider.removeTicketAtLevel(TicketType.FUTURE_AWAIT, chunkPos, ticketLevel, holderIdentifier);
/*      */               i++;
/*      */             } 
/*      */           }  
/*      */       };
/*  287 */     for (int cx = minChunkX; cx <= maxChunkX; cx++)
/*  288 */     { for (int cz = minChunkZ; cz <= maxChunkZ; cz++)
/*  289 */         chunkProvider.getChunkAtAsynchronously(cx, cz, ChunkStatus.FULL, true, false, consumer);  } 
/*      */   } public boolean collidesWithAnyBlockOrWorldBorder(@Nullable Entity entity, AxisAlignedBB axisalignedbb, boolean loadChunks) { if (entity != null && getWorldBorder().isCollidingOnBorderEdge(axisalignedbb)) return true;  int minBlockX = MathHelper.floor(axisalignedbb.minX - 1.0E-7D) - 1; int maxBlockX = MathHelper.floor(axisalignedbb.maxX + 1.0E-7D) + 1; int minBlockY = MathHelper.floor(axisalignedbb.minY - 1.0E-7D) - 1; int maxBlockY = MathHelper.floor(axisalignedbb.maxY + 1.0E-7D) + 1; int minBlockZ = MathHelper.floor(axisalignedbb.minZ - 1.0E-7D) - 1; int maxBlockZ = MathHelper.floor(axisalignedbb.maxZ + 1.0E-7D) + 1; BlockPosition.MutableBlockPosition mutablePos = new BlockPosition.MutableBlockPosition(); VoxelShapeCollision collisionShape = (entity == null) ? VoxelShapeCollision.a() : VoxelShapeCollision.a(entity); if (minBlockY > 255 || maxBlockY < 0)
/*      */       return false;  int minYIterate = Math.max(0, minBlockY); int maxYIterate = Math.min(255, maxBlockY); int minChunkX = minBlockX >> 4; int maxChunkX = maxBlockX >> 4; int minChunkZ = minBlockZ >> 4; int maxChunkZ = maxBlockZ >> 4; ChunkProviderServer chunkProvider = this.chunkProvider; for (int currChunkX = minChunkX; currChunkX <= maxChunkX; currChunkX++) { int minX = (currChunkX == minChunkX) ? (minBlockX & 0xF) : 0; int maxX = (currChunkX == maxChunkX) ? (maxBlockX & 0xF) : 15; for (int currChunkZ = minChunkZ; currChunkZ <= maxChunkZ; currChunkZ++) { int minZ = (currChunkZ == minChunkZ) ? (minBlockZ & 0xF) : 0; int maxZ = (currChunkZ == maxChunkZ) ? (maxBlockZ & 0xF) : 15; int chunkXGlobalPos = currChunkX << 4; int chunkZGlobalPos = currChunkZ << 4; Chunk chunk = loadChunks ? chunkProvider.getChunkAt(currChunkX, currChunkZ, true) : chunkProvider.getChunkAtIfLoadedImmediately(currChunkX, currChunkZ); if (chunk == null)
/*      */           return true;  ChunkSection[] sections = chunk.getSections(); for (int currY = minYIterate; currY <= maxYIterate; currY++) { ChunkSection section = sections[currY >>> 4]; if (section == null || section.isFullOfAir()) { currY = (currY & 0xFFFFFFF0) + 15; } else { DataPaletteBlock<IBlockData> blocks = section.blockIds; int blockKeyY = (currY & 0xF) << 8; int edgeCountY = (currY == minBlockY || currY == maxBlockY) ? 1 : 0; for (int currX = minX; currX <= maxX; currX++) { int edgeCountXY, blockKeyXY = blockKeyY | currX; int blockX = currX | chunkXGlobalPos; if (blockX == minBlockX || blockX == maxBlockX) { edgeCountXY = edgeCountY + 1; } else { edgeCountXY = edgeCountY; }  for (int currZ = minZ; currZ <= maxZ; currZ++) { int edgeCountFull, blockZ = currZ | chunkZGlobalPos; if (blockZ == minBlockZ || blockZ == maxBlockZ) { edgeCountFull = edgeCountXY + 1; } else { edgeCountFull = edgeCountXY; }  if (edgeCountFull != 3) { int blockKeyFull = blockKeyXY | currZ << 4; IBlockData blockData = blocks.rawGet(blockKeyFull); if (!blockData.isAir() && (edgeCountFull != 1 || blockData.shapeExceedsCube()) && (edgeCountFull != 2 || blockData.getBlock() == Blocks.MOVING_PISTON)) { mutablePos.setValues(blockX, currY, blockZ); VoxelShape voxelshape2 = blockData.getCollisionShape(this, mutablePos, collisionShape); if (voxelshape2 != VoxelShapes.getEmptyShape()) { VoxelShape voxelshape3 = voxelshape2.offset(blockX, currY, blockZ); if (voxelshape3.intersects(axisalignedbb))
/*      */                         return true;  }  }  }  }  }  }  }  }  }  return false; } public final boolean hardCollidesWithAnyEntities(@Nullable Entity entity, AxisAlignedBB axisalignedbb, @Nullable Predicate<Entity> predicate) { if (axisalignedbb.isEmpty())
/*      */       return false;  axisalignedbb = axisalignedbb.grow(1.0E-7D, 1.0E-7D, 1.0E-7D); List<Entity> entities = CachedLists.getTempGetEntitiesList(); try { if (entity != null && entity.hardCollides()) { getEntities(entity, axisalignedbb, predicate, entities); } else { getHardCollidingEntities(entity, axisalignedbb, predicate, entities); }  int i; int len; for (i = 0, len = entities.size(); i < len; i++) { Entity otherEntity = entities.get(i); if (entity == null || otherEntity.collisionBoxIsHard() || entity.hardCollidesWith(otherEntity))
/*      */           return true;  }  i = 0; return i; } finally { CachedLists.returnTempGetEntitiesList(entities); }  }
/*      */   public final boolean hasAnyCollisions(@Nullable Entity entity, AxisAlignedBB axisalignedbb) { return hasAnyCollisions(entity, axisalignedbb, true); }
/*      */   public final boolean hasAnyCollisions(@Nullable Entity entity, AxisAlignedBB axisalignedbb, boolean loadChunks) { return (collidesWithAnyBlockOrWorldBorder(entity, axisalignedbb, loadChunks) || hardCollidesWithAnyEntities(entity, axisalignedbb, (Predicate<Entity>)null)); }
/*      */   public void getCollisionsForBlocksOrWorldBorder(@Nullable Entity entity, AxisAlignedBB axisalignedbb, List<AxisAlignedBB> list, boolean loadChunks) { if (entity != null && getWorldBorder().isCollidingOnBorderEdge(axisalignedbb))
/*      */       VoxelShapes.addBoxesTo(getWorldBorder().getCollisionShape(), list);  int minBlockX = MathHelper.floor(axisalignedbb.minX - 1.0E-7D) - 1; int maxBlockX = MathHelper.floor(axisalignedbb.maxX + 1.0E-7D) + 1; int minBlockY = MathHelper.floor(axisalignedbb.minY - 1.0E-7D) - 1; int maxBlockY = MathHelper.floor(axisalignedbb.maxY + 1.0E-7D) + 1; int minBlockZ = MathHelper.floor(axisalignedbb.minZ - 1.0E-7D) - 1; int maxBlockZ = MathHelper.floor(axisalignedbb.maxZ + 1.0E-7D) + 1; BlockPosition.MutableBlockPosition mutablePos = new BlockPosition.MutableBlockPosition(); VoxelShapeCollision collisionShape = (entity == null) ? VoxelShapeCollision.a() : VoxelShapeCollision.a(entity); if (minBlockY > 255 || maxBlockY < 0)
/*      */       return;  int minYIterate = Math.max(0, minBlockY); int maxYIterate = Math.min(255, maxBlockY); int minChunkX = minBlockX >> 4; int maxChunkX = maxBlockX >> 4; int minChunkZ = minBlockZ >> 4; int maxChunkZ = maxBlockZ >> 4; ChunkProviderServer chunkProvider = this.chunkProvider; for (int currChunkX = minChunkX; currChunkX <= maxChunkX; currChunkX++) { int minX = (currChunkX == minChunkX) ? (minBlockX & 0xF) : 0; int maxX = (currChunkX == maxChunkX) ? (maxBlockX & 0xF) : 15; for (int currChunkZ = minChunkZ; currChunkZ <= maxChunkZ; currChunkZ++) { int minZ = (currChunkZ == minChunkZ) ? (minBlockZ & 0xF) : 0; int maxZ = (currChunkZ == maxChunkZ) ? (maxBlockZ & 0xF) : 15; int chunkXGlobalPos = currChunkX << 4; int chunkZGlobalPos = currChunkZ << 4; Chunk chunk = loadChunks ? chunkProvider.getChunkAt(currChunkX, currChunkZ, true) : chunkProvider.getChunkAtIfLoadedImmediately(currChunkX, currChunkZ); if (chunk == null) { list.add(AxisAlignedBB.getBoxForChunk(currChunkX, currChunkZ)); } else { ChunkSection[] sections = chunk.getSections(); for (int currY = minYIterate; currY <= maxYIterate; currY++) { ChunkSection section = sections[currY >>> 4]; if (section == null || section.isFullOfAir()) { currY = (currY & 0xFFFFFFF0) + 15; } else { DataPaletteBlock<IBlockData> blocks = section.blockIds; int blockKeyY = (currY & 0xF) << 8; int edgeCountY = (currY == minBlockY || currY == maxBlockY) ? 1 : 0; for (int currX = minX; currX <= maxX; currX++) { int edgeCountXY, blockKeyXY = blockKeyY | currX; int blockX = currX | chunkXGlobalPos; if (blockX == minBlockX || blockX == maxBlockX) { edgeCountXY = edgeCountY + 1; } else { edgeCountXY = edgeCountY; }  for (int currZ = minZ; currZ <= maxZ; currZ++) { int edgeCountFull, blockZ = currZ | chunkZGlobalPos; if (blockZ == minBlockZ || blockZ == maxBlockZ) { edgeCountFull = edgeCountXY + 1; } else { edgeCountFull = edgeCountXY; }  if (edgeCountFull != 3) { int blockKeyFull = blockKeyXY | currZ << 4; IBlockData blockData = blocks.rawGet(blockKeyFull); if (!blockData.isAir() && (edgeCountFull != 1 || blockData.shapeExceedsCube()) && (edgeCountFull != 2 || blockData.getBlock() == Blocks.MOVING_PISTON)) { mutablePos.setValues(blockX, currY, blockZ); VoxelShape voxelshape2 = blockData.getCollisionShape(this, mutablePos, collisionShape); if (voxelshape2 != VoxelShapes.getEmptyShape()) { VoxelShape voxelshape3 = voxelshape2.offset(blockX, currY, blockZ); VoxelShapes.addBoxesToIfIntersects(voxelshape3, axisalignedbb, list); }  }  }  }  }  }  }  }  }  }  }
/*  301 */   public WorldServer(MinecraftServer minecraftserver, Executor executor, Convertable.ConversionSession convertable_conversionsession, IWorldDataServer iworlddataserver, ResourceKey<World> resourcekey, DimensionManager dimensionmanager, WorldLoadListener worldloadlistener, ChunkGenerator chunkgenerator, boolean flag, long i, List<MobSpawner> list, boolean flag1, World.Environment env, ChunkGenerator gen) { super(iworlddataserver, resourcekey, dimensionmanager, minecraftserver::getMethodProfiler, false, flag, i, gen, env, executor);
/*      */     CustomChunkGenerator customChunkGenerator;
/*      */     this.entitiesById = (Int2ObjectMap<Entity>)new Int2ObjectLinkedOpenHashMap();
/*      */     this.entitiesForIteration = new IteratorSafeOrderedReferenceSet(2048, 0.5F, 2048, 0.2D, true);
/*      */     this.entitiesByUUID = Maps.newHashMap();
/*      */     this.entitiesToAdd = Queues.newArrayDeque();
/*      */     this.players = Lists.newArrayList();
/*      */     this.afterEntityTickingTasks = Lists.newArrayList();
/*      */     this.navigatorsForIteration = new IteratorSafeOrderedReferenceSet(2048, 0.5F, 2048, 0.2D, true);
/*      */     this.hasPhysicsEvent = true;
/*      */     this.poiDataController = new PaperFileIOThread.ChunkDataController()
/*      */       {
/*      */         public void writeData(int x, int z, NBTTagCompound compound) throws IOException {
/*      */           (WorldServer.this.getChunkProvider()).playerChunkMap.getVillagePlace().write(new ChunkCoordIntPair(x, z), compound);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public NBTTagCompound readData(int x, int z) throws IOException {
/*      */           return (WorldServer.this.getChunkProvider()).playerChunkMap.getVillagePlace().read(new ChunkCoordIntPair(x, z));
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public <T> T computeForRegionFile(int chunkX, int chunkZ, Function<RegionFile, T> function) {
/*      */           synchronized ((WorldServer.this.getChunkProvider()).playerChunkMap.getVillagePlace()) {
/*      */             RegionFile file;
/*      */             try {
/*      */               file = (WorldServer.this.getChunkProvider()).playerChunkMap.getVillagePlace().getFile(new ChunkCoordIntPair(chunkX, chunkZ), false);
/*      */             } catch (IOException ex) {
/*      */               throw new RuntimeException(ex);
/*      */             } 
/*      */             return function.apply(file);
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public <T> T computeForRegionFileIfLoaded(int chunkX, int chunkZ, Function<RegionFile, T> function) {
/*      */           synchronized ((WorldServer.this.getChunkProvider()).playerChunkMap.getVillagePlace()) {
/*      */             RegionFile file = (WorldServer.this.getChunkProvider()).playerChunkMap.getVillagePlace().getRegionFileIfLoaded(new ChunkCoordIntPair(chunkX, chunkZ));
/*      */             return function.apply(file);
/*      */           } 
/*      */         }
/*      */       };
/*      */     this.chunkDataController = new PaperFileIOThread.ChunkDataController()
/*      */       {
/*      */         public void writeData(int x, int z, NBTTagCompound compound) throws IOException {
/*      */           (WorldServer.this.getChunkProvider()).playerChunkMap.write(new ChunkCoordIntPair(x, z), compound);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public NBTTagCompound readData(int x, int z) throws IOException {
/*      */           return (WorldServer.this.getChunkProvider()).playerChunkMap.read(new ChunkCoordIntPair(x, z));
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public <T> T computeForRegionFile(int chunkX, int chunkZ, Function<RegionFile, T> function) {
/*      */           synchronized ((WorldServer.this.getChunkProvider()).playerChunkMap) {
/*      */             RegionFile file;
/*      */             try {
/*      */               file = (WorldServer.this.getChunkProvider()).playerChunkMap.regionFileCache.getFile(new ChunkCoordIntPair(chunkX, chunkZ), false);
/*      */             } catch (IOException ex) {
/*      */               throw new RuntimeException(ex);
/*      */             } 
/*      */             return function.apply(file);
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public <T> T computeForRegionFileIfLoaded(int chunkX, int chunkZ, Function<RegionFile, T> function) {
/*      */           synchronized ((WorldServer.this.getChunkProvider()).playerChunkMap) {
/*      */             RegionFile file = (WorldServer.this.getChunkProvider()).playerChunkMap.regionFileCache.getRegionFileIfLoaded(new ChunkCoordIntPair(chunkX, chunkZ));
/*      */             return function.apply(file);
/*      */           } 
/*      */         }
/*      */       };
/* 1023 */     this.chunkTickMutablePosition = new BlockPosition.MutableBlockPosition();
/* 1024 */     this.randomTickRandom = new ThreadUnsafeRandom(); this.pvpMode = minecraftserver.getPVP(); this.convertable = convertable_conversionsession; this.uuid = WorldUUID.getUUID(convertable_conversionsession.folder.toFile()); if (PaperConfig.useOptimizedTickList) { Objects.requireNonNull(IRegistry.BLOCK); this.nextTickListBlock = (TickListServer<Block>)new PaperTickList(this, block -> (block == null || block.getBlockData().isAir()), IRegistry.BLOCK::getKey, this::b, "Blocks"); Objects.requireNonNull(IRegistry.FLUID); this.nextTickListFluid = (TickListServer<FluidType>)new PaperTickList(this, fluidtype -> (fluidtype == null || fluidtype == FluidTypes.EMPTY), IRegistry.FLUID::getKey, this::a, "Fluids"); } else { Objects.requireNonNull(IRegistry.BLOCK); this.nextTickListBlock = new TickListServer<>(this, block -> (block == null || block.getBlockData().isAir()), IRegistry.BLOCK::getKey, this::b, "Blocks"); Objects.requireNonNull(IRegistry.FLUID); this.nextTickListFluid = new TickListServer<>(this, fluidtype -> (fluidtype == null || fluidtype == FluidTypes.EMPTY), IRegistry.FLUID::getKey, this::a, "Fluids"); }  this.navigators = Sets.newHashSet(); this.L = new ObjectLinkedOpenHashSet(); this.Q = flag1; this.server = minecraftserver; this.mobSpawners = list; this.worldDataServer = (WorldDataServer)iworlddataserver; this.worldDataServer.world = this; if (gen != null) customChunkGenerator = new CustomChunkGenerator(this, chunkgenerator, gen);  this.chunkProvider = new ChunkProviderServer(this, convertable_conversionsession, minecraftserver.getDataFixer(), minecraftserver.getDefinedStructureManager(), executor, (ChunkGenerator)customChunkGenerator, this.spigotConfig.viewDistance, minecraftserver.isSyncChunkWrites(), worldloadlistener, () -> minecraftserver.E().getWorldPersistentData()); this.portalTravelAgent = new PortalTravelAgent(this); P(); Q(); getWorldBorder().a(minecraftserver.at()); this.persistentRaid = getWorldPersistentData().<PersistentRaid>a(() -> new PersistentRaid(this), PersistentRaid.a(getDimensionManager())); if (!minecraftserver.isEmbeddedServer()) iworlddataserver.setGameType(minecraftserver.getGamemode());  this.structureManager = new StructureManager(this, this.worldDataServer.getGeneratorSettings()); if (getDimensionManager().isCreateDragonBattle()) { this.dragonBattle = new EnderDragonBattle(this, this.worldDataServer.getGeneratorSettings().getSeed(), this.worldDataServer.C()); } else { this.dragonBattle = null; }  getServer().addWorld((World)getWorld()); this.asyncChunkTaskManager = new ChunkTaskManager(this); } public final void getEntityHardCollisions(@Nullable Entity entity, AxisAlignedBB axisalignedbb, @Nullable Predicate<Entity> predicate, List<AxisAlignedBB> list) { if (axisalignedbb.isEmpty()) return;  axisalignedbb = axisalignedbb.grow(1.0E-7D, 1.0E-7D, 1.0E-7D); List<Entity> entities = CachedLists.getTempGetEntitiesList(); try { if (entity != null && entity.hardCollides()) { getEntities(entity, axisalignedbb, predicate, entities); } else { getHardCollidingEntities(entity, axisalignedbb, predicate, entities); }  for (int i = 0, len = entities.size(); i < len; i++) { Entity otherEntity = entities.get(i); if (entity == null || otherEntity.collisionBoxIsHard() || entity.hardCollidesWith(otherEntity)) list.add(otherEntity.getBoundingBox());  }  } finally { CachedLists.returnTempGetEntitiesList(entities); }  } public final void getCollisions(@Nullable Entity entity, AxisAlignedBB axisalignedbb, List<AxisAlignedBB> list, boolean loadChunks) { getCollisionsForBlocksOrWorldBorder(entity, axisalignedbb, list, loadChunks); getEntityHardCollisions(entity, axisalignedbb, (Predicate<Entity>)null, list); } public boolean getCubes(Entity entity) { return !hasAnyCollisions(entity, entity.getBoundingBox()); }
/*      */   public boolean getCubes(@Nullable Entity entity, AxisAlignedBB axisalignedbb) { if (entity instanceof EntityArmorStand && !entity.world.paperConfig.armorStandEntityLookups) return false;  return !hasAnyCollisions(entity, axisalignedbb); }
/*      */   public boolean getCubes(@Nullable Entity entity, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate) { if (entity instanceof EntityArmorStand && !entity.world.paperConfig.armorStandEntityLookups) return false;  return (!collidesWithAnyBlockOrWorldBorder(entity, axisalignedbb, true) && !hardCollidesWithAnyEntities(entity, axisalignedbb, predicate)); }
/* 1027 */   public void a(Chunk chunk, int i) { int randomTickSpeed = i;
/* 1028 */     ChunkCoordIntPair chunkcoordintpair = chunk.getPos();
/* 1029 */     boolean flag = isRaining();
/* 1030 */     int j = chunkcoordintpair.d();
/* 1031 */     int k = chunkcoordintpair.e();
/* 1032 */     GameProfilerFiller gameprofilerfiller = getMethodProfiler();
/*      */     
/* 1034 */     gameprofilerfiller.enter("thunder");
/* 1035 */     BlockPosition.MutableBlockPosition blockposition = this.chunkTickMutablePosition;
/*      */     
/* 1037 */     if (!this.paperConfig.disableThunder && flag && V() && this.random.nextInt(100000) == 0) {
/* 1038 */       blockposition.setValues(a(a(j, 0, k, 15)));
/* 1039 */       if (isRainingAt(blockposition)) {
/* 1040 */         DifficultyDamageScaler difficultydamagescaler = getDamageScaler(blockposition);
/* 1041 */         boolean flag1 = (getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && this.random.nextDouble() < difficultydamagescaler.b() * this.paperConfig.skeleHorseSpawnChance);
/*      */         
/* 1043 */         if (flag1) {
/* 1044 */           EntityHorseSkeleton entityhorseskeleton = EntityTypes.SKELETON_HORSE.a(this);
/*      */           
/* 1046 */           entityhorseskeleton.t(true);
/* 1047 */           entityhorseskeleton.setAgeRaw(0);
/* 1048 */           entityhorseskeleton.setPosition(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 1049 */           addEntity(entityhorseskeleton, CreatureSpawnEvent.SpawnReason.LIGHTNING);
/*      */         } 
/*      */         
/* 1052 */         EntityLightning entitylightning = EntityTypes.LIGHTNING_BOLT.a(this);
/*      */         
/* 1054 */         entitylightning.d(Vec3D.c(blockposition));
/* 1055 */         entitylightning.setEffect(flag1);
/* 1056 */         strikeLightning(entitylightning, LightningStrikeEvent.Cause.WEATHER);
/*      */       } 
/*      */     } 
/*      */     
/* 1060 */     gameprofilerfiller.exitEnter("iceandsnow");
/* 1061 */     if (!this.paperConfig.disableIceAndSnow && this.randomTickRandom.nextInt(16) == 0) {
/*      */       
/* 1063 */       getRandomBlockPosition(j, 0, k, 15, blockposition);
/* 1064 */       int normalY = chunk.getHighestBlockY(HeightMap.Type.MOTION_BLOCKING, blockposition.getX() & 0xF, blockposition.getZ() & 0xF);
/* 1065 */       int downY = normalY - 1;
/* 1066 */       blockposition.setY(normalY);
/*      */       
/* 1068 */       BiomeBase biomebase = getBiome(blockposition);
/*      */ 
/*      */       
/* 1071 */       blockposition.setY(downY);
/* 1072 */       if (biomebase.a(this, blockposition)) {
/* 1073 */         CraftEventFactory.handleBlockFormEvent(this, blockposition, Blocks.ICE.getBlockData(), null);
/*      */       }
/*      */ 
/*      */       
/* 1077 */       blockposition.setY(normalY);
/* 1078 */       if (flag && biomebase.b(this, blockposition)) {
/* 1079 */         CraftEventFactory.handleBlockFormEvent(this, blockposition, Blocks.SNOW.getBlockData(), null);
/*      */       }
/*      */ 
/*      */       
/* 1083 */       blockposition.setY(downY);
/* 1084 */       if (flag && getBiome(blockposition).c() == BiomeBase.Precipitation.RAIN) {
/* 1085 */         chunk.getType(blockposition).getBlock().c(this, blockposition);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1091 */     gameprofilerfiller.exit();
/* 1092 */     if (i > 0)
/* 1093 */     { gameprofilerfiller.enter("randomTick");
/* 1094 */       this.timings.chunkTicksBlocks.startTiming();
/*      */       
/* 1096 */       ChunkSection[] sections = chunk.getSections();
/*      */       
/* 1098 */       for (int sectionIndex = 0; sectionIndex < 16; sectionIndex++) {
/* 1099 */         ChunkSection section = sections[sectionIndex];
/* 1100 */         if (section != null && section.tickingList.size() != 0) {
/*      */ 
/*      */ 
/*      */           
/* 1104 */           int yPos = sectionIndex << 4;
/*      */           
/* 1106 */           for (int a = 0; a < randomTickSpeed; a++) {
/* 1107 */             int tickingBlocks = section.tickingList.size();
/* 1108 */             int index = this.randomTickRandom.nextInt(4096);
/* 1109 */             if (index < tickingBlocks) {
/*      */ 
/*      */ 
/*      */               
/* 1113 */               long raw = section.tickingList.getRaw(index);
/* 1114 */               int location = IBlockDataList.getLocationFromRaw(raw);
/* 1115 */               int randomX = location & 0xF;
/* 1116 */               int randomY = location >>> 8 & 0xFF | yPos;
/* 1117 */               int randomZ = location >>> 4 & 0xF;
/*      */               
/* 1119 */               BlockPosition blockposition2 = blockposition.setValues(j + randomX, randomY, k + randomZ);
/* 1120 */               IBlockData iblockdata = IBlockDataList.getBlockDataFromRaw(raw);
/*      */               
/* 1122 */               iblockdata.b(this, blockposition2, (Random)this.randomTickRandom);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1128 */       gameprofilerfiller.exit();
/* 1129 */       this.timings.chunkTicksBlocks.stopTiming();
/* 1130 */       getChunkProvider().getLightEngine().queueUpdate(); }  } protected TileEntity getTileEntity(BlockPosition pos, boolean validate) { TileEntity result = super.getTileEntity(pos, validate); if (!validate || Thread.currentThread() != this.serverThread) return result;  Block type = getType(pos).getBlock(); if (result != null && type != Blocks.AIR && !result.getTileType().isValidBlock(type))
/*      */       result = fixTileEntity(pos, type, result);  return result; }
/*      */   private TileEntity fixTileEntity(BlockPosition pos, Block type, TileEntity found) { getServer().getLogger().log(Level.SEVERE, "Block at {0}, {1}, {2} is {3} but has {4}. Bukkit will attempt to fix this, but there may be additional damage that we cannot recover.", new Object[] { Integer.valueOf(pos.getX()), Integer.valueOf(pos.getY()), Integer.valueOf(pos.getZ()), type, found }); if (type instanceof ITileEntity) { TileEntity replacement = ((ITileEntity)type).createTile(this); replacement.world = this; setTileEntity(pos, replacement); return replacement; }  return found; }
/*      */   public void a(int i, int j, boolean flag, boolean flag1) { this.worldDataServer.a(i); this.worldDataServer.setWeatherDuration(j); this.worldDataServer.setThunderDuration(j); this.worldDataServer.setStorm(flag); this.worldDataServer.setThundering(flag1); }
/*      */   public BiomeBase getBiomeBySeed(int i, int j, int k) { return a(i, j, k); }
/*      */   public BiomeBase a(int i, int j, int k) { return getChunkProvider().getChunkGenerator().getWorldChunkManager().getBiome(i, j, k); }
/* 1136 */   protected BlockPosition a(BlockPosition blockposition) { BlockPosition blockposition1 = getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, blockposition);
/* 1137 */     AxisAlignedBB axisalignedbb = (new AxisAlignedBB(blockposition1, new BlockPosition(blockposition1.getX(), getBuildHeight(), blockposition1.getZ()))).g(3.0D);
/* 1138 */     List<EntityLiving> list = a(EntityLiving.class, axisalignedbb, entityliving -> 
/* 1139 */         (entityliving != null && entityliving.isAlive() && e(entityliving.getChunkCoordinates())));
/*      */ 
/*      */     
/* 1142 */     if (!list.isEmpty()) {
/* 1143 */       return ((EntityLiving)list.get(this.random.nextInt(list.size()))).getChunkCoordinates();
/*      */     }
/* 1145 */     if (blockposition1.getY() == -1) {
/* 1146 */       blockposition1 = blockposition1.up(2);
/*      */     }
/*      */     
/* 1149 */     return blockposition1; }
/*      */   public StructureManager getStructureManager() { return this.structureManager; }
/*      */   public void doTick(BooleanSupplier booleansupplier) { GameProfilerFiller gameprofilerfiller = getMethodProfiler(); this.ticking = true; gameprofilerfiller.enter("world border"); getWorldBorder().s(); gameprofilerfiller.exitEnter("weather"); boolean flag = isRaining(); if (getDimensionManager().hasSkyLight()) { if (getGameRules().getBoolean(GameRules.DO_WEATHER_CYCLE)) { int i = this.worldDataServer.h(); int j = this.worldDataServer.getThunderDuration(); int k = this.worldDataServer.getWeatherDuration(); boolean flag1 = this.worldData.isThundering(); boolean flag2 = this.worldData.hasStorm(); if (i > 0) { i--; j = flag1 ? 0 : 1; k = flag2 ? 0 : 1; flag1 = false; flag2 = false; } else { if (j > 0) { j--; if (j == 0) flag1 = !flag1;  } else if (flag1) { j = this.random.nextInt(12000) + 3600; } else { j = this.random.nextInt(168000) + 12000; }  if (k > 0) { k--; if (k == 0) flag2 = !flag2;  } else if (flag2) { k = this.random.nextInt(12000) + 12000; } else { k = this.random.nextInt(168000) + 12000; }  }  this.worldDataServer.setThunderDuration(j); this.worldDataServer.setWeatherDuration(k); this.worldDataServer.a(i); this.worldDataServer.setThundering(flag1); this.worldDataServer.setStorm(flag2); }  this.lastThunderLevel = this.thunderLevel; if (this.worldData.isThundering()) { this.thunderLevel = (float)(this.thunderLevel + 0.01D); } else { this.thunderLevel = (float)(this.thunderLevel - 0.01D); }  this.thunderLevel = MathHelper.a(this.thunderLevel, 0.0F, 1.0F); this.lastRainLevel = this.rainLevel; if (this.worldData.hasStorm()) { this.rainLevel = (float)(this.rainLevel + 0.01D); } else { this.rainLevel = (float)(this.rainLevel - 0.01D); }  this.rainLevel = MathHelper.a(this.rainLevel, 0.0F, 1.0F); }  int idx; for (idx = 0; idx < this.players.size(); idx++) { if (((EntityPlayer)this.players.get(idx)).world == this) ((EntityPlayer)this.players.get(idx)).tickWeather();  }  if (flag != isRaining()) for (idx = 0; idx < this.players.size(); idx++) { if (((EntityPlayer)this.players.get(idx)).world == this) ((EntityPlayer)this.players.get(idx)).setPlayerWeather(!flag ? WeatherType.DOWNFALL : WeatherType.CLEAR, false);  }   for (idx = 0; idx < this.players.size(); idx++) { if (((EntityPlayer)this.players.get(idx)).world == this) ((EntityPlayer)this.players.get(idx)).updateWeather(this.lastRainLevel, this.rainLevel, this.lastThunderLevel, this.thunderLevel);  }  if (this.everyoneSleeping && this.players.stream().noneMatch(entityplayer -> (!entityplayer.isSpectator() && !entityplayer.isDeeplySleeping() && !entityplayer.fauxSleeping))) { long l = this.worldData.getDayTime() + 24000L; TimeSkipEvent event = new TimeSkipEvent((World)getWorld(), TimeSkipEvent.SkipReason.NIGHT_SKIP, l - l % 24000L - getDayTime()); if (getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE)) { getServer().getPluginManager().callEvent((Event)event); if (!event.isCancelled()) setDayTime(getDayTime() + event.getSkipAmount());  }  if (!event.isCancelled()) { this.everyoneSleeping = false; wakeupPlayers(); }  if (getGameRules().getBoolean(GameRules.DO_WEATHER_CYCLE)) clearWeather();  }  P(); b(); gameprofilerfiller.exitEnter("chunkSource"); this.timings.chunkProviderTick.startTiming(); getChunkProvider().tick(booleansupplier); this.timings.chunkProviderTick.stopTiming(); gameprofilerfiller.exitEnter("tickPending"); this.timings.scheduledBlocks.startTiming(); if (!isDebugWorld()) { this.nextTickListBlock.b(); this.nextTickListFluid.b(); }  this.timings.scheduledBlocks.stopTiming(); gameprofilerfiller.exitEnter("raid"); this.timings.raids.startTiming(); this.persistentRaid.a(); this.timings.raids.stopTiming(); gameprofilerfiller.exitEnter("blockEvents"); this.timings.doSounds.startTiming(); aj(); this.timings.doSounds.stopTiming(); this.ticking = false; gameprofilerfiller.exitEnter("entities"); boolean flag3 = true; if (flag3) resetEmptyTime();  if (flag3 || this.emptyTime++ < 300) { this.timings.tickEntities.startTiming(); if (this.dragonBattle != null) this.dragonBattle.b();  this.tickingEntities = true; IteratorSafeOrderedReferenceSet.Iterator<Entity> objectiterator = this.entitiesForIteration.iterator(); ActivationRange.activateEntities(this); this.timings.entityTick.startTiming(); while (objectiterator.hasNext()) { Entity entity = (Entity)objectiterator.next(); Entity entity1 = entity.getVehicle(); gameprofilerfiller.enter("checkDespawn"); if (!entity.dead) entity.checkDespawn();  gameprofilerfiller.exit(); if (entity1 != null) { if (!entity1.dead && entity1.w(entity)) continue;  entity.stopRiding(); }  gameprofilerfiller.enter("tick"); if (!entity.dead && !(entity instanceof EntityComplexPart)) a(this::entityJoinedWorld, entity);  gameprofilerfiller.exit(); gameprofilerfiller.enter("remove"); if (entity.dead) { removeEntityFromChunk(entity); this.entitiesById.remove(entity.getId()); unregisterEntity(entity); }  gameprofilerfiller.exit(); }  this.timings.entityTick.stopTiming(); objectiterator.finishedIterating(); this.tickingEntities = false; for (Runnable run : this.afterEntityTickingTasks) { try { run.run(); } catch (Exception e) { LOGGER.error("Error in After Entity Ticking Task", e); }  }  this.afterEntityTickingTasks.clear(); Entity entity2; while ((entity2 = this.entitiesToAdd.poll()) != null) { if (!entity2.isQueuedForRegister)
/*      */           continue;  registerEntity(entity2); }  this.timings.tickEntities.stopTiming(); tickBlockEntities(); }  gameprofilerfiller.exit(); }
/*      */   protected void b() { if (this.Q) { long i = this.worldData.getTime() + 1L; this.worldDataServer.setTime(i); this.nextTickListBlock.nextTick(); this.nextTickListFluid.nextTick(); this.worldDataServer.u().a(this.server, i); if (this.worldData.q().getBoolean(GameRules.DO_DAYLIGHT_CYCLE))
/* 1154 */         setDayTime(this.worldData.getDayTime() + 1L);  }  } public boolean m_() { return this.ticking; }
/*      */   public void setDayTime(long i) { this.worldDataServer.setDayTime(i); }
/*      */   public void doMobSpawning(boolean flag, boolean flag1) { Iterator<MobSpawner> iterator = this.mobSpawners.iterator(); while (iterator.hasNext()) { MobSpawner mobspawner = iterator.next(); mobspawner.a(this, flag, flag1); }
/*      */      }
/* 1158 */   private void wakeupPlayers() { ((List)this.players.stream().filter(EntityLiving::isSleeping).collect(Collectors.toList())).forEach(entityplayer -> entityplayer.wakeup(false, false)); } public void everyoneSleeping() { this.everyoneSleeping = false;
/* 1159 */     if (!this.players.isEmpty()) {
/* 1160 */       int i = 0;
/* 1161 */       int j = 0;
/* 1162 */       Iterator<EntityPlayer> iterator = this.players.iterator();
/*      */       
/* 1164 */       while (iterator.hasNext()) {
/* 1165 */         EntityPlayer entityplayer = iterator.next();
/*      */         
/* 1167 */         if (entityplayer.isSpectator() || (entityplayer.fauxSleeping && !entityplayer.isSleeping())) {
/* 1168 */           i++; continue;
/* 1169 */         }  if (entityplayer.isSleeping()) {
/* 1170 */           j++;
/*      */         }
/*      */       } 
/*      */       
/* 1174 */       this.everyoneSleeping = (j > 0 && j >= this.players.size() - i);
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ScoreboardServer getScoreboard() {
/* 1181 */     return this.server.getScoreboard();
/*      */   }
/*      */ 
/*      */   
/*      */   private void clearWeather() {
/* 1186 */     this.worldDataServer.setStorm(false);
/*      */ 
/*      */     
/* 1189 */     if (!this.worldDataServer.hasStorm()) {
/* 1190 */       this.worldDataServer.setWeatherDuration(0);
/*      */     }
/*      */     
/* 1193 */     this.worldDataServer.setThundering(false);
/*      */ 
/*      */ 
/*      */     
/* 1197 */     if (!this.worldDataServer.isThundering()) {
/* 1198 */       this.worldDataServer.setThunderDuration(0);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void resetEmptyTime() {
/* 1204 */     this.emptyTime = 0;
/*      */   }
/*      */   
/*      */   private void a(NextTickListEntry<FluidType> nextticklistentry) {
/* 1208 */     Fluid fluid = getFluid(nextticklistentry.a);
/*      */     
/* 1210 */     if (fluid.getType() == nextticklistentry.b()) {
/* 1211 */       fluid.a(this, nextticklistentry.a);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void b(NextTickListEntry<Block> nextticklistentry) {
/* 1217 */     IBlockData iblockdata = getType(nextticklistentry.a);
/*      */     
/* 1219 */     if (iblockdata.a(nextticklistentry.b())) {
/* 1220 */       iblockdata.a(this, nextticklistentry.a, this.random);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1226 */   static final ConcurrentLinkedDeque<Entity> currentlyTickingEntities = new ConcurrentLinkedDeque<>();
/*      */   
/*      */   public static List<Entity> getCurrentlyTickingEntities() {
/* 1229 */     List<Entity> ret = Lists.newArrayListWithCapacity(4);
/*      */     
/* 1231 */     for (Entity entity : currentlyTickingEntities) {
/* 1232 */       ret.add(entity);
/*      */     }
/*      */     
/* 1235 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void entityJoinedWorld(Entity entity) {
/* 1241 */     TickThread.ensureTickThread("Cannot tick an entity off-main");
/*      */     try {
/* 1243 */       currentlyTickingEntities.push(entity);
/*      */       
/* 1245 */       if (!(entity instanceof EntityHuman) && !getChunkProvider().a(entity)) {
/* 1246 */         chunkCheck(entity);
/*      */       } else {
/* 1248 */         TimingHistory.entityTicks++;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1260 */         boolean isActive = ActivationRange.checkIfActive(entity);
/* 1261 */         Timing timer = isActive ? (entity.getEntityType()).tickTimer.startTiming() : (entity.getEntityType()).inactiveTickTimer.startTiming();
/*      */ 
/*      */         
/* 1264 */         try { entity.g(entity.locX(), entity.locY(), entity.locZ());
/* 1265 */           entity.lastYaw = entity.yaw;
/* 1266 */           entity.lastPitch = entity.pitch;
/* 1267 */           if (entity.inChunk) {
/* 1268 */             entity.ticksLived++;
/* 1269 */             GameProfilerFiller gameprofilerfiller = getMethodProfiler();
/*      */             
/* 1271 */             gameprofilerfiller.a(() -> IRegistry.ENTITY_TYPE.getKey(entity.getEntityType()).toString());
/*      */ 
/*      */             
/* 1274 */             gameprofilerfiller.c("tickNonPassenger");
/* 1275 */             if (isActive)
/* 1276 */             { TimingHistory.activatedEntityTicks++;
/* 1277 */               entity.tick();
/* 1278 */               entity.postTick(); }
/* 1279 */             else { entity.inactiveTick(); }
/* 1280 */              gameprofilerfiller.exit();
/*      */           } 
/*      */           
/* 1283 */           chunkCheck(entity); }
/* 1284 */         finally { timer.stopTiming(); }
/* 1285 */          if (entity.inChunk) {
/* 1286 */           Iterator<Entity> iterator = entity.getPassengers().iterator();
/*      */           
/* 1288 */           while (iterator.hasNext()) {
/* 1289 */             Entity entity1 = iterator.next();
/*      */             
/* 1291 */             a(entity, entity1);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } 
/*      */     } finally {
/*      */       
/* 1299 */       currentlyTickingEntities.pop();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(Entity entity, Entity entity1) {
/* 1305 */     if (!entity1.dead && entity1.getVehicle() == entity) {
/* 1306 */       if (entity1 instanceof EntityHuman || getChunkProvider().a(entity1)) {
/*      */         
/* 1308 */         boolean isActive = ActivationRange.checkIfActive(entity1);
/* 1309 */         Timing timer = isActive ? (entity1.getEntityType()).passengerTickTimer.startTiming() : (entity1.getEntityType()).passengerInactiveTickTimer.startTiming();
/*      */ 
/*      */         
/* 1312 */         try { entity1.g(entity1.locX(), entity1.locY(), entity1.locZ());
/* 1313 */           entity1.lastYaw = entity1.yaw;
/* 1314 */           entity1.lastPitch = entity1.pitch;
/* 1315 */           if (entity1.inChunk) {
/* 1316 */             entity1.ticksLived++;
/* 1317 */             GameProfilerFiller gameprofilerfiller = getMethodProfiler();
/*      */             
/* 1319 */             gameprofilerfiller.a(() -> IRegistry.ENTITY_TYPE.getKey(entity1.getEntityType()).toString());
/*      */ 
/*      */             
/* 1322 */             gameprofilerfiller.c("tickPassenger");
/*      */             
/* 1324 */             if (isActive) {
/* 1325 */               entity1.passengerTick();
/*      */             } else {
/* 1327 */               entity1.setMot(Vec3D.ORIGIN);
/* 1328 */               entity1.inactiveTick();
/*      */               
/* 1330 */               entity.syncPositionOf(entity1);
/*      */             } 
/*      */             
/* 1333 */             gameprofilerfiller.exit();
/*      */           } 
/*      */           
/* 1336 */           chunkCheck(entity1);
/* 1337 */           if (entity1.inChunk) {
/* 1338 */             Iterator<Entity> iterator = entity1.getPassengers().iterator();
/*      */             
/* 1340 */             while (iterator.hasNext())
/* 1341 */             { Entity entity2 = iterator.next();
/*      */               
/* 1343 */               a(entity1, entity2); } 
/*      */           }  }
/* 1345 */         finally { timer.stopTiming(); }
/*      */       
/*      */       } 
/*      */     } else {
/* 1349 */       entity1.stopRiding();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void chunkCheck(Entity entity) {
/* 1354 */     if (entity.ck()) {
/* 1355 */       getMethodProfiler().enter("chunkCheck");
/* 1356 */       int i = MathHelper.floor(entity.locX() / 16.0D);
/* 1357 */       int j = Math.min(15, Math.max(0, MathHelper.floor(entity.locY() / 16.0D)));
/* 1358 */       int k = MathHelper.floor(entity.locZ() / 16.0D);
/*      */       
/* 1360 */       if (!entity.inChunk || entity.chunkX != i || entity.chunkY != j || entity.chunkZ != k) {
/*      */         
/* 1362 */         Chunk currentChunk = entity.getCurrentChunk();
/* 1363 */         if (currentChunk != null) {
/* 1364 */           currentChunk.removeEntity(entity);
/*      */         }
/*      */ 
/*      */         
/* 1368 */         if (entity.inChunk && isChunkLoaded(entity.chunkX, entity.chunkZ)) {
/* 1369 */           getChunkAt(entity.chunkX, entity.chunkZ).a(entity, entity.chunkY);
/*      */         }
/*      */         
/* 1372 */         if (!entity.valid && !entity.cj() && !isChunkLoaded(i, k)) {
/* 1373 */           if (entity.inChunk) {
/* 1374 */             LOGGER.warn("Entity {} left loaded chunk area", entity);
/*      */           }
/*      */           
/* 1377 */           entity.inChunk = false;
/*      */         } else {
/* 1379 */           getChunkAt(i, k).a(entity);
/*      */         } 
/*      */       } 
/*      */       
/* 1383 */       getMethodProfiler().exit();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(EntityHuman entityhuman, BlockPosition blockposition) {
/* 1389 */     return (!this.server.a(this, blockposition, entityhuman) && getWorldBorder().a(blockposition));
/*      */   }
/*      */   
/*      */   public void saveIncrementally(boolean doFull)
/*      */   {
/* 1394 */     ChunkProviderServer chunkproviderserver = getChunkProvider();
/*      */     
/* 1396 */     if (doFull) {
/* 1397 */       Bukkit.getPluginManager().callEvent((Event)new WorldSaveEvent((World)getWorld()));
/*      */     }
/*      */     
/* 1400 */     Timing ignored = this.timings.worldSave.startTiming(); 
/* 1401 */     try { if (doFull) {
/* 1402 */         saveData();
/*      */       }
/*      */       
/* 1405 */       this.timings.worldSaveChunks.startTiming();
/* 1406 */       if (!isSavingDisabled()) chunkproviderserver.saveIncrementally(); 
/* 1407 */       this.timings.worldSaveChunks.stopTiming();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1412 */       if (doFull) {
/* 1413 */         WorldServer worldserver1 = this;
/*      */         
/* 1415 */         this.worldDataServer.a(worldserver1.getWorldBorder().t());
/* 1416 */         this.worldDataServer.setCustomBossEvents(this.server.getBossBattleCustomData().save());
/* 1417 */         this.convertable.a(this.server.customRegistry, this.worldDataServer, this.server.getPlayerList().save());
/*      */       } 
/*      */       
/* 1420 */       if (ignored != null) ignored.close();  }
/*      */     catch (Throwable throwable) { if (ignored != null)
/*      */         try { ignored.close(); }
/*      */         catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */           throw throwable; }
/* 1425 */      } public void save(@Nullable IProgressUpdate iprogressupdate, boolean flag, boolean flag1) { ChunkProviderServer chunkproviderserver = getChunkProvider();
/*      */     
/* 1427 */     if (!flag1) {
/* 1428 */       if (flag) Bukkit.getPluginManager().callEvent((Event)new WorldSaveEvent((World)getWorld())); 
/* 1429 */       Timing ignored = this.timings.worldSave.startTiming(); 
/* 1430 */       try { if (iprogressupdate != null) {
/* 1431 */           iprogressupdate.a(new ChatMessage("menu.savingLevel"));
/*      */         }
/*      */         
/* 1434 */         ai();
/* 1435 */         if (iprogressupdate != null) {
/* 1436 */           iprogressupdate.c(new ChatMessage("menu.savingChunks"));
/*      */         }
/*      */         
/* 1439 */         this.timings.worldSaveChunks.startTiming();
/* 1440 */         chunkproviderserver.save(flag);
/* 1441 */         this.timings.worldSaveChunks.stopTiming();
/* 1442 */         if (ignored != null) ignored.close();  } catch (Throwable throwable) { if (ignored != null)
/*      */           try { ignored.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */             throw throwable; }
/*      */     
/* 1446 */     }  WorldServer worldserver1 = this;
/*      */     
/* 1448 */     this.worldDataServer.a(worldserver1.getWorldBorder().t());
/* 1449 */     this.worldDataServer.setCustomBossEvents(this.server.getBossBattleCustomData().save());
/* 1450 */     this.convertable.a(this.server.customRegistry, this.worldDataServer, this.server.getPlayerList().save()); }
/*      */ 
/*      */   
/*      */   private void saveData() {
/* 1454 */     ai();
/*      */   } private void ai() {
/* 1456 */     if (this.dragonBattle != null) {
/* 1457 */       this.worldDataServer.a(this.dragonBattle.a());
/*      */     }
/*      */     
/* 1460 */     getChunkProvider().getWorldPersistentData().a();
/*      */   }
/*      */   
/*      */   public List<Entity> a(@Nullable EntityTypes<?> entitytypes, Predicate<? super Entity> predicate) {
/* 1464 */     List<Entity> list = Lists.newArrayList();
/* 1465 */     ChunkProviderServer chunkproviderserver = getChunkProvider();
/* 1466 */     ObjectIterator objectiterator = this.entitiesById.values().iterator();
/*      */     
/* 1468 */     while (objectiterator.hasNext()) {
/* 1469 */       Entity entity = (Entity)objectiterator.next();
/*      */       
/* 1471 */       if ((entitytypes == null || entity.getEntityType() == entitytypes) && chunkproviderserver.isLoaded(MathHelper.floor(entity.locX()) >> 4, MathHelper.floor(entity.locZ()) >> 4) && predicate.test(entity)) {
/* 1472 */         list.add(entity);
/*      */       }
/*      */     } 
/*      */     
/* 1476 */     return list;
/*      */   }
/*      */   
/*      */   public List<EntityEnderDragon> g() {
/* 1480 */     List<EntityEnderDragon> list = Lists.newArrayList();
/* 1481 */     ObjectIterator objectiterator = this.entitiesById.values().iterator();
/*      */     
/* 1483 */     while (objectiterator.hasNext()) {
/* 1484 */       Entity entity = (Entity)objectiterator.next();
/*      */       
/* 1486 */       if (entity instanceof EntityEnderDragon && entity.isAlive()) {
/* 1487 */         list.add((EntityEnderDragon)entity);
/*      */       }
/*      */     } 
/*      */     
/* 1491 */     return list;
/*      */   }
/*      */   
/*      */   public List<EntityPlayer> a(Predicate<? super EntityPlayer> predicate) {
/* 1495 */     List<EntityPlayer> list = Lists.newArrayList();
/* 1496 */     Iterator<EntityPlayer> iterator = this.players.iterator();
/*      */     
/* 1498 */     while (iterator.hasNext()) {
/* 1499 */       EntityPlayer entityplayer = iterator.next();
/*      */       
/* 1501 */       if (predicate.test(entityplayer)) {
/* 1502 */         list.add(entityplayer);
/*      */       }
/*      */     } 
/*      */     
/* 1506 */     return list;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityPlayer q_() {
/* 1511 */     List<EntityPlayer> list = a(EntityLiving::isAlive);
/*      */     
/* 1513 */     return list.isEmpty() ? null : list.get(this.random.nextInt(list.size()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addEntity(Entity entity) {
/* 1519 */     return addEntity0(entity, CreatureSpawnEvent.SpawnReason.DEFAULT);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
/* 1524 */     return addEntity0(entity, reason);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addEntitySerialized(Entity entity) {
/* 1530 */     return addEntitySerialized(entity, CreatureSpawnEvent.SpawnReason.DEFAULT);
/*      */   }
/*      */   
/*      */   public boolean addEntitySerialized(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
/* 1534 */     return addEntity0(entity, reason);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addEntityTeleport(Entity entity) {
/* 1539 */     boolean flag = entity.attachedToPlayer;
/*      */     
/* 1541 */     entity.attachedToPlayer = true;
/* 1542 */     addEntitySerialized(entity);
/* 1543 */     entity.attachedToPlayer = flag;
/* 1544 */     chunkCheck(entity);
/*      */   }
/*      */   
/*      */   public void addPlayerCommand(EntityPlayer entityplayer) {
/* 1548 */     addPlayer0(entityplayer);
/* 1549 */     chunkCheck(entityplayer);
/*      */   }
/*      */   
/*      */   public void addPlayerPortal(EntityPlayer entityplayer) {
/* 1553 */     addPlayer0(entityplayer);
/* 1554 */     chunkCheck(entityplayer);
/*      */   }
/*      */   
/*      */   public void addPlayerJoin(EntityPlayer entityplayer) {
/* 1558 */     addPlayer0(entityplayer);
/*      */   }
/*      */   
/*      */   public void addPlayerRespawn(EntityPlayer entityplayer) {
/* 1562 */     addPlayer0(entityplayer);
/*      */   }
/*      */   
/*      */   private void addPlayer0(EntityPlayer entityplayer) {
/* 1566 */     Entity entity = this.entitiesByUUID.get(entityplayer.getUniqueID());
/*      */     
/* 1568 */     if (entity != null) {
/* 1569 */       LOGGER.warn("Force-added player with duplicate UUID {}", entityplayer.getUniqueID().toString());
/* 1570 */       entity.decouple();
/* 1571 */       removePlayer((EntityPlayer)entity);
/*      */     } 
/*      */     
/* 1574 */     this.players.add(entityplayer);
/* 1575 */     everyoneSleeping();
/* 1576 */     IChunkAccess ichunkaccess = getChunkAt(MathHelper.floor(entityplayer.locX() / 16.0D), MathHelper.floor(entityplayer.locZ() / 16.0D), ChunkStatus.FULL, true);
/*      */     
/* 1578 */     if (ichunkaccess instanceof Chunk) {
/* 1579 */       ichunkaccess.a(entityplayer);
/*      */     }
/*      */     
/* 1582 */     registerEntity(entityplayer);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean addEntity0(Entity entity, CreatureSpawnEvent.SpawnReason spawnReason) {
/* 1587 */     AsyncCatcher.catchOp("entity add");
/* 1588 */     if (entity.spawnReason == null) entity.spawnReason = spawnReason;
/*      */     
/* 1590 */     if (entity.valid) {
/* 1591 */       MinecraftServer.LOGGER.error("Attempted Double World add on " + entity, new Throwable());
/*      */       
/* 1593 */       if (DEBUG_ENTITIES) {
/* 1594 */         Throwable thr = entity.addedToWorldStack;
/* 1595 */         if (thr == null) {
/* 1596 */           MinecraftServer.LOGGER.error("Double add entity has no add stacktrace");
/*      */         } else {
/* 1598 */           MinecraftServer.LOGGER.error("Double add stacktrace: ", thr);
/*      */         } 
/*      */       } 
/* 1601 */       return true;
/*      */     } 
/*      */     
/* 1604 */     if (entity.dead) {
/*      */       
/* 1606 */       if (DEBUG_ENTITIES) {
/* 1607 */         (new Throwable("Tried to add entity " + entity + " but it was marked as removed already")).printStackTrace();
/* 1608 */         getAddToWorldStackTrace(entity).printStackTrace();
/*      */       } 
/*      */ 
/*      */       
/* 1612 */       return false;
/* 1613 */     }  if (isUUIDTaken(entity)) {
/* 1614 */       return false;
/*      */     }
/*      */     
/* 1617 */     if (this.captureDrops != null && entity instanceof EntityItem) {
/* 1618 */       this.captureDrops.add((EntityItem)entity);
/* 1619 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1623 */     if (!CraftEventFactory.doEntityAddEventCalling(this, entity, spawnReason)) {
/* 1624 */       return false;
/*      */     }
/*      */     
/* 1627 */     IChunkAccess ichunkaccess = getChunkAt(MathHelper.floor(entity.locX() / 16.0D), MathHelper.floor(entity.locZ() / 16.0D), ChunkStatus.FULL, true);
/*      */     
/* 1629 */     if (!(ichunkaccess instanceof Chunk)) {
/* 1630 */       return false;
/*      */     }
/* 1632 */     ichunkaccess.a(entity);
/* 1633 */     registerEntity(entity);
/* 1634 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addEntityChunk(Entity entity) {
/* 1640 */     if (isUUIDTaken(entity)) {
/* 1641 */       return false;
/*      */     }
/* 1643 */     registerEntity(entity);
/* 1644 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isUUIDTaken(Entity entity) {
/* 1649 */     UUID uuid = entity.getUniqueID();
/* 1650 */     Entity entity1 = c(uuid);
/*      */     
/* 1652 */     if (entity1 == null) {
/* 1653 */       return false;
/*      */     }
/*      */     
/* 1656 */     if (entity1.dead) {
/* 1657 */       unregisterEntity(entity1);
/* 1658 */       return false;
/*      */     } 
/*      */     
/* 1661 */     LOGGER.warn("Trying to add entity with duplicated UUID {}. Existing {}#{}, new: {}#{}", uuid, EntityTypes.getName(entity1.getEntityType()), Integer.valueOf(entity1.getId()), EntityTypes.getName(entity.getEntityType()), Integer.valueOf(entity.getId()));
/*      */     
/* 1663 */     if (DEBUG_ENTITIES && entity.world.paperConfig.duplicateUUIDMode != PaperWorldConfig.DuplicateUUIDMode.NOTHING) {
/* 1664 */       if (entity1.addedToWorldStack != null) {
/* 1665 */         entity1.addedToWorldStack.printStackTrace();
/*      */       }
/*      */       
/* 1668 */       getAddToWorldStackTrace(entity).printStackTrace();
/*      */     } 
/*      */     
/* 1671 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   private Entity c(UUID uuid) {
/* 1677 */     Entity entity = this.entitiesByUUID.get(uuid);
/*      */     
/* 1679 */     if (entity != null) {
/* 1680 */       return entity;
/*      */     }
/* 1682 */     if (this.tickingEntities) {
/* 1683 */       Iterator<Entity> iterator = this.entitiesToAdd.iterator();
/*      */       
/* 1685 */       while (iterator.hasNext()) {
/* 1686 */         Entity entity1 = iterator.next();
/*      */         
/* 1688 */         if (entity1.getUniqueID().equals(uuid)) {
/* 1689 */           return entity1;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1694 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAllEntitiesSafely(Entity entity) {
/* 1700 */     return addAllEntitiesSafely(entity, CreatureSpawnEvent.SpawnReason.DEFAULT);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addAllEntitiesSafely(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
/* 1705 */     if (entity.co().anyMatch(this::isUUIDTaken)) {
/* 1706 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1710 */     addAllEntities(entity, reason);
/* 1711 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unloadChunk(Chunk chunk) {
/* 1718 */     for (TileEntity tileentity : chunk.getTileEntities().values()) {
/* 1719 */       if (tileentity instanceof IInventory) {
/* 1720 */         for (HumanEntity h : Lists.newArrayList(((IInventory)tileentity).getViewers())) {
/* 1721 */           h.closeInventory(InventoryCloseEvent.Reason.UNLOADED);
/*      */         }
/*      */       }
/*      */     } 
/*      */     
/* 1726 */     this.tileEntityListUnload.addAll(chunk.getTileEntities().values());
/* 1727 */     List[] aentityslice = (List[])chunk.getEntitySlices();
/* 1728 */     int i = aentityslice.length;
/*      */     
/* 1730 */     List<Entity> toMoveChunks = new ArrayList<>();
/* 1731 */     for (int j = 0; j < i; j++) {
/* 1732 */       List<Entity> entityslice = aentityslice[j];
/* 1733 */       Iterator<Entity> iterator = entityslice.iterator();
/*      */       
/* 1735 */       while (iterator.hasNext()) {
/* 1736 */         Entity entity = iterator.next();
/*      */         
/* 1738 */         if (!(entity instanceof EntityPlayer)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1744 */           if ((!entity.dead && (int)Math.floor(entity.locX()) >> 4 != (chunk.getPos()).x) || (int)Math.floor(entity.locZ()) >> 4 != (chunk.getPos()).z) {
/* 1745 */             toMoveChunks.add(entity);
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/* 1750 */           this.entitiesById.remove(entity.getId());
/* 1751 */           unregisterEntity(entity);
/*      */           
/* 1753 */           if (entity.dead) iterator.remove();
/*      */         
/*      */         } 
/*      */       } 
/*      */     } 
/* 1758 */     for (Entity entity : toMoveChunks) {
/* 1759 */       chunkCheck(entity);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void unregisterEntity(Entity entity) {
/* 1766 */     AsyncCatcher.catchOp("entity unregister");
/* 1767 */     this.entitiesForIteration.remove(entity);
/*      */     
/* 1769 */     if (entity instanceof EntityComplexPart) {
/*      */ 
/*      */       
/* 1772 */       this.entitiesById.remove(entity.getId(), entity);
/*      */       return;
/*      */     } 
/* 1775 */     if (!entity.valid) {
/*      */       
/* 1777 */       entity.isQueuedForRegister = false;
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1782 */     if (entity instanceof EntityHuman)
/*      */     {
/* 1784 */       (getMinecraftServer()).worldServer.values().stream().map(WorldServer::getWorldPersistentData).forEach(worldData -> {
/*      */             for (PersistentBase o : worldData.data.values()) {
/*      */               if (o instanceof WorldMap) {
/*      */                 WorldMap map = (WorldMap)o;
/*      */ 
/*      */                 
/*      */                 map.humans.remove(entity);
/*      */                 
/*      */                 Iterator<WorldMap.WorldMapHumanTracker> iter = map.i.iterator();
/*      */                 
/*      */                 while (iter.hasNext()) {
/*      */                   if (((WorldMap.WorldMapHumanTracker)iter.next()).trackee == entity) {
/*      */                     map.decorations.remove(entity.getDisplayName().getString());
/*      */                     
/*      */                     iter.remove();
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           });
/*      */     }
/*      */     
/* 1806 */     if (entity.getBukkitEntity() instanceof InventoryHolder) {
/* 1807 */       for (HumanEntity h : Lists.newArrayList(((InventoryHolder)entity.getBukkitEntity()).getInventory().getViewers())) {
/* 1808 */         h.closeInventory(InventoryCloseEvent.Reason.UNLOADED);
/*      */       }
/*      */     }
/*      */     
/* 1812 */     if (entity instanceof EntityEnderDragon) {
/* 1813 */       EntityComplexPart[] aentitycomplexpart = ((EntityEnderDragon)entity).eJ();
/* 1814 */       int i = aentitycomplexpart.length;
/*      */       
/* 1816 */       for (int j = 0; j < i; j++) {
/* 1817 */         EntityComplexPart entitycomplexpart = aentitycomplexpart[j];
/*      */         
/* 1819 */         entitycomplexpart.die();
/*      */       } 
/*      */     } 
/*      */     
/* 1823 */     this.entitiesByUUID.remove(entity.getUniqueID());
/* 1824 */     getChunkProvider().removeEntity(entity);
/* 1825 */     if (entity instanceof EntityPlayer) {
/* 1826 */       EntityPlayer entityplayer = (EntityPlayer)entity;
/*      */       
/* 1828 */       this.players.remove(entityplayer);
/*      */     } 
/*      */     
/* 1831 */     getScoreboard().a(entity);
/*      */     
/* 1833 */     if (entity instanceof EntityDrowned) {
/*      */       
/* 1835 */       this.navigators.remove(((EntityDrowned)entity).navigationWater); this.navigatorsForIteration.remove(((EntityDrowned)entity).navigationWater);
/* 1836 */       this.navigators.remove(((EntityDrowned)entity).navigationLand); this.navigatorsForIteration.remove(((EntityDrowned)entity).navigationLand);
/*      */ 
/*      */     
/*      */     }
/* 1840 */     else if (entity instanceof EntityInsentient) {
/*      */       
/* 1842 */       this.navigators.remove(((EntityInsentient)entity).getNavigation()); this.navigatorsForIteration.remove(((EntityInsentient)entity).getNavigation());
/*      */     } 
/*      */     
/* 1845 */     (new EntityRemoveFromWorldEvent((Entity)entity.getBukkitEntity())).callEvent();
/* 1846 */     entity.valid = false;
/*      */   }
/*      */   
/*      */   private void registerEntity(Entity entity) {
/* 1850 */     AsyncCatcher.catchOp("entity register");
/*      */ 
/*      */     
/* 1853 */     if (this.entitiesById.get(entity.getId()) == entity) {
/* 1854 */       LOGGER.error(entity + " was already registered!");
/* 1855 */       (new Throwable()).printStackTrace();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1865 */     entity.isQueuedForRegister = false;
/* 1866 */     this.entitiesById.put(entity.getId(), entity);
/* 1867 */     this.entitiesForIteration.add(entity);
/* 1868 */     if (entity instanceof EntityEnderDragon) {
/* 1869 */       EntityComplexPart[] aentitycomplexpart = ((EntityEnderDragon)entity).eJ();
/* 1870 */       int i = aentitycomplexpart.length;
/*      */       
/* 1872 */       for (int j = 0; j < i; j++) {
/* 1873 */         EntityComplexPart entitycomplexpart = aentitycomplexpart[j];
/*      */         
/* 1875 */         this.entitiesById.put(entitycomplexpart.getId(), entitycomplexpart);
/* 1876 */         this.entitiesForIteration.add(entitycomplexpart);
/*      */       } 
/*      */     } 
/*      */     
/* 1880 */     if (DEBUG_ENTITIES) {
/* 1881 */       entity.addedToWorldStack = getAddToWorldStackTrace(entity);
/*      */     }
/*      */     
/* 1884 */     Entity old = this.entitiesByUUID.put(entity.getUniqueID(), entity);
/* 1885 */     if (old != null && old.getId() != entity.getId() && old.valid) {
/* 1886 */       Logger logger = LogManager.getLogger();
/* 1887 */       logger.error("Overwrote an existing entity " + old + " with " + entity);
/* 1888 */       if (DEBUG_ENTITIES) {
/* 1889 */         if (old.addedToWorldStack != null) {
/* 1890 */           old.addedToWorldStack.printStackTrace();
/*      */         } else {
/* 1892 */           logger.error("Oddly, the old entity was not added to the world in the normal way. Plugins?");
/*      */         } 
/* 1894 */         entity.addedToWorldStack.printStackTrace();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1900 */     if (entity instanceof EntityDrowned) {
/*      */       
/* 1902 */       this.navigators.add(((EntityDrowned)entity).navigationWater); this.navigatorsForIteration.add(((EntityDrowned)entity).navigationWater);
/* 1903 */       this.navigators.add(((EntityDrowned)entity).navigationLand); this.navigatorsForIteration.add(((EntityDrowned)entity).navigationLand);
/*      */ 
/*      */     
/*      */     }
/* 1907 */     else if (entity instanceof EntityInsentient) {
/*      */       
/* 1909 */       this.navigators.add(((EntityInsentient)entity).getNavigation()); this.navigatorsForIteration.add(((EntityInsentient)entity).getNavigation());
/*      */     } 
/*      */     
/* 1912 */     entity.valid = true;
/* 1913 */     getChunkProvider().addEntity(entity);
/*      */     
/* 1915 */     if (entity.origin == null) {
/* 1916 */       entity.origin = entity.getBukkitEntity().getLocation();
/*      */     }
/*      */     
/* 1919 */     entity.shouldBeRemoved = false;
/* 1920 */     (new EntityAddToWorldEvent((Entity)entity.getBukkitEntity())).callEvent();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeEntity(Entity entity) {
/* 1929 */     removeEntityFromChunk(entity);
/* 1930 */     this.entitiesById.remove(entity.getId());
/* 1931 */     unregisterEntity(entity);
/* 1932 */     entity.shouldBeRemoved = true;
/*      */   }
/*      */ 
/*      */   
/*      */   private void removeEntityFromChunk(Entity entity) {
/* 1937 */     Chunk ichunkaccess = entity.getCurrentChunk();
/*      */     
/* 1939 */     if (ichunkaccess != null) {
/* 1940 */       ichunkaccess.b(entity);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void removePlayer(EntityPlayer entityplayer) {
/* 1946 */     entityplayer.die();
/* 1947 */     removeEntity(entityplayer);
/* 1948 */     everyoneSleeping();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean strikeLightning(Entity entitylightning) {
/* 1953 */     return strikeLightning(entitylightning, LightningStrikeEvent.Cause.UNKNOWN);
/*      */   }
/*      */   
/*      */   public boolean strikeLightning(Entity entitylightning, LightningStrikeEvent.Cause cause) {
/* 1957 */     LightningStrikeEvent lightning = new LightningStrikeEvent((World)getWorld(), (LightningStrike)entitylightning.getBukkitEntity(), cause);
/* 1958 */     getServer().getPluginManager().callEvent((Event)lightning);
/*      */     
/* 1960 */     if (lightning.isCancelled()) {
/* 1961 */       return false;
/*      */     }
/*      */     
/* 1964 */     return addEntity(entitylightning);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(int i, BlockPosition blockposition, int j) {
/* 1970 */     Iterator<EntityPlayer> iterator = this.server.getPlayerList().getPlayers().iterator();
/*      */ 
/*      */     
/* 1973 */     EntityHuman entityhuman = null;
/* 1974 */     Entity entity = getEntity(i);
/* 1975 */     if (entity instanceof EntityHuman) entityhuman = (EntityHuman)entity;
/*      */ 
/*      */     
/* 1978 */     while (iterator.hasNext()) {
/* 1979 */       EntityPlayer entityplayer = iterator.next();
/*      */       
/* 1981 */       if (entityplayer != null && entityplayer.world == this && entityplayer.getId() != i) {
/* 1982 */         double d0 = blockposition.getX() - entityplayer.locX();
/* 1983 */         double d1 = blockposition.getY() - entityplayer.locY();
/* 1984 */         double d2 = blockposition.getZ() - entityplayer.locZ();
/*      */ 
/*      */         
/* 1987 */         if (entityhuman != null && entityhuman instanceof EntityPlayer && !entityplayer.getBukkitEntity().canSee((Player)((EntityPlayer)entityhuman).getBukkitEntity())) {
/*      */           continue;
/*      */         }
/*      */ 
/*      */         
/* 1992 */         if (d0 * d0 + d1 * d1 + d2 * d2 < 1024.0D) {
/* 1993 */           entityplayer.playerConnection.sendPacket(new PacketPlayOutBlockBreakAnimation(i, blockposition, j));
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSound(@Nullable EntityHuman entityhuman, double d0, double d1, double d2, SoundEffect soundeffect, SoundCategory soundcategory, float f, float f1) {
/* 2002 */     this.server.getPlayerList().sendPacketNearby(entityhuman, d0, d1, d2, (f > 1.0F) ? (16.0F * f) : 16.0D, getDimensionKey(), new PacketPlayOutNamedSoundEffect(soundeffect, soundcategory, d0, d1, d2, f, f1));
/*      */   }
/*      */ 
/*      */   
/*      */   public void playSound(@Nullable EntityHuman entityhuman, Entity entity, SoundEffect soundeffect, SoundCategory soundcategory, float f, float f1) {
/* 2007 */     this.server.getPlayerList().sendPacketNearby(entityhuman, entity.locX(), entity.locY(), entity.locZ(), (f > 1.0F) ? (16.0F * f) : 16.0D, getDimensionKey(), new PacketPlayOutEntitySound(soundeffect, soundcategory, entity, f, f1));
/*      */   }
/*      */ 
/*      */   
/*      */   public void b(int i, BlockPosition blockposition, int j) {
/* 2012 */     this.server.getPlayerList().sendAll(new PacketPlayOutWorldEvent(i, blockposition, j, true));
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(@Nullable EntityHuman entityhuman, int i, BlockPosition blockposition, int j) {
/* 2017 */     this.server.getPlayerList().sendPacketNearby(entityhuman, blockposition.getX(), blockposition.getY(), blockposition.getZ(), 64.0D, getDimensionKey(), new PacketPlayOutWorldEvent(i, blockposition, j, false));
/*      */   }
/*      */ 
/*      */   
/*      */   public void notify(BlockPosition blockposition, IBlockData iblockdata, IBlockData iblockdata1, int i) {
/* 2022 */     AsyncCatcher.catchOp("notify call");
/* 2023 */     getChunkProvider().flagDirty(blockposition);
/* 2024 */     VoxelShape voxelshape = iblockdata.getCollisionShape(this, blockposition);
/* 2025 */     VoxelShape voxelshape1 = iblockdata1.getCollisionShape(this, blockposition);
/*      */     
/* 2027 */     if (VoxelShapes.c(voxelshape, voxelshape1, OperatorBoolean.NOT_SAME)) {
/* 2028 */       boolean wasTicking = this.tickingEntities; this.tickingEntities = true;
/*      */       
/* 2030 */       IteratorSafeOrderedReferenceSet.Iterator iterator = this.navigatorsForIteration.iterator();
/*      */       
/*      */       try {
/* 2033 */         while (iterator.hasNext()) {
/* 2034 */           NavigationAbstract navigationabstract = (NavigationAbstract)iterator.next();
/*      */           
/* 2036 */           if (!navigationabstract.i()) {
/* 2037 */             navigationabstract.b(blockposition);
/*      */           }
/*      */         } 
/*      */       } finally {
/* 2041 */         iterator.finishedIterating();
/*      */       } 
/*      */       
/* 2044 */       this.tickingEntities = wasTicking;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void broadcastEntityEffect(Entity entity, byte b0) {
/* 2050 */     getChunkProvider().broadcastIncludingSelf(entity, new PacketPlayOutEntityStatus(entity, b0));
/*      */   }
/*      */ 
/*      */   
/*      */   public ChunkProviderServer getChunkProvider() {
/* 2055 */     return this.chunkProvider;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Explosion createExplosion(@Nullable Entity entity, @Nullable DamageSource damagesource, @Nullable ExplosionDamageCalculator explosiondamagecalculator, double d0, double d1, double d2, float f, boolean flag, Explosion.Effect explosion_effect) {
/* 2061 */     Explosion explosion = super.createExplosion(entity, damagesource, explosiondamagecalculator, d0, d1, d2, f, flag, explosion_effect);
/*      */     
/* 2063 */     if (explosion.wasCanceled) {
/* 2064 */       return explosion;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2074 */     if (explosion_effect == Explosion.Effect.NONE) {
/* 2075 */       explosion.clearBlocks();
/*      */     }
/*      */     
/* 2078 */     Iterator<EntityPlayer> iterator = this.players.iterator();
/*      */     
/* 2080 */     while (iterator.hasNext()) {
/* 2081 */       EntityPlayer entityplayer = iterator.next();
/*      */       
/* 2083 */       if (entityplayer.h(d0, d1, d2) < 4096.0D) {
/* 2084 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutExplosion(d0, d1, d2, f, explosion.getBlocks(), explosion.c().get(entityplayer)));
/*      */       }
/*      */     } 
/*      */     
/* 2088 */     return explosion;
/*      */   }
/*      */ 
/*      */   
/*      */   public void playBlockAction(BlockPosition blockposition, Block block, int i, int j) {
/* 2093 */     this.L.add(new BlockActionData(blockposition, block, i, j));
/*      */   }
/*      */   
/*      */   private void aj() {
/* 2097 */     while (!this.L.isEmpty()) {
/* 2098 */       BlockActionData blockactiondata = (BlockActionData)this.L.removeFirst();
/*      */       
/* 2100 */       if (a(blockactiondata)) {
/* 2101 */         this.server.getPlayerList().sendPacketNearby((EntityHuman)null, blockactiondata.a().getX(), blockactiondata.a().getY(), blockactiondata.a().getZ(), 64.0D, getDimensionKey(), new PacketPlayOutBlockAction(blockactiondata.a(), blockactiondata.b(), blockactiondata.c(), blockactiondata.d()));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean a(BlockActionData blockactiondata) {
/* 2108 */     IBlockData iblockdata = getType(blockactiondata.a());
/*      */     
/* 2110 */     return iblockdata.a(blockactiondata.b()) ? iblockdata.a(this, blockactiondata.a(), blockactiondata.c(), blockactiondata.d()) : false;
/*      */   }
/*      */ 
/*      */   
/*      */   public TickListServer<Block> getBlockTickList() {
/* 2115 */     return this.nextTickListBlock;
/*      */   }
/*      */ 
/*      */   
/*      */   public TickListServer<FluidType> getFluidTickList() {
/* 2120 */     return this.nextTickListFluid;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   public MinecraftServer getMinecraftServer() {
/* 2126 */     return this.server;
/*      */   }
/*      */   
/*      */   public PortalTravelAgent getTravelAgent() {
/* 2130 */     return this.portalTravelAgent;
/*      */   }
/*      */   
/*      */   public DefinedStructureManager n() {
/* 2134 */     return this.server.getDefinedStructureManager();
/*      */   }
/*      */ 
/*      */   
/*      */   public <T extends ParticleParam> int a(T t0, double d0, double d1, double d2, int i, double d3, double d4, double d5, double d6) {
/* 2139 */     return sendParticles((EntityPlayer)null, t0, d0, d1, d2, i, d3, d4, d5, d6, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T extends ParticleParam> int sendParticles(EntityPlayer sender, T t0, double d0, double d1, double d2, int i, double d3, double d4, double d5, double d6, boolean force) {
/* 2144 */     return sendParticles(this.players, sender, t0, d0, d1, d2, i, d3, d4, d5, d6, force);
/*      */   }
/*      */   
/*      */   public <T extends ParticleParam> int sendParticles(List<EntityPlayer> receivers, EntityPlayer sender, T t0, double d0, double d1, double d2, int i, double d3, double d4, double d5, double d6, boolean force) {
/* 2148 */     PacketPlayOutWorldParticles packetplayoutworldparticles = new PacketPlayOutWorldParticles(t0, force, d0, d1, d2, (float)d3, (float)d4, (float)d5, (float)d6, i);
/*      */     
/* 2150 */     int j = 0;
/*      */     
/* 2152 */     for (EntityHuman entityhuman : receivers) {
/* 2153 */       EntityPlayer entityplayer = (EntityPlayer)entityhuman;
/* 2154 */       if (sender != null && !entityplayer.getBukkitEntity().canSee((Player)sender.getBukkitEntity()))
/*      */         continue; 
/* 2156 */       if (a(entityplayer, force, d0, d1, d2, packetplayoutworldparticles)) {
/* 2157 */         j++;
/*      */       }
/*      */     } 
/*      */     
/* 2161 */     return j;
/*      */   }
/*      */   
/*      */   public <T extends ParticleParam> boolean a(EntityPlayer entityplayer, T t0, boolean flag, double d0, double d1, double d2, int i, double d3, double d4, double d5, double d6) {
/* 2165 */     Packet<?> packet = new PacketPlayOutWorldParticles(t0, flag, d0, d1, d2, (float)d3, (float)d4, (float)d5, (float)d6, i);
/*      */     
/* 2167 */     return a(entityplayer, flag, d0, d1, d2, packet);
/*      */   }
/*      */   
/*      */   private boolean a(EntityPlayer entityplayer, boolean flag, double d0, double d1, double d2, Packet<?> packet) {
/* 2171 */     if (entityplayer.getWorldServer() != this) {
/* 2172 */       return false;
/*      */     }
/* 2174 */     BlockPosition blockposition = entityplayer.getChunkCoordinates();
/*      */     
/* 2176 */     if (blockposition.a(new Vec3D(d0, d1, d2), flag ? 512.0D : 32.0D)) {
/* 2177 */       entityplayer.playerConnection.sendPacket(packet);
/* 2178 */       return true;
/*      */     } 
/* 2180 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public Entity getEntity(int i) {
/* 2188 */     return (Entity)this.entitiesById.get(i);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Entity getEntity(UUID uuid) {
/* 2193 */     return this.entitiesByUUID.get(uuid);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockPosition a(StructureGenerator<?> structuregenerator, BlockPosition blockposition, int i, boolean flag) {
/* 2198 */     return !this.worldDataServer.getGeneratorSettings().shouldGenerateMapFeatures() ? null : getChunkProvider().getChunkGenerator().findNearestMapFeature(this, structuregenerator, blockposition, i, flag);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockPosition a(BiomeBase biomebase, BlockPosition blockposition, int i, int j) {
/* 2203 */     return getChunkProvider().getChunkGenerator().getWorldChunkManager().a(blockposition.getX(), blockposition.getY(), blockposition.getZ(), i, j, biomebase1 -> (biomebase1 == biomebase), this.random, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CraftingManager getCraftingManager() {
/* 2210 */     return this.server.getCraftingManager();
/*      */   }
/*      */ 
/*      */   
/*      */   public ITagRegistry p() {
/* 2215 */     return this.server.getTagRegistry();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSavingDisabled() {
/* 2220 */     return this.savingDisabled;
/*      */   }
/*      */ 
/*      */   
/*      */   public IRegistryCustom r() {
/* 2225 */     return this.server.getCustomRegistry();
/*      */   }
/*      */   
/*      */   public WorldPersistentData getWorldPersistentData() {
/* 2229 */     return getChunkProvider().getWorldPersistentData();
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public WorldMap a(String s) {
/* 2235 */     return getMinecraftServer().E().getWorldPersistentData().<WorldMap>b(() -> { WorldMap newMap = new WorldMap(s); MapInitializeEvent event = new MapInitializeEvent((MapView)newMap.mapView); Bukkit.getServer().getPluginManager().callEvent((Event)event); return newMap; }s);
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
/*      */   public void a(WorldMap worldmap) {
/* 2248 */     getMinecraftServer().E().getWorldPersistentData().a(worldmap);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getWorldMapCount() {
/* 2253 */     return ((PersistentIdCounts)getMinecraftServer().E().getWorldPersistentData().<PersistentIdCounts>a(PersistentIdCounts::new, "idcounts")).a();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addTicketsForSpawn(int radiusInBlocks, BlockPosition spawn) {
/* 2260 */     ChunkProviderServer chunkproviderserver = getChunkProvider();
/* 2261 */     int tickRadius = radiusInBlocks - 16;
/*      */     
/*      */     int x;
/* 2264 */     for (x = -tickRadius; x <= tickRadius; x += 16) {
/* 2265 */       for (int i = -tickRadius; i <= tickRadius; i += 16)
/*      */       {
/* 2267 */         chunkproviderserver.addTicket(TicketType.START, new ChunkCoordIntPair(spawn.add(x, 0, i)), 2, Unit.INSTANCE);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2274 */     for (x = -radiusInBlocks; x <= radiusInBlocks; x += 16) {
/*      */       
/* 2276 */       chunkproviderserver.addTicket(TicketType.START, new ChunkCoordIntPair(spawn.add(x, 0, radiusInBlocks)), 1, Unit.INSTANCE);
/*      */       
/* 2278 */       chunkproviderserver.addTicket(TicketType.START, new ChunkCoordIntPair(spawn.add(x, 0, -radiusInBlocks)), 1, Unit.INSTANCE);
/*      */     } 
/*      */ 
/*      */     
/* 2282 */     for (int z = -radiusInBlocks + 16; z < radiusInBlocks; z += 16) {
/*      */       
/* 2284 */       chunkproviderserver.addTicket(TicketType.START, new ChunkCoordIntPair(spawn.add(radiusInBlocks, 0, z)), 1, Unit.INSTANCE);
/*      */       
/* 2286 */       chunkproviderserver.addTicket(TicketType.START, new ChunkCoordIntPair(spawn.add(-radiusInBlocks, 0, z)), 1, Unit.INSTANCE);
/*      */     } 
/*      */     
/* 2289 */     MCUtil.getSpiralOutChunks(spawn, radiusInBlocks >> 4).forEach(pair -> getChunkProvider().getChunkAtAsynchronously(pair.x, pair.z, true, false).exceptionally(()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeTicketsForSpawn(int radiusInBlocks, BlockPosition spawn) {
/* 2299 */     ChunkProviderServer chunkproviderserver = getChunkProvider();
/* 2300 */     int tickRadius = radiusInBlocks - 16;
/*      */     
/*      */     int x;
/* 2303 */     for (x = -tickRadius; x <= tickRadius; x += 16) {
/* 2304 */       for (int i = -tickRadius; i <= tickRadius; i += 16)
/*      */       {
/* 2306 */         chunkproviderserver.removeTicket(TicketType.START, new ChunkCoordIntPair(spawn.add(x, 0, i)), 2, Unit.INSTANCE);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2313 */     for (x = -radiusInBlocks; x <= radiusInBlocks; x += 16) {
/*      */       
/* 2315 */       chunkproviderserver.removeTicket(TicketType.START, new ChunkCoordIntPair(spawn.add(x, 0, radiusInBlocks)), 1, Unit.INSTANCE);
/*      */       
/* 2317 */       chunkproviderserver.removeTicket(TicketType.START, new ChunkCoordIntPair(spawn.add(x, 0, -radiusInBlocks)), 1, Unit.INSTANCE);
/*      */     } 
/*      */ 
/*      */     
/* 2321 */     for (int z = -radiusInBlocks + 16; z < radiusInBlocks; z += 16) {
/*      */       
/* 2323 */       chunkproviderserver.removeTicket(TicketType.START, new ChunkCoordIntPair(spawn.add(radiusInBlocks, 0, z)), 1, Unit.INSTANCE);
/*      */       
/* 2325 */       chunkproviderserver.removeTicket(TicketType.START, new ChunkCoordIntPair(spawn.add(-radiusInBlocks, 0, z)), 1, Unit.INSTANCE);
/*      */     } 
/*      */   }
/*      */   
/*      */   public final void setSpawn(BlockPosition blockposition, float f) {
/* 2330 */     a(blockposition, f);
/*      */   }
/*      */   public void a(BlockPosition blockposition, float f) {
/* 2333 */     BlockPosition prevSpawn = getSpawn();
/*      */ 
/*      */     
/* 2336 */     this.worldData.setSpawn(blockposition, f);
/* 2337 */     (new SpawnChangeEvent((World)getWorld(), MCUtil.toLocation(this, prevSpawn))).callEvent();
/* 2338 */     if (this.keepSpawnInMemory) {
/*      */       
/* 2340 */       removeTicketsForSpawn(this.paperConfig.keepLoadedRange, prevSpawn);
/* 2341 */       addTicketsForSpawn(this.paperConfig.keepLoadedRange, blockposition);
/*      */     } 
/* 2343 */     getMinecraftServer().getPlayerList().sendAll(new PacketPlayOutSpawnPosition(blockposition, f));
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
/*      */   public float v() {
/* 2359 */     return this.worldData.d();
/*      */   }
/*      */   
/*      */   public LongSet getForceLoadedChunks() {
/* 2363 */     ForcedChunk forcedchunk = getWorldPersistentData().<ForcedChunk>b(ForcedChunk::new, "chunks");
/*      */     
/* 2365 */     return (forcedchunk != null) ? LongSets.unmodifiable(forcedchunk.a()) : (LongSet)LongSets.EMPTY_SET;
/*      */   }
/*      */   public boolean setForceLoaded(int i, int j, boolean flag) {
/*      */     boolean flag1;
/* 2369 */     ForcedChunk forcedchunk = getWorldPersistentData().<ForcedChunk>a(ForcedChunk::new, "chunks");
/* 2370 */     ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);
/* 2371 */     long k = chunkcoordintpair.pair();
/*      */ 
/*      */     
/* 2374 */     if (flag) {
/* 2375 */       flag1 = forcedchunk.a().add(k);
/* 2376 */       if (flag1) {
/* 2377 */         getChunkAt(i, j);
/*      */       }
/*      */     } else {
/* 2380 */       flag1 = forcedchunk.a().remove(k);
/*      */     } 
/*      */     
/* 2383 */     forcedchunk.a(flag1);
/* 2384 */     if (flag1) {
/* 2385 */       getChunkProvider().a(chunkcoordintpair, flag);
/*      */     }
/*      */     
/* 2388 */     return flag1;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<EntityPlayer> getPlayers() {
/* 2393 */     return this.players;
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(BlockPosition blockposition, IBlockData iblockdata, IBlockData iblockdata1) {
/* 2398 */     Optional<VillagePlaceType> optional = VillagePlaceType.b(iblockdata);
/* 2399 */     Optional<VillagePlaceType> optional1 = VillagePlaceType.b(iblockdata1);
/*      */     
/* 2401 */     if (!Objects.equals(optional, optional1)) {
/* 2402 */       BlockPosition blockposition1 = blockposition.immutableCopy();
/*      */       
/* 2404 */       optional.ifPresent(villageplacetype -> getMinecraftServer().execute(()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2410 */       optional1.ifPresent(villageplacetype -> getMinecraftServer().execute(()));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VillagePlace y() {
/* 2420 */     return getChunkProvider().j();
/*      */   }
/*      */   
/*      */   public boolean a_(BlockPosition blockposition) {
/* 2424 */     return a(blockposition, 1);
/*      */   }
/*      */   
/*      */   public boolean a(SectionPosition sectionposition) {
/* 2428 */     return a_(sectionposition.q());
/*      */   }
/*      */   
/*      */   public boolean a(BlockPosition blockposition, int i) {
/* 2432 */     return (i > 6) ? false : ((b(SectionPosition.a(blockposition)) <= i));
/*      */   }
/*      */   
/*      */   public int b(SectionPosition sectionposition) {
/* 2436 */     return y().a(sectionposition);
/*      */   }
/*      */   
/*      */   public PersistentRaid getPersistentRaid() {
/* 2440 */     return this.persistentRaid;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Raid b_(BlockPosition blockposition) {
/* 2445 */     return this.persistentRaid.getNearbyRaid(blockposition, 9216);
/*      */   }
/*      */   
/*      */   public boolean c_(BlockPosition blockposition) {
/* 2449 */     return (b_(blockposition) != null);
/*      */   }
/*      */   
/*      */   public void a(ReputationEvent reputationevent, Entity entity, ReputationHandler reputationhandler) {
/* 2453 */     reputationhandler.a(reputationevent, entity);
/*      */   }
/*      */   
/*      */   public void a(Path java_nio_file_path) throws IOException {
/* 2457 */     PlayerChunkMap playerchunkmap = (getChunkProvider()).playerChunkMap;
/* 2458 */     BufferedWriter bufferedwriter = Files.newBufferedWriter(java_nio_file_path.resolve("stats.txt"), new java.nio.file.OpenOption[0]);
/* 2459 */     Throwable throwable = null;
/*      */     
/*      */     try {
/* 2462 */       bufferedwriter.write(String.format("spawning_chunks: %d\n", new Object[] { Integer.valueOf(playerchunkmap.e().b()) }));
/* 2463 */       SpawnerCreature.d spawnercreature_d = getChunkProvider().k();
/*      */       
/* 2465 */       if (spawnercreature_d != null) {
/* 2466 */         ObjectIterator objectiterator = spawnercreature_d.b().object2IntEntrySet().iterator();
/*      */         
/* 2468 */         while (objectiterator.hasNext()) {
/* 2469 */           Object2IntMap.Entry<EnumCreatureType> it_unimi_dsi_fastutil_objects_object2intmap_entry = (Object2IntMap.Entry<EnumCreatureType>)objectiterator.next();
/*      */           
/* 2471 */           bufferedwriter.write(String.format("spawn_count.%s: %d\n", new Object[] { ((EnumCreatureType)it_unimi_dsi_fastutil_objects_object2intmap_entry.getKey()).b(), Integer.valueOf(it_unimi_dsi_fastutil_objects_object2intmap_entry.getIntValue()) }));
/*      */         } 
/*      */       } 
/*      */       
/* 2475 */       bufferedwriter.write(String.format("entities: %d\n", new Object[] { Integer.valueOf(this.entitiesById.size()) }));
/* 2476 */       bufferedwriter.write(String.format("block_entities: %d\n", new Object[] { Integer.valueOf(this.tileEntityListTick.size()) }));
/* 2477 */       bufferedwriter.write(String.format("block_ticks: %d\n", new Object[] { Integer.valueOf(getBlockTickList().a()) }));
/* 2478 */       bufferedwriter.write(String.format("fluid_ticks: %d\n", new Object[] { Integer.valueOf(getFluidTickList().a()) }));
/* 2479 */       bufferedwriter.write("distance_manager: " + playerchunkmap.e().c() + "\n");
/* 2480 */       bufferedwriter.write(String.format("pending_tasks: %d\n", new Object[] { Integer.valueOf(getChunkProvider().f()) }));
/* 2481 */     } catch (Throwable throwable1) {
/* 2482 */       throwable = throwable1;
/* 2483 */       throw throwable1;
/*      */     } finally {
/* 2485 */       if (bufferedwriter != null) {
/* 2486 */         if (throwable != null) {
/*      */           try {
/* 2488 */             bufferedwriter.close();
/* 2489 */           } catch (Throwable throwable2) {
/* 2490 */             throwable.addSuppressed(throwable2);
/*      */           } 
/*      */         } else {
/* 2493 */           bufferedwriter.close();
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2499 */     CrashReport crashreport = new CrashReport("Level dump", new Exception("dummy"));
/*      */     
/* 2501 */     a(crashreport);
/* 2502 */     BufferedWriter bufferedwriter1 = Files.newBufferedWriter(java_nio_file_path.resolve("example_crash.txt"), new java.nio.file.OpenOption[0]);
/* 2503 */     Throwable throwable3 = null;
/*      */     
/*      */     try {
/* 2506 */       bufferedwriter1.write(crashreport.e());
/* 2507 */     } catch (Throwable throwable4) {
/* 2508 */       throwable3 = throwable4;
/* 2509 */       throw throwable4;
/*      */     } finally {
/* 2511 */       if (bufferedwriter1 != null) {
/* 2512 */         if (throwable3 != null) {
/*      */           try {
/* 2514 */             bufferedwriter1.close();
/* 2515 */           } catch (Throwable throwable5) {
/* 2516 */             throwable3.addSuppressed(throwable5);
/*      */           } 
/*      */         } else {
/* 2519 */           bufferedwriter1.close();
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2525 */     Path java_nio_file_path1 = java_nio_file_path.resolve("chunks.csv");
/* 2526 */     BufferedWriter bufferedwriter2 = Files.newBufferedWriter(java_nio_file_path1, new java.nio.file.OpenOption[0]);
/* 2527 */     Throwable throwable6 = null;
/*      */     
/*      */     try {
/* 2530 */       playerchunkmap.a(bufferedwriter2);
/* 2531 */     } catch (Throwable throwable7) {
/* 2532 */       throwable6 = throwable7;
/* 2533 */       throw throwable7;
/*      */     } finally {
/* 2535 */       if (bufferedwriter2 != null) {
/* 2536 */         if (throwable6 != null) {
/*      */           try {
/* 2538 */             bufferedwriter2.close();
/* 2539 */           } catch (Throwable throwable8) {
/* 2540 */             throwable6.addSuppressed(throwable8);
/*      */           } 
/*      */         } else {
/* 2543 */           bufferedwriter2.close();
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2549 */     Path java_nio_file_path2 = java_nio_file_path.resolve("entities.csv");
/* 2550 */     BufferedWriter bufferedwriter3 = Files.newBufferedWriter(java_nio_file_path2, new java.nio.file.OpenOption[0]);
/* 2551 */     Throwable throwable9 = null;
/*      */     
/*      */     try {
/* 2554 */       a(bufferedwriter3, (Iterable<Entity>)this.entitiesById.values());
/* 2555 */     } catch (Throwable throwable10) {
/* 2556 */       throwable9 = throwable10;
/* 2557 */       throw throwable10;
/*      */     } finally {
/* 2559 */       if (bufferedwriter3 != null) {
/* 2560 */         if (throwable9 != null) {
/*      */           try {
/* 2562 */             bufferedwriter3.close();
/* 2563 */           } catch (Throwable throwable11) {
/* 2564 */             throwable9.addSuppressed(throwable11);
/*      */           } 
/*      */         } else {
/* 2567 */           bufferedwriter3.close();
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2573 */     Path java_nio_file_path3 = java_nio_file_path.resolve("block_entities.csv");
/* 2574 */     BufferedWriter bufferedwriter4 = Files.newBufferedWriter(java_nio_file_path3, new java.nio.file.OpenOption[0]);
/* 2575 */     Throwable throwable12 = null;
/*      */     
/*      */     try {
/* 2578 */       a(bufferedwriter4);
/* 2579 */     } catch (Throwable throwable13) {
/* 2580 */       throwable12 = throwable13;
/* 2581 */       throw throwable13;
/*      */     } finally {
/* 2583 */       if (bufferedwriter4 != null) {
/* 2584 */         if (throwable12 != null) {
/*      */           try {
/* 2586 */             bufferedwriter4.close();
/* 2587 */           } catch (Throwable throwable14) {
/* 2588 */             throwable12.addSuppressed(throwable14);
/*      */           } 
/*      */         } else {
/* 2591 */           bufferedwriter4.close();
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void a(Writer writer, Iterable<Entity> iterable) throws IOException {
/* 2600 */     CSVWriter csvwriter = CSVWriter.a().a("x").a("y").a("z").a("uuid").a("type").a("alive").a("display_name").a("custom_name").a(writer);
/* 2601 */     Iterator<Entity> iterator = iterable.iterator();
/*      */     
/* 2603 */     while (iterator.hasNext()) {
/* 2604 */       Entity entity = iterator.next();
/* 2605 */       IChatBaseComponent ichatbasecomponent = entity.getCustomName();
/* 2606 */       IChatBaseComponent ichatbasecomponent1 = entity.getScoreboardDisplayName();
/*      */       
/* 2608 */       csvwriter.a(new Object[] { Double.valueOf(entity.locX()), Double.valueOf(entity.locY()), Double.valueOf(entity.locZ()), entity.getUniqueID(), IRegistry.ENTITY_TYPE.getKey(entity.getEntityType()), Boolean.valueOf(entity.isAlive()), ichatbasecomponent1.getString(), (ichatbasecomponent != null) ? ichatbasecomponent.getString() : null });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(Writer writer) throws IOException {
/* 2614 */     CSVWriter csvwriter = CSVWriter.a().a("x").a("y").a("z").a("type").a(writer);
/* 2615 */     Iterator<TileEntity> iterator = this.tileEntityListTick.iterator();
/*      */     
/* 2617 */     while (iterator.hasNext()) {
/* 2618 */       TileEntity tileentity = iterator.next();
/* 2619 */       BlockPosition blockposition = tileentity.getPosition();
/*      */       
/* 2621 */       csvwriter.a(new Object[] { Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ()), IRegistry.BLOCK_ENTITY_TYPE.getKey(tileentity.getTileType()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @VisibleForTesting
/*      */   public void a(StructureBoundingBox structureboundingbox) {
/* 2628 */     this.L.removeIf(blockactiondata -> structureboundingbox.b(blockactiondata.a()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void update(BlockPosition blockposition, Block block) {
/* 2635 */     if (!isDebugWorld()) {
/*      */       
/* 2637 */       if (this.populating) {
/*      */         return;
/*      */       }
/*      */       
/* 2641 */       applyPhysics(blockposition, block);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Iterable<Entity> A() {
/* 2647 */     return Iterables.unmodifiableIterable((Iterable)this.entitiesById.values());
/*      */   }
/*      */   
/*      */   public String toString() {
/* 2651 */     return "ServerLevel[" + this.worldDataServer.getName() + "]";
/*      */   }
/*      */   
/*      */   public boolean isFlatWorld() {
/* 2655 */     return this.worldDataServer.getGeneratorSettings().isFlatWorld();
/*      */   }
/*      */ 
/*      */   
/*      */   public long getSeed() {
/* 2660 */     return this.worldDataServer.getGeneratorSettings().getSeed();
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EnderDragonBattle getDragonBattle() {
/* 2665 */     return this.dragonBattle;
/*      */   }
/*      */ 
/*      */   
/*      */   public Stream<? extends StructureStart<?>> a(SectionPosition sectionposition, StructureGenerator<?> structuregenerator) {
/* 2670 */     return getStructureManager().a(sectionposition, structuregenerator);
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldServer getMinecraftWorld() {
/* 2675 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void a(WorldServer worldserver) {
/* 2680 */     a(worldserver, (Entity)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void a(WorldServer worldserver, Entity entity) {
/* 2685 */     BlockPosition blockposition = a;
/* 2686 */     int i = blockposition.getX();
/* 2687 */     int j = blockposition.getY() - 2;
/* 2688 */     int k = blockposition.getZ();
/*      */ 
/*      */     
/* 2691 */     BlockStateListPopulator blockList = new BlockStateListPopulator(worldserver);
/* 2692 */     BlockPosition.b(i - 2, j + 1, k - 2, i + 2, j + 3, k + 2).forEach(blockposition1 -> blockList.setTypeAndData(blockposition1, Blocks.AIR.getBlockData(), 3));
/*      */ 
/*      */     
/* 2695 */     BlockPosition.b(i - 2, j, k - 2, i + 2, j, k + 2).forEach(blockposition1 -> blockList.setTypeAndData(blockposition1, Blocks.OBSIDIAN.getBlockData(), 3));
/*      */ 
/*      */     
/* 2698 */     CraftWorld craftWorld = worldserver.getWorld();
/* 2699 */     PortalCreateEvent portalEvent = new PortalCreateEvent(blockList.getList(), (World)craftWorld, (entity == null) ? null : (Entity)entity.getBukkitEntity(), PortalCreateEvent.CreateReason.END_PLATFORM);
/*      */     
/* 2701 */     worldserver.getServer().getPluginManager().callEvent((Event)portalEvent);
/* 2702 */     if (!portalEvent.isCancelled())
/* 2703 */       blockList.updateList(); 
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */