/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.Random;
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
/*    */ public class PathfinderGoalNearestVillage
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityCreature a;
/*    */   private final int b;
/*    */   @Nullable
/*    */   private BlockPosition c;
/*    */   
/*    */   public PathfinderGoalNearestVillage(EntityCreature var0, int var1) {
/* 25 */     this.a = var0;
/* 26 */     this.b = var1;
/* 27 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 32 */     if (this.a.isVehicle()) {
/* 33 */       return false;
/*    */     }
/*    */     
/* 36 */     if (this.a.world.isDay()) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     if (this.a.getRandom().nextInt(this.b) != 0) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     WorldServer var0 = (WorldServer)this.a.world;
/*    */     
/* 46 */     BlockPosition var1 = this.a.getChunkCoordinates();
/* 47 */     if (!var0.a(var1, 6)) {
/* 48 */       return false;
/*    */     }
/*    */     
/* 51 */     Vec3D var2 = RandomPositionGenerator.a(this.a, 15, 7, var1 -> -var0.b(SectionPosition.a(var1)));
/* 52 */     this.c = (var2 == null) ? null : new BlockPosition(var2);
/* 53 */     return (this.c != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 58 */     return (this.c != null && !this.a.getNavigation().m() && this.a.getNavigation().h().equals(this.c));
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 63 */     if (this.c == null) {
/*    */       return;
/*    */     }
/* 66 */     NavigationAbstract var0 = this.a.getNavigation();
/* 67 */     if (var0.m() && 
/* 68 */       !this.c.a(this.a.getPositionVector(), 10.0D)) {
/* 69 */       Vec3D var1 = Vec3D.c(this.c);
/*    */ 
/*    */       
/* 72 */       Vec3D var2 = this.a.getPositionVector();
/* 73 */       Vec3D var3 = var2.d(var1);
/*    */       
/* 75 */       var1 = var3.a(0.4D).e(var1);
/*    */       
/* 77 */       Vec3D var4 = var1.d(var2).d().a(10.0D).e(var2);
/* 78 */       BlockPosition var5 = new BlockPosition(var4);
/* 79 */       var5 = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, var5);
/*    */       
/* 81 */       if (!var0.a(var5.getX(), var5.getY(), var5.getZ(), 1.0D))
/*    */       {
/* 83 */         g();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void g() {
/* 90 */     Random var0 = this.a.getRandom();
/* 91 */     BlockPosition var1 = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, this.a.getChunkCoordinates().b(-8 + var0.nextInt(16), 0, -8 + var0.nextInt(16)));
/* 92 */     this.a.getNavigation().a(var1.getX(), var1.getY(), var1.getZ(), 1.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalNearestVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */