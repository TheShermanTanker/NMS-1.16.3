/*    */ package org.jline.utils;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
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
/*    */ public abstract class NonBlockingInputStream
/*    */   extends InputStream
/*    */ {
/*    */   public static final int EOF = -1;
/*    */   public static final int READ_EXPIRED = -2;
/*    */   
/*    */   public int read() throws IOException {
/* 36 */     return read(0L, false);
/*    */   }
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
/*    */   public int peek(long timeout) throws IOException {
/* 49 */     return read(timeout, true);
/*    */   }
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
/*    */   public int read(long timeout) throws IOException {
/* 62 */     return read(timeout, false);
/*    */   }
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 66 */     if (b == null)
/* 67 */       throw new NullPointerException(); 
/* 68 */     if (off < 0 || len < 0 || len > b.length - off)
/* 69 */       throw new IndexOutOfBoundsException(); 
/* 70 */     if (len == 0) {
/* 71 */       return 0;
/*    */     }
/* 73 */     int c = read();
/* 74 */     if (c == -1) {
/* 75 */       return -1;
/*    */     }
/* 77 */     b[off] = (byte)c;
/* 78 */     return 1;
/*    */   }
/*    */   
/*    */   public void shutdown() {}
/*    */   
/*    */   public abstract int read(long paramLong, boolean paramBoolean) throws IOException;
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\NonBlockingInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */