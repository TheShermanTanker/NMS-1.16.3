/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockObserver;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Powerable;
/*    */ import org.bukkit.block.data.type.Observer;
/*    */ 
/*    */ public final class CraftObserver extends CraftBlockData implements Observer, Directional, Powerable {
/*    */   public CraftObserver(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftObserver() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> FACING = getEnum(BlockObserver.class, "facing");
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
/* 37 */   private static final BlockStateBoolean POWERED = getBoolean(BlockObserver.class, "powered");
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 41 */     return ((Boolean)get((IBlockState)POWERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPowered(boolean powered) {
/* 46 */     set((IBlockState)POWERED, Boolean.valueOf(powered));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */