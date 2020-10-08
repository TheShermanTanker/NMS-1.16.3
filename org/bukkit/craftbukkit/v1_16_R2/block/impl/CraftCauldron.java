/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockCauldron;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Levelled;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftCauldron
/*    */   extends CraftBlockData implements Levelled {
/*    */   public CraftCauldron(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftCauldron() {}
/*    */   
/* 18 */   private static final BlockStateInteger LEVEL = getInteger(BlockCauldron.class, "level");
/*    */ 
/*    */   
/*    */   public int getLevel() {
/* 22 */     return ((Integer)get((IBlockState)LEVEL)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLevel(int level) {
/* 27 */     set((IBlockState)LEVEL, Integer.valueOf(level));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumLevel() {
/* 32 */     return getMax(LEVEL);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftCauldron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */