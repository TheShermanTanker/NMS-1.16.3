/*     */ package com.lmax.disruptor;
/*     */ 
/*     */ import com.lmax.disruptor.util.Util;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
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
/*     */ public final class WorkerPool<T>
/*     */ {
/*  31 */   private final AtomicBoolean started = new AtomicBoolean(false);
/*  32 */   private final Sequence workSequence = new Sequence(-1L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final RingBuffer<T> ringBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final WorkProcessor<?>[] workProcessors;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SafeVarargs
/*     */   public WorkerPool(RingBuffer<T> ringBuffer, SequenceBarrier sequenceBarrier, ExceptionHandler<? super T> exceptionHandler, WorkHandler<? super T>... workHandlers) {
/*  55 */     this.ringBuffer = ringBuffer;
/*  56 */     int numWorkers = workHandlers.length;
/*  57 */     this.workProcessors = (WorkProcessor<?>[])new WorkProcessor[numWorkers];
/*     */     
/*  59 */     for (int i = 0; i < numWorkers; i++)
/*     */     {
/*  61 */       this.workProcessors[i] = new WorkProcessor(ringBuffer, sequenceBarrier, workHandlers[i], exceptionHandler, this.workSequence);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SafeVarargs
/*     */   public WorkerPool(EventFactory<T> eventFactory, ExceptionHandler<? super T> exceptionHandler, WorkHandler<? super T>... workHandlers) {
/*  85 */     this.ringBuffer = RingBuffer.createMultiProducer(eventFactory, 1024, new BlockingWaitStrategy());
/*  86 */     SequenceBarrier barrier = this.ringBuffer.newBarrier(new Sequence[0]);
/*  87 */     int numWorkers = workHandlers.length;
/*  88 */     this.workProcessors = (WorkProcessor<?>[])new WorkProcessor[numWorkers];
/*     */     
/*  90 */     for (int i = 0; i < numWorkers; i++)
/*     */     {
/*  92 */       this.workProcessors[i] = new WorkProcessor(this.ringBuffer, barrier, workHandlers[i], exceptionHandler, this.workSequence);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     this.ringBuffer.addGatingSequences(getWorkerSequences());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Sequence[] getWorkerSequences() {
/* 110 */     Sequence[] sequences = new Sequence[this.workProcessors.length + 1];
/* 111 */     for (int i = 0, size = this.workProcessors.length; i < size; i++)
/*     */     {
/* 113 */       sequences[i] = this.workProcessors[i].getSequence();
/*     */     }
/* 115 */     sequences[sequences.length - 1] = this.workSequence;
/*     */     
/* 117 */     return sequences;
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
/*     */   public RingBuffer<T> start(Executor executor) {
/* 129 */     if (!this.started.compareAndSet(false, true))
/*     */     {
/* 131 */       throw new IllegalStateException("WorkerPool has already been started and cannot be restarted until halted.");
/*     */     }
/*     */     
/* 134 */     long cursor = this.ringBuffer.getCursor();
/* 135 */     this.workSequence.set(cursor);
/*     */     
/* 137 */     for (WorkProcessor<?> processor : this.workProcessors) {
/*     */       
/* 139 */       processor.getSequence().set(cursor);
/* 140 */       executor.execute(processor);
/*     */     } 
/*     */     
/* 143 */     return this.ringBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drainAndHalt() {
/* 151 */     Sequence[] workerSequences = getWorkerSequences();
/* 152 */     while (this.ringBuffer.getCursor() > Util.getMinimumSequence(workerSequences))
/*     */     {
/* 154 */       Thread.yield();
/*     */     }
/*     */     
/* 157 */     for (WorkProcessor<?> processor : this.workProcessors)
/*     */     {
/* 159 */       processor.halt();
/*     */     }
/*     */     
/* 162 */     this.started.set(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void halt() {
/* 170 */     for (WorkProcessor<?> processor : this.workProcessors)
/*     */     {
/* 172 */       processor.halt();
/*     */     }
/*     */     
/* 175 */     this.started.set(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRunning() {
/* 180 */     return this.started.get();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\WorkerPool.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */