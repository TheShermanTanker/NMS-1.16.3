/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Hopper;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftHopper extends CraftBlockData implements Hopper {
/*  8 */   private static final BlockStateBoolean ENABLED = getBoolean("enabled");
/*    */ 
/*    */   
/*    */   public boolean isEnabled() {
/* 12 */     return ((Boolean)get((IBlockState)ENABLED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEnabled(boolean enabled) {
/* 17 */     set((IBlockState)ENABLED, Boolean.valueOf(enabled));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */