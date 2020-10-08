/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockPortal;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import org.bukkit.Axis;
/*    */ import org.bukkit.block.data.Orientable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftPortal extends CraftBlockData implements Orientable {
/*    */   public CraftPortal(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftPortal() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> AXIS = getEnum(BlockPortal.class, "axis");
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */