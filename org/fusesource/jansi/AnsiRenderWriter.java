/*    */ package org.fusesource.jansi;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintWriter;
/*    */ import java.io.Writer;
/*    */ import java.util.Locale;
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
/*    */ public class AnsiRenderWriter
/*    */   extends PrintWriter
/*    */ {
/*    */   public AnsiRenderWriter(OutputStream out) {
/* 36 */     super(out);
/*    */   }
/*    */   
/*    */   public AnsiRenderWriter(OutputStream out, boolean autoFlush) {
/* 40 */     super(out, autoFlush);
/*    */   }
/*    */   
/*    */   public AnsiRenderWriter(Writer out) {
/* 44 */     super(out);
/*    */   }
/*    */   
/*    */   public AnsiRenderWriter(Writer out, boolean autoFlush) {
/* 48 */     super(out, autoFlush);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(String s) {
/* 53 */     if (AnsiRenderer.test(s)) {
/* 54 */       super.write(AnsiRenderer.render(s));
/*    */     } else {
/* 56 */       super.write(s);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PrintWriter format(String format, Object... args) {
/* 66 */     print(String.format(format, args));
/* 67 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public PrintWriter format(Locale l, String format, Object... args) {
/* 72 */     print(String.format(l, format, args));
/* 73 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\AnsiRenderWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */