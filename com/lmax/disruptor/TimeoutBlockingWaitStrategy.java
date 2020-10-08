/*    */ package com.lmax.disruptor;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.concurrent.locks.Condition;
/*    */ import java.util.concurrent.locks.Lock;
/*    */ import java.util.concurrent.locks.ReentrantLock;
/*    */ 
/*    */ public class TimeoutBlockingWaitStrategy
/*    */   implements WaitStrategy {
/* 10 */   private final Lock lock = new ReentrantLock();
/* 11 */   private final Condition processorNotifyCondition = this.lock.newCondition();
/*    */   
/*    */   private final long timeoutInNanos;
/*    */   
/*    */   public TimeoutBlockingWaitStrategy(long timeout, TimeUnit units) {
/* 16 */     this.timeoutInNanos = units.toNanos(timeout);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long waitFor(long sequence, Sequence cursorSequence, Sequence dependentSequence, SequenceBarrier barrier) throws AlertException, InterruptedException, TimeoutException {
/* 27 */     long nanos = this.timeoutInNanos;
/*    */ 
/*    */     
/* 30 */     if (cursorSequence.get() < sequence) {
/*    */       
/* 32 */       this.lock.lock();
/*    */       
/*    */       try {
/* 35 */         while (cursorSequence.get() < sequence)
/*    */         {
/* 37 */           barrier.checkAlert();
/* 38 */           nanos = this.processorNotifyCondition.awaitNanos(nanos);
/* 39 */           if (nanos <= 0L)
/*    */           {
/* 41 */             throw TimeoutException.INSTANCE;
/*    */           }
/*    */         }
/*    */       
/*    */       } finally {
/*    */         
/* 47 */         this.lock.unlock();
/*    */       } 
/*    */     } 
/*    */     long availableSequence;
/* 51 */     while ((availableSequence = dependentSequence.get()) < sequence)
/*    */     {
/* 53 */       barrier.checkAlert();
/*    */     }
/*    */     
/* 56 */     return availableSequence;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void signalAllWhenBlocking() {
/* 62 */     this.lock.lock();
/*    */     
/*    */     try {
/* 65 */       this.processorNotifyCondition.signalAll();
/*    */     }
/*    */     finally {
/*    */       
/* 69 */       this.lock.unlock();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     return "TimeoutBlockingWaitStrategy{processorNotifyCondition=" + this.processorNotifyCondition + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\TimeoutBlockingWaitStrategy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */