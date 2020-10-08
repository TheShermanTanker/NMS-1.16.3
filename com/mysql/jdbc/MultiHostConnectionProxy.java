/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.sql.SQLException;
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
/*     */ 
/*     */ 
/*     */ public abstract class MultiHostConnectionProxy
/*     */   implements InvocationHandler
/*     */ {
/*     */   private static final String METHOD_GET_MULTI_HOST_SAFE_PROXY = "getMultiHostSafeProxy";
/*     */   private static final String METHOD_EQUALS = "equals";
/*     */   private static final String METHOD_HASH_CODE = "hashCode";
/*     */   private static final String METHOD_CLOSE = "close";
/*     */   private static final String METHOD_ABORT_INTERNAL = "abortInternal";
/*     */   private static final String METHOD_ABORT = "abort";
/*     */   private static final String METHOD_IS_CLOSED = "isClosed";
/*     */   private static final String METHOD_GET_AUTO_COMMIT = "getAutoCommit";
/*     */   private static final String METHOD_GET_CATALOG = "getCatalog";
/*     */   private static final String METHOD_GET_TRANSACTION_ISOLATION = "getTransactionIsolation";
/*     */   private static final String METHOD_GET_SESSION_MAX_ROWS = "getSessionMaxRows";
/*     */   List<String> hostList;
/*     */   Properties localProps;
/*     */   boolean autoReconnect = false;
/*  58 */   MySQLConnection thisAsConnection = null;
/*  59 */   MySQLConnection proxyConnection = null;
/*     */   
/*  61 */   MySQLConnection currentConnection = null;
/*     */   
/*     */   boolean isClosed = false;
/*     */   boolean closedExplicitly = false;
/*  65 */   String closedReason = null;
/*     */ 
/*     */ 
/*     */   
/*  69 */   protected Throwable lastExceptionDealtWith = null;
/*     */   
/*     */   private static Constructor<?> JDBC_4_MS_CONNECTION_CTOR;
/*     */   
/*     */   static {
/*  74 */     if (Util.isJdbc4()) {
/*     */       try {
/*  76 */         JDBC_4_MS_CONNECTION_CTOR = Class.forName("com.mysql.jdbc.JDBC4MultiHostMySQLConnection").getConstructor(new Class[] { MultiHostConnectionProxy.class });
/*     */       }
/*  78 */       catch (SecurityException e) {
/*  79 */         throw new RuntimeException(e);
/*  80 */       } catch (NoSuchMethodException e) {
/*  81 */         throw new RuntimeException(e);
/*  82 */       } catch (ClassNotFoundException e) {
/*  83 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class JdbcInterfaceProxy
/*     */     implements InvocationHandler
/*     */   {
/*  92 */     Object invokeOn = null;
/*     */     
/*     */     JdbcInterfaceProxy(Object toInvokeOn) {
/*  95 */       this.invokeOn = toInvokeOn;
/*     */     }
/*     */     
/*     */     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/*  99 */       if ("equals".equals(method.getName()))
/*     */       {
/* 101 */         return Boolean.valueOf(args[0].equals(this));
/*     */       }
/*     */       
/* 104 */       synchronized (MultiHostConnectionProxy.this) {
/* 105 */         Object result = null;
/*     */         
/*     */         try {
/* 108 */           result = method.invoke(this.invokeOn, args);
/* 109 */           result = MultiHostConnectionProxy.this.proxyIfReturnTypeIsJdbcInterface(method.getReturnType(), result);
/* 110 */         } catch (InvocationTargetException e) {
/* 111 */           MultiHostConnectionProxy.this.dealWithInvocationException(e);
/*     */         } 
/*     */         
/* 114 */         return result;
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
/*     */   MultiHostConnectionProxy() throws SQLException {
/* 126 */     this.thisAsConnection = getNewWrapperForThisAsConnection();
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
/*     */   MultiHostConnectionProxy(List<String> hosts, Properties props) throws SQLException {
/* 138 */     this();
/* 139 */     initializeHostsSpecs(hosts, props);
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
/*     */   int initializeHostsSpecs(List<String> hosts, Properties props) {
/* 154 */     this.autoReconnect = ("true".equalsIgnoreCase(props.getProperty("autoReconnect")) || "true".equalsIgnoreCase(props.getProperty("autoReconnectForPools")));
/*     */     
/* 156 */     this.hostList = hosts;
/* 157 */     int numHosts = this.hostList.size();
/*     */     
/* 159 */     this.localProps = (Properties)props.clone();
/* 160 */     this.localProps.remove("HOST");
/* 161 */     this.localProps.remove("PORT");
/*     */     
/* 163 */     for (int i = 0; i < numHosts; i++) {
/* 164 */       this.localProps.remove("HOST." + (i + 1));
/* 165 */       this.localProps.remove("PORT." + (i + 1));
/*     */     } 
/*     */     
/* 168 */     this.localProps.remove("NUM_HOSTS");
/*     */     
/* 170 */     return numHosts;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MySQLConnection getNewWrapperForThisAsConnection() throws SQLException {
/* 180 */     if (Util.isJdbc4() || JDBC_4_MS_CONNECTION_CTOR != null) {
/* 181 */       return (MySQLConnection)Util.handleNewInstance(JDBC_4_MS_CONNECTION_CTOR, new Object[] { this }, null);
/*     */     }
/*     */     
/* 184 */     return new MultiHostMySQLConnection(this);
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
/*     */   protected MySQLConnection getProxy() {
/* 196 */     return (this.proxyConnection != null) ? this.proxyConnection : this.thisAsConnection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setProxy(MySQLConnection proxyConn) {
/* 207 */     this.proxyConnection = proxyConn;
/* 208 */     propagateProxyDown(proxyConn);
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
/* 219 */     this.currentConnection.setProxy(proxyConn);
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
/*     */   Object proxyIfReturnTypeIsJdbcInterface(Class<?> returnType, Object toProxy) {
/* 233 */     if (toProxy != null && 
/* 234 */       Util.isJdbcInterface(returnType)) {
/* 235 */       Class<?> toProxyClass = toProxy.getClass();
/* 236 */       return Proxy.newProxyInstance(toProxyClass.getClassLoader(), Util.getImplementedInterfaces(toProxyClass), getNewJdbcInterfaceProxy(toProxy));
/*     */     } 
/*     */     
/* 239 */     return toProxy;
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
/*     */   InvocationHandler getNewJdbcInterfaceProxy(Object toProxy) {
/* 251 */     return new JdbcInterfaceProxy(toProxy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void dealWithInvocationException(InvocationTargetException e) throws SQLException, Throwable, InvocationTargetException {
/* 261 */     Throwable t = e.getTargetException();
/*     */     
/* 263 */     if (t != null) {
/* 264 */       if (this.lastExceptionDealtWith != t && shouldExceptionTriggerConnectionSwitch(t)) {
/* 265 */         invalidateCurrentConnection();
/* 266 */         pickNewConnection();
/* 267 */         this.lastExceptionDealtWith = t;
/*     */       } 
/* 269 */       throw t;
/*     */     } 
/* 271 */     throw e;
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
/*     */   synchronized void invalidateCurrentConnection() throws SQLException {
/* 291 */     invalidateConnection(this.currentConnection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   synchronized void invalidateConnection(MySQLConnection conn) throws SQLException {
/*     */     try {
/* 302 */       if (conn != null && !conn.isClosed()) {
/* 303 */         conn.realClose(true, !conn.getAutoCommit(), true, (Throwable)null);
/*     */       }
/* 305 */     } catch (SQLException e) {}
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
/*     */   synchronized ConnectionImpl createConnectionForHost(String hostPortSpec) throws SQLException {
/* 324 */     Properties connProps = (Properties)this.localProps.clone();
/*     */     
/* 326 */     String[] hostPortPair = NonRegisteringDriver.parseHostPortPair(hostPortSpec);
/* 327 */     String hostName = hostPortPair[0];
/* 328 */     String portNumber = hostPortPair[1];
/* 329 */     String dbName = connProps.getProperty("DBNAME");
/*     */     
/* 331 */     if (hostName == null) {
/* 332 */       throw new SQLException("Could not find a hostname to start a connection to");
/*     */     }
/* 334 */     if (portNumber == null) {
/* 335 */       portNumber = "3306";
/*     */     }
/*     */     
/* 338 */     connProps.setProperty("HOST", hostName);
/* 339 */     connProps.setProperty("PORT", portNumber);
/* 340 */     connProps.setProperty("HOST.1", hostName);
/* 341 */     connProps.setProperty("PORT.1", portNumber);
/* 342 */     connProps.setProperty("NUM_HOSTS", "1");
/* 343 */     connProps.setProperty("roundRobinLoadBalance", "false");
/*     */     
/* 345 */     ConnectionImpl conn = (ConnectionImpl)ConnectionImpl.getInstance(hostName, Integer.parseInt(portNumber), connProps, dbName, "jdbc:mysql://" + hostName + ":" + portNumber + "/");
/*     */ 
/*     */     
/* 348 */     conn.setProxy(getProxy());
/*     */     
/* 350 */     return conn;
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
/*     */   void syncSessionState(Connection source, Connection target) throws SQLException {
/* 362 */     if (source == null || target == null) {
/*     */       return;
/*     */     }
/*     */     
/* 366 */     boolean prevUseLocalSessionState = source.getUseLocalSessionState();
/* 367 */     source.setUseLocalSessionState(true);
/* 368 */     boolean readOnly = source.isReadOnly();
/* 369 */     source.setUseLocalSessionState(prevUseLocalSessionState);
/*     */     
/* 371 */     syncSessionState(source, target, readOnly);
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
/*     */   void syncSessionState(Connection source, Connection target, boolean readOnly) throws SQLException {
/* 385 */     if (target != null) {
/* 386 */       target.setReadOnly(readOnly);
/*     */     }
/*     */     
/* 389 */     if (source == null || target == null) {
/*     */       return;
/*     */     }
/*     */     
/* 393 */     boolean prevUseLocalSessionState = source.getUseLocalSessionState();
/* 394 */     source.setUseLocalSessionState(true);
/*     */     
/* 396 */     target.setAutoCommit(source.getAutoCommit());
/* 397 */     target.setCatalog(source.getCatalog());
/* 398 */     target.setTransactionIsolation(source.getTransactionIsolation());
/* 399 */     target.setSessionMaxRows(source.getSessionMaxRows());
/*     */     
/* 401 */     source.setUseLocalSessionState(prevUseLocalSessionState);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/* 425 */     String methodName = method.getName();
/*     */     
/* 427 */     if ("getMultiHostSafeProxy".equals(methodName)) {
/* 428 */       return this.thisAsConnection;
/*     */     }
/*     */     
/* 431 */     if ("equals".equals(methodName))
/*     */     {
/* 433 */       return Boolean.valueOf(args[0].equals(this));
/*     */     }
/*     */     
/* 436 */     if ("hashCode".equals(methodName)) {
/* 437 */       return Integer.valueOf(hashCode());
/*     */     }
/*     */     
/* 440 */     if ("close".equals(methodName)) {
/* 441 */       doClose();
/* 442 */       this.isClosed = true;
/* 443 */       this.closedReason = "Connection explicitly closed.";
/* 444 */       this.closedExplicitly = true;
/* 445 */       return null;
/*     */     } 
/*     */     
/* 448 */     if ("abortInternal".equals(methodName)) {
/* 449 */       doAbortInternal();
/* 450 */       this.currentConnection.abortInternal();
/* 451 */       this.isClosed = true;
/* 452 */       this.closedReason = "Connection explicitly closed.";
/* 453 */       return null;
/*     */     } 
/*     */     
/* 456 */     if ("abort".equals(methodName) && args.length == 1) {
/* 457 */       doAbort((Executor)args[0]);
/* 458 */       this.isClosed = true;
/* 459 */       this.closedReason = "Connection explicitly closed.";
/* 460 */       return null;
/*     */     } 
/*     */     
/* 463 */     if ("isClosed".equals(methodName)) {
/* 464 */       return Boolean.valueOf(this.isClosed);
/*     */     }
/*     */     
/*     */     try {
/* 468 */       return invokeMore(proxy, method, args);
/* 469 */     } catch (InvocationTargetException e) {
/* 470 */       throw (e.getCause() != null) ? e.getCause() : e;
/* 471 */     } catch (Exception e) {
/*     */       
/* 473 */       Class<?>[] declaredException = method.getExceptionTypes();
/* 474 */       for (Class<?> declEx : declaredException) {
/* 475 */         if (declEx.isAssignableFrom(e.getClass())) {
/* 476 */           throw e;
/*     */         }
/*     */       } 
/* 479 */       throw new IllegalStateException(e.getMessage(), e);
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
/*     */   protected boolean allowedOnClosedConnection(Method method) {
/* 492 */     String methodName = method.getName();
/*     */     
/* 494 */     return (methodName.equals("getAutoCommit") || methodName.equals("getCatalog") || methodName.equals("getTransactionIsolation") || methodName.equals("getSessionMaxRows"));
/*     */   }
/*     */   
/*     */   abstract boolean shouldExceptionTriggerConnectionSwitch(Throwable paramThrowable);
/*     */   
/*     */   abstract boolean isMasterConnection();
/*     */   
/*     */   abstract void pickNewConnection() throws SQLException;
/*     */   
/*     */   abstract void doClose() throws SQLException;
/*     */   
/*     */   abstract void doAbortInternal() throws SQLException;
/*     */   
/*     */   abstract void doAbort(Executor paramExecutor) throws SQLException;
/*     */   
/*     */   abstract Object invokeMore(Object paramObject, Method paramMethod, Object[] paramArrayOfObject) throws Throwable;
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\MultiHostConnectionProxy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */