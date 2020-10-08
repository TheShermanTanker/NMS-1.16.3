/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public class CraftLeaves extends CraftBlockData implements Leaves {
/*  8 */   private static final BlockStateInteger DISTANCE = getInteger("distance");
/*  9 */   private static final BlockStateBoolean PERSISTENT = getBoolean("persistent");
/*    */ 
/*    */   
/*    */   public boolean isPersistent() {
/* 13 */     return ((Boolean)get((IBlockState)PERSISTENT)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPersistent(boolean persistent) {
/* 18 */     set((IBlockState)PERSISTENT, Boolean.valueOf(persistent));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDistance() {
/* 23 */     return ((Integer)get((IBlockState)DISTANCE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDistance(int distance) {
/* 28 */     set((IBlockState)DISTANCE, Integer.valueOf(distance));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */