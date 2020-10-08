/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SensorNearestPlayers
/*    */   extends Sensor<EntityLiving>
/*    */ {
/*    */   public Set<MemoryModuleType<?>> a() {
/* 20 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.NEAREST_PLAYERS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1) {
/* 33 */     List<EntityHuman> var2 = (List<EntityHuman>)var0.getPlayers().stream().filter(IEntitySelector.g).filter(var1 -> var0.a(var1, 16.0D)).sorted(Comparator.comparingDouble(var1::h)).collect(Collectors.toList());
/*    */     
/* 35 */     BehaviorController<?> var3 = var1.getBehaviorController();
/* 36 */     var3.setMemory(MemoryModuleType.NEAREST_PLAYERS, var2);
/*    */ 
/*    */     
/* 39 */     List<EntityHuman> var4 = (List<EntityHuman>)var2.stream().filter(var1 -> a(var0, var1)).collect(Collectors.toList());
/* 40 */     var3.setMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER, var4.isEmpty() ? null : var4.get(0));
/* 41 */     Optional<EntityHuman> var5 = var4.stream().filter(IEntitySelector.f).findFirst();
/* 42 */     var3.setMemory(MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, var5);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorNearestPlayers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */