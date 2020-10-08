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
/*    */ public class BehaviorCooldown
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   public BehaviorCooldown() {
/* 16 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/* 21 */     boolean var4 = (BehaviorPanic.b(var1) || BehaviorPanic.a(var1) || a(var1));
/* 22 */     if (!var4) {
/* 23 */       var1.getBehaviorController().removeMemory(MemoryModuleType.HURT_BY);
/* 24 */       var1.getBehaviorController().removeMemory(MemoryModuleType.HURT_BY_ENTITY);
/* 25 */       var1.getBehaviorController().a(var0.getDayTime(), var0.getTime());
/*    */     } 
/*    */   }
/*    */   
/*    */   private static boolean a(EntityVillager var0) {
/* 30 */     return var0.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.HURT_BY_ENTITY)
/* 31 */       .filter(var1 -> (var1.h(var0) <= 36.0D))
/* 32 */       .isPresent();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorCooldown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */