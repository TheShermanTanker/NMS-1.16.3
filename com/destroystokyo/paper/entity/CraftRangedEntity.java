/*    */ package com.destroystokyo.paper.entity;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public interface CraftRangedEntity<T extends net.minecraft.server.v1_16_R2.IRangedEntity>
/*    */   extends RangedEntity
/*    */ {
/*    */   T getHandle();
/*    */   
/*    */   default void rangedAttack(LivingEntity target, float charge) {
/* 12 */     getHandle().rangedAttack(((CraftLivingEntity)target).getHandle(), charge);
/*    */   }
/*    */ 
/*    */   
/*    */   default void setChargingAttack(boolean raiseHands) {
/* 17 */     getHandle().setChargingAttack(raiseHands);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\CraftRangedEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */