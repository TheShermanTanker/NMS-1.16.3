/*    */ package com.destroystokyo.paper.exception;
/*    */ 
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerTabCompleteException
/*    */   extends ServerCommandException
/*    */ {
/*    */   public ServerTabCompleteException(String message, Throwable cause, Command command, CommandSender commandSender, String[] arguments) {
/* 12 */     super(message, cause, command, commandSender, arguments);
/*    */   }
/*    */   
/*    */   public ServerTabCompleteException(Throwable cause, Command command, CommandSender commandSender, String[] arguments) {
/* 16 */     super(cause, command, commandSender, arguments);
/*    */   }
/*    */   
/*    */   protected ServerTabCompleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Command command, CommandSender commandSender, String[] arguments) {
/* 20 */     super(message, cause, enableSuppression, writableStackTrace, command, commandSender, arguments);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\exception\ServerTabCompleteException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */