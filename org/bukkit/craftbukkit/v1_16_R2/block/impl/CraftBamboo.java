/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import net.minecraft.server.v1_16_R2.BlockBamboo;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Ageable;
/*    */ import org.bukkit.block.data.type.Bamboo;
/*    */ import org.bukkit.block.data.type.Sapling;
/*    */ 
/*    */ public final class CraftBamboo extends CraftBlockData implements Bamboo, Ageable, Sapling {
/*    */   public CraftBamboo(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftBamboo() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> LEAVES = getEnum(BlockBamboo.class, "leaves");
/*    */ 
/*    */   
/*    */   public Bamboo.Leaves getLeaves() {
/* 22 */     return (Bamboo.Leaves)get(LEAVES, Bamboo.Leaves.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLeaves(Bamboo.Leaves leaves) {
/* 27 */     set(LEAVES, (Enum)leaves);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateInteger AGE = getInteger(BlockBamboo.class, "age");
/*    */ 
/*    */   
/*    */   public int getAge() {
/* 36 */     return ((Integer)get((IBlockState)AGE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAge(int age) {
/* 41 */     set((IBlockState)AGE, Integer.valueOf(age));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumAge() {
/* 46 */     return getMax(AGE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 51 */   private static final BlockStateInteger STAGE = getInteger(BlockBamboo.class, "stage");
/*    */ 
/*    */   
/*    */   public int getStage() {
/* 55 */     return ((Integer)get((IBlockState)STAGE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStage(int stage) {
/* 60 */     set((IBlockState)STAGE, Integer.valueOf(stage));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumStage() {
/* 65 */     return getMax(STAGE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftBamboo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */