/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
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
/*     */ public final class Log
/*     */ {
/*     */   public static void trace(Object... messages) {
/*  28 */     log(Level.FINEST, messages);
/*     */   }
/*     */   
/*     */   public static void trace(Supplier<String> supplier) {
/*  32 */     log(Level.FINEST, supplier);
/*     */   }
/*     */   
/*     */   public static void debug(Supplier<String> supplier) {
/*  36 */     log(Level.FINE, supplier);
/*     */   }
/*     */   
/*     */   public static void debug(Object... messages) {
/*  40 */     log(Level.FINE, messages);
/*     */   }
/*     */   
/*     */   public static void info(Object... messages) {
/*  44 */     log(Level.INFO, messages);
/*     */   }
/*     */   
/*     */   public static void warn(Object... messages) {
/*  48 */     log(Level.WARNING, messages);
/*     */   }
/*     */   
/*     */   public static void error(Object... messages) {
/*  52 */     log(Level.SEVERE, messages);
/*     */   }
/*     */   
/*     */   public static boolean isDebugEnabled() {
/*  56 */     return isEnabled(Level.FINE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void render(PrintStream out, Object message) {
/*  63 */     if (message != null && message.getClass().isArray()) {
/*  64 */       Object[] array = (Object[])message;
/*     */       
/*  66 */       out.print("[");
/*  67 */       for (int i = 0; i < array.length; i++) {
/*  68 */         out.print(array[i]);
/*  69 */         if (i + 1 < array.length) {
/*  70 */           out.print(",");
/*     */         }
/*     */       } 
/*  73 */       out.print("]");
/*     */     } else {
/*     */       
/*  76 */       out.print(message);
/*     */     } 
/*     */   }
/*     */   
/*     */   static LogRecord createRecord(Level level, Object... messages) {
/*  81 */     Throwable cause = null;
/*  82 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*  83 */     PrintStream ps = new PrintStream(baos);
/*  84 */     for (int i = 0; i < messages.length; i++) {
/*     */       
/*  86 */       if (i + 1 == messages.length && messages[i] instanceof Throwable) {
/*  87 */         cause = (Throwable)messages[i];
/*     */       } else {
/*     */         
/*  90 */         render(ps, messages[i]);
/*     */       } 
/*     */     } 
/*  93 */     ps.close();
/*  94 */     LogRecord r = new LogRecord(level, baos.toString());
/*  95 */     r.setThrown(cause);
/*  96 */     return r;
/*     */   }
/*     */   
/*     */   static LogRecord createRecord(Level level, Supplier<String> message) {
/* 100 */     return new LogRecord(level, message.get());
/*     */   }
/*     */   
/*     */   static void log(Level level, Supplier<String> message) {
/* 104 */     logr(level, () -> createRecord(level, message));
/*     */   }
/*     */   
/*     */   static void log(Level level, Object... messages) {
/* 108 */     logr(level, () -> createRecord(level, messages));
/*     */   }
/*     */   
/*     */   static void logr(Level level, Supplier<LogRecord> record) {
/* 112 */     Logger logger = Logger.getLogger("org.jline");
/* 113 */     if (logger.isLoggable(level)) {
/*     */       
/* 115 */       LogRecord tmp = record.get();
/* 116 */       tmp.setLoggerName(logger.getName());
/* 117 */       logger.log(tmp);
/*     */     } 
/*     */   }
/*     */   
/*     */   static boolean isEnabled(Level level) {
/* 122 */     Logger logger = Logger.getLogger("org.jline");
/* 123 */     return logger.isLoggable(level);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\Log.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */