/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import java.util.IllegalFormatException;
/*     */ import java.util.Set;
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
/*     */ public class AsyncPlayerChatEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*  27 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancel = false;
/*     */   private String message;
/*  30 */   private String format = "<%1$s> %2$s";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Set<Player> recipients;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AsyncPlayerChatEvent(boolean async, @NotNull Player who, @NotNull String message, @NotNull Set<Player> players) {
/*  41 */     super(who, async);
/*  42 */     this.message = message;
/*  43 */     this.recipients = players;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getMessage() {
/*  54 */     return this.message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessage(@NotNull String message) {
/*  64 */     this.message = message;
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
/*     */   @NotNull
/*     */   public String getFormat() {
/*  79 */     return this.format;
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
/*     */   
/*     */   public void setFormat(@NotNull String format) throws IllegalFormatException, NullPointerException {
/*     */     try {
/*  99 */       String.format(format, new Object[] { this.player, this.message });
/* 100 */     } catch (RuntimeException ex) {
/* 101 */       ex.fillInStackTrace();
/* 102 */       throw ex;
/*     */     } 
/*     */     
/* 105 */     this.format = format;
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
/*     */   @NotNull
/*     */   public Set<Player> getRecipients() {
/* 123 */     return this.recipients;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 128 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 133 */     this.cancel = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 139 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 144 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\AsyncPlayerChatEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */