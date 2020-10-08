/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class PathfinderGoalZombieAttack
/*    */   extends PathfinderGoalMeleeAttack
/*    */ {
/*    */   private final EntityZombie b;
/*    */   private int c;
/*    */   
/*    */   public PathfinderGoalZombieAttack(EntityZombie var0, double var1, boolean var3) {
/* 10 */     super(var0, var1, var3);
/* 11 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 16 */     super.c();
/* 17 */     this.c = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 22 */     super.d();
/* 23 */     this.b.setAggressive(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 28 */     super.e();
/*    */     
/* 30 */     this.c++;
/* 31 */     if (this.c >= 5 && j() < k() / 2) {
/* 32 */       this.b.setAggressive(true);
/*    */     } else {
/* 34 */       this.b.setAggressive(false);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalZombieAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */