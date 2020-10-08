/*     */ package org.bukkit.event.server;
/*     */ 
/*     */ import java.net.InetAddress;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.UndefinedNullability;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.util.CachedServerIcon;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class ServerListPingEvent
/*     */   extends ServerEvent
/*     */   implements Iterable<Player>
/*     */ {
/*     */   private static final int MAGIC_PLAYER_COUNT = -2147483648;
/*  18 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final InetAddress address;
/*     */   private String motd;
/*     */   private final int numPlayers;
/*     */   private int maxPlayers;
/*     */   
/*     */   public ServerListPingEvent(@NotNull InetAddress address, @NotNull String motd, int numPlayers, int maxPlayers) {
/*  25 */     super(true);
/*  26 */     Validate.isTrue((numPlayers >= 0), "Cannot have negative number of players online", numPlayers);
/*  27 */     this.address = address;
/*  28 */     this.motd = motd;
/*  29 */     this.numPlayers = numPlayers;
/*  30 */     this.maxPlayers = maxPlayers;
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
/*     */   protected ServerListPingEvent(@NotNull InetAddress address, @NotNull String motd, int maxPlayers) {
/*  43 */     super(true);
/*  44 */     this.numPlayers = Integer.MIN_VALUE;
/*  45 */     this.address = address;
/*  46 */     this.motd = motd;
/*  47 */     this.maxPlayers = maxPlayers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InetAddress getAddress() {
/*  57 */     return this.address;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getMotd() {
/*  67 */     return this.motd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMotd(@NotNull String motd) {
/*  76 */     this.motd = motd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumPlayers() {
/*  85 */     int numPlayers = this.numPlayers;
/*  86 */     if (numPlayers == Integer.MIN_VALUE) {
/*  87 */       numPlayers = 0;
/*  88 */       for (Player player : this) {
/*  89 */         numPlayers++;
/*     */       }
/*     */     } 
/*  92 */     return numPlayers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxPlayers() {
/* 101 */     return this.maxPlayers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxPlayers(int maxPlayers) {
/* 110 */     this.maxPlayers = maxPlayers;
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
/*     */   public void setServerIcon(@UndefinedNullability("implementation dependent") CachedServerIcon icon) throws IllegalArgumentException, UnsupportedOperationException {
/* 124 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 130 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 135 */     return handlers;
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
/*     */   @NotNull
/*     */   public Iterator<Player> iterator() throws UnsupportedOperationException {
/* 152 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\ServerListPingEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */