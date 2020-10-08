/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorInteractPlayer
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   private final float b;
/*    */   
/*    */   public BehaviorInteractPlayer(float var0) {
/* 16 */     super(
/* 17 */         (Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED), 2147483647);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 23 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityVillager var1) {
/* 28 */     EntityHuman var2 = var1.getTrader();
/*    */     
/* 30 */     return (var1.isAlive() && var2 != null && 
/*    */       
/* 32 */       !var1.isInWater() && !var1.velocityChanged && var1
/*    */       
/* 34 */       .h(var2) <= 16.0D && var2.activeContainer != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean b(WorldServer var0, EntityVillager var1, long var2) {
/* 41 */     return a(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/* 46 */     a(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(WorldServer var0, EntityVillager var1, long var2) {
/* 51 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 52 */     var4.removeMemory(MemoryModuleType.WALK_TARGET);
/* 53 */     var4.removeMemory(MemoryModuleType.LOOK_TARGET);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void d(WorldServer var0, EntityVillager var1, long var2) {
/* 58 */     a(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(long var0) {
/* 63 */     return false;
/*    */   }
/*    */   
/*    */   private void a(EntityVillager var0) {
/* 67 */     BehaviorController<?> var1 = var0.getBehaviorController();
/* 68 */     var1.setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(new BehaviorPositionEntity(var0.getTrader(), false), this.b, 2));
/* 69 */     var1.setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorPositionEntity(var0.getTrader(), true));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorInteractPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */