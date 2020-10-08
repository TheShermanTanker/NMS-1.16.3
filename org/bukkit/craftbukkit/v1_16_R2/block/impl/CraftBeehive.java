/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockBeehive;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.Beehive;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftBeehive extends CraftBlockData implements Beehive, Directional {
/*    */   public CraftBeehive(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftBeehive() {}
/*    */   
/* 18 */   private static final BlockStateInteger HONEY_LEVEL = getInteger(BlockBeehive.class, "honey_level");
/*    */ 
/*    */   
/*    */   public int getHoneyLevel() {
/* 22 */     return ((Integer)get((IBlockState)HONEY_LEVEL)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHoneyLevel(int honeyLevel) {
/* 27 */     set((IBlockState)HONEY_LEVEL, Integer.valueOf(honeyLevel));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumHoneyLevel() {
/* 32 */     return getMax(HONEY_LEVEL);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   private static final BlockStateEnum<?> FACING = getEnum(BlockBeehive.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 41 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 46 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 51 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftBeehive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */