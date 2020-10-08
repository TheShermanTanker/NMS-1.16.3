/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BlockColumn
/*    */   implements IBlockAccess
/*    */ {
/*    */   private final IBlockData[] a;
/*    */   
/*    */   public BlockColumn(IBlockData[] var0) {
/* 15 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public TileEntity getTileEntity(BlockPosition var0) {
/* 21 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getType(BlockPosition var0) {
/* 26 */     int var1 = var0.getY();
/* 27 */     if (var1 < 0 || var1 >= this.a.length) {
/* 28 */       return Blocks.AIR.getBlockData();
/*    */     }
/* 30 */     return this.a[var1];
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid getFluid(BlockPosition var0) {
/* 35 */     return getType(var0).getFluid();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */