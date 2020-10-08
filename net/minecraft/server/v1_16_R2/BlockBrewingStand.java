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
/*     */ public class BlockBrewingStand
/*     */   extends BlockTileEntity
/*     */ {
/*  30 */   public static final BlockStateBoolean[] HAS_BOTTLE = new BlockStateBoolean[] { BlockProperties.k, BlockProperties.l, BlockProperties.m };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   protected static final VoxelShape b = VoxelShapes.a(
/*  37 */       Block.a(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D), 
/*  38 */       Block.a(7.0D, 0.0D, 7.0D, 9.0D, 14.0D, 9.0D));
/*     */ 
/*     */   
/*     */   public BlockBrewingStand(BlockBase.Info var0) {
/*  42 */     super(var0);
/*  43 */     j(((IBlockData)this.blockStateList.getBlockData()).set(HAS_BOTTLE[0], Boolean.valueOf(false)).set(HAS_BOTTLE[1], Boolean.valueOf(false)).set(HAS_BOTTLE[2], Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData var0) {
/*  48 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess var0) {
/*  53 */     return new TileEntityBrewingStand();
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  58 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  63 */     if (var1.isClientSide) {
/*  64 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/*  67 */     TileEntity var6 = var1.getTileEntity(var2);
/*  68 */     if (var6 instanceof TileEntityBrewingStand) {
/*  69 */       var3.openContainer((TileEntityBrewingStand)var6);
/*  70 */       var3.a(StatisticList.INTERACT_WITH_BREWINGSTAND);
/*     */     } 
/*     */     
/*  73 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, EntityLiving var3, ItemStack var4) {
/*  78 */     if (var4.hasName()) {
/*  79 */       TileEntity var5 = var0.getTileEntity(var1);
/*  80 */       if (var5 instanceof TileEntityBrewingStand) {
/*  81 */         ((TileEntityBrewingStand)var5).setCustomName(var4.getName());
/*     */       }
/*     */     } 
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
/*     */   public void remove(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/*  97 */     if (var0.a(var3.getBlock())) {
/*     */       return;
/*     */     }
/* 100 */     TileEntity var5 = var1.getTileEntity(var2);
/* 101 */     if (var5 instanceof TileEntityBrewingStand) {
/* 102 */       InventoryUtils.dropInventory(var1, var2, (TileEntityBrewingStand)var5);
/*     */     }
/* 104 */     super.remove(var0, var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData var0) {
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData var0, World var1, BlockPosition var2) {
/* 114 */     return Container.a(var1.getTileEntity(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 119 */     var0.a((IBlockState<?>[])new IBlockState[] { HAS_BOTTLE[0], HAS_BOTTLE[1], HAS_BOTTLE[2] });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 124 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */