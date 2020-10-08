/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import net.minecraft.server.v1_16_R2.BlockDoor;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Bisected;
/*    */ import org.bukkit.block.data.type.Door;
/*    */ 
/*    */ public final class CraftDoor extends CraftBlockData implements Door, Bisected, Directional, Openable, Powerable {
/*    */   public CraftDoor(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftDoor() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> HINGE = getEnum(BlockDoor.class, "hinge");
/*    */ 
/*    */   
/*    */   public Door.Hinge getHinge() {
/* 22 */     return (Door.Hinge)get(HINGE, Door.Hinge.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHinge(Door.Hinge hinge) {
/* 27 */     set(HINGE, (Enum)hinge);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> HALF = getEnum(BlockDoor.class, "half");
/*    */ 
/*    */   
/*    */   public Bisected.Half getHalf() {
/* 36 */     return (Bisected.Half)get(HALF, Bisected.Half.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHalf(Bisected.Half half) {
/* 41 */     set(HALF, (Enum)half);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 46 */   private static final BlockStateEnum<?> FACING = getEnum(BlockDoor.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 50 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 55 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 60 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 65 */   private static final BlockStateBoolean OPEN = getBoolean(BlockDoor.class, "open");
/*    */ 
/*    */   
/*    */   public boolean isOpen() {
/* 69 */     return ((Boolean)get((IBlockState)OPEN)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOpen(boolean open) {
/* 74 */     set((IBlockState)OPEN, Boolean.valueOf(open));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 79 */   private static final BlockStateBoolean POWERED = getBoolean(BlockDoor.class, "powered");
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 83 */     return ((Boolean)get((IBlockState)POWERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPowered(boolean powered) {
/* 88 */     set((IBlockState)POWERED, Boolean.valueOf(powered));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */