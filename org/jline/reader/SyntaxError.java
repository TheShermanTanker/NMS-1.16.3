/*    */ package org.jline.reader;
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
/*    */ public class SyntaxError
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final int line;
/*    */   private final int column;
/*    */   
/*    */   public SyntaxError(int line, int column, String message) {
/* 29 */     super(message);
/* 30 */     this.line = line;
/* 31 */     this.column = column;
/*    */   }
/*    */   
/*    */   public int column() {
/* 35 */     return this.column;
/*    */   }
/*    */   
/*    */   public int line() {
/* 39 */     return this.line;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\SyntaxError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */