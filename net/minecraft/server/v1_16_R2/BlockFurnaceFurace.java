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
/*    */ 
/*    */ public class BlockFurnaceFurace
/*    */   extends BlockFurnace
/*    */ {
/*    */   protected BlockFurnaceFurace(BlockBase.Info var0) {
/* 21 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess var0) {
/* 26 */     return new TileEntityFurnaceFurnace();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(World var0, BlockPosition var1, EntityHuman var2) {
/* 31 */     TileEntity var3 = var0.getTileEntity(var1);
/* 32 */     if (var3 instanceof TileEntityFurnaceFurnace) {
/* 33 */       var2.openContainer((ITileInventory)var3);
/* 34 */       var2.a(StatisticList.INTERACT_WITH_FURNACE);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFurnaceFurace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */