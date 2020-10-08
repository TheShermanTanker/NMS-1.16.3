/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.Directional;
/*    */ import org.bukkit.block.data.FaceAttachable;
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
/*    */ public interface Switch
/*    */   extends Directional, FaceAttachable, Powerable
/*    */ {
/*    */   @Deprecated
/*    */   @NotNull
/*    */   Face getFace();
/*    */   
/*    */   @Deprecated
/*    */   void setFace(@NotNull Face paramFace);
/*    */   
/*    */   @Deprecated
/*    */   public enum Face
/*    */   {
/* 39 */     FLOOR,
/*    */ 
/*    */ 
/*    */     
/* 43 */     WALL,
/*    */ 
/*    */ 
/*    */     
/* 47 */     CEILING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Switch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */