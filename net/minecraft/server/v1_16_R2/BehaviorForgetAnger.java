/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorForgetAnger<E extends EntityInsentient>
/*    */   extends Behavior<E>
/*    */ {
/*    */   public BehaviorForgetAnger() {
/* 13 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.ANGRY_AT, MemoryStatus.VALUE_PRESENT));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 20 */     BehaviorUtil.a((EntityLiving)var1, MemoryModuleType.ANGRY_AT).ifPresent(var2 -> {
/*    */           if (var2.dk() && (var2.getEntityType() != EntityTypes.PLAYER || var0.getGameRules().getBoolean(GameRules.FORGIVE_DEAD_PLAYERS)))
/*    */             var1.getBehaviorController().removeMemory(MemoryModuleType.ANGRY_AT); 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorForgetAnger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */