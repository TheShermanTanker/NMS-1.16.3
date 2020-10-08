/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public enum GenLayerDesert
/*    */   implements AreaTransformer7
/*    */ {
/*  7 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1, int var2, int var3, int var4, int var5) {
/* 11 */     int[] var6 = new int[1];
/* 12 */     if (a(var6, var5) || 
/* 13 */       a(var6, var1, var2, var3, var4, var5, 38, 37) || 
/* 14 */       a(var6, var1, var2, var3, var4, var5, 39, 37) || 
/* 15 */       a(var6, var1, var2, var3, var4, var5, 32, 5))
/*    */     {
/* 17 */       return var6[0];
/*    */     }
/*    */     
/* 20 */     if (var5 == 2 && (var1 == 12 || var2 == 12 || var4 == 12 || var3 == 12)) {
/* 21 */       return 34;
/*    */     }
/*    */ 
/*    */     
/* 25 */     if (var5 == 6) {
/* 26 */       if (var1 == 2 || var2 == 2 || var4 == 2 || var3 == 2 || var1 == 30 || var2 == 30 || var4 == 30 || var3 == 30 || var1 == 12 || var2 == 12 || var4 == 12 || var3 == 12)
/*    */       {
/*    */ 
/*    */         
/* 30 */         return 1; } 
/* 31 */       if (var1 == 21 || var3 == 21 || var2 == 21 || var4 == 21 || var1 == 168 || var3 == 168 || var2 == 168 || var4 == 168)
/*    */       {
/*    */         
/* 34 */         return 23;
/*    */       }
/*    */     } 
/* 37 */     return var5;
/*    */   }
/*    */   
/*    */   private boolean a(int[] var0, int var1) {
/* 41 */     if (!GenLayers.a(var1, 3)) {
/* 42 */       return false;
/*    */     }
/* 44 */     var0[0] = var1;
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   private boolean a(int[] var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
/* 49 */     if (var5 != var6) {
/* 50 */       return false;
/*    */     }
/* 52 */     if (GenLayers.a(var1, var6) && GenLayers.a(var2, var6) && GenLayers.a(var4, var6) && GenLayers.a(var3, var6)) {
/* 53 */       var0[0] = var5;
/*    */     } else {
/* 55 */       var0[0] = var7;
/*    */     } 
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerDesert.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */