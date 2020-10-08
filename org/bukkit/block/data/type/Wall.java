/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.BlockFace;
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
/*    */ public interface Wall
/*    */   extends Waterlogged
/*    */ {
/*    */   boolean isUp();
/*    */   
/*    */   void setUp(boolean paramBoolean);
/*    */   
/*    */   @NotNull
/*    */   Height getHeight(@NotNull BlockFace paramBlockFace);
/*    */   
/*    */   void setHeight(@NotNull BlockFace paramBlockFace, @NotNull Height paramHeight);
/*    */   
/*    */   public enum Height
/*    */   {
/* 53 */     NONE,
/*    */ 
/*    */ 
/*    */     
/* 57 */     LOW,
/*    */ 
/*    */ 
/*    */     
/* 61 */     TALL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Wall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */