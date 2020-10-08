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
/*    */ final class ProcessingSequenceBarrier
/*    */   implements SequenceBarrier
/*    */ {
/*    */   private final WaitStrategy waitStrategy;
/*    */   private final Sequence dependentSequence;
/*    */   private volatile boolean alerted = false;
/*    */   private final Sequence cursorSequence;
/*    */   private final Sequencer sequencer;
/*    */   
/*    */   ProcessingSequenceBarrier(Sequencer sequencer, WaitStrategy waitStrategy, Sequence cursorSequence, Sequence[] dependentSequences) {
/* 37 */     this.sequencer = sequencer;
/* 38 */     this.waitStrategy = waitStrategy;
/* 39 */     this.cursorSequence = cursorSequence;
/* 40 */     if (0 == dependentSequences.length) {
/*    */       
/* 42 */       this.dependentSequence = cursorSequence;
/*    */     }
/*    */     else {
/*    */       
/* 46 */       this.dependentSequence = new FixedSequenceGroup(dependentSequences);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long waitFor(long sequence) throws AlertException, InterruptedException, TimeoutException {
/* 54 */     checkAlert();
/*    */     
/* 56 */     long availableSequence = this.waitStrategy.waitFor(sequence, this.cursorSequence, this.dependentSequence, this);
/*    */     
/* 58 */     if (availableSequence < sequence)
/*    */     {
/* 60 */       return availableSequence;
/*    */     }
/*    */     
/* 63 */     return this.sequencer.getHighestPublishedSequence(sequence, availableSequence);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getCursor() {
/* 69 */     return this.dependentSequence.get();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAlerted() {
/* 75 */     return this.alerted;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void alert() {
/* 81 */     this.alerted = true;
/* 82 */     this.waitStrategy.signalAllWhenBlocking();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void clearAlert() {
/* 88 */     this.alerted = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void checkAlert() throws AlertException {
/* 94 */     if (this.alerted)
/*    */     {
/* 96 */       throw AlertException.INSTANCE;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\ProcessingSequenceBarrier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */