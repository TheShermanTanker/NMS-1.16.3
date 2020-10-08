/*    */ package com.lmax.disruptor;
/*    */ 
/*    */ import com.lmax.disruptor.util.ThreadHints;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
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
/*    */ 
/*    */ public final class LiteBlockingWaitStrategy
/*    */   implements WaitStrategy
/*    */ {
/* 33 */   private final Lock lock = new ReentrantLock();
/* 34 */   private final Condition processorNotifyCondition = this.lock.newCondition();
/* 35 */   private final AtomicBoolean signalNeeded = new AtomicBoolean(false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long waitFor(long sequence, Sequence cursorSequence, Sequence dependentSequence, SequenceBarrier barrier) throws AlertException, InterruptedException {
/* 42 */     if (cursorSequence.get() < sequence) {
/*    */       
/* 44 */       this.lock.lock();
/*    */ 
/*    */ 
/*    */       
/*    */       try {
/*    */         do {
/* 50 */           this.signalNeeded.getAndSet(true);
/*    */           
/* 52 */           if (cursorSequence.get() >= sequence) {
/*    */             break;
/*    */           }
/*    */ 
/*    */           
/* 57 */           barrier.checkAlert();
/* 58 */           this.processorNotifyCondition.await();
/*    */         }
/* 60 */         while (cursorSequence.get() < sequence);
/*    */       }
/*    */       finally {
/*    */         
/* 64 */         this.lock.unlock();
/*    */       } 
/*    */     } 
/*    */     long availableSequence;
/* 68 */     while ((availableSequence = dependentSequence.get()) < sequence) {
/*    */       
/* 70 */       barrier.checkAlert();
/* 71 */       ThreadHints.onSpinWait();
/*    */     } 
/*    */     
/* 74 */     return availableSequence;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void signalAllWhenBlocking() {
/* 80 */     if (this.signalNeeded.getAndSet(false)) {
/*    */       
/* 82 */       this.lock.lock();
/*    */       
/*    */       try {
/* 85 */         this.processorNotifyCondition.signalAll();
/*    */       }
/*    */       finally {
/*    */         
/* 89 */         this.lock.unlock();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 97 */     return "LiteBlockingWaitStrategy{processorNotifyCondition=" + this.processorNotifyCondition + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\LiteBlockingWaitStrategy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */