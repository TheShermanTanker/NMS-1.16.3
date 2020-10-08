/*     */ package org.apache.logging.log4j.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.spi.ExtendedLogger;
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
/*     */ public class ByteStreamLogger
/*     */ {
/*     */   private static final int BUFFER_SIZE = 1024;
/*     */   private final ExtendedLogger logger;
/*     */   private final Level level;
/*     */   private final Marker marker;
/*     */   private final InputStreamReader reader;
/*     */   
/*     */   private class ByteBufferInputStream
/*     */     extends InputStream
/*     */   {
/*     */     private ByteBufferInputStream() {}
/*     */     
/*     */     public int read() throws IOException {
/*  39 */       ByteStreamLogger.this.buf.flip();
/*  40 */       int result = -1;
/*  41 */       if (ByteStreamLogger.this.buf.limit() > 0) {
/*  42 */         result = ByteStreamLogger.this.buf.get() & 0xFF;
/*     */       }
/*  44 */       ByteStreamLogger.this.buf.compact();
/*  45 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public int read(byte[] bytes, int off, int len) throws IOException {
/*  50 */       ByteStreamLogger.this.buf.flip();
/*  51 */       int result = -1;
/*  52 */       if (ByteStreamLogger.this.buf.limit() > 0) {
/*  53 */         result = Math.min(len, ByteStreamLogger.this.buf.limit());
/*  54 */         ByteStreamLogger.this.buf.get(bytes, off, result);
/*     */       } 
/*  56 */       ByteStreamLogger.this.buf.compact();
/*  57 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private final char[] msgBuf = new char[1024];
/*  67 */   private final StringBuilder msg = new StringBuilder();
/*     */   
/*     */   private boolean closed;
/*  70 */   private final ByteBuffer buf = ByteBuffer.allocate(1024);
/*     */   
/*     */   public ByteStreamLogger(ExtendedLogger logger, Level level, Marker marker, Charset charset) {
/*  73 */     this.logger = logger;
/*  74 */     this.level = (level == null) ? logger.getLevel() : level;
/*  75 */     this.marker = marker;
/*  76 */     this.reader = new InputStreamReader(new ByteBufferInputStream(), (charset == null) ? Charset.defaultCharset() : charset);
/*     */   }
/*     */ 
/*     */   
/*     */   public void close(String fqcn) {
/*  81 */     synchronized (this.msg) {
/*  82 */       this.closed = true;
/*  83 */       logEnd(fqcn);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void extractMessages(String fqcn) throws IOException {
/*  88 */     if (this.closed) {
/*     */       return;
/*     */     }
/*  91 */     int read = this.reader.read(this.msgBuf);
/*  92 */     while (read > 0) {
/*  93 */       int off = 0;
/*  94 */       for (int pos = 0; pos < read; pos++) {
/*  95 */         switch (this.msgBuf[pos]) {
/*     */           case '\r':
/*  97 */             this.msg.append(this.msgBuf, off, pos - off);
/*  98 */             off = pos + 1;
/*     */             break;
/*     */           case '\n':
/* 101 */             this.msg.append(this.msgBuf, off, pos - off);
/* 102 */             off = pos + 1;
/* 103 */             log(fqcn);
/*     */             break;
/*     */         } 
/*     */       } 
/* 107 */       this.msg.append(this.msgBuf, off, read - off);
/* 108 */       read = this.reader.read(this.msgBuf);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void log(String fqcn) {
/* 114 */     this.logger.logIfEnabled(fqcn, this.level, this.marker, this.msg.toString());
/* 115 */     this.msg.setLength(0);
/*     */   }
/*     */   
/*     */   private void logEnd(String fqcn) {
/* 119 */     if (this.msg.length() > 0) {
/* 120 */       log(fqcn);
/*     */     }
/*     */   }
/*     */   
/*     */   public void put(String fqcn, byte[] b, int off, int len) throws IOException {
/* 125 */     int curOff = off;
/* 126 */     int curLen = len;
/* 127 */     if (curLen >= 0) {
/* 128 */       synchronized (this.msg) {
/* 129 */         while (curLen > this.buf.remaining()) {
/* 130 */           int remaining = this.buf.remaining();
/* 131 */           this.buf.put(b, curOff, remaining);
/* 132 */           curLen -= remaining;
/* 133 */           curOff += remaining;
/* 134 */           extractMessages(fqcn);
/*     */         } 
/* 136 */         this.buf.put(b, curOff, curLen);
/* 137 */         extractMessages(fqcn);
/*     */       } 
/*     */     } else {
/* 140 */       logEnd(fqcn);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void put(String fqcn, int b) throws IOException {
/* 145 */     if (b >= 0) {
/* 146 */       synchronized (this.msg) {
/* 147 */         this.buf.put((byte)(b & 0xFF));
/* 148 */         extractMessages(fqcn);
/*     */       } 
/*     */     } else {
/* 151 */       logEnd(fqcn);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\ByteStreamLogger.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */