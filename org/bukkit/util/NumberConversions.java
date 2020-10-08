/*     */ package org.bukkit.util;
/*     */ 
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class NumberConversions
/*     */ {
/*     */   public static int floor(double num) {
/*  13 */     int floor = (int)num;
/*  14 */     return (floor == num) ? floor : (floor - (int)(Double.doubleToRawLongBits(num) >>> 63L));
/*     */   }
/*     */   
/*     */   public static int ceil(double num) {
/*  18 */     int floor = (int)num;
/*  19 */     return (floor == num) ? floor : (floor + (int)((Double.doubleToRawLongBits(num) ^ 0xFFFFFFFFFFFFFFFFL) >>> 63L));
/*     */   }
/*     */   
/*     */   public static int round(double num) {
/*  23 */     return floor(num + 0.5D);
/*     */   }
/*     */   
/*     */   public static double square(double num) {
/*  27 */     return num * num;
/*     */   }
/*     */   
/*     */   public static int toInt(@Nullable Object object) {
/*  31 */     if (object instanceof Number) {
/*  32 */       return ((Number)object).intValue();
/*     */     }
/*     */ 
/*     */     
/*  36 */     try { return Integer.parseInt(object.toString()); }
/*  37 */     catch (NumberFormatException numberFormatException) {  }
/*  38 */     catch (NullPointerException nullPointerException) {}
/*     */     
/*  40 */     return 0;
/*     */   }
/*     */   
/*     */   public static float toFloat(@Nullable Object object) {
/*  44 */     if (object instanceof Number) {
/*  45 */       return ((Number)object).floatValue();
/*     */     }
/*     */ 
/*     */     
/*  49 */     try { return Float.parseFloat(object.toString()); }
/*  50 */     catch (NumberFormatException numberFormatException) {  }
/*  51 */     catch (NullPointerException nullPointerException) {}
/*     */     
/*  53 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public static double toDouble(@Nullable Object object) {
/*  57 */     if (object instanceof Number) {
/*  58 */       return ((Number)object).doubleValue();
/*     */     }
/*     */ 
/*     */     
/*  62 */     try { return Double.parseDouble(object.toString()); }
/*  63 */     catch (NumberFormatException numberFormatException) {  }
/*  64 */     catch (NullPointerException nullPointerException) {}
/*     */     
/*  66 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public static long toLong(@Nullable Object object) {
/*  70 */     if (object instanceof Number) {
/*  71 */       return ((Number)object).longValue();
/*     */     }
/*     */ 
/*     */     
/*  75 */     try { return Long.parseLong(object.toString()); }
/*  76 */     catch (NumberFormatException numberFormatException) {  }
/*  77 */     catch (NullPointerException nullPointerException) {}
/*     */     
/*  79 */     return 0L;
/*     */   }
/*     */   
/*     */   public static short toShort(@Nullable Object object) {
/*  83 */     if (object instanceof Number) {
/*  84 */       return ((Number)object).shortValue();
/*     */     }
/*     */ 
/*     */     
/*  88 */     try { return Short.parseShort(object.toString()); }
/*  89 */     catch (NumberFormatException numberFormatException) {  }
/*  90 */     catch (NullPointerException nullPointerException) {}
/*     */     
/*  92 */     return 0;
/*     */   }
/*     */   
/*     */   public static byte toByte(@Nullable Object object) {
/*  96 */     if (object instanceof Number) {
/*  97 */       return ((Number)object).byteValue();
/*     */     }
/*     */ 
/*     */     
/* 101 */     try { return Byte.parseByte(object.toString()); }
/* 102 */     catch (NumberFormatException numberFormatException) {  }
/* 103 */     catch (NullPointerException nullPointerException) {}
/*     */     
/* 105 */     return 0;
/*     */   }
/*     */   
/*     */   public static boolean isFinite(double d) {
/* 109 */     return (Math.abs(d) <= Double.MAX_VALUE);
/*     */   }
/*     */   
/*     */   public static boolean isFinite(float f) {
/* 113 */     return (Math.abs(f) <= Float.MAX_VALUE);
/*     */   }
/*     */   
/*     */   public static void checkFinite(double d, @NotNull String message) {
/* 117 */     if (!isFinite(d)) {
/* 118 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void checkFinite(float d, @NotNull String message) {
/* 123 */     if (!isFinite(d))
/* 124 */       throw new IllegalArgumentException(message); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\NumberConversions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */