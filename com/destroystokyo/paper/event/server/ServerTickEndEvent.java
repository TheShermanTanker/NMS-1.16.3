/*    */ package com.destroystokyo.paper.event.server;
/*    */ 
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerTickEndEvent
/*    */   extends Event
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final int tickNumber;
/*    */   private final double tickDuration;
/*    */   private final long timeEnd;
/*    */   
/*    */   public ServerTickEndEvent(int tickNumber, double tickDuration, long timeRemaining) {
/* 18 */     this.tickNumber = tickNumber;
/* 19 */     this.tickDuration = tickDuration;
/* 20 */     this.timeEnd = System.nanoTime() + timeRemaining;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTickNumber() {
/* 27 */     return this.tickNumber;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getTickDuration() {
/* 34 */     return this.tickDuration;
/*    */   }
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
/*    */   public long getTimeRemaining() {
/* 47 */     return this.timeEnd - System.nanoTime();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 52 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 57 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\server\ServerTickEndEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */