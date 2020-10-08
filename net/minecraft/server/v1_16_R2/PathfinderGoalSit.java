/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ public class PathfinderGoalSit
/*    */   extends PathfinderGoal {
/*    */   private final EntityTameableAnimal entity;
/*    */   
/*    */   public PathfinderGoalSit(EntityTameableAnimal entitytameableanimal) {
/* 10 */     this.entity = entitytameableanimal;
/* 11 */     a(EnumSet.of(PathfinderGoal.Type.JUMP, PathfinderGoal.Type.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 16 */     return this.entity.isWillSit();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 21 */     if (!this.entity.isTamed())
/* 22 */       return (this.entity.isWillSit() && this.entity.getGoalTarget() == null); 
/* 23 */     if (this.entity.aG())
/* 24 */       return false; 
/* 25 */     if (!this.entity.isOnGround()) {
/* 26 */       return false;
/*    */     }
/* 28 */     EntityLiving entityliving = this.entity.getOwner();
/*    */     
/* 30 */     return (entityliving == null) ? true : ((this.entity.h(entityliving) < 144.0D && entityliving.getLastDamager() != null) ? false : this.entity.isWillSit());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void c() {
/* 36 */     this.entity.getNavigation().o();
/* 37 */     this.entity.setSitting(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 42 */     this.entity.setSitting(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalSit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */