/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityFlying
/*    */   extends EntityInsentient
/*    */ {
/*    */   protected EntityFlying(EntityTypes<? extends EntityFlying> var0, World var1) {
/* 10 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(float var0, float var1) {
/* 15 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(double var0, boolean var2, IBlockData var3, BlockPosition var4) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void g(Vec3D var0) {
/* 26 */     if (isInWater()) {
/* 27 */       a(0.02F, var0);
/* 28 */       move(EnumMoveType.SELF, getMot());
/*    */       
/* 30 */       setMot(getMot().a(0.800000011920929D));
/* 31 */     } else if (aP()) {
/* 32 */       a(0.02F, var0);
/* 33 */       move(EnumMoveType.SELF, getMot());
/* 34 */       setMot(getMot().a(0.5D));
/*    */     } else {
/* 36 */       float var1 = 0.91F;
/* 37 */       if (this.onGround) {
/* 38 */         var1 = this.world.getType(new BlockPosition(locX(), locY() - 1.0D, locZ())).getBlock().getFrictionFactor() * 0.91F;
/*    */       }
/*    */       
/* 41 */       float var2 = 0.16277137F / var1 * var1 * var1;
/*    */       
/* 43 */       var1 = 0.91F;
/* 44 */       if (this.onGround) {
/* 45 */         var1 = this.world.getType(new BlockPosition(locX(), locY() - 1.0D, locZ())).getBlock().getFrictionFactor() * 0.91F;
/*    */       }
/*    */       
/* 48 */       a(this.onGround ? (0.1F * var2) : 0.02F, var0);
/* 49 */       move(EnumMoveType.SELF, getMot());
/*    */       
/* 51 */       setMot(getMot().a(var1));
/*    */     } 
/* 53 */     a(this, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isClimbing() {
/* 58 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityFlying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */