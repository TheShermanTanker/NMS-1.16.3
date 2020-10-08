/*     */ package com.lmax.disruptor;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SequenceGroups
/*     */ {
/*     */   static <T> void addSequences(T holder, AtomicReferenceFieldUpdater<T, Sequence[]> updater, Cursored cursor, Sequence... sequencesToAdd) {
/*     */     Sequence[] updatedSequences, currentSequences;
/*     */     do {
/*  39 */       currentSequences = updater.get(holder);
/*  40 */       updatedSequences = Arrays.<Sequence>copyOf(currentSequences, currentSequences.length + sequencesToAdd.length);
/*  41 */       long cursorSequence = cursor.getCursor();
/*     */       
/*  43 */       int index = currentSequences.length;
/*  44 */       for (Sequence sequence : sequencesToAdd)
/*     */       {
/*  46 */         sequence.set(cursorSequence);
/*  47 */         updatedSequences[index++] = sequence;
/*     */       }
/*     */     
/*  50 */     } while (!updater.compareAndSet(holder, currentSequences, updatedSequences));
/*     */     
/*  52 */     long l = cursor.getCursor();
/*  53 */     for (Sequence sequence : sequencesToAdd)
/*     */     {
/*  55 */       sequence.set(l);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static <T> boolean removeSequence(T holder, AtomicReferenceFieldUpdater<T, Sequence[]> sequenceUpdater, Sequence sequence) {
/*     */     int numToRemove;
/*     */     Sequence[] oldSequences;
/*     */     Sequence[] newSequences;
/*     */     do {
/*  70 */       oldSequences = sequenceUpdater.get(holder);
/*     */       
/*  72 */       numToRemove = countMatching(oldSequences, sequence);
/*     */       
/*  74 */       if (0 == numToRemove) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/*  79 */       int oldSize = oldSequences.length;
/*  80 */       newSequences = new Sequence[oldSize - numToRemove];
/*     */       
/*  82 */       for (int i = 0, pos = 0; i < oldSize; i++)
/*     */       {
/*  84 */         Sequence testSequence = oldSequences[i];
/*  85 */         if (sequence != testSequence)
/*     */         {
/*  87 */           newSequences[pos++] = testSequence;
/*     */         }
/*     */       }
/*     */     
/*  91 */     } while (!sequenceUpdater.compareAndSet(holder, oldSequences, newSequences));
/*     */     
/*  93 */     return (numToRemove != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T> int countMatching(T[] values, T toMatch) {
/*  98 */     int numToRemove = 0;
/*  99 */     for (T value : values) {
/*     */       
/* 101 */       if (value == toMatch)
/*     */       {
/* 103 */         numToRemove++;
/*     */       }
/*     */     } 
/* 106 */     return numToRemove;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\SequenceGroups.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */