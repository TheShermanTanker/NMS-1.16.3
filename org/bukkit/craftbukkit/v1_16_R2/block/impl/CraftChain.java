/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockChain;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.Axis;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ import org.bukkit.block.data.type.Chain;
/*    */ 
/*    */ public final class CraftChain extends CraftBlockData implements Chain, Orientable, Waterlogged {
/*    */   public CraftChain(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftChain() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> AXIS = getEnum(BlockChain.class, "axis");
/*    */ 
/*    */   
/*    */   public Axis getAxis() {
/* 22 */     return (Axis)get(AXIS, Axis.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAxis(Axis axis) {
/* 27 */     set(AXIS, (Enum)axis);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Axis> getAxes() {
/* 32 */     return getValues(AXIS, Axis.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockChain.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 41 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 46 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftChain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */