/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Enderman;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public class EndermanAttackPlayerEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/*    */   @NotNull
/*    */   private final Player player;
/*    */   
/*    */   @NotNull
/*    */   public Enderman getEntity() {
/*    */     return (Enderman)super.getEntity();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Player getPlayer() {
/*    */     return this.player;
/*    */   }
/*    */   
/*    */   public EndermanAttackPlayerEvent(@NotNull Enderman entity, @NotNull Player player) {
/* 45 */     super((Entity)entity);
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
/* 82 */     this.cancelled = false;
/*    */     this.player = player;
/*    */   }
/*    */ 
/*    */   
/*    */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public boolean isCancelled() {
/* 90 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean cancelled;
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 99 */     this.cancelled = cancel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EndermanAttackPlayerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */