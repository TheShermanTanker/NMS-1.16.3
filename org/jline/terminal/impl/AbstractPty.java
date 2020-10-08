/*    */ package org.jline.terminal.impl;
/*    */ 
/*    */ import java.io.IOError;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InterruptedIOException;
/*    */ import org.jline.terminal.Attributes;
/*    */ import org.jline.terminal.spi.Pty;
/*    */ import org.jline.utils.NonBlockingInputStream;
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
/*    */ public abstract class AbstractPty
/*    */   implements Pty
/*    */ {
/*    */   private Attributes current;
/*    */   
/*    */   public void setAttr(Attributes attr) throws IOException {
/* 28 */     this.current = new Attributes(attr);
/* 29 */     doSetAttr(attr);
/*    */   }
/*    */ 
/*    */   
/*    */   public InputStream getSlaveInput() throws IOException {
/* 34 */     InputStream si = doGetSlaveInput();
/* 35 */     if (Boolean.parseBoolean(System.getProperty("org.jline.terminal.pty.nonBlockingReads", "true"))) {
/* 36 */       return (InputStream)new PtyInputStream(si);
/*    */     }
/* 38 */     return si;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void checkInterrupted() throws InterruptedIOException {
/* 47 */     if (Thread.interrupted())
/* 48 */       throw new InterruptedIOException(); 
/*    */   }
/*    */   protected abstract void doSetAttr(Attributes paramAttributes) throws IOException;
/*    */   
/*    */   protected abstract InputStream doGetSlaveInput() throws IOException;
/*    */   
/* 54 */   class PtyInputStream extends NonBlockingInputStream { int c = 0; final InputStream in;
/*    */     
/*    */     PtyInputStream(InputStream in) {
/* 57 */       this.in = in;
/*    */     }
/*    */ 
/*    */     
/*    */     public int read(long timeout, boolean isPeek) throws IOException {
/* 62 */       AbstractPty.this.checkInterrupted();
/* 63 */       if (this.c != 0) {
/* 64 */         int r = this.c;
/* 65 */         if (!isPeek) {
/* 66 */           this.c = 0;
/*    */         }
/* 68 */         return r;
/*    */       } 
/* 70 */       setNonBlocking();
/* 71 */       long start = System.currentTimeMillis();
/*    */       while (true) {
/* 73 */         int r = this.in.read();
/* 74 */         if (r >= 0) {
/* 75 */           if (isPeek) {
/* 76 */             this.c = r;
/*    */           }
/* 78 */           return r;
/*    */         } 
/* 80 */         AbstractPty.this.checkInterrupted();
/* 81 */         long cur = System.currentTimeMillis();
/* 82 */         if (timeout > 0L && cur - start > timeout) {
/* 83 */           return -2;
/*    */         }
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/*    */     private void setNonBlocking() {
/* 90 */       if (AbstractPty.this.current == null || AbstractPty.this
/* 91 */         .current.getControlChar(Attributes.ControlChar.VMIN) != 0 || AbstractPty.this
/* 92 */         .current.getControlChar(Attributes.ControlChar.VTIME) != 1)
/*    */         try {
/* 94 */           Attributes attr = AbstractPty.this.getAttr();
/* 95 */           attr.setControlChar(Attributes.ControlChar.VMIN, 0);
/* 96 */           attr.setControlChar(Attributes.ControlChar.VTIME, 1);
/* 97 */           AbstractPty.this.setAttr(attr);
/* 98 */         } catch (IOException e) {
/* 99 */           throw new IOError(e);
/*    */         }  
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\AbstractPty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */