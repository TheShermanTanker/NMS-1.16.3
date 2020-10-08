/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class BehaviorMakeLove extends Behavior<EntityVillager> {
/*     */   public BehaviorMakeLove() {
/*  11 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.VISIBLE_MOBS, MemoryStatus.VALUE_PRESENT), 350, 350);
/*     */   }
/*     */   private long b;
/*     */   protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
/*  15 */     return a(entityvillager);
/*     */   }
/*     */   
/*     */   protected boolean b(WorldServer worldserver, EntityVillager entityvillager, long i) {
/*  19 */     return (i <= this.b && a(entityvillager));
/*     */   }
/*     */   
/*     */   protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
/*  23 */     EntityAgeable entityageable = entityvillager.getBehaviorController().<EntityAgeable>getMemory(MemoryModuleType.BREED_TARGET).get();
/*     */     
/*  25 */     BehaviorUtil.a(entityvillager, entityageable, 0.5F);
/*  26 */     worldserver.broadcastEntityEffect(entityageable, (byte)18);
/*  27 */     worldserver.broadcastEntityEffect(entityvillager, (byte)18);
/*  28 */     int j = 275 + entityvillager.getRandom().nextInt(50);
/*     */     
/*  30 */     this.b = i + j;
/*     */   }
/*     */   
/*     */   protected void d(WorldServer worldserver, EntityVillager entityvillager, long i) {
/*  34 */     EntityVillager entityvillager1 = entityvillager.getBehaviorController().<EntityAgeable>getMemory(MemoryModuleType.BREED_TARGET).get();
/*     */     
/*  36 */     if (entityvillager.h(entityvillager1) <= 5.0D) {
/*  37 */       BehaviorUtil.a(entityvillager, entityvillager1, 0.5F);
/*  38 */       if (i >= this.b) {
/*  39 */         entityvillager.ff();
/*  40 */         entityvillager1.ff();
/*  41 */         a(worldserver, entityvillager, entityvillager1);
/*  42 */       } else if (entityvillager.getRandom().nextInt(35) == 0) {
/*  43 */         worldserver.broadcastEntityEffect(entityvillager1, (byte)12);
/*  44 */         worldserver.broadcastEntityEffect(entityvillager, (byte)12);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(WorldServer worldserver, EntityVillager entityvillager, EntityVillager entityvillager1) {
/*  51 */     Optional<BlockPosition> optional = b(worldserver, entityvillager);
/*     */     
/*  53 */     if (!optional.isPresent()) {
/*  54 */       worldserver.broadcastEntityEffect(entityvillager1, (byte)13);
/*  55 */       worldserver.broadcastEntityEffect(entityvillager, (byte)13);
/*     */     } else {
/*  57 */       Optional<EntityVillager> optional1 = b(worldserver, entityvillager, entityvillager1);
/*     */       
/*  59 */       if (optional1.isPresent()) {
/*  60 */         a(worldserver, optional1.get(), optional.get());
/*     */       } else {
/*  62 */         worldserver.y().b(optional.get());
/*  63 */         PacketDebug.c(worldserver, optional.get());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(WorldServer worldserver, EntityVillager entityvillager, long i) {
/*  70 */     entityvillager.getBehaviorController().removeMemory(MemoryModuleType.BREED_TARGET);
/*     */   }
/*     */   
/*     */   private boolean a(EntityVillager entityvillager) {
/*  74 */     BehaviorController<EntityVillager> behaviorcontroller = entityvillager.getBehaviorController();
/*  75 */     Optional<EntityAgeable> optional = behaviorcontroller.<EntityAgeable>getMemory(MemoryModuleType.BREED_TARGET).filter(entityageable -> (entityageable.getEntityType() == EntityTypes.VILLAGER));
/*     */ 
/*     */ 
/*     */     
/*  79 */     return !optional.isPresent() ? false : ((BehaviorUtil.a(behaviorcontroller, (MemoryModuleType)MemoryModuleType.BREED_TARGET, EntityTypes.VILLAGER) && entityvillager.canBreed() && ((EntityAgeable)optional.get()).canBreed()));
/*     */   }
/*     */   
/*     */   private Optional<BlockPosition> b(WorldServer worldserver, EntityVillager entityvillager) {
/*  83 */     return worldserver.y().a(VillagePlaceType.r.c(), blockposition -> a(entityvillager, blockposition), entityvillager
/*     */         
/*  85 */         .getChunkCoordinates(), 48);
/*     */   }
/*     */   
/*     */   private boolean a(EntityVillager entityvillager, BlockPosition blockposition) {
/*  89 */     PathEntity pathentity = entityvillager.getNavigation().a(blockposition, VillagePlaceType.r.d());
/*     */     
/*  91 */     return (pathentity != null && pathentity.j());
/*     */   }
/*     */   
/*     */   private Optional<EntityVillager> b(WorldServer worldserver, EntityVillager entityvillager, EntityVillager entityvillager1) {
/*  95 */     EntityVillager entityvillager2 = entityvillager.createChild(worldserver, entityvillager1);
/*     */     
/*  97 */     if (CraftEventFactory.callEntityBreedEvent(entityvillager2, entityvillager, entityvillager1, null, null, 0).isCancelled()) {
/*  98 */       return Optional.empty();
/*     */     }
/*     */ 
/*     */     
/* 102 */     if (entityvillager2 == null) {
/* 103 */       return Optional.empty();
/*     */     }
/* 105 */     entityvillager.setAgeRaw(6000);
/* 106 */     entityvillager1.setAgeRaw(6000);
/* 107 */     entityvillager2.setAgeRaw(-24000);
/* 108 */     entityvillager2.setPositionRotation(entityvillager.locX(), entityvillager.locY(), entityvillager.locZ(), 0.0F, 0.0F);
/* 109 */     worldserver.addAllEntities(entityvillager2, CreatureSpawnEvent.SpawnReason.BREEDING);
/* 110 */     worldserver.broadcastEntityEffect(entityvillager2, (byte)12);
/* 111 */     return Optional.of(entityvillager2);
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(WorldServer worldserver, EntityVillager entityvillager, BlockPosition blockposition) {
/* 116 */     GlobalPos globalpos = GlobalPos.create(worldserver.getDimensionKey(), blockposition);
/*     */     
/* 118 */     entityvillager.getBehaviorController().setMemory(MemoryModuleType.HOME, globalpos);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorMakeLove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */