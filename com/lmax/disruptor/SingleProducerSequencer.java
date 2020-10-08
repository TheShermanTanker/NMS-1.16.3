/*     */ package com.lmax.disruptor;
/*     */ 
/*     */ import com.lmax.disruptor.util.Util;
/*     */ import java.util.concurrent.locks.LockSupport;
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
/*     */ public final class SingleProducerSequencer
/*     */   extends SingleProducerSequencerFields
/*     */ {
/*     */   protected long p1;
/*     */   protected long p2;
/*     */   protected long p3;
/*     */   protected long p4;
/*     */   protected long p5;
/*     */   protected long p6;
/*     */   protected long p7;
/*     */   
/*     */   public SingleProducerSequencer(int bufferSize, WaitStrategy waitStrategy) {
/*  66 */     super(bufferSize, waitStrategy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAvailableCapacity(int requiredCapacity) {
/*  75 */     return hasAvailableCapacity(requiredCapacity, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasAvailableCapacity(int requiredCapacity, boolean doStore) {
/*  80 */     long nextValue = this.nextValue;
/*     */     
/*  82 */     long wrapPoint = nextValue + requiredCapacity - this.bufferSize;
/*  83 */     long cachedGatingSequence = this.cachedValue;
/*     */     
/*  85 */     if (wrapPoint > cachedGatingSequence || cachedGatingSequence > nextValue) {
/*     */       
/*  87 */       if (doStore)
/*     */       {
/*  89 */         this.cursor.setVolatile(nextValue);
/*     */       }
/*     */       
/*  92 */       long minSequence = Util.getMinimumSequence(this.gatingSequences, nextValue);
/*  93 */       this.cachedValue = minSequence;
/*     */       
/*  95 */       if (wrapPoint > minSequence)
/*     */       {
/*  97 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long next() {
/* 110 */     return next(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long next(int n) {
/* 119 */     if (n < 1)
/*     */     {
/* 121 */       throw new IllegalArgumentException("n must be > 0");
/*     */     }
/*     */     
/* 124 */     long nextValue = this.nextValue;
/*     */     
/* 126 */     long nextSequence = nextValue + n;
/* 127 */     long wrapPoint = nextSequence - this.bufferSize;
/* 128 */     long cachedGatingSequence = this.cachedValue;
/*     */     
/* 130 */     if (wrapPoint > cachedGatingSequence || cachedGatingSequence > nextValue) {
/*     */       
/* 132 */       this.cursor.setVolatile(nextValue);
/*     */       
/*     */       long minSequence;
/* 135 */       while (wrapPoint > (minSequence = Util.getMinimumSequence(this.gatingSequences, nextValue)))
/*     */       {
/* 137 */         LockSupport.parkNanos(1L);
/*     */       }
/*     */       
/* 140 */       this.cachedValue = minSequence;
/*     */     } 
/*     */     
/* 143 */     this.nextValue = nextSequence;
/*     */     
/* 145 */     return nextSequence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long tryNext() throws InsufficientCapacityException {
/* 154 */     return tryNext(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long tryNext(int n) throws InsufficientCapacityException {
/* 163 */     if (n < 1)
/*     */     {
/* 165 */       throw new IllegalArgumentException("n must be > 0");
/*     */     }
/*     */     
/* 168 */     if (!hasAvailableCapacity(n, true))
/*     */     {
/* 170 */       throw InsufficientCapacityException.INSTANCE;
/*     */     }
/*     */     
/* 173 */     long nextSequence = this.nextValue += n;
/*     */     
/* 175 */     return nextSequence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long remainingCapacity() {
/* 184 */     long nextValue = this.nextValue;
/*     */     
/* 186 */     long consumed = Util.getMinimumSequence(this.gatingSequences, nextValue);
/* 187 */     long produced = nextValue;
/* 188 */     return getBufferSize() - produced - consumed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void claim(long sequence) {
/* 197 */     this.nextValue = sequence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void publish(long sequence) {
/* 206 */     this.cursor.set(sequence);
/* 207 */     this.waitStrategy.signalAllWhenBlocking();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void publish(long lo, long hi) {
/* 216 */     publish(hi);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAvailable(long sequence) {
/* 225 */     return (sequence <= this.cursor.get());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getHighestPublishedSequence(long lowerBound, long availableSequence) {
/* 231 */     return availableSequence;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\SingleProducerSequencer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */