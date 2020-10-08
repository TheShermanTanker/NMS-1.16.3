/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCoralDead
/*    */   extends BlockCoralBase
/*    */ {
/* 11 */   protected static final VoxelShape a = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 15.0D, 14.0D);
/*    */   
/*    */   protected BlockCoralDead(BlockBase.Info var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 19 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCoralDead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */