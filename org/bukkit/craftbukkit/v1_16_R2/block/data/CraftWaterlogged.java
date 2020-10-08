/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ 
/*    */ public abstract class CraftWaterlogged extends CraftBlockData implements Waterlogged {
/*  7 */   private static final BlockStateBoolean WATERLOGGED = getBoolean("waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 11 */     return ((Boolean)get((IBlockState<Boolean>)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 16 */     set((IBlockState<Comparable>)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftWaterlogged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */