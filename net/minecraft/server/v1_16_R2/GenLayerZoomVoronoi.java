/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum GenLayerZoomVoronoi
/*    */   implements GenLayerZoomer
/*    */ {
/*  6 */   INSTANCE;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BiomeBase a(long var0, int var2, int var3, int var4, BiomeManager.Provider var5) {
/* 14 */     int var6 = var2 - 2;
/* 15 */     int var7 = var3 - 2;
/* 16 */     int var8 = var4 - 2;
/*    */     
/* 18 */     int var9 = var6 >> 2;
/* 19 */     int var10 = var7 >> 2;
/* 20 */     int var11 = var8 >> 2;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     double var12 = (var6 & 0x3) / 4.0D;
/* 26 */     double var14 = (var7 & 0x3) / 4.0D;
/* 27 */     double var16 = (var8 & 0x3) / 4.0D;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 32 */     double[] var18 = new double[8];
/*    */     int var19;
/* 34 */     for (var19 = 0; var19 < 8; var19++) {
/* 35 */       boolean bool1 = ((var19 & 0x4) == 0);
/* 36 */       boolean var21 = ((var19 & 0x2) == 0);
/* 37 */       boolean bool2 = ((var19 & 0x1) == 0);
/*    */       
/* 39 */       int i = bool1 ? var9 : (var9 + 1);
/* 40 */       int j = var21 ? var10 : (var10 + 1);
/* 41 */       int var25 = bool2 ? var11 : (var11 + 1);
/*    */       
/* 43 */       double var26 = bool1 ? var12 : (var12 - 1.0D);
/* 44 */       double var28 = var21 ? var14 : (var14 - 1.0D);
/* 45 */       double var30 = bool2 ? var16 : (var16 - 1.0D);
/*    */       
/* 47 */       var18[var19] = a(var0, i, j, var25, var26, var28, var30);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 52 */     var19 = 0;
/* 53 */     double var20 = var18[0]; int var22;
/* 54 */     for (var22 = 1; var22 < 8; var22++) {
/* 55 */       if (var20 > var18[var22]) {
/* 56 */         var19 = var22;
/* 57 */         var20 = var18[var22];
/*    */       } 
/*    */     } 
/*    */     
/* 61 */     var22 = ((var19 & 0x4) == 0) ? var9 : (var9 + 1);
/* 62 */     int var23 = ((var19 & 0x2) == 0) ? var10 : (var10 + 1);
/* 63 */     int var24 = ((var19 & 0x1) == 0) ? var11 : (var11 + 1);
/*    */     
/* 65 */     return var5.getBiome(var22, var23, var24);
/*    */   }
/*    */   
/*    */   private static double a(long var0, int var2, int var3, int var4, double var5, double var7, double var9) {
/* 69 */     long var11 = var0;
/*    */     
/* 71 */     var11 = LinearCongruentialGenerator.a(var11, var2);
/* 72 */     var11 = LinearCongruentialGenerator.a(var11, var3);
/* 73 */     var11 = LinearCongruentialGenerator.a(var11, var4);
/* 74 */     var11 = LinearCongruentialGenerator.a(var11, var2);
/* 75 */     var11 = LinearCongruentialGenerator.a(var11, var3);
/* 76 */     var11 = LinearCongruentialGenerator.a(var11, var4);
/*    */     
/* 78 */     double var13 = a(var11);
/*    */     
/* 80 */     var11 = LinearCongruentialGenerator.a(var11, var0);
/*    */     
/* 82 */     double var15 = a(var11);
/*    */     
/* 84 */     var11 = LinearCongruentialGenerator.a(var11, var0);
/*    */     
/* 86 */     double var17 = a(var11);
/*    */     
/* 88 */     return a(var9 + var17) + a(var7 + var15) + a(var5 + var13);
/*    */   }
/*    */   
/*    */   private static double a(long var0) {
/* 92 */     double var2 = (int)Math.floorMod(var0 >> 24L, 1024L) / 1024.0D;
/* 93 */     return (var2 - 0.5D) * 0.9D;
/*    */   }
/*    */   
/*    */   private static double a(double var0) {
/* 97 */     return var0 * var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerZoomVoronoi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */