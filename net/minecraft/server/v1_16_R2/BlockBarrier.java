/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockBarrier
/*    */   extends Block
/*    */ {
/*    */   protected BlockBarrier(BlockBase.Info var0) {
/*  9 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 14 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRenderType b(IBlockData var0) {
/* 19 */     return EnumRenderType.INVISIBLE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBarrier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */