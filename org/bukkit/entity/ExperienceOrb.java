/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ public interface ExperienceOrb
/*    */   extends Entity
/*    */ {
/*    */   int getExperience();
/*    */   
/*    */   void setExperience(int paramInt);
/*    */   
/*    */   @Nullable
/*    */   UUID getTriggerEntityId();
/*    */   
/*    */   @Nullable
/*    */   UUID getSourceEntityId();
/*    */   
/*    */   @Deprecated
/*    */   default boolean isFromBottle() {
/* 34 */     return (getSpawnReason() == SpawnReason.EXP_BOTTLE);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   SpawnReason getSpawnReason();
/*    */ 
/*    */   
/*    */   public enum SpawnReason
/*    */   {
/* 44 */     PLAYER_DEATH,
/*    */ 
/*    */ 
/*    */     
/* 48 */     ENTITY_DEATH,
/*    */ 
/*    */ 
/*    */     
/* 52 */     FURNACE,
/*    */ 
/*    */ 
/*    */     
/* 56 */     BREED,
/*    */ 
/*    */ 
/*    */     
/* 60 */     VILLAGER_TRADE,
/*    */ 
/*    */ 
/*    */     
/* 64 */     FISHING,
/*    */ 
/*    */ 
/*    */     
/* 68 */     BLOCK_BREAK,
/*    */ 
/*    */ 
/*    */     
/* 72 */     CUSTOM,
/*    */ 
/*    */ 
/*    */     
/* 76 */     EXP_BOTTLE,
/*    */ 
/*    */ 
/*    */     
/* 80 */     GRINDSTONE,
/*    */ 
/*    */ 
/*    */     
/* 84 */     UNKNOWN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\ExperienceOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */