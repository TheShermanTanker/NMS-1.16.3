/*     */ package com.lmax.disruptor;
/*     */ 
/*     */ import com.lmax.disruptor.util.Util;
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
/*     */ 
/*     */ 
/*     */ public final class SequenceGroup
/*     */   extends Sequence
/*     */ {
/*  32 */   private static final AtomicReferenceFieldUpdater<SequenceGroup, Sequence[]> SEQUENCE_UPDATER = (AtomicReferenceFieldUpdater)AtomicReferenceFieldUpdater.newUpdater(SequenceGroup.class, (Class)Sequence[].class, "sequences");
/*  33 */   private volatile Sequence[] sequences = new Sequence[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SequenceGroup() {
/*  40 */     super(-1L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long get() {
/*  51 */     return Util.getMinimumSequence(this.sequences);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(long value) {
/*  62 */     Sequence[] sequences = this.sequences;
/*  63 */     for (Sequence sequence : sequences)
/*     */     {
/*  65 */       sequence.set(value);
/*     */     }
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
/*     */   public void add(Sequence sequence) {
/*     */     Sequence[] oldSequences;
/*     */     Sequence[] newSequences;
/*     */     do {
/*  82 */       oldSequences = this.sequences;
/*  83 */       int oldSize = oldSequences.length;
/*  84 */       newSequences = new Sequence[oldSize + 1];
/*  85 */       System.arraycopy(oldSequences, 0, newSequences, 0, oldSize);
/*  86 */       newSequences[oldSize] = sequence;
/*     */     }
/*  88 */     while (!SEQUENCE_UPDATER.compareAndSet(this, oldSequences, newSequences));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Sequence sequence) {
/*  99 */     return SequenceGroups.removeSequence(this, SEQUENCE_UPDATER, sequence);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 109 */     return this.sequences.length;
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
/*     */   public void addWhileRunning(Cursored cursored, Sequence sequence) {
/* 123 */     SequenceGroups.addSequences(this, SEQUENCE_UPDATER, cursored, new Sequence[] { sequence });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\SequenceGroup.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */