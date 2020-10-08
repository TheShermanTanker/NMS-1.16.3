/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ public class PathfinderGoalWrapped extends PathfinderGoal {
/*    */   private final PathfinderGoal a;
/*    */   
/*  8 */   public PathfinderGoal getGoal() { return this.a; } private final int b; private boolean c; public int getPriority() {
/*  9 */     return this.b;
/*    */   }
/*    */   
/*    */   public PathfinderGoalWrapped(int i, PathfinderGoal pathfindergoal) {
/* 13 */     this.b = i;
/* 14 */     this.a = pathfindergoal;
/*    */   }
/*    */   
/*    */   public boolean a(PathfinderGoalWrapped pathfindergoalwrapped) {
/* 18 */     return (C_() && pathfindergoalwrapped.h() < h());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 23 */     return this.a.a();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 28 */     return this.a.b();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean C_() {
/* 33 */     return this.a.C_();
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 38 */     if (!this.c) {
/* 39 */       this.c = true;
/* 40 */       this.a.c();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 46 */     if (this.c) {
/* 47 */       this.c = false;
/* 48 */       this.a.d();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 54 */     this.a.e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(EnumSet<PathfinderGoal.Type> enumset) {
/* 59 */     this.a.a(enumset);
/*    */   }
/*    */ 
/*    */   
/*    */   public OptimizedSmallEnumSet<PathfinderGoal.Type> getGoalTypes() {
/* 64 */     return this.a.getGoalTypes();
/*    */   }
/*    */   
/*    */   public boolean isRunning() {
/* 68 */     return g();
/*    */   } public boolean g() {
/* 70 */     return this.c;
/*    */   }
/*    */   
/*    */   public int h() {
/* 74 */     return this.b;
/*    */   }
/*    */   
/*    */   public PathfinderGoal j() {
/* 78 */     return this.a;
/*    */   }
/*    */   
/*    */   public boolean equals(@Nullable Object object) {
/* 82 */     return (this == object) ? true : ((object != null && getClass() == object.getClass()) ? this.a.equals(((PathfinderGoalWrapped)object).a) : false);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 86 */     return this.a.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalWrapped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */