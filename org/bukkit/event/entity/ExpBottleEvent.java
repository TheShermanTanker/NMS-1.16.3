/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.entity.ThrownExpBottle;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ExpBottleEvent
/*    */   extends ProjectileHitEvent {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   private int exp;
/*    */   private boolean showEffect = true;
/*    */   
/*    */   public ExpBottleEvent(@NotNull ThrownExpBottle bottle, int exp) {
/* 16 */     super((Projectile)bottle);
/* 17 */     this.exp = exp;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ThrownExpBottle getEntity() {
/* 23 */     return (ThrownExpBottle)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getShowEffect() {
/* 32 */     return this.showEffect;
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
/*    */   public void setShowEffect(boolean showEffect) {
/* 44 */     this.showEffect = showEffect;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExperience() {
/* 55 */     return this.exp;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setExperience(int exp) {
/* 66 */     this.exp = exp;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 72 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 77 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\ExpBottleEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */