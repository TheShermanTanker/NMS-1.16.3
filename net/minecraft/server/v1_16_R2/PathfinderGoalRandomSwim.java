/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalRandomSwim
/*    */   extends PathfinderGoalRandomStroll
/*    */ {
/*    */   public PathfinderGoalRandomSwim(EntityCreature var0, double var1, int var3) {
/* 13 */     super(var0, var1, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected Vec3D g() {
/* 19 */     Vec3D var0 = RandomPositionGenerator.a(this.a, 10, 7);
/* 20 */     int var1 = 0;
/* 21 */     while (var0 != null && !this.a.world.getType(new BlockPosition(var0)).a(this.a.world, new BlockPosition(var0), PathMode.WATER) && var1++ < 10) {
/* 22 */       var0 = RandomPositionGenerator.a(this.a, 10, 7);
/*    */     }
/* 24 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalRandomSwim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */