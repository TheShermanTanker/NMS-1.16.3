/*    */ package org.bukkit.plugin.messaging;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChannelNameTooLongException
/*    */   extends RuntimeException
/*    */ {
/*    */   public ChannelNameTooLongException() {
/*  9 */     super("Attempted to send a Plugin Message to a channel that was too large. The maximum length a channel may be is " + Messenger.MAX_CHANNEL_SIZE + " chars.");
/*    */   }
/*    */   
/*    */   public ChannelNameTooLongException(String channel) {
/* 13 */     super("Attempted to send a Plugin Message to a channel that was too large. The maximum length a channel may be is " + Messenger.MAX_CHANNEL_SIZE + " chars (attempted " + channel.length() + " - '" + channel + ".");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\messaging\ChannelNameTooLongException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */