/*    */ package org.bukkit.block.data;
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
/*    */ 
/*    */ 
/*    */ public interface Bisected
/*    */   extends BlockData
/*    */ {
/*    */   @NotNull
/*    */   Half getHalf();
/*    */   
/*    */   void setHalf(@NotNull Half paramHalf);
/*    */   
/*    */   public enum Half
/*    */   {
/* 34 */     TOP,
/*    */ 
/*    */ 
/*    */     
/* 38 */     BOTTOM;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\Bisected.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */