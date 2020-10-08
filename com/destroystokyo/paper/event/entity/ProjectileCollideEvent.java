/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProjectileCollideEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/*    */   @NotNull
/*    */   private final Entity collidedWith;
/*    */   
/*    */   @NotNull
/*    */   public Entity getCollidedWith() {
/* 25 */     return this.collidedWith;
/*    */   }
/*    */   
/*    */   public ProjectileCollideEvent(@NotNull Projectile what, @NotNull Entity collidedWith) {
/* 29 */     super((Entity)what);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 56 */     this.cancelled = false;
/*    */     this.collidedWith = collidedWith;
/*    */   }
/*    */   @NotNull
/* 60 */   public Projectile getEntity() { return (Projectile)super.getEntity(); } private static final HandlerList handlerList = new HandlerList(); public boolean isCancelled() { return this.cancelled; }
/*    */   private boolean cancelled;
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() { return handlerList; } @NotNull
/*    */   public HandlerList getHandlers() { return handlerList; } public void setCancelled(boolean cancel) {
/* 65 */     this.cancelled = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\ProjectileCollideEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */