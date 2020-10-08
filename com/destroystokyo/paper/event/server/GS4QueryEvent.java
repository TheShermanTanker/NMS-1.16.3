/*     */ package com.destroystokyo.paper.event.server;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.net.InetAddress;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class GS4QueryEvent
/*     */   extends Event
/*     */ {
/*  23 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   private final QueryType queryType;
/*     */   private final InetAddress querierAddress;
/*     */   private QueryResponse response;
/*     */   
/*     */   public GS4QueryEvent(@NotNull QueryType queryType, @NotNull InetAddress querierAddress, @NotNull QueryResponse response) {
/*  30 */     super(true);
/*  31 */     this.queryType = (QueryType)Preconditions.checkNotNull(queryType, "queryType");
/*  32 */     this.querierAddress = (InetAddress)Preconditions.checkNotNull(querierAddress, "querierAddress");
/*  33 */     this.response = (QueryResponse)Preconditions.checkNotNull(response, "response");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public QueryType getQueryType() {
/*  42 */     return this.queryType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InetAddress getQuerierAddress() {
/*  51 */     return this.querierAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public QueryResponse getResponse() {
/*  60 */     return this.response;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResponse(@NotNull QueryResponse response) {
/*  68 */     this.response = (QueryResponse)Preconditions.checkNotNull(response, "response");
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  73 */     return "GS4QueryEvent{queryType=" + this.queryType + ", querierAddress=" + this.querierAddress + ", response=" + this.response + '}';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  83 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/*  88 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum QueryType
/*     */   {
/*  99 */     BASIC,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     FULL;
/*     */   }
/*     */   
/*     */   public static final class QueryResponse
/*     */   {
/*     */     private final String motd;
/*     */     private final String gameVersion;
/*     */     private final String map;
/*     */     private final int currentPlayers;
/*     */     private final int maxPlayers;
/*     */     private final String hostname;
/*     */     private final int port;
/*     */     private final Collection<String> players;
/*     */     private final String serverVersion;
/*     */     private final Collection<PluginInformation> plugins;
/*     */     
/*     */     private QueryResponse(String motd, String gameVersion, String map, int currentPlayers, int maxPlayers, String hostname, int port, Collection<String> players, String serverVersion, Collection<PluginInformation> plugins) {
/* 121 */       this.motd = motd;
/* 122 */       this.gameVersion = gameVersion;
/* 123 */       this.map = map;
/* 124 */       this.currentPlayers = currentPlayers;
/* 125 */       this.maxPlayers = maxPlayers;
/* 126 */       this.hostname = hostname;
/* 127 */       this.port = port;
/* 128 */       this.players = players;
/* 129 */       this.serverVersion = serverVersion;
/* 130 */       this.plugins = plugins;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public String getMotd() {
/* 139 */       return this.motd;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public String getGameVersion() {
/* 148 */       return this.gameVersion;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public String getMap() {
/* 157 */       return this.map;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getCurrentPlayers() {
/* 165 */       return this.currentPlayers;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getMaxPlayers() {
/* 173 */       return this.maxPlayers;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public String getHostname() {
/* 182 */       return this.hostname;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getPort() {
/* 190 */       return this.port;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Collection<String> getPlayers() {
/* 199 */       return this.players;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public String getServerVersion() {
/* 208 */       return this.serverVersion;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Collection<PluginInformation> getPlugins() {
/* 217 */       return this.plugins;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder toBuilder() {
/* 227 */       return builder()
/* 228 */         .motd(getMotd())
/* 229 */         .gameVersion(getGameVersion())
/* 230 */         .map(getMap())
/* 231 */         .currentPlayers(getCurrentPlayers())
/* 232 */         .maxPlayers(getMaxPlayers())
/* 233 */         .hostname(getHostname())
/* 234 */         .port(getPort())
/* 235 */         .players(getPlayers())
/* 236 */         .serverVersion(getServerVersion())
/* 237 */         .plugins(getPlugins());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public static Builder builder() {
/* 246 */       return new Builder();
/*     */     }
/*     */ 
/*     */     
/*     */     public static final class Builder
/*     */     {
/*     */       private String motd;
/*     */       
/*     */       private String gameVersion;
/*     */       
/*     */       private String map;
/*     */       
/*     */       private String hostname;
/*     */       private String serverVersion;
/*     */       private int currentPlayers;
/*     */       private int maxPlayers;
/*     */       private int port;
/* 263 */       private List<String> players = new ArrayList<>();
/* 264 */       private List<GS4QueryEvent.QueryResponse.PluginInformation> plugins = new ArrayList<>();
/*     */ 
/*     */ 
/*     */       
/*     */       @NotNull
/*     */       public Builder motd(@NotNull String motd) {
/* 270 */         this.motd = (String)Preconditions.checkNotNull(motd, "motd");
/* 271 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder gameVersion(@NotNull String gameVersion) {
/* 276 */         this.gameVersion = (String)Preconditions.checkNotNull(gameVersion, "gameVersion");
/* 277 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder map(@NotNull String map) {
/* 282 */         this.map = (String)Preconditions.checkNotNull(map, "map");
/* 283 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder currentPlayers(int currentPlayers) {
/* 288 */         Preconditions.checkArgument((currentPlayers >= 0), "currentPlayers cannot be negative");
/* 289 */         this.currentPlayers = currentPlayers;
/* 290 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder maxPlayers(int maxPlayers) {
/* 295 */         Preconditions.checkArgument((maxPlayers >= 0), "maxPlayers cannot be negative");
/* 296 */         this.maxPlayers = maxPlayers;
/* 297 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder hostname(@NotNull String hostname) {
/* 302 */         this.hostname = (String)Preconditions.checkNotNull(hostname, "hostname");
/* 303 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder port(int port) {
/* 308 */         Preconditions.checkArgument((port >= 1 && port <= 65535), "port must be between 1-65535");
/* 309 */         this.port = port;
/* 310 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder players(@NotNull Collection<String> players) {
/* 315 */         this.players.addAll((Collection<? extends String>)Preconditions.checkNotNull(players, "players"));
/* 316 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder players(@NotNull String... players) {
/* 321 */         this.players.addAll(Arrays.asList((String[])Preconditions.checkNotNull(players, "players")));
/* 322 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder clearPlayers() {
/* 327 */         this.players.clear();
/* 328 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder serverVersion(@NotNull String serverVersion) {
/* 333 */         this.serverVersion = (String)Preconditions.checkNotNull(serverVersion, "serverVersion");
/* 334 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder plugins(@NotNull Collection<GS4QueryEvent.QueryResponse.PluginInformation> plugins) {
/* 339 */         this.plugins.addAll((Collection<? extends GS4QueryEvent.QueryResponse.PluginInformation>)Preconditions.checkNotNull(plugins, "plugins"));
/* 340 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder plugins(@NotNull GS4QueryEvent.QueryResponse.PluginInformation... plugins) {
/* 345 */         this.plugins.addAll(Arrays.asList((GS4QueryEvent.QueryResponse.PluginInformation[])Preconditions.checkNotNull(plugins, "plugins")));
/* 346 */         return this;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public Builder clearPlugins() {
/* 351 */         this.plugins.clear();
/* 352 */         return this;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       @NotNull
/*     */       public GS4QueryEvent.QueryResponse build() {
/* 361 */         return new GS4QueryEvent.QueryResponse(
/* 362 */             (String)Preconditions.checkNotNull(this.motd, "motd"), 
/* 363 */             (String)Preconditions.checkNotNull(this.gameVersion, "gameVersion"), 
/* 364 */             (String)Preconditions.checkNotNull(this.map, "map"), this.currentPlayers, this.maxPlayers, 
/*     */ 
/*     */             
/* 367 */             (String)Preconditions.checkNotNull(this.hostname, "hostname"), this.port, 
/*     */             
/* 369 */             (Collection)ImmutableList.copyOf(this.players), 
/* 370 */             (String)Preconditions.checkNotNull(this.serverVersion, "serverVersion"), 
/* 371 */             (Collection)ImmutableList.copyOf(this.plugins));
/*     */       }
/*     */ 
/*     */       
/*     */       private Builder() {}
/*     */     }
/*     */     
/*     */     public static class PluginInformation
/*     */     {
/*     */       private String name;
/*     */       private String version;
/*     */       
/*     */       public PluginInformation(@NotNull String name, @NotNull String version) {
/* 384 */         this.name = (String)Preconditions.checkNotNull(name, "name");
/* 385 */         this.version = (String)Preconditions.checkNotNull(version, "version");
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public String getName() {
/* 390 */         return this.name;
/*     */       }
/*     */       
/*     */       public void setName(@NotNull String name) {
/* 394 */         this.name = name;
/*     */       }
/*     */       
/*     */       public void setVersion(@NotNull String version) {
/* 398 */         this.version = version;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public String getVersion() {
/* 403 */         return this.version;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public static PluginInformation of(@NotNull String name, @NotNull String version) {
/* 408 */         return new PluginInformation(name, version);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\server\GS4QueryEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */