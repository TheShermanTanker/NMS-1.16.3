/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.BlockTurtleEgg;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.TurtleEgg;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftTurtleEgg
/*    */   extends CraftBlockData implements TurtleEgg {
/*    */   public CraftTurtleEgg(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftTurtleEgg() {}
/*    */   
/* 18 */   private static final BlockStateInteger EGGS = getInteger(BlockTurtleEgg.class, "eggs");
/* 19 */   private static final BlockStateInteger HATCH = getInteger(BlockTurtleEgg.class, "hatch");
/*    */ 
/*    */   
/*    */   public int getEggs() {
/* 23 */     return ((Integer)get((IBlockState)EGGS)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEggs(int eggs) {
/* 28 */     set((IBlockState)EGGS, Integer.valueOf(eggs));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinimumEggs() {
/* 33 */     return getMin(EGGS);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumEggs() {
/* 38 */     return getMax(EGGS);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHatch() {
/* 43 */     return ((Integer)get((IBlockState)HATCH)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHatch(int hatch) {
/* 48 */     set((IBlockState)HATCH, Integer.valueOf(hatch));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumHatch() {
/* 53 */     return getMax(HATCH);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftTurtleEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */