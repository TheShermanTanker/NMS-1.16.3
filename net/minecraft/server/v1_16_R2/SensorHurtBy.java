/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
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
/*    */ public class SensorHurtBy
/*    */   extends Sensor<EntityLiving>
/*    */ {
/*    */   public Set<MemoryModuleType<?>> a() {
/* 21 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1) {
/* 26 */     BehaviorController<?> var2 = var1.getBehaviorController();
/* 27 */     DamageSource var3 = var1.dl();
/* 28 */     if (var3 != null) {
/* 29 */       var2.setMemory(MemoryModuleType.HURT_BY, var1.dl());
/* 30 */       Entity var4 = var3.getEntity();
/* 31 */       if (var4 instanceof EntityLiving) {
/* 32 */         var2.setMemory(MemoryModuleType.HURT_BY_ENTITY, (EntityLiving)var4);
/*    */       }
/*    */     } else {
/* 35 */       var2.removeMemory(MemoryModuleType.HURT_BY);
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     var2.<EntityLiving>getMemory(MemoryModuleType.HURT_BY_ENTITY).ifPresent(var2 -> {
/*    */           if (!var2.isAlive() || var2.world != var0)
/*    */             var1.removeMemory(MemoryModuleType.HURT_BY_ENTITY); 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorHurtBy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */