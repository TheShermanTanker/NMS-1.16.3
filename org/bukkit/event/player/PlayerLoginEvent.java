/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import java.net.InetAddress;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerLoginEvent
/*     */   extends PlayerEvent
/*     */ {
/*  16 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final InetAddress address;
/*     */   private final String hostname;
/*  19 */   private Result result = Result.ALLOWED;
/*  20 */   private String message = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final InetAddress realAddress;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlayerLoginEvent(@NotNull Player player, @NotNull String hostname, @NotNull InetAddress address, @NotNull InetAddress realAddress) {
/*  34 */     super(player);
/*  35 */     this.hostname = hostname;
/*  36 */     this.address = address;
/*     */     
/*  38 */     this.realAddress = realAddress;
/*     */   }
/*     */   
/*     */   public PlayerLoginEvent(@NotNull Player player, @NotNull String hostname, @NotNull InetAddress address) {
/*  42 */     this(player, hostname, address, address);
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
/*     */   public PlayerLoginEvent(@NotNull Player player, @NotNull String hostname, @NotNull InetAddress address, @NotNull Result result, @NotNull String message, @NotNull InetAddress realAddress) {
/*  58 */     this(player, hostname, address, realAddress);
/*  59 */     this.result = result;
/*  60 */     this.message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InetAddress getRealAddress() {
/*  71 */     return this.realAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Result getResult() {
/*  82 */     return this.result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResult(@NotNull Result result) {
/*  91 */     this.result = result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getKickMessage() {
/* 102 */     return this.message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKickMessage(@NotNull String message) {
/* 111 */     this.message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getHostname() {
/* 122 */     return this.hostname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void allow() {
/* 129 */     this.result = Result.ALLOWED;
/* 130 */     this.message = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disallow(@NotNull Result result, @NotNull String message) {
/* 140 */     this.result = result;
/* 141 */     this.message = message;
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
/*     */   public InetAddress getAddress() {
/* 154 */     return this.address;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 160 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 165 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Result
/*     */   {
/* 176 */     ALLOWED,
/*     */ 
/*     */ 
/*     */     
/* 180 */     KICK_FULL,
/*     */ 
/*     */ 
/*     */     
/* 184 */     KICK_BANNED,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 189 */     KICK_WHITELIST,
/*     */ 
/*     */ 
/*     */     
/* 193 */     KICK_OTHER;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerLoginEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */