/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.IProjectile;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ 
/*    */ public abstract class CraftProjectile extends AbstractProjectile implements Projectile {
/*    */   public CraftProjectile(CraftServer server, IProjectile entity) {
/* 11 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public ProjectileSource getShooter() {
/* 16 */     return (getHandle()).projectileSource;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShooter(ProjectileSource shooter) {
/* 21 */     if (shooter instanceof CraftLivingEntity) {
/* 22 */       getHandle().setShooter(((CraftLivingEntity)shooter).entity);
/*    */     } else {
/* 24 */       getHandle().setShooter(null);
/*    */     } 
/* 26 */     (getHandle()).projectileSource = shooter;
/*    */   }
/*    */ 
/*    */   
/*    */   public IProjectile getHandle() {
/* 31 */     return (IProjectile)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 36 */     return "CraftProjectile";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */