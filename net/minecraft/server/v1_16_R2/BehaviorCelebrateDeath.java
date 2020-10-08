/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.BiPredicate;
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
/*    */ public class BehaviorCelebrateDeath
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   private final int b;
/*    */   private final BiPredicate<EntityLiving, EntityLiving> c;
/*    */   
/*    */   public BehaviorCelebrateDeath(int var0, BiPredicate<EntityLiving, EntityLiving> var1) {
/* 24 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.ANGRY_AT, MemoryStatus.REGISTERED, MemoryModuleType.CELEBRATE_LOCATION, MemoryStatus.VALUE_ABSENT, MemoryModuleType.DANCING, MemoryStatus.REGISTERED));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     this.b = var0;
/* 31 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityLiving var1) {
/* 36 */     return a(var1).dk();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 41 */     EntityLiving var4 = a(var1);
/* 42 */     if (this.c.test(var1, var4)) {
/* 43 */       var1.getBehaviorController().a(MemoryModuleType.DANCING, Boolean.valueOf(true), this.b);
/*    */     }
/* 45 */     var1.getBehaviorController().a(MemoryModuleType.CELEBRATE_LOCATION, var4.getChunkCoordinates(), this.b);
/*    */     
/* 47 */     if (var4.getEntityType() != EntityTypes.PLAYER || var0.getGameRules().getBoolean(GameRules.FORGIVE_DEAD_PLAYERS)) {
/* 48 */       var1.getBehaviorController().removeMemory(MemoryModuleType.ATTACK_TARGET);
/* 49 */       var1.getBehaviorController().removeMemory(MemoryModuleType.ANGRY_AT);
/*    */     } 
/*    */   }
/*    */   
/*    */   private EntityLiving a(EntityLiving var0) {
/* 54 */     return var0.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.ATTACK_TARGET).get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorCelebrateDeath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */