/*     */ package net.minecrell.terminalconsole;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.jline.reader.EndOfFileException;
/*     */ import org.jline.reader.LineReader;
/*     */ import org.jline.reader.LineReaderBuilder;
/*     */ import org.jline.reader.UserInterruptException;
/*     */ import org.jline.terminal.Terminal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SimpleTerminalConsole
/*     */ {
/*     */   protected abstract boolean isRunning();
/*     */   
/*     */   protected abstract void runCommand(String paramString);
/*     */   
/*     */   protected abstract void shutdown();
/*     */   
/*     */   protected void processInput(String input) {
/*  85 */     String command = input.trim();
/*  86 */     if (!command.isEmpty()) {
/*  87 */       runCommand(command);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LineReader buildReader(LineReaderBuilder builder) {
/* 117 */     LineReader reader = builder.build();
/* 118 */     reader.setOpt(LineReader.Option.DISABLE_EVENT_EXPANSION);
/* 119 */     reader.unsetOpt(LineReader.Option.INSERT_TAB);
/* 120 */     return reader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/*     */     try {
/* 139 */       Terminal terminal = TerminalConsoleAppender.getTerminal();
/* 140 */       if (terminal != null) {
/* 141 */         readCommands(terminal);
/*     */       } else {
/* 143 */         readCommands(System.in);
/*     */       } 
/* 145 */     } catch (IOException e) {
/* 146 */       LogManager.getLogger("TerminalConsole").error("Failed to read console input", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readCommands(Terminal terminal) {
/* 151 */     LineReader reader = buildReader(LineReaderBuilder.builder().terminal(terminal));
/* 152 */     TerminalConsoleAppender.setReader(reader);
/*     */ 
/*     */     
/*     */     try {
/* 156 */       while (isRunning()) {
/*     */         String line; try {
/* 158 */           line = reader.readLine("> ");
/* 159 */         } catch (EndOfFileException ignored) {
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/* 164 */         if (line == null) {
/*     */           break;
/*     */         }
/*     */         
/* 168 */         processInput(line);
/*     */       } 
/* 170 */     } catch (UserInterruptException e) {
/* 171 */       String line; shutdown();
/*     */     } finally {
/* 173 */       TerminalConsoleAppender.setReader(null);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readCommands(InputStream in) throws IOException {
/* 178 */     try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
/*     */       String line;
/* 180 */       while (isRunning() && (line = reader.readLine()) != null)
/* 181 */         processInput(line); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecrell\terminalconsole\SimpleTerminalConsole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */