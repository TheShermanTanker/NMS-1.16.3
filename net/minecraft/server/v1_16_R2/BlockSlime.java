/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSlime
/*    */   extends BlockHalfTransparent
/*    */ {
/*    */   public BlockSlime(BlockBase.Info var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fallOn(World var0, BlockPosition var1, Entity var2, float var3) {
/* 17 */     if (var2.bv()) {
/* 18 */       super.fallOn(var0, var1, var2, var3);
/*    */     } else {
/*    */       
/* 21 */       var2.b(var3, 0.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(IBlockAccess var0, Entity var1) {
/* 27 */     if (var1.bv()) {
/* 28 */       super.a(var0, var1);
/*    */     } else {
/* 30 */       a(var1);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void a(Entity var0) {
/* 35 */     Vec3D var1 = var0.getMot();
/* 36 */     if (var1.y < 0.0D) {
/*    */       
/* 38 */       double var2 = (var0 instanceof EntityLiving) ? 1.0D : 0.8D;
/* 39 */       var0.setMot(var1.x, -var1.y * var2, var1.z);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stepOn(World var0, BlockPosition var1, Entity var2) {
/* 49 */     double var3 = Math.abs((var2.getMot()).y);
/* 50 */     if (var3 < 0.1D && !var2.bu()) {
/* 51 */       double var5 = 0.4D + var3 * 0.2D;
/* 52 */       var2.setMot(var2.getMot().d(var5, 1.0D, var5));
/*    */     } 
/* 54 */     super.stepOn(var0, var1, var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */