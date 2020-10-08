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
/*    */ 
/*    */ public interface FaceAttachable
/*    */   extends BlockData
/*    */ {
/*    */   @NotNull
/*    */   AttachedFace getAttachedFace();
/*    */   
/*    */   void setAttachedFace(@NotNull AttachedFace paramAttachedFace);
/*    */   
/*    */   public enum AttachedFace
/*    */   {
/* 35 */     FLOOR,
/*    */ 
/*    */ 
/*    */     
/* 39 */     WALL,
/*    */ 
/*    */ 
/*    */     
/* 43 */     CEILING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\FaceAttachable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */