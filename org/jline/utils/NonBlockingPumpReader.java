/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.io.Writer;
/*     */ import java.nio.CharBuffer;
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
/*     */ public class NonBlockingPumpReader
/*     */   extends NonBlockingReader
/*     */ {
/*     */   private static final int DEFAULT_BUFFER_SIZE = 4096;
/*     */   private final CharBuffer readBuffer;
/*     */   private final CharBuffer writeBuffer;
/*     */   private final Writer writer;
/*     */   private boolean closed;
/*     */   
/*     */   public NonBlockingPumpReader() {
/*  29 */     this(4096);
/*     */   }
/*     */   
/*     */   public NonBlockingPumpReader(int bufferSize) {
/*  33 */     char[] buf = new char[bufferSize];
/*  34 */     this.readBuffer = CharBuffer.wrap(buf);
/*  35 */     this.writeBuffer = CharBuffer.wrap(buf);
/*  36 */     this.writer = new NbpWriter();
/*     */     
/*  38 */     this.readBuffer.limit(0);
/*     */   }
/*     */   
/*     */   public Writer getWriter() {
/*  42 */     return this.writer;
/*     */   }
/*     */   
/*     */   private int wait(CharBuffer buffer, long timeout) throws InterruptedIOException {
/*  46 */     boolean isInfinite = (timeout <= 0L);
/*  47 */     long end = 0L;
/*  48 */     if (!isInfinite) {
/*  49 */       end = System.currentTimeMillis() + timeout;
/*     */     }
/*  51 */     while (!this.closed && !buffer.hasRemaining() && (isInfinite || timeout > 0L)) {
/*     */       
/*  53 */       notifyAll();
/*     */       try {
/*  55 */         wait(timeout);
/*  56 */       } catch (InterruptedException e) {
/*  57 */         throw new InterruptedIOException();
/*     */       } 
/*  59 */       if (!isInfinite) {
/*  60 */         timeout = end - System.currentTimeMillis();
/*     */       }
/*     */     } 
/*  63 */     return this.closed ? -1 : (
/*     */       
/*  65 */       buffer.hasRemaining() ? 0 : -2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean rewind(CharBuffer buffer, CharBuffer other) {
/*  72 */     if (buffer.position() > other.position()) {
/*  73 */       other.limit(buffer.position());
/*     */     }
/*     */     
/*  76 */     if (buffer.position() == buffer.capacity()) {
/*  77 */       buffer.rewind();
/*  78 */       buffer.limit(other.position());
/*  79 */       return true;
/*     */     } 
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean ready() {
/*  87 */     return this.readBuffer.hasRemaining();
/*     */   }
/*     */   
/*     */   public synchronized int available() {
/*  91 */     int count = this.readBuffer.remaining();
/*  92 */     if (this.writeBuffer.position() < this.readBuffer.position()) {
/*  93 */       count += this.writeBuffer.position();
/*     */     }
/*  95 */     return count;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized int read(long timeout, boolean isPeek) throws IOException {
/* 101 */     int res = wait(this.readBuffer, timeout);
/* 102 */     if (res >= 0) {
/* 103 */       res = isPeek ? this.readBuffer.get(this.readBuffer.position()) : this.readBuffer.get();
/*     */     }
/* 105 */     rewind(this.readBuffer, this.writeBuffer);
/* 106 */     return res;
/*     */   }
/*     */   
/*     */   synchronized void write(char[] cbuf, int off, int len) throws IOException {
/* 110 */     while (len > 0) {
/*     */ 
/*     */       
/* 113 */       if (wait(this.writeBuffer, 0L) == -1) {
/* 114 */         throw new ClosedException();
/*     */       }
/*     */       
/* 117 */       int count = Math.min(len, this.writeBuffer.remaining());
/* 118 */       this.writeBuffer.put(cbuf, off, count);
/* 119 */       off += count;
/* 120 */       len -= count;
/*     */       
/* 122 */       rewind(this.writeBuffer, this.readBuffer);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   synchronized void flush() {
/* 128 */     if (this.readBuffer.hasRemaining())
/*     */     {
/* 130 */       notifyAll();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void close() throws IOException {
/* 136 */     this.closed = true;
/* 137 */     notifyAll();
/*     */   }
/*     */   
/*     */   private class NbpWriter extends Writer {
/*     */     private NbpWriter() {}
/*     */     
/*     */     public void write(char[] cbuf, int off, int len) throws IOException {
/* 144 */       NonBlockingPumpReader.this.write(cbuf, off, len);
/*     */     }
/*     */ 
/*     */     
/*     */     public void flush() throws IOException {
/* 149 */       NonBlockingPumpReader.this.flush();
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 154 */       NonBlockingPumpReader.this.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\NonBlockingPumpReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */