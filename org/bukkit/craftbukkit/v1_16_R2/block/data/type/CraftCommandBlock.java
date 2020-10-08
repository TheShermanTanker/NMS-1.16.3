/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.CommandBlock;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftCommandBlock extends CraftBlockData implements CommandBlock {
/*  8 */   private static final BlockStateBoolean CONDITIONAL = getBoolean("conditional");
/*    */ 
/*    */   
/*    */   public boolean isConditional() {
/* 12 */     return ((Boolean)get((IBlockState)CONDITIONAL)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConditional(boolean conditional) {
/* 17 */     set((IBlockState)CONDITIONAL, Boolean.valueOf(conditional));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */