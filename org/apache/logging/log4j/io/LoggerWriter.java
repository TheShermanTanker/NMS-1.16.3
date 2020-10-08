/*    */ package org.apache.logging.log4j.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
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
/*    */ public class LoggerWriter
/*    */   extends Writer
/*    */ {
/* 34 */   private static final String FQCN = LoggerWriter.class.getName();
/*    */   
/*    */   private final CharStreamLogger logger;
/*    */   private final String fqcn;
/*    */   
/*    */   protected LoggerWriter(ExtendedLogger logger, String fqcn, Level level, Marker marker) {
/* 40 */     this.logger = new CharStreamLogger(logger, level, marker);
/* 41 */     this.fqcn = (fqcn == null) ? FQCN : fqcn;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 46 */     this.logger.close(this.fqcn);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void flush() throws IOException {}
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     return getClass().getSimpleName() + "[fqcn=" + this.fqcn + ", logger=" + this.logger + "]";
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(char[] cbuf) throws IOException {
/* 61 */     this.logger.put(this.fqcn, cbuf, 0, cbuf.length);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(char[] cbuf, int off, int len) throws IOException {
/* 66 */     this.logger.put(this.fqcn, cbuf, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(int c) throws IOException {
/* 71 */     this.logger.put(this.fqcn, (char)c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(String str) throws IOException {
/* 76 */     this.logger.put(this.fqcn, str, 0, str.length());
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(String str, int off, int len) throws IOException {
/* 81 */     this.logger.put(this.fqcn, str, off, len);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\LoggerWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */