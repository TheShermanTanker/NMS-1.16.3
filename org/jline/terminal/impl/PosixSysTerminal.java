/*    */ package org.jline.terminal.impl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.PrintWriter;
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.jline.terminal.Terminal;
/*    */ import org.jline.terminal.spi.Pty;
/*    */ import org.jline.utils.NonBlocking;
/*    */ import org.jline.utils.NonBlockingInputStream;
/*    */ import org.jline.utils.NonBlockingReader;
/*    */ import org.jline.utils.ShutdownHooks;
/*    */ import org.jline.utils.Signals;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PosixSysTerminal
/*    */   extends AbstractPosixTerminal
/*    */ {
/*    */   protected final NonBlockingInputStream input;
/*    */   protected final OutputStream output;
/*    */   protected final NonBlockingReader reader;
/*    */   protected final PrintWriter writer;
/* 34 */   protected final Map<Terminal.Signal, Object> nativeHandlers = new HashMap<>();
/*    */   
/*    */   protected final ShutdownHooks.Task closer;
/*    */   
/*    */   public PosixSysTerminal(String name, String type, Pty pty, Charset encoding, boolean nativeSignals, Terminal.SignalHandler signalHandler) throws IOException {
/* 39 */     super(name, type, pty, encoding, signalHandler);
/* 40 */     this.input = NonBlocking.nonBlocking(getName(), pty.getSlaveInput());
/* 41 */     this.output = pty.getSlaveOutput();
/* 42 */     this.reader = NonBlocking.nonBlocking(getName(), (InputStream)this.input, encoding());
/* 43 */     this.writer = new PrintWriter(new OutputStreamWriter(this.output, encoding()));
/* 44 */     parseInfoCmp();
/* 45 */     if (nativeSignals) {
/* 46 */       for (Terminal.Signal signal : Terminal.Signal.values()) {
/* 47 */         if (signalHandler == Terminal.SignalHandler.SIG_DFL) {
/* 48 */           this.nativeHandlers.put(signal, Signals.registerDefault(signal.name()));
/*    */         } else {
/* 50 */           this.nativeHandlers.put(signal, Signals.register(signal.name(), () -> raise(signal)));
/*    */         } 
/*    */       } 
/*    */     }
/* 54 */     this.closer = this::close;
/* 55 */     ShutdownHooks.add(this.closer);
/*    */   }
/*    */ 
/*    */   
/*    */   public Terminal.SignalHandler handle(Terminal.Signal signal, Terminal.SignalHandler handler) {
/* 60 */     Terminal.SignalHandler prev = super.handle(signal, handler);
/* 61 */     if (prev != handler) {
/* 62 */       if (handler == Terminal.SignalHandler.SIG_DFL) {
/* 63 */         Signals.registerDefault(signal.name());
/*    */       } else {
/* 65 */         Signals.register(signal.name(), () -> raise(signal));
/*    */       } 
/*    */     }
/* 68 */     return prev;
/*    */   }
/*    */   
/*    */   public NonBlockingReader reader() {
/* 72 */     return this.reader;
/*    */   }
/*    */   
/*    */   public PrintWriter writer() {
/* 76 */     return this.writer;
/*    */   }
/*    */ 
/*    */   
/*    */   public InputStream input() {
/* 81 */     return (InputStream)this.input;
/*    */   }
/*    */ 
/*    */   
/*    */   public OutputStream output() {
/* 86 */     return this.output;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 91 */     ShutdownHooks.remove(this.closer);
/* 92 */     for (Map.Entry<Terminal.Signal, Object> entry : this.nativeHandlers.entrySet()) {
/* 93 */       Signals.unregister(((Terminal.Signal)entry.getKey()).name(), entry.getValue());
/*    */     }
/* 95 */     super.close();
/*    */     
/* 97 */     this.reader.shutdown();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\PosixSysTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */