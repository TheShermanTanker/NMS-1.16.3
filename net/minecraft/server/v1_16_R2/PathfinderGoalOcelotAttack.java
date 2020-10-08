/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalOcelotAttack
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final IBlockAccess a;
/*    */   private final EntityInsentient b;
/*    */   private EntityLiving c;
/*    */   private int d;
/*    */   
/*    */   public PathfinderGoalOcelotAttack(EntityInsentient var0) {
/* 17 */     this.b = var0;
/* 18 */     this.a = var0.world;
/* 19 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 24 */     EntityLiving var0 = this.b.getGoalTarget();
/* 25 */     if (var0 == null) {
/* 26 */       return false;
/*    */     }
/* 28 */     this.c = var0;
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 34 */     if (!this.c.isAlive()) {
/* 35 */       return false;
/*    */     }
/* 37 */     if (this.b.h(this.c) > 225.0D) {
/* 38 */       return false;
/*    */     }
/* 40 */     return (!this.b.getNavigation().m() || a());
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 45 */     this.c = null;
/* 46 */     this.b.getNavigation().o();
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 51 */     this.b.getControllerLook().a(this.c, 30.0F, 30.0F);
/*    */     
/* 53 */     double var0 = (this.b.getWidth() * 2.0F * this.b.getWidth() * 2.0F);
/* 54 */     double var2 = this.b.h(this.c.locX(), this.c.locY(), this.c.locZ());
/*    */     
/* 56 */     double var4 = 0.8D;
/* 57 */     if (var2 > var0 && var2 < 16.0D) {
/* 58 */       var4 = 1.33D;
/* 59 */     } else if (var2 < 225.0D) {
/* 60 */       var4 = 0.6D;
/*    */     } 
/*    */     
/* 63 */     this.b.getNavigation().a(this.c, var4);
/*    */     
/* 65 */     this.d = Math.max(this.d - 1, 0);
/*    */     
/* 67 */     if (var2 > var0) {
/*    */       return;
/*    */     }
/* 70 */     if (this.d > 0) {
/*    */       return;
/*    */     }
/* 73 */     this.d = 20;
/* 74 */     this.b.attackEntity(this.c);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalOcelotAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */