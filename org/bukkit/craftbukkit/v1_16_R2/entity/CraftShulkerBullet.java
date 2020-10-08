/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityShulkerBullet;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.ShulkerBullet;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ 
/*    */ public class CraftShulkerBullet extends AbstractProjectile implements ShulkerBullet {
/*    */   public CraftShulkerBullet(CraftServer server, EntityShulkerBullet entity) {
/* 13 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public ProjectileSource getShooter() {
/* 18 */     return (getHandle()).projectileSource;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShooter(ProjectileSource shooter) {
/* 23 */     if (shooter instanceof Entity) {
/* 24 */       getHandle().setShooter(((CraftEntity)shooter).getHandle());
/*    */     } else {
/* 26 */       getHandle().setShooter(null);
/*    */     } 
/* 28 */     (getHandle()).projectileSource = shooter;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getTarget() {
/* 33 */     return (getHandle().getTarget() != null) ? getHandle().getTarget().getBukkitEntity() : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTarget(Entity target) {
/* 38 */     getHandle().setTarget((target == null) ? null : ((CraftEntity)target).getHandle());
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     return "CraftShulkerBullet";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 48 */     return EntityType.SHULKER_BULLET;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityShulkerBullet getHandle() {
/* 53 */     return (EntityShulkerBullet)this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftShulkerBullet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */