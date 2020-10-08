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
/*     */ public class BlockEnderChest
/*     */   extends BlockChestAbstract<TileEntityEnderChest>
/*     */   implements IBlockWaterlogged
/*     */ {
/*  44 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  45 */   public static final BlockStateBoolean c = BlockProperties.C;
/*  46 */   protected static final VoxelShape d = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
/*  47 */   private static final IChatBaseComponent e = new ChatMessage("container.enderchest");
/*     */   
/*     */   protected BlockEnderChest(BlockBase.Info var0) {
/*  50 */     super(var0, () -> TileEntityTypes.ENDER_CHEST);
/*  51 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(c, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  61 */     return d;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData var0) {
/*  66 */     return EnumRenderType.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  71 */     Fluid var1 = var0.getWorld().getFluid(var0.getClickPosition());
/*  72 */     return getBlockData().set(FACING, var0.f().opposite()).set(c, Boolean.valueOf((var1.getType() == FluidTypes.WATER)));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  77 */     InventoryEnderChest var6 = var3.getEnderChest();
/*  78 */     TileEntity var7 = var1.getTileEntity(var2);
/*  79 */     if (var6 == null || !(var7 instanceof TileEntityEnderChest)) {
/*  80 */       return EnumInteractionResult.a(var1.isClientSide);
/*     */     }
/*     */     
/*  83 */     BlockPosition var8 = var2.up();
/*  84 */     if (var1.getType(var8).isOccluding(var1, var8)) {
/*  85 */       return EnumInteractionResult.a(var1.isClientSide);
/*     */     }
/*     */     
/*  88 */     if (var1.isClientSide) {
/*  89 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/*  92 */     TileEntityEnderChest var9 = (TileEntityEnderChest)var7;
/*  93 */     var6.a(var9);
/*     */     
/*  95 */     var3.openContainer(new TileInventory((var1, var2, var3) -> ContainerChest.a(var1, var2, var0), e));
/*  96 */     var3.a(StatisticList.OPEN_ENDERCHEST);
/*  97 */     PiglinAI.a(var3, true);
/*  98 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess var0) {
/* 103 */     return new TileEntityEnderChest();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 125 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 130 */     return var0.a(var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 135 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING, c });
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData var0) {
/* 140 */     if (((Boolean)var0.get(c)).booleanValue()) {
/* 141 */       return FluidTypes.WATER.a(false);
/*     */     }
/* 143 */     return super.d(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 148 */     if (((Boolean)var0.get(c)).booleanValue()) {
/* 149 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*     */     }
/* 151 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 156 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */