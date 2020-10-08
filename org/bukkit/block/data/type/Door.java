/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.Bisected;
/*    */ import org.bukkit.block.data.Directional;
/*    */ import org.bukkit.block.data.Openable;
/*    */ import org.bukkit.block.data.Powerable;
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
/*    */ public interface Door
/*    */   extends Bisected, Directional, Openable, Powerable
/*    */ {
/*    */   @NotNull
/*    */   Hinge getHinge();
/*    */   
/*    */   void setHinge(@NotNull Hinge paramHinge);
/*    */   
/*    */   public enum Hinge
/*    */   {
/* 37 */     LEFT,
/*    */ 
/*    */ 
/*    */     
/* 41 */     RIGHT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Door.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */