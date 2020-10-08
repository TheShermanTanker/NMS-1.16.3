/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockRepeater;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Powerable;
/*    */ 
/*    */ public final class CraftRepeater extends CraftBlockData implements Repeater, Directional, Powerable {
/*    */   public CraftRepeater(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftRepeater() {}
/*    */   
/* 18 */   private static final BlockStateInteger DELAY = getInteger(BlockRepeater.class, "delay");
/* 19 */   private static final BlockStateBoolean LOCKED = getBoolean(BlockRepeater.class, "locked");
/*    */ 
/*    */   
/*    */   public int getDelay() {
/* 23 */     return ((Integer)get((IBlockState)DELAY)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDelay(int delay) {
/* 28 */     set((IBlockState)DELAY, Integer.valueOf(delay));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinimumDelay() {
/* 33 */     return getMin(DELAY);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumDelay() {
/* 38 */     return getMax(DELAY);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLocked() {
/* 43 */     return ((Boolean)get((IBlockState)LOCKED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLocked(boolean locked) {
/* 48 */     set((IBlockState)LOCKED, Boolean.valueOf(locked));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 53 */   private static final BlockStateEnum<?> FACING = getEnum(BlockRepeater.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 57 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 62 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 67 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 72 */   private static final BlockStateBoolean POWERED = getBoolean(BlockRepeater.class, "powered");
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 76 */     return ((Boolean)get((IBlockState)POWERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPowered(boolean powered) {
/* 81 */     set((IBlockState)POWERED, Boolean.valueOf(powered));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftRepeater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */