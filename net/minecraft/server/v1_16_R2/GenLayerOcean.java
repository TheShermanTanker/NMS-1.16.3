/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum GenLayerOcean
/*    */   implements AreaTransformer3, AreaTransformerIdentity
/*    */ {
/*  9 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, Area var1, Area var2, int var3, int var4) {
/* 13 */     int var5 = var1.a(a(var3), b(var4));
/* 14 */     int var6 = var2.a(a(var3), b(var4));
/*    */     
/* 16 */     if (!GenLayers.a(var5)) {
/* 17 */       return var5;
/*    */     }
/*    */     
/* 20 */     int var7 = 8;
/* 21 */     int var8 = 4;
/* 22 */     for (int var9 = -8; var9 <= 8; var9 += 4) {
/* 23 */       for (int var10 = -8; var10 <= 8; var10 += 4) {
/* 24 */         int var11 = var1.a(a(var3 + var9), b(var4 + var10));
/* 25 */         if (!GenLayers.a(var11)) {
/* 26 */           if (var6 == 44) {
/* 27 */             return 45;
/*    */           }
/*    */           
/* 30 */           if (var6 == 10) {
/* 31 */             return 46;
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 37 */     if (var5 == 24) {
/* 38 */       if (var6 == 45) {
/* 39 */         return 48;
/*    */       }
/*    */       
/* 42 */       if (var6 == 0) {
/* 43 */         return 24;
/*    */       }
/*    */       
/* 46 */       if (var6 == 46) {
/* 47 */         return 49;
/*    */       }
/*    */       
/* 50 */       if (var6 == 10) {
/* 51 */         return 50;
/*    */       }
/*    */     } 
/* 54 */     return var6;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerOcean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */