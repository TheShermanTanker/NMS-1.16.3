/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ public class PathfinderGoalRandomLookaround
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityInsentient a;
/*    */   private double b;
/*    */   private double c;
/*    */   private int d;
/*    */   
/*    */   public PathfinderGoalRandomLookaround(EntityInsentient var0) {
/* 14 */     this.a = var0;
/* 15 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 20 */     return (this.a.getRandom().nextFloat() < 0.02F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 25 */     return (this.d >= 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 30 */     double var0 = 6.283185307179586D * this.a.getRandom().nextDouble();
/* 31 */     this.b = Math.cos(var0);
/* 32 */     this.c = Math.sin(var0);
/* 33 */     this.d = 20 + this.a.getRandom().nextInt(20);
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 38 */     this.d--;
/* 39 */     this.a.getControllerLook().a(this.a.locX() + this.b, this.a.getHeadY(), this.a.locZ() + this.c);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalRandomLookaround.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */