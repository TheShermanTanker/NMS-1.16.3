/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ControllerLook
/*     */ {
/*     */   protected final EntityInsentient a;
/*     */   protected float b;
/*     */   protected float c;
/*     */   protected boolean d;
/*     */   protected double e;
/*     */   protected double f;
/*     */   protected double g;
/*     */   
/*     */   public ControllerLook(EntityInsentient var0) {
/*  19 */     this.a = var0;
/*     */   }
/*     */   
/*     */   public void a(Vec3D var0) {
/*  23 */     a(var0.x, var0.y, var0.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Entity var0, float var1, float var2) {
/*  31 */     a(var0.locX(), b(var0), var0.locZ(), var1, var2);
/*     */   }
/*     */   
/*     */   public void a(double var0, double var2, double var4) {
/*  35 */     a(var0, var2, var4, this.a.ep(), this.a.O());
/*     */   }
/*     */   
/*     */   public void a(double var0, double var2, double var4, float var6, float var7) {
/*  39 */     this.e = var0;
/*  40 */     this.f = var2;
/*  41 */     this.g = var4;
/*  42 */     this.b = var6;
/*  43 */     this.c = var7;
/*  44 */     this.d = true;
/*     */   }
/*     */   
/*     */   public void a() {
/*  48 */     if (b()) {
/*  49 */       this.a.pitch = 0.0F;
/*     */     }
/*     */     
/*  52 */     if (this.d) {
/*  53 */       this.d = false;
/*     */       
/*  55 */       this.a.aC = a(this.a.aC, h(), this.b);
/*  56 */       this.a.pitch = a(this.a.pitch, g(), this.c);
/*     */     } else {
/*  58 */       this.a.aC = a(this.a.aC, this.a.aA, 10.0F);
/*     */     } 
/*     */     
/*  61 */     if (!this.a.getNavigation().m())
/*     */     {
/*  63 */       this.a.aC = MathHelper.b(this.a.aC, this.a.aA, this.a.eo());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b() {
/*  69 */     return true;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  73 */     return this.d;
/*     */   }
/*     */   
/*     */   public double d() {
/*  77 */     return this.e;
/*     */   }
/*     */   
/*     */   public double e() {
/*  81 */     return this.f;
/*     */   }
/*     */   
/*     */   public double f() {
/*  85 */     return this.g;
/*     */   }
/*     */   
/*     */   protected float g() {
/*  89 */     double var0 = this.e - this.a.locX();
/*  90 */     double var2 = this.f - this.a.getHeadY();
/*  91 */     double var4 = this.g - this.a.locZ();
/*  92 */     double var6 = MathHelper.sqrt(var0 * var0 + var4 * var4);
/*  93 */     return (float)-(MathHelper.d(var2, var6) * 57.2957763671875D);
/*     */   }
/*     */   
/*     */   protected float h() {
/*  97 */     double var0 = this.e - this.a.locX();
/*  98 */     double var2 = this.g - this.a.locZ();
/*  99 */     return (float)(MathHelper.d(var2, var0) * 57.2957763671875D) - 90.0F;
/*     */   }
/*     */   
/*     */   protected float a(float var0, float var1, float var2) {
/* 103 */     float var3 = MathHelper.c(var0, var1);
/* 104 */     float var4 = MathHelper.a(var3, -var2, var2);
/* 105 */     return var0 + var4;
/*     */   }
/*     */   
/*     */   private static double b(Entity var0) {
/* 109 */     if (var0 instanceof EntityLiving) {
/* 110 */       return var0.getHeadY();
/*     */     }
/* 112 */     return ((var0.getBoundingBox()).minY + (var0.getBoundingBox()).maxY) / 2.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ControllerLook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */