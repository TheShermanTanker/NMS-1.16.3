/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import org.bukkit.event.entity.EntityTargetEvent;
/*    */ 
/*    */ public class PathfinderGoalOwnerHurtTarget extends PathfinderGoalTarget {
/*    */   private final EntityTameableAnimal a;
/*    */   private EntityLiving b;
/*    */   private int c;
/*    */   
/*    */   public PathfinderGoalOwnerHurtTarget(EntityTameableAnimal entitytameableanimal) {
/* 12 */     super(entitytameableanimal, false);
/* 13 */     this.a = entitytameableanimal;
/* 14 */     a(EnumSet.of(PathfinderGoal.Type.TARGET));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 19 */     if (this.a.isTamed() && !this.a.isWillSit()) {
/* 20 */       EntityLiving entityliving = this.a.getOwner();
/*    */       
/* 22 */       if (entityliving == null) {
/* 23 */         return false;
/*    */       }
/* 25 */       this.b = entityliving.da();
/* 26 */       int i = entityliving.db();
/*    */       
/* 28 */       return (i != this.c && a(this.b, PathfinderTargetCondition.a) && this.a.a(this.b, entityliving));
/*    */     } 
/*    */     
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void c() {
/* 37 */     this.e.setGoalTarget(this.b, EntityTargetEvent.TargetReason.OWNER_ATTACKED_TARGET, true);
/* 38 */     EntityLiving entityliving = this.a.getOwner();
/*    */     
/* 40 */     if (entityliving != null) {
/* 41 */       this.c = entityliving.db();
/*    */     }
/*    */     
/* 44 */     super.c();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalOwnerHurtTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */