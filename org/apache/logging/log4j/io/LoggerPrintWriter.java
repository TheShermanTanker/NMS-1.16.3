/*     */ package org.apache.logging.log4j.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Writer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoggerPrintWriter
/*     */   extends PrintWriter
/*     */ {
/*  47 */   private static final String FQCN = LoggerPrintWriter.class.getName();
/*     */ 
/*     */   
/*     */   protected LoggerPrintWriter(ExtendedLogger logger, boolean autoFlush, String fqcn, Level level, Marker marker) {
/*  51 */     super(new LoggerWriter(logger, (fqcn == null) ? FQCN : fqcn, level, marker), autoFlush);
/*     */   }
/*     */ 
/*     */   
/*     */   protected LoggerPrintWriter(Writer writer, boolean autoFlush, ExtendedLogger logger, String fqcn, Level level, Marker marker) {
/*  56 */     super(new LoggerFilterWriter(writer, logger, (fqcn == null) ? FQCN : fqcn, level, marker), autoFlush);
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintWriter append(char c) {
/*  61 */     super.append(c);
/*  62 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintWriter append(CharSequence csq) {
/*  67 */     super.append(csq);
/*  68 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintWriter append(CharSequence csq, int start, int end) {
/*  73 */     super.append(csq, start, end);
/*  74 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkError() {
/*  79 */     return super.checkError();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/*  84 */     super.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public void flush() {
/*  89 */     super.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintWriter format(Locale l, String format, Object... args) {
/*  94 */     super.format(l, format, args);
/*  95 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintWriter format(String format, Object... args) {
/* 100 */     super.format(format, args);
/* 101 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(boolean b) {
/* 106 */     super.print(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(char c) {
/* 111 */     super.print(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(char[] s) {
/* 116 */     super.print(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(double d) {
/* 121 */     super.print(d);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(float f) {
/* 126 */     super.print(f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(int i) {
/* 131 */     super.print(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(long l) {
/* 136 */     super.print(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(Object obj) {
/* 141 */     super.print(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(String s) {
/* 146 */     super.print(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintWriter printf(Locale l, String format, Object... args) {
/* 151 */     super.printf(l, format, args);
/* 152 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerPrintWriter printf(String format, Object... args) {
/* 157 */     super.printf(format, args);
/* 158 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void println() {
/* 163 */     super.println();
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(boolean x) {
/* 168 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(char x) {
/* 173 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(char[] x) {
/* 178 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(double x) {
/* 183 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(float x) {
/* 188 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(int x) {
/* 193 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(long x) {
/* 198 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(Object x) {
/* 203 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(String x) {
/* 208 */     super.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 213 */     return LoggerPrintWriter.class.getSimpleName() + "{stream=" + this.out + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(char[] buf) {
/* 218 */     super.write(buf);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(char[] buf, int off, int len) {
/* 223 */     super.write(buf, off, len);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(int c) {
/* 228 */     super.write(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(String s) {
/* 233 */     super.write(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(String s, int off, int len) {
/* 238 */     super.write(s, off, len);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\LoggerPrintWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */