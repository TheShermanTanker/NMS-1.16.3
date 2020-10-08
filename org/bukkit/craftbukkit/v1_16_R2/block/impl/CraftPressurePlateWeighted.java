/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockPressurePlateWeighted;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.AnaloguePowerable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftPressurePlateWeighted
/*    */   extends CraftBlockData implements AnaloguePowerable {
/*    */   public CraftPressurePlateWeighted(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftPressurePlateWeighted() {}
/*    */   
/* 18 */   private static final BlockStateInteger POWER = getInteger(BlockPressurePlateWeighted.class, "power");
/*    */ 
/*    */   
/*    */   public int getPower() {
/* 22 */     return ((Integer)get((IBlockState)POWER)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPower(int power) {
/* 27 */     set((IBlockState)POWER, Integer.valueOf(power));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumPower() {
/* 32 */     return getMax(POWER);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftPressurePlateWeighted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */