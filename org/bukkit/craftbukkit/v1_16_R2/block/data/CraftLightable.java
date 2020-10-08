/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Lightable;
/*    */ 
/*    */ public abstract class CraftLightable extends CraftBlockData implements Lightable {
/*  7 */   private static final BlockStateBoolean LIT = getBoolean("lit");
/*    */ 
/*    */   
/*    */   public boolean isLit() {
/* 11 */     return ((Boolean)get((IBlockState<Boolean>)LIT)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLit(boolean lit) {
/* 16 */     set((IBlockState<Comparable>)LIT, Boolean.valueOf(lit));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftLightable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */