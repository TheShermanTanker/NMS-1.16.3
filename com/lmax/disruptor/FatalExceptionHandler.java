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
/*    */ public final class FatalExceptionHandler
/*    */   implements ExceptionHandler<Object>
/*    */ {
/* 27 */   private static final Logger LOGGER = Logger.getLogger(FatalExceptionHandler.class.getName());
/*    */   
/*    */   private final Logger logger;
/*    */   
/*    */   public FatalExceptionHandler() {
/* 32 */     this.logger = LOGGER;
/*    */   }
/*    */ 
/*    */   
/*    */   public FatalExceptionHandler(Logger logger) {
/* 37 */     this.logger = logger;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleEventException(Throwable ex, long sequence, Object event) {
/* 43 */     this.logger.log(Level.SEVERE, "Exception processing: " + sequence + " " + event, ex);
/*    */     
/* 45 */     throw new RuntimeException(ex);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleOnStartException(Throwable ex) {
/* 51 */     this.logger.log(Level.SEVERE, "Exception during onStart()", ex);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleOnShutdownException(Throwable ex) {
/* 57 */     this.logger.log(Level.SEVERE, "Exception during onShutdown()", ex);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\FatalExceptionHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */