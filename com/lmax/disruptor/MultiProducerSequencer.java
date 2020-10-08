/*     */ package com.lmax.disruptor;
/*     */ 
/*     */ import com.lmax.disruptor.util.Util;
/*     */ import java.util.concurrent.locks.LockSupport;
/*     */ import sun.misc.Unsafe;
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
/*     */ public final class MultiProducerSequencer
/*     */   extends AbstractSequencer
/*     */ {
/*  35 */   private static final Unsafe UNSAFE = Util.getUnsafe();
/*  36 */   private static final long BASE = UNSAFE.arrayBaseOffset(int[].class);
/*  37 */   private static final long SCALE = UNSAFE.arrayIndexScale(int[].class);
/*     */   
/*  39 */   private final Sequence gatingSequenceCache = new Sequence(-1L);
/*     */ 
/*     */ 
/*     */   
/*     */   private final int[] availableBuffer;
/*     */ 
/*     */ 
/*     */   
/*     */   private final int indexMask;
/*     */ 
/*     */   
/*     */   private final int indexShift;
/*     */ 
/*     */ 
/*     */   
/*     */   public MultiProducerSequencer(int bufferSize, WaitStrategy waitStrategy) {
/*  55 */     super(bufferSize, waitStrategy);
/*  56 */     this.availableBuffer = new int[bufferSize];
/*  57 */     this.indexMask = bufferSize - 1;
/*  58 */     this.indexShift = Util.log2(bufferSize);
/*  59 */     initialiseAvailableBuffer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAvailableCapacity(int requiredCapacity) {
/*  68 */     return hasAvailableCapacity(this.gatingSequences, requiredCapacity, this.cursor.get());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasAvailableCapacity(Sequence[] gatingSequences, int requiredCapacity, long cursorValue) {
/*  73 */     long wrapPoint = cursorValue + requiredCapacity - this.bufferSize;
/*  74 */     long cachedGatingSequence = this.gatingSequenceCache.get();
/*     */     
/*  76 */     if (wrapPoint > cachedGatingSequence || cachedGatingSequence > cursorValue) {
/*     */       
/*  78 */       long minSequence = Util.getMinimumSequence(gatingSequences, cursorValue);
/*  79 */       this.gatingSequenceCache.set(minSequence);
/*     */       
/*  81 */       if (wrapPoint > minSequence)
/*     */       {
/*  83 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void claim(long sequence) {
/*  96 */     this.cursor.set(sequence);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long next() {
/* 105 */     return next(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long next(int n) {
/*     */     long next;
/* 114 */     if (n < 1)
/*     */     {
/* 116 */       throw new IllegalArgumentException("n must be > 0");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/* 124 */       long current = this.cursor.get();
/* 125 */       next = current + n;
/*     */       
/* 127 */       long wrapPoint = next - this.bufferSize;
/* 128 */       long cachedGatingSequence = this.gatingSequenceCache.get();
/*     */       
/* 130 */       if (wrapPoint > cachedGatingSequence || cachedGatingSequence > current) {
/*     */         
/* 132 */         long gatingSequence = Util.getMinimumSequence(this.gatingSequences, current);
/*     */         
/* 134 */         if (wrapPoint > gatingSequence) {
/*     */           
/* 136 */           LockSupport.parkNanos(1L);
/*     */           
/*     */           continue;
/*     */         } 
/* 140 */         this.gatingSequenceCache.set(gatingSequence); continue;
/*     */       } 
/* 142 */       if (this.cursor.compareAndSet(current, next)) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 149 */     return next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long tryNext() throws InsufficientCapacityException {
/* 158 */     return tryNext(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long tryNext(int n) throws InsufficientCapacityException {
/*     */     long current;
/*     */     long next;
/* 167 */     if (n < 1)
/*     */     {
/* 169 */       throw new IllegalArgumentException("n must be > 0");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 177 */       current = this.cursor.get();
/* 178 */       next = current + n;
/*     */       
/* 180 */       if (!hasAvailableCapacity(this.gatingSequences, n, current))
/*     */       {
/* 182 */         throw InsufficientCapacityException.INSTANCE;
/*     */       }
/*     */     }
/* 185 */     while (!this.cursor.compareAndSet(current, next));
/*     */     
/* 187 */     return next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long remainingCapacity() {
/* 196 */     long consumed = Util.getMinimumSequence(this.gatingSequences, this.cursor.get());
/* 197 */     long produced = this.cursor.get();
/* 198 */     return getBufferSize() - produced - consumed;
/*     */   }
/*     */ 
/*     */   
/*     */   private void initialiseAvailableBuffer() {
/* 203 */     for (int i = this.availableBuffer.length - 1; i != 0; i--)
/*     */     {
/* 205 */       setAvailableBufferValue(i, -1);
/*     */     }
/*     */     
/* 208 */     setAvailableBufferValue(0, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void publish(long sequence) {
/* 217 */     setAvailable(sequence);
/* 218 */     this.waitStrategy.signalAllWhenBlocking();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void publish(long lo, long hi) {
/*     */     long l;
/* 227 */     for (l = lo; l <= hi; l++)
/*     */     {
/* 229 */       setAvailable(l);
/*     */     }
/* 231 */     this.waitStrategy.signalAllWhenBlocking();
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
/*     */   private void setAvailable(long sequence) {
/* 255 */     setAvailableBufferValue(calculateIndex(sequence), calculateAvailabilityFlag(sequence));
/*     */   }
/*     */ 
/*     */   
/*     */   private void setAvailableBufferValue(int index, int flag) {
/* 260 */     long bufferAddress = index * SCALE + BASE;
/* 261 */     UNSAFE.putOrderedInt(this.availableBuffer, bufferAddress, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAvailable(long sequence) {
/* 270 */     int index = calculateIndex(sequence);
/* 271 */     int flag = calculateAvailabilityFlag(sequence);
/* 272 */     long bufferAddress = index * SCALE + BASE;
/* 273 */     return (UNSAFE.getIntVolatile(this.availableBuffer, bufferAddress) == flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getHighestPublishedSequence(long lowerBound, long availableSequence) {
/*     */     long sequence;
/* 279 */     for (sequence = lowerBound; sequence <= availableSequence; sequence++) {
/*     */       
/* 281 */       if (!isAvailable(sequence))
/*     */       {
/* 283 */         return sequence - 1L;
/*     */       }
/*     */     } 
/*     */     
/* 287 */     return availableSequence;
/*     */   }
/*     */ 
/*     */   
/*     */   private int calculateAvailabilityFlag(long sequence) {
/* 292 */     return (int)(sequence >>> this.indexShift);
/*     */   }
/*     */ 
/*     */   
/*     */   private int calculateIndex(long sequence) {
/* 297 */     return (int)sequence & this.indexMask;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\MultiProducerSequencer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */