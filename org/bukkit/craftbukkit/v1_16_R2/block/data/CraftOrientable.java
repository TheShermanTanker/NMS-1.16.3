/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.Axis;
/*    */ 
/*    */ public class CraftOrientable extends CraftBlockData implements Orientable {
/*  7 */   private static final BlockStateEnum<?> AXIS = getEnum("axis");
/*    */ 
/*    */   
/*    */   public Axis getAxis() {
/* 11 */     return get(AXIS, Axis.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAxis(Axis axis) {
/* 16 */     set(AXIS, (Enum<Enum>)axis);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Axis> getAxes() {
/* 21 */     return getValues(AXIS, Axis.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftOrientable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */