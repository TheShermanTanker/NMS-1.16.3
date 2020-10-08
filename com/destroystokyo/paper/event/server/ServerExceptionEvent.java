/*    */ package com.destroystokyo.paper.event.server;
/*    */ 
/*    */ import com.destroystokyo.paper.exception.ServerException;
/*    */ import com.google.common.base.Preconditions;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerExceptionEvent
/*    */   extends Event
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */   
/*    */   public ServerExceptionEvent(@NotNull ServerException exception) {
/* 19 */     super(!Bukkit.isPrimaryThread());
/* 20 */     this.exception = (ServerException)Preconditions.checkNotNull(exception, "exception");
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   private ServerException exception;
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ServerException getException() {
/* 30 */     return this.exception;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 36 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 41 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\server\ServerExceptionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */