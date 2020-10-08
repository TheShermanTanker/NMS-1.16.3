/*     */ package com.lmax.disruptor;
/*     */ 
/*     */ import java.util.concurrent.atomic.AtomicInteger;
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
/*     */ public final class BatchEventProcessor<T>
/*     */   implements EventProcessor
/*     */ {
/*     */   private static final int IDLE = 0;
/*     */   private static final int HALTED = 1;
/*     */   private static final int RUNNING = 2;
/*  37 */   private final AtomicInteger running = new AtomicInteger(0);
/*  38 */   private ExceptionHandler<? super T> exceptionHandler = new FatalExceptionHandler();
/*     */   private final DataProvider<T> dataProvider;
/*     */   private final SequenceBarrier sequenceBarrier;
/*     */   private final EventHandler<? super T> eventHandler;
/*  42 */   private final Sequence sequence = new Sequence(-1L);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final TimeoutHandler timeoutHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final BatchStartAware batchStartAware;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BatchEventProcessor(DataProvider<T> dataProvider, SequenceBarrier sequenceBarrier, EventHandler<? super T> eventHandler) {
/*  59 */     this.dataProvider = dataProvider;
/*  60 */     this.sequenceBarrier = sequenceBarrier;
/*  61 */     this.eventHandler = eventHandler;
/*     */     
/*  63 */     if (eventHandler instanceof SequenceReportingEventHandler)
/*     */     {
/*  65 */       ((SequenceReportingEventHandler)eventHandler).setSequenceCallback(this.sequence);
/*     */     }
/*     */     
/*  68 */     this.batchStartAware = (eventHandler instanceof BatchStartAware) ? (BatchStartAware)eventHandler : null;
/*     */     
/*  70 */     this.timeoutHandler = (eventHandler instanceof TimeoutHandler) ? (TimeoutHandler)eventHandler : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Sequence getSequence() {
/*  77 */     return this.sequence;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void halt() {
/*  83 */     this.running.set(1);
/*  84 */     this.sequenceBarrier.alert();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRunning() {
/*  90 */     return (this.running.get() != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExceptionHandler(ExceptionHandler<? super T> exceptionHandler) {
/* 100 */     if (null == exceptionHandler)
/*     */     {
/* 102 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 105 */     this.exceptionHandler = exceptionHandler;
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
/* 116 */     if (this.running.compareAndSet(0, 2)) {
/*     */       
/* 118 */       this.sequenceBarrier.clearAlert();
/*     */       
/* 120 */       notifyStart();
/*     */       
/*     */       try {
/* 123 */         if (this.running.get() == 2)
/*     */         {
/* 125 */           processEvents();
/*     */         }
/*     */       }
/*     */       finally {
/*     */         
/* 130 */         notifyShutdown();
/* 131 */         this.running.set(0);
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 139 */       if (this.running.get() == 2)
/*     */       {
/* 141 */         throw new IllegalStateException("Thread is already running");
/*     */       }
/*     */ 
/*     */       
/* 145 */       earlyExit();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void processEvents() {
/* 152 */     T event = null;
/* 153 */     long nextSequence = this.sequence.get() + 1L;
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/*     */       try {
/* 159 */         long availableSequence = this.sequenceBarrier.waitFor(nextSequence);
/* 160 */         if (this.batchStartAware != null)
/*     */         {
/* 162 */           this.batchStartAware.onBatchStart(availableSequence - nextSequence + 1L);
/*     */         }
/*     */         
/* 165 */         while (nextSequence <= availableSequence) {
/*     */           
/* 167 */           event = this.dataProvider.get(nextSequence);
/* 168 */           this.eventHandler.onEvent(event, nextSequence, (nextSequence == availableSequence));
/* 169 */           nextSequence++;
/*     */         } 
/*     */         
/* 172 */         this.sequence.set(availableSequence);
/*     */       }
/* 174 */       catch (TimeoutException e) {
/*     */         
/* 176 */         notifyTimeout(this.sequence.get());
/*     */       }
/* 178 */       catch (AlertException ex) {
/*     */         
/* 180 */         if (this.running.get() != 2)
/*     */         {
/*     */           break;
/*     */         }
/*     */       }
/* 185 */       catch (Throwable ex) {
/*     */         
/* 187 */         this.exceptionHandler.handleEventException(ex, nextSequence, event);
/* 188 */         this.sequence.set(nextSequence);
/* 189 */         nextSequence++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void earlyExit() {
/* 196 */     notifyStart();
/* 197 */     notifyShutdown();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void notifyTimeout(long availableSequence) {
/*     */     try {
/* 204 */       if (this.timeoutHandler != null)
/*     */       {
/* 206 */         this.timeoutHandler.onTimeout(availableSequence);
/*     */       }
/*     */     }
/* 209 */     catch (Throwable e) {
/*     */       
/* 211 */       this.exceptionHandler.handleEventException(e, availableSequence, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void notifyStart() {
/* 220 */     if (this.eventHandler instanceof LifecycleAware) {
/*     */       
/*     */       try {
/*     */         
/* 224 */         ((LifecycleAware)this.eventHandler).onStart();
/*     */       }
/* 226 */       catch (Throwable ex) {
/*     */         
/* 228 */         this.exceptionHandler.handleOnStartException(ex);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void notifyShutdown() {
/* 238 */     if (this.eventHandler instanceof LifecycleAware)
/*     */       
/*     */       try {
/*     */         
/* 242 */         ((LifecycleAware)this.eventHandler).onShutdown();
/*     */       }
/* 244 */       catch (Throwable ex) {
/*     */         
/* 246 */         this.exceptionHandler.handleOnShutdownException(ex);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\BatchEventProcessor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */