/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Warning;
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
/*     */ @Deprecated
/*     */ @Warning(reason = "Listening to this event forces chat to wait for the main thread, delaying chat messages.")
/*     */ public class PlayerChatEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*  25 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancel = false;
/*     */   private String message;
/*     */   private String format;
/*     */   private final Set<Player> recipients;
/*     */   
/*     */   public PlayerChatEvent(@NotNull Player player, @NotNull String message) {
/*  32 */     super(player);
/*  33 */     this.message = message;
/*  34 */     this.format = "<%1$s> %2$s";
/*  35 */     this.recipients = new HashSet<>(player.getServer().getOnlinePlayers());
/*     */   }
/*     */   
/*     */   public PlayerChatEvent(@NotNull Player player, @NotNull String message, @NotNull String format, @NotNull Set<Player> recipients) {
/*  39 */     super(player);
/*  40 */     this.message = message;
/*  41 */     this.format = format;
/*  42 */     this.recipients = recipients;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  47 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  52 */     this.cancel = cancel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getMessage() {
/*  62 */     return this.message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessage(@NotNull String message) {
/*  71 */     this.message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayer(@NotNull Player player) {
/*  81 */     Validate.notNull(player, "Player cannot be null");
/*  82 */     this.player = player;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getFormat() {
/*  92 */     return this.format;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(@NotNull String format) {
/*     */     try {
/* 103 */       String.format(format, new Object[] { this.player, this.message });
/* 104 */     } catch (RuntimeException ex) {
/* 105 */       ex.fillInStackTrace();
/* 106 */       throw ex;
/*     */     } 
/*     */     
/* 109 */     this.format = format;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<Player> getRecipients() {
/* 119 */     return this.recipients;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 125 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 130 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerChatEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */