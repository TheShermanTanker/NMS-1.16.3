/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class BlockTwistingVinesPlant
/*    */   extends BlockGrowingStem
/*    */ {
/*  7 */   public static final VoxelShape d = Block.a(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
/*    */   
/*    */   public BlockTwistingVinesPlant(BlockBase.Info var0) {
/* 10 */     super(var0, EnumDirection.UP, d, false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockGrowingTop c() {
/* 15 */     return (BlockGrowingTop)Blocks.TWISTING_VINES;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTwistingVinesPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */