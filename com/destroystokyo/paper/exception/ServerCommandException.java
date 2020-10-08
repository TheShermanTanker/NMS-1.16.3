/*    */ package com.destroystokyo.paper.exception;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerCommandException
/*    */   extends ServerException
/*    */ {
/*    */   private final Command command;
/*    */   private final CommandSender commandSender;
/*    */   private final String[] arguments;
/*    */   
/*    */   public ServerCommandException(String message, Throwable cause, Command command, CommandSender commandSender, String[] arguments) {
/* 18 */     super(message, cause);
/* 19 */     this.commandSender = (CommandSender)Preconditions.checkNotNull(commandSender, "commandSender");
/* 20 */     this.arguments = (String[])Preconditions.checkNotNull(arguments, "arguments");
/* 21 */     this.command = (Command)Preconditions.checkNotNull(command, "command");
/*    */   }
/*    */   
/*    */   public ServerCommandException(Throwable cause, Command command, CommandSender commandSender, String[] arguments) {
/* 25 */     super(cause);
/* 26 */     this.commandSender = (CommandSender)Preconditions.checkNotNull(commandSender, "commandSender");
/* 27 */     this.arguments = (String[])Preconditions.checkNotNull(arguments, "arguments");
/* 28 */     this.command = (Command)Preconditions.checkNotNull(command, "command");
/*    */   }
/*    */   
/*    */   protected ServerCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Command command, CommandSender commandSender, String[] arguments) {
/* 32 */     super(message, cause, enableSuppression, writableStackTrace);
/* 33 */     this.commandSender = (CommandSender)Preconditions.checkNotNull(commandSender, "commandSender");
/* 34 */     this.arguments = (String[])Preconditions.checkNotNull(arguments, "arguments");
/* 35 */     this.command = (Command)Preconditions.checkNotNull(command, "command");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Command getCommand() {
/* 44 */     return this.command;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CommandSender getCommandSender() {
/* 53 */     return this.commandSender;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] getArguments() {
/* 62 */     return this.arguments;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\exception\ServerCommandException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */