/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EntityToggleSwimEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancel = false;
/*    */   private final boolean isSwimming;
/*    */   
/*    */   public EntityToggleSwimEvent(@NotNull LivingEntity who, boolean isSwimming) {
/* 18 */     super((Entity)who);
/* 19 */     this.isSwimming = isSwimming;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 24 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 29 */     this.cancel = cancel;
/*    */   }
/*    */   
/*    */   public boolean isSwimming() {
/* 33 */     return this.isSwimming;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityToggleSwimEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */