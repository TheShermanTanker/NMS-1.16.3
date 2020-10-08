/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockRespawnAnchor;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.RespawnAnchor;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftRespawnAnchor
/*    */   extends CraftBlockData implements RespawnAnchor {
/*    */   public CraftRespawnAnchor(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftRespawnAnchor() {}
/*    */   
/* 18 */   private static final BlockStateInteger CHARGES = getInteger(BlockRespawnAnchor.class, "charges");
/*    */ 
/*    */   
/*    */   public int getCharges() {
/* 22 */     return ((Integer)get((IBlockState)CHARGES)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCharges(int charges) {
/* 27 */     set((IBlockState)CHARGES, Integer.valueOf(charges));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumCharges() {
/* 32 */     return getMax(CHARGES);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftRespawnAnchor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */