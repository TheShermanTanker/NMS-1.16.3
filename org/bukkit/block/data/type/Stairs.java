/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.Bisected;
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
/*    */ public interface Stairs
/*    */   extends Bisected, Directional, Waterlogged
/*    */ {
/*    */   @NotNull
/*    */   Shape getShape();
/*    */   
/*    */   void setShape(@NotNull Shape paramShape);
/*    */   
/*    */   public enum Shape
/*    */   {
/* 35 */     STRAIGHT,
/*    */ 
/*    */ 
/*    */     
/* 39 */     INNER_LEFT,
/*    */ 
/*    */ 
/*    */     
/* 43 */     INNER_RIGHT,
/*    */ 
/*    */ 
/*    */     
/* 47 */     OUTER_LEFT,
/*    */ 
/*    */ 
/*    */     
/* 51 */     OUTER_RIGHT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Stairs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */