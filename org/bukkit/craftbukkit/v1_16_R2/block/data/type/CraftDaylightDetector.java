/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.DaylightDetector;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftDaylightDetector extends CraftBlockData implements DaylightDetector {
/*  8 */   private static final BlockStateBoolean INVERTED = getBoolean("inverted");
/*    */ 
/*    */   
/*    */   public boolean isInverted() {
/* 12 */     return ((Boolean)get((IBlockState)INVERTED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInverted(boolean inverted) {
/* 17 */     set((IBlockState)INVERTED, Boolean.valueOf(inverted));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftDaylightDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */