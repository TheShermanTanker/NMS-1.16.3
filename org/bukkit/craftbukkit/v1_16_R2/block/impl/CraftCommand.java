/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockCommand;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.CommandBlock;
/*    */ 
/*    */ public final class CraftCommand extends CraftBlockData implements CommandBlock, Directional {
/*    */   public CraftCommand(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftCommand() {}
/*    */   
/* 18 */   private static final BlockStateBoolean CONDITIONAL = getBoolean(BlockCommand.class, "conditional");
/*    */ 
/*    */   
/*    */   public boolean isConditional() {
/* 22 */     return ((Boolean)get((IBlockState)CONDITIONAL)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConditional(boolean conditional) {
/* 27 */     set((IBlockState)CONDITIONAL, Boolean.valueOf(conditional));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockCommand.class, "facing");
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */