package com.mysql.jdbc.log;

public interface Log {
  boolean isDebugEnabled();
  
  boolean isErrorEnabled();
  
  boolean isFatalEnabled();
  
  boolean isInfoEnabled();
  
  boolean isTraceEnabled();
  
  boolean isWarnEnabled();
  
  void logDebug(Object paramObject);
  
  void logDebug(Object paramObject, Throwable paramThrowable);
  
  void logError(Object paramObject);
  
  void logError(Object paramObject, Throwable paramThrowable);
  
  void logFatal(Object paramObject);
  
  void logFatal(Object paramObject, Throwable paramThrowable);
  
  void logInfo(Object paramObject);
  
  void logInfo(Object paramObject, Throwable paramThrowable);
  
  void logTrace(Object paramObject);
  
  void logTrace(Object paramObject, Throwable paramThrowable);
  
  void logWarn(Object paramObject);
  
  void logWarn(Object paramObject, Throwable paramThrowable);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\log\Log.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */