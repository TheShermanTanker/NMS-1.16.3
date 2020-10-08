/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.Waterlogged;
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
/*    */ public interface Slab
/*    */   extends Waterlogged
/*    */ {
/*    */   @NotNull
/*    */   Type getType();
/*    */   
/*    */   void setType(@NotNull Type paramType);
/*    */   
/*    */   public enum Type
/*    */   {
/* 34 */     TOP,
/*    */ 
/*    */ 
/*    */     
/* 38 */     BOTTOM,
/*    */ 
/*    */ 
/*    */     
/* 42 */     DOUBLE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Slab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */