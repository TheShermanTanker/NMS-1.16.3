/*     */ package com.lmax.disruptor;
/*     */ 
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PhasedBackoffWaitStrategy
/*     */   implements WaitStrategy
/*     */ {
/*     */   private static final int SPIN_TRIES = 10000;
/*     */   private final long spinTimeoutNanos;
/*     */   private final long yieldTimeoutNanos;
/*     */   private final WaitStrategy fallbackStrategy;
/*     */   
/*     */   public PhasedBackoffWaitStrategy(long spinTimeout, long yieldTimeout, TimeUnit units, WaitStrategy fallbackStrategy) {
/*  39 */     this.spinTimeoutNanos = units.toNanos(spinTimeout);
/*  40 */     this.yieldTimeoutNanos = this.spinTimeoutNanos + units.toNanos(yieldTimeout);
/*  41 */     this.fallbackStrategy = fallbackStrategy;
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
/*     */   public static PhasedBackoffWaitStrategy withLock(long spinTimeout, long yieldTimeout, TimeUnit units) {
/*  57 */     return new PhasedBackoffWaitStrategy(spinTimeout, yieldTimeout, units, new BlockingWaitStrategy());
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
/*     */   public static PhasedBackoffWaitStrategy withLiteLock(long spinTimeout, long yieldTimeout, TimeUnit units) {
/*  75 */     return new PhasedBackoffWaitStrategy(spinTimeout, yieldTimeout, units, new LiteBlockingWaitStrategy());
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
/*     */   public static PhasedBackoffWaitStrategy withSleep(long spinTimeout, long yieldTimeout, TimeUnit units) {
/*  93 */     return new PhasedBackoffWaitStrategy(spinTimeout, yieldTimeout, units, new SleepingWaitStrategy(0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long waitFor(long sequence, Sequence cursor, Sequence dependentSequence, SequenceBarrier barrier) throws AlertException, InterruptedException, TimeoutException {
/* 103 */     long startTime = 0L;
/* 104 */     int counter = 10000;
/*     */     
/*     */     while (true) {
/*     */       long availableSequence;
/* 108 */       if ((availableSequence = dependentSequence.get()) >= sequence)
/*     */       {
/* 110 */         return availableSequence;
/*     */       }
/*     */       
/* 113 */       if (0 == --counter) {
/*     */         
/* 115 */         if (0L == startTime) {
/*     */           
/* 117 */           startTime = System.nanoTime();
/*     */         }
/*     */         else {
/*     */           
/* 121 */           long timeDelta = System.nanoTime() - startTime;
/* 122 */           if (timeDelta > this.yieldTimeoutNanos)
/*     */           {
/* 124 */             return this.fallbackStrategy.waitFor(sequence, cursor, dependentSequence, barrier);
/*     */           }
/* 126 */           if (timeDelta > this.spinTimeoutNanos)
/*     */           {
/* 128 */             Thread.yield();
/*     */           }
/*     */         } 
/* 131 */         counter = 10000;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void signalAllWhenBlocking() {
/* 140 */     this.fallbackStrategy.signalAllWhenBlocking();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\PhasedBackoffWaitStrategy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */