/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.BlockData;
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
/*    */ public interface StructureBlock
/*    */   extends BlockData
/*    */ {
/*    */   @NotNull
/*    */   Mode getMode();
/*    */   
/*    */   void setMode(@NotNull Mode paramMode);
/*    */   
/*    */   public enum Mode
/*    */   {
/* 34 */     SAVE,
/*    */ 
/*    */ 
/*    */     
/* 38 */     LOAD,
/*    */ 
/*    */ 
/*    */     
/* 42 */     CORNER,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 47 */     DATA;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\StructureBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */