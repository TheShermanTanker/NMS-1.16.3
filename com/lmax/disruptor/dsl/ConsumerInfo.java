package com.lmax.disruptor.dsl;

import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;
import java.util.concurrent.Executor;

interface ConsumerInfo {
  Sequence[] getSequences();
  
  SequenceBarrier getBarrier();
  
  boolean isEndOfChain();
  
  void start(Executor paramExecutor);
  
  void halt();
  
  void markAsUsedInBarrier();
  
  boolean isRunning();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\dsl\ConsumerInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */