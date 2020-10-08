/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import java.net.InetAddress;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Warning;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ @Warning(reason = "This event causes a login thread to synchronize with the main thread")
/*     */ public class PlayerPreLoginEvent
/*     */   extends Event
/*     */ {
/*  20 */   private static final HandlerList handlers = new HandlerList();
/*     */   private Result result;
/*     */   private String message;
/*     */   private final String name;
/*     */   private final InetAddress ipAddress;
/*     */   private final UUID uniqueId;
/*     */   
/*     */   @Deprecated
/*     */   public PlayerPreLoginEvent(@NotNull String name, @NotNull InetAddress ipAddress) {
/*  29 */     this(name, ipAddress, null);
/*     */   }
/*     */   
/*     */   public PlayerPreLoginEvent(@NotNull String name, @NotNull InetAddress ipAddress, @NotNull UUID uniqueId) {
/*  33 */     this.result = Result.ALLOWED;
/*  34 */     this.message = "";
/*  35 */     this.name = name;
/*  36 */     this.ipAddress = ipAddress;
/*  37 */     this.uniqueId = uniqueId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Result getResult() {
/*  47 */     return this.result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResult(@NotNull Result result) {
/*  56 */     this.result = result;
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
/*  67 */     return this.message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKickMessage(@NotNull String message) {
/*  76 */     this.message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void allow() {
/*  83 */     this.result = Result.ALLOWED;
/*  84 */     this.message = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disallow(@NotNull Result result, @NotNull String message) {
/*  94 */     this.result = result;
/*  95 */     this.message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/* 105 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InetAddress getAddress() {
/* 115 */     return this.ipAddress;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 121 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public UUID getUniqueId() {
/* 131 */     return this.uniqueId;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 136 */     return handlers;
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
/* 147 */     ALLOWED,
/*     */ 
/*     */ 
/*     */     
/* 151 */     KICK_FULL,
/*     */ 
/*     */ 
/*     */     
/* 155 */     KICK_BANNED,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     KICK_WHITELIST,
/*     */ 
/*     */ 
/*     */     
/* 164 */     KICK_OTHER;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerPreLoginEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */