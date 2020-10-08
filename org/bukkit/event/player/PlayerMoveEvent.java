/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PlayerMoveEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*  15 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancel = false;
/*     */   private Location from;
/*     */   private Location to;
/*     */   
/*     */   public PlayerMoveEvent(@NotNull Player player, @NotNull Location from, @Nullable Location to) {
/*  21 */     super(player);
/*  22 */     this.from = from;
/*  23 */     this.to = to;
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
/*     */   public boolean isCancelled() {
/*  38 */     return this.cancel;
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
/*     */   public void setCancelled(boolean cancel) {
/*  53 */     this.cancel = cancel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Location getFrom() {
/*  63 */     return this.from;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFrom(@NotNull Location from) {
/*  72 */     validateLocation(from);
/*  73 */     this.from = from;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Location getTo() {
/*  83 */     return this.to;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTo(@NotNull Location to) {
/*  92 */     validateLocation(to);
/*  93 */     this.to = to;
/*     */   }
/*     */   
/*     */   private void validateLocation(@NotNull Location loc) {
/*  97 */     Preconditions.checkArgument((loc != null), "Cannot use null location!");
/*  98 */     Preconditions.checkArgument((loc.getWorld() != null), "Cannot use null location with null world!");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 104 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 109 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerMoveEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */