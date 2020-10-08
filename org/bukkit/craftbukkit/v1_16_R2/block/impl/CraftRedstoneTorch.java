/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockRedstoneTorch;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Lightable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftRedstoneTorch
/*    */   extends CraftBlockData implements Lightable {
/*    */   public CraftRedstoneTorch(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftRedstoneTorch() {}
/*    */   
/* 18 */   private static final BlockStateBoolean LIT = getBoolean(BlockRedstoneTorch.class, "lit");
/*    */ 
/*    */   
/*    */   public boolean isLit() {
/* 22 */     return ((Boolean)get((IBlockState)LIT)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLit(boolean lit) {
/* 27 */     set((IBlockState)LIT, Boolean.valueOf(lit));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftRedstoneTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */