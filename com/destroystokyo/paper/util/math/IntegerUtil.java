/*     */ package com.destroystokyo.paper.util.math;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class IntegerUtil
/*     */ {
/*     */   public static final int HIGH_BIT_U32 = -2147483648;
/*     */   public static final long HIGH_BIT_U64 = -9223372036854775808L;
/*     */   
/*     */   public static int ceilLog2(int value) {
/*  12 */     return 32 - Integer.numberOfLeadingZeros(value - 1);
/*     */   }
/*     */   
/*     */   public static long ceilLog2(long value) {
/*  16 */     return (64 - Long.numberOfLeadingZeros(value - 1L));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int floorLog2(int value) {
/*  22 */     return 0x1F ^ Integer.numberOfLeadingZeros(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int floorLog2(long value) {
/*  28 */     return 0x3F ^ Long.numberOfLeadingZeros(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int roundCeilLog2(int value) {
/*  39 */     return Integer.MIN_VALUE >>> Integer.numberOfLeadingZeros(value - 1) - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long roundCeilLog2(long value) {
/*  44 */     return Long.MIN_VALUE >>> Long.numberOfLeadingZeros(value - 1L) - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int roundFloorLog2(int value) {
/*  54 */     return Integer.MIN_VALUE >>> Integer.numberOfLeadingZeros(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public static long roundFloorLog2(long value) {
/*  59 */     return Long.MIN_VALUE >>> Long.numberOfLeadingZeros(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPowerOfTwo(int n) {
/*  65 */     return (getTrailingBit(n) == n);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPowerOfTwo(long n) {
/*  71 */     return (getTrailingBit(n) == n);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getTrailingBit(int n) {
/*  76 */     return -n & n;
/*     */   }
/*     */   
/*     */   public static long getTrailingBit(long n) {
/*  80 */     return -n & n;
/*     */   }
/*     */   
/*     */   public static int trailingZeros(int n) {
/*  84 */     return Integer.numberOfTrailingZeros(n);
/*     */   }
/*     */   
/*     */   public static long trailingZeros(long n) {
/*  88 */     return Long.numberOfTrailingZeros(n);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDivisorMultiple(long numbers) {
/*  93 */     return (int)(numbers >>> 32L);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDivisorShift(long numbers) {
/*  98 */     return (int)numbers;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getDivisorNumbers(int d) {
/* 104 */     int delta, ad = branchlessAbs(d);
/*     */     
/* 106 */     if (ad < 2) {
/* 107 */       throw new IllegalArgumentException("|number| must be in [2, 2^31 -1], not: " + d);
/*     */     }
/*     */     
/* 110 */     int two31 = Integer.MIN_VALUE;
/* 111 */     long mask = 4294967295L;
/*     */     
/* 113 */     int p = 31;
/*     */ 
/*     */     
/* 116 */     int t = Integer.MIN_VALUE + (d >>> 31);
/* 117 */     int anc = t - 1 - t % ad;
/* 118 */     int q1 = (int)(2147483648L / (anc & 0xFFFFFFFFL));
/* 119 */     int r1 = Integer.MIN_VALUE - q1 * anc;
/* 120 */     int q2 = (int)(2147483648L / (ad & 0xFFFFFFFFL));
/* 121 */     int r2 = Integer.MIN_VALUE - q2 * ad;
/*     */ 
/*     */     
/*     */     do {
/* 125 */       p++;
/* 126 */       q1 = 2 * q1;
/* 127 */       r1 = 2 * r1;
/* 128 */       if ((r1 & 0xFFFFFFFFL) >= (anc & 0xFFFFFFFFL)) {
/* 129 */         q1++;
/* 130 */         r1 -= anc;
/*     */       } 
/* 132 */       q2 = 2 * q2;
/* 133 */       r2 = 2 * r2;
/* 134 */       if ((r2 & 0xFFFFFFFFL) >= (ad & 0xFFFFFFFFL)) {
/* 135 */         q2++;
/* 136 */         r2 -= ad;
/*     */       } 
/* 138 */       delta = ad - r2;
/* 139 */     } while ((q1 & 0xFFFFFFFFL) < (delta & 0xFFFFFFFFL) || (q1 == delta && r1 == 0));
/*     */     
/* 141 */     int magicNum = q2 + 1;
/* 142 */     if (d < 0) {
/* 143 */       magicNum = -magicNum;
/*     */     }
/* 145 */     int shift = p - 32;
/* 146 */     return magicNum << 32L | shift;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int branchlessAbs(int val) {
/* 151 */     int mask = val >> 31;
/* 152 */     return (mask ^ val) - mask;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long branchlessAbs(long val) {
/* 157 */     long mask = val >> 63L;
/* 158 */     return (mask ^ val) - mask;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hash0(int x) {
/* 165 */     x *= 915625301;
/* 166 */     x ^= x >>> 16;
/* 167 */     return x;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int hash1(int x) {
/* 172 */     x ^= x >>> 15;
/* 173 */     x *= 896182957;
/* 174 */     x ^= x >>> 17;
/* 175 */     return x;
/*     */   }
/*     */   
/*     */   public static int hash2(int x) {
/* 179 */     x ^= x >>> 16;
/* 180 */     x *= 2146121005;
/* 181 */     x ^= x >>> 15;
/* 182 */     x *= -2073254261;
/* 183 */     x ^= x >>> 16;
/* 184 */     return x;
/*     */   }
/*     */   
/*     */   public static int hash3(int x) {
/* 188 */     x ^= x >>> 17;
/* 189 */     x *= -312814405;
/* 190 */     x ^= x >>> 11;
/* 191 */     x *= -1404298415;
/* 192 */     x ^= x >>> 15;
/* 193 */     x *= 830770091;
/* 194 */     x ^= x >>> 14;
/* 195 */     return x;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long hash1(long x) {
/* 200 */     x ^= x >>> 27L;
/* 201 */     x *= -5599904292771383989L;
/* 202 */     x ^= x >>> 28L;
/* 203 */     return x;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long hash2(long x) {
/* 208 */     x ^= x >>> 32L;
/* 209 */     x *= -2960836687051489901L;
/* 210 */     x ^= x >>> 32L;
/* 211 */     x *= -2960836687051489901L;
/* 212 */     x ^= x >>> 32L;
/* 213 */     return x;
/*     */   }
/*     */   
/*     */   public static long hash3(long x) {
/* 217 */     x ^= x >>> 45L;
/* 218 */     x *= -4512136349728674695L;
/* 219 */     x ^= x >>> 41L;
/* 220 */     x *= -2025150219368492809L;
/* 221 */     x ^= x >>> 56L;
/* 222 */     x *= 2277337576034381939L;
/* 223 */     x ^= x >>> 53L;
/* 224 */     return x;
/*     */   }
/*     */   
/*     */   private IntegerUtil() {
/* 228 */     throw new RuntimeException();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\math\IntegerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */