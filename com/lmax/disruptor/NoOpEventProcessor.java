/*    */ package com.lmax.disruptor;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
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
/*    */ public final class NoOpEventProcessor
/*    */   implements EventProcessor
/*    */ {
/*    */   private final SequencerFollowingSequence sequence;
/* 28 */   private final AtomicBoolean running = new AtomicBoolean(false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NoOpEventProcessor(RingBuffer<?> sequencer) {
/* 37 */     this.sequence = new SequencerFollowingSequence(sequencer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Sequence getSequence() {
/* 43 */     return this.sequence;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void halt() {
/* 49 */     this.running.set(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRunning() {
/* 55 */     return this.running.get();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 61 */     if (!this.running.compareAndSet(false, true))
/*    */     {
/* 63 */       throw new IllegalStateException("Thread is already running");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static final class SequencerFollowingSequence
/*    */     extends Sequence
/*    */   {
/*    */     private final RingBuffer<?> sequencer;
/*    */ 
/*    */     
/*    */     private SequencerFollowingSequence(RingBuffer<?> sequencer) {
/* 76 */       super(-1L);
/* 77 */       this.sequencer = sequencer;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public long get() {
/* 83 */       return this.sequencer.getCursor();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\NoOpEventProcessor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */