/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.Date;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
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
/*      */ public class UpdatableResultSet
/*      */   extends ResultSetImpl
/*      */ {
/*   42 */   static final byte[] STREAM_DATA_MARKER = StringUtils.getBytes("** STREAM DATA **");
/*      */ 
/*      */   
/*      */   protected SingleByteCharsetConverter charConverter;
/*      */ 
/*      */   
/*      */   private String charEncoding;
/*      */   
/*      */   private byte[][] defaultColumnValue;
/*      */   
/*   52 */   private PreparedStatement deleter = null;
/*      */   
/*   54 */   private String deleteSQL = null;
/*      */ 
/*      */   
/*      */   private boolean initializedCharConverter = false;
/*      */   
/*   59 */   protected PreparedStatement inserter = null;
/*      */   
/*   61 */   private String insertSQL = null;
/*      */ 
/*      */   
/*      */   private boolean isUpdatable = false;
/*      */ 
/*      */   
/*   67 */   private String notUpdatableReason = null;
/*      */ 
/*      */   
/*   70 */   private List<Integer> primaryKeyIndicies = null;
/*      */   
/*      */   private String qualifiedAndQuotedTableName;
/*      */   
/*   74 */   private String quotedIdChar = null;
/*      */ 
/*      */   
/*      */   private PreparedStatement refresher;
/*      */   
/*   79 */   private String refreshSQL = null;
/*      */ 
/*      */   
/*      */   private ResultSetRow savedCurrentRow;
/*      */ 
/*      */   
/*   85 */   protected PreparedStatement updater = null;
/*      */ 
/*      */   
/*   88 */   private String updateSQL = null;
/*      */   
/*      */   private boolean populateInserterWithDefaultValues = false;
/*      */   
/*   92 */   private Map<String, Map<String, Map<String, Integer>>> databasesUsedToTablesUsed = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected UpdatableResultSet(String catalog, Field[] fields, RowData tuples, MySQLConnection conn, StatementImpl creatorStmt) throws SQLException {
/*  110 */     super(catalog, fields, tuples, conn, creatorStmt);
/*  111 */     checkUpdatability();
/*  112 */     this.populateInserterWithDefaultValues = this.connection.getPopulateInsertRowWithDefaultValues();
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
/*  149 */     return super.absolute(row);
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
/*      */   public void afterLast() throws SQLException {
/*  165 */     super.afterLast();
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
/*      */   public void beforeFirst() throws SQLException {
/*  181 */     super.beforeFirst();
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
/*  196 */     synchronized (checkClosed().getConnectionMutex()) {
/*  197 */       if (this.doingUpdates) {
/*  198 */         this.doingUpdates = false;
/*  199 */         this.updater.clearParameters();
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
/*      */   protected void checkRowPos() throws SQLException {
/*  211 */     synchronized (checkClosed().getConnectionMutex()) {
/*  212 */       if (!this.onInsertRow) {
/*  213 */         super.checkRowPos();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkUpdatability() throws SQLException {
/*      */     try {
/*  225 */       if (this.fields == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  232 */       String singleTableName = null;
/*  233 */       String catalogName = null;
/*      */       
/*  235 */       int primaryKeyCount = 0;
/*      */ 
/*      */ 
/*      */       
/*  239 */       if (this.catalog == null || this.catalog.length() == 0) {
/*  240 */         this.catalog = this.fields[0].getDatabaseName();
/*      */         
/*  242 */         if (this.catalog == null || this.catalog.length() == 0) {
/*  243 */           throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.43"), "S1009", getExceptionInterceptor());
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  248 */       if (this.fields.length > 0) {
/*  249 */         singleTableName = this.fields[0].getOriginalTableName();
/*  250 */         catalogName = this.fields[0].getDatabaseName();
/*      */         
/*  252 */         if (singleTableName == null) {
/*  253 */           singleTableName = this.fields[0].getTableName();
/*  254 */           catalogName = this.catalog;
/*      */         } 
/*      */         
/*  257 */         if (singleTableName != null && singleTableName.length() == 0) {
/*  258 */           this.isUpdatable = false;
/*  259 */           this.notUpdatableReason = Messages.getString("NotUpdatableReason.3");
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/*  264 */         if (this.fields[0].isPrimaryKey()) {
/*  265 */           primaryKeyCount++;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  271 */         for (int i = 1; i < this.fields.length; i++) {
/*  272 */           String otherTableName = this.fields[i].getOriginalTableName();
/*  273 */           String otherCatalogName = this.fields[i].getDatabaseName();
/*      */           
/*  275 */           if (otherTableName == null) {
/*  276 */             otherTableName = this.fields[i].getTableName();
/*  277 */             otherCatalogName = this.catalog;
/*      */           } 
/*      */           
/*  280 */           if (otherTableName != null && otherTableName.length() == 0) {
/*  281 */             this.isUpdatable = false;
/*  282 */             this.notUpdatableReason = Messages.getString("NotUpdatableReason.3");
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/*  287 */           if (singleTableName == null || !otherTableName.equals(singleTableName)) {
/*  288 */             this.isUpdatable = false;
/*  289 */             this.notUpdatableReason = Messages.getString("NotUpdatableReason.0");
/*      */ 
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/*  295 */           if (catalogName == null || !otherCatalogName.equals(catalogName)) {
/*  296 */             this.isUpdatable = false;
/*  297 */             this.notUpdatableReason = Messages.getString("NotUpdatableReason.1");
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/*  302 */           if (this.fields[i].isPrimaryKey()) {
/*  303 */             primaryKeyCount++;
/*      */           }
/*      */         } 
/*      */         
/*  307 */         if (singleTableName == null || singleTableName.length() == 0) {
/*  308 */           this.isUpdatable = false;
/*  309 */           this.notUpdatableReason = Messages.getString("NotUpdatableReason.2");
/*      */           
/*      */           return;
/*      */         } 
/*      */       } else {
/*  314 */         this.isUpdatable = false;
/*  315 */         this.notUpdatableReason = Messages.getString("NotUpdatableReason.3");
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  320 */       if (this.connection.getStrictUpdates()) {
/*  321 */         DatabaseMetaData dbmd = this.connection.getMetaData();
/*      */         
/*  323 */         ResultSet rs = null;
/*  324 */         HashMap<String, String> primaryKeyNames = new HashMap<String, String>();
/*      */         
/*      */         try {
/*  327 */           rs = dbmd.getPrimaryKeys(catalogName, null, singleTableName);
/*      */           
/*  329 */           while (rs.next()) {
/*  330 */             String keyName = rs.getString(4);
/*  331 */             keyName = keyName.toUpperCase();
/*  332 */             primaryKeyNames.put(keyName, keyName);
/*      */           } 
/*      */         } finally {
/*  335 */           if (rs != null) {
/*      */             try {
/*  337 */               rs.close();
/*  338 */             } catch (Exception ex) {
/*  339 */               AssertionFailedException.shouldNotHappen(ex);
/*      */             } 
/*      */             
/*  342 */             rs = null;
/*      */           } 
/*      */         } 
/*      */         
/*  346 */         int existingPrimaryKeysCount = primaryKeyNames.size();
/*      */         
/*  348 */         if (existingPrimaryKeysCount == 0) {
/*  349 */           this.isUpdatable = false;
/*  350 */           this.notUpdatableReason = Messages.getString("NotUpdatableReason.5");
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/*  358 */         for (int i = 0; i < this.fields.length; i++) {
/*  359 */           if (this.fields[i].isPrimaryKey()) {
/*  360 */             String columnNameUC = this.fields[i].getName().toUpperCase();
/*      */             
/*  362 */             if (primaryKeyNames.remove(columnNameUC) == null) {
/*      */               
/*  364 */               String originalName = this.fields[i].getOriginalName();
/*      */               
/*  366 */               if (originalName != null && 
/*  367 */                 primaryKeyNames.remove(originalName.toUpperCase()) == null) {
/*      */                 
/*  369 */                 this.isUpdatable = false;
/*  370 */                 this.notUpdatableReason = Messages.getString("NotUpdatableReason.6", new Object[] { originalName });
/*      */ 
/*      */                 
/*      */                 return;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/*  379 */         this.isUpdatable = primaryKeyNames.isEmpty();
/*      */         
/*  381 */         if (!this.isUpdatable) {
/*  382 */           if (existingPrimaryKeysCount > 1) {
/*  383 */             this.notUpdatableReason = Messages.getString("NotUpdatableReason.7");
/*      */           } else {
/*  385 */             this.notUpdatableReason = Messages.getString("NotUpdatableReason.4");
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  395 */       if (primaryKeyCount == 0) {
/*  396 */         this.isUpdatable = false;
/*  397 */         this.notUpdatableReason = Messages.getString("NotUpdatableReason.4");
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  402 */       this.isUpdatable = true;
/*  403 */       this.notUpdatableReason = null;
/*      */       
/*      */       return;
/*  406 */     } catch (SQLException sqlEx) {
/*  407 */       this.isUpdatable = false;
/*  408 */       this.notUpdatableReason = sqlEx.getMessage();
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
/*      */   public void deleteRow() throws SQLException {
/*  424 */     synchronized (checkClosed().getConnectionMutex()) {
/*  425 */       if (!this.isUpdatable) {
/*  426 */         throw new NotUpdatable(this.notUpdatableReason);
/*      */       }
/*      */       
/*  429 */       if (this.onInsertRow)
/*  430 */         throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.1"), getExceptionInterceptor()); 
/*  431 */       if (this.rowData.size() == 0)
/*  432 */         throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.2"), getExceptionInterceptor()); 
/*  433 */       if (isBeforeFirst())
/*  434 */         throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.3"), getExceptionInterceptor()); 
/*  435 */       if (isAfterLast()) {
/*  436 */         throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.4"), getExceptionInterceptor());
/*      */       }
/*      */       
/*  439 */       if (this.deleter == null) {
/*  440 */         if (this.deleteSQL == null) {
/*  441 */           generateStatements();
/*      */         }
/*      */         
/*  444 */         this.deleter = (PreparedStatement)this.connection.clientPrepareStatement(this.deleteSQL);
/*      */       } 
/*      */       
/*  447 */       this.deleter.clearParameters();
/*      */       
/*  449 */       int numKeys = this.primaryKeyIndicies.size();
/*  450 */       for (int i = 0; i < numKeys; i++) {
/*  451 */         int index = ((Integer)this.primaryKeyIndicies.get(i)).intValue();
/*  452 */         setParamValue(this.deleter, i + 1, this.thisRow, index, this.fields[index]);
/*      */       } 
/*      */       
/*  455 */       this.deleter.executeUpdate();
/*  456 */       this.rowData.removeRow(this.rowData.getCurrentRowNumber());
/*      */ 
/*      */       
/*  459 */       previous();
/*      */     } 
/*      */   } private void setParamValue(PreparedStatement ps, int psIdx, ResultSetRow row, int rsIdx, Field field) throws SQLException {
/*      */     Field f;
/*      */     boolean useGmtMillis, useJdbcCompliantTimezoneShift;
/*  464 */     byte[] val = row.getColumnValue(rsIdx);
/*  465 */     if (val == null) {
/*  466 */       ps.setNull(psIdx, 0);
/*      */       return;
/*      */     } 
/*  469 */     switch (field.getSQLType()) {
/*      */       case 0:
/*  471 */         ps.setNull(psIdx, 0);
/*      */         return;
/*      */       case -6:
/*      */       case 4:
/*      */       case 5:
/*  476 */         ps.setInt(psIdx, row.getInt(rsIdx));
/*      */         return;
/*      */       case -5:
/*  479 */         ps.setLong(psIdx, row.getLong(rsIdx));
/*      */         return;
/*      */       case -1:
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 12:
/*  486 */         f = this.fields[rsIdx];
/*  487 */         ps.setString(psIdx, row.getString(rsIdx, f.getEncoding(), this.connection));
/*      */         return;
/*      */       case 91:
/*  490 */         ps.setDate(psIdx, row.getDateFast(rsIdx, this.connection, this, this.fastDefaultCal), this.fastDefaultCal);
/*      */         return;
/*      */       case 93:
/*  493 */         useGmtMillis = false;
/*  494 */         useJdbcCompliantTimezoneShift = false;
/*  495 */         ps.setTimestampInternal(psIdx, row.getTimestampFast(rsIdx, this.fastDefaultCal, this.connection.getServerTimezoneTZ(), false, this.connection, this, useGmtMillis, useJdbcCompliantTimezoneShift), (Calendar)null, this.connection.getDefaultTimeZone(), false, field.getDecimals(), false);
/*      */         return;
/*      */ 
/*      */       
/*      */       case 92:
/*  500 */         ps.setTime(psIdx, row.getTimeFast(rsIdx, this.fastDefaultCal, this.connection.getServerTimezoneTZ(), false, this.connection, this));
/*      */         return;
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/*      */       case 16:
/*  506 */         ps.setBytesNoEscapeNoQuotes(psIdx, val);
/*      */         return;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  514 */     ps.setBytes(psIdx, val);
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
/*      */   private void extractDefaultValues() throws SQLException {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield connection : Lcom/mysql/jdbc/MySQLConnection;
/*      */     //   4: invokeinterface getMetaData : ()Ljava/sql/DatabaseMetaData;
/*      */     //   9: astore_1
/*      */     //   10: aload_0
/*      */     //   11: aload_0
/*      */     //   12: getfield fields : [Lcom/mysql/jdbc/Field;
/*      */     //   15: arraylength
/*      */     //   16: anewarray [B
/*      */     //   19: putfield defaultColumnValue : [[B
/*      */     //   22: aconst_null
/*      */     //   23: astore_2
/*      */     //   24: aload_0
/*      */     //   25: getfield databasesUsedToTablesUsed : Ljava/util/Map;
/*      */     //   28: invokeinterface entrySet : ()Ljava/util/Set;
/*      */     //   33: invokeinterface iterator : ()Ljava/util/Iterator;
/*      */     //   38: astore_3
/*      */     //   39: aload_3
/*      */     //   40: invokeinterface hasNext : ()Z
/*      */     //   45: ifeq -> 252
/*      */     //   48: aload_3
/*      */     //   49: invokeinterface next : ()Ljava/lang/Object;
/*      */     //   54: checkcast java/util/Map$Entry
/*      */     //   57: astore #4
/*      */     //   59: aload #4
/*      */     //   61: invokeinterface getValue : ()Ljava/lang/Object;
/*      */     //   66: checkcast java/util/Map
/*      */     //   69: invokeinterface entrySet : ()Ljava/util/Set;
/*      */     //   74: invokeinterface iterator : ()Ljava/util/Iterator;
/*      */     //   79: astore #5
/*      */     //   81: aload #5
/*      */     //   83: invokeinterface hasNext : ()Z
/*      */     //   88: ifeq -> 249
/*      */     //   91: aload #5
/*      */     //   93: invokeinterface next : ()Ljava/lang/Object;
/*      */     //   98: checkcast java/util/Map$Entry
/*      */     //   101: astore #6
/*      */     //   103: aload #6
/*      */     //   105: invokeinterface getKey : ()Ljava/lang/Object;
/*      */     //   110: checkcast java/lang/String
/*      */     //   113: astore #7
/*      */     //   115: aload #6
/*      */     //   117: invokeinterface getValue : ()Ljava/lang/Object;
/*      */     //   122: checkcast java/util/Map
/*      */     //   125: astore #8
/*      */     //   127: aload_1
/*      */     //   128: aload_0
/*      */     //   129: getfield catalog : Ljava/lang/String;
/*      */     //   132: aconst_null
/*      */     //   133: aload #7
/*      */     //   135: ldc_w '%'
/*      */     //   138: invokeinterface getColumns : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/sql/ResultSet;
/*      */     //   143: astore_2
/*      */     //   144: aload_2
/*      */     //   145: invokeinterface next : ()Z
/*      */     //   150: ifeq -> 216
/*      */     //   153: aload_2
/*      */     //   154: ldc_w 'COLUMN_NAME'
/*      */     //   157: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   162: astore #9
/*      */     //   164: aload_2
/*      */     //   165: ldc_w 'COLUMN_DEF'
/*      */     //   168: invokeinterface getBytes : (Ljava/lang/String;)[B
/*      */     //   173: astore #10
/*      */     //   175: aload #8
/*      */     //   177: aload #9
/*      */     //   179: invokeinterface containsKey : (Ljava/lang/Object;)Z
/*      */     //   184: ifeq -> 213
/*      */     //   187: aload #8
/*      */     //   189: aload #9
/*      */     //   191: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
/*      */     //   196: checkcast java/lang/Integer
/*      */     //   199: invokevirtual intValue : ()I
/*      */     //   202: istore #11
/*      */     //   204: aload_0
/*      */     //   205: getfield defaultColumnValue : [[B
/*      */     //   208: iload #11
/*      */     //   210: aload #10
/*      */     //   212: aastore
/*      */     //   213: goto -> 144
/*      */     //   216: jsr -> 230
/*      */     //   219: goto -> 246
/*      */     //   222: astore #12
/*      */     //   224: jsr -> 230
/*      */     //   227: aload #12
/*      */     //   229: athrow
/*      */     //   230: astore #13
/*      */     //   232: aload_2
/*      */     //   233: ifnull -> 244
/*      */     //   236: aload_2
/*      */     //   237: invokeinterface close : ()V
/*      */     //   242: aconst_null
/*      */     //   243: astore_2
/*      */     //   244: ret #13
/*      */     //   246: goto -> 81
/*      */     //   249: goto -> 39
/*      */     //   252: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #521	-> 0
/*      */     //   #522	-> 10
/*      */     //   #524	-> 22
/*      */     //   #526	-> 24
/*      */     //   #528	-> 59
/*      */     //   #529	-> 103
/*      */     //   #530	-> 115
/*      */     //   #533	-> 127
/*      */     //   #535	-> 144
/*      */     //   #536	-> 153
/*      */     //   #537	-> 164
/*      */     //   #539	-> 175
/*      */     //   #540	-> 187
/*      */     //   #542	-> 204
/*      */     //   #544	-> 213
/*      */     //   #545	-> 216
/*      */     //   #551	-> 219
/*      */     //   #546	-> 222
/*      */     //   #547	-> 236
/*      */     //   #549	-> 242
/*      */     //   #552	-> 246
/*      */     //   #554	-> 252
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   204	9	11	localColumnIndex	I
/*      */     //   164	49	9	columnName	Ljava/lang/String;
/*      */     //   175	38	10	defaultValue	[B
/*      */     //   115	131	7	tableName	Ljava/lang/String;
/*      */     //   127	119	8	columnNamesToIndices	Ljava/util/Map;
/*      */     //   103	143	6	tableEntry	Ljava/util/Map$Entry;
/*      */     //   81	168	5	i$	Ljava/util/Iterator;
/*      */     //   59	190	4	dbEntry	Ljava/util/Map$Entry;
/*      */     //   39	213	3	i$	Ljava/util/Iterator;
/*      */     //   0	253	0	this	Lcom/mysql/jdbc/UpdatableResultSet;
/*      */     //   10	243	1	dbmd	Ljava/sql/DatabaseMetaData;
/*      */     //   24	229	2	columnsResultSet	Ljava/sql/ResultSet;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   127	119	8	columnNamesToIndices	Ljava/util/Map<Ljava/lang/String;Ljava/lang/Integer;>;
/*      */     //   103	143	6	tableEntry	Ljava/util/Map$Entry<Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/Integer;>;>;
/*      */     //   59	190	4	dbEntry	Ljava/util/Map$Entry<Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/Integer;>;>;>;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   127	219	222	finally
/*      */     //   222	227	222	finally
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
/*      */   public boolean first() throws SQLException {
/*  571 */     return super.first();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void generateStatements() throws SQLException {
/*  582 */     if (!this.isUpdatable) {
/*  583 */       this.doingUpdates = false;
/*  584 */       this.onInsertRow = false;
/*      */       
/*  586 */       throw new NotUpdatable(this.notUpdatableReason);
/*      */     } 
/*      */     
/*  589 */     String quotedId = getQuotedIdChar();
/*      */     
/*  591 */     Map<String, String> tableNamesSoFar = null;
/*      */     
/*  593 */     if (this.connection.lowerCaseTableNames()) {
/*  594 */       tableNamesSoFar = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
/*  595 */       this.databasesUsedToTablesUsed = new TreeMap<String, Map<String, Map<String, Integer>>>(String.CASE_INSENSITIVE_ORDER);
/*      */     } else {
/*  597 */       tableNamesSoFar = new TreeMap<String, String>();
/*  598 */       this.databasesUsedToTablesUsed = new TreeMap<String, Map<String, Map<String, Integer>>>();
/*      */     } 
/*      */     
/*  601 */     this.primaryKeyIndicies = new ArrayList<Integer>();
/*      */     
/*  603 */     StringBuilder fieldValues = new StringBuilder();
/*  604 */     StringBuilder keyValues = new StringBuilder();
/*  605 */     StringBuilder columnNames = new StringBuilder();
/*  606 */     StringBuilder insertPlaceHolders = new StringBuilder();
/*  607 */     StringBuilder allTablesBuf = new StringBuilder();
/*  608 */     Map<Integer, String> columnIndicesToTable = new HashMap<Integer, String>();
/*      */     
/*  610 */     boolean firstTime = true;
/*  611 */     boolean keysFirstTime = true;
/*      */     
/*  613 */     String equalsStr = this.connection.versionMeetsMinimum(3, 23, 0) ? "<=>" : "=";
/*      */     
/*  615 */     for (int i = 0; i < this.fields.length; i++) {
/*  616 */       StringBuilder tableNameBuffer = new StringBuilder();
/*  617 */       Map<String, Integer> updColumnNameToIndex = null;
/*      */ 
/*      */       
/*  620 */       if (this.fields[i].getOriginalTableName() != null) {
/*      */         
/*  622 */         String str1 = this.fields[i].getDatabaseName();
/*      */         
/*  624 */         if (str1 != null && str1.length() > 0) {
/*  625 */           tableNameBuffer.append(quotedId);
/*  626 */           tableNameBuffer.append(str1);
/*  627 */           tableNameBuffer.append(quotedId);
/*  628 */           tableNameBuffer.append('.');
/*      */         } 
/*      */         
/*  631 */         String tableOnlyName = this.fields[i].getOriginalTableName();
/*      */         
/*  633 */         tableNameBuffer.append(quotedId);
/*  634 */         tableNameBuffer.append(tableOnlyName);
/*  635 */         tableNameBuffer.append(quotedId);
/*      */         
/*  637 */         String fqTableName = tableNameBuffer.toString();
/*      */         
/*  639 */         if (!tableNamesSoFar.containsKey(fqTableName)) {
/*  640 */           if (!tableNamesSoFar.isEmpty()) {
/*  641 */             allTablesBuf.append(',');
/*      */           }
/*      */           
/*  644 */           allTablesBuf.append(fqTableName);
/*  645 */           tableNamesSoFar.put(fqTableName, fqTableName);
/*      */         } 
/*      */         
/*  648 */         columnIndicesToTable.put(Integer.valueOf(i), fqTableName);
/*      */         
/*  650 */         updColumnNameToIndex = getColumnsToIndexMapForTableAndDB(str1, tableOnlyName);
/*      */       } else {
/*  652 */         String tableOnlyName = this.fields[i].getTableName();
/*      */         
/*  654 */         if (tableOnlyName != null) {
/*  655 */           tableNameBuffer.append(quotedId);
/*  656 */           tableNameBuffer.append(tableOnlyName);
/*  657 */           tableNameBuffer.append(quotedId);
/*      */           
/*  659 */           String fqTableName = tableNameBuffer.toString();
/*      */           
/*  661 */           if (!tableNamesSoFar.containsKey(fqTableName)) {
/*  662 */             if (!tableNamesSoFar.isEmpty()) {
/*  663 */               allTablesBuf.append(',');
/*      */             }
/*      */             
/*  666 */             allTablesBuf.append(fqTableName);
/*  667 */             tableNamesSoFar.put(fqTableName, fqTableName);
/*      */           } 
/*      */           
/*  670 */           columnIndicesToTable.put(Integer.valueOf(i), fqTableName);
/*      */           
/*  672 */           updColumnNameToIndex = getColumnsToIndexMapForTableAndDB(this.catalog, tableOnlyName);
/*      */         } 
/*      */       } 
/*      */       
/*  676 */       String originalColumnName = this.fields[i].getOriginalName();
/*  677 */       String columnName = null;
/*      */       
/*  679 */       if (this.connection.getIO().hasLongColumnInfo() && originalColumnName != null && originalColumnName.length() > 0) {
/*  680 */         columnName = originalColumnName;
/*      */       } else {
/*  682 */         columnName = this.fields[i].getName();
/*      */       } 
/*      */       
/*  685 */       if (updColumnNameToIndex != null && columnName != null) {
/*  686 */         updColumnNameToIndex.put(columnName, Integer.valueOf(i));
/*      */       }
/*      */       
/*  689 */       String originalTableName = this.fields[i].getOriginalTableName();
/*  690 */       String tableName = null;
/*      */       
/*  692 */       if (this.connection.getIO().hasLongColumnInfo() && originalTableName != null && originalTableName.length() > 0) {
/*  693 */         tableName = originalTableName;
/*      */       } else {
/*  695 */         tableName = this.fields[i].getTableName();
/*      */       } 
/*      */       
/*  698 */       StringBuilder fqcnBuf = new StringBuilder();
/*  699 */       String databaseName = this.fields[i].getDatabaseName();
/*      */       
/*  701 */       if (databaseName != null && databaseName.length() > 0) {
/*  702 */         fqcnBuf.append(quotedId);
/*  703 */         fqcnBuf.append(databaseName);
/*  704 */         fqcnBuf.append(quotedId);
/*  705 */         fqcnBuf.append('.');
/*      */       } 
/*      */       
/*  708 */       fqcnBuf.append(quotedId);
/*  709 */       fqcnBuf.append(tableName);
/*  710 */       fqcnBuf.append(quotedId);
/*  711 */       fqcnBuf.append('.');
/*  712 */       fqcnBuf.append(quotedId);
/*  713 */       fqcnBuf.append(columnName);
/*  714 */       fqcnBuf.append(quotedId);
/*      */       
/*  716 */       String qualifiedColumnName = fqcnBuf.toString();
/*      */       
/*  718 */       if (this.fields[i].isPrimaryKey()) {
/*  719 */         this.primaryKeyIndicies.add(Integer.valueOf(i));
/*      */         
/*  721 */         if (!keysFirstTime) {
/*  722 */           keyValues.append(" AND ");
/*      */         } else {
/*  724 */           keysFirstTime = false;
/*      */         } 
/*      */         
/*  727 */         keyValues.append(qualifiedColumnName);
/*  728 */         keyValues.append(equalsStr);
/*  729 */         keyValues.append("?");
/*      */       } 
/*      */       
/*  732 */       if (firstTime) {
/*  733 */         firstTime = false;
/*  734 */         fieldValues.append("SET ");
/*      */       } else {
/*  736 */         fieldValues.append(",");
/*  737 */         columnNames.append(",");
/*  738 */         insertPlaceHolders.append(",");
/*      */       } 
/*      */       
/*  741 */       insertPlaceHolders.append("?");
/*      */       
/*  743 */       columnNames.append(qualifiedColumnName);
/*      */       
/*  745 */       fieldValues.append(qualifiedColumnName);
/*  746 */       fieldValues.append("=?");
/*      */     } 
/*      */     
/*  749 */     this.qualifiedAndQuotedTableName = allTablesBuf.toString();
/*      */     
/*  751 */     this.updateSQL = "UPDATE " + this.qualifiedAndQuotedTableName + " " + fieldValues.toString() + " WHERE " + keyValues.toString();
/*  752 */     this.insertSQL = "INSERT INTO " + this.qualifiedAndQuotedTableName + " (" + columnNames.toString() + ") VALUES (" + insertPlaceHolders.toString() + ")";
/*  753 */     this.refreshSQL = "SELECT " + columnNames.toString() + " FROM " + this.qualifiedAndQuotedTableName + " WHERE " + keyValues.toString();
/*  754 */     this.deleteSQL = "DELETE FROM " + this.qualifiedAndQuotedTableName + " WHERE " + keyValues.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private Map<String, Integer> getColumnsToIndexMapForTableAndDB(String databaseName, String tableName) {
/*  759 */     Map<String, Map<String, Integer>> tablesUsedToColumnsMap = this.databasesUsedToTablesUsed.get(databaseName);
/*      */     
/*  761 */     if (tablesUsedToColumnsMap == null) {
/*  762 */       if (this.connection.lowerCaseTableNames()) {
/*  763 */         tablesUsedToColumnsMap = new TreeMap<String, Map<String, Integer>>(String.CASE_INSENSITIVE_ORDER);
/*      */       } else {
/*  765 */         tablesUsedToColumnsMap = new TreeMap<String, Map<String, Integer>>();
/*      */       } 
/*      */       
/*  768 */       this.databasesUsedToTablesUsed.put(databaseName, tablesUsedToColumnsMap);
/*      */     } 
/*      */     
/*  771 */     Map<String, Integer> nameToIndex = tablesUsedToColumnsMap.get(tableName);
/*      */     
/*  773 */     if (nameToIndex == null) {
/*  774 */       nameToIndex = new HashMap<String, Integer>();
/*  775 */       tablesUsedToColumnsMap.put(tableName, nameToIndex);
/*      */     } 
/*      */     
/*  778 */     return nameToIndex;
/*      */   }
/*      */   
/*      */   private SingleByteCharsetConverter getCharConverter() throws SQLException {
/*  782 */     if (!this.initializedCharConverter) {
/*  783 */       this.initializedCharConverter = true;
/*      */       
/*  785 */       if (this.connection.getUseUnicode()) {
/*  786 */         this.charEncoding = this.connection.getEncoding();
/*  787 */         this.charConverter = this.connection.getCharsetConverter(this.charEncoding);
/*      */       } 
/*      */     } 
/*      */     
/*  791 */     return this.charConverter;
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
/*      */   public int getConcurrency() throws SQLException {
/*  805 */     synchronized (checkClosed().getConnectionMutex()) {
/*  806 */       return this.isUpdatable ? 1008 : 1007;
/*      */     } 
/*      */   }
/*      */   
/*      */   private String getQuotedIdChar() throws SQLException {
/*  811 */     if (this.quotedIdChar == null) {
/*  812 */       boolean useQuotedIdentifiers = this.connection.supportsQuotedIdentifiers();
/*      */       
/*  814 */       if (useQuotedIdentifiers) {
/*  815 */         DatabaseMetaData dbmd = this.connection.getMetaData();
/*  816 */         this.quotedIdChar = dbmd.getIdentifierQuoteString();
/*      */       } else {
/*  818 */         this.quotedIdChar = "";
/*      */       } 
/*      */     } 
/*      */     
/*  822 */     return this.quotedIdChar;
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
/*  836 */     synchronized (checkClosed().getConnectionMutex()) {
/*  837 */       if (!this.onInsertRow) {
/*  838 */         throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.7"), getExceptionInterceptor());
/*      */       }
/*      */       
/*  841 */       this.inserter.executeUpdate();
/*      */       
/*  843 */       long autoIncrementId = this.inserter.getLastInsertID();
/*  844 */       int numFields = this.fields.length;
/*  845 */       byte[][] newRow = new byte[numFields][];
/*      */       
/*  847 */       for (int i = 0; i < numFields; i++) {
/*  848 */         if (this.inserter.isNull(i)) {
/*  849 */           newRow[i] = null;
/*      */         } else {
/*  851 */           newRow[i] = this.inserter.getBytesRepresentation(i);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  857 */         if (this.fields[i].isAutoIncrement() && autoIncrementId > 0L) {
/*  858 */           newRow[i] = StringUtils.getBytes(String.valueOf(autoIncrementId));
/*  859 */           this.inserter.setBytesNoEscapeNoQuotes(i + 1, newRow[i]);
/*      */         } 
/*      */       } 
/*      */       
/*  863 */       ResultSetRow resultSetRow = new ByteArrayRow(newRow, getExceptionInterceptor());
/*      */       
/*  865 */       refreshRow(this.inserter, resultSetRow);
/*      */       
/*  867 */       this.rowData.addRow(resultSetRow);
/*  868 */       resetInserter();
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
/*      */   public boolean isAfterLast() throws SQLException {
/*  887 */     return super.isAfterLast();
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
/*      */   public boolean isBeforeFirst() throws SQLException {
/*  905 */     return super.isBeforeFirst();
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
/*      */   public boolean isFirst() throws SQLException {
/*  922 */     return super.isFirst();
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
/*      */   public boolean isLast() throws SQLException {
/*  940 */     return super.isLast();
/*      */   }
/*      */   
/*      */   boolean isUpdatable() {
/*  944 */     return this.isUpdatable;
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
/*  962 */     return super.last();
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
/*      */   public void moveToCurrentRow() throws SQLException {
/*  977 */     synchronized (checkClosed().getConnectionMutex()) {
/*  978 */       if (!this.isUpdatable) {
/*  979 */         throw new NotUpdatable(this.notUpdatableReason);
/*      */       }
/*      */       
/*  982 */       if (this.onInsertRow) {
/*  983 */         this.onInsertRow = false;
/*  984 */         this.thisRow = this.savedCurrentRow;
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
/*      */ 
/*      */   
/*      */   public void moveToInsertRow() throws SQLException {
/* 1007 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1008 */       if (!this.isUpdatable) {
/* 1009 */         throw new NotUpdatable(this.notUpdatableReason);
/*      */       }
/*      */       
/* 1012 */       if (this.inserter == null) {
/* 1013 */         if (this.insertSQL == null) {
/* 1014 */           generateStatements();
/*      */         }
/*      */         
/* 1017 */         this.inserter = (PreparedStatement)this.connection.clientPrepareStatement(this.insertSQL);
/* 1018 */         this.inserter.parameterMetaData = new MysqlParameterMetadata(this.fields, this.fields.length, getExceptionInterceptor());
/*      */         
/* 1020 */         if (this.populateInserterWithDefaultValues) {
/* 1021 */           extractDefaultValues();
/*      */         }
/*      */         
/* 1024 */         resetInserter();
/*      */       } else {
/* 1026 */         resetInserter();
/*      */       } 
/*      */       
/* 1029 */       int numFields = this.fields.length;
/*      */       
/* 1031 */       this.onInsertRow = true;
/* 1032 */       this.doingUpdates = false;
/* 1033 */       this.savedCurrentRow = this.thisRow;
/* 1034 */       byte[][] newRowData = new byte[numFields][];
/* 1035 */       this.thisRow = new ByteArrayRow(newRowData, getExceptionInterceptor());
/* 1036 */       this.thisRow.setMetadata(this.fields);
/*      */       
/* 1038 */       for (int i = 0; i < numFields; i++) {
/* 1039 */         if (!this.populateInserterWithDefaultValues) {
/* 1040 */           this.inserter.setBytesNoEscapeNoQuotes(i + 1, StringUtils.getBytes("DEFAULT"));
/* 1041 */           newRowData = (byte[][])null;
/*      */         }
/* 1043 */         else if (this.defaultColumnValue[i] != null) {
/* 1044 */           Field f = this.fields[i];
/*      */           
/* 1046 */           switch (f.getMysqlType()) {
/*      */             
/*      */             case 7:
/*      */             case 10:
/*      */             case 11:
/*      */             case 12:
/*      */             case 14:
/* 1053 */               if ((this.defaultColumnValue[i]).length > 7 && this.defaultColumnValue[i][0] == 67 && this.defaultColumnValue[i][1] == 85 && this.defaultColumnValue[i][2] == 82 && this.defaultColumnValue[i][3] == 82 && this.defaultColumnValue[i][4] == 69 && this.defaultColumnValue[i][5] == 78 && this.defaultColumnValue[i][6] == 84 && this.defaultColumnValue[i][7] == 95) {
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1058 */                 this.inserter.setBytesNoEscapeNoQuotes(i + 1, this.defaultColumnValue[i]);
/*      */                 
/*      */                 break;
/*      */               } 
/* 1062 */               this.inserter.setBytes(i + 1, this.defaultColumnValue[i], false, false);
/*      */               break;
/*      */             
/*      */             default:
/* 1066 */               this.inserter.setBytes(i + 1, this.defaultColumnValue[i], false, false);
/*      */               break;
/*      */           } 
/*      */           
/* 1070 */           byte[] defaultValueCopy = new byte[(this.defaultColumnValue[i]).length];
/* 1071 */           System.arraycopy(this.defaultColumnValue[i], 0, defaultValueCopy, 0, defaultValueCopy.length);
/* 1072 */           newRowData[i] = defaultValueCopy;
/*      */         } else {
/* 1074 */           this.inserter.setNull(i + 1, 0);
/* 1075 */           newRowData[i] = null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean next() throws SQLException {
/* 1102 */     return super.next();
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
/*      */   public boolean prev() throws SQLException {
/* 1121 */     return super.prev();
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
/*      */   public boolean previous() throws SQLException {
/* 1143 */     return super.previous();
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
/*      */   public void realClose(boolean calledExplicitly) throws SQLException {
/* 1158 */     MySQLConnection locallyScopedConn = this.connection;
/*      */     
/* 1160 */     if (locallyScopedConn == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1164 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1165 */       SQLException sqlEx = null;
/*      */       
/* 1167 */       if (this.useUsageAdvisor && 
/* 1168 */         this.deleter == null && this.inserter == null && this.refresher == null && this.updater == null) {
/* 1169 */         this.connection.getProfilerEventHandlerInstance().processEvent((byte)0, this.connection, this.owningStatement, this, 0L, new Throwable(), Messages.getString("UpdatableResultSet.34"));
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1175 */         if (this.deleter != null) {
/* 1176 */           this.deleter.close();
/*      */         }
/* 1178 */       } catch (SQLException ex) {
/* 1179 */         sqlEx = ex;
/*      */       } 
/*      */       
/*      */       try {
/* 1183 */         if (this.inserter != null) {
/* 1184 */           this.inserter.close();
/*      */         }
/* 1186 */       } catch (SQLException ex) {
/* 1187 */         sqlEx = ex;
/*      */       } 
/*      */       
/*      */       try {
/* 1191 */         if (this.refresher != null) {
/* 1192 */           this.refresher.close();
/*      */         }
/* 1194 */       } catch (SQLException ex) {
/* 1195 */         sqlEx = ex;
/*      */       } 
/*      */       
/*      */       try {
/* 1199 */         if (this.updater != null) {
/* 1200 */           this.updater.close();
/*      */         }
/* 1202 */       } catch (SQLException ex) {
/* 1203 */         sqlEx = ex;
/*      */       } 
/*      */       
/* 1206 */       super.realClose(calledExplicitly);
/*      */       
/* 1208 */       if (sqlEx != null) {
/* 1209 */         throw sqlEx;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void refreshRow() throws SQLException {
/* 1235 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1236 */       if (!this.isUpdatable) {
/* 1237 */         throw new NotUpdatable();
/*      */       }
/*      */       
/* 1240 */       if (this.onInsertRow)
/* 1241 */         throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.8"), getExceptionInterceptor()); 
/* 1242 */       if (this.rowData.size() == 0)
/* 1243 */         throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.9"), getExceptionInterceptor()); 
/* 1244 */       if (isBeforeFirst())
/* 1245 */         throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.10"), getExceptionInterceptor()); 
/* 1246 */       if (isAfterLast()) {
/* 1247 */         throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.11"), getExceptionInterceptor());
/*      */       }
/*      */       
/* 1250 */       refreshRow(this.updater, this.thisRow);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void refreshRow(PreparedStatement updateInsertStmt, ResultSetRow rowToRefresh) throws SQLException {
/* 1255 */     if (this.refresher == null) {
/* 1256 */       if (this.refreshSQL == null) {
/* 1257 */         generateStatements();
/*      */       }
/*      */       
/* 1260 */       this.refresher = (PreparedStatement)this.connection.clientPrepareStatement(this.refreshSQL);
/* 1261 */       this.refresher.parameterMetaData = new MysqlParameterMetadata(this.fields, this.fields.length, getExceptionInterceptor());
/*      */     } 
/*      */     
/* 1264 */     this.refresher.clearParameters();
/*      */     
/* 1266 */     int numKeys = this.primaryKeyIndicies.size();
/*      */     
/* 1268 */     if (numKeys == 1) {
/* 1269 */       byte[] dataFrom = null;
/* 1270 */       int index = ((Integer)this.primaryKeyIndicies.get(0)).intValue();
/*      */       
/* 1272 */       if (!this.doingUpdates && !this.onInsertRow) {
/* 1273 */         dataFrom = rowToRefresh.getColumnValue(index);
/*      */       } else {
/* 1275 */         dataFrom = updateInsertStmt.getBytesRepresentation(index);
/*      */ 
/*      */         
/* 1278 */         if (updateInsertStmt.isNull(index) || dataFrom.length == 0) {
/* 1279 */           dataFrom = rowToRefresh.getColumnValue(index);
/*      */         } else {
/* 1281 */           dataFrom = stripBinaryPrefix(dataFrom);
/*      */         } 
/*      */       } 
/*      */       
/* 1285 */       if (this.fields[index].getvalueNeedsQuoting() && !this.connection.isNoBackslashEscapesSet()) {
/*      */ 
/*      */ 
/*      */         
/* 1289 */         this.refresher.setBytesNoEscape(1, dataFrom);
/*      */       } else {
/* 1291 */         this.refresher.setBytesNoEscapeNoQuotes(1, dataFrom);
/*      */       } 
/*      */     } else {
/*      */       
/* 1295 */       for (int i = 0; i < numKeys; i++) {
/* 1296 */         byte[] dataFrom = null;
/* 1297 */         int index = ((Integer)this.primaryKeyIndicies.get(i)).intValue();
/*      */         
/* 1299 */         if (!this.doingUpdates && !this.onInsertRow) {
/* 1300 */           dataFrom = rowToRefresh.getColumnValue(index);
/*      */         } else {
/* 1302 */           dataFrom = updateInsertStmt.getBytesRepresentation(index);
/*      */ 
/*      */           
/* 1305 */           if (updateInsertStmt.isNull(index) || dataFrom.length == 0) {
/* 1306 */             dataFrom = rowToRefresh.getColumnValue(index);
/*      */           } else {
/* 1308 */             dataFrom = stripBinaryPrefix(dataFrom);
/*      */           } 
/*      */         } 
/*      */         
/* 1312 */         this.refresher.setBytesNoEscape(i + 1, dataFrom);
/*      */       } 
/*      */     } 
/*      */     
/* 1316 */     ResultSet rs = null;
/*      */     
/*      */     try {
/* 1319 */       rs = this.refresher.executeQuery();
/*      */       
/* 1321 */       int numCols = rs.getMetaData().getColumnCount();
/*      */       
/* 1323 */       if (rs.next()) {
/* 1324 */         for (int i = 0; i < numCols; i++) {
/* 1325 */           byte[] val = rs.getBytes(i + 1);
/*      */           
/* 1327 */           if (val == null || rs.wasNull()) {
/* 1328 */             rowToRefresh.setColumnValue(i, null);
/*      */           } else {
/* 1330 */             rowToRefresh.setColumnValue(i, rs.getBytes(i + 1));
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1334 */         throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.12"), "S1000", getExceptionInterceptor());
/*      */       } 
/*      */     } finally {
/* 1337 */       if (rs != null) {
/*      */         try {
/* 1339 */           rs.close();
/* 1340 */         } catch (SQLException ex) {}
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
/* 1370 */     return super.relative(rows);
/*      */   }
/*      */   
/*      */   private void resetInserter() throws SQLException {
/* 1374 */     this.inserter.clearParameters();
/*      */     
/* 1376 */     for (int i = 0; i < this.fields.length; i++) {
/* 1377 */       this.inserter.setNull(i + 1, 0);
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
/*      */   public boolean rowDeleted() throws SQLException {
/* 1397 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   public boolean rowInserted() throws SQLException {
/* 1415 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   public boolean rowUpdated() throws SQLException {
/* 1433 */     throw SQLError.createSQLFeatureNotSupportedException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setResultSetConcurrency(int concurrencyFlag) {
/* 1444 */     super.setResultSetConcurrency(concurrencyFlag);
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
/*      */   private byte[] stripBinaryPrefix(byte[] dataFrom) {
/* 1458 */     return StringUtils.stripEnclosure(dataFrom, "_binary'", "'");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncUpdate() throws SQLException {
/* 1468 */     if (this.updater == null) {
/* 1469 */       if (this.updateSQL == null) {
/* 1470 */         generateStatements();
/*      */       }
/*      */       
/* 1473 */       this.updater = (PreparedStatement)this.connection.clientPrepareStatement(this.updateSQL);
/* 1474 */       this.updater.parameterMetaData = new MysqlParameterMetadata(this.fields, this.fields.length, getExceptionInterceptor());
/*      */     } 
/*      */     
/* 1477 */     int numFields = this.fields.length;
/* 1478 */     this.updater.clearParameters();
/*      */     
/* 1480 */     for (int i = 0; i < numFields; i++) {
/* 1481 */       if (this.thisRow.getColumnValue(i) != null) {
/*      */         
/* 1483 */         if (this.fields[i].getvalueNeedsQuoting()) {
/* 1484 */           if (this.fields[i].isCharsetApplicableType() && !this.fields[i].getEncoding().equals(this.connection.getEncoding())) {
/* 1485 */             this.updater.setString(i + 1, this.thisRow.getString(i, this.fields[i].getEncoding(), this.connection));
/*      */           } else {
/* 1487 */             this.updater.setBytes(i + 1, this.thisRow.getColumnValue(i), this.fields[i].isBinary(), false);
/*      */           } 
/*      */         } else {
/* 1490 */           this.updater.setBytesNoEscapeNoQuotes(i + 1, this.thisRow.getColumnValue(i));
/*      */         } 
/*      */       } else {
/* 1493 */         this.updater.setNull(i + 1, 0);
/*      */       } 
/*      */     } 
/*      */     
/* 1497 */     int numKeys = this.primaryKeyIndicies.size();
/* 1498 */     for (int j = 0; j < numKeys; j++) {
/* 1499 */       int idx = ((Integer)this.primaryKeyIndicies.get(j)).intValue();
/* 1500 */       setParamValue(this.updater, numFields + j + 1, this.thisRow, idx, this.fields[idx]);
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
/*      */   public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
/* 1523 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1524 */       if (!this.onInsertRow) {
/* 1525 */         if (!this.doingUpdates) {
/* 1526 */           this.doingUpdates = true;
/* 1527 */           syncUpdate();
/*      */         } 
/*      */         
/* 1530 */         this.updater.setAsciiStream(columnIndex, x, length);
/*      */       } else {
/* 1532 */         this.inserter.setAsciiStream(columnIndex, x, length);
/* 1533 */         this.thisRow.setColumnValue(columnIndex - 1, STREAM_DATA_MARKER);
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
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateAsciiStream(String columnName, InputStream x, int length) throws SQLException {
/* 1557 */     updateAsciiStream(findColumn(columnName), x, length);
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
/* 1576 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1577 */       if (!this.onInsertRow) {
/* 1578 */         if (!this.doingUpdates) {
/* 1579 */           this.doingUpdates = true;
/* 1580 */           syncUpdate();
/*      */         } 
/*      */         
/* 1583 */         this.updater.setBigDecimal(columnIndex, x);
/*      */       } else {
/* 1585 */         this.inserter.setBigDecimal(columnIndex, x);
/*      */         
/* 1587 */         if (x == null) {
/* 1588 */           this.thisRow.setColumnValue(columnIndex - 1, null);
/*      */         } else {
/* 1590 */           this.thisRow.setColumnValue(columnIndex - 1, StringUtils.getBytes(x.toString()));
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
/*      */   public void updateBigDecimal(String columnName, BigDecimal x) throws SQLException {
/* 1612 */     updateBigDecimal(findColumn(columnName), x);
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
/* 1634 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1635 */       if (!this.onInsertRow) {
/* 1636 */         if (!this.doingUpdates) {
/* 1637 */           this.doingUpdates = true;
/* 1638 */           syncUpdate();
/*      */         } 
/*      */         
/* 1641 */         this.updater.setBinaryStream(columnIndex, x, length);
/*      */       } else {
/* 1643 */         this.inserter.setBinaryStream(columnIndex, x, length);
/*      */         
/* 1645 */         if (x == null) {
/* 1646 */           this.thisRow.setColumnValue(columnIndex - 1, null);
/*      */         } else {
/* 1648 */           this.thisRow.setColumnValue(columnIndex - 1, STREAM_DATA_MARKER);
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
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateBinaryStream(String columnName, InputStream x, int length) throws SQLException {
/* 1673 */     updateBinaryStream(findColumn(columnName), x, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateBlob(int columnIndex, Blob blob) throws SQLException {
/* 1681 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1682 */       if (!this.onInsertRow) {
/* 1683 */         if (!this.doingUpdates) {
/* 1684 */           this.doingUpdates = true;
/* 1685 */           syncUpdate();
/*      */         } 
/*      */         
/* 1688 */         this.updater.setBlob(columnIndex, blob);
/*      */       } else {
/* 1690 */         this.inserter.setBlob(columnIndex, blob);
/*      */         
/* 1692 */         if (blob == null) {
/* 1693 */           this.thisRow.setColumnValue(columnIndex - 1, null);
/*      */         } else {
/* 1695 */           this.thisRow.setColumnValue(columnIndex - 1, STREAM_DATA_MARKER);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateBlob(String columnName, Blob blob) throws SQLException {
/* 1706 */     updateBlob(findColumn(columnName), blob);
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
/* 1725 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1726 */       if (!this.onInsertRow) {
/* 1727 */         if (!this.doingUpdates) {
/* 1728 */           this.doingUpdates = true;
/* 1729 */           syncUpdate();
/*      */         } 
/*      */         
/* 1732 */         this.updater.setBoolean(columnIndex, x);
/*      */       } else {
/* 1734 */         this.inserter.setBoolean(columnIndex, x);
/*      */         
/* 1736 */         this.thisRow.setColumnValue(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
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
/*      */   public void updateBoolean(String columnName, boolean x) throws SQLException {
/* 1757 */     updateBoolean(findColumn(columnName), x);
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
/* 1776 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1777 */       if (!this.onInsertRow) {
/* 1778 */         if (!this.doingUpdates) {
/* 1779 */           this.doingUpdates = true;
/* 1780 */           syncUpdate();
/*      */         } 
/*      */         
/* 1783 */         this.updater.setByte(columnIndex, x);
/*      */       } else {
/* 1785 */         this.inserter.setByte(columnIndex, x);
/*      */         
/* 1787 */         this.thisRow.setColumnValue(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
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
/*      */   public void updateByte(String columnName, byte x) throws SQLException {
/* 1808 */     updateByte(findColumn(columnName), x);
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
/* 1827 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1828 */       if (!this.onInsertRow) {
/* 1829 */         if (!this.doingUpdates) {
/* 1830 */           this.doingUpdates = true;
/* 1831 */           syncUpdate();
/*      */         } 
/*      */         
/* 1834 */         this.updater.setBytes(columnIndex, x);
/*      */       } else {
/* 1836 */         this.inserter.setBytes(columnIndex, x);
/*      */         
/* 1838 */         this.thisRow.setColumnValue(columnIndex - 1, x);
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
/*      */   public void updateBytes(String columnName, byte[] x) throws SQLException {
/* 1859 */     updateBytes(findColumn(columnName), x);
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
/* 1881 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1882 */       if (!this.onInsertRow) {
/* 1883 */         if (!this.doingUpdates) {
/* 1884 */           this.doingUpdates = true;
/* 1885 */           syncUpdate();
/*      */         } 
/*      */         
/* 1888 */         this.updater.setCharacterStream(columnIndex, x, length);
/*      */       } else {
/* 1890 */         this.inserter.setCharacterStream(columnIndex, x, length);
/*      */         
/* 1892 */         if (x == null) {
/* 1893 */           this.thisRow.setColumnValue(columnIndex - 1, null);
/*      */         } else {
/* 1895 */           this.thisRow.setColumnValue(columnIndex - 1, STREAM_DATA_MARKER);
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
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateCharacterStream(String columnName, Reader reader, int length) throws SQLException {
/* 1920 */     updateCharacterStream(findColumn(columnName), reader, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateClob(int columnIndex, Clob clob) throws SQLException {
/* 1928 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1929 */       if (clob == null) {
/* 1930 */         updateNull(columnIndex);
/*      */       } else {
/* 1932 */         updateCharacterStream(columnIndex, clob.getCharacterStream(), (int)clob.length());
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
/*      */   public void updateDate(int columnIndex, Date x) throws SQLException {
/* 1953 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1954 */       if (!this.onInsertRow) {
/* 1955 */         if (!this.doingUpdates) {
/* 1956 */           this.doingUpdates = true;
/* 1957 */           syncUpdate();
/*      */         } 
/*      */         
/* 1960 */         this.updater.setDate(columnIndex, x);
/*      */       } else {
/* 1962 */         this.inserter.setDate(columnIndex, x);
/*      */         
/* 1964 */         this.thisRow.setColumnValue(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
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
/*      */   public void updateDate(String columnName, Date x) throws SQLException {
/* 1985 */     updateDate(findColumn(columnName), x);
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
/* 2004 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2005 */       if (!this.onInsertRow) {
/* 2006 */         if (!this.doingUpdates) {
/* 2007 */           this.doingUpdates = true;
/* 2008 */           syncUpdate();
/*      */         } 
/*      */         
/* 2011 */         this.updater.setDouble(columnIndex, x);
/*      */       } else {
/* 2013 */         this.inserter.setDouble(columnIndex, x);
/*      */         
/* 2015 */         this.thisRow.setColumnValue(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
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
/*      */   public void updateDouble(String columnName, double x) throws SQLException {
/* 2036 */     updateDouble(findColumn(columnName), x);
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
/* 2055 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2056 */       if (!this.onInsertRow) {
/* 2057 */         if (!this.doingUpdates) {
/* 2058 */           this.doingUpdates = true;
/* 2059 */           syncUpdate();
/*      */         } 
/*      */         
/* 2062 */         this.updater.setFloat(columnIndex, x);
/*      */       } else {
/* 2064 */         this.inserter.setFloat(columnIndex, x);
/*      */         
/* 2066 */         this.thisRow.setColumnValue(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
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
/*      */   public void updateFloat(String columnName, float x) throws SQLException {
/* 2087 */     updateFloat(findColumn(columnName), x);
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
/* 2106 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2107 */       if (!this.onInsertRow) {
/* 2108 */         if (!this.doingUpdates) {
/* 2109 */           this.doingUpdates = true;
/* 2110 */           syncUpdate();
/*      */         } 
/*      */         
/* 2113 */         this.updater.setInt(columnIndex, x);
/*      */       } else {
/* 2115 */         this.inserter.setInt(columnIndex, x);
/*      */         
/* 2117 */         this.thisRow.setColumnValue(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
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
/*      */   public void updateInt(String columnName, int x) throws SQLException {
/* 2138 */     updateInt(findColumn(columnName), x);
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
/* 2157 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2158 */       if (!this.onInsertRow) {
/* 2159 */         if (!this.doingUpdates) {
/* 2160 */           this.doingUpdates = true;
/* 2161 */           syncUpdate();
/*      */         } 
/*      */         
/* 2164 */         this.updater.setLong(columnIndex, x);
/*      */       } else {
/* 2166 */         this.inserter.setLong(columnIndex, x);
/*      */         
/* 2168 */         this.thisRow.setColumnValue(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
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
/*      */   public void updateLong(String columnName, long x) throws SQLException {
/* 2189 */     updateLong(findColumn(columnName), x);
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
/* 2206 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2207 */       if (!this.onInsertRow) {
/* 2208 */         if (!this.doingUpdates) {
/* 2209 */           this.doingUpdates = true;
/* 2210 */           syncUpdate();
/*      */         } 
/*      */         
/* 2213 */         this.updater.setNull(columnIndex, 0);
/*      */       } else {
/* 2215 */         this.inserter.setNull(columnIndex, 0);
/*      */         
/* 2217 */         this.thisRow.setColumnValue(columnIndex - 1, null);
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
/*      */   public void updateNull(String columnName) throws SQLException {
/* 2236 */     updateNull(findColumn(columnName));
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
/* 2255 */     updateObjectInternal(columnIndex, x, (Integer)null, 0);
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
/* 2278 */     updateObjectInternal(columnIndex, x, (Integer)null, scale);
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
/*      */   protected void updateObjectInternal(int columnIndex, Object x, Integer targetType, int scaleOrLength) throws SQLException {
/* 2292 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2293 */       if (!this.onInsertRow) {
/* 2294 */         if (!this.doingUpdates) {
/* 2295 */           this.doingUpdates = true;
/* 2296 */           syncUpdate();
/*      */         } 
/*      */         
/* 2299 */         if (targetType == null) {
/* 2300 */           this.updater.setObject(columnIndex, x);
/*      */         } else {
/* 2302 */           this.updater.setObject(columnIndex, x, targetType.intValue());
/*      */         } 
/*      */       } else {
/* 2305 */         if (targetType == null) {
/* 2306 */           this.inserter.setObject(columnIndex, x);
/*      */         } else {
/* 2308 */           this.inserter.setObject(columnIndex, x, targetType.intValue());
/*      */         } 
/*      */         
/* 2311 */         this.thisRow.setColumnValue(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
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
/*      */   public void updateObject(String columnName, Object x) throws SQLException {
/* 2332 */     updateObject(findColumn(columnName), x);
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
/*      */   public void updateObject(String columnName, Object x, int scale) throws SQLException {
/* 2355 */     updateObject(findColumn(columnName), x);
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
/*      */   public void updateRow() throws SQLException {
/* 2369 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2370 */       if (!this.isUpdatable) {
/* 2371 */         throw new NotUpdatable(this.notUpdatableReason);
/*      */       }
/*      */       
/* 2374 */       if (this.doingUpdates) {
/* 2375 */         this.updater.executeUpdate();
/* 2376 */         refreshRow();
/* 2377 */         this.doingUpdates = false;
/* 2378 */       } else if (this.onInsertRow) {
/* 2379 */         throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.44"), getExceptionInterceptor());
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2385 */       syncUpdate();
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
/*      */   public void updateShort(int columnIndex, short x) throws SQLException {
/* 2405 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2406 */       if (!this.onInsertRow) {
/* 2407 */         if (!this.doingUpdates) {
/* 2408 */           this.doingUpdates = true;
/* 2409 */           syncUpdate();
/*      */         } 
/*      */         
/* 2412 */         this.updater.setShort(columnIndex, x);
/*      */       } else {
/* 2414 */         this.inserter.setShort(columnIndex, x);
/*      */         
/* 2416 */         this.thisRow.setColumnValue(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
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
/*      */   public void updateShort(String columnName, short x) throws SQLException {
/* 2437 */     updateShort(findColumn(columnName), x);
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
/* 2456 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2457 */       if (!this.onInsertRow) {
/* 2458 */         if (!this.doingUpdates) {
/* 2459 */           this.doingUpdates = true;
/* 2460 */           syncUpdate();
/*      */         } 
/*      */         
/* 2463 */         this.updater.setString(columnIndex, x);
/*      */       } else {
/* 2465 */         this.inserter.setString(columnIndex, x);
/*      */         
/* 2467 */         if (x == null) {
/* 2468 */           this.thisRow.setColumnValue(columnIndex - 1, null);
/*      */         }
/* 2470 */         else if (getCharConverter() != null) {
/* 2471 */           this.thisRow.setColumnValue(columnIndex - 1, StringUtils.getBytes(x, this.charConverter, this.charEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), getExceptionInterceptor()));
/*      */         } else {
/*      */           
/* 2474 */           this.thisRow.setColumnValue(columnIndex - 1, StringUtils.getBytes(x));
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
/*      */   
/*      */   public void updateString(String columnName, String x) throws SQLException {
/* 2497 */     updateString(findColumn(columnName), x);
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
/* 2516 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2517 */       if (!this.onInsertRow) {
/* 2518 */         if (!this.doingUpdates) {
/* 2519 */           this.doingUpdates = true;
/* 2520 */           syncUpdate();
/*      */         } 
/*      */         
/* 2523 */         this.updater.setTime(columnIndex, x);
/*      */       } else {
/* 2525 */         this.inserter.setTime(columnIndex, x);
/*      */         
/* 2527 */         this.thisRow.setColumnValue(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
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
/*      */   public void updateTime(String columnName, Time x) throws SQLException {
/* 2548 */     updateTime(findColumn(columnName), x);
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
/* 2567 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2568 */       if (!this.onInsertRow) {
/* 2569 */         if (!this.doingUpdates) {
/* 2570 */           this.doingUpdates = true;
/* 2571 */           syncUpdate();
/*      */         } 
/*      */         
/* 2574 */         this.updater.setTimestamp(columnIndex, x);
/*      */       } else {
/* 2576 */         this.inserter.setTimestamp(columnIndex, x);
/*      */         
/* 2578 */         this.thisRow.setColumnValue(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
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
/*      */   public void updateTimestamp(String columnName, Timestamp x) throws SQLException {
/* 2599 */     updateTimestamp(findColumn(columnName), x);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\UpdatableResultSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */