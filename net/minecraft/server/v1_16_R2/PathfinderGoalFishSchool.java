/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalFishSchool
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityFishSchool a;
/*    */   private int b;
/*    */   private int c;
/*    */   
/*    */   public PathfinderGoalFishSchool(EntityFishSchool var0) {
/* 16 */     this.a = var0;
/* 17 */     this.c = a(var0);
/*    */   }
/*    */   
/*    */   protected int a(EntityFishSchool var0) {
/* 21 */     return 200 + var0.getRandom().nextInt(200) % 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 26 */     if (this.a.eR()) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     if (this.a.eO()) {
/* 31 */       return true;
/*    */     }
/*    */     
/* 34 */     if (this.c > 0) {
/* 35 */       this.c--;
/* 36 */       return false;
/*    */     } 
/*    */     
/* 39 */     this.c = a(this.a);
/*    */     
/* 41 */     Predicate<EntityFishSchool> var0 = var0 -> (var0.eQ() || !var0.eO());
/* 42 */     List<EntityFishSchool> var1 = (List)this.a.world.a(this.a.getClass(), this.a.getBoundingBox().grow(8.0D, 8.0D, 8.0D), var0);
/*    */     
/* 44 */     EntityFishSchool var2 = var1.stream().filter(EntityFishSchool::eQ).findAny().orElse(this.a);
/*    */     
/* 46 */     var2.a(var1.stream().filter(var0 -> !var0.eO()));
/*    */     
/* 48 */     return this.a.eO();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 53 */     return (this.a.eO() && this.a.eS());
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 58 */     this.b = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 63 */     this.a.eP();
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 68 */     if (--this.b > 0) {
/*    */       return;
/*    */     }
/* 71 */     this.b = 10;
/*    */     
/* 73 */     this.a.eT();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalFishSchool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */