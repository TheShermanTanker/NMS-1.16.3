/*     */ package com.mysql.jdbc.log;
/*     */ 
/*     */ import com.mysql.jdbc.Util;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardLogger
/*     */   implements Log
/*     */ {
/*     */   private static final int FATAL = 0;
/*     */   private static final int ERROR = 1;
/*     */   private static final int WARN = 2;
/*     */   private static final int INFO = 3;
/*     */   private static final int DEBUG = 4;
/*     */   private static final int TRACE = 5;
/*     */   private boolean logLocationInfo = true;
/*     */   
/*     */   public StandardLogger(String name) {
/*  56 */     this(name, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StandardLogger(String name, boolean logLocationInfo) {
/*  64 */     this.logLocationInfo = logLocationInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDebugEnabled() {
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isErrorEnabled() {
/*  78 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFatalEnabled() {
/*  85 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInfoEnabled() {
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTraceEnabled() {
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWarnEnabled() {
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logDebug(Object message) {
/* 116 */     logInternal(4, message, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logDebug(Object message, Throwable exception) {
/* 128 */     logInternal(4, message, exception);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logError(Object message) {
/* 138 */     logInternal(1, message, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logError(Object message, Throwable exception) {
/* 150 */     logInternal(1, message, exception);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logFatal(Object message) {
/* 160 */     logInternal(0, message, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logFatal(Object message, Throwable exception) {
/* 172 */     logInternal(0, message, exception);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logInfo(Object message) {
/* 182 */     logInternal(3, message, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logInfo(Object message, Throwable exception) {
/* 194 */     logInternal(3, message, exception);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logTrace(Object message) {
/* 204 */     logInternal(5, message, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logTrace(Object message, Throwable exception) {
/* 216 */     logInternal(5, message, exception);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logWarn(Object message) {
/* 226 */     logInternal(2, message, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logWarn(Object message, Throwable exception) {
/* 238 */     logInternal(2, message, exception);
/*     */   }
/*     */   
/*     */   protected String logInternal(int level, Object msg, Throwable exception) {
/* 242 */     StringBuilder msgBuf = new StringBuilder();
/* 243 */     msgBuf.append((new Date()).toString());
/* 244 */     msgBuf.append(" ");
/*     */     
/* 246 */     switch (level) {
/*     */       case 0:
/* 248 */         msgBuf.append("FATAL: ");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/* 253 */         msgBuf.append("ERROR: ");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 258 */         msgBuf.append("WARN: ");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 263 */         msgBuf.append("INFO: ");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 268 */         msgBuf.append("DEBUG: ");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 273 */         msgBuf.append("TRACE: ");
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 278 */     if (msg instanceof com.mysql.jdbc.profiler.ProfilerEvent) {
/* 279 */       msgBuf.append(msg.toString());
/*     */     } else {
/*     */       
/* 282 */       if (this.logLocationInfo && level != 5) {
/* 283 */         Throwable locationException = new Throwable();
/* 284 */         msgBuf.append(LogUtils.findCallingClassAndMethod(locationException));
/* 285 */         msgBuf.append(" ");
/*     */       } 
/*     */       
/* 288 */       if (msg != null) {
/* 289 */         msgBuf.append(String.valueOf(msg));
/*     */       }
/*     */     } 
/*     */     
/* 293 */     if (exception != null) {
/* 294 */       msgBuf.append("\n");
/* 295 */       msgBuf.append("\n");
/* 296 */       msgBuf.append("EXCEPTION STACK TRACE:");
/* 297 */       msgBuf.append("\n");
/* 298 */       msgBuf.append("\n");
/* 299 */       msgBuf.append(Util.stackTraceToString(exception));
/*     */     } 
/*     */     
/* 302 */     String messageAsString = msgBuf.toString();
/*     */     
/* 304 */     System.err.println(messageAsString);
/*     */     
/* 306 */     return messageAsString;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\log\StandardLogger.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */