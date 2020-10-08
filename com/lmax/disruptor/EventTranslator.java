package com.lmax.disruptor;

public interface EventTranslator<T> {
  void translateTo(T paramT, long paramLong);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\EventTranslator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */