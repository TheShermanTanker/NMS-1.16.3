/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import com.destroystokyo.paper.profile.PlayerProfile;
/*     */ import java.net.InetAddress;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AsyncPlayerPreLoginEvent
/*     */   extends Event
/*     */ {
/*  18 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   private Result result;
/*     */   private String message;
/*     */   private final InetAddress ipAddress;
/*     */   private PlayerProfile profile;
/*     */   
/*     */   @Deprecated
/*     */   public AsyncPlayerPreLoginEvent(@NotNull String name, @NotNull InetAddress ipAddress) {
/*  27 */     this(name, ipAddress, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public AsyncPlayerPreLoginEvent(@NotNull String name, @NotNull InetAddress ipAddress, @NotNull UUID uniqueId) {
/*  32 */     this(name, ipAddress, uniqueId, Bukkit.createProfile(uniqueId, name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PlayerProfile getPlayerProfile() {
/*  42 */     return this.profile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayerProfile(@NotNull PlayerProfile profile) {
/*  49 */     this.profile = profile;
/*     */   }
/*     */   
/*     */   public AsyncPlayerPreLoginEvent(@NotNull String name, @NotNull InetAddress ipAddress, @NotNull UUID uniqueId, @NotNull PlayerProfile profile) {
/*  53 */     super(true);
/*  54 */     this.profile = profile;
/*     */     
/*  56 */     this.result = Result.ALLOWED;
/*  57 */     this.message = "";
/*     */     
/*  59 */     this.ipAddress = ipAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Result getLoginResult() {
/*  70 */     return this.result;
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
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public PlayerPreLoginEvent.Result getResult() {
/*  84 */     return (this.result == null) ? null : this.result.old();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLoginResult(@NotNull Result result) {
/*  93 */     this.result = result;
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
/*     */   @Deprecated
/*     */   public void setResult(@NotNull PlayerPreLoginEvent.Result result) {
/* 106 */     this.result = (result == null) ? null : Result.valueOf(result.name());
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
/* 117 */     return this.message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKickMessage(@NotNull String message) {
/* 126 */     this.message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void allow() {
/* 133 */     this.result = Result.ALLOWED;
/* 134 */     this.message = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disallow(@NotNull Result result, @NotNull String message) {
/* 144 */     this.result = result;
/* 145 */     this.message = message;
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
/*     */   @Deprecated
/*     */   public void disallow(@NotNull PlayerPreLoginEvent.Result result, @NotNull String message) {
/* 159 */     this.result = (result == null) ? null : Result.valueOf(result.name());
/* 160 */     this.message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/* 170 */     return this.profile.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InetAddress getAddress() {
/* 180 */     return this.ipAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public UUID getUniqueId() {
/* 190 */     return this.profile.getId();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 196 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 201 */     return handlers;
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
/* 212 */     ALLOWED,
/*     */ 
/*     */ 
/*     */     
/* 216 */     KICK_FULL,
/*     */ 
/*     */ 
/*     */     
/* 220 */     KICK_BANNED,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     KICK_WHITELIST,
/*     */ 
/*     */ 
/*     */     
/* 229 */     KICK_OTHER;
/*     */     
/*     */     @Deprecated
/*     */     @NotNull
/*     */     private PlayerPreLoginEvent.Result old() {
/* 234 */       return PlayerPreLoginEvent.Result.valueOf(name());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\AsyncPlayerPreLoginEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */