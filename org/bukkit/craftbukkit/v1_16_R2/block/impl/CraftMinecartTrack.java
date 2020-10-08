/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockMinecartTrack;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import org.bukkit.block.data.Rail;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftMinecartTrack
/*    */   extends CraftBlockData implements Rail {
/*    */   public CraftMinecartTrack(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftMinecartTrack() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> SHAPE = getEnum(BlockMinecartTrack.class, "shape");
/*    */ 
/*    */   
/*    */   public Rail.Shape getShape() {
/* 22 */     return (Rail.Shape)get(SHAPE, Rail.Shape.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShape(Rail.Shape shape) {
/* 27 */     set(SHAPE, (Enum)shape);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Rail.Shape> getShapes() {
/* 32 */     return getValues(SHAPE, Rail.Shape.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftMinecartTrack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */