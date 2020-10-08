/*    */ package net.minecraft.server.v1_16_R2;
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
/*    */ 
/*    */ public class BlockSmoker
/*    */   extends BlockFurnace
/*    */ {
/*    */   protected BlockSmoker(BlockBase.Info var0) {
/* 20 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess var0) {
/* 25 */     return new TileEntitySmoker();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(World var0, BlockPosition var1, EntityHuman var2) {
/* 30 */     TileEntity var3 = var0.getTileEntity(var1);
/* 31 */     if (var3 instanceof TileEntitySmoker) {
/* 32 */       var2.openContainer((ITileInventory)var3);
/* 33 */       var2.a(StatisticList.INTERACT_WITH_SMOKER);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSmoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */