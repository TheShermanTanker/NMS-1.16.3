/*    */ package org.bukkit.event.command;
/*    */ 
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnknownCommandEvent
/*    */   extends Event
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   @NotNull
/*    */   private CommandSender sender;
/*    */   
/*    */   public UnknownCommandEvent(@NotNull CommandSender sender, @NotNull String commandLine, @Nullable String message) {
/* 20 */     super(false);
/* 21 */     this.sender = sender;
/* 22 */     this.commandLine = commandLine;
/* 23 */     this.message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   private String commandLine;
/*    */   @Nullable
/*    */   private String message;
/*    */   
/*    */   @NotNull
/*    */   public CommandSender getSender() {
/* 34 */     return this.sender;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getCommandLine() {
/* 45 */     return this.commandLine;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getMessage() {
/* 56 */     return this.message;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMessage(@Nullable String message) {
/* 68 */     this.message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 74 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 79 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\command\UnknownCommandEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */