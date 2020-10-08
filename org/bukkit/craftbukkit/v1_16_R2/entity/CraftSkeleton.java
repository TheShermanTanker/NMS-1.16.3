/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntitySkeletonAbstract;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Skeleton;
/*    */ 
/*    */ public class CraftSkeleton extends CraftMonster implements Skeleton, CraftRangedEntity<EntitySkeletonAbstract> {
/*    */   public CraftSkeleton(CraftServer server, EntitySkeletonAbstract entity) {
/* 12 */     super(server, (EntityMonster)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySkeletonAbstract getHandle() {
/* 17 */     return (EntitySkeletonAbstract)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 22 */     return "CraftSkeleton";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 27 */     return EntityType.SKELETON;
/*    */   }
/*    */ 
/*    */   
/*    */   public Skeleton.SkeletonType getSkeletonType() {
/* 32 */     return Skeleton.SkeletonType.NORMAL;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSkeletonType(Skeleton.SkeletonType type) {
/* 37 */     throw new UnsupportedOperationException("Not supported.");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */