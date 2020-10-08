/*     */ package org.jline.terminal.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.terminal.spi.Pty;
/*     */ import org.jline.utils.ClosedException;
/*     */ import org.jline.utils.NonBlocking;
/*     */ import org.jline.utils.NonBlockingInputStream;
/*     */ import org.jline.utils.NonBlockingReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PosixPtyTerminal
/*     */   extends AbstractPosixTerminal
/*     */ {
/*     */   private final InputStream in;
/*     */   private final OutputStream out;
/*     */   private final InputStream masterInput;
/*     */   private final OutputStream masterOutput;
/*     */   private final NonBlockingInputStream input;
/*     */   private final OutputStream output;
/*     */   private final NonBlockingReader reader;
/*     */   private final PrintWriter writer;
/*  37 */   private final Object lock = new Object();
/*     */   private Thread inputPumpThread;
/*     */   private Thread outputPumpThread;
/*     */   private boolean paused = true;
/*     */   
/*     */   public PosixPtyTerminal(String name, String type, Pty pty, InputStream in, OutputStream out, Charset encoding) throws IOException {
/*  43 */     this(name, type, pty, in, out, encoding, Terminal.SignalHandler.SIG_DFL);
/*     */   }
/*     */   
/*     */   public PosixPtyTerminal(String name, String type, Pty pty, InputStream in, OutputStream out, Charset encoding, Terminal.SignalHandler signalHandler) throws IOException {
/*  47 */     this(name, type, pty, in, out, encoding, signalHandler, false);
/*     */   }
/*     */   
/*     */   public PosixPtyTerminal(String name, String type, Pty pty, InputStream in, OutputStream out, Charset encoding, Terminal.SignalHandler signalHandler, boolean paused) throws IOException {
/*  51 */     super(name, type, pty, encoding, signalHandler);
/*  52 */     this.in = Objects.<InputStream>requireNonNull(in);
/*  53 */     this.out = Objects.<OutputStream>requireNonNull(out);
/*  54 */     this.masterInput = pty.getMasterInput();
/*  55 */     this.masterOutput = pty.getMasterOutput();
/*  56 */     this.input = new InputStreamWrapper(NonBlocking.nonBlocking(name, pty.getSlaveInput()));
/*  57 */     this.output = pty.getSlaveOutput();
/*  58 */     this.reader = NonBlocking.nonBlocking(name, (InputStream)this.input, encoding());
/*  59 */     this.writer = new PrintWriter(new OutputStreamWriter(this.output, encoding()));
/*  60 */     parseInfoCmp();
/*  61 */     if (!paused) {
/*  62 */       resume();
/*     */     }
/*     */   }
/*     */   
/*     */   public InputStream input() {
/*  67 */     return (InputStream)this.input;
/*     */   }
/*     */   
/*     */   public NonBlockingReader reader() {
/*  71 */     return this.reader;
/*     */   }
/*     */   
/*     */   public OutputStream output() {
/*  75 */     return this.output;
/*     */   }
/*     */   
/*     */   public PrintWriter writer() {
/*  79 */     return this.writer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  84 */     super.close();
/*  85 */     this.reader.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPauseResume() {
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void pause() {
/*  95 */     synchronized (this.lock) {
/*  96 */       this.paused = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void pause(boolean wait) throws InterruptedException {
/*     */     Thread p1;
/*     */     Thread p2;
/* 103 */     synchronized (this.lock) {
/* 104 */       this.paused = true;
/* 105 */       p1 = this.inputPumpThread;
/* 106 */       p2 = this.outputPumpThread;
/*     */     } 
/* 108 */     if (p1 != null) {
/* 109 */       p1.interrupt();
/*     */     }
/* 111 */     if (p2 != null) {
/* 112 */       p2.interrupt();
/*     */     }
/* 114 */     if (p1 != null) {
/* 115 */       p1.join();
/*     */     }
/* 117 */     if (p2 != null) {
/* 118 */       p2.join();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void resume() {
/* 124 */     synchronized (this.lock) {
/* 125 */       this.paused = false;
/* 126 */       if (this.inputPumpThread == null) {
/* 127 */         this.inputPumpThread = new Thread(this::pumpIn, toString() + " input pump thread");
/* 128 */         this.inputPumpThread.setDaemon(true);
/* 129 */         this.inputPumpThread.start();
/*     */       } 
/* 131 */       if (this.outputPumpThread == null) {
/* 132 */         this.outputPumpThread = new Thread(this::pumpOut, toString() + " output pump thread");
/* 133 */         this.outputPumpThread.setDaemon(true);
/* 134 */         this.outputPumpThread.start();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean paused() {
/* 141 */     synchronized (this.lock) {
/* 142 */       return this.paused;
/*     */     } 
/*     */   }
/*     */   
/*     */   private class InputStreamWrapper
/*     */     extends NonBlockingInputStream {
/*     */     private final NonBlockingInputStream in;
/* 149 */     private final AtomicBoolean closed = new AtomicBoolean();
/*     */     
/*     */     protected InputStreamWrapper(NonBlockingInputStream in) {
/* 152 */       this.in = in;
/*     */     }
/*     */ 
/*     */     
/*     */     public int read(long timeout, boolean isPeek) throws IOException {
/* 157 */       if (this.closed.get()) {
/* 158 */         throw new ClosedException();
/*     */       }
/* 160 */       return this.in.read(timeout, isPeek);
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 165 */       this.closed.set(true);
/*     */     }
/*     */   }
/*     */   
/*     */   private void pumpIn() {
/*     */     try {
/*     */       while (true) {
/* 172 */         synchronized (this.lock) {
/* 173 */           if (this.paused) {
/* 174 */             this.inputPumpThread = null;
/*     */             return;
/*     */           } 
/*     */         } 
/* 178 */         int b = this.in.read();
/* 179 */         if (b < 0) {
/* 180 */           this.input.close();
/*     */           break;
/*     */         } 
/* 183 */         this.masterOutput.write(b);
/* 184 */         this.masterOutput.flush();
/*     */       } 
/* 186 */     } catch (IOException e) {
/* 187 */       e.printStackTrace();
/*     */     } finally {
/* 189 */       synchronized (this.lock) {
/* 190 */         this.inputPumpThread = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void pumpOut() {
/*     */     try {
/*     */       while (true) {
/* 198 */         synchronized (this.lock) {
/* 199 */           if (this.paused) {
/* 200 */             this.outputPumpThread = null;
/*     */             return;
/*     */           } 
/*     */         } 
/* 204 */         int b = this.masterInput.read();
/* 205 */         if (b < 0) {
/* 206 */           this.input.close();
/*     */           break;
/*     */         } 
/* 209 */         this.out.write(b);
/* 210 */         this.out.flush();
/*     */       } 
/* 212 */     } catch (IOException e) {
/* 213 */       e.printStackTrace();
/*     */     } finally {
/* 215 */       synchronized (this.lock) {
/* 216 */         this.outputPumpThread = null;
/*     */       } 
/*     */     } 
/*     */     try {
/* 220 */       close();
/* 221 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\PosixPtyTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */