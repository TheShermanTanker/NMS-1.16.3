/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalRestrictSun
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityCreature a;
/*    */   
/*    */   public PathfinderGoalRestrictSun(EntityCreature var0) {
/* 12 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 17 */     return (this.a.world.isDay() && this.a.getEquipment(EnumItemSlot.HEAD).isEmpty() && PathfinderGoalUtil.a(this.a));
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 22 */     ((Navigation)this.a.getNavigation()).c(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 27 */     if (PathfinderGoalUtil.a(this.a))
/* 28 */       ((Navigation)this.a.getNavigation()).c(false); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalRestrictSun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */