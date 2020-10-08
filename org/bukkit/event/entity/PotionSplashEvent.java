/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.entity.ThrownPotion;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PotionSplashEvent
/*    */   extends ProjectileHitEvent implements Cancellable {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final Map<LivingEntity, Double> affectedEntities;
/*    */   
/*    */   public PotionSplashEvent(@NotNull ThrownPotion potion, @NotNull Map<LivingEntity, Double> affectedEntities) {
/* 22 */     super((Projectile)potion);
/*    */     
/* 24 */     this.affectedEntities = affectedEntities;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ThrownPotion getEntity() {
/* 30 */     return (ThrownPotion)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ThrownPotion getPotion() {
/* 40 */     return getEntity();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Collection<LivingEntity> getAffectedEntities() {
/* 50 */     return new ArrayList<>(this.affectedEntities.keySet());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getIntensity(@NotNull LivingEntity entity) {
/* 62 */     Double intensity = this.affectedEntities.get(entity);
/* 63 */     return (intensity != null) ? intensity.doubleValue() : 0.0D;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setIntensity(@NotNull LivingEntity entity, double intensity) {
/* 73 */     Validate.notNull(entity, "You must specify a valid entity.");
/* 74 */     if (intensity <= 0.0D) {
/* 75 */       this.affectedEntities.remove(entity);
/*    */     } else {
/* 77 */       this.affectedEntities.put(entity, Double.valueOf(Math.min(intensity, 1.0D)));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 83 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 88 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 94 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 99 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\PotionSplashEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */