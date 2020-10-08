/*    */ package org.fusesource.jansi;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
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
/*    */ public class AnsiString
/*    */   implements CharSequence
/*    */ {
/*    */   private final CharSequence encoded;
/*    */   private final CharSequence plain;
/*    */   
/*    */   public AnsiString(CharSequence str) {
/* 34 */     assert str != null;
/* 35 */     this.encoded = str;
/* 36 */     this.plain = chew(str);
/*    */   }
/*    */   
/*    */   private CharSequence chew(CharSequence str) {
/* 40 */     assert str != null;
/*    */     
/* 42 */     ByteArrayOutputStream buff = new ByteArrayOutputStream();
/* 43 */     AnsiOutputStream out = new AnsiOutputStream(buff);
/*    */     
/*    */     try {
/* 46 */       out.write(str.toString().getBytes());
/* 47 */       out.flush();
/* 48 */       out.close();
/* 49 */     } catch (IOException e) {
/* 50 */       throw new RuntimeException(e);
/*    */     } 
/*    */     
/* 53 */     return new String(buff.toByteArray());
/*    */   }
/*    */   
/*    */   public CharSequence getEncoded() {
/* 57 */     return this.encoded;
/*    */   }
/*    */   
/*    */   public CharSequence getPlain() {
/* 61 */     return this.plain;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public char charAt(int index) {
/* 67 */     return getEncoded().charAt(index);
/*    */   }
/*    */   
/*    */   public CharSequence subSequence(int start, int end) {
/* 71 */     return getEncoded().subSequence(start, end);
/*    */   }
/*    */   
/*    */   public int length() {
/* 75 */     return getPlain().length();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 80 */     return getEncoded().equals(obj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 85 */     return getEncoded().hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 90 */     return getEncoded().toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\AnsiString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */