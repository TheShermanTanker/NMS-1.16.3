/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public enum GenLayerTopSoil
/*    */   implements AreaTransformer6
/*    */ {
/*  7 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1) {
/* 11 */     if (GenLayers.b(var1)) {
/* 12 */       return var1;
/*    */     }
/* 14 */     int var2 = var0.a(6);
/* 15 */     if (var2 == 0) {
/* 16 */       return 4;
/*    */     }
/* 18 */     if (var2 == 1) {
/* 19 */       return 3;
/*    */     }
/* 21 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerTopSoil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */