/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import java.net.InetAddress;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerConnectionCloseEvent
/*    */   extends Event
/*    */ {
/* 48 */   private static final HandlerList HANDLERS = new HandlerList();
/*    */   
/*    */   @NotNull
/*    */   private final UUID playerUniqueId;
/*    */ 
/*    */   
/*    */   public PlayerConnectionCloseEvent(@NotNull UUID playerUniqueId, @NotNull String playerName, @NotNull InetAddress ipAddress, boolean async) {
/* 55 */     super(async);
/* 56 */     this.playerUniqueId = playerUniqueId;
/* 57 */     this.playerName = playerName;
/* 58 */     this.ipAddress = ipAddress;
/*    */   }
/*    */   @NotNull
/*    */   private final String playerName; @NotNull
/*    */   private final InetAddress ipAddress;
/*    */   
/*    */   @NotNull
/*    */   public UUID getPlayerUniqueId() {
/* 66 */     return this.playerUniqueId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getPlayerName() {
/* 74 */     return this.playerName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public InetAddress getIpAddress() {
/* 82 */     return this.ipAddress;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 88 */     return HANDLERS;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 93 */     return HANDLERS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerConnectionCloseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */