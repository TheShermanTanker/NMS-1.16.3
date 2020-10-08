/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.TurtleEgg;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftTurtleEgg extends CraftBlockData implements TurtleEgg {
/*  8 */   private static final BlockStateInteger EGGS = getInteger("eggs");
/*  9 */   private static final BlockStateInteger HATCH = getInteger("hatch");
/*    */ 
/*    */   
/*    */   public int getEggs() {
/* 13 */     return ((Integer)get((IBlockState)EGGS)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEggs(int eggs) {
/* 18 */     set((IBlockState)EGGS, Integer.valueOf(eggs));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinimumEggs() {
/* 23 */     return getMin(EGGS);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumEggs() {
/* 28 */     return getMax(EGGS);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHatch() {
/* 33 */     return ((Integer)get((IBlockState)HATCH)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHatch(int hatch) {
/* 38 */     set((IBlockState)HATCH, Integer.valueOf(hatch));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumHatch() {
/* 43 */     return getMax(HATCH);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftTurtleEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */