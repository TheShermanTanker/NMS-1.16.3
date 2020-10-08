/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorLeaveJob
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   private final float b;
/*    */   
/*    */   public BehaviorLeaveJob(float var0) {
/* 25 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.POTENTIAL_JOB_SITE, MemoryStatus.VALUE_PRESENT, MemoryModuleType.JOB_SITE, MemoryStatus.VALUE_ABSENT, MemoryModuleType.MOBS, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityVillager var1) {
/* 35 */     if (var1.isBaby()) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     return (var1.getVillagerData().getProfession() == VillagerProfession.NONE);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/* 44 */     BlockPosition var4 = ((GlobalPos)var1.getBehaviorController().<GlobalPos>getMemory(MemoryModuleType.POTENTIAL_JOB_SITE).get()).getBlockPosition();
/*    */     
/* 46 */     Optional<VillagePlaceType> var5 = var0.y().c(var4);
/* 47 */     if (!var5.isPresent()) {
/*    */       return;
/*    */     }
/*    */     
/* 51 */     BehaviorUtil.a(var1, var2 -> a(var0.get(), var2, var1))
/* 52 */       .findFirst()
/* 53 */       .ifPresent(var3 -> a(var0, var1, var3, var2, var3.getBehaviorController().<GlobalPos>getMemory(MemoryModuleType.JOB_SITE).isPresent()));
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean a(VillagePlaceType var0, EntityVillager var1, BlockPosition var2) {
/* 58 */     boolean var3 = var1.getBehaviorController().<GlobalPos>getMemory(MemoryModuleType.POTENTIAL_JOB_SITE).isPresent();
/* 59 */     if (var3) {
/* 60 */       return false;
/*    */     }
/*    */     
/* 63 */     Optional<GlobalPos> var4 = var1.getBehaviorController().getMemory(MemoryModuleType.JOB_SITE);
/* 64 */     VillagerProfession var5 = var1.getVillagerData().getProfession();
/*    */ 
/*    */     
/* 67 */     if (var1.getVillagerData().getProfession() != VillagerProfession.NONE && var5.b().c().test(var0)) {
/* 68 */       if (!var4.isPresent()) {
/* 69 */         return a(var1, var2, var0);
/*    */       }
/* 71 */       return ((GlobalPos)var4.get()).getBlockPosition().equals(var2);
/*    */     } 
/* 73 */     return false;
/*    */   }
/*    */   
/*    */   private void a(WorldServer var0, EntityVillager var1, EntityVillager var2, BlockPosition var3, boolean var4) {
/* 77 */     a(var1);
/*    */     
/* 79 */     if (!var4) {
/* 80 */       BehaviorUtil.a(var2, var3, this.b, 1);
/* 81 */       var2.getBehaviorController().setMemory(MemoryModuleType.POTENTIAL_JOB_SITE, GlobalPos.create(var0.getDimensionKey(), var3));
/* 82 */       PacketDebug.c(var0, var3);
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean a(EntityVillager var0, BlockPosition var1, VillagePlaceType var2) {
/* 87 */     PathEntity var3 = var0.getNavigation().a(var1, var2.d());
/* 88 */     return (var3 != null && var3.j());
/*    */   }
/*    */   
/*    */   private void a(EntityVillager var0) {
/* 92 */     var0.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
/* 93 */     var0.getBehaviorController().removeMemory(MemoryModuleType.LOOK_TARGET);
/* 94 */     var0.getBehaviorController().removeMemory(MemoryModuleType.POTENTIAL_JOB_SITE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorLeaveJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */