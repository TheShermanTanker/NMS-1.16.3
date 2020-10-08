/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public interface WorldDataMutable
/*    */   extends WorldData
/*    */ {
/*    */   void b(int paramInt);
/*    */   
/*    */   void c(int paramInt);
/*    */   
/*    */   void d(int paramInt);
/*    */   
/*    */   void a(float paramFloat);
/*    */   
/*    */   default void setSpawn(BlockPosition var0, float var1) {
/* 15 */     b(var0.getX());
/* 16 */     c(var0.getY());
/* 17 */     d(var0.getZ());
/* 18 */     a(var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldDataMutable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */