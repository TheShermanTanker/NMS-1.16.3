/*    */ package com.destroystokyo.paper.entity.ai;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.PathfinderGoal;
/*    */ import org.bukkit.entity.Mob;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PaperCustomGoal<T extends Mob>
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final Goal<T> handle;
/*    */   
/*    */   public PaperCustomGoal(Goal<T> handle) {
/* 15 */     this.handle = handle;
/*    */     
/* 17 */     setTypes(MobGoalHelper.paperToVanilla(handle.getTypes()));
/* 18 */     if (getGoalTypes().size() == 0) {
/* 19 */       getGoalTypes().addUnchecked((Enum)PathfinderGoal.Type.UNKNOWN_BEHAVIOR);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldActivate() {
/* 25 */     return this.handle.shouldActivate();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldStayActive() {
/* 30 */     return this.handle.shouldStayActive();
/*    */   }
/*    */ 
/*    */   
/*    */   public void start() {
/* 35 */     this.handle.start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTaskReset() {
/* 40 */     this.handle.stop();
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 45 */     this.handle.tick();
/*    */   }
/*    */   
/*    */   public Goal<T> getHandle() {
/* 49 */     return this.handle;
/*    */   }
/*    */   
/*    */   public GoalKey<T> getKey() {
/* 53 */     return this.handle.getKey();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\ai\PaperCustomGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */