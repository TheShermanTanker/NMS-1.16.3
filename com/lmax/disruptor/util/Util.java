/*     */ package com.lmax.disruptor.util;
/*     */ 
/*     */ import com.lmax.disruptor.EventProcessor;
/*     */ import com.lmax.disruptor.Sequence;
/*     */ import java.lang.reflect.Field;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import sun.misc.Unsafe;
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
/*     */ public final class Util
/*     */ {
/*     */   private static final Unsafe THE_UNSAFE;
/*     */   
/*     */   public static int ceilingNextPowerOfTwo(int x) {
/*  42 */     return 1 << 32 - Integer.numberOfLeadingZeros(x - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getMinimumSequence(Sequence[] sequences) {
/*  53 */     return getMinimumSequence(sequences, Long.MAX_VALUE);
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
/*     */   public static long getMinimumSequence(Sequence[] sequences, long minimum) {
/*  67 */     for (int i = 0, n = sequences.length; i < n; i++) {
/*     */       
/*  69 */       long value = sequences[i].get();
/*  70 */       minimum = Math.min(minimum, value);
/*     */     } 
/*     */     
/*  73 */     return minimum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Sequence[] getSequencesFor(EventProcessor... processors) {
/*  84 */     Sequence[] sequences = new Sequence[processors.length];
/*  85 */     for (int i = 0; i < sequences.length; i++)
/*     */     {
/*  87 */       sequences[i] = processors[i].getSequence();
/*     */     }
/*     */     
/*  90 */     return sequences;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  99 */       PrivilegedExceptionAction<Unsafe> action = new PrivilegedExceptionAction<Unsafe>()
/*     */         {
/*     */           public Unsafe run() throws Exception
/*     */           {
/* 103 */             Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
/* 104 */             theUnsafe.setAccessible(true);
/* 105 */             return (Unsafe)theUnsafe.get(null);
/*     */           }
/*     */         };
/*     */       
/* 109 */       THE_UNSAFE = AccessController.<Unsafe>doPrivileged(action);
/*     */     }
/* 111 */     catch (Exception e) {
/*     */       
/* 113 */       throw new RuntimeException("Unable to load unsafe", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Unsafe getUnsafe() {
/* 125 */     return THE_UNSAFE;
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
/*     */   public static int log2(int i) {
/* 137 */     int r = 0;
/* 138 */     while ((i >>= 1) != 0)
/*     */     {
/* 140 */       r++;
/*     */     }
/* 142 */     return r;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disrupto\\util\Util.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */