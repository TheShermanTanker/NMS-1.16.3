/*     */ package org.jline.terminal.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.function.IntConsumer;
/*     */ import org.jline.terminal.Cursor;
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
/*     */ public class ExternalTerminal
/*     */   extends LineDisciplineTerminal
/*     */ {
/*  33 */   protected final AtomicBoolean closed = new AtomicBoolean();
/*     */   protected final InputStream masterInput;
/*  35 */   protected final Object lock = new Object();
/*     */   
/*     */   protected boolean paused = true;
/*     */   
/*     */   protected Thread pumpThread;
/*     */ 
/*     */   
/*     */   public ExternalTerminal(String name, String type, InputStream masterInput, OutputStream masterOutput, Charset encoding) throws IOException {
/*  43 */     this(name, type, masterInput, masterOutput, encoding, Terminal.SignalHandler.SIG_DFL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExternalTerminal(String name, String type, InputStream masterInput, OutputStream masterOutput, Charset encoding, Terminal.SignalHandler signalHandler) throws IOException {
/*  51 */     this(name, type, masterInput, masterOutput, encoding, signalHandler, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExternalTerminal(String name, String type, InputStream masterInput, OutputStream masterOutput, Charset encoding, Terminal.SignalHandler signalHandler, boolean paused) throws IOException {
/*  60 */     super(name, type, masterOutput, encoding, signalHandler);
/*  61 */     this.masterInput = masterInput;
/*  62 */     if (!paused) {
/*  63 */       resume();
/*     */     }
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  68 */     if (this.closed.compareAndSet(false, true)) {
/*  69 */       pause();
/*  70 */       super.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPauseResume() {
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void pause() {
/*  81 */     synchronized (this.lock) {
/*  82 */       this.paused = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void pause(boolean wait) throws InterruptedException {
/*     */     Thread p;
/*  89 */     synchronized (this.lock) {
/*  90 */       this.paused = true;
/*  91 */       p = this.pumpThread;
/*     */     } 
/*  93 */     if (p != null) {
/*  94 */       p.interrupt();
/*  95 */       p.join();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void resume() {
/* 101 */     synchronized (this.lock) {
/* 102 */       this.paused = false;
/* 103 */       if (this.pumpThread == null) {
/* 104 */         this.pumpThread = new Thread(this::pump, toString() + " input pump thread");
/* 105 */         this.pumpThread.setDaemon(true);
/* 106 */         this.pumpThread.start();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean paused() {
/* 113 */     synchronized (this.lock) {
/* 114 */       return this.paused;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void pump() {
/*     */     try {
/* 120 */       byte[] buf = new byte[1024];
/*     */       while (true) {
/* 122 */         int c = this.masterInput.read(buf);
/* 123 */         if (c >= 0) {
/* 124 */           processInputBytes(buf, 0, c);
/*     */         }
/* 126 */         if (c < 0 || this.closed.get()) {
/*     */           break;
/*     */         }
/* 129 */         synchronized (this.lock) {
/* 130 */           if (this.paused) {
/* 131 */             this.pumpThread = null;
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/* 136 */     } catch (IOException e) {
/* 137 */       processIOException(e);
/*     */     } finally {
/* 139 */       synchronized (this.lock) {
/* 140 */         this.pumpThread = null;
/*     */       } 
/*     */     } 
/*     */     try {
/* 144 */       this.slaveInput.close();
/* 145 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Cursor getCursorPosition(IntConsumer discarded) {
/* 152 */     return CursorSupport.getCursorPosition(this, discarded);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\ExternalTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */