/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityEgg;
/*    */ import net.minecraft.server.v1_16_R2.EntityProjectileThrowable;
/*    */ import net.minecraft.server.v1_16_R2.IProjectile;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftEgg extends CraftThrowableProjectile implements Egg {
/*    */   public CraftEgg(CraftServer server, EntityEgg entity) {
/* 10 */     super(server, (EntityProjectileThrowable)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEgg getHandle() {
/* 15 */     return (EntityEgg)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftEgg";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 25 */     return EntityType.EGG;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */