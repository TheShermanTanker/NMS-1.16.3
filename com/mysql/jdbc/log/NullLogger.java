/*    */ package com.mysql.jdbc.log;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NullLogger
/*    */   implements Log
/*    */ {
/*    */   public NullLogger(String instanceName) {}
/*    */   
/*    */   public boolean isDebugEnabled() {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isErrorEnabled() {
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isFatalEnabled() {
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInfoEnabled() {
/* 65 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isTraceEnabled() {
/* 72 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isWarnEnabled() {
/* 79 */     return false;
/*    */   }
/*    */   
/*    */   public void logDebug(Object msg) {}
/*    */   
/*    */   public void logDebug(Object msg, Throwable thrown) {}
/*    */   
/*    */   public void logError(Object msg) {}
/*    */   
/*    */   public void logError(Object msg, Throwable thrown) {}
/*    */   
/*    */   public void logFatal(Object msg) {}
/*    */   
/*    */   public void logFatal(Object msg, Throwable thrown) {}
/*    */   
/*    */   public void logInfo(Object msg) {}
/*    */   
/*    */   public void logInfo(Object msg, Throwable thrown) {}
/*    */   
/*    */   public void logTrace(Object msg) {}
/*    */   
/*    */   public void logTrace(Object msg, Throwable thrown) {}
/*    */   
/*    */   public void logWarn(Object msg) {}
/*    */   
/*    */   public void logWarn(Object msg, Throwable thrown) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\log\NullLogger.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */