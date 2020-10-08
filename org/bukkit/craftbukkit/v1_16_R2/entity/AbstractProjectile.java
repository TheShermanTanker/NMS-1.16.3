/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.Projectile;
/*    */ 
/*    */ public abstract class AbstractProjectile extends CraftEntity implements Projectile {
/*    */   private boolean doesBounce;
/*    */   
/*    */   public AbstractProjectile(CraftServer server, Entity entity) {
/* 11 */     super(server, entity);
/* 12 */     this.doesBounce = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean doesBounce() {
/* 17 */     return this.doesBounce;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBounce(boolean doesBounce) {
/* 22 */     this.doesBounce = doesBounce;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\AbstractProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */