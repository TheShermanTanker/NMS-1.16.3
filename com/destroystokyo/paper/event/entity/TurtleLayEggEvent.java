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
/*    */ public class TurtleLayEggEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled = false;
/*    */   @NotNull
/*    */   private final Location location;
/*    */   private int eggCount;
/*    */   
/*    */   public TurtleLayEggEvent(@NotNull Turtle turtle, @NotNull Location location, int eggCount) {
/* 21 */     super((Entity)turtle);
/* 22 */     this.location = location;
/* 23 */     this.eggCount = eggCount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Turtle getEntity() {
/* 33 */     return (Turtle)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getLocation() {
/* 43 */     return this.location;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEggCount() {
/* 52 */     return this.eggCount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEggCount(int eggCount) {
/* 61 */     if (eggCount < 1) {
/* 62 */       this.cancelled = true;
/*    */       return;
/*    */     } 
/* 65 */     eggCount = Math.min(eggCount, 4);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 70 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 75 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 80 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 85 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\TurtleLayEggEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */