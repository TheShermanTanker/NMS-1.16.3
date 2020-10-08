/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import net.minecraft.server.v1_16_R2.BlockCobbleWall;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ import org.bukkit.block.data.type.Wall;
/*    */ 
/*    */ public final class CraftCobbleWall extends CraftBlockData implements Wall, Waterlogged {
/*    */   public CraftCobbleWall(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftCobbleWall() {}
/*    */   
/* 18 */   private static final BlockStateBoolean UP = getBoolean(BlockCobbleWall.class, "up");
/* 19 */   private static final BlockStateEnum<?>[] HEIGHTS = new BlockStateEnum[] {
/* 20 */       getEnum(BlockCobbleWall.class, "north"), getEnum(BlockCobbleWall.class, "east"), getEnum(BlockCobbleWall.class, "south"), getEnum(BlockCobbleWall.class, "west")
/*    */     };
/*    */ 
/*    */   
/*    */   public boolean isUp() {
/* 25 */     return ((Boolean)get((IBlockState)UP)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUp(boolean up) {
/* 30 */     set((IBlockState)UP, Boolean.valueOf(up));
/*    */   }
/*    */ 
/*    */   
/*    */   public Wall.Height getHeight(BlockFace face) {
/* 35 */     return (Wall.Height)get(HEIGHTS[face.ordinal()], Wall.Height.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHeight(BlockFace face, Wall.Height height) {
/* 40 */     set(HEIGHTS[face.ordinal()], (Enum)height);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 45 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockCobbleWall.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 49 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 54 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftCobbleWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */