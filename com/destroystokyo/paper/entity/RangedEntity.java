/*    */ package com.destroystokyo.paper.entity;
/*    */ 
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Mob;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface RangedEntity
/*    */   extends Mob
/*    */ {
/*    */   void rangedAttack(@NotNull LivingEntity paramLivingEntity, float paramFloat);
/*    */   
/*    */   void setChargingAttack(boolean paramBoolean);
/*    */   
/*    */   default boolean isChargingAttack() {
/* 29 */     return isHandRaised();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\RangedEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */