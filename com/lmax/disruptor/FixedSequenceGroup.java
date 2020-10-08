/*    */ package com.lmax.disruptor;
/*    */ 
/*    */ import com.lmax.disruptor.util.Util;
/*    */ import java.util.Arrays;
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
/*    */ public final class FixedSequenceGroup
/*    */   extends Sequence
/*    */ {
/*    */   private final Sequence[] sequences;
/*    */   
/*    */   public FixedSequenceGroup(Sequence[] sequences) {
/* 36 */     this.sequences = Arrays.<Sequence>copyOf(sequences, sequences.length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long get() {
/* 47 */     return Util.getMinimumSequence(this.sequences);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     return Arrays.toString((Object[])this.sequences);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void set(long value) {
/* 62 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean compareAndSet(long expectedValue, long newValue) {
/* 71 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long incrementAndGet() {
/* 80 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long addAndGet(long increment) {
/* 89 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\FixedSequenceGroup.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */