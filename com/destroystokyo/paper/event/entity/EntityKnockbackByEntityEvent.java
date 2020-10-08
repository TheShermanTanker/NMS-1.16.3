/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.bukkit.util.Vector;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityKnockbackByEntityEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   @NotNull
/*    */   private final Entity hitBy;
/*    */   
/*    */   private final float knockbackStrength;
/*    */   
/*    */   public EntityKnockbackByEntityEvent(@NotNull LivingEntity entity, @NotNull Entity hitBy, float knockbackStrength, @NotNull Vector acceleration) {
/* 25 */     super((Entity)entity);
/* 26 */     this.hitBy = hitBy;
/* 27 */     this.knockbackStrength = knockbackStrength;
/* 28 */     this.acceleration = acceleration;
/*    */   } @NotNull
/*    */   private final Vector acceleration; private boolean cancelled = false;
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 33 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 38 */     return handlers;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 43 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 48 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getEntity() {
/* 57 */     return (LivingEntity)super.getEntity();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getKnockbackStrength() {
/* 64 */     return this.knockbackStrength;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getHitBy() {
/* 72 */     return this.hitBy;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Vector getAcceleration() {
/* 80 */     return this.acceleration;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EntityKnockbackByEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */