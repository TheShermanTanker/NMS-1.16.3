/*     */ package com.destroystokyo.paper.event.server;
/*     */ 
/*     */ import com.destroystokyo.paper.network.StatusClient;
/*     */ import com.destroystokyo.paper.profile.PlayerProfile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.server.ServerListPingEvent;
/*     */ import org.bukkit.util.CachedServerIcon;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PaperServerListPingEvent
/*     */   extends ServerListPingEvent
/*     */   implements Cancellable
/*     */ {
/*     */   @NotNull
/*     */   private final StatusClient client;
/*     */   private int numPlayers;
/*     */   private boolean hidePlayers;
/*     */   @NotNull
/*  32 */   private final List<PlayerProfile> playerSample = new ArrayList<>();
/*     */   
/*     */   @NotNull
/*     */   private String version;
/*     */   
/*     */   private int protocolVersion;
/*     */   
/*     */   @Nullable
/*     */   private CachedServerIcon favicon;
/*     */   private boolean cancelled;
/*     */   private boolean originalPlayerCount = true;
/*     */   private Object[] players;
/*     */   
/*     */   public PaperServerListPingEvent(@NotNull StatusClient client, @NotNull String motd, int numPlayers, int maxPlayers, @NotNull String version, int protocolVersion, @Nullable CachedServerIcon favicon) {
/*  46 */     super(client.getAddress().getAddress(), motd, numPlayers, maxPlayers);
/*  47 */     this.client = client;
/*  48 */     this.numPlayers = numPlayers;
/*  49 */     this.version = version;
/*  50 */     this.protocolVersion = protocolVersion;
/*  51 */     setServerIcon(favicon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StatusClient getClient() {
/*  61 */     return this.client;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumPlayers() {
/*  72 */     if (this.hidePlayers) {
/*  73 */       return -1;
/*     */     }
/*     */     
/*  76 */     return this.numPlayers;
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
/*     */   public void setNumPlayers(int numPlayers) {
/*  88 */     if (this.numPlayers != numPlayers) {
/*  89 */       this.numPlayers = numPlayers;
/*  90 */       this.originalPlayerCount = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxPlayers() {
/* 102 */     if (this.hidePlayers) {
/* 103 */       return -1;
/*     */     }
/*     */     
/* 106 */     return super.getMaxPlayers();
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
/*     */   public boolean shouldHidePlayers() {
/* 120 */     return this.hidePlayers;
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
/*     */   public void setHidePlayers(boolean hidePlayers) {
/* 134 */     this.hidePlayers = hidePlayers;
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
/*     */   @NotNull
/*     */   public List<PlayerProfile> getPlayerSample() {
/* 148 */     return this.playerSample;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getVersion() {
/* 158 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(@NotNull String version) {
/* 167 */     this.version = Objects.<String>requireNonNull(version, "version");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProtocolVersion() {
/* 178 */     return this.protocolVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProtocolVersion(int protocolVersion) {
/* 188 */     this.protocolVersion = protocolVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CachedServerIcon getServerIcon() {
/* 198 */     return this.favicon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerIcon(@Nullable CachedServerIcon icon) {
/* 208 */     if (icon != null && icon.isEmpty())
/*     */     {
/* 210 */       icon = null;
/*     */     }
/*     */     
/* 213 */     this.favicon = icon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 224 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 235 */     this.cancelled = cancel;
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
/*     */   @NotNull
/*     */   public Iterator<Player> iterator() {
/* 255 */     if (this.players == null) {
/* 256 */       this.players = getOnlinePlayers();
/*     */     }
/*     */     
/* 259 */     return new PlayerIterator();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   protected Object[] getOnlinePlayers() {
/* 264 */     return Bukkit.getOnlinePlayers().toArray();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   protected Player getBukkitPlayer(@NotNull Object player) {
/* 269 */     return (Player)player;
/*     */   }
/*     */ 
/*     */   
/*     */   private final class PlayerIterator
/*     */     implements Iterator<Player>
/*     */   {
/*     */     private int next;
/*     */     private int current;
/*     */     
/*     */     public boolean hasNext() {
/* 280 */       for (; this.next < PaperServerListPingEvent.this.players.length; this.next++) {
/* 281 */         if (PaperServerListPingEvent.this.players[this.next] != null) {
/* 282 */           return true;
/*     */         }
/*     */       } 
/*     */       
/* 286 */       return false;
/*     */     } @Nullable
/*     */     private Player player;
/*     */     private PlayerIterator() {}
/*     */     @NotNull
/*     */     public Player next() {
/* 292 */       if (!hasNext()) {
/* 293 */         this.player = null;
/* 294 */         throw new NoSuchElementException();
/*     */       } 
/*     */       
/* 297 */       this.current = this.next++;
/* 298 */       return this.player = PaperServerListPingEvent.this.getBukkitPlayer(PaperServerListPingEvent.this.players[this.current]);
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 303 */       if (this.player == null) {
/* 304 */         throw new IllegalStateException();
/*     */       }
/*     */       
/* 307 */       UUID uniqueId = this.player.getUniqueId();
/* 308 */       this.player = null;
/*     */ 
/*     */       
/* 311 */       PaperServerListPingEvent.this.players[this.current] = null;
/*     */ 
/*     */       
/* 314 */       PaperServerListPingEvent.this.getPlayerSample().removeIf(p -> uniqueId.equals(p.getId()));
/*     */ 
/*     */       
/* 317 */       if (PaperServerListPingEvent.this.originalPlayerCount)
/* 318 */         PaperServerListPingEvent.this.numPlayers--; 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\server\PaperServerListPingEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */