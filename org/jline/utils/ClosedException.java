/*    */ package org.jline.utils;
/*    */ 
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
/*    */ public class ClosedException
/*    */   extends IOException
/*    */ {
/*    */   private static final long serialVersionUID = 3085420657077696L;
/*    */   
/*    */   public ClosedException() {}
/*    */   
/*    */   public ClosedException(String message) {
/* 21 */     super(message);
/*    */   }
/*    */   
/*    */   public ClosedException(String message, Throwable cause) {
/* 25 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public ClosedException(Throwable cause) {
/* 29 */     super(cause);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\ClosedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */