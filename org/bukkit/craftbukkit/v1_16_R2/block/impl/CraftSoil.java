/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockSoil;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Farmland;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftSoil
/*    */   extends CraftBlockData implements Farmland {
/*    */   public CraftSoil(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftSoil() {}
/*    */   
/* 18 */   private static final BlockStateInteger MOISTURE = getInteger(BlockSoil.class, "moisture");
/*    */ 
/*    */   
/*    */   public int getMoisture() {
/* 22 */     return ((Integer)get((IBlockState)MOISTURE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMoisture(int moisture) {
/* 27 */     set((IBlockState)MOISTURE, Integer.valueOf(moisture));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumMoisture() {
/* 32 */     return getMax(MOISTURE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftSoil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */