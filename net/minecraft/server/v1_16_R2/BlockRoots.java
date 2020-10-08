/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockRoots
/*    */   extends BlockPlant
/*    */ {
/* 12 */   protected static final VoxelShape a = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
/*    */   
/*    */   protected BlockRoots(BlockBase.Info var0) {
/* 15 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 20 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean c(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 25 */     return (var0.a(TagsBlock.NYLIUM) || var0.a(Blocks.SOUL_SOIL) || super.c(var0, var1, var2));
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockBase.EnumRandomOffset ah_() {
/* 30 */     return BlockBase.EnumRandomOffset.XZ;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockRoots.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */