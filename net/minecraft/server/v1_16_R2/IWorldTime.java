/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public interface IWorldTime
/*    */   extends IWorldReader
/*    */ {
/*    */   long ab();
/*    */   
/*    */   default float ae() {
/*  9 */     return DimensionManager.e[getDimensionManager().b(ab())];
/*    */   }
/*    */   
/*    */   default float f(float var0) {
/* 13 */     return getDimensionManager().a(ab());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IWorldTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */