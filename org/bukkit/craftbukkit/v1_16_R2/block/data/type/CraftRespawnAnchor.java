/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.RespawnAnchor;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftRespawnAnchor extends CraftBlockData implements RespawnAnchor {
/*  8 */   private static final BlockStateInteger CHARGES = getInteger("charges");
/*    */ 
/*    */   
/*    */   public int getCharges() {
/* 12 */     return ((Integer)get((IBlockState)CHARGES)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCharges(int charges) {
/* 17 */     set((IBlockState)CHARGES, Integer.valueOf(charges));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumCharges() {
/* 22 */     return getMax(CHARGES);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftRespawnAnchor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */