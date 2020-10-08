/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockHay;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import org.bukkit.Axis;
/*    */ import org.bukkit.block.data.Orientable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftHay extends CraftBlockData implements Orientable {
/*    */   public CraftHay(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftHay() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> AXIS = getEnum(BlockHay.class, "axis");
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
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftHay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */