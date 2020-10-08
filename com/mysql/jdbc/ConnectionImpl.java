/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import com.mysql.jdbc.log.Log;
/*      */ import com.mysql.jdbc.log.LogFactory;
/*      */ import com.mysql.jdbc.log.NullLogger;
/*      */ import com.mysql.jdbc.profiler.ProfilerEventHandler;
/*      */ import com.mysql.jdbc.util.LRUCache;
/*      */ import java.io.IOException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationHandler;
/*      */ import java.lang.reflect.Method;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.CharBuffer;
/*      */ import java.nio.charset.Charset;
/*      */ import java.nio.charset.CharsetEncoder;
/*      */ import java.nio.charset.UnsupportedCharsetException;
/*      */ import java.sql.Blob;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLPermission;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Savepoint;
/*      */ import java.sql.Statement;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.Random;
/*      */ import java.util.Stack;
/*      */ import java.util.TimeZone;
/*      */ import java.util.Timer;
/*      */ import java.util.concurrent.CopyOnWriteArrayList;
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
/*      */ public class ConnectionImpl
/*      */   extends ConnectionPropertiesImpl
/*      */   implements MySQLConnection
/*      */ {
/*      */   private static final long serialVersionUID = 2877471301981509474L;
/*   83 */   private static final SQLPermission SET_NETWORK_TIMEOUT_PERM = new SQLPermission("setNetworkTimeout");
/*      */   
/*   85 */   private static final SQLPermission ABORT_PERM = new SQLPermission("abort");
/*      */   
/*      */   public static final String JDBC_LOCAL_CHARACTER_SET_RESULTS = "jdbc.local.character_set_results";
/*      */   
/*      */   public String getHost() {
/*   90 */     return this.host;
/*      */   }
/*      */   
/*      */   public String getHostPortPair() {
/*   94 */     return (this.hostPortPair != null) ? this.hostPortPair : (this.host + ":" + this.port);
/*      */   }
/*      */   
/*   97 */   private MySQLConnection proxy = null;
/*   98 */   private InvocationHandler realProxy = null;
/*      */   
/*      */   public boolean isProxySet() {
/*  101 */     return (this.proxy != null);
/*      */   }
/*      */   
/*      */   public void setProxy(MySQLConnection proxy) {
/*  105 */     this.proxy = proxy;
/*  106 */     this.realProxy = (this.proxy instanceof MultiHostMySQLConnection) ? ((MultiHostMySQLConnection)proxy).getThisAsProxy() : null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private MySQLConnection getProxy() {
/*  112 */     return (this.proxy != null) ? this.proxy : this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public MySQLConnection getLoadBalanceSafeProxy() {
/*  120 */     return getMultiHostSafeProxy();
/*      */   }
/*      */   
/*      */   public MySQLConnection getMultiHostSafeProxy() {
/*  124 */     return getProxy();
/*      */   }
/*      */   
/*      */   public MySQLConnection getActiveMySQLConnection() {
/*  128 */     return this;
/*      */   }
/*      */   
/*      */   public Object getConnectionMutex() {
/*  132 */     return (this.realProxy != null) ? this.realProxy : getProxy();
/*      */   }
/*      */   
/*      */   public class ExceptionInterceptorChain implements ExceptionInterceptor {
/*      */     private List<Extension> interceptors;
/*      */     
/*      */     ExceptionInterceptorChain(String interceptorClasses) throws SQLException {
/*  139 */       this.interceptors = Util.loadExtensions(ConnectionImpl.this, ConnectionImpl.this.props, interceptorClasses, "Connection.BadExceptionInterceptor", this);
/*      */     }
/*      */ 
/*      */     
/*      */     void addRingZero(ExceptionInterceptor interceptor) throws SQLException {
/*  144 */       this.interceptors.add(0, interceptor);
/*      */     }
/*      */     
/*      */     public SQLException interceptException(SQLException sqlEx, Connection conn) {
/*  148 */       if (this.interceptors != null) {
/*  149 */         Iterator<Extension> iter = this.interceptors.iterator();
/*      */         
/*  151 */         while (iter.hasNext()) {
/*  152 */           sqlEx = ((ExceptionInterceptor)iter.next()).interceptException(sqlEx, ConnectionImpl.this);
/*      */         }
/*      */       } 
/*      */       
/*  156 */       return sqlEx;
/*      */     }
/*      */     
/*      */     public void destroy() {
/*  160 */       if (this.interceptors != null) {
/*  161 */         Iterator<Extension> iter = this.interceptors.iterator();
/*      */         
/*  163 */         while (iter.hasNext()) {
/*  164 */           ((ExceptionInterceptor)iter.next()).destroy();
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void init(Connection conn, Properties properties) throws SQLException {
/*  171 */       if (this.interceptors != null) {
/*  172 */         Iterator<Extension> iter = this.interceptors.iterator();
/*      */         
/*  174 */         while (iter.hasNext()) {
/*  175 */           ((ExceptionInterceptor)iter.next()).init(conn, properties);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*      */     public List<Extension> getInterceptors() {
/*  181 */       return this.interceptors;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class CompoundCacheKey
/*      */   {
/*      */     final String componentOne;
/*      */ 
/*      */     
/*      */     final String componentTwo;
/*      */ 
/*      */     
/*      */     final int hashCode;
/*      */ 
/*      */     
/*      */     CompoundCacheKey(String partOne, String partTwo) {
/*  199 */       this.componentOne = partOne;
/*  200 */       this.componentTwo = partTwo;
/*      */       
/*  202 */       int hc = 17;
/*  203 */       hc = 31 * hc + ((this.componentOne != null) ? this.componentOne.hashCode() : 0);
/*  204 */       hc = 31 * hc + ((this.componentTwo != null) ? this.componentTwo.hashCode() : 0);
/*  205 */       this.hashCode = hc;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/*  215 */       if (this == obj) {
/*  216 */         return true;
/*      */       }
/*  218 */       if (obj != null && CompoundCacheKey.class.isAssignableFrom(obj.getClass())) {
/*  219 */         CompoundCacheKey another = (CompoundCacheKey)obj;
/*  220 */         if ((this.componentOne == null) ? (another.componentOne == null) : this.componentOne.equals(another.componentOne)) {
/*  221 */           return (this.componentTwo == null) ? ((another.componentTwo == null)) : this.componentTwo.equals(another.componentTwo);
/*      */         }
/*      */       } 
/*  224 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  234 */       return this.hashCode;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  242 */   private static final Object CHARSET_CONVERTER_NOT_AVAILABLE_MARKER = new Object();
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<?, ?> charsetMap;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String DEFAULT_LOGGER_CLASS = "com.mysql.jdbc.log.StandardLogger";
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int HISTOGRAM_BUCKETS = 20;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String LOGGER_INSTANCE_NAME = "MySQL";
/*      */ 
/*      */ 
/*      */   
/*  262 */   private static Map<String, Integer> mapTransIsolationNameToValue = null;
/*      */ 
/*      */   
/*  265 */   private static final Log NULL_LOGGER = (Log)new NullLogger("MySQL");
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Map<?, ?> roundRobinStatsMap;
/*      */ 
/*      */   
/*  272 */   private static final Map<String, Map<Integer, String>> customIndexToCharsetMapByUrl = new HashMap<String, Map<Integer, String>>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  277 */   private static final Map<String, Map<String, Integer>> customCharsetToMblenMapByUrl = new HashMap<String, Map<String, Integer>>();
/*      */   
/*      */   private CacheAdapter<String, Map<String, String>> serverConfigCache;
/*      */   
/*      */   private long queryTimeCount;
/*      */   
/*      */   private double queryTimeSum;
/*      */   
/*      */   private double queryTimeSumSquares;
/*      */   
/*      */   private double queryTimeMean;
/*      */   
/*      */   private transient Timer cancelTimer;
/*      */   private List<Extension> connectionLifecycleInterceptors;
/*      */   private static final Constructor<?> JDBC_4_CONNECTION_CTOR;
/*      */   private static final int DEFAULT_RESULT_SET_TYPE = 1003;
/*      */   private static final int DEFAULT_RESULT_SET_CONCURRENCY = 1007;
/*      */   private static final Random random;
/*      */   
/*      */   static {
/*  297 */     mapTransIsolationNameToValue = new HashMap<String, Integer>(8);
/*  298 */     mapTransIsolationNameToValue.put("READ-UNCOMMITED", Integer.valueOf(1));
/*  299 */     mapTransIsolationNameToValue.put("READ-UNCOMMITTED", Integer.valueOf(1));
/*  300 */     mapTransIsolationNameToValue.put("READ-COMMITTED", Integer.valueOf(2));
/*  301 */     mapTransIsolationNameToValue.put("REPEATABLE-READ", Integer.valueOf(4));
/*  302 */     mapTransIsolationNameToValue.put("SERIALIZABLE", Integer.valueOf(8));
/*      */     
/*  304 */     if (Util.isJdbc4()) {
/*      */       try {
/*  306 */         JDBC_4_CONNECTION_CTOR = Class.forName("com.mysql.jdbc.JDBC4Connection").getConstructor(new Class[] { String.class, int.class, Properties.class, String.class, String.class });
/*      */       }
/*  308 */       catch (SecurityException e) {
/*  309 */         throw new RuntimeException(e);
/*  310 */       } catch (NoSuchMethodException e) {
/*  311 */         throw new RuntimeException(e);
/*  312 */       } catch (ClassNotFoundException e) {
/*  313 */         throw new RuntimeException(e);
/*      */       } 
/*      */     } else {
/*  316 */       JDBC_4_CONNECTION_CTOR = null;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  389 */     random = new Random();
/*      */   }
/*      */   protected static SQLException appendMessageToException(SQLException sqlEx, String messageToAppend, ExceptionInterceptor interceptor) { String origMessage = sqlEx.getMessage(); String sqlState = sqlEx.getSQLState(); int vendorErrorCode = sqlEx.getErrorCode(); StringBuilder messageBuf = new StringBuilder(origMessage.length() + messageToAppend.length()); messageBuf.append(origMessage); messageBuf.append(messageToAppend); SQLException sqlExceptionWithNewMessage = SQLError.createSQLException(messageBuf.toString(), sqlState, vendorErrorCode, interceptor); try { Method getStackTraceMethod = null; Method setStackTraceMethod = null; Object theStackTraceAsObject = null; Class<?> stackTraceElementClass = Class.forName("java.lang.StackTraceElement"); Class<?> stackTraceElementArrayClass = Array.newInstance(stackTraceElementClass, new int[] { 0 }).getClass(); getStackTraceMethod = Throwable.class.getMethod("getStackTrace", new Class[0]); setStackTraceMethod = Throwable.class.getMethod("setStackTrace", new Class[] { stackTraceElementArrayClass }); if (getStackTraceMethod != null && setStackTraceMethod != null) { theStackTraceAsObject = getStackTraceMethod.invoke(sqlEx, new Object[0]); setStackTraceMethod.invoke(sqlExceptionWithNewMessage, new Object[] { theStackTraceAsObject }); }  }
/*      */     catch (NoClassDefFoundError noClassDefFound) {  }
/*      */     catch (NoSuchMethodException noSuchMethodEx) {  }
/*      */     catch (Throwable catchAll) {} return sqlExceptionWithNewMessage; }
/*      */   public Timer getCancelTimer() { synchronized (getConnectionMutex()) { if (this.cancelTimer == null)
/*      */         this.cancelTimer = new Timer("MySQL Statement Cancellation Timer", true);  return this.cancelTimer; }
/*      */      } protected static Connection getInstance(String hostToConnectTo, int portToConnectTo, Properties info, String databaseToConnectTo, String url) throws SQLException { if (!Util.isJdbc4())
/*  398 */       return new ConnectionImpl(hostToConnectTo, portToConnectTo, info, databaseToConnectTo, url);  return (Connection)Util.handleNewInstance(JDBC_4_CONNECTION_CTOR, new Object[] { hostToConnectTo, Integer.valueOf(portToConnectTo), info, databaseToConnectTo, url }, null); } protected static synchronized int getNextRoundRobinHostIndex(String url, List<?> hostList) { int indexRange = hostList.size();
/*      */     
/*  400 */     int index = random.nextInt(indexRange);
/*      */     
/*  402 */     return index; }
/*      */ 
/*      */   
/*      */   private static boolean nullSafeCompare(String s1, String s2) {
/*  406 */     if (s1 == null && s2 == null) {
/*  407 */       return true;
/*      */     }
/*      */     
/*  410 */     if (s1 == null && s2 != null) {
/*  411 */       return false;
/*      */     }
/*      */     
/*  414 */     return (s1 != null && s1.equals(s2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean autoCommit = true;
/*      */ 
/*      */   
/*      */   private CacheAdapter<String, PreparedStatement.ParseInfo> cachedPreparedStatementParams;
/*      */ 
/*      */   
/*  426 */   private String characterSetMetadata = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  432 */   private String characterSetResultsOnServer = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  439 */   private final Map<String, Object> charsetConverterMap = new HashMap<String, Object>(CharsetMapping.getNumberOfCharsetsConfigured());
/*      */ 
/*      */   
/*  442 */   private long connectionCreationTimeMillis = 0L;
/*      */ 
/*      */   
/*      */   private long connectionId;
/*      */ 
/*      */   
/*  448 */   private String database = null;
/*      */ 
/*      */   
/*  451 */   private DatabaseMetaData dbmd = null;
/*      */ 
/*      */   
/*      */   private TimeZone defaultTimeZone;
/*      */ 
/*      */   
/*      */   private ProfilerEventHandler eventSink;
/*      */ 
/*      */   
/*      */   private Throwable forceClosedReason;
/*      */ 
/*      */   
/*      */   private boolean hasIsolationLevels = false;
/*      */ 
/*      */   
/*      */   private boolean hasQuotedIdentifiers = false;
/*      */   
/*  468 */   private String host = null;
/*      */   
/*  470 */   public Map<Integer, String> indexToCustomMysqlCharset = null;
/*      */   
/*  472 */   private Map<String, Integer> mysqlCharsetToCustomMblen = null;
/*      */ 
/*      */   
/*  475 */   private transient MysqlIO io = null;
/*      */ 
/*      */   
/*      */   private boolean isClientTzUTC = false;
/*      */ 
/*      */   
/*      */   private boolean isClosed = true;
/*      */ 
/*      */   
/*      */   private boolean isInGlobalTx = false;
/*      */ 
/*      */   
/*      */   private boolean isRunningOnJDK13 = false;
/*      */   
/*  489 */   private int isolationLevel = 2;
/*      */ 
/*      */   
/*      */   private boolean isServerTzUTC = false;
/*      */   
/*  494 */   private long lastQueryFinishedTime = 0L;
/*      */ 
/*      */   
/*  497 */   private transient Log log = NULL_LOGGER;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  503 */   private long longestQueryTimeMs = 0L;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean lowerCaseTableNames = false;
/*      */ 
/*      */ 
/*      */   
/*  511 */   private long maximumNumberTablesAccessed = 0L;
/*      */ 
/*      */   
/*  514 */   private int sessionMaxRows = -1;
/*      */ 
/*      */   
/*      */   private long metricsLastReportedMs;
/*      */   
/*  519 */   private long minimumNumberTablesAccessed = Long.MAX_VALUE;
/*      */ 
/*      */   
/*  522 */   private String myURL = null;
/*      */ 
/*      */   
/*      */   private boolean needsPing = false;
/*      */   
/*  527 */   private int netBufferLength = 16384;
/*      */   
/*      */   private boolean noBackslashEscapes = false;
/*      */   
/*      */   private boolean serverTruncatesFracSecs = false;
/*      */   
/*  533 */   private long numberOfPreparedExecutes = 0L;
/*      */   
/*  535 */   private long numberOfPrepares = 0L;
/*      */   
/*  537 */   private long numberOfQueriesIssued = 0L;
/*      */   
/*  539 */   private long numberOfResultSetsCreated = 0L;
/*      */   
/*      */   private long[] numTablesMetricsHistBreakpoints;
/*      */   
/*      */   private int[] numTablesMetricsHistCounts;
/*      */   
/*  545 */   private long[] oldHistBreakpoints = null;
/*      */   
/*  547 */   private int[] oldHistCounts = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  553 */   private final CopyOnWriteArrayList<Statement> openStatements = new CopyOnWriteArrayList<Statement>();
/*      */ 
/*      */   
/*      */   private LRUCache<CompoundCacheKey, CallableStatement.CallableStatementParamInfo> parsedCallableStatementCache;
/*      */   
/*      */   private boolean parserKnowsUnicode = false;
/*      */   
/*  560 */   private String password = null;
/*      */ 
/*      */   
/*      */   private long[] perfMetricsHistBreakpoints;
/*      */   
/*      */   private int[] perfMetricsHistCounts;
/*      */   
/*  567 */   private int port = 3306;
/*      */ 
/*      */   
/*  570 */   protected Properties props = null;
/*      */ 
/*      */   
/*      */   private boolean readInfoMsg = false;
/*      */ 
/*      */   
/*      */   private boolean readOnly = false;
/*      */ 
/*      */   
/*      */   protected LRUCache<String, CachedResultSetMetaData> resultSetMetadataCache;
/*      */ 
/*      */   
/*  582 */   private TimeZone serverTimezoneTZ = null;
/*      */ 
/*      */   
/*  585 */   private Map<String, String> serverVariables = null;
/*      */   
/*  587 */   private long shortestQueryTimeMs = Long.MAX_VALUE;
/*      */   
/*  589 */   private double totalQueryTimeMs = 0.0D;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean transactionsSupported = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private Map<String, Class<?>> typeMap;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean useAnsiQuotes = false;
/*      */ 
/*      */   
/*  604 */   private String user = null;
/*      */ 
/*      */   
/*      */   private boolean useServerPreparedStmts = false;
/*      */ 
/*      */   
/*      */   private LRUCache<String, Boolean> serverSideStatementCheckCache;
/*      */ 
/*      */   
/*      */   private LRUCache<CompoundCacheKey, ServerPreparedStatement> serverSideStatementCache;
/*      */ 
/*      */   
/*      */   private Calendar sessionCalendar;
/*      */   
/*      */   private Calendar utcCalendar;
/*      */   
/*      */   private String origHostToConnectTo;
/*      */   
/*      */   private int origPortToConnectTo;
/*      */   
/*      */   private String origDatabaseToConnectTo;
/*      */   
/*  626 */   private String errorMessageEncoding = "Cp1252";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean usePlatformCharsetConverters;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean hasTriedMasterFlag = false;
/*      */ 
/*      */ 
/*      */   
/*  639 */   private String statementComment = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean storesLowerCaseTableName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<StatementInterceptorV2> statementInterceptors;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean requiresEscapingEncoder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String hostPortPair;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String SERVER_VERSION_STRING_VAR_NAME = "server_version_string";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int autoIncrementIncrement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ExceptionInterceptor exceptionInterceptor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unSafeStatementInterceptors() throws SQLException {
/*  808 */     ArrayList<StatementInterceptorV2> unSafedStatementInterceptors = new ArrayList<StatementInterceptorV2>(this.statementInterceptors.size());
/*      */     
/*  810 */     for (int i = 0; i < this.statementInterceptors.size(); i++) {
/*  811 */       NoSubInterceptorWrapper wrappedInterceptor = (NoSubInterceptorWrapper)this.statementInterceptors.get(i);
/*      */       
/*  813 */       unSafedStatementInterceptors.add(wrappedInterceptor.getUnderlyingInterceptor());
/*      */     } 
/*      */     
/*  816 */     this.statementInterceptors = unSafedStatementInterceptors;
/*      */     
/*  818 */     if (this.io != null) {
/*  819 */       this.io.setStatementInterceptors(this.statementInterceptors);
/*      */     }
/*      */   }
/*      */   
/*      */   public void initializeSafeStatementInterceptors() throws SQLException {
/*  824 */     this.isClosed = false;
/*      */     
/*  826 */     List<Extension> unwrappedInterceptors = Util.loadExtensions(this, this.props, getStatementInterceptors(), "MysqlIo.BadStatementInterceptor", getExceptionInterceptor());
/*      */ 
/*      */     
/*  829 */     this.statementInterceptors = new ArrayList<StatementInterceptorV2>(unwrappedInterceptors.size());
/*      */     
/*  831 */     for (int i = 0; i < unwrappedInterceptors.size(); i++) {
/*  832 */       Extension interceptor = unwrappedInterceptors.get(i);
/*      */ 
/*      */       
/*  835 */       if (interceptor instanceof StatementInterceptor) {
/*  836 */         if (ReflectiveStatementInterceptorAdapter.getV2PostProcessMethod(interceptor.getClass()) != null) {
/*  837 */           this.statementInterceptors.add(new NoSubInterceptorWrapper(new ReflectiveStatementInterceptorAdapter((StatementInterceptor)interceptor)));
/*      */         } else {
/*  839 */           this.statementInterceptors.add(new NoSubInterceptorWrapper(new V1toV2StatementInterceptorAdapter((StatementInterceptor)interceptor)));
/*      */         } 
/*      */       } else {
/*  842 */         this.statementInterceptors.add(new NoSubInterceptorWrapper((StatementInterceptorV2)interceptor));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public List<StatementInterceptorV2> getStatementInterceptorsInstances() {
/*  849 */     return this.statementInterceptors;
/*      */   }
/*      */ 
/*      */   
/*      */   private void addToHistogram(int[] histogramCounts, long[] histogramBreakpoints, long value, int numberOfTimes, long currentLowerBound, long currentUpperBound) {
/*  854 */     if (histogramCounts == null) {
/*  855 */       createInitialHistogram(histogramBreakpoints, currentLowerBound, currentUpperBound);
/*      */     } else {
/*  857 */       for (int i = 0; i < 20; i++) {
/*  858 */         if (histogramBreakpoints[i] >= value) {
/*  859 */           histogramCounts[i] = histogramCounts[i] + numberOfTimes;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void addToPerformanceHistogram(long value, int numberOfTimes) {
/*  868 */     checkAndCreatePerformanceHistogram();
/*      */     
/*  870 */     addToHistogram(this.perfMetricsHistCounts, this.perfMetricsHistBreakpoints, value, numberOfTimes, (this.shortestQueryTimeMs == Long.MAX_VALUE) ? 0L : this.shortestQueryTimeMs, this.longestQueryTimeMs);
/*      */   }
/*      */ 
/*      */   
/*      */   private void addToTablesAccessedHistogram(long value, int numberOfTimes) {
/*  875 */     checkAndCreateTablesAccessedHistogram();
/*      */     
/*  877 */     addToHistogram(this.numTablesMetricsHistCounts, this.numTablesMetricsHistBreakpoints, value, numberOfTimes, (this.minimumNumberTablesAccessed == Long.MAX_VALUE) ? 0L : this.minimumNumberTablesAccessed, this.maximumNumberTablesAccessed);
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
/*      */   private void buildCollationMapping() throws SQLException {
/*  889 */     Map<Integer, String> customCharset = null;
/*  890 */     Map<String, Integer> customMblen = null;
/*      */     
/*  892 */     if (getCacheServerConfiguration()) {
/*  893 */       synchronized (customIndexToCharsetMapByUrl) {
/*  894 */         customCharset = customIndexToCharsetMapByUrl.get(getURL());
/*  895 */         customMblen = customCharsetToMblenMapByUrl.get(getURL());
/*      */       } 
/*      */     }
/*      */     
/*  899 */     if (customCharset == null && getDetectCustomCollations() && versionMeetsMinimum(4, 1, 0)) {
/*      */       
/*  901 */       Statement stmt = null;
/*  902 */       ResultSet results = null;
/*      */       
/*      */       try {
/*  905 */         customCharset = new HashMap<Integer, String>();
/*  906 */         customMblen = new HashMap<String, Integer>();
/*      */         
/*  908 */         stmt = getMetadataSafeStatement();
/*      */         
/*      */         try {
/*  911 */           results = stmt.executeQuery("SHOW COLLATION");
/*  912 */           while (results.next()) {
/*  913 */             int collationIndex = results.getInt(3);
/*  914 */             String charsetName = results.getString(2);
/*      */ 
/*      */             
/*  917 */             if (collationIndex >= 2048 || !charsetName.equals(CharsetMapping.getMysqlCharsetNameForCollationIndex(Integer.valueOf(collationIndex))))
/*      */             {
/*  919 */               customCharset.put(Integer.valueOf(collationIndex), charsetName);
/*      */             }
/*      */ 
/*      */             
/*  923 */             if (!CharsetMapping.CHARSET_NAME_TO_CHARSET.containsKey(charsetName)) {
/*  924 */               customMblen.put(charsetName, null);
/*      */             }
/*      */           } 
/*  927 */         } catch (SQLException ex) {
/*  928 */           if (ex.getErrorCode() != 1820 || getDisconnectOnExpiredPasswords()) {
/*  929 */             throw ex;
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  934 */         if (customMblen.size() > 0) {
/*      */           try {
/*  936 */             results = stmt.executeQuery("SHOW CHARACTER SET");
/*  937 */             while (results.next()) {
/*  938 */               String charsetName = results.getString("Charset");
/*  939 */               if (customMblen.containsKey(charsetName)) {
/*  940 */                 customMblen.put(charsetName, Integer.valueOf(results.getInt("Maxlen")));
/*      */               }
/*      */             } 
/*  943 */           } catch (SQLException ex) {
/*  944 */             if (ex.getErrorCode() != 1820 || getDisconnectOnExpiredPasswords()) {
/*  945 */               throw ex;
/*      */             }
/*      */           } 
/*      */         }
/*      */         
/*  950 */         if (getCacheServerConfiguration()) {
/*  951 */           synchronized (customIndexToCharsetMapByUrl) {
/*  952 */             customIndexToCharsetMapByUrl.put(getURL(), customCharset);
/*  953 */             customCharsetToMblenMapByUrl.put(getURL(), customMblen);
/*      */           }
/*      */         
/*      */         }
/*  957 */       } catch (SQLException ex) {
/*  958 */         throw ex;
/*  959 */       } catch (RuntimeException ex) {
/*  960 */         SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/*  961 */         sqlEx.initCause(ex);
/*  962 */         throw sqlEx;
/*      */       } finally {
/*  964 */         if (results != null) {
/*      */           try {
/*  966 */             results.close();
/*  967 */           } catch (SQLException sqlE) {}
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  972 */         if (stmt != null) {
/*      */           try {
/*  974 */             stmt.close();
/*  975 */           } catch (SQLException sqlE) {}
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  982 */     if (customCharset != null) {
/*  983 */       this.indexToCustomMysqlCharset = Collections.unmodifiableMap(customCharset);
/*      */     }
/*  985 */     if (customMblen != null) {
/*  986 */       this.mysqlCharsetToCustomMblen = Collections.unmodifiableMap(customMblen);
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean canHandleAsServerPreparedStatement(String sql) throws SQLException {
/*  991 */     if (sql == null || sql.length() == 0) {
/*  992 */       return true;
/*      */     }
/*      */     
/*  995 */     if (!this.useServerPreparedStmts) {
/*  996 */       return false;
/*      */     }
/*      */     
/*  999 */     if (getCachePreparedStatements()) {
/* 1000 */       synchronized (this.serverSideStatementCheckCache) {
/* 1001 */         Boolean flag = (Boolean)this.serverSideStatementCheckCache.get(sql);
/*      */         
/* 1003 */         if (flag != null) {
/* 1004 */           return flag.booleanValue();
/*      */         }
/*      */         
/* 1007 */         boolean canHandle = canHandleAsServerPreparedStatementNoCache(sql);
/*      */         
/* 1009 */         if (sql.length() < getPreparedStatementCacheSqlLimit()) {
/* 1010 */           this.serverSideStatementCheckCache.put(sql, canHandle ? Boolean.TRUE : Boolean.FALSE);
/*      */         }
/*      */         
/* 1013 */         return canHandle;
/*      */       } 
/*      */     }
/*      */     
/* 1017 */     return canHandleAsServerPreparedStatementNoCache(sql);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean canHandleAsServerPreparedStatementNoCache(String sql) throws SQLException {
/* 1023 */     if (StringUtils.startsWithIgnoreCaseAndNonAlphaNumeric(sql, "CALL")) {
/* 1024 */       return false;
/*      */     }
/*      */     
/* 1027 */     boolean canHandleAsStatement = true;
/*      */     
/* 1029 */     boolean allowBackslashEscapes = !this.noBackslashEscapes;
/* 1030 */     String quoteChar = this.useAnsiQuotes ? "\"" : "'";
/*      */     
/* 1032 */     if (getAllowMultiQueries()) {
/* 1033 */       if (StringUtils.indexOfIgnoreCase(0, sql, ";", quoteChar, quoteChar, allowBackslashEscapes ? StringUtils.SEARCH_MODE__ALL : StringUtils.SEARCH_MODE__MRK_COM_WS) != -1)
/*      */       {
/* 1035 */         canHandleAsStatement = false;
/*      */       }
/* 1037 */     } else if (!versionMeetsMinimum(5, 0, 7) && (StringUtils.startsWithIgnoreCaseAndNonAlphaNumeric(sql, "SELECT") || StringUtils.startsWithIgnoreCaseAndNonAlphaNumeric(sql, "DELETE") || StringUtils.startsWithIgnoreCaseAndNonAlphaNumeric(sql, "INSERT") || StringUtils.startsWithIgnoreCaseAndNonAlphaNumeric(sql, "UPDATE") || StringUtils.startsWithIgnoreCaseAndNonAlphaNumeric(sql, "REPLACE"))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1047 */       int currentPos = 0;
/* 1048 */       int statementLength = sql.length();
/* 1049 */       int lastPosToLook = statementLength - 7;
/* 1050 */       boolean foundLimitWithPlaceholder = false;
/*      */       
/* 1052 */       while (currentPos < lastPosToLook) {
/* 1053 */         int limitStart = StringUtils.indexOfIgnoreCase(currentPos, sql, "LIMIT ", quoteChar, quoteChar, allowBackslashEscapes ? StringUtils.SEARCH_MODE__ALL : StringUtils.SEARCH_MODE__MRK_COM_WS);
/*      */ 
/*      */         
/* 1056 */         if (limitStart == -1) {
/*      */           break;
/*      */         }
/*      */         
/* 1060 */         currentPos = limitStart + 7;
/*      */         
/* 1062 */         while (currentPos < statementLength) {
/* 1063 */           char c = sql.charAt(currentPos);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1069 */           if (!Character.isDigit(c) && !Character.isWhitespace(c) && c != ',' && c != '?') {
/*      */             break;
/*      */           }
/*      */           
/* 1073 */           if (c == '?') {
/* 1074 */             foundLimitWithPlaceholder = true;
/*      */             
/*      */             break;
/*      */           } 
/* 1078 */           currentPos++;
/*      */         } 
/*      */       } 
/*      */       
/* 1082 */       canHandleAsStatement = !foundLimitWithPlaceholder;
/* 1083 */     } else if (StringUtils.startsWithIgnoreCaseAndWs(sql, "XA ")) {
/* 1084 */       canHandleAsStatement = false;
/* 1085 */     } else if (StringUtils.startsWithIgnoreCaseAndWs(sql, "CREATE TABLE")) {
/* 1086 */       canHandleAsStatement = false;
/* 1087 */     } else if (StringUtils.startsWithIgnoreCaseAndWs(sql, "DO")) {
/* 1088 */       canHandleAsStatement = false;
/* 1089 */     } else if (StringUtils.startsWithIgnoreCaseAndWs(sql, "SET")) {
/* 1090 */       canHandleAsStatement = false;
/* 1091 */     } else if (StringUtils.startsWithIgnoreCaseAndWs(sql, "SHOW WARNINGS") && versionMeetsMinimum(5, 7, 2)) {
/* 1092 */       canHandleAsStatement = false;
/* 1093 */     } else if (sql.startsWith("/* ping */")) {
/* 1094 */       canHandleAsStatement = false;
/*      */     } 
/*      */     
/* 1097 */     return canHandleAsStatement;
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
/*      */   public void changeUser(String userName, String newPassword) throws SQLException {
/* 1114 */     synchronized (getConnectionMutex()) {
/* 1115 */       checkClosed();
/*      */       
/* 1117 */       if (userName == null || userName.equals("")) {
/* 1118 */         userName = "";
/*      */       }
/*      */       
/* 1121 */       if (newPassword == null) {
/* 1122 */         newPassword = "";
/*      */       }
/*      */ 
/*      */       
/* 1126 */       this.sessionMaxRows = -1;
/*      */       
/*      */       try {
/* 1129 */         this.io.changeUser(userName, newPassword, this.database);
/* 1130 */       } catch (SQLException ex) {
/* 1131 */         if (versionMeetsMinimum(5, 6, 13) && "28000".equals(ex.getSQLState())) {
/* 1132 */           cleanup(ex);
/*      */         }
/* 1134 */         throw ex;
/*      */       } 
/* 1136 */       this.user = userName;
/* 1137 */       this.password = newPassword;
/*      */       
/* 1139 */       if (versionMeetsMinimum(4, 1, 0)) {
/* 1140 */         configureClientCharacterSet(true);
/*      */       }
/*      */       
/* 1143 */       setSessionVariables();
/*      */       
/* 1145 */       setupServerForTruncationChecks();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean characterSetNamesMatches(String mysqlEncodingName) {
/* 1151 */     return (mysqlEncodingName != null && mysqlEncodingName.equalsIgnoreCase(this.serverVariables.get("character_set_client")) && mysqlEncodingName.equalsIgnoreCase(this.serverVariables.get("character_set_connection")));
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkAndCreatePerformanceHistogram() {
/* 1156 */     if (this.perfMetricsHistCounts == null) {
/* 1157 */       this.perfMetricsHistCounts = new int[20];
/*      */     }
/*      */     
/* 1160 */     if (this.perfMetricsHistBreakpoints == null) {
/* 1161 */       this.perfMetricsHistBreakpoints = new long[20];
/*      */     }
/*      */   }
/*      */   
/*      */   private void checkAndCreateTablesAccessedHistogram() {
/* 1166 */     if (this.numTablesMetricsHistCounts == null) {
/* 1167 */       this.numTablesMetricsHistCounts = new int[20];
/*      */     }
/*      */     
/* 1170 */     if (this.numTablesMetricsHistBreakpoints == null) {
/* 1171 */       this.numTablesMetricsHistBreakpoints = new long[20];
/*      */     }
/*      */   }
/*      */   
/*      */   public void checkClosed() throws SQLException {
/* 1176 */     if (this.isClosed) {
/* 1177 */       throwConnectionClosedException();
/*      */     }
/*      */   }
/*      */   
/*      */   public void throwConnectionClosedException() throws SQLException {
/* 1182 */     SQLException ex = SQLError.createSQLException("No operations allowed after connection closed.", "08003", getExceptionInterceptor());
/*      */ 
/*      */     
/* 1185 */     if (this.forceClosedReason != null) {
/* 1186 */       ex.initCause(this.forceClosedReason);
/*      */     }
/*      */     
/* 1189 */     throw ex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkServerEncoding() throws SQLException {
/* 1199 */     if (getUseUnicode() && getEncoding() != null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1204 */     String serverCharset = this.serverVariables.get("character_set");
/*      */     
/* 1206 */     if (serverCharset == null)
/*      */     {
/* 1208 */       serverCharset = this.serverVariables.get("character_set_server");
/*      */     }
/*      */     
/* 1211 */     String mappedServerEncoding = null;
/*      */     
/* 1213 */     if (serverCharset != null) {
/*      */       try {
/* 1215 */         mappedServerEncoding = CharsetMapping.getJavaEncodingForMysqlCharset(serverCharset);
/* 1216 */       } catch (RuntimeException ex) {
/* 1217 */         SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 1218 */         sqlEx.initCause(ex);
/* 1219 */         throw sqlEx;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1226 */     if (!getUseUnicode() && mappedServerEncoding != null) {
/* 1227 */       SingleByteCharsetConverter converter = getCharsetConverter(mappedServerEncoding);
/*      */       
/* 1229 */       if (converter != null) {
/* 1230 */         setUseUnicode(true);
/* 1231 */         setEncoding(mappedServerEncoding);
/*      */ 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1240 */     if (serverCharset != null) {
/* 1241 */       if (mappedServerEncoding == null)
/*      */       {
/* 1243 */         if (Character.isLowerCase(serverCharset.charAt(0))) {
/* 1244 */           char[] ach = serverCharset.toCharArray();
/* 1245 */           ach[0] = Character.toUpperCase(serverCharset.charAt(0));
/* 1246 */           setEncoding(new String(ach));
/*      */         } 
/*      */       }
/*      */       
/* 1250 */       if (mappedServerEncoding == null) {
/* 1251 */         throw SQLError.createSQLException("Unknown character encoding on server '" + serverCharset + "', use 'characterEncoding=' property " + " to provide correct mapping", "01S00", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1260 */         StringUtils.getBytes("abc", mappedServerEncoding);
/* 1261 */         setEncoding(mappedServerEncoding);
/* 1262 */         setUseUnicode(true);
/* 1263 */       } catch (UnsupportedEncodingException UE) {
/* 1264 */         throw SQLError.createSQLException("The driver can not map the character encoding '" + getEncoding() + "' that your server is using " + "to a character encoding your JVM understands. You can specify this mapping manually by adding \"useUnicode=true\" " + "as well as \"characterEncoding=[an_encoding_your_jvm_understands]\" to your JDBC URL.", "0S100", getExceptionInterceptor());
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
/*      */   private void checkTransactionIsolationLevel() throws SQLException {
/* 1278 */     String s = this.serverVariables.get("transaction_isolation");
/* 1279 */     if (s == null) {
/* 1280 */       s = this.serverVariables.get("tx_isolation");
/*      */     }
/*      */     
/* 1283 */     if (s != null) {
/* 1284 */       Integer intTI = mapTransIsolationNameToValue.get(s);
/*      */       
/* 1286 */       if (intTI != null) {
/* 1287 */         this.isolationLevel = intTI.intValue();
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
/*      */   public void abortInternal() throws SQLException {
/* 1299 */     if (this.io != null) {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */         
/* 1305 */         this.io.forceClose();
/* 1306 */         this.io.releaseResources();
/* 1307 */       } catch (Throwable t) {}
/*      */ 
/*      */       
/* 1310 */       this.io = null;
/*      */     } 
/*      */     
/* 1313 */     this.isClosed = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cleanup(Throwable whyCleanedUp) {
/*      */     try {
/* 1324 */       if (this.io != null) {
/* 1325 */         if (isClosed()) {
/* 1326 */           this.io.forceClose();
/*      */         } else {
/* 1328 */           realClose(false, false, false, whyCleanedUp);
/*      */         } 
/*      */       }
/* 1331 */     } catch (SQLException sqlEx) {}
/*      */ 
/*      */ 
/*      */     
/* 1335 */     this.isClosed = true;
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public void clearHasTriedMaster() {
/* 1340 */     this.hasTriedMasterFlag = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearWarnings() throws SQLException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql) throws SQLException {
/* 1359 */     return clientPrepareStatement(sql, 1003, 1007);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
/* 1366 */     PreparedStatement pStmt = clientPrepareStatement(sql);
/*      */     
/* 1368 */     ((PreparedStatement)pStmt).setRetrieveGeneratedKeys((autoGenKeyIndex == 1));
/*      */     
/* 1370 */     return pStmt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/* 1380 */     return clientPrepareStatement(sql, resultSetType, resultSetConcurrency, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, boolean processEscapeCodesIfNeeded) throws SQLException {
/* 1385 */     checkClosed();
/*      */     
/* 1387 */     String nativeSql = (processEscapeCodesIfNeeded && getProcessEscapeCodesForPrepStmts()) ? nativeSQL(sql) : sql;
/*      */     
/* 1389 */     PreparedStatement pStmt = null;
/*      */     
/* 1391 */     if (getCachePreparedStatements()) {
/* 1392 */       PreparedStatement.ParseInfo pStmtInfo = this.cachedPreparedStatementParams.get(nativeSql);
/*      */       
/* 1394 */       if (pStmtInfo == null) {
/* 1395 */         pStmt = PreparedStatement.getInstance(getMultiHostSafeProxy(), nativeSql, this.database);
/*      */         
/* 1397 */         this.cachedPreparedStatementParams.put(nativeSql, pStmt.getParseInfo());
/*      */       } else {
/* 1399 */         pStmt = PreparedStatement.getInstance(getMultiHostSafeProxy(), nativeSql, this.database, pStmtInfo);
/*      */       } 
/*      */     } else {
/* 1402 */       pStmt = PreparedStatement.getInstance(getMultiHostSafeProxy(), nativeSql, this.database);
/*      */     } 
/*      */     
/* 1405 */     pStmt.setResultSetType(resultSetType);
/* 1406 */     pStmt.setResultSetConcurrency(resultSetConcurrency);
/*      */     
/* 1408 */     return pStmt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
/* 1416 */     PreparedStatement pStmt = (PreparedStatement)clientPrepareStatement(sql);
/*      */     
/* 1418 */     pStmt.setRetrieveGeneratedKeys((autoGenKeyIndexes != null && autoGenKeyIndexes.length > 0));
/*      */     
/* 1420 */     return pStmt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
/* 1427 */     PreparedStatement pStmt = (PreparedStatement)clientPrepareStatement(sql);
/*      */     
/* 1429 */     pStmt.setRetrieveGeneratedKeys((autoGenKeyColNames != null && autoGenKeyColNames.length > 0));
/*      */     
/* 1431 */     return pStmt;
/*      */   }
/*      */ 
/*      */   
/*      */   public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/* 1436 */     return clientPrepareStatement(sql, resultSetType, resultSetConcurrency, true);
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
/*      */   public void close() throws SQLException {
/* 1452 */     synchronized (getConnectionMutex()) {
/* 1453 */       if (this.connectionLifecycleInterceptors != null) {
/* 1454 */         (new IterateBlock<Extension>(this.connectionLifecycleInterceptors.iterator())
/*      */           {
/*      */             void forEach(Extension each) throws SQLException {
/* 1457 */               ((ConnectionLifecycleInterceptor)each).close();
/*      */             }
/*      */           }).doForAll();
/*      */       }
/*      */       
/* 1462 */       realClose(true, true, false, (Throwable)null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void closeAllOpenStatements() throws SQLException {
/* 1472 */     SQLException postponedException = null;
/*      */     
/* 1474 */     for (Statement stmt : this.openStatements) {
/*      */       try {
/* 1476 */         ((StatementImpl)stmt).realClose(false, true);
/* 1477 */       } catch (SQLException sqlEx) {
/* 1478 */         postponedException = sqlEx;
/*      */       } 
/*      */     } 
/*      */     
/* 1482 */     if (postponedException != null) {
/* 1483 */       throw postponedException;
/*      */     }
/*      */   }
/*      */   
/*      */   private void closeStatement(Statement stmt) {
/* 1488 */     if (stmt != null) {
/*      */       try {
/* 1490 */         stmt.close();
/* 1491 */       } catch (SQLException sqlEx) {}
/*      */ 
/*      */ 
/*      */       
/* 1495 */       stmt = null;
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
/*      */   public void commit() throws SQLException {
/* 1513 */     synchronized (getConnectionMutex()) {
/* 1514 */       checkClosed();
/*      */       
/*      */       try {
/* 1517 */         if (this.connectionLifecycleInterceptors != null) {
/* 1518 */           IterateBlock<Extension> iter = new IterateBlock<Extension>(this.connectionLifecycleInterceptors.iterator())
/*      */             {
/*      */               void forEach(Extension each) throws SQLException
/*      */               {
/* 1522 */                 if (!((ConnectionLifecycleInterceptor)each).commit()) {
/* 1523 */                   this.stopIterating = true;
/*      */                 }
/*      */               }
/*      */             };
/*      */           
/* 1528 */           iter.doForAll();
/*      */           
/* 1530 */           if (!iter.fullIteration()) {
/*      */             return;
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1536 */         if (this.autoCommit && !getRelaxAutoCommit())
/* 1537 */           throw SQLError.createSQLException("Can't call commit when autocommit=true", getExceptionInterceptor()); 
/* 1538 */         if (this.transactionsSupported) {
/* 1539 */           if (getUseLocalTransactionState() && versionMeetsMinimum(5, 0, 0) && 
/* 1540 */             !this.io.inTransactionOnServer()) {
/*      */             return;
/*      */           }
/*      */ 
/*      */           
/* 1545 */           execSQL((StatementImpl)null, "commit", -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */         } 
/* 1547 */       } catch (SQLException sqlException) {
/* 1548 */         if ("08S01".equals(sqlException.getSQLState())) {
/* 1549 */           throw SQLError.createSQLException("Communications link failure during commit(). Transaction resolution unknown.", "08007", getExceptionInterceptor());
/*      */         }
/*      */ 
/*      */         
/* 1553 */         throw sqlException;
/*      */       } finally {
/* 1555 */         this.needsPing = getReconnectAtTxEnd();
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
/*      */   private void configureCharsetProperties() throws SQLException {
/* 1568 */     if (getEncoding() != null) {
/*      */       
/*      */       try {
/* 1571 */         String testString = "abc";
/* 1572 */         StringUtils.getBytes(testString, getEncoding());
/* 1573 */       } catch (UnsupportedEncodingException UE) {
/*      */         
/* 1575 */         String oldEncoding = getEncoding();
/*      */         
/*      */         try {
/* 1578 */           setEncoding(CharsetMapping.getJavaEncodingForMysqlCharset(oldEncoding));
/* 1579 */         } catch (RuntimeException ex) {
/* 1580 */           SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 1581 */           sqlEx.initCause(ex);
/* 1582 */           throw sqlEx;
/*      */         } 
/*      */         
/* 1585 */         if (getEncoding() == null) {
/* 1586 */           throw SQLError.createSQLException("Java does not support the MySQL character encoding '" + oldEncoding + "'.", "01S00", getExceptionInterceptor());
/*      */         }
/*      */ 
/*      */         
/*      */         try {
/* 1591 */           String testString = "abc";
/* 1592 */           StringUtils.getBytes(testString, getEncoding());
/* 1593 */         } catch (UnsupportedEncodingException encodingEx) {
/* 1594 */           throw SQLError.createSQLException("Unsupported character encoding '" + getEncoding() + "'.", "01S00", getExceptionInterceptor());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean configureClientCharacterSet(boolean dontCheckServerMatch) throws SQLException {
/* 1615 */     String realJavaEncoding = getEncoding();
/* 1616 */     boolean characterSetAlreadyConfigured = false;
/*      */     
/*      */     try {
/* 1619 */       if (versionMeetsMinimum(4, 1, 0)) {
/* 1620 */         characterSetAlreadyConfigured = true;
/*      */         
/* 1622 */         setUseUnicode(true);
/*      */         
/* 1624 */         configureCharsetProperties();
/* 1625 */         realJavaEncoding = getEncoding();
/*      */         
/* 1627 */         String connectionCollationSuffix = "";
/* 1628 */         String connectionCollationCharset = "";
/*      */         
/* 1630 */         if (!getUseOldUTF8Behavior() && !StringUtils.isNullOrEmpty(getConnectionCollation())) {
/* 1631 */           for (int i = 1; i < CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME.length; i++) {
/* 1632 */             if (CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME[i].equals(getConnectionCollation())) {
/* 1633 */               connectionCollationSuffix = " COLLATE " + CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME[i];
/* 1634 */               connectionCollationCharset = (CharsetMapping.COLLATION_INDEX_TO_CHARSET[i]).charsetName;
/* 1635 */               realJavaEncoding = CharsetMapping.getJavaEncodingForCollationIndex(Integer.valueOf(i));
/*      */             } 
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1644 */           if (this.props != null && this.props.getProperty("com.mysql.jdbc.faultInjection.serverCharsetIndex") != null) {
/* 1645 */             this.io.serverCharsetIndex = Integer.parseInt(this.props.getProperty("com.mysql.jdbc.faultInjection.serverCharsetIndex"));
/*      */           }
/*      */           
/* 1648 */           String serverEncodingToSet = CharsetMapping.getJavaEncodingForCollationIndex(Integer.valueOf(this.io.serverCharsetIndex));
/*      */           
/* 1650 */           if (serverEncodingToSet == null || serverEncodingToSet.length() == 0) {
/* 1651 */             if (realJavaEncoding != null) {
/*      */               
/* 1653 */               setEncoding(realJavaEncoding);
/*      */             } else {
/* 1655 */               throw SQLError.createSQLException("Unknown initial character set index '" + this.io.serverCharsetIndex + "' received from server. Initial client character set can be forced via the 'characterEncoding' property.", "S1000", getExceptionInterceptor());
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1663 */           if (versionMeetsMinimum(4, 1, 0) && "ISO8859_1".equalsIgnoreCase(serverEncodingToSet)) {
/* 1664 */             serverEncodingToSet = "Cp1252";
/*      */           }
/* 1666 */           if ("UnicodeBig".equalsIgnoreCase(serverEncodingToSet) || "UTF-16".equalsIgnoreCase(serverEncodingToSet) || "UTF-16LE".equalsIgnoreCase(serverEncodingToSet) || "UTF-32".equalsIgnoreCase(serverEncodingToSet))
/*      */           {
/* 1668 */             serverEncodingToSet = "UTF-8";
/*      */           }
/*      */           
/* 1671 */           setEncoding(serverEncodingToSet);
/*      */         }
/* 1673 */         catch (ArrayIndexOutOfBoundsException outOfBoundsEx) {
/* 1674 */           if (realJavaEncoding != null) {
/*      */             
/* 1676 */             setEncoding(realJavaEncoding);
/*      */           } else {
/* 1678 */             throw SQLError.createSQLException("Unknown initial character set index '" + this.io.serverCharsetIndex + "' received from server. Initial client character set can be forced via the 'characterEncoding' property.", "S1000", getExceptionInterceptor());
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 1683 */         catch (SQLException ex) {
/* 1684 */           throw ex;
/* 1685 */         } catch (RuntimeException ex) {
/* 1686 */           SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 1687 */           sqlEx.initCause(ex);
/* 1688 */           throw sqlEx;
/*      */         } 
/*      */         
/* 1691 */         if (getEncoding() == null)
/*      */         {
/* 1693 */           setEncoding("ISO8859_1");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1699 */         if (getUseUnicode()) {
/* 1700 */           if (realJavaEncoding != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1705 */             if (realJavaEncoding.equalsIgnoreCase("UTF-8") || realJavaEncoding.equalsIgnoreCase("UTF8")) {
/*      */ 
/*      */               
/* 1708 */               boolean utf8mb4Supported = versionMeetsMinimum(5, 5, 2);
/* 1709 */               String utf8CharsetName = (connectionCollationSuffix.length() > 0) ? connectionCollationCharset : (utf8mb4Supported ? "utf8mb4" : "utf8");
/*      */ 
/*      */               
/* 1712 */               if (!getUseOldUTF8Behavior()) {
/* 1713 */                 if (dontCheckServerMatch || !characterSetNamesMatches("utf8") || (utf8mb4Supported && !characterSetNamesMatches("utf8mb4")) || (connectionCollationSuffix.length() > 0 && !getConnectionCollation().equalsIgnoreCase(this.serverVariables.get("collation_server")))) {
/*      */ 
/*      */                   
/* 1716 */                   execSQL((StatementImpl)null, "SET NAMES " + utf8CharsetName + connectionCollationSuffix, -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */                   
/* 1718 */                   this.serverVariables.put("character_set_client", utf8CharsetName);
/* 1719 */                   this.serverVariables.put("character_set_connection", utf8CharsetName);
/*      */                 } 
/*      */               } else {
/* 1722 */                 execSQL((StatementImpl)null, "SET NAMES latin1", -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */                 
/* 1724 */                 this.serverVariables.put("character_set_client", "latin1");
/* 1725 */                 this.serverVariables.put("character_set_connection", "latin1");
/*      */               } 
/*      */               
/* 1728 */               setEncoding(realJavaEncoding);
/*      */             } else {
/* 1730 */               String mysqlCharsetName = (connectionCollationSuffix.length() > 0) ? connectionCollationCharset : CharsetMapping.getMysqlCharsetForJavaEncoding(realJavaEncoding.toUpperCase(Locale.ENGLISH), this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1742 */               if (mysqlCharsetName != null)
/*      */               {
/* 1744 */                 if (dontCheckServerMatch || !characterSetNamesMatches(mysqlCharsetName)) {
/* 1745 */                   execSQL((StatementImpl)null, "SET NAMES " + mysqlCharsetName + connectionCollationSuffix, -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */                   
/* 1747 */                   this.serverVariables.put("character_set_client", mysqlCharsetName);
/* 1748 */                   this.serverVariables.put("character_set_connection", mysqlCharsetName);
/*      */                 } 
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 1754 */               setEncoding(realJavaEncoding);
/*      */             } 
/* 1756 */           } else if (getEncoding() != null) {
/*      */             
/* 1758 */             String mysqlCharsetName = (connectionCollationSuffix.length() > 0) ? connectionCollationCharset : (getUseOldUTF8Behavior() ? "latin1" : getServerCharset());
/*      */ 
/*      */             
/* 1761 */             boolean ucs2 = false;
/* 1762 */             if ("ucs2".equalsIgnoreCase(mysqlCharsetName) || "utf16".equalsIgnoreCase(mysqlCharsetName) || "utf16le".equalsIgnoreCase(mysqlCharsetName) || "utf32".equalsIgnoreCase(mysqlCharsetName)) {
/*      */               
/* 1764 */               mysqlCharsetName = "utf8";
/* 1765 */               ucs2 = true;
/* 1766 */               if (getCharacterSetResults() == null) {
/* 1767 */                 setCharacterSetResults("UTF-8");
/*      */               }
/*      */             } 
/*      */             
/* 1771 */             if (dontCheckServerMatch || !characterSetNamesMatches(mysqlCharsetName) || ucs2) {
/*      */               try {
/* 1773 */                 execSQL((StatementImpl)null, "SET NAMES " + mysqlCharsetName + connectionCollationSuffix, -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */                 
/* 1775 */                 this.serverVariables.put("character_set_client", mysqlCharsetName);
/* 1776 */                 this.serverVariables.put("character_set_connection", mysqlCharsetName);
/* 1777 */               } catch (SQLException ex) {
/* 1778 */                 if (ex.getErrorCode() != 1820 || getDisconnectOnExpiredPasswords()) {
/* 1779 */                   throw ex;
/*      */                 }
/*      */               } 
/*      */             }
/*      */             
/* 1784 */             realJavaEncoding = getEncoding();
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1794 */         String onServer = null;
/* 1795 */         boolean isNullOnServer = false;
/*      */         
/* 1797 */         if (this.serverVariables != null) {
/* 1798 */           onServer = this.serverVariables.get("character_set_results");
/*      */           
/* 1800 */           isNullOnServer = (onServer == null || "NULL".equalsIgnoreCase(onServer) || onServer.length() == 0);
/*      */         } 
/*      */         
/* 1803 */         if (getCharacterSetResults() == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1808 */           if (!isNullOnServer) {
/*      */             try {
/* 1810 */               execSQL((StatementImpl)null, "SET character_set_results = NULL", -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */             }
/* 1812 */             catch (SQLException ex) {
/* 1813 */               if (ex.getErrorCode() != 1820 || getDisconnectOnExpiredPasswords()) {
/* 1814 */                 throw ex;
/*      */               }
/*      */             } 
/* 1817 */             this.serverVariables.put("jdbc.local.character_set_results", null);
/*      */           } else {
/* 1819 */             this.serverVariables.put("jdbc.local.character_set_results", onServer);
/*      */           } 
/*      */         } else {
/*      */           
/* 1823 */           if (getUseOldUTF8Behavior()) {
/*      */             try {
/* 1825 */               execSQL((StatementImpl)null, "SET NAMES latin1", -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */               
/* 1827 */               this.serverVariables.put("character_set_client", "latin1");
/* 1828 */               this.serverVariables.put("character_set_connection", "latin1");
/* 1829 */             } catch (SQLException ex) {
/* 1830 */               if (ex.getErrorCode() != 1820 || getDisconnectOnExpiredPasswords()) {
/* 1831 */                 throw ex;
/*      */               }
/*      */             } 
/*      */           }
/* 1835 */           String charsetResults = getCharacterSetResults();
/* 1836 */           String mysqlEncodingName = null;
/*      */           
/* 1838 */           if ("UTF-8".equalsIgnoreCase(charsetResults) || "UTF8".equalsIgnoreCase(charsetResults)) {
/* 1839 */             mysqlEncodingName = "utf8";
/* 1840 */           } else if ("null".equalsIgnoreCase(charsetResults)) {
/* 1841 */             mysqlEncodingName = "NULL";
/*      */           } else {
/* 1843 */             mysqlEncodingName = CharsetMapping.getMysqlCharsetForJavaEncoding(charsetResults.toUpperCase(Locale.ENGLISH), this);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1850 */           if (mysqlEncodingName == null) {
/* 1851 */             throw SQLError.createSQLException("Can't map " + charsetResults + " given for characterSetResults to a supported MySQL encoding.", "S1009", getExceptionInterceptor());
/*      */           }
/*      */ 
/*      */           
/* 1855 */           if (!mysqlEncodingName.equalsIgnoreCase(this.serverVariables.get("character_set_results"))) {
/* 1856 */             StringBuilder setBuf = new StringBuilder("SET character_set_results = ".length() + mysqlEncodingName.length());
/* 1857 */             setBuf.append("SET character_set_results = ").append(mysqlEncodingName);
/*      */             
/*      */             try {
/* 1860 */               execSQL((StatementImpl)null, setBuf.toString(), -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */             }
/* 1862 */             catch (SQLException ex) {
/* 1863 */               if (ex.getErrorCode() != 1820 || getDisconnectOnExpiredPasswords()) {
/* 1864 */                 throw ex;
/*      */               }
/*      */             } 
/*      */             
/* 1868 */             this.serverVariables.put("jdbc.local.character_set_results", mysqlEncodingName);
/*      */ 
/*      */             
/* 1871 */             if (versionMeetsMinimum(5, 5, 0)) {
/* 1872 */               this.errorMessageEncoding = charsetResults;
/*      */             }
/*      */           } else {
/*      */             
/* 1876 */             this.serverVariables.put("jdbc.local.character_set_results", onServer);
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/* 1881 */         realJavaEncoding = getEncoding();
/*      */       }
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/* 1887 */       setEncoding(realJavaEncoding);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1895 */       CharsetEncoder enc = Charset.forName(getEncoding()).newEncoder();
/* 1896 */       CharBuffer cbuf = CharBuffer.allocate(1);
/* 1897 */       ByteBuffer bbuf = ByteBuffer.allocate(1);
/*      */       
/* 1899 */       cbuf.put("¥");
/* 1900 */       cbuf.position(0);
/* 1901 */       enc.encode(cbuf, bbuf, true);
/* 1902 */       if (bbuf.get(0) == 92) {
/* 1903 */         this.requiresEscapingEncoder = true;
/*      */       } else {
/* 1905 */         cbuf.clear();
/* 1906 */         bbuf.clear();
/*      */         
/* 1908 */         cbuf.put("₩");
/* 1909 */         cbuf.position(0);
/* 1910 */         enc.encode(cbuf, bbuf, true);
/* 1911 */         if (bbuf.get(0) == 92) {
/* 1912 */           this.requiresEscapingEncoder = true;
/*      */         }
/*      */       } 
/* 1915 */     } catch (UnsupportedCharsetException ucex) {
/*      */       
/*      */       try {
/* 1918 */         byte[] bbuf = StringUtils.getBytes("¥", getEncoding());
/* 1919 */         if (bbuf[0] == 92) {
/* 1920 */           this.requiresEscapingEncoder = true;
/*      */         } else {
/* 1922 */           bbuf = StringUtils.getBytes("₩", getEncoding());
/* 1923 */           if (bbuf[0] == 92) {
/* 1924 */             this.requiresEscapingEncoder = true;
/*      */           }
/*      */         } 
/* 1927 */       } catch (UnsupportedEncodingException ueex) {
/* 1928 */         throw SQLError.createSQLException("Unable to use encoding: " + getEncoding(), "S1000", ueex, getExceptionInterceptor());
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1933 */     return characterSetAlreadyConfigured;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void configureTimezone() throws SQLException {
/* 1944 */     String configuredTimeZoneOnServer = this.serverVariables.get("timezone");
/*      */     
/* 1946 */     if (configuredTimeZoneOnServer == null) {
/* 1947 */       configuredTimeZoneOnServer = this.serverVariables.get("time_zone");
/*      */       
/* 1949 */       if ("SYSTEM".equalsIgnoreCase(configuredTimeZoneOnServer)) {
/* 1950 */         configuredTimeZoneOnServer = this.serverVariables.get("system_time_zone");
/*      */       }
/*      */     } 
/*      */     
/* 1954 */     String canonicalTimezone = getServerTimezone();
/*      */     
/* 1956 */     if ((getUseTimezone() || !getUseLegacyDatetimeCode()) && configuredTimeZoneOnServer != null)
/*      */     {
/* 1958 */       if (canonicalTimezone == null || StringUtils.isEmptyOrWhitespaceOnly(canonicalTimezone)) {
/*      */         try {
/* 1960 */           canonicalTimezone = TimeUtil.getCanonicalTimezone(configuredTimeZoneOnServer, getExceptionInterceptor());
/* 1961 */         } catch (IllegalArgumentException iae) {
/* 1962 */           throw SQLError.createSQLException(iae.getMessage(), "S1000", getExceptionInterceptor());
/*      */         } 
/*      */       }
/*      */     }
/*      */     
/* 1967 */     if (canonicalTimezone != null && canonicalTimezone.length() > 0) {
/* 1968 */       this.serverTimezoneTZ = TimeZone.getTimeZone(canonicalTimezone);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1973 */       if (!canonicalTimezone.equalsIgnoreCase("GMT") && this.serverTimezoneTZ.getID().equals("GMT")) {
/* 1974 */         throw SQLError.createSQLException("No timezone mapping entry for '" + canonicalTimezone + "'", "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/* 1978 */       this.isServerTzUTC = (!this.serverTimezoneTZ.useDaylightTime() && this.serverTimezoneTZ.getRawOffset() == 0);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void createInitialHistogram(long[] breakpoints, long lowerBound, long upperBound) {
/* 1984 */     double bucketSize = (upperBound - lowerBound) / 20.0D * 1.25D;
/*      */     
/* 1986 */     if (bucketSize < 1.0D) {
/* 1987 */       bucketSize = 1.0D;
/*      */     }
/*      */     
/* 1990 */     for (int i = 0; i < 20; i++) {
/* 1991 */       breakpoints[i] = lowerBound;
/* 1992 */       lowerBound = (long)(lowerBound + bucketSize);
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
/*      */   public void createNewIO(boolean isForReconnect) throws SQLException {
/* 2007 */     synchronized (getConnectionMutex()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2012 */       Properties mergedProps = exposeAsProperties(this.props);
/*      */       
/* 2014 */       if (!getHighAvailability()) {
/* 2015 */         connectOneTryOnly(isForReconnect, mergedProps);
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2020 */       connectWithRetries(isForReconnect, mergedProps);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void connectWithRetries(boolean isForReconnect, Properties mergedProps) throws SQLException {
/* 2025 */     double timeout = getInitialTimeout();
/* 2026 */     boolean connectionGood = false;
/*      */     
/* 2028 */     Exception connectionException = null;
/*      */     
/* 2030 */     for (int attemptCount = 0; attemptCount < getMaxReconnects() && !connectionGood; attemptCount++) {
/*      */       try {
/* 2032 */         boolean oldAutoCommit; int oldIsolationLevel; boolean oldReadOnly; String oldCatalog; if (this.io != null) {
/* 2033 */           this.io.forceClose();
/*      */         }
/*      */         
/* 2036 */         coreConnect(mergedProps);
/* 2037 */         pingInternal(false, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2044 */         synchronized (getConnectionMutex()) {
/* 2045 */           this.connectionId = this.io.getThreadId();
/* 2046 */           this.isClosed = false;
/*      */ 
/*      */           
/* 2049 */           oldAutoCommit = getAutoCommit();
/* 2050 */           oldIsolationLevel = this.isolationLevel;
/* 2051 */           oldReadOnly = isReadOnly(false);
/* 2052 */           oldCatalog = getCatalog();
/*      */           
/* 2054 */           this.io.setStatementInterceptors(this.statementInterceptors);
/*      */         } 
/*      */ 
/*      */         
/* 2058 */         initializePropsFromServer();
/*      */         
/* 2060 */         if (isForReconnect) {
/*      */           
/* 2062 */           setAutoCommit(oldAutoCommit);
/*      */           
/* 2064 */           if (this.hasIsolationLevels) {
/* 2065 */             setTransactionIsolation(oldIsolationLevel);
/*      */           }
/*      */           
/* 2068 */           setCatalog(oldCatalog);
/* 2069 */           setReadOnly(oldReadOnly);
/*      */         } 
/*      */         
/* 2072 */         connectionGood = true;
/*      */         
/*      */         break;
/* 2075 */       } catch (Exception EEE) {
/* 2076 */         connectionException = EEE;
/* 2077 */         connectionGood = false;
/*      */ 
/*      */         
/* 2080 */         if (connectionGood) {
/*      */           break;
/*      */         }
/*      */         
/* 2084 */         if (attemptCount > 0) {
/*      */           try {
/* 2086 */             Thread.sleep((long)timeout * 1000L);
/* 2087 */           } catch (InterruptedException IE) {}
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2093 */     if (!connectionGood) {
/*      */       
/* 2095 */       SQLException chainedEx = SQLError.createSQLException(Messages.getString("Connection.UnableToConnectWithRetries", new Object[] { Integer.valueOf(getMaxReconnects()) }), "08001", getExceptionInterceptor());
/*      */ 
/*      */       
/* 2098 */       chainedEx.initCause(connectionException);
/*      */       
/* 2100 */       throw chainedEx;
/*      */     } 
/*      */     
/* 2103 */     if (getParanoid() && !getHighAvailability()) {
/* 2104 */       this.password = null;
/* 2105 */       this.user = null;
/*      */     } 
/*      */     
/* 2108 */     if (isForReconnect) {
/*      */ 
/*      */ 
/*      */       
/* 2112 */       Iterator<Statement> statementIter = this.openStatements.iterator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2118 */       Stack<Statement> serverPreparedStatements = null;
/*      */       
/* 2120 */       while (statementIter.hasNext()) {
/* 2121 */         Statement statementObj = statementIter.next();
/*      */         
/* 2123 */         if (statementObj instanceof ServerPreparedStatement) {
/* 2124 */           if (serverPreparedStatements == null) {
/* 2125 */             serverPreparedStatements = new Stack<Statement>();
/*      */           }
/*      */           
/* 2128 */           serverPreparedStatements.add(statementObj);
/*      */         } 
/*      */       } 
/*      */       
/* 2132 */       if (serverPreparedStatements != null) {
/* 2133 */         while (!serverPreparedStatements.isEmpty()) {
/* 2134 */           ((ServerPreparedStatement)serverPreparedStatements.pop()).rePrepare();
/*      */         }
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void coreConnect(Properties mergedProps) throws SQLException, IOException {
/* 2141 */     int newPort = 3306;
/* 2142 */     String newHost = "localhost";
/*      */     
/* 2144 */     String protocol = mergedProps.getProperty("PROTOCOL");
/*      */     
/* 2146 */     if (protocol != null) {
/*      */ 
/*      */       
/* 2149 */       if ("tcp".equalsIgnoreCase(protocol)) {
/* 2150 */         newHost = normalizeHost(mergedProps.getProperty("HOST"));
/* 2151 */         newPort = parsePortNumber(mergedProps.getProperty("PORT", "3306"));
/* 2152 */       } else if ("pipe".equalsIgnoreCase(protocol)) {
/* 2153 */         setSocketFactoryClassName(NamedPipeSocketFactory.class.getName());
/*      */         
/* 2155 */         String path = mergedProps.getProperty("PATH");
/*      */         
/* 2157 */         if (path != null) {
/* 2158 */           mergedProps.setProperty("namedPipePath", path);
/*      */         }
/*      */       } else {
/*      */         
/* 2162 */         newHost = normalizeHost(mergedProps.getProperty("HOST"));
/* 2163 */         newPort = parsePortNumber(mergedProps.getProperty("PORT", "3306"));
/*      */       } 
/*      */     } else {
/*      */       
/* 2167 */       String[] parsedHostPortPair = NonRegisteringDriver.parseHostPortPair(this.hostPortPair);
/* 2168 */       newHost = parsedHostPortPair[0];
/*      */       
/* 2170 */       newHost = normalizeHost(newHost);
/*      */       
/* 2172 */       if (parsedHostPortPair[1] != null) {
/* 2173 */         newPort = parsePortNumber(parsedHostPortPair[1]);
/*      */       }
/*      */     } 
/*      */     
/* 2177 */     this.port = newPort;
/* 2178 */     this.host = newHost;
/*      */ 
/*      */     
/* 2181 */     this.sessionMaxRows = -1;
/*      */ 
/*      */     
/* 2184 */     this.serverVariables = new HashMap<String, String>();
/* 2185 */     this.serverVariables.put("character_set_server", "utf8");
/*      */     
/* 2187 */     this.io = new MysqlIO(newHost, newPort, mergedProps, getSocketFactoryClassName(), getProxy(), getSocketTimeout(), this.largeRowSizeThreshold.getValueAsInt());
/*      */     
/* 2189 */     this.io.doHandshake(this.user, this.password, this.database);
/* 2190 */     if (versionMeetsMinimum(5, 5, 0))
/*      */     {
/* 2192 */       this.errorMessageEncoding = this.io.getEncodingForHandshake();
/*      */     }
/*      */   }
/*      */   
/*      */   private String normalizeHost(String hostname) {
/* 2197 */     if (hostname == null || StringUtils.isEmptyOrWhitespaceOnly(hostname)) {
/* 2198 */       return "localhost";
/*      */     }
/*      */     
/* 2201 */     return hostname;
/*      */   }
/*      */   
/*      */   private int parsePortNumber(String portAsString) throws SQLException {
/* 2205 */     int portNumber = 3306;
/*      */     try {
/* 2207 */       portNumber = Integer.parseInt(portAsString);
/* 2208 */     } catch (NumberFormatException nfe) {
/* 2209 */       throw SQLError.createSQLException("Illegal connection port value '" + portAsString + "'", "01S00", getExceptionInterceptor());
/*      */     } 
/*      */     
/* 2212 */     return portNumber;
/*      */   }
/*      */   
/*      */   private void connectOneTryOnly(boolean isForReconnect, Properties mergedProps) throws SQLException {
/* 2216 */     Exception connectionNotEstablishedBecause = null;
/*      */ 
/*      */     
/*      */     try {
/* 2220 */       coreConnect(mergedProps);
/* 2221 */       this.connectionId = this.io.getThreadId();
/* 2222 */       this.isClosed = false;
/*      */ 
/*      */       
/* 2225 */       boolean oldAutoCommit = getAutoCommit();
/* 2226 */       int oldIsolationLevel = this.isolationLevel;
/* 2227 */       boolean oldReadOnly = isReadOnly(false);
/* 2228 */       String oldCatalog = getCatalog();
/*      */       
/* 2230 */       this.io.setStatementInterceptors(this.statementInterceptors);
/*      */ 
/*      */       
/* 2233 */       initializePropsFromServer();
/*      */       
/* 2235 */       if (isForReconnect) {
/*      */         
/* 2237 */         setAutoCommit(oldAutoCommit);
/*      */         
/* 2239 */         if (this.hasIsolationLevels) {
/* 2240 */           setTransactionIsolation(oldIsolationLevel);
/*      */         }
/*      */         
/* 2243 */         setCatalog(oldCatalog);
/*      */         
/* 2245 */         setReadOnly(oldReadOnly);
/*      */       } 
/*      */       
/*      */       return;
/* 2249 */     } catch (Exception EEE) {
/*      */       
/* 2251 */       if (EEE instanceof SQLException && ((SQLException)EEE).getErrorCode() == 1820 && !getDisconnectOnExpiredPasswords()) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 2256 */       if (this.io != null) {
/* 2257 */         this.io.forceClose();
/*      */       }
/*      */       
/* 2260 */       connectionNotEstablishedBecause = EEE;
/*      */       
/* 2262 */       if (EEE instanceof SQLException) {
/* 2263 */         throw (SQLException)EEE;
/*      */       }
/*      */       
/* 2266 */       SQLException chainedEx = SQLError.createSQLException(Messages.getString("Connection.UnableToConnect"), "08001", getExceptionInterceptor());
/*      */       
/* 2268 */       chainedEx.initCause(connectionNotEstablishedBecause);
/*      */       
/* 2270 */       throw chainedEx;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void createPreparedStatementCaches() throws SQLException {
/* 2275 */     synchronized (getConnectionMutex()) {
/* 2276 */       int cacheSize = getPreparedStatementCacheSize();
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2281 */         Class<?> factoryClass = Class.forName(getParseInfoCacheFactory());
/*      */ 
/*      */         
/* 2284 */         CacheAdapterFactory<String, PreparedStatement.ParseInfo> cacheFactory = (CacheAdapterFactory<String, PreparedStatement.ParseInfo>)factoryClass.newInstance();
/*      */         
/* 2286 */         this.cachedPreparedStatementParams = cacheFactory.getInstance(this, this.myURL, getPreparedStatementCacheSize(), getPreparedStatementCacheSqlLimit(), this.props);
/*      */       
/*      */       }
/* 2289 */       catch (ClassNotFoundException e) {
/* 2290 */         SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.CantFindCacheFactory", new Object[] { getParseInfoCacheFactory(), "parseInfoCacheFactory" }), getExceptionInterceptor());
/*      */ 
/*      */         
/* 2293 */         sqlEx.initCause(e);
/*      */         
/* 2295 */         throw sqlEx;
/* 2296 */       } catch (InstantiationException e) {
/* 2297 */         SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.CantLoadCacheFactory", new Object[] { getParseInfoCacheFactory(), "parseInfoCacheFactory" }), getExceptionInterceptor());
/*      */ 
/*      */         
/* 2300 */         sqlEx.initCause(e);
/*      */         
/* 2302 */         throw sqlEx;
/* 2303 */       } catch (IllegalAccessException e) {
/* 2304 */         SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.CantLoadCacheFactory", new Object[] { getParseInfoCacheFactory(), "parseInfoCacheFactory" }), getExceptionInterceptor());
/*      */ 
/*      */         
/* 2307 */         sqlEx.initCause(e);
/*      */         
/* 2309 */         throw sqlEx;
/*      */       } 
/*      */       
/* 2312 */       if (getUseServerPreparedStmts()) {
/* 2313 */         this.serverSideStatementCheckCache = new LRUCache(cacheSize);
/*      */         
/* 2315 */         this.serverSideStatementCache = new LRUCache<CompoundCacheKey, ServerPreparedStatement>(cacheSize)
/*      */           {
/*      */             private static final long serialVersionUID = 7692318650375988114L;
/*      */ 
/*      */             
/*      */             protected boolean removeEldestEntry(Map.Entry<ConnectionImpl.CompoundCacheKey, ServerPreparedStatement> eldest) {
/* 2321 */               if (this.maxElements <= 1) {
/* 2322 */                 return false;
/*      */               }
/*      */               
/* 2325 */               boolean removeIt = super.removeEldestEntry(eldest);
/*      */               
/* 2327 */               if (removeIt) {
/* 2328 */                 ServerPreparedStatement ps = eldest.getValue();
/* 2329 */                 ps.isCached = false;
/* 2330 */                 ps.setClosed(false);
/*      */                 
/*      */                 try {
/* 2333 */                   ps.close();
/* 2334 */                 } catch (SQLException sqlEx) {}
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/* 2339 */               return removeIt;
/*      */             }
/*      */           };
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
/*      */   public Statement createStatement() throws SQLException {
/* 2356 */     return createStatement(1003, 1007);
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
/*      */   public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
/* 2372 */     checkClosed();
/*      */     
/* 2374 */     StatementImpl stmt = new StatementImpl(getMultiHostSafeProxy(), this.database);
/* 2375 */     stmt.setResultSetType(resultSetType);
/* 2376 */     stmt.setResultSetConcurrency(resultSetConcurrency);
/*      */     
/* 2378 */     return stmt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/* 2385 */     if (getPedantic() && 
/* 2386 */       resultSetHoldability != 1) {
/* 2387 */       throw SQLError.createSQLException("HOLD_CUSRORS_OVER_COMMIT is only supported holdability level", "S1009", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2392 */     return createStatement(resultSetType, resultSetConcurrency);
/*      */   }
/*      */   
/*      */   public void dumpTestcaseQuery(String query) {
/* 2396 */     System.err.println(query);
/*      */   }
/*      */   
/*      */   public Connection duplicate() throws SQLException {
/* 2400 */     return new ConnectionImpl(this.origHostToConnectTo, this.origPortToConnectTo, this.props, this.origDatabaseToConnectTo, this.myURL);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSetInternalMethods execSQL(StatementImpl callingStatement, String sql, int maxRows, Buffer packet, int resultSetType, int resultSetConcurrency, boolean streamResults, String catalog, Field[] cachedMetadata) throws SQLException {
/* 2439 */     return execSQL(callingStatement, sql, maxRows, packet, resultSetType, resultSetConcurrency, streamResults, catalog, cachedMetadata, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public ResultSetInternalMethods execSQL(StatementImpl callingStatement, String sql, int maxRows, Buffer packet, int resultSetType, int resultSetConcurrency, boolean streamResults, String catalog, Field[] cachedMetadata, boolean isBatch) throws SQLException {
/* 2444 */     synchronized (getConnectionMutex()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2449 */       long queryStartTime = getGatherPerformanceMetrics() ? System.currentTimeMillis() : 0L;
/* 2450 */       int endOfQueryPacketPosition = (packet != null) ? packet.getPosition() : 0;
/*      */       
/* 2452 */       this.lastQueryFinishedTime = 0L;
/*      */       
/* 2454 */       if (getHighAvailability() && (this.autoCommit || getAutoReconnectForPools()) && this.needsPing && !isBatch) {
/*      */         try {
/* 2456 */           pingInternal(false, 0);
/*      */           
/* 2458 */           this.needsPing = false;
/* 2459 */         } catch (Exception Ex) {
/* 2460 */           createNewIO(true);
/*      */         } 
/*      */       }
/*      */       
/*      */       try {
/* 2465 */         return (packet == null) ? this.io.sqlQueryDirect(callingStatement, sql, getUseUnicode() ? getEncoding() : null, null, maxRows, resultSetType, resultSetConcurrency, streamResults, catalog, cachedMetadata) : this.io.sqlQueryDirect(callingStatement, null, null, packet, maxRows, resultSetType, resultSetConcurrency, streamResults, catalog, cachedMetadata);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 2471 */       catch (SQLException sqlE) {
/*      */ 
/*      */         
/* 2474 */         if (getDumpQueriesOnException()) {
/* 2475 */           String extractedSql = extractSqlFromPacket(sql, packet, endOfQueryPacketPosition);
/* 2476 */           StringBuilder messageBuf = new StringBuilder(extractedSql.length() + 32);
/* 2477 */           messageBuf.append("\n\nQuery being executed when exception was thrown:\n");
/* 2478 */           messageBuf.append(extractedSql);
/* 2479 */           messageBuf.append("\n\n");
/*      */           
/* 2481 */           sqlE = appendMessageToException(sqlE, messageBuf.toString(), getExceptionInterceptor());
/*      */         } 
/*      */         
/* 2484 */         if (getHighAvailability()) {
/* 2485 */           if ("08S01".equals(sqlE.getSQLState()))
/*      */           {
/* 2487 */             this.io.forceClose();
/*      */           }
/* 2489 */           this.needsPing = true;
/* 2490 */         } else if ("08S01".equals(sqlE.getSQLState())) {
/* 2491 */           cleanup(sqlE);
/*      */         } 
/*      */         
/* 2494 */         throw sqlE;
/* 2495 */       } catch (Exception ex) {
/* 2496 */         if (getHighAvailability()) {
/* 2497 */           if (ex instanceof IOException)
/*      */           {
/* 2499 */             this.io.forceClose();
/*      */           }
/* 2501 */           this.needsPing = true;
/* 2502 */         } else if (ex instanceof IOException) {
/* 2503 */           cleanup(ex);
/*      */         } 
/*      */         
/* 2506 */         SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.UnexpectedException"), "S1000", getExceptionInterceptor());
/*      */         
/* 2508 */         sqlEx.initCause(ex);
/*      */         
/* 2510 */         throw sqlEx;
/*      */       } finally {
/* 2512 */         if (getMaintainTimeStats()) {
/* 2513 */           this.lastQueryFinishedTime = System.currentTimeMillis();
/*      */         }
/*      */         
/* 2516 */         if (getGatherPerformanceMetrics()) {
/* 2517 */           registerQueryExecutionTime(System.currentTimeMillis() - queryStartTime);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String extractSqlFromPacket(String possibleSqlQuery, Buffer queryPacket, int endOfQueryPacketPosition) throws SQLException {
/* 2525 */     String extractedSql = null;
/*      */     
/* 2527 */     if (possibleSqlQuery != null) {
/* 2528 */       if (possibleSqlQuery.length() > getMaxQuerySizeToLog()) {
/* 2529 */         StringBuilder truncatedQueryBuf = new StringBuilder(possibleSqlQuery.substring(0, getMaxQuerySizeToLog()));
/* 2530 */         truncatedQueryBuf.append(Messages.getString("MysqlIO.25"));
/* 2531 */         extractedSql = truncatedQueryBuf.toString();
/*      */       } else {
/* 2533 */         extractedSql = possibleSqlQuery;
/*      */       } 
/*      */     }
/*      */     
/* 2537 */     if (extractedSql == null) {
/*      */ 
/*      */       
/* 2540 */       int extractPosition = endOfQueryPacketPosition;
/*      */       
/* 2542 */       boolean truncated = false;
/*      */       
/* 2544 */       if (endOfQueryPacketPosition > getMaxQuerySizeToLog()) {
/* 2545 */         extractPosition = getMaxQuerySizeToLog();
/* 2546 */         truncated = true;
/*      */       } 
/*      */       
/* 2549 */       extractedSql = StringUtils.toString(queryPacket.getByteBuffer(), 5, extractPosition - 5);
/*      */       
/* 2551 */       if (truncated) {
/* 2552 */         extractedSql = extractedSql + Messages.getString("MysqlIO.25");
/*      */       }
/*      */     } 
/*      */     
/* 2556 */     return extractedSql;
/*      */   }
/*      */ 
/*      */   
/*      */   public StringBuilder generateConnectionCommentBlock(StringBuilder buf) {
/* 2561 */     buf.append("/* conn id ");
/* 2562 */     buf.append(getId());
/* 2563 */     buf.append(" clock: ");
/* 2564 */     buf.append(System.currentTimeMillis());
/* 2565 */     buf.append(" */ ");
/*      */     
/* 2567 */     return buf;
/*      */   }
/*      */   
/*      */   public int getActiveStatementCount() {
/* 2571 */     return this.openStatements.size();
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
/*      */   public boolean getAutoCommit() throws SQLException {
/* 2583 */     synchronized (getConnectionMutex()) {
/* 2584 */       return this.autoCommit;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getCalendarInstanceForSessionOrNew() {
/* 2593 */     if (getDynamicCalendars()) {
/* 2594 */       return Calendar.getInstance();
/*      */     }
/*      */     
/* 2597 */     return getSessionLockedCalendar();
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
/*      */   public String getCatalog() throws SQLException {
/* 2612 */     synchronized (getConnectionMutex()) {
/* 2613 */       return this.database;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCharacterSetMetadata() {
/* 2621 */     synchronized (getConnectionMutex()) {
/* 2622 */       return this.characterSetMetadata;
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
/*      */   public SingleByteCharsetConverter getCharsetConverter(String javaEncodingName) throws SQLException {
/* 2635 */     if (javaEncodingName == null) {
/* 2636 */       return null;
/*      */     }
/*      */     
/* 2639 */     if (this.usePlatformCharsetConverters) {
/* 2640 */       return null;
/*      */     }
/*      */     
/* 2643 */     SingleByteCharsetConverter converter = null;
/*      */     
/* 2645 */     synchronized (this.charsetConverterMap) {
/* 2646 */       Object asObject = this.charsetConverterMap.get(javaEncodingName);
/*      */       
/* 2648 */       if (asObject == CHARSET_CONVERTER_NOT_AVAILABLE_MARKER) {
/* 2649 */         return null;
/*      */       }
/*      */       
/* 2652 */       converter = (SingleByteCharsetConverter)asObject;
/*      */       
/* 2654 */       if (converter == null) {
/*      */         try {
/* 2656 */           converter = SingleByteCharsetConverter.getInstance(javaEncodingName, this);
/*      */           
/* 2658 */           if (converter == null) {
/* 2659 */             this.charsetConverterMap.put(javaEncodingName, CHARSET_CONVERTER_NOT_AVAILABLE_MARKER);
/*      */           } else {
/* 2661 */             this.charsetConverterMap.put(javaEncodingName, converter);
/*      */           } 
/* 2663 */         } catch (UnsupportedEncodingException unsupEncEx) {
/* 2664 */           this.charsetConverterMap.put(javaEncodingName, CHARSET_CONVERTER_NOT_AVAILABLE_MARKER);
/*      */           
/* 2666 */           converter = null;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 2671 */     return converter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String getCharsetNameForIndex(int charsetIndex) throws SQLException {
/* 2679 */     return getEncodingForIndex(charsetIndex);
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
/*      */   public String getEncodingForIndex(int charsetIndex) throws SQLException {
/* 2693 */     String javaEncoding = null;
/*      */     
/* 2695 */     if (getUseOldUTF8Behavior()) {
/* 2696 */       return getEncoding();
/*      */     }
/*      */     
/* 2699 */     if (charsetIndex != -1) {
/*      */ 
/*      */       
/*      */       try {
/* 2703 */         if (this.indexToCustomMysqlCharset != null) {
/* 2704 */           String cs = this.indexToCustomMysqlCharset.get(Integer.valueOf(charsetIndex));
/* 2705 */           if (cs != null) {
/* 2706 */             javaEncoding = CharsetMapping.getJavaEncodingForMysqlCharset(cs, getEncoding());
/*      */           }
/*      */         } 
/*      */         
/* 2710 */         if (javaEncoding == null) {
/* 2711 */           javaEncoding = CharsetMapping.getJavaEncodingForCollationIndex(Integer.valueOf(charsetIndex), getEncoding());
/*      */         }
/*      */       }
/* 2714 */       catch (ArrayIndexOutOfBoundsException outOfBoundsEx) {
/* 2715 */         throw SQLError.createSQLException("Unknown character set index for field '" + charsetIndex + "' received from server.", "S1000", getExceptionInterceptor());
/*      */       }
/* 2717 */       catch (RuntimeException ex) {
/* 2718 */         SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 2719 */         sqlEx.initCause(ex);
/* 2720 */         throw sqlEx;
/*      */       } 
/*      */ 
/*      */       
/* 2724 */       if (javaEncoding == null) {
/* 2725 */         javaEncoding = getEncoding();
/*      */       }
/*      */     } else {
/* 2728 */       javaEncoding = getEncoding();
/*      */     } 
/*      */     
/* 2731 */     return javaEncoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TimeZone getDefaultTimeZone() {
/* 2739 */     return getCacheDefaultTimezone() ? this.defaultTimeZone : TimeUtil.getDefaultTimeZone(false);
/*      */   }
/*      */   
/*      */   public String getErrorMessageEncoding() {
/* 2743 */     return this.errorMessageEncoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHoldability() throws SQLException {
/* 2750 */     return 2;
/*      */   }
/*      */   
/*      */   public long getId() {
/* 2754 */     return this.connectionId;
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
/*      */   public long getIdleFor() {
/* 2766 */     synchronized (getConnectionMutex()) {
/* 2767 */       return (this.lastQueryFinishedTime == 0L) ? 0L : (System.currentTimeMillis() - this.lastQueryFinishedTime);
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
/*      */   public MysqlIO getIO() throws SQLException {
/* 2779 */     if (this.io == null || this.isClosed) {
/* 2780 */       throw SQLError.createSQLException("Operation not allowed on closed connection", "08003", getExceptionInterceptor());
/*      */     }
/*      */     
/* 2783 */     return this.io;
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
/*      */   public Log getLog() throws SQLException {
/* 2795 */     return this.log;
/*      */   }
/*      */   
/*      */   public int getMaxBytesPerChar(String javaCharsetName) throws SQLException {
/* 2799 */     return getMaxBytesPerChar((Integer)null, javaCharsetName);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxBytesPerChar(Integer charsetIndex, String javaCharsetName) throws SQLException {
/* 2804 */     String charset = null;
/* 2805 */     int res = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2812 */       if (this.indexToCustomMysqlCharset != null) {
/* 2813 */         charset = this.indexToCustomMysqlCharset.get(charsetIndex);
/*      */       }
/*      */       
/* 2816 */       if (charset == null) {
/* 2817 */         charset = CharsetMapping.getMysqlCharsetNameForCollationIndex(charsetIndex);
/*      */       }
/*      */ 
/*      */       
/* 2821 */       if (charset == null) {
/* 2822 */         charset = CharsetMapping.getMysqlCharsetForJavaEncoding(javaCharsetName, this);
/*      */       }
/*      */ 
/*      */       
/* 2826 */       Integer mblen = null;
/* 2827 */       if (this.mysqlCharsetToCustomMblen != null) {
/* 2828 */         mblen = this.mysqlCharsetToCustomMblen.get(charset);
/*      */       }
/*      */ 
/*      */       
/* 2832 */       if (mblen == null) {
/* 2833 */         mblen = Integer.valueOf(CharsetMapping.getMblen(charset));
/*      */       }
/*      */       
/* 2836 */       if (mblen != null) {
/* 2837 */         res = mblen.intValue();
/*      */       }
/* 2839 */     } catch (SQLException ex) {
/* 2840 */       throw ex;
/* 2841 */     } catch (RuntimeException ex) {
/* 2842 */       SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 2843 */       sqlEx.initCause(ex);
/* 2844 */       throw sqlEx;
/*      */     } 
/*      */     
/* 2847 */     return res;
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
/*      */   public DatabaseMetaData getMetaData() throws SQLException {
/* 2861 */     return getMetaData(true, true);
/*      */   }
/*      */   
/*      */   private DatabaseMetaData getMetaData(boolean checkClosed, boolean checkForInfoSchema) throws SQLException {
/* 2865 */     if (checkClosed) {
/* 2866 */       checkClosed();
/*      */     }
/*      */     
/* 2869 */     return DatabaseMetaData.getInstance(getMultiHostSafeProxy(), this.database, checkForInfoSchema);
/*      */   }
/*      */   
/*      */   public Statement getMetadataSafeStatement() throws SQLException {
/* 2873 */     return getMetadataSafeStatement(0);
/*      */   }
/*      */   
/*      */   public Statement getMetadataSafeStatement(int maxRows) throws SQLException {
/* 2877 */     Statement stmt = createStatement();
/*      */     
/* 2879 */     stmt.setMaxRows((maxRows == -1) ? 0 : maxRows);
/*      */     
/* 2881 */     stmt.setEscapeProcessing(false);
/*      */     
/* 2883 */     if (stmt.getFetchSize() != 0) {
/* 2884 */       stmt.setFetchSize(0);
/*      */     }
/*      */     
/* 2887 */     return stmt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNetBufferLength() {
/* 2894 */     return this.netBufferLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String getServerCharacterEncoding() {
/* 2902 */     return getServerCharset();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getServerCharset() {
/* 2911 */     if (this.io.versionMeetsMinimum(4, 1, 0)) {
/* 2912 */       String charset = null;
/* 2913 */       if (this.indexToCustomMysqlCharset != null) {
/* 2914 */         charset = this.indexToCustomMysqlCharset.get(Integer.valueOf(this.io.serverCharsetIndex));
/*      */       }
/* 2916 */       if (charset == null) {
/* 2917 */         charset = CharsetMapping.getMysqlCharsetNameForCollationIndex(Integer.valueOf(this.io.serverCharsetIndex));
/*      */       }
/* 2919 */       return (charset != null) ? charset : this.serverVariables.get("character_set_server");
/*      */     } 
/* 2921 */     return this.serverVariables.get("character_set");
/*      */   }
/*      */   
/*      */   public int getServerMajorVersion() {
/* 2925 */     return this.io.getServerMajorVersion();
/*      */   }
/*      */   
/*      */   public int getServerMinorVersion() {
/* 2929 */     return this.io.getServerMinorVersion();
/*      */   }
/*      */   
/*      */   public int getServerSubMinorVersion() {
/* 2933 */     return this.io.getServerSubMinorVersion();
/*      */   }
/*      */   
/*      */   public TimeZone getServerTimezoneTZ() {
/* 2937 */     return this.serverTimezoneTZ;
/*      */   }
/*      */   
/*      */   public String getServerVariable(String variableName) {
/* 2941 */     if (this.serverVariables != null) {
/* 2942 */       return this.serverVariables.get(variableName);
/*      */     }
/*      */     
/* 2945 */     return null;
/*      */   }
/*      */   
/*      */   public String getServerVersion() {
/* 2949 */     return this.io.getServerVersion();
/*      */   }
/*      */ 
/*      */   
/*      */   public Calendar getSessionLockedCalendar() {
/* 2954 */     return this.sessionCalendar;
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
/*      */   public int getTransactionIsolation() throws SQLException {
/* 2966 */     synchronized (getConnectionMutex()) {
/* 2967 */       if (this.hasIsolationLevels && !getUseLocalSessionState()) {
/* 2968 */         Statement stmt = null;
/* 2969 */         ResultSet rs = null;
/*      */         
/*      */         try {
/* 2972 */           stmt = getMetadataSafeStatement(this.sessionMaxRows);
/* 2973 */           String query = (versionMeetsMinimum(8, 0, 3) || (versionMeetsMinimum(5, 7, 20) && !versionMeetsMinimum(8, 0, 0))) ? "SELECT @@session.transaction_isolation" : "SELECT @@session.tx_isolation";
/*      */ 
/*      */           
/* 2976 */           rs = stmt.executeQuery(query);
/*      */           
/* 2978 */           if (rs.next()) {
/* 2979 */             String s = rs.getString(1);
/*      */             
/* 2981 */             if (s != null) {
/* 2982 */               Integer intTI = mapTransIsolationNameToValue.get(s);
/*      */               
/* 2984 */               if (intTI != null) {
/* 2985 */                 this.isolationLevel = intTI.intValue();
/* 2986 */                 return this.isolationLevel;
/*      */               } 
/*      */             } 
/*      */             
/* 2990 */             throw SQLError.createSQLException("Could not map transaction isolation '" + s + " to a valid JDBC level.", "S1000", getExceptionInterceptor());
/*      */           } 
/*      */ 
/*      */           
/* 2994 */           throw SQLError.createSQLException("Could not retrieve transaction isolation level from server", "S1000", getExceptionInterceptor());
/*      */         }
/*      */         finally {
/*      */           
/* 2998 */           if (rs != null) {
/*      */             try {
/* 3000 */               rs.close();
/* 3001 */             } catch (Exception ex) {}
/*      */ 
/*      */ 
/*      */             
/* 3005 */             rs = null;
/*      */           } 
/*      */           
/* 3008 */           if (stmt != null) {
/*      */             try {
/* 3010 */               stmt.close();
/* 3011 */             } catch (Exception ex) {}
/*      */ 
/*      */ 
/*      */             
/* 3015 */             stmt = null;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 3020 */       return this.isolationLevel;
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
/*      */   public Map<String, Class<?>> getTypeMap() throws SQLException {
/* 3033 */     synchronized (getConnectionMutex()) {
/* 3034 */       if (this.typeMap == null) {
/* 3035 */         this.typeMap = new HashMap<String, Class<?>>();
/*      */       }
/*      */       
/* 3038 */       return this.typeMap;
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getURL() {
/* 3043 */     return this.myURL;
/*      */   }
/*      */   
/*      */   public String getUser() {
/* 3047 */     return this.user;
/*      */   }
/*      */   
/*      */   public Calendar getUtcCalendar() {
/* 3051 */     return this.utcCalendar;
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
/*      */   public SQLWarning getWarnings() throws SQLException {
/* 3064 */     return null;
/*      */   }
/*      */   
/*      */   public boolean hasSameProperties(Connection c) {
/* 3068 */     return this.props.equals(c.getProperties());
/*      */   }
/*      */   
/*      */   public Properties getProperties() {
/* 3072 */     return this.props;
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public boolean hasTriedMaster() {
/* 3077 */     return this.hasTriedMasterFlag;
/*      */   }
/*      */   
/*      */   public void incrementNumberOfPreparedExecutes() {
/* 3081 */     if (getGatherPerformanceMetrics()) {
/* 3082 */       this.numberOfPreparedExecutes++;
/*      */ 
/*      */       
/* 3085 */       this.numberOfQueriesIssued++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void incrementNumberOfPrepares() {
/* 3090 */     if (getGatherPerformanceMetrics()) {
/* 3091 */       this.numberOfPrepares++;
/*      */     }
/*      */   }
/*      */   
/*      */   public void incrementNumberOfResultSetsCreated() {
/* 3096 */     if (getGatherPerformanceMetrics()) {
/* 3097 */       this.numberOfResultSetsCreated++;
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
/*      */   private void initializeDriverProperties(Properties info) throws SQLException {
/* 3109 */     initializeProperties(info);
/*      */     
/* 3111 */     String exceptionInterceptorClasses = getExceptionInterceptors();
/*      */     
/* 3113 */     if (exceptionInterceptorClasses != null && !"".equals(exceptionInterceptorClasses)) {
/* 3114 */       this.exceptionInterceptor = new ExceptionInterceptorChain(exceptionInterceptorClasses);
/*      */     }
/*      */     
/* 3117 */     this.usePlatformCharsetConverters = getUseJvmCharsetConverters();
/*      */     
/* 3119 */     this.log = LogFactory.getLogger(getLogger(), "MySQL", getExceptionInterceptor());
/*      */     
/* 3121 */     if (getProfileSql() || getLogSlowQueries() || getUseUsageAdvisor()) {
/* 3122 */       this.eventSink = ProfilerEventHandlerFactory.getInstance(getMultiHostSafeProxy());
/*      */     }
/*      */     
/* 3125 */     if (getCachePreparedStatements()) {
/* 3126 */       createPreparedStatementCaches();
/*      */     }
/*      */     
/* 3129 */     if (getNoDatetimeStringSync() && getUseTimezone()) {
/* 3130 */       throw SQLError.createSQLException("Can't enable noDatetimeStringSync and useTimezone configuration properties at the same time", "01S00", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 3134 */     if (getCacheCallableStatements()) {
/* 3135 */       this.parsedCallableStatementCache = new LRUCache(getCallableStatementCacheSize());
/*      */     }
/*      */     
/* 3138 */     if (getAllowMultiQueries()) {
/* 3139 */       setCacheResultSetMetadata(false);
/*      */     }
/*      */     
/* 3142 */     if (getCacheResultSetMetadata()) {
/* 3143 */       this.resultSetMetadataCache = new LRUCache(getMetadataCacheSize());
/*      */     }
/*      */     
/* 3146 */     if (getSocksProxyHost() != null) {
/* 3147 */       setSocketFactoryClassName("com.mysql.jdbc.SocksProxySocketFactory");
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
/*      */   private void initializePropsFromServer() throws SQLException {
/* 3159 */     String connectionInterceptorClasses = getConnectionLifecycleInterceptors();
/*      */     
/* 3161 */     this.connectionLifecycleInterceptors = null;
/*      */     
/* 3163 */     if (connectionInterceptorClasses != null) {
/* 3164 */       this.connectionLifecycleInterceptors = Util.loadExtensions(this, this.props, connectionInterceptorClasses, "Connection.badLifecycleInterceptor", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 3168 */     setSessionVariables();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3174 */     if (!versionMeetsMinimum(4, 1, 0)) {
/* 3175 */       setTransformedBitIsBoolean(false);
/*      */     }
/*      */     
/* 3178 */     this.parserKnowsUnicode = versionMeetsMinimum(4, 1, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3183 */     if (getUseServerPreparedStmts() && versionMeetsMinimum(4, 1, 0)) {
/* 3184 */       this.useServerPreparedStmts = true;
/*      */       
/* 3186 */       if (versionMeetsMinimum(5, 0, 0) && !versionMeetsMinimum(5, 0, 3)) {
/* 3187 */         this.useServerPreparedStmts = false;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3195 */     if (versionMeetsMinimum(3, 21, 22)) {
/* 3196 */       loadServerVariables();
/*      */       
/* 3198 */       if (versionMeetsMinimum(5, 0, 2)) {
/* 3199 */         this.autoIncrementIncrement = getServerVariableAsInt("auto_increment_increment", 1);
/*      */       } else {
/* 3201 */         this.autoIncrementIncrement = 1;
/*      */       } 
/*      */       
/* 3204 */       buildCollationMapping();
/*      */ 
/*      */ 
/*      */       
/* 3208 */       if (this.io.serverCharsetIndex == 0) {
/* 3209 */         String collationServer = this.serverVariables.get("collation_server");
/* 3210 */         if (collationServer != null) {
/* 3211 */           for (int i = 1; i < CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME.length; i++) {
/* 3212 */             if (CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME[i].equals(collationServer)) {
/* 3213 */               this.io.serverCharsetIndex = i;
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } else {
/* 3219 */           this.io.serverCharsetIndex = 45;
/*      */         } 
/*      */       } 
/*      */       
/* 3223 */       LicenseConfiguration.checkLicenseType(this.serverVariables);
/*      */       
/* 3225 */       String lowerCaseTables = this.serverVariables.get("lower_case_table_names");
/*      */       
/* 3227 */       this.lowerCaseTableNames = ("on".equalsIgnoreCase(lowerCaseTables) || "1".equalsIgnoreCase(lowerCaseTables) || "2".equalsIgnoreCase(lowerCaseTables));
/*      */       
/* 3229 */       this.storesLowerCaseTableName = ("1".equalsIgnoreCase(lowerCaseTables) || "on".equalsIgnoreCase(lowerCaseTables));
/*      */       
/* 3231 */       configureTimezone();
/*      */       
/* 3233 */       if (this.serverVariables.containsKey("max_allowed_packet")) {
/* 3234 */         int serverMaxAllowedPacket = getServerVariableAsInt("max_allowed_packet", -1);
/*      */         
/* 3236 */         if (serverMaxAllowedPacket != -1 && (serverMaxAllowedPacket < getMaxAllowedPacket() || getMaxAllowedPacket() <= 0)) {
/* 3237 */           setMaxAllowedPacket(serverMaxAllowedPacket);
/* 3238 */         } else if (serverMaxAllowedPacket == -1 && getMaxAllowedPacket() == -1) {
/* 3239 */           setMaxAllowedPacket(65535);
/*      */         } 
/*      */         
/* 3242 */         if (getUseServerPrepStmts()) {
/* 3243 */           int preferredBlobSendChunkSize = getBlobSendChunkSize();
/*      */ 
/*      */           
/* 3246 */           int packetHeaderSize = 8203;
/* 3247 */           int allowedBlobSendChunkSize = Math.min(preferredBlobSendChunkSize, getMaxAllowedPacket()) - packetHeaderSize;
/*      */           
/* 3249 */           if (allowedBlobSendChunkSize <= 0) {
/* 3250 */             throw SQLError.createSQLException("Connection setting too low for 'maxAllowedPacket'. When 'useServerPrepStmts=true', 'maxAllowedPacket' must be higher than " + packetHeaderSize + ". Check also 'max_allowed_packet' in MySQL configuration files.", "01S00", getExceptionInterceptor());
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3257 */           setBlobSendChunkSize(String.valueOf(allowedBlobSendChunkSize));
/*      */         } 
/*      */       } 
/*      */       
/* 3261 */       if (this.serverVariables.containsKey("net_buffer_length")) {
/* 3262 */         this.netBufferLength = getServerVariableAsInt("net_buffer_length", 16384);
/*      */       }
/*      */       
/* 3265 */       checkTransactionIsolationLevel();
/*      */       
/* 3267 */       if (!versionMeetsMinimum(4, 1, 0)) {
/* 3268 */         checkServerEncoding();
/*      */       }
/*      */       
/* 3271 */       this.io.checkForCharsetMismatch();
/*      */       
/* 3273 */       if (this.serverVariables.containsKey("sql_mode")) {
/* 3274 */         String sqlModeAsString = this.serverVariables.get("sql_mode");
/* 3275 */         if (StringUtils.isStrictlyNumeric(sqlModeAsString)) {
/*      */           
/* 3277 */           this.useAnsiQuotes = ((Integer.parseInt(sqlModeAsString) & 0x4) > 0);
/* 3278 */         } else if (sqlModeAsString != null) {
/* 3279 */           this.useAnsiQuotes = (sqlModeAsString.indexOf("ANSI_QUOTES") != -1);
/* 3280 */           this.noBackslashEscapes = (sqlModeAsString.indexOf("NO_BACKSLASH_ESCAPES") != -1);
/* 3281 */           this.serverTruncatesFracSecs = (sqlModeAsString.indexOf("TIME_TRUNCATE_FRACTIONAL") != -1);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3286 */     configureClientCharacterSet(false);
/*      */     
/*      */     try {
/* 3289 */       this.errorMessageEncoding = CharsetMapping.getCharacterEncodingForErrorMessages(this);
/* 3290 */     } catch (SQLException ex) {
/* 3291 */       throw ex;
/* 3292 */     } catch (RuntimeException ex) {
/* 3293 */       SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 3294 */       sqlEx.initCause(ex);
/* 3295 */       throw sqlEx;
/*      */     } 
/*      */     
/* 3298 */     if (versionMeetsMinimum(3, 23, 15)) {
/* 3299 */       this.transactionsSupported = true;
/* 3300 */       handleAutoCommitDefaults();
/*      */     } else {
/* 3302 */       this.transactionsSupported = false;
/*      */     } 
/*      */     
/* 3305 */     if (versionMeetsMinimum(3, 23, 36)) {
/* 3306 */       this.hasIsolationLevels = true;
/*      */     } else {
/* 3308 */       this.hasIsolationLevels = false;
/*      */     } 
/*      */     
/* 3311 */     this.hasQuotedIdentifiers = versionMeetsMinimum(3, 23, 6);
/*      */     
/* 3313 */     this.io.resetMaxBuf();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3319 */     if (this.io.versionMeetsMinimum(4, 1, 0)) {
/* 3320 */       String characterSetResultsOnServerMysql = this.serverVariables.get("jdbc.local.character_set_results");
/*      */       
/* 3322 */       if (characterSetResultsOnServerMysql == null || StringUtils.startsWithIgnoreCaseAndWs(characterSetResultsOnServerMysql, "NULL") || characterSetResultsOnServerMysql.length() == 0) {
/*      */         
/* 3324 */         String defaultMetadataCharsetMysql = this.serverVariables.get("character_set_system");
/* 3325 */         String defaultMetadataCharset = null;
/*      */         
/* 3327 */         if (defaultMetadataCharsetMysql != null) {
/* 3328 */           defaultMetadataCharset = CharsetMapping.getJavaEncodingForMysqlCharset(defaultMetadataCharsetMysql);
/*      */         } else {
/* 3330 */           defaultMetadataCharset = "UTF-8";
/*      */         } 
/*      */         
/* 3333 */         this.characterSetMetadata = defaultMetadataCharset;
/*      */       } else {
/* 3335 */         this.characterSetResultsOnServer = CharsetMapping.getJavaEncodingForMysqlCharset(characterSetResultsOnServerMysql);
/* 3336 */         this.characterSetMetadata = this.characterSetResultsOnServer;
/*      */       } 
/*      */     } else {
/* 3339 */       this.characterSetMetadata = getEncoding();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3346 */     if (versionMeetsMinimum(4, 1, 0) && !versionMeetsMinimum(4, 1, 10) && getAllowMultiQueries() && 
/* 3347 */       isQueryCacheEnabled()) {
/* 3348 */       setAllowMultiQueries(false);
/*      */     }
/*      */ 
/*      */     
/* 3352 */     if (versionMeetsMinimum(5, 0, 0) && (getUseLocalTransactionState() || getElideSetAutoCommits()) && isQueryCacheEnabled() && !versionMeetsMinimum(5, 1, 32)) {
/*      */ 
/*      */       
/* 3355 */       setUseLocalTransactionState(false);
/* 3356 */       setElideSetAutoCommits(false);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3363 */     setupServerForTruncationChecks();
/*      */   }
/*      */   
/*      */   public boolean isQueryCacheEnabled() {
/* 3367 */     return ("ON".equalsIgnoreCase(this.serverVariables.get("query_cache_type")) && !"0".equalsIgnoreCase(this.serverVariables.get("query_cache_size")));
/*      */   }
/*      */   
/*      */   private int getServerVariableAsInt(String variableName, int fallbackValue) throws SQLException {
/*      */     try {
/* 3372 */       return Integer.parseInt(this.serverVariables.get(variableName));
/* 3373 */     } catch (NumberFormatException nfe) {
/* 3374 */       getLog().logWarn(Messages.getString("Connection.BadValueInServerVariables", new Object[] { variableName, this.serverVariables.get(variableName), Integer.valueOf(fallbackValue) }));
/*      */ 
/*      */       
/* 3377 */       return fallbackValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void handleAutoCommitDefaults() throws SQLException {
/* 3386 */     boolean resetAutoCommitDefault = false;
/*      */     
/* 3388 */     if (!getElideSetAutoCommits()) {
/* 3389 */       String initConnectValue = this.serverVariables.get("init_connect");
/* 3390 */       if (versionMeetsMinimum(4, 1, 2) && initConnectValue != null && initConnectValue.length() > 0) {
/*      */         
/* 3392 */         ResultSet rs = null;
/* 3393 */         Statement stmt = null;
/*      */         
/*      */         try {
/* 3396 */           stmt = getMetadataSafeStatement();
/* 3397 */           rs = stmt.executeQuery("SELECT @@session.autocommit");
/* 3398 */           if (rs.next()) {
/* 3399 */             this.autoCommit = rs.getBoolean(1);
/* 3400 */             resetAutoCommitDefault = !this.autoCommit;
/*      */           } 
/*      */         } finally {
/* 3403 */           if (rs != null) {
/*      */             try {
/* 3405 */               rs.close();
/* 3406 */             } catch (SQLException sqlEx) {}
/*      */           }
/*      */ 
/*      */           
/* 3410 */           if (stmt != null) {
/*      */             try {
/* 3412 */               stmt.close();
/* 3413 */             } catch (SQLException sqlEx) {}
/*      */           }
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 3420 */         resetAutoCommitDefault = true;
/*      */       } 
/* 3422 */     } else if (getIO().isSetNeededForAutoCommitMode(true)) {
/*      */       
/* 3424 */       this.autoCommit = false;
/* 3425 */       resetAutoCommitDefault = true;
/*      */     } 
/*      */     
/* 3428 */     if (resetAutoCommitDefault) {
/*      */       try {
/* 3430 */         setAutoCommit(true);
/* 3431 */       } catch (SQLException ex) {
/* 3432 */         if (ex.getErrorCode() != 1820 || getDisconnectOnExpiredPasswords()) {
/* 3433 */           throw ex;
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isClientTzUTC() {
/* 3440 */     return this.isClientTzUTC;
/*      */   }
/*      */   
/*      */   public boolean isClosed() {
/* 3444 */     return this.isClosed;
/*      */   }
/*      */   
/*      */   public boolean isCursorFetchEnabled() throws SQLException {
/* 3448 */     return (versionMeetsMinimum(5, 0, 2) && getUseCursorFetch());
/*      */   }
/*      */   
/*      */   public boolean isInGlobalTx() {
/* 3452 */     return this.isInGlobalTx;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMasterConnection() {
/* 3463 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNoBackslashEscapesSet() {
/* 3473 */     return this.noBackslashEscapes;
/*      */   }
/*      */   
/*      */   public boolean isReadInfoMsgEnabled() {
/* 3477 */     return this.readInfoMsg;
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
/*      */   public boolean isReadOnly() throws SQLException {
/* 3490 */     return isReadOnly(true);
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
/*      */   public boolean isReadOnly(boolean useSessionStatus) throws SQLException {
/* 3507 */     if (useSessionStatus && !this.isClosed && versionMeetsMinimum(5, 6, 5) && !getUseLocalSessionState() && getReadOnlyPropagatesToServer()) {
/* 3508 */       Statement stmt = null;
/* 3509 */       ResultSet rs = null;
/*      */ 
/*      */       
/*      */       try {
/* 3513 */         stmt = getMetadataSafeStatement(this.sessionMaxRows);
/*      */         
/* 3515 */         rs = stmt.executeQuery((versionMeetsMinimum(8, 0, 3) || (versionMeetsMinimum(5, 7, 20) && !versionMeetsMinimum(8, 0, 0))) ? "select @@session.transaction_read_only" : "select @@session.tx_read_only");
/*      */ 
/*      */         
/* 3518 */         if (rs.next()) {
/* 3519 */           return (rs.getInt(1) != 0);
/*      */         }
/* 3521 */       } catch (SQLException ex1) {
/* 3522 */         if (ex1.getErrorCode() != 1820 || getDisconnectOnExpiredPasswords()) {
/* 3523 */           throw SQLError.createSQLException("Could not retrieve transaction read-only status from server", "S1000", ex1, getExceptionInterceptor());
/*      */         
/*      */         }
/*      */       }
/*      */       finally {
/*      */         
/* 3529 */         if (rs != null) {
/*      */           try {
/* 3531 */             rs.close();
/* 3532 */           } catch (Exception ex) {}
/*      */ 
/*      */ 
/*      */           
/* 3536 */           rs = null;
/*      */         } 
/*      */         
/* 3539 */         if (stmt != null) {
/*      */           try {
/* 3541 */             stmt.close();
/* 3542 */           } catch (Exception ex) {}
/*      */ 
/*      */ 
/*      */           
/* 3546 */           stmt = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3551 */     return this.readOnly;
/*      */   }
/*      */   
/*      */   public boolean isRunningOnJDK13() {
/* 3555 */     return this.isRunningOnJDK13;
/*      */   }
/*      */   
/*      */   public boolean isSameResource(Connection otherConnection) {
/* 3559 */     synchronized (getConnectionMutex()) {
/* 3560 */       if (otherConnection == null) {
/* 3561 */         return false;
/*      */       }
/*      */       
/* 3564 */       boolean directCompare = true;
/*      */       
/* 3566 */       String otherHost = ((ConnectionImpl)otherConnection).origHostToConnectTo;
/* 3567 */       String otherOrigDatabase = ((ConnectionImpl)otherConnection).origDatabaseToConnectTo;
/* 3568 */       String otherCurrentCatalog = ((ConnectionImpl)otherConnection).database;
/*      */       
/* 3570 */       if (!nullSafeCompare(otherHost, this.origHostToConnectTo)) {
/* 3571 */         directCompare = false;
/* 3572 */       } else if (otherHost != null && otherHost.indexOf(',') == -1 && otherHost.indexOf(':') == -1) {
/*      */         
/* 3574 */         directCompare = (((ConnectionImpl)otherConnection).origPortToConnectTo == this.origPortToConnectTo);
/*      */       } 
/*      */       
/* 3577 */       if (directCompare && (
/* 3578 */         !nullSafeCompare(otherOrigDatabase, this.origDatabaseToConnectTo) || !nullSafeCompare(otherCurrentCatalog, this.database))) {
/* 3579 */         directCompare = false;
/*      */       }
/*      */ 
/*      */       
/* 3583 */       if (directCompare) {
/* 3584 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 3588 */       String otherResourceId = ((ConnectionImpl)otherConnection).getResourceId();
/* 3589 */       String myResourceId = getResourceId();
/*      */       
/* 3591 */       if (otherResourceId != null || myResourceId != null) {
/* 3592 */         directCompare = nullSafeCompare(otherResourceId, myResourceId);
/*      */         
/* 3594 */         if (directCompare) {
/* 3595 */           return true;
/*      */         }
/*      */       } 
/*      */       
/* 3599 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isServerTzUTC() {
/* 3604 */     return this.isServerTzUTC;
/*      */   }
/*      */   
/*      */   private void createConfigCacheIfNeeded() throws SQLException {
/* 3608 */     synchronized (getConnectionMutex()) {
/* 3609 */       if (this.serverConfigCache != null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3616 */         Class<?> factoryClass = Class.forName(getServerConfigCacheFactory());
/*      */ 
/*      */         
/* 3619 */         CacheAdapterFactory<String, Map<String, String>> cacheFactory = (CacheAdapterFactory<String, Map<String, String>>)factoryClass.newInstance();
/*      */         
/* 3621 */         this.serverConfigCache = cacheFactory.getInstance(this, this.myURL, 2147483647, 2147483647, this.props);
/*      */         
/* 3623 */         ExceptionInterceptor evictOnCommsError = new ExceptionInterceptor()
/*      */           {
/*      */             public void init(Connection conn, Properties config) throws SQLException {}
/*      */ 
/*      */ 
/*      */             
/*      */             public void destroy() {}
/*      */ 
/*      */             
/*      */             public SQLException interceptException(SQLException sqlEx, Connection conn) {
/* 3633 */               if (sqlEx.getSQLState() != null && sqlEx.getSQLState().startsWith("08")) {
/* 3634 */                 ConnectionImpl.this.serverConfigCache.invalidate(ConnectionImpl.this.getURL());
/*      */               }
/* 3636 */               return null;
/*      */             }
/*      */           };
/*      */         
/* 3640 */         if (this.exceptionInterceptor == null) {
/* 3641 */           this.exceptionInterceptor = evictOnCommsError;
/*      */         } else {
/* 3643 */           ((ExceptionInterceptorChain)this.exceptionInterceptor).addRingZero(evictOnCommsError);
/*      */         } 
/* 3645 */       } catch (ClassNotFoundException e) {
/* 3646 */         SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.CantFindCacheFactory", new Object[] { getParseInfoCacheFactory(), "parseInfoCacheFactory" }), getExceptionInterceptor());
/*      */ 
/*      */         
/* 3649 */         sqlEx.initCause(e);
/*      */         
/* 3651 */         throw sqlEx;
/* 3652 */       } catch (InstantiationException e) {
/* 3653 */         SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.CantLoadCacheFactory", new Object[] { getParseInfoCacheFactory(), "parseInfoCacheFactory" }), getExceptionInterceptor());
/*      */ 
/*      */         
/* 3656 */         sqlEx.initCause(e);
/*      */         
/* 3658 */         throw sqlEx;
/* 3659 */       } catch (IllegalAccessException e) {
/* 3660 */         SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.CantLoadCacheFactory", new Object[] { getParseInfoCacheFactory(), "parseInfoCacheFactory" }), getExceptionInterceptor());
/*      */ 
/*      */         
/* 3663 */         sqlEx.initCause(e);
/*      */         
/* 3665 */         throw sqlEx;
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
/*      */   private void loadServerVariables() throws SQLException {
/* 3681 */     if (getCacheServerConfiguration()) {
/* 3682 */       createConfigCacheIfNeeded();
/*      */       
/* 3684 */       Map<String, String> cachedVariableMap = this.serverConfigCache.get(getURL());
/*      */       
/* 3686 */       if (cachedVariableMap != null) {
/* 3687 */         String cachedServerVersion = cachedVariableMap.get("server_version_string");
/*      */         
/* 3689 */         if (cachedServerVersion != null && this.io.getServerVersion() != null && cachedServerVersion.equals(this.io.getServerVersion())) {
/* 3690 */           this.serverVariables = cachedVariableMap;
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 3695 */         this.serverConfigCache.invalidate(getURL());
/*      */       } 
/*      */     } 
/*      */     
/* 3699 */     Statement stmt = null;
/* 3700 */     ResultSet results = null;
/*      */     
/*      */     try {
/* 3703 */       stmt = getMetadataSafeStatement();
/*      */       
/* 3705 */       String version = this.dbmd.getDriverVersion();
/*      */       
/* 3707 */       if (version != null && version.indexOf('*') != -1) {
/* 3708 */         StringBuilder buf = new StringBuilder(version.length() + 10);
/*      */         
/* 3710 */         for (int i = 0; i < version.length(); i++) {
/* 3711 */           char c = version.charAt(i);
/*      */           
/* 3713 */           if (c == '*') {
/* 3714 */             buf.append("[star]");
/*      */           } else {
/* 3716 */             buf.append(c);
/*      */           } 
/*      */         } 
/*      */         
/* 3720 */         version = buf.toString();
/*      */       } 
/*      */       
/* 3723 */       String versionComment = (getParanoid() || version == null) ? "" : ("/* " + version + " */");
/*      */       
/* 3725 */       this.serverVariables = new HashMap<String, String>();
/*      */       
/* 3727 */       boolean currentJdbcComplTrunc = getJdbcCompliantTruncation();
/* 3728 */       setJdbcCompliantTruncation(false);
/*      */       
/*      */       try {
/* 3731 */         if (versionMeetsMinimum(5, 1, 0)) {
/* 3732 */           StringBuilder queryBuf = (new StringBuilder(versionComment)).append("SELECT");
/* 3733 */           queryBuf.append("  @@session.auto_increment_increment AS auto_increment_increment");
/* 3734 */           queryBuf.append(", @@character_set_client AS character_set_client");
/* 3735 */           queryBuf.append(", @@character_set_connection AS character_set_connection");
/* 3736 */           queryBuf.append(", @@character_set_results AS character_set_results");
/* 3737 */           queryBuf.append(", @@character_set_server AS character_set_server");
/* 3738 */           queryBuf.append(", @@collation_server AS collation_server");
/* 3739 */           queryBuf.append(", @@collation_connection AS collation_connection");
/* 3740 */           queryBuf.append(", @@init_connect AS init_connect");
/* 3741 */           queryBuf.append(", @@interactive_timeout AS interactive_timeout");
/* 3742 */           if (!versionMeetsMinimum(5, 5, 0)) {
/* 3743 */             queryBuf.append(", @@language AS language");
/*      */           }
/* 3745 */           queryBuf.append(", @@license AS license");
/* 3746 */           queryBuf.append(", @@lower_case_table_names AS lower_case_table_names");
/* 3747 */           queryBuf.append(", @@max_allowed_packet AS max_allowed_packet");
/* 3748 */           queryBuf.append(", @@net_buffer_length AS net_buffer_length");
/* 3749 */           queryBuf.append(", @@net_write_timeout AS net_write_timeout");
/* 3750 */           if (versionMeetsMinimum(5, 5, 0)) {
/* 3751 */             queryBuf.append(", @@performance_schema AS performance_schema");
/*      */           }
/* 3753 */           if (!versionMeetsMinimum(8, 0, 3)) {
/* 3754 */             queryBuf.append(", @@query_cache_size AS query_cache_size");
/* 3755 */             queryBuf.append(", @@query_cache_type AS query_cache_type");
/*      */           } 
/* 3757 */           queryBuf.append(", @@sql_mode AS sql_mode");
/* 3758 */           queryBuf.append(", @@system_time_zone AS system_time_zone");
/* 3759 */           queryBuf.append(", @@time_zone AS time_zone");
/* 3760 */           if (versionMeetsMinimum(8, 0, 3) || (versionMeetsMinimum(5, 7, 20) && !versionMeetsMinimum(8, 0, 0))) {
/* 3761 */             queryBuf.append(", @@transaction_isolation AS transaction_isolation");
/*      */           } else {
/* 3763 */             queryBuf.append(", @@tx_isolation AS transaction_isolation");
/*      */           } 
/* 3765 */           queryBuf.append(", @@wait_timeout AS wait_timeout");
/*      */           
/* 3767 */           results = stmt.executeQuery(queryBuf.toString());
/* 3768 */           if (results.next()) {
/* 3769 */             ResultSetMetaData rsmd = results.getMetaData();
/* 3770 */             for (int i = 1; i <= rsmd.getColumnCount(); i++) {
/* 3771 */               this.serverVariables.put(rsmd.getColumnLabel(i), results.getString(i));
/*      */             }
/*      */           } 
/*      */         } else {
/* 3775 */           results = stmt.executeQuery(versionComment + "SHOW VARIABLES");
/* 3776 */           while (results.next()) {
/* 3777 */             this.serverVariables.put(results.getString(1), results.getString(2));
/*      */           }
/*      */         } 
/*      */         
/* 3781 */         results.close();
/* 3782 */         results = null;
/* 3783 */       } catch (SQLException ex) {
/* 3784 */         if (ex.getErrorCode() != 1820 || getDisconnectOnExpiredPasswords()) {
/* 3785 */           throw ex;
/*      */         }
/*      */       } finally {
/* 3788 */         setJdbcCompliantTruncation(currentJdbcComplTrunc);
/*      */       } 
/*      */       
/* 3791 */       if (getCacheServerConfiguration()) {
/* 3792 */         this.serverVariables.put("server_version_string", this.io.getServerVersion());
/*      */         
/* 3794 */         this.serverConfigCache.put(getURL(), this.serverVariables);
/*      */       }
/*      */     
/* 3797 */     } catch (SQLException e) {
/* 3798 */       throw e;
/*      */     } finally {
/* 3800 */       if (results != null) {
/*      */         try {
/* 3802 */           results.close();
/* 3803 */         } catch (SQLException sqlE) {}
/*      */       }
/*      */ 
/*      */       
/* 3807 */       if (stmt != null) {
/*      */         try {
/* 3809 */           stmt.close();
/* 3810 */         } catch (SQLException sqlE) {}
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected ConnectionImpl() {
/* 3816 */     this.autoIncrementIncrement = 0; } public ConnectionImpl(String hostToConnectTo, int portToConnectTo, Properties info, String databaseToConnectTo, String url) throws SQLException { this.autoIncrementIncrement = 0; this.connectionCreationTimeMillis = System.currentTimeMillis(); if (databaseToConnectTo == null)
/*      */       databaseToConnectTo = "";  this.origHostToConnectTo = hostToConnectTo; this.origPortToConnectTo = portToConnectTo; this.origDatabaseToConnectTo = databaseToConnectTo; try { Blob.class.getMethod("truncate", new Class[] { long.class }); this.isRunningOnJDK13 = false; } catch (NoSuchMethodException nsme) { this.isRunningOnJDK13 = true; }  this.sessionCalendar = new GregorianCalendar(); this.utcCalendar = new GregorianCalendar(); this.utcCalendar.setTimeZone(TimeZone.getTimeZone("GMT")); this.log = LogFactory.getLogger(getLogger(), "MySQL", getExceptionInterceptor()); if (NonRegisteringDriver.isHostPropertiesList(hostToConnectTo)) { Properties hostSpecificProps = NonRegisteringDriver.expandHostKeyValues(hostToConnectTo); Enumeration<?> propertyNames = hostSpecificProps.propertyNames(); while (propertyNames.hasMoreElements()) { String propertyName = propertyNames.nextElement().toString(); String propertyValue = hostSpecificProps.getProperty(propertyName); info.setProperty(propertyName, propertyValue); }  } else if (hostToConnectTo == null) { this.host = "localhost"; this.hostPortPair = this.host + ":" + portToConnectTo; } else { this.host = hostToConnectTo; if (hostToConnectTo.indexOf(":") == -1) { this.hostPortPair = this.host + ":" + portToConnectTo; } else { this.hostPortPair = this.host; }  }  this.port = portToConnectTo; this.database = databaseToConnectTo; this.myURL = url; this.user = info.getProperty("user"); this.password = info.getProperty("password"); if (this.user == null || this.user.equals(""))
/*      */       this.user = "";  if (this.password == null)
/* 3819 */       this.password = "";  this.props = info; initializeDriverProperties(info); this.defaultTimeZone = TimeUtil.getDefaultTimeZone(getCacheDefaultTimezone()); this.isClientTzUTC = (!this.defaultTimeZone.useDaylightTime() && this.defaultTimeZone.getRawOffset() == 0); try { this.dbmd = getMetaData(false, false); initializeSafeStatementInterceptors(); createNewIO(false); unSafeStatementInterceptors(); } catch (SQLException ex) { cleanup(ex); throw ex; } catch (Exception ex) { cleanup(ex); StringBuilder mesg = new StringBuilder(128); if (!getParanoid()) { mesg.append("Cannot connect to MySQL server on "); mesg.append(this.host); mesg.append(":"); mesg.append(this.port); mesg.append(".\n\n"); mesg.append("Make sure that there is a MySQL server "); mesg.append("running on the machine/port you are trying "); mesg.append("to connect to and that the machine this software is running on "); mesg.append("is able to connect to this host/port (i.e. not firewalled). "); mesg.append("Also make sure that the server has not been started with the --skip-networking "); mesg.append("flag.\n\n"); } else { mesg.append("Unable to connect to database."); }  SQLException sqlEx = SQLError.createSQLException(mesg.toString(), "08S01", getExceptionInterceptor()); sqlEx.initCause(ex); throw sqlEx; }  AbandonedConnectionCleanupThread.trackConnection(this, this.io.getNetworkResources()); } public int getAutoIncrementIncrement() { return this.autoIncrementIncrement; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean lowerCaseTableNames() {
/* 3828 */     return this.lowerCaseTableNames;
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
/*      */   public String nativeSQL(String sql) throws SQLException {
/* 3844 */     if (sql == null) {
/* 3845 */       return null;
/*      */     }
/*      */     
/* 3848 */     Object escapedSqlResult = EscapeProcessor.escapeSQL(sql, serverSupportsConvertFn(), getMultiHostSafeProxy());
/*      */     
/* 3850 */     if (escapedSqlResult instanceof String) {
/* 3851 */       return (String)escapedSqlResult;
/*      */     }
/*      */     
/* 3854 */     return ((EscapeProcessorResult)escapedSqlResult).escapedSql;
/*      */   }
/*      */   
/*      */   private CallableStatement parseCallableStatement(String sql) throws SQLException {
/* 3858 */     Object escapedSqlResult = EscapeProcessor.escapeSQL(sql, serverSupportsConvertFn(), getMultiHostSafeProxy());
/*      */     
/* 3860 */     boolean isFunctionCall = false;
/* 3861 */     String parsedSql = null;
/*      */     
/* 3863 */     if (escapedSqlResult instanceof EscapeProcessorResult) {
/* 3864 */       parsedSql = ((EscapeProcessorResult)escapedSqlResult).escapedSql;
/* 3865 */       isFunctionCall = ((EscapeProcessorResult)escapedSqlResult).callingStoredFunction;
/*      */     } else {
/* 3867 */       parsedSql = (String)escapedSqlResult;
/* 3868 */       isFunctionCall = false;
/*      */     } 
/*      */     
/* 3871 */     return CallableStatement.getInstance(getMultiHostSafeProxy(), parsedSql, this.database, isFunctionCall);
/*      */   }
/*      */   
/*      */   public boolean parserKnowsUnicode() {
/* 3875 */     return this.parserKnowsUnicode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ping() throws SQLException {
/* 3885 */     pingInternal(true, 0);
/*      */   }
/*      */   
/*      */   public void pingInternal(boolean checkForClosedConnection, int timeoutMillis) throws SQLException {
/* 3889 */     if (checkForClosedConnection) {
/* 3890 */       checkClosed();
/*      */     }
/*      */     
/* 3893 */     long pingMillisLifetime = getSelfDestructOnPingSecondsLifetime();
/* 3894 */     int pingMaxOperations = getSelfDestructOnPingMaxOperations();
/*      */     
/* 3896 */     if ((pingMillisLifetime > 0L && System.currentTimeMillis() - this.connectionCreationTimeMillis > pingMillisLifetime) || (pingMaxOperations > 0 && pingMaxOperations <= this.io.getCommandCount())) {
/*      */ 
/*      */       
/* 3899 */       close();
/*      */       
/* 3901 */       throw SQLError.createSQLException(Messages.getString("Connection.exceededConnectionLifetime"), "08S01", getExceptionInterceptor());
/*      */     } 
/*      */ 
/*      */     
/* 3905 */     this.io.sendCommand(14, null, null, false, null, timeoutMillis);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CallableStatement prepareCall(String sql) throws SQLException {
/* 3914 */     return prepareCall(sql, 1003, 1007);
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
/*      */   
/*      */   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/* 3933 */     if (versionMeetsMinimum(5, 0, 0)) {
/* 3934 */       CallableStatement cStmt = null;
/*      */       
/* 3936 */       if (!getCacheCallableStatements()) {
/*      */         
/* 3938 */         cStmt = parseCallableStatement(sql);
/*      */       } else {
/* 3940 */         synchronized (this.parsedCallableStatementCache) {
/* 3941 */           CompoundCacheKey key = new CompoundCacheKey(getCatalog(), sql);
/*      */           
/* 3943 */           CallableStatement.CallableStatementParamInfo cachedParamInfo = (CallableStatement.CallableStatementParamInfo)this.parsedCallableStatementCache.get(key);
/*      */           
/* 3945 */           if (cachedParamInfo != null) {
/* 3946 */             cStmt = CallableStatement.getInstance(getMultiHostSafeProxy(), cachedParamInfo);
/*      */           } else {
/* 3948 */             cStmt = parseCallableStatement(sql);
/*      */             
/* 3950 */             synchronized (cStmt) {
/* 3951 */               cachedParamInfo = cStmt.paramInfo;
/*      */             } 
/*      */             
/* 3954 */             this.parsedCallableStatementCache.put(key, cachedParamInfo);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 3959 */       cStmt.setResultSetType(resultSetType);
/* 3960 */       cStmt.setResultSetConcurrency(resultSetConcurrency);
/*      */       
/* 3962 */       return cStmt;
/*      */     } 
/*      */     
/* 3965 */     throw SQLError.createSQLException("Callable statements not supported.", "S1C00", getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/* 3972 */     if (getPedantic() && 
/* 3973 */       resultSetHoldability != 1) {
/* 3974 */       throw SQLError.createSQLException("HOLD_CUSRORS_OVER_COMMIT is only supported holdability level", "S1009", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3979 */     CallableStatement cStmt = (CallableStatement)prepareCall(sql, resultSetType, resultSetConcurrency);
/*      */     
/* 3981 */     return cStmt;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql) throws SQLException {
/* 4006 */     return prepareStatement(sql, 1003, 1007);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
/* 4013 */     PreparedStatement pStmt = prepareStatement(sql);
/*      */     
/* 4015 */     ((PreparedStatement)pStmt).setRetrieveGeneratedKeys((autoGenKeyIndex == 1));
/*      */     
/* 4017 */     return pStmt;
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
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/* 4036 */     synchronized (getConnectionMutex()) {
/* 4037 */       checkClosed();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4042 */       PreparedStatement pStmt = null;
/*      */       
/* 4044 */       boolean canServerPrepare = true;
/*      */       
/* 4046 */       String nativeSql = getProcessEscapeCodesForPrepStmts() ? nativeSQL(sql) : sql;
/*      */       
/* 4048 */       if (this.useServerPreparedStmts && getEmulateUnsupportedPstmts()) {
/* 4049 */         canServerPrepare = canHandleAsServerPreparedStatement(nativeSql);
/*      */       }
/*      */       
/* 4052 */       if (this.useServerPreparedStmts && canServerPrepare) {
/* 4053 */         if (getCachePreparedStatements()) {
/* 4054 */           synchronized (this.serverSideStatementCache) {
/* 4055 */             pStmt = (PreparedStatement)this.serverSideStatementCache.remove(new CompoundCacheKey(this.database, sql));
/*      */             
/* 4057 */             if (pStmt != null) {
/* 4058 */               ((ServerPreparedStatement)pStmt).setClosed(false);
/* 4059 */               pStmt.clearParameters();
/*      */             } 
/*      */             
/* 4062 */             if (pStmt == null) {
/*      */               try {
/* 4064 */                 pStmt = ServerPreparedStatement.getInstance(getMultiHostSafeProxy(), nativeSql, this.database, resultSetType, resultSetConcurrency);
/*      */                 
/* 4066 */                 if (sql.length() < getPreparedStatementCacheSqlLimit()) {
/* 4067 */                   ((ServerPreparedStatement)pStmt).isCached = true;
/*      */                 }
/*      */                 
/* 4070 */                 pStmt.setResultSetType(resultSetType);
/* 4071 */                 pStmt.setResultSetConcurrency(resultSetConcurrency);
/* 4072 */               } catch (SQLException sqlEx) {
/*      */                 
/* 4074 */                 if (getEmulateUnsupportedPstmts()) {
/* 4075 */                   pStmt = (PreparedStatement)clientPrepareStatement(nativeSql, resultSetType, resultSetConcurrency, false);
/*      */                   
/* 4077 */                   if (sql.length() < getPreparedStatementCacheSqlLimit()) {
/* 4078 */                     this.serverSideStatementCheckCache.put(sql, Boolean.FALSE);
/*      */                   }
/*      */                 } else {
/* 4081 */                   throw sqlEx;
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } else {
/*      */           try {
/* 4088 */             pStmt = ServerPreparedStatement.getInstance(getMultiHostSafeProxy(), nativeSql, this.database, resultSetType, resultSetConcurrency);
/*      */             
/* 4090 */             pStmt.setResultSetType(resultSetType);
/* 4091 */             pStmt.setResultSetConcurrency(resultSetConcurrency);
/* 4092 */           } catch (SQLException sqlEx) {
/*      */             
/* 4094 */             if (getEmulateUnsupportedPstmts()) {
/* 4095 */               pStmt = (PreparedStatement)clientPrepareStatement(nativeSql, resultSetType, resultSetConcurrency, false);
/*      */             } else {
/* 4097 */               throw sqlEx;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } else {
/* 4102 */         pStmt = (PreparedStatement)clientPrepareStatement(nativeSql, resultSetType, resultSetConcurrency, false);
/*      */       } 
/*      */       
/* 4105 */       return pStmt;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/* 4113 */     if (getPedantic() && 
/* 4114 */       resultSetHoldability != 1) {
/* 4115 */       throw SQLError.createSQLException("HOLD_CUSRORS_OVER_COMMIT is only supported holdability level", "S1009", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4120 */     return prepareStatement(sql, resultSetType, resultSetConcurrency);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
/* 4127 */     PreparedStatement pStmt = prepareStatement(sql);
/*      */     
/* 4129 */     ((PreparedStatement)pStmt).setRetrieveGeneratedKeys((autoGenKeyIndexes != null && autoGenKeyIndexes.length > 0));
/*      */     
/* 4131 */     return pStmt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
/* 4138 */     PreparedStatement pStmt = prepareStatement(sql);
/*      */     
/* 4140 */     ((PreparedStatement)pStmt).setRetrieveGeneratedKeys((autoGenKeyColNames != null && autoGenKeyColNames.length > 0));
/*      */     
/* 4142 */     return pStmt;
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
/*      */   public void realClose(boolean calledExplicitly, boolean issueRollback, boolean skipLocalTeardown, Throwable reason) throws SQLException {
/* 4156 */     SQLException sqlEx = null;
/*      */     
/* 4158 */     if (isClosed()) {
/*      */       return;
/*      */     }
/*      */     
/* 4162 */     this.forceClosedReason = reason;
/*      */     
/*      */     try {
/* 4165 */       if (!skipLocalTeardown) {
/* 4166 */         if (!getAutoCommit() && issueRollback) {
/*      */           try {
/* 4168 */             rollback();
/* 4169 */           } catch (SQLException ex) {
/* 4170 */             sqlEx = ex;
/*      */           } 
/*      */         }
/*      */         
/* 4174 */         if (getGatherPerfMetrics()) {
/* 4175 */           reportMetrics();
/*      */         }
/*      */         
/* 4178 */         if (getUseUsageAdvisor()) {
/* 4179 */           if (!calledExplicitly) {
/* 4180 */             String message = "Connection implicitly closed by Driver. You should call Connection.close() from your code to free resources more efficiently and avoid resource leaks.";
/*      */             
/* 4182 */             this.eventSink.processEvent((byte)0, this, null, null, 0L, new Throwable(), message);
/*      */           } 
/*      */           
/* 4185 */           if (System.currentTimeMillis() - this.connectionCreationTimeMillis < 500L) {
/* 4186 */             String message = "Connection lifetime of < .5 seconds. You might be un-necessarily creating short-lived connections and should investigate connection pooling to be more efficient.";
/*      */             
/* 4188 */             this.eventSink.processEvent((byte)0, this, null, null, 0L, new Throwable(), message);
/*      */           } 
/*      */         } 
/*      */         
/*      */         try {
/* 4193 */           closeAllOpenStatements();
/* 4194 */         } catch (SQLException ex) {
/* 4195 */           sqlEx = ex;
/*      */         } 
/*      */         
/* 4198 */         if (this.io != null) {
/*      */           try {
/* 4200 */             this.io.quit();
/* 4201 */           } catch (Exception e) {}
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 4206 */         this.io.forceClose();
/*      */       } 
/*      */       
/* 4209 */       if (this.statementInterceptors != null) {
/* 4210 */         for (int i = 0; i < this.statementInterceptors.size(); i++) {
/* 4211 */           ((StatementInterceptorV2)this.statementInterceptors.get(i)).destroy();
/*      */         }
/*      */       }
/*      */       
/* 4215 */       if (this.exceptionInterceptor != null) {
/* 4216 */         this.exceptionInterceptor.destroy();
/*      */       }
/*      */     } finally {
/* 4219 */       this.openStatements.clear();
/* 4220 */       if (this.io != null) {
/* 4221 */         this.io.releaseResources();
/* 4222 */         this.io = null;
/*      */       } 
/* 4224 */       this.statementInterceptors = null;
/* 4225 */       this.exceptionInterceptor = null;
/* 4226 */       ProfilerEventHandlerFactory.removeInstance(this);
/* 4227 */       this.eventSink = null;
/*      */       
/* 4229 */       synchronized (getConnectionMutex()) {
/* 4230 */         if (this.cancelTimer != null) {
/* 4231 */           this.cancelTimer.cancel();
/*      */         }
/*      */       } 
/*      */       
/* 4235 */       this.isClosed = true;
/*      */     } 
/*      */     
/* 4238 */     if (sqlEx != null) {
/* 4239 */       throw sqlEx;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void recachePreparedStatement(ServerPreparedStatement pstmt) throws SQLException {
/* 4245 */     synchronized (getConnectionMutex()) {
/* 4246 */       if (getCachePreparedStatements() && pstmt.isPoolable()) {
/* 4247 */         synchronized (this.serverSideStatementCache) {
/* 4248 */           Object oldServerPrepStmt = this.serverSideStatementCache.put(new CompoundCacheKey(pstmt.currentCatalog, pstmt.originalSql), pstmt);
/* 4249 */           if (oldServerPrepStmt != null && oldServerPrepStmt != pstmt) {
/* 4250 */             ((ServerPreparedStatement)oldServerPrepStmt).isCached = false;
/* 4251 */             ((ServerPreparedStatement)oldServerPrepStmt).setClosed(false);
/* 4252 */             ((ServerPreparedStatement)oldServerPrepStmt).realClose(true, true);
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void decachePreparedStatement(ServerPreparedStatement pstmt) throws SQLException {
/* 4260 */     synchronized (getConnectionMutex()) {
/* 4261 */       if (getCachePreparedStatements() && pstmt.isPoolable()) {
/* 4262 */         synchronized (this.serverSideStatementCache) {
/* 4263 */           this.serverSideStatementCache.remove(new CompoundCacheKey(pstmt.currentCatalog, pstmt.originalSql));
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerQueryExecutionTime(long queryTimeMs) {
/* 4273 */     if (queryTimeMs > this.longestQueryTimeMs) {
/* 4274 */       this.longestQueryTimeMs = queryTimeMs;
/*      */       
/* 4276 */       repartitionPerformanceHistogram();
/*      */     } 
/*      */     
/* 4279 */     addToPerformanceHistogram(queryTimeMs, 1);
/*      */     
/* 4281 */     if (queryTimeMs < this.shortestQueryTimeMs) {
/* 4282 */       this.shortestQueryTimeMs = (queryTimeMs == 0L) ? 1L : queryTimeMs;
/*      */     }
/*      */     
/* 4285 */     this.numberOfQueriesIssued++;
/*      */     
/* 4287 */     this.totalQueryTimeMs += queryTimeMs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerStatement(Statement stmt) {
/* 4297 */     this.openStatements.addIfAbsent(stmt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void releaseSavepoint(Savepoint arg0) throws SQLException {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void repartitionHistogram(int[] histCounts, long[] histBreakpoints, long currentLowerBound, long currentUpperBound) {
/* 4309 */     if (this.oldHistCounts == null) {
/* 4310 */       this.oldHistCounts = new int[histCounts.length];
/* 4311 */       this.oldHistBreakpoints = new long[histBreakpoints.length];
/*      */     } 
/*      */     
/* 4314 */     System.arraycopy(histCounts, 0, this.oldHistCounts, 0, histCounts.length);
/*      */     
/* 4316 */     System.arraycopy(histBreakpoints, 0, this.oldHistBreakpoints, 0, histBreakpoints.length);
/*      */     
/* 4318 */     createInitialHistogram(histBreakpoints, currentLowerBound, currentUpperBound);
/*      */     
/* 4320 */     for (int i = 0; i < 20; i++) {
/* 4321 */       addToHistogram(histCounts, histBreakpoints, this.oldHistBreakpoints[i], this.oldHistCounts[i], currentLowerBound, currentUpperBound);
/*      */     }
/*      */   }
/*      */   
/*      */   private void repartitionPerformanceHistogram() {
/* 4326 */     checkAndCreatePerformanceHistogram();
/*      */     
/* 4328 */     repartitionHistogram(this.perfMetricsHistCounts, this.perfMetricsHistBreakpoints, (this.shortestQueryTimeMs == Long.MAX_VALUE) ? 0L : this.shortestQueryTimeMs, this.longestQueryTimeMs);
/*      */   }
/*      */ 
/*      */   
/*      */   private void repartitionTablesAccessedHistogram() {
/* 4333 */     checkAndCreateTablesAccessedHistogram();
/*      */     
/* 4335 */     repartitionHistogram(this.numTablesMetricsHistCounts, this.numTablesMetricsHistBreakpoints, (this.minimumNumberTablesAccessed == Long.MAX_VALUE) ? 0L : this.minimumNumberTablesAccessed, this.maximumNumberTablesAccessed);
/*      */   }
/*      */ 
/*      */   
/*      */   private void reportMetrics() {
/* 4340 */     if (getGatherPerformanceMetrics()) {
/* 4341 */       StringBuilder logMessage = new StringBuilder(256);
/*      */       
/* 4343 */       logMessage.append("** Performance Metrics Report **\n");
/* 4344 */       logMessage.append("\nLongest reported query: " + this.longestQueryTimeMs + " ms");
/* 4345 */       logMessage.append("\nShortest reported query: " + this.shortestQueryTimeMs + " ms");
/* 4346 */       logMessage.append("\nAverage query execution time: " + (this.totalQueryTimeMs / this.numberOfQueriesIssued) + " ms");
/* 4347 */       logMessage.append("\nNumber of statements executed: " + this.numberOfQueriesIssued);
/* 4348 */       logMessage.append("\nNumber of result sets created: " + this.numberOfResultSetsCreated);
/* 4349 */       logMessage.append("\nNumber of statements prepared: " + this.numberOfPrepares);
/* 4350 */       logMessage.append("\nNumber of prepared statement executions: " + this.numberOfPreparedExecutes);
/*      */       
/* 4352 */       if (this.perfMetricsHistBreakpoints != null) {
/* 4353 */         logMessage.append("\n\n\tTiming Histogram:\n");
/* 4354 */         int maxNumPoints = 20;
/* 4355 */         int highestCount = Integer.MIN_VALUE;
/*      */         int i;
/* 4357 */         for (i = 0; i < 20; i++) {
/* 4358 */           if (this.perfMetricsHistCounts[i] > highestCount) {
/* 4359 */             highestCount = this.perfMetricsHistCounts[i];
/*      */           }
/*      */         } 
/*      */         
/* 4363 */         if (highestCount == 0) {
/* 4364 */           highestCount = 1;
/*      */         }
/*      */         
/* 4367 */         for (i = 0; i < 19; i++) {
/*      */           
/* 4369 */           if (i == 0) {
/* 4370 */             logMessage.append("\n\tless than " + this.perfMetricsHistBreakpoints[i + 1] + " ms: \t" + this.perfMetricsHistCounts[i]);
/*      */           } else {
/* 4372 */             logMessage.append("\n\tbetween " + this.perfMetricsHistBreakpoints[i] + " and " + this.perfMetricsHistBreakpoints[i + 1] + " ms: \t" + this.perfMetricsHistCounts[i]);
/*      */           } 
/*      */ 
/*      */           
/* 4376 */           logMessage.append("\t");
/*      */           
/* 4378 */           int numPointsToGraph = (int)(maxNumPoints * this.perfMetricsHistCounts[i] / highestCount);
/*      */           
/* 4380 */           for (int j = 0; j < numPointsToGraph; j++) {
/* 4381 */             logMessage.append("*");
/*      */           }
/*      */           
/* 4384 */           if (this.longestQueryTimeMs < this.perfMetricsHistCounts[i + 1]) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */         
/* 4389 */         if (this.perfMetricsHistBreakpoints[18] < this.longestQueryTimeMs) {
/* 4390 */           logMessage.append("\n\tbetween ");
/* 4391 */           logMessage.append(this.perfMetricsHistBreakpoints[18]);
/* 4392 */           logMessage.append(" and ");
/* 4393 */           logMessage.append(this.perfMetricsHistBreakpoints[19]);
/* 4394 */           logMessage.append(" ms: \t");
/* 4395 */           logMessage.append(this.perfMetricsHistCounts[19]);
/*      */         } 
/*      */       } 
/*      */       
/* 4399 */       if (this.numTablesMetricsHistBreakpoints != null) {
/* 4400 */         logMessage.append("\n\n\tTable Join Histogram:\n");
/* 4401 */         int maxNumPoints = 20;
/* 4402 */         int highestCount = Integer.MIN_VALUE;
/*      */         int i;
/* 4404 */         for (i = 0; i < 20; i++) {
/* 4405 */           if (this.numTablesMetricsHistCounts[i] > highestCount) {
/* 4406 */             highestCount = this.numTablesMetricsHistCounts[i];
/*      */           }
/*      */         } 
/*      */         
/* 4410 */         if (highestCount == 0) {
/* 4411 */           highestCount = 1;
/*      */         }
/*      */         
/* 4414 */         for (i = 0; i < 19; i++) {
/*      */           
/* 4416 */           if (i == 0) {
/* 4417 */             logMessage.append("\n\t" + this.numTablesMetricsHistBreakpoints[i + 1] + " tables or less: \t\t" + this.numTablesMetricsHistCounts[i]);
/*      */           } else {
/* 4419 */             logMessage.append("\n\tbetween " + this.numTablesMetricsHistBreakpoints[i] + " and " + this.numTablesMetricsHistBreakpoints[i + 1] + " tables: \t" + this.numTablesMetricsHistCounts[i]);
/*      */           } 
/*      */ 
/*      */           
/* 4423 */           logMessage.append("\t");
/*      */           
/* 4425 */           int numPointsToGraph = (int)(maxNumPoints * this.numTablesMetricsHistCounts[i] / highestCount);
/*      */           
/* 4427 */           for (int j = 0; j < numPointsToGraph; j++) {
/* 4428 */             logMessage.append("*");
/*      */           }
/*      */           
/* 4431 */           if (this.maximumNumberTablesAccessed < this.numTablesMetricsHistBreakpoints[i + 1]) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */         
/* 4436 */         if (this.numTablesMetricsHistBreakpoints[18] < this.maximumNumberTablesAccessed) {
/* 4437 */           logMessage.append("\n\tbetween ");
/* 4438 */           logMessage.append(this.numTablesMetricsHistBreakpoints[18]);
/* 4439 */           logMessage.append(" and ");
/* 4440 */           logMessage.append(this.numTablesMetricsHistBreakpoints[19]);
/* 4441 */           logMessage.append(" tables: ");
/* 4442 */           logMessage.append(this.numTablesMetricsHistCounts[19]);
/*      */         } 
/*      */       } 
/*      */       
/* 4446 */       this.log.logInfo(logMessage);
/*      */       
/* 4448 */       this.metricsLastReportedMs = System.currentTimeMillis();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportMetricsIfNeeded() {
/* 4457 */     if (getGatherPerformanceMetrics() && 
/* 4458 */       System.currentTimeMillis() - this.metricsLastReportedMs > getReportMetricsIntervalMillis()) {
/* 4459 */       reportMetrics();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void reportNumberOfTablesAccessed(int numTablesAccessed) {
/* 4465 */     if (numTablesAccessed < this.minimumNumberTablesAccessed) {
/* 4466 */       this.minimumNumberTablesAccessed = numTablesAccessed;
/*      */     }
/*      */     
/* 4469 */     if (numTablesAccessed > this.maximumNumberTablesAccessed) {
/* 4470 */       this.maximumNumberTablesAccessed = numTablesAccessed;
/*      */       
/* 4472 */       repartitionTablesAccessedHistogram();
/*      */     } 
/*      */     
/* 4475 */     addToTablesAccessedHistogram(numTablesAccessed, 1);
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
/*      */   public void resetServerState() throws SQLException {
/* 4487 */     if (!getParanoid() && this.io != null && versionMeetsMinimum(4, 0, 6)) {
/* 4488 */       changeUser(this.user, this.password);
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
/*      */   public void rollback() throws SQLException {
/* 4502 */     synchronized (getConnectionMutex()) {
/* 4503 */       checkClosed();
/*      */       
/*      */       try {
/* 4506 */         if (this.connectionLifecycleInterceptors != null) {
/* 4507 */           IterateBlock<Extension> iter = new IterateBlock<Extension>(this.connectionLifecycleInterceptors.iterator())
/*      */             {
/*      */               void forEach(Extension each) throws SQLException
/*      */               {
/* 4511 */                 if (!((ConnectionLifecycleInterceptor)each).rollback()) {
/* 4512 */                   this.stopIterating = true;
/*      */                 }
/*      */               }
/*      */             };
/*      */           
/* 4517 */           iter.doForAll();
/*      */           
/* 4519 */           if (!iter.fullIteration()) {
/*      */             return;
/*      */           }
/*      */         } 
/*      */         
/* 4524 */         if (this.autoCommit && !getRelaxAutoCommit()) {
/* 4525 */           throw SQLError.createSQLException("Can't call rollback when autocommit=true", "08003", getExceptionInterceptor());
/*      */         }
/* 4527 */         if (this.transactionsSupported) {
/*      */           try {
/* 4529 */             rollbackNoChecks();
/* 4530 */           } catch (SQLException sqlEx) {
/*      */             
/* 4532 */             if (getIgnoreNonTxTables() && sqlEx.getErrorCode() == 1196) {
/*      */               return;
/*      */             }
/* 4535 */             throw sqlEx;
/*      */           }
/*      */         
/*      */         }
/* 4539 */       } catch (SQLException sqlException) {
/* 4540 */         if ("08S01".equals(sqlException.getSQLState())) {
/* 4541 */           throw SQLError.createSQLException("Communications link failure during rollback(). Transaction resolution unknown.", "08007", getExceptionInterceptor());
/*      */         }
/*      */ 
/*      */         
/* 4545 */         throw sqlException;
/*      */       } finally {
/* 4547 */         this.needsPing = getReconnectAtTxEnd();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void rollback(final Savepoint savepoint) throws SQLException {
/* 4557 */     synchronized (getConnectionMutex()) {
/* 4558 */       if (versionMeetsMinimum(4, 0, 14) || versionMeetsMinimum(4, 1, 1)) {
/* 4559 */         checkClosed();
/*      */         
/*      */         try {
/* 4562 */           if (this.connectionLifecycleInterceptors != null) {
/* 4563 */             IterateBlock<Extension> iter = new IterateBlock<Extension>(this.connectionLifecycleInterceptors.iterator())
/*      */               {
/*      */                 void forEach(Extension each) throws SQLException
/*      */                 {
/* 4567 */                   if (!((ConnectionLifecycleInterceptor)each).rollback(savepoint)) {
/* 4568 */                     this.stopIterating = true;
/*      */                   }
/*      */                 }
/*      */               };
/*      */             
/* 4573 */             iter.doForAll();
/*      */             
/* 4575 */             if (!iter.fullIteration()) {
/*      */               return;
/*      */             }
/*      */           } 
/*      */           
/* 4580 */           StringBuilder rollbackQuery = new StringBuilder("ROLLBACK TO SAVEPOINT ");
/* 4581 */           rollbackQuery.append('`');
/* 4582 */           rollbackQuery.append(savepoint.getSavepointName());
/* 4583 */           rollbackQuery.append('`');
/*      */           
/* 4585 */           Statement stmt = null;
/*      */           
/*      */           try {
/* 4588 */             stmt = getMetadataSafeStatement();
/*      */             
/* 4590 */             stmt.executeUpdate(rollbackQuery.toString());
/* 4591 */           } catch (SQLException sqlEx) {
/* 4592 */             int errno = sqlEx.getErrorCode();
/*      */             
/* 4594 */             if (errno == 1181) {
/* 4595 */               String msg = sqlEx.getMessage();
/*      */               
/* 4597 */               if (msg != null) {
/* 4598 */                 int indexOfError153 = msg.indexOf("153");
/*      */                 
/* 4600 */                 if (indexOfError153 != -1) {
/* 4601 */                   throw SQLError.createSQLException("Savepoint '" + savepoint.getSavepointName() + "' does not exist", "S1009", errno, getExceptionInterceptor());
/*      */                 }
/*      */               } 
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 4608 */             if (getIgnoreNonTxTables() && sqlEx.getErrorCode() != 1196) {
/* 4609 */               throw sqlEx;
/*      */             }
/*      */             
/* 4612 */             if ("08S01".equals(sqlEx.getSQLState())) {
/* 4613 */               throw SQLError.createSQLException("Communications link failure during rollback(). Transaction resolution unknown.", "08007", getExceptionInterceptor());
/*      */             }
/*      */ 
/*      */             
/* 4617 */             throw sqlEx;
/*      */           } finally {
/* 4619 */             closeStatement(stmt);
/*      */           } 
/*      */         } finally {
/* 4622 */           this.needsPing = getReconnectAtTxEnd();
/*      */         } 
/*      */       } else {
/* 4625 */         throw SQLError.createSQLFeatureNotSupportedException();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void rollbackNoChecks() throws SQLException {
/* 4631 */     if (getUseLocalTransactionState() && versionMeetsMinimum(5, 0, 0) && 
/* 4632 */       !this.io.inTransactionOnServer()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 4637 */     execSQL((StatementImpl)null, "rollback", -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql) throws SQLException {
/* 4644 */     String nativeSql = getProcessEscapeCodesForPrepStmts() ? nativeSQL(sql) : sql;
/*      */     
/* 4646 */     return ServerPreparedStatement.getInstance(getMultiHostSafeProxy(), nativeSql, getCatalog(), 1003, 1007);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
/* 4654 */     String nativeSql = getProcessEscapeCodesForPrepStmts() ? nativeSQL(sql) : sql;
/*      */     
/* 4656 */     PreparedStatement pStmt = ServerPreparedStatement.getInstance(getMultiHostSafeProxy(), nativeSql, getCatalog(), 1003, 1007);
/*      */ 
/*      */     
/* 4659 */     pStmt.setRetrieveGeneratedKeys((autoGenKeyIndex == 1));
/*      */     
/* 4661 */     return pStmt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/* 4668 */     String nativeSql = getProcessEscapeCodesForPrepStmts() ? nativeSQL(sql) : sql;
/*      */     
/* 4670 */     return ServerPreparedStatement.getInstance(getMultiHostSafeProxy(), nativeSql, getCatalog(), resultSetType, resultSetConcurrency);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/* 4678 */     if (getPedantic() && 
/* 4679 */       resultSetHoldability != 1) {
/* 4680 */       throw SQLError.createSQLException("HOLD_CUSRORS_OVER_COMMIT is only supported holdability level", "S1009", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4685 */     return serverPrepareStatement(sql, resultSetType, resultSetConcurrency);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
/* 4693 */     PreparedStatement pStmt = (PreparedStatement)serverPrepareStatement(sql);
/*      */     
/* 4695 */     pStmt.setRetrieveGeneratedKeys((autoGenKeyIndexes != null && autoGenKeyIndexes.length > 0));
/*      */     
/* 4697 */     return pStmt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreparedStatement serverPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
/* 4704 */     PreparedStatement pStmt = (PreparedStatement)serverPrepareStatement(sql);
/*      */     
/* 4706 */     pStmt.setRetrieveGeneratedKeys((autoGenKeyColNames != null && autoGenKeyColNames.length > 0));
/*      */     
/* 4708 */     return pStmt;
/*      */   }
/*      */   
/*      */   public boolean serverSupportsConvertFn() throws SQLException {
/* 4712 */     return versionMeetsMinimum(4, 0, 2);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoCommit(final boolean autoCommitFlag) throws SQLException {
/* 4734 */     synchronized (getConnectionMutex()) {
/* 4735 */       checkClosed();
/*      */       
/* 4737 */       if (this.connectionLifecycleInterceptors != null) {
/* 4738 */         IterateBlock<Extension> iter = new IterateBlock<Extension>(this.connectionLifecycleInterceptors.iterator())
/*      */           {
/*      */             void forEach(Extension each) throws SQLException
/*      */             {
/* 4742 */               if (!((ConnectionLifecycleInterceptor)each).setAutoCommit(autoCommitFlag)) {
/* 4743 */                 this.stopIterating = true;
/*      */               }
/*      */             }
/*      */           };
/*      */         
/* 4748 */         iter.doForAll();
/*      */         
/* 4750 */         if (!iter.fullIteration()) {
/*      */           return;
/*      */         }
/*      */       } 
/*      */       
/* 4755 */       if (getAutoReconnectForPools()) {
/* 4756 */         setHighAvailability(true);
/*      */       }
/*      */       
/*      */       try {
/* 4760 */         if (this.transactionsSupported) {
/*      */           
/* 4762 */           boolean needsSetOnServer = true;
/*      */           
/* 4764 */           if (getUseLocalSessionState() && this.autoCommit == autoCommitFlag) {
/* 4765 */             needsSetOnServer = false;
/* 4766 */           } else if (!getHighAvailability()) {
/* 4767 */             needsSetOnServer = getIO().isSetNeededForAutoCommitMode(autoCommitFlag);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4773 */           this.autoCommit = autoCommitFlag;
/*      */           
/* 4775 */           if (needsSetOnServer) {
/* 4776 */             execSQL((StatementImpl)null, autoCommitFlag ? "SET autocommit=1" : "SET autocommit=0", -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/* 4781 */           if (!autoCommitFlag && !getRelaxAutoCommit()) {
/* 4782 */             throw SQLError.createSQLException("MySQL Versions Older than 3.23.15 do not support transactions", "08003", getExceptionInterceptor());
/*      */           }
/*      */ 
/*      */           
/* 4786 */           this.autoCommit = autoCommitFlag;
/*      */         } 
/*      */       } finally {
/* 4789 */         if (getAutoReconnectForPools()) {
/* 4790 */           setHighAvailability(false);
/*      */         }
/*      */       } 
/*      */       return;
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
/*      */   public void setCatalog(final String catalog) throws SQLException {
/* 4812 */     synchronized (getConnectionMutex()) {
/* 4813 */       checkClosed();
/*      */       
/* 4815 */       if (catalog == null) {
/* 4816 */         throw SQLError.createSQLException("Catalog can not be null", "S1009", getExceptionInterceptor());
/*      */       }
/*      */       
/* 4819 */       if (this.connectionLifecycleInterceptors != null) {
/* 4820 */         IterateBlock<Extension> iter = new IterateBlock<Extension>(this.connectionLifecycleInterceptors.iterator())
/*      */           {
/*      */             void forEach(Extension each) throws SQLException
/*      */             {
/* 4824 */               if (!((ConnectionLifecycleInterceptor)each).setCatalog(catalog)) {
/* 4825 */                 this.stopIterating = true;
/*      */               }
/*      */             }
/*      */           };
/*      */         
/* 4830 */         iter.doForAll();
/*      */         
/* 4832 */         if (!iter.fullIteration()) {
/*      */           return;
/*      */         }
/*      */       } 
/*      */       
/* 4837 */       if (getUseLocalSessionState()) {
/* 4838 */         if (this.lowerCaseTableNames) {
/* 4839 */           if (this.database.equalsIgnoreCase(catalog)) {
/*      */             return;
/*      */           }
/*      */         }
/* 4843 */         else if (this.database.equals(catalog)) {
/*      */           return;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 4849 */       String quotedId = this.dbmd.getIdentifierQuoteString();
/*      */       
/* 4851 */       if (quotedId == null || quotedId.equals(" ")) {
/* 4852 */         quotedId = "";
/*      */       }
/*      */       
/* 4855 */       StringBuilder query = new StringBuilder("USE ");
/* 4856 */       query.append(StringUtils.quoteIdentifier(catalog, quotedId, getPedantic()));
/*      */       
/* 4858 */       execSQL((StatementImpl)null, query.toString(), -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */       
/* 4860 */       this.database = catalog;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFailedOver(boolean flag) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHoldability(int arg0) throws SQLException {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInGlobalTx(boolean flag) {
/* 4880 */     this.isInGlobalTx = flag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setPreferSlaveDuringFailover(boolean flag) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReadInfoMsgEnabled(boolean flag) {
/* 4894 */     this.readInfoMsg = flag;
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
/*      */   public void setReadOnly(boolean readOnlyFlag) throws SQLException {
/* 4909 */     checkClosed();
/*      */     
/* 4911 */     setReadOnlyInternal(readOnlyFlag);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setReadOnlyInternal(boolean readOnlyFlag) throws SQLException {
/* 4916 */     if (getReadOnlyPropagatesToServer() && versionMeetsMinimum(5, 6, 5) && (
/* 4917 */       !getUseLocalSessionState() || readOnlyFlag != this.readOnly)) {
/* 4918 */       execSQL((StatementImpl)null, "set session transaction " + (readOnlyFlag ? "read only" : "read write"), -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4923 */     this.readOnly = readOnlyFlag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Savepoint setSavepoint() throws SQLException {
/* 4930 */     MysqlSavepoint savepoint = new MysqlSavepoint(getExceptionInterceptor());
/*      */     
/* 4932 */     setSavepoint(savepoint);
/*      */     
/* 4934 */     return savepoint;
/*      */   }
/*      */ 
/*      */   
/*      */   private void setSavepoint(MysqlSavepoint savepoint) throws SQLException {
/* 4939 */     synchronized (getConnectionMutex()) {
/* 4940 */       if (versionMeetsMinimum(4, 0, 14) || versionMeetsMinimum(4, 1, 1)) {
/* 4941 */         checkClosed();
/*      */         
/* 4943 */         StringBuilder savePointQuery = new StringBuilder("SAVEPOINT ");
/* 4944 */         savePointQuery.append('`');
/* 4945 */         savePointQuery.append(savepoint.getSavepointName());
/* 4946 */         savePointQuery.append('`');
/*      */         
/* 4948 */         Statement stmt = null;
/*      */         
/*      */         try {
/* 4951 */           stmt = getMetadataSafeStatement();
/*      */           
/* 4953 */           stmt.executeUpdate(savePointQuery.toString());
/*      */         } finally {
/* 4955 */           closeStatement(stmt);
/*      */         } 
/*      */       } else {
/* 4958 */         throw SQLError.createSQLFeatureNotSupportedException();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Savepoint setSavepoint(String name) throws SQLException {
/* 4967 */     synchronized (getConnectionMutex()) {
/* 4968 */       MysqlSavepoint savepoint = new MysqlSavepoint(name, getExceptionInterceptor());
/*      */       
/* 4970 */       setSavepoint(savepoint);
/*      */       
/* 4972 */       return savepoint;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setSessionVariables() throws SQLException {
/* 4977 */     if (versionMeetsMinimum(4, 0, 0) && getSessionVariables() != null) {
/* 4978 */       List<String> variablesToSet = new ArrayList<String>();
/* 4979 */       for (String part : StringUtils.split(getSessionVariables(), ",", "\"'(", "\"')", "\"'", true)) {
/* 4980 */         variablesToSet.addAll(StringUtils.split(part, ";", "\"'(", "\"')", "\"'", true));
/*      */       }
/*      */       
/* 4983 */       if (!variablesToSet.isEmpty()) {
/* 4984 */         Statement stmt = null;
/*      */         try {
/* 4986 */           stmt = getMetadataSafeStatement();
/* 4987 */           StringBuilder query = new StringBuilder("SET ");
/* 4988 */           String separator = "";
/* 4989 */           for (String variableToSet : variablesToSet) {
/* 4990 */             if (variableToSet.length() > 0) {
/* 4991 */               query.append(separator);
/* 4992 */               if (!variableToSet.startsWith("@")) {
/* 4993 */                 query.append("SESSION ");
/*      */               }
/* 4995 */               query.append(variableToSet);
/* 4996 */               separator = ",";
/*      */             } 
/*      */           } 
/* 4999 */           stmt.executeUpdate(query.toString());
/*      */         } finally {
/* 5001 */           if (stmt != null) {
/* 5002 */             stmt.close();
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransactionIsolation(int level) throws SQLException {
/* 5014 */     synchronized (getConnectionMutex()) {
/* 5015 */       checkClosed();
/*      */       
/* 5017 */       if (this.hasIsolationLevels) {
/* 5018 */         String sql = null;
/*      */         
/* 5020 */         boolean shouldSendSet = false;
/*      */         
/* 5022 */         if (getAlwaysSendSetIsolation()) {
/* 5023 */           shouldSendSet = true;
/*      */         }
/* 5025 */         else if (level != this.isolationLevel) {
/* 5026 */           shouldSendSet = true;
/*      */         } 
/*      */ 
/*      */         
/* 5030 */         if (getUseLocalSessionState()) {
/* 5031 */           shouldSendSet = (this.isolationLevel != level);
/*      */         }
/*      */         
/* 5034 */         if (shouldSendSet) {
/* 5035 */           switch (level) {
/*      */             case 0:
/* 5037 */               throw SQLError.createSQLException("Transaction isolation level NONE not supported by MySQL", getExceptionInterceptor());
/*      */             
/*      */             case 2:
/* 5040 */               sql = "SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED";
/*      */               break;
/*      */ 
/*      */             
/*      */             case 1:
/* 5045 */               sql = "SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED";
/*      */               break;
/*      */ 
/*      */             
/*      */             case 4:
/* 5050 */               sql = "SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ";
/*      */               break;
/*      */ 
/*      */             
/*      */             case 8:
/* 5055 */               sql = "SET SESSION TRANSACTION ISOLATION LEVEL SERIALIZABLE";
/*      */               break;
/*      */ 
/*      */             
/*      */             default:
/* 5060 */               throw SQLError.createSQLException("Unsupported transaction isolation level '" + level + "'", "S1C00", getExceptionInterceptor());
/*      */           } 
/*      */ 
/*      */           
/* 5064 */           execSQL((StatementImpl)null, sql, -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */           
/* 5066 */           this.isolationLevel = level;
/*      */         } 
/*      */       } else {
/* 5069 */         throw SQLError.createSQLException("Transaction Isolation Levels are not supported on MySQL versions older than 3.23.36.", "S1C00", getExceptionInterceptor());
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
/*      */   public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
/* 5085 */     synchronized (getConnectionMutex()) {
/* 5086 */       this.typeMap = map;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setupServerForTruncationChecks() throws SQLException {
/* 5091 */     if (getJdbcCompliantTruncation() && 
/* 5092 */       versionMeetsMinimum(5, 0, 2)) {
/* 5093 */       String currentSqlMode = this.serverVariables.get("sql_mode");
/*      */       
/* 5095 */       boolean strictTransTablesIsSet = (StringUtils.indexOfIgnoreCase(currentSqlMode, "STRICT_TRANS_TABLES") != -1);
/*      */       
/* 5097 */       if (currentSqlMode == null || currentSqlMode.length() == 0 || !strictTransTablesIsSet) {
/* 5098 */         StringBuilder commandBuf = new StringBuilder("SET sql_mode='");
/*      */         
/* 5100 */         if (currentSqlMode != null && currentSqlMode.length() > 0) {
/* 5101 */           commandBuf.append(currentSqlMode);
/* 5102 */           commandBuf.append(",");
/*      */         } 
/*      */         
/* 5105 */         commandBuf.append("STRICT_TRANS_TABLES'");
/*      */         
/* 5107 */         execSQL((StatementImpl)null, commandBuf.toString(), -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */         
/* 5109 */         setJdbcCompliantTruncation(false);
/* 5110 */       } else if (strictTransTablesIsSet) {
/*      */         
/* 5112 */         setJdbcCompliantTruncation(false);
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
/*      */   public void shutdownServer() throws SQLException {
/*      */     try {
/* 5127 */       if (versionMeetsMinimum(5, 7, 9)) {
/* 5128 */         execSQL((StatementImpl)null, "SHUTDOWN", -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */       } else {
/* 5130 */         this.io.sendCommand(8, null, null, false, null, 0);
/*      */       } 
/* 5132 */     } catch (Exception ex) {
/* 5133 */       SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.UnhandledExceptionDuringShutdown"), "S1000", getExceptionInterceptor());
/*      */ 
/*      */       
/* 5136 */       sqlEx.initCause(ex);
/*      */       
/* 5138 */       throw sqlEx;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean supportsIsolationLevel() {
/* 5143 */     return this.hasIsolationLevels;
/*      */   }
/*      */   
/*      */   public boolean supportsQuotedIdentifiers() {
/* 5147 */     return this.hasQuotedIdentifiers;
/*      */   }
/*      */   
/*      */   public boolean supportsTransactions() {
/* 5151 */     return this.transactionsSupported;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unregisterStatement(Statement stmt) {
/* 5161 */     this.openStatements.remove(stmt);
/*      */   }
/*      */   
/*      */   public boolean useAnsiQuotedIdentifiers() {
/* 5165 */     synchronized (getConnectionMutex()) {
/* 5166 */       return this.useAnsiQuotes;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean versionMeetsMinimum(int major, int minor, int subminor) throws SQLException {
/* 5171 */     checkClosed();
/*      */     
/* 5173 */     return this.io.versionMeetsMinimum(major, minor, subminor);
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
/*      */   public CachedResultSetMetaData getCachedMetaData(String sql) {
/* 5191 */     if (this.resultSetMetadataCache != null) {
/* 5192 */       synchronized (this.resultSetMetadataCache) {
/* 5193 */         return (CachedResultSetMetaData)this.resultSetMetadataCache.get(sql);
/*      */       } 
/*      */     }
/*      */     
/* 5197 */     return null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initializeResultsMetadataFromCache(String sql, CachedResultSetMetaData cachedMetaData, ResultSetInternalMethods resultSet) throws SQLException {
/* 5219 */     if (cachedMetaData == null) {
/*      */ 
/*      */       
/* 5222 */       cachedMetaData = new CachedResultSetMetaData();
/*      */ 
/*      */       
/* 5225 */       resultSet.buildIndexMapping();
/* 5226 */       resultSet.initializeWithMetadata();
/*      */       
/* 5228 */       if (resultSet instanceof UpdatableResultSet) {
/* 5229 */         ((UpdatableResultSet)resultSet).checkUpdatability();
/*      */       }
/*      */       
/* 5232 */       resultSet.populateCachedMetaData(cachedMetaData);
/*      */       
/* 5234 */       this.resultSetMetadataCache.put(sql, cachedMetaData);
/*      */     } else {
/* 5236 */       resultSet.initializeFromCachedMetaData(cachedMetaData);
/* 5237 */       resultSet.initializeWithMetadata();
/*      */       
/* 5239 */       if (resultSet instanceof UpdatableResultSet) {
/* 5240 */         ((UpdatableResultSet)resultSet).checkUpdatability();
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
/*      */   public String getStatementComment() {
/* 5253 */     return this.statementComment;
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
/*      */   public void setStatementComment(String comment) {
/* 5266 */     this.statementComment = comment;
/*      */   }
/*      */   
/*      */   public void reportQueryTime(long millisOrNanos) {
/* 5270 */     synchronized (getConnectionMutex()) {
/* 5271 */       this.queryTimeCount++;
/* 5272 */       this.queryTimeSum += millisOrNanos;
/* 5273 */       this.queryTimeSumSquares += (millisOrNanos * millisOrNanos);
/* 5274 */       this.queryTimeMean = (this.queryTimeMean * (this.queryTimeCount - 1L) + millisOrNanos) / this.queryTimeCount;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isAbonormallyLongQuery(long millisOrNanos) {
/* 5279 */     synchronized (getConnectionMutex()) {
/* 5280 */       boolean res = false;
/* 5281 */       if (this.queryTimeCount > 14L) {
/* 5282 */         double stddev = Math.sqrt((this.queryTimeSumSquares - this.queryTimeSum * this.queryTimeSum / this.queryTimeCount) / (this.queryTimeCount - 1L));
/*      */         
/* 5284 */         res = (millisOrNanos > this.queryTimeMean + 5.0D * stddev);
/*      */       } 
/* 5286 */       reportQueryTime(millisOrNanos);
/* 5287 */       return res;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void initializeExtension(Extension ex) throws SQLException {
/* 5292 */     ex.init(this, this.props);
/*      */   }
/*      */   
/*      */   public void transactionBegun() throws SQLException {
/* 5296 */     synchronized (getConnectionMutex()) {
/* 5297 */       if (this.connectionLifecycleInterceptors != null) {
/* 5298 */         IterateBlock<Extension> iter = new IterateBlock<Extension>(this.connectionLifecycleInterceptors.iterator())
/*      */           {
/*      */             void forEach(Extension each) throws SQLException
/*      */             {
/* 5302 */               ((ConnectionLifecycleInterceptor)each).transactionBegun();
/*      */             }
/*      */           };
/*      */         
/* 5306 */         iter.doForAll();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void transactionCompleted() throws SQLException {
/* 5312 */     synchronized (getConnectionMutex()) {
/* 5313 */       if (this.connectionLifecycleInterceptors != null) {
/* 5314 */         IterateBlock<Extension> iter = new IterateBlock<Extension>(this.connectionLifecycleInterceptors.iterator())
/*      */           {
/*      */             void forEach(Extension each) throws SQLException
/*      */             {
/* 5318 */               ((ConnectionLifecycleInterceptor)each).transactionCompleted();
/*      */             }
/*      */           };
/*      */         
/* 5322 */         iter.doForAll();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean storesLowerCaseTableName() {
/* 5328 */     return this.storesLowerCaseTableName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ExceptionInterceptor getExceptionInterceptor() {
/* 5335 */     return this.exceptionInterceptor;
/*      */   }
/*      */   
/*      */   public boolean getRequiresEscapingEncoder() {
/* 5339 */     return this.requiresEscapingEncoder;
/*      */   }
/*      */   
/*      */   public boolean isServerLocal() throws SQLException {
/* 5343 */     synchronized (getConnectionMutex()) {
/* 5344 */       SocketFactory factory = (getIO()).socketFactory;
/*      */       
/* 5346 */       if (factory instanceof SocketMetadata) {
/* 5347 */         return ((SocketMetadata)factory).isLocallyConnected(this);
/*      */       }
/* 5349 */       getLog().logWarn(Messages.getString("Connection.NoMetadataOnSocketFactory"));
/* 5350 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSessionMaxRows() {
/* 5358 */     synchronized (getConnectionMutex()) {
/* 5359 */       return this.sessionMaxRows;
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
/*      */   public void setSessionMaxRows(int max) throws SQLException {
/* 5372 */     synchronized (getConnectionMutex()) {
/* 5373 */       if (this.sessionMaxRows != max) {
/* 5374 */         this.sessionMaxRows = max;
/* 5375 */         execSQL((StatementImpl)null, "SET SQL_SELECT_LIMIT=" + ((this.sessionMaxRows == -1) ? "DEFAULT" : (String)Integer.valueOf(this.sessionMaxRows)), -1, (Buffer)null, 1003, 1007, false, this.database, (Field[])null, false);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSchema(String schema) throws SQLException {
/* 5384 */     synchronized (getConnectionMutex()) {
/* 5385 */       checkClosed();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getSchema() throws SQLException {
/* 5391 */     synchronized (getConnectionMutex()) {
/* 5392 */       checkClosed();
/*      */       
/* 5394 */       return null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void abort(Executor executor) throws SQLException {
/* 5429 */     SecurityManager sec = System.getSecurityManager();
/*      */     
/* 5431 */     if (sec != null) {
/* 5432 */       sec.checkPermission(ABORT_PERM);
/*      */     }
/*      */     
/* 5435 */     if (executor == null) {
/* 5436 */       throw SQLError.createSQLException("Executor can not be null", "S1009", getExceptionInterceptor());
/*      */     }
/*      */     
/* 5439 */     executor.execute(new Runnable()
/*      */         {
/*      */           public void run() {
/*      */             try {
/* 5443 */               ConnectionImpl.this.abortInternal();
/* 5444 */             } catch (SQLException e) {
/* 5445 */               throw new RuntimeException(e);
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
/* 5453 */     synchronized (getConnectionMutex()) {
/* 5454 */       SecurityManager sec = System.getSecurityManager();
/*      */       
/* 5456 */       if (sec != null) {
/* 5457 */         sec.checkPermission(SET_NETWORK_TIMEOUT_PERM);
/*      */       }
/*      */       
/* 5460 */       if (executor == null) {
/* 5461 */         throw SQLError.createSQLException("Executor can not be null", "S1009", getExceptionInterceptor());
/*      */       }
/*      */       
/* 5464 */       checkClosed();
/* 5465 */       executor.execute(new NetworkTimeoutSetter(this, this.io, milliseconds));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNetworkTimeout() throws SQLException {
/* 5471 */     synchronized (getConnectionMutex()) {
/* 5472 */       checkClosed();
/* 5473 */       return getSocketTimeout();
/*      */     } 
/*      */   }
/*      */   
/*      */   public ProfilerEventHandler getProfilerEventHandlerInstance() {
/* 5478 */     return this.eventSink;
/*      */   }
/*      */   
/*      */   public void setProfilerEventHandlerInstance(ProfilerEventHandler h) {
/* 5482 */     this.eventSink = h;
/*      */   }
/*      */   
/*      */   public boolean isServerTruncatesFracSecs() {
/* 5486 */     return this.serverTruncatesFracSecs;
/*      */   }
/*      */   
/*      */   private static class NetworkTimeoutSetter implements Runnable {
/*      */     private final WeakReference<ConnectionImpl> connImplRef;
/*      */     private final WeakReference<MysqlIO> mysqlIoRef;
/*      */     private final int milliseconds;
/*      */     
/*      */     public NetworkTimeoutSetter(ConnectionImpl conn, MysqlIO io, int milliseconds) {
/* 5495 */       this.connImplRef = new WeakReference<ConnectionImpl>(conn);
/* 5496 */       this.mysqlIoRef = new WeakReference<MysqlIO>(io);
/* 5497 */       this.milliseconds = milliseconds;
/*      */     }
/*      */     
/*      */     public void run() {
/*      */       try {
/* 5502 */         ConnectionImpl conn = this.connImplRef.get();
/* 5503 */         if (conn != null) {
/* 5504 */           synchronized (conn.getConnectionMutex()) {
/* 5505 */             conn.setSocketTimeout(this.milliseconds);
/* 5506 */             MysqlIO io = this.mysqlIoRef.get();
/* 5507 */             if (io != null) {
/* 5508 */               io.setSocketTimeout(this.milliseconds);
/*      */             }
/*      */           } 
/*      */         }
/* 5512 */       } catch (SQLException e) {
/* 5513 */         throw new RuntimeException(e);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public String getQueryTimingUnits() {
/* 5519 */     return (this.io != null) ? this.io.getQueryTimingUnits() : Constants.MILLIS_I18N;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ConnectionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */