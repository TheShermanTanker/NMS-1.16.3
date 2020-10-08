/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockRedstoneOre;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Lightable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftRedstoneOre
/*    */   extends CraftBlockData implements Lightable {
/*    */   public CraftRedstoneOre(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftRedstoneOre() {}
/*    */   
/* 18 */   private static final BlockStateBoolean LIT = getBoolean(BlockRedstoneOre.class, "lit");
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftRedstoneOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */