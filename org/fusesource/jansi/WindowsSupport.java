/*    */ package org.fusesource.jansi;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import org.fusesource.jansi.internal.Kernel32;
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
/*    */ public class WindowsSupport
/*    */ {
/*    */   public static String getLastErrorMessage() {
/* 27 */     int errorCode = Kernel32.GetLastError();
/* 28 */     return getErrorMessage(errorCode);
/*    */   }
/*    */   
/*    */   public static String getErrorMessage(int errorCode) {
/* 32 */     int bufferSize = 160;
/* 33 */     byte[] data = new byte[bufferSize];
/* 34 */     Kernel32.FormatMessageW(Kernel32.FORMAT_MESSAGE_FROM_SYSTEM, 0L, errorCode, 0, data, bufferSize, null);
/*    */     try {
/* 36 */       return (new String(data, "UTF-16LE")).trim();
/* 37 */     } catch (UnsupportedEncodingException e) {
/* 38 */       throw new IllegalStateException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\WindowsSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */