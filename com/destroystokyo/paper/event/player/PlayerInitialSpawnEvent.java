/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.spigotmc.event.player.PlayerSpawnLocationEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerInitialSpawnEvent
/*    */   extends PlayerSpawnLocationEvent
/*    */ {
/*    */   public PlayerInitialSpawnEvent(@NotNull Player who, @NotNull Location spawnLocation) {
/* 14 */     super(who, spawnLocation);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerInitialSpawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */