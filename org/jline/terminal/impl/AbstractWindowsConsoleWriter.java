/*    */ package org.jline.terminal.impl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractWindowsConsoleWriter
/*    */   extends Writer
/*    */ {
/*    */   protected abstract void writeConsole(char[] paramArrayOfchar, int paramInt) throws IOException;
/*    */   
/*    */   public void write(char[] cbuf, int off, int len) throws IOException {
/* 20 */     char[] text = cbuf;
/* 21 */     if (off != 0) {
/* 22 */       text = new char[len];
/* 23 */       System.arraycopy(cbuf, off, text, 0, len);
/*    */     } 
/*    */     
/* 26 */     synchronized (this.lock) {
/* 27 */       writeConsole(text, len);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void flush() {}
/*    */   
/*    */   public void close() {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\AbstractWindowsConsoleWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */