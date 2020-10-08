/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
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
/*    */ public class SensorPiglinBruteSpecific
/*    */   extends Sensor<EntityLiving>
/*    */ {
/*    */   public Set<MemoryModuleType<?>> a() {
/* 22 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.NEAREST_VISIBLE_NEMSIS, MemoryModuleType.NEARBY_ADULT_PIGLINS);
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
/*    */   protected void a(WorldServer var0, EntityLiving var1) {
/* 34 */     BehaviorController<?> var2 = var1.getBehaviorController();
/*    */     
/* 36 */     Optional<EntityInsentient> var3 = Optional.empty();
/* 37 */     List<EntityPiglinAbstract> var4 = Lists.newArrayList();
/*    */ 
/*    */     
/* 40 */     List<EntityLiving> var5 = (List<EntityLiving>)var2.getMemory(MemoryModuleType.VISIBLE_MOBS).orElse(ImmutableList.of());
/* 41 */     for (EntityLiving var7 : var5) {
/* 42 */       if (var7 instanceof EntitySkeletonWither || var7 instanceof EntityWither) {
/* 43 */         var3 = Optional.of((EntityInsentient)var7);
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 48 */     List<EntityLiving> var6 = (List<EntityLiving>)var2.getMemory(MemoryModuleType.MOBS).orElse(ImmutableList.of());
/* 49 */     for (EntityLiving var8 : var6) {
/* 50 */       if (var8 instanceof EntityPiglinAbstract && ((EntityPiglinAbstract)var8).eM()) {
/* 51 */         var4.add((EntityPiglinAbstract)var8);
/*    */       }
/*    */     } 
/*    */     
/* 55 */     var2.setMemory(MemoryModuleType.NEAREST_VISIBLE_NEMSIS, var3);
/* 56 */     var2.setMemory(MemoryModuleType.NEARBY_ADULT_PIGLINS, var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorPiglinBruteSpecific.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */