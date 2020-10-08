/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.AnaloguePowerable;
/*    */ 
/*    */ public abstract class CraftAnaloguePowerable extends CraftBlockData implements AnaloguePowerable {
/*  7 */   private static final BlockStateInteger POWER = getInteger("power");
/*    */ 
/*    */   
/*    */   public int getPower() {
/* 11 */     return ((Integer)get((IBlockState<Integer>)POWER)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPower(int power) {
/* 16 */     set((IBlockState<Comparable>)POWER, Integer.valueOf(power));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumPower() {
/* 21 */     return getMax(POWER);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftAnaloguePowerable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */