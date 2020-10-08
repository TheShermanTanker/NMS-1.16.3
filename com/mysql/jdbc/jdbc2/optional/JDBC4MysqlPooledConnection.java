/*    */ package com.mysql.jdbc.jdbc2.optional;
/*    */ 
/*    */ import com.mysql.jdbc.Connection;
/*    */ import java.sql.SQLException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.sql.StatementEvent;
/*    */ import javax.sql.StatementEventListener;
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
/*    */ public class JDBC4MysqlPooledConnection
/*    */   extends MysqlPooledConnection
/*    */ {
/* 41 */   private final Map<StatementEventListener, StatementEventListener> statementEventListeners = new HashMap<StatementEventListener, StatementEventListener>();
/*    */   
/*    */   public JDBC4MysqlPooledConnection(Connection connection) {
/* 44 */     super(connection);
/*    */   }
/*    */   
/*    */   public synchronized void close() throws SQLException {
/* 48 */     super.close();
/*    */     
/* 50 */     this.statementEventListeners.clear();
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
/*    */   public void addStatementEventListener(StatementEventListener listener) {
/* 66 */     synchronized (this.statementEventListeners) {
/* 67 */       this.statementEventListeners.put(listener, listener);
/*    */     } 
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
/*    */   public void removeStatementEventListener(StatementEventListener listener) {
/* 82 */     synchronized (this.statementEventListeners) {
/* 83 */       this.statementEventListeners.remove(listener);
/*    */     } 
/*    */   }
/*    */   
/*    */   void fireStatementEvent(StatementEvent event) throws SQLException {
/* 88 */     synchronized (this.statementEventListeners) {
/* 89 */       for (StatementEventListener listener : this.statementEventListeners.keySet())
/* 90 */         listener.statementClosed(event); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\JDBC4MysqlPooledConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */