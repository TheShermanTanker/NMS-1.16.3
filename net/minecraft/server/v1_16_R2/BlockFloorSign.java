/*    */ package net.minecraft.server.v1_16_R2;
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
/*    */ public class BlockFloorSign
/*    */   extends BlockSign
/*    */ {
/* 18 */   public static final BlockStateInteger ROTATION = BlockProperties.aD;
/*    */   
/*    */   public BlockFloorSign(BlockBase.Info var0, BlockPropertyWood var1) {
/* 21 */     super(var0, var1);
/* 22 */     j(((IBlockData)this.blockStateList.getBlockData()).set(ROTATION, Integer.valueOf(0)).set(a, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 27 */     return var1.getType(var2.down()).getMaterial().isBuildable();
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 32 */     Fluid var1 = var0.getWorld().getFluid(var0.getClickPosition());
/* 33 */     return getBlockData().set(ROTATION, Integer.valueOf(MathHelper.floor(((180.0F + var0.h()) * 16.0F / 360.0F) + 0.5D) & 0xF)).set(a, Boolean.valueOf((var1.getType() == FluidTypes.WATER)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 38 */     if (var1 == EnumDirection.DOWN && !canPlace(var0, var3, var4)) {
/* 39 */       return Blocks.AIR.getBlockData();
/*    */     }
/* 41 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 46 */     return var0.set(ROTATION, Integer.valueOf(var1.a(((Integer)var0.get(ROTATION)).intValue(), 16)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 51 */     return var0.set(ROTATION, Integer.valueOf(var1.a(((Integer)var0.get(ROTATION)).intValue(), 16)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 56 */     var0.a((IBlockState<?>[])new IBlockState[] { ROTATION, a });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFloorSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */