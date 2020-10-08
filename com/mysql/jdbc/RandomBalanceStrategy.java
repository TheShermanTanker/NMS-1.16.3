/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomBalanceStrategy
/*     */   implements BalanceStrategy
/*     */ {
/*     */   public void destroy() {}
/*     */   
/*     */   public void init(Connection conn, Properties props) throws SQLException {}
/*     */   
/*     */   public ConnectionImpl pickConnection(LoadBalancedConnectionProxy proxy, List<String> configuredHosts, Map<String, ConnectionImpl> liveConnections, long[] responseTimes, int numRetries) throws SQLException {
/*  48 */     int numHosts = configuredHosts.size();
/*     */     
/*  50 */     SQLException ex = null;
/*     */     
/*  52 */     List<String> whiteList = new ArrayList<String>(numHosts);
/*  53 */     whiteList.addAll(configuredHosts);
/*     */     
/*  55 */     Map<String, Long> blackList = proxy.getGlobalBlacklist();
/*     */     
/*  57 */     whiteList.removeAll(blackList.keySet());
/*     */     
/*  59 */     Map<String, Integer> whiteListMap = getArrayIndexMap(whiteList);
/*     */     
/*  61 */     for (int attempts = 0; attempts < numRetries; ) {
/*  62 */       int random = (int)Math.floor(Math.random() * whiteList.size());
/*  63 */       if (whiteList.size() == 0) {
/*  64 */         throw SQLError.createSQLException("No hosts configured", null);
/*     */       }
/*     */       
/*  67 */       String hostPortSpec = whiteList.get(random);
/*     */       
/*  69 */       ConnectionImpl conn = liveConnections.get(hostPortSpec);
/*     */       
/*  71 */       if (conn == null) {
/*     */         try {
/*  73 */           conn = proxy.createConnectionForHost(hostPortSpec);
/*  74 */         } catch (SQLException sqlEx) {
/*  75 */           ex = sqlEx;
/*     */           
/*  77 */           if (proxy.shouldExceptionTriggerConnectionSwitch(sqlEx)) {
/*     */             
/*  79 */             Integer whiteListIndex = whiteListMap.get(hostPortSpec);
/*     */ 
/*     */             
/*  82 */             if (whiteListIndex != null) {
/*  83 */               whiteList.remove(whiteListIndex.intValue());
/*  84 */               whiteListMap = getArrayIndexMap(whiteList);
/*     */             } 
/*  86 */             proxy.addToGlobalBlacklist(hostPortSpec);
/*     */             
/*  88 */             if (whiteList.size() == 0) {
/*  89 */               attempts++;
/*     */               try {
/*  91 */                 Thread.sleep(250L);
/*  92 */               } catch (InterruptedException e) {}
/*     */ 
/*     */ 
/*     */               
/*  96 */               whiteListMap = new HashMap<String, Integer>(numHosts);
/*  97 */               whiteList.addAll(configuredHosts);
/*  98 */               blackList = proxy.getGlobalBlacklist();
/*     */               
/* 100 */               whiteList.removeAll(blackList.keySet());
/* 101 */               whiteListMap = getArrayIndexMap(whiteList);
/*     */             } 
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 107 */           throw sqlEx;
/*     */         } 
/*     */       }
/*     */       
/* 111 */       return conn;
/*     */     } 
/*     */     
/* 114 */     if (ex != null) {
/* 115 */       throw ex;
/*     */     }
/*     */     
/* 118 */     return null;
/*     */   }
/*     */   
/*     */   private Map<String, Integer> getArrayIndexMap(List<String> l) {
/* 122 */     Map<String, Integer> m = new HashMap<String, Integer>(l.size());
/* 123 */     for (int i = 0; i < l.size(); i++) {
/* 124 */       m.put(l.get(i), Integer.valueOf(i));
/*     */     }
/* 126 */     return m;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\RandomBalanceStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */