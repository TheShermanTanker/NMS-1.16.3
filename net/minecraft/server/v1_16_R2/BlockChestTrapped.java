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
/*    */ public class BlockChestTrapped
/*    */   extends BlockChest
/*    */ {
/*    */   public BlockChestTrapped(BlockBase.Info var0) {
/* 19 */     super(var0, () -> TileEntityTypes.TRAPPED_CHEST);
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess var0) {
/* 24 */     return new TileEntityChestTrapped();
/*    */   }
/*    */ 
/*    */   
/*    */   protected Statistic<MinecraftKey> c() {
/* 29 */     return StatisticList.CUSTOM.b(StatisticList.TRIGGER_TRAPPED_CHEST);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPowerSource(IBlockData var0) {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(IBlockData var0, IBlockAccess var1, BlockPosition var2, EnumDirection var3) {
/* 39 */     return MathHelper.clamp(TileEntityChest.a(var1, var2), 0, 15);
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(IBlockData var0, IBlockAccess var1, BlockPosition var2, EnumDirection var3) {
/* 44 */     if (var3 == EnumDirection.UP) {
/* 45 */       return var0.b(var1, var2, var3);
/*    */     }
/*    */     
/* 48 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockChestTrapped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */