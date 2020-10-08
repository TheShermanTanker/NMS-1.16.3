/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockBubbleColumn;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.BubbleColumn;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftBubbleColumn
/*    */   extends CraftBlockData implements BubbleColumn {
/*    */   public CraftBubbleColumn(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftBubbleColumn() {}
/*    */   
/* 18 */   private static final BlockStateBoolean DRAG = getBoolean(BlockBubbleColumn.class, "drag");
/*    */ 
/*    */   
/*    */   public boolean isDrag() {
/* 22 */     return ((Boolean)get((IBlockState)DRAG)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDrag(boolean drag) {
/* 27 */     set((IBlockState)DRAG, Boolean.valueOf(drag));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftBubbleColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */