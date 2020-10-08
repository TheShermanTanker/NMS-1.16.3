/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.SeaPickle;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftSeaPickle extends CraftBlockData implements SeaPickle {
/*  8 */   private static final BlockStateInteger PICKLES = getInteger("pickles");
/*    */ 
/*    */   
/*    */   public int getPickles() {
/* 12 */     return ((Integer)get((IBlockState)PICKLES)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPickles(int pickles) {
/* 17 */     set((IBlockState)PICKLES, Integer.valueOf(pickles));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinimumPickles() {
/* 22 */     return getMin(PICKLES);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumPickles() {
/* 27 */     return getMax(PICKLES);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftSeaPickle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */