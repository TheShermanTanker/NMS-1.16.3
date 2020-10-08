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
/*    */ public class UserInterruptException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 6172232572140736750L;
/*    */   private final String partialLine;
/*    */   
/*    */   public UserInterruptException(String partialLine) {
/* 26 */     this.partialLine = partialLine;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPartialLine() {
/* 34 */     return this.partialLine;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\UserInterruptException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */