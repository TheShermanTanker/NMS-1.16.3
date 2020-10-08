/*    */ package com.destroystokyo.paper.exception;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerEventException
/*    */   extends ServerPluginException
/*    */ {
/*    */   private final Listener listener;
/*    */   private final Event event;
/*    */   
/*    */   public ServerEventException(String message, Throwable cause, Plugin responsiblePlugin, Listener listener, Event event) {
/* 18 */     super(message, cause, responsiblePlugin);
/* 19 */     this.listener = (Listener)Preconditions.checkNotNull(listener, "listener");
/* 20 */     this.event = (Event)Preconditions.checkNotNull(event, "event");
/*    */   }
/*    */   
/*    */   public ServerEventException(Throwable cause, Plugin responsiblePlugin, Listener listener, Event event) {
/* 24 */     super(cause, responsiblePlugin);
/* 25 */     this.listener = (Listener)Preconditions.checkNotNull(listener, "listener");
/* 26 */     this.event = (Event)Preconditions.checkNotNull(event, "event");
/*    */   }
/*    */   
/*    */   protected ServerEventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Plugin responsiblePlugin, Listener listener, Event event) {
/* 30 */     super(message, cause, enableSuppression, writableStackTrace, responsiblePlugin);
/* 31 */     this.listener = (Listener)Preconditions.checkNotNull(listener, "listener");
/* 32 */     this.event = (Event)Preconditions.checkNotNull(event, "event");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Listener getListener() {
/* 41 */     return this.listener;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Event getEvent() {
/* 50 */     return this.event;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\exception\ServerEventException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */