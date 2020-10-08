/*    */ package org.apache.logging.slf4j;
/*    */ 
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.spi.AbstractLoggerAdapter;
/*    */ import org.apache.logging.log4j.spi.LoggerContext;
/*    */ import org.apache.logging.log4j.util.ReflectionUtil;
/*    */ import org.slf4j.ILoggerFactory;
/*    */ import org.slf4j.Logger;
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
/*    */ public class Log4jLoggerFactory
/*    */   extends AbstractLoggerAdapter<Logger>
/*    */   implements ILoggerFactory
/*    */ {
/* 31 */   private static final String FQCN = Log4jLoggerFactory.class.getName();
/*    */   
/*    */   private static final String PACKAGE = "org.slf4j";
/*    */   
/*    */   protected Logger newLogger(String name, LoggerContext context) {
/* 36 */     String key = "ROOT".equals(name) ? "" : name;
/* 37 */     return (Logger)new Log4jLogger(context.getLogger(key), name);
/*    */   }
/*    */ 
/*    */   
/*    */   protected LoggerContext getContext() {
/* 42 */     Class<?> anchor = ReflectionUtil.getCallerClass(FQCN, "org.slf4j");
/* 43 */     return (anchor == null) ? LogManager.getContext() : getContext(ReflectionUtil.getCallerClass(anchor));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\slf4j\Log4jLoggerFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */