/*     */ package com.lmax.disruptor.dsl;
/*     */ 
/*     */ import com.lmax.disruptor.BatchEventProcessor;
/*     */ import com.lmax.disruptor.DataProvider;
/*     */ import com.lmax.disruptor.EventFactory;
/*     */ import com.lmax.disruptor.EventHandler;
/*     */ import com.lmax.disruptor.EventProcessor;
/*     */ import com.lmax.disruptor.EventTranslator;
/*     */ import com.lmax.disruptor.EventTranslatorOneArg;
/*     */ import com.lmax.disruptor.EventTranslatorThreeArg;
/*     */ import com.lmax.disruptor.EventTranslatorTwoArg;
/*     */ import com.lmax.disruptor.ExceptionHandler;
/*     */ import com.lmax.disruptor.RingBuffer;
/*     */ import com.lmax.disruptor.Sequence;
/*     */ import com.lmax.disruptor.SequenceBarrier;
/*     */ import com.lmax.disruptor.TimeoutException;
/*     */ import com.lmax.disruptor.WaitStrategy;
/*     */ import com.lmax.disruptor.WorkHandler;
/*     */ import com.lmax.disruptor.WorkerPool;
/*     */ import com.lmax.disruptor.util.Util;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.TimeUnit;
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
/*     */ public class Disruptor<T>
/*     */ {
/*     */   private final RingBuffer<T> ringBuffer;
/*     */   private final Executor executor;
/*  63 */   private final ConsumerRepository<T> consumerRepository = new ConsumerRepository<>();
/*  64 */   private final AtomicBoolean started = new AtomicBoolean(false);
/*  65 */   private ExceptionHandler<? super T> exceptionHandler = new ExceptionHandlerWrapper<>();
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
/*     */   @Deprecated
/*     */   public Disruptor(EventFactory<T> eventFactory, int ringBufferSize, Executor executor) {
/*  81 */     this(RingBuffer.createMultiProducer(eventFactory, ringBufferSize), executor);
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
/*     */   @Deprecated
/*     */   public Disruptor(EventFactory<T> eventFactory, int ringBufferSize, Executor executor, ProducerType producerType, WaitStrategy waitStrategy) {
/* 104 */     this(RingBuffer.create(producerType, eventFactory, ringBufferSize, waitStrategy), executor);
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
/*     */   public Disruptor(EventFactory<T> eventFactory, int ringBufferSize, ThreadFactory threadFactory) {
/* 117 */     this(RingBuffer.createMultiProducer(eventFactory, ringBufferSize), new BasicExecutor(threadFactory));
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
/*     */   public Disruptor(EventFactory<T> eventFactory, int ringBufferSize, ThreadFactory threadFactory, ProducerType producerType, WaitStrategy waitStrategy) {
/* 136 */     this(
/* 137 */         RingBuffer.create(producerType, eventFactory, ringBufferSize, waitStrategy), new BasicExecutor(threadFactory));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Disruptor(RingBuffer<T> ringBuffer, Executor executor) {
/* 146 */     this.ringBuffer = ringBuffer;
/* 147 */     this.executor = executor;
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
/*     */   @SafeVarargs
/*     */   public final EventHandlerGroup<T> handleEventsWith(EventHandler<? super T>... handlers) {
/* 167 */     return createEventProcessors(new Sequence[0], handlers);
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
/*     */   @SafeVarargs
/*     */   public final EventHandlerGroup<T> handleEventsWith(EventProcessorFactory<T>... eventProcessorFactories) {
/* 191 */     Sequence[] barrierSequences = new Sequence[0];
/* 192 */     return createEventProcessors(barrierSequences, eventProcessorFactories);
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
/*     */   public EventHandlerGroup<T> handleEventsWith(EventProcessor... processors) {
/* 208 */     for (EventProcessor processor : processors)
/*     */     {
/* 210 */       this.consumerRepository.add(processor);
/*     */     }
/*     */     
/* 213 */     Sequence[] sequences = new Sequence[processors.length];
/* 214 */     for (int i = 0; i < processors.length; i++)
/*     */     {
/* 216 */       sequences[i] = processors[i].getSequence();
/*     */     }
/*     */     
/* 219 */     this.ringBuffer.addGatingSequences(sequences);
/*     */     
/* 221 */     return new EventHandlerGroup<>(this, this.consumerRepository, Util.getSequencesFor(processors));
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
/*     */   @SafeVarargs
/*     */   public final EventHandlerGroup<T> handleEventsWithWorkerPool(WorkHandler<T>... workHandlers) {
/* 237 */     return createWorkerPool(new Sequence[0], (WorkHandler<? super T>[])workHandlers);
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
/*     */   public void handleExceptionsWith(ExceptionHandler<? super T> exceptionHandler) {
/* 250 */     this.exceptionHandler = exceptionHandler;
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
/*     */   public void setDefaultExceptionHandler(ExceptionHandler<? super T> exceptionHandler) {
/* 263 */     checkNotStarted();
/* 264 */     if (!(this.exceptionHandler instanceof ExceptionHandlerWrapper))
/*     */     {
/* 266 */       throw new IllegalStateException("setDefaultExceptionHandler can not be used after handleExceptionsWith");
/*     */     }
/* 268 */     ((ExceptionHandlerWrapper)this.exceptionHandler).switchTo(exceptionHandler);
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
/*     */   public ExceptionHandlerSetting<T> handleExceptionsFor(EventHandler<T> eventHandler) {
/* 280 */     return new ExceptionHandlerSetting<>(eventHandler, this.consumerRepository);
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
/*     */   @SafeVarargs
/*     */   public final EventHandlerGroup<T> after(EventHandler<T>... handlers) {
/* 297 */     Sequence[] sequences = new Sequence[handlers.length];
/* 298 */     for (int i = 0, handlersLength = handlers.length; i < handlersLength; i++)
/*     */     {
/* 300 */       sequences[i] = this.consumerRepository.getSequenceFor(handlers[i]);
/*     */     }
/*     */     
/* 303 */     return new EventHandlerGroup<>(this, this.consumerRepository, sequences);
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
/*     */   public EventHandlerGroup<T> after(EventProcessor... processors) {
/* 316 */     for (EventProcessor processor : processors)
/*     */     {
/* 318 */       this.consumerRepository.add(processor);
/*     */     }
/*     */     
/* 321 */     return new EventHandlerGroup<>(this, this.consumerRepository, Util.getSequencesFor(processors));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void publishEvent(EventTranslator<T> eventTranslator) {
/* 331 */     this.ringBuffer.publishEvent(eventTranslator);
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
/*     */   public <A> void publishEvent(EventTranslatorOneArg<T, A> eventTranslator, A arg) {
/* 343 */     this.ringBuffer.publishEvent(eventTranslator, arg);
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
/*     */   public <A> void publishEvents(EventTranslatorOneArg<T, A> eventTranslator, A[] arg) {
/* 355 */     this.ringBuffer.publishEvents(eventTranslator, (Object[])arg);
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
/*     */   public <A, B> void publishEvent(EventTranslatorTwoArg<T, A, B> eventTranslator, A arg0, B arg1) {
/* 369 */     this.ringBuffer.publishEvent(eventTranslator, arg0, arg1);
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
/*     */   public <A, B, C> void publishEvent(EventTranslatorThreeArg<T, A, B, C> eventTranslator, A arg0, B arg1, C arg2) {
/* 385 */     this.ringBuffer.publishEvent(eventTranslator, arg0, arg1, arg2);
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
/*     */   public RingBuffer<T> start() {
/* 400 */     checkOnlyStartedOnce();
/* 401 */     for (ConsumerInfo consumerInfo : this.consumerRepository)
/*     */     {
/* 403 */       consumerInfo.start(this.executor);
/*     */     }
/*     */     
/* 406 */     return this.ringBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void halt() {
/* 414 */     for (ConsumerInfo consumerInfo : this.consumerRepository)
/*     */     {
/* 416 */       consumerInfo.halt();
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
/*     */   public void shutdown() {
/*     */     try {
/* 432 */       shutdown(-1L, TimeUnit.MILLISECONDS);
/*     */     }
/* 434 */     catch (TimeoutException e) {
/*     */       
/* 436 */       this.exceptionHandler.handleOnShutdownException((Throwable)e);
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
/*     */   public void shutdown(long timeout, TimeUnit timeUnit) throws TimeoutException {
/* 453 */     long timeOutAt = System.currentTimeMillis() + timeUnit.toMillis(timeout);
/* 454 */     while (hasBacklog()) {
/*     */       
/* 456 */       if (timeout >= 0L && System.currentTimeMillis() > timeOutAt)
/*     */       {
/* 458 */         throw TimeoutException.INSTANCE;
/*     */       }
/*     */     } 
/*     */     
/* 462 */     halt();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RingBuffer<T> getRingBuffer() {
/* 473 */     return this.ringBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getCursor() {
/* 483 */     return this.ringBuffer.getCursor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getBufferSize() {
/* 494 */     return this.ringBuffer.getBufferSize();
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
/*     */   public T get(long sequence) {
/* 506 */     return (T)this.ringBuffer.get(sequence);
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
/*     */   public SequenceBarrier getBarrierFor(EventHandler<T> handler) {
/* 518 */     return this.consumerRepository.getBarrierFor(handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSequenceValueFor(EventHandler<T> b1) {
/* 529 */     return this.consumerRepository.getSequenceFor(b1).get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasBacklog() {
/* 537 */     long cursor = this.ringBuffer.getCursor();
/* 538 */     for (Sequence consumer : this.consumerRepository.getLastSequenceInChain(false)) {
/*     */       
/* 540 */       if (cursor > consumer.get())
/*     */       {
/* 542 */         return true;
/*     */       }
/*     */     } 
/* 545 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EventHandlerGroup<T> createEventProcessors(Sequence[] barrierSequences, EventHandler<? super T>[] eventHandlers) {
/* 552 */     checkNotStarted();
/*     */     
/* 554 */     Sequence[] processorSequences = new Sequence[eventHandlers.length];
/* 555 */     SequenceBarrier barrier = this.ringBuffer.newBarrier(barrierSequences);
/*     */     
/* 557 */     for (int i = 0, eventHandlersLength = eventHandlers.length; i < eventHandlersLength; i++) {
/*     */       
/* 559 */       EventHandler<? super T> eventHandler = eventHandlers[i];
/*     */       
/* 561 */       BatchEventProcessor<T> batchEventProcessor = new BatchEventProcessor((DataProvider)this.ringBuffer, barrier, eventHandler);
/*     */ 
/*     */       
/* 564 */       if (this.exceptionHandler != null)
/*     */       {
/* 566 */         batchEventProcessor.setExceptionHandler(this.exceptionHandler);
/*     */       }
/*     */       
/* 569 */       this.consumerRepository.add((EventProcessor)batchEventProcessor, eventHandler, barrier);
/* 570 */       processorSequences[i] = batchEventProcessor.getSequence();
/*     */     } 
/*     */     
/* 573 */     updateGatingSequencesForNextInChain(barrierSequences, processorSequences);
/*     */     
/* 575 */     return new EventHandlerGroup<>(this, this.consumerRepository, processorSequences);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateGatingSequencesForNextInChain(Sequence[] barrierSequences, Sequence[] processorSequences) {
/* 580 */     if (processorSequences.length > 0) {
/*     */       
/* 582 */       this.ringBuffer.addGatingSequences(processorSequences);
/* 583 */       for (Sequence barrierSequence : barrierSequences)
/*     */       {
/* 585 */         this.ringBuffer.removeGatingSequence(barrierSequence);
/*     */       }
/* 587 */       this.consumerRepository.unMarkEventProcessorsAsEndOfChain(barrierSequences);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   EventHandlerGroup<T> createEventProcessors(Sequence[] barrierSequences, EventProcessorFactory<T>[] processorFactories) {
/* 594 */     EventProcessor[] eventProcessors = new EventProcessor[processorFactories.length];
/* 595 */     for (int i = 0; i < processorFactories.length; i++)
/*     */     {
/* 597 */       eventProcessors[i] = processorFactories[i].createEventProcessor(this.ringBuffer, barrierSequences);
/*     */     }
/*     */     
/* 600 */     return handleEventsWith(eventProcessors);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   EventHandlerGroup<T> createWorkerPool(Sequence[] barrierSequences, WorkHandler<? super T>[] workHandlers) {
/* 606 */     SequenceBarrier sequenceBarrier = this.ringBuffer.newBarrier(barrierSequences);
/* 607 */     WorkerPool<T> workerPool = new WorkerPool(this.ringBuffer, sequenceBarrier, this.exceptionHandler, (WorkHandler[])workHandlers);
/*     */ 
/*     */     
/* 610 */     this.consumerRepository.add(workerPool, sequenceBarrier);
/*     */     
/* 612 */     Sequence[] workerSequences = workerPool.getWorkerSequences();
/*     */     
/* 614 */     updateGatingSequencesForNextInChain(barrierSequences, workerSequences);
/*     */     
/* 616 */     return new EventHandlerGroup<>(this, this.consumerRepository, workerSequences);
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkNotStarted() {
/* 621 */     if (this.started.get())
/*     */     {
/* 623 */       throw new IllegalStateException("All event handlers must be added before calling starts.");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkOnlyStartedOnce() {
/* 629 */     if (!this.started.compareAndSet(false, true))
/*     */     {
/* 631 */       throw new IllegalStateException("Disruptor.start() must only be called once.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 638 */     return "Disruptor{ringBuffer=" + this.ringBuffer + ", started=" + this.started + ", executor=" + this.executor + '}';
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\dsl\Disruptor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */