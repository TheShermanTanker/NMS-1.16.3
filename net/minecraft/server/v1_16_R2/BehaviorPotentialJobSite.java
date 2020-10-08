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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorPotentialJobSite
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   final float b;
/*    */   
/*    */   public BehaviorPotentialJobSite(float var0) {
/* 29 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.POTENTIAL_JOB_SITE, MemoryStatus.VALUE_PRESENT), 1200);
/*    */ 
/*    */     
/* 32 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityVillager var1) {
/* 37 */     return ((Boolean)var1.getBehaviorController().f().<Boolean>map(var0 -> Boolean.valueOf((var0 == Activity.IDLE || var0 == Activity.WORK || var0 == Activity.PLAY))).orElse(Boolean.valueOf(true))).booleanValue();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean b(WorldServer var0, EntityVillager var1, long var2) {
/* 43 */     return var1.getBehaviorController().hasMemory(MemoryModuleType.POTENTIAL_JOB_SITE);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void d(WorldServer var0, EntityVillager var1, long var2) {
/* 48 */     BehaviorUtil.a(var1, ((GlobalPos)var1.getBehaviorController().<GlobalPos>getMemory(MemoryModuleType.POTENTIAL_JOB_SITE).get()).getBlockPosition(), this.b, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(WorldServer var0, EntityVillager var1, long var2) {
/* 53 */     Optional<GlobalPos> var4 = var1.getBehaviorController().getMemory(MemoryModuleType.POTENTIAL_JOB_SITE);
/* 54 */     var4.ifPresent(var1 -> {
/*    */           BlockPosition var2 = var1.getBlockPosition();
/*    */           WorldServer var3 = var0.getMinecraftServer().getWorldServer(var1.getDimensionManager());
/*    */           if (var3 == null) {
/*    */             return;
/*    */           }
/*    */           VillagePlace var4 = var3.y();
/*    */           if (var4.a(var2, ())) {
/*    */             var4.b(var2);
/*    */           }
/*    */           PacketDebug.c(var0, var2);
/*    */         });
/* 66 */     var1.getBehaviorController().removeMemory(MemoryModuleType.POTENTIAL_JOB_SITE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorPotentialJobSite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */