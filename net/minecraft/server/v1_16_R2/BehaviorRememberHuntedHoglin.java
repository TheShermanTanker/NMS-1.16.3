/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorRememberHuntedHoglin<E extends EntityPiglin>
/*    */   extends Behavior<E>
/*    */ {
/*    */   public BehaviorRememberHuntedHoglin() {
/* 13 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.HUNTED_RECENTLY, MemoryStatus.REGISTERED));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 21 */     if (a(var1)) {
/* 22 */       PiglinAI.c((EntityPiglinAbstract)var1);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean a(E var0) {
/* 27 */     EntityLiving var1 = var0.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.ATTACK_TARGET).get();
/* 28 */     return (var1.getEntityType() == EntityTypes.HOGLIN && var1.dk());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorRememberHuntedHoglin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */