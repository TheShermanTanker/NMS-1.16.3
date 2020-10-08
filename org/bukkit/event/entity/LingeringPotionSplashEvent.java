/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.AreaEffectCloud;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.entity.ThrownPotion;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class LingeringPotionSplashEvent
/*    */   extends ProjectileHitEvent implements Cancellable {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final AreaEffectCloud entity;
/*    */   
/*    */   public LingeringPotionSplashEvent(@NotNull ThrownPotion potion, @NotNull AreaEffectCloud entity) {
/* 18 */     super((Projectile)potion);
/* 19 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ThrownPotion getEntity() {
/* 25 */     return (ThrownPotion)super.getEntity();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AreaEffectCloud getAreaEffectCloud() {
/* 35 */     return this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 40 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 45 */     this.cancelled = cancel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\LingeringPotionSplashEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */