/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.EntitySkeletonAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntitySkeletonWither;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Skeleton;
/*    */ import org.bukkit.entity.WitherSkeleton;
/*    */ 
/*    */ public class CraftWitherSkeleton extends CraftSkeleton implements WitherSkeleton {
/*    */   public CraftWitherSkeleton(CraftServer server, EntitySkeletonWither entity) {
/* 11 */     super(server, (EntitySkeletonAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 16 */     return "CraftWitherSkeleton";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 21 */     return EntityType.WITHER_SKELETON;
/*    */   }
/*    */ 
/*    */   
/*    */   public Skeleton.SkeletonType getSkeletonType() {
/* 26 */     return Skeleton.SkeletonType.WITHER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftWitherSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */