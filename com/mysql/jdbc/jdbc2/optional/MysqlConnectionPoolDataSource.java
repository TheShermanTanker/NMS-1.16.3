/*    */ package com.mysql.jdbc.jdbc2.optional;
/*    */ 
/*    */ import com.mysql.jdbc.Connection;
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import javax.sql.ConnectionPoolDataSource;
/*    */ import javax.sql.PooledConnection;
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
/*    */ public class MysqlConnectionPoolDataSource
/*    */   extends MysqlDataSource
/*    */   implements ConnectionPoolDataSource
/*    */ {
/*    */   static final long serialVersionUID = -7767325445592304961L;
/*    */   
/*    */   public synchronized PooledConnection getPooledConnection() throws SQLException {
/* 48 */     Connection connection = getConnection();
/* 49 */     MysqlPooledConnection mysqlPooledConnection = MysqlPooledConnection.getInstance((Connection)connection);
/*    */     
/* 51 */     return mysqlPooledConnection;
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
/*    */   public synchronized PooledConnection getPooledConnection(String s, String s1) throws SQLException {
/* 67 */     Connection connection = getConnection(s, s1);
/* 68 */     MysqlPooledConnection mysqlPooledConnection = MysqlPooledConnection.getInstance((Connection)connection);
/*    */     
/* 70 */     return mysqlPooledConnection;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\MysqlConnectionPoolDataSource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */