/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalOfferFlower
/*    */   extends PathfinderGoal
/*    */ {
/* 10 */   private static final PathfinderTargetCondition a = (new PathfinderTargetCondition()).a(6.0D).b().a();
/*    */   
/*    */   private final EntityIronGolem b;
/*    */   
/*    */   private EntityVillager c;
/*    */   
/*    */   private int d;
/*    */   
/*    */   public PathfinderGoalOfferFlower(EntityIronGolem var0) {
/* 19 */     this.b = var0;
/* 20 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 25 */     if (!this.b.world.isDay()) {
/* 26 */       return false;
/*    */     }
/* 28 */     if (this.b.getRandom().nextInt(8000) != 0) {
/* 29 */       return false;
/*    */     }
/* 31 */     this.c = this.b.world.<EntityVillager>a(EntityVillager.class, a, this.b, this.b.locX(), this.b.locY(), this.b.locZ(), this.b.getBoundingBox().grow(6.0D, 2.0D, 6.0D));
/* 32 */     return (this.c != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 37 */     return (this.d > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 42 */     this.d = 400;
/* 43 */     this.b.t(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 48 */     this.b.t(false);
/* 49 */     this.c = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 54 */     this.b.getControllerLook().a(this.c, 30.0F, 30.0F);
/* 55 */     this.d--;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalOfferFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */