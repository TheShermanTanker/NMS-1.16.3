/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockTNT;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.TNT;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftTNT
/*    */   extends CraftBlockData implements TNT {
/*    */   public CraftTNT(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftTNT() {}
/*    */   
/* 18 */   private static final BlockStateBoolean UNSTABLE = getBoolean(BlockTNT.class, "unstable");
/*    */ 
/*    */   
/*    */   public boolean isUnstable() {
/* 22 */     return ((Boolean)get((IBlockState)UNSTABLE)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUnstable(boolean unstable) {
/* 27 */     set((IBlockState)UNSTABLE, Boolean.valueOf(unstable));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */