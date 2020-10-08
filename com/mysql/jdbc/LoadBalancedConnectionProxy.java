/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoadBalancedConnectionProxy
/*     */   extends MultiHostConnectionProxy
/*     */   implements PingTarget
/*     */ {
/*  56 */   private ConnectionGroup connectionGroup = null;
/*  57 */   private long connectionGroupProxyID = 0L;
/*     */   
/*     */   protected Map<String, ConnectionImpl> liveConnections;
/*     */   private Map<String, Integer> hostsToListIndexMap;
/*     */   private Map<ConnectionImpl, String> connectionsToHostsMap;
/*  62 */   private long totalPhysicalConnections = 0L;
/*     */   
/*     */   private long[] responseTimes;
/*     */   private int retriesAllDown;
/*     */   private BalanceStrategy balancer;
/*  67 */   private int autoCommitSwapThreshold = 0;
/*     */   
/*     */   public static final String BLACKLIST_TIMEOUT_PROPERTY_KEY = "loadBalanceBlacklistTimeout";
/*  70 */   private int globalBlacklistTimeout = 0;
/*  71 */   private static Map<String, Long> globalBlacklist = new HashMap<String, Long>();
/*     */   public static final String HOST_REMOVAL_GRACE_PERIOD_PROPERTY_KEY = "loadBalanceHostRemovalGracePeriod";
/*  73 */   private int hostRemovalGracePeriod = 0;
/*     */   
/*  75 */   private Set<String> hostsToRemove = new HashSet<String>();
/*     */   
/*     */   private boolean inTransaction = false;
/*  78 */   private long transactionStartTime = 0L;
/*  79 */   private long transactionCount = 0L;
/*     */   
/*     */   private LoadBalanceExceptionChecker exceptionChecker;
/*     */   
/*     */   private static Constructor<?> JDBC_4_LB_CONNECTION_CTOR;
/*     */   private static Class<?>[] INTERFACES_TO_PROXY;
/*     */   
/*     */   static {
/*  87 */     if (Util.isJdbc4()) {
/*     */       try {
/*  89 */         JDBC_4_LB_CONNECTION_CTOR = Class.forName("com.mysql.jdbc.JDBC4LoadBalancedMySQLConnection").getConstructor(new Class[] { LoadBalancedConnectionProxy.class });
/*     */         
/*  91 */         INTERFACES_TO_PROXY = new Class[] { LoadBalancedConnection.class, Class.forName("com.mysql.jdbc.JDBC4MySQLConnection") };
/*  92 */       } catch (SecurityException e) {
/*  93 */         throw new RuntimeException(e);
/*  94 */       } catch (NoSuchMethodException e) {
/*  95 */         throw new RuntimeException(e);
/*  96 */       } catch (ClassNotFoundException e) {
/*  97 */         throw new RuntimeException(e);
/*     */       } 
/*     */     } else {
/* 100 */       INTERFACES_TO_PROXY = new Class[] { LoadBalancedConnection.class };
/*     */     } 
/*     */   }
/*     */   
/*     */   public static LoadBalancedConnection createProxyInstance(List<String> hosts, Properties props) throws SQLException {
/* 105 */     LoadBalancedConnectionProxy connProxy = new LoadBalancedConnectionProxy(hosts, props);
/*     */     
/* 107 */     return (LoadBalancedConnection)Proxy.newProxyInstance(LoadBalancedConnection.class.getClassLoader(), INTERFACES_TO_PROXY, connProxy);
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
/*     */   private LoadBalancedConnectionProxy(List<String> hosts, Properties props) throws SQLException {
/* 122 */     String group = props.getProperty("loadBalanceConnectionGroup", null);
/* 123 */     boolean enableJMX = false;
/* 124 */     String enableJMXAsString = props.getProperty("loadBalanceEnableJMX", "false");
/*     */     try {
/* 126 */       enableJMX = Boolean.parseBoolean(enableJMXAsString);
/* 127 */     } catch (Exception e) {
/* 128 */       throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.badValueForLoadBalanceEnableJMX", new Object[] { enableJMXAsString }), "S1009", null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 133 */     if (group != null) {
/* 134 */       this.connectionGroup = ConnectionGroupManager.getConnectionGroupInstance(group);
/* 135 */       if (enableJMX) {
/* 136 */         ConnectionGroupManager.registerJmx();
/*     */       }
/* 138 */       this.connectionGroupProxyID = this.connectionGroup.registerConnectionProxy(this, hosts);
/* 139 */       hosts = new ArrayList<String>(this.connectionGroup.getInitialHosts());
/*     */     } 
/*     */ 
/*     */     
/* 143 */     int numHosts = initializeHostsSpecs(hosts, props);
/*     */     
/* 145 */     this.liveConnections = new HashMap<String, ConnectionImpl>(numHosts);
/* 146 */     this.hostsToListIndexMap = new HashMap<String, Integer>(numHosts);
/* 147 */     for (int i = 0; i < numHosts; i++) {
/* 148 */       this.hostsToListIndexMap.put(this.hostList.get(i), Integer.valueOf(i));
/*     */     }
/* 150 */     this.connectionsToHostsMap = new HashMap<ConnectionImpl, String>(numHosts);
/* 151 */     this.responseTimes = new long[numHosts];
/*     */     
/* 153 */     String retriesAllDownAsString = this.localProps.getProperty("retriesAllDown", "120");
/*     */     try {
/* 155 */       this.retriesAllDown = Integer.parseInt(retriesAllDownAsString);
/* 156 */     } catch (NumberFormatException nfe) {
/* 157 */       throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.badValueForRetriesAllDown", new Object[] { retriesAllDownAsString }), "S1009", null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 162 */     String blacklistTimeoutAsString = this.localProps.getProperty("loadBalanceBlacklistTimeout", "0");
/*     */     try {
/* 164 */       this.globalBlacklistTimeout = Integer.parseInt(blacklistTimeoutAsString);
/* 165 */     } catch (NumberFormatException nfe) {
/* 166 */       throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.badValueForLoadBalanceBlacklistTimeout", new Object[] { blacklistTimeoutAsString }), "S1009", null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 171 */     String hostRemovalGracePeriodAsString = this.localProps.getProperty("loadBalanceHostRemovalGracePeriod", "15000");
/*     */     try {
/* 173 */       this.hostRemovalGracePeriod = Integer.parseInt(hostRemovalGracePeriodAsString);
/* 174 */     } catch (NumberFormatException nfe) {
/* 175 */       throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.badValueForLoadBalanceHostRemovalGracePeriod", new Object[] { hostRemovalGracePeriodAsString }), "S1009", null);
/*     */     } 
/*     */ 
/*     */     
/* 179 */     String strategy = this.localProps.getProperty("loadBalanceStrategy", "random");
/* 180 */     if ("random".equals(strategy)) {
/* 181 */       this.balancer = (BalanceStrategy)Util.loadExtensions(null, props, RandomBalanceStrategy.class.getName(), "InvalidLoadBalanceStrategy", null).get(0);
/*     */     }
/* 183 */     else if ("bestResponseTime".equals(strategy)) {
/* 184 */       this.balancer = (BalanceStrategy)Util.loadExtensions(null, props, BestResponseTimeBalanceStrategy.class.getName(), "InvalidLoadBalanceStrategy", null).get(0);
/*     */     }
/* 186 */     else if ("serverAffinity".equals(strategy)) {
/* 187 */       this.balancer = (BalanceStrategy)Util.loadExtensions(null, props, ServerAffinityStrategy.class.getName(), "InvalidLoadBalanceStrategy", null).get(0);
/*     */     } else {
/*     */       
/* 190 */       this.balancer = (BalanceStrategy)Util.loadExtensions(null, props, strategy, "InvalidLoadBalanceStrategy", null).get(0);
/*     */     } 
/*     */     
/* 193 */     String autoCommitSwapThresholdAsString = props.getProperty("loadBalanceAutoCommitStatementThreshold", "0");
/*     */     try {
/* 195 */       this.autoCommitSwapThreshold = Integer.parseInt(autoCommitSwapThresholdAsString);
/* 196 */     } catch (NumberFormatException nfe) {
/* 197 */       throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.badValueForLoadBalanceAutoCommitStatementThreshold", new Object[] { autoCommitSwapThresholdAsString }), "S1009", null);
/*     */     } 
/*     */ 
/*     */     
/* 201 */     String autoCommitSwapRegex = props.getProperty("loadBalanceAutoCommitStatementRegex", "");
/* 202 */     if (!"".equals(autoCommitSwapRegex)) {
/*     */       try {
/* 204 */         "".matches(autoCommitSwapRegex);
/* 205 */       } catch (Exception e) {
/* 206 */         throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.badValueForLoadBalanceAutoCommitStatementRegex", new Object[] { autoCommitSwapRegex }), "S1009", null);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 212 */     if (this.autoCommitSwapThreshold > 0) {
/* 213 */       String statementInterceptors = this.localProps.getProperty("statementInterceptors");
/* 214 */       if (statementInterceptors == null) {
/* 215 */         this.localProps.setProperty("statementInterceptors", "com.mysql.jdbc.LoadBalancedAutoCommitInterceptor");
/* 216 */       } else if (statementInterceptors.length() > 0) {
/* 217 */         this.localProps.setProperty("statementInterceptors", statementInterceptors + ",com.mysql.jdbc.LoadBalancedAutoCommitInterceptor");
/*     */       } 
/* 219 */       props.setProperty("statementInterceptors", this.localProps.getProperty("statementInterceptors"));
/*     */     } 
/*     */ 
/*     */     
/* 223 */     this.balancer.init(null, props);
/*     */     
/* 225 */     String lbExceptionChecker = this.localProps.getProperty("loadBalanceExceptionChecker", "com.mysql.jdbc.StandardLoadBalanceExceptionChecker");
/* 226 */     this.exceptionChecker = (LoadBalanceExceptionChecker)Util.loadExtensions(null, props, lbExceptionChecker, "InvalidLoadBalanceExceptionChecker", null).get(0);
/*     */ 
/*     */     
/* 229 */     pickNewConnection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MySQLConnection getNewWrapperForThisAsConnection() throws SQLException {
/* 240 */     if (Util.isJdbc4() || JDBC_4_LB_CONNECTION_CTOR != null) {
/* 241 */       return (MySQLConnection)Util.handleNewInstance(JDBC_4_LB_CONNECTION_CTOR, new Object[] { this }, null);
/*     */     }
/* 243 */     return new LoadBalancedMySQLConnection(this);
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
/* 254 */     for (MySQLConnection c : this.liveConnections.values()) {
/* 255 */       c.setProxy(proxyConn);
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
/* 267 */     return (t instanceof SQLException && this.exceptionChecker.shouldExceptionTriggerFailover((SQLException)t));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isMasterConnection() {
/* 275 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   synchronized void invalidateConnection(MySQLConnection conn) throws SQLException {
/* 286 */     super.invalidateConnection(conn);
/*     */ 
/*     */     
/* 289 */     if (isGlobalBlacklistEnabled()) {
/* 290 */       addToGlobalBlacklist(this.connectionsToHostsMap.get(conn));
/*     */     }
/*     */ 
/*     */     
/* 294 */     this.liveConnections.remove(this.connectionsToHostsMap.get(conn));
/* 295 */     Object mappedHost = this.connectionsToHostsMap.remove(conn);
/* 296 */     if (mappedHost != null && this.hostsToListIndexMap.containsKey(mappedHost)) {
/* 297 */       int hostIndex = ((Integer)this.hostsToListIndexMap.get(mappedHost)).intValue();
/*     */       
/* 299 */       synchronized (this.responseTimes) {
/* 300 */         this.responseTimes[hostIndex] = 0L;
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
/*     */   synchronized void pickNewConnection() throws SQLException {
/* 312 */     if (this.isClosed && this.closedExplicitly) {
/*     */       return;
/*     */     }
/*     */     
/* 316 */     if (this.currentConnection == null) {
/* 317 */       this.currentConnection = this.balancer.pickConnection(this, Collections.unmodifiableList(this.hostList), Collections.unmodifiableMap(this.liveConnections), (long[])this.responseTimes.clone(), this.retriesAllDown);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 322 */     if (this.currentConnection.isClosed()) {
/* 323 */       invalidateCurrentConnection();
/*     */     }
/*     */     
/* 326 */     int pingTimeout = this.currentConnection.getLoadBalancePingTimeout();
/* 327 */     boolean pingBeforeReturn = this.currentConnection.getLoadBalanceValidateConnectionOnSwapServer();
/*     */     
/* 329 */     for (int hostsTried = 0, hostsToTry = this.hostList.size(); hostsTried < hostsToTry; hostsTried++) {
/* 330 */       ConnectionImpl newConn = null;
/*     */       try {
/* 332 */         newConn = this.balancer.pickConnection(this, Collections.unmodifiableList(this.hostList), Collections.unmodifiableMap(this.liveConnections), (long[])this.responseTimes.clone(), this.retriesAllDown);
/*     */ 
/*     */         
/* 335 */         if (this.currentConnection != null) {
/* 336 */           if (pingBeforeReturn) {
/* 337 */             if (pingTimeout == 0) {
/* 338 */               newConn.ping();
/*     */             } else {
/* 340 */               newConn.pingInternal(true, pingTimeout);
/*     */             } 
/*     */           }
/*     */           
/* 344 */           syncSessionState(this.currentConnection, newConn);
/*     */         } 
/*     */         
/* 347 */         this.currentConnection = newConn;
/*     */         
/*     */         return;
/* 350 */       } catch (SQLException e) {
/* 351 */         if (shouldExceptionTriggerConnectionSwitch(e) && newConn != null)
/*     */         {
/* 353 */           invalidateConnection(newConn);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 359 */     this.isClosed = true;
/* 360 */     this.closedReason = "Connection closed after inability to pick valid new connection during load-balance.";
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
/*     */   public synchronized ConnectionImpl createConnectionForHost(String hostPortSpec) throws SQLException {
/* 372 */     ConnectionImpl conn = super.createConnectionForHost(hostPortSpec);
/*     */     
/* 374 */     this.liveConnections.put(hostPortSpec, conn);
/* 375 */     this.connectionsToHostsMap.put(conn, hostPortSpec);
/*     */     
/* 377 */     this.totalPhysicalConnections++;
/*     */     
/* 379 */     for (StatementInterceptorV2 stmtInterceptor : conn.getStatementInterceptorsInstances()) {
/* 380 */       if (stmtInterceptor instanceof LoadBalancedAutoCommitInterceptor) {
/* 381 */         ((LoadBalancedAutoCommitInterceptor)stmtInterceptor).resumeCounters();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 386 */     return conn;
/*     */   }
/*     */ 
/*     */   
/*     */   void syncSessionState(Connection source, Connection target, boolean readOnly) throws SQLException {
/* 391 */     LoadBalancedAutoCommitInterceptor lbAutoCommitStmtInterceptor = null;
/* 392 */     for (StatementInterceptorV2 stmtInterceptor : ((MySQLConnection)target).getStatementInterceptorsInstances()) {
/* 393 */       if (stmtInterceptor instanceof LoadBalancedAutoCommitInterceptor) {
/* 394 */         lbAutoCommitStmtInterceptor = (LoadBalancedAutoCommitInterceptor)stmtInterceptor;
/* 395 */         lbAutoCommitStmtInterceptor.pauseCounters();
/*     */         break;
/*     */       } 
/*     */     } 
/* 399 */     super.syncSessionState(source, target, readOnly);
/* 400 */     if (lbAutoCommitStmtInterceptor != null) {
/* 401 */       lbAutoCommitStmtInterceptor.resumeCounters();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void closeAllConnections() {
/* 410 */     for (MySQLConnection c : this.liveConnections.values()) {
/*     */       try {
/* 412 */         c.close();
/* 413 */       } catch (SQLException e) {}
/*     */     } 
/*     */ 
/*     */     
/* 417 */     if (!this.isClosed) {
/* 418 */       this.balancer.destroy();
/* 419 */       if (this.connectionGroup != null) {
/* 420 */         this.connectionGroup.closeConnectionProxy(this);
/*     */       }
/*     */     } 
/*     */     
/* 424 */     this.liveConnections.clear();
/* 425 */     this.connectionsToHostsMap.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   synchronized void doClose() {
/* 433 */     closeAllConnections();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   synchronized void doAbortInternal() {
/* 442 */     for (MySQLConnection c : this.liveConnections.values()) {
/*     */       try {
/* 444 */         c.abortInternal();
/* 445 */       } catch (SQLException e) {}
/*     */     } 
/*     */ 
/*     */     
/* 449 */     if (!this.isClosed) {
/* 450 */       this.balancer.destroy();
/* 451 */       if (this.connectionGroup != null) {
/* 452 */         this.connectionGroup.closeConnectionProxy(this);
/*     */       }
/*     */     } 
/*     */     
/* 456 */     this.liveConnections.clear();
/* 457 */     this.connectionsToHostsMap.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   synchronized void doAbort(Executor executor) {
/* 466 */     for (MySQLConnection c : this.liveConnections.values()) {
/*     */       try {
/* 468 */         c.abort(executor);
/* 469 */       } catch (SQLException e) {}
/*     */     } 
/*     */ 
/*     */     
/* 473 */     if (!this.isClosed) {
/* 474 */       this.balancer.destroy();
/* 475 */       if (this.connectionGroup != null) {
/* 476 */         this.connectionGroup.closeConnectionProxy(this);
/*     */       }
/*     */     } 
/*     */     
/* 480 */     this.liveConnections.clear();
/* 481 */     this.connectionsToHostsMap.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Object invokeMore(Object proxy, Method method, Object[] args) throws Throwable {
/* 491 */     String methodName = method.getName();
/*     */     
/* 493 */     if (this.isClosed && !allowedOnClosedConnection(method) && (method.getExceptionTypes()).length > 0) {
/* 494 */       if (this.autoReconnect && !this.closedExplicitly) {
/*     */         
/* 496 */         this.currentConnection = null;
/* 497 */         pickNewConnection();
/* 498 */         this.isClosed = false;
/* 499 */         this.closedReason = null;
/*     */       } else {
/* 501 */         String reason = "No operations allowed after connection closed.";
/* 502 */         if (this.closedReason != null) {
/* 503 */           reason = reason + " " + this.closedReason;
/*     */         }
/* 505 */         throw SQLError.createSQLException(reason, "08003", null);
/*     */       } 
/*     */     }
/*     */     
/* 509 */     if (!this.inTransaction) {
/* 510 */       this.inTransaction = true;
/* 511 */       this.transactionStartTime = System.nanoTime();
/* 512 */       this.transactionCount++;
/*     */     } 
/*     */     
/* 515 */     Object result = null;
/*     */     
/*     */     try {
/* 518 */       result = method.invoke(this.thisAsConnection, args);
/*     */       
/* 520 */       if (result != null) {
/* 521 */         if (result instanceof Statement) {
/* 522 */           ((Statement)result).setPingTarget(this);
/*     */         }
/* 524 */         result = proxyIfReturnTypeIsJdbcInterface(method.getReturnType(), result);
/*     */       }
/*     */     
/* 527 */     } catch (InvocationTargetException e) {
/* 528 */       dealWithInvocationException(e);
/*     */     } finally {
/*     */       
/* 531 */       if ("commit".equals(methodName) || "rollback".equals(methodName)) {
/* 532 */         this.inTransaction = false;
/*     */ 
/*     */         
/* 535 */         String host = this.connectionsToHostsMap.get(this.currentConnection);
/*     */         
/* 537 */         if (host != null) {
/* 538 */           synchronized (this.responseTimes) {
/* 539 */             Integer hostIndex = this.hostsToListIndexMap.get(host);
/*     */             
/* 541 */             if (hostIndex != null && hostIndex.intValue() < this.responseTimes.length) {
/* 542 */               this.responseTimes[hostIndex.intValue()] = System.nanoTime() - this.transactionStartTime;
/*     */             }
/*     */           } 
/*     */         }
/* 546 */         pickNewConnection();
/*     */       } 
/*     */     } 
/*     */     
/* 550 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void doPing() throws SQLException {
/* 557 */     SQLException se = null;
/* 558 */     boolean foundHost = false;
/* 559 */     int pingTimeout = this.currentConnection.getLoadBalancePingTimeout();
/*     */     
/* 561 */     for (Iterator<String> i = this.hostList.iterator(); i.hasNext(); ) {
/* 562 */       String host = i.next();
/* 563 */       ConnectionImpl conn = this.liveConnections.get(host);
/* 564 */       if (conn == null) {
/*     */         continue;
/*     */       }
/*     */       try {
/* 568 */         if (pingTimeout == 0) {
/* 569 */           conn.ping();
/*     */         } else {
/* 571 */           conn.pingInternal(true, pingTimeout);
/*     */         } 
/* 573 */         foundHost = true;
/* 574 */       } catch (SQLException e) {
/*     */         
/* 576 */         if (host.equals(this.connectionsToHostsMap.get(this.currentConnection))) {
/*     */           
/* 578 */           closeAllConnections();
/* 579 */           this.isClosed = true;
/* 580 */           this.closedReason = "Connection closed because ping of current connection failed.";
/* 581 */           throw e;
/*     */         } 
/*     */ 
/*     */         
/* 585 */         if (e.getMessage().equals(Messages.getString("Connection.exceededConnectionLifetime"))) {
/*     */           
/* 587 */           if (se == null) {
/* 588 */             se = e;
/*     */           }
/*     */         } else {
/*     */           
/* 592 */           se = e;
/* 593 */           if (isGlobalBlacklistEnabled()) {
/* 594 */             addToGlobalBlacklist(host);
/*     */           }
/*     */         } 
/*     */         
/* 598 */         this.liveConnections.remove(this.connectionsToHostsMap.get(conn));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 603 */     if (!foundHost) {
/* 604 */       closeAllConnections();
/* 605 */       this.isClosed = true;
/* 606 */       this.closedReason = "Connection closed due to inability to ping any active connections.";
/*     */       
/* 608 */       if (se != null) {
/* 609 */         throw se;
/*     */       }
/*     */       
/* 612 */       ((ConnectionImpl)this.currentConnection).throwConnectionClosedException();
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
/*     */   public void addToGlobalBlacklist(String host, long timeout) {
/* 625 */     if (isGlobalBlacklistEnabled()) {
/* 626 */       synchronized (globalBlacklist) {
/* 627 */         globalBlacklist.put(host, Long.valueOf(timeout));
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
/*     */   public void addToGlobalBlacklist(String host) {
/* 639 */     addToGlobalBlacklist(host, System.currentTimeMillis() + this.globalBlacklistTimeout);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGlobalBlacklistEnabled() {
/* 646 */     return (this.globalBlacklistTimeout > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Map<String, Long> getGlobalBlacklist() {
/* 656 */     if (!isGlobalBlacklistEnabled()) {
/* 657 */       if (this.hostsToRemove.isEmpty()) {
/* 658 */         return new HashMap<String, Long>(1);
/*     */       }
/* 660 */       HashMap<String, Long> fakedBlacklist = new HashMap<String, Long>();
/* 661 */       for (String h : this.hostsToRemove) {
/* 662 */         fakedBlacklist.put(h, Long.valueOf(System.currentTimeMillis() + 5000L));
/*     */       }
/* 664 */       return fakedBlacklist;
/*     */     } 
/*     */ 
/*     */     
/* 668 */     Map<String, Long> blacklistClone = new HashMap<String, Long>(globalBlacklist.size());
/*     */     
/* 670 */     synchronized (globalBlacklist) {
/* 671 */       blacklistClone.putAll(globalBlacklist);
/*     */     } 
/* 673 */     Set<String> keys = blacklistClone.keySet();
/*     */ 
/*     */     
/* 676 */     keys.retainAll(this.hostList);
/*     */ 
/*     */     
/* 679 */     for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
/* 680 */       String host = i.next();
/*     */       
/* 682 */       Long timeout = globalBlacklist.get(host);
/* 683 */       if (timeout != null && timeout.longValue() < System.currentTimeMillis()) {
/*     */         
/* 685 */         synchronized (globalBlacklist) {
/* 686 */           globalBlacklist.remove(host);
/*     */         } 
/* 688 */         i.remove();
/*     */       } 
/*     */     } 
/*     */     
/* 692 */     if (keys.size() == this.hostList.size())
/*     */     {
/*     */       
/* 695 */       return new HashMap<String, Long>(1);
/*     */     }
/*     */     
/* 698 */     return blacklistClone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeHostWhenNotInUse(String hostPortPair) throws SQLException {
/* 709 */     if (this.hostRemovalGracePeriod <= 0) {
/* 710 */       removeHost(hostPortPair);
/*     */       
/*     */       return;
/*     */     } 
/* 714 */     int timeBetweenChecks = (this.hostRemovalGracePeriod > 1000) ? 1000 : this.hostRemovalGracePeriod;
/*     */     
/* 716 */     synchronized (this) {
/* 717 */       addToGlobalBlacklist(hostPortPair, System.currentTimeMillis() + this.hostRemovalGracePeriod + timeBetweenChecks);
/*     */       
/* 719 */       long cur = System.currentTimeMillis();
/*     */       
/* 721 */       while (System.currentTimeMillis() < cur + this.hostRemovalGracePeriod) {
/* 722 */         this.hostsToRemove.add(hostPortPair);
/*     */         
/* 724 */         if (!hostPortPair.equals(this.currentConnection.getHostPortPair())) {
/* 725 */           removeHost(hostPortPair);
/*     */           
/*     */           return;
/*     */         } 
/*     */         try {
/* 730 */           Thread.sleep(timeBetweenChecks);
/* 731 */         } catch (InterruptedException e) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 737 */     removeHost(hostPortPair);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void removeHost(String hostPortPair) throws SQLException {
/* 748 */     if (this.connectionGroup != null && 
/* 749 */       this.connectionGroup.getInitialHosts().size() == 1 && this.connectionGroup.getInitialHosts().contains(hostPortPair)) {
/* 750 */       throw SQLError.createSQLException("Cannot remove only configured host.", null);
/*     */     }
/*     */ 
/*     */     
/* 754 */     this.hostsToRemove.add(hostPortPair);
/*     */     
/* 756 */     this.connectionsToHostsMap.remove(this.liveConnections.remove(hostPortPair));
/* 757 */     if (this.hostsToListIndexMap.remove(hostPortPair) != null) {
/* 758 */       long[] newResponseTimes = new long[this.responseTimes.length - 1];
/* 759 */       int newIdx = 0;
/* 760 */       for (String h : this.hostList) {
/* 761 */         if (!this.hostsToRemove.contains(h)) {
/* 762 */           Integer idx = this.hostsToListIndexMap.get(h);
/* 763 */           if (idx != null && idx.intValue() < this.responseTimes.length) {
/* 764 */             newResponseTimes[newIdx] = this.responseTimes[idx.intValue()];
/*     */           }
/* 766 */           this.hostsToListIndexMap.put(h, Integer.valueOf(newIdx++));
/*     */         } 
/*     */       } 
/* 769 */       this.responseTimes = newResponseTimes;
/*     */     } 
/*     */     
/* 772 */     if (hostPortPair.equals(this.currentConnection.getHostPortPair())) {
/* 773 */       invalidateConnection(this.currentConnection);
/* 774 */       pickNewConnection();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean addHost(String hostPortPair) {
/* 785 */     if (this.hostsToListIndexMap.containsKey(hostPortPair)) {
/* 786 */       return false;
/*     */     }
/*     */     
/* 789 */     long[] newResponseTimes = new long[this.responseTimes.length + 1];
/* 790 */     System.arraycopy(this.responseTimes, 0, newResponseTimes, 0, this.responseTimes.length);
/*     */     
/* 792 */     this.responseTimes = newResponseTimes;
/* 793 */     if (!this.hostList.contains(hostPortPair)) {
/* 794 */       this.hostList.add(hostPortPair);
/*     */     }
/* 796 */     this.hostsToListIndexMap.put(hostPortPair, Integer.valueOf(this.responseTimes.length - 1));
/* 797 */     this.hostsToRemove.remove(hostPortPair);
/*     */     
/* 799 */     return true;
/*     */   }
/*     */   
/*     */   public synchronized boolean inTransaction() {
/* 803 */     return this.inTransaction;
/*     */   }
/*     */   
/*     */   public synchronized long getTransactionCount() {
/* 807 */     return this.transactionCount;
/*     */   }
/*     */   
/*     */   public synchronized long getActivePhysicalConnectionCount() {
/* 811 */     return this.liveConnections.size();
/*     */   }
/*     */   
/*     */   public synchronized long getTotalPhysicalConnectionCount() {
/* 815 */     return this.totalPhysicalConnections;
/*     */   }
/*     */   
/*     */   public synchronized long getConnectionGroupProxyID() {
/* 819 */     return this.connectionGroupProxyID;
/*     */   }
/*     */   
/*     */   public synchronized String getCurrentActiveHost() {
/* 823 */     MySQLConnection c = this.currentConnection;
/* 824 */     if (c != null) {
/* 825 */       Object o = this.connectionsToHostsMap.get(c);
/* 826 */       if (o != null) {
/* 827 */         return o.toString();
/*     */       }
/*     */     } 
/* 830 */     return null;
/*     */   }
/*     */   
/*     */   public synchronized long getCurrentTransactionDuration() {
/* 834 */     if (this.inTransaction && this.transactionStartTime > 0L) {
/* 835 */       return System.nanoTime() - this.transactionStartTime;
/*     */     }
/* 837 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class NullLoadBalancedConnectionProxy
/*     */     implements InvocationHandler
/*     */   {
/*     */     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/* 849 */       SQLException exceptionToThrow = SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.unusableConnection"), "25000", 1000001, true, (ExceptionInterceptor)null);
/*     */       
/* 851 */       Class<?>[] declaredException = method.getExceptionTypes();
/* 852 */       for (Class<?> declEx : declaredException) {
/* 853 */         if (declEx.isAssignableFrom(exceptionToThrow.getClass())) {
/* 854 */           throw exceptionToThrow;
/*     */         }
/*     */       } 
/* 857 */       throw new IllegalStateException(exceptionToThrow.getMessage(), exceptionToThrow);
/*     */     }
/*     */   }
/*     */   
/* 861 */   private static LoadBalancedConnection nullLBConnectionInstance = null;
/*     */   
/*     */   static synchronized LoadBalancedConnection getNullLoadBalancedConnectionInstance() {
/* 864 */     if (nullLBConnectionInstance == null) {
/* 865 */       nullLBConnectionInstance = (LoadBalancedConnection)Proxy.newProxyInstance(LoadBalancedConnection.class.getClassLoader(), INTERFACES_TO_PROXY, new NullLoadBalancedConnectionProxy());
/*     */     }
/*     */     
/* 868 */     return nullLBConnectionInstance;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\LoadBalancedConnectionProxy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */