/*    */ package org.spigotmc.event.player;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PlayerSpawnLocationEvent
/*    */   extends PlayerEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private Location spawnLocation;
/*    */   
/*    */   public PlayerSpawnLocationEvent(@NotNull Player who, @NotNull Location spawnLocation) {
/* 17 */     super(who);
/* 18 */     this.spawnLocation = spawnLocation;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getSpawnLocation() {
/* 31 */     return this.spawnLocation;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSpawnLocation(@NotNull Location location) {
/* 40 */     this.spawnLocation = location;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 46 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 51 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\event\player\PlayerSpawnLocationEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */