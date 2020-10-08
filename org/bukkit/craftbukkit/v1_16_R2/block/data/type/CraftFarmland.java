/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Farmland;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftFarmland extends CraftBlockData implements Farmland {
/*  8 */   private static final BlockStateInteger MOISTURE = getInteger("moisture");
/*    */ 
/*    */   
/*    */   public int getMoisture() {
/* 12 */     return ((Integer)get((IBlockState)MOISTURE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMoisture(int moisture) {
/* 17 */     set((IBlockState)MOISTURE, Integer.valueOf(moisture));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumMoisture() {
/* 22 */     return getMax(MOISTURE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftFarmland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */