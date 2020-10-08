/*    */ package com.destroystokyo.paper.console;
/*    */ 
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.command.CraftConsoleCommandSender;
/*    */ 
/*    */ public class TerminalConsoleCommandSender
/*    */   extends CraftConsoleCommandSender {
/*  9 */   private static final Logger LOGGER = LogManager.getRootLogger();
/*    */ 
/*    */ 
/*    */   
/*    */   public void sendRawMessage(String message) {
/* 14 */     LOGGER.info(message);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\console\TerminalConsoleCommandSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */