/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockDirtSnow
/*    */   extends Block
/*    */ {
/* 13 */   public static final BlockStateBoolean a = BlockProperties.z;
/*    */   
/*    */   protected BlockDirtSnow(BlockBase.Info var0) {
/* 16 */     super(var0);
/* 17 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 22 */     if (var1 == EnumDirection.UP) {
/* 23 */       return var0.set(a, Boolean.valueOf((var2.a(Blocks.SNOW_BLOCK) || var2.a(Blocks.SNOW))));
/*    */     }
/* 25 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 30 */     IBlockData var1 = var0.getWorld().getType(var0.getClickPosition().up());
/* 31 */     return getBlockData().set(a, Boolean.valueOf((var1.a(Blocks.SNOW_BLOCK) || var1.a(Blocks.SNOW))));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 36 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockDirtSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */