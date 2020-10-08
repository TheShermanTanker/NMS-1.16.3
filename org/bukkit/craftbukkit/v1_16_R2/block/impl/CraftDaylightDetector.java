/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import net.minecraft.server.v1_16_R2.BlockDaylightDetector;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.AnaloguePowerable;
/*    */ import org.bukkit.block.data.type.DaylightDetector;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftDaylightDetector extends CraftBlockData implements DaylightDetector, AnaloguePowerable {
/*    */   public CraftDaylightDetector(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftDaylightDetector() {}
/*    */   
/* 18 */   private static final BlockStateBoolean INVERTED = getBoolean(BlockDaylightDetector.class, "inverted");
/*    */ 
/*    */   
/*    */   public boolean isInverted() {
/* 22 */     return ((Boolean)get((IBlockState)INVERTED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInverted(boolean inverted) {
/* 27 */     set((IBlockState)INVERTED, Boolean.valueOf(inverted));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateInteger POWER = getInteger(BlockDaylightDetector.class, "power");
/*    */ 
/*    */   
/*    */   public int getPower() {
/* 36 */     return ((Integer)get((IBlockState)POWER)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPower(int power) {
/* 41 */     set((IBlockState)POWER, Integer.valueOf(power));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumPower() {
/* 46 */     return getMax(POWER);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftDaylightDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */