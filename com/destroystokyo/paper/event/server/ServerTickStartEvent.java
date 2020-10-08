/*    */ package com.destroystokyo.paper.event.server;
/*    */ 
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ServerTickStartEvent
/*    */   extends Event {
/*  9 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final int tickNumber;
/*    */   
/*    */   public ServerTickStartEvent(int tickNumber) {
/* 13 */     this.tickNumber = tickNumber;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTickNumber() {
/* 20 */     return this.tickNumber;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 25 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 30 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\server\ServerTickStartEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */