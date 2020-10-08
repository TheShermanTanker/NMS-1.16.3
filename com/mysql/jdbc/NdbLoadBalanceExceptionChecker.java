/*    */ package com.mysql.jdbc;
/*    */ 
/*    */ import java.sql.SQLException;
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
/*    */ public class NdbLoadBalanceExceptionChecker
/*    */   extends StandardLoadBalanceExceptionChecker
/*    */ {
/*    */   public boolean shouldExceptionTriggerFailover(SQLException ex) {
/* 32 */     return (super.shouldExceptionTriggerFailover(ex) || checkNdbException(ex));
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean checkNdbException(SQLException ex) {
/* 37 */     return (ex.getMessage().startsWith("Lock wait timeout exceeded") || (ex.getMessage().startsWith("Got temporary error") && ex.getMessage().endsWith("from NDB")));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\NdbLoadBalanceExceptionChecker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */