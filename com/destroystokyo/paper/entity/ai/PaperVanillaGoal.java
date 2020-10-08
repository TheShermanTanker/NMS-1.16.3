/*    */ package com.destroystokyo.paper.entity.ai;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.server.v1_16_R2.PathfinderGoal;
/*    */ import org.bukkit.entity.Mob;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PaperVanillaGoal<T extends Mob>
/*    */   implements VanillaGoal<T>
/*    */ {
/*    */   private final PathfinderGoal handle;
/*    */   private final GoalKey<T> key;
/*    */   private final EnumSet<GoalType> types;
/*    */   
/*    */   public PaperVanillaGoal(PathfinderGoal handle) {
/* 20 */     this.handle = handle;
/* 21 */     this.key = MobGoalHelper.getKey((Class)handle.getClass());
/* 22 */     this.types = MobGoalHelper.vanillaToPaper(handle.getGoalTypes());
/*    */   }
/*    */   
/*    */   public PathfinderGoal getHandle() {
/* 26 */     return this.handle;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldActivate() {
/* 31 */     return this.handle.shouldActivate2();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldStayActive() {
/* 36 */     return this.handle.shouldStayActive2();
/*    */   }
/*    */ 
/*    */   
/*    */   public void start() {
/* 41 */     this.handle.start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stop() {
/* 46 */     this.handle.onTaskReset();
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 51 */     this.handle.tick();
/*    */   }
/*    */ 
/*    */   
/*    */   public GoalKey<T> getKey() {
/* 56 */     return this.key;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumSet<GoalType> getTypes() {
/* 61 */     return this.types;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\ai\PaperVanillaGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */