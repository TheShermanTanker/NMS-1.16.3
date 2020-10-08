/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockDirtSnow;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Snowable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftDirtSnow
/*    */   extends CraftBlockData implements Snowable {
/*    */   public CraftDirtSnow(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftDirtSnow() {}
/*    */   
/* 18 */   private static final BlockStateBoolean SNOWY = getBoolean(BlockDirtSnow.class, "snowy");
/*    */ 
/*    */   
/*    */   public boolean isSnowy() {
/* 22 */     return ((Boolean)get((IBlockState)SNOWY)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSnowy(boolean snowy) {
/* 27 */     set((IBlockState)SNOWY, Boolean.valueOf(snowy));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftDirtSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */