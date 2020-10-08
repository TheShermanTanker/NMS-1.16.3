/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
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
/*    */ public class PathfinderGoalStrollVillage
/*    */   extends PathfinderGoalRandomStroll
/*    */ {
/*    */   public PathfinderGoalStrollVillage(EntityCreature var0, double var1, boolean var3) {
/* 18 */     super(var0, var1, 10, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 23 */     WorldServer var0 = (WorldServer)this.a.world;
/* 24 */     BlockPosition var1 = this.a.getChunkCoordinates();
/*    */     
/* 26 */     if (var0.a_(var1)) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     return super.a();
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected Vec3D g() {
/* 36 */     WorldServer var0 = (WorldServer)this.a.world;
/* 37 */     BlockPosition var1 = this.a.getChunkCoordinates();
/*    */     
/* 39 */     SectionPosition var2 = SectionPosition.a(var1);
/* 40 */     SectionPosition var3 = BehaviorUtil.a(var0, var2, 2);
/*    */     
/* 42 */     if (var3 != var2) {
/* 43 */       return RandomPositionGenerator.b(this.a, 10, 7, Vec3D.c(var3.q()));
/*    */     }
/*    */     
/* 46 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalStrollVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */