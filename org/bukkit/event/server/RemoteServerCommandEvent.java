/*    */ package org.bukkit.event.server;
/*    */ 
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemoteServerCommandEvent
/*    */   extends ServerCommandEvent
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public RemoteServerCommandEvent(@NotNull CommandSender sender, @NotNull String command) {
/* 15 */     super(sender, command);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 21 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 26 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\RemoteServerCommandEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */