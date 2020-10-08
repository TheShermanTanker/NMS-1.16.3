/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockJigsaw;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import org.bukkit.block.data.type.Jigsaw;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftJigsaw extends CraftBlockData implements Jigsaw {
/*    */   public CraftJigsaw() {}
/*    */   
/*    */   public CraftJigsaw(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 18 */   private static final BlockStateEnum<?> ORIENTATION = getEnum(BlockJigsaw.class, "orientation");
/*    */ 
/*    */   
/*    */   public Jigsaw.Orientation getOrientation() {
/* 22 */     return (Jigsaw.Orientation)get(ORIENTATION, Jigsaw.Orientation.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOrientation(Jigsaw.Orientation orientation) {
/* 27 */     set(ORIENTATION, (Enum)orientation);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftJigsaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */