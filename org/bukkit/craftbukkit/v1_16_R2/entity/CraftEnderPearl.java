/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityEnderPearl;
/*    */ import net.minecraft.server.v1_16_R2.EntityProjectileThrowable;
/*    */ import net.minecraft.server.v1_16_R2.IProjectile;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftEnderPearl extends CraftThrowableProjectile implements EnderPearl {
/*    */   public CraftEnderPearl(CraftServer server, EntityEnderPearl entity) {
/* 10 */     super(server, (EntityProjectileThrowable)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEnderPearl getHandle() {
/* 15 */     return (EntityEnderPearl)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftEnderPearl";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 25 */     return EntityType.ENDER_PEARL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftEnderPearl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */