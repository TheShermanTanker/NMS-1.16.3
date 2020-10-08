/*    */ package org.bukkit.command;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandException
/*    */   extends RuntimeException
/*    */ {
/*    */   public CommandException() {}
/*    */   
/*    */   public CommandException(String msg) {
/* 22 */     super(msg);
/*    */   }
/*    */   
/*    */   public CommandException(String msg, Throwable cause) {
/* 26 */     super(msg, cause);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\CommandException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */