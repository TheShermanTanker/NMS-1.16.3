/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.Wall;
/*    */ 
/*    */ public abstract class CraftWall extends CraftBlockData implements Wall {
/*  8 */   private static final BlockStateBoolean UP = getBoolean("up");
/*  9 */   private static final BlockStateEnum<?>[] HEIGHTS = new BlockStateEnum[] {
/* 10 */       getEnum("north"), getEnum("east"), getEnum("south"), getEnum("west")
/*    */     };
/*    */ 
/*    */   
/*    */   public boolean isUp() {
/* 15 */     return ((Boolean)get((IBlockState)UP)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUp(boolean up) {
/* 20 */     set((IBlockState)UP, Boolean.valueOf(up));
/*    */   }
/*    */ 
/*    */   
/*    */   public Wall.Height getHeight(BlockFace face) {
/* 25 */     return (Wall.Height)get(HEIGHTS[face.ordinal()], Wall.Height.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHeight(BlockFace face, Wall.Height height) {
/* 30 */     set(HEIGHTS[face.ordinal()], (Enum)height);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */