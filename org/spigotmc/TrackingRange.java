/*    */ package org.spigotmc;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TrackingRange
/*    */ {
/*    */   public static int getEntityTrackingRange(Entity entity, int defaultRange) {
/* 26 */     if (entity instanceof net.minecraft.server.v1_16_R2.EntityEnderDragon) return defaultRange; 
/* 27 */     SpigotWorldConfig config = entity.world.spigotConfig;
/* 28 */     if (entity instanceof net.minecraft.server.v1_16_R2.EntityPlayer)
/*    */     {
/* 30 */       return config.playerTrackingRange;
/*    */     }
/*    */     
/* 33 */     switch (entity.activationType) {
/*    */       case RAIDER:
/*    */       case MONSTER:
/*    */       case FLYING_MONSTER:
/* 37 */         return config.monsterTrackingRange;
/*    */       case WATER:
/*    */       case VILLAGER:
/*    */       case ANIMAL:
/* 41 */         return config.animalTrackingRange;
/*    */     } 
/*    */     
/* 44 */     if (entity instanceof net.minecraft.server.v1_16_R2.EntityItemFrame || entity instanceof net.minecraft.server.v1_16_R2.EntityPainting || entity instanceof net.minecraft.server.v1_16_R2.EntityItem || entity instanceof net.minecraft.server.v1_16_R2.EntityExperienceOrb)
/*    */     {
/*    */       
/* 47 */       return config.miscTrackingRange;
/*    */     }
/*    */     
/* 50 */     return config.otherTrackingRange;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static TrackingRangeType getTrackingRangeType(Entity entity) {
/* 58 */     if (entity instanceof net.minecraft.server.v1_16_R2.EntityEnderDragon) return TrackingRangeType.ENDERDRAGON; 
/* 59 */     if (entity instanceof net.minecraft.server.v1_16_R2.EntityPlayer)
/*    */     {
/* 61 */       return TrackingRangeType.PLAYER;
/*    */     }
/*    */     
/* 64 */     switch (entity.activationType) {
/*    */       case RAIDER:
/*    */       case MONSTER:
/*    */       case FLYING_MONSTER:
/* 68 */         return TrackingRangeType.MONSTER;
/*    */       case WATER:
/*    */       case VILLAGER:
/*    */       case ANIMAL:
/* 72 */         return TrackingRangeType.ANIMAL;
/*    */     } 
/*    */     
/* 75 */     if (entity instanceof net.minecraft.server.v1_16_R2.EntityItemFrame || entity instanceof net.minecraft.server.v1_16_R2.EntityPainting || entity instanceof net.minecraft.server.v1_16_R2.EntityItem || entity instanceof net.minecraft.server.v1_16_R2.EntityExperienceOrb)
/*    */     {
/*    */       
/* 78 */       return TrackingRangeType.MISC;
/*    */     }
/*    */     
/* 81 */     return TrackingRangeType.OTHER;
/*    */   }
/*    */   
/*    */   public enum TrackingRangeType
/*    */   {
/* 86 */     PLAYER,
/* 87 */     ANIMAL,
/* 88 */     MONSTER,
/* 89 */     MISC,
/* 90 */     OTHER,
/* 91 */     ENDERDRAGON;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\TrackingRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */