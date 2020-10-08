/*    */ package com.destroystokyo.paper.exception;
/*    */ 
/*    */ import com.destroystokyo.paper.event.server.ServerExceptionEvent;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.Event;
/*    */ 
/*    */ 
/*    */ public class ServerInternalException
/*    */   extends ServerException
/*    */ {
/*    */   public ServerInternalException(String message) {
/* 12 */     super(message);
/*    */   }
/*    */   
/*    */   public ServerInternalException(String message, Throwable cause) {
/* 16 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public ServerInternalException(Throwable cause) {
/* 20 */     super(cause);
/*    */   }
/*    */   
/*    */   protected ServerInternalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
/* 24 */     super(message, cause, enableSuppression, writableStackTrace);
/*    */   }
/*    */   
/*    */   public static void reportInternalException(Throwable cause) {
/*    */     try {
/* 29 */       Bukkit.getPluginManager().callEvent((Event)new ServerExceptionEvent(new ServerInternalException(cause)));
/*    */     }
/* 31 */     catch (Throwable t) {
/* 32 */       t.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\exception\ServerInternalException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */