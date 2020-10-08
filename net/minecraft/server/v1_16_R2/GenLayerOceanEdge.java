/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum GenLayerOceanEdge
/*    */   implements AreaTransformer1
/*    */ {
/*  8 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1, int var2) {
/* 12 */     NoiseGeneratorPerlin var3 = var0.b();
/* 13 */     double var4 = var3.a(var1 / 8.0D, var2 / 8.0D, 0.0D, 0.0D, 0.0D);
/*    */     
/* 15 */     if (var4 > 0.4D) {
/* 16 */       return 44;
/*    */     }
/*    */     
/* 19 */     if (var4 > 0.2D) {
/* 20 */       return 45;
/*    */     }
/*    */     
/* 23 */     if (var4 < -0.4D) {
/* 24 */       return 10;
/*    */     }
/*    */     
/* 27 */     if (var4 < -0.2D) {
/* 28 */       return 46;
/*    */     }
/*    */     
/* 31 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerOceanEdge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */