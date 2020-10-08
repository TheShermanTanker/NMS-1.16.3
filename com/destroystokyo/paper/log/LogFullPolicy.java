/*    */ package com.destroystokyo.paper.log;
/*    */ 
/*    */ import org.apache.logging.log4j.Level;
/*    */ import org.apache.logging.log4j.core.async.AsyncQueueFullPolicy;
/*    */ import org.apache.logging.log4j.core.async.EventRoute;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class LogFullPolicy
/*    */   implements AsyncQueueFullPolicy
/*    */ {
/*    */   public EventRoute getRoute(long backgroundThreadId, Level level) {
/* 15 */     return EventRoute.ENQUEUE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\log\LogFullPolicy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */