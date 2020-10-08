/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class BlockChest
/*     */   extends BlockChestAbstract<TileEntityChest> implements IBlockWaterlogged {
/*  12 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING; public static final BlockStateEnum<BlockPropertyChestType> c;
/*  13 */   public static final BlockStateEnum<BlockPropertyChestType> CHEST_TYPE_PROPERTY = c = BlockProperties.aF;
/*  14 */   public static final BlockStateBoolean d = BlockProperties.C; public static final BlockStateBoolean waterlogged() { return d; }
/*  15 */    protected static final VoxelShape e = Block.a(1.0D, 0.0D, 0.0D, 15.0D, 14.0D, 15.0D);
/*  16 */   protected static final VoxelShape f = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 16.0D);
/*  17 */   protected static final VoxelShape g = Block.a(0.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
/*  18 */   protected static final VoxelShape h = Block.a(1.0D, 0.0D, 1.0D, 16.0D, 14.0D, 15.0D);
/*  19 */   protected static final VoxelShape i = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
/*  20 */   private static final DoubleBlockFinder.Combiner<TileEntityChest, Optional<IInventory>> j = new DoubleBlockFinder.Combiner<TileEntityChest, Optional<IInventory>>() {
/*     */       public Optional<IInventory> a(TileEntityChest tileentitychest, TileEntityChest tileentitychest1) {
/*  22 */         return Optional.of(new InventoryLargeChest(tileentitychest, tileentitychest1));
/*     */       }
/*     */       
/*     */       public Optional<IInventory> a(TileEntityChest tileentitychest) {
/*  26 */         return Optional.of(tileentitychest);
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<IInventory> b() {
/*  31 */         return Optional.empty();
/*     */       }
/*     */     };
/*  34 */   private static final DoubleBlockFinder.Combiner<TileEntityChest, Optional<ITileInventory>> k = new DoubleBlockFinder.Combiner<TileEntityChest, Optional<ITileInventory>>() {
/*     */       public Optional<ITileInventory> a(TileEntityChest tileentitychest, TileEntityChest tileentitychest1) {
/*  36 */         InventoryLargeChest inventorylargechest = new InventoryLargeChest(tileentitychest, tileentitychest1);
/*     */         
/*  38 */         return Optional.of(new BlockChest.DoubleInventory(tileentitychest, tileentitychest1, inventorylargechest));
/*     */       }
/*     */       
/*     */       public Optional<ITileInventory> a(TileEntityChest tileentitychest) {
/*  42 */         return Optional.of(tileentitychest);
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<ITileInventory> b() {
/*  47 */         return Optional.empty();
/*     */       }
/*     */     };
/*     */   
/*     */   public static class DoubleInventory
/*     */     implements ITileInventory
/*     */   {
/*     */     private final TileEntityChest tileentitychest;
/*     */     private final TileEntityChest tileentitychest1;
/*     */     public final InventoryLargeChest inventorylargechest;
/*     */     
/*     */     public DoubleInventory(TileEntityChest tileentitychest, TileEntityChest tileentitychest1, InventoryLargeChest inventorylargechest) {
/*  59 */       this.tileentitychest = tileentitychest;
/*  60 */       this.tileentitychest1 = tileentitychest1;
/*  61 */       this.inventorylargechest = inventorylargechest;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Container createMenu(int i, PlayerInventory playerinventory, EntityHuman entityhuman) {
/*  67 */       if (this.tileentitychest.e(entityhuman) && this.tileentitychest1.e(entityhuman)) {
/*  68 */         this.tileentitychest.d(playerinventory.player);
/*  69 */         this.tileentitychest1.d(playerinventory.player);
/*  70 */         return ContainerChest.b(i, playerinventory, this.inventorylargechest);
/*     */       } 
/*  72 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public IChatBaseComponent getScoreboardDisplayName() {
/*  78 */       return this.tileentitychest.hasCustomName() ? this.tileentitychest.getScoreboardDisplayName() : (this.tileentitychest1.hasCustomName() ? this.tileentitychest1.getScoreboardDisplayName() : new ChatMessage("container.chestDouble"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockChest(BlockBase.Info blockbase_info, Supplier<TileEntityTypes<? extends TileEntityChest>> supplier) {
/*  84 */     super(blockbase_info, supplier);
/*  85 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(c, BlockPropertyChestType.SINGLE).set(d, Boolean.valueOf(false)));
/*     */   }
/*     */   
/*     */   public static DoubleBlockFinder.BlockType g(IBlockData iblockdata) {
/*  89 */     BlockPropertyChestType blockpropertychesttype = (BlockPropertyChestType)iblockdata.get(c);
/*     */     
/*  91 */     return (blockpropertychesttype == BlockPropertyChestType.SINGLE) ? DoubleBlockFinder.BlockType.SINGLE : ((blockpropertychesttype == BlockPropertyChestType.RIGHT) ? DoubleBlockFinder.BlockType.FIRST : DoubleBlockFinder.BlockType.SECOND);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData iblockdata) {
/*  96 */     return EnumRenderType.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 101 */     if (((Boolean)iblockdata.get(d)).booleanValue()) {
/* 102 */       generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a(generatoraccess));
/*     */     }
/*     */     
/* 105 */     if (iblockdata1.a(this) && enumdirection.n().d()) {
/* 106 */       BlockPropertyChestType blockpropertychesttype = (BlockPropertyChestType)iblockdata1.get(c);
/*     */       
/* 108 */       if (iblockdata.get(c) == BlockPropertyChestType.SINGLE && blockpropertychesttype != BlockPropertyChestType.SINGLE && iblockdata.get(FACING) == iblockdata1.get(FACING) && h(iblockdata1) == enumdirection.opposite()) {
/* 109 */         return iblockdata.set(c, blockpropertychesttype.b());
/*     */       }
/* 111 */     } else if (h(iblockdata) == enumdirection) {
/* 112 */       return iblockdata.set(c, BlockPropertyChestType.SINGLE);
/*     */     } 
/*     */     
/* 115 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 120 */     if (iblockdata.get(c) == BlockPropertyChestType.SINGLE) {
/* 121 */       return i;
/*     */     }
/* 123 */     switch (h(iblockdata))
/*     */     
/*     */     { default:
/* 126 */         return e;
/*     */       case SOUTH:
/* 128 */         return f;
/*     */       case WEST:
/* 130 */         return g;
/*     */       case EAST:
/* 132 */         break; }  return h;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumDirection h(IBlockData iblockdata) {
/* 138 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/*     */     
/* 140 */     return (iblockdata.get(c) == BlockPropertyChestType.LEFT) ? enumdirection.g() : enumdirection.h();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 145 */     BlockPropertyChestType blockpropertychesttype = BlockPropertyChestType.SINGLE;
/* 146 */     EnumDirection enumdirection = blockactioncontext.f().opposite();
/* 147 */     Fluid fluid = blockactioncontext.getWorld().getFluid(blockactioncontext.getClickPosition());
/* 148 */     boolean flag = blockactioncontext.isSneaking();
/* 149 */     EnumDirection enumdirection1 = blockactioncontext.getClickedFace();
/*     */     
/* 151 */     if (enumdirection1.n().d() && flag) {
/* 152 */       EnumDirection enumdirection2 = a(blockactioncontext, enumdirection1.opposite());
/*     */       
/* 154 */       if (enumdirection2 != null && enumdirection2.n() != enumdirection1.n()) {
/* 155 */         enumdirection = enumdirection2;
/* 156 */         blockpropertychesttype = (enumdirection2.h() == enumdirection1.opposite()) ? BlockPropertyChestType.RIGHT : BlockPropertyChestType.LEFT;
/*     */       } 
/*     */     } 
/*     */     
/* 160 */     if (blockpropertychesttype == BlockPropertyChestType.SINGLE && !flag) {
/* 161 */       if (enumdirection == a(blockactioncontext, enumdirection.g())) {
/* 162 */         blockpropertychesttype = BlockPropertyChestType.LEFT;
/* 163 */       } else if (enumdirection == a(blockactioncontext, enumdirection.h())) {
/* 164 */         blockpropertychesttype = BlockPropertyChestType.RIGHT;
/*     */       } 
/*     */     }
/*     */     
/* 168 */     return getBlockData().set(FACING, enumdirection).set(c, blockpropertychesttype).set(d, Boolean.valueOf((fluid.getType() == FluidTypes.WATER)));
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData iblockdata) {
/* 173 */     return ((Boolean)iblockdata.get(d)).booleanValue() ? FluidTypes.WATER.a(false) : super.d(iblockdata);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private EnumDirection a(BlockActionContext blockactioncontext, EnumDirection enumdirection) {
/* 178 */     IBlockData iblockdata = blockactioncontext.getWorld().getType(blockactioncontext.getClickPosition().shift(enumdirection));
/*     */     
/* 180 */     return (iblockdata.a(this) && iblockdata.get(c) == BlockPropertyChestType.SINGLE) ? (EnumDirection)iblockdata.get(FACING) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/* 185 */     if (itemstack.hasName()) {
/* 186 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/* 188 */       if (tileentity instanceof TileEntityChest) {
/* 189 */         ((TileEntityChest)tileentity).setCustomName(itemstack.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 197 */     if (!iblockdata.a(iblockdata1.getBlock())) {
/* 198 */       TileEntity tileentity = world.getTileEntity(blockposition, false);
/*     */       
/* 200 */       if (tileentity instanceof IInventory) {
/* 201 */         InventoryUtils.dropInventory(world, blockposition, (IInventory)tileentity);
/* 202 */         world.updateAdjacentComparators(blockposition, this);
/*     */       } 
/*     */       
/* 205 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/* 211 */     if (world.isClientSide) {
/* 212 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/* 214 */     ITileInventory itileinventory = getInventory(iblockdata, world, blockposition);
/*     */     
/* 216 */     if (itileinventory != null) {
/* 217 */       entityhuman.openContainer(itileinventory);
/* 218 */       entityhuman.b(c());
/* 219 */       PiglinAI.a(entityhuman, true);
/*     */     } 
/*     */     
/* 222 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Statistic<MinecraftKey> c() {
/* 227 */     return StatisticList.CUSTOM.b(StatisticList.OPEN_CHEST);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static IInventory getInventory(BlockChest blockchest, IBlockData iblockdata, World world, BlockPosition blockposition, boolean flag) {
/* 232 */     return ((Optional<IInventory>)blockchest.a(iblockdata, world, blockposition, flag).<Optional<IInventory>>apply(j)).orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public DoubleBlockFinder.Result<? extends TileEntityChest> a(IBlockData iblockdata, World world, BlockPosition blockposition, boolean flag) {
/*     */     BiPredicate<GeneratorAccess, BlockPosition> bipredicate;
/* 238 */     if (flag) {
/* 239 */       bipredicate = ((generatoraccess, blockposition1) -> false);
/*     */     }
/*     */     else {
/*     */       
/* 243 */       bipredicate = BlockChest::a;
/*     */     } 
/*     */     
/* 246 */     return DoubleBlockFinder.a(this.a.get(), BlockChest::g, BlockChest::h, FACING, iblockdata, world, blockposition, bipredicate);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ITileInventory getInventory(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 252 */     return ((Optional<ITileInventory>)a(iblockdata, world, blockposition, false).<Optional<ITileInventory>>apply(k)).orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess iblockaccess) {
/* 257 */     return new TileEntityChest();
/*     */   }
/*     */   
/*     */   public static boolean a(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 261 */     return (a(generatoraccess, blockposition) || b(generatoraccess, blockposition));
/*     */   }
/*     */   
/*     */   private static boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 265 */     BlockPosition blockposition1 = blockposition.up();
/*     */     
/* 267 */     return iblockaccess.getType(blockposition1).isOccluding(iblockaccess, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean b(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 272 */     if (((World)generatoraccess).paperConfig.disableChestCatDetection) {
/* 273 */       return false;
/*     */     }
/*     */     
/* 276 */     List<EntityCat> list = generatoraccess.a(EntityCat.class, new AxisAlignedBB(blockposition.getX(), (blockposition.getY() + 1), blockposition.getZ(), (blockposition.getX() + 1), (blockposition.getY() + 2), (blockposition.getZ() + 1)));
/*     */     
/* 278 */     if (!list.isEmpty()) {
/* 279 */       Iterator<EntityCat> iterator = list.iterator();
/*     */       
/* 281 */       while (iterator.hasNext()) {
/* 282 */         EntityCat entitycat = iterator.next();
/*     */         
/* 284 */         if (entitycat.isSitting()) {
/* 285 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 290 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 295 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 300 */     return Container.b(getInventory(this, iblockdata, world, blockposition, false));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 305 */     return iblockdata.set(FACING, enumblockrotation.a((EnumDirection)iblockdata.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 310 */     return iblockdata.a(enumblockmirror.a((EnumDirection)iblockdata.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 315 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACING, c, d });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 320 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */