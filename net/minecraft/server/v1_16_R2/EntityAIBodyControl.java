/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIBodyControl
/*    */ {
/*    */   private final EntityInsentient a;
/*    */   private int b;
/*    */   private float c;
/*    */   
/*    */   public EntityAIBodyControl(EntityInsentient var0) {
/* 17 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public void a() {
/* 21 */     if (f()) {
/* 22 */       this.a.aA = this.a.yaw;
/* 23 */       c();
/*    */       
/* 25 */       this.c = this.a.aC;
/* 26 */       this.b = 0;
/*    */       
/*    */       return;
/*    */     } 
/* 30 */     if (e()) {
/* 31 */       if (Math.abs(this.a.aC - this.c) > 15.0F) {
/*    */ 
/*    */         
/* 34 */         this.b = 0;
/* 35 */         this.c = this.a.aC;
/* 36 */         b();
/*    */       } else {
/* 38 */         this.b++;
/* 39 */         if (this.b > 10)
/*    */         {
/*    */           
/* 42 */           d();
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   private void b() {
/* 49 */     this.a.aA = MathHelper.b(this.a.aA, this.a.aC, this.a.eo());
/*    */   }
/*    */   
/*    */   private void c() {
/* 53 */     this.a.aC = MathHelper.b(this.a.aC, this.a.aA, this.a.eo());
/*    */   }
/*    */   
/*    */   private void d() {
/* 57 */     int var0 = this.b - 10;
/*    */ 
/*    */     
/* 60 */     float var1 = MathHelper.a(var0 / 10.0F, 0.0F, 1.0F);
/*    */     
/* 62 */     float var2 = this.a.eo() * (1.0F - var1);
/*    */     
/* 64 */     this.a.aA = MathHelper.b(this.a.aA, this.a.aC, var2);
/*    */   }
/*    */   
/*    */   private boolean e() {
/* 68 */     return (this.a.getPassengers().isEmpty() || !(this.a.getPassengers().get(0) instanceof EntityInsentient));
/*    */   }
/*    */   
/*    */   private boolean f() {
/* 72 */     double var0 = this.a.locX() - this.a.lastX;
/* 73 */     double var2 = this.a.locZ() - this.a.lastZ;
/*    */     
/* 75 */     return (var0 * var0 + var2 * var2 > 2.500000277905201E-7D);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityAIBodyControl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */