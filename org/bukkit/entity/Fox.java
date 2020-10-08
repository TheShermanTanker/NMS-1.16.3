/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Fox
/*    */   extends Animals, Sittable
/*    */ {
/*    */   @NotNull
/*    */   Type getFoxType();
/*    */   
/*    */   void setFoxType(@NotNull Type paramType);
/*    */   
/*    */   boolean isCrouching();
/*    */   
/*    */   void setCrouching(boolean paramBoolean);
/*    */   
/*    */   void setSleeping(boolean paramBoolean);
/*    */   
/*    */   @Nullable
/*    */   AnimalTamer getFirstTrustedPlayer();
/*    */   
/*    */   void setFirstTrustedPlayer(@Nullable AnimalTamer paramAnimalTamer);
/*    */   
/*    */   @Nullable
/*    */   AnimalTamer getSecondTrustedPlayer();
/*    */   
/*    */   void setSecondTrustedPlayer(@Nullable AnimalTamer paramAnimalTamer);
/*    */   
/*    */   public enum Type
/*    */   {
/* 85 */     RED,
/* 86 */     SNOW;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Fox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */