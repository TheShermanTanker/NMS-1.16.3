/*     */ package com.lmax.disruptor;
/*     */ 
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
/*     */ 
/*     */ public final class WorkProcessor<T>
/*     */   implements EventProcessor
/*     */ {
/*  31 */   private final AtomicBoolean running = new AtomicBoolean(false);
/*  32 */   private final Sequence sequence = new Sequence(-1L);
/*     */   private final RingBuffer<T> ringBuffer;
/*     */   private final SequenceBarrier sequenceBarrier;
/*     */   private final WorkHandler<? super T> workHandler;
/*     */   private final ExceptionHandler<? super T> exceptionHandler;
/*     */   private final Sequence workSequence;
/*     */   
/*  39 */   private final EventReleaser eventReleaser = new EventReleaser()
/*     */     {
/*     */       
/*     */       public void release()
/*     */       {
/*  44 */         WorkProcessor.this.sequence.set(Long.MAX_VALUE);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final TimeoutHandler timeoutHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorkProcessor(RingBuffer<T> ringBuffer, SequenceBarrier sequenceBarrier, WorkHandler<? super T> workHandler, ExceptionHandler<? super T> exceptionHandler, Sequence workSequence) {
/*  67 */     this.ringBuffer = ringBuffer;
/*  68 */     this.sequenceBarrier = sequenceBarrier;
/*  69 */     this.workHandler = workHandler;
/*  70 */     this.exceptionHandler = exceptionHandler;
/*  71 */     this.workSequence = workSequence;
/*     */     
/*  73 */     if (this.workHandler instanceof EventReleaseAware)
/*     */     {
/*  75 */       ((EventReleaseAware)this.workHandler).setEventReleaser(this.eventReleaser);
/*     */     }
/*     */     
/*  78 */     this.timeoutHandler = (workHandler instanceof TimeoutHandler) ? (TimeoutHandler)workHandler : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Sequence getSequence() {
/*  84 */     return this.sequence;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void halt() {
/*  90 */     this.running.set(false);
/*  91 */     this.sequenceBarrier.alert();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRunning() {
/*  97 */     return this.running.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 108 */     if (!this.running.compareAndSet(false, true))
/*     */     {
/* 110 */       throw new IllegalStateException("Thread is already running");
/*     */     }
/* 112 */     this.sequenceBarrier.clearAlert();
/*     */     
/* 114 */     notifyStart();
/*     */     
/* 116 */     boolean processedSequence = true;
/* 117 */     long cachedAvailableSequence = Long.MIN_VALUE;
/* 118 */     long nextSequence = this.sequence.get();
/* 119 */     T event = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/*     */       try {
/* 129 */         if (processedSequence) {
/*     */           
/* 131 */           processedSequence = false;
/*     */           
/*     */           do {
/* 134 */             nextSequence = this.workSequence.get() + 1L;
/* 135 */             this.sequence.set(nextSequence - 1L);
/*     */           }
/* 137 */           while (!this.workSequence.compareAndSet(nextSequence - 1L, nextSequence));
/*     */         } 
/*     */         
/* 140 */         if (cachedAvailableSequence >= nextSequence) {
/*     */           
/* 142 */           event = this.ringBuffer.get(nextSequence);
/* 143 */           this.workHandler.onEvent(event);
/* 144 */           processedSequence = true;
/*     */           
/*     */           continue;
/*     */         } 
/* 148 */         cachedAvailableSequence = this.sequenceBarrier.waitFor(nextSequence);
/*     */       
/*     */       }
/* 151 */       catch (TimeoutException e) {
/*     */         
/* 153 */         notifyTimeout(this.sequence.get());
/*     */       }
/* 155 */       catch (AlertException ex) {
/*     */         
/* 157 */         if (!this.running.get())
/*     */         {
/*     */           break;
/*     */         }
/*     */       }
/* 162 */       catch (Throwable ex) {
/*     */ 
/*     */         
/* 165 */         this.exceptionHandler.handleEventException(ex, nextSequence, event);
/* 166 */         processedSequence = true;
/*     */       } 
/*     */     } 
/*     */     
/* 170 */     notifyShutdown();
/*     */     
/* 172 */     this.running.set(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void notifyTimeout(long availableSequence) {
/*     */     try {
/* 179 */       if (this.timeoutHandler != null)
/*     */       {
/* 181 */         this.timeoutHandler.onTimeout(availableSequence);
/*     */       }
/*     */     }
/* 184 */     catch (Throwable e) {
/*     */       
/* 186 */       this.exceptionHandler.handleEventException(e, availableSequence, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void notifyStart() {
/* 192 */     if (this.workHandler instanceof LifecycleAware) {
/*     */       
/*     */       try {
/*     */         
/* 196 */         ((LifecycleAware)this.workHandler).onStart();
/*     */       }
/* 198 */       catch (Throwable ex) {
/*     */         
/* 200 */         this.exceptionHandler.handleOnStartException(ex);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void notifyShutdown() {
/* 207 */     if (this.workHandler instanceof LifecycleAware)
/*     */       
/*     */       try {
/*     */         
/* 211 */         ((LifecycleAware)this.workHandler).onShutdown();
/*     */       }
/* 213 */       catch (Throwable ex) {
/*     */         
/* 215 */         this.exceptionHandler.handleOnShutdownException(ex);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\WorkProcessor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */