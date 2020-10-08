/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityPigZombie;
/*    */ import net.minecraft.server.v1_16_R2.EntityZombie;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftPigZombie extends CraftZombie implements PigZombie {
/*    */   public CraftPigZombie(CraftServer server, EntityPigZombie entity) {
/* 11 */     super(server, (EntityZombie)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAnger() {
/* 16 */     return getHandle().getAnger();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAnger(int level) {
/* 21 */     getHandle().setAnger(level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAngry(boolean angry) {
/* 26 */     setAnger(angry ? 400 : 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAngry() {
/* 31 */     return (getAnger() > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPigZombie getHandle() {
/* 36 */     return (EntityPigZombie)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return "CraftPigZombie";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 46 */     return EntityType.ZOMBIFIED_PIGLIN;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConverting() {
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getConversionTime() {
/* 56 */     throw new UnsupportedOperationException("Not supported by this Entity.");
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConversionTime(int time) {
/* 61 */     throw new UnsupportedOperationException("Not supported by this Entity.");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPigZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */