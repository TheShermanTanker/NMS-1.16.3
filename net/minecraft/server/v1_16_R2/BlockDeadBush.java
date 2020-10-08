/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockDeadBush
/*    */   extends BlockPlant
/*    */ {
/* 11 */   protected static final VoxelShape a = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
/*    */   
/*    */   protected BlockDeadBush(BlockBase.Info var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 19 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean c(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 24 */     Block var3 = var0.getBlock();
/* 25 */     return (var3 == Blocks.SAND || var3 == Blocks.RED_SAND || var3 == Blocks.TERRACOTTA || var3 == Blocks.WHITE_TERRACOTTA || var3 == Blocks.ORANGE_TERRACOTTA || var3 == Blocks.MAGENTA_TERRACOTTA || var3 == Blocks.LIGHT_BLUE_TERRACOTTA || var3 == Blocks.YELLOW_TERRACOTTA || var3 == Blocks.LIME_TERRACOTTA || var3 == Blocks.PINK_TERRACOTTA || var3 == Blocks.GRAY_TERRACOTTA || var3 == Blocks.LIGHT_GRAY_TERRACOTTA || var3 == Blocks.CYAN_TERRACOTTA || var3 == Blocks.PURPLE_TERRACOTTA || var3 == Blocks.BLUE_TERRACOTTA || var3 == Blocks.BROWN_TERRACOTTA || var3 == Blocks.GREEN_TERRACOTTA || var3 == Blocks.RED_TERRACOTTA || var3 == Blocks.BLACK_TERRACOTTA || var3 == Blocks.DIRT || var3 == Blocks.COARSE_DIRT || var3 == Blocks.PODZOL);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockDeadBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */