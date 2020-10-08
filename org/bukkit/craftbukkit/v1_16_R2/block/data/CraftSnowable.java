/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Snowable;
/*    */ 
/*    */ public abstract class CraftSnowable extends CraftBlockData implements Snowable {
/*  7 */   private static final BlockStateBoolean SNOWY = getBoolean("snowy");
/*    */ 
/*    */   
/*    */   public boolean isSnowy() {
/* 11 */     return ((Boolean)get((IBlockState<Boolean>)SNOWY)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSnowy(boolean snowy) {
/* 16 */     set((IBlockState<Comparable>)SNOWY, Boolean.valueOf(snowy));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftSnowable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */