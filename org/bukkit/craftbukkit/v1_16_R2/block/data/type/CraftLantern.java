/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Lantern;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftLantern extends CraftBlockData implements Lantern {
/*  8 */   private static final BlockStateBoolean HANGING = getBoolean("hanging");
/*    */ 
/*    */   
/*    */   public boolean isHanging() {
/* 12 */     return ((Boolean)get((IBlockState)HANGING)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHanging(boolean hanging) {
/* 17 */     set((IBlockState)HANGING, Boolean.valueOf(hanging));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftLantern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */