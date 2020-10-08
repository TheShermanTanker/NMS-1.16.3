/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ public class PathfinderGoalFloat
/*    */   extends PathfinderGoal {
/*    */   private final EntityInsentient a;
/*    */   
/*    */   public PathfinderGoalFloat(EntityInsentient entityinsentient) {
/* 10 */     this.a = entityinsentient;
/* 11 */     if ((entityinsentient.getWorld()).paperConfig.nerfedMobsShouldJump) entityinsentient.goalFloat = this; 
/* 12 */     a(EnumSet.of(PathfinderGoal.Type.JUMP));
/* 13 */     entityinsentient.getNavigation().d(true);
/*    */   }
/*    */   public final boolean validConditions() {
/* 16 */     return a();
/*    */   }
/*    */   public boolean a() {
/* 19 */     return ((this.a.isInWater() && this.a.b(TagsFluid.WATER) > this.a.cw()) || this.a.aP());
/*    */   }
/*    */   public void update() {
/* 22 */     e();
/*    */   }
/*    */   public void e() {
/* 25 */     if (this.a.getRandom().nextFloat() < 0.8F)
/* 26 */       this.a.getControllerJump().jump(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */