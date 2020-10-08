/*    */ package org.bukkit.event.raid;
/*    */ 
/*    */ import org.bukkit.Raid;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RaidTriggerEvent
/*    */   extends RaidEvent
/*    */   implements Cancellable
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final Player player;
/*    */   private boolean cancel;
/*    */   
/*    */   public RaidTriggerEvent(@NotNull Raid raid, @NotNull World world, @NotNull Player player) {
/* 22 */     super(raid, world);
/* 23 */     this.player = player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Player getPlayer() {
/* 33 */     return this.player;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 38 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 43 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 49 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 54 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\raid\RaidTriggerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */