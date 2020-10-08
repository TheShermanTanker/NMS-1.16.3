/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Levelled;
/*    */ 
/*    */ public abstract class CraftLevelled extends CraftBlockData implements Levelled {
/*  7 */   private static final BlockStateInteger LEVEL = getInteger("level");
/*    */ 
/*    */   
/*    */   public int getLevel() {
/* 11 */     return ((Integer)get((IBlockState<Integer>)LEVEL)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLevel(int level) {
/* 16 */     set((IBlockState<Comparable>)LEVEL, Integer.valueOf(level));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumLevel() {
/* 21 */     return getMax(LEVEL);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftLevelled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */