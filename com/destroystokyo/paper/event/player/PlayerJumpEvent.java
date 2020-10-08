/*     */ package com.destroystokyo.paper.event.player;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.player.PlayerEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerJumpEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*  19 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   private boolean cancel = false;
/*     */ 
/*     */   
/*     */   public PlayerJumpEvent(@NotNull Player player, @NotNull Location from, @NotNull Location to) {
/*  25 */     super(player);
/*  26 */     this.from = from;
/*  27 */     this.to = to;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private Location from;
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private Location to;
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  41 */     return this.cancel;
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
/*     */   public void setCancelled(boolean cancel) {
/*  55 */     this.cancel = cancel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Location getFrom() {
/*  65 */     return this.from;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFrom(@NotNull Location from) {
/*  74 */     validateLocation(from);
/*  75 */     this.from = from;
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
/*     */   @NotNull
/*     */   public Location getTo() {
/*  88 */     return this.to;
/*     */   }
/*     */   
/*     */   private void validateLocation(Location loc) {
/*  92 */     Preconditions.checkArgument((loc != null), "Cannot use null location!");
/*  93 */     Preconditions.checkArgument((loc.getWorld() != null), "Cannot use location with null world!");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  99 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 104 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerJumpEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */