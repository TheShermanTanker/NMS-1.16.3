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
/*    */ public class NavigationSpider
/*    */   extends Navigation
/*    */ {
/*    */   private BlockPosition p;
/*    */   
/*    */   public NavigationSpider(EntityInsentient var0, World var1) {
/* 21 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public PathEntity a(BlockPosition var0, int var1) {
/* 26 */     this.p = var0;
/* 27 */     return super.a(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public PathEntity a(Entity var0, int var1) {
/* 32 */     this.p = var0.getChunkCoordinates();
/* 33 */     return super.a(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Entity var0, double var1) {
/* 38 */     PathEntity var3 = a(var0, 0);
/* 39 */     if (var3 != null) {
/* 40 */       return a(var3, var1);
/*    */     }
/* 42 */     this.p = var0.getChunkCoordinates();
/* 43 */     this.d = var1;
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void c() {
/* 50 */     if (m()) {
/* 51 */       if (this.p != null)
/*    */       {
/* 53 */         if (this.p.a(this.a.getPositionVector(), this.a.getWidth()) || (this.a.locY() > this.p.getY() && (new BlockPosition(this.p.getX(), this.a.locY(), this.p.getZ())).a(this.a.getPositionVector(), this.a.getWidth()))) {
/* 54 */           this.p = null;
/*    */         } else {
/* 56 */           this.a.getControllerMove().a(this.p.getX(), this.p.getY(), this.p.getZ(), this.d);
/*    */         } 
/*    */       }
/*    */       return;
/*    */     } 
/* 61 */     super.c();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NavigationSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */