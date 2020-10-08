/*    */ package org.apache.logging.log4j.io;
/*    */ 
/*    */ import java.io.FilterInputStream;
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
/*    */ 
/*    */ public class LoggerInputStream
/*    */   extends FilterInputStream
/*    */ {
/* 35 */   private static final String FQCN = LoggerInputStream.class.getName();
/*    */   
/*    */   private final String fqcn;
/*    */   
/*    */   private final ByteStreamLogger logger;
/*    */   
/*    */   protected LoggerInputStream(InputStream in, Charset charset, ExtendedLogger logger, String fqcn, Level level, Marker marker) {
/* 42 */     super(in);
/* 43 */     this.logger = new ByteStreamLogger(logger, level, marker, charset);
/* 44 */     this.fqcn = (fqcn == null) ? FQCN : fqcn;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 49 */     this.logger.close(this.fqcn);
/* 50 */     super.close();
/*    */   }
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 55 */     int b = super.read();
/* 56 */     this.logger.put(this.fqcn, b);
/* 57 */     return b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(byte[] b) throws IOException {
/* 62 */     return read(b, 0, b.length);
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 67 */     int bytesRead = super.read(b, off, len);
/* 68 */     this.logger.put(this.fqcn, b, off, bytesRead);
/* 69 */     return bytesRead;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     return LoggerInputStream.class.getSimpleName() + "{stream=" + this.in + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\LoggerInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */