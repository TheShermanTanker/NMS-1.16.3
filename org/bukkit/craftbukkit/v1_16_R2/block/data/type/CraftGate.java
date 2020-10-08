/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Gate;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftGate extends CraftBlockData implements Gate {
/*  8 */   private static final BlockStateBoolean IN_WALL = getBoolean("in_wall");
/*    */ 
/*    */   
/*    */   public boolean isInWall() {
/* 12 */     return ((Boolean)get((IBlockState)IN_WALL)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInWall(boolean inWall) {
/* 17 */     set((IBlockState)IN_WALL, Boolean.valueOf(inWall));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */