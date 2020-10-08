/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityDrowned;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityZombie;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftDrowned extends CraftZombie implements Drowned, CraftRangedEntity<EntityDrowned> {
/*    */   public CraftDrowned(CraftServer server, EntityDrowned entity) {
/* 12 */     super(server, (EntityZombie)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityDrowned getHandle() {
/* 17 */     return (EntityDrowned)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 22 */     return "CraftDrowned";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 27 */     return EntityType.DROWNED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftDrowned.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */