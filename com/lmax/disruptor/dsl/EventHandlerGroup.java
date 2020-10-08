/*     */ package com.lmax.disruptor.dsl;
/*     */ 
/*     */ import com.lmax.disruptor.EventHandler;
/*     */ import com.lmax.disruptor.EventProcessor;
/*     */ import com.lmax.disruptor.Sequence;
/*     */ import com.lmax.disruptor.SequenceBarrier;
/*     */ import com.lmax.disruptor.WorkHandler;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EventHandlerGroup<T>
/*     */ {
/*     */   private final Disruptor<T> disruptor;
/*     */   private final ConsumerRepository<T> consumerRepository;
/*     */   private final Sequence[] sequences;
/*     */   
/*     */   EventHandlerGroup(Disruptor<T> disruptor, ConsumerRepository<T> consumerRepository, Sequence[] sequences) {
/*  42 */     this.disruptor = disruptor;
/*  43 */     this.consumerRepository = consumerRepository;
/*  44 */     this.sequences = Arrays.<Sequence>copyOf(sequences, sequences.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventHandlerGroup<T> and(EventHandlerGroup<T> otherHandlerGroup) {
/*  55 */     Sequence[] combinedSequences = new Sequence[this.sequences.length + otherHandlerGroup.sequences.length];
/*  56 */     System.arraycopy(this.sequences, 0, combinedSequences, 0, this.sequences.length);
/*  57 */     System.arraycopy(otherHandlerGroup.sequences, 0, combinedSequences, this.sequences.length, otherHandlerGroup.sequences.length);
/*     */ 
/*     */     
/*  60 */     return new EventHandlerGroup(this.disruptor, this.consumerRepository, combinedSequences);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventHandlerGroup<T> and(EventProcessor... processors) {
/*  71 */     Sequence[] combinedSequences = new Sequence[this.sequences.length + processors.length];
/*     */     
/*  73 */     for (int i = 0; i < processors.length; i++) {
/*     */       
/*  75 */       this.consumerRepository.add(processors[i]);
/*  76 */       combinedSequences[i] = processors[i].getSequence();
/*     */     } 
/*  78 */     System.arraycopy(this.sequences, 0, combinedSequences, processors.length, this.sequences.length);
/*     */     
/*  80 */     return new EventHandlerGroup(this.disruptor, this.consumerRepository, combinedSequences);
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
/*     */   @SafeVarargs
/*     */   public final EventHandlerGroup<T> then(EventHandler<? super T>... handlers) {
/*  98 */     return handleEventsWith(handlers);
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
/*     */   public final EventHandlerGroup<T> then(EventProcessorFactory<T>... eventProcessorFactories) {
/* 114 */     return handleEventsWith(eventProcessorFactories);
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
/*     */   @SafeVarargs
/*     */   public final EventHandlerGroup<T> thenHandleEventsWithWorkerPool(WorkHandler<? super T>... handlers) {
/* 133 */     return handleEventsWithWorkerPool(handlers);
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
/*     */   @SafeVarargs
/*     */   public final EventHandlerGroup<T> handleEventsWith(EventHandler<? super T>... handlers) {
/* 151 */     return this.disruptor.createEventProcessors(this.sequences, handlers);
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
/*     */   @SafeVarargs
/*     */   public final EventHandlerGroup<T> handleEventsWith(EventProcessorFactory<T>... eventProcessorFactories) {
/* 169 */     return this.disruptor.createEventProcessors(this.sequences, eventProcessorFactories);
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
/*     */   @SafeVarargs
/*     */   public final EventHandlerGroup<T> handleEventsWithWorkerPool(WorkHandler<? super T>... handlers) {
/* 188 */     return this.disruptor.createWorkerPool(this.sequences, handlers);
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
/*     */   public SequenceBarrier asSequenceBarrier() {
/* 200 */     return this.disruptor.getRingBuffer().newBarrier(this.sequences);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\dsl\EventHandlerGroup.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */