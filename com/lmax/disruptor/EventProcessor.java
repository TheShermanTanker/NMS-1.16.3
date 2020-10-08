package com.lmax.disruptor;

public interface EventProcessor extends Runnable {
  Sequence getSequence();
  
  void halt();
  
  boolean isRunning();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\EventProcessor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */