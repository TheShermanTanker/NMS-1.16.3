/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockCake;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Cake;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftCake
/*    */   extends CraftBlockData implements Cake {
/*    */   public CraftCake(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftCake() {}
/*    */   
/* 18 */   private static final BlockStateInteger BITES = getInteger(BlockCake.class, "bites");
/*    */ 
/*    */   
/*    */   public int getBites() {
/* 22 */     return ((Integer)get((IBlockState)BITES)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBites(int bites) {
/* 27 */     set((IBlockState)BITES, Integer.valueOf(bites));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumBites() {
/* 32 */     return getMax(BITES);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftCake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */