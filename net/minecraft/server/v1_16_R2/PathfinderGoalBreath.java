/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
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
/*    */ public class PathfinderGoalBreath
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityCreature a;
/*    */   
/*    */   public PathfinderGoalBreath(EntityCreature var0) {
/* 20 */     this.a = var0;
/* 21 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 26 */     return (this.a.getAirTicks() < 140);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 31 */     return a();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean C_() {
/* 36 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 41 */     g();
/*    */   }
/*    */   
/*    */   private void g() {
/* 45 */     Iterable<BlockPosition> var0 = BlockPosition.b(
/* 46 */         MathHelper.floor(this.a.locX() - 1.0D), 
/* 47 */         MathHelper.floor(this.a.locY()), 
/* 48 */         MathHelper.floor(this.a.locZ() - 1.0D), 
/* 49 */         MathHelper.floor(this.a.locX() + 1.0D), 
/* 50 */         MathHelper.floor(this.a.locY() + 8.0D), 
/* 51 */         MathHelper.floor(this.a.locZ() + 1.0D));
/*    */ 
/*    */     
/* 54 */     BlockPosition var1 = null;
/* 55 */     for (BlockPosition var3 : var0) {
/* 56 */       if (a(this.a.world, var3)) {
/* 57 */         var1 = var3;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 62 */     if (var1 == null) {
/* 63 */       var1 = new BlockPosition(this.a.locX(), this.a.locY() + 8.0D, this.a.locZ());
/*    */     }
/*    */     
/* 66 */     this.a.getNavigation().a(var1.getX(), (var1.getY() + 1), var1.getZ(), 1.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 71 */     g();
/*    */     
/* 73 */     this.a.a(0.02F, new Vec3D(this.a.aR, this.a.aS, this.a.aT));
/* 74 */     this.a.move(EnumMoveType.SELF, this.a.getMot());
/*    */   }
/*    */   
/*    */   private boolean a(IWorldReader var0, BlockPosition var1) {
/* 78 */     IBlockData var2 = var0.getType(var1);
/* 79 */     return ((var0.getFluid(var1).isEmpty() || var2.a(Blocks.BUBBLE_COLUMN)) && var2.a(var0, var1, PathMode.LAND));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalBreath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */