/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorStopAdmiringItem<E extends EntityPiglin>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final int b;
/*    */   
/*    */   public BehaviorStopAdmiringItem(int var0) {
/* 16 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.ADMIRING_ITEM, MemoryStatus.VALUE_PRESENT, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryStatus.REGISTERED));
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 25 */     if (!var1.getItemInOffHand().isEmpty())
/*    */     {
/* 27 */       return false;
/*    */     }
/* 29 */     Optional<EntityItem> var2 = var1.getBehaviorController().getMemory(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM);
/* 30 */     if (!var2.isPresent()) {
/* 31 */       return true;
/*    */     }
/* 33 */     return !((EntityItem)var2.get()).a((Entity)var1, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 38 */     var1.getBehaviorController().removeMemory(MemoryModuleType.ADMIRING_ITEM);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorStopAdmiringItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */