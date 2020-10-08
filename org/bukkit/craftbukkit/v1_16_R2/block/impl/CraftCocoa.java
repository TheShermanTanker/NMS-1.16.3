/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockCocoa;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Ageable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftCocoa extends CraftBlockData implements Cocoa, Ageable, Directional {
/*    */   public CraftCocoa(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftCocoa() {}
/*    */   
/* 18 */   private static final BlockStateInteger AGE = getInteger(BlockCocoa.class, "age");
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
/*    */ 
/*    */ 
/*    */   
/* 37 */   private static final BlockStateEnum<?> FACING = getEnum(BlockCocoa.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 41 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 46 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 51 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftCocoa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */