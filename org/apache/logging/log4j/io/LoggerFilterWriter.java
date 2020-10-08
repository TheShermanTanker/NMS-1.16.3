/*    */ package org.apache.logging.log4j.io;
/*    */ 
/*    */ import java.io.FilterWriter;
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
/*    */ public class LoggerFilterWriter
/*    */   extends FilterWriter
/*    */ {
/* 35 */   private static final String FQCN = LoggerFilterWriter.class.getName();
/*    */   
/*    */   private final CharStreamLogger logger;
/*    */   
/*    */   private final String fqcn;
/*    */   
/*    */   protected LoggerFilterWriter(Writer out, ExtendedLogger logger, String fqcn, Level level, Marker marker) {
/* 42 */     super(out);
/* 43 */     this.logger = new CharStreamLogger(logger, level, marker);
/* 44 */     this.fqcn = (fqcn == null) ? FQCN : fqcn;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 49 */     this.out.close();
/* 50 */     this.logger.close(this.fqcn);
/*    */   }
/*    */ 
/*    */   
/*    */   public void flush() throws IOException {
/* 55 */     this.out.flush();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     return LoggerFilterWriter.class.getSimpleName() + "{writer=" + this.out + '}';
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(char[] cbuf) throws IOException {
/* 65 */     this.out.write(cbuf);
/* 66 */     this.logger.put(this.fqcn, cbuf, 0, cbuf.length);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(char[] cbuf, int off, int len) throws IOException {
/* 71 */     this.out.write(cbuf, off, len);
/* 72 */     this.logger.put(this.fqcn, cbuf, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(int c) throws IOException {
/* 77 */     this.out.write(c);
/* 78 */     this.logger.put(this.fqcn, (char)c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(String str) throws IOException {
/* 83 */     this.out.write(str);
/* 84 */     this.logger.put(this.fqcn, str, 0, str.length());
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(String str, int off, int len) throws IOException {
/* 89 */     this.out.write(str, off, len);
/* 90 */     this.logger.put(this.fqcn, str, off, len);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\LoggerFilterWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */