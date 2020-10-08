package com.lmax.disruptor;

public interface WorkHandler<T> {
  void onEvent(T paramT) throws Exception;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\WorkHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */