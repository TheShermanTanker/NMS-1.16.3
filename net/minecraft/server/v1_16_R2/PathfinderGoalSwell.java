/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalSwell
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityCreeper a;
/*    */   private EntityLiving b;
/*    */   
/*    */   public PathfinderGoalSwell(EntityCreeper var0) {
/* 13 */     this.a = var0;
/* 14 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 19 */     EntityLiving var0 = this.a.getGoalTarget();
/* 20 */     return (this.a.eK() > 0 || (var0 != null && this.a.h(var0) < 9.0D));
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 25 */     this.a.getNavigation().o();
/* 26 */     this.b = this.a.getGoalTarget();
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 31 */     this.b = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 36 */     if (this.b == null) {
/* 37 */       this.a.a(-1);
/*    */       
/*    */       return;
/*    */     } 
/* 41 */     if (this.a.h(this.b) > 49.0D) {
/* 42 */       this.a.a(-1);
/*    */       
/*    */       return;
/*    */     } 
/* 46 */     if (!this.a.getEntitySenses().a(this.b)) {
/* 47 */       this.a.a(-1);
/*    */       
/*    */       return;
/*    */     } 
/* 51 */     this.a.a(1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalSwell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */