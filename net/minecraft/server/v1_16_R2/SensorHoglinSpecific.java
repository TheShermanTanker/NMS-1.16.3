/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SensorHoglinSpecific
/*    */   extends Sensor<EntityHoglin>
/*    */ {
/*    */   public Set<MemoryModuleType<?>> a() {
/* 25 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.NEAREST_REPELLENT, MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLIN, MemoryModuleType.NEAREST_VISIBLE_ADULT_HOGLINS, MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT, MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT, (Object[])new MemoryModuleType[0]);
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
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityHoglin var1) {
/* 40 */     BehaviorController<?> var2 = var1.getBehaviorController();
/*    */     
/* 42 */     var2.setMemory(MemoryModuleType.NEAREST_REPELLENT, b(var0, var1));
/*    */     
/* 44 */     Optional<EntityPiglin> var3 = Optional.empty();
/* 45 */     int var4 = 0;
/*    */     
/* 47 */     List<EntityHoglin> var5 = Lists.newArrayList();
/*    */ 
/*    */     
/* 50 */     List<EntityLiving> var6 = var2.<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_MOBS).orElse(Lists.newArrayList());
/* 51 */     for (EntityLiving var8 : var6) {
/* 52 */       if (var8 instanceof EntityPiglin && !var8.isBaby()) {
/* 53 */         var4++;
/* 54 */         if (!var3.isPresent()) {
/* 55 */           var3 = Optional.of((EntityPiglin)var8);
/*    */         }
/*    */       } 
/*    */       
/* 59 */       if (var8 instanceof EntityHoglin && !var8.isBaby()) {
/* 60 */         var5.add((EntityHoglin)var8);
/*    */       }
/*    */     } 
/*    */     
/* 64 */     var2.setMemory(MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLIN, (Optional)var3);
/* 65 */     var2.setMemory(MemoryModuleType.NEAREST_VISIBLE_ADULT_HOGLINS, var5);
/* 66 */     var2.setMemory(MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT, Integer.valueOf(var4));
/* 67 */     var2.setMemory(MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT, Integer.valueOf(var5.size()));
/*    */   }
/*    */   
/*    */   private Optional<BlockPosition> b(WorldServer var0, EntityHoglin var1) {
/* 71 */     return BlockPosition.a(var1
/* 72 */         .getChunkCoordinates(), 8, 4, var1 -> var0.getType(var1).a(TagsBlock.HOGLIN_REPELLENTS));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorHoglinSpecific.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */