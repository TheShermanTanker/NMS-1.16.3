/*     */ package com.destroystokyo.paper.event.player;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerHandshakeEvent
/*     */   extends Event
/*     */   implements Cancellable
/*     */ {
/*  22 */   private static final HandlerList HANDLERS = new HandlerList();
/*     */   
/*     */   @NotNull
/*     */   private final String originalHandshake;
/*     */   
/*     */   private boolean cancelled;
/*     */   @Nullable
/*     */   private String serverHostname;
/*  30 */   private String failMessage = "If you wish to use IP forwarding, please enable it in your BungeeCord config as well!";
/*     */   
/*     */   @Nullable
/*     */   private String socketAddressHostname;
/*     */   
/*     */   @Nullable
/*     */   private UUID uniqueId;
/*     */   
/*     */   public PlayerHandshakeEvent(@NotNull String originalHandshake, boolean cancelled) {
/*  39 */     super(true);
/*  40 */     this.originalHandshake = originalHandshake;
/*  41 */     this.cancelled = cancelled;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private String propertiesJson;
/*     */ 
/*     */   
/*     */   private boolean failed;
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  54 */     return this.cancelled;
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
/*     */   public void setCancelled(boolean cancelled) {
/*  67 */     this.cancelled = cancelled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getOriginalHandshake() {
/*  77 */     return this.originalHandshake;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getServerHostname() {
/*  89 */     return this.serverHostname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerHostname(@NotNull String serverHostname) {
/* 100 */     this.serverHostname = serverHostname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getSocketAddressHostname() {
/* 112 */     return this.socketAddressHostname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSocketAddressHostname(@NotNull String socketAddressHostname) {
/* 123 */     this.socketAddressHostname = socketAddressHostname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UUID getUniqueId() {
/* 133 */     return this.uniqueId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUniqueId(@NotNull UUID uniqueId) {
/* 142 */     this.uniqueId = uniqueId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getPropertiesJson() {
/* 154 */     return this.propertiesJson;
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
/*     */   public boolean isFailed() {
/* 166 */     return this.failed;
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
/*     */   public void setFailed(boolean failed) {
/* 178 */     this.failed = failed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPropertiesJson(@NotNull String propertiesJson) {
/* 189 */     this.propertiesJson = propertiesJson;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getFailMessage() {
/* 199 */     return this.failMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFailMessage(@NotNull String failMessage) {
/* 208 */     Validate.notEmpty(failMessage, "fail message cannot be null or empty");
/* 209 */     this.failMessage = failMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 215 */     return HANDLERS;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 220 */     return HANDLERS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerHandshakeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */