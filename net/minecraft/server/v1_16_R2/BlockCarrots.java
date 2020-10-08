/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCarrots
/*    */   extends BlockCrops
/*    */ {
/* 12 */   private static final VoxelShape[] a = new VoxelShape[] {
/* 13 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), 
/* 14 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D), 
/* 15 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), 
/* 16 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), 
/* 17 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), 
/* 18 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D), 
/* 19 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), 
/* 20 */       Block.a(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D)
/*    */     };
/*    */   
/*    */   public BlockCarrots(BlockBase.Info var0) {
/* 24 */     super(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 34 */     return a[((Integer)var0.get(c())).intValue()];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCarrots.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */