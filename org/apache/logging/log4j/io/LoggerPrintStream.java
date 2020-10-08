/*     */ package org.apache.logging.log4j.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Locale;
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
/*     */ public class LoggerPrintStream
/*     */   extends PrintStream
/*     */ {
/*  40 */   private static final String FQCN = LoggerPrintStream.class.getName();
/*     */ 
/*     */ 
/*     */   
/*     */   protected LoggerPrintStream(ExtendedLogger logger, boolean autoFlush, Charset charset, String fqcn, Level level, Marker marker) throws UnsupportedEncodingException {
/*  45 */     super(new LoggerOutputStream(logger, level, marker, ensureNonNull(charset), (fqcn == null) ? FQCN : fqcn), autoFlush, ensureNonNull(charset).name());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LoggerPrintStream(OutputStream out, boolean autoFlush, Charset charset, ExtendedLogger logger, String fqcn, Level level, Marker marker) throws UnsupportedEncodingException {
/*  52 */     super(new LoggerFilterOutputStream(out, ensureNonNull(charset), logger, (fqcn == null) ? FQCN : fqcn, level, marker), autoFlush, ensureNonNull(charset).name());
/*     */   }
/*     */ 
/*     */   
/*     */   private static Charset ensureNonNull(Charset charset) {
/*  57 */     return (charset == null) ? Charset.defaultCharset() : charset;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintStream append(char c) {
/*  62 */     super.append(c);
/*  63 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintStream append(CharSequence csq) {
/*  68 */     super.append(csq);
/*  69 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintStream append(CharSequence csq, int start, int end) {
/*  74 */     super.append(csq, start, end);
/*  75 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkError() {
/*  80 */     return super.checkError();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/*  85 */     super.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public void flush() {
/*  90 */     super.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintStream format(Locale l, String format, Object... args) {
/*  95 */     super.format(l, format, args);
/*  96 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintStream format(String format, Object... args) {
/* 101 */     super.format(format, args);
/* 102 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(boolean b) {
/* 107 */     super.print(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(char c) {
/* 112 */     super.print(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(char[] s) {
/* 117 */     super.print(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(double d) {
/* 122 */     super.print(d);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(float f) {
/* 127 */     super.print(f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(int i) {
/* 132 */     super.print(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(long l) {
/* 137 */     super.print(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(Object obj) {
/* 142 */     super.print(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(String s) {
/* 147 */     super.print(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintStream printf(Locale l, String format, Object... args) {
/* 152 */     super.printf(l, format, args);
/* 153 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintStream printf(String format, Object... args) {
/* 158 */     super.printf(format, args);
/* 159 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void println() {
/* 164 */     super.println();
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(boolean x) {
/* 169 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(char x) {
/* 174 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(char[] x) {
/* 179 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(double x) {
/* 184 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(float x) {
/* 189 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(int x) {
/* 194 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(long x) {
/* 199 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(Object x) {
/* 204 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(String x) {
/* 209 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 214 */     return LoggerPrintStream.class.getSimpleName() + "{stream=" + this.out + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 219 */     super.write(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) {
/* 224 */     super.write(b, off, len);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(int b) {
/* 229 */     super.write(b);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\LoggerPrintStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */