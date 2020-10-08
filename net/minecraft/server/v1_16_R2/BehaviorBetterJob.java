/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
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
/*    */ public class BehaviorBetterJob
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   final VillagerProfession b;
/*    */   
/*    */   public BehaviorBetterJob(VillagerProfession var0) {
/* 22 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.JOB_SITE, MemoryStatus.VALUE_PRESENT, MemoryModuleType.MOBS, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */     
/* 26 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/* 31 */     GlobalPos var4 = var1.getBehaviorController().<GlobalPos>getMemory(MemoryModuleType.JOB_SITE).get();
/*    */     
/* 33 */     var0.y().c(var4.getBlockPosition()).ifPresent(var2 -> BehaviorUtil.a(var0, ()).reduce(var0, BehaviorBetterJob::a));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static EntityVillager a(EntityVillager var0, EntityVillager var1) {
/*    */     EntityVillager var2, var3;
/* 42 */     if (var0.getExperience() > var1.getExperience()) {
/* 43 */       var2 = var0;
/* 44 */       var3 = var1;
/*    */     } else {
/* 46 */       var2 = var1;
/* 47 */       var3 = var0;
/*    */     } 
/*    */     
/* 50 */     var3.getBehaviorController().removeMemory(MemoryModuleType.JOB_SITE);
/* 51 */     return var2;
/*    */   }
/*    */   
/*    */   private boolean a(GlobalPos var0, VillagePlaceType var1, EntityVillager var2) {
/* 55 */     return (a(var2) && var0
/* 56 */       .equals(var2.getBehaviorController().<GlobalPos>getMemory(MemoryModuleType.JOB_SITE).get()) && 
/* 57 */       a(var1, var2.getVillagerData().getProfession()));
/*    */   }
/*    */   
/*    */   private boolean a(VillagePlaceType var0, VillagerProfession var1) {
/* 61 */     return var1.b().c().test(var0);
/*    */   }
/*    */   
/*    */   private boolean a(EntityVillager var0) {
/* 65 */     return var0.getBehaviorController().<GlobalPos>getMemory(MemoryModuleType.JOB_SITE).isPresent();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorBetterJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */