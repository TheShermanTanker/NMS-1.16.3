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
/*    */ 
/*    */ 
/*    */ public class BlockBeetroot
/*    */   extends BlockCrops
/*    */ {
/* 20 */   public static final BlockStateInteger a = BlockProperties.ag;
/*    */   
/* 22 */   private static final VoxelShape[] c = new VoxelShape[] {
/* 23 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), 
/* 24 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), 
/* 25 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), 
/* 26 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)
/*    */     };
/*    */   
/*    */   public BlockBeetroot(BlockBase.Info var0) {
/* 30 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockStateInteger c() {
/* 35 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int d() {
/* 40 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(IBlockData var0, WorldServer var1, BlockPosition var2, Random var3) {
/* 51 */     if (var3.nextInt(3) != 0) {
/* 52 */       super.tick(var0, var1, var2, var3);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected int a(World var0) {
/* 58 */     return super.a(var0) / 3;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 63 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 68 */     return c[((Integer)var0.get(c())).intValue()];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBeetroot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */