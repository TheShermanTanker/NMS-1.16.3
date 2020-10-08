/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalUniversalAngerReset<T extends EntityInsentient & IEntityAngerable>
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final T a;
/*    */   private final boolean b;
/*    */   private int c;
/*    */   
/*    */   public PathfinderGoalUniversalAngerReset(T var0, boolean var1) {
/* 32 */     this.a = var0;
/* 33 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 38 */     return (((EntityInsentient)this.a).world.getGameRules().getBoolean(GameRules.UNIVERSAL_ANGER) && g());
/*    */   }
/*    */   
/*    */   private boolean g() {
/* 42 */     return (this.a.getLastDamager() != null && this.a
/* 43 */       .getLastDamager().getEntityType() == EntityTypes.PLAYER && this.a
/* 44 */       .cZ() > this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 49 */     this.c = this.a.cZ();
/* 50 */     ((IEntityAngerable)this.a).I_();
/* 51 */     if (this.b) {
/* 52 */       h().stream()
/* 53 */         .filter(var0 -> (var0 != this.a))
/* 54 */         .map(var0 -> (IEntityAngerable)var0)
/* 55 */         .forEach(IEntityAngerable::I_);
/*    */     }
/* 57 */     super.c();
/*    */   }
/*    */   
/*    */   private List<EntityInsentient> h() {
/* 61 */     double var0 = this.a.b(GenericAttributes.FOLLOW_RANGE);
/* 62 */     AxisAlignedBB var2 = AxisAlignedBB.a(this.a.getPositionVector()).grow(var0, 10.0D, var0);
/* 63 */     return (List)((EntityInsentient)this.a).world.b(this.a.getClass(), var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalUniversalAngerReset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */