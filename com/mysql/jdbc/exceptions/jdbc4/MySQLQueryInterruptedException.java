/*    */ package com.mysql.jdbc.exceptions.jdbc4;
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
/*    */ public class MySQLQueryInterruptedException
/*    */   extends MySQLNonTransientException
/*    */ {
/*    */   private static final long serialVersionUID = -8714521137662613517L;
/*    */   
/*    */   public MySQLQueryInterruptedException() {}
/*    */   
/*    */   public MySQLQueryInterruptedException(String reason, String SQLState, int vendorCode) {
/* 35 */     super(reason, SQLState, vendorCode);
/*    */   }
/*    */   
/*    */   public MySQLQueryInterruptedException(String reason, String SQLState) {
/* 39 */     super(reason, SQLState);
/*    */   }
/*    */   
/*    */   public MySQLQueryInterruptedException(String reason) {
/* 43 */     super(reason);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\exceptions\jdbc4\MySQLQueryInterruptedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */