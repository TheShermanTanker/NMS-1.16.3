package com.lmax.disruptor;

public interface EventTranslatorTwoArg<T, A, B> {
  void translateTo(T paramT, long paramLong, A paramA, B paramB);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\EventTranslatorTwoArg.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */