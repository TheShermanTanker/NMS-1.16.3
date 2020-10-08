/*    */ package org.jline.utils;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.Closeable;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InterruptedIOException;
/*    */ import java.io.OutputStream;
/*    */ import java.util.Objects;
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
/*    */ public final class ExecHelper
/*    */ {
/*    */   public static String exec(boolean redirectInput, String... cmd) throws IOException {
/* 28 */     Objects.requireNonNull(cmd);
/*    */     try {
/* 30 */       Log.trace(new Object[] { "Running: ", cmd });
/* 31 */       ProcessBuilder pb = new ProcessBuilder(cmd);
/* 32 */       if (redirectInput) {
/* 33 */         pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
/*    */       }
/* 35 */       Process p = pb.start();
/* 36 */       String result = waitAndCapture(p);
/* 37 */       Log.trace(new Object[] { "Result: ", result });
/* 38 */       if (p.exitValue() != 0) {
/* 39 */         if (result.endsWith("\n")) {
/* 40 */           result = result.substring(0, result.length() - 1);
/*    */         }
/* 42 */         throw new IOException("Error executing '" + String.join(" ", (CharSequence[])cmd) + "': " + result);
/*    */       } 
/* 44 */       return result;
/* 45 */     } catch (InterruptedException e) {
/* 46 */       throw (IOException)(new InterruptedIOException("Command interrupted")).initCause(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String waitAndCapture(Process p) throws IOException, InterruptedException {
/* 51 */     ByteArrayOutputStream bout = new ByteArrayOutputStream();
/* 52 */     InputStream in = null;
/* 53 */     InputStream err = null;
/* 54 */     OutputStream out = null;
/*    */     
/*    */     try {
/* 57 */       in = p.getInputStream(); int c;
/* 58 */       while ((c = in.read()) != -1) {
/* 59 */         bout.write(c);
/*    */       }
/* 61 */       err = p.getErrorStream();
/* 62 */       while ((c = err.read()) != -1) {
/* 63 */         bout.write(c);
/*    */       }
/* 65 */       out = p.getOutputStream();
/* 66 */       p.waitFor();
/*    */     } finally {
/* 68 */       close(new Closeable[] { in, out, err });
/*    */     } 
/*    */     
/* 71 */     return bout.toString();
/*    */   }
/*    */   
/*    */   private static void close(Closeable... closeables) {
/* 75 */     for (Closeable c : closeables) {
/* 76 */       if (c != null)
/*    */         try {
/* 78 */           c.close();
/* 79 */         } catch (Exception exception) {} 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\ExecHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */