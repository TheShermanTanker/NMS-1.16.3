/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Predicate;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ import org.bukkit.event.entity.EntityTargetEvent;
/*    */ import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
/*    */ 
/*    */ public class BehaviorAttackTargetForget<E extends EntityInsentient>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final Predicate<EntityLiving> b;
/*    */   
/*    */   public BehaviorAttackTargetForget(Predicate<EntityLiving> predicate) {
/* 17 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryStatus.REGISTERED));
/* 18 */     this.b = predicate;
/*    */   }
/*    */   
/*    */   public BehaviorAttackTargetForget() {
/* 22 */     this(entityliving -> false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(WorldServer worldserver, E e0, long i) {
/* 28 */     if (a((EntityLiving)e0)) {
/* 29 */       d(e0, EntityTargetEvent.TargetReason.FORGOT_TARGET);
/* 30 */     } else if (c(e0)) {
/* 31 */       d(e0, EntityTargetEvent.TargetReason.TARGET_DIED);
/* 32 */     } else if (a(e0)) {
/* 33 */       d(e0, EntityTargetEvent.TargetReason.TARGET_OTHER_LEVEL);
/* 34 */     } else if (!IEntitySelector.f.test(b(e0))) {
/* 35 */       d(e0, EntityTargetEvent.TargetReason.TARGET_INVALID);
/* 36 */     } else if (this.b.test(b(e0))) {
/* 37 */       d(e0, EntityTargetEvent.TargetReason.TARGET_INVALID);
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean a(E e0) {
/* 42 */     return ((b(e0)).world != ((EntityInsentient)e0).world);
/*    */   }
/*    */   
/*    */   private EntityLiving b(E e0) {
/* 46 */     return e0.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.ATTACK_TARGET).get();
/*    */   }
/*    */   
/*    */   private static <E extends EntityLiving> boolean a(E e0) {
/* 50 */     Optional<Long> optional = e0.getBehaviorController().getMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
/*    */     
/* 52 */     return (optional.isPresent() && ((EntityLiving)e0).world.getTime() - ((Long)optional.get()).longValue() > 200L);
/*    */   }
/*    */   
/*    */   private boolean c(E e0) {
/* 56 */     Optional<EntityLiving> optional = e0.getBehaviorController().getMemory(MemoryModuleType.ATTACK_TARGET);
/*    */     
/* 58 */     return (optional.isPresent() && !((EntityLiving)optional.get()).isAlive());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void d(E e0, EntityTargetEvent.TargetReason reason) {
/* 65 */     EntityTargetLivingEntityEvent entityTargetLivingEntityEvent = CraftEventFactory.callEntityTargetLivingEvent((Entity)e0, null, reason);
/* 66 */     if (entityTargetLivingEntityEvent.isCancelled()) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 76 */     e0.getBehaviorController().removeMemory(MemoryModuleType.ATTACK_TARGET);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorAttackTargetForget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */