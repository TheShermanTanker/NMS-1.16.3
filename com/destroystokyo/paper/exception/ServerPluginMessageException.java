/*    */ package com.destroystokyo.paper.exception;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerPluginMessageException
/*    */   extends ServerPluginException
/*    */ {
/*    */   private final Player player;
/*    */   private final String channel;
/*    */   private final byte[] data;
/*    */   
/*    */   public ServerPluginMessageException(String message, Throwable cause, Plugin responsiblePlugin, Player player, String channel, byte[] data) {
/* 18 */     super(message, cause, responsiblePlugin);
/* 19 */     this.player = (Player)Preconditions.checkNotNull(player, "player");
/* 20 */     this.channel = (String)Preconditions.checkNotNull(channel, "channel");
/* 21 */     this.data = (byte[])Preconditions.checkNotNull(data, "data");
/*    */   }
/*    */   
/*    */   public ServerPluginMessageException(Throwable cause, Plugin responsiblePlugin, Player player, String channel, byte[] data) {
/* 25 */     super(cause, responsiblePlugin);
/* 26 */     this.player = (Player)Preconditions.checkNotNull(player, "player");
/* 27 */     this.channel = (String)Preconditions.checkNotNull(channel, "channel");
/* 28 */     this.data = (byte[])Preconditions.checkNotNull(data, "data");
/*    */   }
/*    */   
/*    */   protected ServerPluginMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Plugin responsiblePlugin, Player player, String channel, byte[] data) {
/* 32 */     super(message, cause, enableSuppression, writableStackTrace, responsiblePlugin);
/* 33 */     this.player = (Player)Preconditions.checkNotNull(player, "player");
/* 34 */     this.channel = (String)Preconditions.checkNotNull(channel, "channel");
/* 35 */     this.data = (byte[])Preconditions.checkNotNull(data, "data");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getChannel() {
/* 44 */     return this.channel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] getData() {
/* 53 */     return this.data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Player getPlayer() {
/* 62 */     return this.player;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\exception\ServerPluginMessageException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */