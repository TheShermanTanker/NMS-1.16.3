/*     */ package com.lmax.disruptor.dsl;
/*     */ 
/*     */ import com.lmax.disruptor.EventHandler;
/*     */ import com.lmax.disruptor.EventProcessor;
/*     */ import com.lmax.disruptor.Sequence;
/*     */ import com.lmax.disruptor.SequenceBarrier;
/*     */ import com.lmax.disruptor.WorkerPool;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ConsumerRepository<T>
/*     */   implements Iterable<ConsumerInfo>
/*     */ {
/*  29 */   private final Map<EventHandler<?>, EventProcessorInfo<T>> eventProcessorInfoByEventHandler = new IdentityHashMap<>();
/*     */   
/*  31 */   private final Map<Sequence, ConsumerInfo> eventProcessorInfoBySequence = new IdentityHashMap<>();
/*     */   
/*  33 */   private final Collection<ConsumerInfo> consumerInfos = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(EventProcessor eventprocessor, EventHandler<? super T> handler, SequenceBarrier barrier) {
/*  40 */     EventProcessorInfo<T> consumerInfo = new EventProcessorInfo<>(eventprocessor, handler, barrier);
/*  41 */     this.eventProcessorInfoByEventHandler.put(handler, consumerInfo);
/*  42 */     this.eventProcessorInfoBySequence.put(eventprocessor.getSequence(), consumerInfo);
/*  43 */     this.consumerInfos.add(consumerInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(EventProcessor processor) {
/*  48 */     EventProcessorInfo<T> consumerInfo = new EventProcessorInfo<>(processor, null, null);
/*  49 */     this.eventProcessorInfoBySequence.put(processor.getSequence(), consumerInfo);
/*  50 */     this.consumerInfos.add(consumerInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(WorkerPool<T> workerPool, SequenceBarrier sequenceBarrier) {
/*  55 */     WorkerPoolInfo<T> workerPoolInfo = new WorkerPoolInfo<>(workerPool, sequenceBarrier);
/*  56 */     this.consumerInfos.add(workerPoolInfo);
/*  57 */     for (Sequence sequence : workerPool.getWorkerSequences())
/*     */     {
/*  59 */       this.eventProcessorInfoBySequence.put(sequence, workerPoolInfo);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Sequence[] getLastSequenceInChain(boolean includeStopped) {
/*  65 */     List<Sequence> lastSequence = new ArrayList<>();
/*  66 */     for (ConsumerInfo consumerInfo : this.consumerInfos) {
/*     */       
/*  68 */       if ((includeStopped || consumerInfo.isRunning()) && consumerInfo.isEndOfChain()) {
/*     */         
/*  70 */         Sequence[] sequences = consumerInfo.getSequences();
/*  71 */         Collections.addAll(lastSequence, sequences);
/*     */       } 
/*     */     } 
/*     */     
/*  75 */     return lastSequence.<Sequence>toArray(new Sequence[lastSequence.size()]);
/*     */   }
/*     */ 
/*     */   
/*     */   public EventProcessor getEventProcessorFor(EventHandler<T> handler) {
/*  80 */     EventProcessorInfo<T> eventprocessorInfo = getEventProcessorInfo(handler);
/*  81 */     if (eventprocessorInfo == null)
/*     */     {
/*  83 */       throw new IllegalArgumentException("The event handler " + handler + " is not processing events.");
/*     */     }
/*     */     
/*  86 */     return eventprocessorInfo.getEventProcessor();
/*     */   }
/*     */ 
/*     */   
/*     */   public Sequence getSequenceFor(EventHandler<T> handler) {
/*  91 */     return getEventProcessorFor(handler).getSequence();
/*     */   }
/*     */ 
/*     */   
/*     */   public void unMarkEventProcessorsAsEndOfChain(Sequence... barrierEventProcessors) {
/*  96 */     for (Sequence barrierEventProcessor : barrierEventProcessors)
/*     */     {
/*  98 */       getEventProcessorInfo(barrierEventProcessor).markAsUsedInBarrier();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<ConsumerInfo> iterator() {
/* 105 */     return this.consumerInfos.iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public SequenceBarrier getBarrierFor(EventHandler<T> handler) {
/* 110 */     ConsumerInfo<T> consumerInfo = getEventProcessorInfo(handler);
/* 111 */     return (consumerInfo != null) ? consumerInfo.getBarrier() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   private EventProcessorInfo<T> getEventProcessorInfo(EventHandler<T> handler) {
/* 116 */     return this.eventProcessorInfoByEventHandler.get(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   private ConsumerInfo getEventProcessorInfo(Sequence barrierEventProcessor) {
/* 121 */     return this.eventProcessorInfoBySequence.get(barrierEventProcessor);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\dsl\ConsumerRepository.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */