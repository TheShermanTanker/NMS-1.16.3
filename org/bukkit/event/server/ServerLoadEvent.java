/*    */ package org.bukkit.event.server;
/*    */ 
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerLoadEvent
/*    */   extends ServerEvent
/*    */ {
/*    */   public enum LoadType
/*    */   {
/* 15 */     STARTUP, RELOAD;
/*    */   }
/*    */   
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */ 
/*    */   
/*    */   private final LoadType type;
/*    */ 
/*    */ 
/*    */   
/*    */   public ServerLoadEvent(@NotNull LoadType type) {
/* 27 */     this.type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LoadType getType() {
/* 37 */     return this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 43 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 48 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\ServerLoadEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */