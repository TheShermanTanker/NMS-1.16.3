/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import com.mysql.jdbc.exceptions.MySQLStatementCancelledException;
/*      */ import com.mysql.jdbc.exceptions.MySQLTimeoutException;
/*      */ import java.io.InputStream;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.math.BigInteger;
/*      */ import java.sql.BatchUpdateException;
/*      */ import java.sql.Connection;
/*      */ import java.sql.DriverManager;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Statement;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import java.util.TimerTask;
/*      */ import java.util.concurrent.atomic.AtomicBoolean;
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
/*      */ public class StatementImpl
/*      */   implements Statement
/*      */ {
/*      */   protected static final String PING_MARKER = "/* ping */";
/*   62 */   protected static final String[] ON_DUPLICATE_KEY_UPDATE_CLAUSE = new String[] { "ON", "DUPLICATE", "KEY", "UPDATE" };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class CancelTask
/*      */     extends TimerTask
/*      */   {
/*   70 */     SQLException caughtWhileCancelling = null;
/*      */     StatementImpl toCancel;
/*   72 */     Properties origConnProps = null;
/*   73 */     String origConnURL = "";
/*   74 */     long origConnId = 0L;
/*      */     
/*      */     CancelTask(StatementImpl cancellee) throws SQLException {
/*   77 */       this.toCancel = cancellee;
/*   78 */       this.origConnProps = new Properties();
/*      */       
/*   80 */       Properties props = StatementImpl.this.connection.getProperties();
/*      */       
/*   82 */       Enumeration<?> keys = props.propertyNames();
/*      */       
/*   84 */       while (keys.hasMoreElements()) {
/*   85 */         String key = keys.nextElement().toString();
/*   86 */         this.origConnProps.setProperty(key, props.getProperty(key));
/*      */       } 
/*      */       
/*   89 */       this.origConnURL = StatementImpl.this.connection.getURL();
/*   90 */       this.origConnId = StatementImpl.this.connection.getId();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*   96 */       Thread cancelThread = new Thread()
/*      */         {
/*      */           
/*      */           public void run()
/*      */           {
/*  101 */             Connection cancelConn = null;
/*  102 */             Statement cancelStmt = null;
/*      */             
/*      */             try {
/*  105 */               MySQLConnection physicalConn = StatementImpl.this.physicalConnection.get();
/*  106 */               if (physicalConn != null) {
/*  107 */                 if (physicalConn.getQueryTimeoutKillsConnection()) {
/*  108 */                   StatementImpl.CancelTask.this.toCancel.wasCancelled = true;
/*  109 */                   StatementImpl.CancelTask.this.toCancel.wasCancelledByTimeout = true;
/*  110 */                   physicalConn.realClose(false, false, true, (Throwable)new MySQLStatementCancelledException(Messages.getString("Statement.ConnectionKilledDueToTimeout")));
/*      */                 } else {
/*      */                   
/*  113 */                   synchronized (StatementImpl.this.cancelTimeoutMutex) {
/*  114 */                     if (StatementImpl.CancelTask.this.origConnURL.equals(physicalConn.getURL())) {
/*      */                       
/*  116 */                       cancelConn = physicalConn.duplicate();
/*  117 */                       cancelStmt = cancelConn.createStatement();
/*  118 */                       cancelStmt.execute("KILL QUERY " + physicalConn.getId());
/*      */                     } else {
/*      */                       try {
/*  121 */                         cancelConn = (Connection)DriverManager.getConnection(StatementImpl.CancelTask.this.origConnURL, StatementImpl.CancelTask.this.origConnProps);
/*  122 */                         cancelStmt = cancelConn.createStatement();
/*  123 */                         cancelStmt.execute("KILL QUERY " + StatementImpl.CancelTask.this.origConnId);
/*  124 */                       } catch (NullPointerException npe) {}
/*      */                     } 
/*      */ 
/*      */                     
/*  128 */                     StatementImpl.CancelTask.this.toCancel.wasCancelled = true;
/*  129 */                     StatementImpl.CancelTask.this.toCancel.wasCancelledByTimeout = true;
/*      */                   } 
/*      */                 } 
/*      */               }
/*  133 */             } catch (SQLException sqlEx) {
/*  134 */               StatementImpl.CancelTask.this.caughtWhileCancelling = sqlEx;
/*  135 */             } catch (NullPointerException npe) {
/*      */ 
/*      */             
/*      */             } finally {
/*      */               
/*  140 */               if (cancelStmt != null) {
/*      */                 try {
/*  142 */                   cancelStmt.close();
/*  143 */                 } catch (SQLException sqlEx) {
/*  144 */                   throw new RuntimeException(sqlEx.toString());
/*      */                 } 
/*      */               }
/*      */               
/*  148 */               if (cancelConn != null) {
/*      */                 try {
/*  150 */                   cancelConn.close();
/*  151 */                 } catch (SQLException sqlEx) {
/*  152 */                   throw new RuntimeException(sqlEx.toString());
/*      */                 } 
/*      */               }
/*      */               
/*  156 */               StatementImpl.CancelTask.this.toCancel = null;
/*  157 */               StatementImpl.CancelTask.this.origConnProps = null;
/*  158 */               StatementImpl.CancelTask.this.origConnURL = null;
/*      */             } 
/*      */           }
/*      */         };
/*      */       
/*  163 */       cancelThread.start();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  172 */   protected Object cancelTimeoutMutex = new Object();
/*      */ 
/*      */   
/*  175 */   static int statementCounter = 1;
/*      */ 
/*      */   
/*      */   public static final byte USES_VARIABLES_FALSE = 0;
/*      */   
/*      */   public static final byte USES_VARIABLES_TRUE = 1;
/*      */   
/*      */   public static final byte USES_VARIABLES_UNKNOWN = -1;
/*      */   
/*      */   protected boolean wasCancelled = false;
/*      */   
/*      */   protected boolean wasCancelledByTimeout = false;
/*      */   
/*      */   protected List<Object> batchedArgs;
/*      */   
/*  190 */   protected SingleByteCharsetConverter charConverter = null;
/*      */ 
/*      */   
/*  193 */   protected String charEncoding = null;
/*      */ 
/*      */   
/*  196 */   protected volatile MySQLConnection connection = null;
/*      */ 
/*      */   
/*  199 */   protected Reference<MySQLConnection> physicalConnection = null;
/*      */ 
/*      */   
/*  202 */   protected String currentCatalog = null;
/*      */ 
/*      */   
/*      */   protected boolean doEscapeProcessing = true;
/*      */ 
/*      */   
/*  208 */   private int fetchSize = 0;
/*      */ 
/*      */   
/*      */   protected boolean isClosed = false;
/*      */ 
/*      */   
/*  214 */   protected long lastInsertId = -1L;
/*      */ 
/*      */   
/*  217 */   protected int maxFieldSize = MysqlIO.getMaxBuf();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  223 */   protected int maxRows = -1;
/*      */ 
/*      */   
/*  226 */   protected Set<ResultSetInternalMethods> openResults = new HashSet<ResultSetInternalMethods>();
/*      */ 
/*      */   
/*      */   protected boolean pedantic = false;
/*      */ 
/*      */   
/*      */   protected boolean profileSQL = false;
/*      */ 
/*      */   
/*  235 */   protected ResultSetInternalMethods results = null;
/*      */   
/*  237 */   protected ResultSetInternalMethods generatedKeysResults = null;
/*      */ 
/*      */   
/*  240 */   protected int resultSetConcurrency = 0;
/*      */ 
/*      */   
/*  243 */   protected int resultSetType = 0;
/*      */ 
/*      */   
/*      */   protected int statementId;
/*      */ 
/*      */   
/*  249 */   protected int timeoutInMillis = 0;
/*      */ 
/*      */   
/*  252 */   protected long updateCount = -1L;
/*      */ 
/*      */   
/*      */   protected boolean useUsageAdvisor = false;
/*      */ 
/*      */   
/*  258 */   protected SQLWarning warningChain = null;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean clearWarningsCalled = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean holdResultsOpenOverClose = false;
/*      */ 
/*      */   
/*  269 */   protected ArrayList<ResultSetRow> batchedGeneratedKeys = null;
/*      */   
/*      */   protected boolean retrieveGeneratedKeys = false;
/*      */   
/*      */   protected boolean continueBatchOnError = false;
/*      */   
/*  275 */   protected PingTarget pingTarget = null;
/*      */ 
/*      */   
/*      */   protected boolean useLegacyDatetimeCode;
/*      */ 
/*      */   
/*      */   protected boolean sendFractionalSeconds;
/*      */   
/*      */   private ExceptionInterceptor exceptionInterceptor;
/*      */   
/*      */   protected boolean lastQueryIsOnDupKeyUpdate = false;
/*      */   
/*  287 */   protected final AtomicBoolean statementExecuting = new AtomicBoolean(false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isImplicitlyClosingResults = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int originalResultSetType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int originalFetchSize;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isPoolable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InputStream localInfileInputStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final boolean version5013OrNewer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean closeOnCompletion;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addBatch(String sql) throws SQLException {
/*  359 */     synchronized (checkClosed().getConnectionMutex()) {
/*  360 */       if (this.batchedArgs == null) {
/*  361 */         this.batchedArgs = new ArrayList();
/*      */       }
/*      */       
/*  364 */       if (sql != null) {
/*  365 */         this.batchedArgs.add(sql);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Object> getBatchedArgs() {
/*  379 */     return (this.batchedArgs == null) ? null : Collections.<Object>unmodifiableList(this.batchedArgs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void cancel() throws SQLException {
/*  388 */     if (!this.statementExecuting.get()) {
/*      */       return;
/*      */     }
/*      */     
/*  392 */     if (!this.isClosed && this.connection != null && this.connection.versionMeetsMinimum(5, 0, 0)) {
/*  393 */       Connection cancelConn = null;
/*  394 */       Statement cancelStmt = null;
/*      */       
/*      */       try {
/*  397 */         cancelConn = this.connection.duplicate();
/*  398 */         cancelStmt = cancelConn.createStatement();
/*  399 */         cancelStmt.execute("KILL QUERY " + this.connection.getIO().getThreadId());
/*  400 */         this.wasCancelled = true;
/*      */       } finally {
/*  402 */         if (cancelStmt != null) {
/*  403 */           cancelStmt.close();
/*      */         }
/*      */         
/*  406 */         if (cancelConn != null) {
/*  407 */           cancelConn.close();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
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
/*      */   protected MySQLConnection checkClosed() throws SQLException {
/*  423 */     MySQLConnection c = this.connection;
/*      */     
/*  425 */     if (c == null) {
/*  426 */       throw SQLError.createSQLException(Messages.getString("Statement.49"), "S1009", getExceptionInterceptor());
/*      */     }
/*      */     
/*  429 */     return c;
/*      */   }
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
/*      */   protected void checkForDml(String sql, char firstStatementChar) throws SQLException {
/*  445 */     if (firstStatementChar == 'I' || firstStatementChar == 'U' || firstStatementChar == 'D' || firstStatementChar == 'A' || firstStatementChar == 'C' || firstStatementChar == 'T' || firstStatementChar == 'R') {
/*      */       
/*  447 */       String noCommentSql = StringUtils.stripComments(sql, "'\"", "'\"", true, false, true, true);
/*      */       
/*  449 */       if (StringUtils.startsWithIgnoreCaseAndWs(noCommentSql, "INSERT") || StringUtils.startsWithIgnoreCaseAndWs(noCommentSql, "UPDATE") || StringUtils.startsWithIgnoreCaseAndWs(noCommentSql, "DELETE") || StringUtils.startsWithIgnoreCaseAndWs(noCommentSql, "DROP") || StringUtils.startsWithIgnoreCaseAndWs(noCommentSql, "CREATE") || StringUtils.startsWithIgnoreCaseAndWs(noCommentSql, "ALTER") || StringUtils.startsWithIgnoreCaseAndWs(noCommentSql, "TRUNCATE") || StringUtils.startsWithIgnoreCaseAndWs(noCommentSql, "RENAME"))
/*      */       {
/*      */ 
/*      */         
/*  453 */         throw SQLError.createSQLException(Messages.getString("Statement.57"), "S1009", getExceptionInterceptor());
/*      */       }
/*      */     } 
/*      */   }
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
/*      */   protected void checkNullOrEmptyQuery(String sql) throws SQLException {
/*  468 */     if (sql == null) {
/*  469 */       throw SQLError.createSQLException(Messages.getString("Statement.59"), "S1009", getExceptionInterceptor());
/*      */     }
/*      */     
/*  472 */     if (sql.length() == 0) {
/*  473 */       throw SQLError.createSQLException(Messages.getString("Statement.61"), "S1009", getExceptionInterceptor());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearBatch() throws SQLException {
/*  486 */     synchronized (checkClosed().getConnectionMutex()) {
/*  487 */       if (this.batchedArgs != null) {
/*  488 */         this.batchedArgs.clear();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearWarnings() throws SQLException {
/*  501 */     synchronized (checkClosed().getConnectionMutex()) {
/*  502 */       this.clearWarningsCalled = true;
/*  503 */       this.warningChain = null;
/*      */     } 
/*      */   }
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
/*      */   public void close() throws SQLException {
/*  522 */     realClose(true, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void closeAllOpenResults() throws SQLException {
/*  529 */     MySQLConnection locallyScopedConn = this.connection;
/*      */     
/*  531 */     if (locallyScopedConn == null) {
/*      */       return;
/*      */     }
/*      */     
/*  535 */     synchronized (locallyScopedConn.getConnectionMutex()) {
/*  536 */       if (this.openResults != null) {
/*  537 */         for (ResultSetInternalMethods element : this.openResults) {
/*      */           try {
/*  539 */             element.realClose(false);
/*  540 */           } catch (SQLException sqlEx) {
/*  541 */             AssertionFailedException.shouldNotHappen(sqlEx);
/*      */           } 
/*      */         } 
/*      */         
/*  545 */         this.openResults.clear();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void implicitlyCloseAllOpenResults() throws SQLException {
/*  554 */     this.isImplicitlyClosingResults = true;
/*      */     try {
/*  556 */       if (!this.connection.getHoldResultsOpenOverStatementClose() && !this.connection.getDontTrackOpenResources() && !this.holdResultsOpenOverClose) {
/*  557 */         if (this.results != null) {
/*  558 */           this.results.realClose(false);
/*      */         }
/*  560 */         if (this.generatedKeysResults != null) {
/*  561 */           this.generatedKeysResults.realClose(false);
/*      */         }
/*  563 */         closeAllOpenResults();
/*      */       } 
/*      */     } finally {
/*  566 */       this.isImplicitlyClosingResults = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void removeOpenResultSet(ResultSetInternalMethods rs) {
/*      */     try {
/*  572 */       synchronized (checkClosed().getConnectionMutex()) {
/*  573 */         if (this.openResults != null) {
/*  574 */           this.openResults.remove(rs);
/*      */         }
/*      */         
/*  577 */         boolean hasMoreResults = (rs.getNextResultSet() != null);
/*      */ 
/*      */         
/*  580 */         if (this.results == rs && !hasMoreResults) {
/*  581 */           this.results = null;
/*      */         }
/*  583 */         if (this.generatedKeysResults == rs) {
/*  584 */           this.generatedKeysResults = null;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  590 */         if (!this.isImplicitlyClosingResults && !hasMoreResults) {
/*  591 */           checkAndPerformCloseOnCompletionAction();
/*      */         }
/*      */       } 
/*  594 */     } catch (SQLException e) {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOpenResultSetCount() {
/*      */     try {
/*  601 */       synchronized (checkClosed().getConnectionMutex()) {
/*  602 */         if (this.openResults != null) {
/*  603 */           return this.openResults.size();
/*      */         }
/*      */         
/*  606 */         return 0;
/*      */       } 
/*  608 */     } catch (SQLException e) {
/*      */ 
/*      */       
/*  611 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkAndPerformCloseOnCompletionAction() {
/*      */     try {
/*  621 */       synchronized (checkClosed().getConnectionMutex()) {
/*  622 */         if (isCloseOnCompletion() && !this.connection.getDontTrackOpenResources() && getOpenResultSetCount() == 0 && (this.results == null || !this.results.reallyResult() || this.results.isClosed()) && (this.generatedKeysResults == null || !this.generatedKeysResults.reallyResult() || this.generatedKeysResults.isClosed()))
/*      */         {
/*      */           
/*  625 */           realClose(false, false);
/*      */         }
/*      */       } 
/*  628 */     } catch (SQLException e) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ResultSetInternalMethods createResultSetUsingServerFetch(String sql) throws SQLException {
/*  636 */     synchronized (checkClosed().getConnectionMutex()) {
/*  637 */       PreparedStatement pStmt = this.connection.prepareStatement(sql, this.resultSetType, this.resultSetConcurrency);
/*      */       
/*  639 */       pStmt.setFetchSize(this.fetchSize);
/*      */       
/*  641 */       if (this.maxRows > -1) {
/*  642 */         pStmt.setMaxRows(this.maxRows);
/*      */       }
/*      */       
/*  645 */       statementBegins();
/*      */       
/*  647 */       pStmt.execute();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  652 */       ResultSetInternalMethods rs = ((StatementImpl)pStmt).getResultSetInternal();
/*      */       
/*  654 */       rs.setStatementUsedForFetchingRows((PreparedStatement)pStmt);
/*      */       
/*  656 */       this.results = rs;
/*      */       
/*  658 */       return rs;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean createStreamingResultSet() {
/*  670 */     return (this.resultSetType == 1003 && this.resultSetConcurrency == 1007 && this.fetchSize == Integer.MIN_VALUE);
/*      */   }
/*      */   
/*      */   public StatementImpl(MySQLConnection c, String catalog) throws SQLException {
/*  674 */     this.originalResultSetType = 0;
/*  675 */     this.originalFetchSize = 0;
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
/* 2468 */     this.isPoolable = true;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2558 */     this.closeOnCompletion = false; if (c == null || c.isClosed()) throw SQLError.createSQLException(Messages.getString("Statement.0"), "08003", null);  this.connection = c; this.exceptionInterceptor = this.connection.getExceptionInterceptor(); this.currentCatalog = catalog; this.pedantic = this.connection.getPedantic(); this.continueBatchOnError = this.connection.getContinueBatchOnError(); this.useLegacyDatetimeCode = this.connection.getUseLegacyDatetimeCode(); this.sendFractionalSeconds = this.connection.getSendFractionalSeconds(); this.doEscapeProcessing = this.connection.getEnableEscapeProcessing(); if (!this.connection.getDontTrackOpenResources()) this.connection.registerStatement(this);  this.maxFieldSize = this.connection.getMaxAllowedPacket(); int defaultFetchSize = this.connection.getDefaultFetchSize(); if (defaultFetchSize != 0) setFetchSize(defaultFetchSize);  if (this.connection.getUseUnicode()) { this.charEncoding = this.connection.getEncoding(); this.charConverter = this.connection.getCharsetConverter(this.charEncoding); }  boolean profiling = (this.connection.getProfileSql() || this.connection.getUseUsageAdvisor() || this.connection.getLogSlowQueries()); if (this.connection.getAutoGenerateTestcaseScript() || profiling) this.statementId = statementCounter++;  if (profiling) { this.profileSQL = this.connection.getProfileSql(); this.useUsageAdvisor = this.connection.getUseUsageAdvisor(); }  int maxRowsConn = this.connection.getMaxRows(); if (maxRowsConn != -1) setMaxRows(maxRowsConn);  this.holdResultsOpenOverClose = this.connection.getHoldResultsOpenOverStatementClose(); this.version5013OrNewer = this.connection.versionMeetsMinimum(5, 0, 13);
/*      */   }
/*      */   public void enableStreamingResults() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { this.originalResultSetType = this.resultSetType; this.originalFetchSize = this.fetchSize; setFetchSize(-2147483648); setResultSetType(1003); }  }
/* 2561 */   public void disableStreamingResults() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.fetchSize == Integer.MIN_VALUE && this.resultSetType == 1003) { setFetchSize(this.originalFetchSize); setResultSetType(this.originalResultSetType); }  }  } protected void setupStreamingTimeout(MySQLConnection con) throws SQLException { if (createStreamingResultSet() && con.getNetTimeoutForStreamingResults() > 0) executeSimpleNonQuery(con, "SET net_write_timeout=" + con.getNetTimeoutForStreamingResults());  } public boolean execute(String sql) throws SQLException { return executeInternal(sql, false); } private boolean executeInternal(String sql, boolean returnGeneratedKeys) throws SQLException { MySQLConnection locallyScopedConn = checkClosed(); synchronized (locallyScopedConn.getConnectionMutex()) { checkClosed(); checkNullOrEmptyQuery(sql); resetCancelledState(); implicitlyCloseAllOpenResults(); if (sql.charAt(0) == '/' && sql.startsWith("/* ping */")) { doPingInstead(); return true; }  char firstNonWsChar = StringUtils.firstAlphaCharUc(sql, findStartOfStatement(sql)); boolean maybeSelect = (firstNonWsChar == 'S'); this.retrieveGeneratedKeys = returnGeneratedKeys; this.lastQueryIsOnDupKeyUpdate = (returnGeneratedKeys && firstNonWsChar == 'I' && containsOnDuplicateKeyInString(sql)); if (!maybeSelect && locallyScopedConn.isReadOnly()) throw SQLError.createSQLException(Messages.getString("Statement.27") + Messages.getString("Statement.28"), "S1009", getExceptionInterceptor());  boolean readInfoMsgState = locallyScopedConn.isReadInfoMsgEnabled(); if (returnGeneratedKeys && firstNonWsChar == 'R') locallyScopedConn.setReadInfoMsgEnabled(true);  try { setupStreamingTimeout(locallyScopedConn); if (this.doEscapeProcessing) { Object escapedSqlResult = EscapeProcessor.escapeSQL(sql, locallyScopedConn.serverSupportsConvertFn(), locallyScopedConn); if (escapedSqlResult instanceof String) { sql = (String)escapedSqlResult; } else { sql = ((EscapeProcessorResult)escapedSqlResult).escapedSql; }  }  CachedResultSetMetaData cachedMetaData = null; ResultSetInternalMethods rs = null; this.batchedGeneratedKeys = null; if (useServerFetch()) { rs = createResultSetUsingServerFetch(sql); } else { CancelTask timeoutTask = null; String oldCatalog = null; try { if (locallyScopedConn.getEnableQueryTimeouts() && this.timeoutInMillis != 0 && locallyScopedConn.versionMeetsMinimum(5, 0, 0)) { timeoutTask = new CancelTask(this); locallyScopedConn.getCancelTimer().schedule(timeoutTask, this.timeoutInMillis); }  if (!locallyScopedConn.getCatalog().equals(this.currentCatalog)) { oldCatalog = locallyScopedConn.getCatalog(); locallyScopedConn.setCatalog(this.currentCatalog); }  Field[] cachedFields = null; if (locallyScopedConn.getCacheResultSetMetadata()) { cachedMetaData = locallyScopedConn.getCachedMetaData(sql); if (cachedMetaData != null) cachedFields = cachedMetaData.fields;  }  locallyScopedConn.setSessionMaxRows(maybeSelect ? this.maxRows : -1); statementBegins(); rs = locallyScopedConn.execSQL(this, sql, this.maxRows, (Buffer)null, this.resultSetType, this.resultSetConcurrency, createStreamingResultSet(), this.currentCatalog, cachedFields); if (timeoutTask != null) { if (timeoutTask.caughtWhileCancelling != null) throw timeoutTask.caughtWhileCancelling;  timeoutTask.cancel(); timeoutTask = null; }  synchronized (this.cancelTimeoutMutex) { if (this.wasCancelled) { MySQLStatementCancelledException mySQLStatementCancelledException; SQLException cause = null; if (this.wasCancelledByTimeout) { MySQLTimeoutException mySQLTimeoutException = new MySQLTimeoutException(); } else { mySQLStatementCancelledException = new MySQLStatementCancelledException(); }  resetCancelledState(); throw mySQLStatementCancelledException; }  }  } finally { if (timeoutTask != null) { timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); }  if (oldCatalog != null) locallyScopedConn.setCatalog(oldCatalog);  }  }  if (rs != null) { this.lastInsertId = rs.getUpdateID(); this.results = rs; rs.setFirstCharOfQuery(firstNonWsChar); if (rs.reallyResult()) if (cachedMetaData != null) { locallyScopedConn.initializeResultsMetadataFromCache(sql, cachedMetaData, this.results); } else if (this.connection.getCacheResultSetMetadata()) { locallyScopedConn.initializeResultsMetadataFromCache(sql, (CachedResultSetMetaData)null, this.results); }   }  return (rs != null && rs.reallyResult()); } finally { locallyScopedConn.setReadInfoMsgEnabled(readInfoMsgState); this.statementExecuting.set(false); }  }  } protected void statementBegins() { this.clearWarningsCalled = false; this.statementExecuting.set(true); MySQLConnection physicalConn = this.connection.getMultiHostSafeProxy().getActiveMySQLConnection(); while (!(physicalConn instanceof ConnectionImpl)) physicalConn = physicalConn.getMultiHostSafeProxy().getActiveMySQLConnection();  this.physicalConnection = new WeakReference<MySQLConnection>(physicalConn); } protected void resetCancelledState() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.cancelTimeoutMutex == null) return;  synchronized (this.cancelTimeoutMutex) { this.wasCancelled = false; this.wasCancelledByTimeout = false; }  }  } public boolean execute(String sql, int returnGeneratedKeys) throws SQLException { return executeInternal(sql, (returnGeneratedKeys == 1)); } public boolean execute(String sql, int[] generatedKeyIndices) throws SQLException { return executeInternal(sql, (generatedKeyIndices != null && generatedKeyIndices.length > 0)); } public boolean execute(String sql, String[] generatedKeyNames) throws SQLException { return executeInternal(sql, (generatedKeyNames != null && generatedKeyNames.length > 0)); } public int[] executeBatch() throws SQLException { return Util.truncateAndConvertToInt(executeBatchInternal()); } protected long[] executeBatchInternal() throws SQLException { MySQLConnection locallyScopedConn = checkClosed(); synchronized (locallyScopedConn.getConnectionMutex()) { if (locallyScopedConn.isReadOnly()) throw SQLError.createSQLException(Messages.getString("Statement.34") + Messages.getString("Statement.35"), "S1009", getExceptionInterceptor());  implicitlyCloseAllOpenResults(); if (this.batchedArgs == null || this.batchedArgs.size() == 0) return new long[0];  int individualStatementTimeout = this.timeoutInMillis; this.timeoutInMillis = 0; CancelTask timeoutTask = null; try { resetCancelledState(); statementBegins(); try { this.retrieveGeneratedKeys = true; long[] updateCounts = null; if (this.batchedArgs != null) { int nbrCommands = this.batchedArgs.size(); this.batchedGeneratedKeys = new ArrayList<ResultSetRow>(this.batchedArgs.size()); boolean multiQueriesEnabled = locallyScopedConn.getAllowMultiQueries(); if (locallyScopedConn.versionMeetsMinimum(4, 1, 1) && (multiQueriesEnabled || (locallyScopedConn.getRewriteBatchedStatements() && nbrCommands > 4))) return executeBatchUsingMultiQueries(multiQueriesEnabled, nbrCommands, individualStatementTimeout);  if (locallyScopedConn.getEnableQueryTimeouts() && individualStatementTimeout != 0 && locallyScopedConn.versionMeetsMinimum(5, 0, 0)) { timeoutTask = new CancelTask(this); locallyScopedConn.getCancelTimer().schedule(timeoutTask, individualStatementTimeout); }  updateCounts = new long[nbrCommands]; for (int i = 0; i < nbrCommands; i++) updateCounts[i] = -3L;  SQLException sqlEx = null; int commandIndex = 0; for (commandIndex = 0; commandIndex < nbrCommands; commandIndex++) { try { String sql = (String)this.batchedArgs.get(commandIndex); updateCounts[commandIndex] = executeUpdateInternal(sql, true, true); getBatchedGeneratedKeys((this.results.getFirstCharOfQuery() == 'I' && containsOnDuplicateKeyInString(sql)) ? 1 : 0); } catch (SQLException ex) { updateCounts[commandIndex] = -3L; if (this.continueBatchOnError && !(ex instanceof MySQLTimeoutException) && !(ex instanceof MySQLStatementCancelledException) && !hasDeadlockOrTimeoutRolledBackTx(ex)) { sqlEx = ex; } else { long[] newUpdateCounts = new long[commandIndex]; if (hasDeadlockOrTimeoutRolledBackTx(ex)) { for (int j = 0; j < newUpdateCounts.length; j++) newUpdateCounts[j] = -3L;  } else { System.arraycopy(updateCounts, 0, newUpdateCounts, 0, commandIndex); }  throw SQLError.createBatchUpdateException(ex, newUpdateCounts, getExceptionInterceptor()); }  }  }  if (sqlEx != null) throw SQLError.createBatchUpdateException(sqlEx, updateCounts, getExceptionInterceptor());  }  if (timeoutTask != null) { if (timeoutTask.caughtWhileCancelling != null) throw timeoutTask.caughtWhileCancelling;  timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); timeoutTask = null; }  return (updateCounts != null) ? updateCounts : new long[0]; } finally { this.statementExecuting.set(false); }  } finally { if (timeoutTask != null) { timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); }  resetCancelledState(); this.timeoutInMillis = individualStatementTimeout; clearBatch(); }  }  } protected final boolean hasDeadlockOrTimeoutRolledBackTx(SQLException ex) { int vendorCode = ex.getErrorCode(); switch (vendorCode) { case 1206: case 1213: return true;case 1205: return !this.version5013OrNewer; }  return false; } private long[] executeBatchUsingMultiQueries(boolean multiQueriesEnabled, int nbrCommands, int individualStatementTimeout) throws SQLException { MySQLConnection locallyScopedConn = checkClosed(); synchronized (locallyScopedConn.getConnectionMutex()) { if (!multiQueriesEnabled) locallyScopedConn.getIO().enableMultiQueries();  Statement batchStmt = null; CancelTask timeoutTask = null; try { long[] updateCounts = new long[nbrCommands]; for (int i = 0; i < nbrCommands; i++) updateCounts[i] = -3L;  int commandIndex = 0; StringBuilder queryBuf = new StringBuilder(); batchStmt = locallyScopedConn.createStatement(); if (locallyScopedConn.getEnableQueryTimeouts() && individualStatementTimeout != 0 && locallyScopedConn.versionMeetsMinimum(5, 0, 0)) { timeoutTask = new CancelTask((StatementImpl)batchStmt); locallyScopedConn.getCancelTimer().schedule(timeoutTask, individualStatementTimeout); }  int counter = 0; int numberOfBytesPerChar = 1; String connectionEncoding = locallyScopedConn.getEncoding(); if (StringUtils.startsWithIgnoreCase(connectionEncoding, "utf")) { numberOfBytesPerChar = 3; } else if (CharsetMapping.isMultibyteCharset(connectionEncoding)) { numberOfBytesPerChar = 2; }  int escapeAdjust = 1; batchStmt.setEscapeProcessing(this.doEscapeProcessing); if (this.doEscapeProcessing) escapeAdjust = 2;  SQLException sqlEx = null; int argumentSetsInBatchSoFar = 0; for (commandIndex = 0; commandIndex < nbrCommands; commandIndex++) { String nextQuery = (String)this.batchedArgs.get(commandIndex); if (((queryBuf.length() + nextQuery.length()) * numberOfBytesPerChar + 1 + 4) * escapeAdjust + 32 > this.connection.getMaxAllowedPacket()) { try { batchStmt.execute(queryBuf.toString(), 1); } catch (SQLException ex) { sqlEx = handleExceptionForBatch(commandIndex, argumentSetsInBatchSoFar, updateCounts, ex); }  counter = processMultiCountsAndKeys((StatementImpl)batchStmt, counter, updateCounts); queryBuf = new StringBuilder(); argumentSetsInBatchSoFar = 0; }  queryBuf.append(nextQuery); queryBuf.append(";"); argumentSetsInBatchSoFar++; }  if (queryBuf.length() > 0) { try { batchStmt.execute(queryBuf.toString(), 1); } catch (SQLException ex) { sqlEx = handleExceptionForBatch(commandIndex - 1, argumentSetsInBatchSoFar, updateCounts, ex); }  counter = processMultiCountsAndKeys((StatementImpl)batchStmt, counter, updateCounts); }  if (timeoutTask != null) { if (timeoutTask.caughtWhileCancelling != null) throw timeoutTask.caughtWhileCancelling;  timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); timeoutTask = null; }  if (sqlEx != null) throw SQLError.createBatchUpdateException(sqlEx, updateCounts, getExceptionInterceptor());  return (updateCounts != null) ? updateCounts : new long[0]; } finally { if (timeoutTask != null) { timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); }  resetCancelledState(); try { if (batchStmt != null) batchStmt.close();  } finally { if (!multiQueriesEnabled) locallyScopedConn.getIO().disableMultiQueries();  }  }  }  } protected int processMultiCountsAndKeys(StatementImpl batchedStatement, int updateCountCounter, long[] updateCounts) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { updateCounts[updateCountCounter++] = batchedStatement.getLargeUpdateCount(); boolean doGenKeys = (this.batchedGeneratedKeys != null); byte[][] row = (byte[][])null; if (doGenKeys) { long generatedKey = batchedStatement.getLastInsertID(); row = new byte[1][]; row[0] = StringUtils.getBytes(Long.toString(generatedKey)); this.batchedGeneratedKeys.add(new ByteArrayRow(row, getExceptionInterceptor())); }  while (batchedStatement.getMoreResults() || batchedStatement.getLargeUpdateCount() != -1L) { updateCounts[updateCountCounter++] = batchedStatement.getLargeUpdateCount(); if (doGenKeys) { long generatedKey = batchedStatement.getLastInsertID(); row = new byte[1][]; row[0] = StringUtils.getBytes(Long.toString(generatedKey)); this.batchedGeneratedKeys.add(new ByteArrayRow(row, getExceptionInterceptor())); }  }  return updateCountCounter; }  } protected SQLException handleExceptionForBatch(int endOfBatchIndex, int numValuesPerBatch, long[] updateCounts, SQLException ex) throws BatchUpdateException, SQLException { for (int j = endOfBatchIndex; j > endOfBatchIndex - numValuesPerBatch; j--) updateCounts[j] = -3L;  if (this.continueBatchOnError && !(ex instanceof MySQLTimeoutException) && !(ex instanceof MySQLStatementCancelledException) && !hasDeadlockOrTimeoutRolledBackTx(ex)) return ex;  long[] newUpdateCounts = new long[endOfBatchIndex]; System.arraycopy(updateCounts, 0, newUpdateCounts, 0, endOfBatchIndex); throw SQLError.createBatchUpdateException(ex, newUpdateCounts, getExceptionInterceptor()); } public ResultSet executeQuery(String sql) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { MySQLConnection locallyScopedConn = this.connection; this.retrieveGeneratedKeys = false; checkNullOrEmptyQuery(sql); resetCancelledState(); implicitlyCloseAllOpenResults(); if (sql.charAt(0) == '/' && sql.startsWith("/* ping */")) { doPingInstead(); return this.results; }  setupStreamingTimeout(locallyScopedConn); if (this.doEscapeProcessing) { Object escapedSqlResult = EscapeProcessor.escapeSQL(sql, locallyScopedConn.serverSupportsConvertFn(), this.connection); if (escapedSqlResult instanceof String) { sql = (String)escapedSqlResult; } else { sql = ((EscapeProcessorResult)escapedSqlResult).escapedSql; }  }  char firstStatementChar = StringUtils.firstAlphaCharUc(sql, findStartOfStatement(sql)); checkForDml(sql, firstStatementChar); CachedResultSetMetaData cachedMetaData = null; if (useServerFetch()) { this.results = createResultSetUsingServerFetch(sql); return this.results; }  CancelTask timeoutTask = null; String oldCatalog = null; try { if (locallyScopedConn.getEnableQueryTimeouts() && this.timeoutInMillis != 0 && locallyScopedConn.versionMeetsMinimum(5, 0, 0)) { timeoutTask = new CancelTask(this); locallyScopedConn.getCancelTimer().schedule(timeoutTask, this.timeoutInMillis); }  if (!locallyScopedConn.getCatalog().equals(this.currentCatalog)) { oldCatalog = locallyScopedConn.getCatalog(); locallyScopedConn.setCatalog(this.currentCatalog); }  Field[] cachedFields = null; if (locallyScopedConn.getCacheResultSetMetadata()) { cachedMetaData = locallyScopedConn.getCachedMetaData(sql); if (cachedMetaData != null) cachedFields = cachedMetaData.fields;  }  locallyScopedConn.setSessionMaxRows(this.maxRows); statementBegins(); this.results = locallyScopedConn.execSQL(this, sql, this.maxRows, (Buffer)null, this.resultSetType, this.resultSetConcurrency, createStreamingResultSet(), this.currentCatalog, cachedFields); if (timeoutTask != null) { if (timeoutTask.caughtWhileCancelling != null) throw timeoutTask.caughtWhileCancelling;  timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); timeoutTask = null; }  synchronized (this.cancelTimeoutMutex) { if (this.wasCancelled) { MySQLStatementCancelledException mySQLStatementCancelledException; SQLException cause = null; if (this.wasCancelledByTimeout) { MySQLTimeoutException mySQLTimeoutException = new MySQLTimeoutException(); } else { mySQLStatementCancelledException = new MySQLStatementCancelledException(); }  resetCancelledState(); throw mySQLStatementCancelledException; }  }  } finally { this.statementExecuting.set(false); if (timeoutTask != null) { timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); }  if (oldCatalog != null) locallyScopedConn.setCatalog(oldCatalog);  }  this.lastInsertId = this.results.getUpdateID(); if (cachedMetaData != null) { locallyScopedConn.initializeResultsMetadataFromCache(sql, cachedMetaData, this.results); } else if (this.connection.getCacheResultSetMetadata()) { locallyScopedConn.initializeResultsMetadataFromCache(sql, (CachedResultSetMetaData)null, this.results); }  return this.results; }  } protected void doPingInstead() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.pingTarget != null) { this.pingTarget.doPing(); } else { this.connection.ping(); }  ResultSetInternalMethods fakeSelectOneResultSet = generatePingResultSet(); this.results = fakeSelectOneResultSet; }  } protected ResultSetInternalMethods generatePingResultSet() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { Field[] fields = { new Field(null, "1", -5, 1) }; ArrayList<ResultSetRow> rows = new ArrayList<ResultSetRow>(); byte[] colVal = { 49 }; rows.add(new ByteArrayRow(new byte[][] { colVal }, getExceptionInterceptor())); return (ResultSetInternalMethods)DatabaseMetaData.buildResultSet(fields, rows, this.connection); }  } protected void executeSimpleNonQuery(MySQLConnection c, String nonQuery) throws SQLException { c.execSQL(this, nonQuery, -1, (Buffer)null, 1003, 1007, false, this.currentCatalog, (Field[])null, false).close(); } public int executeUpdate(String sql) throws SQLException { return Util.truncateAndConvertToInt(executeLargeUpdate(sql)); } protected long executeUpdateInternal(String sql, boolean isBatch, boolean returnGeneratedKeys) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { MySQLConnection locallyScopedConn = this.connection; checkNullOrEmptyQuery(sql); resetCancelledState(); char firstStatementChar = StringUtils.firstAlphaCharUc(sql, findStartOfStatement(sql)); this.retrieveGeneratedKeys = returnGeneratedKeys; this.lastQueryIsOnDupKeyUpdate = (returnGeneratedKeys && firstStatementChar == 'I' && containsOnDuplicateKeyInString(sql)); ResultSetInternalMethods rs = null; if (this.doEscapeProcessing) { Object escapedSqlResult = EscapeProcessor.escapeSQL(sql, this.connection.serverSupportsConvertFn(), this.connection); if (escapedSqlResult instanceof String) { sql = (String)escapedSqlResult; } else { sql = ((EscapeProcessorResult)escapedSqlResult).escapedSql; }  }  if (locallyScopedConn.isReadOnly(false)) throw SQLError.createSQLException(Messages.getString("Statement.42") + Messages.getString("Statement.43"), "S1009", getExceptionInterceptor());  if (StringUtils.startsWithIgnoreCaseAndWs(sql, "select")) throw SQLError.createSQLException(Messages.getString("Statement.46"), "01S03", getExceptionInterceptor());  implicitlyCloseAllOpenResults(); CancelTask timeoutTask = null; String oldCatalog = null; boolean readInfoMsgState = locallyScopedConn.isReadInfoMsgEnabled(); if (returnGeneratedKeys && firstStatementChar == 'R') locallyScopedConn.setReadInfoMsgEnabled(true);  try { if (locallyScopedConn.getEnableQueryTimeouts() && this.timeoutInMillis != 0 && locallyScopedConn.versionMeetsMinimum(5, 0, 0)) { timeoutTask = new CancelTask(this); locallyScopedConn.getCancelTimer().schedule(timeoutTask, this.timeoutInMillis); }  if (!locallyScopedConn.getCatalog().equals(this.currentCatalog)) { oldCatalog = locallyScopedConn.getCatalog(); locallyScopedConn.setCatalog(this.currentCatalog); }  locallyScopedConn.setSessionMaxRows(-1); statementBegins(); rs = locallyScopedConn.execSQL(this, sql, -1, (Buffer)null, 1003, 1007, false, this.currentCatalog, (Field[])null, isBatch); if (timeoutTask != null) { if (timeoutTask.caughtWhileCancelling != null) throw timeoutTask.caughtWhileCancelling;  timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); timeoutTask = null; }  synchronized (this.cancelTimeoutMutex) { if (this.wasCancelled) { MySQLStatementCancelledException mySQLStatementCancelledException; SQLException cause = null; if (this.wasCancelledByTimeout) { MySQLTimeoutException mySQLTimeoutException = new MySQLTimeoutException(); } else { mySQLStatementCancelledException = new MySQLStatementCancelledException(); }  resetCancelledState(); throw mySQLStatementCancelledException; }  }  } finally { locallyScopedConn.setReadInfoMsgEnabled(readInfoMsgState); if (timeoutTask != null) { timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); }  if (oldCatalog != null) locallyScopedConn.setCatalog(oldCatalog);  if (!isBatch) this.statementExecuting.set(false);  }  this.results = rs; rs.setFirstCharOfQuery(firstStatementChar); this.updateCount = rs.getUpdateCount(); this.lastInsertId = rs.getUpdateID(); return this.updateCount; }  } public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException { return Util.truncateAndConvertToInt(executeLargeUpdate(sql, autoGeneratedKeys)); } public int executeUpdate(String sql, int[] columnIndexes) throws SQLException { return Util.truncateAndConvertToInt(executeLargeUpdate(sql, columnIndexes)); } public int executeUpdate(String sql, String[] columnNames) throws SQLException { return Util.truncateAndConvertToInt(executeLargeUpdate(sql, columnNames)); } protected Calendar getCalendarInstanceForSessionOrNew() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.connection != null) return this.connection.getCalendarInstanceForSessionOrNew();  return new GregorianCalendar(); }  } public Connection getConnection() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { return this.connection; }  } public int getFetchDirection() throws SQLException { return 1000; } public int getFetchSize() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { return this.fetchSize; }  } public ResultSet getGeneratedKeys() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (!this.retrieveGeneratedKeys) throw SQLError.createSQLException(Messages.getString("Statement.GeneratedKeysNotRequested"), "S1009", getExceptionInterceptor());  if (this.batchedGeneratedKeys == null) { if (this.lastQueryIsOnDupKeyUpdate) return this.generatedKeysResults = getGeneratedKeysInternal(1L);  return this.generatedKeysResults = getGeneratedKeysInternal(); }  Field[] fields = new Field[1]; fields[0] = new Field("", "GENERATED_KEY", -5, 20); fields[0].setConnection(this.connection); this.generatedKeysResults = ResultSetImpl.getInstance(this.currentCatalog, fields, new RowDataStatic(this.batchedGeneratedKeys), this.connection, this, false); return this.generatedKeysResults; }  } protected ResultSetInternalMethods getGeneratedKeysInternal() throws SQLException { long numKeys = getLargeUpdateCount(); return getGeneratedKeysInternal(numKeys); } protected ResultSetInternalMethods getGeneratedKeysInternal(long numKeys) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { Field[] fields = new Field[1]; fields[0] = new Field("", "GENERATED_KEY", -5, 20); fields[0].setConnection(this.connection); fields[0].setUseOldNameMetadata(true); ArrayList<ResultSetRow> rowSet = new ArrayList<ResultSetRow>(); long beginAt = getLastInsertID(); if (beginAt < 0L) fields[0].setUnsigned();  if (this.results != null) { String serverInfo = this.results.getServerInfo(); if (numKeys > 0L && this.results.getFirstCharOfQuery() == 'R' && serverInfo != null && serverInfo.length() > 0) numKeys = getRecordCountFromInfo(serverInfo);  if (beginAt != 0L && numKeys > 0L) for (int i = 0; i < numKeys; i++) { byte[][] row = new byte[1][]; if (beginAt > 0L) { row[0] = StringUtils.getBytes(Long.toString(beginAt)); } else { byte[] asBytes = new byte[8]; asBytes[7] = (byte)(int)(beginAt & 0xFFL); asBytes[6] = (byte)(int)(beginAt >>> 8L); asBytes[5] = (byte)(int)(beginAt >>> 16L); asBytes[4] = (byte)(int)(beginAt >>> 24L); asBytes[3] = (byte)(int)(beginAt >>> 32L); asBytes[2] = (byte)(int)(beginAt >>> 40L); asBytes[1] = (byte)(int)(beginAt >>> 48L); asBytes[0] = (byte)(int)(beginAt >>> 56L); BigInteger val = new BigInteger(1, asBytes); row[0] = val.toString().getBytes(); }  rowSet.add(new ByteArrayRow(row, getExceptionInterceptor())); beginAt += this.connection.getAutoIncrementIncrement(); }   }  ResultSetImpl gkRs = ResultSetImpl.getInstance(this.currentCatalog, fields, new RowDataStatic(rowSet), this.connection, this, false); return gkRs; }  } public int getId() { return this.statementId; } public long getLastInsertID() { try { synchronized (checkClosed().getConnectionMutex()) { return this.lastInsertId; }  } catch (SQLException e) { throw new RuntimeException(e); }  } public long getLongUpdateCount() { try { synchronized (checkClosed().getConnectionMutex()) { if (this.results == null) return -1L;  if (this.results.reallyResult()) return -1L;  return this.updateCount; }  } catch (SQLException e) { throw new RuntimeException(e); }  } public int getMaxFieldSize() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { return this.maxFieldSize; }  } public int getMaxRows() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.maxRows <= 0) return 0;  return this.maxRows; }  } public void closeOnCompletion() throws SQLException { synchronized (checkClosed().getConnectionMutex())
/* 2562 */     { this.closeOnCompletion = true; }  }
/*      */   public boolean getMoreResults() throws SQLException { return getMoreResults(1); }
/*      */   public boolean getMoreResults(int current) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.results == null) return false;  boolean streamingMode = createStreamingResultSet(); if (streamingMode && this.results.reallyResult()) while (this.results.next());  ResultSetInternalMethods nextResultSet = this.results.getNextResultSet(); switch (current) { case 1: if (this.results != null) { if (!streamingMode && !this.connection.getDontTrackOpenResources()) this.results.realClose(false);  this.results.clearNextResult(); }  break;case 3: if (this.results != null) { if (!streamingMode && !this.connection.getDontTrackOpenResources()) this.results.realClose(false);  this.results.clearNextResult(); }  closeAllOpenResults(); break;case 2: if (!this.connection.getDontTrackOpenResources()) this.openResults.add(this.results);  this.results.clearNextResult(); break;default: throw SQLError.createSQLException(Messages.getString("Statement.19"), "S1009", getExceptionInterceptor()); }  this.results = nextResultSet; if (this.results == null) { this.updateCount = -1L; this.lastInsertId = -1L; } else if (this.results.reallyResult()) { this.updateCount = -1L; this.lastInsertId = -1L; } else { this.updateCount = this.results.getUpdateCount(); this.lastInsertId = this.results.getUpdateID(); }  boolean moreResults = (this.results != null && this.results.reallyResult()); if (!moreResults) checkAndPerformCloseOnCompletionAction();  return moreResults; }  }
/*      */   public int getQueryTimeout() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { return this.timeoutInMillis / 1000; }  }
/*      */   private long getRecordCountFromInfo(String serverInfo) { StringBuilder recordsBuf = new StringBuilder(); long recordsCount = 0L; long duplicatesCount = 0L; char c = Character.MIN_VALUE; int length = serverInfo.length(); int i = 0; for (; i < length; i++) { c = serverInfo.charAt(i); if (Character.isDigit(c)) break;  }  recordsBuf.append(c); i++; for (; i < length; i++) { c = serverInfo.charAt(i); if (!Character.isDigit(c)) break;  recordsBuf.append(c); }  recordsCount = Long.parseLong(recordsBuf.toString()); StringBuilder duplicatesBuf = new StringBuilder(); for (; i < length; i++) { c = serverInfo.charAt(i); if (Character.isDigit(c)) break;  }  duplicatesBuf.append(c); i++; for (; i < length; i++) { c = serverInfo.charAt(i); if (!Character.isDigit(c)) break;  duplicatesBuf.append(c); }  duplicatesCount = Long.parseLong(duplicatesBuf.toString()); return recordsCount - duplicatesCount; }
/* 2567 */   public ResultSet getResultSet() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { return (this.results != null && this.results.reallyResult()) ? this.results : null; }  } public int getResultSetConcurrency() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { return this.resultSetConcurrency; }  } public int getResultSetHoldability() throws SQLException { return 1; } protected ResultSetInternalMethods getResultSetInternal() { try { synchronized (checkClosed().getConnectionMutex()) { return this.results; }  } catch (SQLException e) { return this.results; }  } public int getResultSetType() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { return this.resultSetType; }  } public int getUpdateCount() throws SQLException { return Util.truncateAndConvertToInt(getLargeUpdateCount()); } public SQLWarning getWarnings() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.clearWarningsCalled) return null;  if (this.connection.versionMeetsMinimum(4, 1, 0)) { SQLWarning pendingWarningsFromServer = SQLError.convertShowWarningsToSQLWarnings(this.connection); if (this.warningChain != null) { this.warningChain.setNextWarning(pendingWarningsFromServer); } else { this.warningChain = pendingWarningsFromServer; }  return this.warningChain; }  return this.warningChain; }  } protected void realClose(boolean calledExplicitly, boolean closeOpenResults) throws SQLException { MySQLConnection locallyScopedConn = this.connection; if (locallyScopedConn == null || this.isClosed) return;  if (!locallyScopedConn.getDontTrackOpenResources()) locallyScopedConn.unregisterStatement(this);  if (this.useUsageAdvisor && !calledExplicitly) this.connection.getProfilerEventHandlerInstance().processEvent((byte)0, this.connection, this, null, 0L, new Throwable(), Messages.getString("Statement.63"));  if (closeOpenResults) closeOpenResults = (!this.holdResultsOpenOverClose && !this.connection.getDontTrackOpenResources());  if (closeOpenResults) { if (this.results != null) try { this.results.close(); } catch (Exception ex) {}  if (this.generatedKeysResults != null) try { this.generatedKeysResults.close(); } catch (Exception ex) {}  closeAllOpenResults(); }  this.isClosed = true; this.results = null; this.generatedKeysResults = null; this.connection = null; this.warningChain = null; this.openResults = null; this.batchedGeneratedKeys = null; this.localInfileInputStream = null; this.pingTarget = null; } public void setCursorName(String name) throws SQLException {} public void setEscapeProcessing(boolean enable) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { this.doEscapeProcessing = enable; }  } public void setFetchDirection(int direction) throws SQLException { switch (direction) { case 1000: case 1001: case 1002: return; }  throw SQLError.createSQLException(Messages.getString("Statement.5"), "S1009", getExceptionInterceptor()); } public void setFetchSize(int rows) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if ((rows < 0 && rows != Integer.MIN_VALUE) || (this.maxRows > 0 && rows > getMaxRows())) throw SQLError.createSQLException(Messages.getString("Statement.7"), "S1009", getExceptionInterceptor());  this.fetchSize = rows; }  } public void setHoldResultsOpenOverClose(boolean holdResultsOpenOverClose) { try { synchronized (checkClosed().getConnectionMutex()) { this.holdResultsOpenOverClose = holdResultsOpenOverClose; }  } catch (SQLException e) {} } public void setMaxFieldSize(int max) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (max < 0) throw SQLError.createSQLException(Messages.getString("Statement.11"), "S1009", getExceptionInterceptor());  int maxBuf = (this.connection != null) ? this.connection.getMaxAllowedPacket() : MysqlIO.getMaxBuf(); if (max > maxBuf) throw SQLError.createSQLException(Messages.getString("Statement.13", new Object[] { Long.valueOf(maxBuf) }), "S1009", getExceptionInterceptor());  this.maxFieldSize = max; }  } public void setMaxRows(int max) throws SQLException { setLargeMaxRows(max); } public void setQueryTimeout(int seconds) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (seconds < 0) throw SQLError.createSQLException(Messages.getString("Statement.21"), "S1009", getExceptionInterceptor());  this.timeoutInMillis = seconds * 1000; }  } void setResultSetConcurrency(int concurrencyFlag) { try { synchronized (checkClosed().getConnectionMutex()) { this.resultSetConcurrency = concurrencyFlag; }  } catch (SQLException e) {} } void setResultSetType(int typeFlag) { try { synchronized (checkClosed().getConnectionMutex()) { this.resultSetType = typeFlag; }  } catch (SQLException e) {} } protected void getBatchedGeneratedKeys(Statement batchedStatement) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.retrieveGeneratedKeys) { ResultSet rs = null; try { rs = batchedStatement.getGeneratedKeys(); while (rs.next()) { this.batchedGeneratedKeys.add(new ByteArrayRow(new byte[][] { rs.getBytes(1) }, getExceptionInterceptor())); }  } finally { if (rs != null) rs.close();  }  }  }  } protected void getBatchedGeneratedKeys(int maxKeys) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.retrieveGeneratedKeys) { ResultSet rs = null; try { if (maxKeys == 0) { rs = getGeneratedKeysInternal(); } else { rs = getGeneratedKeysInternal(maxKeys); }  while (rs.next()) { this.batchedGeneratedKeys.add(new ByteArrayRow(new byte[][] { rs.getBytes(1) }, getExceptionInterceptor())); }  } finally { this.isImplicitlyClosingResults = true; try { if (rs != null) rs.close();  } finally { this.isImplicitlyClosingResults = false; }  }  }  }  } private boolean useServerFetch() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { return (this.connection.isCursorFetchEnabled() && this.fetchSize > 0 && this.resultSetConcurrency == 1007 && this.resultSetType == 1003); }  } public boolean isClosed() throws SQLException { MySQLConnection locallyScopedConn = this.connection; if (locallyScopedConn == null) return true;  synchronized (locallyScopedConn.getConnectionMutex()) { return this.isClosed; }  } public boolean isPoolable() throws SQLException { return this.isPoolable; } public void setPoolable(boolean poolable) throws SQLException { this.isPoolable = poolable; } public boolean isWrapperFor(Class<?> iface) throws SQLException { checkClosed(); return iface.isInstance(this); } public <T> T unwrap(Class<T> iface) throws SQLException { try { return iface.cast(this); } catch (ClassCastException cce) { throw SQLError.createSQLException("Unable to unwrap to " + iface.toString(), "S1009", getExceptionInterceptor()); }  } protected static int findStartOfStatement(String sql) { int statementStartPos = 0; if (StringUtils.startsWithIgnoreCaseAndWs(sql, "/*")) { statementStartPos = sql.indexOf("*/"); if (statementStartPos == -1) { statementStartPos = 0; } else { statementStartPos += 2; }  } else if (StringUtils.startsWithIgnoreCaseAndWs(sql, "--") || StringUtils.startsWithIgnoreCaseAndWs(sql, "#")) { statementStartPos = sql.indexOf('\n'); if (statementStartPos == -1) { statementStartPos = sql.indexOf('\r'); if (statementStartPos == -1) statementStartPos = 0;  }  }  return statementStartPos; } public InputStream getLocalInfileInputStream() { return this.localInfileInputStream; } public void setLocalInfileInputStream(InputStream stream) { this.localInfileInputStream = stream; } public void setPingTarget(PingTarget pingTarget) { this.pingTarget = pingTarget; } public ExceptionInterceptor getExceptionInterceptor() { return this.exceptionInterceptor; } protected boolean containsOnDuplicateKeyInString(String sql) { return (getOnDuplicateKeyLocation(sql, this.connection.getDontCheckOnDuplicateKeyUpdateInSQL(), this.connection.getRewriteBatchedStatements(), this.connection.isNoBackslashEscapesSet()) != -1); } protected static int getOnDuplicateKeyLocation(String sql, boolean dontCheckOnDuplicateKeyUpdateInSQL, boolean rewriteBatchedStatements, boolean noBackslashEscapes) { return (dontCheckOnDuplicateKeyUpdateInSQL && !rewriteBatchedStatements) ? -1 : StringUtils.indexOfIgnoreCase(0, sql, ON_DUPLICATE_KEY_UPDATE_CLAUSE, "\"'`", "\"'`", noBackslashEscapes ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL); } public boolean isCloseOnCompletion() throws SQLException { synchronized (checkClosed().getConnectionMutex()) {
/* 2568 */       return this.closeOnCompletion;
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long[] executeLargeBatch() throws SQLException {
/* 2577 */     return executeBatchInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long executeLargeUpdate(String sql) throws SQLException {
/* 2585 */     return executeUpdateInternal(sql, false, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
/* 2593 */     return executeUpdateInternal(sql, false, (autoGeneratedKeys == 1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
/* 2601 */     return executeUpdateInternal(sql, false, (columnIndexes != null && columnIndexes.length > 0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
/* 2609 */     return executeUpdateInternal(sql, false, (columnNames != null && columnNames.length > 0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLargeMaxRows() throws SQLException {
/* 2618 */     return getMaxRows();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLargeUpdateCount() throws SQLException {
/* 2626 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2627 */       if (this.results == null) {
/* 2628 */         return -1L;
/*      */       }
/*      */       
/* 2631 */       if (this.results.reallyResult()) {
/* 2632 */         return -1L;
/*      */       }
/*      */       
/* 2635 */       return this.results.getUpdateCount();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLargeMaxRows(long max) throws SQLException {
/* 2644 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2645 */       if (max > 50000000L || max < 0L) {
/* 2646 */         throw SQLError.createSQLException(Messages.getString("Statement.15") + max + " > " + 50000000 + ".", "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/* 2650 */       if (max == 0L) {
/* 2651 */         max = -1L;
/*      */       }
/*      */       
/* 2654 */       this.maxRows = (int)max;
/*      */     } 
/*      */   }
/*      */   
/*      */   boolean isCursorRequired() throws SQLException {
/* 2659 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\StatementImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */