/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import com.mysql.jdbc.log.LogUtils;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.StringReader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Date;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Statement;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.TimeZone;
/*      */ import java.util.TreeMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ResultSetImpl
/*      */   implements ResultSetInternalMethods
/*      */ {
/*      */   private static final Constructor<?> JDBC_4_RS_4_ARG_CTOR;
/*      */   private static final Constructor<?> JDBC_4_RS_5_ARG_CTOR;
/*      */   private static final Constructor<?> JDBC_4_UPD_RS_5_ARG_CTOR;
/*      */   
/*      */   static {
/*  105 */     if (Util.isJdbc4()) {
/*      */       try {
/*  107 */         String jdbc4ClassName = Util.isJdbc42() ? "com.mysql.jdbc.JDBC42ResultSet" : "com.mysql.jdbc.JDBC4ResultSet";
/*  108 */         JDBC_4_RS_4_ARG_CTOR = Class.forName(jdbc4ClassName).getConstructor(new Class[] { long.class, long.class, MySQLConnection.class, StatementImpl.class });
/*      */         
/*  110 */         JDBC_4_RS_5_ARG_CTOR = Class.forName(jdbc4ClassName).getConstructor(new Class[] { String.class, Field[].class, RowData.class, MySQLConnection.class, StatementImpl.class });
/*      */ 
/*      */         
/*  113 */         jdbc4ClassName = Util.isJdbc42() ? "com.mysql.jdbc.JDBC42UpdatableResultSet" : "com.mysql.jdbc.JDBC4UpdatableResultSet";
/*  114 */         JDBC_4_UPD_RS_5_ARG_CTOR = Class.forName(jdbc4ClassName).getConstructor(new Class[] { String.class, Field[].class, RowData.class, MySQLConnection.class, StatementImpl.class });
/*      */       }
/*  116 */       catch (SecurityException e) {
/*  117 */         throw new RuntimeException(e);
/*  118 */       } catch (NoSuchMethodException e) {
/*  119 */         throw new RuntimeException(e);
/*  120 */       } catch (ClassNotFoundException e) {
/*  121 */         throw new RuntimeException(e);
/*      */       } 
/*      */     } else {
/*  124 */       JDBC_4_RS_4_ARG_CTOR = null;
/*  125 */       JDBC_4_RS_5_ARG_CTOR = null;
/*  126 */       JDBC_4_UPD_RS_5_ARG_CTOR = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  133 */   protected static final double MIN_DIFF_PREC = Float.parseFloat(Float.toString(Float.MIN_VALUE)) - Double.parseDouble(Float.toString(Float.MIN_VALUE));
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  138 */   protected static final double MAX_DIFF_PREC = Float.parseFloat(Float.toString(Float.MAX_VALUE)) - Double.parseDouble(Float.toString(Float.MAX_VALUE));
/*      */ 
/*      */   
/*  141 */   static int resultCounter = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static BigInteger convertLongToUlong(long longVal) {
/*  147 */     byte[] asBytes = new byte[8];
/*  148 */     asBytes[7] = (byte)(int)(longVal & 0xFFL);
/*  149 */     asBytes[6] = (byte)(int)(longVal >>> 8L);
/*  150 */     asBytes[5] = (byte)(int)(longVal >>> 16L);
/*  151 */     asBytes[4] = (byte)(int)(longVal >>> 24L);
/*  152 */     asBytes[3] = (byte)(int)(longVal >>> 32L);
/*  153 */     asBytes[2] = (byte)(int)(longVal >>> 40L);
/*  154 */     asBytes[1] = (byte)(int)(longVal >>> 48L);
/*  155 */     asBytes[0] = (byte)(int)(longVal >>> 56L);
/*      */     
/*  157 */     return new BigInteger(1, asBytes);
/*      */   }
/*      */ 
/*      */   
/*  161 */   protected String catalog = null;
/*      */ 
/*      */   
/*  164 */   protected Map<String, Integer> columnLabelToIndex = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  170 */   protected Map<String, Integer> columnToIndexCache = null;
/*      */ 
/*      */   
/*  173 */   protected boolean[] columnUsed = null;
/*      */ 
/*      */   
/*      */   protected volatile MySQLConnection connection;
/*      */ 
/*      */   
/*  179 */   protected int currentRow = -1;
/*      */ 
/*      */   
/*      */   protected boolean doingUpdates = false;
/*      */   
/*  184 */   Calendar fastDefaultCal = null;
/*  185 */   Calendar fastClientCal = null;
/*      */ 
/*      */   
/*  188 */   protected int fetchDirection = 1000;
/*      */ 
/*      */   
/*  191 */   protected int fetchSize = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   protected Field[] fields;
/*      */ 
/*      */ 
/*      */   
/*      */   protected char firstCharOfQuery;
/*      */ 
/*      */ 
/*      */   
/*  203 */   protected Map<String, Integer> fullColumnNameToIndex = null;
/*      */   
/*  205 */   protected Map<String, Integer> columnNameToIndex = null;
/*      */ 
/*      */   
/*      */   protected boolean hasBuiltIndexMapping = false;
/*      */ 
/*      */   
/*      */   protected boolean isBinaryEncoded = false;
/*      */ 
/*      */   
/*      */   protected boolean isClosed = false;
/*      */ 
/*      */   
/*  217 */   protected ResultSetInternalMethods nextResultSet = null;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean onInsertRow = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected StatementImpl owningStatement;
/*      */ 
/*      */ 
/*      */   
/*      */   protected String pointOfOrigin;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean reallyResult = false;
/*      */ 
/*      */   
/*      */   protected int resultId;
/*      */ 
/*      */   
/*  239 */   protected int resultSetConcurrency = 0;
/*      */ 
/*      */   
/*  242 */   protected int resultSetType = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   protected RowData rowData;
/*      */ 
/*      */ 
/*      */   
/*  250 */   protected String serverInfo = null;
/*      */ 
/*      */   
/*      */   PreparedStatement statementUsedForFetchingRows;
/*      */   
/*  255 */   protected ResultSetRow thisRow = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long updateCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  269 */   protected long updateId = -1L;
/*      */ 
/*      */   
/*      */   private boolean useStrictFloatingPoint = false;
/*      */   
/*      */   protected boolean useUsageAdvisor = false;
/*      */   
/*  276 */   protected SQLWarning warningChain = null;
/*      */ 
/*      */   
/*      */   protected boolean wasNullFlag = false;
/*      */   
/*      */   protected Statement wrapperStatement;
/*      */   
/*      */   protected boolean retainOwningStatement;
/*      */   
/*  285 */   protected Calendar gmtCalendar = null;
/*      */   
/*      */   protected boolean useFastDateParsing = false;
/*      */   
/*      */   private boolean padCharsWithSpace = false;
/*      */   
/*      */   private boolean jdbcCompliantTruncationForReads;
/*      */   
/*      */   private boolean useFastIntParsing = true;
/*      */   
/*      */   private boolean useColumnNamesInFindColumn;
/*      */   
/*      */   private ExceptionInterceptor exceptionInterceptor;
/*  298 */   static final char[] EMPTY_SPACE = new char[255]; private boolean onValidRow; private String invalidRowReason; protected boolean useLegacyDatetimeCode; private TimeZone serverTimeZoneTz;
/*      */   
/*      */   static {
/*  301 */     for (int i = 0; i < EMPTY_SPACE.length; i++) {
/*  302 */       EMPTY_SPACE[i] = ' ';
/*      */     }
/*      */   }
/*      */   
/*      */   protected static ResultSetImpl getInstance(long updateCount, long updateID, MySQLConnection conn, StatementImpl creatorStmt) throws SQLException {
/*  307 */     if (!Util.isJdbc4()) {
/*  308 */       return new ResultSetImpl(updateCount, updateID, conn, creatorStmt);
/*      */     }
/*      */     
/*  311 */     return (ResultSetImpl)Util.handleNewInstance(JDBC_4_RS_4_ARG_CTOR, new Object[] { Long.valueOf(updateCount), Long.valueOf(updateID), conn, creatorStmt }, conn.getExceptionInterceptor());
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
/*      */   protected static ResultSetImpl getInstance(String catalog, Field[] fields, RowData tuples, MySQLConnection conn, StatementImpl creatorStmt, boolean isUpdatable) throws SQLException {
/*  325 */     if (!Util.isJdbc4()) {
/*  326 */       if (!isUpdatable) {
/*  327 */         return new ResultSetImpl(catalog, fields, tuples, conn, creatorStmt);
/*      */       }
/*      */       
/*  330 */       return new UpdatableResultSet(catalog, fields, tuples, conn, creatorStmt);
/*      */     } 
/*      */     
/*  333 */     if (!isUpdatable) {
/*  334 */       return (ResultSetImpl)Util.handleNewInstance(JDBC_4_RS_5_ARG_CTOR, new Object[] { catalog, fields, tuples, conn, creatorStmt }, conn.getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/*  338 */     return (ResultSetImpl)Util.handleNewInstance(JDBC_4_UPD_RS_5_ARG_CTOR, new Object[] { catalog, fields, tuples, conn, creatorStmt }, conn.getExceptionInterceptor());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initializeWithMetadata() throws SQLException {
/*  449 */     synchronized (checkClosed().getConnectionMutex()) {
/*  450 */       this.rowData.setMetadata(this.fields);
/*      */       
/*  452 */       this.columnToIndexCache = new HashMap<String, Integer>();
/*      */       
/*  454 */       if (this.useUsageAdvisor) {
/*  455 */         this.columnUsed = new boolean[this.fields.length];
/*  456 */         this.pointOfOrigin = LogUtils.findCallingClassAndMethod(new Throwable());
/*  457 */         this.resultId = resultCounter++;
/*      */       } 
/*      */       
/*  460 */       if (this.connection.getGatherPerformanceMetrics()) {
/*  461 */         this.connection.incrementNumberOfResultSetsCreated();
/*      */         
/*  463 */         Set<String> tableNamesSet = new HashSet<String>();
/*      */         
/*  465 */         for (int i = 0; i < this.fields.length; i++) {
/*  466 */           Field f = this.fields[i];
/*      */           
/*  468 */           String tableName = f.getOriginalTableName();
/*      */           
/*  470 */           if (tableName == null) {
/*  471 */             tableName = f.getTableName();
/*      */           }
/*      */           
/*  474 */           if (tableName != null) {
/*  475 */             if (this.connection.lowerCaseTableNames())
/*      */             {
/*  477 */               tableName = tableName.toLowerCase();
/*      */             }
/*      */             
/*  480 */             tableNamesSet.add(tableName);
/*      */           } 
/*      */         } 
/*      */         
/*  484 */         this.connection.reportNumberOfTablesAccessed(tableNamesSet.size());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private synchronized Calendar getFastDefaultCalendar() {
/*  490 */     if (this.fastDefaultCal == null) {
/*  491 */       this.fastDefaultCal = new GregorianCalendar(Locale.US);
/*  492 */       this.fastDefaultCal.setTimeZone(getDefaultTimeZone());
/*      */     } 
/*  494 */     return this.fastDefaultCal;
/*      */   }
/*      */   
/*      */   private synchronized Calendar getFastClientCalendar() {
/*  498 */     if (this.fastClientCal == null) {
/*  499 */       this.fastClientCal = new GregorianCalendar(Locale.US);
/*      */     }
/*  501 */     return this.fastClientCal;
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
/*      */   public boolean absolute(int row) throws SQLException {
/*  538 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       boolean b;
/*      */ 
/*      */       
/*  542 */       if (this.rowData.size() == 0) {
/*  543 */         b = false;
/*      */       } else {
/*  545 */         if (this.onInsertRow) {
/*  546 */           this.onInsertRow = false;
/*      */         }
/*      */         
/*  549 */         if (this.doingUpdates) {
/*  550 */           this.doingUpdates = false;
/*      */         }
/*      */         
/*  553 */         if (this.thisRow != null) {
/*  554 */           this.thisRow.closeOpenStreams();
/*      */         }
/*      */         
/*  557 */         if (row == 0) {
/*  558 */           beforeFirst();
/*  559 */           b = false;
/*  560 */         } else if (row == 1) {
/*  561 */           b = first();
/*  562 */         } else if (row == -1) {
/*  563 */           b = last();
/*  564 */         } else if (row > this.rowData.size()) {
/*  565 */           afterLast();
/*  566 */           b = false;
/*      */         }
/*  568 */         else if (row < 0) {
/*      */           
/*  570 */           int newRowPosition = this.rowData.size() + row + 1;
/*      */           
/*  572 */           if (newRowPosition <= 0) {
/*  573 */             beforeFirst();
/*  574 */             b = false;
/*      */           } else {
/*  576 */             b = absolute(newRowPosition);
/*      */           } 
/*      */         } else {
/*  579 */           row--;
/*  580 */           this.rowData.setCurrentRow(row);
/*  581 */           this.thisRow = this.rowData.getAt(row);
/*  582 */           b = true;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  587 */       setRowPositionValidity();
/*      */       
/*  589 */       return b;
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
/*      */   public void afterLast() throws SQLException {
/*  605 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/*  607 */       if (this.onInsertRow) {
/*  608 */         this.onInsertRow = false;
/*      */       }
/*      */       
/*  611 */       if (this.doingUpdates) {
/*  612 */         this.doingUpdates = false;
/*      */       }
/*      */       
/*  615 */       if (this.thisRow != null) {
/*  616 */         this.thisRow.closeOpenStreams();
/*      */       }
/*      */       
/*  619 */       if (this.rowData.size() != 0) {
/*  620 */         this.rowData.afterLast();
/*  621 */         this.thisRow = null;
/*      */       } 
/*      */       
/*  624 */       setRowPositionValidity();
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
/*      */   public void beforeFirst() throws SQLException {
/*  640 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/*  642 */       if (this.onInsertRow) {
/*  643 */         this.onInsertRow = false;
/*      */       }
/*      */       
/*  646 */       if (this.doingUpdates) {
/*  647 */         this.doingUpdates = false;
/*      */       }
/*      */       
/*  650 */       if (this.rowData.size() == 0) {
/*      */         return;
/*      */       }
/*      */       
/*  654 */       if (this.thisRow != null) {
/*  655 */         this.thisRow.closeOpenStreams();
/*      */       }
/*      */       
/*  658 */       this.rowData.beforeFirst();
/*  659 */       this.thisRow = null;
/*      */       
/*  661 */       setRowPositionValidity();
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
/*      */   public void buildIndexMapping() throws SQLException {
/*  673 */     int numFields = this.fields.length;
/*  674 */     this.columnLabelToIndex = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
/*  675 */     this.fullColumnNameToIndex = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
/*  676 */     this.columnNameToIndex = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  685 */     for (int i = numFields - 1; i >= 0; i--) {
/*  686 */       Integer index = Integer.valueOf(i);
/*  687 */       String columnName = this.fields[i].getOriginalName();
/*  688 */       String columnLabel = this.fields[i].getName();
/*  689 */       String fullColumnName = this.fields[i].getFullName();
/*      */       
/*  691 */       if (columnLabel != null) {
/*  692 */         this.columnLabelToIndex.put(columnLabel, index);
/*      */       }
/*      */       
/*  695 */       if (fullColumnName != null) {
/*  696 */         this.fullColumnNameToIndex.put(fullColumnName, index);
/*      */       }
/*      */       
/*  699 */       if (columnName != null) {
/*  700 */         this.columnNameToIndex.put(columnName, index);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  705 */     this.hasBuiltIndexMapping = true;
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
/*      */   public void cancelRowUpdates() throws SQLException {
/*  720 */     throw new NotUpdatable();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final MySQLConnection checkClosed() throws SQLException {
/*  730 */     MySQLConnection c = this.connection;
/*      */     
/*  732 */     if (c == null) {
/*  733 */       throw SQLError.createSQLException(Messages.getString("ResultSet.Operation_not_allowed_after_ResultSet_closed_144"), "S1000", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/*  737 */     return c;
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
/*      */   protected final void checkColumnBounds(int columnIndex) throws SQLException {
/*  750 */     synchronized (checkClosed().getConnectionMutex()) {
/*  751 */       if (columnIndex < 1) {
/*  752 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Column_Index_out_of_range_low", new Object[] { Integer.valueOf(columnIndex), Integer.valueOf(this.fields.length) }), "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/*  756 */       if (columnIndex > this.fields.length) {
/*  757 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Column_Index_out_of_range_high", new Object[] { Integer.valueOf(columnIndex), Integer.valueOf(this.fields.length) }), "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  763 */       if (this.useUsageAdvisor) {
/*  764 */         this.columnUsed[columnIndex - 1] = true;
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
/*      */   protected void checkRowPos() throws SQLException {
/*  777 */     checkClosed();
/*      */     
/*  779 */     if (!this.onValidRow)
/*  780 */       throw SQLError.createSQLException(this.invalidRowReason, "S1000", getExceptionInterceptor()); 
/*      */   }
/*      */   
/*      */   public ResultSetImpl(long updateCount, long updateID, MySQLConnection conn, StatementImpl creatorStmt) {
/*  784 */     this.onValidRow = false;
/*  785 */     this.invalidRowReason = null; this.updateCount = updateCount; this.updateId = updateID; this.reallyResult = false; this.fields = new Field[0]; this.connection = conn; this.owningStatement = creatorStmt; this.retainOwningStatement = false; if (this.connection != null) { this.exceptionInterceptor = this.connection.getExceptionInterceptor(); this.retainOwningStatement = this.connection.getRetainStatementAfterResultSetClose(); this.serverTimeZoneTz = this.connection.getServerTimezoneTZ(); this.padCharsWithSpace = this.connection.getPadCharsWithSpace(); this.useLegacyDatetimeCode = this.connection.getUseLegacyDatetimeCode(); this.useUsageAdvisor = this.connection.getUseUsageAdvisor(); }  } public ResultSetImpl(String catalog, Field[] fields, RowData tuples, MySQLConnection conn, StatementImpl creatorStmt) throws SQLException { this.onValidRow = false; this.invalidRowReason = null; this.connection = conn; this.retainOwningStatement = false; if (this.connection != null) { this.exceptionInterceptor = this.connection.getExceptionInterceptor(); this.useStrictFloatingPoint = this.connection.getStrictFloatingPoint(); this.useFastDateParsing = this.connection.getUseFastDateParsing(); this.retainOwningStatement = this.connection.getRetainStatementAfterResultSetClose(); this.jdbcCompliantTruncationForReads = this.connection.getJdbcCompliantTruncationForReads(); this.useFastIntParsing = this.connection.getUseFastIntParsing(); this.serverTimeZoneTz = this.connection.getServerTimezoneTZ(); this.padCharsWithSpace = this.connection.getPadCharsWithSpace(); this.useUsageAdvisor = this.connection.getUseUsageAdvisor(); }
/*      */      this.owningStatement = creatorStmt; this.catalog = catalog; this.fields = fields; this.rowData = tuples; this.updateCount = this.rowData.size(); this.reallyResult = true; if (this.rowData.size() > 0) { if (this.updateCount == 1L && this.thisRow == null) { this.rowData.close(); this.updateCount = -1L; }
/*      */        }
/*      */     else { this.thisRow = null; }
/*      */      this.rowData.setOwner(this); if (this.fields != null)
/*  790 */       initializeWithMetadata();  this.useLegacyDatetimeCode = this.connection.getUseLegacyDatetimeCode(); this.useColumnNamesInFindColumn = this.connection.getUseColumnNamesInFindColumn(); setRowPositionValidity(); } private void setRowPositionValidity() throws SQLException { if (!this.rowData.isDynamic() && this.rowData.size() == 0) {
/*  791 */       this.invalidRowReason = Messages.getString("ResultSet.Illegal_operation_on_empty_result_set");
/*  792 */       this.onValidRow = false;
/*  793 */     } else if (this.rowData.isBeforeFirst()) {
/*  794 */       this.invalidRowReason = Messages.getString("ResultSet.Before_start_of_result_set_146");
/*  795 */       this.onValidRow = false;
/*  796 */     } else if (this.rowData.isAfterLast()) {
/*  797 */       this.invalidRowReason = Messages.getString("ResultSet.After_end_of_result_set_148");
/*  798 */       this.onValidRow = false;
/*      */     } else {
/*  800 */       this.onValidRow = true;
/*  801 */       this.invalidRowReason = null;
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void clearNextResult() {
/*  810 */     this.nextResultSet = null;
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
/*  821 */     synchronized (checkClosed().getConnectionMutex()) {
/*  822 */       this.warningChain = null;
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
/*  841 */     realClose(true);
/*      */   }
/*      */   
/*      */   private int convertToZeroWithEmptyCheck() throws SQLException {
/*  845 */     if (this.connection.getEmptyStringsConvertToZero()) {
/*  846 */       return 0;
/*      */     }
/*      */     
/*  849 */     throw SQLError.createSQLException("Can't convert empty string ('') to numeric", "22018", getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String convertToZeroLiteralStringWithEmptyCheck() throws SQLException {
/*  855 */     if (this.connection.getEmptyStringsConvertToZero()) {
/*  856 */       return "0";
/*      */     }
/*      */     
/*  859 */     throw SQLError.createSQLException("Can't convert empty string ('') to numeric", "22018", getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSetInternalMethods copy() throws SQLException {
/*  867 */     synchronized (checkClosed().getConnectionMutex()) {
/*  868 */       ResultSetImpl rs = getInstance(this.catalog, this.fields, this.rowData, this.connection, this.owningStatement, false);
/*  869 */       if (this.isBinaryEncoded) {
/*  870 */         rs.setBinaryEncoded();
/*      */       }
/*      */       
/*  873 */       return rs;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void redefineFieldsForDBMD(Field[] f) {
/*  878 */     this.fields = f;
/*      */     
/*  880 */     for (int i = 0; i < this.fields.length; i++) {
/*  881 */       this.fields[i].setUseOldNameMetadata(true);
/*  882 */       this.fields[i].setConnection(this.connection);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void populateCachedMetaData(CachedResultSetMetaData cachedMetaData) throws SQLException {
/*  887 */     cachedMetaData.fields = this.fields;
/*  888 */     cachedMetaData.columnNameToIndex = this.columnLabelToIndex;
/*  889 */     cachedMetaData.fullColumnNameToIndex = this.fullColumnNameToIndex;
/*  890 */     cachedMetaData.metadata = getMetaData();
/*      */   }
/*      */   
/*      */   public void initializeFromCachedMetaData(CachedResultSetMetaData cachedMetaData) {
/*  894 */     this.fields = cachedMetaData.fields;
/*  895 */     this.columnLabelToIndex = cachedMetaData.columnNameToIndex;
/*  896 */     this.fullColumnNameToIndex = cachedMetaData.fullColumnNameToIndex;
/*  897 */     this.hasBuiltIndexMapping = true;
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
/*      */   public void deleteRow() throws SQLException {
/*  910 */     throw new NotUpdatable();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String extractStringFromNativeColumn(int columnIndex, int mysqlType) throws SQLException {
/*  920 */     int columnIndexMinusOne = columnIndex - 1;
/*      */     
/*  922 */     this.wasNullFlag = false;
/*      */     
/*  924 */     if (this.thisRow.isNull(columnIndexMinusOne)) {
/*  925 */       this.wasNullFlag = true;
/*      */       
/*  927 */       return null;
/*      */     } 
/*      */     
/*  930 */     this.wasNullFlag = false;
/*      */     
/*  932 */     String encoding = (this.fields[columnIndexMinusOne].getCollationIndex() == 63) ? this.connection.getEncoding() : this.fields[columnIndexMinusOne].getEncoding();
/*      */ 
/*      */     
/*  935 */     return this.thisRow.getString(columnIndex - 1, encoding, this.connection);
/*      */   }
/*      */   
/*      */   protected Date fastDateCreate(Calendar cal, int year, int month, int day) throws SQLException {
/*  939 */     synchronized (checkClosed().getConnectionMutex()) {
/*  940 */       Calendar targetCalendar = cal;
/*      */       
/*  942 */       if (cal == null) {
/*  943 */         if (this.connection.getNoTimezoneConversionForDateType()) {
/*  944 */           targetCalendar = getFastClientCalendar();
/*      */         } else {
/*  946 */           targetCalendar = getFastDefaultCalendar();
/*      */         } 
/*      */       }
/*      */       
/*  950 */       if (!this.useLegacyDatetimeCode) {
/*  951 */         return TimeUtil.fastDateCreate(year, month, day, targetCalendar);
/*      */       }
/*      */       
/*  954 */       boolean useGmtMillis = (cal == null && !this.connection.getNoTimezoneConversionForDateType() && this.connection.getUseGmtMillisForDatetimes());
/*      */       
/*  956 */       return TimeUtil.fastDateCreate(useGmtMillis, useGmtMillis ? getGmtCalendar() : targetCalendar, targetCalendar, year, month, day);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Time fastTimeCreate(Calendar cal, int hour, int minute, int second) throws SQLException {
/*  961 */     synchronized (checkClosed().getConnectionMutex()) {
/*  962 */       if (!this.useLegacyDatetimeCode) {
/*  963 */         return TimeUtil.fastTimeCreate(hour, minute, second, cal, getExceptionInterceptor());
/*      */       }
/*      */       
/*  966 */       if (cal == null) {
/*  967 */         cal = getFastDefaultCalendar();
/*      */       }
/*      */       
/*  970 */       return TimeUtil.fastTimeCreate(cal, hour, minute, second, getExceptionInterceptor());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected Timestamp fastTimestampCreate(Calendar cal, int year, int month, int day, int hour, int minute, int seconds, int secondsPart, boolean useGmtMillis) throws SQLException {
/*  976 */     synchronized (checkClosed().getConnectionMutex()) {
/*  977 */       if (!this.useLegacyDatetimeCode) {
/*  978 */         return TimeUtil.fastTimestampCreate(cal.getTimeZone(), year, month, day, hour, minute, seconds, secondsPart);
/*      */       }
/*      */       
/*  981 */       if (cal == null) {
/*  982 */         cal = getFastDefaultCalendar();
/*      */       }
/*      */       
/*  985 */       return TimeUtil.fastTimestampCreate(useGmtMillis, useGmtMillis ? getGmtCalendar() : null, cal, year, month, day, hour, minute, seconds, secondsPart);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int findColumn(String columnName) throws SQLException {
/* 1030 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */ 
/*      */       
/* 1033 */       if (!this.hasBuiltIndexMapping) {
/* 1034 */         buildIndexMapping();
/*      */       }
/*      */       
/* 1037 */       Integer index = this.columnToIndexCache.get(columnName);
/*      */       
/* 1039 */       if (index != null) {
/* 1040 */         return index.intValue() + 1;
/*      */       }
/*      */       
/* 1043 */       index = this.columnLabelToIndex.get(columnName);
/*      */       
/* 1045 */       if (index == null && this.useColumnNamesInFindColumn) {
/* 1046 */         index = this.columnNameToIndex.get(columnName);
/*      */       }
/*      */       
/* 1049 */       if (index == null) {
/* 1050 */         index = this.fullColumnNameToIndex.get(columnName);
/*      */       }
/*      */       
/* 1053 */       if (index != null) {
/* 1054 */         this.columnToIndexCache.put(columnName, index);
/*      */         
/* 1056 */         return index.intValue() + 1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1061 */       for (int i = 0; i < this.fields.length; i++) {
/* 1062 */         if (this.fields[i].getName().equalsIgnoreCase(columnName))
/* 1063 */           return i + 1; 
/* 1064 */         if (this.fields[i].getFullName().equalsIgnoreCase(columnName)) {
/* 1065 */           return i + 1;
/*      */         }
/*      */       } 
/*      */       
/* 1069 */       throw SQLError.createSQLException(Messages.getString("ResultSet.Column____112") + columnName + Messages.getString("ResultSet.___not_found._113"), "S0022", getExceptionInterceptor());
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
/*      */   public boolean first() throws SQLException {
/* 1088 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/* 1090 */       boolean b = true;
/*      */       
/* 1092 */       if (this.rowData.isEmpty()) {
/* 1093 */         b = false;
/*      */       } else {
/*      */         
/* 1096 */         if (this.onInsertRow) {
/* 1097 */           this.onInsertRow = false;
/*      */         }
/*      */         
/* 1100 */         if (this.doingUpdates) {
/* 1101 */           this.doingUpdates = false;
/*      */         }
/*      */         
/* 1104 */         this.rowData.beforeFirst();
/* 1105 */         this.thisRow = this.rowData.next();
/*      */       } 
/*      */       
/* 1108 */       setRowPositionValidity();
/*      */       
/* 1110 */       return b;
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
/*      */   public Array getArray(int i) throws SQLException {
/* 1127 */     checkColumnBounds(i);
/*      */     
/* 1129 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   public Array getArray(String colName) throws SQLException {
/* 1145 */     return getArray(findColumn(colName));
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
/*      */   public InputStream getAsciiStream(int columnIndex) throws SQLException {
/* 1172 */     checkRowPos();
/*      */     
/* 1174 */     if (!this.isBinaryEncoded) {
/* 1175 */       return getBinaryStream(columnIndex);
/*      */     }
/*      */     
/* 1178 */     return getNativeBinaryStream(columnIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InputStream getAsciiStream(String columnName) throws SQLException {
/* 1187 */     return getAsciiStream(findColumn(columnName));
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
/*      */   public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
/* 1204 */     if (!this.isBinaryEncoded) {
/* 1205 */       String stringVal = getString(columnIndex);
/*      */ 
/*      */       
/* 1208 */       if (stringVal != null) {
/* 1209 */         if (stringVal.length() == 0) {
/*      */           
/* 1211 */           BigDecimal val = new BigDecimal(convertToZeroLiteralStringWithEmptyCheck());
/*      */           
/* 1213 */           return val;
/*      */         } 
/*      */         
/*      */         try {
/* 1217 */           BigDecimal val = new BigDecimal(stringVal);
/*      */           
/* 1219 */           return val;
/* 1220 */         } catch (NumberFormatException ex) {
/* 1221 */           throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_BigDecimal", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009", getExceptionInterceptor());
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1227 */       return null;
/*      */     } 
/*      */     
/* 1230 */     return getNativeBigDecimal(columnIndex);
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
/*      */   @Deprecated
/*      */   public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
/* 1251 */     if (!this.isBinaryEncoded) {
/* 1252 */       String stringVal = getString(columnIndex);
/*      */ 
/*      */       
/* 1255 */       if (stringVal != null) {
/* 1256 */         BigDecimal bigDecimal; if (stringVal.length() == 0) {
/* 1257 */           bigDecimal = new BigDecimal(convertToZeroLiteralStringWithEmptyCheck());
/*      */           
/*      */           try {
/* 1260 */             return bigDecimal.setScale(scale);
/* 1261 */           } catch (ArithmeticException ex) {
/*      */             try {
/* 1263 */               return bigDecimal.setScale(scale, 4);
/* 1264 */             } catch (ArithmeticException arEx) {
/* 1265 */               throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_BigDecimal", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009", getExceptionInterceptor());
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1273 */           bigDecimal = new BigDecimal(stringVal);
/* 1274 */         } catch (NumberFormatException ex) {
/* 1275 */           if (this.fields[columnIndex - 1].getMysqlType() == 16) {
/* 1276 */             long valueAsLong = getNumericRepresentationOfSQLBitType(columnIndex);
/*      */             
/* 1278 */             bigDecimal = new BigDecimal(valueAsLong);
/*      */           } else {
/* 1280 */             throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_BigDecimal", new Object[] { Integer.valueOf(columnIndex), stringVal }), "S1009", getExceptionInterceptor());
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1287 */           return bigDecimal.setScale(scale);
/* 1288 */         } catch (ArithmeticException ex) {
/*      */           try {
/* 1290 */             return bigDecimal.setScale(scale, 4);
/* 1291 */           } catch (ArithmeticException arithEx) {
/* 1292 */             throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_BigDecimal", new Object[] { Integer.valueOf(columnIndex), stringVal }), "S1009", getExceptionInterceptor());
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1299 */       return null;
/*      */     } 
/*      */     
/* 1302 */     return getNativeBigDecimal(columnIndex, scale);
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
/*      */   public BigDecimal getBigDecimal(String columnName) throws SQLException {
/* 1318 */     return getBigDecimal(findColumn(columnName));
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
/*      */   @Deprecated
/*      */   public BigDecimal getBigDecimal(String columnName, int scale) throws SQLException {
/* 1331 */     return getBigDecimal(findColumn(columnName), scale);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final BigDecimal getBigDecimalFromString(String stringVal, int columnIndex, int scale) throws SQLException {
/* 1337 */     if (stringVal != null) {
/* 1338 */       if (stringVal.length() == 0) {
/* 1339 */         BigDecimal bdVal = new BigDecimal(convertToZeroLiteralStringWithEmptyCheck());
/*      */         
/*      */         try {
/* 1342 */           return bdVal.setScale(scale);
/* 1343 */         } catch (ArithmeticException ex) {
/*      */           try {
/* 1345 */             return bdVal.setScale(scale, 4);
/* 1346 */           } catch (ArithmeticException arEx) {
/* 1347 */             throw new SQLException(Messages.getString("ResultSet.Bad_format_for_BigDecimal", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009");
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1356 */         return (new BigDecimal(stringVal)).setScale(scale);
/* 1357 */       } catch (ArithmeticException ex) {
/*      */         try {
/* 1359 */           return (new BigDecimal(stringVal)).setScale(scale, 4);
/* 1360 */         } catch (ArithmeticException arEx) {
/* 1361 */           throw new SQLException(Messages.getString("ResultSet.Bad_format_for_BigDecimal", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009");
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1366 */       catch (NumberFormatException ex) {
/* 1367 */         if (this.fields[columnIndex - 1].getMysqlType() == 16) {
/* 1368 */           long valueAsLong = getNumericRepresentationOfSQLBitType(columnIndex);
/*      */           
/*      */           try {
/* 1371 */             return (new BigDecimal(valueAsLong)).setScale(scale);
/* 1372 */           } catch (ArithmeticException arEx1) {
/*      */             try {
/* 1374 */               return (new BigDecimal(valueAsLong)).setScale(scale, 4);
/* 1375 */             } catch (ArithmeticException arEx2) {
/* 1376 */               throw new SQLException(Messages.getString("ResultSet.Bad_format_for_BigDecimal", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009");
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1383 */         if (this.fields[columnIndex - 1].getMysqlType() == 1 && this.connection.getTinyInt1isBit() && this.fields[columnIndex - 1].getLength() == 1L)
/*      */         {
/* 1385 */           return (new BigDecimal(stringVal.equalsIgnoreCase("true") ? 1 : 0)).setScale(scale);
/*      */         }
/*      */         
/* 1388 */         throw new SQLException(Messages.getString("ResultSet.Bad_format_for_BigDecimal", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009");
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1393 */     return null;
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
/*      */   public InputStream getBinaryStream(int columnIndex) throws SQLException {
/* 1414 */     checkRowPos();
/*      */     
/* 1416 */     if (!this.isBinaryEncoded) {
/* 1417 */       checkColumnBounds(columnIndex);
/*      */       
/* 1419 */       int columnIndexMinusOne = columnIndex - 1;
/*      */       
/* 1421 */       if (this.thisRow.isNull(columnIndexMinusOne)) {
/* 1422 */         this.wasNullFlag = true;
/*      */         
/* 1424 */         return null;
/*      */       } 
/*      */       
/* 1427 */       this.wasNullFlag = false;
/*      */       
/* 1429 */       return this.thisRow.getBinaryInputStream(columnIndexMinusOne);
/*      */     } 
/*      */     
/* 1432 */     return getNativeBinaryStream(columnIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InputStream getBinaryStream(String columnName) throws SQLException {
/* 1441 */     return getBinaryStream(findColumn(columnName));
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
/*      */   public Blob getBlob(int columnIndex) throws SQLException {
/* 1456 */     if (!this.isBinaryEncoded) {
/* 1457 */       checkRowPos();
/*      */       
/* 1459 */       checkColumnBounds(columnIndex);
/*      */       
/* 1461 */       int columnIndexMinusOne = columnIndex - 1;
/*      */       
/* 1463 */       if (this.thisRow.isNull(columnIndexMinusOne)) {
/* 1464 */         this.wasNullFlag = true;
/*      */       } else {
/* 1466 */         this.wasNullFlag = false;
/*      */       } 
/*      */       
/* 1469 */       if (this.wasNullFlag) {
/* 1470 */         return null;
/*      */       }
/*      */       
/* 1473 */       if (!this.connection.getEmulateLocators()) {
/* 1474 */         return new Blob(this.thisRow.getColumnValue(columnIndexMinusOne), getExceptionInterceptor());
/*      */       }
/*      */       
/* 1477 */       return new BlobFromLocator(this, columnIndex, getExceptionInterceptor());
/*      */     } 
/*      */     
/* 1480 */     return getNativeBlob(columnIndex);
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
/*      */   public Blob getBlob(String colName) throws SQLException {
/* 1495 */     return getBlob(findColumn(colName));
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
/*      */   public boolean getBoolean(int columnIndex) throws SQLException {
/*      */     long boolVal;
/* 1511 */     checkColumnBounds(columnIndex);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1517 */     int columnIndexMinusOne = columnIndex - 1;
/*      */     
/* 1519 */     Field field = this.fields[columnIndexMinusOne];
/*      */     
/* 1521 */     if (field.getMysqlType() == 16) {
/* 1522 */       return byteArrayToBoolean(columnIndexMinusOne);
/*      */     }
/*      */     
/* 1525 */     this.wasNullFlag = false;
/*      */     
/* 1527 */     int sqlType = field.getSQLType();
/*      */     
/* 1529 */     switch (sqlType) {
/*      */       case 16:
/* 1531 */         if (field.getMysqlType() == -1) {
/* 1532 */           String str = getString(columnIndex);
/*      */           
/* 1534 */           return getBooleanFromString(str);
/*      */         } 
/*      */         
/* 1537 */         boolVal = getLong(columnIndex, false);
/*      */         
/* 1539 */         return (boolVal == -1L || boolVal > 0L);
/*      */       case -7:
/*      */       case -6:
/*      */       case -5:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/* 1550 */         boolVal = getLong(columnIndex, false);
/*      */         
/* 1552 */         return (boolVal == -1L || boolVal > 0L);
/*      */     } 
/* 1554 */     if (this.connection.getPedantic())
/*      */     {
/* 1556 */       switch (sqlType) {
/*      */         case -4:
/*      */         case -3:
/*      */         case -2:
/*      */         case 70:
/*      */         case 91:
/*      */         case 92:
/*      */         case 93:
/*      */         case 2000:
/*      */         case 2002:
/*      */         case 2003:
/*      */         case 2004:
/*      */         case 2005:
/*      */         case 2006:
/* 1570 */           throw SQLError.createSQLException("Required type conversion not allowed", "22018", getExceptionInterceptor());
/*      */       } 
/*      */ 
/*      */     
/*      */     }
/* 1575 */     if (sqlType == -2 || sqlType == -3 || sqlType == -4 || sqlType == 2004) {
/* 1576 */       return byteArrayToBoolean(columnIndexMinusOne);
/*      */     }
/*      */     
/* 1579 */     if (this.useUsageAdvisor) {
/* 1580 */       issueConversionViaParsingWarning("getBoolean()", columnIndex, this.thisRow.getColumnValue(columnIndexMinusOne), this.fields[columnIndex], new int[] { 16, 5, 1, 2, 3, 8, 4 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1585 */     String stringVal = getString(columnIndex);
/*      */     
/* 1587 */     return getBooleanFromString(stringVal);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean byteArrayToBoolean(int columnIndexMinusOne) throws SQLException {
/* 1592 */     Object value = this.thisRow.getColumnValue(columnIndexMinusOne);
/*      */     
/* 1594 */     if (value == null) {
/* 1595 */       this.wasNullFlag = true;
/*      */       
/* 1597 */       return false;
/*      */     } 
/*      */     
/* 1600 */     this.wasNullFlag = false;
/*      */     
/* 1602 */     if (((byte[])value).length == 0) {
/* 1603 */       return false;
/*      */     }
/*      */     
/* 1606 */     byte boolVal = ((byte[])value)[0];
/*      */     
/* 1608 */     if (boolVal == 49)
/* 1609 */       return true; 
/* 1610 */     if (boolVal == 48) {
/* 1611 */       return false;
/*      */     }
/*      */     
/* 1614 */     return (boolVal == -1 || boolVal > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBoolean(String columnName) throws SQLException {
/* 1623 */     return getBoolean(findColumn(columnName));
/*      */   }
/*      */   
/*      */   private final boolean getBooleanFromString(String stringVal) throws SQLException {
/* 1627 */     if (stringVal != null && stringVal.length() > 0) {
/* 1628 */       int c = Character.toLowerCase(stringVal.charAt(0));
/*      */       
/* 1630 */       return (c == 116 || c == 121 || c == 49 || stringVal.equals("-1"));
/*      */     } 
/*      */     
/* 1633 */     return false;
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
/*      */   public byte getByte(int columnIndex) throws SQLException {
/* 1648 */     if (!this.isBinaryEncoded) {
/* 1649 */       String stringVal = getString(columnIndex);
/*      */       
/* 1651 */       if (this.wasNullFlag || stringVal == null) {
/* 1652 */         return 0;
/*      */       }
/*      */       
/* 1655 */       return getByteFromString(stringVal, columnIndex);
/*      */     } 
/*      */     
/* 1658 */     return getNativeByte(columnIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getByte(String columnName) throws SQLException {
/* 1667 */     return getByte(findColumn(columnName));
/*      */   }
/*      */ 
/*      */   
/*      */   private final byte getByteFromString(String stringVal, int columnIndex) throws SQLException {
/* 1672 */     if (stringVal != null && stringVal.length() == 0) {
/* 1673 */       return (byte)convertToZeroWithEmptyCheck();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1683 */     if (stringVal == null) {
/* 1684 */       return 0;
/*      */     }
/*      */     
/* 1687 */     stringVal = stringVal.trim();
/*      */     
/*      */     try {
/* 1690 */       int decimalIndex = stringVal.indexOf(".");
/*      */       
/* 1692 */       if (decimalIndex != -1) {
/* 1693 */         double valueAsDouble = Double.parseDouble(stringVal);
/*      */         
/* 1695 */         if (this.jdbcCompliantTruncationForReads && (
/* 1696 */           valueAsDouble < -128.0D || valueAsDouble > 127.0D)) {
/* 1697 */           throwRangeException(stringVal, columnIndex, -6);
/*      */         }
/*      */ 
/*      */         
/* 1701 */         return (byte)(int)valueAsDouble;
/*      */       } 
/*      */       
/* 1704 */       long valueAsLong = Long.parseLong(stringVal);
/*      */       
/* 1706 */       if (this.jdbcCompliantTruncationForReads && (
/* 1707 */         valueAsLong < -128L || valueAsLong > 127L)) {
/* 1708 */         throwRangeException(String.valueOf(valueAsLong), columnIndex, -6);
/*      */       }
/*      */ 
/*      */       
/* 1712 */       return (byte)(int)valueAsLong;
/* 1713 */     } catch (NumberFormatException NFE) {
/* 1714 */       throw SQLError.createSQLException(Messages.getString("ResultSet.Value____173") + stringVal + Messages.getString("ResultSet.___is_out_of_range_[-127,127]_174"), "S1009", getExceptionInterceptor());
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
/*      */   public byte[] getBytes(int columnIndex) throws SQLException {
/* 1736 */     return getBytes(columnIndex, false);
/*      */   }
/*      */   
/*      */   protected byte[] getBytes(int columnIndex, boolean noConversion) throws SQLException {
/* 1740 */     if (!this.isBinaryEncoded) {
/* 1741 */       checkRowPos();
/*      */       
/* 1743 */       checkColumnBounds(columnIndex);
/*      */       
/* 1745 */       int columnIndexMinusOne = columnIndex - 1;
/*      */       
/* 1747 */       if (this.thisRow.isNull(columnIndexMinusOne)) {
/* 1748 */         this.wasNullFlag = true;
/*      */       } else {
/* 1750 */         this.wasNullFlag = false;
/*      */       } 
/*      */       
/* 1753 */       if (this.wasNullFlag) {
/* 1754 */         return null;
/*      */       }
/*      */       
/* 1757 */       return this.thisRow.getColumnValue(columnIndexMinusOne);
/*      */     } 
/*      */     
/* 1760 */     return getNativeBytes(columnIndex, noConversion);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytes(String columnName) throws SQLException {
/* 1769 */     return getBytes(findColumn(columnName));
/*      */   }
/*      */   
/*      */   private final byte[] getBytesFromString(String stringVal) throws SQLException {
/* 1773 */     if (stringVal != null) {
/* 1774 */       return StringUtils.getBytes(stringVal, this.connection.getEncoding(), this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), this.connection, getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 1778 */     return null;
/*      */   }
/*      */   
/*      */   public int getBytesSize() throws SQLException {
/* 1782 */     RowData localRowData = this.rowData;
/*      */     
/* 1784 */     checkClosed();
/*      */     
/* 1786 */     if (localRowData instanceof RowDataStatic) {
/* 1787 */       int bytesSize = 0;
/*      */       
/* 1789 */       int numRows = localRowData.size();
/*      */       
/* 1791 */       for (int i = 0; i < numRows; i++) {
/* 1792 */         bytesSize += localRowData.getAt(i).getBytesSize();
/*      */       }
/*      */       
/* 1795 */       return bytesSize;
/*      */     } 
/*      */     
/* 1798 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Calendar getCalendarInstanceForSessionOrNew() throws SQLException {
/* 1806 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1807 */       if (this.connection != null) {
/* 1808 */         return this.connection.getCalendarInstanceForSessionOrNew();
/*      */       }
/*      */ 
/*      */       
/* 1812 */       return new GregorianCalendar();
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
/*      */   public Reader getCharacterStream(int columnIndex) throws SQLException {
/* 1832 */     if (!this.isBinaryEncoded) {
/* 1833 */       checkColumnBounds(columnIndex);
/*      */       
/* 1835 */       int columnIndexMinusOne = columnIndex - 1;
/*      */       
/* 1837 */       if (this.thisRow.isNull(columnIndexMinusOne)) {
/* 1838 */         this.wasNullFlag = true;
/*      */         
/* 1840 */         return null;
/*      */       } 
/*      */       
/* 1843 */       this.wasNullFlag = false;
/*      */       
/* 1845 */       return this.thisRow.getReader(columnIndexMinusOne);
/*      */     } 
/*      */     
/* 1848 */     return getNativeCharacterStream(columnIndex);
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
/*      */   public Reader getCharacterStream(String columnName) throws SQLException {
/* 1867 */     return getCharacterStream(findColumn(columnName));
/*      */   }
/*      */   
/*      */   private final Reader getCharacterStreamFromString(String stringVal) throws SQLException {
/* 1871 */     if (stringVal != null) {
/* 1872 */       return new StringReader(stringVal);
/*      */     }
/*      */     
/* 1875 */     return null;
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
/*      */   public Clob getClob(int i) throws SQLException {
/* 1890 */     if (!this.isBinaryEncoded) {
/* 1891 */       String asString = getStringForClob(i);
/*      */       
/* 1893 */       if (asString == null) {
/* 1894 */         return null;
/*      */       }
/*      */       
/* 1897 */       return new Clob(asString, getExceptionInterceptor());
/*      */     } 
/*      */     
/* 1900 */     return getNativeClob(i);
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
/*      */   public Clob getClob(String colName) throws SQLException {
/* 1915 */     return getClob(findColumn(colName));
/*      */   }
/*      */   
/*      */   private final Clob getClobFromString(String stringVal) throws SQLException {
/* 1919 */     return new Clob(stringVal, getExceptionInterceptor());
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
/*      */   public int getConcurrency() throws SQLException {
/* 1932 */     return 1007;
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
/*      */   public String getCursorName() throws SQLException {
/* 1958 */     throw SQLError.createSQLException(Messages.getString("ResultSet.Positioned_Update_not_supported"), "S1C00", getExceptionInterceptor());
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
/*      */   public Date getDate(int columnIndex) throws SQLException {
/* 1974 */     return getDate(columnIndex, (Calendar)null);
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
/*      */   public Date getDate(int columnIndex, Calendar cal) throws SQLException {
/* 1994 */     if (this.isBinaryEncoded) {
/* 1995 */       return getNativeDate(columnIndex, cal);
/*      */     }
/*      */     
/* 1998 */     if (!this.useFastDateParsing) {
/* 1999 */       String stringVal = getStringInternal(columnIndex, false);
/*      */       
/* 2001 */       if (stringVal == null) {
/* 2002 */         return null;
/*      */       }
/*      */       
/* 2005 */       return getDateFromString(stringVal, columnIndex, cal);
/*      */     } 
/*      */     
/* 2008 */     checkColumnBounds(columnIndex);
/*      */     
/* 2010 */     int columnIndexMinusOne = columnIndex - 1;
/* 2011 */     Date tmpDate = this.thisRow.getDateFast(columnIndexMinusOne, this.connection, this, cal);
/* 2012 */     if (this.thisRow.isNull(columnIndexMinusOne) || tmpDate == null) {
/*      */       
/* 2014 */       this.wasNullFlag = true;
/*      */       
/* 2016 */       return null;
/*      */     } 
/*      */     
/* 2019 */     this.wasNullFlag = false;
/*      */     
/* 2021 */     return tmpDate;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Date getDate(String columnName) throws SQLException {
/* 2030 */     return getDate(findColumn(columnName));
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
/*      */   public Date getDate(String columnName, Calendar cal) throws SQLException {
/* 2049 */     return getDate(findColumn(columnName), cal);
/*      */   }
/*      */   
/*      */   private final Date getDateFromString(String stringVal, int columnIndex, Calendar targetCalendar) throws SQLException {
/* 2053 */     int year = 0;
/* 2054 */     int month = 0;
/* 2055 */     int day = 0;
/*      */     
/*      */     try {
/* 2058 */       this.wasNullFlag = false;
/*      */       
/* 2060 */       if (stringVal == null) {
/* 2061 */         this.wasNullFlag = true;
/*      */         
/* 2063 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2073 */       stringVal = stringVal.trim();
/*      */ 
/*      */       
/* 2076 */       int dec = stringVal.indexOf(".");
/* 2077 */       if (dec > -1) {
/* 2078 */         stringVal = stringVal.substring(0, dec);
/*      */       }
/*      */       
/* 2081 */       if (stringVal.equals("0") || stringVal.equals("0000-00-00") || stringVal.equals("0000-00-00 00:00:00") || stringVal.equals("00000000000000") || stringVal.equals("0")) {
/*      */ 
/*      */         
/* 2084 */         if ("convertToNull".equals(this.connection.getZeroDateTimeBehavior())) {
/* 2085 */           this.wasNullFlag = true;
/*      */           
/* 2087 */           return null;
/* 2088 */         }  if ("exception".equals(this.connection.getZeroDateTimeBehavior())) {
/* 2089 */           throw SQLError.createSQLException("Value '" + stringVal + "' can not be represented as java.sql.Date", "S1009", getExceptionInterceptor());
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2094 */         return fastDateCreate(targetCalendar, 1, 1, 1);
/*      */       } 
/* 2096 */       if (this.fields[columnIndex - 1].getMysqlType() == 7) {
/*      */         
/* 2098 */         switch (stringVal.length()) {
/*      */           case 19:
/*      */           case 21:
/* 2101 */             year = Integer.parseInt(stringVal.substring(0, 4));
/* 2102 */             month = Integer.parseInt(stringVal.substring(5, 7));
/* 2103 */             day = Integer.parseInt(stringVal.substring(8, 10));
/*      */             
/* 2105 */             return fastDateCreate(targetCalendar, year, month, day);
/*      */ 
/*      */           
/*      */           case 8:
/*      */           case 14:
/* 2110 */             year = Integer.parseInt(stringVal.substring(0, 4));
/* 2111 */             month = Integer.parseInt(stringVal.substring(4, 6));
/* 2112 */             day = Integer.parseInt(stringVal.substring(6, 8));
/*      */             
/* 2114 */             return fastDateCreate(targetCalendar, year, month, day);
/*      */ 
/*      */           
/*      */           case 6:
/*      */           case 10:
/*      */           case 12:
/* 2120 */             year = Integer.parseInt(stringVal.substring(0, 2));
/*      */             
/* 2122 */             if (year <= 69) {
/* 2123 */               year += 100;
/*      */             }
/*      */             
/* 2126 */             month = Integer.parseInt(stringVal.substring(2, 4));
/* 2127 */             day = Integer.parseInt(stringVal.substring(4, 6));
/*      */             
/* 2129 */             return fastDateCreate(targetCalendar, year + 1900, month, day);
/*      */ 
/*      */           
/*      */           case 4:
/* 2133 */             year = Integer.parseInt(stringVal.substring(0, 4));
/*      */             
/* 2135 */             if (year <= 69) {
/* 2136 */               year += 100;
/*      */             }
/*      */             
/* 2139 */             month = Integer.parseInt(stringVal.substring(2, 4));
/*      */             
/* 2141 */             return fastDateCreate(targetCalendar, year + 1900, month, 1);
/*      */ 
/*      */           
/*      */           case 2:
/* 2145 */             year = Integer.parseInt(stringVal.substring(0, 2));
/*      */             
/* 2147 */             if (year <= 69) {
/* 2148 */               year += 100;
/*      */             }
/*      */             
/* 2151 */             return fastDateCreate(targetCalendar, year + 1900, 1, 1);
/*      */         } 
/*      */ 
/*      */         
/* 2155 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_Date", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009", getExceptionInterceptor());
/*      */       } 
/*      */ 
/*      */       
/* 2159 */       if (this.fields[columnIndex - 1].getMysqlType() == 13) {
/*      */         
/* 2161 */         if (stringVal.length() == 2 || stringVal.length() == 1) {
/* 2162 */           year = Integer.parseInt(stringVal);
/*      */           
/* 2164 */           if (year <= 69) {
/* 2165 */             year += 100;
/*      */           }
/*      */           
/* 2168 */           year += 1900;
/*      */         } else {
/* 2170 */           year = Integer.parseInt(stringVal.substring(0, 4));
/*      */         } 
/*      */         
/* 2173 */         return fastDateCreate(targetCalendar, year, 1, 1);
/* 2174 */       }  if (this.fields[columnIndex - 1].getMysqlType() == 11) {
/* 2175 */         return fastDateCreate(targetCalendar, 1970, 1, 1);
/*      */       }
/* 2177 */       if (stringVal.length() < 10) {
/* 2178 */         if (stringVal.length() == 8) {
/* 2179 */           return fastDateCreate(targetCalendar, 1970, 1, 1);
/*      */         }
/*      */         
/* 2182 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_Date", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009", getExceptionInterceptor());
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2187 */       if (stringVal.length() != 18) {
/* 2188 */         year = Integer.parseInt(stringVal.substring(0, 4));
/* 2189 */         month = Integer.parseInt(stringVal.substring(5, 7));
/* 2190 */         day = Integer.parseInt(stringVal.substring(8, 10));
/*      */       } else {
/*      */         
/* 2193 */         StringTokenizer st = new StringTokenizer(stringVal, "- ");
/*      */         
/* 2195 */         year = Integer.parseInt(st.nextToken());
/* 2196 */         month = Integer.parseInt(st.nextToken());
/* 2197 */         day = Integer.parseInt(st.nextToken());
/*      */       } 
/*      */ 
/*      */       
/* 2201 */       return fastDateCreate(targetCalendar, year, month, day);
/* 2202 */     } catch (SQLException sqlEx) {
/* 2203 */       throw sqlEx;
/* 2204 */     } catch (Exception e) {
/* 2205 */       SQLException sqlEx = SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_Date", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009", getExceptionInterceptor());
/*      */ 
/*      */ 
/*      */       
/* 2209 */       sqlEx.initCause(e);
/*      */       
/* 2211 */       throw sqlEx;
/*      */     } 
/*      */   }
/*      */   
/*      */   private TimeZone getDefaultTimeZone() {
/* 2216 */     return this.useLegacyDatetimeCode ? this.connection.getDefaultTimeZone() : this.serverTimeZoneTz;
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
/*      */   public double getDouble(int columnIndex) throws SQLException {
/* 2231 */     if (!this.isBinaryEncoded) {
/* 2232 */       return getDoubleInternal(columnIndex);
/*      */     }
/*      */     
/* 2235 */     return getNativeDouble(columnIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDouble(String columnName) throws SQLException {
/* 2244 */     return getDouble(findColumn(columnName));
/*      */   }
/*      */   
/*      */   private final double getDoubleFromString(String stringVal, int columnIndex) throws SQLException {
/* 2248 */     return getDoubleInternal(stringVal, columnIndex);
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
/*      */   protected double getDoubleInternal(int colIndex) throws SQLException {
/* 2264 */     return getDoubleInternal(getString(colIndex), colIndex);
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
/*      */   protected double getDoubleInternal(String stringVal, int colIndex) throws SQLException {
/*      */     try {
/* 2283 */       if (stringVal == null) {
/* 2284 */         return 0.0D;
/*      */       }
/*      */       
/* 2287 */       if (stringVal.length() == 0) {
/* 2288 */         return convertToZeroWithEmptyCheck();
/*      */       }
/*      */       
/* 2291 */       double d = Double.parseDouble(stringVal);
/*      */       
/* 2293 */       if (this.useStrictFloatingPoint)
/*      */       {
/* 2295 */         if (d == 2.147483648E9D) {
/*      */           
/* 2297 */           d = 2.147483647E9D;
/* 2298 */         } else if (d == 1.0000000036275E-15D) {
/*      */           
/* 2300 */           d = 1.0E-15D;
/* 2301 */         } else if (d == 9.999999869911E14D) {
/* 2302 */           d = 9.99999999999999E14D;
/* 2303 */         } else if (d == 1.4012984643248E-45D) {
/* 2304 */           d = 1.4E-45D;
/* 2305 */         } else if (d == 1.4013E-45D) {
/* 2306 */           d = 1.4E-45D;
/* 2307 */         } else if (d == 3.4028234663853E37D) {
/* 2308 */           d = 3.4028235E37D;
/* 2309 */         } else if (d == -2.14748E9D) {
/* 2310 */           d = -2.147483648E9D;
/* 2311 */         } else if (d == 3.40282E37D) {
/* 2312 */           d = 3.4028235E37D;
/*      */         } 
/*      */       }
/*      */       
/* 2316 */       return d;
/* 2317 */     } catch (NumberFormatException e) {
/* 2318 */       if (this.fields[colIndex - 1].getMysqlType() == 16) {
/* 2319 */         long valueAsLong = getNumericRepresentationOfSQLBitType(colIndex);
/*      */         
/* 2321 */         return valueAsLong;
/*      */       } 
/*      */       
/* 2324 */       throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_number", new Object[] { stringVal, Integer.valueOf(colIndex) }), "S1009", getExceptionInterceptor());
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
/*      */   public int getFetchDirection() throws SQLException {
/* 2338 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2339 */       return this.fetchDirection;
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
/*      */   public int getFetchSize() throws SQLException {
/* 2352 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2353 */       return this.fetchSize;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char getFirstCharOfQuery() {
/*      */     try {
/* 2365 */       synchronized (checkClosed().getConnectionMutex()) {
/* 2366 */         return this.firstCharOfQuery;
/*      */       } 
/* 2368 */     } catch (SQLException e) {
/* 2369 */       throw new RuntimeException(e);
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
/*      */   public float getFloat(int columnIndex) throws SQLException {
/* 2385 */     if (!this.isBinaryEncoded) {
/* 2386 */       String val = null;
/*      */       
/* 2388 */       val = getString(columnIndex);
/*      */       
/* 2390 */       return getFloatFromString(val, columnIndex);
/*      */     } 
/*      */     
/* 2393 */     return getNativeFloat(columnIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloat(String columnName) throws SQLException {
/* 2402 */     return getFloat(findColumn(columnName));
/*      */   }
/*      */   
/*      */   private final float getFloatFromString(String val, int columnIndex) throws SQLException {
/*      */     try {
/* 2407 */       if (val != null) {
/* 2408 */         if (val.length() == 0) {
/* 2409 */           return convertToZeroWithEmptyCheck();
/*      */         }
/*      */         
/* 2412 */         float f = Float.parseFloat(val);
/*      */         
/* 2414 */         if (this.jdbcCompliantTruncationForReads && (
/* 2415 */           f == Float.MIN_VALUE || f == Float.MAX_VALUE)) {
/* 2416 */           double valAsDouble = Double.parseDouble(val);
/*      */ 
/*      */ 
/*      */           
/* 2420 */           if (valAsDouble < 1.401298464324817E-45D - MIN_DIFF_PREC || valAsDouble > 3.4028234663852886E38D - MAX_DIFF_PREC) {
/* 2421 */             throwRangeException(String.valueOf(valAsDouble), columnIndex, 6);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 2426 */         return f;
/*      */       } 
/*      */       
/* 2429 */       return 0.0F;
/* 2430 */     } catch (NumberFormatException nfe) {
/*      */       try {
/* 2432 */         Double valueAsDouble = new Double(val);
/* 2433 */         float valueAsFloat = valueAsDouble.floatValue();
/*      */         
/* 2435 */         if (this.jdbcCompliantTruncationForReads)
/*      */         {
/* 2437 */           if ((this.jdbcCompliantTruncationForReads && valueAsFloat == Float.NEGATIVE_INFINITY) || valueAsFloat == Float.POSITIVE_INFINITY) {
/* 2438 */             throwRangeException(valueAsDouble.toString(), columnIndex, 6);
/*      */           }
/*      */         }
/*      */         
/* 2442 */         return valueAsFloat;
/* 2443 */       } catch (NumberFormatException newNfe) {
/*      */ 
/*      */ 
/*      */         
/* 2447 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Invalid_value_for_getFloat()_-____200") + val + Messages.getString("ResultSet.___in_column__201") + columnIndex, "S1009", getExceptionInterceptor());
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
/*      */   public int getInt(int columnIndex) throws SQLException {
/* 2464 */     checkRowPos();
/* 2465 */     checkColumnBounds(columnIndex);
/*      */     
/* 2467 */     if (!this.isBinaryEncoded) {
/* 2468 */       int columnIndexMinusOne = columnIndex - 1;
/*      */       
/* 2470 */       if (this.thisRow.isNull(columnIndexMinusOne)) {
/* 2471 */         this.wasNullFlag = true;
/* 2472 */         return 0;
/*      */       } 
/* 2474 */       this.wasNullFlag = false;
/*      */       
/* 2476 */       if (this.fields[columnIndexMinusOne].getMysqlType() == 16) {
/* 2477 */         long valueAsLong = getNumericRepresentationOfSQLBitType(columnIndex);
/*      */         
/* 2479 */         if (this.jdbcCompliantTruncationForReads && (valueAsLong < -2147483648L || valueAsLong > 2147483647L)) {
/* 2480 */           throwRangeException(String.valueOf(valueAsLong), columnIndex, 4);
/*      */         }
/*      */         
/* 2483 */         return (int)valueAsLong;
/*      */       } 
/*      */       
/* 2486 */       if (this.useFastIntParsing) {
/* 2487 */         if (this.thisRow.length(columnIndexMinusOne) == 0L) {
/* 2488 */           return convertToZeroWithEmptyCheck();
/*      */         }
/*      */         
/* 2491 */         boolean needsFullParse = this.thisRow.isFloatingPointNumber(columnIndexMinusOne);
/*      */         
/* 2493 */         if (!needsFullParse) {
/*      */           try {
/* 2495 */             return getIntWithOverflowCheck(columnIndexMinusOne);
/* 2496 */           } catch (NumberFormatException nfe) {
/*      */             try {
/* 2498 */               return parseIntAsDouble(columnIndex, this.thisRow.getString(columnIndexMinusOne, this.fields[columnIndexMinusOne].getEncoding(), this.connection));
/*      */             }
/* 2500 */             catch (NumberFormatException newNfe) {
/*      */ 
/*      */ 
/*      */               
/* 2504 */               throw SQLError.createSQLException(Messages.getString("ResultSet.Invalid_value_for_getInt()_-____74") + this.thisRow.getString(columnIndexMinusOne, this.fields[columnIndexMinusOne].getEncoding(), this.connection) + "'", "S1009", getExceptionInterceptor());
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2512 */       String val = null;
/*      */       try {
/* 2514 */         val = getString(columnIndex);
/* 2515 */         if (val == null) {
/* 2516 */           return 0;
/*      */         }
/*      */         
/* 2519 */         if (val.length() == 0) {
/* 2520 */           return convertToZeroWithEmptyCheck();
/*      */         }
/*      */         
/* 2523 */         if (val.indexOf("e") == -1 && val.indexOf("E") == -1 && val.indexOf(".") == -1) {
/* 2524 */           int i = Integer.parseInt(val);
/*      */           
/* 2526 */           checkForIntegerTruncation(columnIndexMinusOne, null, i);
/*      */           
/* 2528 */           return i;
/*      */         } 
/*      */ 
/*      */         
/* 2532 */         int intVal = parseIntAsDouble(columnIndex, val);
/*      */         
/* 2534 */         checkForIntegerTruncation(columnIndex, null, intVal);
/*      */         
/* 2536 */         return intVal;
/*      */       }
/* 2538 */       catch (NumberFormatException nfe) {
/*      */         try {
/* 2540 */           return parseIntAsDouble(columnIndex, val);
/* 2541 */         } catch (NumberFormatException newNfe) {
/*      */ 
/*      */ 
/*      */           
/* 2545 */           throw SQLError.createSQLException(Messages.getString("ResultSet.Invalid_value_for_getInt()_-____74") + val + "'", "S1009", getExceptionInterceptor());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2550 */     return getNativeInt(columnIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(String columnName) throws SQLException {
/* 2559 */     return getInt(findColumn(columnName));
/*      */   }
/*      */   
/*      */   private final int getIntFromString(String val, int columnIndex) throws SQLException {
/*      */     try {
/* 2564 */       if (val != null) {
/*      */         
/* 2566 */         if (val.length() == 0) {
/* 2567 */           return convertToZeroWithEmptyCheck();
/*      */         }
/*      */         
/* 2570 */         if (val.indexOf("e") == -1 && val.indexOf("E") == -1 && val.indexOf(".") == -1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2578 */           val = val.trim();
/*      */           
/* 2580 */           int valueAsInt = Integer.parseInt(val);
/*      */           
/* 2582 */           if (this.jdbcCompliantTruncationForReads && (
/* 2583 */             valueAsInt == Integer.MIN_VALUE || valueAsInt == Integer.MAX_VALUE)) {
/* 2584 */             long valueAsLong = Long.parseLong(val);
/*      */             
/* 2586 */             if (valueAsLong < -2147483648L || valueAsLong > 2147483647L) {
/* 2587 */               throwRangeException(String.valueOf(valueAsLong), columnIndex, 4);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 2592 */           return valueAsInt;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2597 */         double valueAsDouble = Double.parseDouble(val);
/*      */         
/* 2599 */         if (this.jdbcCompliantTruncationForReads && (
/* 2600 */           valueAsDouble < -2.147483648E9D || valueAsDouble > 2.147483647E9D)) {
/* 2601 */           throwRangeException(String.valueOf(valueAsDouble), columnIndex, 4);
/*      */         }
/*      */ 
/*      */         
/* 2605 */         return (int)valueAsDouble;
/*      */       } 
/*      */       
/* 2608 */       return 0;
/* 2609 */     } catch (NumberFormatException nfe) {
/*      */       try {
/* 2611 */         double valueAsDouble = Double.parseDouble(val);
/*      */         
/* 2613 */         if (this.jdbcCompliantTruncationForReads && (
/* 2614 */           valueAsDouble < -2.147483648E9D || valueAsDouble > 2.147483647E9D)) {
/* 2615 */           throwRangeException(String.valueOf(valueAsDouble), columnIndex, 4);
/*      */         }
/*      */ 
/*      */         
/* 2619 */         return (int)valueAsDouble;
/* 2620 */       } catch (NumberFormatException newNfe) {
/*      */ 
/*      */ 
/*      */         
/* 2624 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Invalid_value_for_getInt()_-____206") + val + Messages.getString("ResultSet.___in_column__207") + columnIndex, "S1009", getExceptionInterceptor());
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
/*      */   public long getLong(int columnIndex) throws SQLException {
/* 2642 */     return getLong(columnIndex, true);
/*      */   }
/*      */   
/*      */   private long getLong(int columnIndex, boolean overflowCheck) throws SQLException {
/* 2646 */     checkRowPos();
/* 2647 */     checkColumnBounds(columnIndex);
/*      */     
/* 2649 */     if (!this.isBinaryEncoded) {
/* 2650 */       int columnIndexMinusOne = columnIndex - 1;
/*      */       
/* 2652 */       if (this.thisRow.isNull(columnIndexMinusOne)) {
/* 2653 */         this.wasNullFlag = true;
/* 2654 */         return 0L;
/*      */       } 
/* 2656 */       this.wasNullFlag = false;
/*      */       
/* 2658 */       if (this.fields[columnIndexMinusOne].getMysqlType() == 16) {
/* 2659 */         return getNumericRepresentationOfSQLBitType(columnIndex);
/*      */       }
/*      */       
/* 2662 */       if (this.useFastIntParsing) {
/* 2663 */         if (this.thisRow.length(columnIndexMinusOne) == 0L) {
/* 2664 */           return convertToZeroWithEmptyCheck();
/*      */         }
/*      */         
/* 2667 */         boolean needsFullParse = this.thisRow.isFloatingPointNumber(columnIndexMinusOne);
/*      */         
/* 2669 */         if (!needsFullParse) {
/*      */           try {
/* 2671 */             return getLongWithOverflowCheck(columnIndexMinusOne, overflowCheck);
/* 2672 */           } catch (NumberFormatException nfe) {
/*      */             try {
/* 2674 */               return parseLongAsDouble(columnIndexMinusOne, this.thisRow.getString(columnIndexMinusOne, this.fields[columnIndexMinusOne].getEncoding(), this.connection));
/*      */             }
/* 2676 */             catch (NumberFormatException newNfe) {
/*      */ 
/*      */ 
/*      */               
/* 2680 */               throw SQLError.createSQLException(Messages.getString("ResultSet.Invalid_value_for_getLong()_-____79") + this.thisRow.getString(columnIndexMinusOne, this.fields[columnIndexMinusOne].getEncoding(), this.connection) + "'", "S1009", getExceptionInterceptor());
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2688 */       String val = null;
/*      */       try {
/* 2690 */         val = getString(columnIndex);
/* 2691 */         if (val == null) {
/* 2692 */           return 0L;
/*      */         }
/*      */         
/* 2695 */         if (val.length() == 0) {
/* 2696 */           return convertToZeroWithEmptyCheck();
/*      */         }
/*      */         
/* 2699 */         if (val.indexOf("e") == -1 && val.indexOf("E") == -1) {
/* 2700 */           return parseLongWithOverflowCheck(columnIndexMinusOne, null, val, overflowCheck);
/*      */         }
/*      */ 
/*      */         
/* 2704 */         return parseLongAsDouble(columnIndexMinusOne, val);
/*      */       }
/* 2706 */       catch (NumberFormatException nfe) {
/*      */         try {
/* 2708 */           return parseLongAsDouble(columnIndexMinusOne, val);
/* 2709 */         } catch (NumberFormatException newNfe) {
/*      */ 
/*      */ 
/*      */           
/* 2713 */           throw SQLError.createSQLException(Messages.getString("ResultSet.Invalid_value_for_getLong()_-____79") + val + "'", "S1009", getExceptionInterceptor());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2718 */     return getNativeLong(columnIndex, overflowCheck, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLong(String columnName) throws SQLException {
/* 2727 */     return getLong(findColumn(columnName));
/*      */   }
/*      */   
/*      */   private final long getLongFromString(String val, int columnIndexZeroBased) throws SQLException {
/*      */     try {
/* 2732 */       if (val != null) {
/*      */         
/* 2734 */         if (val.length() == 0) {
/* 2735 */           return convertToZeroWithEmptyCheck();
/*      */         }
/*      */         
/* 2738 */         if (val.indexOf("e") == -1 && val.indexOf("E") == -1) {
/* 2739 */           return parseLongWithOverflowCheck(columnIndexZeroBased, null, val, true);
/*      */         }
/*      */ 
/*      */         
/* 2743 */         return parseLongAsDouble(columnIndexZeroBased, val);
/*      */       } 
/*      */       
/* 2746 */       return 0L;
/* 2747 */     } catch (NumberFormatException nfe) {
/*      */       
/*      */       try {
/* 2750 */         return parseLongAsDouble(columnIndexZeroBased, val);
/* 2751 */       } catch (NumberFormatException newNfe) {
/*      */ 
/*      */ 
/*      */         
/* 2755 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Invalid_value_for_getLong()_-____211") + val + Messages.getString("ResultSet.___in_column__212") + (columnIndexZeroBased + 1), "S1009", getExceptionInterceptor());
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
/*      */   public ResultSetMetaData getMetaData() throws SQLException {
/* 2771 */     checkClosed();
/*      */     
/* 2773 */     return new ResultSetMetaData(this.fields, this.connection.getUseOldAliasMetadataBehavior(), this.connection.getYearIsDateType(), getExceptionInterceptor());
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
/*      */   protected Array getNativeArray(int i) throws SQLException {
/* 2790 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   protected InputStream getNativeAsciiStream(int columnIndex) throws SQLException {
/* 2817 */     checkRowPos();
/*      */     
/* 2819 */     return getNativeBinaryStream(columnIndex);
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
/*      */   protected BigDecimal getNativeBigDecimal(int columnIndex) throws SQLException {
/* 2837 */     checkColumnBounds(columnIndex);
/*      */     
/* 2839 */     int scale = this.fields[columnIndex - 1].getDecimals();
/*      */     
/* 2841 */     return getNativeBigDecimal(columnIndex, scale);
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
/*      */   protected BigDecimal getNativeBigDecimal(int columnIndex, int scale) throws SQLException {
/* 2859 */     checkColumnBounds(columnIndex);
/*      */     
/* 2861 */     String stringVal = null;
/*      */     
/* 2863 */     Field f = this.fields[columnIndex - 1];
/*      */     
/* 2865 */     Object value = this.thisRow.getColumnValue(columnIndex - 1);
/*      */     
/* 2867 */     if (value == null) {
/* 2868 */       this.wasNullFlag = true;
/*      */       
/* 2870 */       return null;
/*      */     } 
/*      */     
/* 2873 */     this.wasNullFlag = false;
/*      */     
/* 2875 */     switch (f.getSQLType())
/*      */     { case 2:
/*      */       case 3:
/* 2878 */         stringVal = StringUtils.toAsciiString((byte[])value);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2884 */         return getBigDecimalFromString(stringVal, columnIndex, scale); }  stringVal = getNativeString(columnIndex); return getBigDecimalFromString(stringVal, columnIndex, scale);
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
/*      */   protected InputStream getNativeBinaryStream(int columnIndex) throws SQLException {
/* 2905 */     checkRowPos();
/*      */     
/* 2907 */     int columnIndexMinusOne = columnIndex - 1;
/*      */     
/* 2909 */     if (this.thisRow.isNull(columnIndexMinusOne)) {
/* 2910 */       this.wasNullFlag = true;
/*      */       
/* 2912 */       return null;
/*      */     } 
/*      */     
/* 2915 */     this.wasNullFlag = false;
/*      */     
/* 2917 */     switch (this.fields[columnIndexMinusOne].getSQLType()) {
/*      */       case -7:
/*      */       case -4:
/*      */       case -3:
/*      */       case -2:
/*      */       case 2004:
/* 2923 */         return this.thisRow.getBinaryInputStream(columnIndexMinusOne);
/*      */     } 
/*      */     
/* 2926 */     byte[] b = getNativeBytes(columnIndex, false);
/*      */     
/* 2928 */     if (b != null) {
/* 2929 */       return new ByteArrayInputStream(b);
/*      */     }
/*      */     
/* 2932 */     return null;
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
/*      */   protected Blob getNativeBlob(int columnIndex) throws SQLException {
/* 2947 */     checkRowPos();
/*      */     
/* 2949 */     checkColumnBounds(columnIndex);
/*      */     
/* 2951 */     Object value = this.thisRow.getColumnValue(columnIndex - 1);
/*      */     
/* 2953 */     if (value == null) {
/* 2954 */       this.wasNullFlag = true;
/*      */     } else {
/* 2956 */       this.wasNullFlag = false;
/*      */     } 
/*      */     
/* 2959 */     if (this.wasNullFlag) {
/* 2960 */       return null;
/*      */     }
/*      */     
/* 2963 */     int mysqlType = this.fields[columnIndex - 1].getMysqlType();
/*      */     
/* 2965 */     byte[] dataAsBytes = null;
/*      */     
/* 2967 */     switch (mysqlType) {
/*      */       case 249:
/*      */       case 250:
/*      */       case 251:
/*      */       case 252:
/* 2972 */         dataAsBytes = (byte[])value;
/*      */         break;
/*      */       
/*      */       default:
/* 2976 */         dataAsBytes = getNativeBytes(columnIndex, false);
/*      */         break;
/*      */     } 
/* 2979 */     if (!this.connection.getEmulateLocators()) {
/* 2980 */       return new Blob(dataAsBytes, getExceptionInterceptor());
/*      */     }
/*      */     
/* 2983 */     return new BlobFromLocator(this, columnIndex, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public static boolean arraysEqual(byte[] left, byte[] right) {
/* 2987 */     if (left == null) {
/* 2988 */       return (right == null);
/*      */     }
/* 2990 */     if (right == null) {
/* 2991 */       return false;
/*      */     }
/* 2993 */     if (left.length != right.length) {
/* 2994 */       return false;
/*      */     }
/* 2996 */     for (int i = 0; i < left.length; i++) {
/* 2997 */       if (left[i] != right[i]) {
/* 2998 */         return false;
/*      */       }
/*      */     } 
/* 3001 */     return true;
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
/*      */   protected byte getNativeByte(int columnIndex) throws SQLException {
/* 3016 */     return getNativeByte(columnIndex, true); } protected byte getNativeByte(int columnIndex, boolean overflowCheck) throws SQLException { long valueAsLong; byte valueAsByte; short valueAsShort;
/*      */     int valueAsInt;
/*      */     float valueAsFloat;
/*      */     double valueAsDouble;
/* 3020 */     checkRowPos();
/*      */     
/* 3022 */     checkColumnBounds(columnIndex);
/*      */     
/* 3024 */     Object value = this.thisRow.getColumnValue(columnIndex - 1);
/*      */     
/* 3026 */     if (value == null) {
/* 3027 */       this.wasNullFlag = true;
/*      */       
/* 3029 */       return 0;
/*      */     } 
/*      */     
/* 3032 */     this.wasNullFlag = false;
/*      */     
/* 3034 */     columnIndex--;
/*      */     
/* 3036 */     Field field = this.fields[columnIndex];
/*      */     
/* 3038 */     switch (field.getMysqlType()) {
/*      */       case 16:
/* 3040 */         valueAsLong = getNumericRepresentationOfSQLBitType(columnIndex + 1);
/*      */         
/* 3042 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (valueAsLong < -128L || valueAsLong > 127L)) {
/* 3043 */           throwRangeException(String.valueOf(valueAsLong), columnIndex + 1, -6);
/*      */         }
/*      */         
/* 3046 */         return (byte)(int)valueAsLong;
/*      */       case 1:
/* 3048 */         valueAsByte = ((byte[])value)[0];
/*      */         
/* 3050 */         if (!field.isUnsigned()) {
/* 3051 */           return valueAsByte;
/*      */         }
/*      */         
/* 3054 */         valueAsShort = (valueAsByte >= 0) ? (short)valueAsByte : (short)(valueAsByte + 256);
/*      */         
/* 3056 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && 
/* 3057 */           valueAsShort > 127) {
/* 3058 */           throwRangeException(String.valueOf(valueAsShort), columnIndex + 1, -6);
/*      */         }
/*      */ 
/*      */         
/* 3062 */         return (byte)valueAsShort;
/*      */       
/*      */       case 2:
/*      */       case 13:
/* 3066 */         valueAsShort = getNativeShort(columnIndex + 1);
/*      */         
/* 3068 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 3069 */           valueAsShort < -128 || valueAsShort > 127)) {
/* 3070 */           throwRangeException(String.valueOf(valueAsShort), columnIndex + 1, -6);
/*      */         }
/*      */ 
/*      */         
/* 3074 */         return (byte)valueAsShort;
/*      */       case 3:
/*      */       case 9:
/* 3077 */         valueAsInt = getNativeInt(columnIndex + 1, false);
/*      */         
/* 3079 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 3080 */           valueAsInt < -128 || valueAsInt > 127)) {
/* 3081 */           throwRangeException(String.valueOf(valueAsInt), columnIndex + 1, -6);
/*      */         }
/*      */ 
/*      */         
/* 3085 */         return (byte)valueAsInt;
/*      */       
/*      */       case 4:
/* 3088 */         valueAsFloat = getNativeFloat(columnIndex + 1);
/*      */         
/* 3090 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 3091 */           valueAsFloat < -128.0F || valueAsFloat > 127.0F))
/*      */         {
/* 3093 */           throwRangeException(String.valueOf(valueAsFloat), columnIndex + 1, -6);
/*      */         }
/*      */ 
/*      */         
/* 3097 */         return (byte)(int)valueAsFloat;
/*      */       
/*      */       case 5:
/* 3100 */         valueAsDouble = getNativeDouble(columnIndex + 1);
/*      */         
/* 3102 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 3103 */           valueAsDouble < -128.0D || valueAsDouble > 127.0D)) {
/* 3104 */           throwRangeException(String.valueOf(valueAsDouble), columnIndex + 1, -6);
/*      */         }
/*      */ 
/*      */         
/* 3108 */         return (byte)(int)valueAsDouble;
/*      */       
/*      */       case 8:
/* 3111 */         valueAsLong = getNativeLong(columnIndex + 1, false, true);
/*      */         
/* 3113 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 3114 */           valueAsLong < -128L || valueAsLong > 127L)) {
/* 3115 */           throwRangeException(String.valueOf(valueAsLong), columnIndex + 1, -6);
/*      */         }
/*      */ 
/*      */         
/* 3119 */         return (byte)(int)valueAsLong;
/*      */     } 
/*      */     
/* 3122 */     if (this.useUsageAdvisor) {
/* 3123 */       issueConversionViaParsingWarning("getByte()", columnIndex, this.thisRow.getColumnValue(columnIndex - 1), this.fields[columnIndex], new int[] { 5, 1, 2, 3, 8, 4 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3128 */     return getByteFromString(getNativeString(columnIndex + 1), columnIndex + 1); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected byte[] getNativeBytes(int columnIndex, boolean noConversion) throws SQLException {
/* 3148 */     checkRowPos();
/*      */     
/* 3150 */     checkColumnBounds(columnIndex);
/*      */     
/* 3152 */     Object value = this.thisRow.getColumnValue(columnIndex - 1);
/*      */     
/* 3154 */     if (value == null) {
/* 3155 */       this.wasNullFlag = true;
/*      */     } else {
/* 3157 */       this.wasNullFlag = false;
/*      */     } 
/*      */     
/* 3160 */     if (this.wasNullFlag) {
/* 3161 */       return null;
/*      */     }
/*      */     
/* 3164 */     Field field = this.fields[columnIndex - 1];
/*      */     
/* 3166 */     int mysqlType = field.getMysqlType();
/*      */ 
/*      */     
/* 3169 */     if (noConversion) {
/* 3170 */       mysqlType = 252;
/*      */     }
/*      */     
/* 3173 */     switch (mysqlType) {
/*      */       case 16:
/*      */       case 249:
/*      */       case 250:
/*      */       case 251:
/*      */       case 252:
/* 3179 */         return (byte[])value;
/*      */       
/*      */       case 15:
/*      */       case 253:
/*      */       case 254:
/* 3184 */         if (value instanceof byte[]) {
/* 3185 */           return (byte[])value;
/*      */         }
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 3191 */     int sqlType = field.getSQLType();
/*      */     
/* 3193 */     if (sqlType == -3 || sqlType == -2) {
/* 3194 */       return (byte[])value;
/*      */     }
/*      */     
/* 3197 */     return getBytesFromString(getNativeString(columnIndex));
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
/*      */   protected Reader getNativeCharacterStream(int columnIndex) throws SQLException {
/* 3216 */     int columnIndexMinusOne = columnIndex - 1;
/*      */     
/* 3218 */     switch (this.fields[columnIndexMinusOne].getSQLType()) {
/*      */       case -1:
/*      */       case 1:
/*      */       case 12:
/*      */       case 2005:
/* 3223 */         if (this.thisRow.isNull(columnIndexMinusOne)) {
/* 3224 */           this.wasNullFlag = true;
/*      */           
/* 3226 */           return null;
/*      */         } 
/*      */         
/* 3229 */         this.wasNullFlag = false;
/*      */         
/* 3231 */         return this.thisRow.getReader(columnIndexMinusOne);
/*      */     } 
/*      */     
/* 3234 */     String asString = getStringForClob(columnIndex);
/*      */     
/* 3236 */     if (asString == null) {
/* 3237 */       return null;
/*      */     }
/*      */     
/* 3240 */     return getCharacterStreamFromString(asString);
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
/*      */   protected Clob getNativeClob(int columnIndex) throws SQLException {
/* 3255 */     String stringVal = getStringForClob(columnIndex);
/*      */     
/* 3257 */     if (stringVal == null) {
/* 3258 */       return null;
/*      */     }
/*      */     
/* 3261 */     return getClobFromString(stringVal);
/*      */   }
/*      */   
/*      */   private String getNativeConvertToString(int columnIndex, Field field) throws SQLException {
/* 3265 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       boolean booleanVal; byte tinyintVal; short unsignedTinyVal; int intVal; long longVal; float floatVal; double doubleVal; String stringVal; byte[] data; Date dt; Object obj; Time tm; Timestamp tstamp; String result;
/* 3267 */       int sqlType = field.getSQLType();
/* 3268 */       int mysqlType = field.getMysqlType();
/*      */       
/* 3270 */       switch (sqlType) {
/*      */         case -7:
/* 3272 */           return String.valueOf(getNumericRepresentationOfSQLBitType(columnIndex));
/*      */         case 16:
/* 3274 */           booleanVal = getBoolean(columnIndex);
/*      */           
/* 3276 */           if (this.wasNullFlag) {
/* 3277 */             return null;
/*      */           }
/*      */           
/* 3280 */           return String.valueOf(booleanVal);
/*      */         
/*      */         case -6:
/* 3283 */           tinyintVal = getNativeByte(columnIndex, false);
/*      */           
/* 3285 */           if (this.wasNullFlag) {
/* 3286 */             return null;
/*      */           }
/*      */           
/* 3289 */           if (!field.isUnsigned() || tinyintVal >= 0) {
/* 3290 */             return String.valueOf(tinyintVal);
/*      */           }
/*      */           
/* 3293 */           unsignedTinyVal = (short)(tinyintVal & 0xFF);
/*      */           
/* 3295 */           return String.valueOf(unsignedTinyVal);
/*      */ 
/*      */         
/*      */         case 5:
/* 3299 */           intVal = getNativeInt(columnIndex, false);
/*      */           
/* 3301 */           if (this.wasNullFlag) {
/* 3302 */             return null;
/*      */           }
/*      */           
/* 3305 */           if (!field.isUnsigned() || intVal >= 0) {
/* 3306 */             return String.valueOf(intVal);
/*      */           }
/*      */           
/* 3309 */           intVal &= 0xFFFF;
/*      */           
/* 3311 */           return String.valueOf(intVal);
/*      */         
/*      */         case 4:
/* 3314 */           intVal = getNativeInt(columnIndex, false);
/*      */           
/* 3316 */           if (this.wasNullFlag) {
/* 3317 */             return null;
/*      */           }
/*      */           
/* 3320 */           if (!field.isUnsigned() || intVal >= 0 || field.getMysqlType() == 9)
/*      */           {
/* 3322 */             return String.valueOf(intVal);
/*      */           }
/*      */           
/* 3325 */           longVal = intVal & 0xFFFFFFFFL;
/*      */           
/* 3327 */           return String.valueOf(longVal);
/*      */ 
/*      */         
/*      */         case -5:
/* 3331 */           if (!field.isUnsigned()) {
/* 3332 */             longVal = getNativeLong(columnIndex, false, true);
/*      */             
/* 3334 */             if (this.wasNullFlag) {
/* 3335 */               return null;
/*      */             }
/*      */             
/* 3338 */             return String.valueOf(longVal);
/*      */           } 
/*      */           
/* 3341 */           longVal = getNativeLong(columnIndex, false, false);
/*      */           
/* 3343 */           if (this.wasNullFlag) {
/* 3344 */             return null;
/*      */           }
/*      */           
/* 3347 */           return String.valueOf(convertLongToUlong(longVal));
/*      */         case 7:
/* 3349 */           floatVal = getNativeFloat(columnIndex);
/*      */           
/* 3351 */           if (this.wasNullFlag) {
/* 3352 */             return null;
/*      */           }
/*      */           
/* 3355 */           return String.valueOf(floatVal);
/*      */         
/*      */         case 6:
/*      */         case 8:
/* 3359 */           doubleVal = getNativeDouble(columnIndex);
/*      */           
/* 3361 */           if (this.wasNullFlag) {
/* 3362 */             return null;
/*      */           }
/*      */           
/* 3365 */           return String.valueOf(doubleVal);
/*      */         
/*      */         case 2:
/*      */         case 3:
/* 3369 */           stringVal = StringUtils.toAsciiString(this.thisRow.getColumnValue(columnIndex - 1));
/*      */ 
/*      */ 
/*      */           
/* 3373 */           if (stringVal != null) {
/* 3374 */             BigDecimal val; this.wasNullFlag = false;
/*      */             
/* 3376 */             if (stringVal.length() == 0) {
/* 3377 */               val = new BigDecimal(0);
/*      */               
/* 3379 */               return val.toString();
/*      */             } 
/*      */             
/*      */             try {
/* 3383 */               val = new BigDecimal(stringVal);
/* 3384 */             } catch (NumberFormatException ex) {
/* 3385 */               throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_BigDecimal", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009", getExceptionInterceptor());
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 3390 */             return val.toString();
/*      */           } 
/*      */           
/* 3393 */           this.wasNullFlag = true;
/*      */           
/* 3395 */           return null;
/*      */ 
/*      */         
/*      */         case -1:
/*      */         case 1:
/*      */         case 12:
/* 3401 */           return extractStringFromNativeColumn(columnIndex, mysqlType);
/*      */         
/*      */         case -4:
/*      */         case -3:
/*      */         case -2:
/* 3406 */           if (!field.isBlob())
/* 3407 */             return extractStringFromNativeColumn(columnIndex, mysqlType); 
/* 3408 */           if (!field.isBinary()) {
/* 3409 */             return extractStringFromNativeColumn(columnIndex, mysqlType);
/*      */           }
/* 3411 */           data = getBytes(columnIndex);
/* 3412 */           obj = data;
/*      */           
/* 3414 */           if (this.connection.getAutoDeserialize() && 
/* 3415 */             data != null && data.length >= 2) {
/* 3416 */             if (data[0] == -84 && data[1] == -19) {
/*      */               
/*      */               try {
/* 3419 */                 ByteArrayInputStream bytesIn = new ByteArrayInputStream(data);
/* 3420 */                 ObjectInputStream objIn = new ObjectInputStream(bytesIn);
/* 3421 */                 obj = objIn.readObject();
/* 3422 */                 objIn.close();
/* 3423 */                 bytesIn.close();
/* 3424 */               } catch (ClassNotFoundException cnfe) {
/* 3425 */                 throw SQLError.createSQLException(Messages.getString("ResultSet.Class_not_found___91") + cnfe.toString() + Messages.getString("ResultSet._while_reading_serialized_object_92"), getExceptionInterceptor());
/*      */               }
/* 3427 */               catch (IOException ex) {
/* 3428 */                 obj = data;
/*      */               } 
/*      */             }
/*      */             
/* 3432 */             return obj.toString();
/*      */           } 
/*      */ 
/*      */           
/* 3436 */           return extractStringFromNativeColumn(columnIndex, mysqlType);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 91:
/* 3442 */           if (mysqlType == 13) {
/* 3443 */             short shortVal = getNativeShort(columnIndex);
/*      */             
/* 3445 */             if (!this.connection.getYearIsDateType()) {
/*      */               
/* 3447 */               if (this.wasNullFlag) {
/* 3448 */                 return null;
/*      */               }
/*      */               
/* 3451 */               return String.valueOf(shortVal);
/*      */             } 
/*      */             
/* 3454 */             if (field.getLength() == 2L) {
/*      */               
/* 3456 */               if (shortVal <= 69) {
/* 3457 */                 shortVal = (short)(shortVal + 100);
/*      */               }
/*      */               
/* 3460 */               shortVal = (short)(shortVal + 1900);
/*      */             } 
/*      */             
/* 3463 */             return fastDateCreate(null, shortVal, 1, 1).toString();
/*      */           } 
/*      */ 
/*      */           
/* 3467 */           if (this.connection.getNoDatetimeStringSync()) {
/* 3468 */             byte[] asBytes = getNativeBytes(columnIndex, true);
/*      */             
/* 3470 */             if (asBytes == null) {
/* 3471 */               return null;
/*      */             }
/*      */             
/* 3474 */             if (asBytes.length == 0)
/*      */             {
/*      */ 
/*      */               
/* 3478 */               return "0000-00-00";
/*      */             }
/*      */             
/* 3481 */             int year = asBytes[0] & 0xFF | (asBytes[1] & 0xFF) << 8;
/* 3482 */             int month = asBytes[2];
/* 3483 */             int day = asBytes[3];
/*      */             
/* 3485 */             if (year == 0 && month == 0 && day == 0) {
/* 3486 */               return "0000-00-00";
/*      */             }
/*      */           } 
/*      */           
/* 3490 */           dt = getNativeDate(columnIndex);
/*      */           
/* 3492 */           if (dt == null) {
/* 3493 */             return null;
/*      */           }
/*      */           
/* 3496 */           return String.valueOf(dt);
/*      */         
/*      */         case 92:
/* 3499 */           tm = getNativeTime(columnIndex, null, this.connection.getDefaultTimeZone(), false);
/*      */           
/* 3501 */           if (tm == null) {
/* 3502 */             return null;
/*      */           }
/*      */           
/* 3505 */           return String.valueOf(tm);
/*      */         
/*      */         case 93:
/* 3508 */           if (this.connection.getNoDatetimeStringSync()) {
/* 3509 */             byte[] asBytes = getNativeBytes(columnIndex, true);
/*      */             
/* 3511 */             if (asBytes == null) {
/* 3512 */               return null;
/*      */             }
/*      */             
/* 3515 */             if (asBytes.length == 0)
/*      */             {
/*      */ 
/*      */               
/* 3519 */               return "0000-00-00 00:00:00";
/*      */             }
/*      */             
/* 3522 */             int year = asBytes[0] & 0xFF | (asBytes[1] & 0xFF) << 8;
/* 3523 */             int month = asBytes[2];
/* 3524 */             int day = asBytes[3];
/*      */             
/* 3526 */             if (year == 0 && month == 0 && day == 0) {
/* 3527 */               return "0000-00-00 00:00:00";
/*      */             }
/*      */           } 
/*      */           
/* 3531 */           tstamp = getNativeTimestamp(columnIndex, null, this.connection.getDefaultTimeZone(), false);
/*      */           
/* 3533 */           if (tstamp == null) {
/* 3534 */             return null;
/*      */           }
/*      */           
/* 3537 */           result = String.valueOf(tstamp);
/*      */           
/* 3539 */           if (!this.connection.getNoDatetimeStringSync()) {
/* 3540 */             return result;
/*      */           }
/*      */           
/* 3543 */           if (result.endsWith(".0")) {
/* 3544 */             return result.substring(0, result.length() - 2);
/*      */           }
/* 3546 */           return extractStringFromNativeColumn(columnIndex, mysqlType);
/*      */       } 
/*      */       
/* 3549 */       return extractStringFromNativeColumn(columnIndex, mysqlType);
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
/*      */   protected Date getNativeDate(int columnIndex) throws SQLException {
/* 3566 */     return getNativeDate(columnIndex, null);
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
/*      */   protected Date getNativeDate(int columnIndex, Calendar cal) throws SQLException {
/* 3586 */     checkRowPos();
/* 3587 */     checkColumnBounds(columnIndex);
/*      */     
/* 3589 */     int columnIndexMinusOne = columnIndex - 1;
/*      */     
/* 3591 */     int mysqlType = this.fields[columnIndexMinusOne].getMysqlType();
/*      */     
/* 3593 */     Date dateToReturn = null;
/*      */     
/* 3595 */     if (mysqlType == 10) {
/*      */       
/* 3597 */       dateToReturn = this.thisRow.getNativeDate(columnIndexMinusOne, this.connection, this, cal);
/*      */     } else {
/* 3599 */       TimeZone tz = (cal != null) ? cal.getTimeZone() : getDefaultTimeZone();
/*      */       
/* 3601 */       boolean rollForward = (tz != null && !tz.equals(getDefaultTimeZone()));
/*      */       
/* 3603 */       dateToReturn = (Date)this.thisRow.getNativeDateTimeValue(columnIndexMinusOne, null, 91, mysqlType, tz, rollForward, this.connection, this);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3611 */     if (dateToReturn == null) {
/*      */       
/* 3613 */       this.wasNullFlag = true;
/*      */       
/* 3615 */       return null;
/*      */     } 
/*      */     
/* 3618 */     this.wasNullFlag = false;
/*      */     
/* 3620 */     return dateToReturn;
/*      */   }
/*      */   
/*      */   Date getNativeDateViaParseConversion(int columnIndex) throws SQLException {
/* 3624 */     if (this.useUsageAdvisor) {
/* 3625 */       issueConversionViaParsingWarning("getDate()", columnIndex, this.thisRow.getColumnValue(columnIndex - 1), this.fields[columnIndex - 1], new int[] { 10 });
/*      */     }
/*      */ 
/*      */     
/* 3629 */     String stringVal = getNativeString(columnIndex);
/*      */     
/* 3631 */     return getDateFromString(stringVal, columnIndex, null);
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
/*      */   protected double getNativeDouble(int columnIndex) throws SQLException {
/*      */     long valueAsLong;
/*      */     BigInteger asBigInt;
/* 3646 */     checkRowPos();
/* 3647 */     checkColumnBounds(columnIndex);
/*      */     
/* 3649 */     columnIndex--;
/*      */     
/* 3651 */     if (this.thisRow.isNull(columnIndex)) {
/* 3652 */       this.wasNullFlag = true;
/*      */       
/* 3654 */       return 0.0D;
/*      */     } 
/*      */     
/* 3657 */     this.wasNullFlag = false;
/*      */     
/* 3659 */     Field f = this.fields[columnIndex];
/*      */     
/* 3661 */     switch (f.getMysqlType()) {
/*      */       case 5:
/* 3663 */         return this.thisRow.getNativeDouble(columnIndex);
/*      */       case 1:
/* 3665 */         if (!f.isUnsigned()) {
/* 3666 */           return getNativeByte(columnIndex + 1);
/*      */         }
/*      */         
/* 3669 */         return getNativeShort(columnIndex + 1);
/*      */       case 2:
/*      */       case 13:
/* 3672 */         if (!f.isUnsigned()) {
/* 3673 */           return getNativeShort(columnIndex + 1);
/*      */         }
/*      */         
/* 3676 */         return getNativeInt(columnIndex + 1);
/*      */       case 3:
/*      */       case 9:
/* 3679 */         if (!f.isUnsigned()) {
/* 3680 */           return getNativeInt(columnIndex + 1);
/*      */         }
/*      */         
/* 3683 */         return getNativeLong(columnIndex + 1);
/*      */       case 8:
/* 3685 */         valueAsLong = getNativeLong(columnIndex + 1);
/*      */         
/* 3687 */         if (!f.isUnsigned()) {
/* 3688 */           return valueAsLong;
/*      */         }
/*      */         
/* 3691 */         asBigInt = convertLongToUlong(valueAsLong);
/*      */ 
/*      */ 
/*      */         
/* 3695 */         return asBigInt.doubleValue();
/*      */       case 4:
/* 3697 */         return getNativeFloat(columnIndex + 1);
/*      */       case 16:
/* 3699 */         return getNumericRepresentationOfSQLBitType(columnIndex + 1);
/*      */     } 
/* 3701 */     String stringVal = getNativeString(columnIndex + 1);
/*      */     
/* 3703 */     if (this.useUsageAdvisor) {
/* 3704 */       issueConversionViaParsingWarning("getDouble()", columnIndex, stringVal, this.fields[columnIndex], new int[] { 5, 1, 2, 3, 8, 4 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3709 */     return getDoubleFromString(stringVal, columnIndex + 1);
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
/*      */   protected float getNativeFloat(int columnIndex) throws SQLException {
/*      */     long valueAsLong;
/*      */     Double valueAsDouble;
/*      */     float valueAsFloat;
/*      */     BigInteger asBigInt;
/* 3725 */     checkRowPos();
/* 3726 */     checkColumnBounds(columnIndex);
/*      */     
/* 3728 */     columnIndex--;
/*      */     
/* 3730 */     if (this.thisRow.isNull(columnIndex)) {
/* 3731 */       this.wasNullFlag = true;
/*      */       
/* 3733 */       return 0.0F;
/*      */     } 
/*      */     
/* 3736 */     this.wasNullFlag = false;
/*      */     
/* 3738 */     Field f = this.fields[columnIndex];
/*      */     
/* 3740 */     switch (f.getMysqlType()) {
/*      */       case 16:
/* 3742 */         valueAsLong = getNumericRepresentationOfSQLBitType(columnIndex + 1);
/*      */         
/* 3744 */         return (float)valueAsLong;
/*      */ 
/*      */ 
/*      */       
/*      */       case 5:
/* 3749 */         valueAsDouble = new Double(getNativeDouble(columnIndex + 1));
/*      */         
/* 3751 */         valueAsFloat = valueAsDouble.floatValue();
/*      */         
/* 3753 */         if ((this.jdbcCompliantTruncationForReads && valueAsFloat == Float.NEGATIVE_INFINITY) || valueAsFloat == Float.POSITIVE_INFINITY) {
/* 3754 */           throwRangeException(valueAsDouble.toString(), columnIndex + 1, 6);
/*      */         }
/*      */         
/* 3757 */         return (float)getNativeDouble(columnIndex + 1);
/*      */       case 1:
/* 3759 */         if (!f.isUnsigned()) {
/* 3760 */           return getNativeByte(columnIndex + 1);
/*      */         }
/*      */         
/* 3763 */         return getNativeShort(columnIndex + 1);
/*      */       case 2:
/*      */       case 13:
/* 3766 */         if (!f.isUnsigned()) {
/* 3767 */           return getNativeShort(columnIndex + 1);
/*      */         }
/*      */         
/* 3770 */         return getNativeInt(columnIndex + 1);
/*      */       case 3:
/*      */       case 9:
/* 3773 */         if (!f.isUnsigned()) {
/* 3774 */           return getNativeInt(columnIndex + 1);
/*      */         }
/*      */         
/* 3777 */         return (float)getNativeLong(columnIndex + 1);
/*      */       case 8:
/* 3779 */         valueAsLong = getNativeLong(columnIndex + 1);
/*      */         
/* 3781 */         if (!f.isUnsigned()) {
/* 3782 */           return (float)valueAsLong;
/*      */         }
/*      */         
/* 3785 */         asBigInt = convertLongToUlong(valueAsLong);
/*      */ 
/*      */ 
/*      */         
/* 3789 */         return asBigInt.floatValue();
/*      */       
/*      */       case 4:
/* 3792 */         return this.thisRow.getNativeFloat(columnIndex);
/*      */     } 
/*      */     
/* 3795 */     String stringVal = getNativeString(columnIndex + 1);
/*      */     
/* 3797 */     if (this.useUsageAdvisor) {
/* 3798 */       issueConversionViaParsingWarning("getFloat()", columnIndex, stringVal, this.fields[columnIndex], new int[] { 5, 1, 2, 3, 8, 4 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3803 */     return getFloatFromString(stringVal, columnIndex + 1);
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
/*      */   protected int getNativeInt(int columnIndex) throws SQLException {
/* 3819 */     return getNativeInt(columnIndex, true); } protected int getNativeInt(int columnIndex, boolean overflowCheck) throws SQLException { long valueAsLong; byte tinyintVal;
/*      */     short asShort;
/*      */     int valueAsInt;
/*      */     double valueAsDouble;
/* 3823 */     checkRowPos();
/* 3824 */     checkColumnBounds(columnIndex);
/*      */     
/* 3826 */     columnIndex--;
/*      */     
/* 3828 */     if (this.thisRow.isNull(columnIndex)) {
/* 3829 */       this.wasNullFlag = true;
/*      */       
/* 3831 */       return 0;
/*      */     } 
/*      */     
/* 3834 */     this.wasNullFlag = false;
/*      */     
/* 3836 */     Field f = this.fields[columnIndex];
/*      */     
/* 3838 */     switch (f.getMysqlType()) {
/*      */       case 16:
/* 3840 */         valueAsLong = getNumericRepresentationOfSQLBitType(columnIndex + 1);
/*      */         
/* 3842 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (valueAsLong < -2147483648L || valueAsLong > 2147483647L)) {
/* 3843 */           throwRangeException(String.valueOf(valueAsLong), columnIndex + 1, 4);
/*      */         }
/*      */         
/* 3846 */         return (int)valueAsLong;
/*      */       case 1:
/* 3848 */         tinyintVal = getNativeByte(columnIndex + 1, false);
/*      */         
/* 3850 */         if (!f.isUnsigned() || tinyintVal >= 0) {
/* 3851 */           return tinyintVal;
/*      */         }
/*      */         
/* 3854 */         return tinyintVal + 256;
/*      */       case 2:
/*      */       case 13:
/* 3857 */         asShort = getNativeShort(columnIndex + 1, false);
/*      */         
/* 3859 */         if (!f.isUnsigned() || asShort >= 0) {
/* 3860 */           return asShort;
/*      */         }
/*      */         
/* 3863 */         return asShort + 65536;
/*      */       
/*      */       case 3:
/*      */       case 9:
/* 3867 */         valueAsInt = this.thisRow.getNativeInt(columnIndex);
/*      */         
/* 3869 */         if (!f.isUnsigned()) {
/* 3870 */           return valueAsInt;
/*      */         }
/*      */         
/* 3873 */         valueAsLong = (valueAsInt >= 0) ? valueAsInt : (valueAsInt + 4294967296L);
/*      */         
/* 3875 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && valueAsLong > 2147483647L) {
/* 3876 */           throwRangeException(String.valueOf(valueAsLong), columnIndex + 1, 4);
/*      */         }
/*      */         
/* 3879 */         return (int)valueAsLong;
/*      */       case 8:
/* 3881 */         valueAsLong = getNativeLong(columnIndex + 1, false, true);
/*      */         
/* 3883 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 3884 */           valueAsLong < -2147483648L || valueAsLong > 2147483647L)) {
/* 3885 */           throwRangeException(String.valueOf(valueAsLong), columnIndex + 1, 4);
/*      */         }
/*      */ 
/*      */         
/* 3889 */         return (int)valueAsLong;
/*      */       case 5:
/* 3891 */         valueAsDouble = getNativeDouble(columnIndex + 1);
/*      */         
/* 3893 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 3894 */           valueAsDouble < -2.147483648E9D || valueAsDouble > 2.147483647E9D)) {
/* 3895 */           throwRangeException(String.valueOf(valueAsDouble), columnIndex + 1, 4);
/*      */         }
/*      */ 
/*      */         
/* 3899 */         return (int)valueAsDouble;
/*      */       case 4:
/* 3901 */         valueAsDouble = getNativeFloat(columnIndex + 1);
/*      */         
/* 3903 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 3904 */           valueAsDouble < -2.147483648E9D || valueAsDouble > 2.147483647E9D)) {
/* 3905 */           throwRangeException(String.valueOf(valueAsDouble), columnIndex + 1, 4);
/*      */         }
/*      */ 
/*      */         
/* 3909 */         return (int)valueAsDouble;
/*      */     } 
/*      */     
/* 3912 */     String stringVal = getNativeString(columnIndex + 1);
/*      */     
/* 3914 */     if (this.useUsageAdvisor) {
/* 3915 */       issueConversionViaParsingWarning("getInt()", columnIndex, stringVal, this.fields[columnIndex], new int[] { 5, 1, 2, 3, 8, 4 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3920 */     return getIntFromString(stringVal, columnIndex + 1); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long getNativeLong(int columnIndex) throws SQLException {
/* 3936 */     return getNativeLong(columnIndex, true, true); } protected long getNativeLong(int columnIndex, boolean overflowCheck, boolean expandUnsignedLong) throws SQLException { int asInt;
/*      */     long valueAsLong;
/*      */     BigInteger asBigInt;
/*      */     double valueAsDouble;
/* 3940 */     checkRowPos();
/* 3941 */     checkColumnBounds(columnIndex);
/*      */     
/* 3943 */     columnIndex--;
/*      */     
/* 3945 */     if (this.thisRow.isNull(columnIndex)) {
/* 3946 */       this.wasNullFlag = true;
/*      */       
/* 3948 */       return 0L;
/*      */     } 
/*      */     
/* 3951 */     this.wasNullFlag = false;
/*      */     
/* 3953 */     Field f = this.fields[columnIndex];
/*      */     
/* 3955 */     switch (f.getMysqlType()) {
/*      */       case 16:
/* 3957 */         return getNumericRepresentationOfSQLBitType(columnIndex + 1);
/*      */       case 1:
/* 3959 */         if (!f.isUnsigned()) {
/* 3960 */           return getNativeByte(columnIndex + 1);
/*      */         }
/*      */         
/* 3963 */         return getNativeInt(columnIndex + 1);
/*      */       case 2:
/* 3965 */         if (!f.isUnsigned()) {
/* 3966 */           return getNativeShort(columnIndex + 1);
/*      */         }
/*      */         
/* 3969 */         return getNativeInt(columnIndex + 1, false);
/*      */       
/*      */       case 13:
/* 3972 */         return getNativeShort(columnIndex + 1);
/*      */       case 3:
/*      */       case 9:
/* 3975 */         asInt = getNativeInt(columnIndex + 1, false);
/*      */         
/* 3977 */         if (!f.isUnsigned() || asInt >= 0) {
/* 3978 */           return asInt;
/*      */         }
/*      */         
/* 3981 */         return asInt + 4294967296L;
/*      */       case 8:
/* 3983 */         valueAsLong = this.thisRow.getNativeLong(columnIndex);
/*      */         
/* 3985 */         if (!f.isUnsigned() || !expandUnsignedLong) {
/* 3986 */           return valueAsLong;
/*      */         }
/*      */         
/* 3989 */         asBigInt = convertLongToUlong(valueAsLong);
/*      */         
/* 3991 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (asBigInt.compareTo(new BigInteger(String.valueOf(Long.MAX_VALUE))) > 0 || asBigInt.compareTo(new BigInteger(String.valueOf(Long.MIN_VALUE))) < 0))
/*      */         {
/* 3993 */           throwRangeException(asBigInt.toString(), columnIndex + 1, -5);
/*      */         }
/*      */         
/* 3996 */         return getLongFromString(asBigInt.toString(), columnIndex);
/*      */       
/*      */       case 5:
/* 3999 */         valueAsDouble = getNativeDouble(columnIndex + 1);
/*      */         
/* 4001 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 4002 */           valueAsDouble < -9.223372036854776E18D || valueAsDouble > 9.223372036854776E18D)) {
/* 4003 */           throwRangeException(String.valueOf(valueAsDouble), columnIndex + 1, -5);
/*      */         }
/*      */ 
/*      */         
/* 4007 */         return (long)valueAsDouble;
/*      */       case 4:
/* 4009 */         valueAsDouble = getNativeFloat(columnIndex + 1);
/*      */         
/* 4011 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 4012 */           valueAsDouble < -9.223372036854776E18D || valueAsDouble > 9.223372036854776E18D)) {
/* 4013 */           throwRangeException(String.valueOf(valueAsDouble), columnIndex + 1, -5);
/*      */         }
/*      */ 
/*      */         
/* 4017 */         return (long)valueAsDouble;
/*      */     } 
/* 4019 */     String stringVal = getNativeString(columnIndex + 1);
/*      */     
/* 4021 */     if (this.useUsageAdvisor) {
/* 4022 */       issueConversionViaParsingWarning("getLong()", columnIndex, stringVal, this.fields[columnIndex], new int[] { 5, 1, 2, 3, 8, 4 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4027 */     return getLongFromString(stringVal, columnIndex + 1); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Ref getNativeRef(int i) throws SQLException {
/* 4044 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   protected short getNativeShort(int columnIndex) throws SQLException {
/* 4059 */     return getNativeShort(columnIndex, true); } protected short getNativeShort(int columnIndex, boolean overflowCheck) throws SQLException { long valueAsLong; byte tinyintVal; short asShort; int valueAsInt;
/*      */     BigInteger asBigInt;
/*      */     double valueAsDouble;
/*      */     float valueAsFloat;
/* 4063 */     checkRowPos();
/* 4064 */     checkColumnBounds(columnIndex);
/*      */     
/* 4066 */     columnIndex--;
/*      */     
/* 4068 */     if (this.thisRow.isNull(columnIndex)) {
/* 4069 */       this.wasNullFlag = true;
/*      */       
/* 4071 */       return 0;
/*      */     } 
/*      */     
/* 4074 */     this.wasNullFlag = false;
/*      */     
/* 4076 */     Field f = this.fields[columnIndex];
/*      */     
/* 4078 */     switch (f.getMysqlType()) {
/*      */       case 16:
/* 4080 */         valueAsLong = getNumericRepresentationOfSQLBitType(columnIndex + 1);
/*      */         
/* 4082 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (valueAsLong < -32768L || valueAsLong > 32767L)) {
/* 4083 */           throwRangeException(String.valueOf(valueAsLong), columnIndex + 1, 5);
/*      */         }
/*      */         
/* 4086 */         return (short)(int)valueAsLong;
/*      */       
/*      */       case 1:
/* 4089 */         tinyintVal = getNativeByte(columnIndex + 1, false);
/*      */         
/* 4091 */         if (!f.isUnsigned() || tinyintVal >= 0) {
/* 4092 */           return (short)tinyintVal;
/*      */         }
/*      */         
/* 4095 */         return (short)(tinyintVal + 256);
/*      */       
/*      */       case 2:
/*      */       case 13:
/* 4099 */         asShort = this.thisRow.getNativeShort(columnIndex);
/*      */         
/* 4101 */         if (!f.isUnsigned()) {
/* 4102 */           return asShort;
/*      */         }
/*      */         
/* 4105 */         valueAsInt = asShort & 0xFFFF;
/*      */         
/* 4107 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && valueAsInt > 32767) {
/* 4108 */           throwRangeException(String.valueOf(valueAsInt), columnIndex + 1, 5);
/*      */         }
/*      */         
/* 4111 */         return (short)valueAsInt;
/*      */       case 3:
/*      */       case 9:
/* 4114 */         if (!f.isUnsigned()) {
/* 4115 */           valueAsInt = getNativeInt(columnIndex + 1, false);
/*      */           
/* 4117 */           if ((overflowCheck && this.jdbcCompliantTruncationForReads && valueAsInt > 32767) || valueAsInt < -32768) {
/* 4118 */             throwRangeException(String.valueOf(valueAsInt), columnIndex + 1, 5);
/*      */           }
/*      */           
/* 4121 */           return (short)valueAsInt;
/*      */         } 
/*      */         
/* 4124 */         valueAsLong = getNativeLong(columnIndex + 1, false, true);
/*      */         
/* 4126 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && valueAsLong > 32767L) {
/* 4127 */           throwRangeException(String.valueOf(valueAsLong), columnIndex + 1, 5);
/*      */         }
/*      */         
/* 4130 */         return (short)(int)valueAsLong;
/*      */       
/*      */       case 8:
/* 4133 */         valueAsLong = getNativeLong(columnIndex + 1, false, false);
/*      */         
/* 4135 */         if (!f.isUnsigned()) {
/* 4136 */           if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 4137 */             valueAsLong < -32768L || valueAsLong > 32767L)) {
/* 4138 */             throwRangeException(String.valueOf(valueAsLong), columnIndex + 1, 5);
/*      */           }
/*      */ 
/*      */           
/* 4142 */           return (short)(int)valueAsLong;
/*      */         } 
/*      */         
/* 4145 */         asBigInt = convertLongToUlong(valueAsLong);
/*      */         
/* 4147 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (asBigInt.compareTo(new BigInteger(String.valueOf(32767))) > 0 || asBigInt.compareTo(new BigInteger(String.valueOf(-32768))) < 0))
/*      */         {
/* 4149 */           throwRangeException(asBigInt.toString(), columnIndex + 1, 5);
/*      */         }
/*      */         
/* 4152 */         return (short)getIntFromString(asBigInt.toString(), columnIndex + 1);
/*      */       
/*      */       case 5:
/* 4155 */         valueAsDouble = getNativeDouble(columnIndex + 1);
/*      */         
/* 4157 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 4158 */           valueAsDouble < -32768.0D || valueAsDouble > 32767.0D)) {
/* 4159 */           throwRangeException(String.valueOf(valueAsDouble), columnIndex + 1, 5);
/*      */         }
/*      */ 
/*      */         
/* 4163 */         return (short)(int)valueAsDouble;
/*      */       case 4:
/* 4165 */         valueAsFloat = getNativeFloat(columnIndex + 1);
/*      */         
/* 4167 */         if (overflowCheck && this.jdbcCompliantTruncationForReads && (
/* 4168 */           valueAsFloat < -32768.0F || valueAsFloat > 32767.0F)) {
/* 4169 */           throwRangeException(String.valueOf(valueAsFloat), columnIndex + 1, 5);
/*      */         }
/*      */ 
/*      */         
/* 4173 */         return (short)(int)valueAsFloat;
/*      */     } 
/* 4175 */     String stringVal = getNativeString(columnIndex + 1);
/*      */     
/* 4177 */     if (this.useUsageAdvisor) {
/* 4178 */       issueConversionViaParsingWarning("getShort()", columnIndex, stringVal, this.fields[columnIndex], new int[] { 5, 1, 2, 3, 8, 4 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4183 */     return getShortFromString(stringVal, columnIndex + 1); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getNativeString(int columnIndex) throws SQLException {
/* 4199 */     checkRowPos();
/* 4200 */     checkColumnBounds(columnIndex);
/*      */     
/* 4202 */     if (this.fields == null) {
/* 4203 */       throw SQLError.createSQLException(Messages.getString("ResultSet.Query_generated_no_fields_for_ResultSet_133"), "S1002", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 4207 */     if (this.thisRow.isNull(columnIndex - 1)) {
/* 4208 */       this.wasNullFlag = true;
/*      */       
/* 4210 */       return null;
/*      */     } 
/*      */     
/* 4213 */     this.wasNullFlag = false;
/*      */     
/* 4215 */     String stringVal = null;
/*      */     
/* 4217 */     Field field = this.fields[columnIndex - 1];
/*      */ 
/*      */     
/* 4220 */     stringVal = getNativeConvertToString(columnIndex, field);
/* 4221 */     int mysqlType = field.getMysqlType();
/*      */     
/* 4223 */     if (mysqlType != 7 && mysqlType != 10 && field.isZeroFill() && stringVal != null) {
/* 4224 */       int origLength = stringVal.length();
/*      */       
/* 4226 */       StringBuilder zeroFillBuf = new StringBuilder(origLength);
/*      */       
/* 4228 */       long numZeros = field.getLength() - origLength;
/*      */       long i;
/* 4230 */       for (i = 0L; i < numZeros; i++) {
/* 4231 */         zeroFillBuf.append('0');
/*      */       }
/*      */       
/* 4234 */       zeroFillBuf.append(stringVal);
/*      */       
/* 4236 */       stringVal = zeroFillBuf.toString();
/*      */     } 
/*      */     
/* 4239 */     return stringVal;
/*      */   }
/*      */   
/*      */   private Time getNativeTime(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward) throws SQLException {
/* 4243 */     checkRowPos();
/* 4244 */     checkColumnBounds(columnIndex);
/*      */     
/* 4246 */     int columnIndexMinusOne = columnIndex - 1;
/*      */     
/* 4248 */     int mysqlType = this.fields[columnIndexMinusOne].getMysqlType();
/*      */     
/* 4250 */     Time timeVal = null;
/*      */     
/* 4252 */     if (mysqlType == 11) {
/* 4253 */       timeVal = this.thisRow.getNativeTime(columnIndexMinusOne, targetCalendar, tz, rollForward, this.connection, this);
/*      */     } else {
/*      */       
/* 4256 */       timeVal = (Time)this.thisRow.getNativeDateTimeValue(columnIndexMinusOne, null, 92, mysqlType, tz, rollForward, this.connection, this);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4264 */     if (timeVal == null) {
/*      */       
/* 4266 */       this.wasNullFlag = true;
/*      */       
/* 4268 */       return null;
/*      */     } 
/*      */     
/* 4271 */     this.wasNullFlag = false;
/*      */     
/* 4273 */     return timeVal;
/*      */   }
/*      */   
/*      */   Time getNativeTimeViaParseConversion(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward) throws SQLException {
/* 4277 */     if (this.useUsageAdvisor) {
/* 4278 */       issueConversionViaParsingWarning("getTime()", columnIndex, this.thisRow.getColumnValue(columnIndex - 1), this.fields[columnIndex - 1], new int[] { 11 });
/*      */     }
/*      */ 
/*      */     
/* 4282 */     String strTime = getNativeString(columnIndex);
/*      */     
/* 4284 */     return getTimeFromString(strTime, targetCalendar, columnIndex, tz, rollForward);
/*      */   }
/*      */   
/*      */   private Timestamp getNativeTimestamp(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward) throws SQLException {
/* 4288 */     checkRowPos();
/* 4289 */     checkColumnBounds(columnIndex);
/*      */     
/* 4291 */     int columnIndexMinusOne = columnIndex - 1;
/*      */     
/* 4293 */     Timestamp tsVal = null;
/*      */     
/* 4295 */     int mysqlType = this.fields[columnIndexMinusOne].getMysqlType();
/*      */     
/* 4297 */     switch (mysqlType) {
/*      */       case 7:
/*      */       case 12:
/* 4300 */         tsVal = this.thisRow.getNativeTimestamp(columnIndexMinusOne, targetCalendar, tz, rollForward, this.connection, this);
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/* 4305 */         tsVal = (Timestamp)this.thisRow.getNativeDateTimeValue(columnIndexMinusOne, null, 93, mysqlType, tz, rollForward, this.connection, this);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4314 */     if (tsVal == null) {
/*      */       
/* 4316 */       this.wasNullFlag = true;
/*      */       
/* 4318 */       return null;
/*      */     } 
/*      */     
/* 4321 */     this.wasNullFlag = false;
/*      */     
/* 4323 */     return tsVal;
/*      */   }
/*      */   
/*      */   Timestamp getNativeTimestampViaParseConversion(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward) throws SQLException {
/* 4327 */     if (this.useUsageAdvisor) {
/* 4328 */       issueConversionViaParsingWarning("getTimestamp()", columnIndex, this.thisRow.getColumnValue(columnIndex - 1), this.fields[columnIndex - 1], new int[] { 7, 12 });
/*      */     }
/*      */ 
/*      */     
/* 4332 */     String strTimestamp = getNativeString(columnIndex);
/*      */     
/* 4334 */     return getTimestampFromString(columnIndex, targetCalendar, strTimestamp, tz, rollForward);
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
/*      */   protected InputStream getNativeUnicodeStream(int columnIndex) throws SQLException {
/* 4359 */     checkRowPos();
/*      */     
/* 4361 */     return getBinaryStream(columnIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected URL getNativeURL(int colIndex) throws SQLException {
/* 4368 */     String val = getString(colIndex);
/*      */     
/* 4370 */     if (val == null) {
/* 4371 */       return null;
/*      */     }
/*      */     
/*      */     try {
/* 4375 */       return new URL(val);
/* 4376 */     } catch (MalformedURLException mfe) {
/* 4377 */       throw SQLError.createSQLException(Messages.getString("ResultSet.Malformed_URL____141") + val + "'", "S1009", getExceptionInterceptor());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized ResultSetInternalMethods getNextResultSet() {
/* 4386 */     return this.nextResultSet;
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
/*      */   public Object getObject(int columnIndex) throws SQLException {
/*      */     String stringVal;
/* 4410 */     checkRowPos();
/* 4411 */     checkColumnBounds(columnIndex);
/*      */     
/* 4413 */     int columnIndexMinusOne = columnIndex - 1;
/*      */     
/* 4415 */     if (this.thisRow.isNull(columnIndexMinusOne)) {
/* 4416 */       this.wasNullFlag = true;
/*      */       
/* 4418 */       return null;
/*      */     } 
/*      */     
/* 4421 */     this.wasNullFlag = false;
/*      */ 
/*      */     
/* 4424 */     Field field = this.fields[columnIndexMinusOne];
/*      */     
/* 4426 */     switch (field.getSQLType()) {
/*      */       case -7:
/* 4428 */         if (field.getMysqlType() == 16 && !field.isSingleBit()) {
/* 4429 */           return getObjectDeserializingIfNeeded(columnIndex);
/*      */         }
/* 4431 */         return Boolean.valueOf(getBoolean(columnIndex));
/*      */       
/*      */       case 16:
/* 4434 */         return Boolean.valueOf(getBoolean(columnIndex));
/*      */       
/*      */       case -6:
/* 4437 */         if (!field.isUnsigned()) {
/* 4438 */           return Integer.valueOf(getByte(columnIndex));
/*      */         }
/*      */         
/* 4441 */         return Integer.valueOf(getInt(columnIndex));
/*      */ 
/*      */       
/*      */       case 5:
/* 4445 */         return Integer.valueOf(getInt(columnIndex));
/*      */ 
/*      */       
/*      */       case 4:
/* 4449 */         if (!field.isUnsigned() || field.getMysqlType() == 9) {
/* 4450 */           return Integer.valueOf(getInt(columnIndex));
/*      */         }
/*      */         
/* 4453 */         return Long.valueOf(getLong(columnIndex));
/*      */ 
/*      */       
/*      */       case -5:
/* 4457 */         if (!field.isUnsigned()) {
/* 4458 */           return Long.valueOf(getLong(columnIndex));
/*      */         }
/*      */         
/* 4461 */         stringVal = getString(columnIndex);
/*      */         
/* 4463 */         if (stringVal == null) {
/* 4464 */           return null;
/*      */         }
/*      */         
/*      */         try {
/* 4468 */           return new BigInteger(stringVal);
/* 4469 */         } catch (NumberFormatException nfe) {
/* 4470 */           throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_BigInteger", new Object[] { Integer.valueOf(columnIndex), stringVal }), "S1009", getExceptionInterceptor());
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*      */       case 3:
/* 4477 */         stringVal = getString(columnIndex);
/*      */ 
/*      */ 
/*      */         
/* 4481 */         if (stringVal != null) {
/* 4482 */           BigDecimal val; if (stringVal.length() == 0) {
/* 4483 */             val = new BigDecimal(0);
/*      */             
/* 4485 */             return val;
/*      */           } 
/*      */           
/*      */           try {
/* 4489 */             val = new BigDecimal(stringVal);
/* 4490 */           } catch (NumberFormatException ex) {
/* 4491 */             throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_BigDecimal", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009", getExceptionInterceptor());
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 4496 */           return val;
/*      */         } 
/*      */         
/* 4499 */         return null;
/*      */       
/*      */       case 7:
/* 4502 */         return new Float(getFloat(columnIndex));
/*      */       
/*      */       case 6:
/*      */       case 8:
/* 4506 */         return new Double(getDouble(columnIndex));
/*      */       
/*      */       case 1:
/*      */       case 12:
/* 4510 */         if (!field.isOpaqueBinary()) {
/* 4511 */           return getString(columnIndex);
/*      */         }
/*      */         
/* 4514 */         return getBytes(columnIndex);
/*      */       case -1:
/* 4516 */         if (!field.isOpaqueBinary()) {
/* 4517 */           return getStringForClob(columnIndex);
/*      */         }
/*      */         
/* 4520 */         return getBytes(columnIndex);
/*      */       
/*      */       case -4:
/*      */       case -3:
/*      */       case -2:
/* 4525 */         if (field.getMysqlType() == 255) {
/* 4526 */           return getBytes(columnIndex);
/*      */         }
/* 4528 */         return getObjectDeserializingIfNeeded(columnIndex);
/*      */       
/*      */       case 91:
/* 4531 */         if (field.getMysqlType() == 13 && !this.connection.getYearIsDateType()) {
/* 4532 */           return Short.valueOf(getShort(columnIndex));
/*      */         }
/*      */         
/* 4535 */         return getDate(columnIndex);
/*      */       
/*      */       case 92:
/* 4538 */         return getTime(columnIndex);
/*      */       
/*      */       case 93:
/* 4541 */         return getTimestamp(columnIndex);
/*      */     } 
/*      */     
/* 4544 */     return getString(columnIndex);
/*      */   }
/*      */ 
/*      */   
/*      */   private Object getObjectDeserializingIfNeeded(int columnIndex) throws SQLException {
/* 4549 */     Field field = this.fields[columnIndex - 1];
/*      */     
/* 4551 */     if (field.isBinary() || field.isBlob()) {
/* 4552 */       byte[] data = getBytes(columnIndex);
/*      */       
/* 4554 */       if (this.connection.getAutoDeserialize()) {
/* 4555 */         Object obj = data;
/*      */         
/* 4557 */         if (data != null && data.length >= 2) {
/* 4558 */           if (data[0] == -84 && data[1] == -19) {
/*      */             
/*      */             try {
/* 4561 */               ByteArrayInputStream bytesIn = new ByteArrayInputStream(data);
/* 4562 */               ObjectInputStream objIn = new ObjectInputStream(bytesIn);
/* 4563 */               obj = objIn.readObject();
/* 4564 */               objIn.close();
/* 4565 */               bytesIn.close();
/* 4566 */             } catch (ClassNotFoundException cnfe) {
/* 4567 */               throw SQLError.createSQLException(Messages.getString("ResultSet.Class_not_found___91") + cnfe.toString() + Messages.getString("ResultSet._while_reading_serialized_object_92"), getExceptionInterceptor());
/*      */             }
/* 4569 */             catch (IOException ex) {
/* 4570 */               obj = data;
/*      */             } 
/*      */           } else {
/* 4573 */             return getString(columnIndex);
/*      */           } 
/*      */         }
/*      */         
/* 4577 */         return obj;
/*      */       } 
/*      */       
/* 4580 */       return data;
/*      */     } 
/*      */     
/* 4583 */     return getBytes(columnIndex);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
/* 4588 */     if (type == null) {
/* 4589 */       throw SQLError.createSQLException("Type parameter can not be null", "S1009", getExceptionInterceptor());
/*      */     }
/*      */     
/* 4592 */     if (type.equals(String.class))
/* 4593 */       return (T)getString(columnIndex); 
/* 4594 */     if (type.equals(BigDecimal.class))
/* 4595 */       return (T)getBigDecimal(columnIndex); 
/* 4596 */     if (type.equals(Boolean.class) || type.equals(boolean.class))
/* 4597 */       return (T)Boolean.valueOf(getBoolean(columnIndex)); 
/* 4598 */     if (type.equals(Integer.class) || type.equals(int.class))
/* 4599 */       return (T)Integer.valueOf(getInt(columnIndex)); 
/* 4600 */     if (type.equals(Long.class) || type.equals(long.class))
/* 4601 */       return (T)Long.valueOf(getLong(columnIndex)); 
/* 4602 */     if (type.equals(Float.class) || type.equals(float.class))
/* 4603 */       return (T)Float.valueOf(getFloat(columnIndex)); 
/* 4604 */     if (type.equals(Double.class) || type.equals(double.class))
/* 4605 */       return (T)Double.valueOf(getDouble(columnIndex)); 
/* 4606 */     if (type.equals(byte[].class))
/* 4607 */       return (T)getBytes(columnIndex); 
/* 4608 */     if (type.equals(Date.class))
/* 4609 */       return (T)getDate(columnIndex); 
/* 4610 */     if (type.equals(Time.class))
/* 4611 */       return (T)getTime(columnIndex); 
/* 4612 */     if (type.equals(Timestamp.class))
/* 4613 */       return (T)getTimestamp(columnIndex); 
/* 4614 */     if (type.equals(Clob.class))
/* 4615 */       return (T)getClob(columnIndex); 
/* 4616 */     if (type.equals(Blob.class))
/* 4617 */       return (T)getBlob(columnIndex); 
/* 4618 */     if (type.equals(Array.class))
/* 4619 */       return (T)getArray(columnIndex); 
/* 4620 */     if (type.equals(Ref.class))
/* 4621 */       return (T)getRef(columnIndex); 
/* 4622 */     if (type.equals(URL.class)) {
/* 4623 */       return (T)getURL(columnIndex);
/*      */     }
/* 4625 */     if (this.connection.getAutoDeserialize()) {
/*      */       try {
/* 4627 */         return type.cast(getObject(columnIndex));
/* 4628 */       } catch (ClassCastException cce) {
/* 4629 */         SQLException sqlEx = SQLError.createSQLException("Conversion not supported for type " + type.getName(), "S1009", getExceptionInterceptor());
/*      */         
/* 4631 */         sqlEx.initCause(cce);
/*      */         
/* 4633 */         throw sqlEx;
/*      */       } 
/*      */     }
/*      */     
/* 4637 */     throw SQLError.createSQLException("Conversion not supported for type " + type.getName(), "S1009", getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
/* 4644 */     return getObject(findColumn(columnLabel), type);
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
/*      */   public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
/* 4663 */     return getObject(i);
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
/*      */   public Object getObject(String columnName) throws SQLException {
/* 4687 */     return getObject(findColumn(columnName));
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
/*      */   public Object getObject(String colName, Map<String, Class<?>> map) throws SQLException {
/* 4706 */     return getObject(findColumn(colName), map);
/*      */   }
/*      */   public Object getObjectStoredProc(int columnIndex, int desiredSqlType) throws SQLException {
/*      */     String stringVal;
/* 4710 */     checkRowPos();
/* 4711 */     checkColumnBounds(columnIndex);
/*      */     
/* 4713 */     Object value = this.thisRow.getColumnValue(columnIndex - 1);
/*      */     
/* 4715 */     if (value == null) {
/* 4716 */       this.wasNullFlag = true;
/*      */       
/* 4718 */       return null;
/*      */     } 
/*      */     
/* 4721 */     this.wasNullFlag = false;
/*      */ 
/*      */     
/* 4724 */     Field field = this.fields[columnIndex - 1];
/*      */     
/* 4726 */     switch (desiredSqlType) {
/*      */       
/*      */       case -7:
/*      */       case 16:
/* 4730 */         return Boolean.valueOf(getBoolean(columnIndex));
/*      */       
/*      */       case -6:
/* 4733 */         return Integer.valueOf(getInt(columnIndex));
/*      */       
/*      */       case 5:
/* 4736 */         return Integer.valueOf(getInt(columnIndex));
/*      */ 
/*      */       
/*      */       case 4:
/* 4740 */         if (!field.isUnsigned() || field.getMysqlType() == 9) {
/* 4741 */           return Integer.valueOf(getInt(columnIndex));
/*      */         }
/*      */         
/* 4744 */         return Long.valueOf(getLong(columnIndex));
/*      */ 
/*      */       
/*      */       case -5:
/* 4748 */         if (field.isUnsigned()) {
/* 4749 */           return getBigDecimal(columnIndex);
/*      */         }
/*      */         
/* 4752 */         return Long.valueOf(getLong(columnIndex));
/*      */ 
/*      */       
/*      */       case 2:
/*      */       case 3:
/* 4757 */         stringVal = getString(columnIndex);
/*      */ 
/*      */         
/* 4760 */         if (stringVal != null) {
/* 4761 */           BigDecimal val; if (stringVal.length() == 0) {
/* 4762 */             val = new BigDecimal(0);
/*      */             
/* 4764 */             return val;
/*      */           } 
/*      */           
/*      */           try {
/* 4768 */             val = new BigDecimal(stringVal);
/* 4769 */           } catch (NumberFormatException ex) {
/* 4770 */             throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_BigDecimal", new Object[] { stringVal, Integer.valueOf(columnIndex) }), "S1009", getExceptionInterceptor());
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 4775 */           return val;
/*      */         } 
/*      */         
/* 4778 */         return null;
/*      */       
/*      */       case 7:
/* 4781 */         return new Float(getFloat(columnIndex));
/*      */ 
/*      */       
/*      */       case 6:
/* 4785 */         if (!this.connection.getRunningCTS13()) {
/* 4786 */           return new Double(getFloat(columnIndex));
/*      */         }
/* 4788 */         return new Float(getFloat(columnIndex));
/*      */ 
/*      */       
/*      */       case 8:
/* 4792 */         return new Double(getDouble(columnIndex));
/*      */       
/*      */       case 1:
/*      */       case 12:
/* 4796 */         return getString(columnIndex);
/*      */       case -1:
/* 4798 */         return getStringForClob(columnIndex);
/*      */       case -4:
/*      */       case -3:
/*      */       case -2:
/* 4802 */         return getBytes(columnIndex);
/*      */       
/*      */       case 91:
/* 4805 */         if (field.getMysqlType() == 13 && !this.connection.getYearIsDateType()) {
/* 4806 */           return Short.valueOf(getShort(columnIndex));
/*      */         }
/*      */         
/* 4809 */         return getDate(columnIndex);
/*      */       
/*      */       case 92:
/* 4812 */         return getTime(columnIndex);
/*      */       
/*      */       case 93:
/* 4815 */         return getTimestamp(columnIndex);
/*      */     } 
/*      */     
/* 4818 */     return getString(columnIndex);
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getObjectStoredProc(int i, Map<Object, Object> map, int desiredSqlType) throws SQLException {
/* 4823 */     return getObjectStoredProc(i, desiredSqlType);
/*      */   }
/*      */   
/*      */   public Object getObjectStoredProc(String columnName, int desiredSqlType) throws SQLException {
/* 4827 */     return getObjectStoredProc(findColumn(columnName), desiredSqlType);
/*      */   }
/*      */   
/*      */   public Object getObjectStoredProc(String colName, Map<Object, Object> map, int desiredSqlType) throws SQLException {
/* 4831 */     return getObjectStoredProc(findColumn(colName), map, desiredSqlType);
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
/*      */   public Ref getRef(int i) throws SQLException {
/* 4847 */     checkColumnBounds(i);
/* 4848 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   public Ref getRef(String colName) throws SQLException {
/* 4864 */     return getRef(findColumn(colName));
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
/*      */   public int getRow() throws SQLException {
/* 4880 */     checkClosed();
/*      */     
/* 4882 */     int currentRowNumber = this.rowData.getCurrentRowNumber();
/* 4883 */     int row = 0;
/*      */ 
/*      */     
/* 4886 */     if (!this.rowData.isDynamic()) {
/* 4887 */       if (currentRowNumber < 0 || this.rowData.isAfterLast() || this.rowData.isEmpty()) {
/* 4888 */         row = 0;
/*      */       } else {
/* 4890 */         row = currentRowNumber + 1;
/*      */       } 
/*      */     } else {
/*      */       
/* 4894 */       row = currentRowNumber + 1;
/*      */     } 
/*      */     
/* 4897 */     return row;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getServerInfo() {
/*      */     try {
/* 4907 */       synchronized (checkClosed().getConnectionMutex()) {
/* 4908 */         return this.serverInfo;
/*      */       } 
/* 4910 */     } catch (SQLException e) {
/* 4911 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private long getNumericRepresentationOfSQLBitType(int columnIndex) throws SQLException {
/* 4917 */     Object value = this.thisRow.getColumnValue(columnIndex - 1);
/*      */     
/* 4919 */     if (this.fields[columnIndex - 1].isSingleBit() || ((byte[])value).length == 1) {
/* 4920 */       return ((byte[])value)[0];
/*      */     }
/*      */     
/* 4923 */     byte[] asBytes = (byte[])value;
/*      */     
/* 4925 */     int shift = 0;
/*      */     
/* 4927 */     long[] steps = new long[asBytes.length];
/*      */     
/* 4929 */     for (int i = asBytes.length - 1; i >= 0; i--) {
/* 4930 */       steps[i] = (asBytes[i] & 0xFF) << shift;
/* 4931 */       shift += 8;
/*      */     } 
/*      */     
/* 4934 */     long valueAsLong = 0L;
/*      */     
/* 4936 */     for (int j = 0; j < asBytes.length; j++) {
/* 4937 */       valueAsLong |= steps[j];
/*      */     }
/*      */     
/* 4940 */     return valueAsLong;
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
/*      */   public short getShort(int columnIndex) throws SQLException {
/* 4955 */     checkRowPos();
/* 4956 */     checkColumnBounds(columnIndex);
/*      */     
/* 4958 */     if (!this.isBinaryEncoded) {
/* 4959 */       if (this.thisRow.isNull(columnIndex - 1)) {
/* 4960 */         this.wasNullFlag = true;
/* 4961 */         return 0;
/*      */       } 
/* 4963 */       this.wasNullFlag = false;
/*      */       
/* 4965 */       if (this.fields[columnIndex - 1].getMysqlType() == 16) {
/* 4966 */         long valueAsLong = getNumericRepresentationOfSQLBitType(columnIndex);
/*      */         
/* 4968 */         if (this.jdbcCompliantTruncationForReads && (valueAsLong < -32768L || valueAsLong > 32767L)) {
/* 4969 */           throwRangeException(String.valueOf(valueAsLong), columnIndex, 5);
/*      */         }
/*      */         
/* 4972 */         return (short)(int)valueAsLong;
/*      */       } 
/*      */       
/* 4975 */       if (this.useFastIntParsing) {
/* 4976 */         byte[] shortAsBytes = this.thisRow.getColumnValue(columnIndex - 1);
/*      */         
/* 4978 */         if (shortAsBytes.length == 0) {
/* 4979 */           return (short)convertToZeroWithEmptyCheck();
/*      */         }
/*      */         
/* 4982 */         boolean needsFullParse = false;
/*      */         
/* 4984 */         for (int i = 0; i < shortAsBytes.length; i++) {
/* 4985 */           if ((char)shortAsBytes[i] == 'e' || (char)shortAsBytes[i] == 'E') {
/* 4986 */             needsFullParse = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */         
/* 4992 */         if (!needsFullParse) {
/*      */           try {
/* 4994 */             return parseShortWithOverflowCheck(columnIndex, shortAsBytes, null);
/* 4995 */           } catch (NumberFormatException nfe) {
/*      */             try {
/* 4997 */               return parseShortAsDouble(columnIndex, StringUtils.toString(shortAsBytes));
/* 4998 */             } catch (NumberFormatException newNfe) {
/*      */ 
/*      */ 
/*      */               
/* 5002 */               throw SQLError.createSQLException(Messages.getString("ResultSet.Invalid_value_for_getShort()_-____96") + StringUtils.toString(shortAsBytes) + "'", "S1009", getExceptionInterceptor());
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 5009 */       String val = null;
/*      */       try {
/* 5011 */         val = getString(columnIndex);
/* 5012 */         if (val == null) {
/* 5013 */           return 0;
/*      */         }
/*      */         
/* 5016 */         if (val.length() == 0) {
/* 5017 */           return (short)convertToZeroWithEmptyCheck();
/*      */         }
/*      */         
/* 5020 */         if (val.indexOf("e") == -1 && val.indexOf("E") == -1 && val.indexOf(".") == -1) {
/* 5021 */           return parseShortWithOverflowCheck(columnIndex, null, val);
/*      */         }
/*      */ 
/*      */         
/* 5025 */         return parseShortAsDouble(columnIndex, val);
/*      */       }
/* 5027 */       catch (NumberFormatException nfe) {
/*      */         try {
/* 5029 */           return parseShortAsDouble(columnIndex, val);
/* 5030 */         } catch (NumberFormatException newNfe) {
/*      */ 
/*      */ 
/*      */           
/* 5034 */           throw SQLError.createSQLException(Messages.getString("ResultSet.Invalid_value_for_getShort()_-____96") + val + "'", "S1009", getExceptionInterceptor());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 5039 */     return getNativeShort(columnIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getShort(String columnName) throws SQLException {
/* 5048 */     return getShort(findColumn(columnName));
/*      */   }
/*      */   
/*      */   private final short getShortFromString(String val, int columnIndex) throws SQLException {
/*      */     try {
/* 5053 */       if (val != null) {
/*      */         
/* 5055 */         if (val.length() == 0) {
/* 5056 */           return (short)convertToZeroWithEmptyCheck();
/*      */         }
/*      */         
/* 5059 */         if (val.indexOf("e") == -1 && val.indexOf("E") == -1 && val.indexOf(".") == -1) {
/* 5060 */           return parseShortWithOverflowCheck(columnIndex, null, val);
/*      */         }
/*      */ 
/*      */         
/* 5064 */         return parseShortAsDouble(columnIndex, val);
/*      */       } 
/*      */       
/* 5067 */       return 0;
/* 5068 */     } catch (NumberFormatException nfe) {
/*      */       try {
/* 5070 */         return parseShortAsDouble(columnIndex, val);
/* 5071 */       } catch (NumberFormatException newNfe) {
/*      */ 
/*      */ 
/*      */         
/* 5075 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Invalid_value_for_getShort()_-____217") + val + Messages.getString("ResultSet.___in_column__218") + columnIndex, "S1009", getExceptionInterceptor());
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
/*      */   public Statement getStatement() throws SQLException {
/*      */     try {
/* 5091 */       synchronized (checkClosed().getConnectionMutex()) {
/* 5092 */         if (this.wrapperStatement != null) {
/* 5093 */           return this.wrapperStatement;
/*      */         }
/*      */         
/* 5096 */         return this.owningStatement;
/*      */       }
/*      */     
/* 5099 */     } catch (SQLException sqlEx) {
/* 5100 */       if (!this.retainOwningStatement) {
/* 5101 */         throw SQLError.createSQLException("Operation not allowed on closed ResultSet. Statements can be retained over result set closure by setting the connection property \"retainStatementAfterResultSetClose\" to \"true\".", "S1000", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5106 */       if (this.wrapperStatement != null) {
/* 5107 */         return this.wrapperStatement;
/*      */       }
/*      */       
/* 5110 */       return this.owningStatement;
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
/*      */   public String getString(int columnIndex) throws SQLException {
/* 5127 */     String stringVal = getStringInternal(columnIndex, true);
/*      */     
/* 5129 */     if (this.padCharsWithSpace && stringVal != null) {
/* 5130 */       Field f = this.fields[columnIndex - 1];
/*      */       
/* 5132 */       if (f.getMysqlType() == 254) {
/* 5133 */         int fieldLength = (int)f.getLength() / f.getMaxBytesPerCharacter();
/*      */         
/* 5135 */         int currentLength = stringVal.length();
/*      */         
/* 5137 */         if (currentLength < fieldLength) {
/* 5138 */           StringBuilder paddedBuf = new StringBuilder(fieldLength);
/* 5139 */           paddedBuf.append(stringVal);
/*      */           
/* 5141 */           int difference = fieldLength - currentLength;
/*      */           
/* 5143 */           paddedBuf.append(EMPTY_SPACE, 0, difference);
/*      */           
/* 5145 */           stringVal = paddedBuf.toString();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 5150 */     return stringVal;
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
/*      */   public String getString(String columnName) throws SQLException {
/* 5166 */     return getString(findColumn(columnName));
/*      */   }
/*      */   
/*      */   private String getStringForClob(int columnIndex) throws SQLException {
/* 5170 */     String asString = null;
/*      */     
/* 5172 */     String forcedEncoding = this.connection.getClobCharacterEncoding();
/*      */     
/* 5174 */     if (forcedEncoding == null) {
/* 5175 */       if (!this.isBinaryEncoded) {
/* 5176 */         asString = getString(columnIndex);
/*      */       } else {
/* 5178 */         asString = getNativeString(columnIndex);
/*      */       } 
/*      */     } else {
/*      */       try {
/* 5182 */         byte[] asBytes = null;
/*      */         
/* 5184 */         if (!this.isBinaryEncoded) {
/* 5185 */           asBytes = getBytes(columnIndex);
/*      */         } else {
/* 5187 */           asBytes = getNativeBytes(columnIndex, true);
/*      */         } 
/*      */         
/* 5190 */         if (asBytes != null) {
/* 5191 */           asString = StringUtils.toString(asBytes, forcedEncoding);
/*      */         }
/* 5193 */       } catch (UnsupportedEncodingException uee) {
/* 5194 */         throw SQLError.createSQLException("Unsupported character encoding " + forcedEncoding, "S1009", getExceptionInterceptor());
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 5199 */     return asString;
/*      */   }
/*      */   
/*      */   protected String getStringInternal(int columnIndex, boolean checkDateTypes) throws SQLException {
/* 5203 */     if (!this.isBinaryEncoded) {
/* 5204 */       checkRowPos();
/* 5205 */       checkColumnBounds(columnIndex);
/*      */       
/* 5207 */       if (this.fields == null) {
/* 5208 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Query_generated_no_fields_for_ResultSet_99"), "S1002", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5214 */       int internalColumnIndex = columnIndex - 1;
/*      */       
/* 5216 */       if (this.thisRow.isNull(internalColumnIndex)) {
/* 5217 */         this.wasNullFlag = true;
/*      */         
/* 5219 */         return null;
/*      */       } 
/*      */       
/* 5222 */       this.wasNullFlag = false;
/*      */       
/* 5224 */       Field metadata = this.fields[internalColumnIndex];
/*      */       
/* 5226 */       String stringVal = null;
/*      */       
/* 5228 */       if (metadata.getMysqlType() == 16) {
/* 5229 */         if (metadata.isSingleBit()) {
/* 5230 */           byte[] value = this.thisRow.getColumnValue(internalColumnIndex);
/*      */           
/* 5232 */           if (value.length == 0) {
/* 5233 */             return String.valueOf(convertToZeroWithEmptyCheck());
/*      */           }
/*      */           
/* 5236 */           return String.valueOf(value[0]);
/*      */         } 
/*      */         
/* 5239 */         return String.valueOf(getNumericRepresentationOfSQLBitType(columnIndex));
/*      */       } 
/*      */       
/* 5242 */       String encoding = (metadata.getCollationIndex() == 63) ? this.connection.getEncoding() : metadata.getEncoding();
/*      */ 
/*      */       
/* 5245 */       stringVal = this.thisRow.getString(internalColumnIndex, encoding, this.connection);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5251 */       if (metadata.getMysqlType() == 13) {
/* 5252 */         if (!this.connection.getYearIsDateType()) {
/* 5253 */           return stringVal;
/*      */         }
/*      */         
/* 5256 */         Date dt = getDateFromString(stringVal, columnIndex, null);
/*      */         
/* 5258 */         if (dt == null) {
/* 5259 */           this.wasNullFlag = true;
/*      */           
/* 5261 */           return null;
/*      */         } 
/*      */         
/* 5264 */         this.wasNullFlag = false;
/*      */         
/* 5266 */         return dt.toString();
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 5271 */       if (checkDateTypes && !this.connection.getNoDatetimeStringSync()) {
/* 5272 */         Time tm; Date dt; Timestamp ts; switch (metadata.getSQLType()) {
/*      */           case 92:
/* 5274 */             tm = getTimeFromString(stringVal, null, columnIndex, getDefaultTimeZone(), false);
/*      */             
/* 5276 */             if (tm == null) {
/* 5277 */               this.wasNullFlag = true;
/*      */               
/* 5279 */               return null;
/*      */             } 
/*      */             
/* 5282 */             this.wasNullFlag = false;
/*      */             
/* 5284 */             return tm.toString();
/*      */           
/*      */           case 91:
/* 5287 */             dt = getDateFromString(stringVal, columnIndex, null);
/*      */             
/* 5289 */             if (dt == null) {
/* 5290 */               this.wasNullFlag = true;
/*      */               
/* 5292 */               return null;
/*      */             } 
/*      */             
/* 5295 */             this.wasNullFlag = false;
/*      */             
/* 5297 */             return dt.toString();
/*      */           case 93:
/* 5299 */             ts = getTimestampFromString(columnIndex, null, stringVal, getDefaultTimeZone(), false);
/*      */             
/* 5301 */             if (ts == null) {
/* 5302 */               this.wasNullFlag = true;
/*      */               
/* 5304 */               return null;
/*      */             } 
/*      */             
/* 5307 */             this.wasNullFlag = false;
/*      */             
/* 5309 */             return ts.toString();
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/* 5315 */       return stringVal;
/*      */     } 
/*      */     
/* 5318 */     return getNativeString(columnIndex);
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
/*      */   public Time getTime(int columnIndex) throws SQLException {
/* 5333 */     return getTimeInternal(columnIndex, null, getDefaultTimeZone(), false);
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
/*      */   public Time getTime(int columnIndex, Calendar cal) throws SQLException {
/* 5352 */     return getTimeInternal(columnIndex, cal, (cal != null) ? cal.getTimeZone() : getDefaultTimeZone(), true);
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
/*      */   public Time getTime(String columnName) throws SQLException {
/* 5367 */     return getTime(findColumn(columnName));
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
/*      */   public Time getTime(String columnName, Calendar cal) throws SQLException {
/* 5386 */     return getTime(findColumn(columnName), cal);
/*      */   }
/*      */   
/*      */   private Time getTimeFromString(String timeAsString, Calendar targetCalendar, int columnIndex, TimeZone tz, boolean rollForward) throws SQLException {
/* 5390 */     synchronized (checkClosed().getConnectionMutex()) {
/* 5391 */       int hr = 0;
/* 5392 */       int min = 0;
/* 5393 */       int sec = 0;
/*      */ 
/*      */       
/*      */       try {
/* 5397 */         if (timeAsString == null) {
/* 5398 */           this.wasNullFlag = true;
/*      */           
/* 5400 */           return null;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 5410 */         timeAsString = timeAsString.trim();
/*      */ 
/*      */         
/* 5413 */         int dec = timeAsString.indexOf(".");
/* 5414 */         if (dec > -1) {
/* 5415 */           timeAsString = timeAsString.substring(0, dec);
/*      */         }
/*      */         
/* 5418 */         if (timeAsString.equals("0") || timeAsString.equals("0000-00-00") || timeAsString.equals("0000-00-00 00:00:00") || timeAsString.equals("00000000000000")) {
/*      */           
/* 5420 */           if ("convertToNull".equals(this.connection.getZeroDateTimeBehavior())) {
/* 5421 */             this.wasNullFlag = true;
/*      */             
/* 5423 */             return null;
/* 5424 */           }  if ("exception".equals(this.connection.getZeroDateTimeBehavior())) {
/* 5425 */             throw SQLError.createSQLException("Value '" + timeAsString + "' can not be represented as java.sql.Time", "S1009", getExceptionInterceptor());
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 5430 */           return fastTimeCreate(targetCalendar, 0, 0, 0);
/*      */         } 
/*      */         
/* 5433 */         this.wasNullFlag = false;
/*      */         
/* 5435 */         Field timeColField = this.fields[columnIndex - 1];
/*      */         
/* 5437 */         if (timeColField.getMysqlType() == 7)
/*      */         
/* 5439 */         { int length = timeAsString.length();
/*      */           
/* 5441 */           switch (length) {
/*      */             
/*      */             case 19:
/* 5444 */               hr = Integer.parseInt(timeAsString.substring(length - 8, length - 6));
/* 5445 */               min = Integer.parseInt(timeAsString.substring(length - 5, length - 3));
/* 5446 */               sec = Integer.parseInt(timeAsString.substring(length - 2, length));
/*      */               break;
/*      */ 
/*      */             
/*      */             case 12:
/*      */             case 14:
/* 5452 */               hr = Integer.parseInt(timeAsString.substring(length - 6, length - 4));
/* 5453 */               min = Integer.parseInt(timeAsString.substring(length - 4, length - 2));
/* 5454 */               sec = Integer.parseInt(timeAsString.substring(length - 2, length));
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             case 10:
/* 5460 */               hr = Integer.parseInt(timeAsString.substring(6, 8));
/* 5461 */               min = Integer.parseInt(timeAsString.substring(8, 10));
/* 5462 */               sec = 0;
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             default:
/* 5468 */               throw SQLError.createSQLException(Messages.getString("ResultSet.Timestamp_too_small_to_convert_to_Time_value_in_column__257") + columnIndex + "(" + this.fields[columnIndex - 1] + ").", "S1009", getExceptionInterceptor());
/*      */           } 
/*      */ 
/*      */           
/* 5472 */           SQLWarning precisionLost = new SQLWarning(Messages.getString("ResultSet.Precision_lost_converting_TIMESTAMP_to_Time_with_getTime()_on_column__261") + columnIndex + "(" + this.fields[columnIndex - 1] + ").");
/*      */ 
/*      */ 
/*      */           
/* 5476 */           if (this.warningChain == null) {
/* 5477 */             this.warningChain = precisionLost;
/*      */           } else {
/* 5479 */             this.warningChain.setNextWarning(precisionLost);
/*      */           }  }
/* 5481 */         else if (timeColField.getMysqlType() == 12)
/* 5482 */         { hr = Integer.parseInt(timeAsString.substring(11, 13));
/* 5483 */           min = Integer.parseInt(timeAsString.substring(14, 16));
/* 5484 */           sec = Integer.parseInt(timeAsString.substring(17, 19));
/*      */           
/* 5486 */           SQLWarning precisionLost = new SQLWarning(Messages.getString("ResultSet.Precision_lost_converting_DATETIME_to_Time_with_getTime()_on_column__264") + columnIndex + "(" + this.fields[columnIndex - 1] + ").");
/*      */ 
/*      */ 
/*      */           
/* 5490 */           if (this.warningChain == null) {
/* 5491 */             this.warningChain = precisionLost;
/*      */           } else {
/* 5493 */             this.warningChain.setNextWarning(precisionLost);
/*      */           }  }
/* 5495 */         else { if (timeColField.getMysqlType() == 10) {
/* 5496 */             return fastTimeCreate(targetCalendar, 0, 0, 0);
/*      */           }
/*      */ 
/*      */           
/* 5500 */           if (timeAsString.length() != 5 && timeAsString.length() != 8) {
/* 5501 */             throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_Time____267") + timeAsString + Messages.getString("ResultSet.___in_column__268") + columnIndex, "S1009", getExceptionInterceptor());
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5508 */           hr = Integer.parseInt(timeAsString.substring(0, 2));
/* 5509 */           min = Integer.parseInt(timeAsString.substring(3, 5));
/* 5510 */           sec = (timeAsString.length() == 5) ? 0 : Integer.parseInt(timeAsString.substring(6)); }
/*      */ 
/*      */         
/* 5513 */         Calendar sessionCalendar = getCalendarInstanceForSessionOrNew();
/*      */         
/* 5515 */         return TimeUtil.changeTimezone(this.connection, sessionCalendar, targetCalendar, fastTimeCreate(sessionCalendar, hr, min, sec), this.connection.getServerTimezoneTZ(), tz, rollForward);
/*      */       }
/* 5517 */       catch (RuntimeException ex) {
/* 5518 */         SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", getExceptionInterceptor());
/* 5519 */         sqlEx.initCause(ex);
/*      */         
/* 5521 */         throw sqlEx;
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
/*      */   private Time getTimeInternal(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward) throws SQLException {
/* 5541 */     checkRowPos();
/*      */     
/* 5543 */     if (this.isBinaryEncoded) {
/* 5544 */       return getNativeTime(columnIndex, targetCalendar, tz, rollForward);
/*      */     }
/*      */     
/* 5547 */     if (!this.useFastDateParsing) {
/* 5548 */       String timeAsString = getStringInternal(columnIndex, false);
/*      */       
/* 5550 */       return getTimeFromString(timeAsString, targetCalendar, columnIndex, tz, rollForward);
/*      */     } 
/*      */     
/* 5553 */     checkColumnBounds(columnIndex);
/*      */     
/* 5555 */     int columnIndexMinusOne = columnIndex - 1;
/*      */     
/* 5557 */     if (this.thisRow.isNull(columnIndexMinusOne)) {
/* 5558 */       this.wasNullFlag = true;
/*      */       
/* 5560 */       return null;
/*      */     } 
/*      */     
/* 5563 */     this.wasNullFlag = false;
/*      */     
/* 5565 */     return this.thisRow.getTimeFast(columnIndexMinusOne, targetCalendar, tz, rollForward, this.connection, this);
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
/*      */   public Timestamp getTimestamp(int columnIndex) throws SQLException {
/* 5581 */     return getTimestampInternal(columnIndex, null, getDefaultTimeZone(), false);
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
/*      */   public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
/* 5601 */     return getTimestampInternal(columnIndex, cal, (cal != null) ? cal.getTimeZone() : getDefaultTimeZone(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Timestamp getTimestamp(String columnName) throws SQLException {
/* 5610 */     return getTimestamp(findColumn(columnName));
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
/*      */   public Timestamp getTimestamp(String columnName, Calendar cal) throws SQLException {
/* 5630 */     return getTimestamp(findColumn(columnName), cal);
/*      */   }
/*      */ 
/*      */   
/*      */   private Timestamp getTimestampFromString(int columnIndex, Calendar targetCalendar, String timestampValue, TimeZone tz, boolean rollForward) throws SQLException {
/*      */     try {
/* 5636 */       this.wasNullFlag = false;
/*      */       
/* 5638 */       if (timestampValue == null) {
/* 5639 */         this.wasNullFlag = true;
/*      */         
/* 5641 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5651 */       timestampValue = timestampValue.trim();
/*      */       
/* 5653 */       int length = timestampValue.length();
/*      */       
/* 5655 */       Calendar sessionCalendar = this.connection.getUseJDBCCompliantTimezoneShift() ? this.connection.getUtcCalendar() : getCalendarInstanceForSessionOrNew();
/*      */ 
/*      */       
/* 5658 */       boolean useGmtMillis = this.connection.getUseGmtMillisForDatetimes();
/*      */       
/* 5660 */       if (length > 0 && timestampValue.charAt(0) == '0' && (timestampValue.equals("0000-00-00") || timestampValue.equals("0000-00-00 00:00:00") || timestampValue.equals("00000000000000") || timestampValue.equals("0"))) {
/*      */ 
/*      */         
/* 5663 */         if ("convertToNull".equals(this.connection.getZeroDateTimeBehavior())) {
/* 5664 */           this.wasNullFlag = true;
/*      */           
/* 5666 */           return null;
/* 5667 */         }  if ("exception".equals(this.connection.getZeroDateTimeBehavior())) {
/* 5668 */           throw SQLError.createSQLException("Value '" + timestampValue + "' can not be represented as java.sql.Timestamp", "S1009", getExceptionInterceptor());
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 5673 */         return fastTimestampCreate(null, 1, 1, 1, 0, 0, 0, 0, useGmtMillis);
/*      */       } 
/* 5675 */       if (this.fields[columnIndex - 1].getMysqlType() == 13) {
/*      */         
/* 5677 */         if (!this.useLegacyDatetimeCode) {
/* 5678 */           return TimeUtil.fastTimestampCreate(tz, Integer.parseInt(timestampValue.substring(0, 4)), 1, 1, 0, 0, 0, 0);
/*      */         }
/*      */         
/* 5681 */         return TimeUtil.changeTimezone(this.connection, sessionCalendar, targetCalendar, fastTimestampCreate(sessionCalendar, Integer.parseInt(timestampValue.substring(0, 4)), 1, 1, 0, 0, 0, 0, useGmtMillis), this.connection.getServerTimezoneTZ(), tz, rollForward);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5688 */       int year = 0;
/* 5689 */       int month = 0;
/* 5690 */       int day = 0;
/* 5691 */       int hour = 0;
/* 5692 */       int minutes = 0;
/* 5693 */       int seconds = 0;
/* 5694 */       int nanos = 0;
/*      */ 
/*      */       
/* 5697 */       int decimalIndex = timestampValue.indexOf(".");
/*      */       
/* 5699 */       if (decimalIndex == length - 1) {
/*      */         
/* 5701 */         length--;
/*      */       }
/* 5703 */       else if (decimalIndex != -1) {
/*      */         
/* 5705 */         if (decimalIndex + 2 <= length) {
/* 5706 */           nanos = Integer.parseInt(timestampValue.substring(decimalIndex + 1));
/*      */           
/* 5708 */           int numDigits = length - decimalIndex + 1;
/*      */           
/* 5710 */           if (numDigits < 9) {
/* 5711 */             int factor = (int)Math.pow(10.0D, (9 - numDigits));
/* 5712 */             nanos *= factor;
/*      */           } 
/*      */           
/* 5715 */           length = decimalIndex;
/*      */         } else {
/* 5717 */           throw new IllegalArgumentException();
/*      */         } 
/*      */       } 
/*      */       
/* 5721 */       switch (length) {
/*      */         case 19:
/*      */         case 20:
/*      */         case 21:
/*      */         case 22:
/*      */         case 23:
/*      */         case 24:
/*      */         case 25:
/*      */         case 26:
/* 5730 */           year = Integer.parseInt(timestampValue.substring(0, 4));
/* 5731 */           month = Integer.parseInt(timestampValue.substring(5, 7));
/* 5732 */           day = Integer.parseInt(timestampValue.substring(8, 10));
/* 5733 */           hour = Integer.parseInt(timestampValue.substring(11, 13));
/* 5734 */           minutes = Integer.parseInt(timestampValue.substring(14, 16));
/* 5735 */           seconds = Integer.parseInt(timestampValue.substring(17, 19));
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 14:
/* 5741 */           year = Integer.parseInt(timestampValue.substring(0, 4));
/* 5742 */           month = Integer.parseInt(timestampValue.substring(4, 6));
/* 5743 */           day = Integer.parseInt(timestampValue.substring(6, 8));
/* 5744 */           hour = Integer.parseInt(timestampValue.substring(8, 10));
/* 5745 */           minutes = Integer.parseInt(timestampValue.substring(10, 12));
/* 5746 */           seconds = Integer.parseInt(timestampValue.substring(12, 14));
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 12:
/* 5752 */           year = Integer.parseInt(timestampValue.substring(0, 2));
/*      */           
/* 5754 */           if (year <= 69) {
/* 5755 */             year += 100;
/*      */           }
/*      */           
/* 5758 */           year += 1900;
/*      */           
/* 5760 */           month = Integer.parseInt(timestampValue.substring(2, 4));
/* 5761 */           day = Integer.parseInt(timestampValue.substring(4, 6));
/* 5762 */           hour = Integer.parseInt(timestampValue.substring(6, 8));
/* 5763 */           minutes = Integer.parseInt(timestampValue.substring(8, 10));
/* 5764 */           seconds = Integer.parseInt(timestampValue.substring(10, 12));
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 10:
/* 5770 */           if (this.fields[columnIndex - 1].getMysqlType() == 10 || timestampValue.indexOf("-") != -1) {
/* 5771 */             year = Integer.parseInt(timestampValue.substring(0, 4));
/* 5772 */             month = Integer.parseInt(timestampValue.substring(5, 7));
/* 5773 */             day = Integer.parseInt(timestampValue.substring(8, 10));
/* 5774 */             hour = 0;
/* 5775 */             minutes = 0; break;
/*      */           } 
/* 5777 */           year = Integer.parseInt(timestampValue.substring(0, 2));
/*      */           
/* 5779 */           if (year <= 69) {
/* 5780 */             year += 100;
/*      */           }
/*      */           
/* 5783 */           month = Integer.parseInt(timestampValue.substring(2, 4));
/* 5784 */           day = Integer.parseInt(timestampValue.substring(4, 6));
/* 5785 */           hour = Integer.parseInt(timestampValue.substring(6, 8));
/* 5786 */           minutes = Integer.parseInt(timestampValue.substring(8, 10));
/*      */           
/* 5788 */           year += 1900;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 8:
/* 5795 */           if (timestampValue.indexOf(":") != -1) {
/* 5796 */             hour = Integer.parseInt(timestampValue.substring(0, 2));
/* 5797 */             minutes = Integer.parseInt(timestampValue.substring(3, 5));
/* 5798 */             seconds = Integer.parseInt(timestampValue.substring(6, 8));
/* 5799 */             year = 1970;
/* 5800 */             month = 1;
/* 5801 */             day = 1;
/*      */             
/*      */             break;
/*      */           } 
/* 5805 */           year = Integer.parseInt(timestampValue.substring(0, 4));
/* 5806 */           month = Integer.parseInt(timestampValue.substring(4, 6));
/* 5807 */           day = Integer.parseInt(timestampValue.substring(6, 8));
/*      */           
/* 5809 */           year -= 1900;
/* 5810 */           month--;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 6:
/* 5816 */           year = Integer.parseInt(timestampValue.substring(0, 2));
/*      */           
/* 5818 */           if (year <= 69) {
/* 5819 */             year += 100;
/*      */           }
/*      */           
/* 5822 */           year += 1900;
/*      */           
/* 5824 */           month = Integer.parseInt(timestampValue.substring(2, 4));
/* 5825 */           day = Integer.parseInt(timestampValue.substring(4, 6));
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 4:
/* 5831 */           year = Integer.parseInt(timestampValue.substring(0, 2));
/*      */           
/* 5833 */           if (year <= 69) {
/* 5834 */             year += 100;
/*      */           }
/*      */           
/* 5837 */           year += 1900;
/*      */           
/* 5839 */           month = Integer.parseInt(timestampValue.substring(2, 4));
/*      */           
/* 5841 */           day = 1;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/* 5847 */           year = Integer.parseInt(timestampValue.substring(0, 2));
/*      */           
/* 5849 */           if (year <= 69) {
/* 5850 */             year += 100;
/*      */           }
/*      */           
/* 5853 */           year += 1900;
/* 5854 */           month = 1;
/* 5855 */           day = 1;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 5861 */           throw new SQLException("Bad format for Timestamp '" + timestampValue + "' in column " + columnIndex + ".", "S1009");
/*      */       } 
/*      */ 
/*      */       
/* 5865 */       if (!this.useLegacyDatetimeCode) {
/* 5866 */         return TimeUtil.fastTimestampCreate(tz, year, month, day, hour, minutes, seconds, nanos);
/*      */       }
/*      */       
/* 5869 */       return TimeUtil.changeTimezone(this.connection, sessionCalendar, targetCalendar, fastTimestampCreate(sessionCalendar, year, month, day, hour, minutes, seconds, nanos, useGmtMillis), this.connection.getServerTimezoneTZ(), tz, rollForward);
/*      */ 
/*      */     
/*      */     }
/* 5873 */     catch (RuntimeException e) {
/* 5874 */       SQLException sqlEx = SQLError.createSQLException("Cannot convert value '" + timestampValue + "' from column " + columnIndex + " to TIMESTAMP.", "S1009", getExceptionInterceptor());
/*      */       
/* 5876 */       sqlEx.initCause(e);
/*      */       
/* 5878 */       throw sqlEx;
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
/*      */   private Timestamp getTimestampInternal(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward) throws SQLException {
/* 5898 */     if (this.isBinaryEncoded) {
/* 5899 */       return getNativeTimestamp(columnIndex, targetCalendar, tz, rollForward);
/*      */     }
/*      */     
/* 5902 */     Timestamp tsVal = null;
/*      */     
/* 5904 */     if (!this.useFastDateParsing) {
/* 5905 */       String timestampValue = getStringInternal(columnIndex, false);
/*      */       
/* 5907 */       tsVal = getTimestampFromString(columnIndex, targetCalendar, timestampValue, tz, rollForward);
/*      */     } else {
/* 5909 */       checkClosed();
/* 5910 */       checkRowPos();
/* 5911 */       checkColumnBounds(columnIndex);
/*      */       
/* 5913 */       tsVal = this.thisRow.getTimestampFast(columnIndex - 1, targetCalendar, tz, rollForward, this.connection, this, this.connection.getUseGmtMillisForDatetimes(), this.connection.getUseJDBCCompliantTimezoneShift());
/*      */     } 
/*      */ 
/*      */     
/* 5917 */     if (tsVal == null) {
/* 5918 */       this.wasNullFlag = true;
/*      */     } else {
/* 5920 */       this.wasNullFlag = false;
/*      */     } 
/*      */     
/* 5923 */     return tsVal;
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
/*      */   public int getType() throws SQLException {
/* 5937 */     return this.resultSetType;
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
/*      */   @Deprecated
/*      */   public InputStream getUnicodeStream(int columnIndex) throws SQLException {
/* 5960 */     if (!this.isBinaryEncoded) {
/* 5961 */       checkRowPos();
/*      */       
/* 5963 */       return getBinaryStream(columnIndex);
/*      */     } 
/*      */     
/* 5966 */     return getNativeBinaryStream(columnIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public InputStream getUnicodeStream(String columnName) throws SQLException {
/* 5978 */     return getUnicodeStream(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public long getUpdateCount() {
/* 5982 */     return this.updateCount;
/*      */   }
/*      */   
/*      */   public long getUpdateID() {
/* 5986 */     return this.updateId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URL getURL(int colIndex) throws SQLException {
/* 5993 */     String val = getString(colIndex);
/*      */     
/* 5995 */     if (val == null) {
/* 5996 */       return null;
/*      */     }
/*      */     
/*      */     try {
/* 6000 */       return new URL(val);
/* 6001 */     } catch (MalformedURLException mfe) {
/* 6002 */       throw SQLError.createSQLException(Messages.getString("ResultSet.Malformed_URL____104") + val + "'", "S1009", getExceptionInterceptor());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URL getURL(String colName) throws SQLException {
/* 6011 */     String val = getString(colName);
/*      */     
/* 6013 */     if (val == null) {
/* 6014 */       return null;
/*      */     }
/*      */     
/*      */     try {
/* 6018 */       return new URL(val);
/* 6019 */     } catch (MalformedURLException mfe) {
/* 6020 */       throw SQLError.createSQLException(Messages.getString("ResultSet.Malformed_URL____107") + val + "'", "S1009", getExceptionInterceptor());
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
/*      */   public SQLWarning getWarnings() throws SQLException {
/* 6045 */     synchronized (checkClosed().getConnectionMutex()) {
/* 6046 */       return this.warningChain;
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
/*      */   public void insertRow() throws SQLException {
/* 6061 */     throw new NotUpdatable();
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
/*      */   public boolean isAfterLast() throws SQLException {
/* 6078 */     synchronized (checkClosed().getConnectionMutex()) {
/* 6079 */       boolean b = this.rowData.isAfterLast();
/*      */       
/* 6081 */       return b;
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
/*      */   public boolean isBeforeFirst() throws SQLException {
/* 6099 */     synchronized (checkClosed().getConnectionMutex()) {
/* 6100 */       return this.rowData.isBeforeFirst();
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
/*      */   public boolean isFirst() throws SQLException {
/* 6117 */     synchronized (checkClosed().getConnectionMutex()) {
/* 6118 */       return this.rowData.isFirst();
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
/*      */   public boolean isLast() throws SQLException {
/* 6136 */     synchronized (checkClosed().getConnectionMutex()) {
/* 6137 */       return this.rowData.isLast();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void issueConversionViaParsingWarning(String methodName, int columnIndex, Object value, Field fieldInfo, int[] typesWithNoParseConversion) throws SQLException {
/* 6148 */     synchronized (checkClosed().getConnectionMutex()) {
/* 6149 */       StringBuilder originalQueryBuf = new StringBuilder();
/*      */       
/* 6151 */       if (this.owningStatement != null && this.owningStatement instanceof PreparedStatement) {
/* 6152 */         originalQueryBuf.append(Messages.getString("ResultSet.CostlyConversionCreatedFromQuery"));
/* 6153 */         originalQueryBuf.append(((PreparedStatement)this.owningStatement).originalSql);
/* 6154 */         originalQueryBuf.append("\n\n");
/*      */       } else {
/* 6156 */         originalQueryBuf.append(".");
/*      */       } 
/*      */       
/* 6159 */       StringBuilder convertibleTypesBuf = new StringBuilder();
/*      */       
/* 6161 */       for (int i = 0; i < typesWithNoParseConversion.length; i++) {
/* 6162 */         convertibleTypesBuf.append(MysqlDefs.typeToName(typesWithNoParseConversion[i]));
/* 6163 */         convertibleTypesBuf.append("\n");
/*      */       } 
/*      */       
/* 6166 */       String message = Messages.getString("ResultSet.CostlyConversion", new Object[] { methodName, Integer.valueOf(columnIndex + 1), fieldInfo.getOriginalName(), fieldInfo.getOriginalTableName(), originalQueryBuf.toString(), (value != null) ? value.getClass().getName() : ResultSetMetaData.getClassNameForJavaType(fieldInfo.getSQLType(), fieldInfo.isUnsigned(), fieldInfo.getMysqlType(), (fieldInfo.isBinary() || fieldInfo.isBlob()), fieldInfo.isOpaqueBinary(), this.connection.getYearIsDateType()), MysqlDefs.typeToName(fieldInfo.getMysqlType()), convertibleTypesBuf.toString() });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6174 */       this.connection.getProfilerEventHandlerInstance().processEvent((byte)0, this.connection, this.owningStatement, this, 0L, new Throwable(), message);
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
/*      */   public boolean last() throws SQLException {
/* 6193 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/* 6195 */       boolean b = true;
/*      */       
/* 6197 */       if (this.rowData.size() == 0) {
/* 6198 */         b = false;
/*      */       } else {
/*      */         
/* 6201 */         if (this.onInsertRow) {
/* 6202 */           this.onInsertRow = false;
/*      */         }
/*      */         
/* 6205 */         if (this.doingUpdates) {
/* 6206 */           this.doingUpdates = false;
/*      */         }
/*      */         
/* 6209 */         if (this.thisRow != null) {
/* 6210 */           this.thisRow.closeOpenStreams();
/*      */         }
/*      */         
/* 6213 */         this.rowData.beforeLast();
/* 6214 */         this.thisRow = this.rowData.next();
/*      */       } 
/*      */       
/* 6217 */       setRowPositionValidity();
/*      */       
/* 6219 */       return b;
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
/*      */   public void moveToCurrentRow() throws SQLException {
/* 6241 */     throw new NotUpdatable();
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
/*      */   public void moveToInsertRow() throws SQLException {
/* 6261 */     throw new NotUpdatable();
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
/*      */   public boolean next() throws SQLException {
/* 6279 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       boolean b;
/* 6281 */       if (this.onInsertRow) {
/* 6282 */         this.onInsertRow = false;
/*      */       }
/*      */       
/* 6285 */       if (this.doingUpdates) {
/* 6286 */         this.doingUpdates = false;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 6291 */       if (!reallyResult()) {
/* 6292 */         throw SQLError.createSQLException(Messages.getString("ResultSet.ResultSet_is_from_UPDATE._No_Data_115"), "S1000", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/* 6296 */       if (this.thisRow != null) {
/* 6297 */         this.thisRow.closeOpenStreams();
/*      */       }
/*      */       
/* 6300 */       if (this.rowData.size() == 0) {
/* 6301 */         b = false;
/*      */       } else {
/* 6303 */         this.thisRow = this.rowData.next();
/*      */         
/* 6305 */         if (this.thisRow == null) {
/* 6306 */           b = false;
/*      */         } else {
/* 6308 */           clearWarnings();
/*      */           
/* 6310 */           b = true;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 6315 */       setRowPositionValidity();
/*      */       
/* 6317 */       return b;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int parseIntAsDouble(int columnIndex, String val) throws NumberFormatException, SQLException {
/* 6322 */     if (val == null) {
/* 6323 */       return 0;
/*      */     }
/*      */     
/* 6326 */     double valueAsDouble = Double.parseDouble(val);
/*      */     
/* 6328 */     if (this.jdbcCompliantTruncationForReads && (
/* 6329 */       valueAsDouble < -2.147483648E9D || valueAsDouble > 2.147483647E9D)) {
/* 6330 */       throwRangeException(String.valueOf(valueAsDouble), columnIndex, 4);
/*      */     }
/*      */ 
/*      */     
/* 6334 */     return (int)valueAsDouble;
/*      */   }
/*      */   
/*      */   private int getIntWithOverflowCheck(int columnIndex) throws SQLException {
/* 6338 */     int intValue = this.thisRow.getInt(columnIndex);
/*      */     
/* 6340 */     checkForIntegerTruncation(columnIndex, null, intValue);
/*      */     
/* 6342 */     return intValue;
/*      */   }
/*      */   
/*      */   private void checkForIntegerTruncation(int columnIndex, byte[] valueAsBytes, int intValue) throws SQLException {
/* 6346 */     if (this.jdbcCompliantTruncationForReads && (
/* 6347 */       intValue == Integer.MIN_VALUE || intValue == Integer.MAX_VALUE)) {
/* 6348 */       String valueAsString = null;
/*      */       
/* 6350 */       if (valueAsBytes == null) {
/* 6351 */         valueAsString = this.thisRow.getString(columnIndex, this.fields[columnIndex].getEncoding(), this.connection);
/*      */       }
/*      */       
/* 6354 */       long valueAsLong = Long.parseLong((valueAsString == null) ? StringUtils.toString(valueAsBytes) : valueAsString);
/*      */       
/* 6356 */       if (valueAsLong < -2147483648L || valueAsLong > 2147483647L) {
/* 6357 */         throwRangeException((valueAsString == null) ? StringUtils.toString(valueAsBytes) : valueAsString, columnIndex + 1, 4);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private long parseLongAsDouble(int columnIndexZeroBased, String val) throws NumberFormatException, SQLException {
/* 6364 */     if (val == null) {
/* 6365 */       return 0L;
/*      */     }
/*      */     
/* 6368 */     double valueAsDouble = Double.parseDouble(val);
/*      */     
/* 6370 */     if (this.jdbcCompliantTruncationForReads && (
/* 6371 */       valueAsDouble < -9.223372036854776E18D || valueAsDouble > 9.223372036854776E18D)) {
/* 6372 */       throwRangeException(val, columnIndexZeroBased + 1, -5);
/*      */     }
/*      */ 
/*      */     
/* 6376 */     return (long)valueAsDouble;
/*      */   }
/*      */   
/*      */   private long getLongWithOverflowCheck(int columnIndexZeroBased, boolean doOverflowCheck) throws SQLException {
/* 6380 */     long longValue = this.thisRow.getLong(columnIndexZeroBased);
/*      */     
/* 6382 */     if (doOverflowCheck) {
/* 6383 */       checkForLongTruncation(columnIndexZeroBased, null, longValue);
/*      */     }
/*      */     
/* 6386 */     return longValue;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private long parseLongWithOverflowCheck(int columnIndexZeroBased, byte[] valueAsBytes, String valueAsString, boolean doCheck) throws NumberFormatException, SQLException {
/* 6392 */     long longValue = 0L;
/*      */     
/* 6394 */     if (valueAsBytes == null && valueAsString == null) {
/* 6395 */       return 0L;
/*      */     }
/*      */     
/* 6398 */     if (valueAsBytes != null) {
/* 6399 */       longValue = StringUtils.getLong(valueAsBytes);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 6408 */       valueAsString = valueAsString.trim();
/*      */       
/* 6410 */       longValue = Long.parseLong(valueAsString);
/*      */     } 
/*      */     
/* 6413 */     if (doCheck && this.jdbcCompliantTruncationForReads) {
/* 6414 */       checkForLongTruncation(columnIndexZeroBased, valueAsBytes, longValue);
/*      */     }
/*      */     
/* 6417 */     return longValue;
/*      */   }
/*      */   
/*      */   private void checkForLongTruncation(int columnIndexZeroBased, byte[] valueAsBytes, long longValue) throws SQLException {
/* 6421 */     if (longValue == Long.MIN_VALUE || longValue == Long.MAX_VALUE) {
/* 6422 */       String valueAsString = null;
/*      */       
/* 6424 */       if (valueAsBytes == null) {
/* 6425 */         valueAsString = this.thisRow.getString(columnIndexZeroBased, this.fields[columnIndexZeroBased].getEncoding(), this.connection);
/*      */       }
/*      */       
/* 6428 */       double valueAsDouble = Double.parseDouble((valueAsString == null) ? StringUtils.toString(valueAsBytes) : valueAsString);
/*      */       
/* 6430 */       if (valueAsDouble < -9.223372036854776E18D || valueAsDouble > 9.223372036854776E18D) {
/* 6431 */         throwRangeException((valueAsString == null) ? StringUtils.toString(valueAsBytes) : valueAsString, columnIndexZeroBased + 1, -5);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private short parseShortAsDouble(int columnIndex, String val) throws NumberFormatException, SQLException {
/* 6437 */     if (val == null) {
/* 6438 */       return 0;
/*      */     }
/*      */     
/* 6441 */     double valueAsDouble = Double.parseDouble(val);
/*      */     
/* 6443 */     if (this.jdbcCompliantTruncationForReads && (
/* 6444 */       valueAsDouble < -32768.0D || valueAsDouble > 32767.0D)) {
/* 6445 */       throwRangeException(String.valueOf(valueAsDouble), columnIndex, 5);
/*      */     }
/*      */ 
/*      */     
/* 6449 */     return (short)(int)valueAsDouble;
/*      */   }
/*      */ 
/*      */   
/*      */   private short parseShortWithOverflowCheck(int columnIndex, byte[] valueAsBytes, String valueAsString) throws NumberFormatException, SQLException {
/* 6454 */     short shortValue = 0;
/*      */     
/* 6456 */     if (valueAsBytes == null && valueAsString == null) {
/* 6457 */       return 0;
/*      */     }
/*      */     
/* 6460 */     if (valueAsBytes != null) {
/* 6461 */       shortValue = StringUtils.getShort(valueAsBytes);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 6470 */       valueAsString = valueAsString.trim();
/*      */       
/* 6472 */       shortValue = Short.parseShort(valueAsString);
/*      */     } 
/*      */     
/* 6475 */     if (this.jdbcCompliantTruncationForReads && (
/* 6476 */       shortValue == Short.MIN_VALUE || shortValue == Short.MAX_VALUE)) {
/* 6477 */       long valueAsLong = Long.parseLong((valueAsString == null) ? StringUtils.toString(valueAsBytes) : valueAsString);
/*      */       
/* 6479 */       if (valueAsLong < -32768L || valueAsLong > 32767L) {
/* 6480 */         throwRangeException((valueAsString == null) ? StringUtils.toString(valueAsBytes) : valueAsString, columnIndex, 5);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 6485 */     return shortValue;
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
/*      */   public boolean prev() throws SQLException {
/* 6508 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/* 6510 */       int rowIndex = this.rowData.getCurrentRowNumber();
/*      */       
/* 6512 */       if (this.thisRow != null) {
/* 6513 */         this.thisRow.closeOpenStreams();
/*      */       }
/*      */       
/* 6516 */       boolean b = true;
/*      */       
/* 6518 */       if (rowIndex - 1 >= 0) {
/* 6519 */         rowIndex--;
/* 6520 */         this.rowData.setCurrentRow(rowIndex);
/* 6521 */         this.thisRow = this.rowData.getAt(rowIndex);
/*      */         
/* 6523 */         b = true;
/* 6524 */       } else if (rowIndex - 1 == -1) {
/* 6525 */         rowIndex--;
/* 6526 */         this.rowData.setCurrentRow(rowIndex);
/* 6527 */         this.thisRow = null;
/*      */         
/* 6529 */         b = false;
/*      */       } else {
/* 6531 */         b = false;
/*      */       } 
/*      */       
/* 6534 */       setRowPositionValidity();
/*      */       
/* 6536 */       return b;
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
/*      */   public boolean previous() throws SQLException {
/* 6558 */     synchronized (checkClosed().getConnectionMutex()) {
/* 6559 */       if (this.onInsertRow) {
/* 6560 */         this.onInsertRow = false;
/*      */       }
/*      */       
/* 6563 */       if (this.doingUpdates) {
/* 6564 */         this.doingUpdates = false;
/*      */       }
/*      */       
/* 6567 */       return prev();
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
/*      */   public void realClose(boolean calledExplicitly) throws SQLException {
/* 6582 */     MySQLConnection locallyScopedConn = this.connection;
/*      */     
/* 6584 */     if (locallyScopedConn == null) {
/*      */       return;
/*      */     }
/*      */     
/* 6588 */     synchronized (locallyScopedConn.getConnectionMutex()) {
/*      */ 
/*      */       
/* 6591 */       if (this.isClosed) {
/*      */         return;
/*      */       }
/*      */       
/*      */       try {
/* 6596 */         if (this.useUsageAdvisor) {
/*      */           
/* 6598 */           if (!calledExplicitly) {
/* 6599 */             this.connection.getProfilerEventHandlerInstance().processEvent((byte)0, this.connection, this.owningStatement, this, 0L, new Throwable(), Messages.getString("ResultSet.ResultSet_implicitly_closed_by_driver"));
/*      */           }
/*      */ 
/*      */           
/* 6603 */           if (this.rowData instanceof RowDataStatic) {
/* 6604 */             if (this.rowData != null && this.rowData.size() > this.connection.getResultSetSizeThreshold()) {
/* 6605 */               this.connection.getProfilerEventHandlerInstance().processEvent((byte)0, this.connection, this.owningStatement, this, 0L, new Throwable(), Messages.getString("ResultSet.Too_Large_Result_Set", new Object[] { Integer.valueOf(this.rowData.size()), Integer.valueOf(this.connection.getResultSetSizeThreshold()) }));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 6610 */             if (this.rowData != null && this.rowData.size() != 0 && !this.rowData.isLast() && !this.rowData.isAfterLast()) {
/* 6611 */               this.connection.getProfilerEventHandlerInstance().processEvent((byte)0, this.connection, this.owningStatement, this, 0L, new Throwable(), Messages.getString("ResultSet.Possible_incomplete_traversal_of_result_set", new Object[] { Integer.valueOf(getRow()), Integer.valueOf(this.rowData.size()) }));
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 6617 */           if (this.rowData != null && this.columnUsed.length > 0 && !this.rowData.wasEmpty()) {
/* 6618 */             StringBuilder buf = new StringBuilder();
/* 6619 */             for (int i = 0; i < this.columnUsed.length; i++) {
/* 6620 */               if (!this.columnUsed[i]) {
/* 6621 */                 if (buf.length() > 0) {
/* 6622 */                   buf.append(", ");
/*      */                 }
/* 6624 */                 buf.append(this.fields[i].getFullName());
/*      */               } 
/*      */             } 
/*      */             
/* 6628 */             if (buf.length() > 0) {
/* 6629 */               this.connection.getProfilerEventHandlerInstance().processEvent((byte)0, this.connection, this.owningStatement, this, 0L, new Throwable(), Messages.getString("ResultSet.The_following_columns_were_never_referenced", (Object[])new String[] { buf.toString() }));
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*      */       } finally {
/*      */         
/* 6636 */         if (this.owningStatement != null && calledExplicitly) {
/* 6637 */           this.owningStatement.removeOpenResultSet(this);
/*      */         }
/*      */         
/* 6640 */         SQLException exceptionDuringClose = null;
/*      */         
/* 6642 */         if (this.rowData != null) {
/*      */           try {
/* 6644 */             this.rowData.close();
/* 6645 */           } catch (SQLException sqlEx) {
/* 6646 */             exceptionDuringClose = sqlEx;
/*      */           } 
/*      */         }
/*      */         
/* 6650 */         if (this.statementUsedForFetchingRows != null) {
/*      */           try {
/* 6652 */             this.statementUsedForFetchingRows.realClose(true, false);
/* 6653 */           } catch (SQLException sqlEx) {
/* 6654 */             if (exceptionDuringClose != null) {
/* 6655 */               exceptionDuringClose.setNextException(sqlEx);
/*      */             } else {
/* 6657 */               exceptionDuringClose = sqlEx;
/*      */             } 
/*      */           } 
/*      */         }
/*      */         
/* 6662 */         this.rowData = null;
/* 6663 */         this.fields = null;
/* 6664 */         this.columnLabelToIndex = null;
/* 6665 */         this.fullColumnNameToIndex = null;
/* 6666 */         this.columnToIndexCache = null;
/* 6667 */         this.warningChain = null;
/*      */         
/* 6669 */         if (!this.retainOwningStatement) {
/* 6670 */           this.owningStatement = null;
/*      */         }
/*      */         
/* 6673 */         this.catalog = null;
/* 6674 */         this.serverInfo = null;
/* 6675 */         this.thisRow = null;
/* 6676 */         this.fastDefaultCal = null;
/* 6677 */         this.fastClientCal = null;
/* 6678 */         this.connection = null;
/*      */         
/* 6680 */         this.isClosed = true;
/*      */         
/* 6682 */         if (exceptionDuringClose != null) {
/* 6683 */           throw exceptionDuringClose;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isClosed() throws SQLException {
/* 6693 */     return this.isClosed;
/*      */   }
/*      */   
/*      */   public boolean reallyResult() {
/* 6697 */     if (this.rowData != null) {
/* 6698 */       return true;
/*      */     }
/*      */     
/* 6701 */     return this.reallyResult;
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
/*      */   public void refreshRow() throws SQLException {
/* 6724 */     throw new NotUpdatable();
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
/*      */   public boolean relative(int rows) throws SQLException {
/* 6750 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/* 6752 */       if (this.rowData.size() == 0) {
/* 6753 */         setRowPositionValidity();
/*      */         
/* 6755 */         return false;
/*      */       } 
/*      */       
/* 6758 */       if (this.thisRow != null) {
/* 6759 */         this.thisRow.closeOpenStreams();
/*      */       }
/*      */       
/* 6762 */       this.rowData.moveRowRelative(rows);
/* 6763 */       this.thisRow = this.rowData.getAt(this.rowData.getCurrentRowNumber());
/*      */       
/* 6765 */       setRowPositionValidity();
/*      */       
/* 6767 */       return (!this.rowData.isAfterLast() && !this.rowData.isBeforeFirst());
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
/*      */   public boolean rowDeleted() throws SQLException {
/* 6786 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   public boolean rowInserted() throws SQLException {
/* 6803 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   public boolean rowUpdated() throws SQLException {
/* 6820 */     throw SQLError.createSQLFeatureNotSupportedException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBinaryEncoded() {
/* 6828 */     this.isBinaryEncoded = true;
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
/*      */   public void setFetchDirection(int direction) throws SQLException {
/* 6847 */     synchronized (checkClosed().getConnectionMutex()) {
/* 6848 */       if (direction != 1000 && direction != 1001 && direction != 1002) {
/* 6849 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Illegal_value_for_fetch_direction_64"), "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/* 6853 */       this.fetchDirection = direction;
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
/*      */   public void setFetchSize(int rows) throws SQLException {
/* 6874 */     synchronized (checkClosed().getConnectionMutex()) {
/* 6875 */       if (rows < 0) {
/* 6876 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Value_must_be_between_0_and_getMaxRows()_66"), "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/* 6880 */       this.fetchSize = rows;
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
/*      */   public void setFirstCharOfQuery(char c) {
/*      */     try {
/* 6893 */       synchronized (checkClosed().getConnectionMutex()) {
/* 6894 */         this.firstCharOfQuery = c;
/*      */       } 
/* 6896 */     } catch (SQLException e) {
/* 6897 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected synchronized void setNextResultSet(ResultSetInternalMethods nextResultSet) {
/* 6907 */     this.nextResultSet = nextResultSet;
/*      */   }
/*      */   
/*      */   public void setOwningStatement(StatementImpl owningStatement) {
/*      */     try {
/* 6912 */       synchronized (checkClosed().getConnectionMutex()) {
/* 6913 */         this.owningStatement = owningStatement;
/*      */       } 
/* 6915 */     } catch (SQLException e) {
/* 6916 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected synchronized void setResultSetConcurrency(int concurrencyFlag) {
/*      */     try {
/* 6928 */       synchronized (checkClosed().getConnectionMutex()) {
/* 6929 */         this.resultSetConcurrency = concurrencyFlag;
/*      */       } 
/* 6931 */     } catch (SQLException e) {
/* 6932 */       throw new RuntimeException(e);
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
/*      */   protected synchronized void setResultSetType(int typeFlag) {
/*      */     try {
/* 6945 */       synchronized (checkClosed().getConnectionMutex()) {
/* 6946 */         this.resultSetType = typeFlag;
/*      */       } 
/* 6948 */     } catch (SQLException e) {
/* 6949 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setServerInfo(String info) {
/*      */     try {
/* 6961 */       synchronized (checkClosed().getConnectionMutex()) {
/* 6962 */         this.serverInfo = info;
/*      */       } 
/* 6964 */     } catch (SQLException e) {
/* 6965 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void setStatementUsedForFetchingRows(PreparedStatement stmt) {
/*      */     try {
/* 6971 */       synchronized (checkClosed().getConnectionMutex()) {
/* 6972 */         this.statementUsedForFetchingRows = stmt;
/*      */       } 
/* 6974 */     } catch (SQLException e) {
/* 6975 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void setWrapperStatement(Statement wrapperStatement) {
/*      */     try {
/* 6985 */       synchronized (checkClosed().getConnectionMutex()) {
/* 6986 */         this.wrapperStatement = wrapperStatement;
/*      */       } 
/* 6988 */     } catch (SQLException e) {
/* 6989 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void throwRangeException(String valueAsString, int columnIndex, int jdbcType) throws SQLException {
/* 6994 */     String datatype = null;
/*      */     
/* 6996 */     switch (jdbcType)
/*      */     { case -6:
/* 6998 */         datatype = "TINYINT";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 7025 */         throw SQLError.createSQLException("'" + valueAsString + "' in column '" + columnIndex + "' is outside valid range for the datatype " + datatype + ".", "22003", getExceptionInterceptor());case 5: datatype = "SMALLINT"; throw SQLError.createSQLException("'" + valueAsString + "' in column '" + columnIndex + "' is outside valid range for the datatype " + datatype + ".", "22003", getExceptionInterceptor());case 4: datatype = "INTEGER"; throw SQLError.createSQLException("'" + valueAsString + "' in column '" + columnIndex + "' is outside valid range for the datatype " + datatype + ".", "22003", getExceptionInterceptor());case -5: datatype = "BIGINT"; throw SQLError.createSQLException("'" + valueAsString + "' in column '" + columnIndex + "' is outside valid range for the datatype " + datatype + ".", "22003", getExceptionInterceptor());case 7: datatype = "REAL"; throw SQLError.createSQLException("'" + valueAsString + "' in column '" + columnIndex + "' is outside valid range for the datatype " + datatype + ".", "22003", getExceptionInterceptor());case 6: datatype = "FLOAT"; throw SQLError.createSQLException("'" + valueAsString + "' in column '" + columnIndex + "' is outside valid range for the datatype " + datatype + ".", "22003", getExceptionInterceptor());case 8: datatype = "DOUBLE"; throw SQLError.createSQLException("'" + valueAsString + "' in column '" + columnIndex + "' is outside valid range for the datatype " + datatype + ".", "22003", getExceptionInterceptor());case 3: datatype = "DECIMAL"; throw SQLError.createSQLException("'" + valueAsString + "' in column '" + columnIndex + "' is outside valid range for the datatype " + datatype + ".", "22003", getExceptionInterceptor()); }  datatype = " (JDBC type '" + jdbcType + "')"; throw SQLError.createSQLException("'" + valueAsString + "' in column '" + columnIndex + "' is outside valid range for the datatype " + datatype + ".", "22003", getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 7031 */     if (this.reallyResult) {
/* 7032 */       return super.toString();
/*      */     }
/*      */     
/* 7035 */     return "Result set representing update count of " + this.updateCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateArray(int arg0, Array arg1) throws SQLException {
/* 7042 */     throw SQLError.createSQLFeatureNotSupportedException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateArray(String arg0, Array arg1) throws SQLException {
/* 7049 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
/* 7071 */     throw new NotUpdatable();
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
/*      */   public void updateAsciiStream(String columnName, InputStream x, int length) throws SQLException {
/* 7092 */     updateAsciiStream(findColumn(columnName), x, length);
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
/*      */   public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
/* 7111 */     throw new NotUpdatable();
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
/*      */   public void updateBigDecimal(String columnName, BigDecimal x) throws SQLException {
/* 7129 */     updateBigDecimal(findColumn(columnName), x);
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
/*      */   public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
/* 7151 */     throw new NotUpdatable();
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
/*      */   public void updateBinaryStream(String columnName, InputStream x, int length) throws SQLException {
/* 7172 */     updateBinaryStream(findColumn(columnName), x, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateBlob(int arg0, Blob arg1) throws SQLException {
/* 7179 */     throw new NotUpdatable();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateBlob(String arg0, Blob arg1) throws SQLException {
/* 7186 */     throw new NotUpdatable();
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
/*      */   public void updateBoolean(int columnIndex, boolean x) throws SQLException {
/* 7205 */     throw new NotUpdatable();
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
/*      */   public void updateBoolean(String columnName, boolean x) throws SQLException {
/* 7223 */     updateBoolean(findColumn(columnName), x);
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
/*      */   public void updateByte(int columnIndex, byte x) throws SQLException {
/* 7242 */     throw new NotUpdatable();
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
/*      */   public void updateByte(String columnName, byte x) throws SQLException {
/* 7260 */     updateByte(findColumn(columnName), x);
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
/*      */   public void updateBytes(int columnIndex, byte[] x) throws SQLException {
/* 7279 */     throw new NotUpdatable();
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
/*      */   public void updateBytes(String columnName, byte[] x) throws SQLException {
/* 7297 */     updateBytes(findColumn(columnName), x);
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
/*      */   public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
/* 7319 */     throw new NotUpdatable();
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
/*      */   public void updateCharacterStream(String columnName, Reader reader, int length) throws SQLException {
/* 7340 */     updateCharacterStream(findColumn(columnName), reader, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateClob(int arg0, Clob arg1) throws SQLException {
/* 7347 */     throw SQLError.createSQLFeatureNotSupportedException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateClob(String columnName, Clob clob) throws SQLException {
/* 7354 */     updateClob(findColumn(columnName), clob);
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
/*      */   public void updateDate(int columnIndex, Date x) throws SQLException {
/* 7373 */     throw new NotUpdatable();
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
/*      */   public void updateDate(String columnName, Date x) throws SQLException {
/* 7391 */     updateDate(findColumn(columnName), x);
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
/*      */   public void updateDouble(int columnIndex, double x) throws SQLException {
/* 7410 */     throw new NotUpdatable();
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
/*      */   public void updateDouble(String columnName, double x) throws SQLException {
/* 7428 */     updateDouble(findColumn(columnName), x);
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
/*      */   public void updateFloat(int columnIndex, float x) throws SQLException {
/* 7447 */     throw new NotUpdatable();
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
/*      */   public void updateFloat(String columnName, float x) throws SQLException {
/* 7465 */     updateFloat(findColumn(columnName), x);
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
/*      */   public void updateInt(int columnIndex, int x) throws SQLException {
/* 7484 */     throw new NotUpdatable();
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
/*      */   public void updateInt(String columnName, int x) throws SQLException {
/* 7502 */     updateInt(findColumn(columnName), x);
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
/*      */   public void updateLong(int columnIndex, long x) throws SQLException {
/* 7521 */     throw new NotUpdatable();
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
/*      */   public void updateLong(String columnName, long x) throws SQLException {
/* 7539 */     updateLong(findColumn(columnName), x);
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
/*      */   public void updateNull(int columnIndex) throws SQLException {
/* 7556 */     throw new NotUpdatable();
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
/*      */   public void updateNull(String columnName) throws SQLException {
/* 7572 */     updateNull(findColumn(columnName));
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
/*      */   public void updateObject(int columnIndex, Object x) throws SQLException {
/* 7591 */     throw new NotUpdatable();
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
/*      */   public void updateObject(int columnIndex, Object x, int scale) throws SQLException {
/* 7614 */     throw new NotUpdatable();
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
/*      */   public void updateObject(String columnName, Object x) throws SQLException {
/* 7632 */     updateObject(findColumn(columnName), x);
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
/*      */   public void updateObject(String columnName, Object x, int scale) throws SQLException {
/* 7654 */     updateObject(findColumn(columnName), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRef(int arg0, Ref arg1) throws SQLException {
/* 7661 */     throw SQLError.createSQLFeatureNotSupportedException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRef(String arg0, Ref arg1) throws SQLException {
/* 7668 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   public void updateRow() throws SQLException {
/* 7681 */     throw new NotUpdatable();
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
/*      */   public void updateShort(int columnIndex, short x) throws SQLException {
/* 7700 */     throw new NotUpdatable();
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
/*      */   public void updateShort(String columnName, short x) throws SQLException {
/* 7718 */     updateShort(findColumn(columnName), x);
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
/*      */   public void updateString(int columnIndex, String x) throws SQLException {
/* 7737 */     throw new NotUpdatable();
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
/*      */   public void updateString(String columnName, String x) throws SQLException {
/* 7755 */     updateString(findColumn(columnName), x);
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
/*      */   public void updateTime(int columnIndex, Time x) throws SQLException {
/* 7774 */     throw new NotUpdatable();
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
/*      */   public void updateTime(String columnName, Time x) throws SQLException {
/* 7792 */     updateTime(findColumn(columnName), x);
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
/*      */   public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
/* 7811 */     throw new NotUpdatable();
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
/*      */   public void updateTimestamp(String columnName, Timestamp x) throws SQLException {
/* 7829 */     updateTimestamp(findColumn(columnName), x);
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
/*      */   public boolean wasNull() throws SQLException {
/* 7844 */     return this.wasNullFlag;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Calendar getGmtCalendar() {
/* 7850 */     if (this.gmtCalendar == null) {
/* 7851 */       this.gmtCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
/*      */     }
/*      */     
/* 7854 */     return this.gmtCalendar;
/*      */   }
/*      */   
/*      */   protected ExceptionInterceptor getExceptionInterceptor() {
/* 7858 */     return this.exceptionInterceptor;
/*      */   }
/*      */   
/*      */   public int getId() {
/* 7862 */     return this.resultId;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ResultSetImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */