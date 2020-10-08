/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import com.destroystokyo.paper.entity.RangedEntity;
/*    */ import org.jetbrains.annotations.Contract;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Skeleton
/*    */   extends Monster, RangedEntity
/*    */ {
/*    */   @Deprecated
/*    */   @NotNull
/*    */   SkeletonType getSkeletonType();
/*    */   
/*    */   @Deprecated
/*    */   @Contract("_ -> fail")
/*    */   void setSkeletonType(SkeletonType paramSkeletonType);
/*    */   
/*    */   @Deprecated
/*    */   public enum SkeletonType
/*    */   {
/* 39 */     NORMAL,
/*    */ 
/*    */ 
/*    */     
/* 43 */     WITHER,
/*    */ 
/*    */ 
/*    */     
/* 47 */     STRAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Skeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */