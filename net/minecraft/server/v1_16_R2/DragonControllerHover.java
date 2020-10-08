/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class DragonControllerHover
/*    */   extends AbstractDragonController
/*    */ {
/*    */   private Vec3D b;
/*    */   
/*    */   public DragonControllerHover(EntityEnderDragon var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 17 */     if (this.b == null) {
/* 18 */       this.b = this.a.getPositionVector();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 29 */     this.b = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public float f() {
/* 34 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 40 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerHover> getControllerPhase() {
/* 45 */     return DragonControllerPhase.HOVER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerHover.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */