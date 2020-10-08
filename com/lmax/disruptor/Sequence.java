/*     */ package com.lmax.disruptor;
/*     */ 
/*     */ import com.lmax.disruptor.util.Util;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Sequence
/*     */   extends RhsPadding
/*     */ {
/*     */   static final long INITIAL_VALUE = -1L;
/*  54 */   private static final Unsafe UNSAFE = Util.getUnsafe();
/*     */   static {
/*     */     try {
/*  57 */       VALUE_OFFSET = UNSAFE.objectFieldOffset(Value.class.getDeclaredField("value"));
/*     */     }
/*  59 */     catch (Exception e) {
/*     */       
/*  61 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static final long VALUE_OFFSET;
/*     */ 
/*     */   
/*     */   public Sequence() {
/*  70 */     this(-1L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Sequence(long initialValue) {
/*  80 */     UNSAFE.putOrderedLong(this, VALUE_OFFSET, initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long get() {
/*  90 */     return this.value;
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
/*     */   public void set(long value) {
/* 102 */     UNSAFE.putOrderedLong(this, VALUE_OFFSET, value);
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
/*     */   public void setVolatile(long value) {
/* 115 */     UNSAFE.putLongVolatile(this, VALUE_OFFSET, value);
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
/*     */   public boolean compareAndSet(long expectedValue, long newValue) {
/* 127 */     return UNSAFE.compareAndSwapLong(this, VALUE_OFFSET, expectedValue, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long incrementAndGet() {
/* 137 */     return addAndGet(1L);
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
/*     */   public long addAndGet(long increment) {
/*     */     long currentValue;
/*     */     long newValue;
/*     */     do {
/* 153 */       currentValue = get();
/* 154 */       newValue = currentValue + increment;
/*     */     }
/* 156 */     while (!compareAndSet(currentValue, newValue));
/*     */     
/* 158 */     return newValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 164 */     return Long.toString(get());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\Sequence.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */