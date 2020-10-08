/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityEvokerFangs;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.EvokerFangs;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class CraftEvokerFangs extends CraftEntity implements EvokerFangs {
/*    */   public CraftEvokerFangs(CraftServer server, EntityEvokerFangs entity) {
/* 13 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEvokerFangs getHandle() {
/* 18 */     return (EntityEvokerFangs)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 23 */     return "CraftEvokerFangs";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 28 */     return EntityType.EVOKER_FANGS;
/*    */   }
/*    */ 
/*    */   
/*    */   public LivingEntity getOwner() {
/* 33 */     EntityLiving owner = getHandle().getOwner();
/*    */     
/* 35 */     return (owner == null) ? null : (LivingEntity)owner.getBukkitEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOwner(LivingEntity owner) {
/* 40 */     getHandle().a((owner == null) ? null : ((CraftLivingEntity)owner).getHandle());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftEvokerFangs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */