/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockEnderPortalFrame;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.EndPortalFrame;
/*    */ 
/*    */ public final class CraftEnderPortalFrame extends CraftBlockData implements EndPortalFrame, Directional {
/*    */   public CraftEnderPortalFrame(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftEnderPortalFrame() {}
/*    */   
/* 18 */   private static final BlockStateBoolean EYE = getBoolean(BlockEnderPortalFrame.class, "eye");
/*    */ 
/*    */   
/*    */   public boolean hasEye() {
/* 22 */     return ((Boolean)get((IBlockState)EYE)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEye(boolean eye) {
/* 27 */     set((IBlockState)EYE, Boolean.valueOf(eye));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockEnderPortalFrame.class, "facing");
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftEnderPortalFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */