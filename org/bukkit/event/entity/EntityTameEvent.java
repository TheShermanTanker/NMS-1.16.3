/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.AnimalTamer;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EntityTameEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final AnimalTamer owner;
/*    */   
/*    */   public EntityTameEvent(@NotNull LivingEntity entity, @NotNull AnimalTamer owner) {
/* 18 */     super((Entity)entity);
/* 19 */     this.owner = owner;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getEntity() {
/* 25 */     return (LivingEntity)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 30 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 35 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AnimalTamer getOwner() {
/* 45 */     return this.owner;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 51 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 56 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityTameEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */