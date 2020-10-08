/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerKickEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private String leaveMessage;
/*    */   private String kickReason;
/*    */   private Boolean cancel;
/*    */   
/*    */   public PlayerKickEvent(@NotNull Player playerKicked, @NotNull String kickReason, @NotNull String leaveMessage) {
/* 18 */     super(playerKicked);
/* 19 */     this.kickReason = kickReason;
/* 20 */     this.leaveMessage = leaveMessage;
/* 21 */     this.cancel = Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getReason() {
/* 31 */     return this.kickReason;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getLeaveMessage() {
/* 41 */     return this.leaveMessage;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 46 */     return this.cancel.booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 51 */     this.cancel = Boolean.valueOf(cancel);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setReason(@NotNull String kickReason) {
/* 60 */     this.kickReason = kickReason;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLeaveMessage(@NotNull String leaveMessage) {
/* 69 */     this.leaveMessage = leaveMessage;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 75 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 80 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerKickEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */