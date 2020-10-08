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
/*    */ public interface Parrot
/*    */   extends Tameable, Sittable
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
/* 36 */     BLUE,
/*    */ 
/*    */ 
/*    */     
/* 40 */     GREEN,
/*    */ 
/*    */ 
/*    */     
/* 44 */     CYAN,
/*    */ 
/*    */ 
/*    */     
/* 48 */     GRAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Parrot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */