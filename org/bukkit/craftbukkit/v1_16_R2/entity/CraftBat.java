/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAmbient;
/*    */ import net.minecraft.server.v1_16_R2.EntityBat;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftBat extends CraftAmbient implements Bat {
/*    */   public CraftBat(CraftServer server, EntityBat entity) {
/* 10 */     super(server, (EntityAmbient)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityBat getHandle() {
/* 15 */     return (EntityBat)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftBat";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 25 */     return EntityType.BAT;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAwake() {
/* 30 */     return !getHandle().isAsleep();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAwake(boolean state) {
/* 35 */     getHandle().setAsleep(!state);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */