/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityZoglin;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftZoglin extends CraftMonster implements Zoglin {
/*    */   public CraftZoglin(CraftServer server, EntityZoglin entity) {
/* 11 */     super(server, (EntityMonster)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBaby() {
/* 16 */     return getHandle().isBaby();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBaby(boolean flag) {
/* 21 */     getHandle().setBaby(flag);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityZoglin getHandle() {
/* 26 */     return (EntityZoglin)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     return "CraftZoglin";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 36 */     return EntityType.ZOGLIN;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAge() {
/* 41 */     return getHandle().isBaby() ? -1 : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAge(int i) {
/* 46 */     getHandle().setBaby((i < 0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAgeLock(boolean b) {}
/*    */ 
/*    */   
/*    */   public boolean getAgeLock() {
/* 55 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBaby() {
/* 60 */     getHandle().setBaby(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAdult() {
/* 65 */     getHandle().setBaby(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAdult() {
/* 70 */     return !getHandle().isBaby();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBreed() {
/* 75 */     return false;
/*    */   }
/*    */   
/*    */   public void setBreed(boolean b) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftZoglin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */