/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Turtle;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class TurtleGoHomeEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public TurtleGoHomeEvent(@NotNull Turtle turtle) {
/* 17 */     super((Entity)turtle);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Turtle getEntity() {
/* 27 */     return (Turtle)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 32 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 37 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 42 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 47 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\TurtleGoHomeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */