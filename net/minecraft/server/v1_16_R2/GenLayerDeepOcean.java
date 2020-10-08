/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum GenLayerDeepOcean
/*    */   implements AreaTransformer7
/*    */ {
/*  8 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1, int var2, int var3, int var4, int var5) {
/* 12 */     if (GenLayers.b(var5)) {
/* 13 */       int var6 = 0;
/* 14 */       if (GenLayers.b(var1)) {
/* 15 */         var6++;
/*    */       }
/* 17 */       if (GenLayers.b(var2)) {
/* 18 */         var6++;
/*    */       }
/* 20 */       if (GenLayers.b(var4)) {
/* 21 */         var6++;
/*    */       }
/* 23 */       if (GenLayers.b(var3)) {
/* 24 */         var6++;
/*    */       }
/*    */       
/* 27 */       if (var6 > 3) {
/* 28 */         if (var5 == 44) {
/* 29 */           return 47;
/*    */         }
/*    */         
/* 32 */         if (var5 == 45) {
/* 33 */           return 48;
/*    */         }
/*    */         
/* 36 */         if (var5 == 0) {
/* 37 */           return 24;
/*    */         }
/*    */         
/* 40 */         if (var5 == 46) {
/* 41 */           return 49;
/*    */         }
/*    */         
/* 44 */         if (var5 == 10) {
/* 45 */           return 50;
/*    */         }
/*    */         
/* 48 */         return 24;
/*    */       } 
/*    */     } 
/*    */     
/* 52 */     return var5;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerDeepOcean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */