package com.lmax.disruptor;

public interface EventHandler<T> {
  void onEvent(T paramT, long paramLong, boolean paramBoolean) throws Exception;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\EventHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */