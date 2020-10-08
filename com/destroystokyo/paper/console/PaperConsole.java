/*    */ package com.destroystokyo.paper.console;
/*    */ 
/*    */ import java.nio.file.Paths;
/*    */ import net.minecraft.server.v1_16_R2.DedicatedServer;
/*    */ import net.minecrell.terminalconsole.SimpleTerminalConsole;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.command.ConsoleCommandCompleter;
/*    */ import org.jline.reader.Completer;
/*    */ import org.jline.reader.LineReader;
/*    */ import org.jline.reader.LineReaderBuilder;
/*    */ 
/*    */ public final class PaperConsole
/*    */   extends SimpleTerminalConsole {
/*    */   public PaperConsole(DedicatedServer server) {
/* 14 */     this.server = server;
/*    */   }
/*    */   private final DedicatedServer server;
/*    */   
/*    */   protected LineReader buildReader(LineReaderBuilder builder) {
/* 19 */     return super.buildReader(builder
/* 20 */         .appName("Paper")
/* 21 */         .variable("history-file", Paths.get(".console_history", new String[0]))
/* 22 */         .completer((Completer)new ConsoleCommandCompleter(this.server)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isRunning() {
/* 28 */     return (!this.server.isStopped() && this.server.isRunning());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void runCommand(String command) {
/* 33 */     this.server.issueCommand(command, this.server.getServerCommandListener());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void shutdown() {
/* 38 */     this.server.safeShutdown(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\console\PaperConsole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */