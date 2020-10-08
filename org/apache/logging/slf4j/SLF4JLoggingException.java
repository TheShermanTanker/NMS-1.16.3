/*    */ package org.apache.logging.slf4j;
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
/*    */ public class SLF4JLoggingException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = -1618650972455089998L;
/*    */   
/*    */   public SLF4JLoggingException(String msg) {
/* 31 */     super(msg);
/*    */   }
/*    */   
/*    */   public SLF4JLoggingException(String msg, Exception ex) {
/* 35 */     super(msg, ex);
/*    */   }
/*    */   
/*    */   public SLF4JLoggingException(Exception ex) {
/* 39 */     super(ex);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\slf4j\SLF4JLoggingException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */