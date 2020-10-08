/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class BlockWeepingVinesPlant
/*    */   extends BlockGrowingStem
/*    */ {
/*  7 */   public static final VoxelShape d = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
/*    */   
/*    */   public BlockWeepingVinesPlant(BlockBase.Info var0) {
/* 10 */     super(var0, EnumDirection.DOWN, d, false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockGrowingTop c() {
/* 15 */     return (BlockGrowingTop)Blocks.WEEPING_VINES;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockWeepingVinesPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */