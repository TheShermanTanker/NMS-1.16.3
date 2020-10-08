/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockPistonExtension;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.TechnicalPiston;
/*    */ 
/*    */ public final class CraftPistonExtension extends CraftBlockData implements PistonHead, TechnicalPiston, Directional {
/*    */   public CraftPistonExtension(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftPistonExtension() {}
/*    */   
/* 18 */   private static final BlockStateBoolean SHORT = getBoolean(BlockPistonExtension.class, "short");
/*    */ 
/*    */   
/*    */   public boolean isShort() {
/* 22 */     return ((Boolean)get((IBlockState)SHORT)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShort(boolean _short) {
/* 27 */     set((IBlockState)SHORT, Boolean.valueOf(_short));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> TYPE = getEnum(BlockPistonExtension.class, "type");
/*    */ 
/*    */   
/*    */   public TechnicalPiston.Type getType() {
/* 36 */     return (TechnicalPiston.Type)get(TYPE, TechnicalPiston.Type.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setType(TechnicalPiston.Type type) {
/* 41 */     set(TYPE, (Enum)type);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 46 */   private static final BlockStateEnum<?> FACING = getEnum(BlockPistonExtension.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 50 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 55 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 60 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftPistonExtension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */