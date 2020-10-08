/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalRandomStrollLand
/*    */   extends PathfinderGoalRandomStroll
/*    */ {
/*    */   protected final float h;
/*    */   
/*    */   public PathfinderGoalRandomStrollLand(EntityCreature var0, double var1) {
/* 15 */     this(var0, var1, 0.001F);
/*    */   }
/*    */   
/*    */   public PathfinderGoalRandomStrollLand(EntityCreature var0, double var1, float var3) {
/* 19 */     super(var0, var1);
/* 20 */     this.h = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected Vec3D g() {
/* 26 */     if (this.a.aG()) {
/*    */       
/* 28 */       Vec3D var0 = RandomPositionGenerator.b(this.a, 15, 7);
/* 29 */       return (var0 == null) ? super.g() : var0;
/*    */     } 
/* 31 */     if (this.a.getRandom().nextFloat() >= this.h) {
/* 32 */       return RandomPositionGenerator.b(this.a, 10, 7);
/*    */     }
/* 34 */     return super.g();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalRandomStrollLand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */