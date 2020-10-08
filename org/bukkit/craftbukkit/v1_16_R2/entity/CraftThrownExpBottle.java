/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityProjectileThrowable;
/*    */ import net.minecraft.server.v1_16_R2.EntityThrownExpBottle;
/*    */ import net.minecraft.server.v1_16_R2.IProjectile;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftThrownExpBottle extends CraftThrowableProjectile implements ThrownExpBottle {
/*    */   public CraftThrownExpBottle(CraftServer server, EntityThrownExpBottle entity) {
/* 10 */     super(server, (EntityProjectileThrowable)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityThrownExpBottle getHandle() {
/* 15 */     return (EntityThrownExpBottle)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "EntityThrownExpBottle";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 25 */     return EntityType.THROWN_EXP_BOTTLE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftThrownExpBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */