/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.Directional;
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
/*    */ public interface TechnicalPiston
/*    */   extends Directional
/*    */ {
/*    */   @NotNull
/*    */   Type getType();
/*    */   
/*    */   void setType(@NotNull Type paramType);
/*    */   
/*    */   public enum Type
/*    */   {
/* 35 */     NORMAL,
/*    */ 
/*    */ 
/*    */     
/* 39 */     STICKY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\TechnicalPiston.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */