/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class BlockMobSpawner
/*    */   extends BlockTileEntity {
/*    */   protected BlockMobSpawner(BlockBase.Info blockbase_info) {
/*  6 */     super(blockbase_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess iblockaccess) {
/* 11 */     return new TileEntityMobSpawner();
/*    */   }
/*    */ 
/*    */   
/*    */   public void dropNaturally(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, ItemStack itemstack) {
/* 16 */     super.dropNaturally(iblockdata, worldserver, blockposition, itemstack);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExpDrop(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, ItemStack itemstack) {
/* 26 */     int i = 15 + worldserver.random.nextInt(15) + worldserver.random.nextInt(15);
/*    */     
/* 28 */     return i;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRenderType b(IBlockData iblockdata) {
/* 34 */     return EnumRenderType.MODEL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */