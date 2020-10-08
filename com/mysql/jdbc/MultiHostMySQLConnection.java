/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import com.mysql.jdbc.log.Log;
/*      */ import com.mysql.jdbc.profiler.ProfilerEventHandler;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Savepoint;
/*      */ import java.sql.Statement;
/*      */ import java.util.Calendar;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.TimeZone;
/*      */ import java.util.Timer;
/*      */ import java.util.concurrent.Executor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MultiHostMySQLConnection
/*      */   implements MySQLConnection
/*      */ {
/*      */   protected MultiHostConnectionProxy thisAsProxy;
/*      */   
/*      */   public MultiHostMySQLConnection(MultiHostConnectionProxy proxy) {
/*   66 */     this.thisAsProxy = proxy;
/*      */   }
/*      */   
/*      */   protected MultiHostConnectionProxy getThisAsProxy() {
/*   70 */     return this.thisAsProxy;
/*      */   }
/*      */   
/*      */   public MySQLConnection getActiveMySQLConnection() {
/*   74 */     synchronized (this.thisAsProxy) {
/*   75 */       return this.thisAsProxy.currentConnection;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void abortInternal() throws SQLException {
/*   80 */     getActiveMySQLConnection().abortInternal();
/*      */   }
/*      */   
/*      */   public void changeUser(String userName, String newPassword) throws SQLException {
/*   84 */     getActiveMySQLConnection().changeUser(userName, newPassword);
/*      */   }
/*      */   
/*      */   public void checkClosed() throws SQLException {
/*   88 */     getActiveMySQLConnection().checkClosed();
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public void clearHasTriedMaster() {
/*   93 */     getActiveMySQLConnection().clearHasTriedMaster();
/*      */   }
/*      */   
/*      */   public void clearWarnings() throws SQLException {
/*   97 */     getActiveMySQLConnection().clearWarnings();
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/*  101 */     return getActiveMySQLConnection().clientPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/*  105 */     return getActiveMySQLConnection().clientPrepareStatement(sql, resultSetType, resultSetConcurrency);
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
/*  109 */     return getActiveMySQLConnection().clientPrepareStatement(sql, autoGenKeyIndex);
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
/*  113 */     return getActiveMySQLConnection().clientPrepareStatement(sql, autoGenKeyIndexes);
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
/*  117 */     return getActiveMySQLConnection().clientPrepareStatement(sql, autoGenKeyColNames);
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql) throws SQLException {
/*  121 */     return getActiveMySQLConnection().clientPrepareStatement(sql);
/*      */   }
/*      */   
/*      */   public void close() throws SQLException {
/*  125 */     getActiveMySQLConnection().close();
/*      */   }
/*      */   
/*      */   public void commit() throws SQLException {
/*  129 */     getActiveMySQLConnection().commit();
/*      */   }
/*      */   
/*      */   public void createNewIO(boolean isForReconnect) throws SQLException {
/*  133 */     getActiveMySQLConnection().createNewIO(isForReconnect);
/*      */   }
/*      */   
/*      */   public Statement createStatement() throws SQLException {
/*  137 */     return getActiveMySQLConnection().createStatement();
/*      */   }
/*      */   
/*      */   public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/*  141 */     return getActiveMySQLConnection().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
/*      */   }
/*      */   
/*      */   public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
/*  145 */     return getActiveMySQLConnection().createStatement(resultSetType, resultSetConcurrency);
/*      */   }
/*      */   
/*      */   public void dumpTestcaseQuery(String query) {
/*  149 */     getActiveMySQLConnection().dumpTestcaseQuery(query);
/*      */   }
/*      */   
/*      */   public Connection duplicate() throws SQLException {
/*  153 */     return getActiveMySQLConnection().duplicate();
/*      */   }
/*      */ 
/*      */   
/*      */   public ResultSetInternalMethods execSQL(StatementImpl callingStatement, String sql, int maxRows, Buffer packet, int resultSetType, int resultSetConcurrency, boolean streamResults, String catalog, Field[] cachedMetadata, boolean isBatch) throws SQLException {
/*  158 */     return getActiveMySQLConnection().execSQL(callingStatement, sql, maxRows, packet, resultSetType, resultSetConcurrency, streamResults, catalog, cachedMetadata, isBatch);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSetInternalMethods execSQL(StatementImpl callingStatement, String sql, int maxRows, Buffer packet, int resultSetType, int resultSetConcurrency, boolean streamResults, String catalog, Field[] cachedMetadata) throws SQLException {
/*  164 */     return getActiveMySQLConnection().execSQL(callingStatement, sql, maxRows, packet, resultSetType, resultSetConcurrency, streamResults, catalog, cachedMetadata);
/*      */   }
/*      */ 
/*      */   
/*      */   public String extractSqlFromPacket(String possibleSqlQuery, Buffer queryPacket, int endOfQueryPacketPosition) throws SQLException {
/*  169 */     return getActiveMySQLConnection().extractSqlFromPacket(possibleSqlQuery, queryPacket, endOfQueryPacketPosition);
/*      */   }
/*      */   
/*      */   public String exposeAsXml() throws SQLException {
/*  173 */     return getActiveMySQLConnection().exposeAsXml();
/*      */   }
/*      */   
/*      */   public boolean getAllowLoadLocalInfile() {
/*  177 */     return getActiveMySQLConnection().getAllowLoadLocalInfile();
/*      */   }
/*      */   
/*      */   public boolean getAllowMultiQueries() {
/*  181 */     return getActiveMySQLConnection().getAllowMultiQueries();
/*      */   }
/*      */   
/*      */   public boolean getAllowNanAndInf() {
/*  185 */     return getActiveMySQLConnection().getAllowNanAndInf();
/*      */   }
/*      */   
/*      */   public boolean getAllowUrlInLocalInfile() {
/*  189 */     return getActiveMySQLConnection().getAllowUrlInLocalInfile();
/*      */   }
/*      */   
/*      */   public boolean getAlwaysSendSetIsolation() {
/*  193 */     return getActiveMySQLConnection().getAlwaysSendSetIsolation();
/*      */   }
/*      */   
/*      */   public boolean getAutoClosePStmtStreams() {
/*  197 */     return getActiveMySQLConnection().getAutoClosePStmtStreams();
/*      */   }
/*      */   
/*      */   public boolean getAutoDeserialize() {
/*  201 */     return getActiveMySQLConnection().getAutoDeserialize();
/*      */   }
/*      */   
/*      */   public boolean getAutoGenerateTestcaseScript() {
/*  205 */     return getActiveMySQLConnection().getAutoGenerateTestcaseScript();
/*      */   }
/*      */   
/*      */   public boolean getAutoReconnectForPools() {
/*  209 */     return getActiveMySQLConnection().getAutoReconnectForPools();
/*      */   }
/*      */   
/*      */   public boolean getAutoSlowLog() {
/*  213 */     return getActiveMySQLConnection().getAutoSlowLog();
/*      */   }
/*      */   
/*      */   public int getBlobSendChunkSize() {
/*  217 */     return getActiveMySQLConnection().getBlobSendChunkSize();
/*      */   }
/*      */   
/*      */   public boolean getBlobsAreStrings() {
/*  221 */     return getActiveMySQLConnection().getBlobsAreStrings();
/*      */   }
/*      */   
/*      */   public boolean getCacheCallableStatements() {
/*  225 */     return getActiveMySQLConnection().getCacheCallableStatements();
/*      */   }
/*      */   
/*      */   public boolean getCacheCallableStmts() {
/*  229 */     return getActiveMySQLConnection().getCacheCallableStmts();
/*      */   }
/*      */   
/*      */   public boolean getCachePrepStmts() {
/*  233 */     return getActiveMySQLConnection().getCachePrepStmts();
/*      */   }
/*      */   
/*      */   public boolean getCachePreparedStatements() {
/*  237 */     return getActiveMySQLConnection().getCachePreparedStatements();
/*      */   }
/*      */   
/*      */   public boolean getCacheResultSetMetadata() {
/*  241 */     return getActiveMySQLConnection().getCacheResultSetMetadata();
/*      */   }
/*      */   
/*      */   public boolean getCacheServerConfiguration() {
/*  245 */     return getActiveMySQLConnection().getCacheServerConfiguration();
/*      */   }
/*      */   
/*      */   public int getCallableStatementCacheSize() {
/*  249 */     return getActiveMySQLConnection().getCallableStatementCacheSize();
/*      */   }
/*      */   
/*      */   public int getCallableStmtCacheSize() {
/*  253 */     return getActiveMySQLConnection().getCallableStmtCacheSize();
/*      */   }
/*      */   
/*      */   public boolean getCapitalizeTypeNames() {
/*  257 */     return getActiveMySQLConnection().getCapitalizeTypeNames();
/*      */   }
/*      */   
/*      */   public String getCharacterSetResults() {
/*  261 */     return getActiveMySQLConnection().getCharacterSetResults();
/*      */   }
/*      */   
/*      */   public String getClientCertificateKeyStorePassword() {
/*  265 */     return getActiveMySQLConnection().getClientCertificateKeyStorePassword();
/*      */   }
/*      */   
/*      */   public String getClientCertificateKeyStoreType() {
/*  269 */     return getActiveMySQLConnection().getClientCertificateKeyStoreType();
/*      */   }
/*      */   
/*      */   public String getClientCertificateKeyStoreUrl() {
/*  273 */     return getActiveMySQLConnection().getClientCertificateKeyStoreUrl();
/*      */   }
/*      */   
/*      */   public String getClientInfoProvider() {
/*  277 */     return getActiveMySQLConnection().getClientInfoProvider();
/*      */   }
/*      */   
/*      */   public String getClobCharacterEncoding() {
/*  281 */     return getActiveMySQLConnection().getClobCharacterEncoding();
/*      */   }
/*      */   
/*      */   public boolean getClobberStreamingResults() {
/*  285 */     return getActiveMySQLConnection().getClobberStreamingResults();
/*      */   }
/*      */   
/*      */   public boolean getCompensateOnDuplicateKeyUpdateCounts() {
/*  289 */     return getActiveMySQLConnection().getCompensateOnDuplicateKeyUpdateCounts();
/*      */   }
/*      */   
/*      */   public int getConnectTimeout() {
/*  293 */     return getActiveMySQLConnection().getConnectTimeout();
/*      */   }
/*      */   
/*      */   public String getConnectionCollation() {
/*  297 */     return getActiveMySQLConnection().getConnectionCollation();
/*      */   }
/*      */   
/*      */   public String getConnectionLifecycleInterceptors() {
/*  301 */     return getActiveMySQLConnection().getConnectionLifecycleInterceptors();
/*      */   }
/*      */   
/*      */   public boolean getContinueBatchOnError() {
/*  305 */     return getActiveMySQLConnection().getContinueBatchOnError();
/*      */   }
/*      */   
/*      */   public boolean getCreateDatabaseIfNotExist() {
/*  309 */     return getActiveMySQLConnection().getCreateDatabaseIfNotExist();
/*      */   }
/*      */   
/*      */   public int getDefaultFetchSize() {
/*  313 */     return getActiveMySQLConnection().getDefaultFetchSize();
/*      */   }
/*      */   
/*      */   public boolean getDontTrackOpenResources() {
/*  317 */     return getActiveMySQLConnection().getDontTrackOpenResources();
/*      */   }
/*      */   
/*      */   public boolean getDumpMetadataOnColumnNotFound() {
/*  321 */     return getActiveMySQLConnection().getDumpMetadataOnColumnNotFound();
/*      */   }
/*      */   
/*      */   public boolean getDumpQueriesOnException() {
/*  325 */     return getActiveMySQLConnection().getDumpQueriesOnException();
/*      */   }
/*      */   
/*      */   public boolean getDynamicCalendars() {
/*  329 */     return getActiveMySQLConnection().getDynamicCalendars();
/*      */   }
/*      */   
/*      */   public boolean getElideSetAutoCommits() {
/*  333 */     return getActiveMySQLConnection().getElideSetAutoCommits();
/*      */   }
/*      */   
/*      */   public boolean getEmptyStringsConvertToZero() {
/*  337 */     return getActiveMySQLConnection().getEmptyStringsConvertToZero();
/*      */   }
/*      */   
/*      */   public boolean getEmulateLocators() {
/*  341 */     return getActiveMySQLConnection().getEmulateLocators();
/*      */   }
/*      */   
/*      */   public boolean getEmulateUnsupportedPstmts() {
/*  345 */     return getActiveMySQLConnection().getEmulateUnsupportedPstmts();
/*      */   }
/*      */   
/*      */   public boolean getEnablePacketDebug() {
/*  349 */     return getActiveMySQLConnection().getEnablePacketDebug();
/*      */   }
/*      */   
/*      */   public boolean getEnableQueryTimeouts() {
/*  353 */     return getActiveMySQLConnection().getEnableQueryTimeouts();
/*      */   }
/*      */   
/*      */   public String getEncoding() {
/*  357 */     return getActiveMySQLConnection().getEncoding();
/*      */   }
/*      */   
/*      */   public String getExceptionInterceptors() {
/*  361 */     return getActiveMySQLConnection().getExceptionInterceptors();
/*      */   }
/*      */   
/*      */   public boolean getExplainSlowQueries() {
/*  365 */     return getActiveMySQLConnection().getExplainSlowQueries();
/*      */   }
/*      */   
/*      */   public boolean getFailOverReadOnly() {
/*  369 */     return getActiveMySQLConnection().getFailOverReadOnly();
/*      */   }
/*      */   
/*      */   public boolean getFunctionsNeverReturnBlobs() {
/*  373 */     return getActiveMySQLConnection().getFunctionsNeverReturnBlobs();
/*      */   }
/*      */   
/*      */   public boolean getGatherPerfMetrics() {
/*  377 */     return getActiveMySQLConnection().getGatherPerfMetrics();
/*      */   }
/*      */   
/*      */   public boolean getGatherPerformanceMetrics() {
/*  381 */     return getActiveMySQLConnection().getGatherPerformanceMetrics();
/*      */   }
/*      */   
/*      */   public boolean getGenerateSimpleParameterMetadata() {
/*  385 */     return getActiveMySQLConnection().getGenerateSimpleParameterMetadata();
/*      */   }
/*      */   
/*      */   public boolean getIgnoreNonTxTables() {
/*  389 */     return getActiveMySQLConnection().getIgnoreNonTxTables();
/*      */   }
/*      */   
/*      */   public boolean getIncludeInnodbStatusInDeadlockExceptions() {
/*  393 */     return getActiveMySQLConnection().getIncludeInnodbStatusInDeadlockExceptions();
/*      */   }
/*      */   
/*      */   public int getInitialTimeout() {
/*  397 */     return getActiveMySQLConnection().getInitialTimeout();
/*      */   }
/*      */   
/*      */   public boolean getInteractiveClient() {
/*  401 */     return getActiveMySQLConnection().getInteractiveClient();
/*      */   }
/*      */   
/*      */   public boolean getIsInteractiveClient() {
/*  405 */     return getActiveMySQLConnection().getIsInteractiveClient();
/*      */   }
/*      */   
/*      */   public boolean getJdbcCompliantTruncation() {
/*  409 */     return getActiveMySQLConnection().getJdbcCompliantTruncation();
/*      */   }
/*      */   
/*      */   public boolean getJdbcCompliantTruncationForReads() {
/*  413 */     return getActiveMySQLConnection().getJdbcCompliantTruncationForReads();
/*      */   }
/*      */   
/*      */   public String getLargeRowSizeThreshold() {
/*  417 */     return getActiveMySQLConnection().getLargeRowSizeThreshold();
/*      */   }
/*      */   
/*      */   public int getLoadBalanceBlacklistTimeout() {
/*  421 */     return getActiveMySQLConnection().getLoadBalanceBlacklistTimeout();
/*      */   }
/*      */   
/*      */   public int getLoadBalancePingTimeout() {
/*  425 */     return getActiveMySQLConnection().getLoadBalancePingTimeout();
/*      */   }
/*      */   
/*      */   public String getLoadBalanceStrategy() {
/*  429 */     return getActiveMySQLConnection().getLoadBalanceStrategy();
/*      */   }
/*      */   
/*      */   public String getServerAffinityOrder() {
/*  433 */     return getActiveMySQLConnection().getServerAffinityOrder();
/*      */   }
/*      */   
/*      */   public boolean getLoadBalanceValidateConnectionOnSwapServer() {
/*  437 */     return getActiveMySQLConnection().getLoadBalanceValidateConnectionOnSwapServer();
/*      */   }
/*      */   
/*      */   public String getLocalSocketAddress() {
/*  441 */     return getActiveMySQLConnection().getLocalSocketAddress();
/*      */   }
/*      */   
/*      */   public int getLocatorFetchBufferSize() {
/*  445 */     return getActiveMySQLConnection().getLocatorFetchBufferSize();
/*      */   }
/*      */   
/*      */   public boolean getLogSlowQueries() {
/*  449 */     return getActiveMySQLConnection().getLogSlowQueries();
/*      */   }
/*      */   
/*      */   public boolean getLogXaCommands() {
/*  453 */     return getActiveMySQLConnection().getLogXaCommands();
/*      */   }
/*      */   
/*      */   public String getLogger() {
/*  457 */     return getActiveMySQLConnection().getLogger();
/*      */   }
/*      */   
/*      */   public String getLoggerClassName() {
/*  461 */     return getActiveMySQLConnection().getLoggerClassName();
/*      */   }
/*      */   
/*      */   public boolean getMaintainTimeStats() {
/*  465 */     return getActiveMySQLConnection().getMaintainTimeStats();
/*      */   }
/*      */   
/*      */   public int getMaxAllowedPacket() {
/*  469 */     return getActiveMySQLConnection().getMaxAllowedPacket();
/*      */   }
/*      */   
/*      */   public int getMaxQuerySizeToLog() {
/*  473 */     return getActiveMySQLConnection().getMaxQuerySizeToLog();
/*      */   }
/*      */   
/*      */   public int getMaxReconnects() {
/*  477 */     return getActiveMySQLConnection().getMaxReconnects();
/*      */   }
/*      */   
/*      */   public int getMaxRows() {
/*  481 */     return getActiveMySQLConnection().getMaxRows();
/*      */   }
/*      */   
/*      */   public int getMetadataCacheSize() {
/*  485 */     return getActiveMySQLConnection().getMetadataCacheSize();
/*      */   }
/*      */   
/*      */   public int getNetTimeoutForStreamingResults() {
/*  489 */     return getActiveMySQLConnection().getNetTimeoutForStreamingResults();
/*      */   }
/*      */   
/*      */   public boolean getNoAccessToProcedureBodies() {
/*  493 */     return getActiveMySQLConnection().getNoAccessToProcedureBodies();
/*      */   }
/*      */   
/*      */   public boolean getNoDatetimeStringSync() {
/*  497 */     return getActiveMySQLConnection().getNoDatetimeStringSync();
/*      */   }
/*      */   
/*      */   public boolean getNoTimezoneConversionForTimeType() {
/*  501 */     return getActiveMySQLConnection().getNoTimezoneConversionForTimeType();
/*      */   }
/*      */   
/*      */   public boolean getNoTimezoneConversionForDateType() {
/*  505 */     return getActiveMySQLConnection().getNoTimezoneConversionForDateType();
/*      */   }
/*      */   
/*      */   public boolean getCacheDefaultTimezone() {
/*  509 */     return getActiveMySQLConnection().getCacheDefaultTimezone();
/*      */   }
/*      */   
/*      */   public boolean getNullCatalogMeansCurrent() {
/*  513 */     return getActiveMySQLConnection().getNullCatalogMeansCurrent();
/*      */   }
/*      */   
/*      */   public boolean getNullNamePatternMatchesAll() {
/*  517 */     return getActiveMySQLConnection().getNullNamePatternMatchesAll();
/*      */   }
/*      */   
/*      */   public boolean getOverrideSupportsIntegrityEnhancementFacility() {
/*  521 */     return getActiveMySQLConnection().getOverrideSupportsIntegrityEnhancementFacility();
/*      */   }
/*      */   
/*      */   public int getPacketDebugBufferSize() {
/*  525 */     return getActiveMySQLConnection().getPacketDebugBufferSize();
/*      */   }
/*      */   
/*      */   public boolean getPadCharsWithSpace() {
/*  529 */     return getActiveMySQLConnection().getPadCharsWithSpace();
/*      */   }
/*      */   
/*      */   public boolean getParanoid() {
/*  533 */     return getActiveMySQLConnection().getParanoid();
/*      */   }
/*      */   
/*      */   public String getPasswordCharacterEncoding() {
/*  537 */     return getActiveMySQLConnection().getPasswordCharacterEncoding();
/*      */   }
/*      */   
/*      */   public boolean getPedantic() {
/*  541 */     return getActiveMySQLConnection().getPedantic();
/*      */   }
/*      */   
/*      */   public boolean getPinGlobalTxToPhysicalConnection() {
/*  545 */     return getActiveMySQLConnection().getPinGlobalTxToPhysicalConnection();
/*      */   }
/*      */   
/*      */   public boolean getPopulateInsertRowWithDefaultValues() {
/*  549 */     return getActiveMySQLConnection().getPopulateInsertRowWithDefaultValues();
/*      */   }
/*      */   
/*      */   public int getPrepStmtCacheSize() {
/*  553 */     return getActiveMySQLConnection().getPrepStmtCacheSize();
/*      */   }
/*      */   
/*      */   public int getPrepStmtCacheSqlLimit() {
/*  557 */     return getActiveMySQLConnection().getPrepStmtCacheSqlLimit();
/*      */   }
/*      */   
/*      */   public int getPreparedStatementCacheSize() {
/*  561 */     return getActiveMySQLConnection().getPreparedStatementCacheSize();
/*      */   }
/*      */   
/*      */   public int getPreparedStatementCacheSqlLimit() {
/*  565 */     return getActiveMySQLConnection().getPreparedStatementCacheSqlLimit();
/*      */   }
/*      */   
/*      */   public boolean getProcessEscapeCodesForPrepStmts() {
/*  569 */     return getActiveMySQLConnection().getProcessEscapeCodesForPrepStmts();
/*      */   }
/*      */   
/*      */   public boolean getProfileSQL() {
/*  573 */     return getActiveMySQLConnection().getProfileSQL();
/*      */   }
/*      */   
/*      */   public boolean getProfileSql() {
/*  577 */     return getActiveMySQLConnection().getProfileSql();
/*      */   }
/*      */   
/*      */   public String getProfilerEventHandler() {
/*  581 */     return getActiveMySQLConnection().getProfilerEventHandler();
/*      */   }
/*      */   
/*      */   public String getPropertiesTransform() {
/*  585 */     return getActiveMySQLConnection().getPropertiesTransform();
/*      */   }
/*      */   
/*      */   public int getQueriesBeforeRetryMaster() {
/*  589 */     return getActiveMySQLConnection().getQueriesBeforeRetryMaster();
/*      */   }
/*      */   
/*      */   public boolean getQueryTimeoutKillsConnection() {
/*  593 */     return getActiveMySQLConnection().getQueryTimeoutKillsConnection();
/*      */   }
/*      */   
/*      */   public boolean getReconnectAtTxEnd() {
/*  597 */     return getActiveMySQLConnection().getReconnectAtTxEnd();
/*      */   }
/*      */   
/*      */   public boolean getRelaxAutoCommit() {
/*  601 */     return getActiveMySQLConnection().getRelaxAutoCommit();
/*      */   }
/*      */   
/*      */   public int getReportMetricsIntervalMillis() {
/*  605 */     return getActiveMySQLConnection().getReportMetricsIntervalMillis();
/*      */   }
/*      */   
/*      */   public boolean getRequireSSL() {
/*  609 */     return getActiveMySQLConnection().getRequireSSL();
/*      */   }
/*      */   
/*      */   public String getResourceId() {
/*  613 */     return getActiveMySQLConnection().getResourceId();
/*      */   }
/*      */   
/*      */   public int getResultSetSizeThreshold() {
/*  617 */     return getActiveMySQLConnection().getResultSetSizeThreshold();
/*      */   }
/*      */   
/*      */   public boolean getRetainStatementAfterResultSetClose() {
/*  621 */     return getActiveMySQLConnection().getRetainStatementAfterResultSetClose();
/*      */   }
/*      */   
/*      */   public int getRetriesAllDown() {
/*  625 */     return getActiveMySQLConnection().getRetriesAllDown();
/*      */   }
/*      */   
/*      */   public boolean getRewriteBatchedStatements() {
/*  629 */     return getActiveMySQLConnection().getRewriteBatchedStatements();
/*      */   }
/*      */   
/*      */   public boolean getRollbackOnPooledClose() {
/*  633 */     return getActiveMySQLConnection().getRollbackOnPooledClose();
/*      */   }
/*      */   
/*      */   public boolean getRoundRobinLoadBalance() {
/*  637 */     return getActiveMySQLConnection().getRoundRobinLoadBalance();
/*      */   }
/*      */   
/*      */   public boolean getRunningCTS13() {
/*  641 */     return getActiveMySQLConnection().getRunningCTS13();
/*      */   }
/*      */   
/*      */   public int getSecondsBeforeRetryMaster() {
/*  645 */     return getActiveMySQLConnection().getSecondsBeforeRetryMaster();
/*      */   }
/*      */   
/*      */   public int getSelfDestructOnPingMaxOperations() {
/*  649 */     return getActiveMySQLConnection().getSelfDestructOnPingMaxOperations();
/*      */   }
/*      */   
/*      */   public int getSelfDestructOnPingSecondsLifetime() {
/*  653 */     return getActiveMySQLConnection().getSelfDestructOnPingSecondsLifetime();
/*      */   }
/*      */   
/*      */   public String getServerTimezone() {
/*  657 */     return getActiveMySQLConnection().getServerTimezone();
/*      */   }
/*      */   
/*      */   public String getSessionVariables() {
/*  661 */     return getActiveMySQLConnection().getSessionVariables();
/*      */   }
/*      */   
/*      */   public int getSlowQueryThresholdMillis() {
/*  665 */     return getActiveMySQLConnection().getSlowQueryThresholdMillis();
/*      */   }
/*      */   
/*      */   public long getSlowQueryThresholdNanos() {
/*  669 */     return getActiveMySQLConnection().getSlowQueryThresholdNanos();
/*      */   }
/*      */   
/*      */   public String getSocketFactory() {
/*  673 */     return getActiveMySQLConnection().getSocketFactory();
/*      */   }
/*      */   
/*      */   public String getSocketFactoryClassName() {
/*  677 */     return getActiveMySQLConnection().getSocketFactoryClassName();
/*      */   }
/*      */   
/*      */   public int getSocketTimeout() {
/*  681 */     return getActiveMySQLConnection().getSocketTimeout();
/*      */   }
/*      */   
/*      */   public String getStatementInterceptors() {
/*  685 */     return getActiveMySQLConnection().getStatementInterceptors();
/*      */   }
/*      */   
/*      */   public boolean getStrictFloatingPoint() {
/*  689 */     return getActiveMySQLConnection().getStrictFloatingPoint();
/*      */   }
/*      */   
/*      */   public boolean getStrictUpdates() {
/*  693 */     return getActiveMySQLConnection().getStrictUpdates();
/*      */   }
/*      */   
/*      */   public boolean getTcpKeepAlive() {
/*  697 */     return getActiveMySQLConnection().getTcpKeepAlive();
/*      */   }
/*      */   
/*      */   public boolean getTcpNoDelay() {
/*  701 */     return getActiveMySQLConnection().getTcpNoDelay();
/*      */   }
/*      */   
/*      */   public int getTcpRcvBuf() {
/*  705 */     return getActiveMySQLConnection().getTcpRcvBuf();
/*      */   }
/*      */   
/*      */   public int getTcpSndBuf() {
/*  709 */     return getActiveMySQLConnection().getTcpSndBuf();
/*      */   }
/*      */   
/*      */   public int getTcpTrafficClass() {
/*  713 */     return getActiveMySQLConnection().getTcpTrafficClass();
/*      */   }
/*      */   
/*      */   public boolean getTinyInt1isBit() {
/*  717 */     return getActiveMySQLConnection().getTinyInt1isBit();
/*      */   }
/*      */   
/*      */   public boolean getTraceProtocol() {
/*  721 */     return getActiveMySQLConnection().getTraceProtocol();
/*      */   }
/*      */   
/*      */   public boolean getTransformedBitIsBoolean() {
/*  725 */     return getActiveMySQLConnection().getTransformedBitIsBoolean();
/*      */   }
/*      */   
/*      */   public boolean getTreatUtilDateAsTimestamp() {
/*  729 */     return getActiveMySQLConnection().getTreatUtilDateAsTimestamp();
/*      */   }
/*      */   
/*      */   public String getTrustCertificateKeyStorePassword() {
/*  733 */     return getActiveMySQLConnection().getTrustCertificateKeyStorePassword();
/*      */   }
/*      */   
/*      */   public String getTrustCertificateKeyStoreType() {
/*  737 */     return getActiveMySQLConnection().getTrustCertificateKeyStoreType();
/*      */   }
/*      */   
/*      */   public String getTrustCertificateKeyStoreUrl() {
/*  741 */     return getActiveMySQLConnection().getTrustCertificateKeyStoreUrl();
/*      */   }
/*      */   
/*      */   public boolean getUltraDevHack() {
/*  745 */     return getActiveMySQLConnection().getUltraDevHack();
/*      */   }
/*      */   
/*      */   public boolean getUseAffectedRows() {
/*  749 */     return getActiveMySQLConnection().getUseAffectedRows();
/*      */   }
/*      */   
/*      */   public boolean getUseBlobToStoreUTF8OutsideBMP() {
/*  753 */     return getActiveMySQLConnection().getUseBlobToStoreUTF8OutsideBMP();
/*      */   }
/*      */   
/*      */   public boolean getUseColumnNamesInFindColumn() {
/*  757 */     return getActiveMySQLConnection().getUseColumnNamesInFindColumn();
/*      */   }
/*      */   
/*      */   public boolean getUseCompression() {
/*  761 */     return getActiveMySQLConnection().getUseCompression();
/*      */   }
/*      */   
/*      */   public String getUseConfigs() {
/*  765 */     return getActiveMySQLConnection().getUseConfigs();
/*      */   }
/*      */   
/*      */   public boolean getUseCursorFetch() {
/*  769 */     return getActiveMySQLConnection().getUseCursorFetch();
/*      */   }
/*      */   
/*      */   public boolean getUseDirectRowUnpack() {
/*  773 */     return getActiveMySQLConnection().getUseDirectRowUnpack();
/*      */   }
/*      */   
/*      */   public boolean getUseDynamicCharsetInfo() {
/*  777 */     return getActiveMySQLConnection().getUseDynamicCharsetInfo();
/*      */   }
/*      */   
/*      */   public boolean getUseFastDateParsing() {
/*  781 */     return getActiveMySQLConnection().getUseFastDateParsing();
/*      */   }
/*      */   
/*      */   public boolean getUseFastIntParsing() {
/*  785 */     return getActiveMySQLConnection().getUseFastIntParsing();
/*      */   }
/*      */   
/*      */   public boolean getUseGmtMillisForDatetimes() {
/*  789 */     return getActiveMySQLConnection().getUseGmtMillisForDatetimes();
/*      */   }
/*      */   
/*      */   public boolean getUseHostsInPrivileges() {
/*  793 */     return getActiveMySQLConnection().getUseHostsInPrivileges();
/*      */   }
/*      */   
/*      */   public boolean getUseInformationSchema() {
/*  797 */     return getActiveMySQLConnection().getUseInformationSchema();
/*      */   }
/*      */   
/*      */   public boolean getUseJDBCCompliantTimezoneShift() {
/*  801 */     return getActiveMySQLConnection().getUseJDBCCompliantTimezoneShift();
/*      */   }
/*      */   
/*      */   public boolean getUseJvmCharsetConverters() {
/*  805 */     return getActiveMySQLConnection().getUseJvmCharsetConverters();
/*      */   }
/*      */   
/*      */   public boolean getUseLegacyDatetimeCode() {
/*  809 */     return getActiveMySQLConnection().getUseLegacyDatetimeCode();
/*      */   }
/*      */   
/*      */   public boolean getSendFractionalSeconds() {
/*  813 */     return getActiveMySQLConnection().getSendFractionalSeconds();
/*      */   }
/*      */   
/*      */   public boolean getUseLocalSessionState() {
/*  817 */     return getActiveMySQLConnection().getUseLocalSessionState();
/*      */   }
/*      */   
/*      */   public boolean getUseLocalTransactionState() {
/*  821 */     return getActiveMySQLConnection().getUseLocalTransactionState();
/*      */   }
/*      */   
/*      */   public boolean getUseNanosForElapsedTime() {
/*  825 */     return getActiveMySQLConnection().getUseNanosForElapsedTime();
/*      */   }
/*      */   
/*      */   public boolean getUseOldAliasMetadataBehavior() {
/*  829 */     return getActiveMySQLConnection().getUseOldAliasMetadataBehavior();
/*      */   }
/*      */   
/*      */   public boolean getUseOldUTF8Behavior() {
/*  833 */     return getActiveMySQLConnection().getUseOldUTF8Behavior();
/*      */   }
/*      */   
/*      */   public boolean getUseOnlyServerErrorMessages() {
/*  837 */     return getActiveMySQLConnection().getUseOnlyServerErrorMessages();
/*      */   }
/*      */   
/*      */   public boolean getUseReadAheadInput() {
/*  841 */     return getActiveMySQLConnection().getUseReadAheadInput();
/*      */   }
/*      */   
/*      */   public boolean getUseSSL() {
/*  845 */     return getActiveMySQLConnection().getUseSSL();
/*      */   }
/*      */   
/*      */   public boolean getUseSSPSCompatibleTimezoneShift() {
/*  849 */     return getActiveMySQLConnection().getUseSSPSCompatibleTimezoneShift();
/*      */   }
/*      */   
/*      */   public boolean getUseServerPrepStmts() {
/*  853 */     return getActiveMySQLConnection().getUseServerPrepStmts();
/*      */   }
/*      */   
/*      */   public boolean getUseServerPreparedStmts() {
/*  857 */     return getActiveMySQLConnection().getUseServerPreparedStmts();
/*      */   }
/*      */   
/*      */   public boolean getUseSqlStateCodes() {
/*  861 */     return getActiveMySQLConnection().getUseSqlStateCodes();
/*      */   }
/*      */   
/*      */   public boolean getUseStreamLengthsInPrepStmts() {
/*  865 */     return getActiveMySQLConnection().getUseStreamLengthsInPrepStmts();
/*      */   }
/*      */   
/*      */   public boolean getUseTimezone() {
/*  869 */     return getActiveMySQLConnection().getUseTimezone();
/*      */   }
/*      */   
/*      */   public boolean getUseUltraDevWorkAround() {
/*  873 */     return getActiveMySQLConnection().getUseUltraDevWorkAround();
/*      */   }
/*      */   
/*      */   public boolean getUseUnbufferedInput() {
/*  877 */     return getActiveMySQLConnection().getUseUnbufferedInput();
/*      */   }
/*      */   
/*      */   public boolean getUseUnicode() {
/*  881 */     return getActiveMySQLConnection().getUseUnicode();
/*      */   }
/*      */   
/*      */   public boolean getUseUsageAdvisor() {
/*  885 */     return getActiveMySQLConnection().getUseUsageAdvisor();
/*      */   }
/*      */   
/*      */   public String getUtf8OutsideBmpExcludedColumnNamePattern() {
/*  889 */     return getActiveMySQLConnection().getUtf8OutsideBmpExcludedColumnNamePattern();
/*      */   }
/*      */   
/*      */   public String getUtf8OutsideBmpIncludedColumnNamePattern() {
/*  893 */     return getActiveMySQLConnection().getUtf8OutsideBmpIncludedColumnNamePattern();
/*      */   }
/*      */   
/*      */   public boolean getVerifyServerCertificate() {
/*  897 */     return getActiveMySQLConnection().getVerifyServerCertificate();
/*      */   }
/*      */   
/*      */   public boolean getYearIsDateType() {
/*  901 */     return getActiveMySQLConnection().getYearIsDateType();
/*      */   }
/*      */   
/*      */   public String getZeroDateTimeBehavior() {
/*  905 */     return getActiveMySQLConnection().getZeroDateTimeBehavior();
/*      */   }
/*      */   
/*      */   public void setAllowLoadLocalInfile(boolean property) {
/*  909 */     getActiveMySQLConnection().setAllowLoadLocalInfile(property);
/*      */   }
/*      */   
/*      */   public void setAllowMultiQueries(boolean property) {
/*  913 */     getActiveMySQLConnection().setAllowMultiQueries(property);
/*      */   }
/*      */   
/*      */   public void setAllowNanAndInf(boolean flag) {
/*  917 */     getActiveMySQLConnection().setAllowNanAndInf(flag);
/*      */   }
/*      */   
/*      */   public void setAllowUrlInLocalInfile(boolean flag) {
/*  921 */     getActiveMySQLConnection().setAllowUrlInLocalInfile(flag);
/*      */   }
/*      */   
/*      */   public void setAlwaysSendSetIsolation(boolean flag) {
/*  925 */     getActiveMySQLConnection().setAlwaysSendSetIsolation(flag);
/*      */   }
/*      */   
/*      */   public void setAutoClosePStmtStreams(boolean flag) {
/*  929 */     getActiveMySQLConnection().setAutoClosePStmtStreams(flag);
/*      */   }
/*      */   
/*      */   public void setAutoDeserialize(boolean flag) {
/*  933 */     getActiveMySQLConnection().setAutoDeserialize(flag);
/*      */   }
/*      */   
/*      */   public void setAutoGenerateTestcaseScript(boolean flag) {
/*  937 */     getActiveMySQLConnection().setAutoGenerateTestcaseScript(flag);
/*      */   }
/*      */   
/*      */   public void setAutoReconnect(boolean flag) {
/*  941 */     getActiveMySQLConnection().setAutoReconnect(flag);
/*      */   }
/*      */   
/*      */   public void setAutoReconnectForConnectionPools(boolean property) {
/*  945 */     getActiveMySQLConnection().setAutoReconnectForConnectionPools(property);
/*      */   }
/*      */   
/*      */   public void setAutoReconnectForPools(boolean flag) {
/*  949 */     getActiveMySQLConnection().setAutoReconnectForPools(flag);
/*      */   }
/*      */   
/*      */   public void setAutoSlowLog(boolean flag) {
/*  953 */     getActiveMySQLConnection().setAutoSlowLog(flag);
/*      */   }
/*      */   
/*      */   public void setBlobSendChunkSize(String value) throws SQLException {
/*  957 */     getActiveMySQLConnection().setBlobSendChunkSize(value);
/*      */   }
/*      */   
/*      */   public void setBlobsAreStrings(boolean flag) {
/*  961 */     getActiveMySQLConnection().setBlobsAreStrings(flag);
/*      */   }
/*      */   
/*      */   public void setCacheCallableStatements(boolean flag) {
/*  965 */     getActiveMySQLConnection().setCacheCallableStatements(flag);
/*      */   }
/*      */   
/*      */   public void setCacheCallableStmts(boolean flag) {
/*  969 */     getActiveMySQLConnection().setCacheCallableStmts(flag);
/*      */   }
/*      */   
/*      */   public void setCachePrepStmts(boolean flag) {
/*  973 */     getActiveMySQLConnection().setCachePrepStmts(flag);
/*      */   }
/*      */   
/*      */   public void setCachePreparedStatements(boolean flag) {
/*  977 */     getActiveMySQLConnection().setCachePreparedStatements(flag);
/*      */   }
/*      */   
/*      */   public void setCacheResultSetMetadata(boolean property) {
/*  981 */     getActiveMySQLConnection().setCacheResultSetMetadata(property);
/*      */   }
/*      */   
/*      */   public void setCacheServerConfiguration(boolean flag) {
/*  985 */     getActiveMySQLConnection().setCacheServerConfiguration(flag);
/*      */   }
/*      */   
/*      */   public void setCallableStatementCacheSize(int size) throws SQLException {
/*  989 */     getActiveMySQLConnection().setCallableStatementCacheSize(size);
/*      */   }
/*      */   
/*      */   public void setCallableStmtCacheSize(int cacheSize) throws SQLException {
/*  993 */     getActiveMySQLConnection().setCallableStmtCacheSize(cacheSize);
/*      */   }
/*      */   
/*      */   public void setCapitalizeDBMDTypes(boolean property) {
/*  997 */     getActiveMySQLConnection().setCapitalizeDBMDTypes(property);
/*      */   }
/*      */   
/*      */   public void setCapitalizeTypeNames(boolean flag) {
/* 1001 */     getActiveMySQLConnection().setCapitalizeTypeNames(flag);
/*      */   }
/*      */   
/*      */   public void setCharacterEncoding(String encoding) {
/* 1005 */     getActiveMySQLConnection().setCharacterEncoding(encoding);
/*      */   }
/*      */   
/*      */   public void setCharacterSetResults(String characterSet) {
/* 1009 */     getActiveMySQLConnection().setCharacterSetResults(characterSet);
/*      */   }
/*      */   
/*      */   public void setClientCertificateKeyStorePassword(String value) {
/* 1013 */     getActiveMySQLConnection().setClientCertificateKeyStorePassword(value);
/*      */   }
/*      */   
/*      */   public void setClientCertificateKeyStoreType(String value) {
/* 1017 */     getActiveMySQLConnection().setClientCertificateKeyStoreType(value);
/*      */   }
/*      */   
/*      */   public void setClientCertificateKeyStoreUrl(String value) {
/* 1021 */     getActiveMySQLConnection().setClientCertificateKeyStoreUrl(value);
/*      */   }
/*      */   
/*      */   public void setClientInfoProvider(String classname) {
/* 1025 */     getActiveMySQLConnection().setClientInfoProvider(classname);
/*      */   }
/*      */   
/*      */   public void setClobCharacterEncoding(String encoding) {
/* 1029 */     getActiveMySQLConnection().setClobCharacterEncoding(encoding);
/*      */   }
/*      */   
/*      */   public void setClobberStreamingResults(boolean flag) {
/* 1033 */     getActiveMySQLConnection().setClobberStreamingResults(flag);
/*      */   }
/*      */   
/*      */   public void setCompensateOnDuplicateKeyUpdateCounts(boolean flag) {
/* 1037 */     getActiveMySQLConnection().setCompensateOnDuplicateKeyUpdateCounts(flag);
/*      */   }
/*      */   
/*      */   public void setConnectTimeout(int timeoutMs) throws SQLException {
/* 1041 */     getActiveMySQLConnection().setConnectTimeout(timeoutMs);
/*      */   }
/*      */   
/*      */   public void setConnectionCollation(String collation) {
/* 1045 */     getActiveMySQLConnection().setConnectionCollation(collation);
/*      */   }
/*      */   
/*      */   public void setConnectionLifecycleInterceptors(String interceptors) {
/* 1049 */     getActiveMySQLConnection().setConnectionLifecycleInterceptors(interceptors);
/*      */   }
/*      */   
/*      */   public void setContinueBatchOnError(boolean property) {
/* 1053 */     getActiveMySQLConnection().setContinueBatchOnError(property);
/*      */   }
/*      */   
/*      */   public void setCreateDatabaseIfNotExist(boolean flag) {
/* 1057 */     getActiveMySQLConnection().setCreateDatabaseIfNotExist(flag);
/*      */   }
/*      */   
/*      */   public void setDefaultFetchSize(int n) throws SQLException {
/* 1061 */     getActiveMySQLConnection().setDefaultFetchSize(n);
/*      */   }
/*      */   
/*      */   public void setDetectServerPreparedStmts(boolean property) {
/* 1065 */     getActiveMySQLConnection().setDetectServerPreparedStmts(property);
/*      */   }
/*      */   
/*      */   public void setDontTrackOpenResources(boolean flag) {
/* 1069 */     getActiveMySQLConnection().setDontTrackOpenResources(flag);
/*      */   }
/*      */   
/*      */   public void setDumpMetadataOnColumnNotFound(boolean flag) {
/* 1073 */     getActiveMySQLConnection().setDumpMetadataOnColumnNotFound(flag);
/*      */   }
/*      */   
/*      */   public void setDumpQueriesOnException(boolean flag) {
/* 1077 */     getActiveMySQLConnection().setDumpQueriesOnException(flag);
/*      */   }
/*      */   
/*      */   public void setDynamicCalendars(boolean flag) {
/* 1081 */     getActiveMySQLConnection().setDynamicCalendars(flag);
/*      */   }
/*      */   
/*      */   public void setElideSetAutoCommits(boolean flag) {
/* 1085 */     getActiveMySQLConnection().setElideSetAutoCommits(flag);
/*      */   }
/*      */   
/*      */   public void setEmptyStringsConvertToZero(boolean flag) {
/* 1089 */     getActiveMySQLConnection().setEmptyStringsConvertToZero(flag);
/*      */   }
/*      */   
/*      */   public void setEmulateLocators(boolean property) {
/* 1093 */     getActiveMySQLConnection().setEmulateLocators(property);
/*      */   }
/*      */   
/*      */   public void setEmulateUnsupportedPstmts(boolean flag) {
/* 1097 */     getActiveMySQLConnection().setEmulateUnsupportedPstmts(flag);
/*      */   }
/*      */   
/*      */   public void setEnablePacketDebug(boolean flag) {
/* 1101 */     getActiveMySQLConnection().setEnablePacketDebug(flag);
/*      */   }
/*      */   
/*      */   public void setEnableQueryTimeouts(boolean flag) {
/* 1105 */     getActiveMySQLConnection().setEnableQueryTimeouts(flag);
/*      */   }
/*      */   
/*      */   public void setEncoding(String property) {
/* 1109 */     getActiveMySQLConnection().setEncoding(property);
/*      */   }
/*      */   
/*      */   public void setExceptionInterceptors(String exceptionInterceptors) {
/* 1113 */     getActiveMySQLConnection().setExceptionInterceptors(exceptionInterceptors);
/*      */   }
/*      */   
/*      */   public void setExplainSlowQueries(boolean flag) {
/* 1117 */     getActiveMySQLConnection().setExplainSlowQueries(flag);
/*      */   }
/*      */   
/*      */   public void setFailOverReadOnly(boolean flag) {
/* 1121 */     getActiveMySQLConnection().setFailOverReadOnly(flag);
/*      */   }
/*      */   
/*      */   public void setFunctionsNeverReturnBlobs(boolean flag) {
/* 1125 */     getActiveMySQLConnection().setFunctionsNeverReturnBlobs(flag);
/*      */   }
/*      */   
/*      */   public void setGatherPerfMetrics(boolean flag) {
/* 1129 */     getActiveMySQLConnection().setGatherPerfMetrics(flag);
/*      */   }
/*      */   
/*      */   public void setGatherPerformanceMetrics(boolean flag) {
/* 1133 */     getActiveMySQLConnection().setGatherPerformanceMetrics(flag);
/*      */   }
/*      */   
/*      */   public void setGenerateSimpleParameterMetadata(boolean flag) {
/* 1137 */     getActiveMySQLConnection().setGenerateSimpleParameterMetadata(flag);
/*      */   }
/*      */   
/*      */   public void setHoldResultsOpenOverStatementClose(boolean flag) {
/* 1141 */     getActiveMySQLConnection().setHoldResultsOpenOverStatementClose(flag);
/*      */   }
/*      */   
/*      */   public void setIgnoreNonTxTables(boolean property) {
/* 1145 */     getActiveMySQLConnection().setIgnoreNonTxTables(property);
/*      */   }
/*      */   
/*      */   public void setIncludeInnodbStatusInDeadlockExceptions(boolean flag) {
/* 1149 */     getActiveMySQLConnection().setIncludeInnodbStatusInDeadlockExceptions(flag);
/*      */   }
/*      */   
/*      */   public void setInitialTimeout(int property) throws SQLException {
/* 1153 */     getActiveMySQLConnection().setInitialTimeout(property);
/*      */   }
/*      */   
/*      */   public void setInteractiveClient(boolean property) {
/* 1157 */     getActiveMySQLConnection().setInteractiveClient(property);
/*      */   }
/*      */   
/*      */   public void setIsInteractiveClient(boolean property) {
/* 1161 */     getActiveMySQLConnection().setIsInteractiveClient(property);
/*      */   }
/*      */   
/*      */   public void setJdbcCompliantTruncation(boolean flag) {
/* 1165 */     getActiveMySQLConnection().setJdbcCompliantTruncation(flag);
/*      */   }
/*      */   
/*      */   public void setJdbcCompliantTruncationForReads(boolean jdbcCompliantTruncationForReads) {
/* 1169 */     getActiveMySQLConnection().setJdbcCompliantTruncationForReads(jdbcCompliantTruncationForReads);
/*      */   }
/*      */   
/*      */   public void setLargeRowSizeThreshold(String value) throws SQLException {
/* 1173 */     getActiveMySQLConnection().setLargeRowSizeThreshold(value);
/*      */   }
/*      */   
/*      */   public void setLoadBalanceBlacklistTimeout(int loadBalanceBlacklistTimeout) throws SQLException {
/* 1177 */     getActiveMySQLConnection().setLoadBalanceBlacklistTimeout(loadBalanceBlacklistTimeout);
/*      */   }
/*      */   
/*      */   public void setLoadBalancePingTimeout(int loadBalancePingTimeout) throws SQLException {
/* 1181 */     getActiveMySQLConnection().setLoadBalancePingTimeout(loadBalancePingTimeout);
/*      */   }
/*      */   
/*      */   public void setLoadBalanceStrategy(String strategy) {
/* 1185 */     getActiveMySQLConnection().setLoadBalanceStrategy(strategy);
/*      */   }
/*      */   
/*      */   public void setServerAffinityOrder(String hostsList) {
/* 1189 */     getActiveMySQLConnection().setServerAffinityOrder(hostsList);
/*      */   }
/*      */   
/*      */   public void setLoadBalanceValidateConnectionOnSwapServer(boolean loadBalanceValidateConnectionOnSwapServer) {
/* 1193 */     getActiveMySQLConnection().setLoadBalanceValidateConnectionOnSwapServer(loadBalanceValidateConnectionOnSwapServer);
/*      */   }
/*      */   
/*      */   public void setLocalSocketAddress(String address) {
/* 1197 */     getActiveMySQLConnection().setLocalSocketAddress(address);
/*      */   }
/*      */   
/*      */   public void setLocatorFetchBufferSize(String value) throws SQLException {
/* 1201 */     getActiveMySQLConnection().setLocatorFetchBufferSize(value);
/*      */   }
/*      */   
/*      */   public void setLogSlowQueries(boolean flag) {
/* 1205 */     getActiveMySQLConnection().setLogSlowQueries(flag);
/*      */   }
/*      */   
/*      */   public void setLogXaCommands(boolean flag) {
/* 1209 */     getActiveMySQLConnection().setLogXaCommands(flag);
/*      */   }
/*      */   
/*      */   public void setLogger(String property) {
/* 1213 */     getActiveMySQLConnection().setLogger(property);
/*      */   }
/*      */   
/*      */   public void setLoggerClassName(String className) {
/* 1217 */     getActiveMySQLConnection().setLoggerClassName(className);
/*      */   }
/*      */   
/*      */   public void setMaintainTimeStats(boolean flag) {
/* 1221 */     getActiveMySQLConnection().setMaintainTimeStats(flag);
/*      */   }
/*      */   
/*      */   public void setMaxQuerySizeToLog(int sizeInBytes) throws SQLException {
/* 1225 */     getActiveMySQLConnection().setMaxQuerySizeToLog(sizeInBytes);
/*      */   }
/*      */   
/*      */   public void setMaxReconnects(int property) throws SQLException {
/* 1229 */     getActiveMySQLConnection().setMaxReconnects(property);
/*      */   }
/*      */   
/*      */   public void setMaxRows(int property) throws SQLException {
/* 1233 */     getActiveMySQLConnection().setMaxRows(property);
/*      */   }
/*      */   
/*      */   public void setMetadataCacheSize(int value) throws SQLException {
/* 1237 */     getActiveMySQLConnection().setMetadataCacheSize(value);
/*      */   }
/*      */   
/*      */   public void setNetTimeoutForStreamingResults(int value) throws SQLException {
/* 1241 */     getActiveMySQLConnection().setNetTimeoutForStreamingResults(value);
/*      */   }
/*      */   
/*      */   public void setNoAccessToProcedureBodies(boolean flag) {
/* 1245 */     getActiveMySQLConnection().setNoAccessToProcedureBodies(flag);
/*      */   }
/*      */   
/*      */   public void setNoDatetimeStringSync(boolean flag) {
/* 1249 */     getActiveMySQLConnection().setNoDatetimeStringSync(flag);
/*      */   }
/*      */   
/*      */   public void setNoTimezoneConversionForTimeType(boolean flag) {
/* 1253 */     getActiveMySQLConnection().setNoTimezoneConversionForTimeType(flag);
/*      */   }
/*      */   
/*      */   public void setNoTimezoneConversionForDateType(boolean flag) {
/* 1257 */     getActiveMySQLConnection().setNoTimezoneConversionForDateType(flag);
/*      */   }
/*      */   
/*      */   public void setCacheDefaultTimezone(boolean flag) {
/* 1261 */     getActiveMySQLConnection().setCacheDefaultTimezone(flag);
/*      */   }
/*      */   
/*      */   public void setNullCatalogMeansCurrent(boolean value) {
/* 1265 */     getActiveMySQLConnection().setNullCatalogMeansCurrent(value);
/*      */   }
/*      */   
/*      */   public void setNullNamePatternMatchesAll(boolean value) {
/* 1269 */     getActiveMySQLConnection().setNullNamePatternMatchesAll(value);
/*      */   }
/*      */   
/*      */   public void setOverrideSupportsIntegrityEnhancementFacility(boolean flag) {
/* 1273 */     getActiveMySQLConnection().setOverrideSupportsIntegrityEnhancementFacility(flag);
/*      */   }
/*      */   
/*      */   public void setPacketDebugBufferSize(int size) throws SQLException {
/* 1277 */     getActiveMySQLConnection().setPacketDebugBufferSize(size);
/*      */   }
/*      */   
/*      */   public void setPadCharsWithSpace(boolean flag) {
/* 1281 */     getActiveMySQLConnection().setPadCharsWithSpace(flag);
/*      */   }
/*      */   
/*      */   public void setParanoid(boolean property) {
/* 1285 */     getActiveMySQLConnection().setParanoid(property);
/*      */   }
/*      */   
/*      */   public void setPasswordCharacterEncoding(String characterSet) {
/* 1289 */     getActiveMySQLConnection().setPasswordCharacterEncoding(characterSet);
/*      */   }
/*      */   
/*      */   public void setPedantic(boolean property) {
/* 1293 */     getActiveMySQLConnection().setPedantic(property);
/*      */   }
/*      */   
/*      */   public void setPinGlobalTxToPhysicalConnection(boolean flag) {
/* 1297 */     getActiveMySQLConnection().setPinGlobalTxToPhysicalConnection(flag);
/*      */   }
/*      */   
/*      */   public void setPopulateInsertRowWithDefaultValues(boolean flag) {
/* 1301 */     getActiveMySQLConnection().setPopulateInsertRowWithDefaultValues(flag);
/*      */   }
/*      */   
/*      */   public void setPrepStmtCacheSize(int cacheSize) throws SQLException {
/* 1305 */     getActiveMySQLConnection().setPrepStmtCacheSize(cacheSize);
/*      */   }
/*      */   
/*      */   public void setPrepStmtCacheSqlLimit(int sqlLimit) throws SQLException {
/* 1309 */     getActiveMySQLConnection().setPrepStmtCacheSqlLimit(sqlLimit);
/*      */   }
/*      */   
/*      */   public void setPreparedStatementCacheSize(int cacheSize) throws SQLException {
/* 1313 */     getActiveMySQLConnection().setPreparedStatementCacheSize(cacheSize);
/*      */   }
/*      */   
/*      */   public void setPreparedStatementCacheSqlLimit(int cacheSqlLimit) throws SQLException {
/* 1317 */     getActiveMySQLConnection().setPreparedStatementCacheSqlLimit(cacheSqlLimit);
/*      */   }
/*      */   
/*      */   public void setProcessEscapeCodesForPrepStmts(boolean flag) {
/* 1321 */     getActiveMySQLConnection().setProcessEscapeCodesForPrepStmts(flag);
/*      */   }
/*      */   
/*      */   public void setProfileSQL(boolean flag) {
/* 1325 */     getActiveMySQLConnection().setProfileSQL(flag);
/*      */   }
/*      */   
/*      */   public void setProfileSql(boolean property) {
/* 1329 */     getActiveMySQLConnection().setProfileSql(property);
/*      */   }
/*      */   
/*      */   public void setProfilerEventHandler(String handler) {
/* 1333 */     getActiveMySQLConnection().setProfilerEventHandler(handler);
/*      */   }
/*      */   
/*      */   public void setPropertiesTransform(String value) {
/* 1337 */     getActiveMySQLConnection().setPropertiesTransform(value);
/*      */   }
/*      */   
/*      */   public void setQueriesBeforeRetryMaster(int property) throws SQLException {
/* 1341 */     getActiveMySQLConnection().setQueriesBeforeRetryMaster(property);
/*      */   }
/*      */   
/*      */   public void setQueryTimeoutKillsConnection(boolean queryTimeoutKillsConnection) {
/* 1345 */     getActiveMySQLConnection().setQueryTimeoutKillsConnection(queryTimeoutKillsConnection);
/*      */   }
/*      */   
/*      */   public void setReconnectAtTxEnd(boolean property) {
/* 1349 */     getActiveMySQLConnection().setReconnectAtTxEnd(property);
/*      */   }
/*      */   
/*      */   public void setRelaxAutoCommit(boolean property) {
/* 1353 */     getActiveMySQLConnection().setRelaxAutoCommit(property);
/*      */   }
/*      */   
/*      */   public void setReportMetricsIntervalMillis(int millis) throws SQLException {
/* 1357 */     getActiveMySQLConnection().setReportMetricsIntervalMillis(millis);
/*      */   }
/*      */   
/*      */   public void setRequireSSL(boolean property) {
/* 1361 */     getActiveMySQLConnection().setRequireSSL(property);
/*      */   }
/*      */   
/*      */   public void setResourceId(String resourceId) {
/* 1365 */     getActiveMySQLConnection().setResourceId(resourceId);
/*      */   }
/*      */   
/*      */   public void setResultSetSizeThreshold(int threshold) throws SQLException {
/* 1369 */     getActiveMySQLConnection().setResultSetSizeThreshold(threshold);
/*      */   }
/*      */   
/*      */   public void setRetainStatementAfterResultSetClose(boolean flag) {
/* 1373 */     getActiveMySQLConnection().setRetainStatementAfterResultSetClose(flag);
/*      */   }
/*      */   
/*      */   public void setRetriesAllDown(int retriesAllDown) throws SQLException {
/* 1377 */     getActiveMySQLConnection().setRetriesAllDown(retriesAllDown);
/*      */   }
/*      */   
/*      */   public void setRewriteBatchedStatements(boolean flag) {
/* 1381 */     getActiveMySQLConnection().setRewriteBatchedStatements(flag);
/*      */   }
/*      */   
/*      */   public void setRollbackOnPooledClose(boolean flag) {
/* 1385 */     getActiveMySQLConnection().setRollbackOnPooledClose(flag);
/*      */   }
/*      */   
/*      */   public void setRoundRobinLoadBalance(boolean flag) {
/* 1389 */     getActiveMySQLConnection().setRoundRobinLoadBalance(flag);
/*      */   }
/*      */   
/*      */   public void setRunningCTS13(boolean flag) {
/* 1393 */     getActiveMySQLConnection().setRunningCTS13(flag);
/*      */   }
/*      */   
/*      */   public void setSecondsBeforeRetryMaster(int property) throws SQLException {
/* 1397 */     getActiveMySQLConnection().setSecondsBeforeRetryMaster(property);
/*      */   }
/*      */   
/*      */   public void setSelfDestructOnPingMaxOperations(int maxOperations) throws SQLException {
/* 1401 */     getActiveMySQLConnection().setSelfDestructOnPingMaxOperations(maxOperations);
/*      */   }
/*      */   
/*      */   public void setSelfDestructOnPingSecondsLifetime(int seconds) throws SQLException {
/* 1405 */     getActiveMySQLConnection().setSelfDestructOnPingSecondsLifetime(seconds);
/*      */   }
/*      */   
/*      */   public void setServerTimezone(String property) {
/* 1409 */     getActiveMySQLConnection().setServerTimezone(property);
/*      */   }
/*      */   
/*      */   public void setSessionVariables(String variables) {
/* 1413 */     getActiveMySQLConnection().setSessionVariables(variables);
/*      */   }
/*      */   
/*      */   public void setSlowQueryThresholdMillis(int millis) throws SQLException {
/* 1417 */     getActiveMySQLConnection().setSlowQueryThresholdMillis(millis);
/*      */   }
/*      */   
/*      */   public void setSlowQueryThresholdNanos(long nanos) throws SQLException {
/* 1421 */     getActiveMySQLConnection().setSlowQueryThresholdNanos(nanos);
/*      */   }
/*      */   
/*      */   public void setSocketFactory(String name) {
/* 1425 */     getActiveMySQLConnection().setSocketFactory(name);
/*      */   }
/*      */   
/*      */   public void setSocketFactoryClassName(String property) {
/* 1429 */     getActiveMySQLConnection().setSocketFactoryClassName(property);
/*      */   }
/*      */   
/*      */   public void setSocketTimeout(int property) throws SQLException {
/* 1433 */     getActiveMySQLConnection().setSocketTimeout(property);
/*      */   }
/*      */   
/*      */   public void setStatementInterceptors(String value) {
/* 1437 */     getActiveMySQLConnection().setStatementInterceptors(value);
/*      */   }
/*      */   
/*      */   public void setStrictFloatingPoint(boolean property) {
/* 1441 */     getActiveMySQLConnection().setStrictFloatingPoint(property);
/*      */   }
/*      */   
/*      */   public void setStrictUpdates(boolean property) {
/* 1445 */     getActiveMySQLConnection().setStrictUpdates(property);
/*      */   }
/*      */   
/*      */   public void setTcpKeepAlive(boolean flag) {
/* 1449 */     getActiveMySQLConnection().setTcpKeepAlive(flag);
/*      */   }
/*      */   
/*      */   public void setTcpNoDelay(boolean flag) {
/* 1453 */     getActiveMySQLConnection().setTcpNoDelay(flag);
/*      */   }
/*      */   
/*      */   public void setTcpRcvBuf(int bufSize) throws SQLException {
/* 1457 */     getActiveMySQLConnection().setTcpRcvBuf(bufSize);
/*      */   }
/*      */   
/*      */   public void setTcpSndBuf(int bufSize) throws SQLException {
/* 1461 */     getActiveMySQLConnection().setTcpSndBuf(bufSize);
/*      */   }
/*      */   
/*      */   public void setTcpTrafficClass(int classFlags) throws SQLException {
/* 1465 */     getActiveMySQLConnection().setTcpTrafficClass(classFlags);
/*      */   }
/*      */   
/*      */   public void setTinyInt1isBit(boolean flag) {
/* 1469 */     getActiveMySQLConnection().setTinyInt1isBit(flag);
/*      */   }
/*      */   
/*      */   public void setTraceProtocol(boolean flag) {
/* 1473 */     getActiveMySQLConnection().setTraceProtocol(flag);
/*      */   }
/*      */   
/*      */   public void setTransformedBitIsBoolean(boolean flag) {
/* 1477 */     getActiveMySQLConnection().setTransformedBitIsBoolean(flag);
/*      */   }
/*      */   
/*      */   public void setTreatUtilDateAsTimestamp(boolean flag) {
/* 1481 */     getActiveMySQLConnection().setTreatUtilDateAsTimestamp(flag);
/*      */   }
/*      */   
/*      */   public void setTrustCertificateKeyStorePassword(String value) {
/* 1485 */     getActiveMySQLConnection().setTrustCertificateKeyStorePassword(value);
/*      */   }
/*      */   
/*      */   public void setTrustCertificateKeyStoreType(String value) {
/* 1489 */     getActiveMySQLConnection().setTrustCertificateKeyStoreType(value);
/*      */   }
/*      */   
/*      */   public void setTrustCertificateKeyStoreUrl(String value) {
/* 1493 */     getActiveMySQLConnection().setTrustCertificateKeyStoreUrl(value);
/*      */   }
/*      */   
/*      */   public void setUltraDevHack(boolean flag) {
/* 1497 */     getActiveMySQLConnection().setUltraDevHack(flag);
/*      */   }
/*      */   
/*      */   public void setUseAffectedRows(boolean flag) {
/* 1501 */     getActiveMySQLConnection().setUseAffectedRows(flag);
/*      */   }
/*      */   
/*      */   public void setUseBlobToStoreUTF8OutsideBMP(boolean flag) {
/* 1505 */     getActiveMySQLConnection().setUseBlobToStoreUTF8OutsideBMP(flag);
/*      */   }
/*      */   
/*      */   public void setUseColumnNamesInFindColumn(boolean flag) {
/* 1509 */     getActiveMySQLConnection().setUseColumnNamesInFindColumn(flag);
/*      */   }
/*      */   
/*      */   public void setUseCompression(boolean property) {
/* 1513 */     getActiveMySQLConnection().setUseCompression(property);
/*      */   }
/*      */   
/*      */   public void setUseConfigs(String configs) {
/* 1517 */     getActiveMySQLConnection().setUseConfigs(configs);
/*      */   }
/*      */   
/*      */   public void setUseCursorFetch(boolean flag) {
/* 1521 */     getActiveMySQLConnection().setUseCursorFetch(flag);
/*      */   }
/*      */   
/*      */   public void setUseDirectRowUnpack(boolean flag) {
/* 1525 */     getActiveMySQLConnection().setUseDirectRowUnpack(flag);
/*      */   }
/*      */   
/*      */   public void setUseDynamicCharsetInfo(boolean flag) {
/* 1529 */     getActiveMySQLConnection().setUseDynamicCharsetInfo(flag);
/*      */   }
/*      */   
/*      */   public void setUseFastDateParsing(boolean flag) {
/* 1533 */     getActiveMySQLConnection().setUseFastDateParsing(flag);
/*      */   }
/*      */   
/*      */   public void setUseFastIntParsing(boolean flag) {
/* 1537 */     getActiveMySQLConnection().setUseFastIntParsing(flag);
/*      */   }
/*      */   
/*      */   public void setUseGmtMillisForDatetimes(boolean flag) {
/* 1541 */     getActiveMySQLConnection().setUseGmtMillisForDatetimes(flag);
/*      */   }
/*      */   
/*      */   public void setUseHostsInPrivileges(boolean property) {
/* 1545 */     getActiveMySQLConnection().setUseHostsInPrivileges(property);
/*      */   }
/*      */   
/*      */   public void setUseInformationSchema(boolean flag) {
/* 1549 */     getActiveMySQLConnection().setUseInformationSchema(flag);
/*      */   }
/*      */   
/*      */   public void setUseJDBCCompliantTimezoneShift(boolean flag) {
/* 1553 */     getActiveMySQLConnection().setUseJDBCCompliantTimezoneShift(flag);
/*      */   }
/*      */   
/*      */   public void setUseJvmCharsetConverters(boolean flag) {
/* 1557 */     getActiveMySQLConnection().setUseJvmCharsetConverters(flag);
/*      */   }
/*      */   
/*      */   public void setUseLegacyDatetimeCode(boolean flag) {
/* 1561 */     getActiveMySQLConnection().setUseLegacyDatetimeCode(flag);
/*      */   }
/*      */   
/*      */   public void setSendFractionalSeconds(boolean flag) {
/* 1565 */     getActiveMySQLConnection().setSendFractionalSeconds(flag);
/*      */   }
/*      */   
/*      */   public void setUseLocalSessionState(boolean flag) {
/* 1569 */     getActiveMySQLConnection().setUseLocalSessionState(flag);
/*      */   }
/*      */   
/*      */   public void setUseLocalTransactionState(boolean flag) {
/* 1573 */     getActiveMySQLConnection().setUseLocalTransactionState(flag);
/*      */   }
/*      */   
/*      */   public void setUseNanosForElapsedTime(boolean flag) {
/* 1577 */     getActiveMySQLConnection().setUseNanosForElapsedTime(flag);
/*      */   }
/*      */   
/*      */   public void setUseOldAliasMetadataBehavior(boolean flag) {
/* 1581 */     getActiveMySQLConnection().setUseOldAliasMetadataBehavior(flag);
/*      */   }
/*      */   
/*      */   public void setUseOldUTF8Behavior(boolean flag) {
/* 1585 */     getActiveMySQLConnection().setUseOldUTF8Behavior(flag);
/*      */   }
/*      */   
/*      */   public void setUseOnlyServerErrorMessages(boolean flag) {
/* 1589 */     getActiveMySQLConnection().setUseOnlyServerErrorMessages(flag);
/*      */   }
/*      */   
/*      */   public void setUseReadAheadInput(boolean flag) {
/* 1593 */     getActiveMySQLConnection().setUseReadAheadInput(flag);
/*      */   }
/*      */   
/*      */   public void setUseSSL(boolean property) {
/* 1597 */     getActiveMySQLConnection().setUseSSL(property);
/*      */   }
/*      */   
/*      */   public void setUseSSPSCompatibleTimezoneShift(boolean flag) {
/* 1601 */     getActiveMySQLConnection().setUseSSPSCompatibleTimezoneShift(flag);
/*      */   }
/*      */   
/*      */   public void setUseServerPrepStmts(boolean flag) {
/* 1605 */     getActiveMySQLConnection().setUseServerPrepStmts(flag);
/*      */   }
/*      */   
/*      */   public void setUseServerPreparedStmts(boolean flag) {
/* 1609 */     getActiveMySQLConnection().setUseServerPreparedStmts(flag);
/*      */   }
/*      */   
/*      */   public void setUseSqlStateCodes(boolean flag) {
/* 1613 */     getActiveMySQLConnection().setUseSqlStateCodes(flag);
/*      */   }
/*      */   
/*      */   public void setUseStreamLengthsInPrepStmts(boolean property) {
/* 1617 */     getActiveMySQLConnection().setUseStreamLengthsInPrepStmts(property);
/*      */   }
/*      */   
/*      */   public void setUseTimezone(boolean property) {
/* 1621 */     getActiveMySQLConnection().setUseTimezone(property);
/*      */   }
/*      */   
/*      */   public void setUseUltraDevWorkAround(boolean property) {
/* 1625 */     getActiveMySQLConnection().setUseUltraDevWorkAround(property);
/*      */   }
/*      */   
/*      */   public void setUseUnbufferedInput(boolean flag) {
/* 1629 */     getActiveMySQLConnection().setUseUnbufferedInput(flag);
/*      */   }
/*      */   
/*      */   public void setUseUnicode(boolean flag) {
/* 1633 */     getActiveMySQLConnection().setUseUnicode(flag);
/*      */   }
/*      */   
/*      */   public void setUseUsageAdvisor(boolean useUsageAdvisorFlag) {
/* 1637 */     getActiveMySQLConnection().setUseUsageAdvisor(useUsageAdvisorFlag);
/*      */   }
/*      */   
/*      */   public void setUtf8OutsideBmpExcludedColumnNamePattern(String regexPattern) {
/* 1641 */     getActiveMySQLConnection().setUtf8OutsideBmpExcludedColumnNamePattern(regexPattern);
/*      */   }
/*      */   
/*      */   public void setUtf8OutsideBmpIncludedColumnNamePattern(String regexPattern) {
/* 1645 */     getActiveMySQLConnection().setUtf8OutsideBmpIncludedColumnNamePattern(regexPattern);
/*      */   }
/*      */   
/*      */   public void setVerifyServerCertificate(boolean flag) {
/* 1649 */     getActiveMySQLConnection().setVerifyServerCertificate(flag);
/*      */   }
/*      */   
/*      */   public void setYearIsDateType(boolean flag) {
/* 1653 */     getActiveMySQLConnection().setYearIsDateType(flag);
/*      */   }
/*      */   
/*      */   public void setZeroDateTimeBehavior(String behavior) {
/* 1657 */     getActiveMySQLConnection().setZeroDateTimeBehavior(behavior);
/*      */   }
/*      */   
/*      */   public boolean useUnbufferedInput() {
/* 1661 */     return getActiveMySQLConnection().useUnbufferedInput();
/*      */   }
/*      */   
/*      */   public StringBuilder generateConnectionCommentBlock(StringBuilder buf) {
/* 1665 */     return getActiveMySQLConnection().generateConnectionCommentBlock(buf);
/*      */   }
/*      */   
/*      */   public int getActiveStatementCount() {
/* 1669 */     return getActiveMySQLConnection().getActiveStatementCount();
/*      */   }
/*      */   
/*      */   public boolean getAutoCommit() throws SQLException {
/* 1673 */     return getActiveMySQLConnection().getAutoCommit();
/*      */   }
/*      */   
/*      */   public int getAutoIncrementIncrement() {
/* 1677 */     return getActiveMySQLConnection().getAutoIncrementIncrement();
/*      */   }
/*      */   
/*      */   public CachedResultSetMetaData getCachedMetaData(String sql) {
/* 1681 */     return getActiveMySQLConnection().getCachedMetaData(sql);
/*      */   }
/*      */   
/*      */   public Calendar getCalendarInstanceForSessionOrNew() {
/* 1685 */     return getActiveMySQLConnection().getCalendarInstanceForSessionOrNew();
/*      */   }
/*      */   
/*      */   public Timer getCancelTimer() {
/* 1689 */     return getActiveMySQLConnection().getCancelTimer();
/*      */   }
/*      */   
/*      */   public String getCatalog() throws SQLException {
/* 1693 */     return getActiveMySQLConnection().getCatalog();
/*      */   }
/*      */   
/*      */   public String getCharacterSetMetadata() {
/* 1697 */     return getActiveMySQLConnection().getCharacterSetMetadata();
/*      */   }
/*      */   
/*      */   public SingleByteCharsetConverter getCharsetConverter(String javaEncodingName) throws SQLException {
/* 1701 */     return getActiveMySQLConnection().getCharsetConverter(javaEncodingName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String getCharsetNameForIndex(int charsetIndex) throws SQLException {
/* 1709 */     return getEncodingForIndex(charsetIndex);
/*      */   }
/*      */   
/*      */   public String getEncodingForIndex(int collationIndex) throws SQLException {
/* 1713 */     return getActiveMySQLConnection().getEncodingForIndex(collationIndex);
/*      */   }
/*      */   
/*      */   public TimeZone getDefaultTimeZone() {
/* 1717 */     return getActiveMySQLConnection().getDefaultTimeZone();
/*      */   }
/*      */   
/*      */   public String getErrorMessageEncoding() {
/* 1721 */     return getActiveMySQLConnection().getErrorMessageEncoding();
/*      */   }
/*      */   
/*      */   public ExceptionInterceptor getExceptionInterceptor() {
/* 1725 */     return getActiveMySQLConnection().getExceptionInterceptor();
/*      */   }
/*      */   
/*      */   public int getHoldability() throws SQLException {
/* 1729 */     return getActiveMySQLConnection().getHoldability();
/*      */   }
/*      */   
/*      */   public String getHost() {
/* 1733 */     return getActiveMySQLConnection().getHost();
/*      */   }
/*      */   
/*      */   public String getHostPortPair() {
/* 1737 */     return getActiveMySQLConnection().getHostPortPair();
/*      */   }
/*      */   
/*      */   public long getId() {
/* 1741 */     return getActiveMySQLConnection().getId();
/*      */   }
/*      */   
/*      */   public long getIdleFor() {
/* 1745 */     return getActiveMySQLConnection().getIdleFor();
/*      */   }
/*      */   
/*      */   public MysqlIO getIO() throws SQLException {
/* 1749 */     return getActiveMySQLConnection().getIO();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public MySQLConnection getLoadBalanceSafeProxy() {
/* 1757 */     return getMultiHostSafeProxy();
/*      */   }
/*      */   
/*      */   public MySQLConnection getMultiHostSafeProxy() {
/* 1761 */     return getThisAsProxy().getProxy();
/*      */   }
/*      */   
/*      */   public Log getLog() throws SQLException {
/* 1765 */     return getActiveMySQLConnection().getLog();
/*      */   }
/*      */   
/*      */   public int getMaxBytesPerChar(String javaCharsetName) throws SQLException {
/* 1769 */     return getActiveMySQLConnection().getMaxBytesPerChar(javaCharsetName);
/*      */   }
/*      */   
/*      */   public int getMaxBytesPerChar(Integer charsetIndex, String javaCharsetName) throws SQLException {
/* 1773 */     return getActiveMySQLConnection().getMaxBytesPerChar(charsetIndex, javaCharsetName);
/*      */   }
/*      */   
/*      */   public DatabaseMetaData getMetaData() throws SQLException {
/* 1777 */     return getActiveMySQLConnection().getMetaData();
/*      */   }
/*      */   
/*      */   public Statement getMetadataSafeStatement() throws SQLException {
/* 1781 */     return getActiveMySQLConnection().getMetadataSafeStatement();
/*      */   }
/*      */   
/*      */   public int getNetBufferLength() {
/* 1785 */     return getActiveMySQLConnection().getNetBufferLength();
/*      */   }
/*      */   
/*      */   public Properties getProperties() {
/* 1789 */     return getActiveMySQLConnection().getProperties();
/*      */   }
/*      */   
/*      */   public boolean getRequiresEscapingEncoder() {
/* 1793 */     return getActiveMySQLConnection().getRequiresEscapingEncoder();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String getServerCharacterEncoding() {
/* 1801 */     return getServerCharset();
/*      */   }
/*      */   
/*      */   public String getServerCharset() {
/* 1805 */     return getActiveMySQLConnection().getServerCharset();
/*      */   }
/*      */   
/*      */   public int getServerMajorVersion() {
/* 1809 */     return getActiveMySQLConnection().getServerMajorVersion();
/*      */   }
/*      */   
/*      */   public int getServerMinorVersion() {
/* 1813 */     return getActiveMySQLConnection().getServerMinorVersion();
/*      */   }
/*      */   
/*      */   public int getServerSubMinorVersion() {
/* 1817 */     return getActiveMySQLConnection().getServerSubMinorVersion();
/*      */   }
/*      */   
/*      */   public TimeZone getServerTimezoneTZ() {
/* 1821 */     return getActiveMySQLConnection().getServerTimezoneTZ();
/*      */   }
/*      */   
/*      */   public String getServerVariable(String variableName) {
/* 1825 */     return getActiveMySQLConnection().getServerVariable(variableName);
/*      */   }
/*      */   
/*      */   public String getServerVersion() {
/* 1829 */     return getActiveMySQLConnection().getServerVersion();
/*      */   }
/*      */   
/*      */   public Calendar getSessionLockedCalendar() {
/* 1833 */     return getActiveMySQLConnection().getSessionLockedCalendar();
/*      */   }
/*      */   
/*      */   public String getStatementComment() {
/* 1837 */     return getActiveMySQLConnection().getStatementComment();
/*      */   }
/*      */   
/*      */   public List<StatementInterceptorV2> getStatementInterceptorsInstances() {
/* 1841 */     return getActiveMySQLConnection().getStatementInterceptorsInstances();
/*      */   }
/*      */   
/*      */   public int getTransactionIsolation() throws SQLException {
/* 1845 */     return getActiveMySQLConnection().getTransactionIsolation();
/*      */   }
/*      */   
/*      */   public Map<String, Class<?>> getTypeMap() throws SQLException {
/* 1849 */     return getActiveMySQLConnection().getTypeMap();
/*      */   }
/*      */   
/*      */   public String getURL() {
/* 1853 */     return getActiveMySQLConnection().getURL();
/*      */   }
/*      */   
/*      */   public String getUser() {
/* 1857 */     return getActiveMySQLConnection().getUser();
/*      */   }
/*      */   
/*      */   public Calendar getUtcCalendar() {
/* 1861 */     return getActiveMySQLConnection().getUtcCalendar();
/*      */   }
/*      */   
/*      */   public SQLWarning getWarnings() throws SQLException {
/* 1865 */     return getActiveMySQLConnection().getWarnings();
/*      */   }
/*      */   
/*      */   public boolean hasSameProperties(Connection c) {
/* 1869 */     return getActiveMySQLConnection().hasSameProperties(c);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public boolean hasTriedMaster() {
/* 1874 */     return getActiveMySQLConnection().hasTriedMaster();
/*      */   }
/*      */   
/*      */   public void incrementNumberOfPreparedExecutes() {
/* 1878 */     getActiveMySQLConnection().incrementNumberOfPreparedExecutes();
/*      */   }
/*      */   
/*      */   public void incrementNumberOfPrepares() {
/* 1882 */     getActiveMySQLConnection().incrementNumberOfPrepares();
/*      */   }
/*      */   
/*      */   public void incrementNumberOfResultSetsCreated() {
/* 1886 */     getActiveMySQLConnection().incrementNumberOfResultSetsCreated();
/*      */   }
/*      */   
/*      */   public void initializeExtension(Extension ex) throws SQLException {
/* 1890 */     getActiveMySQLConnection().initializeExtension(ex);
/*      */   }
/*      */   
/*      */   public void initializeResultsMetadataFromCache(String sql, CachedResultSetMetaData cachedMetaData, ResultSetInternalMethods resultSet) throws SQLException {
/* 1894 */     getActiveMySQLConnection().initializeResultsMetadataFromCache(sql, cachedMetaData, resultSet);
/*      */   }
/*      */   
/*      */   public void initializeSafeStatementInterceptors() throws SQLException {
/* 1898 */     getActiveMySQLConnection().initializeSafeStatementInterceptors();
/*      */   }
/*      */   
/*      */   public boolean isAbonormallyLongQuery(long millisOrNanos) {
/* 1902 */     return getActiveMySQLConnection().isAbonormallyLongQuery(millisOrNanos);
/*      */   }
/*      */   
/*      */   public boolean isClientTzUTC() {
/* 1906 */     return getActiveMySQLConnection().isClientTzUTC();
/*      */   }
/*      */   
/*      */   public boolean isCursorFetchEnabled() throws SQLException {
/* 1910 */     return getActiveMySQLConnection().isCursorFetchEnabled();
/*      */   }
/*      */   
/*      */   public boolean isInGlobalTx() {
/* 1914 */     return getActiveMySQLConnection().isInGlobalTx();
/*      */   }
/*      */   
/*      */   public boolean isMasterConnection() {
/* 1918 */     return getThisAsProxy().isMasterConnection();
/*      */   }
/*      */   
/*      */   public boolean isNoBackslashEscapesSet() {
/* 1922 */     return getActiveMySQLConnection().isNoBackslashEscapesSet();
/*      */   }
/*      */   
/*      */   public boolean isReadInfoMsgEnabled() {
/* 1926 */     return getActiveMySQLConnection().isReadInfoMsgEnabled();
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() throws SQLException {
/* 1930 */     return getActiveMySQLConnection().isReadOnly();
/*      */   }
/*      */   
/*      */   public boolean isReadOnly(boolean useSessionStatus) throws SQLException {
/* 1934 */     return getActiveMySQLConnection().isReadOnly(useSessionStatus);
/*      */   }
/*      */   
/*      */   public boolean isRunningOnJDK13() {
/* 1938 */     return getActiveMySQLConnection().isRunningOnJDK13();
/*      */   }
/*      */   
/*      */   public boolean isSameResource(Connection otherConnection) {
/* 1942 */     return getActiveMySQLConnection().isSameResource(otherConnection);
/*      */   }
/*      */   
/*      */   public boolean isServerTzUTC() {
/* 1946 */     return getActiveMySQLConnection().isServerTzUTC();
/*      */   }
/*      */   
/*      */   public boolean lowerCaseTableNames() {
/* 1950 */     return getActiveMySQLConnection().lowerCaseTableNames();
/*      */   }
/*      */   
/*      */   public String nativeSQL(String sql) throws SQLException {
/* 1954 */     return getActiveMySQLConnection().nativeSQL(sql);
/*      */   }
/*      */   
/*      */   public boolean parserKnowsUnicode() {
/* 1958 */     return getActiveMySQLConnection().parserKnowsUnicode();
/*      */   }
/*      */   
/*      */   public void ping() throws SQLException {
/* 1962 */     getActiveMySQLConnection().ping();
/*      */   }
/*      */   
/*      */   public void pingInternal(boolean checkForClosedConnection, int timeoutMillis) throws SQLException {
/* 1966 */     getActiveMySQLConnection().pingInternal(checkForClosedConnection, timeoutMillis);
/*      */   }
/*      */   
/*      */   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/* 1970 */     return getActiveMySQLConnection().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
/*      */   }
/*      */   
/*      */   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/* 1974 */     return getActiveMySQLConnection().prepareCall(sql, resultSetType, resultSetConcurrency);
/*      */   }
/*      */   
/*      */   public CallableStatement prepareCall(String sql) throws SQLException {
/* 1978 */     return getActiveMySQLConnection().prepareCall(sql);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/* 1982 */     return getActiveMySQLConnection().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/* 1986 */     return getActiveMySQLConnection().prepareStatement(sql, resultSetType, resultSetConcurrency);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
/* 1990 */     return getActiveMySQLConnection().prepareStatement(sql, autoGenKeyIndex);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
/* 1994 */     return getActiveMySQLConnection().prepareStatement(sql, autoGenKeyIndexes);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
/* 1998 */     return getActiveMySQLConnection().prepareStatement(sql, autoGenKeyColNames);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql) throws SQLException {
/* 2002 */     return getActiveMySQLConnection().prepareStatement(sql);
/*      */   }
/*      */   
/*      */   public void realClose(boolean calledExplicitly, boolean issueRollback, boolean skipLocalTeardown, Throwable reason) throws SQLException {
/* 2006 */     getActiveMySQLConnection().realClose(calledExplicitly, issueRollback, skipLocalTeardown, reason);
/*      */   }
/*      */   
/*      */   public void recachePreparedStatement(ServerPreparedStatement pstmt) throws SQLException {
/* 2010 */     getActiveMySQLConnection().recachePreparedStatement(pstmt);
/*      */   }
/*      */   
/*      */   public void decachePreparedStatement(ServerPreparedStatement pstmt) throws SQLException {
/* 2014 */     getActiveMySQLConnection().decachePreparedStatement(pstmt);
/*      */   }
/*      */   
/*      */   public void registerQueryExecutionTime(long queryTimeMs) {
/* 2018 */     getActiveMySQLConnection().registerQueryExecutionTime(queryTimeMs);
/*      */   }
/*      */   
/*      */   public void registerStatement(Statement stmt) {
/* 2022 */     getActiveMySQLConnection().registerStatement(stmt);
/*      */   }
/*      */   
/*      */   public void releaseSavepoint(Savepoint arg0) throws SQLException {
/* 2026 */     getActiveMySQLConnection().releaseSavepoint(arg0);
/*      */   }
/*      */   
/*      */   public void reportNumberOfTablesAccessed(int numTablesAccessed) {
/* 2030 */     getActiveMySQLConnection().reportNumberOfTablesAccessed(numTablesAccessed);
/*      */   }
/*      */   
/*      */   public void reportQueryTime(long millisOrNanos) {
/* 2034 */     getActiveMySQLConnection().reportQueryTime(millisOrNanos);
/*      */   }
/*      */   
/*      */   public void resetServerState() throws SQLException {
/* 2038 */     getActiveMySQLConnection().resetServerState();
/*      */   }
/*      */   
/*      */   public void rollback() throws SQLException {
/* 2042 */     getActiveMySQLConnection().rollback();
/*      */   }
/*      */   
/*      */   public void rollback(Savepoint savepoint) throws SQLException {
/* 2046 */     getActiveMySQLConnection().rollback(savepoint);
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/* 2050 */     return getActiveMySQLConnection().serverPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/* 2054 */     return getActiveMySQLConnection().serverPrepareStatement(sql, resultSetType, resultSetConcurrency);
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
/* 2058 */     return getActiveMySQLConnection().serverPrepareStatement(sql, autoGenKeyIndex);
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
/* 2062 */     return getActiveMySQLConnection().serverPrepareStatement(sql, autoGenKeyIndexes);
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
/* 2066 */     return getActiveMySQLConnection().serverPrepareStatement(sql, autoGenKeyColNames);
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql) throws SQLException {
/* 2070 */     return getActiveMySQLConnection().serverPrepareStatement(sql);
/*      */   }
/*      */   
/*      */   public boolean serverSupportsConvertFn() throws SQLException {
/* 2074 */     return getActiveMySQLConnection().serverSupportsConvertFn();
/*      */   }
/*      */   
/*      */   public void setAutoCommit(boolean autoCommitFlag) throws SQLException {
/* 2078 */     getActiveMySQLConnection().setAutoCommit(autoCommitFlag);
/*      */   }
/*      */   
/*      */   public void setCatalog(String catalog) throws SQLException {
/* 2082 */     getActiveMySQLConnection().setCatalog(catalog);
/*      */   }
/*      */   
/*      */   public void setFailedOver(boolean flag) {
/* 2086 */     getActiveMySQLConnection().setFailedOver(flag);
/*      */   }
/*      */   
/*      */   public void setHoldability(int arg0) throws SQLException {
/* 2090 */     getActiveMySQLConnection().setHoldability(arg0);
/*      */   }
/*      */   
/*      */   public void setInGlobalTx(boolean flag) {
/* 2094 */     getActiveMySQLConnection().setInGlobalTx(flag);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public void setPreferSlaveDuringFailover(boolean flag) {
/* 2099 */     getActiveMySQLConnection().setPreferSlaveDuringFailover(flag);
/*      */   }
/*      */   
/*      */   public void setProxy(MySQLConnection proxy) {
/* 2103 */     getThisAsProxy().setProxy(proxy);
/*      */   }
/*      */   
/*      */   public void setReadInfoMsgEnabled(boolean flag) {
/* 2107 */     getActiveMySQLConnection().setReadInfoMsgEnabled(flag);
/*      */   }
/*      */   
/*      */   public void setReadOnly(boolean readOnlyFlag) throws SQLException {
/* 2111 */     getActiveMySQLConnection().setReadOnly(readOnlyFlag);
/*      */   }
/*      */   
/*      */   public void setReadOnlyInternal(boolean readOnlyFlag) throws SQLException {
/* 2115 */     getActiveMySQLConnection().setReadOnlyInternal(readOnlyFlag);
/*      */   }
/*      */   
/*      */   public Savepoint setSavepoint() throws SQLException {
/* 2119 */     return getActiveMySQLConnection().setSavepoint();
/*      */   }
/*      */   
/*      */   public Savepoint setSavepoint(String name) throws SQLException {
/* 2123 */     return getActiveMySQLConnection().setSavepoint(name);
/*      */   }
/*      */   
/*      */   public void setStatementComment(String comment) {
/* 2127 */     getActiveMySQLConnection().setStatementComment(comment);
/*      */   }
/*      */   
/*      */   public void setTransactionIsolation(int level) throws SQLException {
/* 2131 */     getActiveMySQLConnection().setTransactionIsolation(level);
/*      */   }
/*      */   
/*      */   public void shutdownServer() throws SQLException {
/* 2135 */     getActiveMySQLConnection().shutdownServer();
/*      */   }
/*      */   
/*      */   public boolean storesLowerCaseTableName() {
/* 2139 */     return getActiveMySQLConnection().storesLowerCaseTableName();
/*      */   }
/*      */   
/*      */   public boolean supportsIsolationLevel() {
/* 2143 */     return getActiveMySQLConnection().supportsIsolationLevel();
/*      */   }
/*      */   
/*      */   public boolean supportsQuotedIdentifiers() {
/* 2147 */     return getActiveMySQLConnection().supportsQuotedIdentifiers();
/*      */   }
/*      */   
/*      */   public boolean supportsTransactions() {
/* 2151 */     return getActiveMySQLConnection().supportsTransactions();
/*      */   }
/*      */   
/*      */   public void throwConnectionClosedException() throws SQLException {
/* 2155 */     getActiveMySQLConnection().throwConnectionClosedException();
/*      */   }
/*      */   
/*      */   public void transactionBegun() throws SQLException {
/* 2159 */     getActiveMySQLConnection().transactionBegun();
/*      */   }
/*      */   
/*      */   public void transactionCompleted() throws SQLException {
/* 2163 */     getActiveMySQLConnection().transactionCompleted();
/*      */   }
/*      */   
/*      */   public void unregisterStatement(Statement stmt) {
/* 2167 */     getActiveMySQLConnection().unregisterStatement(stmt);
/*      */   }
/*      */   
/*      */   public void unSafeStatementInterceptors() throws SQLException {
/* 2171 */     getActiveMySQLConnection().unSafeStatementInterceptors();
/*      */   }
/*      */   
/*      */   public boolean useAnsiQuotedIdentifiers() {
/* 2175 */     return getActiveMySQLConnection().useAnsiQuotedIdentifiers();
/*      */   }
/*      */   
/*      */   public boolean versionMeetsMinimum(int major, int minor, int subminor) throws SQLException {
/* 2179 */     return getActiveMySQLConnection().versionMeetsMinimum(major, minor, subminor);
/*      */   }
/*      */   
/*      */   public boolean isClosed() throws SQLException {
/* 2183 */     return (getThisAsProxy()).isClosed;
/*      */   }
/*      */   
/*      */   public boolean getHoldResultsOpenOverStatementClose() {
/* 2187 */     return getActiveMySQLConnection().getHoldResultsOpenOverStatementClose();
/*      */   }
/*      */   
/*      */   public String getLoadBalanceConnectionGroup() {
/* 2191 */     return getActiveMySQLConnection().getLoadBalanceConnectionGroup();
/*      */   }
/*      */   
/*      */   public boolean getLoadBalanceEnableJMX() {
/* 2195 */     return getActiveMySQLConnection().getLoadBalanceEnableJMX();
/*      */   }
/*      */   
/*      */   public String getLoadBalanceExceptionChecker() {
/* 2199 */     return getActiveMySQLConnection().getLoadBalanceExceptionChecker();
/*      */   }
/*      */   
/*      */   public String getLoadBalanceSQLExceptionSubclassFailover() {
/* 2203 */     return getActiveMySQLConnection().getLoadBalanceSQLExceptionSubclassFailover();
/*      */   }
/*      */   
/*      */   public String getLoadBalanceSQLStateFailover() {
/* 2207 */     return getActiveMySQLConnection().getLoadBalanceSQLStateFailover();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceConnectionGroup(String loadBalanceConnectionGroup) {
/* 2211 */     getActiveMySQLConnection().setLoadBalanceConnectionGroup(loadBalanceConnectionGroup);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceEnableJMX(boolean loadBalanceEnableJMX) {
/* 2216 */     getActiveMySQLConnection().setLoadBalanceEnableJMX(loadBalanceEnableJMX);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceExceptionChecker(String loadBalanceExceptionChecker) {
/* 2221 */     getActiveMySQLConnection().setLoadBalanceExceptionChecker(loadBalanceExceptionChecker);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceSQLExceptionSubclassFailover(String loadBalanceSQLExceptionSubclassFailover) {
/* 2226 */     getActiveMySQLConnection().setLoadBalanceSQLExceptionSubclassFailover(loadBalanceSQLExceptionSubclassFailover);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceSQLStateFailover(String loadBalanceSQLStateFailover) {
/* 2231 */     getActiveMySQLConnection().setLoadBalanceSQLStateFailover(loadBalanceSQLStateFailover);
/*      */   }
/*      */   
/*      */   public void setLoadBalanceHostRemovalGracePeriod(int loadBalanceHostRemovalGracePeriod) throws SQLException {
/* 2235 */     getActiveMySQLConnection().setLoadBalanceHostRemovalGracePeriod(loadBalanceHostRemovalGracePeriod);
/*      */   }
/*      */   
/*      */   public int getLoadBalanceHostRemovalGracePeriod() {
/* 2239 */     return getActiveMySQLConnection().getLoadBalanceHostRemovalGracePeriod();
/*      */   }
/*      */   
/*      */   public boolean isProxySet() {
/* 2243 */     return getActiveMySQLConnection().isProxySet();
/*      */   }
/*      */   
/*      */   public String getLoadBalanceAutoCommitStatementRegex() {
/* 2247 */     return getActiveMySQLConnection().getLoadBalanceAutoCommitStatementRegex();
/*      */   }
/*      */   
/*      */   public int getLoadBalanceAutoCommitStatementThreshold() {
/* 2251 */     return getActiveMySQLConnection().getLoadBalanceAutoCommitStatementThreshold();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceAutoCommitStatementRegex(String loadBalanceAutoCommitStatementRegex) {
/* 2255 */     getActiveMySQLConnection().setLoadBalanceAutoCommitStatementRegex(loadBalanceAutoCommitStatementRegex);
/*      */   }
/*      */   
/*      */   public void setLoadBalanceAutoCommitStatementThreshold(int loadBalanceAutoCommitStatementThreshold) throws SQLException {
/* 2259 */     getActiveMySQLConnection().setLoadBalanceAutoCommitStatementThreshold(loadBalanceAutoCommitStatementThreshold);
/*      */   }
/*      */   
/*      */   public boolean getIncludeThreadDumpInDeadlockExceptions() {
/* 2263 */     return getActiveMySQLConnection().getIncludeThreadDumpInDeadlockExceptions();
/*      */   }
/*      */   
/*      */   public void setIncludeThreadDumpInDeadlockExceptions(boolean flag) {
/* 2267 */     getActiveMySQLConnection().setIncludeThreadDumpInDeadlockExceptions(flag);
/*      */   }
/*      */   
/*      */   public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
/* 2271 */     getActiveMySQLConnection().setTypeMap(map);
/*      */   }
/*      */   
/*      */   public boolean getIncludeThreadNamesAsStatementComment() {
/* 2275 */     return getActiveMySQLConnection().getIncludeThreadNamesAsStatementComment();
/*      */   }
/*      */   
/*      */   public void setIncludeThreadNamesAsStatementComment(boolean flag) {
/* 2279 */     getActiveMySQLConnection().setIncludeThreadNamesAsStatementComment(flag);
/*      */   }
/*      */   
/*      */   public boolean isServerLocal() throws SQLException {
/* 2283 */     return getActiveMySQLConnection().isServerLocal();
/*      */   }
/*      */   
/*      */   public void setAuthenticationPlugins(String authenticationPlugins) {
/* 2287 */     getActiveMySQLConnection().setAuthenticationPlugins(authenticationPlugins);
/*      */   }
/*      */   
/*      */   public String getAuthenticationPlugins() {
/* 2291 */     return getActiveMySQLConnection().getAuthenticationPlugins();
/*      */   }
/*      */   
/*      */   public void setDisabledAuthenticationPlugins(String disabledAuthenticationPlugins) {
/* 2295 */     getActiveMySQLConnection().setDisabledAuthenticationPlugins(disabledAuthenticationPlugins);
/*      */   }
/*      */   
/*      */   public String getDisabledAuthenticationPlugins() {
/* 2299 */     return getActiveMySQLConnection().getDisabledAuthenticationPlugins();
/*      */   }
/*      */   
/*      */   public void setDefaultAuthenticationPlugin(String defaultAuthenticationPlugin) {
/* 2303 */     getActiveMySQLConnection().setDefaultAuthenticationPlugin(defaultAuthenticationPlugin);
/*      */   }
/*      */   
/*      */   public String getDefaultAuthenticationPlugin() {
/* 2307 */     return getActiveMySQLConnection().getDefaultAuthenticationPlugin();
/*      */   }
/*      */   
/*      */   public void setParseInfoCacheFactory(String factoryClassname) {
/* 2311 */     getActiveMySQLConnection().setParseInfoCacheFactory(factoryClassname);
/*      */   }
/*      */   
/*      */   public String getParseInfoCacheFactory() {
/* 2315 */     return getActiveMySQLConnection().getParseInfoCacheFactory();
/*      */   }
/*      */   
/*      */   public void setSchema(String schema) throws SQLException {
/* 2319 */     getActiveMySQLConnection().setSchema(schema);
/*      */   }
/*      */   
/*      */   public String getSchema() throws SQLException {
/* 2323 */     return getActiveMySQLConnection().getSchema();
/*      */   }
/*      */   
/*      */   public void abort(Executor executor) throws SQLException {
/* 2327 */     getActiveMySQLConnection().abort(executor);
/*      */   }
/*      */   
/*      */   public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
/* 2331 */     getActiveMySQLConnection().setNetworkTimeout(executor, milliseconds);
/*      */   }
/*      */   
/*      */   public int getNetworkTimeout() throws SQLException {
/* 2335 */     return getActiveMySQLConnection().getNetworkTimeout();
/*      */   }
/*      */   
/*      */   public void setServerConfigCacheFactory(String factoryClassname) {
/* 2339 */     getActiveMySQLConnection().setServerConfigCacheFactory(factoryClassname);
/*      */   }
/*      */   
/*      */   public String getServerConfigCacheFactory() {
/* 2343 */     return getActiveMySQLConnection().getServerConfigCacheFactory();
/*      */   }
/*      */   
/*      */   public void setDisconnectOnExpiredPasswords(boolean disconnectOnExpiredPasswords) {
/* 2347 */     getActiveMySQLConnection().setDisconnectOnExpiredPasswords(disconnectOnExpiredPasswords);
/*      */   }
/*      */   
/*      */   public boolean getDisconnectOnExpiredPasswords() {
/* 2351 */     return getActiveMySQLConnection().getDisconnectOnExpiredPasswords();
/*      */   }
/*      */   
/*      */   public void setGetProceduresReturnsFunctions(boolean getProcedureReturnsFunctions) {
/* 2355 */     getActiveMySQLConnection().setGetProceduresReturnsFunctions(getProcedureReturnsFunctions);
/*      */   }
/*      */   
/*      */   public boolean getGetProceduresReturnsFunctions() {
/* 2359 */     return getActiveMySQLConnection().getGetProceduresReturnsFunctions();
/*      */   }
/*      */   
/*      */   public Object getConnectionMutex() {
/* 2363 */     return getActiveMySQLConnection().getConnectionMutex();
/*      */   }
/*      */   
/*      */   public String getConnectionAttributes() throws SQLException {
/* 2367 */     return getActiveMySQLConnection().getConnectionAttributes();
/*      */   }
/*      */   
/*      */   public boolean getAllowMasterDownConnections() {
/* 2371 */     return getActiveMySQLConnection().getAllowMasterDownConnections();
/*      */   }
/*      */   
/*      */   public void setAllowMasterDownConnections(boolean connectIfMasterDown) {
/* 2375 */     getActiveMySQLConnection().setAllowMasterDownConnections(connectIfMasterDown);
/*      */   }
/*      */   
/*      */   public boolean getAllowSlaveDownConnections() {
/* 2379 */     return getActiveMySQLConnection().getAllowSlaveDownConnections();
/*      */   }
/*      */   
/*      */   public void setAllowSlaveDownConnections(boolean connectIfSlaveDown) {
/* 2383 */     getActiveMySQLConnection().setAllowSlaveDownConnections(connectIfSlaveDown);
/*      */   }
/*      */   
/*      */   public boolean getReadFromMasterWhenNoSlaves() {
/* 2387 */     return getActiveMySQLConnection().getReadFromMasterWhenNoSlaves();
/*      */   }
/*      */   
/*      */   public void setReadFromMasterWhenNoSlaves(boolean useMasterIfSlavesDown) {
/* 2391 */     getActiveMySQLConnection().setReadFromMasterWhenNoSlaves(useMasterIfSlavesDown);
/*      */   }
/*      */   
/*      */   public boolean getReplicationEnableJMX() {
/* 2395 */     return getActiveMySQLConnection().getReplicationEnableJMX();
/*      */   }
/*      */   
/*      */   public void setReplicationEnableJMX(boolean replicationEnableJMX) {
/* 2399 */     getActiveMySQLConnection().setReplicationEnableJMX(replicationEnableJMX);
/*      */   }
/*      */   
/*      */   public void setDetectCustomCollations(boolean detectCustomCollations) {
/* 2403 */     getActiveMySQLConnection().setDetectCustomCollations(detectCustomCollations);
/*      */   }
/*      */   
/*      */   public boolean getDetectCustomCollations() {
/* 2407 */     return getActiveMySQLConnection().getDetectCustomCollations();
/*      */   }
/*      */   
/*      */   public int getSessionMaxRows() {
/* 2411 */     return getActiveMySQLConnection().getSessionMaxRows();
/*      */   }
/*      */   
/*      */   public void setSessionMaxRows(int max) throws SQLException {
/* 2415 */     getActiveMySQLConnection().setSessionMaxRows(max);
/*      */   }
/*      */   
/*      */   public ProfilerEventHandler getProfilerEventHandlerInstance() {
/* 2419 */     return getActiveMySQLConnection().getProfilerEventHandlerInstance();
/*      */   }
/*      */   
/*      */   public void setProfilerEventHandlerInstance(ProfilerEventHandler h) {
/* 2423 */     getActiveMySQLConnection().setProfilerEventHandlerInstance(h);
/*      */   }
/*      */   
/*      */   public String getServerRSAPublicKeyFile() {
/* 2427 */     return getActiveMySQLConnection().getServerRSAPublicKeyFile();
/*      */   }
/*      */   
/*      */   public void setServerRSAPublicKeyFile(String serverRSAPublicKeyFile) throws SQLException {
/* 2431 */     getActiveMySQLConnection().setServerRSAPublicKeyFile(serverRSAPublicKeyFile);
/*      */   }
/*      */   
/*      */   public boolean getAllowPublicKeyRetrieval() {
/* 2435 */     return getActiveMySQLConnection().getAllowPublicKeyRetrieval();
/*      */   }
/*      */   
/*      */   public void setAllowPublicKeyRetrieval(boolean allowPublicKeyRetrieval) throws SQLException {
/* 2439 */     getActiveMySQLConnection().setAllowPublicKeyRetrieval(allowPublicKeyRetrieval);
/*      */   }
/*      */   
/*      */   public void setDontCheckOnDuplicateKeyUpdateInSQL(boolean dontCheckOnDuplicateKeyUpdateInSQL) {
/* 2443 */     getActiveMySQLConnection().setDontCheckOnDuplicateKeyUpdateInSQL(dontCheckOnDuplicateKeyUpdateInSQL);
/*      */   }
/*      */   
/*      */   public boolean getDontCheckOnDuplicateKeyUpdateInSQL() {
/* 2447 */     return getActiveMySQLConnection().getDontCheckOnDuplicateKeyUpdateInSQL();
/*      */   }
/*      */   
/*      */   public void setSocksProxyHost(String socksProxyHost) {
/* 2451 */     getActiveMySQLConnection().setSocksProxyHost(socksProxyHost);
/*      */   }
/*      */   
/*      */   public String getSocksProxyHost() {
/* 2455 */     return getActiveMySQLConnection().getSocksProxyHost();
/*      */   }
/*      */   
/*      */   public void setSocksProxyPort(int socksProxyPort) throws SQLException {
/* 2459 */     getActiveMySQLConnection().setSocksProxyPort(socksProxyPort);
/*      */   }
/*      */   
/*      */   public int getSocksProxyPort() {
/* 2463 */     return getActiveMySQLConnection().getSocksProxyPort();
/*      */   }
/*      */   
/*      */   public boolean getReadOnlyPropagatesToServer() {
/* 2467 */     return getActiveMySQLConnection().getReadOnlyPropagatesToServer();
/*      */   }
/*      */   
/*      */   public void setReadOnlyPropagatesToServer(boolean flag) {
/* 2471 */     getActiveMySQLConnection().setReadOnlyPropagatesToServer(flag);
/*      */   }
/*      */   
/*      */   public String getEnabledSSLCipherSuites() {
/* 2475 */     return getActiveMySQLConnection().getEnabledSSLCipherSuites();
/*      */   }
/*      */   
/*      */   public void setEnabledSSLCipherSuites(String cipherSuites) {
/* 2479 */     getActiveMySQLConnection().setEnabledSSLCipherSuites(cipherSuites);
/*      */   }
/*      */   
/*      */   public String getEnabledTLSProtocols() {
/* 2483 */     return getActiveMySQLConnection().getEnabledTLSProtocols();
/*      */   }
/*      */   
/*      */   public void setEnabledTLSProtocols(String protocols) {
/* 2487 */     getActiveMySQLConnection().setEnabledTLSProtocols(protocols);
/*      */   }
/*      */   
/*      */   public boolean getEnableEscapeProcessing() {
/* 2491 */     return getActiveMySQLConnection().getEnableEscapeProcessing();
/*      */   }
/*      */   
/*      */   public void setEnableEscapeProcessing(boolean flag) {
/* 2495 */     getActiveMySQLConnection().setEnableEscapeProcessing(flag);
/*      */   }
/*      */   
/*      */   public boolean isUseSSLExplicit() {
/* 2499 */     return getActiveMySQLConnection().isUseSSLExplicit();
/*      */   }
/*      */   
/*      */   public boolean isServerTruncatesFracSecs() {
/* 2503 */     return getActiveMySQLConnection().isServerTruncatesFracSecs();
/*      */   }
/*      */   
/*      */   public String getQueryTimingUnits() {
/* 2507 */     return getActiveMySQLConnection().getQueryTimingUnits();
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\MultiHostMySQLConnection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */