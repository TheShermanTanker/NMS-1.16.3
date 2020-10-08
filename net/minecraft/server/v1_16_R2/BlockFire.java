/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.event.block.TNTPrimeEvent;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockBurnEvent;
/*     */ import org.bukkit.event.block.BlockFadeEvent;
/*     */ 
/*     */ public class BlockFire
/*     */   extends BlockFireAbstract {
/*  21 */   public static final BlockStateInteger AGE = BlockProperties.aj;
/*  22 */   public static final BlockStateBoolean NORTH = BlockSprawling.a;
/*  23 */   public static final BlockStateBoolean EAST = BlockSprawling.b;
/*  24 */   public static final BlockStateBoolean SOUTH = BlockSprawling.c;
/*  25 */   public static final BlockStateBoolean WEST = BlockSprawling.d;
/*  26 */   public static final BlockStateBoolean UPPER = BlockSprawling.e; private static final Map<EnumDirection, BlockStateBoolean> h;
/*     */   
/*     */   static {
/*  29 */     h = (Map<EnumDirection, BlockStateBoolean>)BlockSprawling.g.entrySet().stream().filter(entry -> (entry.getKey() != EnumDirection.DOWN)).collect(SystemUtils.a());
/*  30 */   } private static final VoxelShape i = Block.a(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  31 */   private static final VoxelShape j = Block.a(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
/*  32 */   private static final VoxelShape k = Block.a(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  33 */   private static final VoxelShape o = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
/*  34 */   private static final VoxelShape p = Block.a(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
/*     */   private final Map<IBlockData, VoxelShape> q;
/*  36 */   private final Object2IntMap<Block> flameChances = (Object2IntMap<Block>)new Object2IntOpenHashMap();
/*  37 */   private final Object2IntMap<Block> burnChances = (Object2IntMap<Block>)new Object2IntOpenHashMap();
/*     */   
/*     */   public BlockFire(BlockBase.Info blockbase_info) {
/*  40 */     super(blockbase_info, 1.0F);
/*  41 */     j(((IBlockData)this.blockStateList.getBlockData()).set(AGE, Integer.valueOf(0)).set(NORTH, Boolean.valueOf(false)).set(EAST, Boolean.valueOf(false)).set(SOUTH, Boolean.valueOf(false)).set(WEST, Boolean.valueOf(false)).set(UPPER, Boolean.valueOf(false)));
/*  42 */     this.q = (Map<IBlockData, VoxelShape>)ImmutableMap.copyOf((Map)this.blockStateList.a().stream().filter(iblockdata -> (((Integer)iblockdata.get(AGE)).intValue() == 0))
/*     */         
/*  44 */         .collect(Collectors.toMap(Function.identity(), BlockFire::h)));
/*     */   }
/*     */   
/*     */   private static VoxelShape h(IBlockData iblockdata) {
/*  48 */     VoxelShape voxelshape = VoxelShapes.a();
/*     */     
/*  50 */     if (((Boolean)iblockdata.get(UPPER)).booleanValue()) {
/*  51 */       voxelshape = i;
/*     */     }
/*     */     
/*  54 */     if (((Boolean)iblockdata.get(NORTH)).booleanValue()) {
/*  55 */       voxelshape = VoxelShapes.a(voxelshape, o);
/*     */     }
/*     */     
/*  58 */     if (((Boolean)iblockdata.get(SOUTH)).booleanValue()) {
/*  59 */       voxelshape = VoxelShapes.a(voxelshape, p);
/*     */     }
/*     */     
/*  62 */     if (((Boolean)iblockdata.get(EAST)).booleanValue()) {
/*  63 */       voxelshape = VoxelShapes.a(voxelshape, k);
/*     */     }
/*     */     
/*  66 */     if (((Boolean)iblockdata.get(WEST)).booleanValue()) {
/*  67 */       voxelshape = VoxelShapes.a(voxelshape, j);
/*     */     }
/*     */     
/*  70 */     return voxelshape.isEmpty() ? a : voxelshape;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  76 */     if (!(generatoraccess instanceof WorldServer)) return canPlace(iblockdata, generatoraccess, blockposition) ? a(generatoraccess, blockposition, ((Integer)iblockdata.get(AGE)).intValue()) : Blocks.AIR.getBlockData(); 
/*  77 */     if (!canPlace(iblockdata, generatoraccess, blockposition)) {
/*     */       
/*  79 */       if (!(generatoraccess instanceof World)) {
/*  80 */         return Blocks.AIR.getBlockData();
/*     */       }
/*  82 */       CraftBlockState blockState = CraftBlockState.getBlockState(generatoraccess, blockposition);
/*  83 */       blockState.setData(Blocks.AIR.getBlockData());
/*     */       
/*  85 */       BlockFadeEvent event = new BlockFadeEvent((Block)blockState.getBlock(), (BlockState)blockState);
/*  86 */       ((World)generatoraccess).getServer().getPluginManager().callEvent((Event)event);
/*     */       
/*  88 */       if (!event.isCancelled()) {
/*  89 */         return blockState.getHandle();
/*     */       }
/*     */     } 
/*  92 */     return a(generatoraccess, blockposition, ((Integer)iblockdata.get(AGE)).intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  98 */     return this.q.get(iblockdata.set(AGE, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 103 */     return getPlacedState(blockactioncontext.getWorld(), blockactioncontext.getClickPosition());
/*     */   }
/*     */   
/*     */   protected IBlockData getPlacedState(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 107 */     BlockPosition blockposition1 = blockposition.down();
/* 108 */     IBlockData iblockdata = iblockaccess.getType(blockposition1);
/*     */     
/* 110 */     if (!e(iblockdata) && !iblockdata.d(iblockaccess, blockposition1, EnumDirection.UP)) {
/* 111 */       IBlockData iblockdata1 = getBlockData();
/* 112 */       EnumDirection[] aenumdirection = EnumDirection.values();
/* 113 */       int i = aenumdirection.length;
/*     */       
/* 115 */       for (int j = 0; j < i; j++) {
/* 116 */         EnumDirection enumdirection = aenumdirection[j];
/* 117 */         BlockStateBoolean blockstateboolean = h.get(enumdirection);
/*     */         
/* 119 */         if (blockstateboolean != null) {
/* 120 */           iblockdata1 = iblockdata1.set(blockstateboolean, Boolean.valueOf(e(iblockaccess.getTypeIfLoaded(blockposition.shift(enumdirection)))));
/*     */         }
/*     */       } 
/*     */       
/* 124 */       return iblockdata1;
/*     */     } 
/* 126 */     return getBlockData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 132 */     BlockPosition blockposition1 = blockposition.down();
/*     */     
/* 134 */     return (iworldreader.getType(blockposition1).d(iworldreader, blockposition1, EnumDirection.UP) || canBurn(iworldreader, blockposition));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 139 */     worldserver.getBlockTickList().a(blockposition, this, a(worldserver.random));
/* 140 */     if (worldserver.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
/* 141 */       if (!iblockdata.canPlace(worldserver, blockposition)) {
/* 142 */         fireExtinguished(worldserver, blockposition);
/*     */       }
/*     */       
/* 145 */       IBlockData iblockdata1 = worldserver.getType(blockposition.down());
/* 146 */       boolean flag = iblockdata1.a(worldserver.getDimensionManager().o());
/* 147 */       int i = ((Integer)iblockdata.get(AGE)).intValue();
/*     */       
/* 149 */       if (!flag && worldserver.isRaining() && a(worldserver, blockposition) && random.nextFloat() < 0.2F + i * 0.03F) {
/* 150 */         fireExtinguished(worldserver, blockposition);
/*     */       } else {
/* 152 */         int j = Math.min(15, i + random.nextInt(3) / 2);
/*     */         
/* 154 */         if (i != j) {
/* 155 */           iblockdata = iblockdata.set(AGE, Integer.valueOf(j));
/* 156 */           worldserver.setTypeAndData(blockposition, iblockdata, 4);
/*     */         } 
/*     */         
/* 159 */         if (!flag) {
/* 160 */           if (!canBurn(worldserver, blockposition)) {
/* 161 */             BlockPosition blockposition1 = blockposition.down();
/*     */             
/* 163 */             if (!worldserver.getType(blockposition1).d(worldserver, blockposition1, EnumDirection.UP) || i > 3) {
/* 164 */               fireExtinguished(worldserver, blockposition);
/*     */             }
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/* 170 */           if (i == 15 && random.nextInt(4) == 0 && !e(worldserver.getType(blockposition.down()))) {
/* 171 */             fireExtinguished(worldserver, blockposition);
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/* 176 */         boolean flag1 = worldserver.u(blockposition);
/* 177 */         int k = flag1 ? -50 : 0;
/*     */ 
/*     */         
/* 180 */         trySpread(worldserver, blockposition.east(), 300 + k, random, i, blockposition);
/* 181 */         trySpread(worldserver, blockposition.west(), 300 + k, random, i, blockposition);
/* 182 */         trySpread(worldserver, blockposition.down(), 250 + k, random, i, blockposition);
/* 183 */         trySpread(worldserver, blockposition.up(), 250 + k, random, i, blockposition);
/* 184 */         trySpread(worldserver, blockposition.north(), 300 + k, random, i, blockposition);
/* 185 */         trySpread(worldserver, blockposition.south(), 300 + k, random, i, blockposition);
/*     */         
/* 187 */         BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */         
/* 189 */         for (int l = -1; l <= 1; l++) {
/* 190 */           for (int i1 = -1; i1 <= 1; i1++) {
/* 191 */             for (int j1 = -1; j1 <= 4; j1++) {
/* 192 */               if (l != 0 || j1 != 0 || i1 != 0) {
/* 193 */                 int k1 = 100;
/*     */                 
/* 195 */                 if (j1 > 1) {
/* 196 */                   k1 += (j1 - 1) * 100;
/*     */                 }
/*     */                 
/* 199 */                 blockposition_mutableblockposition.a(blockposition, l, j1, i1);
/* 200 */                 if (worldserver.isLoaded(blockposition_mutableblockposition)) {
/* 201 */                   int l1 = a(worldserver, blockposition_mutableblockposition);
/*     */                   
/* 203 */                   if (l1 > 0) {
/* 204 */                     int i2 = (l1 + 40 + worldserver.getDifficulty().a() * 7) / (i + 30);
/*     */                     
/* 206 */                     if (flag1) {
/* 207 */                       i2 /= 2;
/*     */                     }
/*     */                     
/* 210 */                     if (i2 > 0 && random.nextInt(k1) <= i2 && (!worldserver.isRaining() || !a(worldserver, blockposition_mutableblockposition))) {
/* 211 */                       int j2 = Math.min(15, i + random.nextInt(5) / 4);
/*     */ 
/*     */                       
/* 214 */                       if (worldserver.getType(blockposition_mutableblockposition).getBlock() != Blocks.FIRE && 
/* 215 */                         !CraftEventFactory.callBlockIgniteEvent(worldserver, blockposition_mutableblockposition, blockposition).isCancelled())
/*     */                       {
/*     */ 
/*     */                         
/* 219 */                         CraftEventFactory.handleBlockSpreadEvent(worldserver, blockposition, blockposition_mutableblockposition, a(worldserver, blockposition_mutableblockposition, j2), 3);
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(World world, BlockPosition blockposition) {
/* 234 */     return (world.isRainingAt(blockposition) || world.isRainingAt(blockposition.west()) || world.isRainingAt(blockposition.east()) || world.isRainingAt(blockposition.north()) || world.isRainingAt(blockposition.south()));
/*     */   }
/*     */   
/*     */   private int getBurnChance(IBlockData iblockdata) {
/* 238 */     return (iblockdata.b(BlockProperties.C) && ((Boolean)iblockdata.get(BlockProperties.C)).booleanValue()) ? 0 : this.burnChances.getInt(iblockdata.getBlock());
/*     */   }
/*     */   
/*     */   private int getFlameChance(IBlockData iblockdata) {
/* 242 */     return (iblockdata.b(BlockProperties.C) && ((Boolean)iblockdata.get(BlockProperties.C)).booleanValue()) ? 0 : this.flameChances.getInt(iblockdata.getBlock());
/*     */   }
/*     */ 
/*     */   
/*     */   private void trySpread(World world, BlockPosition blockposition, int i, Random random, int j, BlockPosition sourceposition) {
/* 247 */     IBlockData iblockdata = world.getTypeIfLoaded(blockposition);
/* 248 */     if (iblockdata == null) {
/*     */       return;
/*     */     }
/* 251 */     int k = getBurnChance(iblockdata);
/*     */ 
/*     */     
/* 254 */     if (random.nextInt(i) < k) {
/*     */ 
/*     */ 
/*     */       
/* 258 */       Block theBlock = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 259 */       Block sourceBlock = world.getWorld().getBlockAt(sourceposition.getX(), sourceposition.getY(), sourceposition.getZ());
/*     */       
/* 261 */       BlockBurnEvent event = new BlockBurnEvent(theBlock, sourceBlock);
/* 262 */       world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 264 */       if (event.isCancelled()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 269 */       if (random.nextInt(j + 10) < 5 && !world.isRainingAt(blockposition))
/* 270 */       { int l = Math.min(j + random.nextInt(5) / 4, 15);
/*     */         
/* 272 */         world.setTypeAndData(blockposition, a(world, blockposition, l), 3); }
/*     */       
/* 274 */       else if (iblockdata.getBlock() != Blocks.TNT) { world.a(blockposition, false); }
/*     */ 
/*     */       
/* 277 */       Block block = iblockdata.getBlock();
/*     */       
/* 279 */       if (block instanceof BlockTNT) {
/* 280 */         BlockTNT blocktnt = (BlockTNT)block;
/*     */ 
/*     */         
/* 283 */         Block tntBlock = MCUtil.toBukkitBlock(world, blockposition);
/* 284 */         if (!(new TNTPrimeEvent(tntBlock, TNTPrimeEvent.PrimeReason.FIRE, null)).callEvent()) {
/*     */           return;
/*     */         }
/* 287 */         world.setAir(blockposition, false);
/*     */         
/* 289 */         BlockTNT.a(world, blockposition);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private IBlockData a(GeneratorAccess generatoraccess, BlockPosition blockposition, int i) {
/* 296 */     IBlockData iblockdata = a(generatoraccess, blockposition);
/*     */     
/* 298 */     return iblockdata.a(Blocks.FIRE) ? iblockdata.set(AGE, Integer.valueOf(i)) : iblockdata;
/*     */   }
/*     */   
/*     */   private boolean canBurn(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 302 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 303 */     int i = aenumdirection.length;
/*     */     
/* 305 */     for (int j = 0; j < i; j++) {
/* 306 */       EnumDirection enumdirection = aenumdirection[j];
/*     */       
/* 308 */       if (e(iblockaccess.getTypeIfLoaded(blockposition.shift(enumdirection)))) {
/* 309 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 313 */     return false;
/*     */   }
/*     */   
/*     */   private int a(IWorldReader iworldreader, BlockPosition blockposition) {
/* 317 */     if (!iworldreader.isEmpty(blockposition)) {
/* 318 */       return 0;
/*     */     }
/* 320 */     int i = 0;
/* 321 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 322 */     int j = aenumdirection.length;
/*     */     
/* 324 */     for (int k = 0; k < j; k++) {
/* 325 */       EnumDirection enumdirection = aenumdirection[k];
/*     */       
/* 327 */       IBlockData iblockdata = iworldreader.getTypeIfLoaded(blockposition.shift(enumdirection));
/* 328 */       if (iblockdata != null)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 333 */         i = Math.max(getFlameChance(iblockdata), i);
/*     */       }
/*     */     } 
/* 336 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean e(IBlockData iblockdata) {
/* 342 */     return (iblockdata != null && getFlameChance(iblockdata) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag, ItemActionContext itemActionContext) {
/* 348 */     super.onPlace(iblockdata, world, blockposition, iblockdata1, flag, itemActionContext);
/*     */     
/* 350 */     world.getBlockTickList().a(blockposition, this, a(world.random));
/*     */   }
/*     */   
/*     */   private static int a(Random random) {
/* 354 */     return 30 + random.nextInt(10);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 359 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { AGE, NORTH, EAST, SOUTH, WEST, UPPER });
/*     */   }
/*     */   
/*     */   private void a(Block block, int i, int j) {
/* 363 */     this.flameChances.put(block, i);
/* 364 */     this.burnChances.put(block, j);
/*     */   }
/*     */   
/*     */   public static void c() {
/* 368 */     BlockFire blockfire = (BlockFire)Blocks.FIRE;
/*     */     
/* 370 */     blockfire.a(Blocks.OAK_PLANKS, 5, 20);
/* 371 */     blockfire.a(Blocks.SPRUCE_PLANKS, 5, 20);
/* 372 */     blockfire.a(Blocks.BIRCH_PLANKS, 5, 20);
/* 373 */     blockfire.a(Blocks.JUNGLE_PLANKS, 5, 20);
/* 374 */     blockfire.a(Blocks.ACACIA_PLANKS, 5, 20);
/* 375 */     blockfire.a(Blocks.DARK_OAK_PLANKS, 5, 20);
/* 376 */     blockfire.a(Blocks.OAK_SLAB, 5, 20);
/* 377 */     blockfire.a(Blocks.SPRUCE_SLAB, 5, 20);
/* 378 */     blockfire.a(Blocks.BIRCH_SLAB, 5, 20);
/* 379 */     blockfire.a(Blocks.JUNGLE_SLAB, 5, 20);
/* 380 */     blockfire.a(Blocks.ACACIA_SLAB, 5, 20);
/* 381 */     blockfire.a(Blocks.DARK_OAK_SLAB, 5, 20);
/* 382 */     blockfire.a(Blocks.OAK_FENCE_GATE, 5, 20);
/* 383 */     blockfire.a(Blocks.SPRUCE_FENCE_GATE, 5, 20);
/* 384 */     blockfire.a(Blocks.BIRCH_FENCE_GATE, 5, 20);
/* 385 */     blockfire.a(Blocks.JUNGLE_FENCE_GATE, 5, 20);
/* 386 */     blockfire.a(Blocks.DARK_OAK_FENCE_GATE, 5, 20);
/* 387 */     blockfire.a(Blocks.ACACIA_FENCE_GATE, 5, 20);
/* 388 */     blockfire.a(Blocks.OAK_FENCE, 5, 20);
/* 389 */     blockfire.a(Blocks.SPRUCE_FENCE, 5, 20);
/* 390 */     blockfire.a(Blocks.BIRCH_FENCE, 5, 20);
/* 391 */     blockfire.a(Blocks.JUNGLE_FENCE, 5, 20);
/* 392 */     blockfire.a(Blocks.DARK_OAK_FENCE, 5, 20);
/* 393 */     blockfire.a(Blocks.ACACIA_FENCE, 5, 20);
/* 394 */     blockfire.a(Blocks.OAK_STAIRS, 5, 20);
/* 395 */     blockfire.a(Blocks.BIRCH_STAIRS, 5, 20);
/* 396 */     blockfire.a(Blocks.SPRUCE_STAIRS, 5, 20);
/* 397 */     blockfire.a(Blocks.JUNGLE_STAIRS, 5, 20);
/* 398 */     blockfire.a(Blocks.ACACIA_STAIRS, 5, 20);
/* 399 */     blockfire.a(Blocks.DARK_OAK_STAIRS, 5, 20);
/* 400 */     blockfire.a(Blocks.OAK_LOG, 5, 5);
/* 401 */     blockfire.a(Blocks.SPRUCE_LOG, 5, 5);
/* 402 */     blockfire.a(Blocks.BIRCH_LOG, 5, 5);
/* 403 */     blockfire.a(Blocks.JUNGLE_LOG, 5, 5);
/* 404 */     blockfire.a(Blocks.ACACIA_LOG, 5, 5);
/* 405 */     blockfire.a(Blocks.DARK_OAK_LOG, 5, 5);
/* 406 */     blockfire.a(Blocks.STRIPPED_OAK_LOG, 5, 5);
/* 407 */     blockfire.a(Blocks.STRIPPED_SPRUCE_LOG, 5, 5);
/* 408 */     blockfire.a(Blocks.STRIPPED_BIRCH_LOG, 5, 5);
/* 409 */     blockfire.a(Blocks.STRIPPED_JUNGLE_LOG, 5, 5);
/* 410 */     blockfire.a(Blocks.STRIPPED_ACACIA_LOG, 5, 5);
/* 411 */     blockfire.a(Blocks.STRIPPED_DARK_OAK_LOG, 5, 5);
/* 412 */     blockfire.a(Blocks.STRIPPED_OAK_WOOD, 5, 5);
/* 413 */     blockfire.a(Blocks.STRIPPED_SPRUCE_WOOD, 5, 5);
/* 414 */     blockfire.a(Blocks.STRIPPED_BIRCH_WOOD, 5, 5);
/* 415 */     blockfire.a(Blocks.STRIPPED_JUNGLE_WOOD, 5, 5);
/* 416 */     blockfire.a(Blocks.STRIPPED_ACACIA_WOOD, 5, 5);
/* 417 */     blockfire.a(Blocks.STRIPPED_DARK_OAK_WOOD, 5, 5);
/* 418 */     blockfire.a(Blocks.OAK_WOOD, 5, 5);
/* 419 */     blockfire.a(Blocks.SPRUCE_WOOD, 5, 5);
/* 420 */     blockfire.a(Blocks.BIRCH_WOOD, 5, 5);
/* 421 */     blockfire.a(Blocks.JUNGLE_WOOD, 5, 5);
/* 422 */     blockfire.a(Blocks.ACACIA_WOOD, 5, 5);
/* 423 */     blockfire.a(Blocks.DARK_OAK_WOOD, 5, 5);
/* 424 */     blockfire.a(Blocks.OAK_LEAVES, 30, 60);
/* 425 */     blockfire.a(Blocks.SPRUCE_LEAVES, 30, 60);
/* 426 */     blockfire.a(Blocks.BIRCH_LEAVES, 30, 60);
/* 427 */     blockfire.a(Blocks.JUNGLE_LEAVES, 30, 60);
/* 428 */     blockfire.a(Blocks.ACACIA_LEAVES, 30, 60);
/* 429 */     blockfire.a(Blocks.DARK_OAK_LEAVES, 30, 60);
/* 430 */     blockfire.a(Blocks.BOOKSHELF, 30, 20);
/* 431 */     blockfire.a(Blocks.TNT, 15, 100);
/* 432 */     blockfire.a(Blocks.GRASS, 60, 100);
/* 433 */     blockfire.a(Blocks.FERN, 60, 100);
/* 434 */     blockfire.a(Blocks.DEAD_BUSH, 60, 100);
/* 435 */     blockfire.a(Blocks.SUNFLOWER, 60, 100);
/* 436 */     blockfire.a(Blocks.LILAC, 60, 100);
/* 437 */     blockfire.a(Blocks.ROSE_BUSH, 60, 100);
/* 438 */     blockfire.a(Blocks.PEONY, 60, 100);
/* 439 */     blockfire.a(Blocks.TALL_GRASS, 60, 100);
/* 440 */     blockfire.a(Blocks.LARGE_FERN, 60, 100);
/* 441 */     blockfire.a(Blocks.DANDELION, 60, 100);
/* 442 */     blockfire.a(Blocks.POPPY, 60, 100);
/* 443 */     blockfire.a(Blocks.BLUE_ORCHID, 60, 100);
/* 444 */     blockfire.a(Blocks.ALLIUM, 60, 100);
/* 445 */     blockfire.a(Blocks.AZURE_BLUET, 60, 100);
/* 446 */     blockfire.a(Blocks.RED_TULIP, 60, 100);
/* 447 */     blockfire.a(Blocks.ORANGE_TULIP, 60, 100);
/* 448 */     blockfire.a(Blocks.WHITE_TULIP, 60, 100);
/* 449 */     blockfire.a(Blocks.PINK_TULIP, 60, 100);
/* 450 */     blockfire.a(Blocks.OXEYE_DAISY, 60, 100);
/* 451 */     blockfire.a(Blocks.CORNFLOWER, 60, 100);
/* 452 */     blockfire.a(Blocks.LILY_OF_THE_VALLEY, 60, 100);
/* 453 */     blockfire.a(Blocks.WITHER_ROSE, 60, 100);
/* 454 */     blockfire.a(Blocks.WHITE_WOOL, 30, 60);
/* 455 */     blockfire.a(Blocks.ORANGE_WOOL, 30, 60);
/* 456 */     blockfire.a(Blocks.MAGENTA_WOOL, 30, 60);
/* 457 */     blockfire.a(Blocks.LIGHT_BLUE_WOOL, 30, 60);
/* 458 */     blockfire.a(Blocks.YELLOW_WOOL, 30, 60);
/* 459 */     blockfire.a(Blocks.LIME_WOOL, 30, 60);
/* 460 */     blockfire.a(Blocks.PINK_WOOL, 30, 60);
/* 461 */     blockfire.a(Blocks.GRAY_WOOL, 30, 60);
/* 462 */     blockfire.a(Blocks.LIGHT_GRAY_WOOL, 30, 60);
/* 463 */     blockfire.a(Blocks.CYAN_WOOL, 30, 60);
/* 464 */     blockfire.a(Blocks.PURPLE_WOOL, 30, 60);
/* 465 */     blockfire.a(Blocks.BLUE_WOOL, 30, 60);
/* 466 */     blockfire.a(Blocks.BROWN_WOOL, 30, 60);
/* 467 */     blockfire.a(Blocks.GREEN_WOOL, 30, 60);
/* 468 */     blockfire.a(Blocks.RED_WOOL, 30, 60);
/* 469 */     blockfire.a(Blocks.BLACK_WOOL, 30, 60);
/* 470 */     blockfire.a(Blocks.VINE, 15, 100);
/* 471 */     blockfire.a(Blocks.COAL_BLOCK, 5, 5);
/* 472 */     blockfire.a(Blocks.HAY_BLOCK, 60, 20);
/* 473 */     blockfire.a(Blocks.TARGET, 15, 20);
/* 474 */     blockfire.a(Blocks.WHITE_CARPET, 60, 20);
/* 475 */     blockfire.a(Blocks.ORANGE_CARPET, 60, 20);
/* 476 */     blockfire.a(Blocks.MAGENTA_CARPET, 60, 20);
/* 477 */     blockfire.a(Blocks.LIGHT_BLUE_CARPET, 60, 20);
/* 478 */     blockfire.a(Blocks.YELLOW_CARPET, 60, 20);
/* 479 */     blockfire.a(Blocks.LIME_CARPET, 60, 20);
/* 480 */     blockfire.a(Blocks.PINK_CARPET, 60, 20);
/* 481 */     blockfire.a(Blocks.GRAY_CARPET, 60, 20);
/* 482 */     blockfire.a(Blocks.LIGHT_GRAY_CARPET, 60, 20);
/* 483 */     blockfire.a(Blocks.CYAN_CARPET, 60, 20);
/* 484 */     blockfire.a(Blocks.PURPLE_CARPET, 60, 20);
/* 485 */     blockfire.a(Blocks.BLUE_CARPET, 60, 20);
/* 486 */     blockfire.a(Blocks.BROWN_CARPET, 60, 20);
/* 487 */     blockfire.a(Blocks.GREEN_CARPET, 60, 20);
/* 488 */     blockfire.a(Blocks.RED_CARPET, 60, 20);
/* 489 */     blockfire.a(Blocks.BLACK_CARPET, 60, 20);
/* 490 */     blockfire.a(Blocks.DRIED_KELP_BLOCK, 30, 60);
/* 491 */     blockfire.a(Blocks.BAMBOO, 60, 60);
/* 492 */     blockfire.a(Blocks.SCAFFOLDING, 60, 60);
/* 493 */     blockfire.a(Blocks.LECTERN, 30, 20);
/* 494 */     blockfire.a(Blocks.COMPOSTER, 5, 20);
/* 495 */     blockfire.a(Blocks.SWEET_BERRY_BUSH, 60, 100);
/* 496 */     blockfire.a(Blocks.BEEHIVE, 5, 20);
/* 497 */     blockfire.a(Blocks.BEE_NEST, 30, 20);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */