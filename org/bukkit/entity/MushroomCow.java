/*    */ package org.bukkit.entity;
/*    */ 
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
/*    */ public interface MushroomCow
/*    */   extends Cow
/*    */ {
/*    */   @NotNull
/*    */   Variant getVariant();
/*    */   
/*    */   void setVariant(@NotNull Variant paramVariant);
/*    */   
/*    */   public enum Variant
/*    */   {
/* 32 */     RED,
/*    */ 
/*    */ 
/*    */     
/* 36 */     BROWN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\MushroomCow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */