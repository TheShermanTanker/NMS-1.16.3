/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalTradeWithPlayer
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityVillagerAbstract a;
/*    */   
/*    */   public PathfinderGoalTradeWithPlayer(EntityVillagerAbstract var0) {
/* 12 */     this.a = var0;
/* 13 */     a(EnumSet.of(PathfinderGoal.Type.JUMP, PathfinderGoal.Type.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 18 */     if (!this.a.isAlive()) {
/* 19 */       return false;
/*    */     }
/* 21 */     if (this.a.isInWater()) {
/* 22 */       return false;
/*    */     }
/* 24 */     if (!this.a.isOnGround()) {
/* 25 */       return false;
/*    */     }
/* 27 */     if (this.a.velocityChanged) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     EntityHuman var0 = this.a.getTrader();
/* 32 */     if (var0 == null)
/*    */     {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     if (this.a.h(var0) > 16.0D)
/*    */     {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     return (var0.activeContainer != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 47 */     this.a.getNavigation().o();
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 52 */     this.a.setTradingPlayer((EntityHuman)null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalTradeWithPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */