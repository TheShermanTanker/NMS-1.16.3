/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorStartAdmiringItem<E extends EntityPiglin>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final int b;
/*    */   
/*    */   public BehaviorStartAdmiringItem(int var0) {
/* 14 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryStatus.VALUE_PRESENT, MemoryModuleType.ADMIRING_ITEM, MemoryStatus.VALUE_ABSENT, MemoryModuleType.ADMIRING_DISABLED, MemoryStatus.VALUE_ABSENT, MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM, MemoryStatus.VALUE_ABSENT));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 25 */     EntityItem var2 = var1.getBehaviorController().<EntityItem>getMemory(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM).get();
/* 26 */     return PiglinAI.a(var2.getItemStack().getItem());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 31 */     var1.getBehaviorController().a(MemoryModuleType.ADMIRING_ITEM, Boolean.valueOf(true), this.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorStartAdmiringItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */