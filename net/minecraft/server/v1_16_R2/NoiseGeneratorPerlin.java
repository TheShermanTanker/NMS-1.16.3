/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class NoiseGeneratorPerlin
/*    */ {
/*    */   private final byte[] d;
/*    */   public final double a;
/*    */   public final double b;
/*    */   public final double c;
/*    */   
/*    */   public NoiseGeneratorPerlin(Random var0) {
/* 16 */     this.a = var0.nextDouble() * 256.0D;
/* 17 */     this.b = var0.nextDouble() * 256.0D;
/* 18 */     this.c = var0.nextDouble() * 256.0D;
/*    */     
/* 20 */     this.d = new byte[256];
/*    */     int var1;
/* 22 */     for (var1 = 0; var1 < 256; var1++) {
/* 23 */       this.d[var1] = (byte)var1;
/*    */     }
/*    */     
/* 26 */     for (var1 = 0; var1 < 256; var1++) {
/* 27 */       int var2 = var0.nextInt(256 - var1);
/* 28 */       byte var3 = this.d[var1];
/* 29 */       this.d[var1] = this.d[var1 + var2];
/* 30 */       this.d[var1 + var2] = var3;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public double a(double var0, double var2, double var4, double var6, double var8) {
/* 36 */     double var31, var10 = var0 + this.a;
/* 37 */     double var12 = var2 + this.b;
/* 38 */     double var14 = var4 + this.c;
/*    */     
/* 40 */     int var16 = MathHelper.floor(var10);
/* 41 */     int var17 = MathHelper.floor(var12);
/* 42 */     int var18 = MathHelper.floor(var14);
/*    */ 
/*    */     
/* 45 */     double var19 = var10 - var16;
/* 46 */     double var21 = var12 - var17;
/* 47 */     double var23 = var14 - var18;
/*    */ 
/*    */     
/* 50 */     double var25 = MathHelper.j(var19);
/* 51 */     double var27 = MathHelper.j(var21);
/* 52 */     double var29 = MathHelper.j(var23);
/*    */ 
/*    */     
/* 55 */     if (var6 != 0.0D) {
/* 56 */       double var33 = Math.min(var8, var21);
/* 57 */       var31 = MathHelper.floor(var33 / var6) * var6;
/*    */     } else {
/* 59 */       var31 = 0.0D;
/*    */     } 
/*    */     
/* 62 */     return a(var16, var17, var18, var19, var21 - var31, var23, var25, var27, var29);
/*    */   }
/*    */   
/*    */   private static double a(int var0, double var1, double var3, double var5) {
/* 66 */     int var7 = var0 & 0xF;
/* 67 */     return NoiseGenerator3Handler.a(NoiseGenerator3Handler.a[var7], var1, var3, var5);
/*    */   }
/*    */   
/*    */   private int a(int var0) {
/* 71 */     return this.d[var0 & 0xFF] & 0xFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public double a(int var0, int var1, int var2, double var3, double var5, double var7, double var9, double var11, double var13) {
/* 76 */     int var15 = a(var0) + var1;
/* 77 */     int var16 = a(var15) + var2;
/* 78 */     int var17 = a(var15 + 1) + var2;
/*    */     
/* 80 */     int var18 = a(var0 + 1) + var1;
/* 81 */     int var19 = a(var18) + var2;
/* 82 */     int var20 = a(var18 + 1) + var2;
/*    */ 
/*    */     
/* 85 */     double var21 = a(a(var16), var3, var5, var7);
/* 86 */     double var23 = a(a(var19), var3 - 1.0D, var5, var7);
/* 87 */     double var25 = a(a(var17), var3, var5 - 1.0D, var7);
/* 88 */     double var27 = a(a(var20), var3 - 1.0D, var5 - 1.0D, var7);
/* 89 */     double var29 = a(a(var16 + 1), var3, var5, var7 - 1.0D);
/* 90 */     double var31 = a(a(var19 + 1), var3 - 1.0D, var5, var7 - 1.0D);
/* 91 */     double var33 = a(a(var17 + 1), var3, var5 - 1.0D, var7 - 1.0D);
/* 92 */     double var35 = a(a(var20 + 1), var3 - 1.0D, var5 - 1.0D, var7 - 1.0D);
/*    */ 
/*    */     
/* 95 */     return MathHelper.a(var9, var11, var13, var21, var23, var25, var27, var29, var31, var33, var35);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NoiseGeneratorPerlin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */