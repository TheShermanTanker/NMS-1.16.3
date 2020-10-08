/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class BehaviorLook
/*    */   extends Behavior<EntityInsentient>
/*    */ {
/*    */   public BehaviorLook(int var0, int var1) {
/* 11 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.VALUE_PRESENT), var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b(WorldServer var0, EntityInsentient var1, long var2) {
/* 16 */     return var1.getBehaviorController().<BehaviorPosition>getMemory(MemoryModuleType.LOOK_TARGET)
/* 17 */       .filter(var1 -> var1.a(var0))
/* 18 */       .isPresent();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(WorldServer var0, EntityInsentient var1, long var2) {
/* 23 */     var1.getBehaviorController().removeMemory(MemoryModuleType.LOOK_TARGET);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void d(WorldServer var0, EntityInsentient var1, long var2) {
/* 28 */     var1.getBehaviorController().<BehaviorPosition>getMemory(MemoryModuleType.LOOK_TARGET).ifPresent(var1 -> var0.getControllerLook().a(var1.a()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorLook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */