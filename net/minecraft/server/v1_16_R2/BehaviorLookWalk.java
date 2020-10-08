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
/*    */ public class BehaviorLookWalk
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   private final float b;
/*    */   private final int c;
/*    */   
/*    */   public BehaviorLookWalk(float var0, int var1) {
/* 19 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.LOOK_TARGET, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 24 */     this.b = var0;
/* 25 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 30 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 31 */     BehaviorPosition var5 = var4.<BehaviorPosition>getMemory(MemoryModuleType.LOOK_TARGET).get();
/* 32 */     var4.setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var5, this.b, this.c));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorLookWalk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */