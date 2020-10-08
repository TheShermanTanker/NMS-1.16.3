/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PlayerRespawnEvent
/*    */   extends PlayerEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private Location respawnLocation;
/*    */   private final boolean isBedSpawn;
/*    */   private final boolean isAnchorSpawn;
/*    */   
/*    */   @Deprecated
/*    */   public PlayerRespawnEvent(@NotNull Player respawnPlayer, @NotNull Location respawnLocation, boolean isBedSpawn) {
/* 20 */     this(respawnPlayer, respawnLocation, isBedSpawn, false);
/*    */   }
/*    */   
/*    */   public PlayerRespawnEvent(@NotNull Player respawnPlayer, @NotNull Location respawnLocation, boolean isBedSpawn, boolean isAnchorSpawn) {
/* 24 */     super(respawnPlayer);
/* 25 */     this.respawnLocation = respawnLocation;
/* 26 */     this.isBedSpawn = isBedSpawn;
/* 27 */     this.isAnchorSpawn = isAnchorSpawn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getRespawnLocation() {
/* 37 */     return this.respawnLocation;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRespawnLocation(@NotNull Location respawnLocation) {
/* 46 */     Validate.notNull(respawnLocation, "Respawn location can not be null");
/* 47 */     Validate.notNull(respawnLocation.getWorld(), "Respawn world can not be null");
/*    */     
/* 49 */     this.respawnLocation = respawnLocation;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBedSpawn() {
/* 58 */     return this.isBedSpawn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnchorSpawn() {
/* 67 */     return this.isAnchorSpawn;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 73 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 78 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerRespawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */