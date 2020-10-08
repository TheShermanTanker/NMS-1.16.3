/*    */ package org.bukkit.plugin.messaging;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChannelNotRegisteredException
/*    */   extends RuntimeException
/*    */ {
/*    */   public ChannelNotRegisteredException() {
/*  9 */     this("Attempted to send a plugin message through an unregistered channel.");
/*    */   }
/*    */   
/*    */   public ChannelNotRegisteredException(String channel) {
/* 13 */     super("Attempted to send a plugin message through the unregistered channel `" + channel + "'.");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\messaging\ChannelNotRegisteredException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */