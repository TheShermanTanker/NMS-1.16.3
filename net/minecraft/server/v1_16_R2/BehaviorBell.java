/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorBell
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   public BehaviorBell() {
/* 19 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.MEETING_POINT, MemoryStatus.VALUE_PRESENT, MemoryModuleType.VISIBLE_MOBS, MemoryStatus.VALUE_PRESENT, MemoryModuleType.INTERACTION_TARGET, MemoryStatus.VALUE_ABSENT));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityLiving var1) {
/* 30 */     BehaviorController<?> var2 = var1.getBehaviorController();
/* 31 */     Optional<GlobalPos> var3 = var2.getMemory(MemoryModuleType.MEETING_POINT);
/* 32 */     return (var0.getRandom().nextInt(100) == 0 && var3
/* 33 */       .isPresent() && var0
/* 34 */       .getDimensionKey() == ((GlobalPos)var3.get()).getDimensionManager() && ((GlobalPos)var3
/* 35 */       .get()).getBlockPosition().a(var1.getPositionVector(), 4.0D) && ((List)var2
/* 36 */       .<List>getMemory((MemoryModuleType)MemoryModuleType.VISIBLE_MOBS).get()).stream().anyMatch(var0 -> EntityTypes.VILLAGER.equals(var0.getEntityType())));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 41 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 42 */     var4.<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_MOBS).ifPresent(var2 -> var2.stream().filter(()).filter(()).findFirst().ifPresent(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */