/*    */ package org.apache.logging.log4j.io;
/*    */ 
/*    */ import java.io.FilterOutputStream;
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
/*    */ public class LoggerFilterOutputStream
/*    */   extends FilterOutputStream
/*    */ {
/* 38 */   private static final String FQCN = LoggerFilterOutputStream.class.getName();
/*    */   
/*    */   private final ByteStreamLogger logger;
/*    */   
/*    */   private final String fqcn;
/*    */   
/*    */   protected LoggerFilterOutputStream(OutputStream out, Charset charset, ExtendedLogger logger, String fqcn, Level level, Marker marker) {
/* 45 */     super(out);
/* 46 */     this.logger = new ByteStreamLogger(logger, level, marker, charset);
/* 47 */     this.fqcn = (fqcn == null) ? FQCN : fqcn;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 52 */     this.out.close();
/* 53 */     this.logger.close(this.fqcn);
/*    */   }
/*    */ 
/*    */   
/*    */   public void flush() throws IOException {
/* 58 */     this.out.flush();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     return LoggerFilterOutputStream.class.getSimpleName() + "{stream=" + this.out + '}';
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(byte[] b) throws IOException {
/* 68 */     this.out.write(b);
/* 69 */     this.logger.put(this.fqcn, b, 0, b.length);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(byte[] b, int off, int len) throws IOException {
/* 74 */     this.out.write(b, off, len);
/* 75 */     this.logger.put(this.fqcn, b, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(int b) throws IOException {
/* 80 */     this.out.write(b);
/* 81 */     this.logger.put(this.fqcn, (byte)(b & 0xFF));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\LoggerFilterOutputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */