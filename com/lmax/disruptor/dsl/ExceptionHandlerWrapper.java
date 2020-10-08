/*    */ package com.lmax.disruptor.dsl;
/*    */ 
/*    */ import com.lmax.disruptor.ExceptionHandler;
/*    */ import com.lmax.disruptor.FatalExceptionHandler;
/*    */ 
/*    */ public class ExceptionHandlerWrapper<T>
/*    */   implements ExceptionHandler<T> {
/*  8 */   private ExceptionHandler<? super T> delegate = (ExceptionHandler<? super T>)new FatalExceptionHandler();
/*    */ 
/*    */   
/*    */   public void switchTo(ExceptionHandler<? super T> exceptionHandler) {
/* 12 */     this.delegate = exceptionHandler;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleEventException(Throwable ex, long sequence, T event) {
/* 18 */     this.delegate.handleEventException(ex, sequence, event);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleOnStartException(Throwable ex) {
/* 24 */     this.delegate.handleOnStartException(ex);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleOnShutdownException(Throwable ex) {
/* 30 */     this.delegate.handleOnShutdownException(ex);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\dsl\ExceptionHandlerWrapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */