/*    */ package com.mysql.jdbc;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.List;
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
/*    */ public class ServerAffinityStrategy
/*    */   extends RandomBalanceStrategy
/*    */ {
/*    */   public static final String AFFINITY_ORDER = "serverAffinityOrder";
/* 33 */   public String[] affinityOrderedServers = null;
/*    */ 
/*    */   
/*    */   public void init(Connection conn, Properties props) throws SQLException {
/* 37 */     super.init(conn, props);
/* 38 */     String hosts = props.getProperty("serverAffinityOrder");
/* 39 */     if (!StringUtils.isNullOrEmpty(hosts)) {
/* 40 */       this.affinityOrderedServers = hosts.split(",");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ConnectionImpl pickConnection(LoadBalancedConnectionProxy proxy, List<String> configuredHosts, Map<String, ConnectionImpl> liveConnections, long[] responseTimes, int numRetries) throws SQLException {
/* 47 */     if (this.affinityOrderedServers == null) {
/* 48 */       return super.pickConnection(proxy, configuredHosts, liveConnections, responseTimes, numRetries);
/*    */     }
/* 50 */     Map<String, Long> blackList = proxy.getGlobalBlacklist();
/*    */     
/* 52 */     for (String host : this.affinityOrderedServers) {
/* 53 */       if (configuredHosts.contains(host) && !blackList.containsKey(host)) {
/* 54 */         ConnectionImpl conn = liveConnections.get(host);
/* 55 */         if (conn != null) {
/* 56 */           return conn;
/*    */         }
/*    */         try {
/* 59 */           conn = proxy.createConnectionForHost(host);
/* 60 */           return conn;
/* 61 */         } catch (SQLException sqlEx) {
/* 62 */           if (proxy.shouldExceptionTriggerConnectionSwitch(sqlEx)) {
/* 63 */             proxy.addToGlobalBlacklist(host);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 70 */     return super.pickConnection(proxy, configuredHosts, liveConnections, responseTimes, numRetries);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ServerAffinityStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */