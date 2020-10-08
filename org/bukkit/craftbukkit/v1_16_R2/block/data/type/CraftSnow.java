/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Snow;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public class CraftSnow extends CraftBlockData implements Snow {
/*  8 */   private static final BlockStateInteger LAYERS = getInteger("layers");
/*    */ 
/*    */   
/*    */   public int getLayers() {
/* 12 */     return ((Integer)get((IBlockState)LAYERS)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLayers(int layers) {
/* 17 */     set((IBlockState)LAYERS, Integer.valueOf(layers));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinimumLayers() {
/* 22 */     return getMin(LAYERS);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumLayers() {
/* 27 */     return getMax(LAYERS);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */