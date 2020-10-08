/*    */ package org.apache.logging.log4j.io;
/*    */ 
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.charset.Charset;
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
/*    */ public class LoggerBufferedInputStream
/*    */   extends BufferedInputStream
/*    */ {
/* 34 */   private static final String FQCN = LoggerBufferedInputStream.class.getName();
/*    */ 
/*    */   
/*    */   protected LoggerBufferedInputStream(InputStream in, Charset charset, ExtendedLogger logger, String fqcn, Level level, Marker marker) {
/* 38 */     super(new LoggerInputStream(in, charset, logger, (fqcn == null) ? FQCN : fqcn, level, marker));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected LoggerBufferedInputStream(InputStream in, Charset charset, int size, ExtendedLogger logger, String fqcn, Level level, Marker marker) {
/* 44 */     super(new LoggerInputStream(in, charset, logger, (fqcn == null) ? FQCN : fqcn, level, marker), size);
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 49 */     super.close();
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized int read() throws IOException {
/* 54 */     return super.read();
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(byte[] b) throws IOException {
/* 59 */     return super.read(b, 0, b.length);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized int read(byte[] b, int off, int len) throws IOException {
/* 64 */     return super.read(b, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     return LoggerBufferedInputStream.class.getSimpleName() + "{stream=" + this.in + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\LoggerBufferedInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */