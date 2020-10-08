/*    */ package org.jline.terminal.impl;
/*    */ 
/*    */ import java.io.IOError;
/*    */ import java.io.IOException;
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.Objects;
/*    */ import java.util.function.IntConsumer;
/*    */ import org.jline.terminal.Attributes;
/*    */ import org.jline.terminal.Cursor;
/*    */ import org.jline.terminal.Size;
/*    */ import org.jline.terminal.Terminal;
/*    */ import org.jline.terminal.spi.Pty;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractPosixTerminal
/*    */   extends AbstractTerminal
/*    */ {
/*    */   protected final Pty pty;
/*    */   protected final Attributes originalAttributes;
/*    */   
/*    */   public AbstractPosixTerminal(String name, String type, Pty pty) throws IOException {
/* 28 */     this(name, type, pty, null, Terminal.SignalHandler.SIG_DFL);
/*    */   }
/*    */   
/*    */   public AbstractPosixTerminal(String name, String type, Pty pty, Charset encoding, Terminal.SignalHandler signalHandler) throws IOException {
/* 32 */     super(name, type, encoding, signalHandler);
/* 33 */     Objects.requireNonNull(pty);
/* 34 */     this.pty = pty;
/* 35 */     this.originalAttributes = this.pty.getAttr();
/*    */   }
/*    */   
/*    */   public Pty getPty() {
/* 39 */     return this.pty;
/*    */   }
/*    */   
/*    */   public Attributes getAttributes() {
/*    */     try {
/* 44 */       return this.pty.getAttr();
/* 45 */     } catch (IOException e) {
/* 46 */       throw new IOError(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setAttributes(Attributes attr) {
/*    */     try {
/* 52 */       this.pty.setAttr(attr);
/* 53 */     } catch (IOException e) {
/* 54 */       throw new IOError(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public Size getSize() {
/*    */     try {
/* 60 */       return this.pty.getSize();
/* 61 */     } catch (IOException e) {
/* 62 */       throw new IOError(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setSize(Size size) {
/*    */     try {
/* 68 */       this.pty.setSize(size);
/* 69 */     } catch (IOException e) {
/* 70 */       throw new IOError(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 75 */     super.close();
/* 76 */     this.pty.setAttr(this.originalAttributes);
/* 77 */     this.pty.close();
/*    */   }
/*    */ 
/*    */   
/*    */   public Cursor getCursorPosition(IntConsumer discarded) {
/* 82 */     return CursorSupport.getCursorPosition(this, discarded);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\AbstractPosixTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */