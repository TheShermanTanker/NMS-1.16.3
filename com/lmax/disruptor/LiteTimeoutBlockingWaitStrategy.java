/*    */ package com.lmax.disruptor;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
/*    */ import java.util.concurrent.locks.Condition;
/*    */ import java.util.concurrent.locks.Lock;
/*    */ import java.util.concurrent.locks.ReentrantLock;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LiteTimeoutBlockingWaitStrategy
/*    */   implements WaitStrategy
/*    */ {
/* 15 */   private final Lock lock = new ReentrantLock();
/* 16 */   private final Condition processorNotifyCondition = this.lock.newCondition();
/* 17 */   private final AtomicBoolean signalNeeded = new AtomicBoolean(false);
/*    */   
/*    */   private final long timeoutInNanos;
/*    */   
/*    */   public LiteTimeoutBlockingWaitStrategy(long timeout, TimeUnit units) {
/* 22 */     this.timeoutInNanos = units.toNanos(timeout);
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
/* 33 */     long nanos = this.timeoutInNanos;
/*    */ 
/*    */     
/* 36 */     if (cursorSequence.get() < sequence) {
/*    */       
/* 38 */       this.lock.lock();
/*    */       
/*    */       try {
/* 41 */         while (cursorSequence.get() < sequence)
/*    */         {
/* 43 */           this.signalNeeded.getAndSet(true);
/*    */           
/* 45 */           barrier.checkAlert();
/* 46 */           nanos = this.processorNotifyCondition.awaitNanos(nanos);
/* 47 */           if (nanos <= 0L)
/*    */           {
/* 49 */             throw TimeoutException.INSTANCE;
/*    */           }
/*    */         }
/*    */       
/*    */       } finally {
/*    */         
/* 55 */         this.lock.unlock();
/*    */       } 
/*    */     } 
/*    */     long availableSequence;
/* 59 */     while ((availableSequence = dependentSequence.get()) < sequence)
/*    */     {
/* 61 */       barrier.checkAlert();
/*    */     }
/*    */     
/* 64 */     return availableSequence;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void signalAllWhenBlocking() {
/* 70 */     if (this.signalNeeded.getAndSet(false)) {
/*    */       
/* 72 */       this.lock.lock();
/*    */       
/*    */       try {
/* 75 */         this.processorNotifyCondition.signalAll();
/*    */       }
/*    */       finally {
/*    */         
/* 79 */         this.lock.unlock();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 87 */     return "LiteTimeoutBlockingWaitStrategy{processorNotifyCondition=" + this.processorNotifyCondition + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\LiteTimeoutBlockingWaitStrategy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */