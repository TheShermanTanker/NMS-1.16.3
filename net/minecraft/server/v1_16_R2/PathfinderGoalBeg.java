/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalBeg
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityWolf a;
/*    */   private EntityHuman b;
/*    */   private final World c;
/*    */   private final float d;
/*    */   private int e;
/*    */   private final PathfinderTargetCondition f;
/*    */   
/*    */   public PathfinderGoalBeg(EntityWolf var0, float var1) {
/* 23 */     this.a = var0;
/* 24 */     this.c = var0.world;
/* 25 */     this.d = var1;
/* 26 */     this.f = (new PathfinderTargetCondition()).a(var1).a().b().d();
/* 27 */     a(EnumSet.of(PathfinderGoal.Type.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 32 */     this.b = this.c.a(this.f, this.a);
/* 33 */     if (this.b == null) {
/* 34 */       return false;
/*    */     }
/* 36 */     return a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 41 */     if (!this.b.isAlive()) {
/* 42 */       return false;
/*    */     }
/* 44 */     if (this.a.h(this.b) > (this.d * this.d)) {
/* 45 */       return false;
/*    */     }
/* 47 */     return (this.e > 0 && a(this.b));
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 52 */     this.a.x(true);
/* 53 */     this.e = 40 + this.a.getRandom().nextInt(40);
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 58 */     this.a.x(false);
/* 59 */     this.b = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 64 */     this.a.getControllerLook().a(this.b.locX(), this.b.getHeadY(), this.b.locZ(), 10.0F, this.a.O());
/* 65 */     this.e--;
/*    */   }
/*    */   
/*    */   private boolean a(EntityHuman var0) {
/* 69 */     for (EnumHand var4 : EnumHand.values()) {
/* 70 */       ItemStack var5 = var0.b(var4);
/* 71 */       if (this.a.isTamed() && var5.getItem() == Items.BONE) {
/* 72 */         return true;
/*    */       }
/* 74 */       if (this.a.k(var5)) {
/* 75 */         return true;
/*    */       }
/*    */     } 
/* 78 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalBeg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */