/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Cake;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftCake extends CraftBlockData implements Cake {
/*  8 */   private static final BlockStateInteger BITES = getInteger("bites");
/*    */ 
/*    */   
/*    */   public int getBites() {
/* 12 */     return ((Integer)get((IBlockState)BITES)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBites(int bites) {
/* 17 */     set((IBlockState)BITES, Integer.valueOf(bites));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumBites() {
/* 22 */     return getMax(BITES);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftCake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */