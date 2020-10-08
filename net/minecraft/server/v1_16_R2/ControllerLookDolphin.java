/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ControllerLookDolphin
/*    */   extends ControllerLook
/*    */ {
/*    */   private final int h;
/*    */   
/*    */   public ControllerLookDolphin(EntityInsentient var0, int var1) {
/* 12 */     super(var0);
/* 13 */     this.h = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a() {
/* 18 */     if (this.d) {
/* 19 */       this.d = false;
/*    */       
/* 21 */       this.a.aC = a(this.a.aC, h() + 20.0F, this.b);
/* 22 */       this.a.pitch = a(this.a.pitch, g() + 10.0F, this.c);
/*    */     } else {
/* 24 */       if (this.a.getNavigation().m()) {
/* 25 */         this.a.pitch = a(this.a.pitch, 0.0F, 5.0F);
/*    */       }
/* 27 */       this.a.aC = a(this.a.aC, this.a.aA, this.b);
/*    */     } 
/*    */     
/* 30 */     float var0 = MathHelper.g(this.a.aC - this.a.aA);
/*    */ 
/*    */     
/* 33 */     if (var0 < -this.h) {
/* 34 */       this.a.aA -= 4.0F;
/* 35 */     } else if (var0 > this.h) {
/* 36 */       this.a.aA += 4.0F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ControllerLookDolphin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */