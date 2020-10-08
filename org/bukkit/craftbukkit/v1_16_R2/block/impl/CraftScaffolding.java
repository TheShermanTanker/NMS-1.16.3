/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import net.minecraft.server.v1_16_R2.BlockScaffolding;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ import org.bukkit.block.data.type.Scaffolding;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftScaffolding extends CraftBlockData implements Scaffolding, Waterlogged {
/*    */   public CraftScaffolding(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftScaffolding() {}
/*    */   
/* 18 */   private static final BlockStateBoolean BOTTOM = getBoolean(BlockScaffolding.class, "bottom");
/* 19 */   private static final BlockStateInteger DISTANCE = getInteger(BlockScaffolding.class, "distance");
/*    */ 
/*    */   
/*    */   public boolean isBottom() {
/* 23 */     return ((Boolean)get((IBlockState)BOTTOM)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBottom(boolean bottom) {
/* 28 */     set((IBlockState)BOTTOM, Boolean.valueOf(bottom));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDistance() {
/* 33 */     return ((Integer)get((IBlockState)DISTANCE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDistance(int distance) {
/* 38 */     set((IBlockState)DISTANCE, Integer.valueOf(distance));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumDistance() {
/* 43 */     return getMax(DISTANCE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 48 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockScaffolding.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 52 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 57 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftScaffolding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */