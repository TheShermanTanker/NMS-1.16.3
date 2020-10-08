/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Creeper;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CreeperIgniteEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean canceled;
/*    */   private boolean ignited;
/*    */   
/*    */   public CreeperIgniteEvent(@NotNull Creeper creeper, boolean ignited) {
/* 18 */     super((Entity)creeper);
/* 19 */     this.ignited = ignited;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Creeper getEntity() {
/* 25 */     return (Creeper)this.entity;
/*    */   }
/*    */   
/*    */   public boolean isIgnited() {
/* 29 */     return this.ignited;
/*    */   }
/*    */   
/*    */   public void setIgnited(boolean ignited) {
/* 33 */     this.ignited = ignited;
/*    */   }
/*    */   
/*    */   public boolean isCancelled() {
/* 37 */     return this.canceled;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 41 */     this.canceled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 47 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 52 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\CreeperIgniteEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */