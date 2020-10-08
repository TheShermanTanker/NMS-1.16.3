/*    */ package org.jline.terminal.impl.jansi.win;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.fusesource.jansi.internal.Kernel32;
/*    */ import org.fusesource.jansi.internal.WindowsSupport;
/*    */ import org.jline.terminal.impl.AbstractWindowsConsoleWriter;
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
/*    */ class JansiWinConsoleWriter
/*    */   extends AbstractWindowsConsoleWriter
/*    */ {
/* 22 */   private static final long console = Kernel32.GetStdHandle(Kernel32.STD_OUTPUT_HANDLE);
/* 23 */   private final int[] writtenChars = new int[1];
/*    */ 
/*    */   
/*    */   protected void writeConsole(char[] text, int len) throws IOException {
/* 27 */     if (Kernel32.WriteConsoleW(console, text, len, this.writtenChars, 0L) == 0)
/* 28 */       throw new IOException("Failed to write to console: " + WindowsSupport.getLastErrorMessage()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\jansi\win\JansiWinConsoleWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */