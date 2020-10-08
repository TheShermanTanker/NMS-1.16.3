/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalLookAtTradingPlayer
/*    */   extends PathfinderGoalLookAtPlayer
/*    */ {
/*    */   private final EntityVillagerAbstract g;
/*    */   
/*    */   public PathfinderGoalLookAtTradingPlayer(EntityVillagerAbstract var0) {
/* 10 */     super(var0, (Class)EntityHuman.class, 8.0F);
/* 11 */     this.g = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 16 */     if (this.g.eN()) {
/* 17 */       this.b = this.g.getTrader();
/* 18 */       return true;
/*    */     } 
/* 20 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalLookAtTradingPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */