/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseSkeleton;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Horse;
/*    */ 
/*    */ public class CraftSkeletonHorse extends CraftAbstractHorse implements SkeletonHorse {
/*    */   public CraftSkeletonHorse(CraftServer server, EntityHorseSkeleton entity) {
/* 12 */     super(server, (EntityHorseAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     return "CraftSkeletonHorse";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 22 */     return EntityType.SKELETON_HORSE;
/*    */   }
/*    */ 
/*    */   
/*    */   public Horse.Variant getVariant() {
/* 27 */     return Horse.Variant.SKELETON_HORSE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityHorseSkeleton getHandle() {
/* 33 */     return (EntityHorseSkeleton)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTrapTime() {
/* 38 */     return getHandle().getTrapTime();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTrap() {
/* 43 */     return getHandle().isTrap();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTrap(boolean trap) {
/* 48 */     getHandle().setTrap(trap);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSkeletonHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */