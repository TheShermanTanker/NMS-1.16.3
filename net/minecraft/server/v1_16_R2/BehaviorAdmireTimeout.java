/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorAdmireTimeout<E extends EntityPiglin>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final int b;
/*    */   private final int c;
/*    */   
/*    */   public BehaviorAdmireTimeout(int var0, int var1) {
/* 17 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.ADMIRING_ITEM, MemoryStatus.VALUE_PRESENT, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryStatus.VALUE_PRESENT, MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM, MemoryStatus.REGISTERED, MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM, MemoryStatus.REGISTERED));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 23 */     this.b = var0;
/* 24 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 29 */     return var1.getItemInOffHand().isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 34 */     BehaviorController<EntityPiglin> var4 = var1.getBehaviorController();
/* 35 */     Optional<Integer> var5 = var4.getMemory(MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM);
/* 36 */     if (!var5.isPresent()) {
/* 37 */       var4.setMemory(MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM, Integer.valueOf(0));
/*    */     } else {
/* 39 */       int var6 = ((Integer)var5.get()).intValue();
/* 40 */       if (var6 > this.b) {
/* 41 */         var4.removeMemory(MemoryModuleType.ADMIRING_ITEM);
/* 42 */         var4.removeMemory(MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM);
/* 43 */         var4.a(MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM, Boolean.valueOf(true), this.c);
/*    */       } else {
/* 45 */         var4.setMemory(MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM, Integer.valueOf(var6 + 1));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorAdmireTimeout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */