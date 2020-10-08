/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import org.bukkit.boss.DragonBattle;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public interface EnderDragon
/*    */   extends ComplexLivingEntity, Boss, Mob
/*    */ {
/*    */   @NotNull
/*    */   Phase getPhase();
/*    */   
/*    */   void setPhase(@NotNull Phase paramPhase);
/*    */   
/*    */   @Nullable
/*    */   DragonBattle getDragonBattle();
/*    */   
/*    */   int getDeathAnimationTicks();
/*    */   
/*    */   public enum Phase {
/* 21 */     CIRCLING,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 26 */     STRAFING,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 31 */     FLY_TO_PORTAL,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     LAND_ON_PORTAL,
/*    */ 
/*    */ 
/*    */     
/* 40 */     LEAVE_PORTAL,
/*    */ 
/*    */ 
/*    */     
/* 44 */     BREATH_ATTACK,
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 51 */     SEARCH_FOR_BREATH_ATTACK_TARGET,
/*    */ 
/*    */ 
/*    */     
/* 55 */     ROAR_BEFORE_ATTACK,
/*    */ 
/*    */ 
/*    */     
/* 59 */     CHARGE_PLAYER,
/*    */ 
/*    */ 
/*    */     
/* 63 */     DYING,
/*    */ 
/*    */ 
/*    */     
/* 67 */     HOVER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\EnderDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */