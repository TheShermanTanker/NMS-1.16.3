/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.AreaEffectCloud;
/*    */ import org.bukkit.entity.EnderDragon;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EnderDragonFlameEvent
/*    */   extends EntityEvent implements Cancellable {
/*    */   @NotNull
/*    */   private final AreaEffectCloud areaEffectCloud;
/*    */   
/*    */   public EnderDragonFlameEvent(@NotNull EnderDragon enderDragon, @NotNull AreaEffectCloud areaEffectCloud) {
/* 17 */     super((Entity)enderDragon);
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
/* 50 */     this.cancelled = false;
/*    */     this.areaEffectCloud = areaEffectCloud; }
/*    */   @NotNull public EnderDragon getEntity() { return (EnderDragon)super.getEntity(); }
/*    */   @NotNull
/* 54 */   public AreaEffectCloud getAreaEffectCloud() { return this.areaEffectCloud; } private static final HandlerList handlers = new HandlerList(); public boolean isCancelled() { return this.cancelled; }
/*    */   private boolean cancelled;
/*    */   @NotNull
/*    */   public HandlerList getHandlers() { return handlers; } @NotNull
/*    */   public static HandlerList getHandlerList() { return handlers; } public void setCancelled(boolean cancel) {
/* 59 */     this.cancelled = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EnderDragonFlameEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */