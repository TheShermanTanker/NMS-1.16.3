/*    */ package com.lmax.disruptor;
/*    */ 
/*    */ import java.util.concurrent.locks.LockSupport;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class SleepingWaitStrategy
/*    */   implements WaitStrategy
/*    */ {
/*    */   private static final int DEFAULT_RETRIES = 200;
/*    */   private static final long DEFAULT_SLEEP = 100L;
/*    */   private final int retries;
/*    */   private final long sleepTimeNs;
/*    */   
/*    */   public SleepingWaitStrategy() {
/* 41 */     this(200, 100L);
/*    */   }
/*    */ 
/*    */   
/*    */   public SleepingWaitStrategy(int retries) {
/* 46 */     this(retries, 100L);
/*    */   }
/*    */ 
/*    */   
/*    */   public SleepingWaitStrategy(int retries, long sleepTimeNs) {
/* 51 */     this.retries = retries;
/* 52 */     this.sleepTimeNs = sleepTimeNs;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long waitFor(long sequence, Sequence cursor, Sequence dependentSequence, SequenceBarrier barrier) throws AlertException {
/* 61 */     int counter = this.retries;
/*    */     long availableSequence;
/* 63 */     while ((availableSequence = dependentSequence.get()) < sequence)
/*    */     {
/* 65 */       counter = applyWaitMethod(barrier, counter);
/*    */     }
/*    */     
/* 68 */     return availableSequence;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void signalAllWhenBlocking() {}
/*    */ 
/*    */ 
/*    */   
/*    */   private int applyWaitMethod(SequenceBarrier barrier, int counter) throws AlertException {
/* 79 */     barrier.checkAlert();
/*    */     
/* 81 */     if (counter > 100) {
/*    */       
/* 83 */       counter--;
/*    */     }
/* 85 */     else if (counter > 0) {
/*    */       
/* 87 */       counter--;
/* 88 */       Thread.yield();
/*    */     }
/*    */     else {
/*    */       
/* 92 */       LockSupport.parkNanos(this.sleepTimeNs);
/*    */     } 
/*    */     
/* 95 */     return counter;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\SleepingWaitStrategy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */