/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockLantern;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ import org.bukkit.block.data.type.Lantern;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftLantern extends CraftBlockData implements Lantern, Waterlogged {
/*    */   public CraftLantern(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftLantern() {}
/*    */   
/* 18 */   private static final BlockStateBoolean HANGING = getBoolean(BlockLantern.class, "hanging");
/*    */ 
/*    */   
/*    */   public boolean isHanging() {
/* 22 */     return ((Boolean)get((IBlockState)HANGING)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHanging(boolean hanging) {
/* 27 */     set((IBlockState)HANGING, Boolean.valueOf(hanging));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockLantern.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 36 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 41 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftLantern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */