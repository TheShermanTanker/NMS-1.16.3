/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockWeepingVines
/*    */   extends BlockGrowingTop
/*    */ {
/* 10 */   protected static final VoxelShape e = Block.a(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);
/*    */   
/*    */   public BlockWeepingVines(BlockBase.Info var0) {
/* 13 */     super(var0, EnumDirection.DOWN, e, false, 0.1D);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int a(Random var0) {
/* 18 */     return BlockNetherVinesUtil.a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Block d() {
/* 23 */     return Blocks.WEEPING_VINES_PLANT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean h(IBlockData var0) {
/* 28 */     return BlockNetherVinesUtil.a(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockWeepingVines.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */