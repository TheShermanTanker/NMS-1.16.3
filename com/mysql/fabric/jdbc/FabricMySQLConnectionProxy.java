/*      */ package com.mysql.fabric.jdbc;
/*      */ 
/*      */ import com.mysql.fabric.FabricCommunicationException;
/*      */ import com.mysql.fabric.FabricConnection;
/*      */ import com.mysql.fabric.Server;
/*      */ import com.mysql.fabric.ServerGroup;
/*      */ import com.mysql.fabric.ShardMapping;
/*      */ import com.mysql.jdbc.Buffer;
/*      */ import com.mysql.jdbc.CachedResultSetMetaData;
/*      */ import com.mysql.jdbc.Connection;
/*      */ import com.mysql.jdbc.ConnectionPropertiesImpl;
/*      */ import com.mysql.jdbc.ExceptionInterceptor;
/*      */ import com.mysql.jdbc.Extension;
/*      */ import com.mysql.jdbc.Field;
/*      */ import com.mysql.jdbc.MySQLConnection;
/*      */ import com.mysql.jdbc.MysqlIO;
/*      */ import com.mysql.jdbc.ReplicationConnection;
/*      */ import com.mysql.jdbc.ReplicationConnectionGroup;
/*      */ import com.mysql.jdbc.ReplicationConnectionGroupManager;
/*      */ import com.mysql.jdbc.ReplicationConnectionProxy;
/*      */ import com.mysql.jdbc.ResultSetInternalMethods;
/*      */ import com.mysql.jdbc.SQLError;
/*      */ import com.mysql.jdbc.ServerPreparedStatement;
/*      */ import com.mysql.jdbc.SingleByteCharsetConverter;
/*      */ import com.mysql.jdbc.Statement;
/*      */ import com.mysql.jdbc.StatementImpl;
/*      */ import com.mysql.jdbc.StatementInterceptorV2;
/*      */ import com.mysql.jdbc.Util;
/*      */ import com.mysql.jdbc.exceptions.MySQLNonTransientConnectionException;
/*      */ import com.mysql.jdbc.log.Log;
/*      */ import com.mysql.jdbc.log.LogFactory;
/*      */ import com.mysql.jdbc.profiler.ProfilerEventHandler;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Savepoint;
/*      */ import java.sql.Statement;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
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
/*      */ public class FabricMySQLConnectionProxy
/*      */   extends ConnectionPropertiesImpl
/*      */   implements FabricMySQLConnection, FabricMySQLConnectionProperties
/*      */ {
/*      */   private static final long serialVersionUID = 5845485979107347258L;
/*      */   private Log log;
/*      */   protected FabricConnection fabricConnection;
/*      */   protected boolean closed = false;
/*      */   protected boolean transactionInProgress = false;
/*  100 */   protected Map<ServerGroup, ReplicationConnection> serverConnections = new HashMap<ServerGroup, ReplicationConnection>();
/*      */ 
/*      */   
/*      */   protected ReplicationConnection currentConnection;
/*      */ 
/*      */   
/*      */   protected String shardKey;
/*      */   
/*      */   protected String shardTable;
/*      */   
/*      */   protected String serverGroupName;
/*      */   
/*  112 */   protected Set<String> queryTables = new HashSet<String>();
/*      */   
/*      */   protected ServerGroup serverGroup;
/*      */   
/*      */   protected String host;
/*      */   
/*      */   protected String port;
/*      */   
/*      */   protected String username;
/*      */   protected String password;
/*      */   protected String database;
/*      */   protected ShardMapping shardMapping;
/*      */   protected boolean readOnly = false;
/*      */   protected boolean autoCommit = true;
/*  126 */   protected int transactionIsolation = 4;
/*      */   
/*      */   private String fabricShardKey;
/*      */   
/*      */   private String fabricShardTable;
/*      */   
/*      */   private String fabricServerGroup;
/*      */   
/*      */   private String fabricProtocol;
/*      */   private String fabricUsername;
/*      */   private String fabricPassword;
/*      */   private boolean reportErrors = false;
/*  138 */   private static final Set<String> replConnGroupLocks = Collections.synchronizedSet(new HashSet<String>());
/*      */   
/*      */   private static final Class<?> JDBC4_NON_TRANSIENT_CONN_EXCEPTION;
/*      */   
/*      */   static {
/*  143 */     Class<?> clazz = null;
/*      */     try {
/*  145 */       if (Util.isJdbc4()) {
/*  146 */         clazz = Class.forName("com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException");
/*      */       }
/*  148 */     } catch (ClassNotFoundException e) {}
/*      */ 
/*      */     
/*  151 */     JDBC4_NON_TRANSIENT_CONN_EXCEPTION = clazz;
/*      */   }
/*      */ 
/*      */   
/*      */   public FabricMySQLConnectionProxy(Properties props) throws SQLException {
/*  156 */     this.fabricShardKey = props.getProperty("fabricShardKey");
/*  157 */     this.fabricShardTable = props.getProperty("fabricShardTable");
/*  158 */     this.fabricServerGroup = props.getProperty("fabricServerGroup");
/*  159 */     this.fabricProtocol = props.getProperty("fabricProtocol");
/*  160 */     this.fabricUsername = props.getProperty("fabricUsername");
/*  161 */     this.fabricPassword = props.getProperty("fabricPassword");
/*  162 */     this.reportErrors = Boolean.valueOf(props.getProperty("fabricReportErrors")).booleanValue();
/*  163 */     props.remove("fabricShardKey");
/*  164 */     props.remove("fabricShardTable");
/*  165 */     props.remove("fabricServerGroup");
/*  166 */     props.remove("fabricProtocol");
/*  167 */     props.remove("fabricUsername");
/*  168 */     props.remove("fabricPassword");
/*  169 */     props.remove("fabricReportErrors");
/*      */     
/*  171 */     this.host = props.getProperty("HOST");
/*  172 */     this.port = props.getProperty("PORT");
/*  173 */     this.username = props.getProperty("user");
/*  174 */     this.password = props.getProperty("password");
/*  175 */     this.database = props.getProperty("DBNAME");
/*  176 */     if (this.username == null) {
/*  177 */       this.username = "";
/*      */     }
/*  179 */     if (this.password == null) {
/*  180 */       this.password = "";
/*      */     }
/*      */ 
/*      */     
/*  184 */     String exceptionInterceptors = props.getProperty("exceptionInterceptors");
/*  185 */     if (exceptionInterceptors == null || "null".equals("exceptionInterceptors")) {
/*  186 */       exceptionInterceptors = "";
/*      */     } else {
/*  188 */       exceptionInterceptors = exceptionInterceptors + ",";
/*      */     } 
/*  190 */     exceptionInterceptors = exceptionInterceptors + "com.mysql.fabric.jdbc.ErrorReportingExceptionInterceptor";
/*  191 */     props.setProperty("exceptionInterceptors", exceptionInterceptors);
/*      */     
/*  193 */     initializeProperties(props);
/*      */ 
/*      */     
/*  196 */     if (this.fabricServerGroup != null && this.fabricShardTable != null) {
/*  197 */       throw SQLError.createSQLException("Server group and shard table are mutually exclusive. Only one may be provided.", "08004", null, getExceptionInterceptor(), this);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  202 */       String url = this.fabricProtocol + "://" + this.host + ":" + this.port;
/*  203 */       this.fabricConnection = new FabricConnection(url, this.fabricUsername, this.fabricPassword);
/*  204 */     } catch (FabricCommunicationException ex) {
/*  205 */       throw SQLError.createSQLException("Unable to establish connection to the Fabric server", "08004", ex, getExceptionInterceptor(), this);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  210 */     this.log = LogFactory.getLogger(getLogger(), "FabricMySQLConnectionProxy", null);
/*      */     
/*  212 */     setShardTable(this.fabricShardTable);
/*  213 */     setShardKey(this.fabricShardKey);
/*      */     
/*  215 */     setServerGroupName(this.fabricServerGroup);
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
/*      */   synchronized SQLException interceptException(SQLException sqlEx, Connection conn, String groupName, String hostname, String portNumber) throws FabricCommunicationException {
/*  232 */     if (((sqlEx.getSQLState() == null || !sqlEx.getSQLState().startsWith("08")) && !MySQLNonTransientConnectionException.class.isAssignableFrom(sqlEx.getClass()) && (JDBC4_NON_TRANSIENT_CONN_EXCEPTION == null || !JDBC4_NON_TRANSIENT_CONN_EXCEPTION.isAssignableFrom(sqlEx.getClass()))) || (sqlEx.getCause() != null && FabricCommunicationException.class.isAssignableFrom(sqlEx.getCause().getClass())))
/*      */     {
/*      */ 
/*      */       
/*  236 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  240 */     Server currentServer = this.serverGroup.getServer(hostname + ":" + portNumber);
/*      */ 
/*      */     
/*  243 */     if (currentServer == null) {
/*  244 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  248 */     if (this.reportErrors) {
/*  249 */       this.fabricConnection.getClient().reportServerError(currentServer, sqlEx.toString(), true);
/*      */     }
/*      */ 
/*      */     
/*  253 */     if (replConnGroupLocks.add(this.serverGroup.getName())) {
/*      */ 
/*      */       
/*      */       try {
/*  257 */         this.fabricConnection.refreshStatePassive();
/*  258 */         setCurrentServerGroup(this.serverGroup.getName());
/*  259 */       } catch (SQLException ex) {
/*  260 */         return SQLError.createSQLException("Unable to refresh Fabric state. Failover impossible", "08006", ex, null);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       finally {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  270 */         replConnGroupLocks.remove(this.serverGroup.getName());
/*      */       } 
/*      */     } else {
/*  273 */       return SQLError.createSQLException("Fabric state syncing already in progress in another thread.", "08006", sqlEx, null);
/*      */     } 
/*      */     
/*  276 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void refreshStateIfNecessary() throws SQLException {
/*  283 */     if (this.fabricConnection.isStateExpired()) {
/*  284 */       this.fabricConnection.refreshStatePassive();
/*  285 */       if (this.serverGroup != null) {
/*  286 */         setCurrentServerGroup(this.serverGroup.getName());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShardKey(String shardKey) throws SQLException {
/*  295 */     ensureNoTransactionInProgress();
/*      */     
/*  297 */     this.currentConnection = null;
/*      */     
/*  299 */     if (shardKey != null) {
/*  300 */       if (this.serverGroupName != null) {
/*  301 */         throw SQLError.createSQLException("Shard key cannot be provided when server group is chosen directly.", "S1009", null, getExceptionInterceptor(), this);
/*      */       }
/*  303 */       if (this.shardTable == null) {
/*  304 */         throw SQLError.createSQLException("Shard key cannot be provided without a shard table.", "S1009", null, getExceptionInterceptor(), this);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  309 */       setCurrentServerGroup(this.shardMapping.getGroupNameForKey(shardKey));
/*  310 */     } else if (this.shardTable != null) {
/*  311 */       setCurrentServerGroup(this.shardMapping.getGlobalGroupName());
/*      */     } 
/*  313 */     this.shardKey = shardKey;
/*      */   }
/*      */   
/*      */   public String getShardKey() {
/*  317 */     return this.shardKey;
/*      */   }
/*      */   
/*      */   public void setShardTable(String shardTable) throws SQLException {
/*  321 */     ensureNoTransactionInProgress();
/*      */     
/*  323 */     this.currentConnection = null;
/*      */     
/*  325 */     if (this.serverGroupName != null) {
/*  326 */       throw SQLError.createSQLException("Server group and shard table are mutually exclusive. Only one may be provided.", "S1009", null, getExceptionInterceptor(), this);
/*      */     }
/*      */ 
/*      */     
/*  330 */     this.shardKey = null;
/*  331 */     this.serverGroup = null;
/*  332 */     this.shardTable = shardTable;
/*  333 */     if (shardTable == null) {
/*  334 */       this.shardMapping = null;
/*      */     } else {
/*      */       
/*  337 */       String table = shardTable;
/*  338 */       String db = this.database;
/*  339 */       if (shardTable.contains(".")) {
/*  340 */         String[] pair = shardTable.split("\\.");
/*  341 */         db = pair[0];
/*  342 */         table = pair[1];
/*      */       } 
/*  344 */       this.shardMapping = this.fabricConnection.getShardMapping(db, table);
/*  345 */       if (this.shardMapping == null) {
/*  346 */         throw SQLError.createSQLException("Shard mapping not found for table `" + shardTable + "'", "S1009", null, getExceptionInterceptor(), this);
/*      */       }
/*      */ 
/*      */       
/*  350 */       setCurrentServerGroup(this.shardMapping.getGlobalGroupName());
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getShardTable() {
/*  355 */     return this.shardTable;
/*      */   }
/*      */   
/*      */   public void setServerGroupName(String serverGroupName) throws SQLException {
/*  359 */     ensureNoTransactionInProgress();
/*      */     
/*  361 */     this.currentConnection = null;
/*      */ 
/*      */     
/*  364 */     if (serverGroupName != null) {
/*  365 */       setCurrentServerGroup(serverGroupName);
/*      */     }
/*      */     
/*  368 */     this.serverGroupName = serverGroupName;
/*      */   }
/*      */   
/*      */   public String getServerGroupName() {
/*  372 */     return this.serverGroupName;
/*      */   }
/*      */   
/*      */   public void clearServerSelectionCriteria() throws SQLException {
/*  376 */     ensureNoTransactionInProgress();
/*  377 */     this.shardTable = null;
/*  378 */     this.shardKey = null;
/*  379 */     this.serverGroupName = null;
/*  380 */     this.serverGroup = null;
/*  381 */     this.queryTables.clear();
/*  382 */     this.currentConnection = null;
/*      */   }
/*      */   
/*      */   public ServerGroup getCurrentServerGroup() {
/*  386 */     return this.serverGroup;
/*      */   }
/*      */   
/*      */   public void clearQueryTables() throws SQLException {
/*  390 */     ensureNoTransactionInProgress();
/*      */     
/*  392 */     this.currentConnection = null;
/*      */     
/*  394 */     this.queryTables.clear();
/*  395 */     setShardTable((String)null);
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
/*      */   public void addQueryTable(String tableName) throws SQLException {
/*  407 */     ensureNoTransactionInProgress();
/*      */     
/*  409 */     this.currentConnection = null;
/*      */ 
/*      */     
/*  412 */     if (this.shardMapping == null) {
/*  413 */       if (this.fabricConnection.getShardMapping(this.database, tableName) != null) {
/*  414 */         setShardTable(tableName);
/*      */       }
/*      */     } else {
/*  417 */       ShardMapping mappingForTableName = this.fabricConnection.getShardMapping(this.database, tableName);
/*  418 */       if (mappingForTableName != null && !mappingForTableName.equals(this.shardMapping)) {
/*  419 */         throw SQLError.createSQLException("Cross-shard query not allowed", "S1009", null, getExceptionInterceptor(), this);
/*      */       }
/*      */     } 
/*  422 */     this.queryTables.add(tableName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<String> getQueryTables() {
/*  429 */     return this.queryTables;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setCurrentServerGroup(String serverGroupName) throws SQLException {
/*  436 */     this.serverGroup = this.fabricConnection.getServerGroup(serverGroupName);
/*      */     
/*  438 */     if (this.serverGroup == null) {
/*  439 */       throw SQLError.createSQLException("Cannot find server group: `" + serverGroupName + "'", "S1009", null, getExceptionInterceptor(), this);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  444 */     ReplicationConnectionGroup replConnGroup = ReplicationConnectionGroupManager.getConnectionGroup(serverGroupName);
/*  445 */     if (replConnGroup != null && 
/*  446 */       replConnGroupLocks.add(this.serverGroup.getName())) {
/*      */       try {
/*  448 */         syncGroupServersToReplicationConnectionGroup(replConnGroup);
/*      */       } finally {
/*  450 */         replConnGroupLocks.remove(this.serverGroup.getName());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MySQLConnection getActiveMySQLConnectionChecked() throws SQLException {
/*  470 */     ReplicationConnection c = (ReplicationConnection)getActiveConnection();
/*  471 */     MySQLConnection mc = (MySQLConnection)c.getCurrentConnection();
/*  472 */     return mc;
/*      */   }
/*      */   
/*      */   public MySQLConnection getActiveMySQLConnection() {
/*      */     try {
/*  477 */       return getActiveMySQLConnectionChecked();
/*  478 */     } catch (SQLException ex) {
/*  479 */       throw new IllegalStateException("Unable to determine active connection", ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Connection getActiveConnectionPassive() {
/*      */     try {
/*  485 */       return getActiveConnection();
/*  486 */     } catch (SQLException ex) {
/*  487 */       throw new IllegalStateException("Unable to determine active connection", ex);
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
/*      */   private void syncGroupServersToReplicationConnectionGroup(ReplicationConnectionGroup replConnGroup) throws SQLException {
/*  499 */     String currentMasterString = null;
/*  500 */     if (replConnGroup.getMasterHosts().size() == 1) {
/*  501 */       currentMasterString = replConnGroup.getMasterHosts().iterator().next();
/*      */     }
/*      */     
/*  504 */     if (currentMasterString != null && (this.serverGroup.getMaster() == null || !currentMasterString.equals(this.serverGroup.getMaster().getHostPortString()))) {
/*      */       
/*      */       try {
/*      */         
/*  508 */         replConnGroup.removeMasterHost(currentMasterString, false);
/*  509 */       } catch (SQLException ex) {
/*      */         
/*  511 */         getLog().logWarn("Unable to remove master: " + currentMasterString, ex);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  516 */     Server newMaster = this.serverGroup.getMaster();
/*  517 */     if (newMaster != null && replConnGroup.getMasterHosts().size() == 0) {
/*  518 */       getLog().logInfo("Changing master for group '" + replConnGroup.getGroupName() + "' to: " + newMaster);
/*      */       try {
/*  520 */         if (!replConnGroup.getSlaveHosts().contains(newMaster.getHostPortString())) {
/*  521 */           replConnGroup.addSlaveHost(newMaster.getHostPortString());
/*      */         }
/*  523 */         replConnGroup.promoteSlaveToMaster(newMaster.getHostPortString());
/*  524 */       } catch (SQLException ex) {
/*  525 */         throw SQLError.createSQLException("Unable to promote new master '" + newMaster.toString() + "'", ex.getSQLState(), ex, null);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  531 */     for (Server s : this.serverGroup.getServers()) {
/*  532 */       if (s.isSlave()) {
/*      */         
/*      */         try {
/*  535 */           replConnGroup.addSlaveHost(s.getHostPortString());
/*  536 */         } catch (SQLException ex) {
/*      */           
/*  538 */           getLog().logWarn("Unable to add slave: " + s.toString(), ex);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  543 */     for (String hostPortString : replConnGroup.getSlaveHosts()) {
/*  544 */       Server fabServer = this.serverGroup.getServer(hostPortString);
/*  545 */       if (fabServer == null || !fabServer.isSlave()) {
/*      */         try {
/*  547 */           replConnGroup.removeSlaveHost(hostPortString, true);
/*  548 */         } catch (SQLException ex) {
/*      */           
/*  550 */           getLog().logWarn("Unable to remove slave: " + hostPortString, ex);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Connection getActiveConnection() throws SQLException {
/*  557 */     if (!this.transactionInProgress) {
/*  558 */       refreshStateIfNecessary();
/*      */     }
/*      */     
/*  561 */     if (this.currentConnection != null) {
/*  562 */       return (Connection)this.currentConnection;
/*      */     }
/*      */     
/*  565 */     if (getCurrentServerGroup() == null) {
/*  566 */       throw SQLError.createSQLException("No server group selected.", "08004", null, getExceptionInterceptor(), this);
/*      */     }
/*      */ 
/*      */     
/*  570 */     this.currentConnection = this.serverConnections.get(this.serverGroup);
/*  571 */     if (this.currentConnection != null) {
/*  572 */       return (Connection)this.currentConnection;
/*      */     }
/*      */ 
/*      */     
/*  576 */     List<String> masterHost = new ArrayList<String>();
/*  577 */     List<String> slaveHosts = new ArrayList<String>();
/*  578 */     for (Server s : this.serverGroup.getServers()) {
/*  579 */       if (s.isMaster()) {
/*  580 */         masterHost.add(s.getHostPortString()); continue;
/*  581 */       }  if (s.isSlave()) {
/*  582 */         slaveHosts.add(s.getHostPortString());
/*      */       }
/*      */     } 
/*  585 */     Properties info = exposeAsProperties(null);
/*  586 */     ReplicationConnectionGroup replConnGroup = ReplicationConnectionGroupManager.getConnectionGroup(this.serverGroup.getName());
/*  587 */     if (replConnGroup != null && 
/*  588 */       replConnGroupLocks.add(this.serverGroup.getName())) {
/*      */       try {
/*  590 */         syncGroupServersToReplicationConnectionGroup(replConnGroup);
/*      */       } finally {
/*  592 */         replConnGroupLocks.remove(this.serverGroup.getName());
/*      */       } 
/*      */     }
/*      */     
/*  596 */     info.put("replicationConnectionGroup", this.serverGroup.getName());
/*  597 */     info.setProperty("user", this.username);
/*  598 */     info.setProperty("password", this.password);
/*  599 */     info.setProperty("DBNAME", getCatalog());
/*  600 */     info.setProperty("connectionAttributes", "fabricHaGroup:" + this.serverGroup.getName());
/*  601 */     info.setProperty("retriesAllDown", "1");
/*  602 */     info.setProperty("allowMasterDownConnections", "true");
/*  603 */     info.setProperty("allowSlaveDownConnections", "true");
/*  604 */     info.setProperty("readFromMasterWhenNoSlaves", "true");
/*  605 */     this.currentConnection = ReplicationConnectionProxy.createProxyInstance(masterHost, info, slaveHosts, info);
/*  606 */     this.serverConnections.put(this.serverGroup, this.currentConnection);
/*      */     
/*  608 */     this.currentConnection.setProxy(this);
/*  609 */     this.currentConnection.setAutoCommit(this.autoCommit);
/*  610 */     this.currentConnection.setReadOnly(this.readOnly);
/*  611 */     this.currentConnection.setTransactionIsolation(this.transactionIsolation);
/*  612 */     return (Connection)this.currentConnection;
/*      */   }
/*      */   
/*      */   private void ensureOpen() throws SQLException {
/*  616 */     if (this.closed) {
/*  617 */       throw SQLError.createSQLException("No operations allowed after connection closed.", "08003", getExceptionInterceptor());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void ensureNoTransactionInProgress() throws SQLException {
/*  623 */     ensureOpen();
/*  624 */     if (this.transactionInProgress && !this.autoCommit) {
/*  625 */       throw SQLError.createSQLException("Not allow while a transaction is active.", "25000", getExceptionInterceptor());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() throws SQLException {
/*  634 */     this.closed = true;
/*  635 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/*      */       try {
/*  637 */         replicationConnection.close();
/*  638 */       } catch (SQLException ex) {}
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isClosed() {
/*  644 */     return this.closed;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isValid(int timeout) throws SQLException {
/*  652 */     return !this.closed;
/*      */   }
/*      */   
/*      */   public void setReadOnly(boolean readOnly) throws SQLException {
/*  656 */     this.readOnly = readOnly;
/*  657 */     for (ReplicationConnection conn : this.serverConnections.values()) {
/*  658 */       conn.setReadOnly(readOnly);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() throws SQLException {
/*  663 */     return this.readOnly;
/*      */   }
/*      */   
/*      */   public boolean isReadOnly(boolean useSessionStatus) throws SQLException {
/*  667 */     return this.readOnly;
/*      */   }
/*      */   
/*      */   public void setCatalog(String catalog) throws SQLException {
/*  671 */     this.database = catalog;
/*  672 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/*  673 */       replicationConnection.setCatalog(catalog);
/*      */     }
/*      */   }
/*      */   
/*      */   public String getCatalog() {
/*  678 */     return this.database;
/*      */   }
/*      */   
/*      */   public void rollback() throws SQLException {
/*  682 */     getActiveConnection().rollback();
/*  683 */     transactionCompleted();
/*      */   }
/*      */   
/*      */   public void rollback(Savepoint savepoint) throws SQLException {
/*  687 */     getActiveConnection().rollback();
/*  688 */     transactionCompleted();
/*      */   }
/*      */   
/*      */   public void commit() throws SQLException {
/*  692 */     getActiveConnection().commit();
/*  693 */     transactionCompleted();
/*      */   }
/*      */   
/*      */   public void setAutoCommit(boolean autoCommit) throws SQLException {
/*  697 */     this.autoCommit = autoCommit;
/*  698 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/*  699 */       replicationConnection.setAutoCommit(this.autoCommit);
/*      */     }
/*      */   }
/*      */   
/*      */   public void transactionBegun() throws SQLException {
/*  704 */     if (!this.autoCommit) {
/*  705 */       this.transactionInProgress = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void transactionCompleted() throws SQLException {
/*  710 */     this.transactionInProgress = false;
/*  711 */     refreshStateIfNecessary();
/*      */   }
/*      */   
/*      */   public boolean getAutoCommit() {
/*  715 */     return this.autoCommit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public MySQLConnection getLoadBalanceSafeProxy() {
/*  723 */     return getMultiHostSafeProxy();
/*      */   }
/*      */   
/*      */   public MySQLConnection getMultiHostSafeProxy() {
/*  727 */     return getActiveMySQLConnection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransactionIsolation(int level) throws SQLException {
/*  734 */     this.transactionIsolation = level;
/*  735 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/*  736 */       replicationConnection.setTransactionIsolation(level);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
/*  741 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/*  742 */       replicationConnection.setTypeMap(map);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setHoldability(int holdability) throws SQLException {
/*  747 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/*  748 */       replicationConnection.setHoldability(holdability);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProxy(MySQLConnection proxy) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public Savepoint setSavepoint() throws SQLException {
/*  759 */     return getActiveConnection().setSavepoint();
/*      */   }
/*      */   
/*      */   public Savepoint setSavepoint(String name) throws SQLException {
/*  763 */     this.transactionInProgress = true;
/*  764 */     return getActiveConnection().setSavepoint(name);
/*      */   }
/*      */ 
/*      */   
/*      */   public void releaseSavepoint(Savepoint savepoint) {}
/*      */   
/*      */   public CallableStatement prepareCall(String sql) throws SQLException {
/*  771 */     transactionBegun();
/*  772 */     return getActiveConnection().prepareCall(sql);
/*      */   }
/*      */   
/*      */   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/*  776 */     transactionBegun();
/*  777 */     return getActiveConnection().prepareCall(sql, resultSetType, resultSetConcurrency);
/*      */   }
/*      */   
/*      */   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/*  781 */     transactionBegun();
/*  782 */     return getActiveConnection().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql) throws SQLException {
/*  786 */     transactionBegun();
/*  787 */     return getActiveConnection().prepareStatement(sql);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
/*  791 */     transactionBegun();
/*  792 */     return getActiveConnection().prepareStatement(sql, autoGeneratedKeys);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
/*  796 */     transactionBegun();
/*  797 */     return getActiveConnection().prepareStatement(sql, columnIndexes);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/*  801 */     transactionBegun();
/*  802 */     return getActiveConnection().prepareStatement(sql, resultSetType, resultSetConcurrency);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/*  806 */     transactionBegun();
/*  807 */     return getActiveConnection().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
/*  811 */     transactionBegun();
/*  812 */     return getActiveConnection().prepareStatement(sql, columnNames);
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql) throws SQLException {
/*  816 */     transactionBegun();
/*  817 */     return getActiveConnection().clientPrepareStatement(sql);
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
/*  821 */     transactionBegun();
/*  822 */     return getActiveConnection().clientPrepareStatement(sql, autoGenKeyIndex);
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/*  826 */     transactionBegun();
/*  827 */     return getActiveConnection().clientPrepareStatement(sql, resultSetType, resultSetConcurrency);
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
/*  831 */     transactionBegun();
/*  832 */     return getActiveConnection().clientPrepareStatement(sql, autoGenKeyIndexes);
/*      */   }
/*      */ 
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/*  837 */     transactionBegun();
/*  838 */     return getActiveConnection().clientPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
/*      */   }
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
/*  842 */     transactionBegun();
/*  843 */     return getActiveConnection().clientPrepareStatement(sql, autoGenKeyColNames);
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql) throws SQLException {
/*  847 */     transactionBegun();
/*  848 */     return getActiveConnection().serverPrepareStatement(sql);
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
/*  852 */     transactionBegun();
/*  853 */     return getActiveConnection().serverPrepareStatement(sql, autoGenKeyIndex);
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/*  857 */     transactionBegun();
/*  858 */     return getActiveConnection().serverPrepareStatement(sql, resultSetType, resultSetConcurrency);
/*      */   }
/*      */ 
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/*  863 */     transactionBegun();
/*  864 */     return getActiveConnection().serverPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
/*  868 */     transactionBegun();
/*  869 */     return getActiveConnection().serverPrepareStatement(sql, autoGenKeyIndexes);
/*      */   }
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
/*  873 */     transactionBegun();
/*  874 */     return getActiveConnection().serverPrepareStatement(sql, autoGenKeyColNames);
/*      */   }
/*      */   
/*      */   public Statement createStatement() throws SQLException {
/*  878 */     transactionBegun();
/*  879 */     return getActiveConnection().createStatement();
/*      */   }
/*      */   
/*      */   public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
/*  883 */     transactionBegun();
/*  884 */     return getActiveConnection().createStatement(resultSetType, resultSetConcurrency);
/*      */   }
/*      */   
/*      */   public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/*  888 */     transactionBegun();
/*  889 */     return getActiveConnection().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
/*      */   }
/*      */ 
/*      */   
/*      */   public ResultSetInternalMethods execSQL(StatementImpl callingStatement, String sql, int maxRows, Buffer packet, int resultSetType, int resultSetConcurrency, boolean streamResults, String catalog, Field[] cachedMetadata) throws SQLException {
/*  894 */     return getActiveMySQLConnectionChecked().execSQL(callingStatement, sql, maxRows, packet, resultSetType, resultSetConcurrency, streamResults, catalog, cachedMetadata);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSetInternalMethods execSQL(StatementImpl callingStatement, String sql, int maxRows, Buffer packet, int resultSetType, int resultSetConcurrency, boolean streamResults, String catalog, Field[] cachedMetadata, boolean isBatch) throws SQLException {
/*  900 */     return getActiveMySQLConnectionChecked().execSQL(callingStatement, sql, maxRows, packet, resultSetType, resultSetConcurrency, streamResults, catalog, cachedMetadata, isBatch);
/*      */   }
/*      */ 
/*      */   
/*      */   public String extractSqlFromPacket(String possibleSqlQuery, Buffer queryPacket, int endOfQueryPacketPosition) throws SQLException {
/*  905 */     return getActiveMySQLConnectionChecked().extractSqlFromPacket(possibleSqlQuery, queryPacket, endOfQueryPacketPosition);
/*      */   }
/*      */   
/*      */   public StringBuilder generateConnectionCommentBlock(StringBuilder buf) {
/*  909 */     return getActiveMySQLConnection().generateConnectionCommentBlock(buf);
/*      */   }
/*      */   
/*      */   public MysqlIO getIO() throws SQLException {
/*  913 */     return getActiveMySQLConnectionChecked().getIO();
/*      */   }
/*      */   
/*      */   public Calendar getCalendarInstanceForSessionOrNew() {
/*  917 */     return getActiveMySQLConnection().getCalendarInstanceForSessionOrNew();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String getServerCharacterEncoding() {
/*  925 */     return getServerCharset();
/*      */   }
/*      */   
/*      */   public String getServerCharset() {
/*  929 */     return getActiveMySQLConnection().getServerCharset();
/*      */   }
/*      */   
/*      */   public TimeZone getServerTimezoneTZ() {
/*  933 */     return getActiveMySQLConnection().getServerTimezoneTZ();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean versionMeetsMinimum(int major, int minor, int subminor) throws SQLException {
/*  941 */     return getActiveConnection().versionMeetsMinimum(major, minor, subminor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsIsolationLevel() {
/*  948 */     return getActiveConnectionPassive().supportsIsolationLevel();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsQuotedIdentifiers() {
/*  955 */     return getActiveConnectionPassive().supportsQuotedIdentifiers();
/*      */   }
/*      */   
/*      */   public DatabaseMetaData getMetaData() throws SQLException {
/*  959 */     return getActiveConnection().getMetaData();
/*      */   }
/*      */   
/*      */   public String getCharacterSetMetadata() {
/*  963 */     return getActiveMySQLConnection().getCharacterSetMetadata();
/*      */   }
/*      */   
/*      */   public Statement getMetadataSafeStatement() throws SQLException {
/*  967 */     return getActiveMySQLConnectionChecked().getMetadataSafeStatement();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWrapperFor(Class<?> iface) {
/*  976 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T unwrap(Class<T> iface) {
/*  983 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void unSafeStatementInterceptors() throws SQLException {}
/*      */ 
/*      */   
/*      */   public boolean supportsTransactions() {
/*  991 */     return true;
/*      */   }
/*      */   
/*      */   public boolean isRunningOnJDK13() {
/*  995 */     return false;
/*      */   }
/*      */   
/*      */   public void createNewIO(boolean isForReconnect) throws SQLException {
/*  999 */     throw SQLError.createSQLFeatureNotSupportedException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void dumpTestcaseQuery(String query) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void abortInternal() throws SQLException {}
/*      */ 
/*      */   
/*      */   public boolean isServerLocal() throws SQLException {
/* 1012 */     return false;
/*      */   }
/*      */   
/*      */   public void shutdownServer() throws SQLException {
/* 1016 */     throw SQLError.createSQLFeatureNotSupportedException();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void clearHasTriedMaster() {}
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean hasTriedMaster() {
/* 1026 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInGlobalTx() {
/* 1031 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInGlobalTx(boolean flag) {
/* 1036 */     throw new RuntimeException("Global transactions not supported.");
/*      */   }
/*      */   
/*      */   public void changeUser(String userName, String newPassword) throws SQLException {
/* 1040 */     throw SQLError.createSQLException("User change not allowed.", getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFabricShardKey(String value) {
/* 1047 */     this.fabricShardKey = value;
/*      */   }
/*      */   
/*      */   public String getFabricShardKey() {
/* 1051 */     return this.fabricShardKey;
/*      */   }
/*      */   
/*      */   public void setFabricShardTable(String value) {
/* 1055 */     this.fabricShardTable = value;
/*      */   }
/*      */   
/*      */   public String getFabricShardTable() {
/* 1059 */     return this.fabricShardTable;
/*      */   }
/*      */   
/*      */   public void setFabricServerGroup(String value) {
/* 1063 */     this.fabricServerGroup = value;
/*      */   }
/*      */   
/*      */   public String getFabricServerGroup() {
/* 1067 */     return this.fabricServerGroup;
/*      */   }
/*      */   
/*      */   public void setFabricProtocol(String value) {
/* 1071 */     this.fabricProtocol = value;
/*      */   }
/*      */   
/*      */   public String getFabricProtocol() {
/* 1075 */     return this.fabricProtocol;
/*      */   }
/*      */   
/*      */   public void setFabricUsername(String value) {
/* 1079 */     this.fabricUsername = value;
/*      */   }
/*      */   
/*      */   public String getFabricUsername() {
/* 1083 */     return this.fabricUsername;
/*      */   }
/*      */   
/*      */   public void setFabricPassword(String value) {
/* 1087 */     this.fabricPassword = value;
/*      */   }
/*      */   
/*      */   public String getFabricPassword() {
/* 1091 */     return this.fabricPassword;
/*      */   }
/*      */   
/*      */   public void setFabricReportErrors(boolean value) {
/* 1095 */     this.reportErrors = value;
/*      */   }
/*      */   
/*      */   public boolean getFabricReportErrors() {
/* 1099 */     return this.reportErrors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllowLoadLocalInfile(boolean property) {
/* 1107 */     super.setAllowLoadLocalInfile(property);
/* 1108 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1109 */       replicationConnection.setAllowLoadLocalInfile(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAllowMultiQueries(boolean property) {
/* 1115 */     super.setAllowMultiQueries(property);
/* 1116 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1117 */       replicationConnection.setAllowMultiQueries(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAllowNanAndInf(boolean flag) {
/* 1123 */     super.setAllowNanAndInf(flag);
/* 1124 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1125 */       replicationConnection.setAllowNanAndInf(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAllowUrlInLocalInfile(boolean flag) {
/* 1131 */     super.setAllowUrlInLocalInfile(flag);
/* 1132 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1133 */       replicationConnection.setAllowUrlInLocalInfile(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAlwaysSendSetIsolation(boolean flag) {
/* 1139 */     super.setAlwaysSendSetIsolation(flag);
/* 1140 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1141 */       replicationConnection.setAlwaysSendSetIsolation(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAutoDeserialize(boolean flag) {
/* 1147 */     super.setAutoDeserialize(flag);
/* 1148 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1149 */       replicationConnection.setAutoDeserialize(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAutoGenerateTestcaseScript(boolean flag) {
/* 1155 */     super.setAutoGenerateTestcaseScript(flag);
/* 1156 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1157 */       replicationConnection.setAutoGenerateTestcaseScript(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAutoReconnect(boolean flag) {
/* 1163 */     super.setAutoReconnect(flag);
/* 1164 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1165 */       replicationConnection.setAutoReconnect(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAutoReconnectForConnectionPools(boolean property) {
/* 1171 */     super.setAutoReconnectForConnectionPools(property);
/* 1172 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1173 */       replicationConnection.setAutoReconnectForConnectionPools(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAutoReconnectForPools(boolean flag) {
/* 1179 */     super.setAutoReconnectForPools(flag);
/* 1180 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1181 */       replicationConnection.setAutoReconnectForPools(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBlobSendChunkSize(String value) throws SQLException {
/* 1187 */     super.setBlobSendChunkSize(value);
/* 1188 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1189 */       replicationConnection.setBlobSendChunkSize(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCacheCallableStatements(boolean flag) {
/* 1195 */     super.setCacheCallableStatements(flag);
/* 1196 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1197 */       replicationConnection.setCacheCallableStatements(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCachePreparedStatements(boolean flag) {
/* 1203 */     super.setCachePreparedStatements(flag);
/* 1204 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1205 */       replicationConnection.setCachePreparedStatements(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCacheResultSetMetadata(boolean property) {
/* 1211 */     super.setCacheResultSetMetadata(property);
/* 1212 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1213 */       replicationConnection.setCacheResultSetMetadata(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCacheServerConfiguration(boolean flag) {
/* 1219 */     super.setCacheServerConfiguration(flag);
/* 1220 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1221 */       replicationConnection.setCacheServerConfiguration(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCallableStatementCacheSize(int size) throws SQLException {
/* 1227 */     super.setCallableStatementCacheSize(size);
/* 1228 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1229 */       replicationConnection.setCallableStatementCacheSize(size);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCapitalizeDBMDTypes(boolean property) {
/* 1235 */     super.setCapitalizeDBMDTypes(property);
/* 1236 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1237 */       replicationConnection.setCapitalizeDBMDTypes(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCapitalizeTypeNames(boolean flag) {
/* 1243 */     super.setCapitalizeTypeNames(flag);
/* 1244 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1245 */       replicationConnection.setCapitalizeTypeNames(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCharacterEncoding(String encoding) {
/* 1251 */     super.setCharacterEncoding(encoding);
/* 1252 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1253 */       replicationConnection.setCharacterEncoding(encoding);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCharacterSetResults(String characterSet) {
/* 1259 */     super.setCharacterSetResults(characterSet);
/* 1260 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1261 */       replicationConnection.setCharacterSetResults(characterSet);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setClobberStreamingResults(boolean flag) {
/* 1267 */     super.setClobberStreamingResults(flag);
/* 1268 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1269 */       replicationConnection.setClobberStreamingResults(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setClobCharacterEncoding(String encoding) {
/* 1275 */     super.setClobCharacterEncoding(encoding);
/* 1276 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1277 */       replicationConnection.setClobCharacterEncoding(encoding);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConnectionCollation(String collation) {
/* 1283 */     super.setConnectionCollation(collation);
/* 1284 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1285 */       replicationConnection.setConnectionCollation(collation);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConnectTimeout(int timeoutMs) throws SQLException {
/* 1291 */     super.setConnectTimeout(timeoutMs);
/* 1292 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1293 */       replicationConnection.setConnectTimeout(timeoutMs);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setContinueBatchOnError(boolean property) {
/* 1299 */     super.setContinueBatchOnError(property);
/* 1300 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1301 */       replicationConnection.setContinueBatchOnError(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCreateDatabaseIfNotExist(boolean flag) {
/* 1307 */     super.setCreateDatabaseIfNotExist(flag);
/* 1308 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1309 */       replicationConnection.setCreateDatabaseIfNotExist(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDefaultFetchSize(int n) throws SQLException {
/* 1315 */     super.setDefaultFetchSize(n);
/* 1316 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1317 */       replicationConnection.setDefaultFetchSize(n);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDetectServerPreparedStmts(boolean property) {
/* 1323 */     super.setDetectServerPreparedStmts(property);
/* 1324 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1325 */       replicationConnection.setDetectServerPreparedStmts(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDontTrackOpenResources(boolean flag) {
/* 1331 */     super.setDontTrackOpenResources(flag);
/* 1332 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1333 */       replicationConnection.setDontTrackOpenResources(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDumpQueriesOnException(boolean flag) {
/* 1339 */     super.setDumpQueriesOnException(flag);
/* 1340 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1341 */       replicationConnection.setDumpQueriesOnException(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDynamicCalendars(boolean flag) {
/* 1347 */     super.setDynamicCalendars(flag);
/* 1348 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1349 */       replicationConnection.setDynamicCalendars(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setElideSetAutoCommits(boolean flag) {
/* 1355 */     super.setElideSetAutoCommits(flag);
/* 1356 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1357 */       replicationConnection.setElideSetAutoCommits(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEmptyStringsConvertToZero(boolean flag) {
/* 1363 */     super.setEmptyStringsConvertToZero(flag);
/* 1364 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1365 */       replicationConnection.setEmptyStringsConvertToZero(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEmulateLocators(boolean property) {
/* 1371 */     super.setEmulateLocators(property);
/* 1372 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1373 */       replicationConnection.setEmulateLocators(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEmulateUnsupportedPstmts(boolean flag) {
/* 1379 */     super.setEmulateUnsupportedPstmts(flag);
/* 1380 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1381 */       replicationConnection.setEmulateUnsupportedPstmts(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEnablePacketDebug(boolean flag) {
/* 1387 */     super.setEnablePacketDebug(flag);
/* 1388 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1389 */       replicationConnection.setEnablePacketDebug(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEncoding(String property) {
/* 1395 */     super.setEncoding(property);
/* 1396 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1397 */       replicationConnection.setEncoding(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setExplainSlowQueries(boolean flag) {
/* 1403 */     super.setExplainSlowQueries(flag);
/* 1404 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1405 */       replicationConnection.setExplainSlowQueries(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFailOverReadOnly(boolean flag) {
/* 1411 */     super.setFailOverReadOnly(flag);
/* 1412 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1413 */       replicationConnection.setFailOverReadOnly(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGatherPerformanceMetrics(boolean flag) {
/* 1419 */     super.setGatherPerformanceMetrics(flag);
/* 1420 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1421 */       replicationConnection.setGatherPerformanceMetrics(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHoldResultsOpenOverStatementClose(boolean flag) {
/* 1427 */     super.setHoldResultsOpenOverStatementClose(flag);
/* 1428 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1429 */       replicationConnection.setHoldResultsOpenOverStatementClose(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setIgnoreNonTxTables(boolean property) {
/* 1435 */     super.setIgnoreNonTxTables(property);
/* 1436 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1437 */       replicationConnection.setIgnoreNonTxTables(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInitialTimeout(int property) throws SQLException {
/* 1443 */     super.setInitialTimeout(property);
/* 1444 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1445 */       replicationConnection.setInitialTimeout(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setIsInteractiveClient(boolean property) {
/* 1451 */     super.setIsInteractiveClient(property);
/* 1452 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1453 */       replicationConnection.setIsInteractiveClient(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setJdbcCompliantTruncation(boolean flag) {
/* 1459 */     super.setJdbcCompliantTruncation(flag);
/* 1460 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1461 */       replicationConnection.setJdbcCompliantTruncation(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLocatorFetchBufferSize(String value) throws SQLException {
/* 1467 */     super.setLocatorFetchBufferSize(value);
/* 1468 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1469 */       replicationConnection.setLocatorFetchBufferSize(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLogger(String property) {
/* 1475 */     super.setLogger(property);
/* 1476 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1477 */       replicationConnection.setLogger(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoggerClassName(String className) {
/* 1483 */     super.setLoggerClassName(className);
/* 1484 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1485 */       replicationConnection.setLoggerClassName(className);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLogSlowQueries(boolean flag) {
/* 1491 */     super.setLogSlowQueries(flag);
/* 1492 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1493 */       replicationConnection.setLogSlowQueries(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMaintainTimeStats(boolean flag) {
/* 1499 */     super.setMaintainTimeStats(flag);
/* 1500 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1501 */       replicationConnection.setMaintainTimeStats(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMaxQuerySizeToLog(int sizeInBytes) throws SQLException {
/* 1507 */     super.setMaxQuerySizeToLog(sizeInBytes);
/* 1508 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1509 */       replicationConnection.setMaxQuerySizeToLog(sizeInBytes);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMaxReconnects(int property) throws SQLException {
/* 1515 */     super.setMaxReconnects(property);
/* 1516 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1517 */       replicationConnection.setMaxReconnects(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMaxRows(int property) throws SQLException {
/* 1523 */     super.setMaxRows(property);
/* 1524 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1525 */       replicationConnection.setMaxRows(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMetadataCacheSize(int value) throws SQLException {
/* 1531 */     super.setMetadataCacheSize(value);
/* 1532 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1533 */       replicationConnection.setMetadataCacheSize(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNoDatetimeStringSync(boolean flag) {
/* 1539 */     super.setNoDatetimeStringSync(flag);
/* 1540 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1541 */       replicationConnection.setNoDatetimeStringSync(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNullCatalogMeansCurrent(boolean value) {
/* 1547 */     super.setNullCatalogMeansCurrent(value);
/* 1548 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1549 */       replicationConnection.setNullCatalogMeansCurrent(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNullNamePatternMatchesAll(boolean value) {
/* 1555 */     super.setNullNamePatternMatchesAll(value);
/* 1556 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1557 */       replicationConnection.setNullNamePatternMatchesAll(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPacketDebugBufferSize(int size) throws SQLException {
/* 1563 */     super.setPacketDebugBufferSize(size);
/* 1564 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1565 */       replicationConnection.setPacketDebugBufferSize(size);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setParanoid(boolean property) {
/* 1571 */     super.setParanoid(property);
/* 1572 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1573 */       replicationConnection.setParanoid(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPedantic(boolean property) {
/* 1579 */     super.setPedantic(property);
/* 1580 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1581 */       replicationConnection.setPedantic(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPreparedStatementCacheSize(int cacheSize) throws SQLException {
/* 1587 */     super.setPreparedStatementCacheSize(cacheSize);
/* 1588 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1589 */       replicationConnection.setPreparedStatementCacheSize(cacheSize);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPreparedStatementCacheSqlLimit(int cacheSqlLimit) throws SQLException {
/* 1595 */     super.setPreparedStatementCacheSqlLimit(cacheSqlLimit);
/* 1596 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1597 */       replicationConnection.setPreparedStatementCacheSqlLimit(cacheSqlLimit);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProfileSql(boolean property) {
/* 1603 */     super.setProfileSql(property);
/* 1604 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1605 */       replicationConnection.setProfileSql(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProfileSQL(boolean flag) {
/* 1611 */     super.setProfileSQL(flag);
/* 1612 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1613 */       replicationConnection.setProfileSQL(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPropertiesTransform(String value) {
/* 1619 */     super.setPropertiesTransform(value);
/* 1620 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1621 */       replicationConnection.setPropertiesTransform(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setQueriesBeforeRetryMaster(int property) throws SQLException {
/* 1627 */     super.setQueriesBeforeRetryMaster(property);
/* 1628 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1629 */       replicationConnection.setQueriesBeforeRetryMaster(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setReconnectAtTxEnd(boolean property) {
/* 1635 */     super.setReconnectAtTxEnd(property);
/* 1636 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1637 */       replicationConnection.setReconnectAtTxEnd(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRelaxAutoCommit(boolean property) {
/* 1643 */     super.setRelaxAutoCommit(property);
/* 1644 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1645 */       replicationConnection.setRelaxAutoCommit(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setReportMetricsIntervalMillis(int millis) throws SQLException {
/* 1651 */     super.setReportMetricsIntervalMillis(millis);
/* 1652 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1653 */       replicationConnection.setReportMetricsIntervalMillis(millis);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRequireSSL(boolean property) {
/* 1659 */     super.setRequireSSL(property);
/* 1660 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1661 */       replicationConnection.setRequireSSL(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRetainStatementAfterResultSetClose(boolean flag) {
/* 1667 */     super.setRetainStatementAfterResultSetClose(flag);
/* 1668 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1669 */       replicationConnection.setRetainStatementAfterResultSetClose(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRollbackOnPooledClose(boolean flag) {
/* 1675 */     super.setRollbackOnPooledClose(flag);
/* 1676 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1677 */       replicationConnection.setRollbackOnPooledClose(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRoundRobinLoadBalance(boolean flag) {
/* 1683 */     super.setRoundRobinLoadBalance(flag);
/* 1684 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1685 */       replicationConnection.setRoundRobinLoadBalance(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRunningCTS13(boolean flag) {
/* 1691 */     super.setRunningCTS13(flag);
/* 1692 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1693 */       replicationConnection.setRunningCTS13(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSecondsBeforeRetryMaster(int property) throws SQLException {
/* 1699 */     super.setSecondsBeforeRetryMaster(property);
/* 1700 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1701 */       replicationConnection.setSecondsBeforeRetryMaster(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setServerTimezone(String property) {
/* 1707 */     super.setServerTimezone(property);
/* 1708 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1709 */       replicationConnection.setServerTimezone(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSessionVariables(String variables) {
/* 1715 */     super.setSessionVariables(variables);
/* 1716 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1717 */       replicationConnection.setSessionVariables(variables);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSlowQueryThresholdMillis(int millis) throws SQLException {
/* 1723 */     super.setSlowQueryThresholdMillis(millis);
/* 1724 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1725 */       replicationConnection.setSlowQueryThresholdMillis(millis);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSocketFactoryClassName(String property) {
/* 1731 */     super.setSocketFactoryClassName(property);
/* 1732 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1733 */       replicationConnection.setSocketFactoryClassName(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSocketTimeout(int property) throws SQLException {
/* 1739 */     super.setSocketTimeout(property);
/* 1740 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1741 */       replicationConnection.setSocketTimeout(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setStrictFloatingPoint(boolean property) {
/* 1747 */     super.setStrictFloatingPoint(property);
/* 1748 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1749 */       replicationConnection.setStrictFloatingPoint(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setStrictUpdates(boolean property) {
/* 1755 */     super.setStrictUpdates(property);
/* 1756 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1757 */       replicationConnection.setStrictUpdates(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTinyInt1isBit(boolean flag) {
/* 1763 */     super.setTinyInt1isBit(flag);
/* 1764 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1765 */       replicationConnection.setTinyInt1isBit(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTraceProtocol(boolean flag) {
/* 1771 */     super.setTraceProtocol(flag);
/* 1772 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1773 */       replicationConnection.setTraceProtocol(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTransformedBitIsBoolean(boolean flag) {
/* 1779 */     super.setTransformedBitIsBoolean(flag);
/* 1780 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1781 */       replicationConnection.setTransformedBitIsBoolean(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseCompression(boolean property) {
/* 1787 */     super.setUseCompression(property);
/* 1788 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1789 */       replicationConnection.setUseCompression(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseFastIntParsing(boolean flag) {
/* 1795 */     super.setUseFastIntParsing(flag);
/* 1796 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1797 */       replicationConnection.setUseFastIntParsing(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseHostsInPrivileges(boolean property) {
/* 1803 */     super.setUseHostsInPrivileges(property);
/* 1804 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1805 */       replicationConnection.setUseHostsInPrivileges(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseInformationSchema(boolean flag) {
/* 1811 */     super.setUseInformationSchema(flag);
/* 1812 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1813 */       replicationConnection.setUseInformationSchema(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseLocalSessionState(boolean flag) {
/* 1819 */     super.setUseLocalSessionState(flag);
/* 1820 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1821 */       replicationConnection.setUseLocalSessionState(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseOldUTF8Behavior(boolean flag) {
/* 1827 */     super.setUseOldUTF8Behavior(flag);
/* 1828 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1829 */       replicationConnection.setUseOldUTF8Behavior(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseOnlyServerErrorMessages(boolean flag) {
/* 1835 */     super.setUseOnlyServerErrorMessages(flag);
/* 1836 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1837 */       replicationConnection.setUseOnlyServerErrorMessages(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseReadAheadInput(boolean flag) {
/* 1843 */     super.setUseReadAheadInput(flag);
/* 1844 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1845 */       replicationConnection.setUseReadAheadInput(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseServerPreparedStmts(boolean flag) {
/* 1851 */     super.setUseServerPreparedStmts(flag);
/* 1852 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1853 */       replicationConnection.setUseServerPreparedStmts(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseSqlStateCodes(boolean flag) {
/* 1859 */     super.setUseSqlStateCodes(flag);
/* 1860 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1861 */       replicationConnection.setUseSqlStateCodes(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseSSL(boolean property) {
/* 1867 */     super.setUseSSL(property);
/* 1868 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1869 */       replicationConnection.setUseSSL(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseStreamLengthsInPrepStmts(boolean property) {
/* 1875 */     super.setUseStreamLengthsInPrepStmts(property);
/* 1876 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1877 */       replicationConnection.setUseStreamLengthsInPrepStmts(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseTimezone(boolean property) {
/* 1883 */     super.setUseTimezone(property);
/* 1884 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1885 */       replicationConnection.setUseTimezone(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseUltraDevWorkAround(boolean property) {
/* 1891 */     super.setUseUltraDevWorkAround(property);
/* 1892 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1893 */       replicationConnection.setUseUltraDevWorkAround(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseUnbufferedInput(boolean flag) {
/* 1899 */     super.setUseUnbufferedInput(flag);
/* 1900 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1901 */       replicationConnection.setUseUnbufferedInput(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseUnicode(boolean flag) {
/* 1907 */     super.setUseUnicode(flag);
/* 1908 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1909 */       replicationConnection.setUseUnicode(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseUsageAdvisor(boolean useUsageAdvisorFlag) {
/* 1915 */     super.setUseUsageAdvisor(useUsageAdvisorFlag);
/* 1916 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1917 */       replicationConnection.setUseUsageAdvisor(useUsageAdvisorFlag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setYearIsDateType(boolean flag) {
/* 1923 */     super.setYearIsDateType(flag);
/* 1924 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1925 */       replicationConnection.setYearIsDateType(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setZeroDateTimeBehavior(String behavior) {
/* 1931 */     super.setZeroDateTimeBehavior(behavior);
/* 1932 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1933 */       replicationConnection.setZeroDateTimeBehavior(behavior);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseCursorFetch(boolean flag) {
/* 1939 */     super.setUseCursorFetch(flag);
/* 1940 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1941 */       replicationConnection.setUseCursorFetch(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOverrideSupportsIntegrityEnhancementFacility(boolean flag) {
/* 1947 */     super.setOverrideSupportsIntegrityEnhancementFacility(flag);
/* 1948 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1949 */       replicationConnection.setOverrideSupportsIntegrityEnhancementFacility(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNoTimezoneConversionForTimeType(boolean flag) {
/* 1955 */     super.setNoTimezoneConversionForTimeType(flag);
/* 1956 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1957 */       replicationConnection.setNoTimezoneConversionForTimeType(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseJDBCCompliantTimezoneShift(boolean flag) {
/* 1963 */     super.setUseJDBCCompliantTimezoneShift(flag);
/* 1964 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1965 */       replicationConnection.setUseJDBCCompliantTimezoneShift(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAutoClosePStmtStreams(boolean flag) {
/* 1971 */     super.setAutoClosePStmtStreams(flag);
/* 1972 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1973 */       replicationConnection.setAutoClosePStmtStreams(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProcessEscapeCodesForPrepStmts(boolean flag) {
/* 1979 */     super.setProcessEscapeCodesForPrepStmts(flag);
/* 1980 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1981 */       replicationConnection.setProcessEscapeCodesForPrepStmts(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseGmtMillisForDatetimes(boolean flag) {
/* 1987 */     super.setUseGmtMillisForDatetimes(flag);
/* 1988 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1989 */       replicationConnection.setUseGmtMillisForDatetimes(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDumpMetadataOnColumnNotFound(boolean flag) {
/* 1995 */     super.setDumpMetadataOnColumnNotFound(flag);
/* 1996 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 1997 */       replicationConnection.setDumpMetadataOnColumnNotFound(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setResourceId(String resourceId) {
/* 2003 */     super.setResourceId(resourceId);
/* 2004 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2005 */       replicationConnection.setResourceId(resourceId);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRewriteBatchedStatements(boolean flag) {
/* 2011 */     super.setRewriteBatchedStatements(flag);
/* 2012 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2013 */       replicationConnection.setRewriteBatchedStatements(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setJdbcCompliantTruncationForReads(boolean jdbcCompliantTruncationForReads) {
/* 2019 */     super.setJdbcCompliantTruncationForReads(jdbcCompliantTruncationForReads);
/* 2020 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2021 */       replicationConnection.setJdbcCompliantTruncationForReads(jdbcCompliantTruncationForReads);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseJvmCharsetConverters(boolean flag) {
/* 2027 */     super.setUseJvmCharsetConverters(flag);
/* 2028 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2029 */       replicationConnection.setUseJvmCharsetConverters(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPinGlobalTxToPhysicalConnection(boolean flag) {
/* 2035 */     super.setPinGlobalTxToPhysicalConnection(flag);
/* 2036 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2037 */       replicationConnection.setPinGlobalTxToPhysicalConnection(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGatherPerfMetrics(boolean flag) {
/* 2043 */     super.setGatherPerfMetrics(flag);
/* 2044 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2045 */       replicationConnection.setGatherPerfMetrics(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUltraDevHack(boolean flag) {
/* 2051 */     super.setUltraDevHack(flag);
/* 2052 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2053 */       replicationConnection.setUltraDevHack(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInteractiveClient(boolean property) {
/* 2059 */     super.setInteractiveClient(property);
/* 2060 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2061 */       replicationConnection.setInteractiveClient(property);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSocketFactory(String name) {
/* 2067 */     super.setSocketFactory(name);
/* 2068 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2069 */       replicationConnection.setSocketFactory(name);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseServerPrepStmts(boolean flag) {
/* 2075 */     super.setUseServerPrepStmts(flag);
/* 2076 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2077 */       replicationConnection.setUseServerPrepStmts(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCacheCallableStmts(boolean flag) {
/* 2083 */     super.setCacheCallableStmts(flag);
/* 2084 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2085 */       replicationConnection.setCacheCallableStmts(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCachePrepStmts(boolean flag) {
/* 2091 */     super.setCachePrepStmts(flag);
/* 2092 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2093 */       replicationConnection.setCachePrepStmts(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCallableStmtCacheSize(int cacheSize) throws SQLException {
/* 2099 */     super.setCallableStmtCacheSize(cacheSize);
/* 2100 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2101 */       replicationConnection.setCallableStmtCacheSize(cacheSize);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPrepStmtCacheSize(int cacheSize) throws SQLException {
/* 2107 */     super.setPrepStmtCacheSize(cacheSize);
/* 2108 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2109 */       replicationConnection.setPrepStmtCacheSize(cacheSize);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPrepStmtCacheSqlLimit(int sqlLimit) throws SQLException {
/* 2115 */     super.setPrepStmtCacheSqlLimit(sqlLimit);
/* 2116 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2117 */       replicationConnection.setPrepStmtCacheSqlLimit(sqlLimit);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNoAccessToProcedureBodies(boolean flag) {
/* 2123 */     super.setNoAccessToProcedureBodies(flag);
/* 2124 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2125 */       replicationConnection.setNoAccessToProcedureBodies(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseOldAliasMetadataBehavior(boolean flag) {
/* 2131 */     super.setUseOldAliasMetadataBehavior(flag);
/* 2132 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2133 */       replicationConnection.setUseOldAliasMetadataBehavior(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setClientCertificateKeyStorePassword(String value) {
/* 2139 */     super.setClientCertificateKeyStorePassword(value);
/* 2140 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2141 */       replicationConnection.setClientCertificateKeyStorePassword(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setClientCertificateKeyStoreType(String value) {
/* 2147 */     super.setClientCertificateKeyStoreType(value);
/* 2148 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2149 */       replicationConnection.setClientCertificateKeyStoreType(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setClientCertificateKeyStoreUrl(String value) {
/* 2155 */     super.setClientCertificateKeyStoreUrl(value);
/* 2156 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2157 */       replicationConnection.setClientCertificateKeyStoreUrl(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTrustCertificateKeyStorePassword(String value) {
/* 2163 */     super.setTrustCertificateKeyStorePassword(value);
/* 2164 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2165 */       replicationConnection.setTrustCertificateKeyStorePassword(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTrustCertificateKeyStoreType(String value) {
/* 2171 */     super.setTrustCertificateKeyStoreType(value);
/* 2172 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2173 */       replicationConnection.setTrustCertificateKeyStoreType(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTrustCertificateKeyStoreUrl(String value) {
/* 2179 */     super.setTrustCertificateKeyStoreUrl(value);
/* 2180 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2181 */       replicationConnection.setTrustCertificateKeyStoreUrl(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseSSPSCompatibleTimezoneShift(boolean flag) {
/* 2187 */     super.setUseSSPSCompatibleTimezoneShift(flag);
/* 2188 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2189 */       replicationConnection.setUseSSPSCompatibleTimezoneShift(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTreatUtilDateAsTimestamp(boolean flag) {
/* 2195 */     super.setTreatUtilDateAsTimestamp(flag);
/* 2196 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2197 */       replicationConnection.setTreatUtilDateAsTimestamp(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseFastDateParsing(boolean flag) {
/* 2203 */     super.setUseFastDateParsing(flag);
/* 2204 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2205 */       replicationConnection.setUseFastDateParsing(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLocalSocketAddress(String address) {
/* 2211 */     super.setLocalSocketAddress(address);
/* 2212 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2213 */       replicationConnection.setLocalSocketAddress(address);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseConfigs(String configs) {
/* 2219 */     super.setUseConfigs(configs);
/* 2220 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2221 */       replicationConnection.setUseConfigs(configs);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGenerateSimpleParameterMetadata(boolean flag) {
/* 2227 */     super.setGenerateSimpleParameterMetadata(flag);
/* 2228 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2229 */       replicationConnection.setGenerateSimpleParameterMetadata(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLogXaCommands(boolean flag) {
/* 2235 */     super.setLogXaCommands(flag);
/* 2236 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2237 */       replicationConnection.setLogXaCommands(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setResultSetSizeThreshold(int threshold) throws SQLException {
/* 2243 */     super.setResultSetSizeThreshold(threshold);
/* 2244 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2245 */       replicationConnection.setResultSetSizeThreshold(threshold);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNetTimeoutForStreamingResults(int value) throws SQLException {
/* 2251 */     super.setNetTimeoutForStreamingResults(value);
/* 2252 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2253 */       replicationConnection.setNetTimeoutForStreamingResults(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEnableQueryTimeouts(boolean flag) {
/* 2259 */     super.setEnableQueryTimeouts(flag);
/* 2260 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2261 */       replicationConnection.setEnableQueryTimeouts(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPadCharsWithSpace(boolean flag) {
/* 2267 */     super.setPadCharsWithSpace(flag);
/* 2268 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2269 */       replicationConnection.setPadCharsWithSpace(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseDynamicCharsetInfo(boolean flag) {
/* 2275 */     super.setUseDynamicCharsetInfo(flag);
/* 2276 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2277 */       replicationConnection.setUseDynamicCharsetInfo(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setClientInfoProvider(String classname) {
/* 2283 */     super.setClientInfoProvider(classname);
/* 2284 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2285 */       replicationConnection.setClientInfoProvider(classname);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPopulateInsertRowWithDefaultValues(boolean flag) {
/* 2291 */     super.setPopulateInsertRowWithDefaultValues(flag);
/* 2292 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2293 */       replicationConnection.setPopulateInsertRowWithDefaultValues(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceStrategy(String strategy) {
/* 2299 */     super.setLoadBalanceStrategy(strategy);
/* 2300 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2301 */       replicationConnection.setLoadBalanceStrategy(strategy);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTcpNoDelay(boolean flag) {
/* 2307 */     super.setTcpNoDelay(flag);
/* 2308 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2309 */       replicationConnection.setTcpNoDelay(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTcpKeepAlive(boolean flag) {
/* 2315 */     super.setTcpKeepAlive(flag);
/* 2316 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2317 */       replicationConnection.setTcpKeepAlive(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTcpRcvBuf(int bufSize) throws SQLException {
/* 2323 */     super.setTcpRcvBuf(bufSize);
/* 2324 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2325 */       replicationConnection.setTcpRcvBuf(bufSize);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTcpSndBuf(int bufSize) throws SQLException {
/* 2331 */     super.setTcpSndBuf(bufSize);
/* 2332 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2333 */       replicationConnection.setTcpSndBuf(bufSize);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTcpTrafficClass(int classFlags) throws SQLException {
/* 2339 */     super.setTcpTrafficClass(classFlags);
/* 2340 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2341 */       replicationConnection.setTcpTrafficClass(classFlags);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseNanosForElapsedTime(boolean flag) {
/* 2347 */     super.setUseNanosForElapsedTime(flag);
/* 2348 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2349 */       replicationConnection.setUseNanosForElapsedTime(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSlowQueryThresholdNanos(long nanos) throws SQLException {
/* 2355 */     super.setSlowQueryThresholdNanos(nanos);
/* 2356 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2357 */       replicationConnection.setSlowQueryThresholdNanos(nanos);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setStatementInterceptors(String value) {
/* 2363 */     super.setStatementInterceptors(value);
/* 2364 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2365 */       replicationConnection.setStatementInterceptors(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseDirectRowUnpack(boolean flag) {
/* 2371 */     super.setUseDirectRowUnpack(flag);
/* 2372 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2373 */       replicationConnection.setUseDirectRowUnpack(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLargeRowSizeThreshold(String value) throws SQLException {
/* 2379 */     super.setLargeRowSizeThreshold(value);
/* 2380 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2381 */       replicationConnection.setLargeRowSizeThreshold(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseBlobToStoreUTF8OutsideBMP(boolean flag) {
/* 2387 */     super.setUseBlobToStoreUTF8OutsideBMP(flag);
/* 2388 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2389 */       replicationConnection.setUseBlobToStoreUTF8OutsideBMP(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUtf8OutsideBmpExcludedColumnNamePattern(String regexPattern) {
/* 2395 */     super.setUtf8OutsideBmpExcludedColumnNamePattern(regexPattern);
/* 2396 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2397 */       replicationConnection.setUtf8OutsideBmpExcludedColumnNamePattern(regexPattern);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUtf8OutsideBmpIncludedColumnNamePattern(String regexPattern) {
/* 2403 */     super.setUtf8OutsideBmpIncludedColumnNamePattern(regexPattern);
/* 2404 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2405 */       replicationConnection.setUtf8OutsideBmpIncludedColumnNamePattern(regexPattern);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setIncludeInnodbStatusInDeadlockExceptions(boolean flag) {
/* 2411 */     super.setIncludeInnodbStatusInDeadlockExceptions(flag);
/* 2412 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2413 */       replicationConnection.setIncludeInnodbStatusInDeadlockExceptions(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setIncludeThreadDumpInDeadlockExceptions(boolean flag) {
/* 2419 */     super.setIncludeThreadDumpInDeadlockExceptions(flag);
/* 2420 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2421 */       replicationConnection.setIncludeThreadDumpInDeadlockExceptions(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setIncludeThreadNamesAsStatementComment(boolean flag) {
/* 2427 */     super.setIncludeThreadNamesAsStatementComment(flag);
/* 2428 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2429 */       replicationConnection.setIncludeThreadNamesAsStatementComment(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBlobsAreStrings(boolean flag) {
/* 2435 */     super.setBlobsAreStrings(flag);
/* 2436 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2437 */       replicationConnection.setBlobsAreStrings(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFunctionsNeverReturnBlobs(boolean flag) {
/* 2443 */     super.setFunctionsNeverReturnBlobs(flag);
/* 2444 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2445 */       replicationConnection.setFunctionsNeverReturnBlobs(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAutoSlowLog(boolean flag) {
/* 2451 */     super.setAutoSlowLog(flag);
/* 2452 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2453 */       replicationConnection.setAutoSlowLog(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConnectionLifecycleInterceptors(String interceptors) {
/* 2459 */     super.setConnectionLifecycleInterceptors(interceptors);
/* 2460 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2461 */       replicationConnection.setConnectionLifecycleInterceptors(interceptors);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProfilerEventHandler(String handler) {
/* 2467 */     super.setProfilerEventHandler(handler);
/* 2468 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2469 */       replicationConnection.setProfilerEventHandler(handler);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setVerifyServerCertificate(boolean flag) {
/* 2475 */     super.setVerifyServerCertificate(flag);
/* 2476 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2477 */       replicationConnection.setVerifyServerCertificate(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseLegacyDatetimeCode(boolean flag) {
/* 2483 */     super.setUseLegacyDatetimeCode(flag);
/* 2484 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2485 */       replicationConnection.setUseLegacyDatetimeCode(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSelfDestructOnPingSecondsLifetime(int seconds) throws SQLException {
/* 2491 */     super.setSelfDestructOnPingSecondsLifetime(seconds);
/* 2492 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2493 */       replicationConnection.setSelfDestructOnPingSecondsLifetime(seconds);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSelfDestructOnPingMaxOperations(int maxOperations) throws SQLException {
/* 2499 */     super.setSelfDestructOnPingMaxOperations(maxOperations);
/* 2500 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2501 */       replicationConnection.setSelfDestructOnPingMaxOperations(maxOperations);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseColumnNamesInFindColumn(boolean flag) {
/* 2507 */     super.setUseColumnNamesInFindColumn(flag);
/* 2508 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2509 */       replicationConnection.setUseColumnNamesInFindColumn(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseLocalTransactionState(boolean flag) {
/* 2515 */     super.setUseLocalTransactionState(flag);
/* 2516 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2517 */       replicationConnection.setUseLocalTransactionState(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCompensateOnDuplicateKeyUpdateCounts(boolean flag) {
/* 2523 */     super.setCompensateOnDuplicateKeyUpdateCounts(flag);
/* 2524 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2525 */       replicationConnection.setCompensateOnDuplicateKeyUpdateCounts(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUseAffectedRows(boolean flag) {
/* 2531 */     super.setUseAffectedRows(flag);
/* 2532 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2533 */       replicationConnection.setUseAffectedRows(flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPasswordCharacterEncoding(String characterSet) {
/* 2539 */     super.setPasswordCharacterEncoding(characterSet);
/* 2540 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2541 */       replicationConnection.setPasswordCharacterEncoding(characterSet);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceBlacklistTimeout(int loadBalanceBlacklistTimeout) throws SQLException {
/* 2547 */     super.setLoadBalanceBlacklistTimeout(loadBalanceBlacklistTimeout);
/* 2548 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2549 */       replicationConnection.setLoadBalanceBlacklistTimeout(loadBalanceBlacklistTimeout);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRetriesAllDown(int retriesAllDown) throws SQLException {
/* 2555 */     super.setRetriesAllDown(retriesAllDown);
/* 2556 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2557 */       replicationConnection.setRetriesAllDown(retriesAllDown);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setExceptionInterceptors(String exceptionInterceptors) {
/* 2563 */     super.setExceptionInterceptors(exceptionInterceptors);
/* 2564 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2565 */       replicationConnection.setExceptionInterceptors(exceptionInterceptors);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setQueryTimeoutKillsConnection(boolean queryTimeoutKillsConnection) {
/* 2571 */     super.setQueryTimeoutKillsConnection(queryTimeoutKillsConnection);
/* 2572 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2573 */       replicationConnection.setQueryTimeoutKillsConnection(queryTimeoutKillsConnection);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalancePingTimeout(int loadBalancePingTimeout) throws SQLException {
/* 2579 */     super.setLoadBalancePingTimeout(loadBalancePingTimeout);
/* 2580 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2581 */       replicationConnection.setLoadBalancePingTimeout(loadBalancePingTimeout);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceValidateConnectionOnSwapServer(boolean loadBalanceValidateConnectionOnSwapServer) {
/* 2587 */     super.setLoadBalanceValidateConnectionOnSwapServer(loadBalanceValidateConnectionOnSwapServer);
/* 2588 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2589 */       replicationConnection.setLoadBalanceValidateConnectionOnSwapServer(loadBalanceValidateConnectionOnSwapServer);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceConnectionGroup(String loadBalanceConnectionGroup) {
/* 2595 */     super.setLoadBalanceConnectionGroup(loadBalanceConnectionGroup);
/* 2596 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2597 */       replicationConnection.setLoadBalanceConnectionGroup(loadBalanceConnectionGroup);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceExceptionChecker(String loadBalanceExceptionChecker) {
/* 2603 */     super.setLoadBalanceExceptionChecker(loadBalanceExceptionChecker);
/* 2604 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2605 */       replicationConnection.setLoadBalanceExceptionChecker(loadBalanceExceptionChecker);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceSQLStateFailover(String loadBalanceSQLStateFailover) {
/* 2611 */     super.setLoadBalanceSQLStateFailover(loadBalanceSQLStateFailover);
/* 2612 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2613 */       replicationConnection.setLoadBalanceSQLStateFailover(loadBalanceSQLStateFailover);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceSQLExceptionSubclassFailover(String loadBalanceSQLExceptionSubclassFailover) {
/* 2619 */     super.setLoadBalanceSQLExceptionSubclassFailover(loadBalanceSQLExceptionSubclassFailover);
/* 2620 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2621 */       replicationConnection.setLoadBalanceSQLExceptionSubclassFailover(loadBalanceSQLExceptionSubclassFailover);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceEnableJMX(boolean loadBalanceEnableJMX) {
/* 2627 */     super.setLoadBalanceEnableJMX(loadBalanceEnableJMX);
/* 2628 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2629 */       replicationConnection.setLoadBalanceEnableJMX(loadBalanceEnableJMX);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceAutoCommitStatementThreshold(int loadBalanceAutoCommitStatementThreshold) throws SQLException {
/* 2635 */     super.setLoadBalanceAutoCommitStatementThreshold(loadBalanceAutoCommitStatementThreshold);
/* 2636 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2637 */       replicationConnection.setLoadBalanceAutoCommitStatementThreshold(loadBalanceAutoCommitStatementThreshold);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoadBalanceAutoCommitStatementRegex(String loadBalanceAutoCommitStatementRegex) {
/* 2643 */     super.setLoadBalanceAutoCommitStatementRegex(loadBalanceAutoCommitStatementRegex);
/* 2644 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2645 */       replicationConnection.setLoadBalanceAutoCommitStatementRegex(loadBalanceAutoCommitStatementRegex);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAuthenticationPlugins(String authenticationPlugins) {
/* 2651 */     super.setAuthenticationPlugins(authenticationPlugins);
/* 2652 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2653 */       replicationConnection.setAuthenticationPlugins(authenticationPlugins);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisabledAuthenticationPlugins(String disabledAuthenticationPlugins) {
/* 2659 */     super.setDisabledAuthenticationPlugins(disabledAuthenticationPlugins);
/* 2660 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2661 */       replicationConnection.setDisabledAuthenticationPlugins(disabledAuthenticationPlugins);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDefaultAuthenticationPlugin(String defaultAuthenticationPlugin) {
/* 2667 */     super.setDefaultAuthenticationPlugin(defaultAuthenticationPlugin);
/* 2668 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2669 */       replicationConnection.setDefaultAuthenticationPlugin(defaultAuthenticationPlugin);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setParseInfoCacheFactory(String factoryClassname) {
/* 2675 */     super.setParseInfoCacheFactory(factoryClassname);
/* 2676 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2677 */       replicationConnection.setParseInfoCacheFactory(factoryClassname);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setServerConfigCacheFactory(String factoryClassname) {
/* 2683 */     super.setServerConfigCacheFactory(factoryClassname);
/* 2684 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2685 */       replicationConnection.setServerConfigCacheFactory(factoryClassname);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisconnectOnExpiredPasswords(boolean disconnectOnExpiredPasswords) {
/* 2691 */     super.setDisconnectOnExpiredPasswords(disconnectOnExpiredPasswords);
/* 2692 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2693 */       replicationConnection.setDisconnectOnExpiredPasswords(disconnectOnExpiredPasswords);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGetProceduresReturnsFunctions(boolean getProcedureReturnsFunctions) {
/* 2699 */     super.setGetProceduresReturnsFunctions(getProcedureReturnsFunctions);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getActiveStatementCount() {
/* 2705 */     return -1;
/*      */   }
/*      */   
/*      */   public long getIdleFor() {
/* 2709 */     return -1L;
/*      */   }
/*      */   
/*      */   public Log getLog() {
/* 2713 */     return this.log;
/*      */   }
/*      */   
/*      */   public boolean isMasterConnection() {
/* 2717 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isNoBackslashEscapesSet() {
/* 2721 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isSameResource(Connection c) {
/* 2725 */     return false;
/*      */   }
/*      */   
/*      */   public boolean parserKnowsUnicode() {
/* 2729 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void ping() throws SQLException {}
/*      */ 
/*      */   
/*      */   public void resetServerState() throws SQLException {}
/*      */ 
/*      */   
/*      */   public void setFailedOver(boolean flag) {}
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setPreferSlaveDuringFailover(boolean flag) {}
/*      */ 
/*      */   
/*      */   public void setStatementComment(String comment) {}
/*      */ 
/*      */   
/*      */   public void reportQueryTime(long millisOrNanos) {}
/*      */   
/*      */   public boolean isAbonormallyLongQuery(long millisOrNanos) {
/* 2752 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void initializeExtension(Extension ex) throws SQLException {}
/*      */   
/*      */   public int getAutoIncrementIncrement() {
/* 2759 */     return -1;
/*      */   }
/*      */   
/*      */   public boolean hasSameProperties(Connection c) {
/* 2763 */     return false;
/*      */   }
/*      */   
/*      */   public Properties getProperties() {
/* 2767 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSchema(String schema) throws SQLException {}
/*      */   
/*      */   public String getSchema() throws SQLException {
/* 2774 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void abort(Executor executor) throws SQLException {}
/*      */ 
/*      */   
/*      */   public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {}
/*      */   
/*      */   public int getNetworkTimeout() throws SQLException {
/* 2784 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void checkClosed() throws SQLException {}
/*      */   
/*      */   public Object getConnectionMutex() {
/* 2791 */     return this;
/*      */   }
/*      */   
/*      */   public void setSessionMaxRows(int max) throws SQLException {
/* 2795 */     for (ReplicationConnection replicationConnection : this.serverConnections.values()) {
/* 2796 */       replicationConnection.setSessionMaxRows(max);
/*      */     }
/*      */   }
/*      */   
/*      */   public int getSessionMaxRows() {
/* 2801 */     return getActiveConnectionPassive().getSessionMaxRows();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isProxySet() {
/* 2806 */     return false;
/*      */   }
/*      */   
/*      */   public Connection duplicate() throws SQLException {
/* 2810 */     return null;
/*      */   }
/*      */   
/*      */   public CachedResultSetMetaData getCachedMetaData(String sql) {
/* 2814 */     return null;
/*      */   }
/*      */   
/*      */   public Timer getCancelTimer() {
/* 2818 */     return null;
/*      */   }
/*      */   
/*      */   public SingleByteCharsetConverter getCharsetConverter(String javaEncodingName) throws SQLException {
/* 2822 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String getCharsetNameForIndex(int charsetIndex) throws SQLException {
/* 2830 */     return getEncodingForIndex(charsetIndex);
/*      */   }
/*      */   
/*      */   public String getEncodingForIndex(int charsetIndex) throws SQLException {
/* 2834 */     return null;
/*      */   }
/*      */   
/*      */   public TimeZone getDefaultTimeZone() {
/* 2838 */     return null;
/*      */   }
/*      */   
/*      */   public String getErrorMessageEncoding() {
/* 2842 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ExceptionInterceptor getExceptionInterceptor() {
/* 2847 */     if (this.currentConnection == null) {
/* 2848 */       return null;
/*      */     }
/*      */     
/* 2851 */     return this.currentConnection.getExceptionInterceptor();
/*      */   }
/*      */   
/*      */   public String getHost() {
/* 2855 */     return null;
/*      */   }
/*      */   
/*      */   public String getHostPortPair() {
/* 2859 */     return getActiveMySQLConnection().getHostPortPair();
/*      */   }
/*      */   
/*      */   public long getId() {
/* 2863 */     return -1L;
/*      */   }
/*      */   
/*      */   public int getMaxBytesPerChar(String javaCharsetName) throws SQLException {
/* 2867 */     return -1;
/*      */   }
/*      */   
/*      */   public int getMaxBytesPerChar(Integer charsetIndex, String javaCharsetName) throws SQLException {
/* 2871 */     return -1;
/*      */   }
/*      */   
/*      */   public int getNetBufferLength() {
/* 2875 */     return -1;
/*      */   }
/*      */   
/*      */   public boolean getRequiresEscapingEncoder() {
/* 2879 */     return false;
/*      */   }
/*      */   
/*      */   public int getServerMajorVersion() {
/* 2883 */     return -1;
/*      */   }
/*      */   
/*      */   public int getServerMinorVersion() {
/* 2887 */     return -1;
/*      */   }
/*      */   
/*      */   public int getServerSubMinorVersion() {
/* 2891 */     return -1;
/*      */   }
/*      */   
/*      */   public String getServerVariable(String variableName) {
/* 2895 */     return null;
/*      */   }
/*      */   
/*      */   public String getServerVersion() {
/* 2899 */     return null;
/*      */   }
/*      */   
/*      */   public Calendar getSessionLockedCalendar() {
/* 2903 */     return null;
/*      */   }
/*      */   
/*      */   public String getStatementComment() {
/* 2907 */     return null;
/*      */   }
/*      */   
/*      */   public List<StatementInterceptorV2> getStatementInterceptorsInstances() {
/* 2911 */     return null;
/*      */   }
/*      */   
/*      */   public String getURL() {
/* 2915 */     return null;
/*      */   }
/*      */   
/*      */   public String getUser() {
/* 2919 */     return null;
/*      */   }
/*      */   
/*      */   public Calendar getUtcCalendar() {
/* 2923 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void incrementNumberOfPreparedExecutes() {}
/*      */ 
/*      */   
/*      */   public void incrementNumberOfPrepares() {}
/*      */ 
/*      */   
/*      */   public void incrementNumberOfResultSetsCreated() {}
/*      */ 
/*      */   
/*      */   public void initializeResultsMetadataFromCache(String sql, CachedResultSetMetaData cachedMetaData, ResultSetInternalMethods resultSet) throws SQLException {}
/*      */ 
/*      */   
/*      */   public void initializeSafeStatementInterceptors() throws SQLException {}
/*      */   
/*      */   public boolean isClientTzUTC() {
/* 2942 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isCursorFetchEnabled() throws SQLException {
/* 2946 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isReadInfoMsgEnabled() {
/* 2950 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isServerTzUTC() {
/* 2954 */     return false;
/*      */   }
/*      */   
/*      */   public boolean lowerCaseTableNames() {
/* 2958 */     return getActiveMySQLConnection().lowerCaseTableNames();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void maxRowsChanged(Statement stmt) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void pingInternal(boolean checkForClosedConnection, int timeoutMillis) throws SQLException {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void realClose(boolean calledExplicitly, boolean issueRollback, boolean skipLocalTeardown, Throwable reason) throws SQLException {}
/*      */ 
/*      */   
/*      */   public void recachePreparedStatement(ServerPreparedStatement pstmt) throws SQLException {}
/*      */ 
/*      */   
/*      */   public void registerQueryExecutionTime(long queryTimeMs) {}
/*      */ 
/*      */   
/*      */   public void registerStatement(Statement stmt) {}
/*      */ 
/*      */   
/*      */   public void reportNumberOfTablesAccessed(int numTablesAccessed) {}
/*      */ 
/*      */   
/*      */   public boolean serverSupportsConvertFn() throws SQLException {
/* 2987 */     return getActiveMySQLConnectionChecked().serverSupportsConvertFn();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setReadInfoMsgEnabled(boolean flag) {}
/*      */ 
/*      */   
/*      */   public void setReadOnlyInternal(boolean readOnlyFlag) throws SQLException {}
/*      */   
/*      */   public boolean storesLowerCaseTableName() {
/* 2997 */     return getActiveMySQLConnection().storesLowerCaseTableName();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void throwConnectionClosedException() throws SQLException {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void unregisterStatement(Statement stmt) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetMaxRows(Statement stmt) throws SQLException {}
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean useAnsiQuotedIdentifiers() {
/* 3015 */     return false;
/*      */   }
/*      */   
/*      */   public boolean useMaxRows() {
/* 3019 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearWarnings() {}
/*      */ 
/*      */   
/*      */   public Properties getClientInfo() {
/* 3027 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getClientInfo(String name) {
/* 3036 */     return null;
/*      */   }
/*      */   
/*      */   public int getHoldability() {
/* 3040 */     return -1;
/*      */   }
/*      */   
/*      */   public int getTransactionIsolation() {
/* 3044 */     return -1;
/*      */   }
/*      */   
/*      */   public Map<String, Class<?>> getTypeMap() {
/* 3048 */     return null;
/*      */   }
/*      */   
/*      */   public SQLWarning getWarnings() throws SQLException {
/* 3052 */     return getActiveMySQLConnectionChecked().getWarnings();
/*      */   }
/*      */   
/*      */   public String nativeSQL(String sql) throws SQLException {
/* 3056 */     return getActiveMySQLConnectionChecked().nativeSQL(sql);
/*      */   }
/*      */   
/*      */   public ProfilerEventHandler getProfilerEventHandlerInstance() {
/* 3060 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProfilerEventHandlerInstance(ProfilerEventHandler h) {}
/*      */ 
/*      */   
/*      */   public void decachePreparedStatement(ServerPreparedStatement pstmt) throws SQLException {}
/*      */   
/*      */   public boolean isServerTruncatesFracSecs() {
/* 3070 */     return getActiveMySQLConnection().isServerTruncatesFracSecs();
/*      */   }
/*      */   
/*      */   public String getQueryTimingUnits() {
/* 3074 */     return getActiveMySQLConnection().getQueryTimingUnits();
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\jdbc\FabricMySQLConnectionProxy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */