/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Turtle;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class TurtleStartDiggingEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public TurtleStartDiggingEvent(@NotNull Turtle turtle, @NotNull Location location) {
/* 19 */     super((Entity)turtle);
/* 20 */     this.location = location;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   private final Location location;
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Turtle getEntity() {
/* 30 */     return (Turtle)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getLocation() {
/* 40 */     return this.location;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 45 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 50 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 55 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 60 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\TurtleStartDiggingEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */