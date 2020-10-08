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
/*    */ public class PlayerStopSpectatingEntityEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   
/*    */   public PlayerStopSpectatingEntityEvent(@NotNull Player player, @NotNull Entity spectatorTarget) {
/* 20 */     super(player);
/* 21 */     this.spectatorTarget = spectatorTarget;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   private final Entity spectatorTarget;
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getSpectatorTarget() {
/* 31 */     return this.spectatorTarget;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 36 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 41 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 47 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 52 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerStopSpectatingEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */