/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.Directional;
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
/*    */ public interface Bell
/*    */   extends Directional, Powerable
/*    */ {
/*    */   @NotNull
/*    */   Attachment getAttachment();
/*    */   
/*    */   void setAttachment(@NotNull Attachment paramAttachment);
/*    */   
/*    */   public enum Attachment
/*    */   {
/* 35 */     FLOOR,
/*    */ 
/*    */ 
/*    */     
/* 39 */     CEILING,
/*    */ 
/*    */ 
/*    */     
/* 43 */     SINGLE_WALL,
/*    */ 
/*    */ 
/*    */     
/* 47 */     DOUBLE_WALL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Bell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */