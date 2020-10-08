/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockPiston;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftPiston extends CraftBlockData implements Piston, Directional {
/*    */   public CraftPiston(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftPiston() {}
/*    */   
/* 18 */   private static final BlockStateBoolean EXTENDED = getBoolean(BlockPiston.class, "extended");
/*    */ 
/*    */   
/*    */   public boolean isExtended() {
/* 22 */     return ((Boolean)get((IBlockState)EXTENDED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExtended(boolean extended) {
/* 27 */     set((IBlockState)EXTENDED, Boolean.valueOf(extended));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockPiston.class, "facing");
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
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftPiston.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */