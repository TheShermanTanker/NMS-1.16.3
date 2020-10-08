/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.AnaloguePowerable;
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
/*    */ public interface RedstoneWire
/*    */   extends AnaloguePowerable
/*    */ {
/*    */   @NotNull
/*    */   Connection getFace(@NotNull BlockFace paramBlockFace);
/*    */   
/*    */   void setFace(@NotNull BlockFace paramBlockFace, @NotNull Connection paramConnection);
/*    */   
/*    */   @NotNull
/*    */   Set<BlockFace> getAllowedFaces();
/*    */   
/*    */   public enum Connection
/*    */   {
/* 46 */     UP,
/*    */ 
/*    */ 
/*    */     
/* 50 */     SIDE,
/*    */ 
/*    */ 
/*    */     
/* 54 */     NONE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\RedstoneWire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */