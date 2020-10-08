/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.BlockTripwireHook;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Attachable;
/*    */ 
/*    */ public final class CraftTripwireHook extends CraftBlockData implements TripwireHook, Attachable, Directional, Powerable {
/*    */   public CraftTripwireHook(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftTripwireHook() {}
/*    */   
/* 18 */   private static final BlockStateBoolean ATTACHED = getBoolean(BlockTripwireHook.class, "attached");
/*    */ 
/*    */   
/*    */   public boolean isAttached() {
/* 22 */     return ((Boolean)get((IBlockState)ATTACHED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAttached(boolean attached) {
/* 27 */     set((IBlockState)ATTACHED, Boolean.valueOf(attached));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockTripwireHook.class, "facing");
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
/* 51 */   private static final BlockStateBoolean POWERED = getBoolean(BlockTripwireHook.class, "powered");
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftTripwireHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */