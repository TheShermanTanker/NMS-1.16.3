/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PlayerPostRespawnEvent
/*    */   extends PlayerEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Location respawnedLocation;
/*    */   private final boolean isBedSpawn;
/*    */   
/*    */   public PlayerPostRespawnEvent(@NotNull Player respawnPlayer, @NotNull Location respawnedLocation, boolean isBedSpawn) {
/* 18 */     super(respawnPlayer);
/* 19 */     this.respawnedLocation = respawnedLocation;
/* 20 */     this.isBedSpawn = isBedSpawn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getRespawnedLocation() {
/* 30 */     return this.respawnedLocation.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBedSpawn() {
/* 39 */     return this.isBedSpawn;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 45 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 50 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerPostRespawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */