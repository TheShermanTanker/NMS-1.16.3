/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockDropper;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.Dispenser;
/*    */ 
/*    */ public final class CraftDropper extends CraftBlockData implements Dispenser, Directional {
/*    */   public CraftDropper(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftDropper() {}
/*    */   
/* 18 */   private static final BlockStateBoolean TRIGGERED = getBoolean(BlockDropper.class, "triggered");
/*    */ 
/*    */   
/*    */   public boolean isTriggered() {
/* 22 */     return ((Boolean)get((IBlockState)TRIGGERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTriggered(boolean triggered) {
/* 27 */     set((IBlockState)TRIGGERED, Boolean.valueOf(triggered));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockDropper.class, "facing");
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftDropper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */