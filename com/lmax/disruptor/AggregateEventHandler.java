/*    */ package com.lmax.disruptor;
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
/*    */ public final class AggregateEventHandler<T>
/*    */   implements EventHandler<T>, LifecycleAware
/*    */ {
/*    */   private final EventHandler<T>[] eventHandlers;
/*    */   
/*    */   @SafeVarargs
/*    */   public AggregateEventHandler(EventHandler<T>... eventHandlers) {
/* 36 */     this.eventHandlers = eventHandlers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEvent(T event, long sequence, boolean endOfBatch) throws Exception {
/* 43 */     for (EventHandler<T> eventHandler : this.eventHandlers)
/*    */     {
/* 45 */       eventHandler.onEvent(event, sequence, endOfBatch);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onStart() {
/* 52 */     for (EventHandler<T> eventHandler : this.eventHandlers) {
/*    */       
/* 54 */       if (eventHandler instanceof LifecycleAware)
/*    */       {
/* 56 */         ((LifecycleAware)eventHandler).onStart();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onShutdown() {
/* 64 */     for (EventHandler<T> eventHandler : this.eventHandlers) {
/*    */       
/* 66 */       if (eventHandler instanceof LifecycleAware)
/*    */       {
/* 68 */         ((LifecycleAware)eventHandler).onShutdown();
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\AggregateEventHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */