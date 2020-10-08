package com.lmax.disruptor;

public interface Sequenced {
  int getBufferSize();
  
  boolean hasAvailableCapacity(int paramInt);
  
  long remainingCapacity();
  
  long next();
  
  long next(int paramInt);
  
  long tryNext() throws InsufficientCapacityException;
  
  long tryNext(int paramInt) throws InsufficientCapacityException;
  
  void publish(long paramLong);
  
  void publish(long paramLong1, long paramLong2);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\Sequenced.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */