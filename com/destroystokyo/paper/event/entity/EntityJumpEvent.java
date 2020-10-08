/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class EntityJumpEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean canceled;
/*    */   
/*    */   public EntityJumpEvent(@NotNull LivingEntity entity) {
/* 19 */     super((Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getEntity() {
/* 25 */     return (LivingEntity)this.entity;
/*    */   }
/*    */   
/*    */   public boolean isCancelled() {
/* 29 */     return this.canceled;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 33 */     this.canceled = cancel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EntityJumpEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */