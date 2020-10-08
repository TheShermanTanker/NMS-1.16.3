package com.lmax.disruptor;

public interface EventTranslatorThreeArg<T, A, B, C> {
  void translateTo(T paramT, long paramLong, A paramA, B paramB, C paramC);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\EventTranslatorThreeArg.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */