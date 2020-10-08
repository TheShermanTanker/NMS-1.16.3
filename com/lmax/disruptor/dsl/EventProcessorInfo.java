/*    */ package com.lmax.disruptor.dsl;
/*    */ 
/*    */ import com.lmax.disruptor.EventHandler;
/*    */ import com.lmax.disruptor.EventProcessor;
/*    */ import com.lmax.disruptor.Sequence;
/*    */ import com.lmax.disruptor.SequenceBarrier;
/*    */ import java.util.concurrent.Executor;
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
/*    */ class EventProcessorInfo<T>
/*    */   implements ConsumerInfo
/*    */ {
/*    */   private final EventProcessor eventprocessor;
/*    */   private final EventHandler<? super T> handler;
/*    */   private final SequenceBarrier barrier;
/*    */   private boolean endOfChain = true;
/*    */   
/*    */   EventProcessorInfo(EventProcessor eventprocessor, EventHandler<? super T> handler, SequenceBarrier barrier) {
/* 42 */     this.eventprocessor = eventprocessor;
/* 43 */     this.handler = handler;
/* 44 */     this.barrier = barrier;
/*    */   }
/*    */ 
/*    */   
/*    */   public EventProcessor getEventProcessor() {
/* 49 */     return this.eventprocessor;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Sequence[] getSequences() {
/* 55 */     return new Sequence[] { this.eventprocessor.getSequence() };
/*    */   }
/*    */ 
/*    */   
/*    */   public EventHandler<? super T> getHandler() {
/* 60 */     return this.handler;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SequenceBarrier getBarrier() {
/* 66 */     return this.barrier;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEndOfChain() {
/* 72 */     return this.endOfChain;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void start(Executor executor) {
/* 78 */     executor.execute((Runnable)this.eventprocessor);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void halt() {
/* 84 */     this.eventprocessor.halt();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void markAsUsedInBarrier() {
/* 93 */     this.endOfChain = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRunning() {
/* 99 */     return this.eventprocessor.isRunning();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\dsl\EventProcessorInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */