/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.ByteBuffer;
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
/*     */ public class NonBlockingPumpInputStream
/*     */   extends NonBlockingInputStream
/*     */ {
/*     */   private static final int DEFAULT_BUFFER_SIZE = 4096;
/*     */   private final ByteBuffer readBuffer;
/*     */   private final ByteBuffer writeBuffer;
/*     */   private final OutputStream output;
/*     */   private boolean closed;
/*     */   private IOException ioException;
/*     */   
/*     */   public NonBlockingPumpInputStream() {
/*  31 */     this(4096);
/*     */   }
/*     */   
/*     */   public NonBlockingPumpInputStream(int bufferSize) {
/*  35 */     byte[] buf = new byte[bufferSize];
/*  36 */     this.readBuffer = ByteBuffer.wrap(buf);
/*  37 */     this.writeBuffer = ByteBuffer.wrap(buf);
/*  38 */     this.output = new NbpOutputStream();
/*     */     
/*  40 */     this.readBuffer.limit(0);
/*     */   }
/*     */   
/*     */   public OutputStream getOutputStream() {
/*  44 */     return this.output;
/*     */   }
/*     */   
/*     */   private int wait(ByteBuffer buffer, long timeout) throws IOException {
/*  48 */     boolean isInfinite = (timeout <= 0L);
/*  49 */     long end = 0L;
/*  50 */     if (!isInfinite) {
/*  51 */       end = System.currentTimeMillis() + timeout;
/*     */     }
/*  53 */     while (!this.closed && !buffer.hasRemaining() && (isInfinite || timeout > 0L)) {
/*     */       
/*  55 */       notifyAll();
/*     */       try {
/*  57 */         wait(timeout);
/*  58 */         checkIoException();
/*  59 */       } catch (InterruptedException e) {
/*  60 */         checkIoException();
/*  61 */         throw new InterruptedIOException();
/*     */       } 
/*  63 */       if (!isInfinite) {
/*  64 */         timeout = end - System.currentTimeMillis();
/*     */       }
/*     */     } 
/*  67 */     return buffer.hasRemaining() ? 0 : (this.closed ? -1 : -2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean rewind(ByteBuffer buffer, ByteBuffer other) {
/*  76 */     if (buffer.position() > other.position()) {
/*  77 */       other.limit(buffer.position());
/*     */     }
/*     */     
/*  80 */     if (buffer.position() == buffer.capacity()) {
/*  81 */       buffer.rewind();
/*  82 */       buffer.limit(other.position());
/*  83 */       return true;
/*     */     } 
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized int available() {
/*  90 */     int count = this.readBuffer.remaining();
/*  91 */     if (this.writeBuffer.position() < this.readBuffer.position()) {
/*  92 */       count += this.writeBuffer.position();
/*     */     }
/*  94 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized int read(long timeout, boolean isPeek) throws IOException {
/*  99 */     checkIoException();
/*     */     
/* 101 */     int res = wait(this.readBuffer, timeout);
/* 102 */     if (res >= 0) {
/* 103 */       res = this.readBuffer.get() & 0xFF;
/*     */     }
/* 105 */     rewind(this.readBuffer, this.writeBuffer);
/* 106 */     return res;
/*     */   }
/*     */   
/*     */   public synchronized void setIoException(IOException exception) {
/* 110 */     this.ioException = exception;
/* 111 */     notifyAll();
/*     */   }
/*     */   
/*     */   protected synchronized void checkIoException() throws IOException {
/* 115 */     if (this.ioException != null) {
/* 116 */       throw this.ioException;
/*     */     }
/*     */   }
/*     */   
/*     */   synchronized void write(byte[] cbuf, int off, int len) throws IOException {
/* 121 */     while (len > 0) {
/*     */ 
/*     */       
/* 124 */       if (wait(this.writeBuffer, 0L) == -1) {
/* 125 */         throw new ClosedException();
/*     */       }
/*     */       
/* 128 */       int count = Math.min(len, this.writeBuffer.remaining());
/* 129 */       this.writeBuffer.put(cbuf, off, count);
/* 130 */       off += count;
/* 131 */       len -= count;
/*     */       
/* 133 */       rewind(this.writeBuffer, this.readBuffer);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   synchronized void flush() {
/* 139 */     if (this.readBuffer.hasRemaining())
/*     */     {
/* 141 */       notifyAll();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void close() throws IOException {
/* 147 */     this.closed = true;
/* 148 */     notifyAll();
/*     */   }
/*     */   
/*     */   private class NbpOutputStream extends OutputStream {
/*     */     private NbpOutputStream() {}
/*     */     
/*     */     public void write(int b) throws IOException {
/* 155 */       NonBlockingPumpInputStream.this.write(new byte[] { (byte)b }, 0, 1);
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(byte[] cbuf, int off, int len) throws IOException {
/* 160 */       NonBlockingPumpInputStream.this.write(cbuf, off, len);
/*     */     }
/*     */ 
/*     */     
/*     */     public void flush() throws IOException {
/* 165 */       NonBlockingPumpInputStream.this.flush();
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 170 */       NonBlockingPumpInputStream.this.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\NonBlockingPumpInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */