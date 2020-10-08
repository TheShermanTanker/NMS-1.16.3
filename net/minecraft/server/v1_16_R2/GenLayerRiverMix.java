/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum GenLayerRiverMix
/*    */   implements AreaTransformer3, AreaTransformerIdentity
/*    */ {
/*  9 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, Area var1, Area var2, int var3, int var4) {
/* 13 */     int var5 = var1.a(a(var3), b(var4));
/* 14 */     int var6 = var2.a(a(var3), b(var4));
/*    */     
/* 16 */     if (GenLayers.a(var5)) {
/* 17 */       return var5;
/*    */     }
/* 19 */     if (var6 == 7) {
/* 20 */       if (var5 == 12) {
/* 21 */         return 11;
/*    */       }
/* 23 */       if (var5 == 14 || var5 == 15) {
/* 24 */         return 15;
/*    */       }
/* 26 */       return var6 & 0xFF;
/*    */     } 
/* 28 */     return var5;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerRiverMix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */