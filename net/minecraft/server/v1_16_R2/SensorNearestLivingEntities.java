/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Comparator;
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
/*    */ 
/*    */ public class SensorNearestLivingEntities
/*    */   extends Sensor<EntityLiving>
/*    */ {
/*    */   protected void a(WorldServer var0, EntityLiving var1) {
/* 22 */     AxisAlignedBB var2 = var1.getBoundingBox().grow(16.0D, 16.0D, 16.0D);
/* 23 */     List<EntityLiving> var3 = var0.a(EntityLiving.class, var2, var1 -> (var1 != var0 && var1.isAlive()));
/* 24 */     var3.sort(Comparator.comparingDouble(var1::h));
/*    */     
/* 26 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 27 */     var4.setMemory(MemoryModuleType.MOBS, var3);
/* 28 */     var4.setMemory(MemoryModuleType.VISIBLE_MOBS, (List<EntityLiving>)var3.stream()
/* 29 */         .filter(var1 -> a(var0, var1))
/* 30 */         .collect(Collectors.toList()));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MemoryModuleType<?>> a() {
/* 35 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorNearestLivingEntities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */