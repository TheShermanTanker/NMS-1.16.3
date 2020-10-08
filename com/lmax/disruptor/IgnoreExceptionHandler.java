/*    */ package com.lmax.disruptor;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
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
/*    */ public final class IgnoreExceptionHandler
/*    */   implements ExceptionHandler<Object>
/*    */ {
/* 27 */   private static final Logger LOGGER = Logger.getLogger(IgnoreExceptionHandler.class.getName());
/*    */   
/*    */   private final Logger logger;
/*    */   
/*    */   public IgnoreExceptionHandler() {
/* 32 */     this.logger = LOGGER;
/*    */   }
/*    */ 
/*    */   
/*    */   public IgnoreExceptionHandler(Logger logger) {
/* 37 */     this.logger = logger;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleEventException(Throwable ex, long sequence, Object event) {
/* 43 */     this.logger.log(Level.INFO, "Exception processing: " + sequence + " " + event, ex);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleOnStartException(Throwable ex) {
/* 49 */     this.logger.log(Level.INFO, "Exception during onStart()", ex);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleOnShutdownException(Throwable ex) {
/* 55 */     this.logger.log(Level.INFO, "Exception during onShutdown()", ex);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\IgnoreExceptionHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */