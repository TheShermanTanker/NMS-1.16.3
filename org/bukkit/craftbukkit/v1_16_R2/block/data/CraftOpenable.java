/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Openable;
/*    */ 
/*    */ public abstract class CraftOpenable extends CraftBlockData implements Openable {
/*  7 */   private static final BlockStateBoolean OPEN = getBoolean("open");
/*    */ 
/*    */   
/*    */   public boolean isOpen() {
/* 11 */     return ((Boolean)get((IBlockState<Boolean>)OPEN)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOpen(boolean open) {
/* 16 */     set((IBlockState<Comparable>)OPEN, Boolean.valueOf(open));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftOpenable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */