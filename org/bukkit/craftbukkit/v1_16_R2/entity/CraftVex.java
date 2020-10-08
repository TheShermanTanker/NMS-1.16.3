/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityVex;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Mob;
/*    */ 
/*    */ public class CraftVex extends CraftMonster implements Vex {
/*    */   public CraftVex(CraftServer server, EntityVex entity) {
/* 13 */     super(server, (EntityMonster)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityVex getHandle() {
/* 18 */     return (EntityVex)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public Mob getSummoner() {
/* 23 */     EntityInsentient owner = getHandle().getOwner();
/* 24 */     return (owner != null) ? (Mob)owner.getBukkitEntity() : null;
/*    */   }
/*    */   
/*    */   public void setSummoner(Mob summoner) {
/* 28 */     getHandle().setOwner((summoner == null) ? null : ((CraftMob)summoner).getHandle());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 34 */     return "CraftVex";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 39 */     return EntityType.VEX;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCharging() {
/* 44 */     return getHandle().isCharging();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCharging(boolean charging) {
/* 49 */     getHandle().setCharging(charging);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftVex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */