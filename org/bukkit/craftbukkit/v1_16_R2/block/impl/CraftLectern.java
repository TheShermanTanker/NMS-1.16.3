/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockLectern;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.Lectern;
/*    */ 
/*    */ public final class CraftLectern extends CraftBlockData implements Lectern, Directional, Powerable {
/*    */   public CraftLectern(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftLectern() {}
/*    */   
/* 18 */   private static final BlockStateBoolean HAS_BOOK = getBoolean(BlockLectern.class, "has_book");
/*    */ 
/*    */   
/*    */   public boolean hasBook() {
/* 22 */     return ((Boolean)get((IBlockState)HAS_BOOK)).booleanValue();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 27 */   private static final BlockStateEnum<?> FACING = getEnum(BlockLectern.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 31 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 36 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 41 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 46 */   private static final BlockStateBoolean POWERED = getBoolean(BlockLectern.class, "powered");
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 50 */     return ((Boolean)get((IBlockState)POWERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPowered(boolean powered) {
/* 55 */     set((IBlockState)POWERED, Boolean.valueOf(powered));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftLectern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */