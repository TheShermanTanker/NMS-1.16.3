/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.sql.SQLException;
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
/*     */ public class LoadBalancedAutoCommitInterceptor
/*     */   implements StatementInterceptorV2
/*     */ {
/*  30 */   private int matchingAfterStatementCount = 0;
/*  31 */   private int matchingAfterStatementThreshold = 0;
/*     */   private String matchingAfterStatementRegex;
/*     */   private ConnectionImpl conn;
/*  34 */   private LoadBalancedConnectionProxy proxy = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean countStatements = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroy() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean executeTopLevelOnly() {
/*  49 */     return false;
/*     */   }
/*     */   
/*     */   public void init(Connection connection, Properties props) throws SQLException {
/*  53 */     this.conn = (ConnectionImpl)connection;
/*     */     
/*  55 */     String autoCommitSwapThresholdAsString = props.getProperty("loadBalanceAutoCommitStatementThreshold", "0");
/*     */     try {
/*  57 */       this.matchingAfterStatementThreshold = Integer.parseInt(autoCommitSwapThresholdAsString);
/*  58 */     } catch (NumberFormatException nfe) {}
/*     */ 
/*     */     
/*  61 */     String autoCommitSwapRegex = props.getProperty("loadBalanceAutoCommitStatementRegex", "");
/*  62 */     if ("".equals(autoCommitSwapRegex)) {
/*     */       return;
/*     */     }
/*  65 */     this.matchingAfterStatementRegex = autoCommitSwapRegex;
/*     */   }
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
/*     */   public ResultSetInternalMethods postProcess(String sql, Statement interceptedStatement, ResultSetInternalMethods originalResultSet, Connection connection, int warningCount, boolean noIndexUsed, boolean noGoodIndexUsed, SQLException statementException) throws SQLException {
/*  79 */     if (!this.countStatements || StringUtils.startsWithIgnoreCase(sql, "SET") || StringUtils.startsWithIgnoreCase(sql, "SHOW")) {
/*  80 */       return originalResultSet;
/*     */     }
/*     */ 
/*     */     
/*  84 */     if (!this.conn.getAutoCommit()) {
/*  85 */       this.matchingAfterStatementCount = 0;
/*  86 */       return originalResultSet;
/*     */     } 
/*     */     
/*  89 */     if (this.proxy == null && this.conn.isProxySet()) {
/*  90 */       MySQLConnection lcl_proxy = this.conn.getMultiHostSafeProxy();
/*  91 */       while (lcl_proxy != null && !(lcl_proxy instanceof LoadBalancedMySQLConnection)) {
/*  92 */         lcl_proxy = lcl_proxy.getMultiHostSafeProxy();
/*     */       }
/*  94 */       if (lcl_proxy != null) {
/*  95 */         this.proxy = ((LoadBalancedMySQLConnection)lcl_proxy).getThisAsProxy();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 101 */     if (this.proxy == null) {
/* 102 */       return originalResultSet;
/*     */     }
/*     */ 
/*     */     
/* 106 */     if (this.matchingAfterStatementRegex == null || sql.matches(this.matchingAfterStatementRegex)) {
/* 107 */       this.matchingAfterStatementCount++;
/*     */     }
/*     */ 
/*     */     
/* 111 */     if (this.matchingAfterStatementCount >= this.matchingAfterStatementThreshold) {
/* 112 */       this.matchingAfterStatementCount = 0;
/*     */       try {
/* 114 */         this.proxy.pickNewConnection();
/* 115 */       } catch (SQLException e) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     return originalResultSet;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResultSetInternalMethods preProcess(String sql, Statement interceptedStatement, Connection connection) throws SQLException {
/* 126 */     return null;
/*     */   }
/*     */   
/*     */   void pauseCounters() {
/* 130 */     this.countStatements = false;
/*     */   }
/*     */   
/*     */   void resumeCounters() {
/* 134 */     this.countStatements = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\LoadBalancedAutoCommitInterceptor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */