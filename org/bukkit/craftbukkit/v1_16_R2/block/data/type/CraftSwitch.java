/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.type.Switch;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftSwitch extends CraftBlockData implements Switch {
/*  8 */   private static final BlockStateEnum<?> FACE = getEnum("face");
/*    */ 
/*    */   
/*    */   public Switch.Face getFace() {
/* 12 */     return (Switch.Face)get(FACE, Switch.Face.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFace(Switch.Face face) {
/* 17 */     set(FACE, (Enum)face);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftSwitch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */