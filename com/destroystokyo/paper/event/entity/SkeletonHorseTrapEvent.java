/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.SkeletonHorse;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SkeletonHorseTrapEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   
/*    */   public SkeletonHorseTrapEvent(@NotNull SkeletonHorse horse) {
/* 17 */     super((Entity)horse);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public SkeletonHorse getEntity() {
/* 23 */     return (SkeletonHorse)super.getEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 28 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 33 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 39 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 44 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\SkeletonHorseTrapEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */