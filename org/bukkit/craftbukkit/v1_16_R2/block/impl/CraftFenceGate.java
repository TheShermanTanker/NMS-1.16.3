/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockFenceGate;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Openable;
/*    */ 
/*    */ public final class CraftFenceGate extends CraftBlockData implements Gate, Directional, Openable, Powerable {
/*    */   public CraftFenceGate(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftFenceGate() {}
/*    */   
/* 18 */   private static final BlockStateBoolean IN_WALL = getBoolean(BlockFenceGate.class, "in_wall");
/*    */ 
/*    */   
/*    */   public boolean isInWall() {
/* 22 */     return ((Boolean)get((IBlockState)IN_WALL)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInWall(boolean inWall) {
/* 27 */     set((IBlockState)IN_WALL, Boolean.valueOf(inWall));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockFenceGate.class, "facing");
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
/* 51 */   private static final BlockStateBoolean OPEN = getBoolean(BlockFenceGate.class, "open");
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
/* 65 */   private static final BlockStateBoolean POWERED = getBoolean(BlockFenceGate.class, "powered");
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
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftFenceGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */