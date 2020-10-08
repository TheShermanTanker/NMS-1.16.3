/*    */ package org.apache.logging.log4j.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoggerOutputStream
/*    */   extends OutputStream
/*    */ {
/* 37 */   private static final String FQCN = LoggerOutputStream.class.getName();
/*    */   
/*    */   private final ByteStreamLogger logger;
/*    */   
/*    */   private final String fqcn;
/*    */   
/*    */   protected LoggerOutputStream(ExtendedLogger logger, Level level, Marker marker, Charset charset, String fqcn) {
/* 44 */     this.logger = new ByteStreamLogger(logger, level, marker, charset);
/* 45 */     this.fqcn = (fqcn == null) ? FQCN : fqcn;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 50 */     this.logger.close(this.fqcn);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void flush() throws IOException {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(byte[] b) throws IOException {
/* 60 */     this.logger.put(this.fqcn, b, 0, b.length);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(byte[] b, int off, int len) throws IOException {
/* 65 */     this.logger.put(this.fqcn, b, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(int b) throws IOException {
/* 70 */     this.logger.put(this.fqcn, (byte)(b & 0xFF));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\LoggerOutputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */