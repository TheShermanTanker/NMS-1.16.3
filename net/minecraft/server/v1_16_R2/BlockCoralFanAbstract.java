/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCoralFanAbstract
/*    */   extends BlockCoralBase
/*    */ {
/* 10 */   private static final VoxelShape a = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
/*    */   
/*    */   protected BlockCoralFanAbstract(BlockBase.Info var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 18 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCoralFanAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */