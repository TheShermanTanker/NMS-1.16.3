/*    */ package com.mysql.jdbc.jdbc2.optional;
/*    */ 
/*    */ import com.mysql.jdbc.Connection;
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import javax.sql.XAConnection;
/*    */ import javax.sql.XADataSource;
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
/*    */ public class MysqlXADataSource
/*    */   extends MysqlDataSource
/*    */   implements XADataSource
/*    */ {
/*    */   static final long serialVersionUID = 7911390333152247455L;
/*    */   
/*    */   public XAConnection getXAConnection() throws SQLException {
/* 46 */     Connection conn = getConnection();
/*    */     
/* 48 */     return wrapConnection(conn);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public XAConnection getXAConnection(String u, String p) throws SQLException {
/* 56 */     Connection conn = getConnection(u, p);
/*    */     
/* 58 */     return wrapConnection(conn);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private XAConnection wrapConnection(Connection conn) throws SQLException {
/* 66 */     if (getPinGlobalTxToPhysicalConnection() || ((Connection)conn).getPinGlobalTxToPhysicalConnection()) {
/* 67 */       return SuspendableXAConnection.getInstance((Connection)conn);
/*    */     }
/*    */     
/* 70 */     return MysqlXAConnection.getInstance((Connection)conn, getLogXaCommands());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\MysqlXADataSource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */