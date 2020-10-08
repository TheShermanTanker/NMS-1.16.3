/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerItemHeldEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel = false;
/*    */   private final int previous;
/*    */   private final int current;
/*    */   
/*    */   public PlayerItemHeldEvent(@NotNull Player player, int previous, int current) {
/* 18 */     super(player);
/* 19 */     this.previous = previous;
/* 20 */     this.current = current;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPreviousSlot() {
/* 29 */     return this.previous;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNewSlot() {
/* 38 */     return this.current;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 43 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 48 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 54 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 59 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerItemHeldEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */