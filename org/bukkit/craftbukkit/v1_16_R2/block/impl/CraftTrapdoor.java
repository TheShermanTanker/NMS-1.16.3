/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.BlockTrapdoor;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Bisected;
/*    */ import org.bukkit.block.data.Openable;
/*    */ 
/*    */ public final class CraftTrapdoor extends CraftBlockData implements TrapDoor, Bisected, Directional, Openable, Powerable, Waterlogged {
/*    */   public CraftTrapdoor(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftTrapdoor() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> HALF = getEnum(BlockTrapdoor.class, "half");
/*    */ 
/*    */   
/*    */   public Bisected.Half getHalf() {
/* 22 */     return (Bisected.Half)get(HALF, Bisected.Half.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHalf(Bisected.Half half) {
/* 27 */     set(HALF, (Enum)half);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockTrapdoor.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 36 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 41 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 46 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 51 */   private static final BlockStateBoolean OPEN = getBoolean(BlockTrapdoor.class, "open");
/*    */ 
/*    */   
/*    */   public boolean isOpen() {
/* 55 */     return ((Boolean)get((IBlockState)OPEN)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOpen(boolean open) {
/* 60 */     set((IBlockState)OPEN, Boolean.valueOf(open));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 65 */   private static final BlockStateBoolean POWERED = getBoolean(BlockTrapdoor.class, "powered");
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 69 */     return ((Boolean)get((IBlockState)POWERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPowered(boolean powered) {
/* 74 */     set((IBlockState)POWERED, Boolean.valueOf(powered));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 79 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockTrapdoor.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 83 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 88 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftTrapdoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */