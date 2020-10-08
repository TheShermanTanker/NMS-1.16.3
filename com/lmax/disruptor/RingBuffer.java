/*      */ package com.lmax.disruptor;
/*      */ 
/*      */ import com.lmax.disruptor.dsl.ProducerType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class RingBuffer<E>
/*      */   extends RingBufferFields<E>
/*      */   implements Cursored, EventSequencer<E>, EventSink<E>
/*      */ {
/*      */   public static final long INITIAL_CURSOR_VALUE = -1L;
/*      */   protected long p1;
/*      */   protected long p2;
/*      */   protected long p3;
/*      */   protected long p4;
/*      */   protected long p5;
/*      */   protected long p6;
/*      */   protected long p7;
/*      */   
/*      */   RingBuffer(EventFactory<E> eventFactory, Sequencer sequencer) {
/*  119 */     super(eventFactory, sequencer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <E> RingBuffer<E> createMultiProducer(EventFactory<E> factory, int bufferSize, WaitStrategy waitStrategy) {
/*  138 */     MultiProducerSequencer sequencer = new MultiProducerSequencer(bufferSize, waitStrategy);
/*      */     
/*  140 */     return new RingBuffer<>(factory, sequencer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <E> RingBuffer<E> createMultiProducer(EventFactory<E> factory, int bufferSize) {
/*  155 */     return createMultiProducer(factory, bufferSize, new BlockingWaitStrategy());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <E> RingBuffer<E> createSingleProducer(EventFactory<E> factory, int bufferSize, WaitStrategy waitStrategy) {
/*  174 */     SingleProducerSequencer sequencer = new SingleProducerSequencer(bufferSize, waitStrategy);
/*      */     
/*  176 */     return new RingBuffer<>(factory, sequencer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <E> RingBuffer<E> createSingleProducer(EventFactory<E> factory, int bufferSize) {
/*  191 */     return createSingleProducer(factory, bufferSize, new BlockingWaitStrategy());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <E> RingBuffer<E> create(ProducerType producerType, EventFactory<E> factory, int bufferSize, WaitStrategy waitStrategy) {
/*  211 */     switch (producerType) {
/*      */       
/*      */       case SINGLE:
/*  214 */         return createSingleProducer(factory, bufferSize, waitStrategy);
/*      */       case MULTI:
/*  216 */         return createMultiProducer(factory, bufferSize, waitStrategy);
/*      */     } 
/*  218 */     throw new IllegalStateException(producerType.toString());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public E get(long sequence) {
/*  240 */     return elementAt(sequence);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long next() {
/*  263 */     return this.sequencer.next();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long next(int n) {
/*  277 */     return this.sequencer.next(n);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long tryNext() throws InsufficientCapacityException {
/*  303 */     return this.sequencer.tryNext();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long tryNext(int n) throws InsufficientCapacityException {
/*  317 */     return this.sequencer.tryNext(n);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void resetTo(long sequence) {
/*  331 */     this.sequencer.claim(sequence);
/*  332 */     this.sequencer.publish(sequence);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public E claimAndGetPreallocated(long sequence) {
/*  344 */     this.sequencer.claim(sequence);
/*  345 */     return get(sequence);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean isPublished(long sequence) {
/*  361 */     return this.sequencer.isAvailable(sequence);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addGatingSequences(Sequence... gatingSequences) {
/*  372 */     this.sequencer.addGatingSequences(gatingSequences);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getMinimumGatingSequence() {
/*  384 */     return this.sequencer.getMinimumSequence();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean removeGatingSequence(Sequence sequence) {
/*  395 */     return this.sequencer.removeGatingSequence(sequence);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SequenceBarrier newBarrier(Sequence... sequencesToTrack) {
/*  408 */     return this.sequencer.newBarrier(sequencesToTrack);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EventPoller<E> newPoller(Sequence... gatingSequences) {
/*  419 */     return this.sequencer.newPoller(this, gatingSequences);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getCursor() {
/*  432 */     return this.sequencer.getCursor();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBufferSize() {
/*  440 */     return this.bufferSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasAvailableCapacity(int requiredCapacity) {
/*  455 */     return this.sequencer.hasAvailableCapacity(requiredCapacity);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void publishEvent(EventTranslator<E> translator) {
/*  465 */     long sequence = this.sequencer.next();
/*  466 */     translateAndPublish(translator, sequence);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryPublishEvent(EventTranslator<E> translator) {
/*      */     try {
/*  477 */       long sequence = this.sequencer.tryNext();
/*  478 */       translateAndPublish(translator, sequence);
/*  479 */       return true;
/*      */     }
/*  481 */     catch (InsufficientCapacityException e) {
/*      */       
/*  483 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A> void publishEvent(EventTranslatorOneArg<E, A> translator, A arg0) {
/*  494 */     long sequence = this.sequencer.next();
/*  495 */     translateAndPublish(translator, sequence, arg0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A> boolean tryPublishEvent(EventTranslatorOneArg<E, A> translator, A arg0) {
/*      */     try {
/*  507 */       long sequence = this.sequencer.tryNext();
/*  508 */       translateAndPublish(translator, sequence, arg0);
/*  509 */       return true;
/*      */     }
/*  511 */     catch (InsufficientCapacityException e) {
/*      */       
/*  513 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B> void publishEvent(EventTranslatorTwoArg<E, A, B> translator, A arg0, B arg1) {
/*  524 */     long sequence = this.sequencer.next();
/*  525 */     translateAndPublish(translator, sequence, arg0, arg1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B> boolean tryPublishEvent(EventTranslatorTwoArg<E, A, B> translator, A arg0, B arg1) {
/*      */     try {
/*  537 */       long sequence = this.sequencer.tryNext();
/*  538 */       translateAndPublish(translator, sequence, arg0, arg1);
/*  539 */       return true;
/*      */     }
/*  541 */     catch (InsufficientCapacityException e) {
/*      */       
/*  543 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B, C> void publishEvent(EventTranslatorThreeArg<E, A, B, C> translator, A arg0, B arg1, C arg2) {
/*  554 */     long sequence = this.sequencer.next();
/*  555 */     translateAndPublish(translator, sequence, arg0, arg1, arg2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B, C> boolean tryPublishEvent(EventTranslatorThreeArg<E, A, B, C> translator, A arg0, B arg1, C arg2) {
/*      */     try {
/*  567 */       long sequence = this.sequencer.tryNext();
/*  568 */       translateAndPublish(translator, sequence, arg0, arg1, arg2);
/*  569 */       return true;
/*      */     }
/*  571 */     catch (InsufficientCapacityException e) {
/*      */       
/*  573 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void publishEvent(EventTranslatorVararg<E> translator, Object... args) {
/*  583 */     long sequence = this.sequencer.next();
/*  584 */     translateAndPublish(translator, sequence, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryPublishEvent(EventTranslatorVararg<E> translator, Object... args) {
/*      */     try {
/*  595 */       long sequence = this.sequencer.tryNext();
/*  596 */       translateAndPublish(translator, sequence, args);
/*  597 */       return true;
/*      */     }
/*  599 */     catch (InsufficientCapacityException e) {
/*      */       
/*  601 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void publishEvents(EventTranslator<E>[] translators) {
/*  612 */     publishEvents(translators, 0, translators.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void publishEvents(EventTranslator<E>[] translators, int batchStartsAt, int batchSize) {
/*  621 */     checkBounds(translators, batchStartsAt, batchSize);
/*  622 */     long finalSequence = this.sequencer.next(batchSize);
/*  623 */     translateAndPublishBatch(translators, batchStartsAt, batchSize, finalSequence);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryPublishEvents(EventTranslator<E>[] translators) {
/*  632 */     return tryPublishEvents(translators, 0, translators.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryPublishEvents(EventTranslator<E>[] translators, int batchStartsAt, int batchSize) {
/*  641 */     checkBounds(translators, batchStartsAt, batchSize);
/*      */     
/*      */     try {
/*  644 */       long finalSequence = this.sequencer.tryNext(batchSize);
/*  645 */       translateAndPublishBatch(translators, batchStartsAt, batchSize, finalSequence);
/*  646 */       return true;
/*      */     }
/*  648 */     catch (InsufficientCapacityException e) {
/*      */       
/*  650 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A> void publishEvents(EventTranslatorOneArg<E, A> translator, A[] arg0) {
/*  661 */     publishEvents(translator, 0, arg0.length, arg0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A> void publishEvents(EventTranslatorOneArg<E, A> translator, int batchStartsAt, int batchSize, A[] arg0) {
/*  671 */     checkBounds(arg0, batchStartsAt, batchSize);
/*  672 */     long finalSequence = this.sequencer.next(batchSize);
/*  673 */     translateAndPublishBatch(translator, arg0, batchStartsAt, batchSize, finalSequence);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A> boolean tryPublishEvents(EventTranslatorOneArg<E, A> translator, A[] arg0) {
/*  683 */     return tryPublishEvents(translator, 0, arg0.length, arg0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A> boolean tryPublishEvents(EventTranslatorOneArg<E, A> translator, int batchStartsAt, int batchSize, A[] arg0) {
/*  694 */     checkBounds(arg0, batchStartsAt, batchSize);
/*      */     
/*      */     try {
/*  697 */       long finalSequence = this.sequencer.tryNext(batchSize);
/*  698 */       translateAndPublishBatch(translator, arg0, batchStartsAt, batchSize, finalSequence);
/*  699 */       return true;
/*      */     }
/*  701 */     catch (InsufficientCapacityException e) {
/*      */       
/*  703 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B> void publishEvents(EventTranslatorTwoArg<E, A, B> translator, A[] arg0, B[] arg1) {
/*  714 */     publishEvents(translator, 0, arg0.length, arg0, arg1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B> void publishEvents(EventTranslatorTwoArg<E, A, B> translator, int batchStartsAt, int batchSize, A[] arg0, B[] arg1) {
/*  725 */     checkBounds(arg0, arg1, batchStartsAt, batchSize);
/*  726 */     long finalSequence = this.sequencer.next(batchSize);
/*  727 */     translateAndPublishBatch(translator, arg0, arg1, batchStartsAt, batchSize, finalSequence);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B> boolean tryPublishEvents(EventTranslatorTwoArg<E, A, B> translator, A[] arg0, B[] arg1) {
/*  737 */     return tryPublishEvents(translator, 0, arg0.length, arg0, arg1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B> boolean tryPublishEvents(EventTranslatorTwoArg<E, A, B> translator, int batchStartsAt, int batchSize, A[] arg0, B[] arg1) {
/*  748 */     checkBounds(arg0, arg1, batchStartsAt, batchSize);
/*      */     
/*      */     try {
/*  751 */       long finalSequence = this.sequencer.tryNext(batchSize);
/*  752 */       translateAndPublishBatch(translator, arg0, arg1, batchStartsAt, batchSize, finalSequence);
/*  753 */       return true;
/*      */     }
/*  755 */     catch (InsufficientCapacityException e) {
/*      */       
/*  757 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B, C> void publishEvents(EventTranslatorThreeArg<E, A, B, C> translator, A[] arg0, B[] arg1, C[] arg2) {
/*  768 */     publishEvents(translator, 0, arg0.length, arg0, arg1, arg2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B, C> void publishEvents(EventTranslatorThreeArg<E, A, B, C> translator, int batchStartsAt, int batchSize, A[] arg0, B[] arg1, C[] arg2) {
/*  779 */     checkBounds(arg0, arg1, arg2, batchStartsAt, batchSize);
/*  780 */     long finalSequence = this.sequencer.next(batchSize);
/*  781 */     translateAndPublishBatch(translator, arg0, arg1, arg2, batchStartsAt, batchSize, finalSequence);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B, C> boolean tryPublishEvents(EventTranslatorThreeArg<E, A, B, C> translator, A[] arg0, B[] arg1, C[] arg2) {
/*  792 */     return tryPublishEvents(translator, 0, arg0.length, arg0, arg1, arg2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <A, B, C> boolean tryPublishEvents(EventTranslatorThreeArg<E, A, B, C> translator, int batchStartsAt, int batchSize, A[] arg0, B[] arg1, C[] arg2) {
/*  803 */     checkBounds(arg0, arg1, arg2, batchStartsAt, batchSize);
/*      */     
/*      */     try {
/*  806 */       long finalSequence = this.sequencer.tryNext(batchSize);
/*  807 */       translateAndPublishBatch(translator, arg0, arg1, arg2, batchStartsAt, batchSize, finalSequence);
/*  808 */       return true;
/*      */     }
/*  810 */     catch (InsufficientCapacityException e) {
/*      */       
/*  812 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void publishEvents(EventTranslatorVararg<E> translator, Object[]... args) {
/*  822 */     publishEvents(translator, 0, args.length, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void publishEvents(EventTranslatorVararg<E> translator, int batchStartsAt, int batchSize, Object[]... args) {
/*  831 */     checkBounds(batchStartsAt, batchSize, args);
/*  832 */     long finalSequence = this.sequencer.next(batchSize);
/*  833 */     translateAndPublishBatch(translator, batchStartsAt, batchSize, finalSequence, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryPublishEvents(EventTranslatorVararg<E> translator, Object[]... args) {
/*  842 */     return tryPublishEvents(translator, 0, args.length, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryPublishEvents(EventTranslatorVararg<E> translator, int batchStartsAt, int batchSize, Object[]... args) {
/*  852 */     checkBounds(args, batchStartsAt, batchSize);
/*      */     
/*      */     try {
/*  855 */       long finalSequence = this.sequencer.tryNext(batchSize);
/*  856 */       translateAndPublishBatch(translator, batchStartsAt, batchSize, finalSequence, args);
/*  857 */       return true;
/*      */     }
/*  859 */     catch (InsufficientCapacityException e) {
/*      */       
/*  861 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void publish(long sequence) {
/*  874 */     this.sequencer.publish(sequence);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void publish(long lo, long hi) {
/*  888 */     this.sequencer.publish(lo, hi);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long remainingCapacity() {
/*  898 */     return this.sequencer.remainingCapacity();
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkBounds(EventTranslator<E>[] translators, int batchStartsAt, int batchSize) {
/*  903 */     checkBatchSizing(batchStartsAt, batchSize);
/*  904 */     batchOverRuns(translators, batchStartsAt, batchSize);
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkBatchSizing(int batchStartsAt, int batchSize) {
/*  909 */     if (batchStartsAt < 0 || batchSize < 0)
/*      */     {
/*  911 */       throw new IllegalArgumentException("Both batchStartsAt and batchSize must be positive but got: batchStartsAt " + batchStartsAt + " and batchSize " + batchSize);
/*      */     }
/*  913 */     if (batchSize > this.bufferSize)
/*      */     {
/*  915 */       throw new IllegalArgumentException("The ring buffer cannot accommodate " + batchSize + " it only has space for " + this.bufferSize + " entities.");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private <A> void checkBounds(A[] arg0, int batchStartsAt, int batchSize) {
/*  921 */     checkBatchSizing(batchStartsAt, batchSize);
/*  922 */     batchOverRuns(arg0, batchStartsAt, batchSize);
/*      */   }
/*      */ 
/*      */   
/*      */   private <A, B> void checkBounds(A[] arg0, B[] arg1, int batchStartsAt, int batchSize) {
/*  927 */     checkBatchSizing(batchStartsAt, batchSize);
/*  928 */     batchOverRuns(arg0, batchStartsAt, batchSize);
/*  929 */     batchOverRuns(arg1, batchStartsAt, batchSize);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private <A, B, C> void checkBounds(A[] arg0, B[] arg1, C[] arg2, int batchStartsAt, int batchSize) {
/*  935 */     checkBatchSizing(batchStartsAt, batchSize);
/*  936 */     batchOverRuns(arg0, batchStartsAt, batchSize);
/*  937 */     batchOverRuns(arg1, batchStartsAt, batchSize);
/*  938 */     batchOverRuns(arg2, batchStartsAt, batchSize);
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkBounds(int batchStartsAt, int batchSize, Object[][] args) {
/*  943 */     checkBatchSizing(batchStartsAt, batchSize);
/*  944 */     batchOverRuns(args, batchStartsAt, batchSize);
/*      */   }
/*      */ 
/*      */   
/*      */   private <A> void batchOverRuns(A[] arg0, int batchStartsAt, int batchSize) {
/*  949 */     if (batchStartsAt + batchSize > arg0.length)
/*      */     {
/*  951 */       throw new IllegalArgumentException("A batchSize of: " + batchSize + " with batchStatsAt of: " + batchStartsAt + " will overrun the available number of arguments: " + (arg0.length - batchStartsAt));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void translateAndPublish(EventTranslator<E> translator, long sequence) {
/*      */     try {
/*  962 */       translator.translateTo(get(sequence), sequence);
/*      */     }
/*      */     finally {
/*      */       
/*  966 */       this.sequencer.publish(sequence);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private <A> void translateAndPublish(EventTranslatorOneArg<E, A> translator, long sequence, A arg0) {
/*      */     try {
/*  974 */       translator.translateTo(get(sequence), sequence, arg0);
/*      */     }
/*      */     finally {
/*      */       
/*  978 */       this.sequencer.publish(sequence);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private <A, B> void translateAndPublish(EventTranslatorTwoArg<E, A, B> translator, long sequence, A arg0, B arg1) {
/*      */     try {
/*  986 */       translator.translateTo(get(sequence), sequence, arg0, arg1);
/*      */     }
/*      */     finally {
/*      */       
/*  990 */       this.sequencer.publish(sequence);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private <A, B, C> void translateAndPublish(EventTranslatorThreeArg<E, A, B, C> translator, long sequence, A arg0, B arg1, C arg2) {
/*      */     try {
/* 1000 */       translator.translateTo(get(sequence), sequence, arg0, arg1, arg2);
/*      */     }
/*      */     finally {
/*      */       
/* 1004 */       this.sequencer.publish(sequence);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void translateAndPublish(EventTranslatorVararg<E> translator, long sequence, Object... args) {
/*      */     try {
/* 1012 */       translator.translateTo(get(sequence), sequence, args);
/*      */     }
/*      */     finally {
/*      */       
/* 1016 */       this.sequencer.publish(sequence);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void translateAndPublishBatch(EventTranslator<E>[] translators, int batchStartsAt, int batchSize, long finalSequence) {
/* 1024 */     long initialSequence = finalSequence - (batchSize - 1);
/*      */     
/*      */     try {
/* 1027 */       long sequence = initialSequence;
/* 1028 */       int batchEndsAt = batchStartsAt + batchSize;
/* 1029 */       for (int i = batchStartsAt; i < batchEndsAt; i++)
/*      */       {
/* 1031 */         EventTranslator<E> translator = translators[i];
/* 1032 */         translator.translateTo(get(sequence), sequence++);
/*      */       }
/*      */     
/*      */     } finally {
/*      */       
/* 1037 */       this.sequencer.publish(initialSequence, finalSequence);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private <A> void translateAndPublishBatch(EventTranslatorOneArg<E, A> translator, A[] arg0, int batchStartsAt, int batchSize, long finalSequence) {
/* 1045 */     long initialSequence = finalSequence - (batchSize - 1);
/*      */     
/*      */     try {
/* 1048 */       long sequence = initialSequence;
/* 1049 */       int batchEndsAt = batchStartsAt + batchSize;
/* 1050 */       for (int i = batchStartsAt; i < batchEndsAt; i++)
/*      */       {
/* 1052 */         translator.translateTo(get(sequence), sequence++, arg0[i]);
/*      */       }
/*      */     }
/*      */     finally {
/*      */       
/* 1057 */       this.sequencer.publish(initialSequence, finalSequence);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private <A, B> void translateAndPublishBatch(EventTranslatorTwoArg<E, A, B> translator, A[] arg0, B[] arg1, int batchStartsAt, int batchSize, long finalSequence) {
/* 1066 */     long initialSequence = finalSequence - (batchSize - 1);
/*      */     
/*      */     try {
/* 1069 */       long sequence = initialSequence;
/* 1070 */       int batchEndsAt = batchStartsAt + batchSize;
/* 1071 */       for (int i = batchStartsAt; i < batchEndsAt; i++)
/*      */       {
/* 1073 */         translator.translateTo(get(sequence), sequence++, arg0[i], arg1[i]);
/*      */       }
/*      */     }
/*      */     finally {
/*      */       
/* 1078 */       this.sequencer.publish(initialSequence, finalSequence);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private <A, B, C> void translateAndPublishBatch(EventTranslatorThreeArg<E, A, B, C> translator, A[] arg0, B[] arg1, C[] arg2, int batchStartsAt, int batchSize, long finalSequence) {
/* 1087 */     long initialSequence = finalSequence - (batchSize - 1);
/*      */     
/*      */     try {
/* 1090 */       long sequence = initialSequence;
/* 1091 */       int batchEndsAt = batchStartsAt + batchSize;
/* 1092 */       for (int i = batchStartsAt; i < batchEndsAt; i++)
/*      */       {
/* 1094 */         translator.translateTo(get(sequence), sequence++, arg0[i], arg1[i], arg2[i]);
/*      */       }
/*      */     }
/*      */     finally {
/*      */       
/* 1099 */       this.sequencer.publish(initialSequence, finalSequence);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void translateAndPublishBatch(EventTranslatorVararg<E> translator, int batchStartsAt, int batchSize, long finalSequence, Object[][] args) {
/* 1107 */     long initialSequence = finalSequence - (batchSize - 1);
/*      */     
/*      */     try {
/* 1110 */       long sequence = initialSequence;
/* 1111 */       int batchEndsAt = batchStartsAt + batchSize;
/* 1112 */       for (int i = batchStartsAt; i < batchEndsAt; i++)
/*      */       {
/* 1114 */         translator.translateTo(get(sequence), sequence++, args[i]);
/*      */       }
/*      */     }
/*      */     finally {
/*      */       
/* 1119 */       this.sequencer.publish(initialSequence, finalSequence);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1126 */     return "RingBuffer{bufferSize=" + this.bufferSize + ", sequencer=" + this.sequencer + "}";
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\RingBuffer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */