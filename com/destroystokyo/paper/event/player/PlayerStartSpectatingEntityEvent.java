/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PlayerStartSpectatingEntityEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */ 
/*    */   
/*    */   public PlayerStartSpectatingEntityEvent(@NotNull Player player, @NotNull Entity currentSpectatorTarget, @NotNull Entity newSpectatorTarget) {
/* 21 */     super(player);
/* 22 */     this.currentSpectatorTarget = currentSpectatorTarget;
/* 23 */     this.newSpectatorTarget = newSpectatorTarget;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   private final Entity currentSpectatorTarget;
/*    */   @NotNull
/*    */   private final Entity newSpectatorTarget;
/*    */   
/*    */   @NotNull
/*    */   public Entity getCurrentSpectatorTarget() {
/* 33 */     return this.currentSpectatorTarget;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getNewSpectatorTarget() {
/* 43 */     return this.newSpectatorTarget;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 48 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 53 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 59 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 64 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerStartSpectatingEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */