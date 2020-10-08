/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFireball;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Fireball;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class CraftFireball extends AbstractProjectile implements Fireball {
/*    */   public CraftFireball(CraftServer server, EntityFireball entity) {
/* 14 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getYield() {
/* 19 */     return (getHandle()).bukkitYield;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isIncendiary() {
/* 24 */     return (getHandle()).isIncendiary;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIsIncendiary(boolean isIncendiary) {
/* 29 */     (getHandle()).isIncendiary = isIncendiary;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setYield(float yield) {
/* 34 */     (getHandle()).bukkitYield = yield;
/*    */   }
/*    */ 
/*    */   
/*    */   public ProjectileSource getShooter() {
/* 39 */     return (getHandle()).projectileSource;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShooter(ProjectileSource shooter) {
/* 44 */     if (shooter instanceof CraftLivingEntity) {
/* 45 */       getHandle().setShooter((Entity)((CraftLivingEntity)shooter).getHandle());
/*    */     } else {
/* 47 */       getHandle().setShooter(null);
/*    */     } 
/* 49 */     (getHandle()).projectileSource = shooter;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vector getDirection() {
/* 54 */     return new Vector((getHandle()).dirX, (getHandle()).dirY, (getHandle()).dirZ);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDirection(Vector direction) {
/* 59 */     Validate.notNull(direction, "Direction can not be null");
/* 60 */     getHandle().setDirection(direction.getX(), direction.getY(), direction.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityFireball getHandle() {
/* 65 */     return (EntityFireball)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     return "CraftFireball";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 75 */     return EntityType.UNKNOWN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */