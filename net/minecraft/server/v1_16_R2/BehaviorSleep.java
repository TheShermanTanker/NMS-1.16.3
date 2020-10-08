/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class BehaviorSleep extends Behavior<EntityLiving> {
/*    */   private long b;
/*    */   
/*    */   public BehaviorSleep() {
/* 11 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.HOME, MemoryStatus.VALUE_PRESENT, MemoryModuleType.LAST_WOKEN, MemoryStatus.REGISTERED));
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
/* 16 */     if (entityliving.isPassenger()) {
/* 17 */       return false;
/*    */     }
/* 19 */     BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
/* 20 */     GlobalPos globalpos = behaviorcontroller.<GlobalPos>getMemory(MemoryModuleType.HOME).get();
/*    */     
/* 22 */     if (worldserver.getDimensionKey() != globalpos.getDimensionManager()) {
/* 23 */       return false;
/*    */     }
/* 25 */     Optional<Long> optional = behaviorcontroller.getMemory(MemoryModuleType.LAST_WOKEN);
/*    */     
/* 27 */     if (optional.isPresent()) {
/* 28 */       long i = worldserver.getTime() - ((Long)optional.get()).longValue();
/*    */       
/* 30 */       if (i > 0L && i < 100L) {
/* 31 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 35 */     IBlockData iblockdata = worldserver.getTypeIfLoaded(globalpos.getBlockPosition());
/* 36 */     if (iblockdata == null) return false;
/*    */     
/* 38 */     return (globalpos.getBlockPosition().a(entityliving.getPositionVector(), 2.0D) && iblockdata.getBlock().a(TagsBlock.BEDS) && !((Boolean)iblockdata.get(BlockBed.OCCUPIED)).booleanValue());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean b(WorldServer worldserver, EntityLiving entityliving, long i) {
/* 45 */     Optional<GlobalPos> optional = entityliving.getBehaviorController().getMemory(MemoryModuleType.HOME);
/*    */     
/* 47 */     if (!optional.isPresent()) {
/* 48 */       return false;
/*    */     }
/* 50 */     BlockPosition blockposition = ((GlobalPos)optional.get()).getBlockPosition();
/*    */     
/* 52 */     return (entityliving.getBehaviorController().c(Activity.REST) && entityliving.locY() > blockposition.getY() + 0.4D && blockposition.a(entityliving.getPositionVector(), 1.14D));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
/* 58 */     if (i > this.b) {
/* 59 */       BehaviorInteractDoor.a(worldserver, entityliving, (PathPoint)null, (PathPoint)null);
/* 60 */       entityliving.entitySleep(((GlobalPos)entityliving.getBehaviorController().<GlobalPos>getMemory(MemoryModuleType.HOME).get()).getBlockPosition());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(long i) {
/* 67 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(WorldServer worldserver, EntityLiving entityliving, long i) {
/* 72 */     if (entityliving.isSleeping()) {
/* 73 */       entityliving.entityWakeup();
/* 74 */       this.b = i + 40L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorSleep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */