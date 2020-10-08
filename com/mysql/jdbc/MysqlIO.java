/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import com.mysql.jdbc.authentication.CachingSha2PasswordPlugin;
/*      */ import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;
/*      */ import com.mysql.jdbc.authentication.MysqlNativePasswordPlugin;
/*      */ import com.mysql.jdbc.authentication.MysqlOldPasswordPlugin;
/*      */ import com.mysql.jdbc.authentication.Sha256PasswordPlugin;
/*      */ import com.mysql.jdbc.exceptions.MySQLStatementCancelledException;
/*      */ import com.mysql.jdbc.exceptions.MySQLTimeoutException;
/*      */ import com.mysql.jdbc.util.ReadAheadInputStream;
/*      */ import com.mysql.jdbc.util.ResultSetUtil;
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.management.ManagementFactory;
/*      */ import java.lang.management.ThreadInfo;
/*      */ import java.lang.management.ThreadMXBean;
/*      */ import java.lang.ref.SoftReference;
/*      */ import java.math.BigInteger;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.Socket;
/*      */ import java.net.SocketException;
/*      */ import java.net.URL;
/*      */ import java.security.NoSuchAlgorithmException;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.zip.Deflater;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MysqlIO
/*      */ {
/*      */   private static final String CODE_PAGE_1252 = "Cp1252";
/*      */   protected static final int NULL_LENGTH = -1;
/*      */   protected static final int COMP_HEADER_LENGTH = 3;
/*      */   protected static final int MIN_COMPRESS_LEN = 50;
/*      */   protected static final int HEADER_LENGTH = 4;
/*      */   protected static final int AUTH_411_OVERHEAD = 33;
/*      */   public static final int SEED_LENGTH = 20;
/*   79 */   private static int maxBufferSize = 65535;
/*      */   
/*      */   private static final String NONE = "none";
/*      */   
/*      */   private static final int CLIENT_LONG_PASSWORD = 1;
/*      */   
/*      */   private static final int CLIENT_FOUND_ROWS = 2;
/*      */   
/*      */   private static final int CLIENT_LONG_FLAG = 4;
/*      */   
/*      */   protected static final int CLIENT_CONNECT_WITH_DB = 8;
/*      */   
/*      */   private static final int CLIENT_COMPRESS = 32;
/*      */   private static final int CLIENT_LOCAL_FILES = 128;
/*      */   private static final int CLIENT_PROTOCOL_41 = 512;
/*      */   private static final int CLIENT_INTERACTIVE = 1024;
/*      */   protected static final int CLIENT_SSL = 2048;
/*      */   private static final int CLIENT_TRANSACTIONS = 8192;
/*      */   protected static final int CLIENT_RESERVED = 16384;
/*      */   protected static final int CLIENT_SECURE_CONNECTION = 32768;
/*      */   private static final int CLIENT_MULTI_STATEMENTS = 65536;
/*      */   private static final int CLIENT_MULTI_RESULTS = 131072;
/*      */   private static final int CLIENT_PLUGIN_AUTH = 524288;
/*      */   private static final int CLIENT_CONNECT_ATTRS = 1048576;
/*      */   private static final int CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA = 2097152;
/*      */   private static final int CLIENT_CAN_HANDLE_EXPIRED_PASSWORD = 4194304;
/*      */   private static final int CLIENT_SESSION_TRACK = 8388608;
/*      */   private static final int CLIENT_DEPRECATE_EOF = 16777216;
/*      */   private static final int SERVER_STATUS_IN_TRANS = 1;
/*      */   private static final int SERVER_STATUS_AUTOCOMMIT = 2;
/*      */   static final int SERVER_MORE_RESULTS_EXISTS = 8;
/*      */   private static final int SERVER_QUERY_NO_GOOD_INDEX_USED = 16;
/*      */   private static final int SERVER_QUERY_NO_INDEX_USED = 32;
/*      */   private static final int SERVER_QUERY_WAS_SLOW = 2048;
/*      */   private static final int SERVER_STATUS_CURSOR_EXISTS = 64;
/*      */   private static final String FALSE_SCRAMBLE = "xxxxxxxx";
/*      */   protected static final int MAX_QUERY_SIZE_TO_LOG = 1024;
/*      */   protected static final int MAX_QUERY_SIZE_TO_EXPLAIN = 1048576;
/*      */   protected static final int INITIAL_PACKET_SIZE = 1024;
/*  118 */   private static String jvmPlatformCharset = null;
/*      */ 
/*      */   
/*      */   protected static final String ZERO_DATE_VALUE_MARKER = "0000-00-00";
/*      */   
/*      */   protected static final String ZERO_DATETIME_VALUE_MARKER = "0000-00-00 00:00:00";
/*      */   
/*      */   private static final String EXPLAINABLE_STATEMENT = "SELECT";
/*      */   
/*  127 */   private static final String[] EXPLAINABLE_STATEMENT_EXTENSION = new String[] { "INSERT", "UPDATE", "REPLACE", "DELETE" }; private static final int MAX_PACKET_DUMP_LENGTH = 1024;
/*      */   
/*      */   static {
/*  130 */     OutputStreamWriter outWriter = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  137 */       outWriter = new OutputStreamWriter(new ByteArrayOutputStream());
/*  138 */       jvmPlatformCharset = outWriter.getEncoding();
/*      */     } finally {
/*      */       try {
/*  141 */         if (outWriter != null) {
/*  142 */           outWriter.close();
/*      */         }
/*  144 */       } catch (IOException ioEx) {}
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean packetSequenceReset = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int serverCharsetIndex;
/*      */ 
/*      */   
/*  158 */   private Buffer reusablePacket = null;
/*  159 */   private Buffer sendPacket = null;
/*  160 */   private Buffer sharedSendPacket = null;
/*      */ 
/*      */   
/*  163 */   protected BufferedOutputStream mysqlOutput = null;
/*      */   protected MySQLConnection connection;
/*  165 */   private Deflater deflater = null;
/*  166 */   protected InputStream mysqlInput = null;
/*  167 */   private LinkedList<StringBuilder> packetDebugRingBuffer = null;
/*  168 */   private RowData streamingData = null;
/*      */ 
/*      */   
/*  171 */   public Socket mysqlConnection = null;
/*  172 */   protected SocketFactory socketFactory = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private SoftReference<Buffer> loadFileBufRef;
/*      */ 
/*      */ 
/*      */   
/*      */   private SoftReference<Buffer> splitBufRef;
/*      */ 
/*      */ 
/*      */   
/*      */   private SoftReference<Buffer> compressBufRef;
/*      */ 
/*      */   
/*  187 */   protected String host = null;
/*      */   protected String seed;
/*  189 */   private String serverVersion = null;
/*  190 */   private String socketFactoryClassName = null;
/*  191 */   private byte[] packetHeaderBuf = new byte[4];
/*      */   
/*      */   private boolean colDecimalNeedsBump = false;
/*      */   
/*      */   private boolean hadWarnings = false;
/*      */   
/*      */   private boolean has41NewNewProt = false;
/*      */   
/*      */   private boolean hasLongColumnInfo = false;
/*      */   
/*      */   private boolean isInteractiveClient = false;
/*      */   
/*      */   private boolean logSlowQueries = false;
/*      */   
/*      */   private boolean platformDbCharsetMatches = true;
/*      */   
/*      */   private boolean profileSql = false;
/*      */   
/*      */   private boolean queryBadIndexUsed = false;
/*      */   private boolean queryNoIndexUsed = false;
/*      */   private boolean serverQueryWasSlow = false;
/*      */   private boolean use41Extensions = false;
/*      */   private boolean useCompression = false;
/*      */   private boolean useNewLargePackets = false;
/*      */   private boolean useNewUpdateCounts = false;
/*  216 */   private byte packetSequence = 0;
/*  217 */   private byte compressedPacketSequence = 0;
/*  218 */   private byte readPacketSequence = -1;
/*      */   private boolean checkPacketSequence = false;
/*  220 */   private byte protocolVersion = 0;
/*  221 */   private int maxAllowedPacket = 1048576;
/*  222 */   protected int maxThreeBytes = 16581375;
/*  223 */   protected int port = 3306;
/*      */   protected int serverCapabilities;
/*  225 */   private int serverMajorVersion = 0;
/*  226 */   private int serverMinorVersion = 0;
/*  227 */   private int oldServerStatus = 0;
/*  228 */   private int serverStatus = 0;
/*  229 */   private int serverSubMinorVersion = 0;
/*  230 */   private int warningCount = 0;
/*  231 */   protected long clientParam = 0L;
/*  232 */   protected long lastPacketSentTimeMs = 0L;
/*  233 */   protected long lastPacketReceivedTimeMs = 0L;
/*      */   private boolean traceProtocol = false;
/*      */   private boolean enablePacketDebug = false;
/*      */   private boolean useConnectWithDb;
/*      */   private boolean needToGrabQueryFromPacket;
/*      */   private boolean autoGenerateTestcaseScript;
/*      */   private long threadId;
/*      */   private boolean useNanosForElapsedTime;
/*      */   private long slowQueryThreshold;
/*      */   private String queryTimingUnits;
/*      */   private boolean useDirectRowUnpack = true;
/*      */   private int useBufferRowSizeThreshold;
/*  245 */   private int commandCount = 0;
/*      */   private List<StatementInterceptorV2> statementInterceptors;
/*      */   private ExceptionInterceptor exceptionInterceptor;
/*  248 */   private int authPluginDataLength = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Map<String, AuthenticationPlugin> authenticationPlugins;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<String> disabledAuthenticationPlugins;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String clientDefaultAuthenticationPlugin;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String clientDefaultAuthenticationPluginName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String serverDefaultAuthenticationPluginName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int statementExecutionDepth;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean useAutoSlowLog;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasLongColumnInfo() {
/*  345 */     return this.hasLongColumnInfo;
/*      */   }
/*      */   
/*      */   protected boolean isDataAvailable() throws SQLException {
/*      */     try {
/*  350 */       return (this.mysqlInput.available() > 0);
/*  351 */     } catch (IOException ioEx) {
/*  352 */       throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ioEx, getExceptionInterceptor());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long getLastPacketSentTimeMs() {
/*  361 */     return this.lastPacketSentTimeMs;
/*      */   }
/*      */   
/*      */   protected long getLastPacketReceivedTimeMs() {
/*  365 */     return this.lastPacketReceivedTimeMs;
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
/*      */   protected ResultSetImpl getResultSet(StatementImpl callingStatement, long columnCount, int maxRows, int resultSetType, int resultSetConcurrency, boolean streamResults, String catalog, boolean isBinaryEncoded, Field[] metadataFromCache) throws SQLException {
/*  401 */     Field[] fields = null;
/*      */ 
/*      */ 
/*      */     
/*  405 */     if (metadataFromCache == null) {
/*  406 */       fields = new Field[(int)columnCount];
/*      */       
/*  408 */       for (int i = 0; i < columnCount; i++) {
/*  409 */         Buffer fieldPacket = null;
/*      */         
/*  411 */         fieldPacket = readPacket();
/*  412 */         fields[i] = unpackField(fieldPacket, false);
/*      */       } 
/*      */     } else {
/*  415 */       for (int i = 0; i < columnCount; i++) {
/*  416 */         skipPacket();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  421 */     if (!isEOFDeprecated() || (this.connection.versionMeetsMinimum(5, 0, 2) && callingStatement != null && isBinaryEncoded && callingStatement.isCursorRequired())) {
/*      */ 
/*      */ 
/*      */       
/*  425 */       Buffer packet = reuseAndReadPacket(this.reusablePacket);
/*  426 */       readServerStatusForResultSets(packet);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  433 */     if (this.connection.versionMeetsMinimum(5, 0, 2) && this.connection.getUseCursorFetch() && isBinaryEncoded && callingStatement != null && callingStatement.getFetchSize() != 0 && callingStatement.getResultSetType() == 1003) {
/*      */       
/*  435 */       ServerPreparedStatement prepStmt = (ServerPreparedStatement)callingStatement;
/*      */       
/*  437 */       boolean usingCursor = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  444 */       if (this.connection.versionMeetsMinimum(5, 0, 5)) {
/*  445 */         usingCursor = ((this.serverStatus & 0x40) != 0);
/*      */       }
/*      */       
/*  448 */       if (usingCursor) {
/*  449 */         RowData rows = new RowDataCursor(this, prepStmt, fields);
/*      */         
/*  451 */         ResultSetImpl resultSetImpl = buildResultSetWithRows(callingStatement, catalog, fields, rows, resultSetType, resultSetConcurrency, isBinaryEncoded);
/*      */         
/*  453 */         if (usingCursor) {
/*  454 */           resultSetImpl.setFetchSize(callingStatement.getFetchSize());
/*      */         }
/*      */         
/*  457 */         return resultSetImpl;
/*      */       } 
/*      */     } 
/*      */     
/*  461 */     RowData rowData = null;
/*      */     
/*  463 */     if (!streamResults) {
/*  464 */       rowData = readSingleRowSet(columnCount, maxRows, resultSetConcurrency, isBinaryEncoded, (metadataFromCache == null) ? fields : metadataFromCache);
/*      */     } else {
/*  466 */       rowData = new RowDataDynamic(this, (int)columnCount, (metadataFromCache == null) ? fields : metadataFromCache, isBinaryEncoded);
/*  467 */       this.streamingData = rowData;
/*      */     } 
/*      */     
/*  470 */     ResultSetImpl rs = buildResultSetWithRows(callingStatement, catalog, (metadataFromCache == null) ? fields : metadataFromCache, rowData, resultSetType, resultSetConcurrency, isBinaryEncoded);
/*      */ 
/*      */     
/*  473 */     return rs;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected NetworkResources getNetworkResources() {
/*  479 */     return new NetworkResources(this.mysqlConnection, this.mysqlInput, this.mysqlOutput);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void forceClose() {
/*      */     try {
/*  487 */       getNetworkResources().forceClose();
/*      */     } finally {
/*  489 */       this.mysqlConnection = null;
/*  490 */       this.mysqlInput = null;
/*  491 */       this.mysqlOutput = null;
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
/*      */   protected final void skipPacket() throws SQLException {
/*      */     try {
/*  505 */       int lengthRead = readFully(this.mysqlInput, this.packetHeaderBuf, 0, 4);
/*      */       
/*  507 */       if (lengthRead < 4) {
/*  508 */         forceClose();
/*  509 */         throw new IOException(Messages.getString("MysqlIO.1"));
/*      */       } 
/*      */       
/*  512 */       int packetLength = (this.packetHeaderBuf[0] & 0xFF) + ((this.packetHeaderBuf[1] & 0xFF) << 8) + ((this.packetHeaderBuf[2] & 0xFF) << 16);
/*      */       
/*  514 */       if (this.traceProtocol) {
/*  515 */         StringBuilder traceMessageBuf = new StringBuilder();
/*      */         
/*  517 */         traceMessageBuf.append(Messages.getString("MysqlIO.2"));
/*  518 */         traceMessageBuf.append(packetLength);
/*  519 */         traceMessageBuf.append(Messages.getString("MysqlIO.3"));
/*  520 */         traceMessageBuf.append(StringUtils.dumpAsHex(this.packetHeaderBuf, 4));
/*      */         
/*  522 */         this.connection.getLog().logTrace(traceMessageBuf.toString());
/*      */       } 
/*      */       
/*  525 */       byte multiPacketSeq = this.packetHeaderBuf[3];
/*      */       
/*  527 */       if (!this.packetSequenceReset) {
/*  528 */         if (this.enablePacketDebug && this.checkPacketSequence) {
/*  529 */           checkPacketSequencing(multiPacketSeq);
/*      */         }
/*      */       } else {
/*  532 */         this.packetSequenceReset = false;
/*      */       } 
/*      */       
/*  535 */       this.readPacketSequence = multiPacketSeq;
/*      */       
/*  537 */       skipFully(this.mysqlInput, packetLength);
/*  538 */     } catch (IOException ioEx) {
/*  539 */       throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ioEx, getExceptionInterceptor());
/*      */     }
/*  541 */     catch (OutOfMemoryError oom) {
/*      */       try {
/*  543 */         this.connection.realClose(false, false, true, oom);
/*  544 */       } catch (Exception ex) {}
/*      */       
/*  546 */       throw oom;
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
/*      */   protected final Buffer readPacket() throws SQLException {
/*      */     try {
/*  561 */       int lengthRead = readFully(this.mysqlInput, this.packetHeaderBuf, 0, 4);
/*      */       
/*  563 */       if (lengthRead < 4) {
/*  564 */         forceClose();
/*  565 */         throw new IOException(Messages.getString("MysqlIO.1"));
/*      */       } 
/*      */       
/*  568 */       int packetLength = (this.packetHeaderBuf[0] & 0xFF) + ((this.packetHeaderBuf[1] & 0xFF) << 8) + ((this.packetHeaderBuf[2] & 0xFF) << 16);
/*      */       
/*  570 */       if (packetLength > this.maxAllowedPacket) {
/*  571 */         throw new PacketTooBigException(packetLength, this.maxAllowedPacket);
/*      */       }
/*      */       
/*  574 */       if (this.traceProtocol) {
/*  575 */         StringBuilder traceMessageBuf = new StringBuilder();
/*      */         
/*  577 */         traceMessageBuf.append(Messages.getString("MysqlIO.2"));
/*  578 */         traceMessageBuf.append(packetLength);
/*  579 */         traceMessageBuf.append(Messages.getString("MysqlIO.3"));
/*  580 */         traceMessageBuf.append(StringUtils.dumpAsHex(this.packetHeaderBuf, 4));
/*      */         
/*  582 */         this.connection.getLog().logTrace(traceMessageBuf.toString());
/*      */       } 
/*      */       
/*  585 */       byte multiPacketSeq = this.packetHeaderBuf[3];
/*      */       
/*  587 */       if (!this.packetSequenceReset) {
/*  588 */         if (this.enablePacketDebug && this.checkPacketSequence) {
/*  589 */           checkPacketSequencing(multiPacketSeq);
/*      */         }
/*      */       } else {
/*  592 */         this.packetSequenceReset = false;
/*      */       } 
/*      */       
/*  595 */       this.readPacketSequence = multiPacketSeq;
/*      */ 
/*      */       
/*  598 */       byte[] buffer = new byte[packetLength];
/*  599 */       int numBytesRead = readFully(this.mysqlInput, buffer, 0, packetLength);
/*      */       
/*  601 */       if (numBytesRead != packetLength) {
/*  602 */         throw new IOException("Short read, expected " + packetLength + " bytes, only read " + numBytesRead);
/*      */       }
/*      */       
/*  605 */       Buffer packet = new Buffer(buffer);
/*      */       
/*  607 */       if (this.traceProtocol) {
/*  608 */         StringBuilder traceMessageBuf = new StringBuilder();
/*      */         
/*  610 */         traceMessageBuf.append(Messages.getString("MysqlIO.4"));
/*  611 */         traceMessageBuf.append(getPacketDumpToLog(packet, packetLength));
/*      */         
/*  613 */         this.connection.getLog().logTrace(traceMessageBuf.toString());
/*      */       } 
/*      */       
/*  616 */       if (this.enablePacketDebug) {
/*  617 */         enqueuePacketForDebugging(false, false, 0, this.packetHeaderBuf, packet);
/*      */       }
/*      */       
/*  620 */       if (this.connection.getMaintainTimeStats()) {
/*  621 */         this.lastPacketReceivedTimeMs = System.currentTimeMillis();
/*      */       }
/*      */       
/*  624 */       return packet;
/*  625 */     } catch (IOException ioEx) {
/*  626 */       throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ioEx, getExceptionInterceptor());
/*      */     }
/*  628 */     catch (OutOfMemoryError oom) {
/*      */       try {
/*  630 */         this.connection.realClose(false, false, true, oom);
/*  631 */       } catch (Exception ex) {}
/*      */       
/*  633 */       throw oom;
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
/*      */   protected final Field unpackField(Buffer packet, boolean extractDefaultValues) throws SQLException {
/*  651 */     if (this.use41Extensions) {
/*      */ 
/*      */       
/*  654 */       if (this.has41NewNewProt) {
/*      */         
/*  656 */         int catalogNameStart = packet.getPosition() + 1;
/*  657 */         int catalogNameLength = packet.fastSkipLenString();
/*  658 */         catalogNameStart = adjustStartForFieldLength(catalogNameStart, catalogNameLength);
/*      */       } 
/*      */       
/*  661 */       int databaseNameStart = packet.getPosition() + 1;
/*  662 */       int databaseNameLength = packet.fastSkipLenString();
/*  663 */       databaseNameStart = adjustStartForFieldLength(databaseNameStart, databaseNameLength);
/*      */       
/*  665 */       int i = packet.getPosition() + 1;
/*  666 */       int j = packet.fastSkipLenString();
/*  667 */       i = adjustStartForFieldLength(i, j);
/*      */ 
/*      */       
/*  670 */       int originalTableNameStart = packet.getPosition() + 1;
/*  671 */       int originalTableNameLength = packet.fastSkipLenString();
/*  672 */       originalTableNameStart = adjustStartForFieldLength(originalTableNameStart, originalTableNameLength);
/*      */ 
/*      */       
/*  675 */       int k = packet.getPosition() + 1;
/*  676 */       int m = packet.fastSkipLenString();
/*      */       
/*  678 */       k = adjustStartForFieldLength(k, m);
/*      */ 
/*      */       
/*  681 */       int originalColumnNameStart = packet.getPosition() + 1;
/*  682 */       int originalColumnNameLength = packet.fastSkipLenString();
/*  683 */       originalColumnNameStart = adjustStartForFieldLength(originalColumnNameStart, originalColumnNameLength);
/*      */       
/*  685 */       packet.readByte();
/*      */       
/*  687 */       short charSetNumber = (short)packet.readInt();
/*      */       
/*  689 */       long l = 0L;
/*      */       
/*  691 */       if (this.has41NewNewProt) {
/*  692 */         l = packet.readLong();
/*      */       } else {
/*  694 */         l = packet.readLongInt();
/*      */       } 
/*      */       
/*  697 */       int n = packet.readByte() & 0xFF;
/*      */       
/*  699 */       short s1 = 0;
/*      */       
/*  701 */       if (this.hasLongColumnInfo) {
/*  702 */         s1 = (short)packet.readInt();
/*      */       } else {
/*  704 */         s1 = (short)(packet.readByte() & 0xFF);
/*      */       } 
/*      */       
/*  707 */       int i1 = packet.readByte() & 0xFF;
/*      */       
/*  709 */       int defaultValueStart = -1;
/*  710 */       int defaultValueLength = -1;
/*      */       
/*  712 */       if (extractDefaultValues) {
/*  713 */         defaultValueStart = packet.getPosition() + 1;
/*  714 */         defaultValueLength = packet.fastSkipLenString();
/*      */       } 
/*      */       
/*  717 */       Field field1 = new Field(this.connection, packet.getByteBuffer(), databaseNameStart, databaseNameLength, i, j, originalTableNameStart, originalTableNameLength, k, m, originalColumnNameStart, originalColumnNameLength, l, n, s1, i1, defaultValueStart, defaultValueLength, charSetNumber);
/*      */ 
/*      */ 
/*      */       
/*  721 */       return field1;
/*      */     } 
/*      */     
/*  724 */     int tableNameStart = packet.getPosition() + 1;
/*  725 */     int tableNameLength = packet.fastSkipLenString();
/*  726 */     tableNameStart = adjustStartForFieldLength(tableNameStart, tableNameLength);
/*      */     
/*  728 */     int nameStart = packet.getPosition() + 1;
/*  729 */     int nameLength = packet.fastSkipLenString();
/*  730 */     nameStart = adjustStartForFieldLength(nameStart, nameLength);
/*      */     
/*  732 */     int colLength = packet.readnBytes();
/*  733 */     int colType = packet.readnBytes();
/*  734 */     packet.readByte();
/*      */     
/*  736 */     short colFlag = 0;
/*      */     
/*  738 */     if (this.hasLongColumnInfo) {
/*  739 */       colFlag = (short)packet.readInt();
/*      */     } else {
/*  741 */       colFlag = (short)(packet.readByte() & 0xFF);
/*      */     } 
/*      */     
/*  744 */     int colDecimals = packet.readByte() & 0xFF;
/*      */     
/*  746 */     if (this.colDecimalNeedsBump) {
/*  747 */       colDecimals++;
/*      */     }
/*      */     
/*  750 */     Field field = new Field(this.connection, packet.getByteBuffer(), nameStart, nameLength, tableNameStart, tableNameLength, colLength, colType, colFlag, colDecimals);
/*      */ 
/*      */     
/*  753 */     return field;
/*      */   }
/*      */   
/*      */   private int adjustStartForFieldLength(int nameStart, int nameLength) {
/*  757 */     if (nameLength < 251) {
/*  758 */       return nameStart;
/*      */     }
/*      */     
/*  761 */     if (nameLength >= 251 && nameLength < 65536) {
/*  762 */       return nameStart + 2;
/*      */     }
/*      */     
/*  765 */     if (nameLength >= 65536 && nameLength < 16777216) {
/*  766 */       return nameStart + 3;
/*      */     }
/*      */     
/*  769 */     return nameStart + 8;
/*      */   }
/*      */   
/*      */   protected boolean isSetNeededForAutoCommitMode(boolean autoCommitFlag) {
/*  773 */     if (this.use41Extensions && this.connection.getElideSetAutoCommits()) {
/*  774 */       boolean autoCommitModeOnServer = ((this.serverStatus & 0x2) != 0);
/*      */       
/*  776 */       if (!autoCommitFlag && versionMeetsMinimum(5, 0, 0))
/*      */       {
/*      */ 
/*      */         
/*  780 */         return !inTransactionOnServer();
/*      */       }
/*      */       
/*  783 */       return (autoCommitModeOnServer != autoCommitFlag);
/*      */     } 
/*      */     
/*  786 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean inTransactionOnServer() {
/*  790 */     return ((this.serverStatus & 0x1) != 0);
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
/*      */   protected void changeUser(String userName, String password, String database) throws SQLException {
/*  803 */     this.packetSequence = -1;
/*  804 */     this.compressedPacketSequence = -1;
/*      */     
/*  806 */     int passwordLength = 16;
/*  807 */     int userLength = (userName != null) ? userName.length() : 0;
/*  808 */     int databaseLength = (database != null) ? database.length() : 0;
/*      */     
/*  810 */     int packLength = (userLength + passwordLength + databaseLength) * 3 + 7 + 4 + 33;
/*      */     
/*  812 */     if ((this.serverCapabilities & 0x80000) != 0) {
/*      */       
/*  814 */       proceedHandshakeWithPluggableAuthentication(userName, password, database, null);
/*      */     }
/*  816 */     else if ((this.serverCapabilities & 0x8000) != 0) {
/*  817 */       Buffer changeUserPacket = new Buffer(packLength + 1);
/*  818 */       changeUserPacket.writeByte((byte)17);
/*      */       
/*  820 */       if (versionMeetsMinimum(4, 1, 1)) {
/*  821 */         secureAuth411(changeUserPacket, packLength, userName, password, database, false, true);
/*      */       } else {
/*  823 */         secureAuth(changeUserPacket, packLength, userName, password, database, false);
/*      */       } 
/*      */     } else {
/*      */       
/*  827 */       Buffer packet = new Buffer(packLength);
/*  828 */       packet.writeByte((byte)17);
/*      */ 
/*      */       
/*  831 */       packet.writeString(userName);
/*      */       
/*  833 */       if (this.protocolVersion > 9) {
/*  834 */         packet.writeString(Util.newCrypt(password, this.seed, this.connection.getPasswordCharacterEncoding()));
/*      */       } else {
/*  836 */         packet.writeString(Util.oldCrypt(password, this.seed));
/*      */       } 
/*      */       
/*  839 */       boolean localUseConnectWithDb = (this.useConnectWithDb && database != null && database.length() > 0);
/*      */       
/*  841 */       if (localUseConnectWithDb) {
/*  842 */         packet.writeString(database);
/*      */       }
/*      */       
/*  845 */       send(packet, packet.getPosition());
/*  846 */       checkErrorPacket();
/*      */       
/*  848 */       if (!localUseConnectWithDb) {
/*  849 */         changeDatabaseTo(database);
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
/*      */   protected Buffer checkErrorPacket() throws SQLException {
/*  864 */     return checkErrorPacket(-1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkForCharsetMismatch() {
/*  871 */     if (this.connection.getUseUnicode() && this.connection.getEncoding() != null) {
/*  872 */       String encodingToCheck = jvmPlatformCharset;
/*      */       
/*  874 */       if (encodingToCheck == null) {
/*  875 */         encodingToCheck = System.getProperty("file.encoding");
/*      */       }
/*      */       
/*  878 */       if (encodingToCheck == null) {
/*  879 */         this.platformDbCharsetMatches = false;
/*      */       } else {
/*  881 */         this.platformDbCharsetMatches = encodingToCheck.equals(this.connection.getEncoding());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void clearInputStream() throws SQLException {
/*      */     try {
/*      */       int len;
/*  892 */       while ((len = this.mysqlInput.available()) > 0 && this.mysqlInput.skip(len) > 0L);
/*      */     
/*      */     }
/*  895 */     catch (IOException ioEx) {
/*  896 */       throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ioEx, getExceptionInterceptor());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void resetReadPacketSequence() {
/*  902 */     this.readPacketSequence = 0;
/*      */   }
/*      */   
/*      */   protected void dumpPacketRingBuffer() throws SQLException {
/*  906 */     if (this.packetDebugRingBuffer != null && this.connection.getEnablePacketDebug()) {
/*  907 */       StringBuilder dumpBuffer = new StringBuilder();
/*      */       
/*  909 */       dumpBuffer.append("Last " + this.packetDebugRingBuffer.size() + " packets received from server, from oldest->newest:\n");
/*  910 */       dumpBuffer.append("\n");
/*      */       
/*  912 */       for (Iterator<StringBuilder> ringBufIter = this.packetDebugRingBuffer.iterator(); ringBufIter.hasNext(); ) {
/*  913 */         dumpBuffer.append(ringBufIter.next());
/*  914 */         dumpBuffer.append("\n");
/*      */       } 
/*      */       
/*  917 */       this.connection.getLog().logTrace(dumpBuffer.toString());
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
/*      */   protected void explainSlowQuery(byte[] querySQL, String truncatedQuery) throws SQLException {
/*  930 */     if (StringUtils.startsWithIgnoreCaseAndWs(truncatedQuery, "SELECT") || (versionMeetsMinimum(5, 6, 3) && StringUtils.startsWithIgnoreCaseAndWs(truncatedQuery, EXPLAINABLE_STATEMENT_EXTENSION) != -1)) {
/*      */ 
/*      */       
/*  933 */       PreparedStatement stmt = null;
/*  934 */       ResultSet rs = null;
/*      */ 
/*      */       
/*  937 */       try { stmt = (PreparedStatement)this.connection.clientPrepareStatement("EXPLAIN ?");
/*  938 */         stmt.setBytesNoEscapeNoQuotes(1, querySQL);
/*  939 */         rs = stmt.executeQuery();
/*      */         
/*  941 */         StringBuilder explainResults = new StringBuilder(Messages.getString("MysqlIO.8") + truncatedQuery + Messages.getString("MysqlIO.9"));
/*      */         
/*  943 */         ResultSetUtil.appendResultSetSlashGStyle(explainResults, rs);
/*      */         
/*  945 */         this.connection.getLog().logWarn(explainResults.toString()); }
/*  946 */       catch (SQLException sqlEx) {  }
/*      */       finally
/*  948 */       { if (rs != null) {
/*  949 */           rs.close();
/*      */         }
/*      */         
/*  952 */         if (stmt != null) {
/*  953 */           stmt.close();
/*      */         } }
/*      */     
/*      */     } 
/*      */   }
/*      */   
/*      */   static int getMaxBuf() {
/*  960 */     return maxBufferSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final int getServerMajorVersion() {
/*  967 */     return this.serverMajorVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final int getServerMinorVersion() {
/*  974 */     return this.serverMinorVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final int getServerSubMinorVersion() {
/*  981 */     return this.serverSubMinorVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getServerVersion() {
/*  988 */     return this.serverVersion;
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
/*      */   void doHandshake(String user, String password, String database) throws SQLException {
/* 1004 */     this.checkPacketSequence = false;
/* 1005 */     this.readPacketSequence = 0;
/*      */     
/* 1007 */     Buffer buf = readPacket();
/*      */ 
/*      */     
/* 1010 */     this.protocolVersion = buf.readByte();
/*      */     
/* 1012 */     if (this.protocolVersion == -1) {
/*      */       try {
/* 1014 */         this.mysqlConnection.close();
/* 1015 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/* 1019 */       int errno = 2000;
/*      */       
/* 1021 */       errno = buf.readInt();
/*      */       
/* 1023 */       String serverErrorMessage = buf.readString("ASCII", getExceptionInterceptor());
/*      */       
/* 1025 */       StringBuilder errorBuf = new StringBuilder(Messages.getString("MysqlIO.10"));
/* 1026 */       errorBuf.append(serverErrorMessage);
/* 1027 */       errorBuf.append("\"");
/*      */       
/* 1029 */       String xOpen = SQLError.mysqlToSqlState(errno, this.connection.getUseSqlStateCodes());
/*      */       
/* 1031 */       throw SQLError.createSQLException(SQLError.get(xOpen) + ", " + errorBuf.toString(), xOpen, errno, getExceptionInterceptor());
/*      */     } 
/*      */     
/* 1034 */     this.serverVersion = buf.readString("ASCII", getExceptionInterceptor());
/*      */ 
/*      */     
/* 1037 */     int point = this.serverVersion.indexOf('.');
/*      */     
/* 1039 */     if (point != -1) {
/*      */       try {
/* 1041 */         int n = Integer.parseInt(this.serverVersion.substring(0, point));
/* 1042 */         this.serverMajorVersion = n;
/* 1043 */       } catch (NumberFormatException NFE1) {}
/*      */ 
/*      */ 
/*      */       
/* 1047 */       String remaining = this.serverVersion.substring(point + 1, this.serverVersion.length());
/* 1048 */       point = remaining.indexOf('.');
/*      */       
/* 1050 */       if (point != -1) {
/*      */         try {
/* 1052 */           int n = Integer.parseInt(remaining.substring(0, point));
/* 1053 */           this.serverMinorVersion = n;
/* 1054 */         } catch (NumberFormatException nfe) {}
/*      */ 
/*      */ 
/*      */         
/* 1058 */         remaining = remaining.substring(point + 1, remaining.length());
/*      */         
/* 1060 */         int pos = 0;
/*      */         
/* 1062 */         while (pos < remaining.length() && 
/* 1063 */           remaining.charAt(pos) >= '0' && remaining.charAt(pos) <= '9')
/*      */         {
/*      */ 
/*      */           
/* 1067 */           pos++;
/*      */         }
/*      */         
/*      */         try {
/* 1071 */           int n = Integer.parseInt(remaining.substring(0, pos));
/* 1072 */           this.serverSubMinorVersion = n;
/* 1073 */         } catch (NumberFormatException nfe) {}
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1079 */     if (versionMeetsMinimum(4, 0, 8)) {
/* 1080 */       this.maxThreeBytes = 16777215;
/* 1081 */       this.useNewLargePackets = true;
/*      */     } else {
/* 1083 */       this.maxThreeBytes = 16581375;
/* 1084 */       this.useNewLargePackets = false;
/*      */     } 
/*      */     
/* 1087 */     this.colDecimalNeedsBump = versionMeetsMinimum(3, 23, 0);
/* 1088 */     this.colDecimalNeedsBump = !versionMeetsMinimum(3, 23, 15);
/* 1089 */     this.useNewUpdateCounts = versionMeetsMinimum(3, 22, 5);
/*      */ 
/*      */     
/* 1092 */     this.threadId = buf.readLong();
/*      */     
/* 1094 */     if (this.protocolVersion > 9) {
/*      */       
/* 1096 */       this.seed = buf.readString("ASCII", getExceptionInterceptor(), 8);
/*      */       
/* 1098 */       buf.readByte();
/*      */     } else {
/*      */       
/* 1101 */       this.seed = buf.readString("ASCII", getExceptionInterceptor());
/*      */     } 
/*      */     
/* 1104 */     this.serverCapabilities = 0;
/*      */ 
/*      */     
/* 1107 */     if (buf.getPosition() < buf.getBufLength()) {
/* 1108 */       this.serverCapabilities = buf.readInt();
/*      */     }
/*      */     
/* 1111 */     if (versionMeetsMinimum(4, 1, 1) || (this.protocolVersion > 9 && (this.serverCapabilities & 0x200) != 0)) {
/*      */ 
/*      */ 
/*      */       
/* 1115 */       this.serverCharsetIndex = buf.readByte() & 0xFF;
/*      */       
/* 1117 */       this.serverStatus = buf.readInt();
/* 1118 */       checkTransactionState(0);
/*      */ 
/*      */       
/* 1121 */       this.serverCapabilities |= buf.readInt() << 16;
/*      */       
/* 1123 */       if ((this.serverCapabilities & 0x80000) != 0) {
/*      */         
/* 1125 */         this.authPluginDataLength = buf.readByte() & 0xFF;
/*      */       } else {
/*      */         
/* 1128 */         buf.readByte();
/*      */       } 
/*      */       
/* 1131 */       buf.setPosition(buf.getPosition() + 10);
/*      */       
/* 1133 */       if ((this.serverCapabilities & 0x8000) != 0) {
/*      */         String seedPart2;
/*      */         
/*      */         StringBuilder newSeed;
/* 1137 */         if (this.authPluginDataLength > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1144 */           seedPart2 = buf.readString("ASCII", getExceptionInterceptor(), this.authPluginDataLength - 8);
/* 1145 */           newSeed = new StringBuilder(this.authPluginDataLength);
/*      */         } else {
/* 1147 */           seedPart2 = buf.readString("ASCII", getExceptionInterceptor());
/* 1148 */           newSeed = new StringBuilder(20);
/*      */         } 
/* 1150 */         newSeed.append(this.seed);
/* 1151 */         newSeed.append(seedPart2);
/* 1152 */         this.seed = newSeed.toString();
/*      */       } 
/*      */     } 
/*      */     
/* 1156 */     if ((this.serverCapabilities & 0x20) != 0 && this.connection.getUseCompression()) {
/* 1157 */       this.clientParam |= 0x20L;
/*      */     }
/*      */     
/* 1160 */     this.useConnectWithDb = (database != null && database.length() > 0 && !this.connection.getCreateDatabaseIfNotExist());
/*      */     
/* 1162 */     if (this.useConnectWithDb) {
/* 1163 */       this.clientParam |= 0x8L;
/*      */     }
/*      */ 
/*      */     
/* 1167 */     if (versionMeetsMinimum(5, 7, 0) && !this.connection.getUseSSL() && !this.connection.isUseSSLExplicit()) {
/* 1168 */       this.connection.setUseSSL(true);
/* 1169 */       this.connection.setVerifyServerCertificate(false);
/* 1170 */       this.connection.getLog().logWarn(Messages.getString("MysqlIO.SSLWarning"));
/*      */     } 
/*      */ 
/*      */     
/* 1174 */     if ((this.serverCapabilities & 0x800) == 0 && this.connection.getUseSSL()) {
/* 1175 */       if (this.connection.getRequireSSL()) {
/* 1176 */         this.connection.close();
/* 1177 */         forceClose();
/* 1178 */         throw SQLError.createSQLException(Messages.getString("MysqlIO.15"), "08001", getExceptionInterceptor());
/*      */       } 
/*      */ 
/*      */       
/* 1182 */       this.connection.setUseSSL(false);
/*      */     } 
/*      */     
/* 1185 */     if ((this.serverCapabilities & 0x4) != 0) {
/*      */       
/* 1187 */       this.clientParam |= 0x4L;
/* 1188 */       this.hasLongColumnInfo = true;
/*      */     } 
/*      */ 
/*      */     
/* 1192 */     if (!this.connection.getUseAffectedRows()) {
/* 1193 */       this.clientParam |= 0x2L;
/*      */     }
/*      */     
/* 1196 */     if (this.connection.getAllowLoadLocalInfile()) {
/* 1197 */       this.clientParam |= 0x80L;
/*      */     }
/*      */     
/* 1200 */     if (this.isInteractiveClient) {
/* 1201 */       this.clientParam |= 0x400L;
/*      */     }
/*      */     
/* 1204 */     if ((this.serverCapabilities & 0x800000) != 0);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1209 */     if ((this.serverCapabilities & 0x1000000) != 0) {
/* 1210 */       this.clientParam |= 0x1000000L;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1216 */     if ((this.serverCapabilities & 0x80000) != 0) {
/* 1217 */       proceedHandshakeWithPluggableAuthentication(user, password, database, buf);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1222 */     if (this.protocolVersion > 9) {
/* 1223 */       this.clientParam |= 0x1L;
/*      */     } else {
/* 1225 */       this.clientParam &= 0xFFFFFFFFFFFFFFFEL;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1231 */     if (versionMeetsMinimum(4, 1, 0) || (this.protocolVersion > 9 && (this.serverCapabilities & 0x4000) != 0)) {
/* 1232 */       if (versionMeetsMinimum(4, 1, 1) || (this.protocolVersion > 9 && (this.serverCapabilities & 0x200) != 0)) {
/* 1233 */         this.clientParam |= 0x200L;
/* 1234 */         this.has41NewNewProt = true;
/*      */ 
/*      */         
/* 1237 */         this.clientParam |= 0x2000L;
/*      */ 
/*      */         
/* 1240 */         this.clientParam |= 0x20000L;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1245 */         if (this.connection.getAllowMultiQueries()) {
/* 1246 */           this.clientParam |= 0x10000L;
/*      */         }
/*      */       } else {
/* 1249 */         this.clientParam |= 0x4000L;
/* 1250 */         this.has41NewNewProt = false;
/*      */       } 
/*      */       
/* 1253 */       this.use41Extensions = true;
/*      */     } 
/*      */     
/* 1256 */     int passwordLength = 16;
/* 1257 */     int userLength = (user != null) ? user.length() : 0;
/* 1258 */     int databaseLength = (database != null) ? database.length() : 0;
/*      */     
/* 1260 */     int packLength = (userLength + passwordLength + databaseLength) * 3 + 7 + 4 + 33;
/*      */     
/* 1262 */     Buffer packet = null;
/*      */     
/* 1264 */     if (!this.connection.getUseSSL()) {
/* 1265 */       if ((this.serverCapabilities & 0x8000) != 0) {
/* 1266 */         this.clientParam |= 0x8000L;
/*      */         
/* 1268 */         if (versionMeetsMinimum(4, 1, 1) || (this.protocolVersion > 9 && (this.serverCapabilities & 0x200) != 0)) {
/* 1269 */           secureAuth411(null, packLength, user, password, database, true, false);
/*      */         } else {
/* 1271 */           secureAuth(null, packLength, user, password, database, true);
/*      */         } 
/*      */       } else {
/*      */         
/* 1275 */         packet = new Buffer(packLength);
/*      */         
/* 1277 */         if ((this.clientParam & 0x4000L) != 0L) {
/* 1278 */           if (versionMeetsMinimum(4, 1, 1) || (this.protocolVersion > 9 && (this.serverCapabilities & 0x200) != 0)) {
/* 1279 */             packet.writeLong(this.clientParam);
/* 1280 */             packet.writeLong(this.maxThreeBytes);
/*      */ 
/*      */             
/* 1283 */             packet.writeByte((byte)8);
/*      */ 
/*      */             
/* 1286 */             packet.writeBytesNoNull(new byte[23]);
/*      */           } else {
/* 1288 */             packet.writeLong(this.clientParam);
/* 1289 */             packet.writeLong(this.maxThreeBytes);
/*      */           } 
/*      */         } else {
/* 1292 */           packet.writeInt((int)this.clientParam);
/* 1293 */           packet.writeLongInt(this.maxThreeBytes);
/*      */         } 
/*      */ 
/*      */         
/* 1297 */         packet.writeString(user, "Cp1252", this.connection);
/*      */         
/* 1299 */         if (this.protocolVersion > 9) {
/* 1300 */           packet.writeString(Util.newCrypt(password, this.seed, this.connection.getPasswordCharacterEncoding()), "Cp1252", this.connection);
/*      */         } else {
/* 1302 */           packet.writeString(Util.oldCrypt(password, this.seed), "Cp1252", this.connection);
/*      */         } 
/*      */         
/* 1305 */         if (this.useConnectWithDb) {
/* 1306 */           packet.writeString(database, "Cp1252", this.connection);
/*      */         }
/*      */         
/* 1309 */         send(packet, packet.getPosition());
/*      */       } 
/*      */     } else {
/* 1312 */       negotiateSSLConnection(user, password, database, packLength);
/*      */       
/* 1314 */       if ((this.serverCapabilities & 0x8000) != 0) {
/* 1315 */         if (versionMeetsMinimum(4, 1, 1)) {
/* 1316 */           secureAuth411(null, packLength, user, password, database, true, false);
/*      */         } else {
/* 1318 */           secureAuth411(null, packLength, user, password, database, true, false);
/*      */         } 
/*      */       } else {
/*      */         
/* 1322 */         packet = new Buffer(packLength);
/*      */         
/* 1324 */         if (this.use41Extensions) {
/* 1325 */           packet.writeLong(this.clientParam);
/* 1326 */           packet.writeLong(this.maxThreeBytes);
/*      */         } else {
/* 1328 */           packet.writeInt((int)this.clientParam);
/* 1329 */           packet.writeLongInt(this.maxThreeBytes);
/*      */         } 
/*      */ 
/*      */         
/* 1333 */         packet.writeString(user);
/*      */         
/* 1335 */         if (this.protocolVersion > 9) {
/* 1336 */           packet.writeString(Util.newCrypt(password, this.seed, this.connection.getPasswordCharacterEncoding()));
/*      */         } else {
/* 1338 */           packet.writeString(Util.oldCrypt(password, this.seed));
/*      */         } 
/*      */         
/* 1341 */         if ((this.serverCapabilities & 0x8) != 0 && database != null && database.length() > 0) {
/* 1342 */           packet.writeString(database);
/*      */         }
/*      */         
/* 1345 */         send(packet, packet.getPosition());
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1350 */     if (!versionMeetsMinimum(4, 1, 1) || this.protocolVersion <= 9 || (this.serverCapabilities & 0x200) == 0) {
/* 1351 */       checkErrorPacket();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1357 */     if ((this.serverCapabilities & 0x20) != 0 && this.connection.getUseCompression() && !(this.mysqlInput instanceof CompressedInputStream)) {
/*      */       
/* 1359 */       this.deflater = new Deflater();
/* 1360 */       this.useCompression = true;
/* 1361 */       this.mysqlInput = new CompressedInputStream(this.connection, this.mysqlInput);
/*      */     } 
/*      */     
/* 1364 */     if (!this.useConnectWithDb) {
/* 1365 */       changeDatabaseTo(database);
/*      */     }
/*      */ 
/*      */     
/* 1369 */     try { this.mysqlConnection = this.socketFactory.afterHandshake(); }
/* 1370 */     catch (IOException ioEx)
/* 1371 */     { throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ioEx, getExceptionInterceptor()); } 
/*      */   } private void loadAuthenticationPlugins() throws SQLException { this.clientDefaultAuthenticationPlugin = this.connection.getDefaultAuthenticationPlugin(); if (this.clientDefaultAuthenticationPlugin == null || "".equals(this.clientDefaultAuthenticationPlugin.trim())) throw SQLError.createSQLException(Messages.getString("Connection.BadDefaultAuthenticationPlugin", new Object[] { this.clientDefaultAuthenticationPlugin }), getExceptionInterceptor());  String disabledPlugins = this.connection.getDisabledAuthenticationPlugins(); if (disabledPlugins != null && !"".equals(disabledPlugins)) { this.disabledAuthenticationPlugins = new ArrayList<String>(); List<String> pluginsToDisable = StringUtils.split(disabledPlugins, ",", true); Iterator<String> iter = pluginsToDisable.iterator(); while (iter.hasNext()) this.disabledAuthenticationPlugins.add(iter.next());  }  this.authenticationPlugins = new HashMap<String, AuthenticationPlugin>(); MysqlOldPasswordPlugin mysqlOldPasswordPlugin = new MysqlOldPasswordPlugin(); mysqlOldPasswordPlugin.init(this.connection, this.connection.getProperties()); boolean defaultIsFound = addAuthenticationPlugin((AuthenticationPlugin)mysqlOldPasswordPlugin); MysqlNativePasswordPlugin mysqlNativePasswordPlugin = new MysqlNativePasswordPlugin(); mysqlNativePasswordPlugin.init(this.connection, this.connection.getProperties()); if (addAuthenticationPlugin((AuthenticationPlugin)mysqlNativePasswordPlugin)) defaultIsFound = true;  MysqlClearPasswordPlugin mysqlClearPasswordPlugin = new MysqlClearPasswordPlugin(); mysqlClearPasswordPlugin.init(this.connection, this.connection.getProperties()); if (addAuthenticationPlugin((AuthenticationPlugin)mysqlClearPasswordPlugin)) defaultIsFound = true;  Sha256PasswordPlugin sha256PasswordPlugin = new Sha256PasswordPlugin(); sha256PasswordPlugin.init(this.connection, this.connection.getProperties()); if (addAuthenticationPlugin((AuthenticationPlugin)sha256PasswordPlugin)) defaultIsFound = true;  CachingSha2PasswordPlugin cachingSha2PasswordPlugin = new CachingSha2PasswordPlugin(); cachingSha2PasswordPlugin.init(this.connection, this.connection.getProperties()); if (addAuthenticationPlugin((AuthenticationPlugin)cachingSha2PasswordPlugin))
/*      */       defaultIsFound = true;  String authenticationPluginClasses = this.connection.getAuthenticationPlugins(); if (authenticationPluginClasses != null && !"".equals(authenticationPluginClasses)) { List<Extension> plugins = Util.loadExtensions(this.connection, this.connection.getProperties(), authenticationPluginClasses, "Connection.BadAuthenticationPlugin", getExceptionInterceptor()); for (Extension object : plugins) { AuthenticationPlugin authenticationPlugin = (AuthenticationPlugin)object; if (addAuthenticationPlugin(authenticationPlugin))
/*      */           defaultIsFound = true;  }  }  if (!defaultIsFound)
/*      */       throw SQLError.createSQLException(Messages.getString("Connection.DefaultAuthenticationPluginIsNotListed", new Object[] { this.clientDefaultAuthenticationPlugin }), getExceptionInterceptor());  } private boolean addAuthenticationPlugin(AuthenticationPlugin plugin) throws SQLException { boolean isDefault = false; String pluginClassName = plugin.getClass().getName(); String pluginProtocolName = plugin.getProtocolPluginName(); boolean disabledByClassName = (this.disabledAuthenticationPlugins != null && this.disabledAuthenticationPlugins.contains(pluginClassName)); boolean disabledByMechanism = (this.disabledAuthenticationPlugins != null && this.disabledAuthenticationPlugins.contains(pluginProtocolName)); if (disabledByClassName || disabledByMechanism) { if (this.clientDefaultAuthenticationPlugin.equals(pluginClassName))
/*      */         throw SQLError.createSQLException(Messages.getString("Connection.BadDisabledAuthenticationPlugin", new Object[] { disabledByClassName ? pluginClassName : pluginProtocolName }), getExceptionInterceptor());  } else { this.authenticationPlugins.put(pluginProtocolName, plugin); if (this.clientDefaultAuthenticationPlugin.equals(pluginClassName)) { this.clientDefaultAuthenticationPluginName = pluginProtocolName; isDefault = true; }  }  return isDefault; }
/*      */   private AuthenticationPlugin getAuthenticationPlugin(String pluginName) throws SQLException { AuthenticationPlugin plugin = this.authenticationPlugins.get(pluginName); if (plugin != null && !plugin.isReusable())
/*      */       try { plugin = (AuthenticationPlugin)plugin.getClass().newInstance(); plugin.init(this.connection, this.connection.getProperties()); } catch (Throwable t) { SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.BadAuthenticationPlugin", new Object[] { plugin.getClass().getName() }), getExceptionInterceptor()); sqlEx.initCause(t); throw sqlEx; }   return plugin; }
/*      */   private void checkConfidentiality(AuthenticationPlugin plugin) throws SQLException { if (plugin.requiresConfidentiality() && !isSSLEstablished())
/*      */       throw SQLError.createSQLException(Messages.getString("Connection.AuthenticationPluginRequiresSSL", new Object[] { plugin.getProtocolPluginName() }), getExceptionInterceptor());  }
/* 1381 */   public MysqlIO(String host, int port, Properties props, String socketFactoryClassName, MySQLConnection conn, int socketTimeout, int useBufferRowSizeThreshold) throws IOException, SQLException { this.authenticationPlugins = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1386 */     this.disabledAuthenticationPlugins = null;
/*      */ 
/*      */ 
/*      */     
/* 1390 */     this.clientDefaultAuthenticationPlugin = null;
/*      */ 
/*      */ 
/*      */     
/* 1394 */     this.clientDefaultAuthenticationPluginName = null;
/*      */ 
/*      */ 
/*      */     
/* 1398 */     this.serverDefaultAuthenticationPluginName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2549 */     this.statementExecutionDepth = 0; this.connection = conn; if (this.connection.getEnablePacketDebug()) this.packetDebugRingBuffer = new LinkedList<StringBuilder>();  this.traceProtocol = this.connection.getTraceProtocol(); this.useAutoSlowLog = this.connection.getAutoSlowLog(); this.useBufferRowSizeThreshold = useBufferRowSizeThreshold; this.useDirectRowUnpack = this.connection.getUseDirectRowUnpack(); this.logSlowQueries = this.connection.getLogSlowQueries(); this.reusablePacket = new Buffer(1024); this.sendPacket = new Buffer(1024); this.port = port; this.host = host; this.socketFactoryClassName = socketFactoryClassName; this.socketFactory = createSocketFactory(); this.exceptionInterceptor = this.connection.getExceptionInterceptor(); try { this.mysqlConnection = this.socketFactory.connect(this.host, this.port, props); if (socketTimeout != 0) try { this.mysqlConnection.setSoTimeout(socketTimeout); } catch (Exception ex) {}  this.mysqlConnection = this.socketFactory.beforeHandshake(); if (this.connection.getUseReadAheadInput()) { this.mysqlInput = (InputStream)new ReadAheadInputStream(this.mysqlConnection.getInputStream(), 16384, this.connection.getTraceProtocol(), this.connection.getLog()); } else if (this.connection.useUnbufferedInput()) { this.mysqlInput = this.mysqlConnection.getInputStream(); } else { this.mysqlInput = new BufferedInputStream(this.mysqlConnection.getInputStream(), 16384); }  this.mysqlOutput = new BufferedOutputStream(this.mysqlConnection.getOutputStream(), 16384); this.isInteractiveClient = this.connection.getInteractiveClient(); this.profileSql = this.connection.getProfileSql(); this.autoGenerateTestcaseScript = this.connection.getAutoGenerateTestcaseScript(); this.needToGrabQueryFromPacket = (this.profileSql || this.logSlowQueries || this.autoGenerateTestcaseScript); this.useNanosForElapsedTime = (this.connection.getUseNanosForElapsedTime() && TimeUtil.nanoTimeAvailable()); this.queryTimingUnits = this.useNanosForElapsedTime ? Messages.getString("Nanoseconds") : Messages.getString("Milliseconds"); if (this.connection.getLogSlowQueries()) calculateSlowQueryThreshold();  } catch (IOException ioEx) { throw SQLError.createCommunicationsException(this.connection, 0L, 0L, ioEx, getExceptionInterceptor()); }  } private void proceedHandshakeWithPluggableAuthentication(String user, String password, String database, Buffer challenge) throws SQLException { if (this.authenticationPlugins == null) loadAuthenticationPlugins();  boolean skipPassword = false; int passwordLength = 16; int userLength = (user != null) ? user.length() : 0; int databaseLength = (database != null) ? database.length() : 0; int packLength = (userLength + passwordLength + databaseLength) * 3 + 7 + 4 + 33; AuthenticationPlugin plugin = null; Buffer fromServer = null; ArrayList<Buffer> toServer = new ArrayList<Buffer>(); boolean done = false; Buffer last_sent = null; boolean old_raw_challenge = false; int counter = 100; while (0 < counter--) { if (!done) { if (challenge != null) { if (challenge.isOKPacket()) throw SQLError.createSQLException(Messages.getString("Connection.UnexpectedAuthenticationApproval", new Object[] { plugin.getProtocolPluginName() }), getExceptionInterceptor());  this.clientParam |= 0xAA201L; if (this.connection.getAllowMultiQueries()) this.clientParam |= 0x10000L;  if ((this.serverCapabilities & 0x400000) != 0 && !this.connection.getDisconnectOnExpiredPasswords()) this.clientParam |= 0x400000L;  if ((this.serverCapabilities & 0x100000) != 0 && !"none".equals(this.connection.getConnectionAttributes())) this.clientParam |= 0x100000L;  if ((this.serverCapabilities & 0x200000) != 0) this.clientParam |= 0x200000L;  this.has41NewNewProt = true; this.use41Extensions = true; if (this.connection.getUseSSL()) negotiateSSLConnection(user, password, database, packLength);  String pluginName = null; if ((this.serverCapabilities & 0x80000) != 0) if (!versionMeetsMinimum(5, 5, 10) || (versionMeetsMinimum(5, 6, 0) && !versionMeetsMinimum(5, 6, 2))) { pluginName = challenge.readString("ASCII", getExceptionInterceptor(), this.authPluginDataLength); } else { pluginName = challenge.readString("ASCII", getExceptionInterceptor()); }   plugin = getAuthenticationPlugin(pluginName); if (plugin == null) { plugin = getAuthenticationPlugin(this.clientDefaultAuthenticationPluginName); } else if (pluginName.equals(Sha256PasswordPlugin.PLUGIN_NAME) && !isSSLEstablished() && this.connection.getServerRSAPublicKeyFile() == null && !this.connection.getAllowPublicKeyRetrieval()) { plugin = getAuthenticationPlugin(this.clientDefaultAuthenticationPluginName); skipPassword = !this.clientDefaultAuthenticationPluginName.equals(pluginName); }  this.serverDefaultAuthenticationPluginName = plugin.getProtocolPluginName(); checkConfidentiality(plugin); fromServer = new Buffer(StringUtils.getBytes(this.seed)); } else { plugin = getAuthenticationPlugin((this.serverDefaultAuthenticationPluginName == null) ? this.clientDefaultAuthenticationPluginName : this.serverDefaultAuthenticationPluginName); checkConfidentiality(plugin); fromServer = new Buffer(StringUtils.getBytes(this.seed)); }  } else { challenge = checkErrorPacket(); old_raw_challenge = false; this.packetSequence = (byte)(this.packetSequence + 1); this.compressedPacketSequence = (byte)(this.compressedPacketSequence + 1); if (plugin == null) plugin = getAuthenticationPlugin((this.serverDefaultAuthenticationPluginName != null) ? this.serverDefaultAuthenticationPluginName : this.clientDefaultAuthenticationPluginName);  if (challenge.isOKPacket()) { challenge.newReadLength(); challenge.newReadLength(); this.oldServerStatus = this.serverStatus; this.serverStatus = challenge.readInt(); plugin.destroy(); break; }  if (challenge.isAuthMethodSwitchRequestPacket()) { skipPassword = false; String pluginName = challenge.readString("ASCII", getExceptionInterceptor()); if (!plugin.getProtocolPluginName().equals(pluginName)) { plugin.destroy(); plugin = getAuthenticationPlugin(pluginName); if (plugin == null) throw SQLError.createSQLException(Messages.getString("Connection.BadAuthenticationPlugin", new Object[] { pluginName }), getExceptionInterceptor());  } else { plugin.reset(); }  checkConfidentiality(plugin); fromServer = new Buffer(StringUtils.getBytes(challenge.readString("ASCII", getExceptionInterceptor()))); } else if (versionMeetsMinimum(5, 5, 16)) { fromServer = new Buffer(challenge.getBytes(challenge.getPosition(), challenge.getBufLength() - challenge.getPosition())); } else { old_raw_challenge = true; fromServer = new Buffer(challenge.getBytes(challenge.getPosition() - 1, challenge.getBufLength() - challenge.getPosition() + 1)); }  }  try { plugin.setAuthenticationParameters(user, skipPassword ? null : password); done = plugin.nextAuthenticationStep(fromServer, toServer); } catch (SQLException e) { throw SQLError.createSQLException(e.getMessage(), e.getSQLState(), e, getExceptionInterceptor()); }  if (toServer.size() > 0) { if (challenge == null) { String str = getEncodingForHandshake(); last_sent = new Buffer(packLength + 1); last_sent.writeByte((byte)17); last_sent.writeString(user, str, this.connection); if (((Buffer)toServer.get(0)).getBufLength() < 256) { last_sent.writeByte((byte)((Buffer)toServer.get(0)).getBufLength()); last_sent.writeBytesNoNull(((Buffer)toServer.get(0)).getByteBuffer(), 0, ((Buffer)toServer.get(0)).getBufLength()); } else { last_sent.writeByte((byte)0); }  if (this.useConnectWithDb) { last_sent.writeString(database, str, this.connection); } else { last_sent.writeByte((byte)0); }  appendCharsetByteForHandshake(last_sent, str); last_sent.writeByte((byte)0); if ((this.serverCapabilities & 0x80000) != 0) last_sent.writeString(plugin.getProtocolPluginName(), str, this.connection);  if ((this.clientParam & 0x100000L) != 0L) { sendConnectionAttributes(last_sent, str, this.connection); last_sent.writeByte((byte)0); }  send(last_sent, last_sent.getPosition()); continue; }  if (challenge.isAuthMethodSwitchRequestPacket()) { last_sent = new Buffer(((Buffer)toServer.get(0)).getBufLength() + 4); last_sent.writeBytesNoNull(((Buffer)toServer.get(0)).getByteBuffer(), 0, ((Buffer)toServer.get(0)).getBufLength()); send(last_sent, last_sent.getPosition()); continue; }  if (challenge.isRawPacket() || old_raw_challenge) { for (Buffer buffer : toServer) { last_sent = new Buffer(buffer.getBufLength() + 4); last_sent.writeBytesNoNull(buffer.getByteBuffer(), 0, ((Buffer)toServer.get(0)).getBufLength()); send(last_sent, last_sent.getPosition()); }  continue; }  String enc = getEncodingForHandshake(); last_sent = new Buffer(packLength); last_sent.writeLong(this.clientParam); last_sent.writeLong(this.maxThreeBytes); appendCharsetByteForHandshake(last_sent, enc); last_sent.writeBytesNoNull(new byte[23]); last_sent.writeString(user, enc, this.connection); if ((this.serverCapabilities & 0x200000) != 0) { last_sent.writeLenBytes(((Buffer)toServer.get(0)).getBytes(((Buffer)toServer.get(0)).getBufLength())); } else { last_sent.writeByte((byte)((Buffer)toServer.get(0)).getBufLength()); last_sent.writeBytesNoNull(((Buffer)toServer.get(0)).getByteBuffer(), 0, ((Buffer)toServer.get(0)).getBufLength()); }  if (this.useConnectWithDb) last_sent.writeString(database, enc, this.connection);  if ((this.serverCapabilities & 0x80000) != 0) last_sent.writeString(plugin.getProtocolPluginName(), enc, this.connection);  if ((this.clientParam & 0x100000L) != 0L) sendConnectionAttributes(last_sent, enc, this.connection);  send(last_sent, last_sent.getPosition()); }  }  if (counter == 0) throw SQLError.createSQLException(Messages.getString("CommunicationsException.TooManyAuthenticationPluginNegotiations"), getExceptionInterceptor());  if ((this.serverCapabilities & 0x20) != 0 && this.connection.getUseCompression() && !(this.mysqlInput instanceof CompressedInputStream)) { this.deflater = new Deflater(); this.useCompression = true; this.mysqlInput = new CompressedInputStream(this.connection, this.mysqlInput); }  if (!this.useConnectWithDb) changeDatabaseTo(database);  try { this.mysqlConnection = this.socketFactory.afterHandshake(); } catch (IOException ioEx) { throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ioEx, getExceptionInterceptor()); }  }
/*      */   private Properties getConnectionAttributesAsProperties(String atts) throws SQLException { Properties props = new Properties(); if (atts != null) { String[] pairs = atts.split(","); for (String pair : pairs) { int keyEnd = pair.indexOf(":"); if (keyEnd > 0 && keyEnd + 1 < pair.length()) props.setProperty(pair.substring(0, keyEnd), pair.substring(keyEnd + 1));  }  }  props.setProperty("_client_name", "MySQL Connector Java"); props.setProperty("_client_version", "5.1.49"); props.setProperty("_runtime_vendor", NonRegisteringDriver.RUNTIME_VENDOR); props.setProperty("_runtime_version", NonRegisteringDriver.RUNTIME_VERSION); props.setProperty("_client_license", "GPL"); return props; }
/*      */   private void sendConnectionAttributes(Buffer buf, String enc, MySQLConnection conn) throws SQLException { String atts = conn.getConnectionAttributes(); Buffer lb = new Buffer(100); try { Properties props = getConnectionAttributesAsProperties(atts); for (Object key : props.keySet()) { lb.writeLenString((String)key, enc, conn.getServerCharset(), null, conn.parserKnowsUnicode(), conn); lb.writeLenString(props.getProperty((String)key), enc, conn.getServerCharset(), null, conn.parserKnowsUnicode(), conn); }  } catch (UnsupportedEncodingException e) {} buf.writeByte((byte)(lb.getPosition() - 4)); buf.writeBytesNoNull(lb.getByteBuffer(), 4, lb.getBufLength() - 4); }
/*      */   private void changeDatabaseTo(String database) throws SQLException { if (database == null || database.length() == 0) return;  try { sendCommand(2, database, null, false, null, 0); } catch (Exception ex) { if (this.connection.getCreateDatabaseIfNotExist()) { sendCommand(3, "CREATE DATABASE IF NOT EXISTS " + database, null, false, null, 0); sendCommand(2, database, null, false, null, 0); } else { throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ex, getExceptionInterceptor()); }  }  }
/* 2553 */   protected boolean shouldIntercept() { return (this.statementInterceptors != null); }
/*      */   final ResultSetRow nextRow(Field[] fields, int columnCount, boolean isBinaryEncoded, int resultSetConcurrency, boolean useBufferRowIfPossible, boolean useBufferRowExplicit, boolean canReuseRowPacketForBufferRow, Buffer existingRowPacket) throws SQLException { if (this.useDirectRowUnpack && existingRowPacket == null && !isBinaryEncoded && !useBufferRowIfPossible && !useBufferRowExplicit)
/*      */       return nextRowFast(fields, columnCount, isBinaryEncoded, resultSetConcurrency, useBufferRowIfPossible, useBufferRowExplicit, canReuseRowPacketForBufferRow);  Buffer rowPacket = null; if (existingRowPacket == null) { rowPacket = checkErrorPacket(); if (!useBufferRowExplicit && useBufferRowIfPossible && rowPacket.getBufLength() > this.useBufferRowSizeThreshold)
/*      */         useBufferRowExplicit = true;  } else { rowPacket = existingRowPacket; checkErrorPacket(existingRowPacket); }  if (!isBinaryEncoded) { rowPacket.setPosition(rowPacket.getPosition() - 1); if ((isEOFDeprecated() || !rowPacket.isEOFPacket()) && (!isEOFDeprecated() || !rowPacket.isResultSetOKPacket())) { if (resultSetConcurrency == 1008 || (!useBufferRowIfPossible && !useBufferRowExplicit)) { byte[][] rowData = new byte[columnCount][]; for (int i = 0; i < columnCount; i++)
/*      */             rowData[i] = rowPacket.readLenByteArray(0);  return new ByteArrayRow(rowData, getExceptionInterceptor()); }  if (!canReuseRowPacketForBufferRow)
/*      */           this.reusablePacket = new Buffer(rowPacket.getBufLength());  return new BufferRow(rowPacket, fields, false, getExceptionInterceptor()); }  readServerStatusForResultSets(rowPacket); return null; }  if ((isEOFDeprecated() || !rowPacket.isEOFPacket()) && (!isEOFDeprecated() || !rowPacket.isResultSetOKPacket())) { if (resultSetConcurrency == 1008 || (!useBufferRowIfPossible && !useBufferRowExplicit))
/*      */         return unpackBinaryResultSetRow(fields, rowPacket, resultSetConcurrency);  if (!canReuseRowPacketForBufferRow)
/*      */         this.reusablePacket = new Buffer(rowPacket.getBufLength());  return new BufferRow(rowPacket, fields, true, getExceptionInterceptor()); }  rowPacket.setPosition(rowPacket.getPosition() - 1); readServerStatusForResultSets(rowPacket); return null; }
/*      */   final ResultSetRow nextRowFast(Field[] fields, int columnCount, boolean isBinaryEncoded, int resultSetConcurrency, boolean useBufferRowIfPossible, boolean useBufferRowExplicit, boolean canReuseRowPacket) throws SQLException { try { int lengthRead = readFully(this.mysqlInput, this.packetHeaderBuf, 0, 4); if (lengthRead < 4) { forceClose(); throw new RuntimeException(Messages.getString("MysqlIO.43")); }  int packetLength = (this.packetHeaderBuf[0] & 0xFF) + ((this.packetHeaderBuf[1] & 0xFF) << 8) + ((this.packetHeaderBuf[2] & 0xFF) << 16); if (packetLength == this.maxThreeBytes) { reuseAndReadPacket(this.reusablePacket, packetLength); return nextRow(fields, columnCount, isBinaryEncoded, resultSetConcurrency, useBufferRowIfPossible, useBufferRowExplicit, canReuseRowPacket, this.reusablePacket); }  if (packetLength > this.useBufferRowSizeThreshold) { reuseAndReadPacket(this.reusablePacket, packetLength); return nextRow(fields, columnCount, isBinaryEncoded, resultSetConcurrency, true, true, false, this.reusablePacket); }  int remaining = packetLength; boolean firstTime = true; byte[][] rowData = (byte[][])null; for (int i = 0; i < columnCount; i++) { int sw = this.mysqlInput.read() & 0xFF; remaining--; if (firstTime) { if (sw == 255) { Buffer errorPacket = new Buffer(packetLength + 4); errorPacket.setPosition(0); errorPacket.writeByte(this.packetHeaderBuf[0]); errorPacket.writeByte(this.packetHeaderBuf[1]); errorPacket.writeByte(this.packetHeaderBuf[2]); errorPacket.writeByte((byte)1); errorPacket.writeByte((byte)sw); readFully(this.mysqlInput, errorPacket.getByteBuffer(), 5, packetLength - 1); errorPacket.setPosition(4); checkErrorPacket(errorPacket); }  if (sw == 254 && packetLength < 16777215) { if (this.use41Extensions) { if (isEOFDeprecated()) { remaining -= skipLengthEncodedInteger(this.mysqlInput); remaining -= skipLengthEncodedInteger(this.mysqlInput); this.oldServerStatus = this.serverStatus; this.serverStatus = this.mysqlInput.read() & 0xFF | (this.mysqlInput.read() & 0xFF) << 8; checkTransactionState(this.oldServerStatus); remaining -= 2; this.warningCount = this.mysqlInput.read() & 0xFF | (this.mysqlInput.read() & 0xFF) << 8; remaining -= 2; if (this.warningCount > 0)
/*      */                   this.hadWarnings = true;  } else { this.warningCount = this.mysqlInput.read() & 0xFF | (this.mysqlInput.read() & 0xFF) << 8; remaining -= 2; if (this.warningCount > 0)
/*      */                   this.hadWarnings = true;  this.oldServerStatus = this.serverStatus; this.serverStatus = this.mysqlInput.read() & 0xFF | (this.mysqlInput.read() & 0xFF) << 8; checkTransactionState(this.oldServerStatus); remaining -= 2; }  setServerSlowQueryFlags(); if (remaining > 0)
/*      */                 skipFully(this.mysqlInput, remaining);  }  return null; }  rowData = new byte[columnCount][]; firstTime = false; }  int len = 0; switch (sw) { case 251: len = -1; break;
/*      */           case 252: len = this.mysqlInput.read() & 0xFF | (this.mysqlInput.read() & 0xFF) << 8; remaining -= 2; break;
/*      */           case 253: len = this.mysqlInput.read() & 0xFF | (this.mysqlInput.read() & 0xFF) << 8 | (this.mysqlInput.read() & 0xFF) << 16; remaining -= 3; break;
/*      */           case 254:
/*      */             len = (int)((this.mysqlInput.read() & 0xFF) | (this.mysqlInput.read() & 0xFF) << 8L | (this.mysqlInput.read() & 0xFF) << 16L | (this.mysqlInput.read() & 0xFF) << 24L | (this.mysqlInput.read() & 0xFF) << 32L | (this.mysqlInput.read() & 0xFF) << 40L | (this.mysqlInput.read() & 0xFF) << 48L | (this.mysqlInput.read() & 0xFF) << 56L); remaining -= 8; break;
/*      */           default:
/*      */             len = sw; break; }  if (len == -1) { rowData[i] = null; } else if (len == 0) { rowData[i] = Constants.EMPTY_BYTE_ARRAY; } else { rowData[i] = new byte[len]; int bytesRead = readFully(this.mysqlInput, rowData[i], 0, len); if (bytesRead != len)
/*      */             throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, new IOException(Messages.getString("MysqlIO.43")), getExceptionInterceptor());  remaining -= bytesRead; }  }  if (remaining > 0)
/*      */         skipFully(this.mysqlInput, remaining);  return new ByteArrayRow(rowData, getExceptionInterceptor()); } catch (IOException ioEx) { throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ioEx, getExceptionInterceptor()); }  }
/*      */   final void quit() throws SQLException { try { if (!ExportControlled.isSSLEstablished(this.mysqlConnection) && !this.mysqlConnection.isClosed())
/*      */         try { this.mysqlConnection.shutdownInput(); } catch (UnsupportedOperationException e) {}  } catch (IOException e) {  } finally { forceClose(); }  }
/*      */   Buffer getSharedSendPacket() { if (this.sharedSendPacket == null)
/* 2576 */       this.sharedSendPacket = new Buffer(1024);  return this.sharedSendPacket; } final ResultSetInternalMethods sqlQueryDirect(StatementImpl callingStatement, String query, String characterEncoding, Buffer queryPacket, int maxRows, int resultSetType, int resultSetConcurrency, boolean streamResults, String catalog, Field[] cachedMetadata) throws Exception { this.statementExecutionDepth++;
/*      */ 
/*      */     
/* 2579 */     try { if (this.statementInterceptors != null) {
/* 2580 */         ResultSetInternalMethods interceptedResults = invokeStatementInterceptorsPre(query, callingStatement, false);
/*      */         
/* 2582 */         if (interceptedResults != null) {
/* 2583 */           return interceptedResults;
/*      */         }
/*      */       } 
/*      */       
/* 2587 */       String statementComment = this.connection.getStatementComment();
/*      */       
/* 2589 */       if (this.connection.getIncludeThreadNamesAsStatementComment()) {
/* 2590 */         statementComment = ((statementComment != null) ? (statementComment + ", ") : "") + "java thread: " + Thread.currentThread().getName();
/*      */       }
/*      */       
/* 2593 */       if (query != null) {
/*      */ 
/*      */         
/* 2596 */         int packLength = 5 + query.length() * 3 + 2;
/*      */         
/* 2598 */         byte[] commentAsBytes = null;
/*      */         
/* 2600 */         if (statementComment != null) {
/* 2601 */           commentAsBytes = StringUtils.getBytes(statementComment, (SingleByteCharsetConverter)null, characterEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), getExceptionInterceptor());
/*      */ 
/*      */           
/* 2604 */           packLength += commentAsBytes.length;
/* 2605 */           packLength += 6;
/*      */         } 
/*      */         
/* 2608 */         if (this.sendPacket == null) {
/* 2609 */           this.sendPacket = new Buffer(packLength);
/*      */         } else {
/* 2611 */           this.sendPacket.clear();
/*      */         } 
/*      */         
/* 2614 */         this.sendPacket.writeByte((byte)3);
/*      */         
/* 2616 */         if (commentAsBytes != null) {
/* 2617 */           this.sendPacket.writeBytesNoNull(Constants.SLASH_STAR_SPACE_AS_BYTES);
/* 2618 */           this.sendPacket.writeBytesNoNull(commentAsBytes);
/* 2619 */           this.sendPacket.writeBytesNoNull(Constants.SPACE_STAR_SLASH_SPACE_AS_BYTES);
/*      */         } 
/*      */         
/* 2622 */         if (characterEncoding != null) {
/* 2623 */           if (this.platformDbCharsetMatches) {
/* 2624 */             this.sendPacket.writeStringNoNull(query, characterEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), this.connection);
/*      */           
/*      */           }
/* 2627 */           else if (StringUtils.startsWithIgnoreCaseAndWs(query, "LOAD DATA")) {
/* 2628 */             this.sendPacket.writeBytesNoNull(StringUtils.getBytes(query));
/*      */           } else {
/* 2630 */             this.sendPacket.writeStringNoNull(query, characterEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), this.connection);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 2635 */           this.sendPacket.writeStringNoNull(query);
/*      */         } 
/*      */         
/* 2638 */         queryPacket = this.sendPacket;
/*      */       } 
/*      */       
/* 2641 */       byte[] queryBuf = null;
/* 2642 */       int oldPacketPosition = 0;
/* 2643 */       long queryStartTime = 0L;
/*      */       
/* 2645 */       if (this.needToGrabQueryFromPacket) {
/* 2646 */         queryBuf = queryPacket.getByteBuffer();
/*      */ 
/*      */         
/* 2649 */         oldPacketPosition = queryPacket.getPosition();
/*      */         
/* 2651 */         queryStartTime = getCurrentTimeNanosOrMillis();
/*      */       } 
/*      */       
/* 2654 */       if (this.autoGenerateTestcaseScript) {
/* 2655 */         String testcaseQuery = null;
/*      */         
/* 2657 */         if (query != null) {
/* 2658 */           if (statementComment != null) {
/* 2659 */             testcaseQuery = "/* " + statementComment + " */ " + query;
/*      */           } else {
/* 2661 */             testcaseQuery = query;
/*      */           } 
/*      */         } else {
/* 2664 */           testcaseQuery = StringUtils.toString(queryBuf, 5, oldPacketPosition - 5);
/*      */         } 
/*      */         
/* 2667 */         StringBuilder debugBuf = new StringBuilder(testcaseQuery.length() + 32);
/* 2668 */         this.connection.generateConnectionCommentBlock(debugBuf);
/* 2669 */         debugBuf.append(testcaseQuery);
/* 2670 */         debugBuf.append(';');
/* 2671 */         this.connection.dumpTestcaseQuery(debugBuf.toString());
/*      */       } 
/*      */ 
/*      */       
/* 2675 */       Buffer resultPacket = sendCommand(3, null, queryPacket, false, null, 0);
/*      */       
/* 2677 */       long fetchBeginTime = 0L;
/*      */       
/* 2679 */       String profileQueryToLog = null;
/*      */       
/* 2681 */       boolean queryWasSlow = false;
/*      */       
/* 2683 */       long queryEndTime = 0L;
/*      */       
/* 2685 */       if (this.profileSql || this.logSlowQueries) {
/* 2686 */         queryEndTime = getCurrentTimeNanosOrMillis();
/*      */         
/* 2688 */         boolean shouldExtractQuery = false;
/*      */         
/* 2690 */         if (this.profileSql) {
/* 2691 */           shouldExtractQuery = true;
/* 2692 */         } else if (this.logSlowQueries) {
/* 2693 */           long queryTime = queryEndTime - queryStartTime;
/*      */           
/* 2695 */           boolean logSlow = this.useAutoSlowLog ? this.connection.isAbonormallyLongQuery(queryTime) : ((queryTime > this.connection.getSlowQueryThresholdMillis()));
/*      */ 
/*      */           
/* 2698 */           if (logSlow) {
/* 2699 */             shouldExtractQuery = true;
/* 2700 */             queryWasSlow = true;
/*      */           } 
/*      */         } 
/*      */         
/* 2704 */         if (shouldExtractQuery) {
/*      */           
/* 2706 */           boolean truncated = false;
/*      */           
/* 2708 */           int extractPosition = oldPacketPosition;
/*      */           
/* 2710 */           if (oldPacketPosition > this.connection.getMaxQuerySizeToLog()) {
/* 2711 */             extractPosition = this.connection.getMaxQuerySizeToLog() + 5;
/* 2712 */             truncated = true;
/*      */           } 
/*      */           
/* 2715 */           profileQueryToLog = StringUtils.toString(queryBuf, 5, extractPosition - 5);
/*      */           
/* 2717 */           if (truncated) {
/* 2718 */             profileQueryToLog = profileQueryToLog + Messages.getString("MysqlIO.25");
/*      */           }
/*      */         } 
/*      */         
/* 2722 */         fetchBeginTime = queryEndTime;
/*      */       } 
/*      */       
/* 2725 */       ResultSetInternalMethods rs = readAllResults(callingStatement, maxRows, resultSetType, resultSetConcurrency, streamResults, catalog, resultPacket, false, -1L, cachedMetadata);
/*      */ 
/*      */       
/* 2728 */       if (queryWasSlow && !this.serverQueryWasSlow) {
/* 2729 */         this.connection.getProfilerEventHandlerInstance().processEvent((byte)6, this.connection, callingStatement, rs, (int)(queryEndTime - queryStartTime), new Throwable(), Messages.getString("Protocol.SlowQuery", new Object[] { this.useAutoSlowLog ? " 95% of all queries " : String.valueOf(this.slowQueryThreshold), this.queryTimingUnits, Long.valueOf(queryEndTime - queryStartTime), profileQueryToLog }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2735 */         if (this.connection.getExplainSlowQueries()) {
/* 2736 */           if (oldPacketPosition < 1048576) {
/* 2737 */             explainSlowQuery(queryPacket.getBytes(5, oldPacketPosition - 5), profileQueryToLog);
/*      */           } else {
/* 2739 */             this.connection.getLog().logWarn(Messages.getString("MysqlIO.28") + 1048576 + Messages.getString("MysqlIO.29"));
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/* 2744 */       if (this.logSlowQueries) {
/* 2745 */         if (this.queryBadIndexUsed && this.profileSql) {
/* 2746 */           this.connection.getProfilerEventHandlerInstance().processEvent((byte)6, this.connection, callingStatement, rs, queryEndTime - queryStartTime, new Throwable(), Messages.getString("MysqlIO.33") + profileQueryToLog);
/*      */         }
/*      */ 
/*      */         
/* 2750 */         if (this.queryNoIndexUsed && this.profileSql) {
/* 2751 */           this.connection.getProfilerEventHandlerInstance().processEvent((byte)6, this.connection, callingStatement, rs, queryEndTime - queryStartTime, new Throwable(), Messages.getString("MysqlIO.35") + profileQueryToLog);
/*      */         }
/*      */ 
/*      */         
/* 2755 */         if (this.serverQueryWasSlow && this.profileSql) {
/* 2756 */           this.connection.getProfilerEventHandlerInstance().processEvent((byte)6, this.connection, callingStatement, rs, queryEndTime - queryStartTime, new Throwable(), Messages.getString("MysqlIO.ServerSlowQuery") + profileQueryToLog);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 2761 */       if (this.profileSql) {
/* 2762 */         this.connection.getProfilerEventHandlerInstance().processEvent((byte)3, this.connection, callingStatement, rs, queryEndTime - queryStartTime, new Throwable(), profileQueryToLog);
/*      */ 
/*      */         
/* 2765 */         this.connection.getProfilerEventHandlerInstance().processEvent((byte)5, this.connection, callingStatement, rs, getCurrentTimeNanosOrMillis() - fetchBeginTime, new Throwable(), null);
/*      */       } 
/*      */ 
/*      */       
/* 2769 */       if (this.hadWarnings) {
/* 2770 */         scanForAndThrowDataTruncation();
/*      */       }
/*      */       
/* 2773 */       if (this.statementInterceptors != null) {
/* 2774 */         rs = invokeStatementInterceptorsPost(query, callingStatement, rs, false, null);
/*      */       }
/*      */       
/* 2777 */       return rs; }
/* 2778 */     catch (SQLException sqlEx)
/* 2779 */     { if (this.statementInterceptors != null) {
/* 2780 */         invokeStatementInterceptorsPost(query, callingStatement, null, false, sqlEx);
/*      */       }
/*      */       
/* 2783 */       if (callingStatement != null) {
/* 2784 */         synchronized (callingStatement.cancelTimeoutMutex) {
/* 2785 */           if (callingStatement.wasCancelled) {
/* 2786 */             MySQLStatementCancelledException mySQLStatementCancelledException; SQLException cause = null;
/*      */             
/* 2788 */             if (callingStatement.wasCancelledByTimeout) {
/* 2789 */               MySQLTimeoutException mySQLTimeoutException = new MySQLTimeoutException();
/*      */             } else {
/* 2791 */               mySQLStatementCancelledException = new MySQLStatementCancelledException();
/*      */             } 
/*      */             
/* 2794 */             callingStatement.resetCancelledState();
/*      */             
/* 2796 */             throw mySQLStatementCancelledException;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/* 2801 */       throw sqlEx; }
/*      */     finally
/* 2803 */     { this.statementExecutionDepth--; }  }
/*      */   void closeStreamer(RowData streamer) throws SQLException { if (this.streamingData == null) throw SQLError.createSQLException(Messages.getString("MysqlIO.17") + streamer + Messages.getString("MysqlIO.18"), getExceptionInterceptor());  if (streamer != this.streamingData) throw SQLError.createSQLException(Messages.getString("MysqlIO.19") + streamer + Messages.getString("MysqlIO.20") + Messages.getString("MysqlIO.21") + Messages.getString("MysqlIO.22"), getExceptionInterceptor());  this.streamingData = null; }
/*      */   boolean tackOnMoreStreamingResults(ResultSetImpl addingTo) throws SQLException { if ((this.serverStatus & 0x8) != 0) { boolean moreRowSetsExist = true; ResultSetImpl currentResultSet = addingTo; boolean firstTime = true; while (moreRowSetsExist && (firstTime || !currentResultSet.reallyResult())) { firstTime = false; Buffer fieldPacket = checkErrorPacket(); fieldPacket.setPosition(0); Statement owningStatement = addingTo.getStatement(); int maxRows = owningStatement.getMaxRows(); ResultSetImpl newResultSet = readResultsForQueryOrUpdate((StatementImpl)owningStatement, maxRows, owningStatement.getResultSetType(), owningStatement.getResultSetConcurrency(), true, owningStatement.getConnection().getCatalog(), fieldPacket, addingTo.isBinaryEncoded, -1L, null); currentResultSet.setNextResultSet(newResultSet); currentResultSet = newResultSet; moreRowSetsExist = ((this.serverStatus & 0x8) != 0); if (!currentResultSet.reallyResult() && !moreRowSetsExist) return false;  }  return true; }  return false; }
/*      */   ResultSetImpl readAllResults(StatementImpl callingStatement, int maxRows, int resultSetType, int resultSetConcurrency, boolean streamResults, String catalog, Buffer resultPacket, boolean isBinaryEncoded, long preSentColumnCount, Field[] metadataFromCache) throws SQLException { resultPacket.setPosition(resultPacket.getPosition() - 1); ResultSetImpl topLevelResultSet = readResultsForQueryOrUpdate(callingStatement, maxRows, resultSetType, resultSetConcurrency, streamResults, catalog, resultPacket, isBinaryEncoded, preSentColumnCount, metadataFromCache); ResultSetImpl currentResultSet = topLevelResultSet; boolean checkForMoreResults = ((this.clientParam & 0x20000L) != 0L); boolean serverHasMoreResults = ((this.serverStatus & 0x8) != 0); if (serverHasMoreResults && streamResults) { if (topLevelResultSet.getUpdateCount() != -1L) tackOnMoreStreamingResults(topLevelResultSet);  reclaimLargeReusablePacket(); return topLevelResultSet; }  boolean moreRowSetsExist = checkForMoreResults & serverHasMoreResults; while (moreRowSetsExist) { Buffer fieldPacket = checkErrorPacket(); fieldPacket.setPosition(0); ResultSetImpl newResultSet = readResultsForQueryOrUpdate(callingStatement, maxRows, resultSetType, resultSetConcurrency, streamResults, catalog, fieldPacket, isBinaryEncoded, preSentColumnCount, metadataFromCache); currentResultSet.setNextResultSet(newResultSet); currentResultSet = newResultSet; moreRowSetsExist = ((this.serverStatus & 0x8) != 0); }  if (!streamResults) clearInputStream();  reclaimLargeReusablePacket(); return topLevelResultSet; }
/*      */   void resetMaxBuf() { this.maxAllowedPacket = this.connection.getMaxAllowedPacket(); }
/* 2808 */   final Buffer sendCommand(int command, String extraData, Buffer queryPacket, boolean skipCheck, String extraDataCharEncoding, int timeoutMillis) throws SQLException { this.commandCount++; this.enablePacketDebug = this.connection.getEnablePacketDebug(); this.readPacketSequence = 0; int oldTimeout = 0; if (timeoutMillis != 0) try { oldTimeout = this.mysqlConnection.getSoTimeout(); this.mysqlConnection.setSoTimeout(timeoutMillis); } catch (SocketException e) { throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, e, getExceptionInterceptor()); }   try { checkForOutstandingStreamingData(); this.oldServerStatus = this.serverStatus; this.serverStatus = 0; this.hadWarnings = false; this.warningCount = 0; this.queryNoIndexUsed = false; this.queryBadIndexUsed = false; this.serverQueryWasSlow = false; if (this.useCompression) { int bytesLeft = this.mysqlInput.available(); if (bytesLeft > 0) this.mysqlInput.skip(bytesLeft);  }  try { clearInputStream(); if (queryPacket == null) { int packLength = 8 + ((extraData != null) ? extraData.length() : 0) + 2; if (this.sendPacket == null) this.sendPacket = new Buffer(packLength);  this.packetSequence = -1; this.compressedPacketSequence = -1; this.readPacketSequence = 0; this.checkPacketSequence = true; this.sendPacket.clear(); this.sendPacket.writeByte((byte)command); if (command == 2 || command == 3 || command == 22) if (extraDataCharEncoding == null) { this.sendPacket.writeStringNoNull(extraData); } else { this.sendPacket.writeStringNoNull(extraData, extraDataCharEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), this.connection); }   send(this.sendPacket, this.sendPacket.getPosition()); } else { this.packetSequence = -1; this.compressedPacketSequence = -1; send(queryPacket, queryPacket.getPosition()); }  } catch (SQLException sqlEx) { throw sqlEx; } catch (Exception ex) { throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ex, getExceptionInterceptor()); }  Buffer returnPacket = null; if (!skipCheck) { if (command == 23 || command == 26) { this.readPacketSequence = 0; this.packetSequenceReset = true; }  returnPacket = checkErrorPacket(command); }  return returnPacket; } catch (IOException ioEx) { preserveOldTransactionState(); throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ioEx, getExceptionInterceptor()); } catch (SQLException e) { preserveOldTransactionState(); throw e; } finally { if (timeoutMillis != 0) try { this.mysqlConnection.setSoTimeout(oldTimeout); } catch (SocketException e) { throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, e, getExceptionInterceptor()); }   }  } ResultSetInternalMethods invokeStatementInterceptorsPre(String sql, Statement interceptedStatement, boolean forceExecute) throws SQLException { ResultSetInternalMethods previousResultSet = null;
/*      */     
/* 2810 */     for (int i = 0, s = this.statementInterceptors.size(); i < s; i++) {
/* 2811 */       StatementInterceptorV2 interceptor = this.statementInterceptors.get(i);
/*      */       
/* 2813 */       boolean executeTopLevelOnly = interceptor.executeTopLevelOnly();
/* 2814 */       boolean shouldExecute = ((executeTopLevelOnly && (this.statementExecutionDepth == 1 || forceExecute)) || !executeTopLevelOnly);
/*      */       
/* 2816 */       if (shouldExecute) {
/* 2817 */         String sqlToInterceptor = sql;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2824 */         ResultSetInternalMethods interceptedResultSet = interceptor.preProcess(sqlToInterceptor, interceptedStatement, this.connection);
/*      */         
/* 2826 */         if (interceptedResultSet != null) {
/* 2827 */           previousResultSet = interceptedResultSet;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2832 */     return previousResultSet; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ResultSetInternalMethods invokeStatementInterceptorsPost(String sql, Statement interceptedStatement, ResultSetInternalMethods originalResultSet, boolean forceExecute, SQLException statementException) throws SQLException {
/* 2838 */     for (int i = 0, s = this.statementInterceptors.size(); i < s; i++) {
/* 2839 */       StatementInterceptorV2 interceptor = this.statementInterceptors.get(i);
/*      */       
/* 2841 */       boolean executeTopLevelOnly = interceptor.executeTopLevelOnly();
/* 2842 */       boolean shouldExecute = ((executeTopLevelOnly && (this.statementExecutionDepth == 1 || forceExecute)) || !executeTopLevelOnly);
/*      */       
/* 2844 */       if (shouldExecute) {
/* 2845 */         String sqlToInterceptor = sql;
/*      */         
/* 2847 */         ResultSetInternalMethods interceptedResultSet = interceptor.postProcess(sqlToInterceptor, interceptedStatement, originalResultSet, this.connection, this.warningCount, this.queryNoIndexUsed, this.queryBadIndexUsed, statementException);
/*      */ 
/*      */         
/* 2850 */         if (interceptedResultSet != null) {
/* 2851 */           originalResultSet = interceptedResultSet;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2856 */     return originalResultSet;
/*      */   }
/*      */   
/*      */   private void calculateSlowQueryThreshold() {
/* 2860 */     this.slowQueryThreshold = this.connection.getSlowQueryThresholdMillis();
/*      */     
/* 2862 */     if (this.connection.getUseNanosForElapsedTime()) {
/* 2863 */       long nanosThreshold = this.connection.getSlowQueryThresholdNanos();
/*      */       
/* 2865 */       if (nanosThreshold != 0L) {
/* 2866 */         this.slowQueryThreshold = nanosThreshold;
/*      */       } else {
/* 2868 */         this.slowQueryThreshold *= 1000000L;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected long getCurrentTimeNanosOrMillis() {
/* 2874 */     return this.useNanosForElapsedTime ? TimeUtil.getCurrentTimeNanosOrMillis() : System.currentTimeMillis();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getHost() {
/* 2881 */     return this.host;
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
/*      */   boolean isVersion(int major, int minor, int subminor) {
/* 2899 */     return (major == getServerMajorVersion() && minor == getServerMinorVersion() && subminor == getServerSubMinorVersion());
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
/*      */   boolean versionMeetsMinimum(int major, int minor, int subminor) {
/* 2911 */     if (getServerMajorVersion() >= major) {
/* 2912 */       if (getServerMajorVersion() == major) {
/* 2913 */         if (getServerMinorVersion() >= minor) {
/* 2914 */           if (getServerMinorVersion() == minor) {
/* 2915 */             return (getServerSubMinorVersion() >= subminor);
/*      */           }
/*      */ 
/*      */           
/* 2919 */           return true;
/*      */         } 
/*      */ 
/*      */         
/* 2923 */         return false;
/*      */       } 
/*      */ 
/*      */       
/* 2927 */       return true;
/*      */     } 
/*      */     
/* 2930 */     return false;
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
/*      */   private static final String getPacketDumpToLog(Buffer packetToDump, int packetLength) {
/* 2945 */     if (packetLength < 1024) {
/* 2946 */       return packetToDump.dump(packetLength);
/*      */     }
/*      */     
/* 2949 */     StringBuilder packetDumpBuf = new StringBuilder(4096);
/* 2950 */     packetDumpBuf.append(packetToDump.dump(1024));
/* 2951 */     packetDumpBuf.append(Messages.getString("MysqlIO.36"));
/* 2952 */     packetDumpBuf.append(1024);
/* 2953 */     packetDumpBuf.append(Messages.getString("MysqlIO.37"));
/*      */     
/* 2955 */     return packetDumpBuf.toString();
/*      */   }
/*      */   
/*      */   private final int readFully(InputStream in, byte[] b, int off, int len) throws IOException {
/* 2959 */     if (len < 0) {
/* 2960 */       throw new IndexOutOfBoundsException();
/*      */     }
/*      */     
/* 2963 */     int n = 0;
/*      */     
/* 2965 */     while (n < len) {
/* 2966 */       int count = in.read(b, off + n, len - n);
/*      */       
/* 2968 */       if (count < 0) {
/* 2969 */         throw new EOFException(Messages.getString("MysqlIO.EOF", new Object[] { Integer.valueOf(len), Integer.valueOf(n) }));
/*      */       }
/*      */       
/* 2972 */       n += count;
/*      */     } 
/*      */     
/* 2975 */     return n;
/*      */   }
/*      */   
/*      */   private final long skipFully(InputStream in, long len) throws IOException {
/* 2979 */     if (len < 0L) {
/* 2980 */       throw new IOException("Negative skip length not allowed");
/*      */     }
/*      */     
/* 2983 */     long n = 0L;
/*      */     
/* 2985 */     while (n < len) {
/* 2986 */       long count = in.skip(len - n);
/*      */       
/* 2988 */       if (count < 0L) {
/* 2989 */         throw new EOFException(Messages.getString("MysqlIO.EOF", new Object[] { Long.valueOf(len), Long.valueOf(n) }));
/*      */       }
/*      */       
/* 2992 */       n += count;
/*      */     } 
/*      */     
/* 2995 */     return n;
/*      */   }
/*      */   
/*      */   private final int skipLengthEncodedInteger(InputStream in) throws IOException {
/* 2999 */     int sw = in.read() & 0xFF;
/*      */     
/* 3001 */     switch (sw) {
/*      */       case 252:
/* 3003 */         return (int)skipFully(in, 2L) + 1;
/*      */       
/*      */       case 253:
/* 3006 */         return (int)skipFully(in, 3L) + 1;
/*      */       
/*      */       case 254:
/* 3009 */         return (int)skipFully(in, 8L) + 1;
/*      */     } 
/*      */     
/* 3012 */     return 1;
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
/*      */   protected final ResultSetImpl readResultsForQueryOrUpdate(StatementImpl callingStatement, int maxRows, int resultSetType, int resultSetConcurrency, boolean streamResults, String catalog, Buffer resultPacket, boolean isBinaryEncoded, long preSentColumnCount, Field[] metadataFromCache) throws SQLException {
/* 3049 */     long columnCount = resultPacket.readFieldLength();
/*      */     
/* 3051 */     if (columnCount == 0L)
/* 3052 */       return buildResultSetWithUpdates(callingStatement, resultPacket); 
/* 3053 */     if (columnCount == -1L) {
/* 3054 */       String charEncoding = null;
/*      */       
/* 3056 */       if (this.connection.getUseUnicode()) {
/* 3057 */         charEncoding = this.connection.getEncoding();
/*      */       }
/*      */       
/* 3060 */       String fileName = null;
/*      */       
/* 3062 */       if (this.platformDbCharsetMatches) {
/* 3063 */         fileName = (charEncoding != null) ? resultPacket.readString(charEncoding, getExceptionInterceptor()) : resultPacket.readString();
/*      */       } else {
/* 3065 */         fileName = resultPacket.readString();
/*      */       } 
/*      */       
/* 3068 */       return sendFileToServer(callingStatement, fileName);
/*      */     } 
/* 3070 */     ResultSetImpl results = getResultSet(callingStatement, columnCount, maxRows, resultSetType, resultSetConcurrency, streamResults, catalog, isBinaryEncoded, metadataFromCache);
/*      */ 
/*      */     
/* 3073 */     return results;
/*      */   }
/*      */ 
/*      */   
/*      */   private int alignPacketSize(int a, int l) {
/* 3078 */     return a + l - 1 & (l - 1 ^ 0xFFFFFFFF);
/*      */   }
/*      */ 
/*      */   
/*      */   private ResultSetImpl buildResultSetWithRows(StatementImpl callingStatement, String catalog, Field[] fields, RowData rows, int resultSetType, int resultSetConcurrency, boolean isBinaryEncoded) throws SQLException {
/* 3083 */     ResultSetImpl rs = null;
/*      */     
/* 3085 */     switch (resultSetConcurrency) {
/*      */       case 1007:
/* 3087 */         rs = ResultSetImpl.getInstance(catalog, fields, rows, this.connection, callingStatement, false);
/*      */         
/* 3089 */         if (isBinaryEncoded) {
/* 3090 */           rs.setBinaryEncoded();
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3104 */         rs.setResultSetType(resultSetType);
/* 3105 */         rs.setResultSetConcurrency(resultSetConcurrency);
/*      */         
/* 3107 */         return rs;case 1008: rs = ResultSetImpl.getInstance(catalog, fields, rows, this.connection, callingStatement, true); rs.setResultSetType(resultSetType); rs.setResultSetConcurrency(resultSetConcurrency); return rs;
/*      */     } 
/*      */     return ResultSetImpl.getInstance(catalog, fields, rows, this.connection, callingStatement, false);
/*      */   } private ResultSetImpl buildResultSetWithUpdates(StatementImpl callingStatement, Buffer resultPacket) throws SQLException {
/* 3111 */     long updateCount = -1L;
/* 3112 */     long updateID = -1L;
/* 3113 */     String info = null;
/*      */     
/*      */     try {
/* 3116 */       if (this.useNewUpdateCounts) {
/* 3117 */         updateCount = resultPacket.newReadLength();
/* 3118 */         updateID = resultPacket.newReadLength();
/*      */       } else {
/* 3120 */         updateCount = resultPacket.readLength();
/* 3121 */         updateID = resultPacket.readLength();
/*      */       } 
/*      */       
/* 3124 */       if (this.use41Extensions) {
/*      */         
/* 3126 */         this.serverStatus = resultPacket.readInt();
/*      */         
/* 3128 */         checkTransactionState(this.oldServerStatus);
/*      */         
/* 3130 */         this.warningCount = resultPacket.readInt();
/*      */         
/* 3132 */         if (this.warningCount > 0) {
/* 3133 */           this.hadWarnings = true;
/*      */         }
/*      */         
/* 3136 */         resultPacket.readByte();
/*      */         
/* 3138 */         setServerSlowQueryFlags();
/*      */       } 
/*      */       
/* 3141 */       if (this.connection.isReadInfoMsgEnabled()) {
/* 3142 */         info = resultPacket.readString(this.connection.getErrorMessageEncoding(), getExceptionInterceptor());
/*      */       }
/* 3144 */     } catch (Exception ex) {
/* 3145 */       SQLException sqlEx = SQLError.createSQLException(SQLError.get("S1000"), "S1000", -1, getExceptionInterceptor());
/*      */       
/* 3147 */       sqlEx.initCause(ex);
/*      */       
/* 3149 */       throw sqlEx;
/*      */     } 
/*      */     
/* 3152 */     ResultSetInternalMethods updateRs = ResultSetImpl.getInstance(updateCount, updateID, this.connection, callingStatement);
/*      */     
/* 3154 */     if (info != null) {
/* 3155 */       ((ResultSetImpl)updateRs).setServerInfo(info);
/*      */     }
/*      */     
/* 3158 */     return (ResultSetImpl)updateRs;
/*      */   }
/*      */   
/*      */   private void setServerSlowQueryFlags() {
/* 3162 */     this.queryBadIndexUsed = ((this.serverStatus & 0x10) != 0);
/* 3163 */     this.queryNoIndexUsed = ((this.serverStatus & 0x20) != 0);
/* 3164 */     this.serverQueryWasSlow = ((this.serverStatus & 0x800) != 0);
/*      */   }
/*      */   
/*      */   private void checkForOutstandingStreamingData() throws SQLException {
/* 3168 */     if (this.streamingData != null) {
/* 3169 */       boolean shouldClobber = this.connection.getClobberStreamingResults();
/*      */       
/* 3171 */       if (!shouldClobber) {
/* 3172 */         throw SQLError.createSQLException(Messages.getString("MysqlIO.39") + this.streamingData + Messages.getString("MysqlIO.40") + Messages.getString("MysqlIO.41") + Messages.getString("MysqlIO.42"), getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3177 */       this.streamingData.getOwner().realClose(false);
/*      */ 
/*      */       
/* 3180 */       clearInputStream();
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
/*      */   private Buffer compressPacket(Buffer packet, int offset, int packetLen) throws SQLException {
/* 3197 */     int compressedLength = packetLen;
/* 3198 */     int uncompressedLength = 0;
/* 3199 */     byte[] compressedBytes = null;
/* 3200 */     int offsetWrite = offset;
/*      */     
/* 3202 */     if (packetLen < 50) {
/* 3203 */       compressedBytes = packet.getByteBuffer();
/*      */     } else {
/*      */       
/* 3206 */       byte[] bytesToCompress = packet.getByteBuffer();
/* 3207 */       compressedBytes = new byte[bytesToCompress.length * 2];
/*      */       
/* 3209 */       if (this.deflater == null) {
/* 3210 */         this.deflater = new Deflater();
/*      */       }
/* 3212 */       this.deflater.reset();
/* 3213 */       this.deflater.setInput(bytesToCompress, offset, packetLen);
/* 3214 */       this.deflater.finish();
/*      */       
/* 3216 */       compressedLength = this.deflater.deflate(compressedBytes);
/*      */       
/* 3218 */       if (compressedLength > packetLen) {
/*      */         
/* 3220 */         compressedBytes = packet.getByteBuffer();
/* 3221 */         compressedLength = packetLen;
/*      */       } else {
/* 3223 */         uncompressedLength = packetLen;
/* 3224 */         offsetWrite = 0;
/*      */       } 
/*      */     } 
/*      */     
/* 3228 */     Buffer compressedPacket = new Buffer(7 + compressedLength);
/*      */     
/* 3230 */     compressedPacket.setPosition(0);
/* 3231 */     compressedPacket.writeLongInt(compressedLength);
/* 3232 */     compressedPacket.writeByte(this.compressedPacketSequence);
/* 3233 */     compressedPacket.writeLongInt(uncompressedLength);
/* 3234 */     compressedPacket.writeBytesNoNull(compressedBytes, offsetWrite, compressedLength);
/*      */     
/* 3236 */     return compressedPacket;
/*      */   }
/*      */   
/*      */   private final void readServerStatusForResultSets(Buffer rowPacket) throws SQLException {
/* 3240 */     if (this.use41Extensions) {
/* 3241 */       rowPacket.readByte();
/*      */       
/* 3243 */       if (isEOFDeprecated()) {
/*      */         
/* 3245 */         rowPacket.newReadLength();
/* 3246 */         rowPacket.newReadLength();
/*      */         
/* 3248 */         this.oldServerStatus = this.serverStatus;
/* 3249 */         this.serverStatus = rowPacket.readInt();
/* 3250 */         checkTransactionState(this.oldServerStatus);
/*      */         
/* 3252 */         this.warningCount = rowPacket.readInt();
/* 3253 */         if (this.warningCount > 0) {
/* 3254 */           this.hadWarnings = true;
/*      */         }
/*      */         
/* 3257 */         rowPacket.readByte();
/*      */         
/* 3259 */         if (this.connection.isReadInfoMsgEnabled()) {
/* 3260 */           rowPacket.readString(this.connection.getErrorMessageEncoding(), getExceptionInterceptor());
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 3265 */         this.warningCount = rowPacket.readInt();
/* 3266 */         if (this.warningCount > 0) {
/* 3267 */           this.hadWarnings = true;
/*      */         }
/*      */         
/* 3270 */         this.oldServerStatus = this.serverStatus;
/* 3271 */         this.serverStatus = rowPacket.readInt();
/* 3272 */         checkTransactionState(this.oldServerStatus);
/*      */       } 
/*      */       
/* 3275 */       setServerSlowQueryFlags();
/*      */     } 
/*      */   }
/*      */   
/*      */   private SocketFactory createSocketFactory() throws SQLException {
/*      */     try {
/* 3281 */       if (this.socketFactoryClassName == null) {
/* 3282 */         throw SQLError.createSQLException(Messages.getString("MysqlIO.75"), "08001", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/* 3286 */       return (SocketFactory)Class.forName(this.socketFactoryClassName).newInstance();
/* 3287 */     } catch (Exception ex) {
/* 3288 */       SQLException sqlEx = SQLError.createSQLException(Messages.getString("MysqlIO.76") + this.socketFactoryClassName + Messages.getString("MysqlIO.77"), "08001", getExceptionInterceptor());
/*      */ 
/*      */       
/* 3291 */       sqlEx.initCause(ex);
/*      */       
/* 3293 */       throw sqlEx;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void enqueuePacketForDebugging(boolean isPacketBeingSent, boolean isPacketReused, int sendLength, byte[] header, Buffer packet) throws SQLException {
/* 3299 */     if (this.packetDebugRingBuffer.size() + 1 > this.connection.getPacketDebugBufferSize()) {
/* 3300 */       this.packetDebugRingBuffer.removeFirst();
/*      */     }
/*      */     
/* 3303 */     StringBuilder packetDump = null;
/*      */     
/* 3305 */     if (!isPacketBeingSent) {
/* 3306 */       int bytesToDump = Math.min(1024, packet.getBufLength());
/*      */       
/* 3308 */       Buffer packetToDump = new Buffer(4 + bytesToDump);
/*      */       
/* 3310 */       packetToDump.setPosition(0);
/* 3311 */       packetToDump.writeBytesNoNull(header);
/* 3312 */       packetToDump.writeBytesNoNull(packet.getBytes(0, bytesToDump));
/*      */       
/* 3314 */       String packetPayload = packetToDump.dump(bytesToDump);
/*      */       
/* 3316 */       packetDump = new StringBuilder(96 + packetPayload.length());
/*      */       
/* 3318 */       packetDump.append("Server ");
/*      */       
/* 3320 */       packetDump.append(isPacketReused ? "(re-used) " : "(new) ");
/*      */       
/* 3322 */       packetDump.append(packet.toSuperString());
/* 3323 */       packetDump.append(" --------------------> Client\n");
/* 3324 */       packetDump.append("\nPacket payload:\n\n");
/* 3325 */       packetDump.append(packetPayload);
/*      */       
/* 3327 */       if (bytesToDump == 1024) {
/* 3328 */         packetDump.append("\nNote: Packet of " + packet.getBufLength() + " bytes truncated to " + 'Ѐ' + " bytes.\n");
/*      */       }
/*      */     } else {
/* 3331 */       int bytesToDump = Math.min(1024, sendLength);
/*      */       
/* 3333 */       String packetPayload = packet.dump(bytesToDump);
/*      */       
/* 3335 */       packetDump = new StringBuilder(68 + packetPayload.length());
/*      */       
/* 3337 */       packetDump.append("Client ");
/* 3338 */       packetDump.append(packet.toSuperString());
/* 3339 */       packetDump.append("--------------------> Server\n");
/* 3340 */       packetDump.append("\nPacket payload:\n\n");
/* 3341 */       packetDump.append(packetPayload);
/*      */       
/* 3343 */       if (bytesToDump == 1024) {
/* 3344 */         packetDump.append("\nNote: Packet of " + sendLength + " bytes truncated to " + 'Ѐ' + " bytes.\n");
/*      */       }
/*      */     } 
/*      */     
/* 3348 */     this.packetDebugRingBuffer.addLast(packetDump);
/*      */   }
/*      */ 
/*      */   
/*      */   private RowData readSingleRowSet(long columnCount, int maxRows, int resultSetConcurrency, boolean isBinaryEncoded, Field[] fields) throws SQLException {
/* 3353 */     ArrayList<ResultSetRow> rows = new ArrayList<ResultSetRow>();
/*      */     
/* 3355 */     boolean useBufferRowExplicit = useBufferRowExplicit(fields);
/*      */ 
/*      */     
/* 3358 */     ResultSetRow row = nextRow(fields, (int)columnCount, isBinaryEncoded, resultSetConcurrency, false, useBufferRowExplicit, false, null);
/*      */     
/* 3360 */     int rowCount = 0;
/*      */     
/* 3362 */     if (row != null) {
/* 3363 */       rows.add(row);
/* 3364 */       rowCount = 1;
/*      */     } 
/*      */     
/* 3367 */     while (row != null) {
/* 3368 */       row = nextRow(fields, (int)columnCount, isBinaryEncoded, resultSetConcurrency, false, useBufferRowExplicit, false, null);
/*      */       
/* 3370 */       if (row != null && (
/* 3371 */         maxRows == -1 || rowCount < maxRows)) {
/* 3372 */         rows.add(row);
/* 3373 */         rowCount++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 3378 */     RowData rowData = new RowDataStatic(rows);
/*      */     
/* 3380 */     return rowData;
/*      */   }
/*      */   
/*      */   public static boolean useBufferRowExplicit(Field[] fields) {
/* 3384 */     if (fields == null) {
/* 3385 */       return false;
/*      */     }
/*      */     
/* 3388 */     for (int i = 0; i < fields.length; i++) {
/* 3389 */       switch (fields[i].getSQLType()) {
/*      */         case -4:
/*      */         case -1:
/*      */         case 2004:
/*      */         case 2005:
/* 3394 */           return true;
/*      */       } 
/*      */     
/*      */     } 
/* 3398 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void reclaimLargeReusablePacket() {
/* 3405 */     if (this.reusablePacket != null && this.reusablePacket.getCapacity() > 1048576) {
/* 3406 */       this.reusablePacket = new Buffer(1024);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final Buffer reuseAndReadPacket(Buffer reuse) throws SQLException {
/* 3417 */     return reuseAndReadPacket(reuse, -1);
/*      */   }
/*      */ 
/*      */   
/*      */   private final Buffer reuseAndReadPacket(Buffer reuse, int existingPacketLength) throws SQLException {
/*      */     try {
/* 3423 */       reuse.setWasMultiPacket(false);
/* 3424 */       int packetLength = 0;
/*      */       
/* 3426 */       if (existingPacketLength == -1) {
/* 3427 */         int lengthRead = readFully(this.mysqlInput, this.packetHeaderBuf, 0, 4);
/*      */         
/* 3429 */         if (lengthRead < 4) {
/* 3430 */           forceClose();
/* 3431 */           throw new IOException(Messages.getString("MysqlIO.43"));
/*      */         } 
/*      */         
/* 3434 */         packetLength = (this.packetHeaderBuf[0] & 0xFF) + ((this.packetHeaderBuf[1] & 0xFF) << 8) + ((this.packetHeaderBuf[2] & 0xFF) << 16);
/*      */       } else {
/* 3436 */         packetLength = existingPacketLength;
/*      */       } 
/*      */       
/* 3439 */       if (this.traceProtocol) {
/* 3440 */         StringBuilder traceMessageBuf = new StringBuilder();
/*      */         
/* 3442 */         traceMessageBuf.append(Messages.getString("MysqlIO.44"));
/* 3443 */         traceMessageBuf.append(packetLength);
/* 3444 */         traceMessageBuf.append(Messages.getString("MysqlIO.45"));
/* 3445 */         traceMessageBuf.append(StringUtils.dumpAsHex(this.packetHeaderBuf, 4));
/*      */         
/* 3447 */         this.connection.getLog().logTrace(traceMessageBuf.toString());
/*      */       } 
/*      */       
/* 3450 */       byte multiPacketSeq = this.packetHeaderBuf[3];
/*      */       
/* 3452 */       if (!this.packetSequenceReset) {
/* 3453 */         if (this.enablePacketDebug && this.checkPacketSequence) {
/* 3454 */           checkPacketSequencing(multiPacketSeq);
/*      */         }
/*      */       } else {
/* 3457 */         this.packetSequenceReset = false;
/*      */       } 
/*      */       
/* 3460 */       this.readPacketSequence = multiPacketSeq;
/*      */ 
/*      */       
/* 3463 */       reuse.setPosition(0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3469 */       if ((reuse.getByteBuffer()).length <= packetLength) {
/* 3470 */         reuse.setByteBuffer(new byte[packetLength + 1]);
/*      */       }
/*      */ 
/*      */       
/* 3474 */       reuse.setBufLength(packetLength);
/*      */ 
/*      */       
/* 3477 */       int numBytesRead = readFully(this.mysqlInput, reuse.getByteBuffer(), 0, packetLength);
/*      */       
/* 3479 */       if (numBytesRead != packetLength) {
/* 3480 */         throw new IOException("Short read, expected " + packetLength + " bytes, only read " + numBytesRead);
/*      */       }
/*      */       
/* 3483 */       if (this.traceProtocol) {
/* 3484 */         StringBuilder traceMessageBuf = new StringBuilder();
/*      */         
/* 3486 */         traceMessageBuf.append(Messages.getString("MysqlIO.46"));
/* 3487 */         traceMessageBuf.append(getPacketDumpToLog(reuse, packetLength));
/*      */         
/* 3489 */         this.connection.getLog().logTrace(traceMessageBuf.toString());
/*      */       } 
/*      */       
/* 3492 */       if (this.enablePacketDebug) {
/* 3493 */         enqueuePacketForDebugging(false, true, 0, this.packetHeaderBuf, reuse);
/*      */       }
/*      */       
/* 3496 */       boolean isMultiPacket = false;
/*      */       
/* 3498 */       if (packetLength == this.maxThreeBytes) {
/* 3499 */         reuse.setPosition(this.maxThreeBytes);
/*      */ 
/*      */         
/* 3502 */         isMultiPacket = true;
/*      */         
/* 3504 */         packetLength = readRemainingMultiPackets(reuse, multiPacketSeq);
/*      */       } 
/*      */       
/* 3507 */       if (!isMultiPacket) {
/* 3508 */         reuse.getByteBuffer()[packetLength] = 0;
/*      */       }
/*      */       
/* 3511 */       if (this.connection.getMaintainTimeStats()) {
/* 3512 */         this.lastPacketReceivedTimeMs = System.currentTimeMillis();
/*      */       }
/*      */       
/* 3515 */       return reuse;
/* 3516 */     } catch (IOException ioEx) {
/* 3517 */       throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ioEx, getExceptionInterceptor());
/*      */     }
/* 3519 */     catch (OutOfMemoryError oom) {
/*      */       
/*      */       try {
/* 3522 */         clearInputStream();
/* 3523 */       } catch (Exception ex) {}
/*      */       
/*      */       try {
/* 3526 */         this.connection.realClose(false, false, true, oom);
/* 3527 */       } catch (Exception ex) {}
/*      */       
/* 3529 */       throw oom;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private int readRemainingMultiPackets(Buffer reuse, byte multiPacketSeq) throws IOException, SQLException {
/* 3535 */     int packetLength = -1;
/* 3536 */     Buffer multiPacket = null;
/*      */     
/*      */     do {
/* 3539 */       int lengthRead = readFully(this.mysqlInput, this.packetHeaderBuf, 0, 4);
/* 3540 */       if (lengthRead < 4) {
/* 3541 */         forceClose();
/* 3542 */         throw new IOException(Messages.getString("MysqlIO.47"));
/*      */       } 
/*      */       
/* 3545 */       packetLength = (this.packetHeaderBuf[0] & 0xFF) + ((this.packetHeaderBuf[1] & 0xFF) << 8) + ((this.packetHeaderBuf[2] & 0xFF) << 16);
/* 3546 */       if (multiPacket == null) {
/* 3547 */         multiPacket = new Buffer(packetLength);
/*      */       }
/*      */       
/* 3550 */       if (!this.useNewLargePackets && packetLength == 1) {
/* 3551 */         clearInputStream();
/*      */         
/*      */         break;
/*      */       } 
/* 3555 */       multiPacketSeq = (byte)(multiPacketSeq + 1);
/* 3556 */       if (multiPacketSeq != this.packetHeaderBuf[3]) {
/* 3557 */         throw new IOException(Messages.getString("MysqlIO.49"));
/*      */       }
/*      */ 
/*      */       
/* 3561 */       multiPacket.setPosition(0);
/*      */ 
/*      */       
/* 3564 */       multiPacket.setBufLength(packetLength);
/*      */ 
/*      */       
/* 3567 */       byte[] byteBuf = multiPacket.getByteBuffer();
/* 3568 */       int lengthToWrite = packetLength;
/*      */       
/* 3570 */       int bytesRead = readFully(this.mysqlInput, byteBuf, 0, packetLength);
/*      */       
/* 3572 */       if (bytesRead != lengthToWrite) {
/* 3573 */         throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, SQLError.createSQLException(Messages.getString("MysqlIO.50") + lengthToWrite + Messages.getString("MysqlIO.51") + bytesRead + ".", getExceptionInterceptor()), getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3579 */       reuse.writeBytesNoNull(byteBuf, 0, lengthToWrite);
/* 3580 */     } while (packetLength == this.maxThreeBytes);
/*      */     
/* 3582 */     reuse.setPosition(0);
/* 3583 */     reuse.setWasMultiPacket(true);
/* 3584 */     return packetLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkPacketSequencing(byte multiPacketSeq) throws SQLException {
/* 3592 */     if (multiPacketSeq == Byte.MIN_VALUE && this.readPacketSequence != Byte.MAX_VALUE) {
/* 3593 */       throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, new IOException("Packets out of order, expected packet # -128, but received packet # " + multiPacketSeq), getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 3597 */     if (this.readPacketSequence == -1 && multiPacketSeq != 0) {
/* 3598 */       throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, new IOException("Packets out of order, expected packet # -1, but received packet # " + multiPacketSeq), getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 3602 */     if (multiPacketSeq != Byte.MIN_VALUE && this.readPacketSequence != -1 && multiPacketSeq != this.readPacketSequence + 1) {
/* 3603 */       throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, new IOException("Packets out of order, expected packet # " + (this.readPacketSequence + 1) + ", but received packet # " + multiPacketSeq), getExceptionInterceptor());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void enableMultiQueries() throws SQLException {
/* 3610 */     Buffer buf = getSharedSendPacket();
/*      */     
/* 3612 */     buf.clear();
/* 3613 */     buf.writeByte((byte)27);
/* 3614 */     buf.writeInt(0);
/* 3615 */     sendCommand(27, null, buf, false, null, 0);
/* 3616 */     preserveOldTransactionState();
/*      */   }
/*      */   
/*      */   void disableMultiQueries() throws SQLException {
/* 3620 */     Buffer buf = getSharedSendPacket();
/*      */     
/* 3622 */     buf.clear();
/* 3623 */     buf.writeByte((byte)27);
/* 3624 */     buf.writeInt(1);
/* 3625 */     sendCommand(27, null, buf, false, null, 0);
/* 3626 */     preserveOldTransactionState();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void send(Buffer packet, int packetLen) throws SQLException {
/*      */     try {
/* 3637 */       if (this.maxAllowedPacket > 0 && packetLen > this.maxAllowedPacket) {
/* 3638 */         throw new PacketTooBigException(packetLen, this.maxAllowedPacket);
/*      */       }
/*      */       
/* 3641 */       if (this.serverMajorVersion >= 4 && (packetLen - 4 >= this.maxThreeBytes || (this.useCompression && packetLen - 4 >= this.maxThreeBytes - 3))) {
/*      */         
/* 3643 */         sendSplitPackets(packet, packetLen);
/*      */       } else {
/*      */         
/* 3646 */         this.packetSequence = (byte)(this.packetSequence + 1);
/*      */         
/* 3648 */         Buffer packetToSend = packet;
/* 3649 */         packetToSend.setPosition(0);
/* 3650 */         packetToSend.writeLongInt(packetLen - 4);
/* 3651 */         packetToSend.writeByte(this.packetSequence);
/*      */         
/* 3653 */         if (this.useCompression) {
/* 3654 */           this.compressedPacketSequence = (byte)(this.compressedPacketSequence + 1);
/* 3655 */           int originalPacketLen = packetLen;
/*      */           
/* 3657 */           packetToSend = compressPacket(packetToSend, 0, packetLen);
/* 3658 */           packetLen = packetToSend.getPosition();
/*      */           
/* 3660 */           if (this.traceProtocol) {
/* 3661 */             StringBuilder traceMessageBuf = new StringBuilder();
/*      */             
/* 3663 */             traceMessageBuf.append(Messages.getString("MysqlIO.57"));
/* 3664 */             traceMessageBuf.append(getPacketDumpToLog(packetToSend, packetLen));
/* 3665 */             traceMessageBuf.append(Messages.getString("MysqlIO.58"));
/* 3666 */             traceMessageBuf.append(getPacketDumpToLog(packet, originalPacketLen));
/*      */             
/* 3668 */             this.connection.getLog().logTrace(traceMessageBuf.toString());
/*      */           }
/*      */         
/*      */         }
/* 3672 */         else if (this.traceProtocol) {
/* 3673 */           StringBuilder traceMessageBuf = new StringBuilder();
/*      */           
/* 3675 */           traceMessageBuf.append(Messages.getString("MysqlIO.59"));
/* 3676 */           traceMessageBuf.append("host: '");
/* 3677 */           traceMessageBuf.append(this.host);
/* 3678 */           traceMessageBuf.append("' threadId: '");
/* 3679 */           traceMessageBuf.append(this.threadId);
/* 3680 */           traceMessageBuf.append("'\n");
/* 3681 */           traceMessageBuf.append(packetToSend.dump(packetLen));
/*      */           
/* 3683 */           this.connection.getLog().logTrace(traceMessageBuf.toString());
/*      */         } 
/*      */ 
/*      */         
/* 3687 */         this.mysqlOutput.write(packetToSend.getByteBuffer(), 0, packetLen);
/* 3688 */         this.mysqlOutput.flush();
/*      */       } 
/*      */       
/* 3691 */       if (this.enablePacketDebug) {
/* 3692 */         enqueuePacketForDebugging(true, false, packetLen + 5, this.packetHeaderBuf, packet);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3698 */       if (packet == this.sharedSendPacket) {
/* 3699 */         reclaimLargeSharedSendPacket();
/*      */       }
/*      */       
/* 3702 */       if (this.connection.getMaintainTimeStats()) {
/* 3703 */         this.lastPacketSentTimeMs = System.currentTimeMillis();
/*      */       }
/* 3705 */     } catch (IOException ioEx) {
/* 3706 */       throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ioEx, getExceptionInterceptor());
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
/*      */   private final ResultSetImpl sendFileToServer(StatementImpl callingStatement, String fileName) throws SQLException {
/* 3722 */     if (this.useCompression) {
/* 3723 */       this.compressedPacketSequence = (byte)(this.compressedPacketSequence + 1);
/*      */     }
/*      */     
/* 3726 */     Buffer filePacket = (this.loadFileBufRef == null) ? null : this.loadFileBufRef.get();
/*      */     
/* 3728 */     int bigPacketLength = Math.min(this.connection.getMaxAllowedPacket() - 12, alignPacketSize(this.connection.getMaxAllowedPacket() - 16, 4096) - 12);
/*      */ 
/*      */     
/* 3731 */     int oneMeg = 1048576;
/*      */     
/* 3733 */     int smallerPacketSizeAligned = Math.min(oneMeg - 12, alignPacketSize(oneMeg - 16, 4096) - 12);
/*      */     
/* 3735 */     int packetLength = Math.min(smallerPacketSizeAligned, bigPacketLength);
/*      */     
/* 3737 */     if (filePacket == null) {
/*      */       try {
/* 3739 */         filePacket = new Buffer(packetLength + 4);
/* 3740 */         this.loadFileBufRef = new SoftReference<Buffer>(filePacket);
/* 3741 */       } catch (OutOfMemoryError oom) {
/* 3742 */         throw SQLError.createSQLException("Could not allocate packet of " + packetLength + " bytes required for LOAD DATA LOCAL INFILE operation." + " Try increasing max heap allocation for JVM or decreasing server variable 'max_allowed_packet'", "S1001", getExceptionInterceptor());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3750 */     filePacket.clear();
/* 3751 */     send(filePacket, 0);
/*      */     
/* 3753 */     byte[] fileBuf = new byte[packetLength];
/*      */     
/* 3755 */     BufferedInputStream fileIn = null;
/*      */     
/*      */     try {
/* 3758 */       if (!this.connection.getAllowLoadLocalInfile()) {
/* 3759 */         throw SQLError.createSQLException(Messages.getString("MysqlIO.LoadDataLocalNotAllowed"), "S1000", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/* 3763 */       InputStream hookedStream = null;
/*      */       
/* 3765 */       if (callingStatement != null) {
/* 3766 */         hookedStream = callingStatement.getLocalInfileInputStream();
/*      */       }
/*      */       
/* 3769 */       if (hookedStream != null) {
/* 3770 */         fileIn = new BufferedInputStream(hookedStream);
/* 3771 */       } else if (!this.connection.getAllowUrlInLocalInfile()) {
/* 3772 */         fileIn = new BufferedInputStream(new FileInputStream(fileName));
/*      */       
/*      */       }
/* 3775 */       else if (fileName.indexOf(':') != -1) {
/*      */         try {
/* 3777 */           URL urlFromFileName = new URL(fileName);
/* 3778 */           fileIn = new BufferedInputStream(urlFromFileName.openStream());
/* 3779 */         } catch (MalformedURLException badUrlEx) {
/*      */           
/* 3781 */           fileIn = new BufferedInputStream(new FileInputStream(fileName));
/*      */         } 
/*      */       } else {
/* 3784 */         fileIn = new BufferedInputStream(new FileInputStream(fileName));
/*      */       } 
/*      */ 
/*      */       
/* 3788 */       int bytesRead = 0;
/*      */       
/* 3790 */       while ((bytesRead = fileIn.read(fileBuf)) != -1) {
/* 3791 */         filePacket.clear();
/* 3792 */         filePacket.writeBytesNoNull(fileBuf, 0, bytesRead);
/* 3793 */         send(filePacket, filePacket.getPosition());
/*      */       } 
/* 3795 */     } catch (IOException ioEx) {
/* 3796 */       StringBuilder messageBuf = new StringBuilder(Messages.getString("MysqlIO.60"));
/*      */       
/* 3798 */       if (fileName != null && !this.connection.getParanoid()) {
/* 3799 */         messageBuf.append("'");
/* 3800 */         messageBuf.append(fileName);
/* 3801 */         messageBuf.append("'");
/*      */       } 
/*      */       
/* 3804 */       messageBuf.append(Messages.getString("MysqlIO.63"));
/*      */       
/* 3806 */       if (!this.connection.getParanoid()) {
/* 3807 */         messageBuf.append(Messages.getString("MysqlIO.64"));
/* 3808 */         messageBuf.append(Util.stackTraceToString(ioEx));
/*      */       } 
/*      */       
/* 3811 */       throw SQLError.createSQLException(messageBuf.toString(), "S1009", getExceptionInterceptor());
/*      */     } finally {
/* 3813 */       if (fileIn != null) {
/*      */         try {
/* 3815 */           fileIn.close();
/* 3816 */         } catch (Exception ex) {
/* 3817 */           SQLException sqlEx = SQLError.createSQLException(Messages.getString("MysqlIO.65"), "S1000", ex, getExceptionInterceptor());
/*      */ 
/*      */           
/* 3820 */           throw sqlEx;
/*      */         } 
/*      */         
/* 3823 */         fileIn = null;
/*      */       } else {
/*      */         
/* 3826 */         filePacket.clear();
/* 3827 */         send(filePacket, filePacket.getPosition());
/* 3828 */         checkErrorPacket();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 3833 */     filePacket.clear();
/* 3834 */     send(filePacket, filePacket.getPosition());
/*      */     
/* 3836 */     Buffer resultPacket = checkErrorPacket();
/*      */     
/* 3838 */     return buildResultSetWithUpdates(callingStatement, resultPacket);
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
/*      */   private Buffer checkErrorPacket(int command) throws SQLException {
/* 3854 */     Buffer resultPacket = null;
/* 3855 */     this.serverStatus = 0;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3860 */       resultPacket = reuseAndReadPacket(this.reusablePacket);
/* 3861 */     } catch (SQLException sqlEx) {
/*      */       
/* 3863 */       throw sqlEx;
/* 3864 */     } catch (Exception fallThru) {
/* 3865 */       throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, fallThru, getExceptionInterceptor());
/*      */     } 
/*      */ 
/*      */     
/* 3869 */     checkErrorPacket(resultPacket);
/*      */     
/* 3871 */     return resultPacket;
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkErrorPacket(Buffer resultPacket) throws SQLException {
/* 3876 */     int statusCode = resultPacket.readByte();
/*      */ 
/*      */     
/* 3879 */     if (statusCode == -1) {
/*      */       
/* 3881 */       int errno = 2000;
/*      */       
/* 3883 */       if (this.protocolVersion > 9) {
/* 3884 */         errno = resultPacket.readInt();
/*      */         
/* 3886 */         String xOpen = null;
/*      */         
/* 3888 */         String str1 = resultPacket.readString(this.connection.getErrorMessageEncoding(), getExceptionInterceptor());
/*      */         
/* 3890 */         if (str1.charAt(0) == '#') {
/*      */ 
/*      */           
/* 3893 */           if (str1.length() > 6) {
/* 3894 */             xOpen = str1.substring(1, 6);
/* 3895 */             str1 = str1.substring(6);
/*      */             
/* 3897 */             if (xOpen.equals("HY000")) {
/* 3898 */               xOpen = SQLError.mysqlToSqlState(errno, this.connection.getUseSqlStateCodes());
/*      */             }
/*      */           } else {
/* 3901 */             xOpen = SQLError.mysqlToSqlState(errno, this.connection.getUseSqlStateCodes());
/*      */           } 
/*      */         } else {
/* 3904 */           xOpen = SQLError.mysqlToSqlState(errno, this.connection.getUseSqlStateCodes());
/*      */         } 
/*      */         
/* 3907 */         clearInputStream();
/*      */         
/* 3909 */         StringBuilder stringBuilder = new StringBuilder();
/*      */         
/* 3911 */         String xOpenErrorMessage = SQLError.get(xOpen);
/*      */         
/* 3913 */         if (!this.connection.getUseOnlyServerErrorMessages() && 
/* 3914 */           xOpenErrorMessage != null) {
/* 3915 */           stringBuilder.append(xOpenErrorMessage);
/* 3916 */           stringBuilder.append(Messages.getString("MysqlIO.68"));
/*      */         } 
/*      */ 
/*      */         
/* 3920 */         stringBuilder.append(str1);
/*      */         
/* 3922 */         if (!this.connection.getUseOnlyServerErrorMessages() && 
/* 3923 */           xOpenErrorMessage != null) {
/* 3924 */           stringBuilder.append("\"");
/*      */         }
/*      */ 
/*      */         
/* 3928 */         appendDeadlockStatusInformation(xOpen, stringBuilder);
/*      */         
/* 3930 */         if (xOpen != null && xOpen.startsWith("22")) {
/* 3931 */           throw new MysqlDataTruncation(stringBuilder.toString(), 0, true, false, 0, 0, errno);
/*      */         }
/* 3933 */         throw SQLError.createSQLException(stringBuilder.toString(), xOpen, errno, false, getExceptionInterceptor(), this.connection);
/*      */       } 
/*      */       
/* 3936 */       String serverErrorMessage = resultPacket.readString(this.connection.getErrorMessageEncoding(), getExceptionInterceptor());
/* 3937 */       clearInputStream();
/*      */       
/* 3939 */       if (serverErrorMessage.indexOf(Messages.getString("MysqlIO.70")) != -1) {
/* 3940 */         throw SQLError.createSQLException(SQLError.get("S0022") + ", " + serverErrorMessage, "S0022", -1, false, getExceptionInterceptor(), this.connection);
/*      */       }
/*      */ 
/*      */       
/* 3944 */       StringBuilder errorBuf = new StringBuilder(Messages.getString("MysqlIO.72"));
/* 3945 */       errorBuf.append(serverErrorMessage);
/* 3946 */       errorBuf.append("\"");
/*      */       
/* 3948 */       throw SQLError.createSQLException(SQLError.get("S1000") + ", " + errorBuf.toString(), "S1000", -1, false, getExceptionInterceptor(), this.connection);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void appendDeadlockStatusInformation(String xOpen, StringBuilder errorBuf) throws SQLException {
/* 3954 */     if (this.connection.getIncludeInnodbStatusInDeadlockExceptions() && xOpen != null && (xOpen.startsWith("40") || xOpen.startsWith("41")) && this.streamingData == null) {
/*      */       
/* 3956 */       ResultSet rs = null;
/*      */       
/*      */       try {
/* 3959 */         rs = sqlQueryDirect(null, "SHOW ENGINE INNODB STATUS", this.connection.getEncoding(), null, -1, 1003, 1007, false, this.connection.getCatalog(), null);
/*      */ 
/*      */         
/* 3962 */         if (rs.next()) {
/* 3963 */           errorBuf.append("\n\n");
/* 3964 */           errorBuf.append(rs.getString("Status"));
/*      */         } else {
/* 3966 */           errorBuf.append("\n\n");
/* 3967 */           errorBuf.append(Messages.getString("MysqlIO.NoInnoDBStatusFound"));
/*      */         } 
/* 3969 */       } catch (Exception ex) {
/* 3970 */         errorBuf.append("\n\n");
/* 3971 */         errorBuf.append(Messages.getString("MysqlIO.InnoDBStatusFailed"));
/* 3972 */         errorBuf.append("\n\n");
/* 3973 */         errorBuf.append(Util.stackTraceToString(ex));
/*      */       } finally {
/* 3975 */         if (rs != null) {
/* 3976 */           rs.close();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 3981 */     if (this.connection.getIncludeThreadDumpInDeadlockExceptions()) {
/* 3982 */       errorBuf.append("\n\n*** Java threads running at time of deadlock ***\n\n");
/*      */       
/* 3984 */       ThreadMXBean threadMBean = ManagementFactory.getThreadMXBean();
/* 3985 */       long[] threadIds = threadMBean.getAllThreadIds();
/*      */       
/* 3987 */       ThreadInfo[] threads = threadMBean.getThreadInfo(threadIds, 2147483647);
/* 3988 */       List<ThreadInfo> activeThreads = new ArrayList<ThreadInfo>();
/*      */       
/* 3990 */       for (ThreadInfo info : threads) {
/* 3991 */         if (info != null) {
/* 3992 */           activeThreads.add(info);
/*      */         }
/*      */       } 
/*      */       
/* 3996 */       for (ThreadInfo threadInfo : activeThreads) {
/*      */ 
/*      */         
/* 3999 */         errorBuf.append('"');
/* 4000 */         errorBuf.append(threadInfo.getThreadName());
/* 4001 */         errorBuf.append("\" tid=");
/* 4002 */         errorBuf.append(threadInfo.getThreadId());
/* 4003 */         errorBuf.append(" ");
/* 4004 */         errorBuf.append(threadInfo.getThreadState());
/*      */         
/* 4006 */         if (threadInfo.getLockName() != null) {
/* 4007 */           errorBuf.append(" on lock=" + threadInfo.getLockName());
/*      */         }
/* 4009 */         if (threadInfo.isSuspended()) {
/* 4010 */           errorBuf.append(" (suspended)");
/*      */         }
/* 4012 */         if (threadInfo.isInNative()) {
/* 4013 */           errorBuf.append(" (running in native)");
/*      */         }
/*      */         
/* 4016 */         StackTraceElement[] stackTrace = threadInfo.getStackTrace();
/*      */         
/* 4018 */         if (stackTrace.length > 0) {
/* 4019 */           errorBuf.append(" in ");
/* 4020 */           errorBuf.append(stackTrace[0].getClassName());
/* 4021 */           errorBuf.append(".");
/* 4022 */           errorBuf.append(stackTrace[0].getMethodName());
/* 4023 */           errorBuf.append("()");
/*      */         } 
/*      */         
/* 4026 */         errorBuf.append("\n");
/*      */         
/* 4028 */         if (threadInfo.getLockOwnerName() != null) {
/* 4029 */           errorBuf.append("\t owned by " + threadInfo.getLockOwnerName() + " Id=" + threadInfo.getLockOwnerId());
/* 4030 */           errorBuf.append("\n");
/*      */         } 
/*      */         
/* 4033 */         for (int j = 0; j < stackTrace.length; j++) {
/* 4034 */           StackTraceElement ste = stackTrace[j];
/* 4035 */           errorBuf.append("\tat " + ste.toString());
/* 4036 */           errorBuf.append("\n");
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
/*      */   private final void sendSplitPackets(Buffer packet, int packetLen) throws SQLException {
/*      */     try {
/* 4052 */       Buffer packetToSend = (this.splitBufRef == null) ? null : this.splitBufRef.get();
/* 4053 */       Buffer toCompress = (!this.useCompression || this.compressBufRef == null) ? null : this.compressBufRef.get();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4059 */       if (packetToSend == null) {
/* 4060 */         packetToSend = new Buffer(this.maxThreeBytes + 4);
/* 4061 */         this.splitBufRef = new SoftReference<Buffer>(packetToSend);
/*      */       } 
/* 4063 */       if (this.useCompression) {
/* 4064 */         int cbuflen = packetLen + (packetLen / this.maxThreeBytes + 1) * 4;
/* 4065 */         if (toCompress == null) {
/* 4066 */           toCompress = new Buffer(cbuflen);
/* 4067 */           this.compressBufRef = new SoftReference<Buffer>(toCompress);
/* 4068 */         } else if (toCompress.getBufLength() < cbuflen) {
/* 4069 */           toCompress.setPosition(toCompress.getBufLength());
/* 4070 */           toCompress.ensureCapacity(cbuflen - toCompress.getBufLength());
/*      */         } 
/*      */       } 
/*      */       
/* 4074 */       int len = packetLen - 4;
/* 4075 */       int splitSize = this.maxThreeBytes;
/* 4076 */       int originalPacketPos = 4;
/* 4077 */       byte[] origPacketBytes = packet.getByteBuffer();
/*      */       
/* 4079 */       int toCompressPosition = 0;
/*      */ 
/*      */       
/* 4082 */       while (len >= 0) {
/* 4083 */         this.packetSequence = (byte)(this.packetSequence + 1);
/*      */         
/* 4085 */         if (len < splitSize) {
/* 4086 */           splitSize = len;
/*      */         }
/*      */         
/* 4089 */         packetToSend.setPosition(0);
/* 4090 */         packetToSend.writeLongInt(splitSize);
/* 4091 */         packetToSend.writeByte(this.packetSequence);
/* 4092 */         if (len > 0) {
/* 4093 */           System.arraycopy(origPacketBytes, originalPacketPos, packetToSend.getByteBuffer(), 4, splitSize);
/*      */         }
/*      */         
/* 4096 */         if (this.useCompression) {
/* 4097 */           System.arraycopy(packetToSend.getByteBuffer(), 0, toCompress.getByteBuffer(), toCompressPosition, 4 + splitSize);
/* 4098 */           toCompressPosition += 4 + splitSize;
/*      */         } else {
/* 4100 */           this.mysqlOutput.write(packetToSend.getByteBuffer(), 0, 4 + splitSize);
/* 4101 */           this.mysqlOutput.flush();
/*      */         } 
/*      */         
/* 4104 */         originalPacketPos += splitSize;
/* 4105 */         len -= this.maxThreeBytes;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 4110 */       if (this.useCompression) {
/* 4111 */         len = toCompressPosition;
/* 4112 */         toCompressPosition = 0;
/* 4113 */         splitSize = this.maxThreeBytes - 3;
/* 4114 */         while (len >= 0) {
/* 4115 */           this.compressedPacketSequence = (byte)(this.compressedPacketSequence + 1);
/*      */           
/* 4117 */           if (len < splitSize) {
/* 4118 */             splitSize = len;
/*      */           }
/*      */           
/* 4121 */           Buffer compressedPacketToSend = compressPacket(toCompress, toCompressPosition, splitSize);
/* 4122 */           packetLen = compressedPacketToSend.getPosition();
/* 4123 */           this.mysqlOutput.write(compressedPacketToSend.getByteBuffer(), 0, packetLen);
/* 4124 */           this.mysqlOutput.flush();
/*      */           
/* 4126 */           toCompressPosition += splitSize;
/* 4127 */           len -= this.maxThreeBytes - 3;
/*      */         } 
/*      */       } 
/* 4130 */     } catch (IOException ioEx) {
/* 4131 */       throw SQLError.createCommunicationsException(this.connection, this.lastPacketSentTimeMs, this.lastPacketReceivedTimeMs, ioEx, getExceptionInterceptor());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void reclaimLargeSharedSendPacket() {
/* 4137 */     if (this.sharedSendPacket != null && this.sharedSendPacket.getCapacity() > 1048576) {
/* 4138 */       this.sharedSendPacket = new Buffer(1024);
/*      */     }
/*      */   }
/*      */   
/*      */   boolean hadWarnings() {
/* 4143 */     return this.hadWarnings;
/*      */   }
/*      */   
/*      */   void scanForAndThrowDataTruncation() throws SQLException {
/* 4147 */     if (this.streamingData == null && versionMeetsMinimum(4, 1, 0) && this.connection.getJdbcCompliantTruncation() && this.warningCount > 0) {
/* 4148 */       int warningCountOld = this.warningCount;
/* 4149 */       SQLError.convertShowWarningsToSQLWarnings(this.connection, this.warningCount, true);
/* 4150 */       this.warningCount = warningCountOld;
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
/*      */   private void secureAuth(Buffer packet, int packLength, String user, String password, String database, boolean writeClientParams) throws SQLException {
/* 4168 */     if (packet == null) {
/* 4169 */       packet = new Buffer(packLength);
/*      */     }
/*      */     
/* 4172 */     if (writeClientParams) {
/* 4173 */       if (this.use41Extensions) {
/* 4174 */         if (versionMeetsMinimum(4, 1, 1)) {
/* 4175 */           packet.writeLong(this.clientParam);
/* 4176 */           packet.writeLong(this.maxThreeBytes);
/*      */ 
/*      */           
/* 4179 */           packet.writeByte((byte)8);
/*      */ 
/*      */           
/* 4182 */           packet.writeBytesNoNull(new byte[23]);
/*      */         } else {
/* 4184 */           packet.writeLong(this.clientParam);
/* 4185 */           packet.writeLong(this.maxThreeBytes);
/*      */         } 
/*      */       } else {
/* 4188 */         packet.writeInt((int)this.clientParam);
/* 4189 */         packet.writeLongInt(this.maxThreeBytes);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 4194 */     packet.writeString(user, "Cp1252", this.connection);
/*      */     
/* 4196 */     if (password.length() != 0) {
/*      */       
/* 4198 */       packet.writeString("xxxxxxxx", "Cp1252", this.connection);
/*      */     } else {
/*      */       
/* 4201 */       packet.writeString("", "Cp1252", this.connection);
/*      */     } 
/*      */     
/* 4204 */     if (this.useConnectWithDb) {
/* 4205 */       packet.writeString(database, "Cp1252", this.connection);
/*      */     }
/*      */     
/* 4208 */     send(packet, packet.getPosition());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4213 */     if (password.length() > 0) {
/* 4214 */       Buffer b = readPacket();
/*      */       
/* 4216 */       b.setPosition(0);
/*      */       
/* 4218 */       byte[] replyAsBytes = b.getByteBuffer();
/*      */       
/* 4220 */       if (replyAsBytes.length == 24 && replyAsBytes[0] != 0)
/*      */       {
/* 4222 */         if (replyAsBytes[0] != 42) {
/*      */           
/*      */           try {
/* 4225 */             byte[] buff = Security.passwordHashStage1(password);
/*      */ 
/*      */             
/* 4228 */             byte[] passwordHash = new byte[buff.length];
/* 4229 */             System.arraycopy(buff, 0, passwordHash, 0, buff.length);
/*      */ 
/*      */             
/* 4232 */             passwordHash = Security.passwordHashStage2(passwordHash, replyAsBytes);
/*      */             
/* 4234 */             byte[] packetDataAfterSalt = new byte[replyAsBytes.length - 4];
/*      */             
/* 4236 */             System.arraycopy(replyAsBytes, 4, packetDataAfterSalt, 0, replyAsBytes.length - 4);
/*      */             
/* 4238 */             byte[] mysqlScrambleBuff = new byte[20];
/*      */ 
/*      */             
/* 4241 */             Security.xorString(packetDataAfterSalt, mysqlScrambleBuff, passwordHash, 20);
/*      */ 
/*      */             
/* 4244 */             Security.xorString(mysqlScrambleBuff, buff, buff, 20);
/*      */             
/* 4246 */             Buffer packet2 = new Buffer(25);
/* 4247 */             packet2.writeBytesNoNull(buff);
/*      */             
/* 4249 */             this.packetSequence = (byte)(this.packetSequence + 1);
/*      */             
/* 4251 */             send(packet2, 24);
/* 4252 */           } catch (NoSuchAlgorithmException nse) {
/* 4253 */             throw SQLError.createSQLException(Messages.getString("MysqlIO.91") + Messages.getString("MysqlIO.92"), "S1000", getExceptionInterceptor());
/*      */           } 
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 4259 */             byte[] passwordHash = Security.createKeyFromOldPassword(password);
/*      */ 
/*      */             
/* 4262 */             byte[] netReadPos4 = new byte[replyAsBytes.length - 4];
/*      */             
/* 4264 */             System.arraycopy(replyAsBytes, 4, netReadPos4, 0, replyAsBytes.length - 4);
/*      */             
/* 4266 */             byte[] mysqlScrambleBuff = new byte[20];
/*      */ 
/*      */             
/* 4269 */             Security.xorString(netReadPos4, mysqlScrambleBuff, passwordHash, 20);
/*      */ 
/*      */             
/* 4272 */             String scrambledPassword = Util.scramble(StringUtils.toString(mysqlScrambleBuff), password);
/*      */             
/* 4274 */             Buffer packet2 = new Buffer(packLength);
/* 4275 */             packet2.writeString(scrambledPassword, "Cp1252", this.connection);
/* 4276 */             this.packetSequence = (byte)(this.packetSequence + 1);
/*      */             
/* 4278 */             send(packet2, 24);
/* 4279 */           } catch (NoSuchAlgorithmException nse) {
/* 4280 */             throw SQLError.createSQLException(Messages.getString("MysqlIO.91") + Messages.getString("MysqlIO.92"), "S1000", getExceptionInterceptor());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void secureAuth411(Buffer packet, int packLength, String user, String password, String database, boolean writeClientParams, boolean forChangeUser) throws SQLException {
/* 4303 */     String enc = getEncodingForHandshake();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4322 */     if (packet == null) {
/* 4323 */       packet = new Buffer(packLength);
/*      */     }
/*      */     
/* 4326 */     if (writeClientParams) {
/* 4327 */       if (this.use41Extensions) {
/* 4328 */         if (versionMeetsMinimum(4, 1, 1)) {
/* 4329 */           packet.writeLong(this.clientParam);
/* 4330 */           packet.writeLong(this.maxThreeBytes);
/*      */           
/* 4332 */           appendCharsetByteForHandshake(packet, enc);
/*      */ 
/*      */           
/* 4335 */           packet.writeBytesNoNull(new byte[23]);
/*      */         } else {
/* 4337 */           packet.writeLong(this.clientParam);
/* 4338 */           packet.writeLong(this.maxThreeBytes);
/*      */         } 
/*      */       } else {
/* 4341 */         packet.writeInt((int)this.clientParam);
/* 4342 */         packet.writeLongInt(this.maxThreeBytes);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 4347 */     if (user != null) {
/* 4348 */       packet.writeString(user, enc, this.connection);
/*      */     }
/*      */     
/* 4351 */     if (password.length() != 0) {
/* 4352 */       packet.writeByte((byte)20);
/*      */       
/*      */       try {
/* 4355 */         packet.writeBytesNoNull(Security.scramble411(password, this.seed, this.connection.getPasswordCharacterEncoding()));
/* 4356 */       } catch (NoSuchAlgorithmException nse) {
/* 4357 */         throw SQLError.createSQLException(Messages.getString("MysqlIO.91") + Messages.getString("MysqlIO.92"), "S1000", getExceptionInterceptor());
/*      */       }
/* 4359 */       catch (UnsupportedEncodingException e) {
/* 4360 */         throw SQLError.createSQLException(Messages.getString("MysqlIO.91") + Messages.getString("MysqlIO.92"), "S1000", getExceptionInterceptor());
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 4365 */       packet.writeByte((byte)0);
/*      */     } 
/*      */     
/* 4368 */     if (this.useConnectWithDb) {
/* 4369 */       packet.writeString(database, enc, this.connection);
/* 4370 */     } else if (forChangeUser) {
/*      */       
/* 4372 */       packet.writeByte((byte)0);
/*      */     } 
/*      */ 
/*      */     
/* 4376 */     if ((this.serverCapabilities & 0x100000) != 0) {
/* 4377 */       sendConnectionAttributes(packet, enc, this.connection);
/*      */     }
/*      */     
/* 4380 */     send(packet, packet.getPosition());
/*      */     
/* 4382 */     byte savePacketSequence = this.packetSequence = (byte)(this.packetSequence + 1);
/*      */     
/* 4384 */     Buffer reply = checkErrorPacket();
/*      */     
/* 4386 */     if (reply.isAuthMethodSwitchRequestPacket()) {
/*      */ 
/*      */ 
/*      */       
/* 4390 */       this.packetSequence = savePacketSequence = (byte)(savePacketSequence + 1);
/* 4391 */       packet.clear();
/*      */       
/* 4393 */       String seed323 = this.seed.substring(0, 8);
/* 4394 */       packet.writeString(Util.newCrypt(password, seed323, this.connection.getPasswordCharacterEncoding()));
/* 4395 */       send(packet, packet.getPosition());
/*      */ 
/*      */       
/* 4398 */       checkErrorPacket();
/*      */     } 
/*      */     
/* 4401 */     if (!this.useConnectWithDb) {
/* 4402 */       changeDatabaseTo(database);
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
/*      */   private final ResultSetRow unpackBinaryResultSetRow(Field[] fields, Buffer binaryData, int resultSetConcurrency) throws SQLException {
/* 4418 */     int numFields = fields.length;
/*      */     
/* 4420 */     byte[][] unpackedRowData = new byte[numFields][];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4426 */     int nullCount = (numFields + 9) / 8;
/* 4427 */     int nullMaskPos = binaryData.getPosition();
/* 4428 */     binaryData.setPosition(nullMaskPos + nullCount);
/* 4429 */     int bit = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4435 */     for (int i = 0; i < numFields; i++) {
/* 4436 */       if ((binaryData.readByte(nullMaskPos) & bit) != 0) {
/* 4437 */         unpackedRowData[i] = null;
/*      */       }
/* 4439 */       else if (resultSetConcurrency != 1008) {
/* 4440 */         extractNativeEncodedColumn(binaryData, fields, i, unpackedRowData);
/*      */       } else {
/* 4442 */         unpackNativeEncodedColumn(binaryData, fields, i, unpackedRowData);
/*      */       } 
/*      */ 
/*      */       
/* 4446 */       if (((bit <<= 1) & 0xFF) == 0) {
/* 4447 */         bit = 1;
/*      */         
/* 4449 */         nullMaskPos++;
/*      */       } 
/*      */     } 
/*      */     
/* 4453 */     return new ByteArrayRow(unpackedRowData, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   private final void extractNativeEncodedColumn(Buffer binaryData, Field[] fields, int columnIndex, byte[][] unpackedRowData) throws SQLException { int length;
/* 4457 */     Field curField = fields[columnIndex];
/*      */     
/* 4459 */     switch (curField.getMysqlType()) {
/*      */       case 6:
/*      */         return;
/*      */ 
/*      */       
/*      */       case 1:
/* 4465 */         (new byte[1])[0] = binaryData.readByte(); unpackedRowData[columnIndex] = new byte[1];
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*      */       case 13:
/* 4471 */         unpackedRowData[columnIndex] = binaryData.getBytes(2);
/*      */ 
/*      */       
/*      */       case 3:
/*      */       case 9:
/* 4476 */         unpackedRowData[columnIndex] = binaryData.getBytes(4);
/*      */ 
/*      */       
/*      */       case 8:
/* 4480 */         unpackedRowData[columnIndex] = binaryData.getBytes(8);
/*      */ 
/*      */       
/*      */       case 4:
/* 4484 */         unpackedRowData[columnIndex] = binaryData.getBytes(4);
/*      */ 
/*      */       
/*      */       case 5:
/* 4488 */         unpackedRowData[columnIndex] = binaryData.getBytes(8);
/*      */ 
/*      */       
/*      */       case 11:
/* 4492 */         length = (int)binaryData.readFieldLength();
/*      */         
/* 4494 */         unpackedRowData[columnIndex] = binaryData.getBytes(length);
/*      */ 
/*      */ 
/*      */       
/*      */       case 10:
/* 4499 */         length = (int)binaryData.readFieldLength();
/*      */         
/* 4501 */         unpackedRowData[columnIndex] = binaryData.getBytes(length);
/*      */ 
/*      */       
/*      */       case 7:
/*      */       case 12:
/* 4506 */         length = (int)binaryData.readFieldLength();
/*      */         
/* 4508 */         unpackedRowData[columnIndex] = binaryData.getBytes(length);
/*      */       
/*      */       case 0:
/*      */       case 15:
/*      */       case 16:
/*      */       case 245:
/*      */       case 246:
/*      */       case 249:
/*      */       case 250:
/*      */       case 251:
/*      */       case 252:
/*      */       case 253:
/*      */       case 254:
/*      */       case 255:
/* 4522 */         unpackedRowData[columnIndex] = binaryData.readLenByteArray(0);
/*      */     } 
/*      */ 
/*      */     
/* 4526 */     throw SQLError.createSQLException(Messages.getString("MysqlIO.97") + curField.getMysqlType() + Messages.getString("MysqlIO.98") + columnIndex + Messages.getString("MysqlIO.99") + fields.length + Messages.getString("MysqlIO.100"), "S1000", getExceptionInterceptor()); } private final void unpackNativeEncodedColumn(Buffer binaryData, Field[] fields, int columnIndex, byte[][] unpackedRowData) throws SQLException { byte tinyVal; short shortVal; int intVal; long longVal; float floatVal; double doubleVal;
/*      */     int length, hour, minute, seconds;
/*      */     byte[] timeAsBytes;
/*      */     int year, month, day;
/*      */     byte[] arrayOfByte1;
/*      */     int i, j, nanos, k;
/*      */     byte[] arrayOfByte2, arrayOfByte3;
/*      */     byte b;
/* 4534 */     Field curField = fields[columnIndex];
/*      */     
/* 4536 */     switch (curField.getMysqlType()) {
/*      */       case 6:
/*      */         return;
/*      */ 
/*      */       
/*      */       case 1:
/* 4542 */         tinyVal = binaryData.readByte();
/*      */         
/* 4544 */         if (!curField.isUnsigned()) {
/* 4545 */           unpackedRowData[columnIndex] = StringUtils.getBytes(String.valueOf(tinyVal));
/*      */         } else {
/* 4547 */           short unsignedTinyVal = (short)(tinyVal & 0xFF);
/*      */           
/* 4549 */           unpackedRowData[columnIndex] = StringUtils.getBytes(String.valueOf(unsignedTinyVal));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*      */       case 13:
/* 4557 */         shortVal = (short)binaryData.readInt();
/*      */         
/* 4559 */         if (!curField.isUnsigned()) {
/* 4560 */           unpackedRowData[columnIndex] = StringUtils.getBytes(String.valueOf(shortVal));
/*      */         } else {
/* 4562 */           int unsignedShortVal = shortVal & 0xFFFF;
/*      */           
/* 4564 */           unpackedRowData[columnIndex] = StringUtils.getBytes(String.valueOf(unsignedShortVal));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/*      */       case 9:
/* 4572 */         intVal = (int)binaryData.readLong();
/*      */         
/* 4574 */         if (!curField.isUnsigned()) {
/* 4575 */           unpackedRowData[columnIndex] = StringUtils.getBytes(String.valueOf(intVal));
/*      */         } else {
/* 4577 */           long l = intVal & 0xFFFFFFFFL;
/*      */           
/* 4579 */           unpackedRowData[columnIndex] = StringUtils.getBytes(String.valueOf(l));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 8:
/* 4586 */         longVal = binaryData.readLongLong();
/*      */         
/* 4588 */         if (!curField.isUnsigned()) {
/* 4589 */           unpackedRowData[columnIndex] = StringUtils.getBytes(String.valueOf(longVal));
/*      */         } else {
/* 4591 */           BigInteger asBigInteger = ResultSetImpl.convertLongToUlong(longVal);
/*      */           
/* 4593 */           unpackedRowData[columnIndex] = StringUtils.getBytes(asBigInteger.toString());
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 4:
/* 4600 */         floatVal = Float.intBitsToFloat(binaryData.readIntAsLong());
/*      */         
/* 4602 */         unpackedRowData[columnIndex] = StringUtils.getBytes(String.valueOf(floatVal));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 5:
/* 4608 */         doubleVal = Double.longBitsToDouble(binaryData.readLongLong());
/*      */         
/* 4610 */         unpackedRowData[columnIndex] = StringUtils.getBytes(String.valueOf(doubleVal));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 11:
/* 4616 */         length = (int)binaryData.readFieldLength();
/*      */         
/* 4618 */         hour = 0;
/* 4619 */         minute = 0;
/* 4620 */         seconds = 0;
/*      */         
/* 4622 */         if (length != 0) {
/* 4623 */           binaryData.readByte();
/* 4624 */           binaryData.readLong();
/* 4625 */           hour = binaryData.readByte();
/* 4626 */           minute = binaryData.readByte();
/* 4627 */           seconds = binaryData.readByte();
/*      */           
/* 4629 */           if (length > 8) {
/* 4630 */             binaryData.readLong();
/*      */           }
/*      */         } 
/*      */         
/* 4634 */         timeAsBytes = new byte[8];
/*      */         
/* 4636 */         timeAsBytes[0] = (byte)Character.forDigit(hour / 10, 10);
/* 4637 */         timeAsBytes[1] = (byte)Character.forDigit(hour % 10, 10);
/*      */         
/* 4639 */         timeAsBytes[2] = 58;
/*      */         
/* 4641 */         timeAsBytes[3] = (byte)Character.forDigit(minute / 10, 10);
/* 4642 */         timeAsBytes[4] = (byte)Character.forDigit(minute % 10, 10);
/*      */         
/* 4644 */         timeAsBytes[5] = 58;
/*      */         
/* 4646 */         timeAsBytes[6] = (byte)Character.forDigit(seconds / 10, 10);
/* 4647 */         timeAsBytes[7] = (byte)Character.forDigit(seconds % 10, 10);
/*      */         
/* 4649 */         unpackedRowData[columnIndex] = timeAsBytes;
/*      */ 
/*      */ 
/*      */       
/*      */       case 10:
/* 4654 */         length = (int)binaryData.readFieldLength();
/*      */         
/* 4656 */         year = 0;
/* 4657 */         month = 0;
/* 4658 */         day = 0;
/*      */         
/* 4660 */         hour = 0;
/* 4661 */         minute = 0;
/* 4662 */         seconds = 0;
/*      */         
/* 4664 */         if (length != 0) {
/* 4665 */           year = binaryData.readInt();
/* 4666 */           month = binaryData.readByte();
/* 4667 */           day = binaryData.readByte();
/*      */         } 
/*      */         
/* 4670 */         if (year == 0 && month == 0 && day == 0)
/* 4671 */           if ("convertToNull".equals(this.connection.getZeroDateTimeBehavior()))
/* 4672 */           { unpackedRowData[columnIndex] = null; }
/*      */           else
/*      */           
/* 4675 */           { if ("exception".equals(this.connection.getZeroDateTimeBehavior())) {
/* 4676 */               throw SQLError.createSQLException("Value '0000-00-00' can not be represented as java.sql.Date", "S1009", getExceptionInterceptor());
/*      */             }
/*      */ 
/*      */             
/* 4680 */             year = 1;
/* 4681 */             month = 1;
/* 4682 */             day = 1;
/*      */ 
/*      */             
/* 4685 */             byte[] dateAsBytes = new byte[10];
/*      */             
/* 4687 */             dateAsBytes[0] = (byte)Character.forDigit(year / 1000, 10);
/*      */             
/* 4689 */             int after1000 = year % 1000;
/*      */             
/* 4691 */             dateAsBytes[1] = (byte)Character.forDigit(after1000 / 100, 10);
/*      */             
/* 4693 */             int after100 = after1000 % 100;
/*      */             
/* 4695 */             dateAsBytes[2] = (byte)Character.forDigit(after100 / 10, 10);
/* 4696 */             dateAsBytes[3] = (byte)Character.forDigit(after100 % 10, 10);
/*      */             
/* 4698 */             dateAsBytes[4] = 45;
/*      */             
/* 4700 */             dateAsBytes[5] = (byte)Character.forDigit(month / 10, 10);
/* 4701 */             dateAsBytes[6] = (byte)Character.forDigit(month % 10, 10);
/*      */             
/* 4703 */             dateAsBytes[7] = 45;
/*      */             
/* 4705 */             dateAsBytes[8] = (byte)Character.forDigit(day / 10, 10);
/* 4706 */             dateAsBytes[9] = (byte)Character.forDigit(day % 10, 10);
/*      */             
/* 4708 */             unpackedRowData[columnIndex] = dateAsBytes; }   arrayOfByte1 = new byte[10]; arrayOfByte1[0] = (byte)Character.forDigit(year / 1000, 10); i = year % 1000; arrayOfByte1[1] = (byte)Character.forDigit(i / 100, 10); j = i % 100; arrayOfByte1[2] = (byte)Character.forDigit(j / 10, 10); arrayOfByte1[3] = (byte)Character.forDigit(j % 10, 10); arrayOfByte1[4] = 45; arrayOfByte1[5] = (byte)Character.forDigit(month / 10, 10); arrayOfByte1[6] = (byte)Character.forDigit(month % 10, 10); arrayOfByte1[7] = 45; arrayOfByte1[8] = (byte)Character.forDigit(day / 10, 10); arrayOfByte1[9] = (byte)Character.forDigit(day % 10, 10); unpackedRowData[columnIndex] = arrayOfByte1;
/*      */ 
/*      */ 
/*      */       
/*      */       case 7:
/*      */       case 12:
/* 4714 */         length = (int)binaryData.readFieldLength();
/*      */         
/* 4716 */         year = 0;
/* 4717 */         month = 0;
/* 4718 */         day = 0;
/*      */         
/* 4720 */         hour = 0;
/* 4721 */         minute = 0;
/* 4722 */         seconds = 0;
/*      */         
/* 4724 */         nanos = 0;
/*      */         
/* 4726 */         if (length != 0) {
/* 4727 */           year = binaryData.readInt();
/* 4728 */           month = binaryData.readByte();
/* 4729 */           day = binaryData.readByte();
/*      */           
/* 4731 */           if (length > 4) {
/* 4732 */             hour = binaryData.readByte();
/* 4733 */             minute = binaryData.readByte();
/* 4734 */             seconds = binaryData.readByte();
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4742 */         if (year == 0 && month == 0 && day == 0)
/* 4743 */           if ("convertToNull".equals(this.connection.getZeroDateTimeBehavior()))
/* 4744 */           { unpackedRowData[columnIndex] = null; }
/*      */           else
/*      */           
/* 4747 */           { if ("exception".equals(this.connection.getZeroDateTimeBehavior())) {
/* 4748 */               throw SQLError.createSQLException("Value '0000-00-00' can not be represented as java.sql.Timestamp", "S1009", getExceptionInterceptor());
/*      */             }
/*      */ 
/*      */             
/* 4752 */             year = 1;
/* 4753 */             month = 1;
/* 4754 */             day = 1;
/*      */ 
/*      */             
/* 4757 */             int stringLength = 19;
/*      */             
/* 4759 */             byte[] nanosAsBytes = StringUtils.getBytes(Integer.toString(nanos));
/*      */             
/* 4761 */             stringLength += 1 + nanosAsBytes.length;
/*      */             
/* 4763 */             byte[] datetimeAsBytes = new byte[stringLength];
/*      */             
/* 4765 */             datetimeAsBytes[0] = (byte)Character.forDigit(year / 1000, 10);
/*      */             
/* 4767 */             i = year % 1000;
/*      */             
/* 4769 */             datetimeAsBytes[1] = (byte)Character.forDigit(i / 100, 10);
/*      */             
/* 4771 */             j = i % 100;
/*      */             
/* 4773 */             datetimeAsBytes[2] = (byte)Character.forDigit(j / 10, 10);
/* 4774 */             datetimeAsBytes[3] = (byte)Character.forDigit(j % 10, 10);
/*      */             
/* 4776 */             datetimeAsBytes[4] = 45;
/*      */             
/* 4778 */             datetimeAsBytes[5] = (byte)Character.forDigit(month / 10, 10);
/* 4779 */             datetimeAsBytes[6] = (byte)Character.forDigit(month % 10, 10);
/*      */             
/* 4781 */             datetimeAsBytes[7] = 45;
/*      */             
/* 4783 */             datetimeAsBytes[8] = (byte)Character.forDigit(day / 10, 10);
/* 4784 */             datetimeAsBytes[9] = (byte)Character.forDigit(day % 10, 10);
/*      */             
/* 4786 */             datetimeAsBytes[10] = 32;
/*      */             
/* 4788 */             datetimeAsBytes[11] = (byte)Character.forDigit(hour / 10, 10);
/* 4789 */             datetimeAsBytes[12] = (byte)Character.forDigit(hour % 10, 10);
/*      */             
/* 4791 */             datetimeAsBytes[13] = 58;
/*      */             
/* 4793 */             datetimeAsBytes[14] = (byte)Character.forDigit(minute / 10, 10);
/* 4794 */             datetimeAsBytes[15] = (byte)Character.forDigit(minute % 10, 10);
/*      */             
/* 4796 */             datetimeAsBytes[16] = 58;
/*      */             
/* 4798 */             datetimeAsBytes[17] = (byte)Character.forDigit(seconds / 10, 10);
/* 4799 */             datetimeAsBytes[18] = (byte)Character.forDigit(seconds % 10, 10);
/*      */             
/* 4801 */             datetimeAsBytes[19] = 46;
/*      */             
/* 4803 */             int nanosOffset = 20;
/*      */             
/* 4805 */             System.arraycopy(nanosAsBytes, 0, datetimeAsBytes, 20, nanosAsBytes.length);
/*      */             
/* 4807 */             unpackedRowData[columnIndex] = datetimeAsBytes; }   k = 19; arrayOfByte2 = StringUtils.getBytes(Integer.toString(nanos)); k += 1 + arrayOfByte2.length; arrayOfByte3 = new byte[k]; arrayOfByte3[0] = (byte)Character.forDigit(year / 1000, 10); i = year % 1000; arrayOfByte3[1] = (byte)Character.forDigit(i / 100, 10); j = i % 100; arrayOfByte3[2] = (byte)Character.forDigit(j / 10, 10); arrayOfByte3[3] = (byte)Character.forDigit(j % 10, 10); arrayOfByte3[4] = 45; arrayOfByte3[5] = (byte)Character.forDigit(month / 10, 10); arrayOfByte3[6] = (byte)Character.forDigit(month % 10, 10); arrayOfByte3[7] = 45; arrayOfByte3[8] = (byte)Character.forDigit(day / 10, 10); arrayOfByte3[9] = (byte)Character.forDigit(day % 10, 10); arrayOfByte3[10] = 32; arrayOfByte3[11] = (byte)Character.forDigit(hour / 10, 10); arrayOfByte3[12] = (byte)Character.forDigit(hour % 10, 10); arrayOfByte3[13] = 58; arrayOfByte3[14] = (byte)Character.forDigit(minute / 10, 10); arrayOfByte3[15] = (byte)Character.forDigit(minute % 10, 10); arrayOfByte3[16] = 58; arrayOfByte3[17] = (byte)Character.forDigit(seconds / 10, 10); arrayOfByte3[18] = (byte)Character.forDigit(seconds % 10, 10); arrayOfByte3[19] = 46; b = 20; System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 20, arrayOfByte2.length); unpackedRowData[columnIndex] = arrayOfByte3;
/*      */ 
/*      */ 
/*      */       
/*      */       case 0:
/*      */       case 15:
/*      */       case 16:
/*      */       case 245:
/*      */       case 246:
/*      */       case 249:
/*      */       case 250:
/*      */       case 251:
/*      */       case 252:
/*      */       case 253:
/*      */       case 254:
/* 4822 */         unpackedRowData[columnIndex] = binaryData.readLenByteArray(0);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4827 */     throw SQLError.createSQLException(Messages.getString("MysqlIO.97") + curField.getMysqlType() + Messages.getString("MysqlIO.98") + columnIndex + Messages.getString("MysqlIO.99") + fields.length + Messages.getString("MysqlIO.100"), "S1000", getExceptionInterceptor()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void negotiateSSLConnection(String user, String password, String database, int packLength) throws SQLException {
/* 4846 */     if (!ExportControlled.enabled()) {
/* 4847 */       throw new ConnectionFeatureNotAvailableException(this.connection, this.lastPacketSentTimeMs, null);
/*      */     }
/*      */     
/* 4850 */     if ((this.serverCapabilities & 0x8000) != 0) {
/* 4851 */       this.clientParam |= 0x8000L;
/*      */     }
/*      */     
/* 4854 */     this.clientParam |= 0x800L;
/*      */     
/* 4856 */     Buffer packet = new Buffer(packLength);
/*      */     
/* 4858 */     if (this.use41Extensions) {
/* 4859 */       packet.writeLong(this.clientParam);
/* 4860 */       packet.writeLong(this.maxThreeBytes);
/* 4861 */       appendCharsetByteForHandshake(packet, getEncodingForHandshake());
/* 4862 */       packet.writeBytesNoNull(new byte[23]);
/*      */     } else {
/* 4864 */       packet.writeInt((int)this.clientParam);
/*      */     } 
/*      */     
/* 4867 */     send(packet, packet.getPosition());
/*      */     
/* 4869 */     ExportControlled.transformSocketToSSLSocket(this);
/*      */   }
/*      */   
/*      */   public boolean isSSLEstablished() {
/* 4873 */     return (ExportControlled.enabled() && ExportControlled.isSSLEstablished(this.mysqlConnection));
/*      */   }
/*      */   
/*      */   protected int getServerStatus() {
/* 4877 */     return this.serverStatus;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<ResultSetRow> fetchRowsViaCursor(List<ResultSetRow> fetchedRows, long statementId, Field[] columnTypes, int fetchSize, boolean useBufferRowExplicit) throws SQLException {
/* 4883 */     if (fetchedRows == null) {
/* 4884 */       fetchedRows = new ArrayList<ResultSetRow>(fetchSize);
/*      */     } else {
/* 4886 */       fetchedRows.clear();
/*      */     } 
/*      */     
/* 4889 */     this.sharedSendPacket.clear();
/*      */     
/* 4891 */     this.sharedSendPacket.writeByte((byte)28);
/* 4892 */     this.sharedSendPacket.writeLong(statementId);
/* 4893 */     this.sharedSendPacket.writeLong(fetchSize);
/*      */     
/* 4895 */     sendCommand(28, null, this.sharedSendPacket, true, null, 0);
/*      */     
/* 4897 */     ResultSetRow row = null;
/*      */     
/* 4899 */     while ((row = nextRow(columnTypes, columnTypes.length, true, 1007, false, useBufferRowExplicit, false, null)) != null) {
/* 4900 */       fetchedRows.add(row);
/*      */     }
/*      */     
/* 4903 */     return fetchedRows;
/*      */   }
/*      */   
/*      */   protected long getThreadId() {
/* 4907 */     return this.threadId;
/*      */   }
/*      */   
/*      */   protected boolean useNanosForElapsedTime() {
/* 4911 */     return this.useNanosForElapsedTime;
/*      */   }
/*      */   
/*      */   protected long getSlowQueryThreshold() {
/* 4915 */     return this.slowQueryThreshold;
/*      */   }
/*      */   
/*      */   public String getQueryTimingUnits() {
/* 4919 */     return this.queryTimingUnits;
/*      */   }
/*      */   
/*      */   protected int getCommandCount() {
/* 4923 */     return this.commandCount;
/*      */   }
/*      */   
/*      */   private void checkTransactionState(int oldStatus) throws SQLException {
/* 4927 */     boolean previouslyInTrans = ((oldStatus & 0x1) != 0);
/* 4928 */     boolean currentlyInTrans = inTransactionOnServer();
/*      */     
/* 4930 */     if (previouslyInTrans && !currentlyInTrans) {
/* 4931 */       this.connection.transactionCompleted();
/* 4932 */     } else if (!previouslyInTrans && currentlyInTrans) {
/* 4933 */       this.connection.transactionBegun();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void preserveOldTransactionState() {
/* 4938 */     this.serverStatus |= this.oldServerStatus & 0x1;
/*      */   }
/*      */   
/*      */   protected void setStatementInterceptors(List<StatementInterceptorV2> statementInterceptors) {
/* 4942 */     this.statementInterceptors = statementInterceptors.isEmpty() ? null : statementInterceptors;
/*      */   }
/*      */   
/*      */   protected ExceptionInterceptor getExceptionInterceptor() {
/* 4946 */     return this.exceptionInterceptor;
/*      */   }
/*      */   
/*      */   protected void setSocketTimeout(int milliseconds) throws SQLException {
/*      */     try {
/* 4951 */       if (this.mysqlConnection != null) {
/* 4952 */         this.mysqlConnection.setSoTimeout(milliseconds);
/*      */       }
/* 4954 */     } catch (SocketException e) {
/* 4955 */       SQLException sqlEx = SQLError.createSQLException("Invalid socket timeout value or state", "S1009", getExceptionInterceptor());
/*      */       
/* 4957 */       sqlEx.initCause(e);
/*      */       
/* 4959 */       throw sqlEx;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void releaseResources() {
/* 4964 */     if (this.deflater != null) {
/* 4965 */       this.deflater.end();
/* 4966 */       this.deflater = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getEncodingForHandshake() {
/* 4975 */     String enc = this.connection.getEncoding();
/* 4976 */     if (enc == null) {
/* 4977 */       enc = "UTF-8";
/*      */     }
/* 4979 */     return enc;
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
/*      */   private void appendCharsetByteForHandshake(Buffer packet, String enc) throws SQLException {
/* 4999 */     int charsetIndex = 0;
/* 5000 */     if (enc != null) {
/* 5001 */       charsetIndex = CharsetMapping.getCollationIndexForJavaEncoding(enc, this.connection);
/*      */     }
/* 5003 */     if (charsetIndex == 0) {
/* 5004 */       charsetIndex = 33;
/*      */     }
/* 5006 */     if (charsetIndex > 255) {
/* 5007 */       throw SQLError.createSQLException("Invalid character set index for encoding: " + enc, "S1009", getExceptionInterceptor());
/*      */     }
/*      */     
/* 5010 */     packet.writeByte((byte)charsetIndex);
/*      */   }
/*      */   
/*      */   public boolean isEOFDeprecated() {
/* 5014 */     return ((this.clientParam & 0x1000000L) != 0L);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\MysqlIO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */