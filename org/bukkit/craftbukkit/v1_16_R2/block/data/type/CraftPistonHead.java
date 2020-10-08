/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.PistonHead;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftPistonHead extends CraftBlockData implements PistonHead {
/*  8 */   private static final BlockStateBoolean SHORT = getBoolean("short");
/*    */ 
/*    */   
/*    */   public boolean isShort() {
/* 12 */     return ((Boolean)get((IBlockState)SHORT)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShort(boolean _short) {
/* 17 */     set((IBlockState)SHORT, Boolean.valueOf(_short));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftPistonHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */