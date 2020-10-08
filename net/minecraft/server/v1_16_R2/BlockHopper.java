/*     */ package net.minecraft.server.v1_16_R2;
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
/*     */ public class BlockHopper
/*     */   extends BlockTileEntity
/*     */ {
/*  33 */   public static final BlockStateDirection FACING = BlockProperties.N;
/*  34 */   public static final BlockStateBoolean ENABLED = BlockProperties.f;
/*     */   
/*  36 */   private static final VoxelShape c = Block.a(0.0D, 10.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  37 */   private static final VoxelShape d = Block.a(4.0D, 4.0D, 4.0D, 12.0D, 10.0D, 12.0D);
/*     */   
/*  39 */   private static final VoxelShape e = VoxelShapes.a(d, c);
/*  40 */   private static final VoxelShape f = VoxelShapes.a(e, IHopper.a, OperatorBoolean.ONLY_FIRST);
/*     */   
/*  42 */   private static final VoxelShape g = VoxelShapes.a(f, Block.a(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D));
/*  43 */   private static final VoxelShape h = VoxelShapes.a(f, Block.a(12.0D, 4.0D, 6.0D, 16.0D, 8.0D, 10.0D));
/*  44 */   private static final VoxelShape i = VoxelShapes.a(f, Block.a(6.0D, 4.0D, 0.0D, 10.0D, 8.0D, 4.0D));
/*  45 */   private static final VoxelShape j = VoxelShapes.a(f, Block.a(6.0D, 4.0D, 12.0D, 10.0D, 8.0D, 16.0D));
/*  46 */   private static final VoxelShape k = VoxelShapes.a(f, Block.a(0.0D, 4.0D, 6.0D, 4.0D, 8.0D, 10.0D));
/*     */   
/*  48 */   private static final VoxelShape o = IHopper.a;
/*  49 */   private static final VoxelShape p = VoxelShapes.a(IHopper.a, Block.a(12.0D, 8.0D, 6.0D, 16.0D, 10.0D, 10.0D));
/*  50 */   private static final VoxelShape q = VoxelShapes.a(IHopper.a, Block.a(6.0D, 8.0D, 0.0D, 10.0D, 10.0D, 4.0D));
/*  51 */   private static final VoxelShape r = VoxelShapes.a(IHopper.a, Block.a(6.0D, 8.0D, 12.0D, 10.0D, 10.0D, 16.0D));
/*  52 */   private static final VoxelShape s = VoxelShapes.a(IHopper.a, Block.a(0.0D, 8.0D, 6.0D, 4.0D, 10.0D, 10.0D));
/*     */   
/*     */   public BlockHopper(BlockBase.Info var0) {
/*  55 */     super(var0);
/*  56 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.DOWN).set(ENABLED, Boolean.valueOf(true)));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  61 */     switch (null.a[((EnumDirection)var0.get(FACING)).ordinal()]) {
/*     */       case 1:
/*  63 */         return g;
/*     */       case 2:
/*  65 */         return i;
/*     */       case 3:
/*  67 */         return j;
/*     */       case 4:
/*  69 */         return k;
/*     */       case 5:
/*  71 */         return h;
/*     */     } 
/*  73 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape a_(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/*  78 */     switch (null.a[((EnumDirection)var0.get(FACING)).ordinal()]) {
/*     */       case 1:
/*  80 */         return o;
/*     */       case 2:
/*  82 */         return q;
/*     */       case 3:
/*  84 */         return r;
/*     */       case 4:
/*  86 */         return s;
/*     */       case 5:
/*  88 */         return p;
/*     */     } 
/*  90 */     return IHopper.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  95 */     EnumDirection var1 = var0.getClickedFace().opposite();
/*  96 */     return getBlockData().set(FACING, (var1.n() == EnumDirection.EnumAxis.Y) ? EnumDirection.DOWN : var1).set(ENABLED, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess var0) {
/* 101 */     return new TileEntityHopper();
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, EntityLiving var3, ItemStack var4) {
/* 106 */     if (var4.hasName()) {
/* 107 */       TileEntity var5 = var0.getTileEntity(var1);
/* 108 */       if (var5 instanceof TileEntityHopper) {
/* 109 */         ((TileEntityHopper)var5).setCustomName(var4.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/* 116 */     if (var3.a(var0.getBlock())) {
/*     */       return;
/*     */     }
/* 119 */     a(var1, var2, var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 124 */     if (var1.isClientSide) {
/* 125 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/* 128 */     TileEntity var6 = var1.getTileEntity(var2);
/* 129 */     if (var6 instanceof TileEntityHopper) {
/* 130 */       var3.openContainer((TileEntityHopper)var6);
/* 131 */       var3.a(StatisticList.INSPECT_HOPPER);
/*     */     } 
/*     */     
/* 134 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData var0, World var1, BlockPosition var2, Block var3, BlockPosition var4, boolean var5) {
/* 139 */     a(var1, var2, var0);
/*     */   }
/*     */   
/*     */   private void a(World var0, BlockPosition var1, IBlockData var2) {
/* 143 */     boolean var3 = !var0.isBlockIndirectlyPowered(var1);
/* 144 */     if (var3 != ((Boolean)var2.get(ENABLED)).booleanValue()) {
/* 145 */       var0.setTypeAndData(var1, var2.set(ENABLED, Boolean.valueOf(var3)), 4);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/* 151 */     if (var0.a(var3.getBlock())) {
/*     */       return;
/*     */     }
/* 154 */     TileEntity var5 = var1.getTileEntity(var2);
/* 155 */     if (var5 instanceof TileEntityHopper) {
/* 156 */       InventoryUtils.dropInventory(var1, var2, (TileEntityHopper)var5);
/*     */       
/* 158 */       var1.updateAdjacentComparators(var2, this);
/*     */     } 
/*     */     
/* 161 */     super.remove(var0, var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData var0) {
/* 166 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData var0) {
/* 171 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData var0, World var1, BlockPosition var2) {
/* 176 */     return Container.a(var1.getTileEntity(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 181 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 186 */     return var0.a(var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 191 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING, ENABLED });
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData var0, World var1, BlockPosition var2, Entity var3) {
/* 196 */     TileEntity var4 = var1.getTileEntity(var2);
/* 197 */     if (var4 instanceof TileEntityHopper) {
/* 198 */       ((TileEntityHopper)var4).a(var3);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 204 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */