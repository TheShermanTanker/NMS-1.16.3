/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PlayerChannelEvent
/*    */   extends PlayerEvent
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final String channel;
/*    */   
/*    */   public PlayerChannelEvent(@NotNull Player player, @NotNull String channel) {
/* 16 */     super(player);
/* 17 */     this.channel = channel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public final String getChannel() {
/* 22 */     return this.channel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 28 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 33 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerChannelEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */