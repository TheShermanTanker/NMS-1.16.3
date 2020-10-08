/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockStructureVoid
/*    */   extends Block
/*    */ {
/* 12 */   private static final VoxelShape a = Block.a(5.0D, 5.0D, 5.0D, 11.0D, 11.0D, 11.0D);
/*    */   
/*    */   protected BlockStructureVoid(BlockBase.Info var0) {
/* 15 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRenderType b(IBlockData var0) {
/* 20 */     return EnumRenderType.INVISIBLE;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 25 */     return a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumPistonReaction getPushReaction(IBlockData var0) {
/* 35 */     return EnumPistonReaction.DESTROY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStructureVoid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */