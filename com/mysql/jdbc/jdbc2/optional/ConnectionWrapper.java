/*      */ package com.mysql.jdbc.jdbc2.optional;
/*      */ 
/*      */ import com.mysql.jdbc.Connection;
/*      */ import com.mysql.jdbc.ExceptionInterceptor;
/*      */ import com.mysql.jdbc.Extension;
/*      */ import com.mysql.jdbc.MySQLConnection;
/*      */ import com.mysql.jdbc.SQLError;
/*      */ import com.mysql.jdbc.Util;
/*      */ import com.mysql.jdbc.log.Log;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Savepoint;
/*      */ import java.sql.Statement;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.TimeZone;
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
/*      */ public class ConnectionWrapper
/*      */   extends WrapperBase
/*      */   implements Connection
/*      */ {
/*   54 */   protected Connection mc = null;
/*      */   
/*   56 */   private String invalidHandleStr = "Logical handle no longer valid";
/*      */   
/*      */   private boolean closed;
/*      */   
/*      */   private boolean isForXa;
/*      */   
/*      */   private static final Constructor<?> JDBC_4_CONNECTION_WRAPPER_CTOR;
/*      */   
/*      */   static {
/*   65 */     if (Util.isJdbc4()) {
/*      */       try {
/*   67 */         JDBC_4_CONNECTION_WRAPPER_CTOR = Class.forName("com.mysql.jdbc.jdbc2.optional.JDBC4ConnectionWrapper").getConstructor(new Class[] { MysqlPooledConnection.class, Connection.class, boolean.class });
/*      */       }
/*   69 */       catch (SecurityException e) {
/*   70 */         throw new RuntimeException(e);
/*   71 */       } catch (NoSuchMethodException e) {
/*   72 */         throw new RuntimeException(e);
/*   73 */       } catch (ClassNotFoundException e) {
/*   74 */         throw new RuntimeException(e);
/*      */       } 
/*      */     } else {
/*   77 */       JDBC_4_CONNECTION_WRAPPER_CTOR = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static ConnectionWrapper getInstance(MysqlPooledConnection mysqlPooledConnection, Connection mysqlConnection, boolean forXa) throws SQLException {
/*   82 */     if (!Util.isJdbc4()) {
/*   83 */       return new ConnectionWrapper(mysqlPooledConnection, mysqlConnection, forXa);
/*      */     }
/*      */     
/*   86 */     return (ConnectionWrapper)Util.handleNewInstance(JDBC_4_CONNECTION_WRAPPER_CTOR, new Object[] { mysqlPooledConnection, mysqlConnection, Boolean.valueOf(forXa) }, mysqlPooledConnection.getExceptionInterceptor());
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
/*      */   public ConnectionWrapper(MysqlPooledConnection mysqlPooledConnection, Connection mysqlConnection, boolean forXa) throws SQLException {
/*  102 */     super(mysqlPooledConnection);
/*      */     
/*  104 */     this.mc = mysqlConnection;
/*  105 */     this.closed = false;
/*  106 */     this.isForXa = forXa;
/*      */     
/*  108 */     if (this.isForXa) {
/*  109 */       setInGlobalTx(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoCommit(boolean autoCommit) throws SQLException {
/*  120 */     checkClosed();
/*      */     
/*  122 */     if (autoCommit && isInGlobalTx()) {
/*  123 */       throw SQLError.createSQLException("Can't set autocommit to 'true' on an XAConnection", "2D000", 1401, this.exceptionInterceptor);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  128 */       this.mc.setAutoCommit(autoCommit);
/*  129 */     } catch (SQLException sqlException) {
/*  130 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAutoCommit() throws SQLException {
/*  141 */     checkClosed();
/*      */     
/*      */     try {
/*  144 */       return this.mc.getAutoCommit();
/*  145 */     } catch (SQLException sqlException) {
/*  146 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  149 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCatalog(String catalog) throws SQLException {
/*  159 */     checkClosed();
/*      */     
/*      */     try {
/*  162 */       this.mc.setCatalog(catalog);
/*  163 */     } catch (SQLException sqlException) {
/*  164 */       checkAndFireConnectionError(sqlException);
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
/*      */   public String getCatalog() throws SQLException {
/*  178 */     checkClosed();
/*      */     
/*      */     try {
/*  181 */       return this.mc.getCatalog();
/*  182 */     } catch (SQLException sqlException) {
/*  183 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  186 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isClosed() throws SQLException {
/*  196 */     return (this.closed || this.mc.isClosed());
/*      */   }
/*      */   
/*      */   public boolean isMasterConnection() {
/*  200 */     return this.mc.isMasterConnection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHoldability(int arg0) throws SQLException {
/*  207 */     checkClosed();
/*      */     
/*      */     try {
/*  210 */       this.mc.setHoldability(arg0);
/*  211 */     } catch (SQLException sqlException) {
/*  212 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHoldability() throws SQLException {
/*  220 */     checkClosed();
/*      */     
/*      */     try {
/*  223 */       return this.mc.getHoldability();
/*  224 */     } catch (SQLException sqlException) {
/*  225 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  228 */       return 1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getIdleFor() {
/*  238 */     return this.mc.getIdleFor();
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
/*      */   public DatabaseMetaData getMetaData() throws SQLException {
/*  251 */     checkClosed();
/*      */     
/*      */     try {
/*  254 */       return this.mc.getMetaData();
/*  255 */     } catch (SQLException sqlException) {
/*  256 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  259 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReadOnly(boolean readOnly) throws SQLException {
/*  269 */     checkClosed();
/*      */     
/*      */     try {
/*  272 */       this.mc.setReadOnly(readOnly);
/*  273 */     } catch (SQLException sqlException) {
/*  274 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isReadOnly() throws SQLException {
/*  285 */     checkClosed();
/*      */     
/*      */     try {
/*  288 */       return this.mc.isReadOnly();
/*  289 */     } catch (SQLException sqlException) {
/*  290 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  293 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Savepoint setSavepoint() throws SQLException {
/*  300 */     checkClosed();
/*      */     
/*  302 */     if (isInGlobalTx()) {
/*  303 */       throw SQLError.createSQLException("Can't set autocommit to 'true' on an XAConnection", "2D000", 1401, this.exceptionInterceptor);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  308 */       return this.mc.setSavepoint();
/*  309 */     } catch (SQLException sqlException) {
/*  310 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  313 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Savepoint setSavepoint(String arg0) throws SQLException {
/*  320 */     checkClosed();
/*      */     
/*  322 */     if (isInGlobalTx()) {
/*  323 */       throw SQLError.createSQLException("Can't set autocommit to 'true' on an XAConnection", "2D000", 1401, this.exceptionInterceptor);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  328 */       return this.mc.setSavepoint(arg0);
/*  329 */     } catch (SQLException sqlException) {
/*  330 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  333 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransactionIsolation(int level) throws SQLException {
/*  343 */     checkClosed();
/*      */     
/*      */     try {
/*  346 */       this.mc.setTransactionIsolation(level);
/*  347 */     } catch (SQLException sqlException) {
/*  348 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTransactionIsolation() throws SQLException {
/*  359 */     checkClosed();
/*      */     
/*      */     try {
/*  362 */       return this.mc.getTransactionIsolation();
/*  363 */     } catch (SQLException sqlException) {
/*  364 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  367 */       return 4;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map<String, Class<?>> getTypeMap() throws SQLException {
/*  378 */     checkClosed();
/*      */     
/*      */     try {
/*  381 */       return this.mc.getTypeMap();
/*  382 */     } catch (SQLException sqlException) {
/*  383 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  386 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SQLWarning getWarnings() throws SQLException {
/*  396 */     checkClosed();
/*      */     
/*      */     try {
/*  399 */       return this.mc.getWarnings();
/*  400 */     } catch (SQLException sqlException) {
/*  401 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  404 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearWarnings() throws SQLException {
/*  415 */     checkClosed();
/*      */     
/*      */     try {
/*  418 */       this.mc.clearWarnings();
/*  419 */     } catch (SQLException sqlException) {
/*  420 */       checkAndFireConnectionError(sqlException);
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
/*      */   public void close() throws SQLException {
/*  435 */     close(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void commit() throws SQLException {
/*  446 */     checkClosed();
/*      */     
/*  448 */     if (isInGlobalTx()) {
/*  449 */       throw SQLError.createSQLException("Can't call commit() on an XAConnection associated with a global transaction", "2D000", 1401, this.exceptionInterceptor);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  454 */       this.mc.commit();
/*  455 */     } catch (SQLException sqlException) {
/*  456 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Statement createStatement() throws SQLException {
/*  467 */     checkClosed();
/*      */     
/*      */     try {
/*  470 */       return StatementWrapper.getInstance(this, this.pooledConnection, this.mc.createStatement());
/*  471 */     } catch (SQLException sqlException) {
/*  472 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  475 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
/*  485 */     checkClosed();
/*      */     
/*      */     try {
/*  488 */       return StatementWrapper.getInstance(this, this.pooledConnection, this.mc.createStatement(resultSetType, resultSetConcurrency));
/*  489 */     } catch (SQLException sqlException) {
/*  490 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  493 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Statement createStatement(int arg0, int arg1, int arg2) throws SQLException {
/*  500 */     checkClosed();
/*      */     
/*      */     try {
/*  503 */       return StatementWrapper.getInstance(this, this.pooledConnection, this.mc.createStatement(arg0, arg1, arg2));
/*  504 */     } catch (SQLException sqlException) {
/*  505 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  508 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String nativeSQL(String sql) throws SQLException {
/*  518 */     checkClosed();
/*      */     
/*      */     try {
/*  521 */       return this.mc.nativeSQL(sql);
/*  522 */     } catch (SQLException sqlException) {
/*  523 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  526 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CallableStatement prepareCall(String sql) throws SQLException {
/*  536 */     checkClosed();
/*      */     
/*      */     try {
/*  539 */       return CallableStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareCall(sql));
/*  540 */     } catch (SQLException sqlException) {
/*  541 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  544 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/*  554 */     checkClosed();
/*      */     
/*      */     try {
/*  557 */       return CallableStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareCall(sql, resultSetType, resultSetConcurrency));
/*  558 */     } catch (SQLException sqlException) {
/*  559 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  562 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException {
/*  569 */     checkClosed();
/*      */     
/*      */     try {
/*  572 */       return CallableStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareCall(arg0, arg1, arg2, arg3));
/*  573 */     } catch (SQLException sqlException) {
/*  574 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  577 */       return null;
/*      */     } 
/*      */   }
/*      */   public PreparedStatement clientPrepare(String sql) throws SQLException {
/*  581 */     checkClosed();
/*      */     
/*      */     try {
/*  584 */       return new PreparedStatementWrapper(this, this.pooledConnection, this.mc.clientPrepareStatement(sql));
/*  585 */     } catch (SQLException sqlException) {
/*  586 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  589 */       return null;
/*      */     } 
/*      */   }
/*      */   public PreparedStatement clientPrepare(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/*  593 */     checkClosed();
/*      */     
/*      */     try {
/*  596 */       return new PreparedStatementWrapper(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, resultSetType, resultSetConcurrency));
/*  597 */     } catch (SQLException sqlException) {
/*  598 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  601 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql) throws SQLException {
/*  611 */     checkClosed();
/*      */     
/*  613 */     PreparedStatement res = null;
/*      */     
/*      */     try {
/*  616 */       res = PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(sql));
/*  617 */     } catch (SQLException sqlException) {
/*  618 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */     
/*  621 */     return res;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/*  631 */     checkClosed();
/*      */     
/*      */     try {
/*  634 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(sql, resultSetType, resultSetConcurrency));
/*  635 */     } catch (SQLException sqlException) {
/*  636 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  639 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException {
/*  646 */     checkClosed();
/*      */     
/*      */     try {
/*  649 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(arg0, arg1, arg2, arg3));
/*  650 */     } catch (SQLException sqlException) {
/*  651 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  654 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException {
/*  661 */     checkClosed();
/*      */     
/*      */     try {
/*  664 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(arg0, arg1));
/*  665 */     } catch (SQLException sqlException) {
/*  666 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  669 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException {
/*  676 */     checkClosed();
/*      */     
/*      */     try {
/*  679 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(arg0, arg1));
/*  680 */     } catch (SQLException sqlException) {
/*  681 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  684 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException {
/*  691 */     checkClosed();
/*      */     
/*      */     try {
/*  694 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(arg0, arg1));
/*  695 */     } catch (SQLException sqlException) {
/*  696 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  699 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void releaseSavepoint(Savepoint arg0) throws SQLException {
/*  706 */     checkClosed();
/*      */     
/*      */     try {
/*  709 */       this.mc.releaseSavepoint(arg0);
/*  710 */     } catch (SQLException sqlException) {
/*  711 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void rollback() throws SQLException {
/*  722 */     checkClosed();
/*      */     
/*  724 */     if (isInGlobalTx()) {
/*  725 */       throw SQLError.createSQLException("Can't call rollback() on an XAConnection associated with a global transaction", "2D000", 1401, this.exceptionInterceptor);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  730 */       this.mc.rollback();
/*  731 */     } catch (SQLException sqlException) {
/*  732 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void rollback(Savepoint arg0) throws SQLException {
/*  740 */     checkClosed();
/*      */     
/*  742 */     if (isInGlobalTx()) {
/*  743 */       throw SQLError.createSQLException("Can't call rollback() on an XAConnection associated with a global transaction", "2D000", 1401, this.exceptionInterceptor);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  748 */       this.mc.rollback(arg0);
/*  749 */     } catch (SQLException sqlException) {
/*  750 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isSameResource(Connection c) {
/*  755 */     if (c instanceof ConnectionWrapper) {
/*  756 */       return this.mc.isSameResource(((ConnectionWrapper)c).mc);
/*      */     }
/*  758 */     return this.mc.isSameResource(c);
/*      */   }
/*      */   
/*      */   protected void close(boolean fireClosedEvent) throws SQLException {
/*  762 */     synchronized (this.pooledConnection) {
/*  763 */       if (this.closed) {
/*      */         return;
/*      */       }
/*      */       
/*  767 */       if (!isInGlobalTx() && this.mc.getRollbackOnPooledClose() && !getAutoCommit()) {
/*  768 */         rollback();
/*      */       }
/*      */       
/*  771 */       if (fireClosedEvent) {
/*  772 */         this.pooledConnection.callConnectionEventListeners(2, null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  777 */       this.closed = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void checkClosed() throws SQLException {
/*  782 */     if (this.closed) {
/*  783 */       throw SQLError.createSQLException(this.invalidHandleStr, this.exceptionInterceptor);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isInGlobalTx() {
/*  788 */     return this.mc.isInGlobalTx();
/*      */   }
/*      */   
/*      */   public void setInGlobalTx(boolean flag) {
/*  792 */     this.mc.setInGlobalTx(flag);
/*      */   }
/*      */   
/*      */   public void ping() throws SQLException {
/*  796 */     if (this.mc != null) {
/*  797 */       this.mc.ping();
/*      */     }
/*      */   }
/*      */   
/*      */   public void changeUser(String userName, String newPassword) throws SQLException {
/*  802 */     checkClosed();
/*      */     
/*      */     try {
/*  805 */       this.mc.changeUser(userName, newPassword);
/*  806 */     } catch (SQLException sqlException) {
/*  807 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public void clearHasTriedMaster() {
/*  813 */     this.mc.clearHasTriedMaster();
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql) throws SQLException {
/*  817 */     checkClosed();
/*      */     
/*      */     try {
/*  820 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql));
/*  821 */     } catch (SQLException sqlException) {
/*  822 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  825 */       return null;
/*      */     } 
/*      */   }
/*      */   public PreparedStatement clientPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
/*      */     try {
/*  830 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, autoGenKeyIndex));
/*  831 */     } catch (SQLException sqlException) {
/*  832 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  835 */       return null;
/*      */     } 
/*      */   }
/*      */   public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/*      */     try {
/*  840 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, resultSetType, resultSetConcurrency));
/*  841 */     } catch (SQLException sqlException) {
/*  842 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  845 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/*      */     try {
/*  851 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
/*      */     }
/*  853 */     catch (SQLException sqlException) {
/*  854 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  857 */       return null;
/*      */     } 
/*      */   }
/*      */   public PreparedStatement clientPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
/*      */     try {
/*  862 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, autoGenKeyIndexes));
/*  863 */     } catch (SQLException sqlException) {
/*  864 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  867 */       return null;
/*      */     } 
/*      */   }
/*      */   public PreparedStatement clientPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
/*      */     try {
/*  872 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, autoGenKeyColNames));
/*  873 */     } catch (SQLException sqlException) {
/*  874 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  877 */       return null;
/*      */     } 
/*      */   }
/*      */   public int getActiveStatementCount() {
/*  881 */     return this.mc.getActiveStatementCount();
/*      */   }
/*      */   
/*      */   public Log getLog() throws SQLException {
/*  885 */     return this.mc.getLog();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String getServerCharacterEncoding() {
/*  893 */     return getServerCharset();
/*      */   }
/*      */   
/*      */   public String getServerCharset() {
/*  897 */     return this.mc.getServerCharset();
/*      */   }
/*      */   
/*      */   public TimeZone getServerTimezoneTZ() {
/*  901 */     return this.mc.getServerTimezoneTZ();
/*      */   }
/*      */   
/*      */   public String getStatementComment() {
/*  905 */     return this.mc.getStatementComment();
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public boolean hasTriedMaster() {
/*  910 */     return this.mc.hasTriedMaster();
/*      */   }
/*      */   
/*      */   public boolean isAbonormallyLongQuery(long millisOrNanos) {
/*  914 */     return this.mc.isAbonormallyLongQuery(millisOrNanos);
/*      */   }
/*      */   
/*      */   public boolean isNoBackslashEscapesSet() {
/*  918 */     return this.mc.isNoBackslashEscapesSet();
/*      */   }
/*      */   
/*      */   public boolean lowerCaseTableNames() {
/*  922 */     return this.mc.lowerCaseTableNames();
/*      */   }
/*      */   
/*      */   public boolean parserKnowsUnicode() {
/*  926 */     return this.mc.parserKnowsUnicode();
/*      */   }
/*      */   
/*      */   public void reportQueryTime(long millisOrNanos) {
/*  930 */     this.mc.reportQueryTime(millisOrNanos);
/*      */   }
/*      */   
/*      */   public void resetServerState() throws SQLException {
/*  934 */     checkClosed();
/*      */     
/*      */     try {
/*  937 */       this.mc.resetServerState();
/*  938 */     } catch (SQLException sqlException) {
/*  939 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql) throws SQLException {
/*  944 */     checkClosed();
/*      */     
/*      */     try {
/*  947 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql));
/*  948 */     } catch (SQLException sqlException) {
/*  949 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  952 */       return null;
/*      */     } 
/*      */   }
/*      */   public PreparedStatement serverPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
/*      */     try {
/*  957 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql, autoGenKeyIndex));
/*  958 */     } catch (SQLException sqlException) {
/*  959 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  962 */       return null;
/*      */     } 
/*      */   }
/*      */   public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/*      */     try {
/*  967 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql, resultSetType, resultSetConcurrency));
/*  968 */     } catch (SQLException sqlException) {
/*  969 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  972 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/*      */     try {
/*  978 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
/*      */     }
/*  980 */     catch (SQLException sqlException) {
/*  981 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  984 */       return null;
/*      */     } 
/*      */   }
/*      */   public PreparedStatement serverPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
/*      */     try {
/*  989 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql, autoGenKeyIndexes));
/*  990 */     } catch (SQLException sqlException) {
/*  991 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/*  994 */       return null;
/*      */     } 
/*      */   }
/*      */   public PreparedStatement serverPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
/*      */     try {
/*  999 */       return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql, autoGenKeyColNames));
/* 1000 */     } catch (SQLException sqlException) {
/* 1001 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/* 1004 */       return null;
/*      */     } 
/*      */   }
/*      */   public void setFailedOver(boolean flag) {
/* 1008 */     this.mc.setFailedOver(flag);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setPreferSlaveDuringFailover(boolean flag) {
/* 1014 */     this.mc.setPreferSlaveDuringFailover(flag);
/*      */   }
/*      */   
/*      */   public void setStatementComment(String comment) {
/* 1018 */     this.mc.setStatementComment(comment);
/*      */   }
/*      */ 
/*      */   
/*      */   public void shutdownServer() throws SQLException {
/* 1023 */     checkClosed();
/*      */     
/*      */     try {
/* 1026 */       this.mc.shutdownServer();
/* 1027 */     } catch (SQLException sqlException) {
/* 1028 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean supportsIsolationLevel() {
/* 1034 */     return this.mc.supportsIsolationLevel();
/*      */   }
/*      */   
/*      */   public boolean supportsQuotedIdentifiers() {
/* 1038 */     return this.mc.supportsQuotedIdentifiers();
/*      */   }
/*      */   
/*      */   public boolean supportsTransactions() {
/* 1042 */     return this.mc.supportsTransactions();
/*      */   }
/*      */   
/*      */   public boolean versionMeetsMinimum(int major, int minor, int subminor) throws SQLException {
/* 1046 */     checkClosed();
/*      */     
/*      */     try {
/* 1049 */       return this.mc.versionMeetsMinimum(major, minor, subminor);
/* 1050 */     } catch (SQLException sqlException) {
/* 1051 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/* 1054 */       return false;
/*      */     } 
/*      */   }
/*      */   public String exposeAsXml() throws SQLException {
/* 1058 */     checkClosed();
/*      */     
/*      */     try {
/* 1061 */       return this.mc.exposeAsXml();
/* 1062 */     } catch (SQLException sqlException) {
/* 1063 */       checkAndFireConnectionError(sqlException);
/*      */ 
/*      */       
/* 1066 */       return null;
/*      */     } 
/*      */   }
/*      */   public boolean getAllowLoadLocalInfile() {
/* 1070 */     return this.mc.getAllowLoadLocalInfile();
/*      */   }
/*      */   
/*      */   public boolean getAllowMultiQueries() {
/* 1074 */     return this.mc.getAllowMultiQueries();
/*      */   }
/*      */   
/*      */   public boolean getAllowNanAndInf() {
/* 1078 */     return this.mc.getAllowNanAndInf();
/*      */   }
/*      */   
/*      */   public boolean getAllowUrlInLocalInfile() {
/* 1082 */     return this.mc.getAllowUrlInLocalInfile();
/*      */   }
/*      */   
/*      */   public boolean getAlwaysSendSetIsolation() {
/* 1086 */     return this.mc.getAlwaysSendSetIsolation();
/*      */   }
/*      */   
/*      */   public boolean getAutoClosePStmtStreams() {
/* 1090 */     return this.mc.getAutoClosePStmtStreams();
/*      */   }
/*      */   
/*      */   public boolean getAutoDeserialize() {
/* 1094 */     return this.mc.getAutoDeserialize();
/*      */   }
/*      */   
/*      */   public boolean getAutoGenerateTestcaseScript() {
/* 1098 */     return this.mc.getAutoGenerateTestcaseScript();
/*      */   }
/*      */   
/*      */   public boolean getAutoReconnectForPools() {
/* 1102 */     return this.mc.getAutoReconnectForPools();
/*      */   }
/*      */   
/*      */   public boolean getAutoSlowLog() {
/* 1106 */     return this.mc.getAutoSlowLog();
/*      */   }
/*      */   
/*      */   public int getBlobSendChunkSize() {
/* 1110 */     return this.mc.getBlobSendChunkSize();
/*      */   }
/*      */   
/*      */   public boolean getBlobsAreStrings() {
/* 1114 */     return this.mc.getBlobsAreStrings();
/*      */   }
/*      */   
/*      */   public boolean getCacheCallableStatements() {
/* 1118 */     return this.mc.getCacheCallableStatements();
/*      */   }
/*      */   
/*      */   public boolean getCacheCallableStmts() {
/* 1122 */     return this.mc.getCacheCallableStmts();
/*      */   }
/*      */   
/*      */   public boolean getCachePrepStmts() {
/* 1126 */     return this.mc.getCachePrepStmts();
/*      */   }
/*      */   
/*      */   public boolean getCachePreparedStatements() {
/* 1130 */     return this.mc.getCachePreparedStatements();
/*      */   }
/*      */   
/*      */   public boolean getCacheResultSetMetadata() {
/* 1134 */     return this.mc.getCacheResultSetMetadata();
/*      */   }
/*      */   
/*      */   public boolean getCacheServerConfiguration() {
/* 1138 */     return this.mc.getCacheServerConfiguration();
/*      */   }
/*      */   
/*      */   public int getCallableStatementCacheSize() {
/* 1142 */     return this.mc.getCallableStatementCacheSize();
/*      */   }
/*      */   
/*      */   public int getCallableStmtCacheSize() {
/* 1146 */     return this.mc.getCallableStmtCacheSize();
/*      */   }
/*      */   
/*      */   public boolean getCapitalizeTypeNames() {
/* 1150 */     return this.mc.getCapitalizeTypeNames();
/*      */   }
/*      */   
/*      */   public String getCharacterSetResults() {
/* 1154 */     return this.mc.getCharacterSetResults();
/*      */   }
/*      */   
/*      */   public String getClientCertificateKeyStorePassword() {
/* 1158 */     return this.mc.getClientCertificateKeyStorePassword();
/*      */   }
/*      */   
/*      */   public String getClientCertificateKeyStoreType() {
/* 1162 */     return this.mc.getClientCertificateKeyStoreType();
/*      */   }
/*      */   
/*      */   public String getClientCertificateKeyStoreUrl() {
/* 1166 */     return this.mc.getClientCertificateKeyStoreUrl();
/*      */   }
/*      */   
/*      */   public String getClientInfoProvider() {
/* 1170 */     return this.mc.getClientInfoProvider();
/*      */   }
/*      */   
/*      */   public String getClobCharacterEncoding() {
/* 1174 */     return this.mc.getClobCharacterEncoding();
/*      */   }
/*      */   
/*      */   public boolean getClobberStreamingResults() {
/* 1178 */     return this.mc.getClobberStreamingResults();
/*      */   }
/*      */   
/*      */   public int getConnectTimeout() {
/* 1182 */     return this.mc.getConnectTimeout();
/*      */   }
/*      */   
/*      */   public String getConnectionCollation() {
/* 1186 */     return this.mc.getConnectionCollation();
/*      */   }
/*      */   
/*      */   public String getConnectionLifecycleInterceptors() {
/* 1190 */     return this.mc.getConnectionLifecycleInterceptors();
/*      */   }
/*      */   
/*      */   public boolean getContinueBatchOnError() {
/* 1194 */     return this.mc.getContinueBatchOnError();
/*      */   }
/*      */   
/*      */   public boolean getCreateDatabaseIfNotExist() {
/* 1198 */     return this.mc.getCreateDatabaseIfNotExist();
/*      */   }
/*      */   
/*      */   public int getDefaultFetchSize() {
/* 1202 */     return this.mc.getDefaultFetchSize();
/*      */   }
/*      */   
/*      */   public boolean getDontTrackOpenResources() {
/* 1206 */     return this.mc.getDontTrackOpenResources();
/*      */   }
/*      */   
/*      */   public boolean getDumpMetadataOnColumnNotFound() {
/* 1210 */     return this.mc.getDumpMetadataOnColumnNotFound();
/*      */   }
/*      */   
/*      */   public boolean getDumpQueriesOnException() {
/* 1214 */     return this.mc.getDumpQueriesOnException();
/*      */   }
/*      */   
/*      */   public boolean getDynamicCalendars() {
/* 1218 */     return this.mc.getDynamicCalendars();
/*      */   }
/*      */   
/*      */   public boolean getElideSetAutoCommits() {
/* 1222 */     return this.mc.getElideSetAutoCommits();
/*      */   }
/*      */   
/*      */   public boolean getEmptyStringsConvertToZero() {
/* 1226 */     return this.mc.getEmptyStringsConvertToZero();
/*      */   }
/*      */   
/*      */   public boolean getEmulateLocators() {
/* 1230 */     return this.mc.getEmulateLocators();
/*      */   }
/*      */   
/*      */   public boolean getEmulateUnsupportedPstmts() {
/* 1234 */     return this.mc.getEmulateUnsupportedPstmts();
/*      */   }
/*      */   
/*      */   public boolean getEnablePacketDebug() {
/* 1238 */     return this.mc.getEnablePacketDebug();
/*      */   }
/*      */   
/*      */   public boolean getEnableQueryTimeouts() {
/* 1242 */     return this.mc.getEnableQueryTimeouts();
/*      */   }
/*      */   
/*      */   public String getEncoding() {
/* 1246 */     return this.mc.getEncoding();
/*      */   }
/*      */   
/*      */   public boolean getExplainSlowQueries() {
/* 1250 */     return this.mc.getExplainSlowQueries();
/*      */   }
/*      */   
/*      */   public boolean getFailOverReadOnly() {
/* 1254 */     return this.mc.getFailOverReadOnly();
/*      */   }
/*      */   
/*      */   public boolean getFunctionsNeverReturnBlobs() {
/* 1258 */     return this.mc.getFunctionsNeverReturnBlobs();
/*      */   }
/*      */   
/*      */   public boolean getGatherPerfMetrics() {
/* 1262 */     return this.mc.getGatherPerfMetrics();
/*      */   }
/*      */   
/*      */   public boolean getGatherPerformanceMetrics() {
/* 1266 */     return this.mc.getGatherPerformanceMetrics();
/*      */   }
/*      */   
/*      */   public boolean getGenerateSimpleParameterMetadata() {
/* 1270 */     return this.mc.getGenerateSimpleParameterMetadata();
/*      */   }
/*      */   
/*      */   public boolean getHoldResultsOpenOverStatementClose() {
/* 1274 */     return this.mc.getHoldResultsOpenOverStatementClose();
/*      */   }
/*      */   
/*      */   public boolean getIgnoreNonTxTables() {
/* 1278 */     return this.mc.getIgnoreNonTxTables();
/*      */   }
/*      */   
/*      */   public boolean getIncludeInnodbStatusInDeadlockExceptions() {
/* 1282 */     return this.mc.getIncludeInnodbStatusInDeadlockExceptions();
/*      */   }
/*      */   
/*      */   public int getInitialTimeout() {
/* 1286 */     return this.mc.getInitialTimeout();
/*      */   }
/*      */   
/*      */   public boolean getInteractiveClient() {
/* 1290 */     return this.mc.getInteractiveClient();
/*      */   }
/*      */   
/*      */   public boolean getIsInteractiveClient() {
/* 1294 */     return this.mc.getIsInteractiveClient();
/*      */   }
/*      */   
/*      */   public boolean getJdbcCompliantTruncation() {
/* 1298 */     return this.mc.getJdbcCompliantTruncation();
/*      */   }
/*      */   
/*      */   public boolean getJdbcCompliantTruncationForReads() {
/* 1302 */     return this.mc.getJdbcCompliantTruncationForReads();
/*      */   }
/*      */   
/*      */   public String getLargeRowSizeThreshold() {
/* 1306 */     return this.mc.getLargeRowSizeThreshold();
/*      */   }
/*      */   
/*      */   public String getLoadBalanceStrategy() {
/* 1310 */     return this.mc.getLoadBalanceStrategy();
/*      */   }
/*      */   
/*      */   public String getServerAffinityOrder() {
/* 1314 */     return this.mc.getServerAffinityOrder();
/*      */   }
/*      */   
/*      */   public String getLocalSocketAddress() {
/* 1318 */     return this.mc.getLocalSocketAddress();
/*      */   }
/*      */   
/*      */   public int getLocatorFetchBufferSize() {
/* 1322 */     return this.mc.getLocatorFetchBufferSize();
/*      */   }
/*      */   
/*      */   public boolean getLogSlowQueries() {
/* 1326 */     return this.mc.getLogSlowQueries();
/*      */   }
/*      */   
/*      */   public boolean getLogXaCommands() {
/* 1330 */     return this.mc.getLogXaCommands();
/*      */   }
/*      */   
/*      */   public String getLogger() {
/* 1334 */     return this.mc.getLogger();
/*      */   }
/*      */   
/*      */   public String getLoggerClassName() {
/* 1338 */     return this.mc.getLoggerClassName();
/*      */   }
/*      */   
/*      */   public boolean getMaintainTimeStats() {
/* 1342 */     return this.mc.getMaintainTimeStats();
/*      */   }
/*      */   
/*      */   public int getMaxQuerySizeToLog() {
/* 1346 */     return this.mc.getMaxQuerySizeToLog();
/*      */   }
/*      */   
/*      */   public int getMaxReconnects() {
/* 1350 */     return this.mc.getMaxReconnects();
/*      */   }
/*      */   
/*      */   public int getMaxRows() {
/* 1354 */     return this.mc.getMaxRows();
/*      */   }
/*      */   
/*      */   public int getMetadataCacheSize() {
/* 1358 */     return this.mc.getMetadataCacheSize();
/*      */   }
/*      */   
/*      */   public int getNetTimeoutForStreamingResults() {
/* 1362 */     return this.mc.getNetTimeoutForStreamingResults();
/*      */   }
/*      */   
/*      */   public boolean getNoAccessToProcedureBodies() {
/* 1366 */     return this.mc.getNoAccessToProcedureBodies();
/*      */   }
/*      */   
/*      */   public boolean getNoDatetimeStringSync() {
/* 1370 */     return this.mc.getNoDatetimeStringSync();
/*      */   }
/*      */   
/*      */   public boolean getNoTimezoneConversionForTimeType() {
/* 1374 */     return this.mc.getNoTimezoneConversionForTimeType();
/*      */   }
/*      */   
/*      */   public boolean getNoTimezoneConversionForDateType() {
/* 1378 */     return this.mc.getNoTimezoneConversionForDateType();
/*      */   }
/*      */   
/*      */   public boolean getCacheDefaultTimezone() {
/* 1382 */     return this.mc.getCacheDefaultTimezone();
/*      */   }
/*      */   
/*      */   public boolean getNullCatalogMeansCurrent() {
/* 1386 */     return this.mc.getNullCatalogMeansCurrent();
/*      */   }
/*      */   
/*      */   public boolean getNullNamePatternMatchesAll() {
/* 1390 */     return this.mc.getNullNamePatternMatchesAll();
/*      */   }
/*      */   
/*      */   public boolean getOverrideSupportsIntegrityEnhancementFacility() {
/* 1394 */     return this.mc.getOverrideSupportsIntegrityEnhancementFacility();
/*      */   }
/*      */   
/*      */   public int getPacketDebugBufferSize() {
/* 1398 */     return this.mc.getPacketDebugBufferSize();
/*      */   }
/*      */   
/*      */   public boolean getPadCharsWithSpace() {
/* 1402 */     return this.mc.getPadCharsWithSpace();
/*      */   }
/*      */   
/*      */   public boolean getParanoid() {
/* 1406 */     return this.mc.getParanoid();
/*      */   }
/*      */   
/*      */   public boolean getPedantic() {
/* 1410 */     return this.mc.getPedantic();
/*      */   }
/*      */   
/*      */   public boolean getPinGlobalTxToPhysicalConnection() {
/* 1414 */     return this.mc.getPinGlobalTxToPhysicalConnection();
/*      */   }
/*      */   
/*      */   public boolean getPopulateInsertRowWithDefaultValues() {
/* 1418 */     return this.mc.getPopulateInsertRowWithDefaultValues();
/*      */   }
/*      */   
/*      */   public int getPrepStmtCacheSize() {
/* 1422 */     return this.mc.getPrepStmtCacheSize();
/*      */   }
/*      */   
/*      */   public int getPrepStmtCacheSqlLimit() {
/* 1426 */     return this.mc.getPrepStmtCacheSqlLimit();
/*      */   }
/*      */   
/*      */   public int getPreparedStatementCacheSize() {
/* 1430 */     return this.mc.getPreparedStatementCacheSize();
/*      */   }
/*      */   
/*      */   public int getPreparedStatementCacheSqlLimit() {
/* 1434 */     return this.mc.getPreparedStatementCacheSqlLimit();
/*      */   }
/*      */   
/*      */   public boolean getProcessEscapeCodesForPrepStmts() {
/* 1438 */     return this.mc.getProcessEscapeCodesForPrepStmts();
/*      */   }
/*      */   
/*      */   public boolean getProfileSQL() {
/* 1442 */     return this.mc.getProfileSQL();
/*      */   }
/*      */   
/*      */   public boolean getProfileSql() {
/* 1446 */     return this.mc.getProfileSql();
/*      */   }
/*      */   
/*      */   public String getPropertiesTransform() {
/* 1450 */     return this.mc.getPropertiesTransform();
/*      */   }
/*      */   
/*      */   public int getQueriesBeforeRetryMaster() {
/* 1454 */     return this.mc.getQueriesBeforeRetryMaster();
/*      */   }
/*      */   
/*      */   public boolean getReconnectAtTxEnd() {
/* 1458 */     return this.mc.getReconnectAtTxEnd();
/*      */   }
/*      */   
/*      */   public boolean getRelaxAutoCommit() {
/* 1462 */     return this.mc.getRelaxAutoCommit();
/*      */   }
/*      */   
/*      */   public int getReportMetricsIntervalMillis() {
/* 1466 */     return this.mc.getReportMetricsIntervalMillis();
/*      */   }
/*      */   
/*      */   public boolean getRequireSSL() {
/* 1470 */     return this.mc.getRequireSSL();
/*      */   }
/*      */   
/*      */   public String getResourceId() {
/* 1474 */     return this.mc.getResourceId();
/*      */   }
/*      */   
/*      */   public int getResultSetSizeThreshold() {
/* 1478 */     return this.mc.getResultSetSizeThreshold();
/*      */   }
/*      */   
/*      */   public boolean getRewriteBatchedStatements() {
/* 1482 */     return this.mc.getRewriteBatchedStatements();
/*      */   }
/*      */   
/*      */   public boolean getRollbackOnPooledClose() {
/* 1486 */     return this.mc.getRollbackOnPooledClose();
/*      */   }
/*      */   
/*      */   public boolean getRoundRobinLoadBalance() {
/* 1490 */     return this.mc.getRoundRobinLoadBalance();
/*      */   }
/*      */   
/*      */   public boolean getRunningCTS13() {
/* 1494 */     return this.mc.getRunningCTS13();
/*      */   }
/*      */   
/*      */   public int getSecondsBeforeRetryMaster() {
/* 1498 */     return this.mc.getSecondsBeforeRetryMaster();
/*      */   }
/*      */   
/*      */   public String getServerTimezone() {
/* 1502 */     return this.mc.getServerTimezone();
/*      */   }
/*      */   
/*      */   public String getSessionVariables() {
/* 1506 */     return this.mc.getSessionVariables();
/*      */   }
/*      */   
/*      */   public int getSlowQueryThresholdMillis() {
/* 1510 */     return this.mc.getSlowQueryThresholdMillis();
/*      */   }
/*      */   
/*      */   public long getSlowQueryThresholdNanos() {
/* 1514 */     return this.mc.getSlowQueryThresholdNanos();
/*      */   }
/*      */   
/*      */   public String getSocketFactory() {
/* 1518 */     return this.mc.getSocketFactory();
/*      */   }
/*      */   
/*      */   public String getSocketFactoryClassName() {
/* 1522 */     return this.mc.getSocketFactoryClassName();
/*      */   }
/*      */   
/*      */   public int getSocketTimeout() {
/* 1526 */     return this.mc.getSocketTimeout();
/*      */   }
/*      */   
/*      */   public String getStatementInterceptors() {
/* 1530 */     return this.mc.getStatementInterceptors();
/*      */   }
/*      */   
/*      */   public boolean getStrictFloatingPoint() {
/* 1534 */     return this.mc.getStrictFloatingPoint();
/*      */   }
/*      */   
/*      */   public boolean getStrictUpdates() {
/* 1538 */     return this.mc.getStrictUpdates();
/*      */   }
/*      */   
/*      */   public boolean getTcpKeepAlive() {
/* 1542 */     return this.mc.getTcpKeepAlive();
/*      */   }
/*      */   
/*      */   public boolean getTcpNoDelay() {
/* 1546 */     return this.mc.getTcpNoDelay();
/*      */   }
/*      */   
/*      */   public int getTcpRcvBuf() {
/* 1550 */     return this.mc.getTcpRcvBuf();
/*      */   }
/*      */   
/*      */   public int getTcpSndBuf() {
/* 1554 */     return this.mc.getTcpSndBuf();
/*      */   }
/*      */   
/*      */   public int getTcpTrafficClass() {
/* 1558 */     return this.mc.getTcpTrafficClass();
/*      */   }
/*      */   
/*      */   public boolean getTinyInt1isBit() {
/* 1562 */     return this.mc.getTinyInt1isBit();
/*      */   }
/*      */   
/*      */   public boolean getTraceProtocol() {
/* 1566 */     return this.mc.getTraceProtocol();
/*      */   }
/*      */   
/*      */   public boolean getTransformedBitIsBoolean() {
/* 1570 */     return this.mc.getTransformedBitIsBoolean();
/*      */   }
/*      */   
/*      */   public boolean getTreatUtilDateAsTimestamp() {
/* 1574 */     return this.mc.getTreatUtilDateAsTimestamp();
/*      */   }
/*      */   
/*      */   public String getTrustCertificateKeyStorePassword() {
/* 1578 */     return this.mc.getTrustCertificateKeyStorePassword();
/*      */   }
/*      */   
/*      */   public String getTrustCertificateKeyStoreType() {
/* 1582 */     return this.mc.getTrustCertificateKeyStoreType();
/*      */   }
/*      */   
/*      */   public String getTrustCertificateKeyStoreUrl() {
/* 1586 */     return this.mc.getTrustCertificateKeyStoreUrl();
/*      */   }
/*      */   
/*      */   public boolean getUltraDevHack() {
/* 1590 */     return this.mc.getUltraDevHack();
/*      */   }
/*      */   
/*      */   public boolean getUseBlobToStoreUTF8OutsideBMP() {
/* 1594 */     return this.mc.getUseBlobToStoreUTF8OutsideBMP();
/*      */   }
/*      */   
/*      */   public boolean getUseCompression() {
/* 1598 */     return this.mc.getUseCompression();
/*      */   }
/*      */   
/*      */   public String getUseConfigs() {
/* 1602 */     return this.mc.getUseConfigs();
/*      */   }
/*      */   
/*      */   public boolean getUseCursorFetch() {
/* 1606 */     return this.mc.getUseCursorFetch();
/*      */   }
/*      */   
/*      */   public boolean getUseDirectRowUnpack() {
/* 1610 */     return this.mc.getUseDirectRowUnpack();
/*      */   }
/*      */   
/*      */   public boolean getUseDynamicCharsetInfo() {
/* 1614 */     return this.mc.getUseDynamicCharsetInfo();
/*      */   }
/*      */   
/*      */   public boolean getUseFastDateParsing() {
/* 1618 */     return this.mc.getUseFastDateParsing();
/*      */   }
/*      */   
/*      */   public boolean getUseFastIntParsing() {
/* 1622 */     return this.mc.getUseFastIntParsing();
/*      */   }
/*      */   
/*      */   public boolean getUseGmtMillisForDatetimes() {
/* 1626 */     return this.mc.getUseGmtMillisForDatetimes();
/*      */   }
/*      */   
/*      */   public boolean getUseHostsInPrivileges() {
/* 1630 */     return this.mc.getUseHostsInPrivileges();
/*      */   }
/*      */   
/*      */   public boolean getUseInformationSchema() {
/* 1634 */     return this.mc.getUseInformationSchema();
/*      */   }
/*      */   
/*      */   public boolean getUseJDBCCompliantTimezoneShift() {
/* 1638 */     return this.mc.getUseJDBCCompliantTimezoneShift();
/*      */   }
/*      */   
/*      */   public boolean getUseJvmCharsetConverters() {
/* 1642 */     return this.mc.getUseJvmCharsetConverters();
/*      */   }
/*      */   
/*      */   public boolean getUseLocalSessionState() {
/* 1646 */     return this.mc.getUseLocalSessionState();
/*      */   }
/*      */   
/*      */   public boolean getUseNanosForElapsedTime() {
/* 1650 */     return this.mc.getUseNanosForElapsedTime();
/*      */   }
/*      */   
/*      */   public boolean getUseOldAliasMetadataBehavior() {
/* 1654 */     return this.mc.getUseOldAliasMetadataBehavior();
/*      */   }
/*      */   
/*      */   public boolean getUseOldUTF8Behavior() {
/* 1658 */     return this.mc.getUseOldUTF8Behavior();
/*      */   }
/*      */   
/*      */   public boolean getUseOnlyServerErrorMessages() {
/* 1662 */     return this.mc.getUseOnlyServerErrorMessages();
/*      */   }
/*      */   
/*      */   public boolean getUseReadAheadInput() {
/* 1666 */     return this.mc.getUseReadAheadInput();
/*      */   }
/*      */   
/*      */   public boolean getUseSSL() {
/* 1670 */     return this.mc.getUseSSL();
/*      */   }
/*      */   
/*      */   public boolean getUseSSPSCompatibleTimezoneShift() {
/* 1674 */     return this.mc.getUseSSPSCompatibleTimezoneShift();
/*      */   }
/*      */   
/*      */   public boolean getUseServerPrepStmts() {
/* 1678 */     return this.mc.getUseServerPrepStmts();
/*      */   }
/*      */   
/*      */   public boolean getUseServerPreparedStmts() {
/* 1682 */     return this.mc.getUseServerPreparedStmts();
/*      */   }
/*      */   
/*      */   public boolean getUseSqlStateCodes() {
/* 1686 */     return this.mc.getUseSqlStateCodes();
/*      */   }
/*      */   
/*      */   public boolean getUseStreamLengthsInPrepStmts() {
/* 1690 */     return this.mc.getUseStreamLengthsInPrepStmts();
/*      */   }
/*      */   
/*      */   public boolean getUseTimezone() {
/* 1694 */     return this.mc.getUseTimezone();
/*      */   }
/*      */   
/*      */   public boolean getUseUltraDevWorkAround() {
/* 1698 */     return this.mc.getUseUltraDevWorkAround();
/*      */   }
/*      */   
/*      */   public boolean getUseUnbufferedInput() {
/* 1702 */     return this.mc.getUseUnbufferedInput();
/*      */   }
/*      */   
/*      */   public boolean getUseUnicode() {
/* 1706 */     return this.mc.getUseUnicode();
/*      */   }
/*      */   
/*      */   public boolean getUseUsageAdvisor() {
/* 1710 */     return this.mc.getUseUsageAdvisor();
/*      */   }
/*      */   
/*      */   public String getUtf8OutsideBmpExcludedColumnNamePattern() {
/* 1714 */     return this.mc.getUtf8OutsideBmpExcludedColumnNamePattern();
/*      */   }
/*      */   
/*      */   public String getUtf8OutsideBmpIncludedColumnNamePattern() {
/* 1718 */     return this.mc.getUtf8OutsideBmpIncludedColumnNamePattern();
/*      */   }
/*      */   
/*      */   public boolean getYearIsDateType() {
/* 1722 */     return this.mc.getYearIsDateType();
/*      */   }
/*      */   
/*      */   public String getZeroDateTimeBehavior() {
/* 1726 */     return this.mc.getZeroDateTimeBehavior();
/*      */   }
/*      */   
/*      */   public void setAllowLoadLocalInfile(boolean property) {
/* 1730 */     this.mc.setAllowLoadLocalInfile(property);
/*      */   }
/*      */   
/*      */   public void setAllowMultiQueries(boolean property) {
/* 1734 */     this.mc.setAllowMultiQueries(property);
/*      */   }
/*      */   
/*      */   public void setAllowNanAndInf(boolean flag) {
/* 1738 */     this.mc.setAllowNanAndInf(flag);
/*      */   }
/*      */   
/*      */   public void setAllowUrlInLocalInfile(boolean flag) {
/* 1742 */     this.mc.setAllowUrlInLocalInfile(flag);
/*      */   }
/*      */   
/*      */   public void setAlwaysSendSetIsolation(boolean flag) {
/* 1746 */     this.mc.setAlwaysSendSetIsolation(flag);
/*      */   }
/*      */   
/*      */   public void setAutoClosePStmtStreams(boolean flag) {
/* 1750 */     this.mc.setAutoClosePStmtStreams(flag);
/*      */   }
/*      */   
/*      */   public void setAutoDeserialize(boolean flag) {
/* 1754 */     this.mc.setAutoDeserialize(flag);
/*      */   }
/*      */   
/*      */   public void setAutoGenerateTestcaseScript(boolean flag) {
/* 1758 */     this.mc.setAutoGenerateTestcaseScript(flag);
/*      */   }
/*      */   
/*      */   public void setAutoReconnect(boolean flag) {
/* 1762 */     this.mc.setAutoReconnect(flag);
/*      */   }
/*      */   
/*      */   public void setAutoReconnectForConnectionPools(boolean property) {
/* 1766 */     this.mc.setAutoReconnectForConnectionPools(property);
/*      */   }
/*      */   
/*      */   public void setAutoReconnectForPools(boolean flag) {
/* 1770 */     this.mc.setAutoReconnectForPools(flag);
/*      */   }
/*      */   
/*      */   public void setAutoSlowLog(boolean flag) {
/* 1774 */     this.mc.setAutoSlowLog(flag);
/*      */   }
/*      */   
/*      */   public void setBlobSendChunkSize(String value) throws SQLException {
/* 1778 */     this.mc.setBlobSendChunkSize(value);
/*      */   }
/*      */   
/*      */   public void setBlobsAreStrings(boolean flag) {
/* 1782 */     this.mc.setBlobsAreStrings(flag);
/*      */   }
/*      */   
/*      */   public void setCacheCallableStatements(boolean flag) {
/* 1786 */     this.mc.setCacheCallableStatements(flag);
/*      */   }
/*      */   
/*      */   public void setCacheCallableStmts(boolean flag) {
/* 1790 */     this.mc.setCacheCallableStmts(flag);
/*      */   }
/*      */   
/*      */   public void setCachePrepStmts(boolean flag) {
/* 1794 */     this.mc.setCachePrepStmts(flag);
/*      */   }
/*      */   
/*      */   public void setCachePreparedStatements(boolean flag) {
/* 1798 */     this.mc.setCachePreparedStatements(flag);
/*      */   }
/*      */   
/*      */   public void setCacheResultSetMetadata(boolean property) {
/* 1802 */     this.mc.setCacheResultSetMetadata(property);
/*      */   }
/*      */   
/*      */   public void setCacheServerConfiguration(boolean flag) {
/* 1806 */     this.mc.setCacheServerConfiguration(flag);
/*      */   }
/*      */   
/*      */   public void setCallableStatementCacheSize(int size) throws SQLException {
/* 1810 */     this.mc.setCallableStatementCacheSize(size);
/*      */   }
/*      */   
/*      */   public void setCallableStmtCacheSize(int cacheSize) throws SQLException {
/* 1814 */     this.mc.setCallableStmtCacheSize(cacheSize);
/*      */   }
/*      */   
/*      */   public void setCapitalizeDBMDTypes(boolean property) {
/* 1818 */     this.mc.setCapitalizeDBMDTypes(property);
/*      */   }
/*      */   
/*      */   public void setCapitalizeTypeNames(boolean flag) {
/* 1822 */     this.mc.setCapitalizeTypeNames(flag);
/*      */   }
/*      */   
/*      */   public void setCharacterEncoding(String encoding) {
/* 1826 */     this.mc.setCharacterEncoding(encoding);
/*      */   }
/*      */   
/*      */   public void setCharacterSetResults(String characterSet) {
/* 1830 */     this.mc.setCharacterSetResults(characterSet);
/*      */   }
/*      */   
/*      */   public void setClientCertificateKeyStorePassword(String value) {
/* 1834 */     this.mc.setClientCertificateKeyStorePassword(value);
/*      */   }
/*      */   
/*      */   public void setClientCertificateKeyStoreType(String value) {
/* 1838 */     this.mc.setClientCertificateKeyStoreType(value);
/*      */   }
/*      */   
/*      */   public void setClientCertificateKeyStoreUrl(String value) {
/* 1842 */     this.mc.setClientCertificateKeyStoreUrl(value);
/*      */   }
/*      */   
/*      */   public void setClientInfoProvider(String classname) {
/* 1846 */     this.mc.setClientInfoProvider(classname);
/*      */   }
/*      */   
/*      */   public void setClobCharacterEncoding(String encoding) {
/* 1850 */     this.mc.setClobCharacterEncoding(encoding);
/*      */   }
/*      */   
/*      */   public void setClobberStreamingResults(boolean flag) {
/* 1854 */     this.mc.setClobberStreamingResults(flag);
/*      */   }
/*      */   
/*      */   public void setConnectTimeout(int timeoutMs) throws SQLException {
/* 1858 */     this.mc.setConnectTimeout(timeoutMs);
/*      */   }
/*      */   
/*      */   public void setConnectionCollation(String collation) {
/* 1862 */     this.mc.setConnectionCollation(collation);
/*      */   }
/*      */   
/*      */   public void setConnectionLifecycleInterceptors(String interceptors) {
/* 1866 */     this.mc.setConnectionLifecycleInterceptors(interceptors);
/*      */   }
/*      */   
/*      */   public void setContinueBatchOnError(boolean property) {
/* 1870 */     this.mc.setContinueBatchOnError(property);
/*      */   }
/*      */   
/*      */   public void setCreateDatabaseIfNotExist(boolean flag) {
/* 1874 */     this.mc.setCreateDatabaseIfNotExist(flag);
/*      */   }
/*      */   
/*      */   public void setDefaultFetchSize(int n) throws SQLException {
/* 1878 */     this.mc.setDefaultFetchSize(n);
/*      */   }
/*      */   
/*      */   public void setDetectServerPreparedStmts(boolean property) {
/* 1882 */     this.mc.setDetectServerPreparedStmts(property);
/*      */   }
/*      */   
/*      */   public void setDontTrackOpenResources(boolean flag) {
/* 1886 */     this.mc.setDontTrackOpenResources(flag);
/*      */   }
/*      */   
/*      */   public void setDumpMetadataOnColumnNotFound(boolean flag) {
/* 1890 */     this.mc.setDumpMetadataOnColumnNotFound(flag);
/*      */   }
/*      */   
/*      */   public void setDumpQueriesOnException(boolean flag) {
/* 1894 */     this.mc.setDumpQueriesOnException(flag);
/*      */   }
/*      */   
/*      */   public void setDynamicCalendars(boolean flag) {
/* 1898 */     this.mc.setDynamicCalendars(flag);
/*      */   }
/*      */   
/*      */   public void setElideSetAutoCommits(boolean flag) {
/* 1902 */     this.mc.setElideSetAutoCommits(flag);
/*      */   }
/*      */   
/*      */   public void setEmptyStringsConvertToZero(boolean flag) {
/* 1906 */     this.mc.setEmptyStringsConvertToZero(flag);
/*      */   }
/*      */   
/*      */   public void setEmulateLocators(boolean property) {
/* 1910 */     this.mc.setEmulateLocators(property);
/*      */   }
/*      */   
/*      */   public void setEmulateUnsupportedPstmts(boolean flag) {
/* 1914 */     this.mc.setEmulateUnsupportedPstmts(flag);
/*      */   }
/*      */   
/*      */   public void setEnablePacketDebug(boolean flag) {
/* 1918 */     this.mc.setEnablePacketDebug(flag);
/*      */   }
/*      */   
/*      */   public void setEnableQueryTimeouts(boolean flag) {
/* 1922 */     this.mc.setEnableQueryTimeouts(flag);
/*      */   }
/*      */   
/*      */   public void setEncoding(String property) {
/* 1926 */     this.mc.setEncoding(property);
/*      */   }
/*      */   
/*      */   public void setExplainSlowQueries(boolean flag) {
/* 1930 */     this.mc.setExplainSlowQueries(flag);
/*      */   }
/*      */   
/*      */   public void setFailOverReadOnly(boolean flag) {
/* 1934 */     this.mc.setFailOverReadOnly(flag);
/*      */   }
/*      */   
/*      */   public void setFunctionsNeverReturnBlobs(boolean flag) {
/* 1938 */     this.mc.setFunctionsNeverReturnBlobs(flag);
/*      */   }
/*      */   
/*      */   public void setGatherPerfMetrics(boolean flag) {
/* 1942 */     this.mc.setGatherPerfMetrics(flag);
/*      */   }
/*      */   
/*      */   public void setGatherPerformanceMetrics(boolean flag) {
/* 1946 */     this.mc.setGatherPerformanceMetrics(flag);
/*      */   }
/*      */   
/*      */   public void setGenerateSimpleParameterMetadata(boolean flag) {
/* 1950 */     this.mc.setGenerateSimpleParameterMetadata(flag);
/*      */   }
/*      */   
/*      */   public void setHoldResultsOpenOverStatementClose(boolean flag) {
/* 1954 */     this.mc.setHoldResultsOpenOverStatementClose(flag);
/*      */   }
/*      */   
/*      */   public void setIgnoreNonTxTables(boolean property) {
/* 1958 */     this.mc.setIgnoreNonTxTables(property);
/*      */   }
/*      */   
/*      */   public void setIncludeInnodbStatusInDeadlockExceptions(boolean flag) {
/* 1962 */     this.mc.setIncludeInnodbStatusInDeadlockExceptions(flag);
/*      */   }
/*      */   
/*      */   public void setInitialTimeout(int property) throws SQLException {
/* 1966 */     this.mc.setInitialTimeout(property);
/*      */   }
/*      */   
/*      */   public void setInteractiveClient(boolean property) {
/* 1970 */     this.mc.setInteractiveClient(property);
/*      */   }
/*      */   
/*      */   public void setIsInteractiveClient(boolean property) {
/* 1974 */     this.mc.setIsInteractiveClient(property);
/*      */   }
/*      */   
/*      */   public void setJdbcCompliantTruncation(boolean flag) {
/* 1978 */     this.mc.setJdbcCompliantTruncation(flag);
/*      */   }
/*      */   
/*      */   public void setJdbcCompliantTruncationForReads(boolean jdbcCompliantTruncationForReads) {
/* 1982 */     this.mc.setJdbcCompliantTruncationForReads(jdbcCompliantTruncationForReads);
/*      */   }
/*      */   
/*      */   public void setLargeRowSizeThreshold(String value) throws SQLException {
/* 1986 */     this.mc.setLargeRowSizeThreshold(value);
/*      */   }
/*      */   
/*      */   public void setLoadBalanceStrategy(String strategy) {
/* 1990 */     this.mc.setLoadBalanceStrategy(strategy);
/*      */   }
/*      */   
/*      */   public void setServerAffinityOrder(String hostsList) {
/* 1994 */     this.mc.setServerAffinityOrder(hostsList);
/*      */   }
/*      */   
/*      */   public void setLocalSocketAddress(String address) {
/* 1998 */     this.mc.setLocalSocketAddress(address);
/*      */   }
/*      */   
/*      */   public void setLocatorFetchBufferSize(String value) throws SQLException {
/* 2002 */     this.mc.setLocatorFetchBufferSize(value);
/*      */   }
/*      */   
/*      */   public void setLogSlowQueries(boolean flag) {
/* 2006 */     this.mc.setLogSlowQueries(flag);
/*      */   }
/*      */   
/*      */   public void setLogXaCommands(boolean flag) {
/* 2010 */     this.mc.setLogXaCommands(flag);
/*      */   }
/*      */   
/*      */   public void setLogger(String property) {
/* 2014 */     this.mc.setLogger(property);
/*      */   }
/*      */   
/*      */   public void setLoggerClassName(String className) {
/* 2018 */     this.mc.setLoggerClassName(className);
/*      */   }
/*      */   
/*      */   public void setMaintainTimeStats(boolean flag) {
/* 2022 */     this.mc.setMaintainTimeStats(flag);
/*      */   }
/*      */   
/*      */   public void setMaxQuerySizeToLog(int sizeInBytes) throws SQLException {
/* 2026 */     this.mc.setMaxQuerySizeToLog(sizeInBytes);
/*      */   }
/*      */   
/*      */   public void setMaxReconnects(int property) throws SQLException {
/* 2030 */     this.mc.setMaxReconnects(property);
/*      */   }
/*      */   
/*      */   public void setMaxRows(int property) throws SQLException {
/* 2034 */     this.mc.setMaxRows(property);
/*      */   }
/*      */   
/*      */   public void setMetadataCacheSize(int value) throws SQLException {
/* 2038 */     this.mc.setMetadataCacheSize(value);
/*      */   }
/*      */   
/*      */   public void setNetTimeoutForStreamingResults(int value) throws SQLException {
/* 2042 */     this.mc.setNetTimeoutForStreamingResults(value);
/*      */   }
/*      */   
/*      */   public void setNoAccessToProcedureBodies(boolean flag) {
/* 2046 */     this.mc.setNoAccessToProcedureBodies(flag);
/*      */   }
/*      */   
/*      */   public void setNoDatetimeStringSync(boolean flag) {
/* 2050 */     this.mc.setNoDatetimeStringSync(flag);
/*      */   }
/*      */   
/*      */   public void setNoTimezoneConversionForTimeType(boolean flag) {
/* 2054 */     this.mc.setNoTimezoneConversionForTimeType(flag);
/*      */   }
/*      */   
/*      */   public void setNoTimezoneConversionForDateType(boolean flag) {
/* 2058 */     this.mc.setNoTimezoneConversionForDateType(flag);
/*      */   }
/*      */   
/*      */   public void setCacheDefaultTimezone(boolean flag) {
/* 2062 */     this.mc.setCacheDefaultTimezone(flag);
/*      */   }
/*      */   
/*      */   public void setNullCatalogMeansCurrent(boolean value) {
/* 2066 */     this.mc.setNullCatalogMeansCurrent(value);
/*      */   }
/*      */   
/*      */   public void setNullNamePatternMatchesAll(boolean value) {
/* 2070 */     this.mc.setNullNamePatternMatchesAll(value);
/*      */   }
/*      */   
/*      */   public void setOverrideSupportsIntegrityEnhancementFacility(boolean flag) {
/* 2074 */     this.mc.setOverrideSupportsIntegrityEnhancementFacility(flag);
/*      */   }
/*      */   
/*      */   public void setPacketDebugBufferSize(int size) throws SQLException {
/* 2078 */     this.mc.setPacketDebugBufferSize(size);
/*      */   }
/*      */   
/*      */   public void setPadCharsWithSpace(boolean flag) {
/* 2082 */     this.mc.setPadCharsWithSpace(flag);
/*      */   }
/*      */   
/*      */   public void setParanoid(boolean property) {
/* 2086 */     this.mc.setParanoid(property);
/*      */   }
/*      */   
/*      */   public void setPedantic(boolean property) {
/* 2090 */     this.mc.setPedantic(property);
/*      */   }
/*      */   
/*      */   public void setPinGlobalTxToPhysicalConnection(boolean flag) {
/* 2094 */     this.mc.setPinGlobalTxToPhysicalConnection(flag);
/*      */   }
/*      */   
/*      */   public void setPopulateInsertRowWithDefaultValues(boolean flag) {
/* 2098 */     this.mc.setPopulateInsertRowWithDefaultValues(flag);
/*      */   }
/*      */   
/*      */   public void setPrepStmtCacheSize(int cacheSize) throws SQLException {
/* 2102 */     this.mc.setPrepStmtCacheSize(cacheSize);
/*      */   }
/*      */   
/*      */   public void setPrepStmtCacheSqlLimit(int sqlLimit) throws SQLException {
/* 2106 */     this.mc.setPrepStmtCacheSqlLimit(sqlLimit);
/*      */   }
/*      */   
/*      */   public void setPreparedStatementCacheSize(int cacheSize) throws SQLException {
/* 2110 */     this.mc.setPreparedStatementCacheSize(cacheSize);
/*      */   }
/*      */   
/*      */   public void setPreparedStatementCacheSqlLimit(int cacheSqlLimit) throws SQLException {
/* 2114 */     this.mc.setPreparedStatementCacheSqlLimit(cacheSqlLimit);
/*      */   }
/*      */   
/*      */   public void setProcessEscapeCodesForPrepStmts(boolean flag) {
/* 2118 */     this.mc.setProcessEscapeCodesForPrepStmts(flag);
/*      */   }
/*      */   
/*      */   public void setProfileSQL(boolean flag) {
/* 2122 */     this.mc.setProfileSQL(flag);
/*      */   }
/*      */   
/*      */   public void setProfileSql(boolean property) {
/* 2126 */     this.mc.setProfileSql(property);
/*      */   }
/*      */   
/*      */   public void setPropertiesTransform(String value) {
/* 2130 */     this.mc.setPropertiesTransform(value);
/*      */   }
/*      */   
/*      */   public void setQueriesBeforeRetryMaster(int property) throws SQLException {
/* 2134 */     this.mc.setQueriesBeforeRetryMaster(property);
/*      */   }
/*      */   
/*      */   public void setReconnectAtTxEnd(boolean property) {
/* 2138 */     this.mc.setReconnectAtTxEnd(property);
/*      */   }
/*      */   
/*      */   public void setRelaxAutoCommit(boolean property) {
/* 2142 */     this.mc.setRelaxAutoCommit(property);
/*      */   }
/*      */   
/*      */   public void setReportMetricsIntervalMillis(int millis) throws SQLException {
/* 2146 */     this.mc.setReportMetricsIntervalMillis(millis);
/*      */   }
/*      */   
/*      */   public void setRequireSSL(boolean property) {
/* 2150 */     this.mc.setRequireSSL(property);
/*      */   }
/*      */   
/*      */   public void setResourceId(String resourceId) {
/* 2154 */     this.mc.setResourceId(resourceId);
/*      */   }
/*      */   
/*      */   public void setResultSetSizeThreshold(int threshold) throws SQLException {
/* 2158 */     this.mc.setResultSetSizeThreshold(threshold);
/*      */   }
/*      */   
/*      */   public void setRetainStatementAfterResultSetClose(boolean flag) {
/* 2162 */     this.mc.setRetainStatementAfterResultSetClose(flag);
/*      */   }
/*      */   
/*      */   public void setRewriteBatchedStatements(boolean flag) {
/* 2166 */     this.mc.setRewriteBatchedStatements(flag);
/*      */   }
/*      */   
/*      */   public void setRollbackOnPooledClose(boolean flag) {
/* 2170 */     this.mc.setRollbackOnPooledClose(flag);
/*      */   }
/*      */   
/*      */   public void setRoundRobinLoadBalance(boolean flag) {
/* 2174 */     this.mc.setRoundRobinLoadBalance(flag);
/*      */   }
/*      */   
/*      */   public void setRunningCTS13(boolean flag) {
/* 2178 */     this.mc.setRunningCTS13(flag);
/*      */   }
/*      */   
/*      */   public void setSecondsBeforeRetryMaster(int property) throws SQLException {
/* 2182 */     this.mc.setSecondsBeforeRetryMaster(property);
/*      */   }
/*      */   
/*      */   public void setServerTimezone(String property) {
/* 2186 */     this.mc.setServerTimezone(property);
/*      */   }
/*      */   
/*      */   public void setSessionVariables(String variables) {
/* 2190 */     this.mc.setSessionVariables(variables);
/*      */   }
/*      */   
/*      */   public void setSlowQueryThresholdMillis(int millis) throws SQLException {
/* 2194 */     this.mc.setSlowQueryThresholdMillis(millis);
/*      */   }
/*      */   
/*      */   public void setSlowQueryThresholdNanos(long nanos) throws SQLException {
/* 2198 */     this.mc.setSlowQueryThresholdNanos(nanos);
/*      */   }
/*      */   
/*      */   public void setSocketFactory(String name) {
/* 2202 */     this.mc.setSocketFactory(name);
/*      */   }
/*      */   
/*      */   public void setSocketFactoryClassName(String property) {
/* 2206 */     this.mc.setSocketFactoryClassName(property);
/*      */   }
/*      */   
/*      */   public void setSocketTimeout(int property) throws SQLException {
/* 2210 */     this.mc.setSocketTimeout(property);
/*      */   }
/*      */   
/*      */   public void setStatementInterceptors(String value) {
/* 2214 */     this.mc.setStatementInterceptors(value);
/*      */   }
/*      */   
/*      */   public void setStrictFloatingPoint(boolean property) {
/* 2218 */     this.mc.setStrictFloatingPoint(property);
/*      */   }
/*      */   
/*      */   public void setStrictUpdates(boolean property) {
/* 2222 */     this.mc.setStrictUpdates(property);
/*      */   }
/*      */   
/*      */   public void setTcpKeepAlive(boolean flag) {
/* 2226 */     this.mc.setTcpKeepAlive(flag);
/*      */   }
/*      */   
/*      */   public void setTcpNoDelay(boolean flag) {
/* 2230 */     this.mc.setTcpNoDelay(flag);
/*      */   }
/*      */   
/*      */   public void setTcpRcvBuf(int bufSize) throws SQLException {
/* 2234 */     this.mc.setTcpRcvBuf(bufSize);
/*      */   }
/*      */   
/*      */   public void setTcpSndBuf(int bufSize) throws SQLException {
/* 2238 */     this.mc.setTcpSndBuf(bufSize);
/*      */   }
/*      */   
/*      */   public void setTcpTrafficClass(int classFlags) throws SQLException {
/* 2242 */     this.mc.setTcpTrafficClass(classFlags);
/*      */   }
/*      */   
/*      */   public void setTinyInt1isBit(boolean flag) {
/* 2246 */     this.mc.setTinyInt1isBit(flag);
/*      */   }
/*      */   
/*      */   public void setTraceProtocol(boolean flag) {
/* 2250 */     this.mc.setTraceProtocol(flag);
/*      */   }
/*      */   
/*      */   public void setTransformedBitIsBoolean(boolean flag) {
/* 2254 */     this.mc.setTransformedBitIsBoolean(flag);
/*      */   }
/*      */   
/*      */   public void setTreatUtilDateAsTimestamp(boolean flag) {
/* 2258 */     this.mc.setTreatUtilDateAsTimestamp(flag);
/*      */   }
/*      */   
/*      */   public void setTrustCertificateKeyStorePassword(String value) {
/* 2262 */     this.mc.setTrustCertificateKeyStorePassword(value);
/*      */   }
/*      */   
/*      */   public void setTrustCertificateKeyStoreType(String value) {
/* 2266 */     this.mc.setTrustCertificateKeyStoreType(value);
/*      */   }
/*      */   
/*      */   public void setTrustCertificateKeyStoreUrl(String value) {
/* 2270 */     this.mc.setTrustCertificateKeyStoreUrl(value);
/*      */   }
/*      */   
/*      */   public void setUltraDevHack(boolean flag) {
/* 2274 */     this.mc.setUltraDevHack(flag);
/*      */   }
/*      */   
/*      */   public void setUseBlobToStoreUTF8OutsideBMP(boolean flag) {
/* 2278 */     this.mc.setUseBlobToStoreUTF8OutsideBMP(flag);
/*      */   }
/*      */   
/*      */   public void setUseCompression(boolean property) {
/* 2282 */     this.mc.setUseCompression(property);
/*      */   }
/*      */   
/*      */   public void setUseConfigs(String configs) {
/* 2286 */     this.mc.setUseConfigs(configs);
/*      */   }
/*      */   
/*      */   public void setUseCursorFetch(boolean flag) {
/* 2290 */     this.mc.setUseCursorFetch(flag);
/*      */   }
/*      */   
/*      */   public void setUseDirectRowUnpack(boolean flag) {
/* 2294 */     this.mc.setUseDirectRowUnpack(flag);
/*      */   }
/*      */   
/*      */   public void setUseDynamicCharsetInfo(boolean flag) {
/* 2298 */     this.mc.setUseDynamicCharsetInfo(flag);
/*      */   }
/*      */   
/*      */   public void setUseFastDateParsing(boolean flag) {
/* 2302 */     this.mc.setUseFastDateParsing(flag);
/*      */   }
/*      */   
/*      */   public void setUseFastIntParsing(boolean flag) {
/* 2306 */     this.mc.setUseFastIntParsing(flag);
/*      */   }
/*      */   
/*      */   public void setUseGmtMillisForDatetimes(boolean flag) {
/* 2310 */     this.mc.setUseGmtMillisForDatetimes(flag);
/*      */   }
/*      */   
/*      */   public void setUseHostsInPrivileges(boolean property) {
/* 2314 */     this.mc.setUseHostsInPrivileges(property);
/*      */   }
/*      */   
/*      */   public void setUseInformationSchema(boolean flag) {
/* 2318 */     this.mc.setUseInformationSchema(flag);
/*      */   }
/*      */   
/*      */   public void setUseJDBCCompliantTimezoneShift(boolean flag) {
/* 2322 */     this.mc.setUseJDBCCompliantTimezoneShift(flag);
/*      */   }
/*      */   
/*      */   public void setUseJvmCharsetConverters(boolean flag) {
/* 2326 */     this.mc.setUseJvmCharsetConverters(flag);
/*      */   }
/*      */   
/*      */   public void setUseLocalSessionState(boolean flag) {
/* 2330 */     this.mc.setUseLocalSessionState(flag);
/*      */   }
/*      */   
/*      */   public void setUseNanosForElapsedTime(boolean flag) {
/* 2334 */     this.mc.setUseNanosForElapsedTime(flag);
/*      */   }
/*      */   
/*      */   public void setUseOldAliasMetadataBehavior(boolean flag) {
/* 2338 */     this.mc.setUseOldAliasMetadataBehavior(flag);
/*      */   }
/*      */   
/*      */   public void setUseOldUTF8Behavior(boolean flag) {
/* 2342 */     this.mc.setUseOldUTF8Behavior(flag);
/*      */   }
/*      */   
/*      */   public void setUseOnlyServerErrorMessages(boolean flag) {
/* 2346 */     this.mc.setUseOnlyServerErrorMessages(flag);
/*      */   }
/*      */   
/*      */   public void setUseReadAheadInput(boolean flag) {
/* 2350 */     this.mc.setUseReadAheadInput(flag);
/*      */   }
/*      */   
/*      */   public void setUseSSL(boolean property) {
/* 2354 */     this.mc.setUseSSL(property);
/*      */   }
/*      */   
/*      */   public void setUseSSPSCompatibleTimezoneShift(boolean flag) {
/* 2358 */     this.mc.setUseSSPSCompatibleTimezoneShift(flag);
/*      */   }
/*      */   
/*      */   public void setUseServerPrepStmts(boolean flag) {
/* 2362 */     this.mc.setUseServerPrepStmts(flag);
/*      */   }
/*      */   
/*      */   public void setUseServerPreparedStmts(boolean flag) {
/* 2366 */     this.mc.setUseServerPreparedStmts(flag);
/*      */   }
/*      */   
/*      */   public void setUseSqlStateCodes(boolean flag) {
/* 2370 */     this.mc.setUseSqlStateCodes(flag);
/*      */   }
/*      */   
/*      */   public void setUseStreamLengthsInPrepStmts(boolean property) {
/* 2374 */     this.mc.setUseStreamLengthsInPrepStmts(property);
/*      */   }
/*      */   
/*      */   public void setUseTimezone(boolean property) {
/* 2378 */     this.mc.setUseTimezone(property);
/*      */   }
/*      */   
/*      */   public void setUseUltraDevWorkAround(boolean property) {
/* 2382 */     this.mc.setUseUltraDevWorkAround(property);
/*      */   }
/*      */   
/*      */   public void setUseUnbufferedInput(boolean flag) {
/* 2386 */     this.mc.setUseUnbufferedInput(flag);
/*      */   }
/*      */   
/*      */   public void setUseUnicode(boolean flag) {
/* 2390 */     this.mc.setUseUnicode(flag);
/*      */   }
/*      */   
/*      */   public void setUseUsageAdvisor(boolean useUsageAdvisorFlag) {
/* 2394 */     this.mc.setUseUsageAdvisor(useUsageAdvisorFlag);
/*      */   }
/*      */   
/*      */   public void setUtf8OutsideBmpExcludedColumnNamePattern(String regexPattern) {
/* 2398 */     this.mc.setUtf8OutsideBmpExcludedColumnNamePattern(regexPattern);
/*      */   }
/*      */   
/*      */   public void setUtf8OutsideBmpIncludedColumnNamePattern(String regexPattern) {
/* 2402 */     this.mc.setUtf8OutsideBmpIncludedColumnNamePattern(regexPattern);
/*      */   }
/*      */   
/*      */   public void setYearIsDateType(boolean flag) {
/* 2406 */     this.mc.setYearIsDateType(flag);
/*      */   }
/*      */   
/*      */   public void setZeroDateTimeBehavior(String behavior) {
/* 2410 */     this.mc.setZeroDateTimeBehavior(behavior);
/*      */   }
/*      */   
/*      */   public boolean useUnbufferedInput() {
/* 2414 */     return this.mc.useUnbufferedInput();
/*      */   }
/*      */   
/*      */   public void initializeExtension(Extension ex) throws SQLException {
/* 2418 */     this.mc.initializeExtension(ex);
/*      */   }
/*      */   
/*      */   public String getProfilerEventHandler() {
/* 2422 */     return this.mc.getProfilerEventHandler();
/*      */   }
/*      */   
/*      */   public void setProfilerEventHandler(String handler) {
/* 2426 */     this.mc.setProfilerEventHandler(handler);
/*      */   }
/*      */   
/*      */   public boolean getVerifyServerCertificate() {
/* 2430 */     return this.mc.getVerifyServerCertificate();
/*      */   }
/*      */   
/*      */   public void setVerifyServerCertificate(boolean flag) {
/* 2434 */     this.mc.setVerifyServerCertificate(flag);
/*      */   }
/*      */   
/*      */   public boolean getUseLegacyDatetimeCode() {
/* 2438 */     return this.mc.getUseLegacyDatetimeCode();
/*      */   }
/*      */   
/*      */   public void setUseLegacyDatetimeCode(boolean flag) {
/* 2442 */     this.mc.setUseLegacyDatetimeCode(flag);
/*      */   }
/*      */   
/*      */   public boolean getSendFractionalSeconds() {
/* 2446 */     return this.mc.getSendFractionalSeconds();
/*      */   }
/*      */   
/*      */   public void setSendFractionalSeconds(boolean flag) {
/* 2450 */     this.mc.setSendFractionalSeconds(flag);
/*      */   }
/*      */   
/*      */   public int getSelfDestructOnPingMaxOperations() {
/* 2454 */     return this.mc.getSelfDestructOnPingMaxOperations();
/*      */   }
/*      */   
/*      */   public int getSelfDestructOnPingSecondsLifetime() {
/* 2458 */     return this.mc.getSelfDestructOnPingSecondsLifetime();
/*      */   }
/*      */   
/*      */   public void setSelfDestructOnPingMaxOperations(int maxOperations) throws SQLException {
/* 2462 */     this.mc.setSelfDestructOnPingMaxOperations(maxOperations);
/*      */   }
/*      */   
/*      */   public void setSelfDestructOnPingSecondsLifetime(int seconds) throws SQLException {
/* 2466 */     this.mc.setSelfDestructOnPingSecondsLifetime(seconds);
/*      */   }
/*      */   
/*      */   public boolean getUseColumnNamesInFindColumn() {
/* 2470 */     return this.mc.getUseColumnNamesInFindColumn();
/*      */   }
/*      */   
/*      */   public void setUseColumnNamesInFindColumn(boolean flag) {
/* 2474 */     this.mc.setUseColumnNamesInFindColumn(flag);
/*      */   }
/*      */   
/*      */   public boolean getUseLocalTransactionState() {
/* 2478 */     return this.mc.getUseLocalTransactionState();
/*      */   }
/*      */   
/*      */   public void setUseLocalTransactionState(boolean flag) {
/* 2482 */     this.mc.setUseLocalTransactionState(flag);
/*      */   }
/*      */   
/*      */   public boolean getCompensateOnDuplicateKeyUpdateCounts() {
/* 2486 */     return this.mc.getCompensateOnDuplicateKeyUpdateCounts();
/*      */   }
/*      */   
/*      */   public void setCompensateOnDuplicateKeyUpdateCounts(boolean flag) {
/* 2490 */     this.mc.setCompensateOnDuplicateKeyUpdateCounts(flag);
/*      */   }
/*      */   
/*      */   public boolean getUseAffectedRows() {
/* 2494 */     return this.mc.getUseAffectedRows();
/*      */   }
/*      */   
/*      */   public void setUseAffectedRows(boolean flag) {
/* 2498 */     this.mc.setUseAffectedRows(flag);
/*      */   }
/*      */   
/*      */   public String getPasswordCharacterEncoding() {
/* 2502 */     return this.mc.getPasswordCharacterEncoding();
/*      */   }
/*      */   
/*      */   public void setPasswordCharacterEncoding(String characterSet) {
/* 2506 */     this.mc.setPasswordCharacterEncoding(characterSet);
/*      */   }
/*      */   
/*      */   public int getAutoIncrementIncrement() {
/* 2510 */     return this.mc.getAutoIncrementIncrement();
/*      */   }
/*      */   
/*      */   public int getLoadBalanceBlacklistTimeout() {
/* 2514 */     return this.mc.getLoadBalanceBlacklistTimeout();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceBlacklistTimeout(int loadBalanceBlacklistTimeout) throws SQLException {
/* 2518 */     this.mc.setLoadBalanceBlacklistTimeout(loadBalanceBlacklistTimeout);
/*      */   }
/*      */   
/*      */   public int getLoadBalancePingTimeout() {
/* 2522 */     return this.mc.getLoadBalancePingTimeout();
/*      */   }
/*      */   
/*      */   public void setLoadBalancePingTimeout(int loadBalancePingTimeout) throws SQLException {
/* 2526 */     this.mc.setLoadBalancePingTimeout(loadBalancePingTimeout);
/*      */   }
/*      */   
/*      */   public boolean getLoadBalanceValidateConnectionOnSwapServer() {
/* 2530 */     return this.mc.getLoadBalanceValidateConnectionOnSwapServer();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceValidateConnectionOnSwapServer(boolean loadBalanceValidateConnectionOnSwapServer) {
/* 2534 */     this.mc.setLoadBalanceValidateConnectionOnSwapServer(loadBalanceValidateConnectionOnSwapServer);
/*      */   }
/*      */   
/*      */   public void setRetriesAllDown(int retriesAllDown) throws SQLException {
/* 2538 */     this.mc.setRetriesAllDown(retriesAllDown);
/*      */   }
/*      */   
/*      */   public int getRetriesAllDown() {
/* 2542 */     return this.mc.getRetriesAllDown();
/*      */   }
/*      */   
/*      */   public ExceptionInterceptor getExceptionInterceptor() {
/* 2546 */     return this.pooledConnection.getExceptionInterceptor();
/*      */   }
/*      */   
/*      */   public String getExceptionInterceptors() {
/* 2550 */     return this.mc.getExceptionInterceptors();
/*      */   }
/*      */   
/*      */   public void setExceptionInterceptors(String exceptionInterceptors) {
/* 2554 */     this.mc.setExceptionInterceptors(exceptionInterceptors);
/*      */   }
/*      */   
/*      */   public boolean getQueryTimeoutKillsConnection() {
/* 2558 */     return this.mc.getQueryTimeoutKillsConnection();
/*      */   }
/*      */   
/*      */   public void setQueryTimeoutKillsConnection(boolean queryTimeoutKillsConnection) {
/* 2562 */     this.mc.setQueryTimeoutKillsConnection(queryTimeoutKillsConnection);
/*      */   }
/*      */   
/*      */   public boolean hasSameProperties(Connection c) {
/* 2566 */     return this.mc.hasSameProperties(c);
/*      */   }
/*      */   
/*      */   public Properties getProperties() {
/* 2570 */     return this.mc.getProperties();
/*      */   }
/*      */   
/*      */   public String getHost() {
/* 2574 */     return this.mc.getHost();
/*      */   }
/*      */   
/*      */   public void setProxy(MySQLConnection conn) {
/* 2578 */     this.mc.setProxy(conn);
/*      */   }
/*      */   
/*      */   public boolean getRetainStatementAfterResultSetClose() {
/* 2582 */     return this.mc.getRetainStatementAfterResultSetClose();
/*      */   }
/*      */   
/*      */   public int getMaxAllowedPacket() {
/* 2586 */     return this.mc.getMaxAllowedPacket();
/*      */   }
/*      */   
/*      */   public String getLoadBalanceConnectionGroup() {
/* 2590 */     return this.mc.getLoadBalanceConnectionGroup();
/*      */   }
/*      */   
/*      */   public boolean getLoadBalanceEnableJMX() {
/* 2594 */     return this.mc.getLoadBalanceEnableJMX();
/*      */   }
/*      */   
/*      */   public String getLoadBalanceExceptionChecker() {
/* 2598 */     return this.mc.getLoadBalanceExceptionChecker();
/*      */   }
/*      */   
/*      */   public String getLoadBalanceSQLExceptionSubclassFailover() {
/* 2602 */     return this.mc.getLoadBalanceSQLExceptionSubclassFailover();
/*      */   }
/*      */   
/*      */   public String getLoadBalanceSQLStateFailover() {
/* 2606 */     return this.mc.getLoadBalanceSQLStateFailover();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceConnectionGroup(String loadBalanceConnectionGroup) {
/* 2610 */     this.mc.setLoadBalanceConnectionGroup(loadBalanceConnectionGroup);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceEnableJMX(boolean loadBalanceEnableJMX) {
/* 2615 */     this.mc.setLoadBalanceEnableJMX(loadBalanceEnableJMX);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceExceptionChecker(String loadBalanceExceptionChecker) {
/* 2620 */     this.mc.setLoadBalanceExceptionChecker(loadBalanceExceptionChecker);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceSQLExceptionSubclassFailover(String loadBalanceSQLExceptionSubclassFailover) {
/* 2625 */     this.mc.setLoadBalanceSQLExceptionSubclassFailover(loadBalanceSQLExceptionSubclassFailover);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceSQLStateFailover(String loadBalanceSQLStateFailover) {
/* 2630 */     this.mc.setLoadBalanceSQLStateFailover(loadBalanceSQLStateFailover);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getLoadBalanceAutoCommitStatementRegex() {
/* 2635 */     return this.mc.getLoadBalanceAutoCommitStatementRegex();
/*      */   }
/*      */   
/*      */   public int getLoadBalanceAutoCommitStatementThreshold() {
/* 2639 */     return this.mc.getLoadBalanceAutoCommitStatementThreshold();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceAutoCommitStatementRegex(String loadBalanceAutoCommitStatementRegex) {
/* 2643 */     this.mc.setLoadBalanceAutoCommitStatementRegex(loadBalanceAutoCommitStatementRegex);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceAutoCommitStatementThreshold(int loadBalanceAutoCommitStatementThreshold) throws SQLException {
/* 2648 */     this.mc.setLoadBalanceAutoCommitStatementThreshold(loadBalanceAutoCommitStatementThreshold);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceHostRemovalGracePeriod(int loadBalanceHostRemovalGracePeriod) throws SQLException {
/* 2653 */     this.mc.setLoadBalanceHostRemovalGracePeriod(loadBalanceHostRemovalGracePeriod);
/*      */   }
/*      */   
/*      */   public int getLoadBalanceHostRemovalGracePeriod() {
/* 2657 */     return this.mc.getLoadBalanceHostRemovalGracePeriod();
/*      */   }
/*      */   
/*      */   public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
/* 2661 */     checkClosed();
/*      */     
/*      */     try {
/* 2664 */       this.mc.setTypeMap(map);
/* 2665 */     } catch (SQLException sqlException) {
/* 2666 */       checkAndFireConnectionError(sqlException);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean getIncludeThreadDumpInDeadlockExceptions() {
/* 2671 */     return this.mc.getIncludeThreadDumpInDeadlockExceptions();
/*      */   }
/*      */   
/*      */   public void setIncludeThreadDumpInDeadlockExceptions(boolean flag) {
/* 2675 */     this.mc.setIncludeThreadDumpInDeadlockExceptions(flag);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIncludeThreadNamesAsStatementComment() {
/* 2680 */     return this.mc.getIncludeThreadNamesAsStatementComment();
/*      */   }
/*      */   
/*      */   public void setIncludeThreadNamesAsStatementComment(boolean flag) {
/* 2684 */     this.mc.setIncludeThreadNamesAsStatementComment(flag);
/*      */   }
/*      */   
/*      */   public boolean isServerLocal() throws SQLException {
/* 2688 */     return this.mc.isServerLocal();
/*      */   }
/*      */   
/*      */   public void setAuthenticationPlugins(String authenticationPlugins) {
/* 2692 */     this.mc.setAuthenticationPlugins(authenticationPlugins);
/*      */   }
/*      */   
/*      */   public String getAuthenticationPlugins() {
/* 2696 */     return this.mc.getAuthenticationPlugins();
/*      */   }
/*      */   
/*      */   public void setDisabledAuthenticationPlugins(String disabledAuthenticationPlugins) {
/* 2700 */     this.mc.setDisabledAuthenticationPlugins(disabledAuthenticationPlugins);
/*      */   }
/*      */   
/*      */   public String getDisabledAuthenticationPlugins() {
/* 2704 */     return this.mc.getDisabledAuthenticationPlugins();
/*      */   }
/*      */   
/*      */   public void setDefaultAuthenticationPlugin(String defaultAuthenticationPlugin) {
/* 2708 */     this.mc.setDefaultAuthenticationPlugin(defaultAuthenticationPlugin);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDefaultAuthenticationPlugin() {
/* 2713 */     return this.mc.getDefaultAuthenticationPlugin();
/*      */   }
/*      */   
/*      */   public void setParseInfoCacheFactory(String factoryClassname) {
/* 2717 */     this.mc.setParseInfoCacheFactory(factoryClassname);
/*      */   }
/*      */   
/*      */   public String getParseInfoCacheFactory() {
/* 2721 */     return this.mc.getParseInfoCacheFactory();
/*      */   }
/*      */   
/*      */   public void setSchema(String schema) throws SQLException {
/* 2725 */     this.mc.setSchema(schema);
/*      */   }
/*      */   
/*      */   public String getSchema() throws SQLException {
/* 2729 */     return this.mc.getSchema();
/*      */   }
/*      */   
/*      */   public void abort(Executor executor) throws SQLException {
/* 2733 */     this.mc.abort(executor);
/*      */   }
/*      */   
/*      */   public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
/* 2737 */     this.mc.setNetworkTimeout(executor, milliseconds);
/*      */   }
/*      */   
/*      */   public int getNetworkTimeout() throws SQLException {
/* 2741 */     return this.mc.getNetworkTimeout();
/*      */   }
/*      */   
/*      */   public void setServerConfigCacheFactory(String factoryClassname) {
/* 2745 */     this.mc.setServerConfigCacheFactory(factoryClassname);
/*      */   }
/*      */   
/*      */   public String getServerConfigCacheFactory() {
/* 2749 */     return this.mc.getServerConfigCacheFactory();
/*      */   }
/*      */   
/*      */   public void setDisconnectOnExpiredPasswords(boolean disconnectOnExpiredPasswords) {
/* 2753 */     this.mc.setDisconnectOnExpiredPasswords(disconnectOnExpiredPasswords);
/*      */   }
/*      */   
/*      */   public boolean getDisconnectOnExpiredPasswords() {
/* 2757 */     return this.mc.getDisconnectOnExpiredPasswords();
/*      */   }
/*      */   
/*      */   public void setGetProceduresReturnsFunctions(boolean getProcedureReturnsFunctions) {
/* 2761 */     this.mc.setGetProceduresReturnsFunctions(getProcedureReturnsFunctions);
/*      */   }
/*      */   
/*      */   public boolean getGetProceduresReturnsFunctions() {
/* 2765 */     return this.mc.getGetProceduresReturnsFunctions();
/*      */   }
/*      */   
/*      */   public void abortInternal() throws SQLException {
/* 2769 */     this.mc.abortInternal();
/*      */   }
/*      */   
/*      */   public Object getConnectionMutex() {
/* 2773 */     return this.mc.getConnectionMutex();
/*      */   }
/*      */   
/*      */   public boolean getAllowMasterDownConnections() {
/* 2777 */     return this.mc.getAllowMasterDownConnections();
/*      */   }
/*      */   
/*      */   public void setAllowMasterDownConnections(boolean connectIfMasterDown) {
/* 2781 */     this.mc.setAllowMasterDownConnections(connectIfMasterDown);
/*      */   }
/*      */   
/*      */   public boolean getAllowSlaveDownConnections() {
/* 2785 */     return this.mc.getAllowSlaveDownConnections();
/*      */   }
/*      */   
/*      */   public void setAllowSlaveDownConnections(boolean connectIfSlaveDown) {
/* 2789 */     this.mc.setAllowSlaveDownConnections(connectIfSlaveDown);
/*      */   }
/*      */   
/*      */   public boolean getReadFromMasterWhenNoSlaves() {
/* 2793 */     return this.mc.getReadFromMasterWhenNoSlaves();
/*      */   }
/*      */   
/*      */   public void setReadFromMasterWhenNoSlaves(boolean useMasterIfSlavesDown) {
/* 2797 */     this.mc.setReadFromMasterWhenNoSlaves(useMasterIfSlavesDown);
/*      */   }
/*      */   
/*      */   public boolean getReplicationEnableJMX() {
/* 2801 */     return this.mc.getReplicationEnableJMX();
/*      */   }
/*      */   
/*      */   public void setReplicationEnableJMX(boolean replicationEnableJMX) {
/* 2805 */     this.mc.setReplicationEnableJMX(replicationEnableJMX);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getConnectionAttributes() throws SQLException {
/* 2810 */     return this.mc.getConnectionAttributes();
/*      */   }
/*      */   
/*      */   public void setDetectCustomCollations(boolean detectCustomCollations) {
/* 2814 */     this.mc.setDetectCustomCollations(detectCustomCollations);
/*      */   }
/*      */   
/*      */   public boolean getDetectCustomCollations() {
/* 2818 */     return this.mc.getDetectCustomCollations();
/*      */   }
/*      */   
/*      */   public int getSessionMaxRows() {
/* 2822 */     return this.mc.getSessionMaxRows();
/*      */   }
/*      */   
/*      */   public void setSessionMaxRows(int max) throws SQLException {
/* 2826 */     this.mc.setSessionMaxRows(max);
/*      */   }
/*      */   
/*      */   public String getServerRSAPublicKeyFile() {
/* 2830 */     return this.mc.getServerRSAPublicKeyFile();
/*      */   }
/*      */   
/*      */   public void setServerRSAPublicKeyFile(String serverRSAPublicKeyFile) throws SQLException {
/* 2834 */     this.mc.setServerRSAPublicKeyFile(serverRSAPublicKeyFile);
/*      */   }
/*      */   
/*      */   public boolean getAllowPublicKeyRetrieval() {
/* 2838 */     return this.mc.getAllowPublicKeyRetrieval();
/*      */   }
/*      */   
/*      */   public void setAllowPublicKeyRetrieval(boolean allowPublicKeyRetrieval) throws SQLException {
/* 2842 */     this.mc.setAllowPublicKeyRetrieval(allowPublicKeyRetrieval);
/*      */   }
/*      */   
/*      */   public void setDontCheckOnDuplicateKeyUpdateInSQL(boolean dontCheckOnDuplicateKeyUpdateInSQL) {
/* 2846 */     this.mc.setDontCheckOnDuplicateKeyUpdateInSQL(dontCheckOnDuplicateKeyUpdateInSQL);
/*      */   }
/*      */   
/*      */   public boolean getDontCheckOnDuplicateKeyUpdateInSQL() {
/* 2850 */     return this.mc.getDontCheckOnDuplicateKeyUpdateInSQL();
/*      */   }
/*      */   
/*      */   public void setSocksProxyHost(String socksProxyHost) {
/* 2854 */     this.mc.setSocksProxyHost(socksProxyHost);
/*      */   }
/*      */   
/*      */   public String getSocksProxyHost() {
/* 2858 */     return this.mc.getSocksProxyHost();
/*      */   }
/*      */   
/*      */   public void setSocksProxyPort(int socksProxyPort) throws SQLException {
/* 2862 */     this.mc.setSocksProxyPort(socksProxyPort);
/*      */   }
/*      */   
/*      */   public int getSocksProxyPort() {
/* 2866 */     return this.mc.getSocksProxyPort();
/*      */   }
/*      */   
/*      */   public boolean getReadOnlyPropagatesToServer() {
/* 2870 */     return this.mc.getReadOnlyPropagatesToServer();
/*      */   }
/*      */   
/*      */   public void setReadOnlyPropagatesToServer(boolean flag) {
/* 2874 */     this.mc.setReadOnlyPropagatesToServer(flag);
/*      */   }
/*      */   
/*      */   public String getEnabledSSLCipherSuites() {
/* 2878 */     return this.mc.getEnabledSSLCipherSuites();
/*      */   }
/*      */   
/*      */   public void setEnabledSSLCipherSuites(String cipherSuites) {
/* 2882 */     this.mc.setEnabledSSLCipherSuites(cipherSuites);
/*      */   }
/*      */   
/*      */   public String getEnabledTLSProtocols() {
/* 2886 */     return this.mc.getEnabledTLSProtocols();
/*      */   }
/*      */   
/*      */   public void setEnabledTLSProtocols(String protocols) {
/* 2890 */     this.mc.setEnabledTLSProtocols(protocols);
/*      */   }
/*      */   
/*      */   public boolean getEnableEscapeProcessing() {
/* 2894 */     return this.mc.getEnableEscapeProcessing();
/*      */   }
/*      */   
/*      */   public void setEnableEscapeProcessing(boolean flag) {
/* 2898 */     this.mc.setEnableEscapeProcessing(flag);
/*      */   }
/*      */   
/*      */   public boolean isUseSSLExplicit() {
/* 2902 */     return this.mc.isUseSSLExplicit();
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\ConnectionWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */