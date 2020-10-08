/*     */ package com.lmax.disruptor;
/*     */ 
/*     */ import com.lmax.disruptor.util.Util;
/*     */ import java.util.Arrays;
/*     */ import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSequencer
/*     */   implements Sequencer
/*     */ {
/*  31 */   private static final AtomicReferenceFieldUpdater<AbstractSequencer, Sequence[]> SEQUENCE_UPDATER = (AtomicReferenceFieldUpdater)AtomicReferenceFieldUpdater.newUpdater(AbstractSequencer.class, (Class)Sequence[].class, "gatingSequences");
/*     */   
/*     */   protected final int bufferSize;
/*     */   protected final WaitStrategy waitStrategy;
/*  35 */   protected final Sequence cursor = new Sequence(-1L);
/*  36 */   protected volatile Sequence[] gatingSequences = new Sequence[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSequencer(int bufferSize, WaitStrategy waitStrategy) {
/*  46 */     if (bufferSize < 1)
/*     */     {
/*  48 */       throw new IllegalArgumentException("bufferSize must not be less than 1");
/*     */     }
/*  50 */     if (Integer.bitCount(bufferSize) != 1)
/*     */     {
/*  52 */       throw new IllegalArgumentException("bufferSize must be a power of 2");
/*     */     }
/*     */     
/*  55 */     this.bufferSize = bufferSize;
/*  56 */     this.waitStrategy = waitStrategy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final long getCursor() {
/*  65 */     return this.cursor.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getBufferSize() {
/*  74 */     return this.bufferSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void addGatingSequences(Sequence... gatingSequences) {
/*  83 */     SequenceGroups.addSequences(this, SEQUENCE_UPDATER, this, gatingSequences);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeGatingSequence(Sequence sequence) {
/*  92 */     return SequenceGroups.removeSequence(this, SEQUENCE_UPDATER, sequence);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMinimumSequence() {
/* 101 */     return Util.getMinimumSequence(this.gatingSequences, this.cursor.get());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SequenceBarrier newBarrier(Sequence... sequencesToTrack) {
/* 110 */     return new ProcessingSequenceBarrier(this, this.waitStrategy, this.cursor, sequencesToTrack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> EventPoller<T> newPoller(DataProvider<T> dataProvider, Sequence... gatingSequences) {
/* 124 */     return EventPoller.newInstance(dataProvider, this, new Sequence(), this.cursor, gatingSequences);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 130 */     return "AbstractSequencer{waitStrategy=" + this.waitStrategy + ", cursor=" + this.cursor + ", gatingSequences=" + 
/*     */ 
/*     */       
/* 133 */       Arrays.toString((Object[])this.gatingSequences) + '}';
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\AbstractSequencer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */