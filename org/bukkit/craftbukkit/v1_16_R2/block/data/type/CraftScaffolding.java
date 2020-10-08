/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Scaffolding;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftScaffolding extends CraftBlockData implements Scaffolding {
/*  8 */   private static final BlockStateBoolean BOTTOM = getBoolean("bottom");
/*  9 */   private static final BlockStateInteger DISTANCE = getInteger("distance");
/*    */ 
/*    */   
/*    */   public boolean isBottom() {
/* 13 */     return ((Boolean)get((IBlockState)BOTTOM)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBottom(boolean bottom) {
/* 18 */     set((IBlockState)BOTTOM, Boolean.valueOf(bottom));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDistance() {
/* 23 */     return ((Integer)get((IBlockState)DISTANCE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDistance(int distance) {
/* 28 */     set((IBlockState)DISTANCE, Integer.valueOf(distance));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumDistance() {
/* 33 */     return getMax(DISTANCE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftScaffolding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */