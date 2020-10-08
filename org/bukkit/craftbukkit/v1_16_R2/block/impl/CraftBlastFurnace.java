/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockBlastFurnace;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.Furnace;
/*    */ 
/*    */ public final class CraftBlastFurnace extends CraftBlockData implements Furnace, Directional, Lightable {
/*    */   public CraftBlastFurnace(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftBlastFurnace() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> FACING = getEnum(BlockBlastFurnace.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 22 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 27 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 32 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   private static final BlockStateBoolean LIT = getBoolean(BlockBlastFurnace.class, "lit");
/*    */ 
/*    */   
/*    */   public boolean isLit() {
/* 41 */     return ((Boolean)get((IBlockState)LIT)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLit(boolean lit) {
/* 46 */     set((IBlockState)LIT, Boolean.valueOf(lit));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftBlastFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */