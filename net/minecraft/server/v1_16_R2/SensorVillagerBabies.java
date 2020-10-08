/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SensorVillagerBabies
/*    */   extends Sensor<EntityLiving>
/*    */ {
/*    */   public Set<MemoryModuleType<?>> a() {
/* 21 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.VISIBLE_VILLAGER_BABIES);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1) {
/* 26 */     var1.getBehaviorController().setMemory(MemoryModuleType.VISIBLE_VILLAGER_BABIES, a(var1));
/*    */   }
/*    */   
/*    */   private List<EntityLiving> a(EntityLiving var0) {
/* 30 */     return (List<EntityLiving>)c(var0).stream()
/* 31 */       .filter(this::b)
/* 32 */       .collect(Collectors.toList());
/*    */   }
/*    */   
/*    */   private boolean b(EntityLiving var0) {
/* 36 */     return (var0.getEntityType() == EntityTypes.VILLAGER && var0.isBaby());
/*    */   }
/*    */   
/*    */   private List<EntityLiving> c(EntityLiving var0) {
/* 40 */     return var0.getBehaviorController().<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_MOBS)
/* 41 */       .orElse(Lists.newArrayList());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorVillagerBabies.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */