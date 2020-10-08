/*    */ package com.mysql.jdbc.integration.jboss;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import org.jboss.resource.adapter.jdbc.ValidConnectionChecker;
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
/*    */ public final class MysqlValidConnectionChecker
/*    */   implements ValidConnectionChecker, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8909421133577519177L;
/*    */   
/*    */   public SQLException isValidConnection(Connection conn) {
/* 52 */     Statement pingStatement = null;
/*    */     
/*    */     try {
/* 55 */       pingStatement = conn.createStatement();
/*    */       
/* 57 */       pingStatement.executeQuery("/* ping */ SELECT 1").close();
/*    */       
/* 59 */       return null;
/* 60 */     } catch (SQLException sqlEx) {
/* 61 */       return sqlEx;
/*    */     } finally {
/* 63 */       if (pingStatement != null)
/*    */         try {
/* 65 */           pingStatement.close();
/* 66 */         } catch (SQLException sqlEx) {} 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\integration\jboss\MysqlValidConnectionChecker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */