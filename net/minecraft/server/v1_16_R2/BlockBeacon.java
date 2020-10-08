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
/*    */ public class BlockBeacon
/*    */   extends BlockTileEntity
/*    */   implements IBeaconBeam
/*    */ {
/*    */   public BlockBeacon(BlockBase.Info var0) {
/* 20 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumColor a() {
/* 25 */     return EnumColor.WHITE;
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess var0) {
/* 30 */     return new TileEntityBeacon();
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 35 */     if (var1.isClientSide) {
/* 36 */       return EnumInteractionResult.SUCCESS;
/*    */     }
/*    */     
/* 39 */     TileEntity var6 = var1.getTileEntity(var2);
/* 40 */     if (var6 instanceof TileEntityBeacon) {
/* 41 */       var3.openContainer((TileEntityBeacon)var6);
/* 42 */       var3.a(StatisticList.INTERACT_WITH_BEACON);
/*    */     } 
/*    */     
/* 45 */     return EnumInteractionResult.CONSUME;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRenderType b(IBlockData var0) {
/* 50 */     return EnumRenderType.MODEL;
/*    */   }
/*    */ 
/*    */   
/*    */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, EntityLiving var3, ItemStack var4) {
/* 55 */     if (var4.hasName()) {
/* 56 */       TileEntity var5 = var0.getTileEntity(var1);
/* 57 */       if (var5 instanceof TileEntityBeacon)
/* 58 */         ((TileEntityBeacon)var5).setCustomName(var4.getName()); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */