/*    */ package org.apache.logging.log4j.io;
/*    */ 
/*    */ import java.io.BufferedReader;
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
/*    */ public class LoggerBufferedReader
/*    */   extends BufferedReader
/*    */ {
/* 34 */   private static final String FQCN = LoggerBufferedReader.class.getName();
/*    */ 
/*    */   
/*    */   protected LoggerBufferedReader(Reader reader, ExtendedLogger logger, String fqcn, Level level, Marker marker) {
/* 38 */     super(new LoggerReader(reader, logger, (fqcn == null) ? FQCN : fqcn, level, marker));
/*    */   }
/*    */ 
/*    */   
/*    */   protected LoggerBufferedReader(Reader reader, int size, ExtendedLogger logger, String fqcn, Level level, Marker marker) {
/* 43 */     super(new LoggerReader(reader, logger, (fqcn == null) ? FQCN : fqcn, level, marker), size);
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 48 */     super.close();
/*    */   }
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 53 */     return super.read();
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(char[] cbuf) throws IOException {
/* 58 */     return super.read(cbuf, 0, cbuf.length);
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(char[] cbuf, int off, int len) throws IOException {
/* 63 */     return super.read(cbuf, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(CharBuffer target) throws IOException {
/* 68 */     int len = target.remaining();
/* 69 */     char[] cbuf = new char[len];
/* 70 */     int charsRead = read(cbuf, 0, len);
/* 71 */     if (charsRead > 0) {
/* 72 */       target.put(cbuf, 0, charsRead);
/*    */     }
/* 74 */     return charsRead;
/*    */   }
/*    */ 
/*    */   
/*    */   public String readLine() throws IOException {
/* 79 */     return super.readLine();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\LoggerBufferedReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */