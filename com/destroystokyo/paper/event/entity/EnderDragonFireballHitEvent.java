/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.bukkit.entity.AreaEffectCloud;
/*    */ import org.bukkit.entity.DragonFireball;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class EnderDragonFireballHitEvent
/*    */   extends EntityEvent implements Cancellable {
/*    */   @Nullable
/*    */   private final Collection<LivingEntity> targets;
/*    */   @NotNull
/*    */   private final AreaEffectCloud areaEffectCloud;
/*    */   
/*    */   public EnderDragonFireballHitEvent(@NotNull DragonFireball fireball, @Nullable Collection<LivingEntity> targets, @NotNull AreaEffectCloud areaEffectCloud) {
/* 22 */     super((Entity)fireball);
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
/* 68 */     this.cancelled = false; this.targets = targets;
/*    */     this.areaEffectCloud = areaEffectCloud; }
/*    */   @NotNull public DragonFireball getEntity() { return (DragonFireball)super.getEntity(); }
/*    */   @Nullable public Collection<LivingEntity> getTargets() { return this.targets; }
/* 72 */   @NotNull public AreaEffectCloud getAreaEffectCloud() { return this.areaEffectCloud; } public boolean isCancelled() { return this.cancelled; }
/*    */   
/*    */   private static final HandlerList handlers = new HandlerList(); private boolean cancelled;
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 77 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/*    */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/*    */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EnderDragonFireballHitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */