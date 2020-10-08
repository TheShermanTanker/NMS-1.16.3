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
/*    */ public abstract class AbstractDragonController
/*    */   implements IDragonController
/*    */ {
/*    */   protected final EntityEnderDragon a;
/*    */   
/*    */   public AbstractDragonController(EntityEnderDragon var0) {
/* 18 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void b() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void c() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(EntityEnderCrystal var0, BlockPosition var1, DamageSource var2, @Nullable EntityHuman var3) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void d() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void e() {}
/*    */ 
/*    */   
/*    */   public float f() {
/* 48 */     return 0.6F;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 54 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public float a(DamageSource var0, float var1) {
/* 59 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float h() {
/* 64 */     float var0 = MathHelper.sqrt(Entity.c(this.a.getMot())) + 1.0F;
/* 65 */     float var1 = Math.min(var0, 40.0F);
/*    */     
/* 67 */     return 0.7F / var1 / var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AbstractDragonController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */