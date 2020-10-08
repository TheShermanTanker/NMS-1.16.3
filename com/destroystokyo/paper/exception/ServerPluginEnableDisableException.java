/*    */ package com.destroystokyo.paper.exception;
/*    */ 
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ public class ServerPluginEnableDisableException
/*    */   extends ServerPluginException
/*    */ {
/*    */   public ServerPluginEnableDisableException(String message, Throwable cause, Plugin responsiblePlugin) {
/* 10 */     super(message, cause, responsiblePlugin);
/*    */   }
/*    */   
/*    */   public ServerPluginEnableDisableException(Throwable cause, Plugin responsiblePlugin) {
/* 14 */     super(cause, responsiblePlugin);
/*    */   }
/*    */   
/*    */   protected ServerPluginEnableDisableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Plugin responsiblePlugin) {
/* 18 */     super(message, cause, enableSuppression, writableStackTrace, responsiblePlugin);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\exception\ServerPluginEnableDisableException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */