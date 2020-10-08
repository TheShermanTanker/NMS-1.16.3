/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import org.bukkit.DyeColor;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Cat
/*    */   extends Tameable, Sittable
/*    */ {
/*    */   @NotNull
/*    */   Type getCatType();
/*    */   
/*    */   void setCatType(@NotNull Type paramType);
/*    */   
/*    */   @NotNull
/*    */   DyeColor getCollarColor();
/*    */   
/*    */   void setCollarColor(@NotNull DyeColor paramDyeColor);
/*    */   
/*    */   public enum Type
/*    */   {
/* 45 */     TABBY,
/* 46 */     BLACK,
/* 47 */     RED,
/* 48 */     SIAMESE,
/* 49 */     BRITISH_SHORTHAIR,
/* 50 */     CALICO,
/* 51 */     PERSIAN,
/* 52 */     RAGDOLL,
/* 53 */     WHITE,
/* 54 */     JELLIE,
/* 55 */     ALL_BLACK;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Cat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */