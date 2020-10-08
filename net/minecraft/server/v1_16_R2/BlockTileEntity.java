/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BlockTileEntity
/*    */   extends Block
/*    */   implements ITileEntity
/*    */ {
/*    */   protected BlockTileEntity(BlockBase.Info var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRenderType b(IBlockData var0) {
/* 18 */     return EnumRenderType.INVISIBLE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, World var1, BlockPosition var2, int var3, int var4) {
/* 23 */     super.a(var0, var1, var2, var3, var4);
/*    */     
/* 25 */     TileEntity var5 = var1.getTileEntity(var2);
/* 26 */     if (var5 == null) {
/* 27 */       return false;
/*    */     }
/* 29 */     return var5.setProperty(var3, var4);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ITileInventory getInventory(IBlockData var0, World var1, BlockPosition var2) {
/* 35 */     TileEntity var3 = var1.getTileEntity(var2);
/* 36 */     return (var3 instanceof ITileInventory) ? (ITileInventory)var3 : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */