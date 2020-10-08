/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorHuntHoglin<E extends EntityPiglin>
/*    */   extends Behavior<E>
/*    */ {
/*    */   public BehaviorHuntHoglin() {
/* 12 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.NEAREST_VISIBLE_HUNTABLE_HOGLIN, MemoryStatus.VALUE_PRESENT, MemoryModuleType.ANGRY_AT, MemoryStatus.VALUE_ABSENT, MemoryModuleType.HUNTED_RECENTLY, MemoryStatus.VALUE_ABSENT, MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLINS, MemoryStatus.REGISTERED));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityPiglin var1) {
/* 22 */     return (!var1.isBaby() && !PiglinAI.e(var1));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 27 */     EntityHoglin var4 = var1.getBehaviorController().<EntityHoglin>getMemory(MemoryModuleType.NEAREST_VISIBLE_HUNTABLE_HOGLIN).get();
/* 28 */     PiglinAI.c((EntityPiglinAbstract)var1, var4);
/*    */     
/* 30 */     PiglinAI.c((EntityPiglinAbstract)var1);
/* 31 */     PiglinAI.b((EntityPiglinAbstract)var1, var4);
/* 32 */     PiglinAI.f((EntityPiglin)var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorHuntHoglin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */