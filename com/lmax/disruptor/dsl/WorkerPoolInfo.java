/*    */ package com.lmax.disruptor.dsl;
/*    */ 
/*    */ import com.lmax.disruptor.Sequence;
/*    */ import com.lmax.disruptor.SequenceBarrier;
/*    */ import com.lmax.disruptor.WorkerPool;
/*    */ import java.util.concurrent.Executor;
/*    */ 
/*    */ class WorkerPoolInfo<T>
/*    */   implements ConsumerInfo {
/*    */   private final WorkerPool<T> workerPool;
/*    */   private final SequenceBarrier sequenceBarrier;
/*    */   private boolean endOfChain = true;
/*    */   
/*    */   WorkerPoolInfo(WorkerPool<T> workerPool, SequenceBarrier sequenceBarrier) {
/* 15 */     this.workerPool = workerPool;
/* 16 */     this.sequenceBarrier = sequenceBarrier;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Sequence[] getSequences() {
/* 22 */     return this.workerPool.getWorkerSequences();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SequenceBarrier getBarrier() {
/* 28 */     return this.sequenceBarrier;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEndOfChain() {
/* 34 */     return this.endOfChain;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void start(Executor executor) {
/* 40 */     this.workerPool.start(executor);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void halt() {
/* 46 */     this.workerPool.halt();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void markAsUsedInBarrier() {
/* 52 */     this.endOfChain = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRunning() {
/* 58 */     return this.workerPool.isRunning();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\dsl\WorkerPoolInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */