/*    */ package com.mysql.jdbc.interceptors;
/*    */ 
/*    */ import com.mysql.jdbc.Connection;
/*    */ import com.mysql.jdbc.ResultSetInternalMethods;
/*    */ import com.mysql.jdbc.Statement;
/*    */ import com.mysql.jdbc.StatementInterceptor;
/*    */ import com.mysql.jdbc.Util;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
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
/*    */ public class ServerStatusDiffInterceptor
/*    */   implements StatementInterceptor
/*    */ {
/* 39 */   private Map<String, String> preExecuteValues = new HashMap<String, String>();
/*    */   
/* 41 */   private Map<String, String> postExecuteValues = new HashMap<String, String>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(Connection conn, Properties props) throws SQLException {}
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultSetInternalMethods postProcess(String sql, Statement interceptedStatement, ResultSetInternalMethods originalResultSet, Connection connection) throws SQLException {
/* 50 */     if (connection.versionMeetsMinimum(5, 0, 2)) {
/* 51 */       populateMapWithSessionStatusValues(connection, this.postExecuteValues);
/*    */       
/* 53 */       connection.getLog().logInfo("Server status change for statement:\n" + Util.calculateDifferences(this.preExecuteValues, this.postExecuteValues));
/*    */     } 
/*    */     
/* 56 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   private void populateMapWithSessionStatusValues(Connection connection, Map<String, String> toPopulate) throws SQLException {
/* 61 */     Statement stmt = null;
/* 62 */     ResultSet rs = null;
/*    */     
/*    */     try {
/* 65 */       toPopulate.clear();
/* 66 */       stmt = connection.createStatement();
/* 67 */       rs = stmt.executeQuery("SHOW SESSION STATUS");
/* 68 */       while (rs.next()) {
/* 69 */         toPopulate.put(rs.getString(1), rs.getString(2));
/*    */       }
/*    */     } finally {
/* 72 */       if (rs != null) {
/* 73 */         rs.close();
/*    */       }
/* 75 */       if (stmt != null) {
/* 76 */         stmt.close();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ResultSetInternalMethods preProcess(String sql, Statement interceptedStatement, Connection connection) throws SQLException {
/* 83 */     if (connection.versionMeetsMinimum(5, 0, 2)) {
/* 84 */       populateMapWithSessionStatusValues(connection, this.preExecuteValues);
/*    */     }
/*    */     
/* 87 */     return null;
/*    */   }
/*    */   
/*    */   public boolean executeTopLevelOnly() {
/* 91 */     return true;
/*    */   }
/*    */   
/*    */   public void destroy() {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\interceptors\ServerStatusDiffInterceptor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */