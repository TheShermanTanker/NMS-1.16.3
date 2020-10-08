/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockSnow;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Snow;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftSnow
/*    */   extends CraftBlockData implements Snow {
/*    */   public CraftSnow(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftSnow() {}
/*    */   
/* 18 */   private static final BlockStateInteger LAYERS = getInteger(BlockSnow.class, "layers");
/*    */ 
/*    */   
/*    */   public int getLayers() {
/* 22 */     return ((Integer)get((IBlockState)LAYERS)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLayers(int layers) {
/* 27 */     set((IBlockState)LAYERS, Integer.valueOf(layers));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinimumLayers() {
/* 32 */     return getMin(LAYERS);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumLayers() {
/* 37 */     return getMax(LAYERS);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */