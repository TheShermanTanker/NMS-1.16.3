/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractDragonControllerLanded
/*    */   extends AbstractDragonController
/*    */ {
/*    */   public AbstractDragonControllerLanded(EntityEnderDragon var0) {
/*  9 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 14 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float a(DamageSource var0, float var1) {
/* 19 */     if (var0.j() instanceof EntityArrow) {
/* 20 */       var0.j().setOnFire(1);
/* 21 */       return 0.0F;
/*    */     } 
/* 23 */     return super.a(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AbstractDragonControllerLanded.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */