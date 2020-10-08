/*     */ package org.apache.logging.log4j.io;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Objects;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.LoggingException;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.spi.ExtendedLogger;
/*     */ import org.apache.logging.log4j.util.ReflectionUtil;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IoBuilder
/*     */ {
/*     */   private final ExtendedLogger logger;
/*     */   private Level level;
/*     */   private Marker marker;
/*     */   private String fqcn;
/*     */   private boolean autoFlush;
/*     */   private boolean buffered;
/*     */   private int bufferSize;
/*     */   private Charset charset;
/*     */   private Reader reader;
/*     */   private Writer writer;
/*     */   private InputStream inputStream;
/*     */   private OutputStream outputStream;
/*     */   
/*     */   public static IoBuilder forLogger(Logger logger) {
/*  81 */     return new IoBuilder(logger);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IoBuilder forLogger(String loggerName) {
/*  92 */     return new IoBuilder(LogManager.getLogger(loggerName));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IoBuilder forLogger(Class<?> clazz) {
/* 103 */     return new IoBuilder(LogManager.getLogger(clazz));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IoBuilder forLogger() {
/* 115 */     return new IoBuilder(LogManager.getLogger(ReflectionUtil.getCallerClass(2)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IoBuilder(Logger logger) {
/* 125 */     if (!(logger instanceof ExtendedLogger)) {
/* 126 */       throw new UnsupportedOperationException("The provided Logger [" + String.valueOf(logger) + "] does not implement " + ExtendedLogger.class.getName());
/*     */     }
/*     */     
/* 129 */     this.logger = (ExtendedLogger)logger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IoBuilder setLevel(Level level) {
/* 140 */     this.level = level;
/* 141 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IoBuilder setMarker(Marker marker) {
/* 152 */     this.marker = marker;
/* 153 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IoBuilder setWrapperClassName(String fqcn) {
/* 164 */     this.fqcn = fqcn;
/* 165 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IoBuilder setAutoFlush(boolean autoFlush) {
/* 177 */     this.autoFlush = autoFlush;
/* 178 */     return this;
/*     */   }
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
/*     */   public IoBuilder setBuffered(boolean buffered) {
/* 191 */     this.buffered = buffered;
/* 192 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IoBuilder setBufferSize(int bufferSize) {
/* 203 */     this.bufferSize = bufferSize;
/* 204 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IoBuilder setCharset(Charset charset) {
/* 216 */     this.charset = charset;
/* 217 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IoBuilder filter(Reader reader) {
/* 228 */     this.reader = reader;
/* 229 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IoBuilder filter(Writer writer) {
/* 240 */     this.writer = writer;
/* 241 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IoBuilder filter(InputStream inputStream) {
/* 252 */     this.inputStream = inputStream;
/* 253 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IoBuilder filter(OutputStream outputStream) {
/* 264 */     this.outputStream = outputStream;
/* 265 */     return this;
/*     */   }
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
/*     */   public Reader buildReader() {
/* 278 */     Reader in = Objects.<Reader>requireNonNull(this.reader, "reader");
/* 279 */     if (this.buffered) {
/* 280 */       if (this.bufferSize > 0) {
/* 281 */         return new LoggerBufferedReader(in, this.bufferSize, this.logger, this.fqcn, this.level, this.marker);
/*     */       }
/* 283 */       return new LoggerBufferedReader(in, this.logger, this.fqcn, this.level, this.marker);
/*     */     } 
/* 285 */     return new LoggerReader(in, this.logger, this.fqcn, this.level, this.marker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Writer buildWriter() {
/* 295 */     if (this.writer == null) {
/* 296 */       return new LoggerWriter(this.logger, this.fqcn, this.level, this.marker);
/*     */     }
/* 298 */     return new LoggerFilterWriter(this.writer, this.logger, this.fqcn, this.level, this.marker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrintWriter buildPrintWriter() {
/* 309 */     if (this.writer == null) {
/* 310 */       return new LoggerPrintWriter(this.logger, this.autoFlush, this.fqcn, this.level, this.marker);
/*     */     }
/* 312 */     return new LoggerPrintWriter(this.writer, this.autoFlush, this.logger, this.fqcn, this.level, this.marker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream buildInputStream() {
/* 323 */     InputStream in = Objects.<InputStream>requireNonNull(this.inputStream, "inputStream");
/* 324 */     if (this.buffered) {
/* 325 */       if (this.bufferSize > 0) {
/* 326 */         return new LoggerBufferedInputStream(in, this.charset, this.bufferSize, this.logger, this.fqcn, this.level, this.marker);
/*     */       }
/*     */       
/* 329 */       return new LoggerBufferedInputStream(in, this.charset, this.logger, this.fqcn, this.level, this.marker);
/*     */     } 
/* 331 */     return new LoggerInputStream(in, this.charset, this.logger, this.fqcn, this.level, this.marker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream buildOutputStream() {
/* 342 */     if (this.outputStream == null) {
/* 343 */       return new LoggerOutputStream(this.logger, this.level, this.marker, this.charset, this.fqcn);
/*     */     }
/* 345 */     return new LoggerFilterOutputStream(this.outputStream, this.charset, this.logger, this.fqcn, this.level, this.marker);
/*     */   }
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
/*     */   public PrintStream buildPrintStream() {
/*     */     try {
/* 359 */       if (this.outputStream == null) {
/* 360 */         return new LoggerPrintStream(this.logger, this.autoFlush, this.charset, this.fqcn, this.level, this.marker);
/*     */       }
/*     */       
/* 363 */       return new LoggerPrintStream(this.outputStream, this.autoFlush, this.charset, this.logger, this.fqcn, this.level, this.marker);
/*     */     }
/* 365 */     catch (UnsupportedEncodingException e) {
/*     */       
/* 367 */       throw new LoggingException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\IoBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */