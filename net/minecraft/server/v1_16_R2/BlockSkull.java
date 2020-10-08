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
/*    */ public class BlockSkull
/*    */   extends BlockSkullAbstract
/*    */ {
/*    */   public enum Type
/*    */     implements a
/*    */   {
/* 19 */     SKELETON, WITHER_SKELETON, PLAYER, ZOMBIE, CREEPER, DRAGON;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public static final BlockStateInteger a = BlockProperties.aD;
/*    */   
/* 27 */   protected static final VoxelShape b = Block.a(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
/*    */   
/*    */   protected BlockSkull(a var0, BlockBase.Info var1) {
/* 30 */     super(var0, var1);
/* 31 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 36 */     return b;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape d(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 41 */     return VoxelShapes.a();
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 46 */     return getBlockData().set(a, Integer.valueOf(MathHelper.floor((var0.h() * 16.0F / 360.0F) + 0.5D) & 0xF));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 51 */     return var0.set(a, Integer.valueOf(var1.a(((Integer)var0.get(a)).intValue(), 16)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 56 */     return var0.set(a, Integer.valueOf(var1.a(((Integer)var0.get(a)).intValue(), 16)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 61 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */   
/*    */   public static interface a {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */