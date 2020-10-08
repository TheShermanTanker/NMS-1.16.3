/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ public class BehaviorWalkAwayOutOfRange
/*    */   extends Behavior<EntityInsentient>
/*    */ {
/*    */   private final float b;
/*    */   
/*    */   public BehaviorWalkAwayOutOfRange(float var0) {
/* 22 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.VISIBLE_MOBS, MemoryStatus.REGISTERED));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityInsentient var1, long var2) {
/* 34 */     EntityLiving var4 = var1.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.ATTACK_TARGET).get();
/* 35 */     if (BehaviorUtil.c(var1, var4) && BehaviorUtil.a(var1, var4, 1)) {
/* 36 */       a(var1);
/*    */     } else {
/* 38 */       a(var1, var4);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void a(EntityLiving var0, EntityLiving var1) {
/* 43 */     BehaviorController<?> var2 = var0.getBehaviorController();
/*    */     
/* 45 */     var2.setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorPositionEntity(var1, true));
/*    */     
/* 47 */     MemoryTarget var3 = new MemoryTarget(new BehaviorPositionEntity(var1, false), this.b, 0);
/* 48 */     var2.setMemory(MemoryModuleType.WALK_TARGET, var3);
/*    */   }
/*    */   
/*    */   private void a(EntityLiving var0) {
/* 52 */     var0.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorWalkAwayOutOfRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */