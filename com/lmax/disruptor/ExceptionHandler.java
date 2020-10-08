package com.lmax.disruptor;

public interface ExceptionHandler<T> {
  void handleEventException(Throwable paramThrowable, long paramLong, T paramT);
  
  void handleOnStartException(Throwable paramThrowable);
  
  void handleOnShutdownException(Throwable paramThrowable);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\ExceptionHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */