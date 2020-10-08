/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import net.minecraft.server.v1_16_R2.BlockStairs;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Bisected;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ import org.bukkit.block.data.type.Stairs;
/*    */ 
/*    */ public final class CraftStairs extends CraftBlockData implements Stairs, Bisected, Directional, Waterlogged {
/*    */   public CraftStairs(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftStairs() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> SHAPE = getEnum(BlockStairs.class, "shape");
/*    */ 
/*    */   
/*    */   public Stairs.Shape getShape() {
/* 22 */     return (Stairs.Shape)get(SHAPE, Stairs.Shape.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShape(Stairs.Shape shape) {
/* 27 */     set(SHAPE, (Enum)shape);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> HALF = getEnum(BlockStairs.class, "half");
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
/* 46 */   private static final BlockStateEnum<?> FACING = getEnum(BlockStairs.class, "facing");
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
/* 65 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockStairs.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 69 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 74 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftStairs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */