package com.lmax.disruptor;

public interface EventTranslatorVararg<T> {
  void translateTo(T paramT, long paramLong, Object... paramVarArgs);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\EventTranslatorVararg.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */