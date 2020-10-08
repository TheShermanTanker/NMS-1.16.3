package com.lmax.disruptor;

public interface WaitStrategy {
  long waitFor(long paramLong, Sequence paramSequence1, Sequence paramSequence2, SequenceBarrier paramSequenceBarrier) throws AlertException, InterruptedException, TimeoutException;
  
  void signalAllWhenBlocking();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\WaitStrategy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */