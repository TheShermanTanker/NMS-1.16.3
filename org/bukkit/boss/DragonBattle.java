/*     */ package org.bukkit.boss;
/*     */ 
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.EnderDragon;
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
/*     */ public interface DragonBattle
/*     */ {
/*     */   @Nullable
/*     */   EnderDragon getEnderDragon();
/*     */   
/*     */   @NotNull
/*     */   BossBar getBossBar();
/*     */   
/*     */   @Nullable
/*     */   Location getEndPortalLocation();
/*     */   
/*     */   boolean generateEndPortal(boolean paramBoolean);
/*     */   
/*     */   boolean hasBeenPreviouslyKilled();
/*     */   
/*     */   void initiateRespawn();
/*     */   
/*     */   @NotNull
/*     */   RespawnPhase getRespawnPhase();
/*     */   
/*     */   boolean setRespawnPhase(@NotNull RespawnPhase paramRespawnPhase);
/*     */   
/*     */   void resetCrystals();
/*     */   
/*     */   public enum RespawnPhase
/*     */   {
/*  98 */     START,
/*     */ 
/*     */ 
/*     */     
/* 102 */     PREPARING_TO_SUMMON_PILLARS,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     SUMMONING_PILLARS,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     SUMMONING_DRAGON,
/*     */ 
/*     */ 
/*     */     
/* 117 */     END,
/*     */ 
/*     */ 
/*     */     
/* 121 */     NONE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\boss\DragonBattle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */