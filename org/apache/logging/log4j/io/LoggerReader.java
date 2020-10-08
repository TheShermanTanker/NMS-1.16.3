/*    */ package org.apache.logging.log4j.io;
/*    */ 
/*    */ import java.io.FilterReader;
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
/*    */ import java.nio.CharBuffer;
/*    */ import org.apache.logging.log4j.Level;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.spi.ExtendedLogger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoggerReader
/*    */   extends FilterReader
/*    */ {
/* 35 */   private static final String FQCN = LoggerReader.class.getName();
/*    */   
/*    */   private final CharStreamLogger logger;
/*    */   
/*    */   private final String fqcn;
/*    */   
/*    */   protected LoggerReader(Reader reader, ExtendedLogger logger, String fqcn, Level level, Marker marker) {
/* 42 */     super(reader);
/* 43 */     this.logger = new CharStreamLogger(logger, level, marker);
/* 44 */     this.fqcn = (fqcn == null) ? FQCN : fqcn;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 49 */     super.close();
/* 50 */     this.logger.close(this.fqcn);
/*    */   }
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 55 */     int c = super.read();
/* 56 */     this.logger.put(this.fqcn, c);
/* 57 */     return c;
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(char[] cbuf) throws IOException {
/* 62 */     return read(cbuf, 0, cbuf.length);
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(char[] cbuf, int off, int len) throws IOException {
/* 67 */     int charsRead = super.read(cbuf, off, len);
/* 68 */     this.logger.put(this.fqcn, cbuf, off, charsRead);
/* 69 */     return charsRead;
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(CharBuffer target) throws IOException {
/* 74 */     int len = target.remaining();
/* 75 */     char[] cbuf = new char[len];
/* 76 */     int charsRead = read(cbuf, 0, len);
/* 77 */     if (charsRead > 0) {
/* 78 */       target.put(cbuf, 0, charsRead);
/*    */     }
/* 80 */     return charsRead;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 85 */     return LoggerReader.class.getSimpleName() + "{stream=" + this.in + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\LoggerReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */