/*    */ package com.mysql.jdbc;
/*    */ 
/*    */ import java.sql.NClob;
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
/*    */ public class JDBC4NClob
/*    */   extends Clob
/*    */   implements NClob
/*    */ {
/*    */   JDBC4NClob(ExceptionInterceptor exceptionInterceptor) {
/* 34 */     super(exceptionInterceptor);
/*    */   }
/*    */   
/*    */   JDBC4NClob(String charDataInit, ExceptionInterceptor exceptionInterceptor) {
/* 38 */     super(charDataInit, exceptionInterceptor);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\JDBC4NClob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */