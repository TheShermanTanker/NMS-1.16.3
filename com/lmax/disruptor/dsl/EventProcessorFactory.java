package com.lmax.disruptor.dsl;

import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;

public interface EventProcessorFactory<T> {
  EventProcessor createEventProcessor(RingBuffer<T> paramRingBuffer, Sequence[] paramArrayOfSequence);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\dsl\EventProcessorFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */