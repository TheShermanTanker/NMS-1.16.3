/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.AreaEffectCloud;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class AreaEffectCloudApplyEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final List<LivingEntity> affectedEntities;
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public AreaEffectCloudApplyEvent(@NotNull AreaEffectCloud entity, @NotNull List<LivingEntity> affectedEntities) {
/* 20 */     super((Entity)entity);
/* 21 */     this.affectedEntities = affectedEntities;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 26 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 31 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AreaEffectCloud getEntity() {
/* 37 */     return (AreaEffectCloud)this.entity;
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
/*    */   @NotNull
/*    */   public List<LivingEntity> getAffectedEntities() {
/* 52 */     return this.affectedEntities;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 58 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 63 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\AreaEffectCloudApplyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */