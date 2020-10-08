/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockNetherSprouts
/*    */   extends BlockPlant
/*    */ {
/* 11 */   protected static final VoxelShape a = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
/*    */   
/*    */   public BlockNetherSprouts(BlockBase.Info var0) {
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
/* 24 */     return (var0.a(TagsBlock.NYLIUM) || var0.a(Blocks.SOUL_SOIL) || super.c(var0, var1, var2));
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockBase.EnumRandomOffset ah_() {
/* 29 */     return BlockBase.EnumRandomOffset.XZ;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockNetherSprouts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */