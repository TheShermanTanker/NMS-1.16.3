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
/*    */ public interface IBlockWaterlogged
/*    */   extends IFluidSource, IFluidContainer
/*    */ {
/*    */   default boolean canPlace(IBlockAccess var0, BlockPosition var1, IBlockData var2, FluidType var3) {
/* 15 */     return (!((Boolean)var2.get(BlockProperties.C)).booleanValue() && var3 == FluidTypes.WATER);
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean place(GeneratorAccess var0, BlockPosition var1, IBlockData var2, Fluid var3) {
/* 20 */     if (!((Boolean)var2.get(BlockProperties.C)).booleanValue() && var3.getType() == FluidTypes.WATER) {
/* 21 */       if (!var0.s_()) {
/* 22 */         var0.setTypeAndData(var1, var2.set(BlockProperties.C, Boolean.valueOf(true)), 3);
/* 23 */         var0.getFluidTickList().a(var1, var3.getType(), var3.getType().a(var0));
/*    */       } 
/* 25 */       return true;
/*    */     } 
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   default FluidType removeFluid(GeneratorAccess var0, BlockPosition var1, IBlockData var2) {
/* 32 */     if (((Boolean)var2.get(BlockProperties.C)).booleanValue()) {
/* 33 */       var0.setTypeAndData(var1, var2.set(BlockProperties.C, Boolean.valueOf(false)), 3);
/* 34 */       return FluidTypes.WATER;
/*    */     } 
/* 36 */     return FluidTypes.EMPTY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IBlockWaterlogged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */