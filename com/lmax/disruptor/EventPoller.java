/*     */ package com.lmax.disruptor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EventPoller<T>
/*     */ {
/*     */   private final DataProvider<T> dataProvider;
/*     */   private final Sequencer sequencer;
/*     */   private final Sequence sequence;
/*     */   private final Sequence gatingSequence;
/*     */   
/*     */   public enum PollState
/*     */   {
/*  20 */     PROCESSING, GATING, IDLE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventPoller(DataProvider<T> dataProvider, Sequencer sequencer, Sequence sequence, Sequence gatingSequence) {
/*  29 */     this.dataProvider = dataProvider;
/*  30 */     this.sequencer = sequencer;
/*  31 */     this.sequence = sequence;
/*  32 */     this.gatingSequence = gatingSequence;
/*     */   }
/*     */ 
/*     */   
/*     */   public PollState poll(Handler<T> eventHandler) throws Exception {
/*  37 */     long currentSequence = this.sequence.get();
/*  38 */     long nextSequence = currentSequence + 1L;
/*  39 */     long availableSequence = this.sequencer.getHighestPublishedSequence(nextSequence, this.gatingSequence.get());
/*     */     
/*  41 */     if (nextSequence <= availableSequence) {
/*     */ 
/*     */       
/*  44 */       long processedSequence = currentSequence;
/*     */       
/*     */       try {
/*     */         boolean processNextEvent;
/*     */         
/*     */         do {
/*  50 */           T event = this.dataProvider.get(nextSequence);
/*  51 */           processNextEvent = eventHandler.onEvent(event, nextSequence, (nextSequence == availableSequence));
/*  52 */           processedSequence = nextSequence;
/*  53 */           nextSequence++;
/*     */         
/*     */         }
/*  56 */         while ((((nextSequence <= availableSequence) ? 1 : 0) & processNextEvent) != 0);
/*     */       }
/*     */       finally {
/*     */         
/*  60 */         this.sequence.set(processedSequence);
/*     */       } 
/*     */       
/*  63 */       return PollState.PROCESSING;
/*     */     } 
/*  65 */     if (this.sequencer.getCursor() >= nextSequence)
/*     */     {
/*  67 */       return PollState.GATING;
/*     */     }
/*     */ 
/*     */     
/*  71 */     return PollState.IDLE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> EventPoller<T> newInstance(DataProvider<T> dataProvider, Sequencer sequencer, Sequence sequence, Sequence cursorSequence, Sequence... gatingSequences) {
/*     */     Sequence gatingSequence;
/*  83 */     if (gatingSequences.length == 0) {
/*     */       
/*  85 */       gatingSequence = cursorSequence;
/*     */     }
/*  87 */     else if (gatingSequences.length == 1) {
/*     */       
/*  89 */       gatingSequence = gatingSequences[0];
/*     */     }
/*     */     else {
/*     */       
/*  93 */       gatingSequence = new FixedSequenceGroup(gatingSequences);
/*     */     } 
/*     */     
/*  96 */     return new EventPoller<>(dataProvider, sequencer, sequence, gatingSequence);
/*     */   }
/*     */ 
/*     */   
/*     */   public Sequence getSequence() {
/* 101 */     return this.sequence;
/*     */   }
/*     */   
/*     */   public static interface Handler<T> {
/*     */     boolean onEvent(T param1T, long param1Long, boolean param1Boolean) throws Exception;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\EventPoller.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */