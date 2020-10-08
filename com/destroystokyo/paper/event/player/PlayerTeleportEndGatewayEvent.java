/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.EndGateway;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.player.PlayerTeleportEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerTeleportEndGatewayEvent
/*    */   extends PlayerTeleportEvent
/*    */ {
/*    */   @NotNull
/*    */   private final EndGateway gateway;
/*    */   
/*    */   public PlayerTeleportEndGatewayEvent(@NotNull Player player, @NotNull Location from, @NotNull Location to, @NotNull EndGateway gateway) {
/* 16 */     super(player, from, to, PlayerTeleportEvent.TeleportCause.END_GATEWAY);
/* 17 */     this.gateway = gateway;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public EndGateway getGateway() {
/* 27 */     return this.gateway;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerTeleportEndGatewayEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */