/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockHay
/*    */   extends BlockRotatable
/*    */ {
/*    */   public BlockHay(BlockBase.Info var0) {
/* 10 */     super(var0);
/* 11 */     j(((IBlockData)this.blockStateList.getBlockData()).set(AXIS, EnumDirection.EnumAxis.Y));
/*    */   }
/*    */ 
/*    */   
/*    */   public void fallOn(World var0, BlockPosition var1, Entity var2, float var3) {
/* 16 */     var2.b(var3, 0.2F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockHay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */