/*     */ package org.bukkit.entity;
/*     */ 
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface AbstractArrow
/*     */   extends Projectile
/*     */ {
/*     */   int getKnockbackStrength();
/*     */   
/*     */   void setKnockbackStrength(int paramInt);
/*     */   
/*     */   double getDamage();
/*     */   
/*     */   void setDamage(double paramDouble);
/*     */   
/*     */   int getPierceLevel();
/*     */   
/*     */   void setPierceLevel(int paramInt);
/*     */   
/*     */   boolean isCritical();
/*     */   
/*     */   void setCritical(boolean paramBoolean);
/*     */   
/*     */   boolean isInBlock();
/*     */   
/*     */   @Nullable
/*     */   Block getAttachedBlock();
/*     */   
/*     */   @NotNull
/*     */   PickupStatus getPickupStatus();
/*     */   
/*     */   void setPickupStatus(@NotNull PickupStatus paramPickupStatus);
/*     */   
/*     */   boolean isShotFromCrossbow();
/*     */   
/*     */   void setShotFromCrossbow(boolean paramBoolean);
/*     */   
/*     */   @NotNull
/*     */   ItemStack getItemStack();
/*     */   
/*     */   public enum PickupStatus
/*     */   {
/* 134 */     DISALLOWED,
/*     */ 
/*     */ 
/*     */     
/* 138 */     ALLOWED,
/*     */ 
/*     */ 
/*     */     
/* 142 */     CREATIVE_ONLY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   default PickupRule getPickupRule() {
/* 165 */     return PickupRule.valueOf(getPickupStatus().name());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   default void setPickupRule(PickupRule rule) {
/* 176 */     setPickupStatus(PickupStatus.valueOf(rule.name()));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public enum PickupRule {
/* 181 */     DISALLOWED,
/* 182 */     ALLOWED,
/* 183 */     CREATIVE_ONLY;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\AbstractArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */