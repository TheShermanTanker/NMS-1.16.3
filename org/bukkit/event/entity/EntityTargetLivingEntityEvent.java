/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityTargetLivingEntityEvent
/*    */   extends EntityTargetEvent
/*    */ {
/*    */   public EntityTargetLivingEntityEvent(@NotNull Entity entity, @Nullable LivingEntity target, @Nullable EntityTargetEvent.TargetReason reason) {
/* 14 */     super(entity, (Entity)target, reason);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public LivingEntity getTarget() {
/* 20 */     return (LivingEntity)super.getTarget();
/*    */   }
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
/*    */   public void setTarget(@Nullable Entity target) {
/* 35 */     if (target == null || target instanceof LivingEntity)
/* 36 */       super.setTarget(target); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityTargetLivingEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */