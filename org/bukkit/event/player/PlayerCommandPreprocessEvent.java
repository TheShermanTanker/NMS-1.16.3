/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerCommandPreprocessEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*  49 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancel = false;
/*     */   private String message;
/*     */   private final Set<Player> recipients;
/*     */   
/*     */   public PlayerCommandPreprocessEvent(@NotNull Player player, @NotNull String message) {
/*  55 */     super(player);
/*  56 */     this.recipients = new HashSet<>(player.getServer().getOnlinePlayers());
/*  57 */     this.message = message;
/*     */   }
/*     */   
/*     */   public PlayerCommandPreprocessEvent(@NotNull Player player, @NotNull String message, @NotNull Set<Player> recipients) {
/*  61 */     super(player);
/*  62 */     this.recipients = recipients;
/*  63 */     this.message = message;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  68 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  73 */     this.cancel = cancel;
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
/*     */   public String getMessage() {
/*  86 */     return this.message;
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
/*     */   public void setMessage(@NotNull String command) throws IllegalArgumentException {
/*  99 */     Validate.notNull(command, "Command cannot be null");
/* 100 */     Validate.notEmpty(command, "Command cannot be empty");
/* 101 */     this.message = command;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayer(@NotNull Player player) throws IllegalArgumentException {
/* 111 */     Validate.notNull(player, "Player cannot be null");
/* 112 */     this.player = player;
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
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public Set<Player> getRecipients() {
/* 132 */     return this.recipients;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 138 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 143 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerCommandPreprocessEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */