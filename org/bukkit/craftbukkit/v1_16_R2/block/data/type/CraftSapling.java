/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Sapling;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftSapling extends CraftBlockData implements Sapling {
/*  8 */   private static final BlockStateInteger STAGE = getInteger("stage");
/*    */ 
/*    */   
/*    */   public int getStage() {
/* 12 */     return ((Integer)get((IBlockState)STAGE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStage(int stage) {
/* 17 */     set((IBlockState)STAGE, Integer.valueOf(stage));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumStage() {
/* 22 */     return getMax(STAGE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftSapling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */