/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.sql.SQLException;
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
/*     */ public class SequentialBalanceStrategy
/*     */   implements BalanceStrategy
/*     */ {
/*  37 */   private int currentHostIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroy() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(Connection conn, Properties props) throws SQLException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionImpl pickConnection(LoadBalancedConnectionProxy proxy, List<String> configuredHosts, Map<String, ConnectionImpl> liveConnections, long[] responseTimes, int numRetries) throws SQLException {
/*  52 */     int numHosts = configuredHosts.size();
/*     */     
/*  54 */     SQLException ex = null;
/*     */     
/*  56 */     Map<String, Long> blackList = proxy.getGlobalBlacklist();
/*     */     
/*  58 */     for (int attempts = 0; attempts < numRetries; attempts++) {
/*  59 */       if (numHosts == 1) {
/*  60 */         this.currentHostIndex = 0;
/*  61 */       } else if (this.currentHostIndex == -1) {
/*  62 */         int random = (int)Math.floor(Math.random() * numHosts);
/*     */         int i;
/*  64 */         for (i = random; i < numHosts; i++) {
/*  65 */           if (!blackList.containsKey(configuredHosts.get(i))) {
/*  66 */             this.currentHostIndex = i;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*  71 */         if (this.currentHostIndex == -1) {
/*  72 */           for (i = 0; i < random; i++) {
/*  73 */             if (!blackList.containsKey(configuredHosts.get(i))) {
/*  74 */               this.currentHostIndex = i;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/*  80 */         if (this.currentHostIndex == -1) {
/*  81 */           blackList = proxy.getGlobalBlacklist();
/*     */ 
/*     */           
/*     */           try {
/*  85 */             Thread.sleep(250L);
/*  86 */           } catch (InterruptedException e) {}
/*     */ 
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */       } else {
/*  93 */         int i = this.currentHostIndex + 1;
/*  94 */         boolean foundGoodHost = false;
/*     */         
/*  96 */         for (; i < numHosts; i++) {
/*  97 */           if (!blackList.containsKey(configuredHosts.get(i))) {
/*  98 */             this.currentHostIndex = i;
/*  99 */             foundGoodHost = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 104 */         if (!foundGoodHost) {
/* 105 */           for (i = 0; i < this.currentHostIndex; i++) {
/* 106 */             if (!blackList.containsKey(configuredHosts.get(i))) {
/* 107 */               this.currentHostIndex = i;
/* 108 */               foundGoodHost = true;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/* 114 */         if (!foundGoodHost) {
/* 115 */           blackList = proxy.getGlobalBlacklist();
/*     */ 
/*     */           
/*     */           try {
/* 119 */             Thread.sleep(250L);
/* 120 */           } catch (InterruptedException e) {}
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */       
/* 127 */       String hostPortSpec = configuredHosts.get(this.currentHostIndex);
/*     */       
/* 129 */       ConnectionImpl conn = liveConnections.get(hostPortSpec);
/*     */       
/* 131 */       if (conn == null) {
/*     */         try {
/* 133 */           conn = proxy.createConnectionForHost(hostPortSpec);
/* 134 */         } catch (SQLException sqlEx) {
/* 135 */           ex = sqlEx;
/*     */           
/* 137 */           if (proxy.shouldExceptionTriggerConnectionSwitch(sqlEx)) {
/*     */             
/* 139 */             proxy.addToGlobalBlacklist(hostPortSpec);
/*     */             
/*     */             try {
/* 142 */               Thread.sleep(250L);
/* 143 */             } catch (InterruptedException e) {}
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 148 */             throw sqlEx;
/*     */           } 
/*     */         } 
/*     */       }
/* 152 */       return conn;
/*     */     } 
/*     */     
/* 155 */     if (ex != null) {
/* 156 */       throw ex;
/*     */     }
/*     */     
/* 159 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\SequentialBalanceStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */