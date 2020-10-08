/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BlockSkullAbstract
/*    */   extends BlockTileEntity
/*    */   implements ItemWearable
/*    */ {
/*    */   private final BlockSkull.a a;
/*    */   
/*    */   public BlockSkullAbstract(BlockSkull.a var0, BlockBase.Info var1) {
/* 15 */     super(var1);
/* 16 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess var0) {
/* 21 */     return new TileEntitySkull();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 30 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSkullAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */