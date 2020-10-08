/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class ControllerMoveFlying
/*    */   extends ControllerMove {
/*    */   private final int i;
/*    */   private final boolean j;
/*    */   
/*    */   public ControllerMoveFlying(EntityInsentient entityinsentient, int i, boolean flag) {
/*  9 */     super(entityinsentient);
/* 10 */     this.i = i;
/* 11 */     this.j = flag;
/*    */   }
/*    */   
/*    */   public void a() {
/* 15 */     tick(); } public void tick() {
/* 16 */     if (this.h == ControllerMove.Operation.MOVE_TO) {
/* 17 */       float f1; this.h = ControllerMove.Operation.WAIT;
/* 18 */       this.a.setNoGravity(true);
/* 19 */       double d0 = this.b - this.a.locX();
/* 20 */       double d1 = this.c - this.a.locY();
/* 21 */       double d2 = this.d - this.a.locZ();
/* 22 */       double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*    */       
/* 24 */       if (d3 < 2.500000277905201E-7D) {
/* 25 */         this.a.u(0.0F);
/* 26 */         this.a.t(0.0F);
/*    */         
/*    */         return;
/*    */       } 
/* 30 */       float f = (float)(MathHelper.d(d2, d0) * 57.2957763671875D) - 90.0F;
/*    */       
/* 32 */       this.a.yaw = a(this.a.yaw, f, 90.0F);
/*    */ 
/*    */       
/* 35 */       if (this.a.isOnGround()) {
/* 36 */         f1 = (float)(this.e * this.a.b(GenericAttributes.MOVEMENT_SPEED));
/*    */       } else {
/* 38 */         f1 = (float)(this.e * this.a.b(GenericAttributes.FLYING_SPEED));
/*    */       } 
/*    */       
/* 41 */       this.a.q(f1);
/* 42 */       double d4 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/* 43 */       float f2 = (float)-(MathHelper.d(d1, d4) * 57.2957763671875D);
/*    */       
/* 45 */       this.a.pitch = a(this.a.pitch, f2, this.i);
/* 46 */       this.a.u((d1 > 0.0D) ? f1 : -f1);
/*    */     } else {
/* 48 */       if (!this.j) {
/* 49 */         this.a.setNoGravity(false);
/*    */       }
/*    */       
/* 52 */       this.a.u(0.0F);
/* 53 */       this.a.t(0.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ControllerMoveFlying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */