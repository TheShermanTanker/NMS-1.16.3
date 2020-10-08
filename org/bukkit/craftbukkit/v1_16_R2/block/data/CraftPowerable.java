/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Powerable;
/*    */ 
/*    */ public abstract class CraftPowerable extends CraftBlockData implements Powerable {
/*  7 */   private static final BlockStateBoolean POWERED = getBoolean("powered");
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 11 */     return ((Boolean)get((IBlockState<Boolean>)POWERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPowered(boolean powered) {
/* 16 */     set((IBlockState<Comparable>)POWERED, Boolean.valueOf(powered));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftPowerable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */