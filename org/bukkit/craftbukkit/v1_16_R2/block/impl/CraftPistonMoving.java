/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockPistonMoving;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Directional;
/*    */ import org.bukkit.block.data.type.TechnicalPiston;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftPistonMoving extends CraftBlockData implements TechnicalPiston, Directional {
/*    */   public CraftPistonMoving(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftPistonMoving() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> TYPE = getEnum(BlockPistonMoving.class, "type");
/*    */ 
/*    */   
/*    */   public TechnicalPiston.Type getType() {
/* 22 */     return (TechnicalPiston.Type)get(TYPE, TechnicalPiston.Type.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setType(TechnicalPiston.Type type) {
/* 27 */     set(TYPE, (Enum)type);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockPistonMoving.class, "facing");
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
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftPistonMoving.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */