/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockRedstoneComparator;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Powerable;
/*    */ import org.bukkit.block.data.type.Comparator;
/*    */ 
/*    */ public final class CraftRedstoneComparator extends CraftBlockData implements Comparator, Directional, Powerable {
/*    */   public CraftRedstoneComparator(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftRedstoneComparator() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> MODE = getEnum(BlockRedstoneComparator.class, "mode");
/*    */ 
/*    */   
/*    */   public Comparator.Mode getMode() {
/* 22 */     return (Comparator.Mode)get(MODE, Comparator.Mode.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMode(Comparator.Mode mode) {
/* 27 */     set(MODE, (Enum)mode);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockRedstoneComparator.class, "facing");
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
/* 51 */   private static final BlockStateBoolean POWERED = getBoolean(BlockRedstoneComparator.class, "powered");
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 55 */     return ((Boolean)get((IBlockState)POWERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPowered(boolean powered) {
/* 60 */     set((IBlockState)POWERED, Boolean.valueOf(powered));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftRedstoneComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */