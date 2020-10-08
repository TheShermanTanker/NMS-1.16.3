/*    */ package org.jline.utils;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
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
/*    */ public abstract class NonBlockingReader
/*    */   extends Reader
/*    */ {
/*    */   public static final int EOF = -1;
/*    */   public static final int READ_EXPIRED = -2;
/*    */   
/*    */   public void shutdown() {}
/*    */   
/*    */   public int read() throws IOException {
/* 31 */     return read(0L, false);
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
/* 44 */     return read(timeout, true);
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
/* 57 */     return read(timeout, false);
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
/*    */   
/*    */   public int read(char[] b, int off, int len) throws IOException {
/* 71 */     if (b == null)
/* 72 */       throw new NullPointerException(); 
/* 73 */     if (off < 0 || len < 0 || len > b.length - off)
/* 74 */       throw new IndexOutOfBoundsException(); 
/* 75 */     if (len == 0) {
/* 76 */       return 0;
/*    */     }
/*    */     
/* 79 */     int c = read(0L);
/*    */     
/* 81 */     if (c == -1) {
/* 82 */       return -1;
/*    */     }
/* 84 */     b[off] = (char)c;
/* 85 */     return 1;
/*    */   }
/*    */   
/*    */   public int available() {
/* 89 */     return 0;
/*    */   }
/*    */   
/*    */   protected abstract int read(long paramLong, boolean paramBoolean) throws IOException;
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\NonBlockingReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */