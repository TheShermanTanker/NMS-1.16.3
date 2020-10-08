/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.type.Door;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftDoor extends CraftBlockData implements Door {
/*  8 */   private static final BlockStateEnum<?> HINGE = getEnum("hinge");
/*    */ 
/*    */   
/*    */   public Door.Hinge getHinge() {
/* 12 */     return (Door.Hinge)get(HINGE, Door.Hinge.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHinge(Door.Hinge hinge) {
/* 17 */     set(HINGE, (Enum)hinge);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */