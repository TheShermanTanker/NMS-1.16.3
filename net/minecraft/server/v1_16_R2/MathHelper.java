/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.function.IntPredicate;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MathHelper
/*     */ {
/*  32 */   public static final float a = c(2.0F);
/*     */   private static final float[] b;
/*     */   
/*     */   static {
/*  36 */     b = SystemUtils.<float[]>a(new float[65536], var0 -> {
/*     */           for (int var1 = 0; var1 < var0.length; var1++)
/*     */             var0[var1] = (float)Math.sin(var1 * Math.PI * 2.0D / 65536.0D); 
/*     */         });
/*     */   }
/*     */   
/*  42 */   private static final Random c = new Random();
/*     */   
/*     */   public static float sin(float var0) {
/*  45 */     return b[(int)(var0 * 10430.378F) & 0xFFFF];
/*     */   }
/*     */   
/*     */   public static float cos(float var0) {
/*  49 */     return b[(int)(var0 * 10430.378F + 16384.0F) & 0xFFFF];
/*     */   }
/*     */   
/*     */   public static float c(float var0) {
/*  53 */     return (float)Math.sqrt(var0);
/*     */   }
/*     */   
/*     */   public static float sqrt(double var0) {
/*  57 */     return (float)Math.sqrt(var0);
/*     */   }
/*     */   
/*     */   public static int d(float var0) {
/*  61 */     int var1 = (int)var0;
/*  62 */     return (var0 < var1) ? (var1 - 1) : var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int floor(double var0) {
/*  70 */     int var2 = (int)var0;
/*  71 */     return (var0 < var2) ? (var2 - 1) : var2;
/*     */   }
/*     */   
/*     */   public static long d(double var0) {
/*  75 */     long var2 = (long)var0;
/*  76 */     return (var0 < var2) ? (var2 - 1L) : var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float e(float var0) {
/*  84 */     return Math.abs(var0);
/*     */   }
/*     */   
/*     */   public static int a(int var0) {
/*  88 */     return Math.abs(var0);
/*     */   }
/*     */   
/*     */   public static int f(float var0) {
/*  92 */     int var1 = (int)var0;
/*  93 */     return (var0 > var1) ? (var1 + 1) : var1;
/*     */   }
/*     */   
/*     */   public static int f(double var0) {
/*  97 */     int var2 = (int)var0;
/*  98 */     return (var0 > var2) ? (var2 + 1) : var2;
/*     */   }
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
/*     */   public static int clamp(int var0, int var1, int var2) {
/* 112 */     if (var0 < var1) {
/* 113 */       return var1;
/*     */     }
/* 115 */     if (var0 > var2) {
/* 116 */       return var2;
/*     */     }
/* 118 */     return var0;
/*     */   }
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
/*     */   public static float a(float var0, float var1, float var2) {
/* 132 */     if (var0 < var1) {
/* 133 */       return var1;
/*     */     }
/* 135 */     if (var0 > var2) {
/* 136 */       return var2;
/*     */     }
/* 138 */     return var0;
/*     */   }
/*     */   
/*     */   public static double a(double var0, double var2, double var4) {
/* 142 */     if (var0 < var2) {
/* 143 */       return var2;
/*     */     }
/* 145 */     if (var0 > var4) {
/* 146 */       return var4;
/*     */     }
/* 148 */     return var0;
/*     */   }
/*     */   
/*     */   public static double b(double var0, double var2, double var4) {
/* 152 */     if (var4 < 0.0D) {
/* 153 */       return var0;
/*     */     }
/* 155 */     if (var4 > 1.0D) {
/* 156 */       return var2;
/*     */     }
/* 158 */     return d(var4, var0, var2);
/*     */   }
/*     */   
/*     */   public static double a(double var0, double var2) {
/* 162 */     if (var0 < 0.0D) {
/* 163 */       var0 = -var0;
/*     */     }
/* 165 */     if (var2 < 0.0D) {
/* 166 */       var2 = -var2;
/*     */     }
/* 168 */     return (var0 > var2) ? var0 : var2;
/*     */   }
/*     */   
/*     */   public static int a(int var0, int var1) {
/* 172 */     return Math.floorDiv(var0, var1);
/*     */   }
/*     */   
/*     */   public static int nextInt(Random var0, int var1, int var2) {
/* 176 */     if (var1 >= var2) {
/* 177 */       return var1;
/*     */     }
/* 179 */     return var0.nextInt(var2 - var1 + 1) + var1;
/*     */   }
/*     */   
/*     */   public static float a(Random var0, float var1, float var2) {
/* 183 */     if (var1 >= var2) {
/* 184 */       return var1;
/*     */     }
/* 186 */     return var0.nextFloat() * (var2 - var1) + var1;
/*     */   }
/*     */   
/*     */   public static double a(Random var0, double var1, double var3) {
/* 190 */     if (var1 >= var3) {
/* 191 */       return var1;
/*     */     }
/* 193 */     return var0.nextDouble() * (var3 - var1) + var1;
/*     */   }
/*     */   
/*     */   public static double a(long[] var0) {
/* 197 */     long var1 = 0L;
/*     */     
/* 199 */     for (long var6 : var0) {
/* 200 */       var1 += var6;
/*     */     }
/*     */     
/* 203 */     return var1 / var0.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean b(double var0, double var2) {
/* 211 */     return (Math.abs(var2 - var0) < 9.999999747378752E-6D);
/*     */   }
/*     */   
/*     */   public static int b(int var0, int var1) {
/* 215 */     return Math.floorMod(var0, var1);
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float g(float var0) {
/* 244 */     float var1 = var0 % 360.0F;
/* 245 */     if (var1 >= 180.0F) {
/* 246 */       var1 -= 360.0F;
/*     */     }
/* 248 */     if (var1 < -180.0F) {
/* 249 */       var1 += 360.0F;
/*     */     }
/* 251 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double g(double var0) {
/* 258 */     double var2 = var0 % 360.0D;
/* 259 */     if (var2 >= 180.0D) {
/* 260 */       var2 -= 360.0D;
/*     */     }
/* 262 */     if (var2 < -180.0D) {
/* 263 */       var2 += 360.0D;
/*     */     }
/* 265 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float c(float var0, float var1) {
/* 273 */     return g(var1 - var0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float d(float var0, float var1) {
/* 281 */     return e(c(var0, var1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float b(float var0, float var1, float var2) {
/* 290 */     float var3 = c(var0, var1);
/* 291 */     float var4 = a(var3, -var2, var2);
/* 292 */     return var1 - var4;
/*     */   }
/*     */   
/*     */   public static float c(float var0, float var1, float var2) {
/* 296 */     var2 = e(var2);
/*     */     
/* 298 */     if (var0 < var1) {
/* 299 */       return a(var0 + var2, var0, var1);
/*     */     }
/* 301 */     return a(var0 - var2, var1, var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float d(float var0, float var1, float var2) {
/* 306 */     float var3 = c(var0, var1);
/* 307 */     return c(var0, var0 + var3, var2);
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int c(int var0) {
/* 332 */     int var1 = var0 - 1;
/* 333 */     var1 |= var1 >> 1;
/* 334 */     var1 |= var1 >> 2;
/* 335 */     var1 |= var1 >> 4;
/* 336 */     var1 |= var1 >> 8;
/* 337 */     var1 |= var1 >> 16;
/* 338 */     return var1 + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean d(int var0) {
/* 343 */     return (var0 != 0 && (var0 & var0 - 1) == 0);
/*     */   }
/*     */ 
/*     */   
/* 347 */   private static final int[] d = new int[] { 0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9 };
/*     */ 
/*     */ 
/*     */   
/*     */   public static int e(int var0) {
/* 352 */     var0 = d(var0) ? var0 : c(var0);
/* 353 */     return d[(int)(var0 * 125613361L >> 27L) & 0x1F];
/*     */   }
/*     */   
/*     */   public static int f(int var0) {
/* 357 */     return e(var0) - (d(var0) ? 0 : 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int c(int var0, int var1) {
/* 362 */     if (var1 == 0) {
/* 363 */       return 0;
/*     */     }
/* 365 */     if (var0 == 0) {
/* 366 */       return var1;
/*     */     }
/*     */     
/* 369 */     if (var0 < 0) {
/* 370 */       var1 *= -1;
/*     */     }
/*     */     
/* 373 */     int var2 = var0 % var1;
/* 374 */     if (var2 == 0) {
/* 375 */       return var0;
/*     */     }
/* 377 */     return var0 + var1 - var2;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float h(float var0) {
/* 420 */     return var0 - d(var0);
/*     */   }
/*     */   
/*     */   public static double h(double var0) {
/* 424 */     return var0 - d(var0);
/*     */   }
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
/*     */   public static long a(BaseBlockPosition var0) {
/* 437 */     return c(var0.getX(), var0.getY(), var0.getZ());
/*     */   }
/*     */   
/*     */   public static long c(int var0, int var1, int var2) {
/* 441 */     long var3 = (var0 * 3129871) ^ var2 * 116129781L ^ var1;
/* 442 */     var3 = var3 * var3 * 42317861L + var3 * 11L;
/* 443 */     return var3 >> 16L;
/*     */   }
/*     */   
/*     */   public static UUID a(Random var0) {
/* 447 */     long var1 = var0.nextLong() & 0xFFFFFFFFFFFF0FFFL | 0x4000L;
/* 448 */     long var3 = var0.nextLong() & 0x3FFFFFFFFFFFFFFFL | Long.MIN_VALUE;
/* 449 */     return new UUID(var1, var3);
/*     */   }
/*     */   
/*     */   public static UUID a() {
/* 453 */     return a(c);
/*     */   }
/*     */   
/*     */   public static double c(double var0, double var2, double var4) {
/* 457 */     return (var0 - var2) / (var4 - var2);
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double d(double var0, double var2) {
/* 503 */     double var4 = var2 * var2 + var0 * var0;
/*     */ 
/*     */     
/* 506 */     if (Double.isNaN(var4)) {
/* 507 */       return Double.NaN;
/*     */     }
/*     */ 
/*     */     
/* 511 */     boolean var6 = (var0 < 0.0D);
/* 512 */     if (var6) {
/* 513 */       var0 = -var0;
/*     */     }
/* 515 */     boolean var7 = (var2 < 0.0D);
/* 516 */     if (var7) {
/* 517 */       var2 = -var2;
/*     */     }
/* 519 */     boolean var8 = (var0 > var2);
/* 520 */     if (var8) {
/* 521 */       double d = var2;
/* 522 */       var2 = var0;
/* 523 */       var0 = d;
/*     */     } 
/*     */ 
/*     */     
/* 527 */     double var9 = i(var4);
/* 528 */     var2 *= var9;
/* 529 */     var0 *= var9;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 538 */     double var11 = e + var0;
/* 539 */     int var13 = (int)Double.doubleToRawLongBits(var11);
/*     */ 
/*     */     
/* 542 */     double var14 = f[var13];
/* 543 */     double var16 = g[var13];
/*     */ 
/*     */ 
/*     */     
/* 547 */     double var18 = var11 - e;
/* 548 */     double var20 = var0 * var16 - var2 * var18;
/*     */ 
/*     */     
/* 551 */     double var22 = (6.0D + var20 * var20) * var20 * 0.16666666666666666D;
/* 552 */     double var24 = var14 + var22;
/*     */ 
/*     */     
/* 555 */     if (var8) {
/* 556 */       var24 = 1.5707963267948966D - var24;
/*     */     }
/* 558 */     if (var7) {
/* 559 */       var24 = Math.PI - var24;
/*     */     }
/* 561 */     if (var6) {
/* 562 */       var24 = -var24;
/*     */     }
/*     */     
/* 565 */     return var24;
/*     */   }
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
/*     */   public static double i(double var0) {
/* 578 */     double var2 = 0.5D * var0;
/* 579 */     long var4 = Double.doubleToRawLongBits(var0);
/* 580 */     var4 = 6910469410427058090L - (var4 >> 1L);
/* 581 */     var0 = Double.longBitsToDouble(var4);
/* 582 */     var0 *= 1.5D - var2 * var0 * var0;
/* 583 */     return var0;
/*     */   }
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
/* 598 */   private static final double e = Double.longBitsToDouble(4805340802404319232L);
/* 599 */   private static final double[] f = new double[257];
/* 600 */   private static final double[] g = new double[257];
/*     */ 
/*     */   
/*     */   static {
/* 604 */     for (int var0 = 0; var0 < 257; var0++) {
/* 605 */       double var1 = var0 / 256.0D;
/* 606 */       double var3 = Math.asin(var1);
/* 607 */       g[var0] = Math.cos(var3);
/* 608 */       f[var0] = var3;
/*     */     } 
/*     */   }
/*     */   public static int f(float var0, float var1, float var2) {
/*     */     float var8, var9, var10;
/* 613 */     int var11, var12, var13, var3 = (int)(var0 * 6.0F) % 6;
/* 614 */     float var4 = var0 * 6.0F - var3;
/* 615 */     float var5 = var2 * (1.0F - var1);
/* 616 */     float var6 = var2 * (1.0F - var4 * var1);
/* 617 */     float var7 = var2 * (1.0F - (1.0F - var4) * var1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 623 */     switch (var3) {
/*     */       case 0:
/* 625 */         var8 = var2;
/* 626 */         var9 = var7;
/* 627 */         var10 = var5;
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
/* 658 */         var11 = clamp((int)(var8 * 255.0F), 0, 255);
/* 659 */         var12 = clamp((int)(var9 * 255.0F), 0, 255);
/* 660 */         var13 = clamp((int)(var10 * 255.0F), 0, 255);
/*     */         
/* 662 */         return var11 << 16 | var12 << 8 | var13;case 1: var8 = var6; var9 = var2; var10 = var5; var11 = clamp((int)(var8 * 255.0F), 0, 255); var12 = clamp((int)(var9 * 255.0F), 0, 255); var13 = clamp((int)(var10 * 255.0F), 0, 255); return var11 << 16 | var12 << 8 | var13;case 2: var8 = var5; var9 = var2; var10 = var7; var11 = clamp((int)(var8 * 255.0F), 0, 255); var12 = clamp((int)(var9 * 255.0F), 0, 255); var13 = clamp((int)(var10 * 255.0F), 0, 255); return var11 << 16 | var12 << 8 | var13;case 3: var8 = var5; var9 = var6; var10 = var2; var11 = clamp((int)(var8 * 255.0F), 0, 255); var12 = clamp((int)(var9 * 255.0F), 0, 255); var13 = clamp((int)(var10 * 255.0F), 0, 255); return var11 << 16 | var12 << 8 | var13;case 4: var8 = var7; var9 = var5; var10 = var2; var11 = clamp((int)(var8 * 255.0F), 0, 255); var12 = clamp((int)(var9 * 255.0F), 0, 255); var13 = clamp((int)(var10 * 255.0F), 0, 255); return var11 << 16 | var12 << 8 | var13;case 5: var8 = var2; var9 = var5; var10 = var6; var11 = clamp((int)(var8 * 255.0F), 0, 255); var12 = clamp((int)(var9 * 255.0F), 0, 255); var13 = clamp((int)(var10 * 255.0F), 0, 255); return var11 << 16 | var12 << 8 | var13;
/*     */     } 
/*     */     throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + var0 + ", " + var1 + ", " + var2);
/*     */   }
/*     */   public static int g(int var0) {
/* 667 */     var0 ^= var0 >>> 16;
/* 668 */     var0 *= -2048144789;
/* 669 */     var0 ^= var0 >>> 13;
/* 670 */     var0 *= -1028477387;
/* 671 */     var0 ^= var0 >>> 16;
/* 672 */     return var0;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int a(int var0, int var1, IntPredicate var2) {
/* 769 */     int var3 = var1 - var0;
/* 770 */     while (var3 > 0) {
/* 771 */       int var4 = var3 / 2;
/* 772 */       int var5 = var0 + var4;
/* 773 */       if (var2.test(var5)) {
/*     */         
/* 775 */         var3 = var4; continue;
/*     */       } 
/* 777 */       var0 = var5 + 1;
/* 778 */       var3 -= var4 + 1;
/*     */     } 
/*     */     
/* 781 */     return var0;
/*     */   }
/*     */   
/*     */   public static float g(float var0, float var1, float var2) {
/* 785 */     return var1 + var0 * (var2 - var1);
/*     */   }
/*     */   
/*     */   public static double d(double var0, double var2, double var4) {
/* 789 */     return var2 + var0 * (var4 - var2);
/*     */   }
/*     */   
/*     */   public static double a(double var0, double var2, double var4, double var6, double var8, double var10) {
/* 793 */     return d(var2, 
/*     */         
/* 795 */         d(var0, var4, var6), 
/* 796 */         d(var0, var8, var10));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double a(double var0, double var2, double var4, double var6, double var8, double var10, double var12, double var14, double var16, double var18, double var20) {
/* 805 */     return d(var4, 
/*     */         
/* 807 */         a(var0, var2, var6, var8, var10, var12), 
/* 808 */         a(var0, var2, var14, var16, var18, var20));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double j(double var0) {
/* 816 */     return var0 * var0 * var0 * (var0 * (var0 * 6.0D - 15.0D) + 10.0D);
/*     */   }
/*     */   
/*     */   public static int k(double var0) {
/* 820 */     if (var0 == 0.0D) {
/* 821 */       return 0;
/*     */     }
/* 823 */     return (var0 > 0.0D) ? 1 : -1;
/*     */   }
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
/*     */   @Deprecated
/*     */   public static float j(float var0, float var1, float var2) {
/* 837 */     float var3 = var1 - var0;
/* 838 */     while (var3 < -180.0F) {
/* 839 */       var3 += 360.0F;
/*     */     }
/* 841 */     while (var3 >= 180.0F) {
/* 842 */       var3 -= 360.0F;
/*     */     }
/* 844 */     return var0 + var2 * var3;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float k(float var0) {
/* 871 */     return var0 * var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MathHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */