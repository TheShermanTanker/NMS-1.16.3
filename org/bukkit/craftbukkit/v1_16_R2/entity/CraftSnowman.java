/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityGolem;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntitySnowman;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftSnowman extends CraftGolem implements Snowman, CraftRangedEntity<EntitySnowman> {
/*    */   public CraftSnowman(CraftServer server, EntitySnowman entity) {
/* 11 */     super(server, (EntityGolem)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDerp() {
/* 16 */     return !getHandle().hasPumpkin();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDerp(boolean derpMode) {
/* 21 */     getHandle().setHasPumpkin(!derpMode);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySnowman getHandle() {
/* 26 */     return (EntitySnowman)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     return "CraftSnowman";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 36 */     return EntityType.SNOWMAN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSnowman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */