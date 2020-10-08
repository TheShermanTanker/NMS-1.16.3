package com.lmax.disruptor;

public interface SequenceBarrier {
  long waitFor(long paramLong) throws AlertException, InterruptedException, TimeoutException;
  
  long getCursor();
  
  boolean isAlerted();
  
  void alert();
  
  void clearAlert();
  
  void checkAlert() throws AlertException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\SequenceBarrier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */