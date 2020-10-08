/*    */ package com.destroystokyo.paper.util.math;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public final class ThreadUnsafeRandom
/*    */   extends Random {
/*    */   private static final long multiplier = 25214903917L;
/*    */   private static final long addend = 11L;
/*    */   private static final long mask = 281474976710655L;
/*    */   private long seed;
/*    */   
/*    */   private static long initialScramble(long seed) {
/* 13 */     return (seed ^ 0x5DEECE66DL) & 0xFFFFFFFFFFFFL;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSeed(long seed) {
/* 21 */     this.seed = initialScramble(seed);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int next(int bits) {
/* 27 */     return (int)(((this.seed = this.seed * 25214903917L + 11L) & 0xFFFFFFFFFFFFL) >>> 48 - bits);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int fastRandomBounded(long randomInteger, long limit) {
/* 37 */     return (int)(randomInteger * limit >>> 32L);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int nextInt(int bound) {
/* 44 */     return fastRandomBounded(next(32) & 0xFFFFFFFFL, bound);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\math\ThreadUnsafeRandom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */