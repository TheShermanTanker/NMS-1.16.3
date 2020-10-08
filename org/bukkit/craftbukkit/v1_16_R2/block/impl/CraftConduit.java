/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockConduit;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftConduit
/*    */   extends CraftBlockData implements Waterlogged {
/*    */   public CraftConduit(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftConduit() {}
/*    */   
/* 18 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockConduit.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 22 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 27 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftConduit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */