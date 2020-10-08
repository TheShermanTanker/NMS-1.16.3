/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Comparator;
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
/*    */ public class SensorNearestItems
/*    */   extends Sensor<EntityInsentient>
/*    */ {
/*    */   public Set<MemoryModuleType<?>> a() {
/* 22 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityInsentient var1) {
/* 28 */     BehaviorController<?> var2 = var1.getBehaviorController();
/*    */     
/* 30 */     List<EntityItem> var3 = var0.a(EntityItem.class, var1.getBoundingBox().grow(8.0D, 4.0D, 8.0D), var0 -> true);
/* 31 */     var3.sort(Comparator.comparingDouble(var1::h));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 37 */     Optional<EntityItem> var4 = var3.stream().filter(var1 -> var0.i(var1.getItemStack())).filter(var1 -> var1.a(var0, 9.0D)).filter(var1::hasLineOfSight).findFirst();
/* 38 */     var2.setMemory(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorNearestItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */