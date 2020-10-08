/*    */ package com.lmax.disruptor;
/*    */ 
/*    */ import com.lmax.disruptor.util.ThreadHints;
/*    */ import java.util.concurrent.locks.Condition;
/*    */ import java.util.concurrent.locks.Lock;
/*    */ import java.util.concurrent.locks.ReentrantLock;
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
/*    */ public final class BlockingWaitStrategy
/*    */   implements WaitStrategy
/*    */ {
/* 31 */   private final Lock lock = new ReentrantLock();
/* 32 */   private final Condition processorNotifyCondition = this.lock.newCondition();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long waitFor(long sequence, Sequence cursorSequence, Sequence dependentSequence, SequenceBarrier barrier) throws AlertException, InterruptedException {
/* 39 */     if (cursorSequence.get() < sequence) {
/*    */       
/* 41 */       this.lock.lock();
/*    */       
/*    */       try {
/* 44 */         while (cursorSequence.get() < sequence)
/*    */         {
/* 46 */           barrier.checkAlert();
/* 47 */           this.processorNotifyCondition.await();
/*    */         }
/*    */       
/*    */       } finally {
/*    */         
/* 52 */         this.lock.unlock();
/*    */       } 
/*    */     } 
/*    */     long availableSequence;
/* 56 */     while ((availableSequence = dependentSequence.get()) < sequence) {
/*    */       
/* 58 */       barrier.checkAlert();
/* 59 */       ThreadHints.onSpinWait();
/*    */     } 
/*    */     
/* 62 */     return availableSequence;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void signalAllWhenBlocking() {
/* 68 */     this.lock.lock();
/*    */     
/*    */     try {
/* 71 */       this.processorNotifyCondition.signalAll();
/*    */     }
/*    */     finally {
/*    */       
/* 75 */       this.lock.unlock();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     return "BlockingWaitStrategy{processorNotifyCondition=" + this.processorNotifyCondition + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\BlockingWaitStrategy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */