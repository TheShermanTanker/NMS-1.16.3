/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
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
/*    */ public class SensorAdult
/*    */   extends Sensor<EntityAgeable>
/*    */ {
/*    */   public Set<MemoryModuleType<?>> a() {
/* 20 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.NEAREST_VISIBLE_ADULY, MemoryModuleType.VISIBLE_MOBS);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityAgeable var1) {
/* 27 */     var1.getBehaviorController().<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_MOBS).ifPresent(var1 -> a(var0, var1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void a(EntityAgeable var0, List<EntityLiving> var1) {
/* 37 */     Optional<EntityAgeable> var2 = var1.stream().filter(var1 -> (var1.getEntityType() == var0.getEntityType())).map(var0 -> (EntityAgeable)var0).filter(var0 -> !var0.isBaby()).findFirst();
/* 38 */     var0.getBehaviorController().setMemory(MemoryModuleType.NEAREST_VISIBLE_ADULY, var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorAdult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */