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
/*    */ public class BehaviorWork
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   private long b;
/*    */   
/*    */   public BehaviorWork() {
/* 25 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.JOB_SITE, MemoryStatus.VALUE_PRESENT, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityVillager var1) {
/* 33 */     if (var0.getTime() - this.b < 300L) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     if (var0.random.nextInt(2) != 0) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     this.b = var0.getTime();
/*    */     
/* 43 */     GlobalPos var2 = var1.getBehaviorController().<GlobalPos>getMemory(MemoryModuleType.JOB_SITE).get();
/* 44 */     return (var2.getDimensionManager() == var0.getDimensionKey() && var2.getBlockPosition().a(var1.getPositionVector(), 1.73D));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/* 49 */     BehaviorController<EntityVillager> var4 = var1.getBehaviorController();
/* 50 */     var4.setMemory(MemoryModuleType.LAST_WORKED_AT_POI, Long.valueOf(var2));
/* 51 */     var4.<GlobalPos>getMemory(MemoryModuleType.JOB_SITE).ifPresent(var1 -> var0.setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorTarget(var1.getBlockPosition())));
/*    */ 
/*    */ 
/*    */     
/* 55 */     var1.fd();
/* 56 */     doWork(var0, var1);
/*    */     
/* 58 */     if (var1.fc()) {
/* 59 */       var1.fb();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doWork(WorldServer var0, EntityVillager var1) {}
/*    */ 
/*    */   
/*    */   protected boolean b(WorldServer var0, EntityVillager var1, long var2) {
/* 69 */     Optional<GlobalPos> var4 = var1.getBehaviorController().getMemory(MemoryModuleType.JOB_SITE);
/* 70 */     if (!var4.isPresent()) {
/* 71 */       return false;
/*    */     }
/*    */     
/* 74 */     GlobalPos var5 = var4.get();
/* 75 */     return (var5.getDimensionManager() == var0.getDimensionKey() && var5
/* 76 */       .getBlockPosition().a(var1.getPositionVector(), 1.73D));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorWork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */