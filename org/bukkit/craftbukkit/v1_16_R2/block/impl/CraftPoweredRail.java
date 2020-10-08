/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockPoweredRail;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Powerable;
/*    */ import org.bukkit.block.data.Rail;
/*    */ import org.bukkit.block.data.type.RedstoneRail;
/*    */ 
/*    */ public final class CraftPoweredRail extends CraftBlockData implements RedstoneRail, Powerable, Rail {
/*    */   public CraftPoweredRail(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftPoweredRail() {}
/*    */   
/* 18 */   private static final BlockStateBoolean POWERED = getBoolean(BlockPoweredRail.class, "powered");
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 22 */     return ((Boolean)get((IBlockState)POWERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPowered(boolean powered) {
/* 27 */     set((IBlockState)POWERED, Boolean.valueOf(powered));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> SHAPE = getEnum(BlockPoweredRail.class, "shape");
/*    */ 
/*    */   
/*    */   public Rail.Shape getShape() {
/* 36 */     return (Rail.Shape)get(SHAPE, Rail.Shape.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShape(Rail.Shape shape) {
/* 41 */     set(SHAPE, (Enum)shape);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Rail.Shape> getShapes() {
/* 46 */     return getValues(SHAPE, Rail.Shape.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftPoweredRail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */