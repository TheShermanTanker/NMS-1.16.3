/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Ageable;
/*    */ 
/*    */ public abstract class CraftAgeable extends CraftBlockData implements Ageable {
/*  7 */   private static final BlockStateInteger AGE = getInteger("age");
/*    */ 
/*    */   
/*    */   public int getAge() {
/* 11 */     return ((Integer)get((IBlockState<Integer>)AGE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAge(int age) {
/* 16 */     set((IBlockState<Comparable>)AGE, Integer.valueOf(age));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumAge() {
/* 21 */     return getMax(AGE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftAgeable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */