/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockPressurePlateBinary;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Powerable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftPressurePlateBinary
/*    */   extends CraftBlockData implements Powerable {
/*    */   public CraftPressurePlateBinary(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftPressurePlateBinary() {}
/*    */   
/* 18 */   private static final BlockStateBoolean POWERED = getBoolean(BlockPressurePlateBinary.class, "powered");
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 22 */     return ((Boolean)get((IBlockState)POWERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPowered(boolean powered) {
/* 27 */     set((IBlockState)POWERED, Boolean.valueOf(powered));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftPressurePlateBinary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */