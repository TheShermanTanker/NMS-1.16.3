/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ public class BestResponseTimeBalanceStrategy
/*     */   implements BalanceStrategy
/*     */ {
/*     */   public void destroy() {}
/*     */   
/*     */   public void init(Connection conn, Properties props) throws SQLException {}
/*     */   
/*     */   public ConnectionImpl pickConnection(LoadBalancedConnectionProxy proxy, List<String> configuredHosts, Map<String, ConnectionImpl> liveConnections, long[] responseTimes, int numRetries) throws SQLException {
/*  48 */     List<String> whiteList = new ArrayList<String>(configuredHosts.size());
/*  49 */     whiteList.addAll(configuredHosts);
/*     */     
/*  51 */     Map<String, Long> blackList = proxy.getGlobalBlacklist();
/*     */     
/*  53 */     whiteList.removeAll(blackList.keySet());
/*     */     
/*  55 */     SQLException ex = null;
/*     */     
/*  57 */     for (int attempts = 0; attempts < numRetries; ) {
/*  58 */       long minResponseTime = Long.MAX_VALUE;
/*     */       
/*  60 */       int bestHostIndex = 0;
/*     */ 
/*     */       
/*  63 */       if (blackList.size() == configuredHosts.size()) {
/*  64 */         blackList = proxy.getGlobalBlacklist();
/*     */       }
/*     */       
/*  67 */       for (int i = 0; i < responseTimes.length; i++) {
/*  68 */         long candidateResponseTime = responseTimes[i];
/*     */         
/*  70 */         if (candidateResponseTime < minResponseTime && !blackList.containsKey(whiteList.get(i))) {
/*  71 */           if (candidateResponseTime == 0L) {
/*  72 */             bestHostIndex = i;
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/*  77 */           bestHostIndex = i;
/*  78 */           minResponseTime = candidateResponseTime;
/*     */         } 
/*     */       } 
/*     */       
/*  82 */       String bestHost = whiteList.get(bestHostIndex);
/*     */       
/*  84 */       ConnectionImpl conn = liveConnections.get(bestHost);
/*     */       
/*  86 */       if (conn == null) {
/*     */         try {
/*  88 */           conn = proxy.createConnectionForHost(bestHost);
/*  89 */         } catch (SQLException sqlEx) {
/*  90 */           ex = sqlEx;
/*     */           
/*  92 */           if (proxy.shouldExceptionTriggerConnectionSwitch(sqlEx)) {
/*  93 */             proxy.addToGlobalBlacklist(bestHost);
/*  94 */             blackList.put(bestHost, null);
/*     */             
/*  96 */             if (blackList.size() == configuredHosts.size()) {
/*  97 */               attempts++;
/*     */               try {
/*  99 */                 Thread.sleep(250L);
/* 100 */               } catch (InterruptedException e) {}
/*     */               
/* 102 */               blackList = proxy.getGlobalBlacklist();
/*     */             } 
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 108 */           throw sqlEx;
/*     */         } 
/*     */       }
/*     */       
/* 112 */       return conn;
/*     */     } 
/*     */     
/* 115 */     if (ex != null) {
/* 116 */       throw ex;
/*     */     }
/*     */     
/* 119 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\BestResponseTimeBalanceStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */