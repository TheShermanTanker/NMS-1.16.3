/*   */ package net.minecraft.server.v1_16_R2;
/*   */ 
/*   */ public interface IRangedEntity {
/*   */   default void rangedAttack(EntityLiving entityliving, float f) {
/* 5 */     a(entityliving, f);
/*   */   } void a(EntityLiving paramEntityLiving, float paramFloat);
/*   */   default void setChargingAttack(boolean charging) {
/* 8 */     setAggressive(charging);
/*   */   }
/*   */   
/*   */   void setAggressive(boolean paramBoolean);
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IRangedEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */