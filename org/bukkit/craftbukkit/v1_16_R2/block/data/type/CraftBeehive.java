/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Beehive;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftBeehive extends CraftBlockData implements Beehive {
/*  8 */   private static final BlockStateInteger HONEY_LEVEL = getInteger("honey_level");
/*    */ 
/*    */   
/*    */   public int getHoneyLevel() {
/* 12 */     return ((Integer)get((IBlockState)HONEY_LEVEL)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHoneyLevel(int honeyLevel) {
/* 17 */     set((IBlockState)HONEY_LEVEL, Integer.valueOf(honeyLevel));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumHoneyLevel() {
/* 22 */     return getMax(HONEY_LEVEL);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftBeehive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */