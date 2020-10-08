/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockChest;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ import org.bukkit.block.data.type.Chest;
/*    */ 
/*    */ public final class CraftChest extends CraftBlockData implements Chest, Directional, Waterlogged {
/*    */   public CraftChest(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftChest() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> TYPE = getEnum(BlockChest.class, "type");
/*    */ 
/*    */   
/*    */   public Chest.Type getType() {
/* 22 */     return (Chest.Type)get(TYPE, Chest.Type.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setType(Chest.Type type) {
/* 27 */     set(TYPE, (Enum)type);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateEnum<?> FACING = getEnum(BlockChest.class, "facing");
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
/* 51 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockChest.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 55 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 60 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */