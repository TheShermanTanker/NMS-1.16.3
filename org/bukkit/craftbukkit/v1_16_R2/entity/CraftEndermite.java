/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityEndermite;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftEndermite extends CraftMonster implements Endermite {
/*    */   public CraftEndermite(CraftServer server, EntityEndermite entity) {
/* 11 */     super(server, (EntityMonster)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEndermite getHandle() {
/* 16 */     return (EntityEndermite)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftEndermite";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.ENDERMITE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPlayerSpawned() {
/* 31 */     return getHandle().isPlayerSpawned();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPlayerSpawned(boolean playerSpawned) {
/* 36 */     getHandle().setPlayerSpawned(playerSpawned);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftEndermite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */