/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityGolem;
/*    */ import net.minecraft.server.v1_16_R2.EntityIronGolem;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftIronGolem extends CraftGolem implements IronGolem {
/*    */   public CraftIronGolem(CraftServer server, EntityIronGolem entity) {
/* 10 */     super(server, (EntityGolem)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityIronGolem getHandle() {
/* 15 */     return (EntityIronGolem)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftIronGolem";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPlayerCreated() {
/* 25 */     return getHandle().isPlayerCreated();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPlayerCreated(boolean playerCreated) {
/* 30 */     getHandle().setPlayerCreated(playerCreated);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 35 */     return EntityType.IRON_GOLEM;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftIronGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */