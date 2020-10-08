/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.Rail;
/*    */ 
/*    */ public abstract class CraftRail extends CraftBlockData implements Rail {
/*  7 */   private static final BlockStateEnum<?> SHAPE = getEnum("shape");
/*    */ 
/*    */   
/*    */   public Rail.Shape getShape() {
/* 11 */     return get(SHAPE, Rail.Shape.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShape(Rail.Shape shape) {
/* 16 */     set(SHAPE, (Enum<Enum>)shape);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Rail.Shape> getShapes() {
/* 21 */     return getValues(SHAPE, Rail.Shape.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftRail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */