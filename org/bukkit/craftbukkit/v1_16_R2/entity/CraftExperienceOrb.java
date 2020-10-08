/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityExperienceOrb;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.ExperienceOrb;
/*    */ 
/*    */ public class CraftExperienceOrb extends CraftEntity implements ExperienceOrb {
/*    */   public CraftExperienceOrb(CraftServer server, EntityExperienceOrb entity) {
/* 10 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getExperience() {
/* 15 */     return (getHandle()).value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExperience(int value) {
/* 20 */     (getHandle()).value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public UUID getTriggerEntityId() {
/* 25 */     return (getHandle()).triggerEntityId;
/*    */   }
/*    */   public UUID getSourceEntityId() {
/* 28 */     return (getHandle()).sourceEntityId;
/*    */   }
/*    */   public ExperienceOrb.SpawnReason getSpawnReason() {
/* 31 */     return (getHandle()).spawnReason;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityExperienceOrb getHandle() {
/* 37 */     return (EntityExperienceOrb)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return "CraftExperienceOrb";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 47 */     return EntityType.EXPERIENCE_ORB;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftExperienceOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */