/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.destroystokyo.paper.util.set.OptimizedSmallEnumSet;
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ public abstract class PathfinderGoal
/*    */ {
/*  8 */   private final EnumSet<Type> a = EnumSet.noneOf(Type.class);
/*  9 */   private final OptimizedSmallEnumSet<Type> goalTypes = new OptimizedSmallEnumSet(Type.class);
/*    */ 
/*    */   
/*    */   public PathfinderGoal() {
/* 13 */     if (this.goalTypes.size() == 0) {
/* 14 */       this.goalTypes.addUnchecked(Type.UNKNOWN_BEHAVIOR);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 19 */     return shouldActivate(); } public boolean shouldActivate() { return false; } public boolean shouldActivate2() { return a(); }
/*    */   
/* 21 */   public boolean b() { return shouldStayActive(); } public boolean shouldStayActive2() { return b(); } public boolean shouldStayActive() {
/* 22 */     return a();
/*    */   }
/*    */   
/*    */   public boolean C_() {
/* 26 */     return true;
/*    */   }
/*    */   public void c() {
/* 29 */     start();
/*    */   } public void start() {}
/*    */   public void d() {
/* 32 */     onTaskReset();
/*    */   }
/*    */   public void onTaskReset() {}
/*    */   public void e() {
/* 36 */     tick();
/*    */   } public void tick() {} public void a(EnumSet<Type> enumset) {
/* 38 */     setTypes(enumset);
/*    */   } public void setTypes(EnumSet<Type> enumset) {
/* 40 */     this.goalTypes.clear();
/* 41 */     this.goalTypes.addAllUnchecked(enumset);
/*    */     
/* 43 */     if (this.goalTypes.size() == 0) {
/* 44 */       this.goalTypes.addUnchecked(Type.UNKNOWN_BEHAVIOR);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public OptimizedSmallEnumSet<Type> getGoalTypes() {
/* 55 */     return this.goalTypes;
/*    */   }
/*    */ 
/*    */   
/*    */   public enum Type
/*    */   {
/* 61 */     MOVE, LOOK, JUMP, TARGET, UNKNOWN_BEHAVIOR;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */