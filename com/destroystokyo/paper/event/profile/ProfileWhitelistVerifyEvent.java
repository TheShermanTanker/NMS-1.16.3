/*     */ package com.destroystokyo.paper.event.profile;
/*     */ 
/*     */ import com.destroystokyo.paper.profile.PlayerProfile;
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
/*     */ public class ProfileWhitelistVerifyEvent
/*     */   extends Event
/*     */ {
/*  40 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   @NotNull
/*     */   private final PlayerProfile profile;
/*     */   
/*     */   private final boolean whitelistEnabled;
/*     */   
/*     */   public ProfileWhitelistVerifyEvent(@NotNull PlayerProfile profile, boolean whitelistEnabled, boolean whitelisted, boolean isOp, @Nullable String kickMessage) {
/*  48 */     this.profile = profile;
/*  49 */     this.whitelistEnabled = whitelistEnabled;
/*  50 */     this.whitelisted = whitelisted;
/*  51 */     this.isOp = isOp;
/*  52 */     this.kickMessage = kickMessage;
/*     */   }
/*     */   private boolean whitelisted; private final boolean isOp;
/*     */   @Nullable
/*     */   private String kickMessage;
/*     */   
/*     */   @Nullable
/*     */   public String getKickMessage() {
/*  60 */     return this.kickMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKickMessage(@Nullable String kickMessage) {
/*  67 */     this.kickMessage = kickMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PlayerProfile getPlayerProfile() {
/*  75 */     return this.profile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWhitelisted() {
/*  82 */     return this.whitelisted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWhitelisted(boolean whitelisted) {
/*  90 */     this.whitelisted = whitelisted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOp() {
/*  97 */     return this.isOp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWhitelistEnabled() {
/* 104 */     return this.whitelistEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 110 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 115 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\profile\ProfileWhitelistVerifyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */