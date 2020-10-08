/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class BlockLectern
/*     */   extends BlockTileEntity {
/*   8 */   public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
/*   9 */   public static final BlockStateBoolean b = BlockProperties.w;
/*  10 */   public static final BlockStateBoolean c = BlockProperties.o;
/*  11 */   public static final VoxelShape d = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
/*  12 */   public static final VoxelShape e = Block.a(4.0D, 2.0D, 4.0D, 12.0D, 14.0D, 12.0D);
/*  13 */   public static final VoxelShape f = VoxelShapes.a(d, e);
/*  14 */   public static final VoxelShape g = Block.a(0.0D, 15.0D, 0.0D, 16.0D, 15.0D, 16.0D);
/*  15 */   public static final VoxelShape h = VoxelShapes.a(f, g);
/*  16 */   public static final VoxelShape i = VoxelShapes.a(Block.a(1.0D, 10.0D, 0.0D, 5.333333D, 14.0D, 16.0D), new VoxelShape[] { Block.a(5.333333D, 12.0D, 0.0D, 9.666667D, 16.0D, 16.0D), Block.a(9.666667D, 14.0D, 0.0D, 14.0D, 18.0D, 16.0D), f });
/*  17 */   public static final VoxelShape j = VoxelShapes.a(Block.a(0.0D, 10.0D, 1.0D, 16.0D, 14.0D, 5.333333D), new VoxelShape[] { Block.a(0.0D, 12.0D, 5.333333D, 16.0D, 16.0D, 9.666667D), Block.a(0.0D, 14.0D, 9.666667D, 16.0D, 18.0D, 14.0D), f });
/*  18 */   public static final VoxelShape k = VoxelShapes.a(Block.a(15.0D, 10.0D, 0.0D, 10.666667D, 14.0D, 16.0D), new VoxelShape[] { Block.a(10.666667D, 12.0D, 0.0D, 6.333333D, 16.0D, 16.0D), Block.a(6.333333D, 14.0D, 0.0D, 2.0D, 18.0D, 16.0D), f });
/*  19 */   public static final VoxelShape o = VoxelShapes.a(Block.a(0.0D, 10.0D, 15.0D, 16.0D, 14.0D, 10.666667D), new VoxelShape[] { Block.a(0.0D, 12.0D, 10.666667D, 16.0D, 16.0D, 6.333333D), Block.a(0.0D, 14.0D, 6.333333D, 16.0D, 18.0D, 2.0D), f });
/*     */   
/*     */   protected BlockLectern(BlockBase.Info blockbase_info) {
/*  22 */     super(blockbase_info);
/*  23 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH).set(b, Boolean.valueOf(false)).set(c, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData iblockdata) {
/*  28 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape d(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  33 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c_(IBlockData iblockdata) {
/*  38 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  43 */     World world = blockactioncontext.getWorld();
/*  44 */     ItemStack itemstack = blockactioncontext.getItemStack();
/*  45 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*  46 */     EntityHuman entityhuman = blockactioncontext.getEntity();
/*  47 */     boolean flag = false;
/*     */     
/*  49 */     if (!world.isClientSide && entityhuman != null && nbttagcompound != null && entityhuman.isCreativeAndOp() && nbttagcompound.hasKey("BlockEntityTag")) {
/*  50 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("BlockEntityTag");
/*     */       
/*  52 */       if (nbttagcompound1.hasKey("Book")) {
/*  53 */         flag = true;
/*     */       }
/*     */     } 
/*     */     
/*  57 */     return getBlockData().set(a, blockactioncontext.f().opposite()).set(c, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  62 */     return h;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  67 */     switch ((EnumDirection)iblockdata.get(a)) {
/*     */       case NORTH:
/*  69 */         return j;
/*     */       case SOUTH:
/*  71 */         return o;
/*     */       case EAST:
/*  73 */         return k;
/*     */       case WEST:
/*  75 */         return i;
/*     */     } 
/*  77 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/*  83 */     return iblockdata.set(a, enumblockrotation.a((EnumDirection)iblockdata.get(a)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/*  88 */     return iblockdata.a(enumblockmirror.a((EnumDirection)iblockdata.get(a)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/*  93 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a, b, c });
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TileEntity createTile(IBlockAccess iblockaccess) {
/*  99 */     return new TileEntityLectern();
/*     */   }
/*     */   
/*     */   public static boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, ItemStack itemstack) {
/* 103 */     if (!((Boolean)iblockdata.get(c)).booleanValue()) {
/* 104 */       if (!world.isClientSide) {
/* 105 */         b(world, blockposition, iblockdata, itemstack);
/*     */       }
/*     */       
/* 108 */       return true;
/*     */     } 
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void b(World world, BlockPosition blockposition, IBlockData iblockdata, ItemStack itemstack) {
/* 115 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 117 */     if (tileentity instanceof TileEntityLectern) {
/* 118 */       TileEntityLectern tileentitylectern = (TileEntityLectern)tileentity;
/*     */       
/* 120 */       tileentitylectern.setBook(itemstack.cloneAndSubtract(1));
/* 121 */       setHasBook(world, blockposition, iblockdata, true);
/* 122 */       world.playSound((EntityHuman)null, blockposition, SoundEffects.ITEM_BOOK_PUT, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setHasBook(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 128 */     world.setTypeAndData(blockposition, iblockdata.set(b, Boolean.valueOf(false)).set(c, Boolean.valueOf(flag)), 3);
/* 129 */     b(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public static void a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 133 */     b(world, blockposition, iblockdata, true);
/* 134 */     world.getBlockTickList().a(blockposition, iblockdata.getBlock(), 2);
/* 135 */     world.triggerEffect(1043, blockposition, 0);
/*     */   }
/*     */   
/*     */   private static void b(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 139 */     world.setTypeAndData(blockposition, iblockdata.set(b, Boolean.valueOf(flag)), 3);
/* 140 */     b(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   private static void b(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 144 */     world.applyPhysics(blockposition.down(), iblockdata.getBlock());
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 149 */     b(worldserver, blockposition, iblockdata, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 154 */     if (!iblockdata.a(iblockdata1.getBlock())) {
/* 155 */       if (((Boolean)iblockdata.get(c)).booleanValue()) {
/* 156 */         d(iblockdata, world, blockposition);
/*     */       }
/*     */       
/* 159 */       if (((Boolean)iblockdata.get(b)).booleanValue()) {
/* 160 */         world.applyPhysics(blockposition.down(), this);
/*     */       }
/*     */       
/* 163 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void d(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 168 */     TileEntity tileentity = world.getTileEntity(blockposition, false);
/*     */     
/* 170 */     if (tileentity instanceof TileEntityLectern) {
/* 171 */       TileEntityLectern tileentitylectern = (TileEntityLectern)tileentity;
/* 172 */       EnumDirection enumdirection = (EnumDirection)iblockdata.get(a);
/* 173 */       ItemStack itemstack = tileentitylectern.getBook().cloneItemStack();
/* 174 */       if (itemstack.isEmpty())
/* 175 */         return;  float f = 0.25F * enumdirection.getAdjacentX();
/* 176 */       float f1 = 0.25F * enumdirection.getAdjacentZ();
/* 177 */       EntityItem entityitem = new EntityItem(world, blockposition.getX() + 0.5D + f, (blockposition.getY() + 1), blockposition.getZ() + 0.5D + f1, itemstack);
/*     */       
/* 179 */       entityitem.defaultPickupDelay();
/* 180 */       world.addEntity(entityitem);
/* 181 */       tileentitylectern.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 188 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 193 */     return ((Boolean)iblockdata.get(b)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 198 */     return (enumdirection == EnumDirection.UP && ((Boolean)iblockdata.get(b)).booleanValue()) ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 203 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 208 */     if (((Boolean)iblockdata.get(c)).booleanValue()) {
/* 209 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/* 211 */       if (tileentity instanceof TileEntityLectern) {
/* 212 */         return ((TileEntityLectern)tileentity).j();
/*     */       }
/*     */     } 
/*     */     
/* 216 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/* 221 */     if (((Boolean)iblockdata.get(c)).booleanValue()) {
/* 222 */       if (!world.isClientSide) {
/* 223 */         a(world, blockposition, entityhuman);
/*     */       }
/*     */       
/* 226 */       return EnumInteractionResult.a(world.isClientSide);
/*     */     } 
/* 228 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 230 */     return (!itemstack.isEmpty() && !itemstack.getItem().a(TagsItem.LECTERN_BOOKS)) ? EnumInteractionResult.CONSUME : EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ITileInventory getInventory(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 237 */     return !((Boolean)iblockdata.get(c)).booleanValue() ? null : super.getInventory(iblockdata, world, blockposition);
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, EntityHuman entityhuman) {
/* 241 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 243 */     if (tileentity instanceof TileEntityLectern) {
/* 244 */       entityhuman.openContainer((TileEntityLectern)tileentity);
/* 245 */       entityhuman.a(StatisticList.INTERACT_WITH_LECTERN);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 252 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockLectern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */