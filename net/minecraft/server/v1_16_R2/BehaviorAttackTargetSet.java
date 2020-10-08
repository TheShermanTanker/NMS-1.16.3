/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ import org.bukkit.event.entity.EntityTargetEvent;
/*    */ import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
/*    */ 
/*    */ public class BehaviorAttackTargetSet<E extends EntityInsentient>
/*    */   extends Behavior<E> {
/*    */   private final Predicate<E> b;
/*    */   private final Function<E, Optional<? extends EntityLiving>> c;
/*    */   
/*    */   public BehaviorAttackTargetSet(Predicate<E> predicate, Function<E, Optional<? extends EntityLiving>> function) {
/* 19 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryStatus.REGISTERED));
/* 20 */     this.b = predicate;
/* 21 */     this.c = function;
/*    */   }
/*    */   
/*    */   public BehaviorAttackTargetSet(Function<E, Optional<? extends EntityLiving>> function) {
/* 25 */     this(entityinsentient -> true, function);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer worldserver, E e0) {
/* 31 */     if (!this.b.test(e0)) {
/* 32 */       return false;
/*    */     }
/* 34 */     Optional<? extends EntityLiving> optional = this.c.apply(e0);
/*    */     
/* 36 */     return (optional.isPresent() && ((EntityLiving)optional.get()).isAlive());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer worldserver, E e0, long i) {
/* 41 */     ((Optional)this.c.apply(e0)).ifPresent(entityliving -> a((E)e0, entityliving));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void a(E e0, EntityLiving entityliving) {
/* 48 */     EntityTargetLivingEntityEvent entityTargetLivingEntityEvent = CraftEventFactory.callEntityTargetLivingEvent((Entity)e0, entityliving, (entityliving instanceof EntityPlayer) ? EntityTargetEvent.TargetReason.CLOSEST_PLAYER : EntityTargetEvent.TargetReason.CLOSEST_ENTITY);
/* 49 */     if (entityTargetLivingEntityEvent.isCancelled()) {
/*    */       return;
/*    */     }
/* 52 */     entityliving = (entityTargetLivingEntityEvent.getTarget() != null) ? ((CraftLivingEntity)entityTargetLivingEntityEvent.getTarget()).getHandle() : null;
/*    */ 
/*    */     
/* 55 */     e0.getBehaviorController().setMemory(MemoryModuleType.ATTACK_TARGET, entityliving);
/* 56 */     e0.getBehaviorController().removeMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorAttackTargetSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */