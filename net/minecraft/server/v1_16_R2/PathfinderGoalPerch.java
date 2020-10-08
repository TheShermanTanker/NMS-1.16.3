/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalPerch
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityPerchable a;
/*    */   private EntityPlayer b;
/*    */   private boolean c;
/*    */   
/*    */   public PathfinderGoalPerch(EntityPerchable var0) {
/* 13 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 18 */     EntityPlayer var0 = (EntityPlayer)this.a.getOwner();
/* 19 */     boolean var1 = (var0 != null && !var0.isSpectator() && !var0.abilities.isFlying && !var0.isInWater());
/* 20 */     return (!this.a.isWillSit() && var1 && this.a.eY());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean C_() {
/* 25 */     return !this.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 30 */     this.b = (EntityPlayer)this.a.getOwner();
/* 31 */     this.c = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 36 */     if (this.c || this.a.isSitting() || this.a.isLeashed()) {
/*    */       return;
/*    */     }
/*    */     
/* 40 */     if (this.a.getBoundingBox().c(this.b.getBoundingBox()))
/* 41 */       this.c = this.a.d(this.b); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalPerch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */