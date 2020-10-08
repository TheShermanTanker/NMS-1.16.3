/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftVillager;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ import org.bukkit.event.entity.VillagerCareerChangeEvent;
/*    */ 
/*    */ public class BehaviorCareer
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   public BehaviorCareer() {
/* 14 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.POTENTIAL_JOB_SITE, MemoryStatus.VALUE_PRESENT));
/*    */   }
/*    */   
/*    */   protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
/* 18 */     BlockPosition blockposition = ((GlobalPos)entityvillager.getBehaviorController().<GlobalPos>getMemory(MemoryModuleType.POTENTIAL_JOB_SITE).get()).getBlockPosition();
/*    */     
/* 20 */     return (blockposition.a(entityvillager.getPositionVector(), 2.0D) || entityvillager.eZ());
/*    */   }
/*    */   
/*    */   protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
/* 24 */     GlobalPos globalpos = entityvillager.getBehaviorController().<GlobalPos>getMemory(MemoryModuleType.POTENTIAL_JOB_SITE).get();
/*    */     
/* 26 */     entityvillager.getBehaviorController().removeMemory(MemoryModuleType.POTENTIAL_JOB_SITE);
/* 27 */     entityvillager.getBehaviorController().setMemory(MemoryModuleType.JOB_SITE, globalpos);
/* 28 */     worldserver.broadcastEntityEffect(entityvillager, (byte)14);
/* 29 */     if (entityvillager.getVillagerData().getProfession() == VillagerProfession.NONE) {
/* 30 */       MinecraftServer minecraftserver = worldserver.getMinecraftServer();
/*    */       
/* 32 */       Optional.<WorldServer>ofNullable(minecraftserver.getWorldServer(globalpos.getDimensionManager())).flatMap(worldserver1 -> worldserver1.y().c(globalpos.getBlockPosition()))
/*    */         
/* 34 */         .flatMap(villageplacetype -> IRegistry.VILLAGER_PROFESSION.g().filter(()).findFirst())
/*    */ 
/*    */ 
/*    */         
/* 38 */         .ifPresent(villagerprofession -> {
/*    */             VillagerCareerChangeEvent event = CraftEventFactory.callVillagerCareerChangeEvent(entityvillager, CraftVillager.nmsToBukkitProfession(villagerprofession), VillagerCareerChangeEvent.ChangeReason.EMPLOYED);
/*    */             if (event.isCancelled())
/*    */               return; 
/*    */             entityvillager.setVillagerData(entityvillager.getVillagerData().withProfession(CraftVillager.bukkitToNmsProfession(event.getProfession())));
/*    */             entityvillager.c(worldserver);
/*    */           });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorCareer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */