/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.BlockTallSeaGrass;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import org.bukkit.block.data.Bisected;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftTallSeaGrass extends CraftBlockData implements Bisected {
/*    */   public CraftTallSeaGrass() {}
/*    */   
/*    */   public CraftTallSeaGrass(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 18 */   private static final BlockStateEnum<?> HALF = getEnum(BlockTallSeaGrass.class, "half");
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftTallSeaGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */