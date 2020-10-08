/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockCoralFanWallAbstract;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ import org.bukkit.block.data.type.CoralWallFan;
/*    */ 
/*    */ public final class CraftCoralFanWallAbstract extends CraftBlockData implements CoralWallFan, Directional, Waterlogged {
/*    */   public CraftCoralFanWallAbstract(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftCoralFanWallAbstract() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> FACING = getEnum(BlockCoralFanWallAbstract.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 22 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 27 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 32 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockCoralFanWallAbstract.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 41 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 46 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftCoralFanWallAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */