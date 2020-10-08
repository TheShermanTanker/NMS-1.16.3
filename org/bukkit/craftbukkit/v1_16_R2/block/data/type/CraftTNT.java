/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.TNT;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftTNT extends CraftBlockData implements TNT {
/*  8 */   private static final BlockStateBoolean UNSTABLE = getBoolean("unstable");
/*    */ 
/*    */   
/*    */   public boolean isUnstable() {
/* 12 */     return ((Boolean)get((IBlockState)UNSTABLE)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUnstable(boolean unstable) {
/* 17 */     set((IBlockState)UNSTABLE, Boolean.valueOf(unstable));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */