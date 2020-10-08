/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.Directional;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Chest
/*    */   extends Directional, Waterlogged
/*    */ {
/*    */   @NotNull
/*    */   Type getType();
/*    */   
/*    */   void setType(@NotNull Type paramType);
/*    */   
/*    */   public enum Type
/*    */   {
/* 39 */     SINGLE,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 44 */     LEFT,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     RIGHT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Chest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */