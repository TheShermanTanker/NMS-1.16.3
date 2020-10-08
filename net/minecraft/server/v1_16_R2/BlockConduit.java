/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockConduit
/*    */   extends BlockTileEntity
/*    */   implements IBlockWaterlogged
/*    */ {
/* 28 */   public static final BlockStateBoolean a = BlockProperties.C;
/*    */   
/* 30 */   protected static final VoxelShape b = Block.a(5.0D, 5.0D, 5.0D, 11.0D, 11.0D, 11.0D);
/*    */   
/*    */   public BlockConduit(BlockBase.Info var0) {
/* 33 */     super(var0);
/* 34 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Boolean.valueOf(true)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 39 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess var0) {
/* 44 */     return new TileEntityConduit();
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRenderType b(IBlockData var0) {
/* 49 */     return EnumRenderType.ENTITYBLOCK_ANIMATED;
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid d(IBlockData var0) {
/* 54 */     if (((Boolean)var0.get(a)).booleanValue()) {
/* 55 */       return FluidTypes.WATER.a(false);
/*    */     }
/*    */     
/* 58 */     return super.d(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 63 */     if (((Boolean)var0.get(a)).booleanValue()) {
/* 64 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*    */     }
/*    */     
/* 67 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 72 */     return b;
/*    */   }
/*    */ 
/*    */   
/*    */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, @Nullable EntityLiving var3, ItemStack var4) {
/* 77 */     if (var4.hasName()) {
/* 78 */       TileEntity var5 = var0.getTileEntity(var1);
/* 79 */       if (var5 instanceof TileEntityBeacon) {
/* 80 */         ((TileEntityBeacon)var5).setCustomName(var4.getName());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 88 */     Fluid var1 = var0.getWorld().getFluid(var0.getClickPosition());
/* 89 */     return getBlockData().set(a, Boolean.valueOf((var1.a(TagsFluid.WATER) && var1.e() == 8)));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 94 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockConduit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */