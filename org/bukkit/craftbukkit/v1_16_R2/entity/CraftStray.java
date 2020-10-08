/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.EntitySkeletonAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntitySkeletonStray;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Skeleton;
/*    */ import org.bukkit.entity.Stray;
/*    */ 
/*    */ public class CraftStray extends CraftSkeleton implements Stray {
/*    */   public CraftStray(CraftServer server, EntitySkeletonStray entity) {
/* 11 */     super(server, (EntitySkeletonAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 16 */     return "CraftStray";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 21 */     return EntityType.STRAY;
/*    */   }
/*    */ 
/*    */   
/*    */   public Skeleton.SkeletonType getSkeletonType() {
/* 26 */     return Skeleton.SkeletonType.STRAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftStray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */