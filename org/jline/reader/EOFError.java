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
/*    */ public class EOFError
/*    */   extends SyntaxError
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final String missing;
/*    */   
/*    */   public EOFError(int line, int column, String message) {
/* 28 */     this(line, column, message, null);
/*    */   }
/*    */   
/*    */   public EOFError(int line, int column, String message, String missing) {
/* 32 */     super(line, column, message);
/* 33 */     this.missing = missing;
/*    */   }
/*    */   
/*    */   public String getMissing() {
/* 37 */     return this.missing;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\EOFError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */