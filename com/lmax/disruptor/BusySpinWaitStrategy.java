/*    */ package com.lmax.disruptor;
/*    */ 
/*    */ import com.lmax.disruptor.util.ThreadHints;
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
/*    */ public final class BusySpinWaitStrategy
/*    */   implements WaitStrategy
/*    */ {
/*    */   public long waitFor(long sequence, Sequence cursor, Sequence dependentSequence, SequenceBarrier barrier) throws AlertException, InterruptedException {
/*    */     long availableSequence;
/* 36 */     while ((availableSequence = dependentSequence.get()) < sequence) {
/*    */       
/* 38 */       barrier.checkAlert();
/* 39 */       ThreadHints.onSpinWait();
/*    */     } 
/*    */     
/* 42 */     return availableSequence;
/*    */   }
/*    */   
/*    */   public void signalAllWhenBlocking() {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\BusySpinWaitStrategy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */