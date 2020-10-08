/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Piston;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftPiston extends CraftBlockData implements Piston {
/*  8 */   private static final BlockStateBoolean EXTENDED = getBoolean("extended");
/*    */ 
/*    */   
/*    */   public boolean isExtended() {
/* 12 */     return ((Boolean)get((IBlockState)EXTENDED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExtended(boolean extended) {
/* 17 */     set((IBlockState)EXTENDED, Boolean.valueOf(extended));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftPiston.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */