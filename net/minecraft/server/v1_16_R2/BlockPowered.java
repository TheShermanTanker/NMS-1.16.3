/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockPowered
/*    */   extends Block
/*    */ {
/*    */   public BlockPowered(BlockBase.Info var0) {
/* 11 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPowerSource(IBlockData var0) {
/* 16 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(IBlockData var0, IBlockAccess var1, BlockPosition var2, EnumDirection var3) {
/* 21 */     return 15;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPowered.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */