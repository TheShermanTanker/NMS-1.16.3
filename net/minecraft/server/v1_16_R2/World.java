/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import co.aikar.timings.WorldTimingsHandler;
/*      */ import com.destroystokyo.paper.PaperWorldConfig;
/*      */ import com.destroystokyo.paper.antixray.ChunkPacketBlockController;
/*      */ import com.destroystokyo.paper.event.block.BlockDestroyEvent;
/*      */ import com.destroystokyo.paper.event.server.ServerExceptionEvent;
/*      */ import com.destroystokyo.paper.exception.ServerException;
/*      */ import com.destroystokyo.paper.exception.ServerInternalException;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.tuinity.tuinity.config.TuinityConfig;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.Executor;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.function.Supplier;
/*      */ import javax.annotation.Nullable;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.bukkit.block.data.BlockData;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.CapturedBlockState;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockState;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.block.BlockPhysicsEvent;
/*      */ import org.bukkit.generator.ChunkGenerator;
/*      */ import org.spigotmc.SpigotWorldConfig;
/*      */ import org.spigotmc.TickLimiter;
/*      */ 
/*      */ public abstract class World implements GeneratorAccess, AutoCloseable {
/*   38 */   protected static final Logger LOGGER = LogManager.getLogger();
/*   39 */   public static final Codec<ResourceKey<World>> f = MinecraftKey.a.xmap(ResourceKey.b(IRegistry.L), ResourceKey::a);
/*   40 */   public static final ResourceKey<World> OVERWORLD = ResourceKey.a(IRegistry.L, new MinecraftKey("overworld"));
/*   41 */   public static final ResourceKey<World> THE_NETHER = ResourceKey.a(IRegistry.L, new MinecraftKey("the_nether"));
/*   42 */   public static final ResourceKey<World> THE_END = ResourceKey.a(IRegistry.L, new MinecraftKey("the_end"));
/*   43 */   private static final EnumDirection[] a = EnumDirection.values();
/*      */   
/*   45 */   public final List<TileEntity> tileEntityListTick = Lists.newArrayList();
/*   46 */   protected final List<TileEntity> tileEntityListPending = Lists.newArrayList();
/*   47 */   protected final Set<TileEntity> tileEntityListUnload = Sets.newHashSet();
/*      */   public final Thread serverThread;
/*      */   private final boolean debugWorld;
/*      */   private int d;
/*   51 */   protected int n = (new Random()).nextInt();
/*   52 */   protected final int o = 1013904223;
/*      */   protected float lastRainLevel;
/*      */   protected float rainLevel;
/*      */   protected float lastThunderLevel;
/*      */   protected float thunderLevel;
/*   57 */   public final Random random = new Random();
/*      */   
/*      */   private final DimensionManager x;
/*      */   
/*      */   public final WorldDataMutable worldData;
/*      */   private final Supplier<GameProfilerFiller> methodProfiler;
/*      */   public final boolean isClientSide;
/*      */   protected boolean tickingTileEntities;
/*      */   private final WorldBorder worldBorder;
/*      */   private final BiomeManager biomeManager;
/*      */   private final ResourceKey<World> dimensionKey;
/*      */   private final ResourceKey<DimensionManager> typeKey;
/*      */   private final CraftWorld world;
/*      */   public boolean pvpMode;
/*      */   public boolean keepSpawnInMemory = true;
/*      */   public ChunkGenerator generator;
/*   73 */   public static final boolean DEBUG_ENTITIES = Boolean.getBoolean("debug.entities");
/*      */   
/*      */   public boolean captureBlockStates = false;
/*      */   public boolean captureTreeGeneration = false;
/*   77 */   public Map<BlockPosition, CraftBlockState> capturedBlockStates = new LinkedHashMap<>();
/*   78 */   public Map<BlockPosition, TileEntity> capturedTileEntities = new LinkedHashMap<>();
/*      */   
/*      */   public List<EntityItem> captureDrops;
/*      */   
/*      */   public long ticksPerAnimalSpawns;
/*      */   
/*      */   public long ticksPerMonsterSpawns;
/*      */   
/*      */   public long ticksPerWaterSpawns;
/*      */   
/*      */   public long ticksPerWaterAmbientSpawns;
/*      */   public long ticksPerAmbientSpawns;
/*      */   public int wakeupInactiveRemainingAnimals;
/*      */   public int wakeupInactiveRemainingFlying;
/*      */   public int wakeupInactiveRemainingMonsters;
/*      */   public int wakeupInactiveRemainingVillagers;
/*      */   public boolean populating;
/*      */   public final SpigotWorldConfig spigotConfig;
/*      */   public final PaperWorldConfig paperConfig;
/*      */   public final ChunkPacketBlockController chunkPacketBlockController;
/*      */   public final TuinityConfig.WorldConfig tuinityConfig;
/*      */   public final WorldTimingsHandler timings;
/*      */   public static BlockPosition lastPhysicsProblem;
/*      */   private TickLimiter entityLimiter;
/*      */   private TickLimiter tileLimiter;
/*      */   private int tileTickPosition;
/*  104 */   public final Map<Explosion.CacheKey, Float> explosionDensityCache = new HashMap<>();
/*      */   public ArrayDeque<BlockRedstoneTorch.RedstoneUpdateInfo> redstoneUpdateInfos;
/*      */   
/*      */   public CraftWorld getWorld() {
/*  108 */     return this.world;
/*      */   }
/*      */   
/*      */   public CraftServer getServer() {
/*  112 */     return (CraftServer)Bukkit.getServer();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isChunkLoaded(int x, int z) {
/*  118 */     return (((WorldServer)this).getChunkIfLoaded(x, z) != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public ResourceKey<DimensionManager> getTypeKey() {
/*  123 */     return this.typeKey;
/*      */   }
/*      */   
/*      */   protected World(WorldDataMutable worlddatamutable, ResourceKey<World> resourcekey, DimensionManager dimensionmanager, Supplier<GameProfilerFiller> supplier, boolean flag, boolean flag1, long i, ChunkGenerator gen, org.bukkit.World.Environment env, Executor executor) {
/*  127 */     this.spigotConfig = new SpigotWorldConfig(((WorldDataServer)worlddatamutable).getName());
/*  128 */     this.paperConfig = new PaperWorldConfig(((WorldDataServer)worlddatamutable).getName(), this.spigotConfig);
/*  129 */     this.chunkPacketBlockController = this.paperConfig.antiXray ? (ChunkPacketBlockController)new ChunkPacketBlockControllerAntiXray(this, executor) : ChunkPacketBlockController.NO_OPERATION_INSTANCE;
/*  130 */     this.tuinityConfig = new TuinityConfig.WorldConfig(((WorldDataServer)worlddatamutable).getName());
/*  131 */     this.generator = gen;
/*  132 */     this.world = new CraftWorld((WorldServer)this, gen, env);
/*  133 */     this.ticksPerAnimalSpawns = getServer().getTicksPerAnimalSpawns();
/*  134 */     this.ticksPerMonsterSpawns = getServer().getTicksPerMonsterSpawns();
/*  135 */     this.ticksPerWaterSpawns = getServer().getTicksPerWaterSpawns();
/*  136 */     this.ticksPerWaterAmbientSpawns = getServer().getTicksPerWaterAmbientSpawns();
/*  137 */     this.ticksPerAmbientSpawns = getServer().getTicksPerAmbientSpawns();
/*  138 */     this.typeKey = (ResourceKey<DimensionManager>)(getServer().getHandle().getServer()).customRegistry.a().c(dimensionmanager).orElseThrow(() -> new IllegalStateException("Unregistered dimension type: " + dimensionmanager));
/*      */ 
/*      */ 
/*      */     
/*  142 */     this.methodProfiler = supplier;
/*  143 */     this.worldData = worlddatamutable;
/*  144 */     this.x = dimensionmanager;
/*  145 */     this.dimensionKey = resourcekey;
/*  146 */     this.isClientSide = flag;
/*  147 */     if (dimensionmanager.getCoordinateScale() != 1.0D) {
/*  148 */       this.worldBorder = new WorldBorder()
/*      */         {
/*      */           public double getCenterX() {
/*  151 */             return super.getCenterX();
/*      */           }
/*      */ 
/*      */           
/*      */           public double getCenterZ() {
/*  156 */             return super.getCenterZ();
/*      */           }
/*      */         };
/*      */     } else {
/*  160 */       this.worldBorder = new WorldBorder();
/*      */     } 
/*      */     
/*  163 */     this.serverThread = Thread.currentThread();
/*  164 */     this.biomeManager = new BiomeManager(this, i, dimensionmanager.getGenLayerZoomer());
/*  165 */     this.debugWorld = flag1;
/*      */     
/*  167 */     (getWorldBorder()).world = (WorldServer)this;
/*      */     
/*  169 */     getWorldBorder().a(new IWorldBorderListener() {
/*      */           public void a(WorldBorder worldborder, double d0) {
/*  171 */             World.this.getServer().getHandle().sendAll(new PacketPlayOutWorldBorder(worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_SIZE), worldborder.world);
/*      */           }
/*      */           
/*      */           public void a(WorldBorder worldborder, double d0, double d1, long i) {
/*  175 */             World.this.getServer().getHandle().sendAll(new PacketPlayOutWorldBorder(worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction.LERP_SIZE), worldborder.world);
/*      */           }
/*      */           
/*      */           public void a(WorldBorder worldborder, double d0, double d1) {
/*  179 */             World.this.getServer().getHandle().sendAll(new PacketPlayOutWorldBorder(worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_CENTER), worldborder.world);
/*      */           }
/*      */           
/*      */           public void a(WorldBorder worldborder, int i) {
/*  183 */             World.this.getServer().getHandle().sendAll(new PacketPlayOutWorldBorder(worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_TIME), worldborder.world);
/*      */           }
/*      */           
/*      */           public void b(WorldBorder worldborder, int i) {
/*  187 */             World.this.getServer().getHandle().sendAll(new PacketPlayOutWorldBorder(worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_BLOCKS), worldborder.world);
/*      */           }
/*      */ 
/*      */           
/*      */           public void b(WorldBorder worldborder, double d0) {}
/*      */           
/*      */           public void c(WorldBorder worldborder, double d0) {}
/*      */         });
/*  195 */     this.timings = new WorldTimingsHandler(this);
/*  196 */     this.keepSpawnInMemory = this.paperConfig.keepSpawnInMemory;
/*  197 */     this.entityLimiter = new TickLimiter(this.spigotConfig.entityMaxTickTime);
/*  198 */     this.tileLimiter = new TickLimiter(this.spigotConfig.tileMaxTickTime);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean checkEntityCollision(IBlockData data, Entity source, VoxelShapeCollision voxelshapedcollision, BlockPosition position, boolean checkCanSee) {
/*  206 */     VoxelShape voxelshape = data.getCollisionShape(this, position, voxelshapedcollision);
/*  207 */     if (voxelshape.isEmpty()) {
/*  208 */       return true;
/*      */     }
/*      */     
/*  211 */     voxelshape = voxelshape.offset(position.getX(), position.getY(), position.getZ());
/*  212 */     if (voxelshape.isEmpty()) {
/*  213 */       return true;
/*      */     }
/*      */     
/*  216 */     List<Entity> entities = getEntities((Entity)null, voxelshape.getBoundingBox());
/*  217 */     for (int i = 0, len = entities.size(); i < len; i++) {
/*  218 */       Entity entity = entities.get(i);
/*      */       
/*  220 */       if (!checkCanSee || !(source instanceof EntityPlayer) || !(entity instanceof EntityPlayer) || ((EntityPlayer)source)
/*  221 */         .getBukkitEntity().canSee((Player)((EntityPlayer)entity).getBukkitEntity()))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  228 */         if (!entity.dead && entity.blocksEntitySpawning())
/*      */         {
/*      */ 
/*      */           
/*  232 */           if (VoxelShapes.applyOperation(voxelshape, VoxelShapes.of(entity.getBoundingBox()), OperatorBoolean.AND))
/*  233 */             return false; 
/*      */         }
/*      */       }
/*      */     } 
/*  237 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public BlockPosition getSpawn() {
/*  243 */     BlockPosition blockposition = new BlockPosition(this.worldData.a(), this.worldData.b(), this.worldData.c());
/*      */     
/*  245 */     if (!getWorldBorder().a(blockposition)) {
/*  246 */       blockposition = getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, new BlockPosition(getWorldBorder().getCenterX(), 0.0D, getWorldBorder().getCenterZ()));
/*      */     }
/*      */     
/*  249 */     return blockposition;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean s_() {
/*  254 */     return this.isClientSide;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public MinecraftServer getMinecraftServer() {
/*  259 */     return null;
/*      */   }
/*      */   
/*      */   public static boolean isValidLocation(BlockPosition blockposition) {
/*  263 */     return blockposition.isValidLocation();
/*      */   }
/*      */   
/*      */   public static boolean l(BlockPosition blockposition) {
/*  267 */     return (!d(blockposition.getY()) && D(blockposition));
/*      */   }
/*      */   
/*      */   private static boolean D(BlockPosition blockposition) {
/*  271 */     return (blockposition.getX() >= -30000000 && blockposition.getZ() >= -30000000 && blockposition.getX() < 30000000 && blockposition.getZ() < 30000000);
/*      */   }
/*      */   
/*      */   private static boolean d(int i) {
/*  275 */     return (i < -20000000 || i >= 20000000);
/*      */   }
/*      */   
/*      */   public static boolean isOutsideWorld(BlockPosition blockposition) {
/*  279 */     return b(blockposition.getY());
/*      */   }
/*      */   
/*      */   public static boolean b(int i) {
/*  283 */     return (i < 0 || i >= 256);
/*      */   }
/*      */   
/*      */   public final Chunk getChunkAtWorldCoords(BlockPosition blockposition) {
/*  287 */     return getChunkAt(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final Chunk getChunkAt(int i, int j) {
/*  293 */     ChunkProviderServer cps = ((WorldServer)this).chunkProvider;
/*  294 */     if (cps.serverThread == Thread.currentThread()) {
/*  295 */       Chunk ifLoaded = cps.getChunkAtIfLoadedMainThread(i, j);
/*  296 */       if (ifLoaded != null) {
/*  297 */         return ifLoaded;
/*      */       }
/*      */     } 
/*      */     
/*  301 */     return (Chunk)getChunkAt(i, j, ChunkStatus.FULL, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public final IChunkAccess getChunkIfLoadedImmediately(int x, int z) {
/*  308 */     return ((WorldServer)this).chunkProvider.getChunkAtIfLoadedImmediately(x, z);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final IBlockData getTypeIfLoaded(BlockPosition blockposition) {
/*  314 */     if (this.captureTreeGeneration) {
/*  315 */       CraftBlockState previous = this.capturedBlockStates.get(blockposition);
/*  316 */       if (previous != null) {
/*  317 */         return previous.getHandle();
/*      */       }
/*      */     } 
/*      */     
/*  321 */     if (!isValidLocation(blockposition)) {
/*  322 */       return Blocks.AIR.getBlockData();
/*      */     }
/*  324 */     IChunkAccess chunk = getChunkIfLoadedImmediately(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*      */     
/*  326 */     return (chunk == null) ? null : chunk.getType(blockposition);
/*      */   }
/*      */ 
/*      */   
/*      */   public final Fluid getFluidIfLoaded(BlockPosition blockposition) {
/*  331 */     IChunkAccess chunk = getChunkIfLoadedImmediately(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*      */     
/*  333 */     return (chunk == null) ? null : chunk.getFluid(blockposition);
/*      */   }
/*      */   
/*      */   public final boolean isLoaded(BlockPosition blockposition) {
/*  337 */     return (getChunkIfLoaded(blockposition.getX() >> 4, blockposition.getZ() >> 4) != null);
/*      */   }
/*      */   
/*      */   public final boolean isLoadedAndInBounds(BlockPosition blockposition) {
/*  341 */     return (getWorldBorder().isInBounds(blockposition) && getChunkIfLoadedImmediately(blockposition.getX() >> 4, blockposition.getZ() >> 4) != null);
/*      */   }
/*      */   
/*      */   public Chunk getChunkIfLoaded(int x, int z) {
/*  345 */     return ((WorldServer)this).getChunkProvider().getChunkAtIfLoadedImmediately(x, z);
/*      */   }
/*      */   public final Chunk getChunkIfLoaded(BlockPosition blockposition) {
/*  348 */     return ((WorldServer)this).getChunkProvider().getChunkAtIfLoadedImmediately(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*      */   }
/*      */ 
/*      */   
/*      */   public final IBlockData getTypeIfLoadedAndInBounds(BlockPosition blockposition) {
/*  353 */     return getWorldBorder().isInBounds(blockposition) ? getTypeIfLoaded(blockposition) : null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final IChunkAccess getChunkAt(int i, int j, ChunkStatus chunkstatus, boolean flag) {
/*  359 */     IChunkAccess ichunkaccess = getChunkProvider().getChunkAt(i, j, chunkstatus, flag);
/*      */     
/*  361 */     if (ichunkaccess == null && flag) {
/*  362 */       throw new IllegalStateException("Should always be able to create a chunk!");
/*      */     }
/*  364 */     return ichunkaccess;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean setTypeAndData(BlockPosition blockposition, IBlockData iblockdata, int i) {
/*  370 */     return a(blockposition, iblockdata, i, 512);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(BlockPosition blockposition, IBlockData iblockdata, int i, int j) {
/*  375 */     AsyncCatcher.catchOp("set type call");
/*      */     
/*  377 */     if (this.captureTreeGeneration) {
/*      */       CapturedBlockState capturedBlockState;
/*  379 */       IBlockData type = getType(blockposition);
/*  380 */       if (!type.isDestroyable()) return false;
/*      */       
/*  382 */       CraftBlockState blockstate = this.capturedBlockStates.get(blockposition);
/*  383 */       if (blockstate == null) {
/*  384 */         capturedBlockState = CapturedBlockState.getTreeBlockState(this, blockposition, i);
/*  385 */         this.capturedBlockStates.put(blockposition.immutableCopy(), capturedBlockState);
/*      */       } 
/*  387 */       capturedBlockState.setData(iblockdata);
/*  388 */       return true;
/*      */     } 
/*      */     
/*  391 */     if (isOutsideWorld(blockposition))
/*  392 */       return false; 
/*  393 */     if (!this.isClientSide && isDebugWorld()) {
/*  394 */       return false;
/*      */     }
/*  396 */     Chunk chunk = getChunkAtWorldCoords(blockposition);
/*  397 */     Block block = iblockdata.getBlock();
/*      */ 
/*      */     
/*  400 */     boolean captured = false;
/*  401 */     if (this.captureBlockStates && !this.capturedBlockStates.containsKey(blockposition)) {
/*  402 */       CraftBlockState blockstate = (CraftBlockState)this.world.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()).getState();
/*  403 */       this.capturedBlockStates.put(blockposition.immutableCopy(), blockstate);
/*  404 */       captured = true;
/*      */     } 
/*      */ 
/*      */     
/*  408 */     IBlockData iblockdata1 = chunk.setType(blockposition, iblockdata, ((i & 0x40) != 0), ((i & 0x400) == 0));
/*  409 */     this.chunkPacketBlockController.onBlockChange(this, blockposition, iblockdata, iblockdata1, i);
/*      */     
/*  411 */     if (iblockdata1 == null) {
/*      */       
/*  413 */       if (this.captureBlockStates && captured) {
/*  414 */         this.capturedBlockStates.remove(blockposition);
/*      */       }
/*      */       
/*  417 */       return false;
/*      */     } 
/*  419 */     IBlockData iblockdata2 = getType(blockposition);
/*      */     
/*  421 */     if ((i & 0x80) == 0 && iblockdata2 != iblockdata1 && (iblockdata2.b(this, blockposition) != iblockdata1.b(this, blockposition) || iblockdata2.f() != iblockdata1.f() || iblockdata2.e() || iblockdata1.e())) {
/*  422 */       getMethodProfiler().enter("queueCheckLight");
/*  423 */       getChunkProvider().getLightEngine().a(blockposition);
/*  424 */       getMethodProfiler().exit();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  457 */     if (!this.captureBlockStates) {
/*      */       
/*      */       try {
/*      */         
/*  461 */         notifyAndUpdatePhysics(blockposition, chunk, iblockdata1, iblockdata, iblockdata2, i, j);
/*  462 */       } catch (StackOverflowError ex) {
/*  463 */         lastPhysicsProblem = new BlockPosition(blockposition);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  469 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void notifyAndUpdatePhysics(BlockPosition blockposition, Chunk chunk, IBlockData oldBlock, IBlockData newBlock, IBlockData actualBlock, int i, int j) {
/*  476 */     TickThread.softEnsureTickThread("Async notify and update");
/*  477 */     IBlockData iblockdata = newBlock;
/*  478 */     IBlockData iblockdata1 = oldBlock;
/*  479 */     IBlockData iblockdata2 = actualBlock;
/*  480 */     if (iblockdata2 == iblockdata) {
/*  481 */       if (iblockdata1 != iblockdata2) {
/*  482 */         b(blockposition, iblockdata1, iblockdata2);
/*      */       }
/*      */       
/*  485 */       if ((i & 0x2) != 0 && (!this.isClientSide || (i & 0x4) == 0) && (this.isClientSide || chunk == null || (chunk.getState() != null && chunk.getState().isAtLeast(PlayerChunk.State.TICKING)))) {
/*  486 */         notify(blockposition, iblockdata1, iblockdata, i);
/*      */       
/*      */       }
/*  489 */       else if ((i & 0x2) != 0 && (!this.isClientSide || (i & 0x4) == 0) && (this.isClientSide || chunk == null || (((WorldServer)this).getChunkProvider()).playerChunkMap.playerViewDistanceBroadcastMap.getObjectsInRange(MCUtil.getCoordinateKey(blockposition)) != null)) {
/*  490 */         ((WorldServer)this).getChunkProvider().flagDirty(blockposition);
/*      */       } 
/*      */ 
/*      */       
/*  494 */       if ((i & 0x1) != 0) {
/*  495 */         update(blockposition, iblockdata1.getBlock());
/*  496 */         if (!this.isClientSide && iblockdata.isComplexRedstone()) {
/*  497 */           updateAdjacentComparators(blockposition, newBlock.getBlock());
/*      */         }
/*      */       } 
/*      */       
/*  501 */       if ((i & 0x10) == 0 && j > 0) {
/*  502 */         int k = i & 0xFFFFFFDE;
/*      */ 
/*      */         
/*  505 */         iblockdata1.b(this, blockposition, k, j - 1);
/*  506 */         CraftWorld world = ((WorldServer)this).getWorld();
/*  507 */         if (world != null && ((WorldServer)this).hasPhysicsEvent) {
/*  508 */           BlockPhysicsEvent event = new BlockPhysicsEvent(world.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), (BlockData)CraftBlockData.fromData(iblockdata));
/*  509 */           getServer().getPluginManager().callEvent((Event)event);
/*      */           
/*  511 */           if (event.isCancelled()) {
/*      */             return;
/*      */           }
/*      */         } 
/*      */         
/*  516 */         iblockdata.a(this, blockposition, k, j - 1);
/*  517 */         iblockdata.b(this, blockposition, k, j - 1);
/*      */       } 
/*      */       
/*  520 */       a(blockposition, iblockdata1, iblockdata2);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void a(BlockPosition blockposition, IBlockData iblockdata, IBlockData iblockdata1) {}
/*      */   
/*      */   public boolean setAir(BlockPosition blockposition) {
/*  527 */     return a(blockposition, false); } public boolean setAir(BlockPosition blockposition, boolean moved) {
/*  528 */     return a(blockposition, moved);
/*      */   } public boolean a(BlockPosition blockposition, boolean flag) {
/*  530 */     Fluid fluid = getFluid(blockposition);
/*      */     
/*  532 */     return setTypeAndData(blockposition, fluid.getBlockData(), 0x3 | (flag ? 64 : 0));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(BlockPosition blockposition, boolean flag, @Nullable Entity entity, int i) {
/*  537 */     IBlockData iblockdata = getType(blockposition);
/*      */     
/*  539 */     if (iblockdata.isAir()) {
/*  540 */       return false;
/*      */     }
/*  542 */     Fluid fluid = getFluid(blockposition);
/*      */ 
/*      */ 
/*      */     
/*  546 */     boolean playEffect = true;
/*  547 */     if ((BlockDestroyEvent.getHandlerList().getRegisteredListeners()).length > 0) {
/*  548 */       BlockDestroyEvent event = new BlockDestroyEvent(MCUtil.toBukkitBlock(this, blockposition), (BlockData)fluid.getBlockData().createCraftBlockData(), flag);
/*  549 */       if (!event.callEvent()) {
/*  550 */         return false;
/*      */       }
/*  552 */       playEffect = event.playEffect();
/*      */     } 
/*      */ 
/*      */     
/*  556 */     if (playEffect && !(iblockdata.getBlock() instanceof BlockFireAbstract)) {
/*  557 */       triggerEffect(2001, blockposition, Block.getCombinedId(iblockdata));
/*      */     }
/*      */     
/*  560 */     if (flag) {
/*  561 */       TileEntity tileentity = iblockdata.getBlock().isTileEntity() ? getTileEntity(blockposition) : null;
/*      */       
/*  563 */       Block.dropItems(iblockdata, this, blockposition, tileentity, entity, ItemStack.b);
/*      */     } 
/*      */     
/*  566 */     return a(blockposition, fluid.getBlockData(), 3, i);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setTypeUpdate(BlockPosition blockposition, IBlockData iblockdata) {
/*  571 */     return setTypeAndData(blockposition, iblockdata, 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public void b(BlockPosition blockposition, IBlockData iblockdata, IBlockData iblockdata1) {}
/*      */ 
/*      */   
/*      */   public void applyPhysics(BlockPosition blockposition, Block block) {
/*  579 */     if (this.captureBlockStates)
/*  580 */       return;  a(blockposition.west(), block, blockposition);
/*  581 */     a(blockposition.east(), block, blockposition);
/*  582 */     a(blockposition.down(), block, blockposition);
/*  583 */     a(blockposition.up(), block, blockposition);
/*  584 */     a(blockposition.north(), block, blockposition);
/*  585 */     a(blockposition.south(), block, blockposition);
/*      */   }
/*      */   
/*      */   public void a(BlockPosition blockposition, Block block, EnumDirection enumdirection) {
/*  589 */     if (enumdirection != EnumDirection.WEST) {
/*  590 */       a(blockposition.west(), block, blockposition);
/*      */     }
/*      */     
/*  593 */     if (enumdirection != EnumDirection.EAST) {
/*  594 */       a(blockposition.east(), block, blockposition);
/*      */     }
/*      */     
/*  597 */     if (enumdirection != EnumDirection.DOWN) {
/*  598 */       a(blockposition.down(), block, blockposition);
/*      */     }
/*      */     
/*  601 */     if (enumdirection != EnumDirection.UP) {
/*  602 */       a(blockposition.up(), block, blockposition);
/*      */     }
/*      */     
/*  605 */     if (enumdirection != EnumDirection.NORTH) {
/*  606 */       a(blockposition.north(), block, blockposition);
/*      */     }
/*      */     
/*  609 */     if (enumdirection != EnumDirection.SOUTH) {
/*  610 */       a(blockposition.south(), block, blockposition);
/*      */     }
/*      */   }
/*      */   
/*      */   public void neighborChanged(BlockPosition pos, Block blockIn, BlockPosition fromPos) {
/*  615 */     a(pos, blockIn, fromPos);
/*      */   } public void a(BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  617 */     if (!this.isClientSide) {
/*  618 */       IBlockData iblockdata = getType(blockposition);
/*      */ 
/*      */       
/*      */       try {
/*  622 */         CraftWorld world = ((WorldServer)this).getWorld();
/*  623 */         if (world != null && ((WorldServer)this).hasPhysicsEvent) {
/*  624 */           BlockPhysicsEvent event = new BlockPhysicsEvent(world.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), (BlockData)CraftBlockData.fromData(iblockdata), world.getBlockAt(blockposition1.getX(), blockposition1.getY(), blockposition1.getZ()));
/*  625 */           getServer().getPluginManager().callEvent((Event)event);
/*      */           
/*  627 */           if (event.isCancelled()) {
/*      */             return;
/*      */           }
/*      */         } 
/*      */         
/*  632 */         iblockdata.doPhysics(this, blockposition, block, blockposition1, false);
/*      */       }
/*  634 */       catch (StackOverflowError ex) {
/*  635 */         lastPhysicsProblem = new BlockPosition(blockposition);
/*      */       }
/*  637 */       catch (Throwable throwable) {
/*  638 */         CrashReport crashreport = CrashReport.a(throwable, "Exception while updating neighbours");
/*  639 */         CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Block being updated");
/*      */         
/*  641 */         crashreportsystemdetails.a("Source block type", () -> {
/*      */               try {
/*      */                 return String.format("ID #%s (%s // %s)", new Object[] { IRegistry.BLOCK.getKey(block), block.i(), block.getClass().getCanonicalName() });
/*  644 */               } catch (Throwable throwable1) {
/*      */                 return "ID #" + IRegistry.BLOCK.getKey(block);
/*      */               } 
/*      */             });
/*  648 */         CrashReportSystemDetails.a(crashreportsystemdetails, blockposition, iblockdata);
/*  649 */         throw new ReportedException(crashreport);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   public final int getHighestBlockY(HeightMap.Type heightmap, int x, int z) {
/*  654 */     return a(heightmap, x, z);
/*      */   }
/*      */   public int a(HeightMap.Type heightmap_type, int i, int j) {
/*      */     int k;
/*  658 */     if (i >= -30000000 && j >= -30000000 && i < 30000000 && j < 30000000) {
/*  659 */       if (isChunkLoaded(i >> 4, j >> 4)) {
/*  660 */         k = getChunkAt(i >> 4, j >> 4).getHighestBlock(heightmap_type, i & 0xF, j & 0xF) + 1;
/*      */       } else {
/*  662 */         k = 0;
/*      */       } 
/*      */     } else {
/*  665 */       k = getSeaLevel() + 1;
/*      */     } 
/*      */     
/*  668 */     return k;
/*      */   }
/*      */ 
/*      */   
/*      */   public LightEngine e() {
/*  673 */     return getChunkProvider().getLightEngine();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IBlockData getType(BlockPosition blockposition) {
/*  679 */     if (this.captureTreeGeneration) {
/*  680 */       CraftBlockState previous = this.capturedBlockStates.get(blockposition);
/*  681 */       if (previous != null) {
/*  682 */         return previous.getHandle();
/*      */       }
/*      */     } 
/*      */     
/*  686 */     if (isOutsideWorld(blockposition)) {
/*  687 */       return Blocks.VOID_AIR.getBlockData();
/*      */     }
/*  689 */     Chunk chunk = getChunkAt(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*      */     
/*  691 */     return chunk.getType(blockposition);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Fluid getFluid(BlockPosition blockposition) {
/*  697 */     if (isOutsideWorld(blockposition)) {
/*  698 */       return FluidTypes.EMPTY.h();
/*      */     }
/*  700 */     Chunk chunk = getChunkAtWorldCoords(blockposition);
/*      */     
/*  702 */     return chunk.getFluid(blockposition);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDay() {
/*  707 */     return (!getDimensionManager().isFixedTime() && this.d < 4);
/*      */   }
/*      */   
/*      */   public boolean isNight() {
/*  711 */     return (!getDimensionManager().isFixedTime() && !isDay());
/*      */   }
/*      */ 
/*      */   
/*      */   public void playSound(@Nullable EntityHuman entityhuman, BlockPosition blockposition, SoundEffect soundeffect, SoundCategory soundcategory, float f, float f1) {
/*  716 */     playSound(entityhuman, blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D, soundeffect, soundcategory, f, f1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(double d0, double d1, double d2, SoundEffect soundeffect, SoundCategory soundcategory, float f, float f1, boolean flag) {}
/*      */ 
/*      */   
/*      */   public void addParticle(ParticleParam particleparam, double d0, double d1, double d2, double d3, double d4, double d5) {}
/*      */ 
/*      */   
/*      */   public void b(ParticleParam particleparam, double d0, double d1, double d2, double d3, double d4, double d5) {}
/*      */ 
/*      */   
/*      */   public void b(ParticleParam particleparam, boolean flag, double d0, double d1, double d2, double d3, double d4, double d5) {}
/*      */ 
/*      */   
/*      */   public float a(float f) {
/*  733 */     float f1 = f(f);
/*      */     
/*  735 */     return f1 * 6.2831855F;
/*      */   }
/*      */   
/*      */   public boolean a(TileEntity tileentity) {
/*  739 */     if (this.tickingTileEntities) {
/*  740 */       (new Supplier[2])[0] = (() -> IRegistry.BLOCK_ENTITY_TYPE.getKey(tileentity.getTileType()));
/*      */       
/*  742 */       Objects.requireNonNull(tileentity); LOGGER.error("Adding block entity while ticking: {} @ {}", new Supplier[] { null, tileentity::getPosition });
/*      */     } 
/*      */     
/*  745 */     boolean flag = true;
/*      */     
/*  747 */     if (flag && tileentity instanceof ITickable && !this.tileEntityListTick.contains(tileentity)) {
/*  748 */       this.tileEntityListTick.add(tileentity);
/*      */     }
/*      */     
/*  751 */     if (this.isClientSide) {
/*  752 */       BlockPosition blockposition = tileentity.getPosition();
/*  753 */       IBlockData iblockdata = getType(blockposition);
/*      */       
/*  755 */       notify(blockposition, iblockdata, iblockdata, 2);
/*      */     } 
/*      */     
/*  758 */     return flag;
/*      */   }
/*      */   
/*      */   public void a(Collection<TileEntity> collection) {
/*  762 */     if (this.tickingTileEntities) {
/*  763 */       this.tileEntityListPending.addAll(collection);
/*      */     } else {
/*  765 */       Iterator<TileEntity> iterator = collection.iterator();
/*      */       
/*  767 */       while (iterator.hasNext()) {
/*  768 */         TileEntity tileentity = iterator.next();
/*      */         
/*  770 */         a(tileentity);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void tickBlockEntities() {
/*  777 */     GameProfilerFiller gameprofilerfiller = getMethodProfiler();
/*      */     
/*  779 */     gameprofilerfiller.enter("blockEntities");
/*  780 */     this.timings.tileEntityTick.startTiming();
/*  781 */     if (!this.tileEntityListUnload.isEmpty()) {
/*      */       
/*  783 */       Set<TileEntity> toRemove = Collections.newSetFromMap(new IdentityHashMap<>());
/*  784 */       toRemove.addAll(this.tileEntityListUnload);
/*  785 */       this.tileEntityListTick.removeAll(toRemove);
/*      */ 
/*      */       
/*  788 */       this.tileEntityListUnload.clear();
/*      */     } 
/*      */     
/*  791 */     this.tickingTileEntities = true;
/*      */ 
/*      */     
/*  794 */     int tilesThisCycle = 0;
/*  795 */     for (this.tileTickPosition = 0; this.tileTickPosition < this.tileEntityListTick.size(); this.tileTickPosition++) {
/*  796 */       this.tileTickPosition = (this.tileTickPosition < this.tileEntityListTick.size()) ? this.tileTickPosition : 0;
/*  797 */       TileEntity tileentity = this.tileEntityListTick.get(this.tileTickPosition);
/*      */       
/*  799 */       if (tileentity == null) {
/*  800 */         getServer().getLogger().severe("Spigot has detected a null entity and has removed it, preventing a crash");
/*  801 */         tilesThisCycle--;
/*  802 */         this.tileEntityListTick.remove(this.tileTickPosition--);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  807 */         if (!tileentity.isRemoved() && tileentity.hasWorld()) {
/*  808 */           BlockPosition blockposition = tileentity.getPosition();
/*      */           
/*  810 */           if (getChunkProvider().a(blockposition) && getWorldBorder().a(blockposition)) {
/*      */             try {
/*  812 */               gameprofilerfiller.a(() -> String.valueOf(TileEntityTypes.a(tileentity.getTileType())));
/*      */ 
/*      */               
/*  815 */               tileentity.tickTimer.startTiming();
/*  816 */               if (tileentity.getTileType().isValidBlock(getType(blockposition).getBlock())) {
/*  817 */                 ((ITickable)tileentity).tick();
/*      */               } else {
/*  819 */                 tileentity.w();
/*      */               } 
/*      */               
/*  822 */               gameprofilerfiller.exit();
/*  823 */             } catch (Throwable throwable) {
/*  824 */               if (throwable instanceof ThreadDeath) throw throwable;
/*      */               
/*  826 */               String msg = "TileEntity threw exception at " + tileentity.world.getWorld().getName() + ":" + tileentity.position.getX() + "," + tileentity.position.getY() + "," + tileentity.position.getZ();
/*  827 */               System.err.println(msg);
/*  828 */               throwable.printStackTrace();
/*  829 */               getServer().getPluginManager().callEvent((Event)new ServerExceptionEvent((ServerException)new ServerInternalException(msg, throwable)));
/*      */               
/*  831 */               tilesThisCycle--;
/*  832 */               this.tileEntityListTick.remove(this.tileTickPosition--);
/*      */             
/*      */             }
/*      */             finally {
/*      */ 
/*      */               
/*  838 */               tileentity.tickTimer.stopTiming();
/*      */             } 
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  844 */         if (tileentity.isRemoved()) {
/*      */           
/*  846 */           tilesThisCycle--;
/*  847 */           this.tileEntityListTick.remove(this.tileTickPosition--);
/*      */ 
/*      */           
/*  850 */           if (isLoaded(tileentity.getPosition())) {
/*  851 */             getChunkAtWorldCoords(tileentity.getPosition()).removeTileEntity(tileentity.getPosition());
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*  856 */     this.timings.tileEntityTick.stopTiming();
/*  857 */     this.timings.tileEntityPending.startTiming();
/*  858 */     this.tickingTileEntities = false;
/*  859 */     gameprofilerfiller.exitEnter("pendingBlockEntities");
/*  860 */     if (!this.tileEntityListPending.isEmpty()) {
/*  861 */       for (int i = 0; i < this.tileEntityListPending.size(); i++) {
/*  862 */         TileEntity tileentity1 = this.tileEntityListPending.get(i);
/*      */         
/*  864 */         if (!tileentity1.isRemoved())
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  871 */           if (isLoaded(tileentity1.getPosition())) {
/*  872 */             Chunk chunk = getChunkAtWorldCoords(tileentity1.getPosition());
/*  873 */             IBlockData iblockdata = chunk.getType(tileentity1.getPosition());
/*      */             
/*  875 */             chunk.setTileEntity(tileentity1.getPosition(), tileentity1);
/*  876 */             notify(tileentity1.getPosition(), iblockdata, iblockdata, 3);
/*      */ 
/*      */ 
/*      */             
/*  880 */             a(tileentity1);
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  887 */       this.tileEntityListPending.clear();
/*      */     } 
/*      */     
/*  890 */     this.timings.tileEntityPending.stopTiming();
/*  891 */     TimingHistory.tileEntityTicks += this.tileEntityListTick.size();
/*  892 */     gameprofilerfiller.exit();
/*  893 */     this.spigotConfig.currentPrimedTnt = 0;
/*      */   }
/*      */   
/*      */   public void a(Consumer<Entity> consumer, Entity entity) {
/*      */     try {
/*  898 */       consumer.accept(entity);
/*  899 */     } catch (Throwable throwable) {
/*  900 */       if (throwable instanceof ThreadDeath) throw throwable;
/*      */       
/*  902 */       String msg = "Entity threw exception at " + entity.world.getWorld().getName() + ":" + entity.locX() + "," + entity.locY() + "," + entity.locZ();
/*  903 */       System.err.println(msg);
/*  904 */       throwable.printStackTrace();
/*  905 */       getServer().getPluginManager().callEvent((Event)new ServerExceptionEvent((ServerException)new ServerInternalException(msg, throwable)));
/*  906 */       entity.dead = true;
/*      */       
/*      */       return;
/*      */     } 
/*  910 */     MinecraftServer.getServer().executeMidTickTasks();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getCubes(@Nullable Entity entity, AxisAlignedBB axisAlignedBB) {
/*  915 */     if (entity instanceof EntityArmorStand && !entity.world.paperConfig.armorStandEntityLookups) return false; 
/*  916 */     return super.getCubes(entity, axisAlignedBB);
/*      */   }
/*      */ 
/*      */   
/*      */   public Explosion explode(@Nullable Entity entity, double d0, double d1, double d2, float f, Explosion.Effect explosion_effect) {
/*  921 */     return createExplosion(entity, (DamageSource)null, (ExplosionDamageCalculator)null, d0, d1, d2, f, false, explosion_effect);
/*      */   }
/*      */   
/*      */   public Explosion createExplosion(@Nullable Entity entity, double d0, double d1, double d2, float f, boolean flag, Explosion.Effect explosion_effect) {
/*  925 */     return createExplosion(entity, (DamageSource)null, (ExplosionDamageCalculator)null, d0, d1, d2, f, flag, explosion_effect);
/*      */   }
/*      */   
/*      */   public Explosion createExplosion(@Nullable Entity entity, @Nullable DamageSource damagesource, @Nullable ExplosionDamageCalculator explosiondamagecalculator, double d0, double d1, double d2, float f, boolean flag, Explosion.Effect explosion_effect) {
/*  929 */     Explosion explosion = new Explosion(this, entity, damagesource, explosiondamagecalculator, d0, d1, d2, f, flag, explosion_effect);
/*      */     
/*  931 */     explosion.a();
/*  932 */     explosion.a(true);
/*  933 */     return explosion;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public TileEntity getTileEntity(BlockPosition blockposition) {
/*  940 */     return getTileEntity(blockposition, true);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   protected TileEntity getTileEntity(BlockPosition blockposition, boolean validate) {
/*  946 */     if (isOutsideWorld(blockposition))
/*  947 */       return null; 
/*  948 */     if (!this.isClientSide && Thread.currentThread() != this.serverThread) {
/*  949 */       return null;
/*      */     }
/*      */     
/*  952 */     TileEntity tileentity = null;
/*  953 */     if (!this.capturedTileEntities.isEmpty() && (tileentity = this.capturedTileEntities.get(blockposition)) != null) {
/*  954 */       return tileentity;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  960 */     if (this.tickingTileEntities) {
/*  961 */       tileentity = E(blockposition);
/*      */     }
/*      */     
/*  964 */     if (tileentity == null) {
/*  965 */       tileentity = getChunkAtWorldCoords(blockposition).a(blockposition, Chunk.EnumTileEntityState.IMMEDIATE);
/*      */     }
/*      */     
/*  968 */     if (tileentity == null) {
/*  969 */       tileentity = E(blockposition);
/*      */     }
/*      */     
/*  972 */     return tileentity;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   private TileEntity E(BlockPosition blockposition) {
/*  978 */     for (int i = 0; i < this.tileEntityListPending.size(); i++) {
/*  979 */       TileEntity tileentity = this.tileEntityListPending.get(i);
/*      */       
/*  981 */       if (!tileentity.isRemoved() && tileentity.getPosition().equals(blockposition)) {
/*  982 */         return tileentity;
/*      */       }
/*      */     } 
/*      */     
/*  986 */     return null;
/*      */   }
/*      */   
/*      */   public void setTileEntity(BlockPosition blockposition, @Nullable TileEntity tileentity) {
/*  990 */     if (!isOutsideWorld(blockposition) && 
/*  991 */       tileentity != null && !tileentity.isRemoved()) {
/*      */       
/*  993 */       if (this.captureBlockStates) {
/*  994 */         tileentity.setLocation(this, blockposition);
/*  995 */         this.capturedTileEntities.put(blockposition.immutableCopy(), tileentity);
/*      */         
/*      */         return;
/*      */       } 
/*  999 */       if (this.tickingTileEntities) {
/* 1000 */         tileentity.setLocation(this, blockposition);
/* 1001 */         Iterator<TileEntity> iterator = this.tileEntityListPending.iterator();
/*      */         
/* 1003 */         while (iterator.hasNext()) {
/* 1004 */           TileEntity tileentity1 = iterator.next();
/*      */           
/* 1006 */           if (tileentity1.getPosition().equals(blockposition)) {
/* 1007 */             tileentity1.al_();
/* 1008 */             iterator.remove();
/*      */           } 
/*      */         } 
/*      */         
/* 1012 */         this.tileEntityListPending.add(tileentity);
/*      */       } else {
/* 1014 */         getChunkAtWorldCoords(blockposition).setTileEntity(blockposition, tileentity);
/* 1015 */         a(tileentity);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeTileEntity(BlockPosition blockposition) {
/* 1023 */     TileEntity tileentity = getTileEntity(blockposition, false);
/*      */     
/* 1025 */     if (tileentity != null && this.tickingTileEntities) {
/* 1026 */       tileentity.al_();
/* 1027 */       this.tileEntityListPending.remove(tileentity);
/*      */     } else {
/* 1029 */       if (tileentity != null) {
/* 1030 */         this.tileEntityListPending.remove(tileentity);
/*      */         
/* 1032 */         this.tileEntityListTick.remove(tileentity);
/*      */       } 
/*      */       
/* 1035 */       getChunkAtWorldCoords(blockposition).removeTileEntity(blockposition);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean p(BlockPosition blockposition) {
/* 1041 */     return isOutsideWorld(blockposition) ? false : isChunkLoaded(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*      */   }
/*      */   
/*      */   public boolean a(BlockPosition blockposition, Entity entity, EnumDirection enumdirection) {
/* 1045 */     if (isOutsideWorld(blockposition)) {
/* 1046 */       return false;
/*      */     }
/* 1048 */     IChunkAccess ichunkaccess = getChunkIfLoadedImmediately(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*      */     
/* 1050 */     return (ichunkaccess == null) ? false : ichunkaccess.getType(blockposition).a(this, blockposition, entity, enumdirection);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(BlockPosition blockposition, Entity entity) {
/* 1055 */     return a(blockposition, entity, EnumDirection.UP);
/*      */   }
/*      */   
/*      */   public void P() {
/* 1059 */     double d0 = 1.0D - (d(1.0F) * 5.0F) / 16.0D;
/* 1060 */     double d1 = 1.0D - (b(1.0F) * 5.0F) / 16.0D;
/* 1061 */     double d2 = 0.5D + 2.0D * MathHelper.a(MathHelper.cos(f(1.0F) * 6.2831855F), -0.25D, 0.25D);
/*      */     
/* 1063 */     this.d = (int)((1.0D - d2 * d0 * d1) * 11.0D);
/*      */   }
/*      */   
/*      */   public void setSpawnFlags(boolean flag, boolean flag1) {
/* 1067 */     getChunkProvider().a(flag, flag1);
/*      */   }
/*      */   
/*      */   protected void Q() {
/* 1071 */     if (this.worldData.hasStorm()) {
/* 1072 */       this.rainLevel = 1.0F;
/* 1073 */       if (this.worldData.isThundering()) {
/* 1074 */         this.thunderLevel = 1.0F;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void close() throws IOException {
/* 1081 */     getChunkProvider().close();
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public IBlockAccess c(int i, int j) {
/* 1087 */     return getChunkAt(i, j, ChunkStatus.FULL, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Entity> getHardCollidingEntities(@Nullable Entity entity, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate) {
/* 1093 */     return getHardCollidingEntities(entity, axisalignedbb, predicate, Lists.newArrayList());
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Entity> getHardCollidingEntities(@Nullable Entity entity, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate, List<Entity> list) {
/* 1098 */     int i = MathHelper.floor((axisalignedbb.minX - 2.0D) / 16.0D);
/* 1099 */     int j = MathHelper.floor((axisalignedbb.maxX + 2.0D) / 16.0D);
/* 1100 */     int k = MathHelper.floor((axisalignedbb.minZ - 2.0D) / 16.0D);
/* 1101 */     int l = MathHelper.floor((axisalignedbb.maxZ + 2.0D) / 16.0D);
/*      */     
/* 1103 */     ChunkProviderServer chunkProvider = ((WorldServer)this).getChunkProvider();
/*      */     
/* 1105 */     for (int i1 = i; i1 <= j; i1++) {
/* 1106 */       for (int j1 = k; j1 <= l; j1++) {
/* 1107 */         Chunk chunk = chunkProvider.getChunkAtIfLoadedMainThread(i1, j1);
/*      */         
/* 1109 */         if (chunk != null) {
/* 1110 */           chunk.getHardCollidingEntities(entity, axisalignedbb, list, predicate);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1115 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Entity> getEntities(@Nullable Entity entity, AxisAlignedBB axisalignedbb, @Nullable Predicate<? super Entity> predicate) {
/* 1122 */     List<Entity> list = Lists.newArrayList();
/* 1123 */     return getEntities(entity, axisalignedbb, predicate, list);
/*      */   }
/*      */   
/*      */   public List<Entity> getEntities(@Nullable Entity entity, AxisAlignedBB axisalignedbb, @Nullable Predicate<? super Entity> predicate, List<Entity> list) {
/* 1127 */     getMethodProfiler().c("getEntities");
/* 1128 */     int i = MathHelper.floor((axisalignedbb.minX - 2.0D) / 16.0D);
/* 1129 */     int j = MathHelper.floor((axisalignedbb.maxX + 2.0D) / 16.0D);
/* 1130 */     int k = MathHelper.floor((axisalignedbb.minZ - 2.0D) / 16.0D);
/* 1131 */     int l = MathHelper.floor((axisalignedbb.maxZ + 2.0D) / 16.0D);
/* 1132 */     IChunkProvider ichunkprovider = getChunkProvider();
/*      */     
/* 1134 */     for (int i1 = i; i1 <= j; i1++) {
/* 1135 */       for (int j1 = k; j1 <= l; j1++) {
/* 1136 */         Chunk chunk = (Chunk)getChunkIfLoadedImmediately(i1, j1);
/*      */         
/* 1138 */         if (chunk != null) {
/* 1139 */           chunk.a(entity, axisalignedbb, list, predicate);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1144 */     return list;
/*      */   }
/*      */   
/*      */   public <T extends Entity> List<T> a(@Nullable EntityTypes<T> entitytypes, AxisAlignedBB axisalignedbb, Predicate<? super T> predicate) {
/* 1148 */     getMethodProfiler().c("getEntities");
/* 1149 */     int i = MathHelper.floor((axisalignedbb.minX - 2.0D) / 16.0D);
/* 1150 */     int j = MathHelper.f((axisalignedbb.maxX + 2.0D) / 16.0D);
/* 1151 */     int k = MathHelper.floor((axisalignedbb.minZ - 2.0D) / 16.0D);
/* 1152 */     int l = MathHelper.f((axisalignedbb.maxZ + 2.0D) / 16.0D);
/* 1153 */     List<T> list = Lists.newArrayList();
/*      */     
/* 1155 */     for (int i1 = i; i1 < j; i1++) {
/* 1156 */       for (int j1 = k; j1 < l; j1++) {
/* 1157 */         Chunk chunk = (Chunk)getChunkIfLoadedImmediately(i1, j1);
/*      */         
/* 1159 */         if (chunk != null) {
/* 1160 */           chunk.a(entitytypes, axisalignedbb, list, predicate);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1165 */     return list;
/*      */   }
/*      */   public <T extends Entity> List<T> getEntities(Class<? extends T> oclass, AxisAlignedBB axisalignedbb, @Nullable Predicate<? super T> predicate) {
/* 1168 */     return a(oclass, axisalignedbb, predicate);
/*      */   } public <T extends Entity> List<T> a(Class<? extends T> oclass, AxisAlignedBB axisalignedbb, @Nullable Predicate<? super T> predicate) {
/* 1170 */     getMethodProfiler().c("getEntities");
/* 1171 */     int i = MathHelper.floor((axisalignedbb.minX - 2.0D) / 16.0D);
/* 1172 */     int j = MathHelper.f((axisalignedbb.maxX + 2.0D) / 16.0D);
/* 1173 */     int k = MathHelper.floor((axisalignedbb.minZ - 2.0D) / 16.0D);
/* 1174 */     int l = MathHelper.f((axisalignedbb.maxZ + 2.0D) / 16.0D);
/* 1175 */     List<T> list = Lists.newArrayList();
/* 1176 */     IChunkProvider ichunkprovider = getChunkProvider();
/*      */     
/* 1178 */     for (int i1 = i; i1 < j; i1++) {
/* 1179 */       for (int j1 = k; j1 < l; j1++) {
/* 1180 */         Chunk chunk = (Chunk)getChunkIfLoadedImmediately(i1, j1);
/*      */         
/* 1182 */         if (chunk != null) {
/* 1183 */           chunk.a(oclass, axisalignedbb, list, predicate);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1188 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   public <T extends Entity> List<T> b(Class<? extends T> oclass, AxisAlignedBB axisalignedbb, @Nullable Predicate<? super T> predicate) {
/* 1193 */     getMethodProfiler().c("getLoadedEntities");
/* 1194 */     int i = MathHelper.floor((axisalignedbb.minX - 2.0D) / 16.0D);
/* 1195 */     int j = MathHelper.f((axisalignedbb.maxX + 2.0D) / 16.0D);
/* 1196 */     int k = MathHelper.floor((axisalignedbb.minZ - 2.0D) / 16.0D);
/* 1197 */     int l = MathHelper.f((axisalignedbb.maxZ + 2.0D) / 16.0D);
/* 1198 */     List<T> list = Lists.newArrayList();
/* 1199 */     IChunkProvider ichunkprovider = getChunkProvider();
/*      */     
/* 1201 */     for (int i1 = i; i1 < j; i1++) {
/* 1202 */       for (int j1 = k; j1 < l; j1++) {
/* 1203 */         Chunk chunk = (Chunk)getChunkIfLoadedImmediately(i1, j1);
/*      */         
/* 1205 */         if (chunk != null) {
/* 1206 */           chunk.a(oclass, axisalignedbb, list, predicate);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1211 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void b(BlockPosition blockposition, TileEntity tileentity) {
/* 1218 */     if (isLoaded(blockposition)) {
/* 1219 */       getChunkAtWorldCoords(blockposition).markDirty();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSeaLevel() {
/* 1226 */     return 63;
/*      */   }
/*      */   
/*      */   public int getBlockPower(BlockPosition blockposition) {
/* 1230 */     byte b0 = 0;
/* 1231 */     int i = Math.max(b0, c(blockposition.down(), EnumDirection.DOWN));
/*      */     
/* 1233 */     if (i >= 15) {
/* 1234 */       return i;
/*      */     }
/* 1236 */     i = Math.max(i, c(blockposition.up(), EnumDirection.UP));
/* 1237 */     if (i >= 15) {
/* 1238 */       return i;
/*      */     }
/* 1240 */     i = Math.max(i, c(blockposition.north(), EnumDirection.NORTH));
/* 1241 */     if (i >= 15) {
/* 1242 */       return i;
/*      */     }
/* 1244 */     i = Math.max(i, c(blockposition.south(), EnumDirection.SOUTH));
/* 1245 */     if (i >= 15) {
/* 1246 */       return i;
/*      */     }
/* 1248 */     i = Math.max(i, c(blockposition.west(), EnumDirection.WEST));
/* 1249 */     if (i >= 15) {
/* 1250 */       return i;
/*      */     }
/* 1252 */     i = Math.max(i, c(blockposition.east(), EnumDirection.EAST));
/* 1253 */     return (i >= 15) ? i : i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBlockFacePowered(BlockPosition blockposition, EnumDirection enumdirection) {
/* 1262 */     return (getBlockFacePower(blockposition, enumdirection) > 0);
/*      */   }
/*      */   
/*      */   public int getBlockFacePower(BlockPosition blockposition, EnumDirection enumdirection) {
/* 1266 */     IBlockData iblockdata = getType(blockposition);
/* 1267 */     int i = iblockdata.b(this, blockposition, enumdirection);
/*      */     
/* 1269 */     return iblockdata.isOccluding(this, blockposition) ? Math.max(i, getBlockPower(blockposition)) : i;
/*      */   }
/*      */   
/*      */   public boolean isBlockIndirectlyPowered(BlockPosition blockposition) {
/* 1273 */     return (getBlockFacePower(blockposition.down(), EnumDirection.DOWN) > 0) ? true : ((getBlockFacePower(blockposition.up(), EnumDirection.UP) > 0) ? true : ((getBlockFacePower(blockposition.north(), EnumDirection.NORTH) > 0) ? true : ((getBlockFacePower(blockposition.south(), EnumDirection.SOUTH) > 0) ? true : ((getBlockFacePower(blockposition.west(), EnumDirection.WEST) > 0) ? true : ((getBlockFacePower(blockposition.east(), EnumDirection.EAST) > 0))))));
/*      */   }
/*      */   public int isBlockIndirectlyGettingPowered(BlockPosition pos) {
/* 1276 */     return s(pos);
/*      */   } public int s(BlockPosition blockposition) {
/* 1278 */     int i = 0;
/* 1279 */     EnumDirection[] aenumdirection = a;
/* 1280 */     int j = aenumdirection.length;
/*      */     
/* 1282 */     for (int k = 0; k < j; k++) {
/* 1283 */       EnumDirection enumdirection = aenumdirection[k];
/* 1284 */       int l = getBlockFacePower(blockposition.shift(enumdirection), enumdirection);
/*      */       
/* 1286 */       if (l >= 15) {
/* 1287 */         return 15;
/*      */       }
/*      */       
/* 1290 */       if (l > i) {
/* 1291 */         i = l;
/*      */       }
/*      */     } 
/*      */     
/* 1295 */     return i;
/*      */   }
/*      */   
/*      */   public long getTime() {
/* 1299 */     return this.worldData.getTime();
/*      */   }
/*      */   
/*      */   public long getDayTime() {
/* 1303 */     return this.worldData.getDayTime();
/*      */   }
/*      */   
/*      */   public boolean a(EntityHuman entityhuman, BlockPosition blockposition) {
/* 1307 */     return true;
/*      */   }
/*      */   
/*      */   public void broadcastEntityEffect(Entity entity, byte b0) {}
/*      */   
/*      */   public void playBlockAction(BlockPosition blockposition, Block block, int i, int j) {
/* 1313 */     getType(blockposition).a(this, blockposition, i, j);
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldData getWorldData() {
/* 1318 */     return this.worldData;
/*      */   }
/*      */   
/*      */   public GameRules getGameRules() {
/* 1322 */     return this.worldData.q();
/*      */   }
/*      */   
/*      */   public float b(float f) {
/* 1326 */     return MathHelper.g(f, this.lastThunderLevel, this.thunderLevel) * d(f);
/*      */   }
/*      */   
/*      */   public float d(float f) {
/* 1330 */     return MathHelper.g(f, this.lastRainLevel, this.rainLevel);
/*      */   }
/*      */   
/*      */   public boolean V() {
/* 1334 */     return (getDimensionManager().hasSkyLight() && !getDimensionManager().hasCeiling()) ? ((b(1.0F) > 0.9D)) : false;
/*      */   }
/*      */   
/*      */   public boolean isRaining() {
/* 1338 */     return (d(1.0F) > 0.2D);
/*      */   }
/*      */   
/*      */   public boolean isRainingAt(BlockPosition blockposition) {
/* 1342 */     if (!isRaining())
/* 1343 */       return false; 
/* 1344 */     if (!e(blockposition))
/* 1345 */       return false; 
/* 1346 */     if (getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, blockposition).getY() > blockposition.getY()) {
/* 1347 */       return false;
/*      */     }
/* 1349 */     BiomeBase biomebase = getBiome(blockposition);
/*      */     
/* 1351 */     return (biomebase.c() == BiomeBase.Precipitation.RAIN && biomebase.getAdjustedTemperature(blockposition) >= 0.15F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean u(BlockPosition blockposition) {
/* 1356 */     BiomeBase biomebase = getBiome(blockposition);
/*      */     
/* 1358 */     return biomebase.d();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void b(int i, BlockPosition blockposition, int j) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CrashReportSystemDetails a(CrashReport crashreport) {
/* 1371 */     CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Affected level", 1);
/*      */     
/* 1373 */     crashreportsystemdetails.a("All players", () -> getPlayers().size() + " total; " + getPlayers());
/*      */ 
/*      */     
/* 1376 */     IChunkProvider ichunkprovider = getChunkProvider();
/*      */     
/* 1378 */     Objects.requireNonNull(ichunkprovider); crashreportsystemdetails.a("Chunk stats", ichunkprovider::getName);
/* 1379 */     crashreportsystemdetails.a("Level dimension", () -> getDimensionKey().a().toString());
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1384 */       this.worldData.a(crashreportsystemdetails);
/* 1385 */     } catch (Throwable throwable) {
/* 1386 */       crashreportsystemdetails.a("Level Data Unobtainable", throwable);
/*      */     } 
/*      */     
/* 1389 */     return crashreportsystemdetails;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateAdjacentComparators(BlockPosition blockposition, Block block) {
/* 1397 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*      */     
/* 1399 */     while (iterator.hasNext()) {
/* 1400 */       EnumDirection enumdirection = iterator.next();
/* 1401 */       BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*      */       
/* 1403 */       if (isLoaded(blockposition1)) {
/* 1404 */         IBlockData iblockdata = getType(blockposition1);
/*      */         
/* 1406 */         if (iblockdata.a(Blocks.COMPARATOR)) {
/* 1407 */           iblockdata.doPhysics(this, blockposition1, block, blockposition, false); continue;
/* 1408 */         }  if (iblockdata.isOccluding(this, blockposition1)) {
/* 1409 */           blockposition1 = blockposition1.shift(enumdirection);
/* 1410 */           iblockdata = getType(blockposition1);
/* 1411 */           if (iblockdata.a(Blocks.COMPARATOR)) {
/* 1412 */             iblockdata.doPhysics(this, blockposition1, block, blockposition, false);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DifficultyDamageScaler getDamageScaler(BlockPosition blockposition) {
/* 1422 */     long i = 0L;
/* 1423 */     float f = 0.0F;
/*      */     
/* 1425 */     if (isLoaded(blockposition)) {
/* 1426 */       f = ae();
/* 1427 */       i = getChunkAtWorldCoords(blockposition).getInhabitedTime();
/*      */     } 
/*      */     
/* 1430 */     return new DifficultyDamageScaler(getDifficulty(), getDayTime(), i, f);
/*      */   }
/*      */ 
/*      */   
/*      */   public int c() {
/* 1435 */     return this.d;
/*      */   }
/*      */ 
/*      */   
/*      */   public void c(int i) {}
/*      */   
/*      */   public WorldBorder getWorldBorder() {
/* 1442 */     return this.worldBorder;
/*      */   }
/*      */   
/*      */   public void a(Packet<?> packet) {
/* 1446 */     throw new UnsupportedOperationException("Can't send packets to server unless you're on the client.");
/*      */   }
/*      */ 
/*      */   
/*      */   public DimensionManager getDimensionManager() {
/* 1451 */     return this.x;
/*      */   }
/*      */   
/*      */   public ResourceKey<World> getDimensionKey() {
/* 1455 */     return this.dimensionKey;
/*      */   }
/*      */ 
/*      */   
/*      */   public Random getRandom() {
/* 1460 */     return this.random;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(BlockPosition blockposition, Predicate<IBlockData> predicate) {
/* 1465 */     return predicate.test(getType(blockposition));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BlockPosition a(int i, int j, int k, int l) {
/* 1474 */     BlockPosition.MutableBlockPosition ret = new BlockPosition.MutableBlockPosition();
/* 1475 */     getRandomBlockPosition(i, j, k, l, ret);
/* 1476 */     return ret.immutableCopy();
/*      */   }
/*      */   
/*      */   public final BlockPosition.MutableBlockPosition getRandomBlockPosition(int i, int j, int k, int l, BlockPosition.MutableBlockPosition out) {
/* 1480 */     this.n = this.n * 3 + 1013904223;
/* 1481 */     int i1 = this.n >> 2;
/*      */     
/* 1483 */     out.setValues(i + (i1 & 0xF), j + (i1 >> 16 & l), k + (i1 >> 8 & 0xF));
/* 1484 */     return out;
/*      */   }
/*      */   
/*      */   public boolean isSavingDisabled() {
/* 1488 */     return false;
/*      */   }
/*      */   
/*      */   public GameProfilerFiller getMethodProfiler() {
/* 1492 */     return this.methodProfiler.get();
/*      */   }
/*      */   
/*      */   public Supplier<GameProfilerFiller> getMethodProfilerSupplier() {
/* 1496 */     return this.methodProfiler;
/*      */   }
/*      */ 
/*      */   
/*      */   public BiomeManager d() {
/* 1501 */     return this.biomeManager;
/*      */   }
/*      */   
/*      */   public final boolean isDebugWorld() {
/* 1505 */     return this.debugWorld;
/*      */   }
/*      */   
/*      */   public abstract void notify(BlockPosition paramBlockPosition, IBlockData paramIBlockData1, IBlockData paramIBlockData2, int paramInt);
/*      */   
/*      */   public abstract void playSound(@Nullable EntityHuman paramEntityHuman, double paramDouble1, double paramDouble2, double paramDouble3, SoundEffect paramSoundEffect, SoundCategory paramSoundCategory, float paramFloat1, float paramFloat2);
/*      */   
/*      */   public abstract void playSound(@Nullable EntityHuman paramEntityHuman, Entity paramEntity, SoundEffect paramSoundEffect, SoundCategory paramSoundCategory, float paramFloat1, float paramFloat2);
/*      */   
/*      */   @Nullable
/*      */   public abstract Entity getEntity(int paramInt);
/*      */   
/*      */   @Nullable
/*      */   public abstract WorldMap a(String paramString);
/*      */   
/*      */   public abstract void a(WorldMap paramWorldMap);
/*      */   
/*      */   public abstract int getWorldMapCount();
/*      */   
/*      */   public abstract void a(int paramInt1, BlockPosition paramBlockPosition, int paramInt2);
/*      */   
/*      */   public abstract Scoreboard getScoreboard();
/*      */   
/*      */   public abstract CraftingManager getCraftingManager();
/*      */   
/*      */   public abstract ITagRegistry p();
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\World.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */