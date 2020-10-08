/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.BlockStoneButton;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.FaceAttachable;
/*    */ import org.bukkit.block.data.type.Switch;
/*    */ 
/*    */ public final class CraftStoneButton extends CraftBlockData implements Switch, Directional, FaceAttachable, Powerable {
/*    */   public CraftStoneButton(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftStoneButton() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> FACE = getEnum(BlockStoneButton.class, "face");
/*    */ 
/*    */   
/*    */   public Switch.Face getFace() {
/* 22 */     return (Switch.Face)get(FACE, Switch.Face.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFace(Switch.Face face) {
/* 27 */     set(FACE, (Enum)face);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockStoneButton.class, "facing");
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
/* 51 */   private static final BlockStateEnum<?> ATTACH_FACE = getEnum(BlockStoneButton.class, "face");
/*    */ 
/*    */   
/*    */   public FaceAttachable.AttachedFace getAttachedFace() {
/* 55 */     return (FaceAttachable.AttachedFace)get(ATTACH_FACE, FaceAttachable.AttachedFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAttachedFace(FaceAttachable.AttachedFace face) {
/* 60 */     set(ATTACH_FACE, (Enum)face);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 65 */   private static final BlockStateBoolean POWERED = getBoolean(BlockStoneButton.class, "powered");
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftStoneButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */