/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.event.entity.EntityTargetEvent;
/*    */ 
/*    */ public class PathfinderGoalNearestAttackableTarget<T extends EntityLiving> extends PathfinderGoalTarget {
/*    */   protected final Class<T> a;
/*    */   protected final int b;
/*    */   protected EntityLiving c;
/*    */   protected PathfinderTargetCondition d;
/*    */   
/*    */   public PathfinderGoalNearestAttackableTarget(EntityInsentient entityinsentient, Class<T> oclass, boolean flag) {
/* 15 */     this(entityinsentient, oclass, flag, false);
/*    */   }
/*    */   
/*    */   public PathfinderGoalNearestAttackableTarget(EntityInsentient entityinsentient, Class<T> oclass, boolean flag, boolean flag1) {
/* 19 */     this(entityinsentient, oclass, 10, flag, flag1, (Predicate<EntityLiving>)null);
/*    */   }
/*    */   
/*    */   public PathfinderGoalNearestAttackableTarget(EntityInsentient entityinsentient, Class<T> oclass, int i, boolean flag, boolean flag1, @Nullable Predicate<EntityLiving> predicate) {
/* 23 */     super(entityinsentient, flag, flag1);
/* 24 */     this.a = oclass;
/* 25 */     this.b = i;
/* 26 */     a(EnumSet.of(PathfinderGoal.Type.TARGET));
/* 27 */     this.d = (new PathfinderTargetCondition()).a(k()).a(predicate);
/* 28 */     if (entityinsentient.world.paperConfig.entitiesTargetWithFollowRange) this.d.useFollowRange();
/*    */   
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 33 */     if (this.b > 0 && this.e.getRandom().nextInt(this.b) != 0) {
/* 34 */       return false;
/*    */     }
/* 36 */     g();
/* 37 */     return (this.c != null);
/*    */   }
/*    */ 
/*    */   
/*    */   protected AxisAlignedBB a(double d0) {
/* 42 */     return this.e.getBoundingBox().grow(d0, 4.0D, d0);
/*    */   }
/*    */   
/*    */   protected void g() {
/* 46 */     if (this.a != EntityHuman.class && this.a != EntityPlayer.class) {
/* 47 */       this.c = this.e.world.b(this.a, this.d, this.e, this.e.locX(), this.e.getHeadY(), this.e.locZ(), a(k()));
/*    */     } else {
/* 49 */       this.c = this.e.world.a(this.d, this.e, this.e.locX(), this.e.getHeadY(), this.e.locZ());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void c() {
/* 56 */     this.e.setGoalTarget(this.c, (this.c instanceof EntityPlayer) ? EntityTargetEvent.TargetReason.CLOSEST_PLAYER : EntityTargetEvent.TargetReason.CLOSEST_ENTITY, true);
/* 57 */     super.c();
/*    */   }
/*    */   
/*    */   public void a(@Nullable EntityLiving entityliving) {
/* 61 */     this.c = entityliving;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalNearestAttackableTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */