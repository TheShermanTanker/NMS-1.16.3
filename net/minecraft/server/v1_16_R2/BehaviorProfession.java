/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftVillager;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ import org.bukkit.event.entity.VillagerCareerChangeEvent;
/*    */ 
/*    */ public class BehaviorProfession
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   public BehaviorProfession() {
/* 13 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.JOB_SITE, MemoryStatus.VALUE_ABSENT));
/*    */   }
/*    */   
/*    */   protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
/* 17 */     VillagerData villagerdata = entityvillager.getVillagerData();
/*    */     
/* 19 */     return (villagerdata.getProfession() != VillagerProfession.NONE && villagerdata.getProfession() != VillagerProfession.NITWIT && entityvillager.getExperience() == 0 && villagerdata.getLevel() <= 1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
/* 24 */     VillagerCareerChangeEvent event = CraftEventFactory.callVillagerCareerChangeEvent(entityvillager, CraftVillager.nmsToBukkitProfession(VillagerProfession.NONE), VillagerCareerChangeEvent.ChangeReason.EMPLOYED);
/* 25 */     if (event.isCancelled()) {
/*    */       return;
/*    */     }
/*    */     
/* 29 */     entityvillager.setVillagerData(entityvillager.getVillagerData().withProfession(CraftVillager.bukkitToNmsProfession(event.getProfession())));
/*    */     
/* 31 */     entityvillager.c(worldserver);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorProfession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */