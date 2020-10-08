/*    */ package com.lmax.disruptor.dsl;
/*    */ 
/*    */ import com.lmax.disruptor.BatchEventProcessor;
/*    */ import com.lmax.disruptor.EventHandler;
/*    */ import com.lmax.disruptor.EventProcessor;
/*    */ import com.lmax.disruptor.ExceptionHandler;
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
/*    */ public class ExceptionHandlerSetting<T>
/*    */ {
/*    */   private final EventHandler<T> eventHandler;
/*    */   private final ConsumerRepository<T> consumerRepository;
/*    */   
/*    */   ExceptionHandlerSetting(EventHandler<T> eventHandler, ConsumerRepository<T> consumerRepository) {
/* 39 */     this.eventHandler = eventHandler;
/* 40 */     this.consumerRepository = consumerRepository;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void with(ExceptionHandler<? super T> exceptionHandler) {
/* 51 */     EventProcessor eventProcessor = this.consumerRepository.getEventProcessorFor(this.eventHandler);
/* 52 */     if (eventProcessor instanceof BatchEventProcessor) {
/*    */       
/* 54 */       ((BatchEventProcessor)eventProcessor).setExceptionHandler(exceptionHandler);
/* 55 */       this.consumerRepository.getBarrierFor(this.eventHandler).alert();
/*    */     }
/*    */     else {
/*    */       
/* 59 */       throw new RuntimeException("EventProcessor: " + eventProcessor + " is not a BatchEventProcessor " + "and does not support exception handlers");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\dsl\ExceptionHandlerSetting.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */