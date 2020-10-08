/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockSapling;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Sapling;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftSapling
/*    */   extends CraftBlockData implements Sapling {
/*    */   public CraftSapling(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftSapling() {}
/*    */   
/* 18 */   private static final BlockStateInteger STAGE = getInteger(BlockSapling.class, "stage");
/*    */ 
/*    */   
/*    */   public int getStage() {
/* 22 */     return ((Integer)get((IBlockState)STAGE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStage(int stage) {
/* 27 */     set((IBlockState)STAGE, Integer.valueOf(stage));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumStage() {
/* 32 */     return getMax(STAGE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftSapling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */