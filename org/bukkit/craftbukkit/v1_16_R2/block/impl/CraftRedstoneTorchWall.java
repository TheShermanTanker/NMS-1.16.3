/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockRedstoneTorchWall;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.RedstoneWallTorch;
/*    */ 
/*    */ public final class CraftRedstoneTorchWall extends CraftBlockData implements RedstoneWallTorch, Directional, Lightable {
/*    */   public CraftRedstoneTorchWall(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftRedstoneTorchWall() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> FACING = getEnum(BlockRedstoneTorchWall.class, "facing");
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
/* 37 */   private static final BlockStateBoolean LIT = getBoolean(BlockRedstoneTorchWall.class, "lit");
/*    */ 
/*    */   
/*    */   public boolean isLit() {
/* 41 */     return ((Boolean)get((IBlockState)LIT)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLit(boolean lit) {
/* 46 */     set((IBlockState)LIT, Boolean.valueOf(lit));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftRedstoneTorchWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */