/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class NoiseGenerator3Handler
/*     */ {
/*   8 */   protected static final int[][] a = new int[][] { { 1, 1, 0 }, { -1, 1, 0 }, { 1, -1, 0 }, { -1, -1, 0 }, { 1, 0, 1 }, { -1, 0, 1 }, { 1, 0, -1 }, { -1, 0, -1 }, { 0, 1, 1 }, { 0, -1, 1 }, { 0, 1, -1 }, { 0, -1, -1 }, { 1, 1, 0 }, { 0, -1, 1 }, { -1, 1, 0 }, { 0, -1, -1 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  27 */   private static final double e = Math.sqrt(3.0D);
/*  28 */   private static final double f = 0.5D * (e - 1.0D);
/*  29 */   private static final double g = (3.0D - e) / 6.0D;
/*     */   
/*  31 */   private final int[] h = new int[512];
/*     */   
/*     */   public final double b;
/*     */   public final double c;
/*     */   public final double d;
/*     */   
/*     */   public NoiseGenerator3Handler(Random var0) {
/*  38 */     this.b = var0.nextDouble() * 256.0D;
/*  39 */     this.c = var0.nextDouble() * 256.0D;
/*  40 */     this.d = var0.nextDouble() * 256.0D; int var1;
/*  41 */     for (var1 = 0; var1 < 256; var1++) {
/*  42 */       this.h[var1] = var1;
/*     */     }
/*     */     
/*  45 */     for (var1 = 0; var1 < 256; var1++) {
/*  46 */       int var2 = var0.nextInt(256 - var1);
/*  47 */       int var3 = this.h[var1];
/*  48 */       this.h[var1] = this.h[var2 + var1];
/*  49 */       this.h[var2 + var1] = var3;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int a(int var0) {
/*  54 */     return this.h[var0 & 0xFF];
/*     */   }
/*     */   
/*     */   protected static double a(int[] var0, double var1, double var3, double var5) {
/*  58 */     return var0[0] * var1 + var0[1] * var3 + var0[2] * var5;
/*     */   }
/*     */ 
/*     */   
/*     */   private double a(int var0, double var1, double var3, double var5, double var7) {
/*  63 */     double var9, var11 = var7 - var1 * var1 - var3 * var3 - var5 * var5;
/*  64 */     if (var11 < 0.0D) {
/*  65 */       var9 = 0.0D;
/*     */     } else {
/*  67 */       var11 *= var11;
/*  68 */       var9 = var11 * var11 * a(a[var0], var1, var3, var5);
/*     */     } 
/*  70 */     return var9;
/*     */   }
/*     */ 
/*     */   
/*     */   public double a(double var0, double var2) {
/*     */     int var18, var19;
/*  76 */     double var4 = (var0 + var2) * f;
/*  77 */     int var6 = MathHelper.floor(var0 + var4);
/*  78 */     int var7 = MathHelper.floor(var2 + var4);
/*     */ 
/*     */     
/*  81 */     double var8 = (var6 + var7) * g;
/*  82 */     double var10 = var6 - var8;
/*  83 */     double var12 = var7 - var8;
/*     */ 
/*     */     
/*  86 */     double var14 = var0 - var10;
/*  87 */     double var16 = var2 - var12;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     if (var14 > var16) {
/*     */       
/*  97 */       var18 = 1;
/*  98 */       var19 = 0;
/*     */     } else {
/*     */       
/* 101 */       var18 = 0;
/* 102 */       var19 = 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     double var20 = var14 - var18 + g;
/* 110 */     double var22 = var16 - var19 + g;
/*     */ 
/*     */     
/* 113 */     double var24 = var14 - 1.0D + 2.0D * g;
/* 114 */     double var26 = var16 - 1.0D + 2.0D * g;
/*     */ 
/*     */     
/* 117 */     int var28 = var6 & 0xFF;
/* 118 */     int var29 = var7 & 0xFF;
/*     */     
/* 120 */     int var30 = a(var28 + a(var29)) % 12;
/* 121 */     int var31 = a(var28 + var18 + a(var29 + var19)) % 12;
/* 122 */     int var32 = a(var28 + 1 + a(var29 + 1)) % 12;
/*     */ 
/*     */ 
/*     */     
/* 126 */     double var33 = a(var30, var14, var16, 0.0D, 0.5D);
/* 127 */     double var35 = a(var31, var20, var22, 0.0D, 0.5D);
/* 128 */     double var37 = a(var32, var24, var26, 0.0D, 0.5D);
/*     */ 
/*     */ 
/*     */     
/* 132 */     return 70.0D * (var33 + var35 + var37);
/*     */   }
/*     */   
/*     */   public double a(double var0, double var2, double var4) {
/*     */     int var29, var30, var31, var32, var33, var34;
/* 137 */     double var6 = 0.3333333333333333D;
/* 138 */     double var8 = (var0 + var2 + var4) * 0.3333333333333333D;
/*     */     
/* 140 */     int var10 = MathHelper.floor(var0 + var8);
/* 141 */     int var11 = MathHelper.floor(var2 + var8);
/* 142 */     int var12 = MathHelper.floor(var4 + var8);
/* 143 */     double var13 = 0.16666666666666666D;
/* 144 */     double var15 = (var10 + var11 + var12) * 0.16666666666666666D;
/*     */     
/* 146 */     double var17 = var10 - var15;
/* 147 */     double var19 = var11 - var15;
/* 148 */     double var21 = var12 - var15;
/*     */     
/* 150 */     double var23 = var0 - var17;
/* 151 */     double var25 = var2 - var19;
/* 152 */     double var27 = var4 - var21;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     if (var23 >= var25) {
/* 162 */       if (var25 >= var27) {
/*     */         
/* 164 */         var29 = 1;
/* 165 */         var30 = 0;
/* 166 */         var31 = 0;
/* 167 */         var32 = 1;
/* 168 */         var33 = 1;
/* 169 */         var34 = 0;
/* 170 */       } else if (var23 >= var27) {
/*     */         
/* 172 */         var29 = 1;
/* 173 */         var30 = 0;
/* 174 */         var31 = 0;
/* 175 */         var32 = 1;
/* 176 */         var33 = 0;
/* 177 */         var34 = 1;
/*     */       } else {
/*     */         
/* 180 */         var29 = 0;
/* 181 */         var30 = 0;
/* 182 */         var31 = 1;
/* 183 */         var32 = 1;
/* 184 */         var33 = 0;
/* 185 */         var34 = 1;
/*     */       }
/*     */     
/*     */     }
/* 189 */     else if (var25 < var27) {
/*     */       
/* 191 */       var29 = 0;
/* 192 */       var30 = 0;
/* 193 */       var31 = 1;
/* 194 */       var32 = 0;
/* 195 */       var33 = 1;
/* 196 */       var34 = 1;
/* 197 */     } else if (var23 < var27) {
/*     */       
/* 199 */       var29 = 0;
/* 200 */       var30 = 1;
/* 201 */       var31 = 0;
/* 202 */       var32 = 0;
/* 203 */       var33 = 1;
/* 204 */       var34 = 1;
/*     */     } else {
/*     */       
/* 207 */       var29 = 0;
/* 208 */       var30 = 1;
/* 209 */       var31 = 0;
/* 210 */       var32 = 1;
/* 211 */       var33 = 1;
/* 212 */       var34 = 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     double var35 = var23 - var29 + 0.16666666666666666D;
/* 222 */     double var37 = var25 - var30 + 0.16666666666666666D;
/* 223 */     double var39 = var27 - var31 + 0.16666666666666666D;
/*     */ 
/*     */     
/* 226 */     double var41 = var23 - var32 + 0.3333333333333333D;
/* 227 */     double var43 = var25 - var33 + 0.3333333333333333D;
/* 228 */     double var45 = var27 - var34 + 0.3333333333333333D;
/*     */ 
/*     */     
/* 231 */     double var47 = var23 - 1.0D + 0.5D;
/* 232 */     double var49 = var25 - 1.0D + 0.5D;
/* 233 */     double var51 = var27 - 1.0D + 0.5D;
/*     */ 
/*     */     
/* 236 */     int var53 = var10 & 0xFF;
/* 237 */     int var54 = var11 & 0xFF;
/* 238 */     int var55 = var12 & 0xFF;
/*     */     
/* 240 */     int var56 = a(var53 + a(var54 + a(var55))) % 12;
/* 241 */     int var57 = a(var53 + var29 + a(var54 + var30 + a(var55 + var31))) % 12;
/* 242 */     int var58 = a(var53 + var32 + a(var54 + var33 + a(var55 + var34))) % 12;
/* 243 */     int var59 = a(var53 + 1 + a(var54 + 1 + a(var55 + 1))) % 12;
/*     */ 
/*     */     
/* 246 */     double var60 = a(var56, var23, var25, var27, 0.6D);
/* 247 */     double var62 = a(var57, var35, var37, var39, 0.6D);
/* 248 */     double var64 = a(var58, var41, var43, var45, 0.6D);
/* 249 */     double var66 = a(var59, var47, var49, var51, 0.6D);
/*     */ 
/*     */ 
/*     */     
/* 253 */     return 32.0D * (var60 + var62 + var64 + var66);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NoiseGenerator3Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */