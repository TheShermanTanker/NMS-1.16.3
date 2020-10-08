/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
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
/*    */ public interface FallingBlock
/*    */   extends Entity
/*    */ {
/*    */   @Deprecated
/*    */   @NotNull
/*    */   Material getMaterial();
/*    */   
/*    */   @NotNull
/*    */   BlockData getBlockData();
/*    */   
/*    */   boolean getDropItem();
/*    */   
/*    */   void setDropItem(boolean paramBoolean);
/*    */   
/*    */   boolean canHurtEntities();
/*    */   
/*    */   void setHurtEntities(boolean paramBoolean);
/*    */   
/*    */   @Deprecated
/*    */   default Location getSourceLoc() {
/* 66 */     return getOrigin();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\FallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */