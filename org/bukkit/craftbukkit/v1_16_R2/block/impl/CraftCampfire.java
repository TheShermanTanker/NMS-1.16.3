/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockCampfire;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ 
/*    */ public final class CraftCampfire extends CraftBlockData implements Campfire, Directional, Lightable, Waterlogged {
/*    */   public CraftCampfire(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftCampfire() {}
/*    */   
/* 18 */   private static final BlockStateBoolean SIGNAL_FIRE = getBoolean(BlockCampfire.class, "signal_fire");
/*    */ 
/*    */   
/*    */   public boolean isSignalFire() {
/* 22 */     return ((Boolean)get((IBlockState)SIGNAL_FIRE)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSignalFire(boolean signalFire) {
/* 27 */     set((IBlockState)SIGNAL_FIRE, Boolean.valueOf(signalFire));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockCampfire.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 36 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 41 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 46 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 51 */   private static final BlockStateBoolean LIT = getBoolean(BlockCampfire.class, "lit");
/*    */ 
/*    */   
/*    */   public boolean isLit() {
/* 55 */     return ((Boolean)get((IBlockState)LIT)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLit(boolean lit) {
/* 60 */     set((IBlockState)LIT, Boolean.valueOf(lit));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 65 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockCampfire.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 69 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 74 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftCampfire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */