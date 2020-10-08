/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorStopAdmiring<E extends EntityPiglin>
/*    */   extends Behavior<E>
/*    */ {
/*    */   public BehaviorStopAdmiring() {
/* 12 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.ADMIRING_ITEM, MemoryStatus.VALUE_ABSENT));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 19 */     return (!var1.getItemInOffHand().isEmpty() && var1.getItemInOffHand().getItem() != Items.SHIELD);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 24 */     PiglinAI.a((EntityPiglin)var1, true);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorStopAdmiring.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */