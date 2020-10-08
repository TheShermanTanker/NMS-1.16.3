/*     */ package org.bukkit.event.entity;
/*     */ 
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class EntityTargetEvent
/*     */   extends EntityEvent
/*     */   implements Cancellable
/*     */ {
/*  13 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancel = false;
/*     */   private Entity target;
/*     */   private final TargetReason reason;
/*     */   
/*     */   public EntityTargetEvent(@NotNull Entity entity, @Nullable Entity target, @NotNull TargetReason reason) {
/*  19 */     super(entity);
/*  20 */     this.target = target;
/*  21 */     this.reason = reason;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  26 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  31 */     this.cancel = cancel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public TargetReason getReason() {
/*  41 */     return this.reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getTarget() {
/*  54 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(@Nullable Entity target) {
/*  70 */     this.target = target;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  76 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/*  81 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum TargetReason
/*     */   {
/*  92 */     TARGET_DIED,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     CLOSEST_PLAYER,
/*     */ 
/*     */ 
/*     */     
/* 101 */     TARGET_ATTACKED_ENTITY,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     PIG_ZOMBIE_TARGET,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     FORGOT_TARGET,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     TARGET_ATTACKED_OWNER,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     OWNER_ATTACKED_TARGET,
/*     */ 
/*     */ 
/*     */     
/* 127 */     RANDOM_TARGET,
/*     */ 
/*     */ 
/*     */     
/* 131 */     DEFEND_VILLAGE,
/*     */ 
/*     */ 
/*     */     
/* 135 */     TARGET_ATTACKED_NEARBY_ENTITY,
/*     */ 
/*     */ 
/*     */     
/* 139 */     REINFORCEMENT_TARGET,
/*     */ 
/*     */ 
/*     */     
/* 143 */     COLLISION,
/*     */ 
/*     */ 
/*     */     
/* 147 */     CUSTOM,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     CLOSEST_ENTITY,
/*     */ 
/*     */ 
/*     */     
/* 156 */     FOLLOW_LEADER,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     TEMPT,
/*     */ 
/*     */ 
/*     */     
/* 165 */     TARGET_OTHER_LEVEL,
/*     */ 
/*     */ 
/*     */     
/* 169 */     TARGET_INVALID,
/*     */ 
/*     */ 
/*     */     
/* 173 */     UNKNOWN;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityTargetEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */