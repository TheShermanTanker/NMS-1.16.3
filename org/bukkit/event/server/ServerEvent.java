/*    */ package org.bukkit.event.server;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.Event;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ServerEvent
/*    */   extends Event
/*    */ {
/*    */   public ServerEvent() {
/* 12 */     super(!Bukkit.isPrimaryThread());
/*    */   }
/*    */   
/*    */   public ServerEvent(boolean isAsync) {
/* 16 */     super(isAsync);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\ServerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */