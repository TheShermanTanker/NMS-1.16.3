/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockTorch
/*    */   extends Block
/*    */ {
/* 19 */   protected static final VoxelShape d = Block.a(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
/*    */   protected final ParticleParam e;
/*    */   
/*    */   protected BlockTorch(BlockBase.Info var0, ParticleParam var1) {
/* 23 */     super(var0);
/* 24 */     this.e = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 29 */     return d;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 34 */     if (var1 == EnumDirection.DOWN && !canPlace(var0, var3, var4)) {
/* 35 */       return Blocks.AIR.getBlockData();
/*    */     }
/* 37 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 42 */     return a(var1, var2.down(), EnumDirection.UP);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */