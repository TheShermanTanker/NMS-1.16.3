/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockGlazedTerracotta
/*    */   extends BlockFacingHorizontal
/*    */ {
/*    */   public BlockGlazedTerracotta(BlockBase.Info var0) {
/* 10 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 15 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING });
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 20 */     return getBlockData().set(FACING, var0.f().opposite());
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumPistonReaction getPushReaction(IBlockData var0) {
/* 25 */     return EnumPistonReaction.PUSH_ONLY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockGlazedTerracotta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */