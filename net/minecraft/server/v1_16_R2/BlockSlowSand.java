/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
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
/*    */ public class BlockSlowSand
/*    */   extends Block
/*    */ {
/* 18 */   protected static final VoxelShape a = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);
/*    */ 
/*    */   
/*    */   public BlockSlowSand(BlockBase.Info var0) {
/* 22 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape c(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 27 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape e(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 32 */     return VoxelShapes.b();
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape a(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 37 */     return VoxelShapes.b();
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData var0, WorldServer var1, BlockPosition var2, Random var3) {
/* 42 */     BlockBubbleColumn.a(var1, var2.up(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 47 */     if (var1 == EnumDirection.UP && var2.a(Blocks.WATER)) {
/* 48 */       var3.getBlockTickList().a(var4, this, 20);
/*    */     }
/*    */     
/* 51 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlace(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/* 56 */     var1.getBlockTickList().a(var2, this, 20);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 61 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSlowSand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */