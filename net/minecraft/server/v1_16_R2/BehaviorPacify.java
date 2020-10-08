/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class BehaviorPacify
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   private final int b;
/*    */   
/*    */   public BehaviorPacify(MemoryModuleType<?> var0, int var1) {
/* 13 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.PACIFIED, MemoryStatus.VALUE_ABSENT, var0, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 18 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 23 */     var1.getBehaviorController().a(MemoryModuleType.PACIFIED, Boolean.valueOf(true), this.b);
/* 24 */     var1.getBehaviorController().removeMemory(MemoryModuleType.ATTACK_TARGET);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorPacify.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */