/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.Ageable;
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
/*    */ public interface Bamboo
/*    */   extends Ageable, Sapling
/*    */ {
/*    */   @NotNull
/*    */   Leaves getLeaves();
/*    */   
/*    */   void setLeaves(@NotNull Leaves paramLeaves);
/*    */   
/*    */   public enum Leaves
/*    */   {
/* 34 */     NONE,
/*    */ 
/*    */ 
/*    */     
/* 38 */     SMALL,
/*    */ 
/*    */ 
/*    */     
/* 42 */     LARGE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Bamboo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */