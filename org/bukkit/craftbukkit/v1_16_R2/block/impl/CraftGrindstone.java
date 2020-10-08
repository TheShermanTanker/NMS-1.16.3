/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockGrindstone;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.FaceAttachable;
/*    */ import org.bukkit.block.data.type.Grindstone;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftGrindstone extends CraftBlockData implements Grindstone, Directional, FaceAttachable {
/*    */   public CraftGrindstone(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftGrindstone() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> FACING = getEnum(BlockGrindstone.class, "facing");
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
/* 37 */   private static final BlockStateEnum<?> ATTACH_FACE = getEnum(BlockGrindstone.class, "face");
/*    */ 
/*    */   
/*    */   public FaceAttachable.AttachedFace getAttachedFace() {
/* 41 */     return (FaceAttachable.AttachedFace)get(ATTACH_FACE, FaceAttachable.AttachedFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAttachedFace(FaceAttachable.AttachedFace face) {
/* 46 */     set(ATTACH_FACE, (Enum)face);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftGrindstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */