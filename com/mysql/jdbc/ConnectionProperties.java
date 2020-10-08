package com.mysql.jdbc;

import java.sql.SQLException;

public interface ConnectionProperties {
  String exposeAsXml() throws SQLException;
  
  boolean getAllowLoadLocalInfile();
  
  boolean getAllowMultiQueries();
  
  boolean getAllowNanAndInf();
  
  boolean getAllowUrlInLocalInfile();
  
  boolean getAlwaysSendSetIsolation();
  
  boolean getAutoDeserialize();
  
  boolean getAutoGenerateTestcaseScript();
  
  boolean getAutoReconnectForPools();
  
  int getBlobSendChunkSize();
  
  boolean getCacheCallableStatements();
  
  boolean getCachePreparedStatements();
  
  boolean getCacheResultSetMetadata();
  
  boolean getCacheServerConfiguration();
  
  int getCallableStatementCacheSize();
  
  boolean getCapitalizeTypeNames();
  
  String getCharacterSetResults();
  
  boolean getClobberStreamingResults();
  
  String getClobCharacterEncoding();
  
  String getConnectionCollation();
  
  int getConnectTimeout();
  
  boolean getContinueBatchOnError();
  
  boolean getCreateDatabaseIfNotExist();
  
  int getDefaultFetchSize();
  
  boolean getDontTrackOpenResources();
  
  boolean getDumpQueriesOnException();
  
  boolean getDynamicCalendars();
  
  boolean getElideSetAutoCommits();
  
  boolean getEmptyStringsConvertToZero();
  
  boolean getEmulateLocators();
  
  boolean getEmulateUnsupportedPstmts();
  
  boolean getEnablePacketDebug();
  
  String getEncoding();
  
  boolean getExplainSlowQueries();
  
  boolean getFailOverReadOnly();
  
  boolean getGatherPerformanceMetrics();
  
  boolean getHoldResultsOpenOverStatementClose();
  
  boolean getIgnoreNonTxTables();
  
  int getInitialTimeout();
  
  boolean getInteractiveClient();
  
  boolean getIsInteractiveClient();
  
  boolean getJdbcCompliantTruncation();
  
  int getLocatorFetchBufferSize();
  
  String getLogger();
  
  String getLoggerClassName();
  
  boolean getLogSlowQueries();
  
  boolean getMaintainTimeStats();
  
  int getMaxQuerySizeToLog();
  
  int getMaxReconnects();
  
  int getMaxRows();
  
  int getMetadataCacheSize();
  
  boolean getNoDatetimeStringSync();
  
  boolean getNullCatalogMeansCurrent();
  
  boolean getNullNamePatternMatchesAll();
  
  int getPacketDebugBufferSize();
  
  boolean getParanoid();
  
  boolean getPedantic();
  
  int getPreparedStatementCacheSize();
  
  int getPreparedStatementCacheSqlLimit();
  
  boolean getProfileSql();
  
  boolean getProfileSQL();
  
  String getPropertiesTransform();
  
  int getQueriesBeforeRetryMaster();
  
  boolean getReconnectAtTxEnd();
  
  boolean getRelaxAutoCommit();
  
  int getReportMetricsIntervalMillis();
  
  boolean getRequireSSL();
  
  boolean getRollbackOnPooledClose();
  
  boolean getRoundRobinLoadBalance();
  
  boolean getRunningCTS13();
  
  int getSecondsBeforeRetryMaster();
  
  String getServerTimezone();
  
  String getSessionVariables();
  
  int getSlowQueryThresholdMillis();
  
  String getSocketFactoryClassName();
  
  int getSocketTimeout();
  
  boolean getStrictFloatingPoint();
  
  boolean getStrictUpdates();
  
  boolean getTinyInt1isBit();
  
  boolean getTraceProtocol();
  
  boolean getTransformedBitIsBoolean();
  
  boolean getUseCompression();
  
  boolean getUseFastIntParsing();
  
  boolean getUseHostsInPrivileges();
  
  boolean getUseInformationSchema();
  
  boolean getUseLocalSessionState();
  
  boolean getUseOldUTF8Behavior();
  
  boolean getUseOnlyServerErrorMessages();
  
  boolean getUseReadAheadInput();
  
  boolean getUseServerPreparedStmts();
  
  boolean getUseSqlStateCodes();
  
  boolean getUseSSL();
  
  boolean isUseSSLExplicit();
  
  boolean getUseStreamLengthsInPrepStmts();
  
  boolean getUseTimezone();
  
  boolean getUseUltraDevWorkAround();
  
  boolean getUseUnbufferedInput();
  
  boolean getUseUnicode();
  
  boolean getUseUsageAdvisor();
  
  boolean getYearIsDateType();
  
  String getZeroDateTimeBehavior();
  
  void setAllowLoadLocalInfile(boolean paramBoolean);
  
  void setAllowMultiQueries(boolean paramBoolean);
  
  void setAllowNanAndInf(boolean paramBoolean);
  
  void setAllowUrlInLocalInfile(boolean paramBoolean);
  
  void setAlwaysSendSetIsolation(boolean paramBoolean);
  
  void setAutoDeserialize(boolean paramBoolean);
  
  void setAutoGenerateTestcaseScript(boolean paramBoolean);
  
  void setAutoReconnect(boolean paramBoolean);
  
  void setAutoReconnectForConnectionPools(boolean paramBoolean);
  
  void setAutoReconnectForPools(boolean paramBoolean);
  
  void setBlobSendChunkSize(String paramString) throws SQLException;
  
  void setCacheCallableStatements(boolean paramBoolean);
  
  void setCachePreparedStatements(boolean paramBoolean);
  
  void setCacheResultSetMetadata(boolean paramBoolean);
  
  void setCacheServerConfiguration(boolean paramBoolean);
  
  void setCallableStatementCacheSize(int paramInt) throws SQLException;
  
  void setCapitalizeDBMDTypes(boolean paramBoolean);
  
  void setCapitalizeTypeNames(boolean paramBoolean);
  
  void setCharacterEncoding(String paramString);
  
  void setCharacterSetResults(String paramString);
  
  void setClobberStreamingResults(boolean paramBoolean);
  
  void setClobCharacterEncoding(String paramString);
  
  void setConnectionCollation(String paramString);
  
  void setConnectTimeout(int paramInt) throws SQLException;
  
  void setContinueBatchOnError(boolean paramBoolean);
  
  void setCreateDatabaseIfNotExist(boolean paramBoolean);
  
  void setDefaultFetchSize(int paramInt) throws SQLException;
  
  void setDetectServerPreparedStmts(boolean paramBoolean);
  
  void setDontTrackOpenResources(boolean paramBoolean);
  
  void setDumpQueriesOnException(boolean paramBoolean);
  
  void setDynamicCalendars(boolean paramBoolean);
  
  void setElideSetAutoCommits(boolean paramBoolean);
  
  void setEmptyStringsConvertToZero(boolean paramBoolean);
  
  void setEmulateLocators(boolean paramBoolean);
  
  void setEmulateUnsupportedPstmts(boolean paramBoolean);
  
  void setEnablePacketDebug(boolean paramBoolean);
  
  void setEncoding(String paramString);
  
  void setExplainSlowQueries(boolean paramBoolean);
  
  void setFailOverReadOnly(boolean paramBoolean);
  
  void setGatherPerformanceMetrics(boolean paramBoolean);
  
  void setHoldResultsOpenOverStatementClose(boolean paramBoolean);
  
  void setIgnoreNonTxTables(boolean paramBoolean);
  
  void setInitialTimeout(int paramInt) throws SQLException;
  
  void setIsInteractiveClient(boolean paramBoolean);
  
  void setJdbcCompliantTruncation(boolean paramBoolean);
  
  void setLocatorFetchBufferSize(String paramString) throws SQLException;
  
  void setLogger(String paramString);
  
  void setLoggerClassName(String paramString);
  
  void setLogSlowQueries(boolean paramBoolean);
  
  void setMaintainTimeStats(boolean paramBoolean);
  
  void setMaxQuerySizeToLog(int paramInt) throws SQLException;
  
  void setMaxReconnects(int paramInt) throws SQLException;
  
  void setMaxRows(int paramInt) throws SQLException;
  
  void setMetadataCacheSize(int paramInt) throws SQLException;
  
  void setNoDatetimeStringSync(boolean paramBoolean);
  
  void setNullCatalogMeansCurrent(boolean paramBoolean);
  
  void setNullNamePatternMatchesAll(boolean paramBoolean);
  
  void setPacketDebugBufferSize(int paramInt) throws SQLException;
  
  void setParanoid(boolean paramBoolean);
  
  void setPedantic(boolean paramBoolean);
  
  void setPreparedStatementCacheSize(int paramInt) throws SQLException;
  
  void setPreparedStatementCacheSqlLimit(int paramInt) throws SQLException;
  
  void setProfileSql(boolean paramBoolean);
  
  void setProfileSQL(boolean paramBoolean);
  
  void setPropertiesTransform(String paramString);
  
  void setQueriesBeforeRetryMaster(int paramInt) throws SQLException;
  
  void setReconnectAtTxEnd(boolean paramBoolean);
  
  void setRelaxAutoCommit(boolean paramBoolean);
  
  void setReportMetricsIntervalMillis(int paramInt) throws SQLException;
  
  void setRequireSSL(boolean paramBoolean);
  
  void setRetainStatementAfterResultSetClose(boolean paramBoolean);
  
  void setRollbackOnPooledClose(boolean paramBoolean);
  
  void setRoundRobinLoadBalance(boolean paramBoolean);
  
  void setRunningCTS13(boolean paramBoolean);
  
  void setSecondsBeforeRetryMaster(int paramInt) throws SQLException;
  
  void setServerTimezone(String paramString);
  
  void setSessionVariables(String paramString);
  
  void setSlowQueryThresholdMillis(int paramInt) throws SQLException;
  
  void setSocketFactoryClassName(String paramString);
  
  void setSocketTimeout(int paramInt) throws SQLException;
  
  void setStrictFloatingPoint(boolean paramBoolean);
  
  void setStrictUpdates(boolean paramBoolean);
  
  void setTinyInt1isBit(boolean paramBoolean);
  
  void setTraceProtocol(boolean paramBoolean);
  
  void setTransformedBitIsBoolean(boolean paramBoolean);
  
  void setUseCompression(boolean paramBoolean);
  
  void setUseFastIntParsing(boolean paramBoolean);
  
  void setUseHostsInPrivileges(boolean paramBoolean);
  
  void setUseInformationSchema(boolean paramBoolean);
  
  void setUseLocalSessionState(boolean paramBoolean);
  
  void setUseOldUTF8Behavior(boolean paramBoolean);
  
  void setUseOnlyServerErrorMessages(boolean paramBoolean);
  
  void setUseReadAheadInput(boolean paramBoolean);
  
  void setUseServerPreparedStmts(boolean paramBoolean);
  
  void setUseSqlStateCodes(boolean paramBoolean);
  
  void setUseSSL(boolean paramBoolean);
  
  void setUseStreamLengthsInPrepStmts(boolean paramBoolean);
  
  void setUseTimezone(boolean paramBoolean);
  
  void setUseUltraDevWorkAround(boolean paramBoolean);
  
  void setUseUnbufferedInput(boolean paramBoolean);
  
  void setUseUnicode(boolean paramBoolean);
  
  void setUseUsageAdvisor(boolean paramBoolean);
  
  void setYearIsDateType(boolean paramBoolean);
  
  void setZeroDateTimeBehavior(String paramString);
  
  boolean useUnbufferedInput();
  
  boolean getUseCursorFetch();
  
  void setUseCursorFetch(boolean paramBoolean);
  
  boolean getOverrideSupportsIntegrityEnhancementFacility();
  
  void setOverrideSupportsIntegrityEnhancementFacility(boolean paramBoolean);
  
  boolean getNoTimezoneConversionForTimeType();
  
  void setNoTimezoneConversionForTimeType(boolean paramBoolean);
  
  boolean getNoTimezoneConversionForDateType();
  
  void setNoTimezoneConversionForDateType(boolean paramBoolean);
  
  boolean getCacheDefaultTimezone();
  
  void setCacheDefaultTimezone(boolean paramBoolean);
  
  boolean getUseJDBCCompliantTimezoneShift();
  
  void setUseJDBCCompliantTimezoneShift(boolean paramBoolean);
  
  boolean getAutoClosePStmtStreams();
  
  void setAutoClosePStmtStreams(boolean paramBoolean);
  
  boolean getProcessEscapeCodesForPrepStmts();
  
  void setProcessEscapeCodesForPrepStmts(boolean paramBoolean);
  
  boolean getUseGmtMillisForDatetimes();
  
  void setUseGmtMillisForDatetimes(boolean paramBoolean);
  
  boolean getDumpMetadataOnColumnNotFound();
  
  void setDumpMetadataOnColumnNotFound(boolean paramBoolean);
  
  String getResourceId();
  
  void setResourceId(String paramString);
  
  boolean getRewriteBatchedStatements();
  
  void setRewriteBatchedStatements(boolean paramBoolean);
  
  boolean getJdbcCompliantTruncationForReads();
  
  void setJdbcCompliantTruncationForReads(boolean paramBoolean);
  
  boolean getUseJvmCharsetConverters();
  
  void setUseJvmCharsetConverters(boolean paramBoolean);
  
  boolean getPinGlobalTxToPhysicalConnection();
  
  void setPinGlobalTxToPhysicalConnection(boolean paramBoolean);
  
  void setGatherPerfMetrics(boolean paramBoolean);
  
  boolean getGatherPerfMetrics();
  
  void setUltraDevHack(boolean paramBoolean);
  
  boolean getUltraDevHack();
  
  void setInteractiveClient(boolean paramBoolean);
  
  void setSocketFactory(String paramString);
  
  String getSocketFactory();
  
  void setUseServerPrepStmts(boolean paramBoolean);
  
  boolean getUseServerPrepStmts();
  
  void setCacheCallableStmts(boolean paramBoolean);
  
  boolean getCacheCallableStmts();
  
  void setCachePrepStmts(boolean paramBoolean);
  
  boolean getCachePrepStmts();
  
  void setCallableStmtCacheSize(int paramInt) throws SQLException;
  
  int getCallableStmtCacheSize();
  
  void setPrepStmtCacheSize(int paramInt) throws SQLException;
  
  int getPrepStmtCacheSize();
  
  void setPrepStmtCacheSqlLimit(int paramInt) throws SQLException;
  
  int getPrepStmtCacheSqlLimit();
  
  boolean getNoAccessToProcedureBodies();
  
  void setNoAccessToProcedureBodies(boolean paramBoolean);
  
  boolean getUseOldAliasMetadataBehavior();
  
  void setUseOldAliasMetadataBehavior(boolean paramBoolean);
  
  String getClientCertificateKeyStorePassword();
  
  void setClientCertificateKeyStorePassword(String paramString);
  
  String getClientCertificateKeyStoreType();
  
  void setClientCertificateKeyStoreType(String paramString);
  
  String getClientCertificateKeyStoreUrl();
  
  void setClientCertificateKeyStoreUrl(String paramString);
  
  String getTrustCertificateKeyStorePassword();
  
  void setTrustCertificateKeyStorePassword(String paramString);
  
  String getTrustCertificateKeyStoreType();
  
  void setTrustCertificateKeyStoreType(String paramString);
  
  String getTrustCertificateKeyStoreUrl();
  
  void setTrustCertificateKeyStoreUrl(String paramString);
  
  boolean getUseSSPSCompatibleTimezoneShift();
  
  void setUseSSPSCompatibleTimezoneShift(boolean paramBoolean);
  
  boolean getTreatUtilDateAsTimestamp();
  
  void setTreatUtilDateAsTimestamp(boolean paramBoolean);
  
  boolean getUseFastDateParsing();
  
  void setUseFastDateParsing(boolean paramBoolean);
  
  String getLocalSocketAddress();
  
  void setLocalSocketAddress(String paramString);
  
  void setUseConfigs(String paramString);
  
  String getUseConfigs();
  
  boolean getGenerateSimpleParameterMetadata();
  
  void setGenerateSimpleParameterMetadata(boolean paramBoolean);
  
  boolean getLogXaCommands();
  
  void setLogXaCommands(boolean paramBoolean);
  
  int getResultSetSizeThreshold();
  
  void setResultSetSizeThreshold(int paramInt) throws SQLException;
  
  int getNetTimeoutForStreamingResults();
  
  void setNetTimeoutForStreamingResults(int paramInt) throws SQLException;
  
  boolean getEnableQueryTimeouts();
  
  void setEnableQueryTimeouts(boolean paramBoolean);
  
  boolean getPadCharsWithSpace();
  
  void setPadCharsWithSpace(boolean paramBoolean);
  
  boolean getUseDynamicCharsetInfo();
  
  void setUseDynamicCharsetInfo(boolean paramBoolean);
  
  String getClientInfoProvider();
  
  void setClientInfoProvider(String paramString);
  
  boolean getPopulateInsertRowWithDefaultValues();
  
  void setPopulateInsertRowWithDefaultValues(boolean paramBoolean);
  
  String getLoadBalanceStrategy();
  
  void setLoadBalanceStrategy(String paramString);
  
  String getServerAffinityOrder();
  
  void setServerAffinityOrder(String paramString);
  
  boolean getTcpNoDelay();
  
  void setTcpNoDelay(boolean paramBoolean);
  
  boolean getTcpKeepAlive();
  
  void setTcpKeepAlive(boolean paramBoolean);
  
  int getTcpRcvBuf();
  
  void setTcpRcvBuf(int paramInt) throws SQLException;
  
  int getTcpSndBuf();
  
  void setTcpSndBuf(int paramInt) throws SQLException;
  
  int getTcpTrafficClass();
  
  void setTcpTrafficClass(int paramInt) throws SQLException;
  
  boolean getUseNanosForElapsedTime();
  
  void setUseNanosForElapsedTime(boolean paramBoolean);
  
  long getSlowQueryThresholdNanos();
  
  void setSlowQueryThresholdNanos(long paramLong) throws SQLException;
  
  String getStatementInterceptors();
  
  void setStatementInterceptors(String paramString);
  
  boolean getUseDirectRowUnpack();
  
  void setUseDirectRowUnpack(boolean paramBoolean);
  
  String getLargeRowSizeThreshold();
  
  void setLargeRowSizeThreshold(String paramString) throws SQLException;
  
  boolean getUseBlobToStoreUTF8OutsideBMP();
  
  void setUseBlobToStoreUTF8OutsideBMP(boolean paramBoolean);
  
  String getUtf8OutsideBmpExcludedColumnNamePattern();
  
  void setUtf8OutsideBmpExcludedColumnNamePattern(String paramString);
  
  String getUtf8OutsideBmpIncludedColumnNamePattern();
  
  void setUtf8OutsideBmpIncludedColumnNamePattern(String paramString);
  
  boolean getIncludeInnodbStatusInDeadlockExceptions();
  
  void setIncludeInnodbStatusInDeadlockExceptions(boolean paramBoolean);
  
  boolean getIncludeThreadDumpInDeadlockExceptions();
  
  void setIncludeThreadDumpInDeadlockExceptions(boolean paramBoolean);
  
  boolean getIncludeThreadNamesAsStatementComment();
  
  void setIncludeThreadNamesAsStatementComment(boolean paramBoolean);
  
  boolean getBlobsAreStrings();
  
  void setBlobsAreStrings(boolean paramBoolean);
  
  boolean getFunctionsNeverReturnBlobs();
  
  void setFunctionsNeverReturnBlobs(boolean paramBoolean);
  
  boolean getAutoSlowLog();
  
  void setAutoSlowLog(boolean paramBoolean);
  
  String getConnectionLifecycleInterceptors();
  
  void setConnectionLifecycleInterceptors(String paramString);
  
  String getProfilerEventHandler();
  
  void setProfilerEventHandler(String paramString);
  
  boolean getVerifyServerCertificate();
  
  void setVerifyServerCertificate(boolean paramBoolean);
  
  boolean getUseLegacyDatetimeCode();
  
  void setUseLegacyDatetimeCode(boolean paramBoolean);
  
  boolean getSendFractionalSeconds();
  
  void setSendFractionalSeconds(boolean paramBoolean);
  
  int getSelfDestructOnPingSecondsLifetime();
  
  void setSelfDestructOnPingSecondsLifetime(int paramInt) throws SQLException;
  
  int getSelfDestructOnPingMaxOperations();
  
  void setSelfDestructOnPingMaxOperations(int paramInt) throws SQLException;
  
  boolean getUseColumnNamesInFindColumn();
  
  void setUseColumnNamesInFindColumn(boolean paramBoolean);
  
  boolean getUseLocalTransactionState();
  
  void setUseLocalTransactionState(boolean paramBoolean);
  
  boolean getCompensateOnDuplicateKeyUpdateCounts();
  
  void setCompensateOnDuplicateKeyUpdateCounts(boolean paramBoolean);
  
  void setUseAffectedRows(boolean paramBoolean);
  
  boolean getUseAffectedRows();
  
  void setPasswordCharacterEncoding(String paramString);
  
  String getPasswordCharacterEncoding();
  
  int getLoadBalanceBlacklistTimeout();
  
  void setLoadBalanceBlacklistTimeout(int paramInt) throws SQLException;
  
  void setRetriesAllDown(int paramInt) throws SQLException;
  
  int getRetriesAllDown();
  
  ExceptionInterceptor getExceptionInterceptor();
  
  void setExceptionInterceptors(String paramString);
  
  String getExceptionInterceptors();
  
  boolean getQueryTimeoutKillsConnection();
  
  void setQueryTimeoutKillsConnection(boolean paramBoolean);
  
  int getMaxAllowedPacket();
  
  boolean getRetainStatementAfterResultSetClose();
  
  int getLoadBalancePingTimeout();
  
  void setLoadBalancePingTimeout(int paramInt) throws SQLException;
  
  boolean getLoadBalanceValidateConnectionOnSwapServer();
  
  void setLoadBalanceValidateConnectionOnSwapServer(boolean paramBoolean);
  
  String getLoadBalanceConnectionGroup();
  
  void setLoadBalanceConnectionGroup(String paramString);
  
  String getLoadBalanceExceptionChecker();
  
  void setLoadBalanceExceptionChecker(String paramString);
  
  String getLoadBalanceSQLStateFailover();
  
  void setLoadBalanceSQLStateFailover(String paramString);
  
  String getLoadBalanceSQLExceptionSubclassFailover();
  
  void setLoadBalanceSQLExceptionSubclassFailover(String paramString);
  
  boolean getLoadBalanceEnableJMX();
  
  void setLoadBalanceEnableJMX(boolean paramBoolean);
  
  void setLoadBalanceHostRemovalGracePeriod(int paramInt) throws SQLException;
  
  int getLoadBalanceHostRemovalGracePeriod();
  
  void setLoadBalanceAutoCommitStatementThreshold(int paramInt) throws SQLException;
  
  int getLoadBalanceAutoCommitStatementThreshold();
  
  void setLoadBalanceAutoCommitStatementRegex(String paramString);
  
  String getLoadBalanceAutoCommitStatementRegex();
  
  void setAuthenticationPlugins(String paramString);
  
  String getAuthenticationPlugins();
  
  void setDisabledAuthenticationPlugins(String paramString);
  
  String getDisabledAuthenticationPlugins();
  
  void setDefaultAuthenticationPlugin(String paramString);
  
  String getDefaultAuthenticationPlugin();
  
  void setParseInfoCacheFactory(String paramString);
  
  String getParseInfoCacheFactory();
  
  void setServerConfigCacheFactory(String paramString);
  
  String getServerConfigCacheFactory();
  
  void setDisconnectOnExpiredPasswords(boolean paramBoolean);
  
  boolean getDisconnectOnExpiredPasswords();
  
  boolean getAllowMasterDownConnections();
  
  void setAllowMasterDownConnections(boolean paramBoolean);
  
  boolean getAllowSlaveDownConnections();
  
  void setAllowSlaveDownConnections(boolean paramBoolean);
  
  boolean getReadFromMasterWhenNoSlaves();
  
  void setReadFromMasterWhenNoSlaves(boolean paramBoolean);
  
  boolean getReplicationEnableJMX();
  
  void setReplicationEnableJMX(boolean paramBoolean);
  
  void setGetProceduresReturnsFunctions(boolean paramBoolean);
  
  boolean getGetProceduresReturnsFunctions();
  
  void setDetectCustomCollations(boolean paramBoolean);
  
  boolean getDetectCustomCollations();
  
  String getConnectionAttributes() throws SQLException;
  
  String getServerRSAPublicKeyFile();
  
  void setServerRSAPublicKeyFile(String paramString) throws SQLException;
  
  boolean getAllowPublicKeyRetrieval();
  
  void setAllowPublicKeyRetrieval(boolean paramBoolean) throws SQLException;
  
  void setDontCheckOnDuplicateKeyUpdateInSQL(boolean paramBoolean);
  
  boolean getDontCheckOnDuplicateKeyUpdateInSQL();
  
  void setSocksProxyHost(String paramString);
  
  String getSocksProxyHost();
  
  void setSocksProxyPort(int paramInt) throws SQLException;
  
  int getSocksProxyPort();
  
  boolean getReadOnlyPropagatesToServer();
  
  void setReadOnlyPropagatesToServer(boolean paramBoolean);
  
  String getEnabledSSLCipherSuites();
  
  void setEnabledSSLCipherSuites(String paramString);
  
  String getEnabledTLSProtocols();
  
  void setEnabledTLSProtocols(String paramString);
  
  boolean getEnableEscapeProcessing();
  
  void setEnableEscapeProcessing(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ConnectionProperties.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */