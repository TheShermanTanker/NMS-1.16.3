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
/*    */ public class LoadBalancedMySQLConnection
/*    */   extends MultiHostMySQLConnection
/*    */   implements LoadBalancedConnection
/*    */ {
/*    */   public LoadBalancedMySQLConnection(LoadBalancedConnectionProxy proxy) {
/* 31 */     super(proxy);
/*    */   }
/*    */ 
/*    */   
/*    */   protected LoadBalancedConnectionProxy getThisAsProxy() {
/* 36 */     return (LoadBalancedConnectionProxy)super.getThisAsProxy();
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws SQLException {
/* 41 */     getThisAsProxy().doClose();
/*    */   }
/*    */ 
/*    */   
/*    */   public void ping() throws SQLException {
/* 46 */     ping(true);
/*    */   }
/*    */   
/*    */   public void ping(boolean allConnections) throws SQLException {
/* 50 */     if (allConnections) {
/* 51 */       getThisAsProxy().doPing();
/*    */     } else {
/* 53 */       getActiveMySQLConnection().ping();
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean addHost(String host) throws SQLException {
/* 58 */     return getThisAsProxy().addHost(host);
/*    */   }
/*    */   
/*    */   public void removeHost(String host) throws SQLException {
/* 62 */     getThisAsProxy().removeHost(host);
/*    */   }
/*    */   
/*    */   public void removeHostWhenNotInUse(String host) throws SQLException {
/* 66 */     getThisAsProxy().removeHostWhenNotInUse(host);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\LoadBalancedMySQLConnection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */