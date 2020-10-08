/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockAir
/*    */   extends Block
/*    */ {
/*    */   protected BlockAir(BlockBase.Info var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRenderType b(IBlockData var0) {
/* 17 */     return EnumRenderType.INVISIBLE;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 22 */     return VoxelShapes.a();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockAir.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */