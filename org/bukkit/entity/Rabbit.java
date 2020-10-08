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
/*    */ public interface Rabbit
/*    */   extends Animals
/*    */ {
/*    */   @NotNull
/*    */   Type getRabbitType();
/*    */   
/*    */   void setRabbitType(@NotNull Type paramType);
/*    */   
/*    */   public enum Type
/*    */   {
/* 26 */     BROWN,
/*    */ 
/*    */ 
/*    */     
/* 30 */     WHITE,
/*    */ 
/*    */ 
/*    */     
/* 34 */     BLACK,
/*    */ 
/*    */ 
/*    */     
/* 38 */     BLACK_AND_WHITE,
/*    */ 
/*    */ 
/*    */     
/* 42 */     GOLD,
/*    */ 
/*    */ 
/*    */     
/* 46 */     SALT_AND_PEPPER,
/*    */ 
/*    */ 
/*    */     
/* 50 */     THE_KILLER_BUNNY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Rabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */