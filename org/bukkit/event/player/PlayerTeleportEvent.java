/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class PlayerTeleportEvent
/*    */   extends PlayerMoveEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/* 14 */   private TeleportCause cause = TeleportCause.UNKNOWN;
/*    */   
/*    */   public PlayerTeleportEvent(@NotNull Player player, @NotNull Location from, @Nullable Location to) {
/* 17 */     super(player, from, to);
/*    */   }
/*    */   
/*    */   public PlayerTeleportEvent(@NotNull Player player, @NotNull Location from, @Nullable Location to, @NotNull TeleportCause cause) {
/* 21 */     this(player, from, to);
/*    */     
/* 23 */     this.cause = cause;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public TeleportCause getCause() {
/* 33 */     return this.cause;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum TeleportCause
/*    */   {
/* 41 */     ENDER_PEARL,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     COMMAND,
/*    */ 
/*    */ 
/*    */     
/* 50 */     PLUGIN,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 55 */     NETHER_PORTAL,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 60 */     END_PORTAL,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 65 */     SPECTATE,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 70 */     END_GATEWAY,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 75 */     CHORUS_FRUIT,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 80 */     UNKNOWN;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 86 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 91 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerTeleportEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */