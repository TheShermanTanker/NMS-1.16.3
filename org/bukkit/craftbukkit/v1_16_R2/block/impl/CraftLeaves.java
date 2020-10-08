/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockLeaves;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Leaves;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftLeaves extends CraftBlockData implements Leaves {
/*    */   public CraftLeaves(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftLeaves() {}
/*    */   
/* 18 */   private static final BlockStateInteger DISTANCE = getInteger(BlockLeaves.class, "distance");
/* 19 */   private static final BlockStateBoolean PERSISTENT = getBoolean(BlockLeaves.class, "persistent");
/*    */ 
/*    */   
/*    */   public boolean isPersistent() {
/* 23 */     return ((Boolean)get((IBlockState)PERSISTENT)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPersistent(boolean persistent) {
/* 28 */     set((IBlockState)PERSISTENT, Boolean.valueOf(persistent));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDistance() {
/* 33 */     return ((Integer)get((IBlockState)DISTANCE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDistance(int distance) {
/* 38 */     set((IBlockState)DISTANCE, Integer.valueOf(distance));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */