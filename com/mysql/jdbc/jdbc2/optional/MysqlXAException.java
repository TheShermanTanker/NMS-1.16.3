/*    */ package com.mysql.jdbc.jdbc2.optional;
/*    */ 
/*    */ import javax.transaction.xa.XAException;
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
/*    */ 
/*    */ 
/*    */ class MysqlXAException
/*    */   extends XAException
/*    */ {
/*    */   private static final long serialVersionUID = -9075817535836563004L;
/*    */   private String message;
/*    */   protected String xidAsString;
/*    */   
/*    */   public MysqlXAException(int errorCode, String message, String xidAsString) {
/* 38 */     super(errorCode);
/* 39 */     this.message = message;
/* 40 */     this.xidAsString = xidAsString;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MysqlXAException(String message, String xidAsString) {
/* 46 */     this.message = message;
/* 47 */     this.xidAsString = xidAsString;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 52 */     String superMessage = super.getMessage();
/* 53 */     StringBuilder returnedMessage = new StringBuilder();
/*    */     
/* 55 */     if (superMessage != null) {
/* 56 */       returnedMessage.append(superMessage);
/* 57 */       returnedMessage.append(":");
/*    */     } 
/*    */     
/* 60 */     if (this.message != null) {
/* 61 */       returnedMessage.append(this.message);
/*    */     }
/*    */     
/* 64 */     return returnedMessage.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\MysqlXAException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */