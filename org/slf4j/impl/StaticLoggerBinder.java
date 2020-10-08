/*    */ package org.slf4j.impl;
/*    */ 
/*    */ import org.apache.logging.slf4j.Log4jLoggerFactory;
/*    */ import org.slf4j.ILoggerFactory;
/*    */ import org.slf4j.spi.LoggerFactoryBinder;
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
/*    */ public final class StaticLoggerBinder
/*    */   implements LoggerFactoryBinder
/*    */ {
/* 34 */   public static String REQUESTED_API_VERSION = "1.6";
/*    */   
/* 36 */   private static final String LOGGER_FACTORY_CLASS_STR = Log4jLoggerFactory.class.getName();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
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
/* 53 */   private final ILoggerFactory loggerFactory = (ILoggerFactory)new Log4jLoggerFactory();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static StaticLoggerBinder getSingleton() {
/* 62 */     return SINGLETON;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ILoggerFactory getLoggerFactory() {
/* 71 */     return this.loggerFactory;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLoggerFactoryClassStr() {
/* 80 */     return LOGGER_FACTORY_CLASS_STR;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\slf4j\impl\StaticLoggerBinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */