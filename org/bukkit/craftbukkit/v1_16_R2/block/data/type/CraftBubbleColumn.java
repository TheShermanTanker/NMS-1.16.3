/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.BubbleColumn;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftBubbleColumn extends CraftBlockData implements BubbleColumn {
/*  8 */   private static final BlockStateBoolean DRAG = getBoolean("drag");
/*    */ 
/*    */   
/*    */   public boolean isDrag() {
/* 12 */     return ((Boolean)get((IBlockState)DRAG)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDrag(boolean drag) {
/* 17 */     set((IBlockState)DRAG, Boolean.valueOf(drag));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftBubbleColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */