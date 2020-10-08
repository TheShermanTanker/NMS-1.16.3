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
/*    */ public class BehaviorStartRiding<E extends EntityLiving>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final float b;
/*    */   
/*    */   public BehaviorStartRiding(float var0) {
/* 19 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.RIDE_TARGET, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 24 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 29 */     return !var1.isPassenger();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 34 */     if (a(var1)) {
/* 35 */       var1.startRiding(b(var1));
/*    */     } else {
/* 37 */       BehaviorUtil.a((EntityLiving)var1, b(var1), this.b, 1);
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean a(E var0) {
/* 42 */     return b(var0).a((Entity)var0, 1.0D);
/*    */   }
/*    */   
/*    */   private Entity b(E var0) {
/* 46 */     return var0.getBehaviorController().<Entity>getMemory(MemoryModuleType.RIDE_TARGET).get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorStartRiding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */