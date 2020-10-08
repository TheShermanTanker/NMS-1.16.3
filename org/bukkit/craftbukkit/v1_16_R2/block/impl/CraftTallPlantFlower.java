/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.BlockTallPlantFlower;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import org.bukkit.block.data.Bisected;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftTallPlantFlower extends CraftBlockData implements Bisected {
/*    */   public CraftTallPlantFlower() {}
/*    */   
/*    */   public CraftTallPlantFlower(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 18 */   private static final BlockStateEnum<?> HALF = getEnum(BlockTallPlantFlower.class, "half");
/*    */ 
/*    */   
/*    */   public Bisected.Half getHalf() {
/* 22 */     return (Bisected.Half)get(HALF, Bisected.Half.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHalf(Bisected.Half half) {
/* 27 */     set(HALF, (Enum)half);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftTallPlantFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */