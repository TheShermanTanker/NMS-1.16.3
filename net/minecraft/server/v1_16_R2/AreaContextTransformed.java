/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface AreaContextTransformed<R extends Area>
/*    */   extends WorldGenContext
/*    */ {
/*    */   void a(long paramLong1, long paramLong2);
/*    */   
/*    */   R a(AreaTransformer8 paramAreaTransformer8);
/*    */   
/*    */   default R a(AreaTransformer8 var0, R var1) {
/* 14 */     return a(var0);
/*    */   }
/*    */   
/*    */   default R a(AreaTransformer8 var0, R var1, R var2) {
/* 18 */     return a(var0);
/*    */   }
/*    */   
/*    */   default int a(int var0, int var1) {
/* 22 */     return (a(2) == 0) ? var0 : var1;
/*    */   }
/*    */   
/*    */   default int a(int var0, int var1, int var2, int var3) {
/* 26 */     int var4 = a(4);
/* 27 */     if (var4 == 0) {
/* 28 */       return var0;
/*    */     }
/* 30 */     if (var4 == 1) {
/* 31 */       return var1;
/*    */     }
/* 33 */     if (var4 == 2) {
/* 34 */       return var2;
/*    */     }
/* 36 */     return var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AreaContextTransformed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */