/*    */ package com.destroystokyo.paper.exception;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerPluginException
/*    */   extends ServerException
/*    */ {
/*    */   private final Plugin responsiblePlugin;
/*    */   
/*    */   public ServerPluginException(String message, Throwable cause, Plugin responsiblePlugin) {
/* 14 */     super(message, cause);
/* 15 */     this.responsiblePlugin = (Plugin)Preconditions.checkNotNull(responsiblePlugin, "responsiblePlugin");
/*    */   }
/*    */   
/*    */   public ServerPluginException(Throwable cause, Plugin responsiblePlugin) {
/* 19 */     super(cause);
/* 20 */     this.responsiblePlugin = (Plugin)Preconditions.checkNotNull(responsiblePlugin, "responsiblePlugin");
/*    */   }
/*    */   
/*    */   protected ServerPluginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Plugin responsiblePlugin) {
/* 24 */     super(message, cause, enableSuppression, writableStackTrace);
/* 25 */     this.responsiblePlugin = (Plugin)Preconditions.checkNotNull(responsiblePlugin, "responsiblePlugin");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Plugin getResponsiblePlugin() {
/* 36 */     return this.responsiblePlugin;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\exception\ServerPluginException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */