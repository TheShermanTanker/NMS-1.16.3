/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityProjectileThrowable;
/*    */ import net.minecraft.server.v1_16_R2.EntitySnowball;
/*    */ import net.minecraft.server.v1_16_R2.IProjectile;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftSnowball extends CraftThrowableProjectile implements Snowball {
/*    */   public CraftSnowball(CraftServer server, EntitySnowball entity) {
/* 10 */     super(server, (EntityProjectileThrowable)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySnowball getHandle() {
/* 15 */     return (EntitySnowball)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftSnowball";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 25 */     return EntityType.SNOWBALL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSnowball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */