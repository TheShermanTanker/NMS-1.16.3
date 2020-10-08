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
/*    */ public enum EnumBlockSupport
/*    */ {
/* 13 */   FULL
/*    */   {
/*    */     public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, EnumDirection var3) {
/* 16 */       return Block.a(var0.l(var1, var2), var3);
/*    */     }
/*    */   },
/* 19 */   CENTER {
/* 20 */     private final int d = 1;
/* 21 */     private final VoxelShape e = Block.a(7.0D, 0.0D, 7.0D, 9.0D, 10.0D, 9.0D);
/*    */ 
/*    */     
/*    */     public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, EnumDirection var3) {
/* 25 */       return !VoxelShapes.c(var0.l(var1, var2).a(var3), this.e, OperatorBoolean.ONLY_SECOND);
/*    */     }
/*    */   },
/* 28 */   RIGID {
/* 29 */     private final int d = 2;
/* 30 */     private final VoxelShape e = VoxelShapes.a(
/* 31 */         VoxelShapes.b(), 
/* 32 */         Block.a(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D), OperatorBoolean.ONLY_FIRST);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, EnumDirection var3) {
/* 38 */       return !VoxelShapes.c(var0.l(var1, var2).a(var3), this.e, OperatorBoolean.ONLY_SECOND);
/*    */     }
/*    */   };
/*    */   
/*    */   public abstract boolean a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumBlockSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */