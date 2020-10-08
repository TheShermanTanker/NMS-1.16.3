/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BlockBannerAbstract
/*    */   extends BlockTileEntity
/*    */ {
/*    */   private final EnumColor a;
/*    */   
/*    */   protected BlockBannerAbstract(EnumColor var0, BlockBase.Info var1) {
/* 19 */     super(var1);
/* 20 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean ai_() {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess var0) {
/* 30 */     return new TileEntityBanner(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, @Nullable EntityLiving var3, ItemStack var4) {
/* 35 */     if (var4.hasName()) {
/* 36 */       TileEntity var5 = var0.getTileEntity(var1);
/* 37 */       if (var5 instanceof TileEntityBanner) {
/* 38 */         ((TileEntityBanner)var5).a(var4.getName());
/*    */       }
/*    */     } 
/*    */   }
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
/*    */   public EnumColor getColor() {
/* 54 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBannerAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */