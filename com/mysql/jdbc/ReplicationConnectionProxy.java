/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import java.util.concurrent.Executor;
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
/*     */ public class ReplicationConnectionProxy
/*     */   extends MultiHostConnectionProxy
/*     */   implements PingTarget
/*     */ {
/*     */   private ReplicationConnection thisAsReplicationConnection;
/*     */   private NonRegisteringDriver driver;
/*     */   protected boolean enableJMX = false;
/*     */   protected boolean allowMasterDownConnections = false;
/*     */   protected boolean allowSlaveDownConnections = false;
/*     */   protected boolean readFromMasterWhenNoSlaves = false;
/*     */   protected boolean readFromMasterWhenNoSlavesOriginal = false;
/*     */   protected boolean readOnly = false;
/*     */   ReplicationConnectionGroup connectionGroup;
/*  52 */   private long connectionGroupID = -1L;
/*     */   
/*     */   private List<String> masterHosts;
/*     */   
/*     */   private Properties masterProperties;
/*     */   
/*     */   protected LoadBalancedConnection masterConnection;
/*     */   private List<String> slaveHosts;
/*     */   private Properties slaveProperties;
/*     */   protected LoadBalancedConnection slavesConnection;
/*     */   private static Constructor<?> JDBC_4_REPL_CONNECTION_CTOR;
/*     */   private static Class<?>[] INTERFACES_TO_PROXY;
/*     */   
/*     */   static {
/*  66 */     if (Util.isJdbc4()) {
/*     */       try {
/*  68 */         JDBC_4_REPL_CONNECTION_CTOR = Class.forName("com.mysql.jdbc.JDBC4ReplicationMySQLConnection").getConstructor(new Class[] { ReplicationConnectionProxy.class });
/*     */         
/*  70 */         INTERFACES_TO_PROXY = new Class[] { ReplicationConnection.class, Class.forName("com.mysql.jdbc.JDBC4MySQLConnection") };
/*  71 */       } catch (SecurityException e) {
/*  72 */         throw new RuntimeException(e);
/*  73 */       } catch (NoSuchMethodException e) {
/*  74 */         throw new RuntimeException(e);
/*  75 */       } catch (ClassNotFoundException e) {
/*  76 */         throw new RuntimeException(e);
/*     */       } 
/*     */     } else {
/*  79 */       INTERFACES_TO_PROXY = new Class[] { ReplicationConnection.class };
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ReplicationConnection createProxyInstance(List<String> masterHostList, Properties masterProperties, List<String> slaveHostList, Properties slaveProperties) throws SQLException {
/*  85 */     ReplicationConnectionProxy connProxy = new ReplicationConnectionProxy(masterHostList, masterProperties, slaveHostList, slaveProperties);
/*     */     
/*  87 */     return (ReplicationConnection)Proxy.newProxyInstance(ReplicationConnection.class.getClassLoader(), INTERFACES_TO_PROXY, connProxy);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ReplicationConnectionProxy(List<String> masterHostList, Properties masterProperties, List<String> slaveHostList, Properties slaveProperties) throws SQLException {
/* 108 */     this.thisAsReplicationConnection = (ReplicationConnection)this.thisAsConnection;
/*     */     
/* 110 */     String enableJMXAsString = masterProperties.getProperty("replicationEnableJMX", "false");
/*     */     try {
/* 112 */       this.enableJMX = Boolean.parseBoolean(enableJMXAsString);
/* 113 */     } catch (Exception e) {
/* 114 */       throw SQLError.createSQLException(Messages.getString("ReplicationConnectionProxy.badValueForReplicationEnableJMX", new Object[] { enableJMXAsString }), "S1009", null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 119 */     String allowMasterDownConnectionsAsString = masterProperties.getProperty("allowMasterDownConnections", "false");
/*     */     try {
/* 121 */       this.allowMasterDownConnections = Boolean.parseBoolean(allowMasterDownConnectionsAsString);
/* 122 */     } catch (Exception e) {
/* 123 */       throw SQLError.createSQLException(Messages.getString("ReplicationConnectionProxy.badValueForAllowMasterDownConnections", new Object[] { allowMasterDownConnectionsAsString }), "S1009", null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 128 */     String allowSlaveDownConnectionsAsString = masterProperties.getProperty("allowSlaveDownConnections", "false");
/*     */     try {
/* 130 */       this.allowSlaveDownConnections = Boolean.parseBoolean(allowSlaveDownConnectionsAsString);
/* 131 */     } catch (Exception e) {
/* 132 */       throw SQLError.createSQLException(Messages.getString("ReplicationConnectionProxy.badValueForAllowSlaveDownConnections", new Object[] { allowSlaveDownConnectionsAsString }), "S1009", null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 137 */     String readFromMasterWhenNoSlavesAsString = masterProperties.getProperty("readFromMasterWhenNoSlaves");
/*     */     try {
/* 139 */       this.readFromMasterWhenNoSlavesOriginal = Boolean.parseBoolean(readFromMasterWhenNoSlavesAsString);
/*     */     }
/* 141 */     catch (Exception e) {
/* 142 */       throw SQLError.createSQLException(Messages.getString("ReplicationConnectionProxy.badValueForReadFromMasterWhenNoSlaves", new Object[] { readFromMasterWhenNoSlavesAsString }), "S1009", null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 147 */     String group = masterProperties.getProperty("replicationConnectionGroup", null);
/* 148 */     if (group != null) {
/* 149 */       this.connectionGroup = ReplicationConnectionGroupManager.getConnectionGroupInstance(group);
/* 150 */       if (this.enableJMX) {
/* 151 */         ReplicationConnectionGroupManager.registerJmx();
/*     */       }
/* 153 */       this.connectionGroupID = this.connectionGroup.registerReplicationConnection(this.thisAsReplicationConnection, masterHostList, slaveHostList);
/*     */       
/* 155 */       this.slaveHosts = new ArrayList<String>(this.connectionGroup.getSlaveHosts());
/* 156 */       this.masterHosts = new ArrayList<String>(this.connectionGroup.getMasterHosts());
/*     */     } else {
/* 158 */       this.slaveHosts = new ArrayList<String>(slaveHostList);
/* 159 */       this.masterHosts = new ArrayList<String>(masterHostList);
/*     */     } 
/*     */     
/* 162 */     this.driver = new NonRegisteringDriver();
/* 163 */     this.slaveProperties = slaveProperties;
/* 164 */     this.masterProperties = masterProperties;
/*     */     
/* 166 */     resetReadFromMasterWhenNoSlaves();
/*     */ 
/*     */     
/*     */     try {
/* 170 */       initializeSlavesConnection();
/* 171 */     } catch (SQLException e) {
/* 172 */       if (!this.allowSlaveDownConnections) {
/* 173 */         if (this.connectionGroup != null) {
/* 174 */           this.connectionGroup.handleCloseConnection(this.thisAsReplicationConnection);
/*     */         }
/* 176 */         throw e;
/*     */       } 
/*     */     } 
/*     */     
/* 180 */     SQLException exCaught = null;
/*     */     try {
/* 182 */       this.currentConnection = initializeMasterConnection();
/* 183 */     } catch (SQLException e) {
/* 184 */       exCaught = e;
/*     */     } 
/*     */     
/* 187 */     if (this.currentConnection == null) {
/* 188 */       if (this.allowMasterDownConnections && this.slavesConnection != null) {
/*     */         
/* 190 */         this.readOnly = true;
/* 191 */         this.currentConnection = this.slavesConnection;
/*     */       } else {
/* 193 */         if (this.connectionGroup != null) {
/* 194 */           this.connectionGroup.handleCloseConnection(this.thisAsReplicationConnection);
/*     */         }
/* 196 */         if (exCaught != null) {
/* 197 */           throw exCaught;
/*     */         }
/* 199 */         throw SQLError.createSQLException(Messages.getString("ReplicationConnectionProxy.initializationWithEmptyHostsLists"), "S1009", null);
/*     */       } 
/*     */     }
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
/*     */   MySQLConnection getNewWrapperForThisAsConnection() throws SQLException {
/* 213 */     if (Util.isJdbc4() || JDBC_4_REPL_CONNECTION_CTOR != null) {
/* 214 */       return (MySQLConnection)Util.handleNewInstance(JDBC_4_REPL_CONNECTION_CTOR, new Object[] { this }, null);
/*     */     }
/* 216 */     return new ReplicationMySQLConnection(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void propagateProxyDown(MySQLConnection proxyConn) {
/* 227 */     if (this.masterConnection != null) {
/* 228 */       this.masterConnection.setProxy(proxyConn);
/*     */     }
/* 230 */     if (this.slavesConnection != null) {
/* 231 */       this.slavesConnection.setProxy(proxyConn);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean shouldExceptionTriggerConnectionSwitch(Throwable t) {
/* 243 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMasterConnection() {
/* 251 */     return (this.currentConnection != null && this.currentConnection == this.masterConnection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSlavesConnection() {
/* 258 */     return (this.currentConnection != null && this.currentConnection == this.slavesConnection);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void pickNewConnection() throws SQLException {}
/*     */ 
/*     */ 
/*     */   
/*     */   void syncSessionState(Connection source, Connection target, boolean readOnlyStatus) throws SQLException {
/*     */     try {
/* 269 */       super.syncSessionState(source, target, readOnlyStatus);
/* 270 */     } catch (SQLException e1) {
/*     */       
/*     */       try {
/* 273 */         super.syncSessionState(source, target, readOnlyStatus);
/* 274 */       } catch (SQLException e2) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void doClose() throws SQLException {
/* 282 */     if (this.masterConnection != null) {
/* 283 */       this.masterConnection.close();
/*     */     }
/* 285 */     if (this.slavesConnection != null) {
/* 286 */       this.slavesConnection.close();
/*     */     }
/*     */     
/* 289 */     if (this.connectionGroup != null) {
/* 290 */       this.connectionGroup.handleCloseConnection(this.thisAsReplicationConnection);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void doAbortInternal() throws SQLException {
/* 296 */     this.masterConnection.abortInternal();
/* 297 */     this.slavesConnection.abortInternal();
/* 298 */     if (this.connectionGroup != null) {
/* 299 */       this.connectionGroup.handleCloseConnection(this.thisAsReplicationConnection);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void doAbort(Executor executor) throws SQLException {
/* 305 */     this.masterConnection.abort(executor);
/* 306 */     this.slavesConnection.abort(executor);
/* 307 */     if (this.connectionGroup != null) {
/* 308 */       this.connectionGroup.handleCloseConnection(this.thisAsReplicationConnection);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object invokeMore(Object proxy, Method method, Object[] args) throws Throwable {
/* 318 */     checkConnectionCapabilityForMethod(method);
/*     */     
/* 320 */     boolean invokeAgain = false;
/*     */     while (true) {
/*     */       try {
/* 323 */         Object result = method.invoke(this.thisAsConnection, args);
/* 324 */         if (result != null && result instanceof Statement) {
/* 325 */           ((Statement)result).setPingTarget(this);
/*     */         }
/* 327 */         return result;
/* 328 */       } catch (InvocationTargetException e) {
/* 329 */         if (invokeAgain) {
/* 330 */           invokeAgain = false;
/* 331 */         } else if (e.getCause() != null && e.getCause() instanceof SQLException && ((SQLException)e.getCause()).getSQLState() == "25000" && ((SQLException)e.getCause()).getErrorCode() == 1000001) {
/*     */ 
/*     */           
/*     */           try {
/*     */             
/* 336 */             setReadOnly(this.readOnly);
/* 337 */             invokeAgain = true;
/* 338 */           } catch (SQLException sqlEx) {}
/*     */         } 
/*     */ 
/*     */         
/* 342 */         if (!invokeAgain) {
/* 343 */           throw e;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkConnectionCapabilityForMethod(Method method) throws Throwable {
/* 355 */     if (this.masterHosts.isEmpty() && this.slaveHosts.isEmpty() && !ReplicationConnection.class.isAssignableFrom(method.getDeclaringClass())) {
/* 356 */       throw SQLError.createSQLException(Messages.getString("ReplicationConnectionProxy.noHostsInconsistentState"), "25000", 1000002, true, null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPing() throws SQLException {
/* 365 */     boolean isMasterConn = isMasterConnection();
/*     */     
/* 367 */     SQLException mastersPingException = null;
/* 368 */     SQLException slavesPingException = null;
/*     */     
/* 370 */     if (this.masterConnection != null) {
/*     */       try {
/* 372 */         this.masterConnection.ping();
/* 373 */       } catch (SQLException e) {
/* 374 */         mastersPingException = e;
/*     */       } 
/*     */     } else {
/* 377 */       initializeMasterConnection();
/*     */     } 
/*     */     
/* 380 */     if (this.slavesConnection != null) {
/*     */       try {
/* 382 */         this.slavesConnection.ping();
/* 383 */       } catch (SQLException e) {
/* 384 */         slavesPingException = e;
/*     */       } 
/*     */     } else {
/*     */       try {
/* 388 */         initializeSlavesConnection();
/* 389 */         if (switchToSlavesConnectionIfNecessary()) {
/* 390 */           isMasterConn = false;
/*     */         }
/* 392 */       } catch (SQLException e) {
/* 393 */         if (this.masterConnection == null || !this.readFromMasterWhenNoSlaves) {
/* 394 */           throw e;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 399 */     if (isMasterConn && mastersPingException != null) {
/*     */       
/* 401 */       if (this.slavesConnection != null && slavesPingException == null) {
/* 402 */         this.masterConnection = null;
/* 403 */         this.currentConnection = this.slavesConnection;
/* 404 */         this.readOnly = true;
/*     */       } 
/* 406 */       throw mastersPingException;
/*     */     } 
/* 408 */     if (!isMasterConn && (slavesPingException != null || this.slavesConnection == null)) {
/*     */       
/* 410 */       if (this.masterConnection != null && this.readFromMasterWhenNoSlaves && mastersPingException == null) {
/* 411 */         this.slavesConnection = null;
/* 412 */         this.currentConnection = this.masterConnection;
/* 413 */         this.readOnly = true;
/* 414 */         this.currentConnection.setReadOnly(true);
/*     */       } 
/* 416 */       if (slavesPingException != null) {
/* 417 */         throw slavesPingException;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private MySQLConnection initializeMasterConnection() throws SQLException {
/* 423 */     this.masterConnection = null;
/*     */     
/* 425 */     if (this.masterHosts.size() == 0) {
/* 426 */       return null;
/*     */     }
/*     */     
/* 429 */     LoadBalancedConnection newMasterConn = (LoadBalancedConnection)this.driver.connect(buildURL(this.masterHosts, this.masterProperties), this.masterProperties);
/*     */     
/* 431 */     newMasterConn.setProxy(getProxy());
/*     */     
/* 433 */     this.masterConnection = newMasterConn;
/* 434 */     return this.masterConnection;
/*     */   }
/*     */   
/*     */   private MySQLConnection initializeSlavesConnection() throws SQLException {
/* 438 */     this.slavesConnection = null;
/*     */     
/* 440 */     if (this.slaveHosts.size() == 0) {
/* 441 */       return null;
/*     */     }
/*     */     
/* 444 */     LoadBalancedConnection newSlavesConn = (LoadBalancedConnection)this.driver.connect(buildURL(this.slaveHosts, this.slaveProperties), this.slaveProperties);
/*     */     
/* 446 */     newSlavesConn.setProxy(getProxy());
/* 447 */     newSlavesConn.setReadOnly(true);
/*     */     
/* 449 */     this.slavesConnection = newSlavesConn;
/* 450 */     return this.slavesConnection;
/*     */   }
/*     */   
/*     */   private String buildURL(List<String> hosts, Properties props) {
/* 454 */     StringBuilder url = new StringBuilder("jdbc:mysql:loadbalance://");
/*     */     
/* 456 */     boolean firstHost = true;
/* 457 */     for (String host : hosts) {
/* 458 */       if (!firstHost) {
/* 459 */         url.append(',');
/*     */       }
/* 461 */       url.append(host);
/* 462 */       firstHost = false;
/*     */     } 
/* 464 */     url.append("/");
/* 465 */     String masterDb = props.getProperty("DBNAME");
/* 466 */     if (masterDb != null) {
/* 467 */       url.append(masterDb);
/*     */     }
/*     */     
/* 470 */     return url.toString();
/*     */   }
/*     */   
/*     */   private synchronized boolean switchToMasterConnection() throws SQLException {
/* 474 */     if (this.masterConnection == null || this.masterConnection.isClosed()) {
/*     */       try {
/* 476 */         if (initializeMasterConnection() == null) {
/* 477 */           return false;
/*     */         }
/* 479 */       } catch (SQLException e) {
/* 480 */         this.currentConnection = null;
/* 481 */         throw e;
/*     */       } 
/*     */     }
/* 484 */     if (!isMasterConnection() && this.masterConnection != null) {
/* 485 */       syncSessionState(this.currentConnection, this.masterConnection, false);
/* 486 */       this.currentConnection = this.masterConnection;
/*     */     } 
/* 488 */     return true;
/*     */   }
/*     */   
/*     */   private synchronized boolean switchToSlavesConnection() throws SQLException {
/* 492 */     if (this.slavesConnection == null || this.slavesConnection.isClosed()) {
/*     */       try {
/* 494 */         if (initializeSlavesConnection() == null) {
/* 495 */           return false;
/*     */         }
/* 497 */       } catch (SQLException e) {
/* 498 */         this.currentConnection = null;
/* 499 */         throw e;
/*     */       } 
/*     */     }
/* 502 */     if (!isSlavesConnection() && this.slavesConnection != null) {
/* 503 */       syncSessionState(this.currentConnection, this.slavesConnection, true);
/* 504 */       this.currentConnection = this.slavesConnection;
/*     */     } 
/* 506 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean switchToSlavesConnectionIfNecessary() throws SQLException {
/* 515 */     if (this.currentConnection == null || (isMasterConnection() && (this.readOnly || (this.masterHosts.isEmpty() && this.currentConnection.isClosed()))) || (!isMasterConnection() && this.currentConnection.isClosed()))
/*     */     {
/* 517 */       return switchToSlavesConnection();
/*     */     }
/* 519 */     return false;
/*     */   }
/*     */   
/*     */   public synchronized Connection getCurrentConnection() {
/* 523 */     return (this.currentConnection == null) ? LoadBalancedConnectionProxy.getNullLoadBalancedConnectionInstance() : this.currentConnection;
/*     */   }
/*     */   
/*     */   public long getConnectionGroupId() {
/* 527 */     return this.connectionGroupID;
/*     */   }
/*     */   
/*     */   public synchronized Connection getMasterConnection() {
/* 531 */     return this.masterConnection;
/*     */   }
/*     */   
/*     */   public synchronized void promoteSlaveToMaster(String hostPortPair) throws SQLException {
/* 535 */     this.masterHosts.add(hostPortPair);
/* 536 */     removeSlave(hostPortPair);
/* 537 */     if (this.masterConnection != null) {
/* 538 */       this.masterConnection.addHost(hostPortPair);
/*     */     }
/*     */ 
/*     */     
/* 542 */     if (!this.readOnly && !isMasterConnection()) {
/* 543 */       switchToMasterConnection();
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void removeMasterHost(String hostPortPair) throws SQLException {
/* 548 */     removeMasterHost(hostPortPair, true);
/*     */   }
/*     */   
/*     */   public synchronized void removeMasterHost(String hostPortPair, boolean waitUntilNotInUse) throws SQLException {
/* 552 */     removeMasterHost(hostPortPair, waitUntilNotInUse, false);
/*     */   }
/*     */   
/*     */   public synchronized void removeMasterHost(String hostPortPair, boolean waitUntilNotInUse, boolean isNowSlave) throws SQLException {
/* 556 */     if (isNowSlave) {
/* 557 */       this.slaveHosts.add(hostPortPair);
/* 558 */       resetReadFromMasterWhenNoSlaves();
/*     */     } 
/* 560 */     this.masterHosts.remove(hostPortPair);
/*     */ 
/*     */     
/* 563 */     if (this.masterConnection == null || this.masterConnection.isClosed()) {
/* 564 */       this.masterConnection = null;
/*     */       
/*     */       return;
/*     */     } 
/* 568 */     if (waitUntilNotInUse) {
/* 569 */       this.masterConnection.removeHostWhenNotInUse(hostPortPair);
/*     */     } else {
/* 571 */       this.masterConnection.removeHost(hostPortPair);
/*     */     } 
/*     */ 
/*     */     
/* 575 */     if (this.masterHosts.isEmpty()) {
/* 576 */       this.masterConnection.close();
/* 577 */       this.masterConnection = null;
/*     */ 
/*     */       
/* 580 */       switchToSlavesConnectionIfNecessary();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isHostMaster(String hostPortPair) {
/* 585 */     if (hostPortPair == null) {
/* 586 */       return false;
/*     */     }
/* 588 */     for (String masterHost : this.masterHosts) {
/* 589 */       if (masterHost.equalsIgnoreCase(hostPortPair)) {
/* 590 */         return true;
/*     */       }
/*     */     } 
/* 593 */     return false;
/*     */   }
/*     */   
/*     */   public synchronized Connection getSlavesConnection() {
/* 597 */     return this.slavesConnection;
/*     */   }
/*     */   
/*     */   public synchronized void addSlaveHost(String hostPortPair) throws SQLException {
/* 601 */     if (isHostSlave(hostPortPair)) {
/*     */       return;
/*     */     }
/* 604 */     this.slaveHosts.add(hostPortPair);
/* 605 */     resetReadFromMasterWhenNoSlaves();
/* 606 */     if (this.slavesConnection == null) {
/* 607 */       initializeSlavesConnection();
/* 608 */       switchToSlavesConnectionIfNecessary();
/*     */     } else {
/* 610 */       this.slavesConnection.addHost(hostPortPair);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void removeSlave(String hostPortPair) throws SQLException {
/* 615 */     removeSlave(hostPortPair, true);
/*     */   }
/*     */   
/*     */   public synchronized void removeSlave(String hostPortPair, boolean closeGently) throws SQLException {
/* 619 */     this.slaveHosts.remove(hostPortPair);
/* 620 */     resetReadFromMasterWhenNoSlaves();
/*     */     
/* 622 */     if (this.slavesConnection == null || this.slavesConnection.isClosed()) {
/* 623 */       this.slavesConnection = null;
/*     */       
/*     */       return;
/*     */     } 
/* 627 */     if (closeGently) {
/* 628 */       this.slavesConnection.removeHostWhenNotInUse(hostPortPair);
/*     */     } else {
/* 630 */       this.slavesConnection.removeHost(hostPortPair);
/*     */     } 
/*     */ 
/*     */     
/* 634 */     if (this.slaveHosts.isEmpty()) {
/* 635 */       this.slavesConnection.close();
/* 636 */       this.slavesConnection = null;
/*     */ 
/*     */       
/* 639 */       switchToMasterConnection();
/* 640 */       if (isMasterConnection()) {
/* 641 */         this.currentConnection.setReadOnly(this.readOnly);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isHostSlave(String hostPortPair) {
/* 647 */     if (hostPortPair == null) {
/* 648 */       return false;
/*     */     }
/* 650 */     for (String test : this.slaveHosts) {
/* 651 */       if (test.equalsIgnoreCase(hostPortPair)) {
/* 652 */         return true;
/*     */       }
/*     */     } 
/* 655 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void setReadOnly(boolean readOnly) throws SQLException {
/* 660 */     if (readOnly) {
/* 661 */       if (!isSlavesConnection() || this.currentConnection.isClosed()) {
/* 662 */         boolean switched = true;
/* 663 */         SQLException exceptionCaught = null;
/*     */         try {
/* 665 */           switched = switchToSlavesConnection();
/* 666 */         } catch (SQLException e) {
/* 667 */           switched = false;
/* 668 */           exceptionCaught = e;
/*     */         } 
/* 670 */         if (!switched && this.readFromMasterWhenNoSlaves && switchToMasterConnection()) {
/* 671 */           exceptionCaught = null;
/*     */         }
/* 673 */         if (exceptionCaught != null) {
/* 674 */           throw exceptionCaught;
/*     */         }
/*     */       }
/*     */     
/* 678 */     } else if (!isMasterConnection() || this.currentConnection.isClosed()) {
/* 679 */       boolean switched = true;
/* 680 */       SQLException exceptionCaught = null;
/*     */       try {
/* 682 */         switched = switchToMasterConnection();
/* 683 */       } catch (SQLException e) {
/* 684 */         switched = false;
/* 685 */         exceptionCaught = e;
/*     */       } 
/* 687 */       if (!switched && switchToSlavesConnectionIfNecessary()) {
/* 688 */         exceptionCaught = null;
/*     */       }
/* 690 */       if (exceptionCaught != null) {
/* 691 */         throw exceptionCaught;
/*     */       }
/*     */     } 
/*     */     
/* 695 */     this.readOnly = readOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 701 */     if (this.readFromMasterWhenNoSlaves && isMasterConnection()) {
/* 702 */       this.currentConnection.setReadOnly(this.readOnly);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() throws SQLException {
/* 707 */     return (!isMasterConnection() || this.readOnly);
/*     */   }
/*     */   
/*     */   private void resetReadFromMasterWhenNoSlaves() {
/* 711 */     this.readFromMasterWhenNoSlaves = (this.slaveHosts.isEmpty() || this.readFromMasterWhenNoSlavesOriginal);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ReplicationConnectionProxy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */