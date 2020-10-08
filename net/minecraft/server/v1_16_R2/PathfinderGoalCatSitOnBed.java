/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalCatSitOnBed
/*    */   extends PathfinderGoalGotoTarget
/*    */ {
/*    */   private final EntityCat g;
/*    */   
/*    */   public PathfinderGoalCatSitOnBed(EntityCat var0, double var1, int var3) {
/* 15 */     super(var0, var1, var3, 6);
/* 16 */     this.g = var0;
/* 17 */     this.f = -2;
/* 18 */     a(EnumSet.of(PathfinderGoal.Type.JUMP, PathfinderGoal.Type.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 23 */     return (this.g.isTamed() && !this.g.isWillSit() && !this.g.eW() && super.a());
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 28 */     super.c();
/* 29 */     this.g.setSitting(false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int a(EntityCreature var0) {
/* 34 */     return 40;
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 39 */     super.d();
/* 40 */     this.g.x(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 45 */     super.e();
/*    */     
/* 47 */     this.g.setSitting(false);
/* 48 */     if (!l()) {
/* 49 */       this.g.x(false);
/* 50 */     } else if (!this.g.eW()) {
/* 51 */       this.g.x(true);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(IWorldReader var0, BlockPosition var1) {
/* 57 */     return (var0.isEmpty(var1.up()) && var0.getType(var1).getBlock().a(TagsBlock.BEDS));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalCatSitOnBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */