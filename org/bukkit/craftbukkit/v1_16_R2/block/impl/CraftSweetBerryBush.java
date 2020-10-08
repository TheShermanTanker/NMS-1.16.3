/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.BlockSweetBerryBush;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Ageable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftSweetBerryBush
/*    */   extends CraftBlockData implements Ageable {
/*    */   public CraftSweetBerryBush(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftSweetBerryBush() {}
/*    */   
/* 18 */   private static final BlockStateInteger AGE = getInteger(BlockSweetBerryBush.class, "age");
/*    */ 
/*    */   
/*    */   public int getAge() {
/* 22 */     return ((Integer)get((IBlockState)AGE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAge(int age) {
/* 27 */     set((IBlockState)AGE, Integer.valueOf(age));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumAge() {
/* 32 */     return getMax(AGE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftSweetBerryBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */