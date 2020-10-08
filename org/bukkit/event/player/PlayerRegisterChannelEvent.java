/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerRegisterChannelEvent
/*    */   extends PlayerChannelEvent
/*    */ {
/*    */   public PlayerRegisterChannelEvent(@NotNull Player player, @NotNull String channel) {
/* 12 */     super(player, channel);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerRegisterChannelEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */