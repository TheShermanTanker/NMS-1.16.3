/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.type.Stairs;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftStairs extends CraftBlockData implements Stairs {
/*  8 */   private static final BlockStateEnum<?> SHAPE = getEnum("shape");
/*    */ 
/*    */   
/*    */   public Stairs.Shape getShape() {
/* 12 */     return (Stairs.Shape)get(SHAPE, Stairs.Shape.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShape(Stairs.Shape shape) {
/* 17 */     set(SHAPE, (Enum)shape);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftStairs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */