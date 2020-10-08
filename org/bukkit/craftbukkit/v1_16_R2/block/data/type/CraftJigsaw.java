/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.type.Jigsaw;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftJigsaw extends CraftBlockData implements Jigsaw {
/*  8 */   private static final BlockStateEnum<?> ORIENTATION = getEnum("orientation");
/*    */ 
/*    */   
/*    */   public Jigsaw.Orientation getOrientation() {
/* 12 */     return (Jigsaw.Orientation)get(ORIENTATION, Jigsaw.Orientation.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOrientation(Jigsaw.Orientation orientation) {
/* 17 */     set(ORIENTATION, (Enum)orientation);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftJigsaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */