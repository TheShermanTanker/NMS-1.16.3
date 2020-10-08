/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityToggleGlideEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancel = false;
/*    */   private final boolean isGliding;
/*    */   
/*    */   public EntityToggleGlideEvent(@NotNull LivingEntity who, boolean isGliding) {
/* 24 */     super((Entity)who);
/* 25 */     this.isGliding = isGliding;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 30 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 35 */     this.cancel = cancel;
/*    */   }
/*    */   
/*    */   public boolean isGliding() {
/* 39 */     return this.isGliding;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 45 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 50 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityToggleGlideEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */