/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Repeater;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftRepeater extends CraftBlockData implements Repeater {
/*  8 */   private static final BlockStateInteger DELAY = getInteger("delay");
/*  9 */   private static final BlockStateBoolean LOCKED = getBoolean("locked");
/*    */ 
/*    */   
/*    */   public int getDelay() {
/* 13 */     return ((Integer)get((IBlockState)DELAY)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDelay(int delay) {
/* 18 */     set((IBlockState)DELAY, Integer.valueOf(delay));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinimumDelay() {
/* 23 */     return getMin(DELAY);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumDelay() {
/* 28 */     return getMax(DELAY);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLocked() {
/* 33 */     return ((Boolean)get((IBlockState)LOCKED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLocked(boolean locked) {
/* 38 */     set((IBlockState)LOCKED, Boolean.valueOf(locked));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftRepeater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */