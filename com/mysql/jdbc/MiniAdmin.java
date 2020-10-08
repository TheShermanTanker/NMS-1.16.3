/*    */ package com.mysql.jdbc;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
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
/*    */ public class MiniAdmin
/*    */ {
/*    */   private Connection conn;
/*    */   
/*    */   public MiniAdmin(Connection conn) throws SQLException {
/* 45 */     if (conn == null) {
/* 46 */       throw SQLError.createSQLException(Messages.getString("MiniAdmin.0"), "S1000", null);
/*    */     }
/*    */     
/* 49 */     if (!(conn instanceof Connection)) {
/* 50 */       throw SQLError.createSQLException(Messages.getString("MiniAdmin.1"), "S1000", ((ConnectionImpl)conn).getExceptionInterceptor());
/*    */     }
/*    */ 
/*    */     
/* 54 */     this.conn = (Connection)conn;
/*    */   }
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
/*    */   public MiniAdmin(String jdbcUrl) throws SQLException {
/* 67 */     this(jdbcUrl, new Properties());
/*    */   }
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
/*    */   public MiniAdmin(String jdbcUrl, Properties props) throws SQLException {
/* 83 */     this.conn = (Connection)(new Driver()).connect(jdbcUrl, props);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void shutdown() throws SQLException {
/* 94 */     this.conn.shutdownServer();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\MiniAdmin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */