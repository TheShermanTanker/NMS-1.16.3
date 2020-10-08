/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockDispenser;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.Dispenser;
/*    */ 
/*    */ public final class CraftDispenser extends CraftBlockData implements Dispenser, Directional {
/*    */   public CraftDispenser(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftDispenser() {}
/*    */   
/* 18 */   private static final BlockStateBoolean TRIGGERED = getBoolean(BlockDispenser.class, "triggered");
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
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockDispenser.class, "facing");
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */