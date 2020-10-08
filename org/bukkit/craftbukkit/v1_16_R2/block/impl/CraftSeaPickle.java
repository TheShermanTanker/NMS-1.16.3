/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import net.minecraft.server.v1_16_R2.BlockSeaPickle;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ import org.bukkit.block.data.type.SeaPickle;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftSeaPickle extends CraftBlockData implements SeaPickle, Waterlogged {
/*    */   public CraftSeaPickle(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftSeaPickle() {}
/*    */   
/* 18 */   private static final BlockStateInteger PICKLES = getInteger(BlockSeaPickle.class, "pickles");
/*    */ 
/*    */   
/*    */   public int getPickles() {
/* 22 */     return ((Integer)get((IBlockState)PICKLES)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPickles(int pickles) {
/* 27 */     set((IBlockState)PICKLES, Integer.valueOf(pickles));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinimumPickles() {
/* 32 */     return getMin(PICKLES);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumPickles() {
/* 37 */     return getMax(PICKLES);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 42 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockSeaPickle.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 46 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 51 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftSeaPickle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */