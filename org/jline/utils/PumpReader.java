/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.io.Reader;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.nio.charset.CoderResult;
/*     */ import java.nio.charset.CodingErrorAction;
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
/*     */ public class PumpReader
/*     */   extends Reader
/*     */ {
/*     */   private static final int EOF = -1;
/*     */   private static final int DEFAULT_BUFFER_SIZE = 4096;
/*     */   private final CharBuffer readBuffer;
/*     */   private final CharBuffer writeBuffer;
/*     */   private final Writer writer;
/*     */   private boolean closed;
/*     */   
/*     */   public PumpReader() {
/*  35 */     this(4096);
/*     */   }
/*     */   
/*     */   public PumpReader(int bufferSize) {
/*  39 */     char[] buf = new char[bufferSize];
/*  40 */     this.readBuffer = CharBuffer.wrap(buf);
/*  41 */     this.writeBuffer = CharBuffer.wrap(buf);
/*  42 */     this.writer = new Writer(this);
/*     */ 
/*     */     
/*  45 */     this.readBuffer.limit(0);
/*     */   }
/*     */   
/*     */   public java.io.Writer getWriter() {
/*  49 */     return this.writer;
/*     */   }
/*     */   
/*     */   public java.io.InputStream createInputStream(Charset charset) {
/*  53 */     return new InputStream(this, charset);
/*     */   }
/*     */   
/*     */   private boolean wait(CharBuffer buffer) throws InterruptedIOException {
/*  57 */     if (this.closed) {
/*  58 */       return false;
/*     */     }
/*     */     
/*  61 */     while (!buffer.hasRemaining()) {
/*     */       
/*  63 */       notifyAll();
/*     */       
/*     */       try {
/*  66 */         wait();
/*  67 */       } catch (InterruptedException e) {
/*  68 */         throw new InterruptedIOException();
/*     */       } 
/*     */       
/*  71 */       if (this.closed) {
/*  72 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean waitForInput() throws InterruptedIOException {
/*  86 */     return wait(this.readBuffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void waitForBufferSpace() throws InterruptedIOException, ClosedException {
/*  97 */     if (!wait(this.writeBuffer)) {
/*  98 */       throw new ClosedException();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean rewind(CharBuffer buffer, CharBuffer other) {
/* 104 */     if (buffer.position() > other.position()) {
/* 105 */       other.limit(buffer.position());
/*     */     }
/*     */ 
/*     */     
/* 109 */     if (buffer.position() == buffer.capacity()) {
/* 110 */       buffer.rewind();
/* 111 */       buffer.limit(other.position());
/* 112 */       return true;
/*     */     } 
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean rewindReadBuffer() {
/* 125 */     return (rewind(this.readBuffer, this.writeBuffer) && this.readBuffer.hasRemaining());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rewindWriteBuffer() {
/* 133 */     rewind(this.writeBuffer, this.readBuffer);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean ready() {
/* 138 */     return this.readBuffer.hasRemaining();
/*     */   }
/*     */   
/*     */   public synchronized int available() {
/* 142 */     int count = this.readBuffer.remaining();
/* 143 */     if (this.writeBuffer.position() < this.readBuffer.position()) {
/* 144 */       count += this.writeBuffer.position();
/*     */     }
/* 146 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized int read() throws IOException {
/* 151 */     if (!waitForInput()) {
/* 152 */       return -1;
/*     */     }
/*     */     
/* 155 */     int b = this.readBuffer.get();
/* 156 */     rewindReadBuffer();
/* 157 */     return b;
/*     */   }
/*     */   
/*     */   private int copyFromBuffer(char[] cbuf, int off, int len) {
/* 161 */     len = Math.min(len, this.readBuffer.remaining());
/* 162 */     this.readBuffer.get(cbuf, off, len);
/* 163 */     return len;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized int read(char[] cbuf, int off, int len) throws IOException {
/* 168 */     if (len == 0) {
/* 169 */       return 0;
/*     */     }
/*     */     
/* 172 */     if (!waitForInput()) {
/* 173 */       return -1;
/*     */     }
/*     */     
/* 176 */     int count = copyFromBuffer(cbuf, off, len);
/* 177 */     if (rewindReadBuffer() && count < len) {
/* 178 */       count += copyFromBuffer(cbuf, off + count, len - count);
/* 179 */       rewindReadBuffer();
/*     */     } 
/*     */     
/* 182 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(CharBuffer target) throws IOException {
/* 187 */     if (!target.hasRemaining()) {
/* 188 */       return 0;
/*     */     }
/*     */     
/* 191 */     if (!waitForInput()) {
/* 192 */       return -1;
/*     */     }
/*     */     
/* 195 */     int count = this.readBuffer.read(target);
/* 196 */     if (rewindReadBuffer() && target.hasRemaining()) {
/* 197 */       count += this.readBuffer.read(target);
/* 198 */       rewindReadBuffer();
/*     */     } 
/*     */     
/* 201 */     return count;
/*     */   }
/*     */   
/*     */   private void encodeBytes(CharsetEncoder encoder, ByteBuffer output) throws IOException {
/* 205 */     CoderResult result = encoder.encode(this.readBuffer, output, false);
/* 206 */     if (rewindReadBuffer() && result.isUnderflow()) {
/* 207 */       encoder.encode(this.readBuffer, output, false);
/* 208 */       rewindReadBuffer();
/*     */     } 
/*     */   }
/*     */   
/*     */   synchronized int readBytes(CharsetEncoder encoder, byte[] b, int off, int len) throws IOException {
/* 213 */     if (!waitForInput()) {
/* 214 */       return 0;
/*     */     }
/*     */     
/* 217 */     ByteBuffer output = ByteBuffer.wrap(b, off, len);
/* 218 */     encodeBytes(encoder, output);
/* 219 */     return output.position() - off;
/*     */   }
/*     */   
/*     */   synchronized void readBytes(CharsetEncoder encoder, ByteBuffer output) throws IOException {
/* 223 */     if (!waitForInput()) {
/*     */       return;
/*     */     }
/*     */     
/* 227 */     encodeBytes(encoder, output);
/*     */   }
/*     */   
/*     */   synchronized void write(char c) throws IOException {
/* 231 */     waitForBufferSpace();
/* 232 */     this.writeBuffer.put(c);
/* 233 */     rewindWriteBuffer();
/*     */   }
/*     */   
/*     */   synchronized void write(char[] cbuf, int off, int len) throws IOException {
/* 237 */     while (len > 0) {
/* 238 */       waitForBufferSpace();
/*     */ 
/*     */       
/* 241 */       int count = Math.min(len, this.writeBuffer.remaining());
/* 242 */       this.writeBuffer.put(cbuf, off, count);
/*     */       
/* 244 */       off += count;
/* 245 */       len -= count;
/*     */ 
/*     */       
/* 248 */       rewindWriteBuffer();
/*     */     } 
/*     */   }
/*     */   
/*     */   synchronized void write(String str, int off, int len) throws IOException {
/* 253 */     char[] buf = this.writeBuffer.array();
/*     */     
/* 255 */     while (len > 0) {
/* 256 */       waitForBufferSpace();
/*     */ 
/*     */       
/* 259 */       int count = Math.min(len, this.writeBuffer.remaining());
/*     */       
/* 261 */       str.getChars(off, off + count, buf, this.writeBuffer.position());
/* 262 */       this.writeBuffer.position(this.writeBuffer.position() + count);
/*     */       
/* 264 */       off += count;
/* 265 */       len -= count;
/*     */ 
/*     */       
/* 268 */       rewindWriteBuffer();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   synchronized void flush() {
/* 274 */     if (this.readBuffer.hasRemaining())
/*     */     {
/* 276 */       notifyAll();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void close() throws IOException {
/* 282 */     this.closed = true;
/* 283 */     notifyAll();
/*     */   }
/*     */   
/*     */   private static class Writer
/*     */     extends java.io.Writer {
/*     */     private final PumpReader reader;
/*     */     
/*     */     private Writer(PumpReader reader) {
/* 291 */       this.reader = reader;
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(int c) throws IOException {
/* 296 */       this.reader.write((char)c);
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(char[] cbuf, int off, int len) throws IOException {
/* 301 */       this.reader.write(cbuf, off, len);
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(String str, int off, int len) throws IOException {
/* 306 */       this.reader.write(str, off, len);
/*     */     }
/*     */ 
/*     */     
/*     */     public void flush() throws IOException {
/* 311 */       this.reader.flush();
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 316 */       this.reader.close();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class InputStream
/*     */     extends java.io.InputStream
/*     */   {
/*     */     private final PumpReader reader;
/*     */     
/*     */     private final CharsetEncoder encoder;
/*     */     
/*     */     private final ByteBuffer buffer;
/*     */ 
/*     */     
/*     */     private InputStream(PumpReader reader, Charset charset) {
/* 333 */       this.reader = reader;
/* 334 */       this
/*     */         
/* 336 */         .encoder = charset.newEncoder().onUnmappableCharacter(CodingErrorAction.REPLACE).onMalformedInput(CodingErrorAction.REPLACE);
/* 337 */       this.buffer = ByteBuffer.allocate((int)Math.ceil(this.encoder.maxBytesPerChar()));
/*     */ 
/*     */       
/* 340 */       this.buffer.limit(0);
/*     */     }
/*     */ 
/*     */     
/*     */     public int available() throws IOException {
/* 345 */       return (int)(this.reader.available() * this.encoder.averageBytesPerChar()) + this.buffer.remaining();
/*     */     }
/*     */ 
/*     */     
/*     */     public int read() throws IOException {
/* 350 */       if (!this.buffer.hasRemaining() && !readUsingBuffer()) {
/* 351 */         return -1;
/*     */       }
/*     */       
/* 354 */       return this.buffer.get();
/*     */     }
/*     */     
/*     */     private boolean readUsingBuffer() throws IOException {
/* 358 */       this.buffer.clear();
/* 359 */       this.reader.readBytes(this.encoder, this.buffer);
/* 360 */       this.buffer.flip();
/* 361 */       return this.buffer.hasRemaining();
/*     */     }
/*     */     
/*     */     private int copyFromBuffer(byte[] b, int off, int len) {
/* 365 */       len = Math.min(len, this.buffer.remaining());
/* 366 */       this.buffer.get(b, off, len);
/* 367 */       return len;
/*     */     }
/*     */     
/*     */     public int read(byte[] b, int off, int len) throws IOException {
/*     */       int read;
/* 372 */       if (len == 0) {
/* 373 */         return 0;
/*     */       }
/*     */ 
/*     */       
/* 377 */       if (this.buffer.hasRemaining()) {
/* 378 */         read = copyFromBuffer(b, off, len);
/* 379 */         if (read == len) {
/* 380 */           return len;
/*     */         }
/*     */         
/* 383 */         off += read;
/* 384 */         len -= read;
/*     */       } else {
/* 386 */         read = 0;
/*     */       } 
/*     */ 
/*     */       
/* 390 */       if (len >= this.buffer.capacity()) {
/* 391 */         read += this.reader.readBytes(this.encoder, b, off, len);
/* 392 */       } else if (readUsingBuffer()) {
/* 393 */         read += copyFromBuffer(b, off, len);
/*     */       } 
/*     */ 
/*     */       
/* 397 */       return (read == 0) ? -1 : read;
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 402 */       this.reader.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\PumpReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */