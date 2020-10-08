/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BlockGlassAbstract
/*    */   extends BlockHalfTransparent
/*    */ {
/*    */   protected BlockGlassAbstract(BlockBase.Info var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape a(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 17 */     return VoxelShapes.a();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 27 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockGlassAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */