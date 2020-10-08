/*    */ package com.lmax.disruptor;
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
/*    */ public final class YieldingWaitStrategy
/*    */   implements WaitStrategy
/*    */ {
/*    */   private static final int SPIN_TRIES = 100;
/*    */   
/*    */   public long waitFor(long sequence, Sequence cursor, Sequence dependentSequence, SequenceBarrier barrier) throws AlertException, InterruptedException {
/* 36 */     int counter = 100;
/*    */     long availableSequence;
/* 38 */     while ((availableSequence = dependentSequence.get()) < sequence)
/*    */     {
/* 40 */       counter = applyWaitMethod(barrier, counter);
/*    */     }
/*    */     
/* 43 */     return availableSequence;
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
/* 54 */     barrier.checkAlert();
/*    */     
/* 56 */     if (0 == counter) {
/*    */       
/* 58 */       Thread.yield();
/*    */     }
/*    */     else {
/*    */       
/* 62 */       counter--;
/*    */     } 
/*    */     
/* 65 */     return counter;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\YieldingWaitStrategy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */