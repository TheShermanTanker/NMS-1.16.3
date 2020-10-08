/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import java.util.logging.ConsoleHandler;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.LogRecord;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class ForwardLogHandler extends ConsoleHandler {
/* 12 */   private Map<String, Logger> cachedLoggers = new ConcurrentHashMap<>();
/*    */   
/*    */   private Logger getLogger(String name) {
/* 15 */     Logger logger = this.cachedLoggers.get(name);
/* 16 */     if (logger == null) {
/* 17 */       logger = LogManager.getLogger(name);
/* 18 */       this.cachedLoggers.put(name, logger);
/*    */     } 
/*    */     
/* 21 */     return logger;
/*    */   }
/*    */ 
/*    */   
/*    */   public void publish(LogRecord record) {
/* 26 */     Logger logger = getLogger(String.valueOf(record.getLoggerName()));
/* 27 */     Throwable exception = record.getThrown();
/* 28 */     Level level = record.getLevel();
/* 29 */     String message = getFormatter().formatMessage(record);
/*    */     
/* 31 */     if (level == Level.SEVERE) {
/* 32 */       logger.error(message, exception);
/* 33 */     } else if (level == Level.WARNING) {
/* 34 */       logger.warn(message, exception);
/* 35 */     } else if (level == Level.INFO) {
/* 36 */       logger.info(message, exception);
/* 37 */     } else if (level == Level.CONFIG) {
/* 38 */       logger.debug(message, exception);
/*    */     } else {
/* 40 */       logger.trace(message, exception);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void flush() {}
/*    */   
/*    */   public void close() throws SecurityException {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\ForwardLogHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */