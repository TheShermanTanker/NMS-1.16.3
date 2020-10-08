/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.sql.Connection;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Set;
/*      */ import java.util.SortedMap;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.TreeMap;
/*      */ import java.util.TreeSet;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DatabaseMetaData
/*      */   implements DatabaseMetaData
/*      */ {
/*      */   protected static final int MAX_IDENTIFIER_LENGTH = 64;
/*      */   private static final int DEFERRABILITY = 13;
/*      */   private static final int DELETE_RULE = 10;
/*      */   private static final int FK_NAME = 11;
/*      */   private static final int FKCOLUMN_NAME = 7;
/*      */   private static final int FKTABLE_CAT = 4;
/*      */   private static final int FKTABLE_NAME = 6;
/*      */   private static final int FKTABLE_SCHEM = 5;
/*      */   private static final int KEY_SEQ = 8;
/*      */   private static final int PK_NAME = 12;
/*      */   private static final int PKCOLUMN_NAME = 3;
/*      */   private static final int PKTABLE_CAT = 0;
/*      */   private static final int PKTABLE_NAME = 2;
/*      */   private static final int PKTABLE_SCHEM = 1;
/*      */   private static final String SUPPORTS_FK = "SUPPORTS_FK";
/*      */   
/*      */   protected abstract class IteratorWithCleanup<T>
/*      */   {
/*      */     abstract void close() throws SQLException;
/*      */     
/*      */     abstract boolean hasNext() throws SQLException;
/*      */     
/*      */     abstract T next() throws SQLException;
/*      */   }
/*      */   
/*      */   class LocalAndReferencedColumns
/*      */   {
/*      */     String constraintName;
/*      */     List<String> localColumnsList;
/*      */     String referencedCatalog;
/*      */     List<String> referencedColumnsList;
/*      */     String referencedTable;
/*      */     
/*      */     LocalAndReferencedColumns(List<String> localColumns, List<String> refColumns, String constName, String refCatalog, String refTable) {
/*   86 */       this.localColumnsList = localColumns;
/*   87 */       this.referencedColumnsList = refColumns;
/*   88 */       this.constraintName = constName;
/*   89 */       this.referencedTable = refTable;
/*   90 */       this.referencedCatalog = refCatalog;
/*      */     }
/*      */   }
/*      */   
/*      */   protected class ResultSetIterator
/*      */     extends IteratorWithCleanup<String> {
/*      */     int colIndex;
/*      */     ResultSet resultSet;
/*      */     
/*      */     ResultSetIterator(ResultSet rs, int index) {
/*  100 */       this.resultSet = rs;
/*  101 */       this.colIndex = index;
/*      */     }
/*      */ 
/*      */     
/*      */     void close() throws SQLException {
/*  106 */       this.resultSet.close();
/*      */     }
/*      */ 
/*      */     
/*      */     boolean hasNext() throws SQLException {
/*  111 */       return this.resultSet.next();
/*      */     }
/*      */ 
/*      */     
/*      */     String next() throws SQLException {
/*  116 */       return this.resultSet.getObject(this.colIndex).toString();
/*      */     }
/*      */   }
/*      */   
/*      */   protected class SingleStringIterator
/*      */     extends IteratorWithCleanup<String> {
/*      */     boolean onFirst = true;
/*      */     String value;
/*      */     
/*      */     SingleStringIterator(String s) {
/*  126 */       this.value = s;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void close() throws SQLException {}
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasNext() throws SQLException {
/*  137 */       return this.onFirst;
/*      */     }
/*      */ 
/*      */     
/*      */     String next() throws SQLException {
/*  142 */       this.onFirst = false;
/*  143 */       return this.value;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   class TypeDescriptor
/*      */   {
/*      */     int bufferLength;
/*      */ 
/*      */     
/*      */     int charOctetLength;
/*      */     
/*      */     Integer columnSize;
/*      */     
/*      */     short dataType;
/*      */     
/*      */     Integer decimalDigits;
/*      */     
/*      */     String isNullable;
/*      */     
/*      */     int nullability;
/*      */     
/*  166 */     int numPrecRadix = 10;
/*      */     
/*      */     String typeName;
/*      */     
/*      */     TypeDescriptor(String typeInfo, String nullabilityInfo) throws SQLException {
/*  171 */       if (typeInfo == null) {
/*  172 */         throw SQLError.createSQLException("NULL typeinfo not supported.", "S1009", DatabaseMetaData.this.getExceptionInterceptor());
/*      */       }
/*      */       
/*  175 */       String mysqlType = "";
/*  176 */       String fullMysqlType = null;
/*      */       
/*  178 */       if (typeInfo.indexOf("(") != -1) {
/*  179 */         mysqlType = typeInfo.substring(0, typeInfo.indexOf("(")).trim();
/*      */       } else {
/*  181 */         mysqlType = typeInfo;
/*      */       } 
/*      */       
/*  184 */       int indexOfUnsignedInMysqlType = StringUtils.indexOfIgnoreCase(mysqlType, "unsigned");
/*      */       
/*  186 */       if (indexOfUnsignedInMysqlType != -1) {
/*  187 */         mysqlType = mysqlType.substring(0, indexOfUnsignedInMysqlType - 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  192 */       boolean isUnsigned = false;
/*      */       
/*  194 */       if (StringUtils.indexOfIgnoreCase(typeInfo, "unsigned") != -1 && StringUtils.indexOfIgnoreCase(typeInfo, "set") != 0 && StringUtils.indexOfIgnoreCase(typeInfo, "enum") != 0) {
/*      */         
/*  196 */         fullMysqlType = mysqlType + " unsigned";
/*  197 */         isUnsigned = true;
/*      */       } else {
/*  199 */         fullMysqlType = mysqlType;
/*      */       } 
/*      */       
/*  202 */       if (DatabaseMetaData.this.conn.getCapitalizeTypeNames()) {
/*  203 */         fullMysqlType = fullMysqlType.toUpperCase(Locale.ENGLISH);
/*      */       }
/*      */       
/*  206 */       this.dataType = (short)MysqlDefs.mysqlToJavaType(mysqlType);
/*      */       
/*  208 */       this.typeName = fullMysqlType;
/*      */ 
/*      */ 
/*      */       
/*  212 */       if (StringUtils.startsWithIgnoreCase(typeInfo, "enum")) {
/*  213 */         String temp = typeInfo.substring(typeInfo.indexOf("("), typeInfo.lastIndexOf(")"));
/*  214 */         StringTokenizer tokenizer = new StringTokenizer(temp, ",");
/*  215 */         int maxLength = 0;
/*      */         
/*  217 */         while (tokenizer.hasMoreTokens()) {
/*  218 */           maxLength = Math.max(maxLength, tokenizer.nextToken().length() - 2);
/*      */         }
/*      */         
/*  221 */         this.columnSize = Integer.valueOf(maxLength);
/*  222 */         this.decimalDigits = null;
/*  223 */       } else if (StringUtils.startsWithIgnoreCase(typeInfo, "set")) {
/*  224 */         String temp = typeInfo.substring(typeInfo.indexOf("(") + 1, typeInfo.lastIndexOf(")"));
/*  225 */         StringTokenizer tokenizer = new StringTokenizer(temp, ",");
/*  226 */         int maxLength = 0;
/*      */         
/*  228 */         int numElements = tokenizer.countTokens();
/*      */         
/*  230 */         if (numElements > 0) {
/*  231 */           maxLength += numElements - 1;
/*      */         }
/*      */         
/*  234 */         while (tokenizer.hasMoreTokens()) {
/*  235 */           String setMember = tokenizer.nextToken().trim();
/*      */           
/*  237 */           if (setMember.startsWith("'") && setMember.endsWith("'")) {
/*  238 */             maxLength += setMember.length() - 2; continue;
/*      */           } 
/*  240 */           maxLength += setMember.length();
/*      */         } 
/*      */ 
/*      */         
/*  244 */         this.columnSize = Integer.valueOf(maxLength);
/*  245 */         this.decimalDigits = null;
/*  246 */       } else if (typeInfo.indexOf(",") != -1) {
/*      */         
/*  248 */         this.columnSize = Integer.valueOf(typeInfo.substring(typeInfo.indexOf("(") + 1, typeInfo.indexOf(",")).trim());
/*  249 */         this.decimalDigits = Integer.valueOf(typeInfo.substring(typeInfo.indexOf(",") + 1, typeInfo.indexOf(")")).trim());
/*      */       } else {
/*  251 */         this.columnSize = null;
/*  252 */         this.decimalDigits = null;
/*      */ 
/*      */         
/*  255 */         if ((StringUtils.indexOfIgnoreCase(typeInfo, "char") != -1 || StringUtils.indexOfIgnoreCase(typeInfo, "text") != -1 || StringUtils.indexOfIgnoreCase(typeInfo, "blob") != -1 || StringUtils.indexOfIgnoreCase(typeInfo, "binary") != -1 || StringUtils.indexOfIgnoreCase(typeInfo, "bit") != -1) && typeInfo.indexOf("(") != -1) {
/*      */ 
/*      */           
/*  258 */           int endParenIndex = typeInfo.indexOf(")");
/*      */           
/*  260 */           if (endParenIndex == -1) {
/*  261 */             endParenIndex = typeInfo.length();
/*      */           }
/*      */           
/*  264 */           this.columnSize = Integer.valueOf(typeInfo.substring(typeInfo.indexOf("(") + 1, endParenIndex).trim());
/*      */ 
/*      */           
/*  267 */           if (DatabaseMetaData.this.conn.getTinyInt1isBit() && this.columnSize.intValue() == 1 && StringUtils.startsWithIgnoreCase(typeInfo, 0, "tinyint"))
/*      */           {
/*  269 */             if (DatabaseMetaData.this.conn.getTransformedBitIsBoolean()) {
/*  270 */               this.dataType = 16;
/*  271 */               this.typeName = "BOOLEAN";
/*      */             } else {
/*  273 */               this.dataType = -7;
/*  274 */               this.typeName = "BIT";
/*      */             } 
/*      */           }
/*  277 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "tinyint")) {
/*  278 */           if (DatabaseMetaData.this.conn.getTinyInt1isBit() && typeInfo.indexOf("(1)") != -1) {
/*  279 */             if (DatabaseMetaData.this.conn.getTransformedBitIsBoolean()) {
/*  280 */               this.dataType = 16;
/*  281 */               this.typeName = "BOOLEAN";
/*      */             } else {
/*  283 */               this.dataType = -7;
/*  284 */               this.typeName = "BIT";
/*      */             } 
/*      */           } else {
/*  287 */             this.columnSize = Integer.valueOf(3);
/*  288 */             this.decimalDigits = Integer.valueOf(0);
/*      */           } 
/*  290 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "smallint")) {
/*  291 */           this.columnSize = Integer.valueOf(5);
/*  292 */           this.decimalDigits = Integer.valueOf(0);
/*  293 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "mediumint")) {
/*  294 */           this.columnSize = Integer.valueOf(isUnsigned ? 8 : 7);
/*  295 */           this.decimalDigits = Integer.valueOf(0);
/*  296 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "int")) {
/*  297 */           this.columnSize = Integer.valueOf(10);
/*  298 */           this.decimalDigits = Integer.valueOf(0);
/*  299 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "integer")) {
/*  300 */           this.columnSize = Integer.valueOf(10);
/*  301 */           this.decimalDigits = Integer.valueOf(0);
/*  302 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "bigint")) {
/*  303 */           this.columnSize = Integer.valueOf(isUnsigned ? 20 : 19);
/*  304 */           this.decimalDigits = Integer.valueOf(0);
/*  305 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "int24")) {
/*  306 */           this.columnSize = Integer.valueOf(19);
/*  307 */           this.decimalDigits = Integer.valueOf(0);
/*  308 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "real")) {
/*  309 */           this.columnSize = Integer.valueOf(12);
/*  310 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "float")) {
/*  311 */           this.columnSize = Integer.valueOf(12);
/*  312 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "decimal")) {
/*  313 */           this.columnSize = Integer.valueOf(12);
/*  314 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "numeric")) {
/*  315 */           this.columnSize = Integer.valueOf(12);
/*  316 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "double")) {
/*  317 */           this.columnSize = Integer.valueOf(22);
/*  318 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "char")) {
/*  319 */           this.columnSize = Integer.valueOf(1);
/*  320 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "varchar")) {
/*  321 */           this.columnSize = Integer.valueOf(255);
/*  322 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "timestamp")) {
/*  323 */           this.columnSize = Integer.valueOf(19);
/*  324 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "datetime")) {
/*  325 */           this.columnSize = Integer.valueOf(19);
/*  326 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "date")) {
/*  327 */           this.columnSize = Integer.valueOf(10);
/*  328 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "time")) {
/*  329 */           this.columnSize = Integer.valueOf(8);
/*      */         }
/*  331 */         else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "tinyblob")) {
/*  332 */           this.columnSize = Integer.valueOf(255);
/*  333 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "blob")) {
/*  334 */           this.columnSize = Integer.valueOf(65535);
/*  335 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "mediumblob")) {
/*  336 */           this.columnSize = Integer.valueOf(16777215);
/*  337 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "longblob")) {
/*  338 */           this.columnSize = Integer.valueOf(2147483647);
/*  339 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "tinytext")) {
/*  340 */           this.columnSize = Integer.valueOf(255);
/*  341 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "text")) {
/*  342 */           this.columnSize = Integer.valueOf(65535);
/*  343 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "mediumtext")) {
/*  344 */           this.columnSize = Integer.valueOf(16777215);
/*  345 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "longtext")) {
/*  346 */           this.columnSize = Integer.valueOf(2147483647);
/*  347 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "enum")) {
/*  348 */           this.columnSize = Integer.valueOf(255);
/*  349 */         } else if (StringUtils.startsWithIgnoreCaseAndWs(typeInfo, "set")) {
/*  350 */           this.columnSize = Integer.valueOf(255);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  356 */       this.bufferLength = MysqlIO.getMaxBuf();
/*      */ 
/*      */       
/*  359 */       this.numPrecRadix = 10;
/*      */ 
/*      */       
/*  362 */       if (nullabilityInfo != null) {
/*  363 */         if (nullabilityInfo.equals("YES")) {
/*  364 */           this.nullability = 1;
/*  365 */           this.isNullable = "YES";
/*      */         }
/*  367 */         else if (nullabilityInfo.equals("UNKNOWN")) {
/*  368 */           this.nullability = 2;
/*  369 */           this.isNullable = "";
/*      */         }
/*      */         else {
/*      */           
/*  373 */           this.nullability = 0;
/*  374 */           this.isNullable = "NO";
/*      */         } 
/*      */       } else {
/*  377 */         this.nullability = 0;
/*  378 */         this.isNullable = "NO";
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class IndexMetaDataKey
/*      */     implements Comparable<IndexMetaDataKey>
/*      */   {
/*      */     Boolean columnNonUnique;
/*      */     Short columnType;
/*      */     String columnIndexName;
/*      */     Short columnOrdinalPosition;
/*      */     
/*      */     IndexMetaDataKey(boolean columnNonUnique, short columnType, String columnIndexName, short columnOrdinalPosition) {
/*  393 */       this.columnNonUnique = Boolean.valueOf(columnNonUnique);
/*  394 */       this.columnType = Short.valueOf(columnType);
/*  395 */       this.columnIndexName = columnIndexName;
/*  396 */       this.columnOrdinalPosition = Short.valueOf(columnOrdinalPosition);
/*      */     }
/*      */ 
/*      */     
/*      */     public int compareTo(IndexMetaDataKey indexInfoKey) {
/*      */       int compareResult;
/*  402 */       if ((compareResult = this.columnNonUnique.compareTo(indexInfoKey.columnNonUnique)) != 0) {
/*  403 */         return compareResult;
/*      */       }
/*  405 */       if ((compareResult = this.columnType.compareTo(indexInfoKey.columnType)) != 0) {
/*  406 */         return compareResult;
/*      */       }
/*  408 */       if ((compareResult = this.columnIndexName.compareTo(indexInfoKey.columnIndexName)) != 0) {
/*  409 */         return compareResult;
/*      */       }
/*  411 */       return this.columnOrdinalPosition.compareTo(indexInfoKey.columnOrdinalPosition);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/*  416 */       if (obj == null) {
/*  417 */         return false;
/*      */       }
/*      */       
/*  420 */       if (obj == this) {
/*  421 */         return true;
/*      */       }
/*      */       
/*  424 */       if (!(obj instanceof IndexMetaDataKey)) {
/*  425 */         return false;
/*      */       }
/*  427 */       return (compareTo((IndexMetaDataKey)obj) == 0);
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  432 */       assert false : "hashCode not designed";
/*  433 */       return 0;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class TableMetaDataKey
/*      */     implements Comparable<TableMetaDataKey>
/*      */   {
/*      */     String tableType;
/*      */     String tableCat;
/*      */     String tableSchem;
/*      */     String tableName;
/*      */     
/*      */     TableMetaDataKey(String tableType, String tableCat, String tableSchem, String tableName) {
/*  447 */       this.tableType = (tableType == null) ? "" : tableType;
/*  448 */       this.tableCat = (tableCat == null) ? "" : tableCat;
/*  449 */       this.tableSchem = (tableSchem == null) ? "" : tableSchem;
/*  450 */       this.tableName = (tableName == null) ? "" : tableName;
/*      */     }
/*      */ 
/*      */     
/*      */     public int compareTo(TableMetaDataKey tablesKey) {
/*      */       int compareResult;
/*  456 */       if ((compareResult = this.tableType.compareTo(tablesKey.tableType)) != 0) {
/*  457 */         return compareResult;
/*      */       }
/*  459 */       if ((compareResult = this.tableCat.compareTo(tablesKey.tableCat)) != 0) {
/*  460 */         return compareResult;
/*      */       }
/*  462 */       if ((compareResult = this.tableSchem.compareTo(tablesKey.tableSchem)) != 0) {
/*  463 */         return compareResult;
/*      */       }
/*  465 */       return this.tableName.compareTo(tablesKey.tableName);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/*  470 */       if (obj == null) {
/*  471 */         return false;
/*      */       }
/*      */       
/*  474 */       if (obj == this) {
/*  475 */         return true;
/*      */       }
/*      */       
/*  478 */       if (!(obj instanceof TableMetaDataKey)) {
/*  479 */         return false;
/*      */       }
/*  481 */       return (compareTo((TableMetaDataKey)obj) == 0);
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  486 */       assert false : "hashCode not designed";
/*  487 */       return 0;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class ComparableWrapper<K extends Comparable<? super K>, V>
/*      */     implements Comparable<ComparableWrapper<K, V>>
/*      */   {
/*      */     K key;
/*      */     V value;
/*      */     
/*      */     public ComparableWrapper(K key, V value) {
/*  499 */       this.key = key;
/*  500 */       this.value = value;
/*      */     }
/*      */     
/*      */     public K getKey() {
/*  504 */       return this.key;
/*      */     }
/*      */     
/*      */     public V getValue() {
/*  508 */       return this.value;
/*      */     }
/*      */     
/*      */     public int compareTo(ComparableWrapper<K, V> other) {
/*  512 */       return ((Comparable)getKey()).compareTo(other.getKey());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/*  517 */       if (obj == null) {
/*  518 */         return false;
/*      */       }
/*      */       
/*  521 */       if (obj == this) {
/*  522 */         return true;
/*      */       }
/*      */       
/*  525 */       if (!(obj instanceof ComparableWrapper)) {
/*  526 */         return false;
/*      */       }
/*      */       
/*  529 */       Object otherKey = ((ComparableWrapper)obj).getKey();
/*  530 */       return this.key.equals(otherKey);
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  535 */       assert false : "hashCode not designed";
/*  536 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/*  541 */       return "{KEY:" + this.key + "; VALUE:" + this.value + "}";
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected enum TableType
/*      */   {
/*  549 */     LOCAL_TEMPORARY("LOCAL TEMPORARY"), SYSTEM_TABLE("SYSTEM TABLE"), SYSTEM_VIEW("SYSTEM VIEW"), TABLE("TABLE", new String[] { "BASE TABLE" }),
/*  550 */     VIEW("VIEW"), UNKNOWN("UNKNOWN");
/*      */ 
/*      */     
/*      */     private String name;
/*      */     
/*      */     private byte[] nameAsBytes;
/*      */     
/*      */     private String[] synonyms;
/*      */ 
/*      */     
/*      */     TableType(String tableTypeName, String[] tableTypeSynonyms) {
/*  561 */       this.name = tableTypeName;
/*  562 */       this.nameAsBytes = tableTypeName.getBytes();
/*  563 */       this.synonyms = tableTypeSynonyms;
/*      */     }
/*      */     
/*      */     String getName() {
/*  567 */       return this.name;
/*      */     }
/*      */     
/*      */     byte[] asBytes() {
/*  571 */       return this.nameAsBytes;
/*      */     }
/*      */     
/*      */     boolean equalsTo(String tableTypeName) {
/*  575 */       return this.name.equalsIgnoreCase(tableTypeName);
/*      */     }
/*      */     
/*      */     static TableType getTableTypeEqualTo(String tableTypeName) {
/*  579 */       for (TableType tableType : values()) {
/*  580 */         if (tableType.equalsTo(tableTypeName)) {
/*  581 */           return tableType;
/*      */         }
/*      */       } 
/*  584 */       return UNKNOWN;
/*      */     }
/*      */     
/*      */     boolean compliesWith(String tableTypeName) {
/*  588 */       if (equalsTo(tableTypeName)) {
/*  589 */         return true;
/*      */       }
/*  591 */       if (this.synonyms != null) {
/*  592 */         for (String synonym : this.synonyms) {
/*  593 */           if (synonym.equalsIgnoreCase(tableTypeName)) {
/*  594 */             return true;
/*      */           }
/*      */         } 
/*      */       }
/*  598 */       return false;
/*      */     }
/*      */     
/*      */     static TableType getTableTypeCompliantWith(String tableTypeName) {
/*  602 */       for (TableType tableType : values()) {
/*  603 */         if (tableType.compliesWith(tableTypeName)) {
/*  604 */           return tableType;
/*      */         }
/*      */       } 
/*  607 */       return UNKNOWN;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected enum ProcedureType
/*      */   {
/*  615 */     PROCEDURE, FUNCTION;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  652 */   protected static final byte[] TABLE_AS_BYTES = "TABLE".getBytes();
/*      */   
/*  654 */   protected static final byte[] SYSTEM_TABLE_AS_BYTES = "SYSTEM TABLE".getBytes();
/*      */   
/*      */   private static final int UPDATE_RULE = 9;
/*      */   
/*  658 */   protected static final byte[] VIEW_AS_BYTES = "VIEW".getBytes();
/*      */   
/*      */   private static final Constructor<?> JDBC_4_DBMD_SHOW_CTOR;
/*      */   
/*      */   private static final Constructor<?> JDBC_4_DBMD_IS_CTOR;
/*      */   
/*      */   static {
/*  665 */     if (Util.isJdbc4()) {
/*      */       try {
/*  667 */         JDBC_4_DBMD_SHOW_CTOR = Class.forName("com.mysql.jdbc.JDBC4DatabaseMetaData").getConstructor(new Class[] { MySQLConnection.class, String.class });
/*      */         
/*  669 */         JDBC_4_DBMD_IS_CTOR = Class.forName("com.mysql.jdbc.JDBC4DatabaseMetaDataUsingInfoSchema").getConstructor(new Class[] { MySQLConnection.class, String.class });
/*      */       }
/*  671 */       catch (SecurityException e) {
/*  672 */         throw new RuntimeException(e);
/*  673 */       } catch (NoSuchMethodException e) {
/*  674 */         throw new RuntimeException(e);
/*  675 */       } catch (ClassNotFoundException e) {
/*  676 */         throw new RuntimeException(e);
/*      */       } 
/*      */     } else {
/*  679 */       JDBC_4_DBMD_IS_CTOR = null;
/*  680 */       JDBC_4_DBMD_SHOW_CTOR = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  685 */   private static final String[] MYSQL_KEYWORDS = new String[] { "ACCESSIBLE", "ADD", "ALL", "ALTER", "ANALYZE", "AND", "ARRAY", "AS", "ASC", "ASENSITIVE", "BEFORE", "BETWEEN", "BIGINT", "BINARY", "BLOB", "BOTH", "BY", "CALL", "CASCADE", "CASE", "CHANGE", "CHAR", "CHARACTER", "CHECK", "COLLATE", "COLUMN", "CONDITION", "CONSTRAINT", "CONTINUE", "CONVERT", "CREATE", "CROSS", "CUBE", "CUME_DIST", "CURRENT_DATE", "CURRENT_TIME", "CURRENT_TIMESTAMP", "CURRENT_USER", "CURSOR", "DATABASE", "DATABASES", "DAY_HOUR", "DAY_MICROSECOND", "DAY_MINUTE", "DAY_SECOND", "DEC", "DECIMAL", "DECLARE", "DEFAULT", "DELAYED", "DELETE", "DENSE_RANK", "DESC", "DESCRIBE", "DETERMINISTIC", "DISTINCT", "DISTINCTROW", "DIV", "DOUBLE", "DROP", "DUAL", "EACH", "ELSE", "ELSEIF", "EMPTY", "ENCLOSED", "ESCAPED", "EXCEPT", "EXISTS", "EXIT", "EXPLAIN", "FALSE", "FETCH", "FIRST_VALUE", "FLOAT", "FLOAT4", "FLOAT8", "FOR", "FORCE", "FOREIGN", "FROM", "FULLTEXT", "FUNCTION", "GENERATED", "GET", "GRANT", "GROUP", "GROUPING", "GROUPS", "HAVING", "HIGH_PRIORITY", "HOUR_MICROSECOND", "HOUR_MINUTE", "HOUR_SECOND", "IF", "IGNORE", "IN", "INDEX", "INFILE", "INNER", "INOUT", "INSENSITIVE", "INSERT", "INT", "INT1", "INT2", "INT3", "INT4", "INT8", "INTEGER", "INTERVAL", "INTO", "IO_AFTER_GTIDS", "IO_BEFORE_GTIDS", "IS", "ITERATE", "JOIN", "JSON_TABLE", "KEY", "KEYS", "KILL", "LAG", "LAST_VALUE", "LATERAL", "LEAD", "LEADING", "LEAVE", "LEFT", "LIKE", "LIMIT", "LINEAR", "LINES", "LOAD", "LOCALTIME", "LOCALTIMESTAMP", "LOCK", "LONG", "LONGBLOB", "LONGTEXT", "LOOP", "LOW_PRIORITY", "MASTER_BIND", "MASTER_SSL_VERIFY_SERVER_CERT", "MATCH", "MAXVALUE", "MEDIUMBLOB", "MEDIUMINT", "MEDIUMTEXT", "MEMBER", "MIDDLEINT", "MINUTE_MICROSECOND", "MINUTE_SECOND", "MOD", "MODIFIES", "NATURAL", "NOT", "NO_WRITE_TO_BINLOG", "NTH_VALUE", "NTILE", "NULL", "NUMERIC", "OF", "ON", "OPTIMIZE", "OPTIMIZER_COSTS", "OPTION", "OPTIONALLY", "OR", "ORDER", "OUT", "OUTER", "OUTFILE", "OVER", "PARTITION", "PERCENT_RANK", "PRECISION", "PRIMARY", "PROCEDURE", "PURGE", "RANGE", "RANK", "READ", "READS", "READ_WRITE", "REAL", "RECURSIVE", "REFERENCES", "REGEXP", "RELEASE", "RENAME", "REPEAT", "REPLACE", "REQUIRE", "RESIGNAL", "RESTRICT", "RETURN", "REVOKE", "RIGHT", "RLIKE", "ROW", "ROWS", "ROW_NUMBER", "SCHEMA", "SCHEMAS", "SECOND_MICROSECOND", "SELECT", "SENSITIVE", "SEPARATOR", "SET", "SHOW", "SIGNAL", "SMALLINT", "SPATIAL", "SPECIFIC", "SQL", "SQLEXCEPTION", "SQLSTATE", "SQLWARNING", "SQL_BIG_RESULT", "SQL_CALC_FOUND_ROWS", "SQL_SMALL_RESULT", "SSL", "STARTING", "STORED", "STRAIGHT_JOIN", "SYSTEM", "TABLE", "TERMINATED", "THEN", "TINYBLOB", "TINYINT", "TINYTEXT", "TO", "TRAILING", "TRIGGER", "TRUE", "UNDO", "UNION", "UNIQUE", "UNLOCK", "UNSIGNED", "UPDATE", "USAGE", "USE", "USING", "UTC_DATE", "UTC_TIME", "UTC_TIMESTAMP", "VALUES", "VARBINARY", "VARCHAR", "VARCHARACTER", "VARYING", "VIRTUAL", "WHEN", "WHERE", "WHILE", "WINDOW", "WITH", "WRITE", "XOR", "YEAR_MONTH", "ZEROFILL" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  708 */   private static final String[] SQL92_KEYWORDS = new String[] { "ABSOLUTE", "ACTION", "ADD", "ALL", "ALLOCATE", "ALTER", "AND", "ANY", "ARE", "AS", "ASC", "ASSERTION", "AT", "AUTHORIZATION", "AVG", "BEGIN", "BETWEEN", "BIT", "BIT_LENGTH", "BOTH", "BY", "CASCADE", "CASCADED", "CASE", "CAST", "CATALOG", "CHAR", "CHARACTER", "CHARACTER_LENGTH", "CHAR_LENGTH", "CHECK", "CLOSE", "COALESCE", "COLLATE", "COLLATION", "COLUMN", "COMMIT", "CONNECT", "CONNECTION", "CONSTRAINT", "CONSTRAINTS", "CONTINUE", "CONVERT", "CORRESPONDING", "COUNT", "CREATE", "CROSS", "CURRENT", "CURRENT_DATE", "CURRENT_TIME", "CURRENT_TIMESTAMP", "CURRENT_USER", "CURSOR", "DATE", "DAY", "DEALLOCATE", "DEC", "DECIMAL", "DECLARE", "DEFAULT", "DEFERRABLE", "DEFERRED", "DELETE", "DESC", "DESCRIBE", "DESCRIPTOR", "DIAGNOSTICS", "DISCONNECT", "DISTINCT", "DOMAIN", "DOUBLE", "DROP", "ELSE", "END", "END-EXEC", "ESCAPE", "EXCEPT", "EXCEPTION", "EXEC", "EXECUTE", "EXISTS", "EXTERNAL", "EXTRACT", "FALSE", "FETCH", "FIRST", "FLOAT", "FOR", "FOREIGN", "FOUND", "FROM", "FULL", "GET", "GLOBAL", "GO", "GOTO", "GRANT", "GROUP", "HAVING", "HOUR", "IDENTITY", "IMMEDIATE", "IN", "INDICATOR", "INITIALLY", "INNER", "INPUT", "INSENSITIVE", "INSERT", "INT", "INTEGER", "INTERSECT", "INTERVAL", "INTO", "IS", "ISOLATION", "JOIN", "KEY", "LANGUAGE", "LAST", "LEADING", "LEFT", "LEVEL", "LIKE", "LOCAL", "LOWER", "MATCH", "MAX", "MIN", "MINUTE", "MODULE", "MONTH", "NAMES", "NATIONAL", "NATURAL", "NCHAR", "NEXT", "NO", "NOT", "NULL", "NULLIF", "NUMERIC", "OCTET_LENGTH", "OF", "ON", "ONLY", "OPEN", "OPTION", "OR", "ORDER", "OUTER", "OUTPUT", "OVERLAPS", "PAD", "PARTIAL", "POSITION", "PRECISION", "PREPARE", "PRESERVE", "PRIMARY", "PRIOR", "PRIVILEGES", "PROCEDURE", "PUBLIC", "READ", "REAL", "REFERENCES", "RELATIVE", "RESTRICT", "REVOKE", "RIGHT", "ROLLBACK", "ROWS", "SCHEMA", "SCROLL", "SECOND", "SECTION", "SELECT", "SESSION", "SESSION_USER", "SET", "SIZE", "SMALLINT", "SOME", "SPACE", "SQL", "SQLCODE", "SQLERROR", "SQLSTATE", "SUBSTRING", "SUM", "SYSTEM_USER", "TABLE", "TEMPORARY", "THEN", "TIME", "TIMESTAMP", "TIMEZONE_HOUR", "TIMEZONE_MINUTE", "TO", "TRAILING", "TRANSACTION", "TRANSLATE", "TRANSLATION", "TRIM", "TRUE", "UNION", "UNIQUE", "UNKNOWN", "UPDATE", "UPPER", "USAGE", "USER", "USING", "VALUE", "VALUES", "VARCHAR", "VARYING", "VIEW", "WHEN", "WHENEVER", "WHERE", "WITH", "WORK", "WRITE", "YEAR", "ZONE" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  727 */   private static final String[] SQL2003_KEYWORDS = new String[] { "ABS", "ALL", "ALLOCATE", "ALTER", "AND", "ANY", "ARE", "ARRAY", "AS", "ASENSITIVE", "ASYMMETRIC", "AT", "ATOMIC", "AUTHORIZATION", "AVG", "BEGIN", "BETWEEN", "BIGINT", "BINARY", "BLOB", "BOOLEAN", "BOTH", "BY", "CALL", "CALLED", "CARDINALITY", "CASCADED", "CASE", "CAST", "CEIL", "CEILING", "CHAR", "CHARACTER", "CHARACTER_LENGTH", "CHAR_LENGTH", "CHECK", "CLOB", "CLOSE", "COALESCE", "COLLATE", "COLLECT", "COLUMN", "COMMIT", "CONDITION", "CONNECT", "CONSTRAINT", "CONVERT", "CORR", "CORRESPONDING", "COUNT", "COVAR_POP", "COVAR_SAMP", "CREATE", "CROSS", "CUBE", "CUME_DIST", "CURRENT", "CURRENT_DATE", "CURRENT_DEFAULT_TRANSFORM_GROUP", "CURRENT_PATH", "CURRENT_ROLE", "CURRENT_TIME", "CURRENT_TIMESTAMP", "CURRENT_TRANSFORM_GROUP_FOR_TYPE", "CURRENT_USER", "CURSOR", "CYCLE", "DATE", "DAY", "DEALLOCATE", "DEC", "DECIMAL", "DECLARE", "DEFAULT", "DELETE", "DENSE_RANK", "DEREF", "DESCRIBE", "DETERMINISTIC", "DISCONNECT", "DISTINCT", "DOUBLE", "DROP", "DYNAMIC", "EACH", "ELEMENT", "ELSE", "END", "END-EXEC", "ESCAPE", "EVERY", "EXCEPT", "EXEC", "EXECUTE", "EXISTS", "EXP", "EXTERNAL", "EXTRACT", "FALSE", "FETCH", "FILTER", "FLOAT", "FLOOR", "FOR", "FOREIGN", "FREE", "FROM", "FULL", "FUNCTION", "FUSION", "GET", "GLOBAL", "GRANT", "GROUP", "GROUPING", "HAVING", "HOLD", "HOUR", "IDENTITY", "IN", "INDICATOR", "INNER", "INOUT", "INSENSITIVE", "INSERT", "INT", "INTEGER", "INTERSECT", "INTERSECTION", "INTERVAL", "INTO", "IS", "JOIN", "LANGUAGE", "LARGE", "LATERAL", "LEADING", "LEFT", "LIKE", "LN", "LOCAL", "LOCALTIME", "LOCALTIMESTAMP", "LOWER", "MATCH", "MAX", "MEMBER", "MERGE", "METHOD", "MIN", "MINUTE", "MOD", "MODIFIES", "MODULE", "MONTH", "MULTISET", "NATIONAL", "NATURAL", "NCHAR", "NCLOB", "NEW", "NO", "NONE", "NORMALIZE", "NOT", "NULL", "NULLIF", "NUMERIC", "OCTET_LENGTH", "OF", "OLD", "ON", "ONLY", "OPEN", "OR", "ORDER", "OUT", "OUTER", "OVER", "OVERLAPS", "OVERLAY", "PARAMETER", "PARTITION", "PERCENTILE_CONT", "PERCENTILE_DISC", "PERCENT_RANK", "POSITION", "POWER", "PRECISION", "PREPARE", "PRIMARY", "PROCEDURE", "RANGE", "RANK", "READS", "REAL", "RECURSIVE", "REF", "REFERENCES", "REFERENCING", "REGR_AVGX", "REGR_AVGY", "REGR_COUNT", "REGR_INTERCEPT", "REGR_R2", "REGR_SLOPE", "REGR_SXX", "REGR_SXY", "REGR_SYY", "RELEASE", "RESULT", "RETURN", "RETURNS", "REVOKE", "RIGHT", "ROLLBACK", "ROLLUP", "ROW", "ROWS", "ROW_NUMBER", "SAVEPOINT", "SCOPE", "SCROLL", "SEARCH", "SECOND", "SELECT", "SENSITIVE", "SESSION_USER", "SET", "SIMILAR", "SMALLINT", "SOME", "SPECIFIC", "SPECIFICTYPE", "SQL", "SQLEXCEPTION", "SQLSTATE", "SQLWARNING", "SQRT", "START", "STATIC", "STDDEV_POP", "STDDEV_SAMP", "SUBMULTISET", "SUBSTRING", "SUM", "SYMMETRIC", "SYSTEM", "SYSTEM_USER", "TABLE", "TABLESAMPLE", "THEN", "TIME", "TIMESTAMP", "TIMEZONE_HOUR", "TIMEZONE_MINUTE", "TO", "TRAILING", "TRANSLATE", "TRANSLATION", "TREAT", "TRIGGER", "TRIM", "TRUE", "UESCAPE", "UNION", "UNIQUE", "UNKNOWN", "UNNEST", "UPDATE", "UPPER", "USER", "USING", "VALUE", "VALUES", "VARCHAR", "VARYING", "VAR_POP", "VAR_SAMP", "WHEN", "WHENEVER", "WHERE", "WIDTH_BUCKET", "WINDOW", "WITH", "WITHIN", "WITHOUT", "YEAR" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  751 */   private static volatile String mysqlKeywords = null;
/*      */ 
/*      */   
/*      */   protected MySQLConnection conn;
/*      */ 
/*      */   
/*  757 */   protected String database = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final String quotedId;
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
/*      */   protected static DatabaseMetaData getInstance(MySQLConnection connToSet, String databaseToSet, boolean checkForInfoSchema) throws SQLException {
/*  774 */     if (!Util.isJdbc4()) {
/*  775 */       if (checkForInfoSchema && connToSet.getUseInformationSchema() && connToSet.versionMeetsMinimum(5, 0, 7)) {
/*  776 */         return new DatabaseMetaDataUsingInfoSchema(connToSet, databaseToSet);
/*      */       }
/*      */       
/*  779 */       return new DatabaseMetaData(connToSet, databaseToSet);
/*      */     } 
/*      */     
/*  782 */     if (checkForInfoSchema && connToSet.getUseInformationSchema() && connToSet.versionMeetsMinimum(5, 0, 7))
/*      */     {
/*  784 */       return (DatabaseMetaData)Util.handleNewInstance(JDBC_4_DBMD_IS_CTOR, new Object[] { connToSet, databaseToSet }, connToSet.getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/*  788 */     return (DatabaseMetaData)Util.handleNewInstance(JDBC_4_DBMD_SHOW_CTOR, new Object[] { connToSet, databaseToSet }, connToSet.getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected DatabaseMetaData(MySQLConnection connToSet, String databaseToSet) {
/*  798 */     this.conn = connToSet;
/*  799 */     this.database = databaseToSet;
/*  800 */     this.exceptionInterceptor = this.conn.getExceptionInterceptor();
/*      */     
/*  802 */     String identifierQuote = null;
/*      */     try {
/*  804 */       identifierQuote = getIdentifierQuoteString();
/*  805 */     } catch (SQLException sqlEx) {
/*      */       
/*  807 */       AssertionFailedException.shouldNotHappen(sqlEx);
/*      */     } finally {
/*  809 */       this.quotedId = identifierQuote;
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
/*      */   public boolean allProceduresAreCallable() throws SQLException {
/*  821 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean allTablesAreSelectable() throws SQLException {
/*  831 */     return false;
/*      */   }
/*      */   
/*      */   private ResultSet buildResultSet(Field[] fields, ArrayList<ResultSetRow> rows) throws SQLException {
/*  835 */     return buildResultSet(fields, rows, this.conn);
/*      */   }
/*      */   
/*      */   static ResultSet buildResultSet(Field[] fields, ArrayList<ResultSetRow> rows, MySQLConnection c) throws SQLException {
/*  839 */     int fieldsLength = fields.length;
/*      */     
/*  841 */     for (int i = 0; i < fieldsLength; i++) {
/*  842 */       int jdbcType = fields[i].getSQLType();
/*      */       
/*  844 */       switch (jdbcType) {
/*      */         case -1:
/*      */         case 1:
/*      */         case 12:
/*  848 */           fields[i].setEncoding(c.getCharacterSetMetadata(), c);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  854 */       fields[i].setConnection(c);
/*  855 */       fields[i].setUseOldNameMetadata(true);
/*      */     } 
/*      */     
/*  858 */     return ResultSetImpl.getInstance(c.getCatalog(), fields, new RowDataStatic(rows), c, null, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void convertToJdbcFunctionList(String catalog, ResultSet proceduresRs, boolean needsClientFiltering, String db, List<ComparableWrapper<String, ResultSetRow>> procedureRows, int nameIndex, Field[] fields) throws SQLException {
/*  863 */     while (proceduresRs.next()) {
/*  864 */       boolean shouldAdd = true;
/*      */       
/*  866 */       if (needsClientFiltering) {
/*  867 */         shouldAdd = false;
/*      */         
/*  869 */         String procDb = proceduresRs.getString(1);
/*      */         
/*  871 */         if (db == null && procDb == null) {
/*  872 */           shouldAdd = true;
/*  873 */         } else if (db != null && db.equals(procDb)) {
/*  874 */           shouldAdd = true;
/*      */         } 
/*      */       } 
/*      */       
/*  878 */       if (shouldAdd) {
/*  879 */         String functionName = proceduresRs.getString(nameIndex);
/*      */         
/*  881 */         byte[][] rowData = (byte[][])null;
/*      */         
/*  883 */         if (fields != null && fields.length == 9) {
/*      */           
/*  885 */           rowData = new byte[9][];
/*  886 */           rowData[0] = (catalog == null) ? null : s2b(catalog);
/*  887 */           rowData[1] = null;
/*  888 */           rowData[2] = s2b(functionName);
/*  889 */           rowData[3] = null;
/*  890 */           rowData[4] = null;
/*  891 */           rowData[5] = null;
/*  892 */           rowData[6] = s2b(proceduresRs.getString("comment"));
/*  893 */           rowData[7] = s2b(Integer.toString(2));
/*  894 */           rowData[8] = s2b(functionName);
/*      */         } else {
/*      */           
/*  897 */           rowData = new byte[6][];
/*      */           
/*  899 */           rowData[0] = (catalog == null) ? null : s2b(catalog);
/*  900 */           rowData[1] = null;
/*  901 */           rowData[2] = s2b(functionName);
/*  902 */           rowData[3] = s2b(proceduresRs.getString("comment"));
/*  903 */           rowData[4] = s2b(Integer.toString(getJDBC4FunctionNoTableConstant()));
/*  904 */           rowData[5] = s2b(functionName);
/*      */         } 
/*      */         
/*  907 */         procedureRows.add(new ComparableWrapper<String, ResultSetRow>(getFullyQualifiedName(catalog, functionName), new ByteArrayRow(rowData, getExceptionInterceptor())));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getFullyQualifiedName(String catalog, String entity) {
/*  917 */     StringBuilder fullyQualifiedName = new StringBuilder(StringUtils.quoteIdentifier((catalog == null) ? "" : catalog, this.quotedId, this.conn.getPedantic()));
/*      */     
/*  919 */     fullyQualifiedName.append('.');
/*  920 */     fullyQualifiedName.append(StringUtils.quoteIdentifier(entity, this.quotedId, this.conn.getPedantic()));
/*  921 */     return fullyQualifiedName.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getJDBC4FunctionNoTableConstant() {
/*  931 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void convertToJdbcProcedureList(boolean fromSelect, String catalog, ResultSet proceduresRs, boolean needsClientFiltering, String db, List<ComparableWrapper<String, ResultSetRow>> procedureRows, int nameIndex) throws SQLException {
/*  936 */     while (proceduresRs.next()) {
/*  937 */       boolean shouldAdd = true;
/*      */       
/*  939 */       if (needsClientFiltering) {
/*  940 */         shouldAdd = false;
/*      */         
/*  942 */         String procDb = proceduresRs.getString(1);
/*      */         
/*  944 */         if (db == null && procDb == null) {
/*  945 */           shouldAdd = true;
/*  946 */         } else if (db != null && db.equals(procDb)) {
/*  947 */           shouldAdd = true;
/*      */         } 
/*      */       } 
/*      */       
/*  951 */       if (shouldAdd) {
/*  952 */         String procedureName = proceduresRs.getString(nameIndex);
/*  953 */         byte[][] rowData = new byte[9][];
/*  954 */         rowData[0] = (catalog == null) ? null : s2b(catalog);
/*  955 */         rowData[1] = null;
/*  956 */         rowData[2] = s2b(procedureName);
/*  957 */         rowData[3] = null;
/*  958 */         rowData[4] = null;
/*  959 */         rowData[5] = null;
/*  960 */         rowData[6] = s2b(proceduresRs.getString("comment"));
/*      */         
/*  962 */         boolean isFunction = fromSelect ? "FUNCTION".equalsIgnoreCase(proceduresRs.getString("type")) : false;
/*  963 */         rowData[7] = s2b(isFunction ? Integer.toString(2) : Integer.toString(1));
/*      */         
/*  965 */         rowData[8] = s2b(procedureName);
/*      */         
/*  967 */         procedureRows.add(new ComparableWrapper<String, ResultSetRow>(getFullyQualifiedName(catalog, procedureName), new ByteArrayRow(rowData, getExceptionInterceptor())));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private ResultSetRow convertTypeDescriptorToProcedureRow(byte[] procNameAsBytes, byte[] procCatAsBytes, String paramName, boolean isOutParam, boolean isInParam, boolean isReturnParam, TypeDescriptor typeDesc, boolean forGetFunctionColumns, int ordinal) throws SQLException {
/*  975 */     byte[][] row = forGetFunctionColumns ? new byte[17][] : new byte[20][];
/*  976 */     row[0] = procCatAsBytes;
/*  977 */     row[1] = null;
/*  978 */     row[2] = procNameAsBytes;
/*  979 */     row[3] = s2b(paramName);
/*  980 */     row[4] = s2b(String.valueOf(getColumnType(isOutParam, isInParam, isReturnParam, forGetFunctionColumns)));
/*  981 */     row[5] = s2b(Short.toString(typeDesc.dataType));
/*  982 */     row[6] = s2b(typeDesc.typeName);
/*  983 */     row[7] = (typeDesc.columnSize == null) ? null : s2b(typeDesc.columnSize.toString());
/*  984 */     row[8] = row[7];
/*  985 */     row[9] = (typeDesc.decimalDigits == null) ? null : s2b(typeDesc.decimalDigits.toString());
/*  986 */     row[10] = s2b(Integer.toString(typeDesc.numPrecRadix));
/*      */     
/*  988 */     switch (typeDesc.nullability) {
/*      */       case 0:
/*  990 */         row[11] = s2b(String.valueOf(0));
/*      */         break;
/*      */       
/*      */       case 1:
/*  994 */         row[11] = s2b(String.valueOf(1));
/*      */         break;
/*      */       
/*      */       case 2:
/*  998 */         row[11] = s2b(String.valueOf(2));
/*      */         break;
/*      */       
/*      */       default:
/* 1002 */         throw SQLError.createSQLException("Internal error while parsing callable statement metadata (unknown nullability value fount)", "S1000", getExceptionInterceptor());
/*      */     } 
/*      */ 
/*      */     
/* 1006 */     row[12] = null;
/*      */     
/* 1008 */     if (forGetFunctionColumns) {
/*      */       
/* 1010 */       row[13] = null;
/*      */ 
/*      */       
/* 1013 */       row[14] = s2b(String.valueOf(ordinal));
/*      */ 
/*      */       
/* 1016 */       row[15] = s2b(typeDesc.isNullable);
/*      */ 
/*      */       
/* 1019 */       row[16] = procNameAsBytes;
/*      */     } else {
/*      */       
/* 1022 */       row[13] = null;
/*      */ 
/*      */       
/* 1025 */       row[14] = null;
/*      */ 
/*      */       
/* 1028 */       row[15] = null;
/*      */ 
/*      */       
/* 1031 */       row[16] = null;
/*      */ 
/*      */       
/* 1034 */       row[17] = s2b(String.valueOf(ordinal));
/*      */ 
/*      */       
/* 1037 */       row[18] = s2b(typeDesc.isNullable);
/*      */ 
/*      */       
/* 1040 */       row[19] = procNameAsBytes;
/*      */     } 
/*      */     
/* 1043 */     return new ByteArrayRow(row, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getColumnType(boolean isOutParam, boolean isInParam, boolean isReturnParam, boolean forGetFunctionColumns) {
/* 1062 */     if (isInParam && isOutParam)
/* 1063 */       return 2; 
/* 1064 */     if (isInParam)
/* 1065 */       return 1; 
/* 1066 */     if (isOutParam)
/* 1067 */       return 4; 
/* 1068 */     if (isReturnParam) {
/* 1069 */       return 5;
/*      */     }
/* 1071 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ExceptionInterceptor getExceptionInterceptor() {
/* 1078 */     return this.exceptionInterceptor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
/* 1089 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
/* 1099 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean deletesAreDetected(int type) throws SQLException {
/* 1114 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
/* 1126 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<ResultSetRow> extractForeignKeyForTable(ArrayList<ResultSetRow> rows, ResultSet rs, String catalog) throws SQLException {
/* 1143 */     byte[][] row = new byte[3][];
/* 1144 */     row[0] = rs.getBytes(1);
/* 1145 */     row[1] = s2b("SUPPORTS_FK");
/*      */     
/* 1147 */     String createTableString = rs.getString(2);
/* 1148 */     StringTokenizer lineTokenizer = new StringTokenizer(createTableString, "\n");
/* 1149 */     StringBuilder commentBuf = new StringBuilder("comment; ");
/* 1150 */     boolean firstTime = true;
/*      */     
/* 1152 */     while (lineTokenizer.hasMoreTokens()) {
/* 1153 */       String line = lineTokenizer.nextToken().trim();
/*      */       
/* 1155 */       String constraintName = null;
/*      */       
/* 1157 */       if (StringUtils.startsWithIgnoreCase(line, "CONSTRAINT")) {
/* 1158 */         boolean usingBackTicks = true;
/* 1159 */         int beginPos = StringUtils.indexOfQuoteDoubleAware(line, this.quotedId, 0);
/*      */         
/* 1161 */         if (beginPos == -1) {
/* 1162 */           beginPos = line.indexOf("\"");
/* 1163 */           usingBackTicks = false;
/*      */         } 
/*      */         
/* 1166 */         if (beginPos != -1) {
/* 1167 */           int endPos = -1;
/*      */           
/* 1169 */           if (usingBackTicks) {
/* 1170 */             endPos = StringUtils.indexOfQuoteDoubleAware(line, this.quotedId, beginPos + 1);
/*      */           } else {
/* 1172 */             endPos = StringUtils.indexOfQuoteDoubleAware(line, "\"", beginPos + 1);
/*      */           } 
/*      */           
/* 1175 */           if (endPos != -1) {
/* 1176 */             constraintName = line.substring(beginPos + 1, endPos);
/* 1177 */             line = line.substring(endPos + 1, line.length()).trim();
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1182 */       if (line.startsWith("FOREIGN KEY")) {
/* 1183 */         if (line.endsWith(",")) {
/* 1184 */           line = line.substring(0, line.length() - 1);
/*      */         }
/*      */         
/* 1187 */         int indexOfFK = line.indexOf("FOREIGN KEY");
/*      */         
/* 1189 */         String localColumnName = null;
/* 1190 */         String referencedCatalogName = StringUtils.quoteIdentifier(catalog, this.quotedId, this.conn.getPedantic());
/* 1191 */         String referencedTableName = null;
/* 1192 */         String referencedColumnName = null;
/*      */         
/* 1194 */         if (indexOfFK != -1) {
/* 1195 */           int afterFk = indexOfFK + "FOREIGN KEY".length();
/*      */           
/* 1197 */           int indexOfRef = StringUtils.indexOfIgnoreCase(afterFk, line, "REFERENCES", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
/*      */           
/* 1199 */           if (indexOfRef != -1) {
/*      */             
/* 1201 */             int indexOfParenOpen = line.indexOf('(', afterFk);
/* 1202 */             int indexOfParenClose = StringUtils.indexOfIgnoreCase(indexOfParenOpen, line, ")", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
/*      */ 
/*      */             
/* 1205 */             if (indexOfParenOpen == -1 || indexOfParenClose == -1);
/*      */ 
/*      */ 
/*      */             
/* 1209 */             localColumnName = line.substring(indexOfParenOpen + 1, indexOfParenClose);
/*      */             
/* 1211 */             int afterRef = indexOfRef + "REFERENCES".length();
/*      */             
/* 1213 */             int referencedColumnBegin = StringUtils.indexOfIgnoreCase(afterRef, line, "(", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
/*      */ 
/*      */             
/* 1216 */             if (referencedColumnBegin != -1) {
/* 1217 */               referencedTableName = line.substring(afterRef, referencedColumnBegin);
/*      */               
/* 1219 */               int referencedColumnEnd = StringUtils.indexOfIgnoreCase(referencedColumnBegin + 1, line, ")", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
/*      */ 
/*      */               
/* 1222 */               if (referencedColumnEnd != -1) {
/* 1223 */                 referencedColumnName = line.substring(referencedColumnBegin + 1, referencedColumnEnd);
/*      */               }
/*      */               
/* 1226 */               int indexOfCatalogSep = StringUtils.indexOfIgnoreCase(0, referencedTableName, ".", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
/*      */ 
/*      */               
/* 1229 */               if (indexOfCatalogSep != -1) {
/* 1230 */                 referencedCatalogName = referencedTableName.substring(0, indexOfCatalogSep);
/* 1231 */                 referencedTableName = referencedTableName.substring(indexOfCatalogSep + 1);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/* 1237 */         if (!firstTime) {
/* 1238 */           commentBuf.append("; ");
/*      */         } else {
/* 1240 */           firstTime = false;
/*      */         } 
/*      */         
/* 1243 */         if (constraintName != null) {
/* 1244 */           commentBuf.append(constraintName);
/*      */         } else {
/* 1246 */           commentBuf.append("not_available");
/*      */         } 
/*      */         
/* 1249 */         commentBuf.append("(");
/* 1250 */         commentBuf.append(localColumnName);
/* 1251 */         commentBuf.append(") REFER ");
/* 1252 */         commentBuf.append(referencedCatalogName);
/* 1253 */         commentBuf.append("/");
/* 1254 */         commentBuf.append(referencedTableName);
/* 1255 */         commentBuf.append("(");
/* 1256 */         commentBuf.append(referencedColumnName);
/* 1257 */         commentBuf.append(")");
/*      */         
/* 1259 */         int lastParenIndex = line.lastIndexOf(")");
/*      */         
/* 1261 */         if (lastParenIndex != line.length() - 1) {
/* 1262 */           String cascadeOptions = line.substring(lastParenIndex + 1);
/* 1263 */           commentBuf.append(" ");
/* 1264 */           commentBuf.append(cascadeOptions);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1269 */     row[2] = s2b(commentBuf.toString());
/* 1270 */     rows.add(new ByteArrayRow(row, getExceptionInterceptor()));
/*      */     
/* 1272 */     return rows;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet extractForeignKeyFromCreateTable(String catalog, String tableName) throws SQLException {
/* 1292 */     ArrayList<String> tableList = new ArrayList<String>();
/* 1293 */     ResultSet rs = null;
/* 1294 */     Statement stmt = null;
/*      */     
/* 1296 */     if (tableName != null) {
/* 1297 */       tableList.add(tableName);
/*      */     } else {
/*      */       try {
/* 1300 */         rs = getTables(catalog, "", "%", new String[] { "TABLE" });
/*      */         
/* 1302 */         while (rs.next()) {
/* 1303 */           tableList.add(rs.getString("TABLE_NAME"));
/*      */         }
/*      */       } finally {
/* 1306 */         if (rs != null) {
/* 1307 */           rs.close();
/*      */         }
/*      */         
/* 1310 */         rs = null;
/*      */       } 
/*      */     } 
/*      */     
/* 1314 */     ArrayList<ResultSetRow> rows = new ArrayList<ResultSetRow>();
/* 1315 */     Field[] fields = new Field[3];
/* 1316 */     fields[0] = new Field("", "Name", 1, 2147483647);
/* 1317 */     fields[1] = new Field("", "Type", 1, 255);
/* 1318 */     fields[2] = new Field("", "Comment", 1, 2147483647);
/*      */     
/* 1320 */     int numTables = tableList.size();
/* 1321 */     stmt = this.conn.getMetadataSafeStatement();
/*      */     
/*      */     try {
/* 1324 */       for (int i = 0; i < numTables; i++) {
/* 1325 */         String tableToExtract = tableList.get(i);
/*      */         
/* 1327 */         String query = "SHOW CREATE TABLE " + getFullyQualifiedName(catalog, tableToExtract);
/*      */         
/*      */         try {
/* 1330 */           rs = stmt.executeQuery(query);
/* 1331 */         } catch (SQLException sqlEx) {
/*      */           
/* 1333 */           String sqlState = sqlEx.getSQLState();
/*      */           
/* 1335 */           if (!"42S02".equals(sqlState) && sqlEx.getErrorCode() != 1146) {
/* 1336 */             throw sqlEx;
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1342 */         while (rs.next()) {
/* 1343 */           extractForeignKeyForTable(rows, rs, catalog);
/*      */         }
/*      */       } 
/*      */     } finally {
/* 1347 */       if (rs != null) {
/* 1348 */         rs.close();
/*      */       }
/*      */       
/* 1351 */       rs = null;
/*      */       
/* 1353 */       if (stmt != null) {
/* 1354 */         stmt.close();
/*      */       }
/*      */       
/* 1357 */       stmt = null;
/*      */     } 
/*      */     
/* 1360 */     return buildResultSet(fields, rows);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getAttributes(String arg0, String arg1, String arg2, String arg3) throws SQLException {
/* 1367 */     Field[] fields = new Field[21];
/* 1368 */     fields[0] = new Field("", "TYPE_CAT", 1, 32);
/* 1369 */     fields[1] = new Field("", "TYPE_SCHEM", 1, 32);
/* 1370 */     fields[2] = new Field("", "TYPE_NAME", 1, 32);
/* 1371 */     fields[3] = new Field("", "ATTR_NAME", 1, 32);
/* 1372 */     fields[4] = new Field("", "DATA_TYPE", 5, 32);
/* 1373 */     fields[5] = new Field("", "ATTR_TYPE_NAME", 1, 32);
/* 1374 */     fields[6] = new Field("", "ATTR_SIZE", 4, 32);
/* 1375 */     fields[7] = new Field("", "DECIMAL_DIGITS", 4, 32);
/* 1376 */     fields[8] = new Field("", "NUM_PREC_RADIX", 4, 32);
/* 1377 */     fields[9] = new Field("", "NULLABLE ", 4, 32);
/* 1378 */     fields[10] = new Field("", "REMARKS", 1, 32);
/* 1379 */     fields[11] = new Field("", "ATTR_DEF", 1, 32);
/* 1380 */     fields[12] = new Field("", "SQL_DATA_TYPE", 4, 32);
/* 1381 */     fields[13] = new Field("", "SQL_DATETIME_SUB", 4, 32);
/* 1382 */     fields[14] = new Field("", "CHAR_OCTET_LENGTH", 4, 32);
/* 1383 */     fields[15] = new Field("", "ORDINAL_POSITION", 4, 32);
/* 1384 */     fields[16] = new Field("", "IS_NULLABLE", 1, 32);
/* 1385 */     fields[17] = new Field("", "SCOPE_CATALOG", 1, 32);
/* 1386 */     fields[18] = new Field("", "SCOPE_SCHEMA", 1, 32);
/* 1387 */     fields[19] = new Field("", "SCOPE_TABLE", 1, 32);
/* 1388 */     fields[20] = new Field("", "SOURCE_DATA_TYPE", 5, 32);
/*      */     
/* 1390 */     return buildResultSet(fields, new ArrayList<ResultSetRow>());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getBestRowIdentifier(String catalog, String schema, final String table, int scope, boolean nullable) throws SQLException {
/* 1436 */     if (table == null) {
/* 1437 */       throw SQLError.createSQLException("Table not specified.", "S1009", getExceptionInterceptor());
/*      */     }
/*      */     
/* 1440 */     Field[] fields = new Field[8];
/* 1441 */     fields[0] = new Field("", "SCOPE", 5, 5);
/* 1442 */     fields[1] = new Field("", "COLUMN_NAME", 1, 32);
/* 1443 */     fields[2] = new Field("", "DATA_TYPE", 4, 32);
/* 1444 */     fields[3] = new Field("", "TYPE_NAME", 1, 32);
/* 1445 */     fields[4] = new Field("", "COLUMN_SIZE", 4, 10);
/* 1446 */     fields[5] = new Field("", "BUFFER_LENGTH", 4, 10);
/* 1447 */     fields[6] = new Field("", "DECIMAL_DIGITS", 5, 10);
/* 1448 */     fields[7] = new Field("", "PSEUDO_COLUMN", 5, 5);
/*      */     
/* 1450 */     final ArrayList<ResultSetRow> rows = new ArrayList<ResultSetRow>();
/* 1451 */     final Statement stmt = this.conn.getMetadataSafeStatement();
/*      */ 
/*      */     
/*      */     try {
/* 1455 */       (new IterateBlock<String>(getCatalogIterator(catalog))
/*      */         {
/*      */           void forEach(String catalogStr) throws SQLException {
/* 1458 */             ResultSet results = null;
/*      */             
/*      */             try {
/* 1461 */               StringBuilder queryBuf = new StringBuilder("SHOW COLUMNS FROM ");
/* 1462 */               queryBuf.append(StringUtils.quoteIdentifier(table, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()));
/* 1463 */               queryBuf.append(" FROM ");
/* 1464 */               queryBuf.append(StringUtils.quoteIdentifier(catalogStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()));
/*      */               
/* 1466 */               results = stmt.executeQuery(queryBuf.toString());
/*      */               
/* 1468 */               while (results.next()) {
/* 1469 */                 String keyType = results.getString("Key");
/*      */                 
/* 1471 */                 if (keyType != null && 
/* 1472 */                   StringUtils.startsWithIgnoreCase(keyType, "PRI")) {
/* 1473 */                   byte[][] rowVal = new byte[8][];
/* 1474 */                   rowVal[0] = Integer.toString(2).getBytes();
/* 1475 */                   rowVal[1] = results.getBytes("Field");
/*      */                   
/* 1477 */                   String type = results.getString("Type");
/* 1478 */                   int size = MysqlIO.getMaxBuf();
/* 1479 */                   int decimals = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1484 */                   if (type.indexOf("enum") != -1) {
/* 1485 */                     String temp = type.substring(type.indexOf("("), type.indexOf(")"));
/* 1486 */                     StringTokenizer tokenizer = new StringTokenizer(temp, ",");
/* 1487 */                     int maxLength = 0;
/*      */                     
/* 1489 */                     while (tokenizer.hasMoreTokens()) {
/* 1490 */                       maxLength = Math.max(maxLength, tokenizer.nextToken().length() - 2);
/*      */                     }
/*      */                     
/* 1493 */                     size = maxLength;
/* 1494 */                     decimals = 0;
/* 1495 */                     type = "enum";
/* 1496 */                   } else if (type.indexOf("(") != -1) {
/* 1497 */                     if (type.indexOf(",") != -1) {
/* 1498 */                       size = Integer.parseInt(type.substring(type.indexOf("(") + 1, type.indexOf(",")));
/* 1499 */                       decimals = Integer.parseInt(type.substring(type.indexOf(",") + 1, type.indexOf(")")));
/*      */                     } else {
/* 1501 */                       size = Integer.parseInt(type.substring(type.indexOf("(") + 1, type.indexOf(")")));
/*      */                     } 
/*      */                     
/* 1504 */                     type = type.substring(0, type.indexOf("("));
/*      */                   } 
/*      */                   
/* 1507 */                   rowVal[2] = DatabaseMetaData.this.s2b(String.valueOf(MysqlDefs.mysqlToJavaType(type)));
/* 1508 */                   rowVal[3] = DatabaseMetaData.this.s2b(type);
/* 1509 */                   rowVal[4] = Integer.toString(size + decimals).getBytes();
/* 1510 */                   rowVal[5] = Integer.toString(size + decimals).getBytes();
/* 1511 */                   rowVal[6] = Integer.toString(decimals).getBytes();
/* 1512 */                   rowVal[7] = Integer.toString(1).getBytes();
/*      */                   
/* 1514 */                   rows.add(new ByteArrayRow(rowVal, DatabaseMetaData.this.getExceptionInterceptor()));
/*      */                 }
/*      */               
/*      */               } 
/* 1518 */             } catch (SQLException sqlEx) {
/* 1519 */               if (!"42S02".equals(sqlEx.getSQLState())) {
/* 1520 */                 throw sqlEx;
/*      */               }
/*      */             } finally {
/* 1523 */               if (results != null) {
/*      */                 try {
/* 1525 */                   results.close();
/* 1526 */                 } catch (Exception ex) {}
/*      */ 
/*      */                 
/* 1529 */                 results = null;
/*      */               } 
/*      */             } 
/*      */           }
/*      */         }).doForAll();
/*      */     } finally {
/* 1535 */       if (stmt != null) {
/* 1536 */         stmt.close();
/*      */       }
/*      */     } 
/*      */     
/* 1540 */     ResultSet results = buildResultSet(fields, rows);
/*      */     
/* 1542 */     return results;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getCallStmtParameterTypes(String catalog, String quotedProcName, ProcedureType procType, String parameterNamePattern, List<ResultSetRow> resultRows, boolean forGetFunctionColumns) throws SQLException {
/* 1554 */     Statement paramRetrievalStmt = null;
/* 1555 */     ResultSet paramRetrievalRs = null;
/*      */     
/* 1557 */     if (parameterNamePattern == null) {
/* 1558 */       if (this.conn.getNullNamePatternMatchesAll()) {
/* 1559 */         parameterNamePattern = "%";
/*      */       } else {
/* 1561 */         throw SQLError.createSQLException("Parameter/Column name pattern can not be NULL or empty.", "S1009", getExceptionInterceptor());
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1566 */     String parameterDef = null;
/*      */     
/* 1568 */     byte[] procNameAsBytes = null;
/* 1569 */     byte[] procCatAsBytes = null;
/*      */     
/* 1571 */     boolean isProcedureInAnsiMode = false;
/* 1572 */     String storageDefnDelims = null;
/* 1573 */     String storageDefnClosures = null;
/*      */     
/*      */     try {
/* 1576 */       paramRetrievalStmt = this.conn.getMetadataSafeStatement();
/*      */       
/* 1578 */       String oldCatalog = this.conn.getCatalog();
/* 1579 */       if (this.conn.lowerCaseTableNames() && catalog != null && catalog.length() != 0 && oldCatalog != null && oldCatalog.length() != 0) {
/*      */ 
/*      */         
/* 1582 */         ResultSet rs = null;
/*      */         
/*      */         try {
/* 1585 */           this.conn.setCatalog(StringUtils.unQuoteIdentifier(catalog, this.quotedId));
/* 1586 */           rs = paramRetrievalStmt.executeQuery("SELECT DATABASE()");
/* 1587 */           rs.next();
/*      */           
/* 1589 */           catalog = rs.getString(1);
/*      */         }
/*      */         finally {
/*      */           
/* 1593 */           this.conn.setCatalog(oldCatalog);
/*      */           
/* 1595 */           if (rs != null) {
/* 1596 */             rs.close();
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1601 */       if (paramRetrievalStmt.getMaxRows() != 0) {
/* 1602 */         paramRetrievalStmt.setMaxRows(0);
/*      */       }
/*      */       
/* 1605 */       int dotIndex = -1;
/*      */       
/* 1607 */       if (!" ".equals(this.quotedId)) {
/* 1608 */         dotIndex = StringUtils.indexOfIgnoreCase(0, quotedProcName, ".", this.quotedId, this.quotedId, this.conn.isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
/*      */       } else {
/*      */         
/* 1611 */         dotIndex = quotedProcName.indexOf(".");
/*      */       } 
/*      */       
/* 1614 */       String dbName = null;
/*      */       
/* 1616 */       if (dotIndex != -1 && dotIndex + 1 < quotedProcName.length()) {
/* 1617 */         dbName = quotedProcName.substring(0, dotIndex);
/* 1618 */         quotedProcName = quotedProcName.substring(dotIndex + 1);
/*      */       } else {
/* 1620 */         dbName = StringUtils.quoteIdentifier(catalog, this.quotedId, this.conn.getPedantic());
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1625 */       String tmpProcName = StringUtils.unQuoteIdentifier(quotedProcName, this.quotedId);
/*      */       try {
/* 1627 */         procNameAsBytes = StringUtils.getBytes(tmpProcName, "UTF-8");
/* 1628 */       } catch (UnsupportedEncodingException ueEx) {
/* 1629 */         procNameAsBytes = s2b(tmpProcName);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1634 */       tmpProcName = StringUtils.unQuoteIdentifier(dbName, this.quotedId);
/*      */       try {
/* 1636 */         procCatAsBytes = StringUtils.getBytes(tmpProcName, "UTF-8");
/* 1637 */       } catch (UnsupportedEncodingException ueEx) {
/* 1638 */         procCatAsBytes = s2b(tmpProcName);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1644 */       StringBuilder procNameBuf = new StringBuilder();
/* 1645 */       procNameBuf.append(dbName);
/* 1646 */       procNameBuf.append('.');
/* 1647 */       procNameBuf.append(quotedProcName);
/*      */       
/* 1649 */       String fieldName = null;
/* 1650 */       if (procType == ProcedureType.PROCEDURE) {
/* 1651 */         paramRetrievalRs = paramRetrievalStmt.executeQuery("SHOW CREATE PROCEDURE " + procNameBuf.toString());
/* 1652 */         fieldName = "Create Procedure";
/*      */       } else {
/* 1654 */         paramRetrievalRs = paramRetrievalStmt.executeQuery("SHOW CREATE FUNCTION " + procNameBuf.toString());
/* 1655 */         fieldName = "Create Function";
/*      */       } 
/*      */       
/* 1658 */       if (paramRetrievalRs.next()) {
/* 1659 */         String procedureDef = paramRetrievalRs.getString(fieldName);
/*      */         
/* 1661 */         if (!this.conn.getNoAccessToProcedureBodies() && (procedureDef == null || procedureDef.length() == 0)) {
/* 1662 */           throw SQLError.createSQLException("User does not have access to metadata required to determine stored procedure parameter types. If rights can not be granted, configure connection with \"noAccessToProcedureBodies=true\" to have driver generate parameters that represent INOUT strings irregardless of actual parameter types.", "S1000", getExceptionInterceptor());
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1669 */           String sqlMode = paramRetrievalRs.getString("sql_mode");
/*      */           
/* 1671 */           if (StringUtils.indexOfIgnoreCase(sqlMode, "ANSI") != -1) {
/* 1672 */             isProcedureInAnsiMode = true;
/*      */           }
/* 1674 */         } catch (SQLException sqlEx) {}
/*      */ 
/*      */ 
/*      */         
/* 1678 */         String identifierMarkers = isProcedureInAnsiMode ? "`\"" : "`";
/* 1679 */         String identifierAndStringMarkers = "'" + identifierMarkers;
/* 1680 */         storageDefnDelims = "(" + identifierMarkers;
/* 1681 */         storageDefnClosures = ")" + identifierMarkers;
/*      */         
/* 1683 */         if (procedureDef != null && procedureDef.length() != 0) {
/*      */           
/* 1685 */           procedureDef = StringUtils.stripComments(procedureDef, identifierAndStringMarkers, identifierAndStringMarkers, true, false, true, true);
/*      */           
/* 1687 */           int openParenIndex = StringUtils.indexOfIgnoreCase(0, procedureDef, "(", this.quotedId, this.quotedId, this.conn.isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
/*      */           
/* 1689 */           int endOfParamDeclarationIndex = 0;
/*      */           
/* 1691 */           endOfParamDeclarationIndex = endPositionOfParameterDeclaration(openParenIndex, procedureDef, this.quotedId);
/*      */           
/* 1693 */           if (procType == ProcedureType.FUNCTION) {
/*      */ 
/*      */ 
/*      */             
/* 1697 */             int returnsIndex = StringUtils.indexOfIgnoreCase(0, procedureDef, " RETURNS ", this.quotedId, this.quotedId, this.conn.isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
/*      */ 
/*      */             
/* 1700 */             int endReturnsDef = findEndOfReturnsClause(procedureDef, returnsIndex);
/*      */ 
/*      */ 
/*      */             
/* 1704 */             int declarationStart = returnsIndex + "RETURNS ".length();
/*      */             
/* 1706 */             while (declarationStart < procedureDef.length() && 
/* 1707 */               Character.isWhitespace(procedureDef.charAt(declarationStart))) {
/* 1708 */               declarationStart++;
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1714 */             String returnsDefn = procedureDef.substring(declarationStart, endReturnsDef).trim();
/* 1715 */             TypeDescriptor returnDescriptor = new TypeDescriptor(returnsDefn, "YES");
/*      */             
/* 1717 */             resultRows.add(convertTypeDescriptorToProcedureRow(procNameAsBytes, procCatAsBytes, "", false, false, true, returnDescriptor, forGetFunctionColumns, 0));
/*      */           } 
/*      */ 
/*      */           
/* 1721 */           if (openParenIndex == -1 || endOfParamDeclarationIndex == -1)
/*      */           {
/* 1723 */             throw SQLError.createSQLException("Internal error when parsing callable statement metadata", "S1000", getExceptionInterceptor());
/*      */           }
/*      */ 
/*      */           
/* 1727 */           parameterDef = procedureDef.substring(openParenIndex + 1, endOfParamDeclarationIndex);
/*      */         } 
/*      */       } 
/*      */     } finally {
/*      */       
/* 1732 */       SQLException sqlExRethrow = null;
/*      */       
/* 1734 */       if (paramRetrievalRs != null) {
/*      */         try {
/* 1736 */           paramRetrievalRs.close();
/* 1737 */         } catch (SQLException sqlEx) {
/* 1738 */           sqlExRethrow = sqlEx;
/*      */         } 
/*      */         
/* 1741 */         paramRetrievalRs = null;
/*      */       } 
/*      */       
/* 1744 */       if (paramRetrievalStmt != null) {
/*      */         try {
/* 1746 */           paramRetrievalStmt.close();
/* 1747 */         } catch (SQLException sqlEx) {
/* 1748 */           sqlExRethrow = sqlEx;
/*      */         } 
/*      */         
/* 1751 */         paramRetrievalStmt = null;
/*      */       } 
/*      */       
/* 1754 */       if (sqlExRethrow != null) {
/* 1755 */         throw sqlExRethrow;
/*      */       }
/*      */     } 
/*      */     
/* 1759 */     if (parameterDef != null) {
/* 1760 */       int ordinal = 1;
/*      */       
/* 1762 */       List<String> parseList = StringUtils.split(parameterDef, ",", storageDefnDelims, storageDefnClosures, true);
/*      */       
/* 1764 */       int parseListLen = parseList.size();
/*      */       
/* 1766 */       for (int i = 0; i < parseListLen; i++) {
/* 1767 */         String declaration = parseList.get(i);
/*      */         
/* 1769 */         if (declaration.trim().length() == 0) {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/* 1774 */         declaration = declaration.replaceAll("[\\t\\n\\x0B\\f\\r]", " ");
/* 1775 */         StringTokenizer declarationTok = new StringTokenizer(declaration, " \t");
/*      */         
/* 1777 */         String paramName = null;
/* 1778 */         boolean isOutParam = false;
/* 1779 */         boolean isInParam = false;
/*      */         
/* 1781 */         if (declarationTok.hasMoreTokens()) {
/* 1782 */           String possibleParamName = declarationTok.nextToken();
/*      */           
/* 1784 */           if (possibleParamName.equalsIgnoreCase("OUT")) {
/* 1785 */             isOutParam = true;
/*      */             
/* 1787 */             if (declarationTok.hasMoreTokens()) {
/* 1788 */               paramName = declarationTok.nextToken();
/*      */             } else {
/* 1790 */               throw SQLError.createSQLException("Internal error when parsing callable statement metadata (missing parameter name)", "S1000", getExceptionInterceptor());
/*      */             }
/*      */           
/* 1793 */           } else if (possibleParamName.equalsIgnoreCase("INOUT")) {
/* 1794 */             isOutParam = true;
/* 1795 */             isInParam = true;
/*      */             
/* 1797 */             if (declarationTok.hasMoreTokens()) {
/* 1798 */               paramName = declarationTok.nextToken();
/*      */             } else {
/* 1800 */               throw SQLError.createSQLException("Internal error when parsing callable statement metadata (missing parameter name)", "S1000", getExceptionInterceptor());
/*      */             }
/*      */           
/* 1803 */           } else if (possibleParamName.equalsIgnoreCase("IN")) {
/* 1804 */             isOutParam = false;
/* 1805 */             isInParam = true;
/*      */             
/* 1807 */             if (declarationTok.hasMoreTokens()) {
/* 1808 */               paramName = declarationTok.nextToken();
/*      */             } else {
/* 1810 */               throw SQLError.createSQLException("Internal error when parsing callable statement metadata (missing parameter name)", "S1000", getExceptionInterceptor());
/*      */             } 
/*      */           } else {
/*      */             
/* 1814 */             isOutParam = false;
/* 1815 */             isInParam = true;
/*      */             
/* 1817 */             paramName = possibleParamName;
/*      */           } 
/*      */           
/* 1820 */           TypeDescriptor typeDesc = null;
/*      */           
/* 1822 */           if (declarationTok.hasMoreTokens()) {
/* 1823 */             StringBuilder typeInfoBuf = new StringBuilder(declarationTok.nextToken());
/*      */             
/* 1825 */             while (declarationTok.hasMoreTokens()) {
/* 1826 */               typeInfoBuf.append(" ");
/* 1827 */               typeInfoBuf.append(declarationTok.nextToken());
/*      */             } 
/*      */             
/* 1830 */             String typeInfo = typeInfoBuf.toString();
/*      */             
/* 1832 */             typeDesc = new TypeDescriptor(typeInfo, "YES");
/*      */           } else {
/* 1834 */             throw SQLError.createSQLException("Internal error when parsing callable statement metadata (missing parameter type)", "S1000", getExceptionInterceptor());
/*      */           } 
/*      */ 
/*      */           
/* 1838 */           if ((paramName.startsWith("`") && paramName.endsWith("`")) || (isProcedureInAnsiMode && paramName.startsWith("\"") && paramName.endsWith("\"")))
/*      */           {
/* 1840 */             paramName = paramName.substring(1, paramName.length() - 1);
/*      */           }
/*      */           
/* 1843 */           if (StringUtils.wildCompareIgnoreCase(paramName, parameterNamePattern)) {
/* 1844 */             ResultSetRow row = convertTypeDescriptorToProcedureRow(procNameAsBytes, procCatAsBytes, paramName, isOutParam, isInParam, false, typeDesc, forGetFunctionColumns, ordinal++);
/*      */ 
/*      */             
/* 1847 */             resultRows.add(row);
/*      */           } 
/*      */         } else {
/* 1850 */           throw SQLError.createSQLException("Internal error when parsing callable statement metadata (unknown output from 'SHOW CREATE PROCEDURE')", "S1000", getExceptionInterceptor());
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
/*      */   private int endPositionOfParameterDeclaration(int beginIndex, String procedureDef, String quoteChar) throws SQLException {
/* 1876 */     int currentPos = beginIndex + 1;
/* 1877 */     int parenDepth = 1;
/*      */     
/* 1879 */     while (parenDepth > 0 && currentPos < procedureDef.length()) {
/* 1880 */       int closedParenIndex = StringUtils.indexOfIgnoreCase(currentPos, procedureDef, ")", quoteChar, quoteChar, this.conn.isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
/*      */ 
/*      */       
/* 1883 */       if (closedParenIndex != -1) {
/* 1884 */         int nextOpenParenIndex = StringUtils.indexOfIgnoreCase(currentPos, procedureDef, "(", quoteChar, quoteChar, this.conn.isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
/*      */ 
/*      */         
/* 1887 */         if (nextOpenParenIndex != -1 && nextOpenParenIndex < closedParenIndex) {
/* 1888 */           parenDepth++;
/* 1889 */           currentPos = closedParenIndex + 1; continue;
/*      */         } 
/* 1891 */         parenDepth--;
/* 1892 */         currentPos = closedParenIndex;
/*      */         
/*      */         continue;
/*      */       } 
/* 1896 */       throw SQLError.createSQLException("Internal error when parsing callable statement metadata", "S1000", getExceptionInterceptor());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1901 */     return currentPos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int findEndOfReturnsClause(String procedureDefn, int positionOfReturnKeyword) throws SQLException {
/* 1924 */     String openingMarkers = this.quotedId + "(";
/* 1925 */     String closingMarkers = this.quotedId + ")";
/*      */     
/* 1927 */     String[] tokens = { "LANGUAGE", "NOT", "DETERMINISTIC", "CONTAINS", "NO", "READ", "MODIFIES", "SQL", "COMMENT", "BEGIN", "RETURN" };
/*      */     
/* 1929 */     int startLookingAt = positionOfReturnKeyword + "RETURNS".length() + 1;
/*      */     
/* 1931 */     int endOfReturn = -1;
/*      */     int i;
/* 1933 */     for (i = 0; i < tokens.length; i++) {
/* 1934 */       int nextEndOfReturn = StringUtils.indexOfIgnoreCase(startLookingAt, procedureDefn, tokens[i], openingMarkers, closingMarkers, this.conn.isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
/*      */ 
/*      */       
/* 1937 */       if (nextEndOfReturn != -1 && (
/* 1938 */         endOfReturn == -1 || nextEndOfReturn < endOfReturn)) {
/* 1939 */         endOfReturn = nextEndOfReturn;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1944 */     if (endOfReturn != -1) {
/* 1945 */       return endOfReturn;
/*      */     }
/*      */ 
/*      */     
/* 1949 */     endOfReturn = StringUtils.indexOfIgnoreCase(startLookingAt, procedureDefn, ":", openingMarkers, closingMarkers, this.conn.isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
/*      */ 
/*      */     
/* 1952 */     if (endOfReturn != -1)
/*      */     {
/* 1954 */       for (i = endOfReturn; i > 0; i--) {
/* 1955 */         if (Character.isWhitespace(procedureDefn.charAt(i))) {
/* 1956 */           return i;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1963 */     throw SQLError.createSQLException("Internal error when parsing callable statement metadata", "S1000", getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getCascadeDeleteOption(String cascadeOptions) {
/* 1976 */     int onDeletePos = cascadeOptions.indexOf("ON DELETE");
/*      */     
/* 1978 */     if (onDeletePos != -1) {
/* 1979 */       String deleteOptions = cascadeOptions.substring(onDeletePos, cascadeOptions.length());
/*      */       
/* 1981 */       if (deleteOptions.startsWith("ON DELETE CASCADE"))
/* 1982 */         return 0; 
/* 1983 */       if (deleteOptions.startsWith("ON DELETE SET NULL"))
/* 1984 */         return 2; 
/* 1985 */       if (deleteOptions.startsWith("ON DELETE RESTRICT"))
/* 1986 */         return 1; 
/* 1987 */       if (deleteOptions.startsWith("ON DELETE NO ACTION")) {
/* 1988 */         return 3;
/*      */       }
/*      */     } 
/*      */     
/* 1992 */     return 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getCascadeUpdateOption(String cascadeOptions) {
/* 2004 */     int onUpdatePos = cascadeOptions.indexOf("ON UPDATE");
/*      */     
/* 2006 */     if (onUpdatePos != -1) {
/* 2007 */       String updateOptions = cascadeOptions.substring(onUpdatePos, cascadeOptions.length());
/*      */       
/* 2009 */       if (updateOptions.startsWith("ON UPDATE CASCADE"))
/* 2010 */         return 0; 
/* 2011 */       if (updateOptions.startsWith("ON UPDATE SET NULL"))
/* 2012 */         return 2; 
/* 2013 */       if (updateOptions.startsWith("ON UPDATE RESTRICT"))
/* 2014 */         return 1; 
/* 2015 */       if (updateOptions.startsWith("ON UPDATE NO ACTION")) {
/* 2016 */         return 3;
/*      */       }
/*      */     } 
/*      */     
/* 2020 */     return 3;
/*      */   }
/*      */   
/*      */   protected IteratorWithCleanup<String> getCatalogIterator(String catalogSpec) throws SQLException {
/*      */     IteratorWithCleanup<String> allCatalogsIter;
/* 2025 */     if (catalogSpec != null) {
/* 2026 */       if (!catalogSpec.equals("")) {
/* 2027 */         if (this.conn.getPedantic()) {
/* 2028 */           allCatalogsIter = new SingleStringIterator(catalogSpec);
/*      */         } else {
/* 2030 */           allCatalogsIter = new SingleStringIterator(StringUtils.unQuoteIdentifier(catalogSpec, this.quotedId));
/*      */         } 
/*      */       } else {
/*      */         
/* 2034 */         allCatalogsIter = new SingleStringIterator(this.database);
/*      */       } 
/* 2036 */     } else if (this.conn.getNullCatalogMeansCurrent()) {
/*      */       
/* 2038 */       allCatalogsIter = new SingleStringIterator(this.database);
/*      */     } else {
/* 2040 */       allCatalogsIter = new ResultSetIterator(getCatalogs(), 1);
/*      */     } 
/*      */     
/* 2043 */     return allCatalogsIter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getCatalogs() throws SQLException {
/* 2061 */     ResultSet results = null;
/* 2062 */     Statement stmt = null;
/*      */     
/*      */     try {
/* 2065 */       stmt = this.conn.getMetadataSafeStatement();
/* 2066 */       results = stmt.executeQuery("SHOW DATABASES");
/*      */       
/* 2068 */       int catalogsCount = 0;
/* 2069 */       if (results.last()) {
/* 2070 */         catalogsCount = results.getRow();
/* 2071 */         results.beforeFirst();
/*      */       } 
/*      */       
/* 2074 */       List<String> resultsAsList = new ArrayList<String>(catalogsCount);
/* 2075 */       while (results.next()) {
/* 2076 */         resultsAsList.add(results.getString(1));
/*      */       }
/* 2078 */       Collections.sort(resultsAsList);
/*      */       
/* 2080 */       Field[] fields = new Field[1];
/* 2081 */       fields[0] = new Field("", "TABLE_CAT", 12, results.getMetaData().getColumnDisplaySize(1));
/*      */       
/* 2083 */       ArrayList<ResultSetRow> tuples = new ArrayList<ResultSetRow>(catalogsCount);
/* 2084 */       for (String cat : resultsAsList) {
/* 2085 */         byte[][] rowVal = new byte[1][];
/* 2086 */         rowVal[0] = s2b(cat);
/* 2087 */         tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */       } 
/*      */       
/* 2090 */       return buildResultSet(fields, tuples);
/*      */     } finally {
/* 2092 */       if (results != null) {
/*      */         try {
/* 2094 */           results.close();
/* 2095 */         } catch (SQLException sqlEx) {
/* 2096 */           AssertionFailedException.shouldNotHappen(sqlEx);
/*      */         } 
/*      */         
/* 2099 */         results = null;
/*      */       } 
/*      */       
/* 2102 */       if (stmt != null) {
/*      */         try {
/* 2104 */           stmt.close();
/* 2105 */         } catch (SQLException sqlEx) {
/* 2106 */           AssertionFailedException.shouldNotHappen(sqlEx);
/*      */         } 
/*      */         
/* 2109 */         stmt = null;
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
/*      */   public String getCatalogSeparator() throws SQLException {
/* 2121 */     return ".";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCatalogTerm() throws SQLException {
/* 2134 */     return "database";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
/* 2170 */     Field[] fields = new Field[8];
/* 2171 */     fields[0] = new Field("", "TABLE_CAT", 1, 64);
/* 2172 */     fields[1] = new Field("", "TABLE_SCHEM", 1, 1);
/* 2173 */     fields[2] = new Field("", "TABLE_NAME", 1, 64);
/* 2174 */     fields[3] = new Field("", "COLUMN_NAME", 1, 64);
/* 2175 */     fields[4] = new Field("", "GRANTOR", 1, 77);
/* 2176 */     fields[5] = new Field("", "GRANTEE", 1, 77);
/* 2177 */     fields[6] = new Field("", "PRIVILEGE", 1, 64);
/* 2178 */     fields[7] = new Field("", "IS_GRANTABLE", 1, 3);
/*      */     
/* 2180 */     String grantQuery = "SELECT c.host, c.db, t.grantor, c.user, c.table_name, c.column_name, c.column_priv FROM mysql.columns_priv c, mysql.tables_priv t WHERE c.host = t.host AND c.db = t.db AND c.table_name = t.table_name AND c.db LIKE ? AND c.table_name = ? AND c.column_name LIKE ?";
/*      */ 
/*      */ 
/*      */     
/* 2184 */     PreparedStatement pStmt = null;
/* 2185 */     ResultSet results = null;
/* 2186 */     ArrayList<ResultSetRow> grantRows = new ArrayList<ResultSetRow>();
/*      */     
/*      */     try {
/* 2189 */       pStmt = prepareMetaDataSafeStatement(grantQuery);
/*      */       
/* 2191 */       pStmt.setString(1, (catalog != null && catalog.length() != 0) ? catalog : "%");
/* 2192 */       pStmt.setString(2, table);
/* 2193 */       pStmt.setString(3, columnNamePattern);
/*      */       
/* 2195 */       results = pStmt.executeQuery();
/*      */       
/* 2197 */       while (results.next()) {
/* 2198 */         String host = results.getString(1);
/* 2199 */         String db = results.getString(2);
/* 2200 */         String grantor = results.getString(3);
/* 2201 */         String user = results.getString(4);
/*      */         
/* 2203 */         if (user == null || user.length() == 0) {
/* 2204 */           user = "%";
/*      */         }
/*      */         
/* 2207 */         StringBuilder fullUser = new StringBuilder(user);
/*      */         
/* 2209 */         if (host != null && this.conn.getUseHostsInPrivileges()) {
/* 2210 */           fullUser.append("@");
/* 2211 */           fullUser.append(host);
/*      */         } 
/*      */         
/* 2214 */         String columnName = results.getString(6);
/* 2215 */         String allPrivileges = results.getString(7);
/*      */         
/* 2217 */         if (allPrivileges != null) {
/* 2218 */           allPrivileges = allPrivileges.toUpperCase(Locale.ENGLISH);
/*      */           
/* 2220 */           StringTokenizer st = new StringTokenizer(allPrivileges, ",");
/*      */           
/* 2222 */           while (st.hasMoreTokens()) {
/* 2223 */             String privilege = st.nextToken().trim();
/* 2224 */             byte[][] tuple = new byte[8][];
/* 2225 */             tuple[0] = s2b(db);
/* 2226 */             tuple[1] = null;
/* 2227 */             tuple[2] = s2b(table);
/* 2228 */             tuple[3] = s2b(columnName);
/*      */             
/* 2230 */             if (grantor != null) {
/* 2231 */               tuple[4] = s2b(grantor);
/*      */             } else {
/* 2233 */               tuple[4] = null;
/*      */             } 
/*      */             
/* 2236 */             tuple[5] = s2b(fullUser.toString());
/* 2237 */             tuple[6] = s2b(privilege);
/* 2238 */             tuple[7] = null;
/* 2239 */             grantRows.add(new ByteArrayRow(tuple, getExceptionInterceptor()));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } finally {
/* 2244 */       if (results != null) {
/*      */         try {
/* 2246 */           results.close();
/* 2247 */         } catch (Exception ex) {}
/*      */ 
/*      */         
/* 2250 */         results = null;
/*      */       } 
/*      */       
/* 2253 */       if (pStmt != null) {
/*      */         try {
/* 2255 */           pStmt.close();
/* 2256 */         } catch (Exception ex) {}
/*      */ 
/*      */         
/* 2259 */         pStmt = null;
/*      */       } 
/*      */     } 
/*      */     
/* 2263 */     return buildResultSet(fields, grantRows);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getColumns(String catalog, final String schemaPattern, final String tableNamePattern, String columnNamePattern) throws SQLException {
/* 2320 */     if (columnNamePattern == null) {
/* 2321 */       if (this.conn.getNullNamePatternMatchesAll()) {
/* 2322 */         columnNamePattern = "%";
/*      */       } else {
/* 2324 */         throw SQLError.createSQLException("Column name pattern can not be NULL or empty.", "S1009", getExceptionInterceptor());
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2329 */     final String colPattern = columnNamePattern;
/*      */     
/* 2331 */     Field[] fields = createColumnsFields();
/*      */     
/* 2333 */     final ArrayList<ResultSetRow> rows = new ArrayList<ResultSetRow>();
/* 2334 */     final Statement stmt = this.conn.getMetadataSafeStatement();
/*      */ 
/*      */     
/*      */     try {
/* 2338 */       (new IterateBlock<String>(getCatalogIterator(catalog)) { void forEach(String catalogStr) throws SQLException { // Byte code:
/*      */             //   0: new java/util/ArrayList
/*      */             //   3: dup
/*      */             //   4: invokespecial <init> : ()V
/*      */             //   7: astore_2
/*      */             //   8: aload_0
/*      */             //   9: getfield val$tableNamePattern : Ljava/lang/String;
/*      */             //   12: ifnonnull -> 108
/*      */             //   15: aconst_null
/*      */             //   16: astore_3
/*      */             //   17: aload_0
/*      */             //   18: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   21: aload_1
/*      */             //   22: aload_0
/*      */             //   23: getfield val$schemaPattern : Ljava/lang/String;
/*      */             //   26: ldc '%'
/*      */             //   28: iconst_0
/*      */             //   29: anewarray java/lang/String
/*      */             //   32: invokevirtual getTables : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Ljava/sql/ResultSet;
/*      */             //   35: astore_3
/*      */             //   36: aload_3
/*      */             //   37: invokeinterface next : ()Z
/*      */             //   42: ifeq -> 65
/*      */             //   45: aload_3
/*      */             //   46: ldc 'TABLE_NAME'
/*      */             //   48: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */             //   53: astore #4
/*      */             //   55: aload_2
/*      */             //   56: aload #4
/*      */             //   58: invokevirtual add : (Ljava/lang/Object;)Z
/*      */             //   61: pop
/*      */             //   62: goto -> 36
/*      */             //   65: jsr -> 79
/*      */             //   68: goto -> 105
/*      */             //   71: astore #5
/*      */             //   73: jsr -> 79
/*      */             //   76: aload #5
/*      */             //   78: athrow
/*      */             //   79: astore #6
/*      */             //   81: aload_3
/*      */             //   82: ifnull -> 103
/*      */             //   85: aload_3
/*      */             //   86: invokeinterface close : ()V
/*      */             //   91: goto -> 101
/*      */             //   94: astore #7
/*      */             //   96: aload #7
/*      */             //   98: invokestatic shouldNotHappen : (Ljava/lang/Exception;)V
/*      */             //   101: aconst_null
/*      */             //   102: astore_3
/*      */             //   103: ret #6
/*      */             //   105: goto -> 200
/*      */             //   108: aconst_null
/*      */             //   109: astore_3
/*      */             //   110: aload_0
/*      */             //   111: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   114: aload_1
/*      */             //   115: aload_0
/*      */             //   116: getfield val$schemaPattern : Ljava/lang/String;
/*      */             //   119: aload_0
/*      */             //   120: getfield val$tableNamePattern : Ljava/lang/String;
/*      */             //   123: iconst_0
/*      */             //   124: anewarray java/lang/String
/*      */             //   127: invokevirtual getTables : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Ljava/sql/ResultSet;
/*      */             //   130: astore_3
/*      */             //   131: aload_3
/*      */             //   132: invokeinterface next : ()Z
/*      */             //   137: ifeq -> 160
/*      */             //   140: aload_3
/*      */             //   141: ldc 'TABLE_NAME'
/*      */             //   143: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */             //   148: astore #4
/*      */             //   150: aload_2
/*      */             //   151: aload #4
/*      */             //   153: invokevirtual add : (Ljava/lang/Object;)Z
/*      */             //   156: pop
/*      */             //   157: goto -> 131
/*      */             //   160: jsr -> 174
/*      */             //   163: goto -> 200
/*      */             //   166: astore #8
/*      */             //   168: jsr -> 174
/*      */             //   171: aload #8
/*      */             //   173: athrow
/*      */             //   174: astore #9
/*      */             //   176: aload_3
/*      */             //   177: ifnull -> 198
/*      */             //   180: aload_3
/*      */             //   181: invokeinterface close : ()V
/*      */             //   186: goto -> 196
/*      */             //   189: astore #10
/*      */             //   191: aload #10
/*      */             //   193: invokestatic shouldNotHappen : (Ljava/lang/Exception;)V
/*      */             //   196: aconst_null
/*      */             //   197: astore_3
/*      */             //   198: ret #9
/*      */             //   200: aload_2
/*      */             //   201: invokevirtual iterator : ()Ljava/util/Iterator;
/*      */             //   204: astore_3
/*      */             //   205: aload_3
/*      */             //   206: invokeinterface hasNext : ()Z
/*      */             //   211: ifeq -> 1444
/*      */             //   214: aload_3
/*      */             //   215: invokeinterface next : ()Ljava/lang/Object;
/*      */             //   220: checkcast java/lang/String
/*      */             //   223: astore #4
/*      */             //   225: aconst_null
/*      */             //   226: astore #5
/*      */             //   228: new java/lang/StringBuilder
/*      */             //   231: dup
/*      */             //   232: ldc 'SHOW '
/*      */             //   234: invokespecial <init> : (Ljava/lang/String;)V
/*      */             //   237: astore #6
/*      */             //   239: aload_0
/*      */             //   240: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   243: getfield conn : Lcom/mysql/jdbc/MySQLConnection;
/*      */             //   246: iconst_4
/*      */             //   247: iconst_1
/*      */             //   248: iconst_0
/*      */             //   249: invokeinterface versionMeetsMinimum : (III)Z
/*      */             //   254: ifeq -> 265
/*      */             //   257: aload #6
/*      */             //   259: ldc 'FULL '
/*      */             //   261: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   264: pop
/*      */             //   265: aload #6
/*      */             //   267: ldc 'COLUMNS FROM '
/*      */             //   269: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   272: pop
/*      */             //   273: aload #6
/*      */             //   275: aload #4
/*      */             //   277: aload_0
/*      */             //   278: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   281: getfield quotedId : Ljava/lang/String;
/*      */             //   284: aload_0
/*      */             //   285: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   288: getfield conn : Lcom/mysql/jdbc/MySQLConnection;
/*      */             //   291: invokeinterface getPedantic : ()Z
/*      */             //   296: invokestatic quoteIdentifier : (Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;
/*      */             //   299: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   302: pop
/*      */             //   303: aload #6
/*      */             //   305: ldc ' FROM '
/*      */             //   307: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   310: pop
/*      */             //   311: aload #6
/*      */             //   313: aload_1
/*      */             //   314: aload_0
/*      */             //   315: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   318: getfield quotedId : Ljava/lang/String;
/*      */             //   321: aload_0
/*      */             //   322: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   325: getfield conn : Lcom/mysql/jdbc/MySQLConnection;
/*      */             //   328: invokeinterface getPedantic : ()Z
/*      */             //   333: invokestatic quoteIdentifier : (Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;
/*      */             //   336: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   339: pop
/*      */             //   340: aload #6
/*      */             //   342: ldc ' LIKE '
/*      */             //   344: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   347: pop
/*      */             //   348: aload #6
/*      */             //   350: aload_0
/*      */             //   351: getfield val$colPattern : Ljava/lang/String;
/*      */             //   354: ldc '''
/*      */             //   356: iconst_1
/*      */             //   357: invokestatic quoteIdentifier : (Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;
/*      */             //   360: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   363: pop
/*      */             //   364: iconst_0
/*      */             //   365: istore #7
/*      */             //   367: aconst_null
/*      */             //   368: astore #8
/*      */             //   370: aload_0
/*      */             //   371: getfield val$colPattern : Ljava/lang/String;
/*      */             //   374: ldc '%'
/*      */             //   376: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */             //   379: ifne -> 567
/*      */             //   382: iconst_1
/*      */             //   383: istore #7
/*      */             //   385: new java/lang/StringBuilder
/*      */             //   388: dup
/*      */             //   389: ldc 'SHOW '
/*      */             //   391: invokespecial <init> : (Ljava/lang/String;)V
/*      */             //   394: astore #9
/*      */             //   396: aload_0
/*      */             //   397: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   400: getfield conn : Lcom/mysql/jdbc/MySQLConnection;
/*      */             //   403: iconst_4
/*      */             //   404: iconst_1
/*      */             //   405: iconst_0
/*      */             //   406: invokeinterface versionMeetsMinimum : (III)Z
/*      */             //   411: ifeq -> 422
/*      */             //   414: aload #9
/*      */             //   416: ldc 'FULL '
/*      */             //   418: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   421: pop
/*      */             //   422: aload #9
/*      */             //   424: ldc 'COLUMNS FROM '
/*      */             //   426: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   429: pop
/*      */             //   430: aload #9
/*      */             //   432: aload #4
/*      */             //   434: aload_0
/*      */             //   435: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   438: getfield quotedId : Ljava/lang/String;
/*      */             //   441: aload_0
/*      */             //   442: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   445: getfield conn : Lcom/mysql/jdbc/MySQLConnection;
/*      */             //   448: invokeinterface getPedantic : ()Z
/*      */             //   453: invokestatic quoteIdentifier : (Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;
/*      */             //   456: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   459: pop
/*      */             //   460: aload #9
/*      */             //   462: ldc ' FROM '
/*      */             //   464: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   467: pop
/*      */             //   468: aload #9
/*      */             //   470: aload_1
/*      */             //   471: aload_0
/*      */             //   472: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   475: getfield quotedId : Ljava/lang/String;
/*      */             //   478: aload_0
/*      */             //   479: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   482: getfield conn : Lcom/mysql/jdbc/MySQLConnection;
/*      */             //   485: invokeinterface getPedantic : ()Z
/*      */             //   490: invokestatic quoteIdentifier : (Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;
/*      */             //   493: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */             //   496: pop
/*      */             //   497: aload_0
/*      */             //   498: getfield val$stmt : Ljava/sql/Statement;
/*      */             //   501: aload #9
/*      */             //   503: invokevirtual toString : ()Ljava/lang/String;
/*      */             //   506: invokeinterface executeQuery : (Ljava/lang/String;)Ljava/sql/ResultSet;
/*      */             //   511: astore #5
/*      */             //   513: new java/util/HashMap
/*      */             //   516: dup
/*      */             //   517: invokespecial <init> : ()V
/*      */             //   520: astore #8
/*      */             //   522: iconst_1
/*      */             //   523: istore #10
/*      */             //   525: aload #5
/*      */             //   527: invokeinterface next : ()Z
/*      */             //   532: ifeq -> 567
/*      */             //   535: aload #5
/*      */             //   537: ldc 'Field'
/*      */             //   539: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */             //   544: astore #11
/*      */             //   546: aload #8
/*      */             //   548: aload #11
/*      */             //   550: iload #10
/*      */             //   552: iinc #10, 1
/*      */             //   555: invokestatic valueOf : (I)Ljava/lang/Integer;
/*      */             //   558: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*      */             //   563: pop
/*      */             //   564: goto -> 525
/*      */             //   567: aload_0
/*      */             //   568: getfield val$stmt : Ljava/sql/Statement;
/*      */             //   571: aload #6
/*      */             //   573: invokevirtual toString : ()Ljava/lang/String;
/*      */             //   576: invokeinterface executeQuery : (Ljava/lang/String;)Ljava/sql/ResultSet;
/*      */             //   581: astore #5
/*      */             //   583: iconst_1
/*      */             //   584: istore #9
/*      */             //   586: aload #5
/*      */             //   588: invokeinterface next : ()Z
/*      */             //   593: ifeq -> 1403
/*      */             //   596: bipush #24
/*      */             //   598: anewarray [B
/*      */             //   601: astore #10
/*      */             //   603: aload #10
/*      */             //   605: iconst_0
/*      */             //   606: aload_0
/*      */             //   607: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   610: aload_1
/*      */             //   611: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   614: aastore
/*      */             //   615: aload #10
/*      */             //   617: iconst_1
/*      */             //   618: aconst_null
/*      */             //   619: aastore
/*      */             //   620: aload #10
/*      */             //   622: iconst_2
/*      */             //   623: aload_0
/*      */             //   624: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   627: aload #4
/*      */             //   629: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   632: aastore
/*      */             //   633: aload #10
/*      */             //   635: iconst_3
/*      */             //   636: aload #5
/*      */             //   638: ldc 'Field'
/*      */             //   640: invokeinterface getBytes : (Ljava/lang/String;)[B
/*      */             //   645: aastore
/*      */             //   646: new com/mysql/jdbc/DatabaseMetaData$TypeDescriptor
/*      */             //   649: dup
/*      */             //   650: aload_0
/*      */             //   651: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   654: aload #5
/*      */             //   656: ldc 'Type'
/*      */             //   658: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */             //   663: aload #5
/*      */             //   665: ldc 'Null'
/*      */             //   667: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */             //   672: invokespecial <init> : (Lcom/mysql/jdbc/DatabaseMetaData;Ljava/lang/String;Ljava/lang/String;)V
/*      */             //   675: astore #11
/*      */             //   677: aload #10
/*      */             //   679: iconst_4
/*      */             //   680: aload #11
/*      */             //   682: getfield dataType : S
/*      */             //   685: invokestatic toString : (S)Ljava/lang/String;
/*      */             //   688: invokevirtual getBytes : ()[B
/*      */             //   691: aastore
/*      */             //   692: aload #10
/*      */             //   694: iconst_5
/*      */             //   695: aload_0
/*      */             //   696: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   699: aload #11
/*      */             //   701: getfield typeName : Ljava/lang/String;
/*      */             //   704: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   707: aastore
/*      */             //   708: aload #11
/*      */             //   710: getfield columnSize : Ljava/lang/Integer;
/*      */             //   713: ifnonnull -> 725
/*      */             //   716: aload #10
/*      */             //   718: bipush #6
/*      */             //   720: aconst_null
/*      */             //   721: aastore
/*      */             //   722: goto -> 878
/*      */             //   725: aload #5
/*      */             //   727: ldc 'Collation'
/*      */             //   729: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */             //   734: astore #12
/*      */             //   736: iconst_1
/*      */             //   737: istore #13
/*      */             //   739: aload #12
/*      */             //   741: ifnull -> 825
/*      */             //   744: ldc 'TEXT'
/*      */             //   746: aload #11
/*      */             //   748: getfield typeName : Ljava/lang/String;
/*      */             //   751: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */             //   754: ifne -> 783
/*      */             //   757: ldc 'TINYTEXT'
/*      */             //   759: aload #11
/*      */             //   761: getfield typeName : Ljava/lang/String;
/*      */             //   764: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */             //   767: ifne -> 783
/*      */             //   770: ldc 'MEDIUMTEXT'
/*      */             //   772: aload #11
/*      */             //   774: getfield typeName : Ljava/lang/String;
/*      */             //   777: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */             //   780: ifeq -> 825
/*      */             //   783: aload #12
/*      */             //   785: ldc 'ucs2'
/*      */             //   787: invokevirtual indexOf : (Ljava/lang/String;)I
/*      */             //   790: iconst_m1
/*      */             //   791: if_icmpgt -> 805
/*      */             //   794: aload #12
/*      */             //   796: ldc 'utf16'
/*      */             //   798: invokevirtual indexOf : (Ljava/lang/String;)I
/*      */             //   801: iconst_m1
/*      */             //   802: if_icmple -> 811
/*      */             //   805: iconst_2
/*      */             //   806: istore #13
/*      */             //   808: goto -> 825
/*      */             //   811: aload #12
/*      */             //   813: ldc 'utf32'
/*      */             //   815: invokevirtual indexOf : (Ljava/lang/String;)I
/*      */             //   818: iconst_m1
/*      */             //   819: if_icmple -> 825
/*      */             //   822: iconst_4
/*      */             //   823: istore #13
/*      */             //   825: aload #10
/*      */             //   827: bipush #6
/*      */             //   829: iload #13
/*      */             //   831: iconst_1
/*      */             //   832: if_icmpne -> 853
/*      */             //   835: aload_0
/*      */             //   836: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   839: aload #11
/*      */             //   841: getfield columnSize : Ljava/lang/Integer;
/*      */             //   844: invokevirtual toString : ()Ljava/lang/String;
/*      */             //   847: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   850: goto -> 877
/*      */             //   853: aload_0
/*      */             //   854: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   857: aload #11
/*      */             //   859: getfield columnSize : Ljava/lang/Integer;
/*      */             //   862: invokevirtual intValue : ()I
/*      */             //   865: iload #13
/*      */             //   867: idiv
/*      */             //   868: invokestatic valueOf : (I)Ljava/lang/Integer;
/*      */             //   871: invokevirtual toString : ()Ljava/lang/String;
/*      */             //   874: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   877: aastore
/*      */             //   878: aload #10
/*      */             //   880: bipush #7
/*      */             //   882: aload_0
/*      */             //   883: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   886: aload #11
/*      */             //   888: getfield bufferLength : I
/*      */             //   891: invokestatic toString : (I)Ljava/lang/String;
/*      */             //   894: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   897: aastore
/*      */             //   898: aload #10
/*      */             //   900: bipush #8
/*      */             //   902: aload #11
/*      */             //   904: getfield decimalDigits : Ljava/lang/Integer;
/*      */             //   907: ifnonnull -> 914
/*      */             //   910: aconst_null
/*      */             //   911: goto -> 929
/*      */             //   914: aload_0
/*      */             //   915: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   918: aload #11
/*      */             //   920: getfield decimalDigits : Ljava/lang/Integer;
/*      */             //   923: invokevirtual toString : ()Ljava/lang/String;
/*      */             //   926: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   929: aastore
/*      */             //   930: aload #10
/*      */             //   932: bipush #9
/*      */             //   934: aload_0
/*      */             //   935: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   938: aload #11
/*      */             //   940: getfield numPrecRadix : I
/*      */             //   943: invokestatic toString : (I)Ljava/lang/String;
/*      */             //   946: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   949: aastore
/*      */             //   950: aload #10
/*      */             //   952: bipush #10
/*      */             //   954: aload_0
/*      */             //   955: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   958: aload #11
/*      */             //   960: getfield nullability : I
/*      */             //   963: invokestatic toString : (I)Ljava/lang/String;
/*      */             //   966: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   969: aastore
/*      */             //   970: aload_0
/*      */             //   971: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   974: getfield conn : Lcom/mysql/jdbc/MySQLConnection;
/*      */             //   977: iconst_4
/*      */             //   978: iconst_1
/*      */             //   979: iconst_0
/*      */             //   980: invokeinterface versionMeetsMinimum : (III)Z
/*      */             //   985: ifeq -> 1005
/*      */             //   988: aload #10
/*      */             //   990: bipush #11
/*      */             //   992: aload #5
/*      */             //   994: ldc 'Comment'
/*      */             //   996: invokeinterface getBytes : (Ljava/lang/String;)[B
/*      */             //   1001: aastore
/*      */             //   1002: goto -> 1019
/*      */             //   1005: aload #10
/*      */             //   1007: bipush #11
/*      */             //   1009: aload #5
/*      */             //   1011: ldc 'Extra'
/*      */             //   1013: invokeinterface getBytes : (Ljava/lang/String;)[B
/*      */             //   1018: aastore
/*      */             //   1019: goto -> 1032
/*      */             //   1022: astore #12
/*      */             //   1024: aload #10
/*      */             //   1026: bipush #11
/*      */             //   1028: iconst_0
/*      */             //   1029: newarray byte
/*      */             //   1031: aastore
/*      */             //   1032: aload #10
/*      */             //   1034: bipush #12
/*      */             //   1036: aload #5
/*      */             //   1038: ldc_w 'Default'
/*      */             //   1041: invokeinterface getBytes : (Ljava/lang/String;)[B
/*      */             //   1046: aastore
/*      */             //   1047: aload #10
/*      */             //   1049: bipush #13
/*      */             //   1051: iconst_1
/*      */             //   1052: newarray byte
/*      */             //   1054: dup
/*      */             //   1055: iconst_0
/*      */             //   1056: bipush #48
/*      */             //   1058: bastore
/*      */             //   1059: aastore
/*      */             //   1060: aload #10
/*      */             //   1062: bipush #14
/*      */             //   1064: iconst_1
/*      */             //   1065: newarray byte
/*      */             //   1067: dup
/*      */             //   1068: iconst_0
/*      */             //   1069: bipush #48
/*      */             //   1071: bastore
/*      */             //   1072: aastore
/*      */             //   1073: aload #11
/*      */             //   1075: getfield typeName : Ljava/lang/String;
/*      */             //   1078: ldc_w 'CHAR'
/*      */             //   1081: invokestatic indexOfIgnoreCase : (Ljava/lang/String;Ljava/lang/String;)I
/*      */             //   1084: iconst_m1
/*      */             //   1085: if_icmpne -> 1132
/*      */             //   1088: aload #11
/*      */             //   1090: getfield typeName : Ljava/lang/String;
/*      */             //   1093: ldc_w 'BLOB'
/*      */             //   1096: invokestatic indexOfIgnoreCase : (Ljava/lang/String;Ljava/lang/String;)I
/*      */             //   1099: iconst_m1
/*      */             //   1100: if_icmpne -> 1132
/*      */             //   1103: aload #11
/*      */             //   1105: getfield typeName : Ljava/lang/String;
/*      */             //   1108: ldc 'TEXT'
/*      */             //   1110: invokestatic indexOfIgnoreCase : (Ljava/lang/String;Ljava/lang/String;)I
/*      */             //   1113: iconst_m1
/*      */             //   1114: if_icmpne -> 1132
/*      */             //   1117: aload #11
/*      */             //   1119: getfield typeName : Ljava/lang/String;
/*      */             //   1122: ldc_w 'BINARY'
/*      */             //   1125: invokestatic indexOfIgnoreCase : (Ljava/lang/String;Ljava/lang/String;)I
/*      */             //   1128: iconst_m1
/*      */             //   1129: if_icmpeq -> 1145
/*      */             //   1132: aload #10
/*      */             //   1134: bipush #15
/*      */             //   1136: aload #10
/*      */             //   1138: bipush #6
/*      */             //   1140: aaload
/*      */             //   1141: aastore
/*      */             //   1142: goto -> 1151
/*      */             //   1145: aload #10
/*      */             //   1147: bipush #15
/*      */             //   1149: aconst_null
/*      */             //   1150: aastore
/*      */             //   1151: iload #7
/*      */             //   1153: ifne -> 1175
/*      */             //   1156: aload #10
/*      */             //   1158: bipush #16
/*      */             //   1160: iload #9
/*      */             //   1162: iinc #9, 1
/*      */             //   1165: invokestatic toString : (I)Ljava/lang/String;
/*      */             //   1168: invokevirtual getBytes : ()[B
/*      */             //   1171: aastore
/*      */             //   1172: goto -> 1238
/*      */             //   1175: aload #5
/*      */             //   1177: ldc 'Field'
/*      */             //   1179: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */             //   1184: astore #12
/*      */             //   1186: aload #8
/*      */             //   1188: aload #12
/*      */             //   1190: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
/*      */             //   1195: checkcast java/lang/Integer
/*      */             //   1198: astore #13
/*      */             //   1200: aload #13
/*      */             //   1202: ifnull -> 1221
/*      */             //   1205: aload #10
/*      */             //   1207: bipush #16
/*      */             //   1209: aload #13
/*      */             //   1211: invokevirtual toString : ()Ljava/lang/String;
/*      */             //   1214: invokevirtual getBytes : ()[B
/*      */             //   1217: aastore
/*      */             //   1218: goto -> 1238
/*      */             //   1221: ldc_w 'Can not find column in full column list to determine true ordinal position.'
/*      */             //   1224: ldc_w 'S1000'
/*      */             //   1227: aload_0
/*      */             //   1228: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   1231: invokevirtual getExceptionInterceptor : ()Lcom/mysql/jdbc/ExceptionInterceptor;
/*      */             //   1234: invokestatic createSQLException : (Ljava/lang/String;Ljava/lang/String;Lcom/mysql/jdbc/ExceptionInterceptor;)Ljava/sql/SQLException;
/*      */             //   1237: athrow
/*      */             //   1238: aload #10
/*      */             //   1240: bipush #17
/*      */             //   1242: aload_0
/*      */             //   1243: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   1246: aload #11
/*      */             //   1248: getfield isNullable : Ljava/lang/String;
/*      */             //   1251: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   1254: aastore
/*      */             //   1255: aload #10
/*      */             //   1257: bipush #18
/*      */             //   1259: aconst_null
/*      */             //   1260: aastore
/*      */             //   1261: aload #10
/*      */             //   1263: bipush #19
/*      */             //   1265: aconst_null
/*      */             //   1266: aastore
/*      */             //   1267: aload #10
/*      */             //   1269: bipush #20
/*      */             //   1271: aconst_null
/*      */             //   1272: aastore
/*      */             //   1273: aload #10
/*      */             //   1275: bipush #21
/*      */             //   1277: aconst_null
/*      */             //   1278: aastore
/*      */             //   1279: aload #10
/*      */             //   1281: bipush #22
/*      */             //   1283: aload_0
/*      */             //   1284: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   1287: ldc_w ''
/*      */             //   1290: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   1293: aastore
/*      */             //   1294: aload #5
/*      */             //   1296: ldc 'Extra'
/*      */             //   1298: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */             //   1303: astore #12
/*      */             //   1305: aload #12
/*      */             //   1307: ifnull -> 1376
/*      */             //   1310: aload #10
/*      */             //   1312: bipush #22
/*      */             //   1314: aload_0
/*      */             //   1315: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   1318: aload #12
/*      */             //   1320: ldc_w 'auto_increment'
/*      */             //   1323: invokestatic indexOfIgnoreCase : (Ljava/lang/String;Ljava/lang/String;)I
/*      */             //   1326: iconst_m1
/*      */             //   1327: if_icmpeq -> 1336
/*      */             //   1330: ldc_w 'YES'
/*      */             //   1333: goto -> 1339
/*      */             //   1336: ldc_w 'NO'
/*      */             //   1339: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   1342: aastore
/*      */             //   1343: aload #10
/*      */             //   1345: bipush #23
/*      */             //   1347: aload_0
/*      */             //   1348: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   1351: aload #12
/*      */             //   1353: ldc_w 'generated'
/*      */             //   1356: invokestatic indexOfIgnoreCase : (Ljava/lang/String;Ljava/lang/String;)I
/*      */             //   1359: iconst_m1
/*      */             //   1360: if_icmpeq -> 1369
/*      */             //   1363: ldc_w 'YES'
/*      */             //   1366: goto -> 1372
/*      */             //   1369: ldc_w 'NO'
/*      */             //   1372: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */             //   1375: aastore
/*      */             //   1376: aload_0
/*      */             //   1377: getfield val$rows : Ljava/util/ArrayList;
/*      */             //   1380: new com/mysql/jdbc/ByteArrayRow
/*      */             //   1383: dup
/*      */             //   1384: aload #10
/*      */             //   1386: aload_0
/*      */             //   1387: getfield this$0 : Lcom/mysql/jdbc/DatabaseMetaData;
/*      */             //   1390: invokevirtual getExceptionInterceptor : ()Lcom/mysql/jdbc/ExceptionInterceptor;
/*      */             //   1393: invokespecial <init> : ([[BLcom/mysql/jdbc/ExceptionInterceptor;)V
/*      */             //   1396: invokevirtual add : (Ljava/lang/Object;)Z
/*      */             //   1399: pop
/*      */             //   1400: goto -> 586
/*      */             //   1403: jsr -> 1417
/*      */             //   1406: goto -> 1441
/*      */             //   1409: astore #14
/*      */             //   1411: jsr -> 1417
/*      */             //   1414: aload #14
/*      */             //   1416: athrow
/*      */             //   1417: astore #15
/*      */             //   1419: aload #5
/*      */             //   1421: ifnull -> 1439
/*      */             //   1424: aload #5
/*      */             //   1426: invokeinterface close : ()V
/*      */             //   1431: goto -> 1436
/*      */             //   1434: astore #16
/*      */             //   1436: aconst_null
/*      */             //   1437: astore #5
/*      */             //   1439: ret #15
/*      */             //   1441: goto -> 205
/*      */             //   1444: return
/*      */             // Line number table:
/*      */             //   Java source line number -> byte code offset
/*      */             //   #2342	-> 0
/*      */             //   #2344	-> 8
/*      */             //   #2346	-> 15
/*      */             //   #2349	-> 17
/*      */             //   #2351	-> 36
/*      */             //   #2352	-> 45
/*      */             //   #2353	-> 55
/*      */             //   #2354	-> 62
/*      */             //   #2355	-> 65
/*      */             //   #2365	-> 68
/*      */             //   #2356	-> 71
/*      */             //   #2358	-> 85
/*      */             //   #2361	-> 91
/*      */             //   #2359	-> 94
/*      */             //   #2360	-> 96
/*      */             //   #2363	-> 101
/*      */             //   #2366	-> 105
/*      */             //   #2367	-> 108
/*      */             //   #2370	-> 110
/*      */             //   #2372	-> 131
/*      */             //   #2373	-> 140
/*      */             //   #2374	-> 150
/*      */             //   #2375	-> 157
/*      */             //   #2376	-> 160
/*      */             //   #2386	-> 163
/*      */             //   #2377	-> 166
/*      */             //   #2379	-> 180
/*      */             //   #2382	-> 186
/*      */             //   #2380	-> 189
/*      */             //   #2381	-> 191
/*      */             //   #2384	-> 196
/*      */             //   #2389	-> 200
/*      */             //   #2391	-> 225
/*      */             //   #2394	-> 228
/*      */             //   #2396	-> 239
/*      */             //   #2397	-> 257
/*      */             //   #2400	-> 265
/*      */             //   #2401	-> 273
/*      */             //   #2402	-> 303
/*      */             //   #2403	-> 311
/*      */             //   #2404	-> 340
/*      */             //   #2405	-> 348
/*      */             //   #2410	-> 364
/*      */             //   #2411	-> 367
/*      */             //   #2413	-> 370
/*      */             //   #2414	-> 382
/*      */             //   #2416	-> 385
/*      */             //   #2418	-> 396
/*      */             //   #2419	-> 414
/*      */             //   #2422	-> 422
/*      */             //   #2423	-> 430
/*      */             //   #2425	-> 460
/*      */             //   #2426	-> 468
/*      */             //   #2429	-> 497
/*      */             //   #2431	-> 513
/*      */             //   #2433	-> 522
/*      */             //   #2435	-> 525
/*      */             //   #2436	-> 535
/*      */             //   #2438	-> 546
/*      */             //   #2439	-> 564
/*      */             //   #2442	-> 567
/*      */             //   #2444	-> 583
/*      */             //   #2446	-> 586
/*      */             //   #2447	-> 596
/*      */             //   #2448	-> 603
/*      */             //   #2449	-> 615
/*      */             //   #2452	-> 620
/*      */             //   #2453	-> 633
/*      */             //   #2455	-> 646
/*      */             //   #2457	-> 677
/*      */             //   #2460	-> 692
/*      */             //   #2462	-> 708
/*      */             //   #2463	-> 716
/*      */             //   #2465	-> 725
/*      */             //   #2466	-> 736
/*      */             //   #2467	-> 739
/*      */             //   #2469	-> 783
/*      */             //   #2470	-> 805
/*      */             //   #2471	-> 811
/*      */             //   #2472	-> 822
/*      */             //   #2475	-> 825
/*      */             //   #2478	-> 878
/*      */             //   #2479	-> 898
/*      */             //   #2480	-> 930
/*      */             //   #2481	-> 950
/*      */             //   #2490	-> 970
/*      */             //   #2491	-> 988
/*      */             //   #2493	-> 1005
/*      */             //   #2497	-> 1019
/*      */             //   #2495	-> 1022
/*      */             //   #2496	-> 1024
/*      */             //   #2500	-> 1032
/*      */             //   #2502	-> 1047
/*      */             //   #2503	-> 1060
/*      */             //   #2505	-> 1073
/*      */             //   #2509	-> 1132
/*      */             //   #2511	-> 1145
/*      */             //   #2515	-> 1151
/*      */             //   #2516	-> 1156
/*      */             //   #2518	-> 1175
/*      */             //   #2519	-> 1186
/*      */             //   #2521	-> 1200
/*      */             //   #2522	-> 1205
/*      */             //   #2524	-> 1221
/*      */             //   #2529	-> 1238
/*      */             //   #2532	-> 1255
/*      */             //   #2533	-> 1261
/*      */             //   #2534	-> 1267
/*      */             //   #2535	-> 1273
/*      */             //   #2537	-> 1279
/*      */             //   #2539	-> 1294
/*      */             //   #2541	-> 1305
/*      */             //   #2542	-> 1310
/*      */             //   #2543	-> 1343
/*      */             //   #2546	-> 1376
/*      */             //   #2547	-> 1400
/*      */             //   #2548	-> 1403
/*      */             //   #2557	-> 1406
/*      */             //   #2549	-> 1409
/*      */             //   #2551	-> 1424
/*      */             //   #2553	-> 1431
/*      */             //   #2552	-> 1434
/*      */             //   #2555	-> 1436
/*      */             //   #2558	-> 1441
/*      */             //   #2559	-> 1444
/*      */             // Local variable table:
/*      */             //   start	length	slot	name	descriptor
/*      */             //   55	7	4	tableNameFromList	Ljava/lang/String;
/*      */             //   96	5	7	sqlEx	Ljava/lang/Exception;
/*      */             //   17	88	3	tables	Ljava/sql/ResultSet;
/*      */             //   150	7	4	tableNameFromList	Ljava/lang/String;
/*      */             //   191	5	10	sqlEx	Ljava/sql/SQLException;
/*      */             //   110	90	3	tables	Ljava/sql/ResultSet;
/*      */             //   546	18	11	fullOrdColName	Ljava/lang/String;
/*      */             //   396	171	9	fullColumnQueryBuf	Ljava/lang/StringBuilder;
/*      */             //   525	42	10	fullOrdinalPos	I
/*      */             //   736	142	12	collation	Ljava/lang/String;
/*      */             //   739	139	13	mbminlen	I
/*      */             //   1024	8	12	E	Ljava/lang/Exception;
/*      */             //   1186	52	12	origColName	Ljava/lang/String;
/*      */             //   1200	38	13	realOrdinal	Ljava/lang/Integer;
/*      */             //   603	797	10	rowVal	[[B
/*      */             //   677	723	11	typeDesc	Lcom/mysql/jdbc/DatabaseMetaData$TypeDescriptor;
/*      */             //   1305	95	12	extra	Ljava/lang/String;
/*      */             //   239	1164	6	queryBuf	Ljava/lang/StringBuilder;
/*      */             //   367	1036	7	fixUpOrdinalsRequired	Z
/*      */             //   370	1033	8	ordinalFixUpMap	Ljava/util/Map;
/*      */             //   586	817	9	ordPos	I
/*      */             //   1436	0	16	ex	Ljava/lang/Exception;
/*      */             //   228	1213	5	results	Ljava/sql/ResultSet;
/*      */             //   225	1216	4	tableName	Ljava/lang/String;
/*      */             //   205	1239	3	i$	Ljava/util/Iterator;
/*      */             //   0	1445	0	this	Lcom/mysql/jdbc/DatabaseMetaData$2;
/*      */             //   0	1445	1	catalogStr	Ljava/lang/String;
/*      */             //   8	1437	2	tableNameList	Ljava/util/ArrayList;
/*      */             // Local variable type table:
/*      */             //   start	length	slot	name	signature
/*      */             //   370	1033	8	ordinalFixUpMap	Ljava/util/Map<Ljava/lang/String;Ljava/lang/Integer;>;
/*      */             //   8	1437	2	tableNameList	Ljava/util/ArrayList<Ljava/lang/String;>;
/*      */             // Exception table:
/*      */             //   from	to	target	type
/*      */             //   17	68	71	finally
/*      */             //   71	76	71	finally
/*      */             //   85	91	94	java/lang/Exception
/*      */             //   110	163	166	finally
/*      */             //   166	171	166	finally
/*      */             //   180	186	189	java/sql/SQLException
/*      */             //   228	1406	1409	finally
/*      */             //   970	1019	1022	java/lang/Exception
/*      */             //   1409	1414	1409	finally
/* 2338 */             //   1424	1431	1434	java/lang/Exception } }).doForAll();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     finally {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2562 */       if (stmt != null) {
/* 2563 */         stmt.close();
/*      */       }
/*      */     } 
/*      */     
/* 2567 */     ResultSet results = buildResultSet(fields, rows);
/*      */     
/* 2569 */     return results;
/*      */   }
/*      */   
/*      */   protected Field[] createColumnsFields() {
/* 2573 */     Field[] fields = new Field[24];
/* 2574 */     fields[0] = new Field("", "TABLE_CAT", 1, 255);
/* 2575 */     fields[1] = new Field("", "TABLE_SCHEM", 1, 0);
/* 2576 */     fields[2] = new Field("", "TABLE_NAME", 1, 255);
/* 2577 */     fields[3] = new Field("", "COLUMN_NAME", 1, 32);
/* 2578 */     fields[4] = new Field("", "DATA_TYPE", 4, 5);
/* 2579 */     fields[5] = new Field("", "TYPE_NAME", 1, 16);
/* 2580 */     fields[6] = new Field("", "COLUMN_SIZE", 4, Integer.toString(2147483647).length());
/* 2581 */     fields[7] = new Field("", "BUFFER_LENGTH", 4, 10);
/* 2582 */     fields[8] = new Field("", "DECIMAL_DIGITS", 4, 10);
/* 2583 */     fields[9] = new Field("", "NUM_PREC_RADIX", 4, 10);
/* 2584 */     fields[10] = new Field("", "NULLABLE", 4, 10);
/* 2585 */     fields[11] = new Field("", "REMARKS", 1, 0);
/* 2586 */     fields[12] = new Field("", "COLUMN_DEF", 1, 0);
/* 2587 */     fields[13] = new Field("", "SQL_DATA_TYPE", 4, 10);
/* 2588 */     fields[14] = new Field("", "SQL_DATETIME_SUB", 4, 10);
/* 2589 */     fields[15] = new Field("", "CHAR_OCTET_LENGTH", 4, Integer.toString(2147483647).length());
/* 2590 */     fields[16] = new Field("", "ORDINAL_POSITION", 4, 10);
/* 2591 */     fields[17] = new Field("", "IS_NULLABLE", 1, 3);
/* 2592 */     fields[18] = new Field("", "SCOPE_CATALOG", 1, 255);
/* 2593 */     fields[19] = new Field("", "SCOPE_SCHEMA", 1, 255);
/* 2594 */     fields[20] = new Field("", "SCOPE_TABLE", 1, 255);
/* 2595 */     fields[21] = new Field("", "SOURCE_DATA_TYPE", 5, 10);
/* 2596 */     fields[22] = new Field("", "IS_AUTOINCREMENT", 1, 3);
/* 2597 */     fields[23] = new Field("", "IS_GENERATEDCOLUMN", 1, 3);
/* 2598 */     return fields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Connection getConnection() throws SQLException {
/* 2609 */     return this.conn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getCrossReference(final String primaryCatalog, final String primarySchema, final String primaryTable, final String foreignCatalog, final String foreignSchema, final String foreignTable) throws SQLException {
/* 2668 */     if (primaryTable == null) {
/* 2669 */       throw SQLError.createSQLException("Table not specified.", "S1009", getExceptionInterceptor());
/*      */     }
/*      */     
/* 2672 */     Field[] fields = createFkMetadataFields();
/*      */     
/* 2674 */     final ArrayList<ResultSetRow> tuples = new ArrayList<ResultSetRow>();
/*      */     
/* 2676 */     if (this.conn.versionMeetsMinimum(3, 23, 0)) {
/*      */       
/* 2678 */       final Statement stmt = this.conn.getMetadataSafeStatement();
/*      */ 
/*      */       
/*      */       try {
/* 2682 */         (new IterateBlock<String>(getCatalogIterator(foreignCatalog))
/*      */           {
/*      */             void forEach(String catalogStr) throws SQLException
/*      */             {
/* 2686 */               ResultSet fkresults = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               try {
/* 2693 */                 if (DatabaseMetaData.this.conn.versionMeetsMinimum(3, 23, 50)) {
/* 2694 */                   fkresults = DatabaseMetaData.this.extractForeignKeyFromCreateTable(catalogStr, null);
/*      */                 } else {
/* 2696 */                   StringBuilder queryBuf = new StringBuilder("SHOW TABLE STATUS FROM ");
/* 2697 */                   queryBuf.append(StringUtils.quoteIdentifier(catalogStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()));
/*      */ 
/*      */                   
/* 2700 */                   fkresults = stmt.executeQuery(queryBuf.toString());
/*      */                 } 
/*      */                 
/* 2703 */                 String foreignTableWithCase = DatabaseMetaData.this.getTableNameWithCase(foreignTable);
/* 2704 */                 String primaryTableWithCase = DatabaseMetaData.this.getTableNameWithCase(primaryTable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 2712 */                 while (fkresults.next()) {
/* 2713 */                   String tableType = fkresults.getString("Type");
/*      */                   
/* 2715 */                   if (tableType != null && (tableType.equalsIgnoreCase("innodb") || tableType.equalsIgnoreCase("SUPPORTS_FK"))) {
/* 2716 */                     String comment = fkresults.getString("Comment").trim();
/*      */                     
/* 2718 */                     if (comment != null) {
/* 2719 */                       StringTokenizer commentTokens = new StringTokenizer(comment, ";", false);
/*      */                       
/* 2721 */                       if (commentTokens.hasMoreTokens()) {
/* 2722 */                         String str = commentTokens.nextToken();
/*      */                       }
/*      */ 
/*      */ 
/*      */                       
/* 2727 */                       while (commentTokens.hasMoreTokens()) {
/* 2728 */                         String keys = commentTokens.nextToken();
/* 2729 */                         DatabaseMetaData.LocalAndReferencedColumns parsedInfo = DatabaseMetaData.this.parseTableStatusIntoLocalAndReferencedColumns(keys);
/*      */                         
/* 2731 */                         int keySeq = 0;
/*      */                         
/* 2733 */                         Iterator<String> referencingColumns = parsedInfo.localColumnsList.iterator();
/* 2734 */                         Iterator<String> referencedColumns = parsedInfo.referencedColumnsList.iterator();
/*      */                         
/* 2736 */                         while (referencingColumns.hasNext()) {
/* 2737 */                           String referencingColumn = StringUtils.unQuoteIdentifier(referencingColumns.next(), DatabaseMetaData.this.quotedId);
/*      */ 
/*      */ 
/*      */                           
/* 2741 */                           byte[][] tuple = new byte[14][];
/* 2742 */                           tuple[4] = (foreignCatalog == null) ? null : DatabaseMetaData.this.s2b(foreignCatalog);
/* 2743 */                           tuple[5] = (foreignSchema == null) ? null : DatabaseMetaData.this.s2b(foreignSchema);
/* 2744 */                           String dummy = fkresults.getString("Name");
/*      */                           
/* 2746 */                           if (dummy.compareTo(foreignTableWithCase) != 0) {
/*      */                             continue;
/*      */                           }
/*      */                           
/* 2750 */                           tuple[6] = DatabaseMetaData.this.s2b(dummy);
/*      */                           
/* 2752 */                           tuple[7] = DatabaseMetaData.this.s2b(referencingColumn);
/* 2753 */                           tuple[0] = (primaryCatalog == null) ? null : DatabaseMetaData.this.s2b(primaryCatalog);
/* 2754 */                           tuple[1] = (primarySchema == null) ? null : DatabaseMetaData.this.s2b(primarySchema);
/*      */ 
/*      */                           
/* 2757 */                           if (parsedInfo.referencedTable.compareTo(primaryTableWithCase) != 0) {
/*      */                             continue;
/*      */                           }
/*      */                           
/* 2761 */                           tuple[2] = DatabaseMetaData.this.s2b(parsedInfo.referencedTable);
/* 2762 */                           tuple[3] = DatabaseMetaData.this.s2b(StringUtils.unQuoteIdentifier(referencedColumns.next(), DatabaseMetaData.this.quotedId));
/* 2763 */                           tuple[8] = Integer.toString(keySeq).getBytes();
/*      */                           
/* 2765 */                           int[] actions = DatabaseMetaData.this.getForeignKeyActions(keys);
/*      */                           
/* 2767 */                           tuple[9] = Integer.toString(actions[1]).getBytes();
/* 2768 */                           tuple[10] = Integer.toString(actions[0]).getBytes();
/* 2769 */                           tuple[11] = null;
/* 2770 */                           tuple[12] = null;
/* 2771 */                           tuple[13] = Integer.toString(7).getBytes();
/* 2772 */                           tuples.add(new ByteArrayRow(tuple, DatabaseMetaData.this.getExceptionInterceptor()));
/* 2773 */                           keySeq++;
/*      */                         } 
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } finally {
/*      */                 
/* 2781 */                 if (fkresults != null) {
/*      */                   try {
/* 2783 */                     fkresults.close();
/* 2784 */                   } catch (Exception sqlEx) {
/* 2785 */                     AssertionFailedException.shouldNotHappen(sqlEx);
/*      */                   } 
/*      */                   
/* 2788 */                   fkresults = null;
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           }).doForAll();
/*      */       } finally {
/* 2794 */         if (stmt != null) {
/* 2795 */           stmt.close();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2800 */     ResultSet results = buildResultSet(fields, tuples);
/*      */     
/* 2802 */     return results;
/*      */   }
/*      */   
/*      */   protected Field[] createFkMetadataFields() {
/* 2806 */     Field[] fields = new Field[14];
/* 2807 */     fields[0] = new Field("", "PKTABLE_CAT", 1, 255);
/* 2808 */     fields[1] = new Field("", "PKTABLE_SCHEM", 1, 0);
/* 2809 */     fields[2] = new Field("", "PKTABLE_NAME", 1, 255);
/* 2810 */     fields[3] = new Field("", "PKCOLUMN_NAME", 1, 32);
/* 2811 */     fields[4] = new Field("", "FKTABLE_CAT", 1, 255);
/* 2812 */     fields[5] = new Field("", "FKTABLE_SCHEM", 1, 0);
/* 2813 */     fields[6] = new Field("", "FKTABLE_NAME", 1, 255);
/* 2814 */     fields[7] = new Field("", "FKCOLUMN_NAME", 1, 32);
/* 2815 */     fields[8] = new Field("", "KEY_SEQ", 5, 2);
/* 2816 */     fields[9] = new Field("", "UPDATE_RULE", 5, 2);
/* 2817 */     fields[10] = new Field("", "DELETE_RULE", 5, 2);
/* 2818 */     fields[11] = new Field("", "FK_NAME", 1, 0);
/* 2819 */     fields[12] = new Field("", "PK_NAME", 1, 0);
/* 2820 */     fields[13] = new Field("", "DEFERRABILITY", 5, 2);
/* 2821 */     return fields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDatabaseMajorVersion() throws SQLException {
/* 2828 */     return this.conn.getServerMajorVersion();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDatabaseMinorVersion() throws SQLException {
/* 2835 */     return this.conn.getServerMinorVersion();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDatabaseProductName() throws SQLException {
/* 2845 */     return "MySQL";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDatabaseProductVersion() throws SQLException {
/* 2855 */     return this.conn.getServerVersion();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDefaultTransactionIsolation() throws SQLException {
/* 2868 */     if (this.conn.supportsIsolationLevel()) {
/* 2869 */       return 2;
/*      */     }
/*      */     
/* 2872 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDriverMajorVersion() {
/* 2881 */     return NonRegisteringDriver.getMajorVersionInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDriverMinorVersion() {
/* 2890 */     return NonRegisteringDriver.getMinorVersionInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDriverName() throws SQLException {
/* 2900 */     return "MySQL Connector Java";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDriverVersion() throws SQLException {
/* 2910 */     return "mysql-connector-java-5.1.49 ( Revision: ad86f36e100e104cd926c6b81c8cab9565750116 )";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getExportedKeys(String catalog, String schema, final String table) throws SQLException {
/* 2960 */     if (table == null) {
/* 2961 */       throw SQLError.createSQLException("Table not specified.", "S1009", getExceptionInterceptor());
/*      */     }
/*      */     
/* 2964 */     Field[] fields = createFkMetadataFields();
/*      */     
/* 2966 */     final ArrayList<ResultSetRow> rows = new ArrayList<ResultSetRow>();
/*      */     
/* 2968 */     if (this.conn.versionMeetsMinimum(3, 23, 0)) {
/*      */       
/* 2970 */       final Statement stmt = this.conn.getMetadataSafeStatement();
/*      */ 
/*      */       
/*      */       try {
/* 2974 */         (new IterateBlock<String>(getCatalogIterator(catalog))
/*      */           {
/*      */             void forEach(String catalogStr) throws SQLException {
/* 2977 */               ResultSet fkresults = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               try {
/* 2984 */                 if (DatabaseMetaData.this.conn.versionMeetsMinimum(3, 23, 50)) {
/*      */ 
/*      */                   
/* 2987 */                   fkresults = DatabaseMetaData.this.extractForeignKeyFromCreateTable(catalogStr, null);
/*      */                 } else {
/* 2989 */                   StringBuilder queryBuf = new StringBuilder("SHOW TABLE STATUS FROM ");
/* 2990 */                   queryBuf.append(StringUtils.quoteIdentifier(catalogStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()));
/*      */ 
/*      */                   
/* 2993 */                   fkresults = stmt.executeQuery(queryBuf.toString());
/*      */                 } 
/*      */ 
/*      */                 
/* 2997 */                 String tableNameWithCase = DatabaseMetaData.this.getTableNameWithCase(table);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 3003 */                 while (fkresults.next()) {
/* 3004 */                   String tableType = fkresults.getString("Type");
/*      */                   
/* 3006 */                   if (tableType != null && (tableType.equalsIgnoreCase("innodb") || tableType.equalsIgnoreCase("SUPPORTS_FK"))) {
/* 3007 */                     String comment = fkresults.getString("Comment").trim();
/*      */                     
/* 3009 */                     if (comment != null) {
/* 3010 */                       StringTokenizer commentTokens = new StringTokenizer(comment, ";", false);
/*      */                       
/* 3012 */                       if (commentTokens.hasMoreTokens()) {
/* 3013 */                         commentTokens.nextToken();
/*      */ 
/*      */ 
/*      */                         
/* 3017 */                         while (commentTokens.hasMoreTokens()) {
/* 3018 */                           String keys = commentTokens.nextToken();
/* 3019 */                           DatabaseMetaData.this.getExportKeyResults(catalogStr, tableNameWithCase, keys, rows, fkresults.getString("Name"));
/*      */                         } 
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } finally {
/*      */                 
/* 3027 */                 if (fkresults != null) {
/*      */                   try {
/* 3029 */                     fkresults.close();
/* 3030 */                   } catch (SQLException sqlEx) {
/* 3031 */                     AssertionFailedException.shouldNotHappen(sqlEx);
/*      */                   } 
/*      */                   
/* 3034 */                   fkresults = null;
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           }).doForAll();
/*      */       } finally {
/* 3040 */         if (stmt != null) {
/* 3041 */           stmt.close();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 3046 */     ResultSet results = buildResultSet(fields, rows);
/*      */     
/* 3048 */     return results;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void getExportKeyResults(String catalog, String exportingTable, String keysComment, List<ResultSetRow> tuples, String fkTableName) throws SQLException {
/* 3071 */     getResultsImpl(catalog, exportingTable, keysComment, tuples, fkTableName, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getExtraNameCharacters() throws SQLException {
/* 3082 */     return "#@";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int[] getForeignKeyActions(String commentString) {
/* 3095 */     int[] actions = { 3, 3 };
/*      */     
/* 3097 */     int lastParenIndex = commentString.lastIndexOf(")");
/*      */     
/* 3099 */     if (lastParenIndex != commentString.length() - 1) {
/* 3100 */       String cascadeOptions = commentString.substring(lastParenIndex + 1).trim().toUpperCase(Locale.ENGLISH);
/*      */       
/* 3102 */       actions[0] = getCascadeDeleteOption(cascadeOptions);
/* 3103 */       actions[1] = getCascadeUpdateOption(cascadeOptions);
/*      */     } 
/*      */     
/* 3106 */     return actions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getIdentifierQuoteString() throws SQLException {
/* 3118 */     if (this.conn.supportsQuotedIdentifiers()) {
/* 3119 */       return this.conn.useAnsiQuotedIdentifiers() ? "\"" : "`";
/*      */     }
/*      */ 
/*      */     
/* 3123 */     return " ";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getImportedKeys(String catalog, String schema, final String table) throws SQLException {
/* 3173 */     if (table == null) {
/* 3174 */       throw SQLError.createSQLException("Table not specified.", "S1009", getExceptionInterceptor());
/*      */     }
/*      */     
/* 3177 */     Field[] fields = createFkMetadataFields();
/*      */     
/* 3179 */     final ArrayList<ResultSetRow> rows = new ArrayList<ResultSetRow>();
/*      */     
/* 3181 */     if (this.conn.versionMeetsMinimum(3, 23, 0)) {
/*      */       
/* 3183 */       final Statement stmt = this.conn.getMetadataSafeStatement();
/*      */ 
/*      */       
/*      */       try {
/* 3187 */         (new IterateBlock<String>(getCatalogIterator(catalog))
/*      */           {
/*      */             void forEach(String catalogStr) throws SQLException {
/* 3190 */               ResultSet fkresults = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               try {
/* 3197 */                 if (DatabaseMetaData.this.conn.versionMeetsMinimum(3, 23, 50)) {
/*      */ 
/*      */                   
/* 3200 */                   fkresults = DatabaseMetaData.this.extractForeignKeyFromCreateTable(catalogStr, table);
/*      */                 } else {
/* 3202 */                   StringBuilder queryBuf = new StringBuilder("SHOW TABLE STATUS ");
/* 3203 */                   queryBuf.append(" FROM ");
/* 3204 */                   queryBuf.append(StringUtils.quoteIdentifier(catalogStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()));
/*      */                   
/* 3206 */                   queryBuf.append(" LIKE ");
/* 3207 */                   queryBuf.append(StringUtils.quoteIdentifier(table, "'", true));
/*      */                   
/* 3209 */                   fkresults = stmt.executeQuery(queryBuf.toString());
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 3216 */                 while (fkresults.next()) {
/* 3217 */                   String tableType = fkresults.getString("Type");
/*      */                   
/* 3219 */                   if (tableType != null && (tableType.equalsIgnoreCase("innodb") || tableType.equalsIgnoreCase("SUPPORTS_FK"))) {
/* 3220 */                     String comment = fkresults.getString("Comment").trim();
/*      */                     
/* 3222 */                     if (comment != null) {
/* 3223 */                       StringTokenizer commentTokens = new StringTokenizer(comment, ";", false);
/*      */                       
/* 3225 */                       if (commentTokens.hasMoreTokens()) {
/* 3226 */                         commentTokens.nextToken();
/*      */                         
/* 3228 */                         while (commentTokens.hasMoreTokens()) {
/* 3229 */                           String keys = commentTokens.nextToken();
/* 3230 */                           DatabaseMetaData.this.getImportKeyResults(catalogStr, table, keys, rows);
/*      */                         } 
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } finally {
/* 3237 */                 if (fkresults != null) {
/*      */                   try {
/* 3239 */                     fkresults.close();
/* 3240 */                   } catch (SQLException sqlEx) {
/* 3241 */                     AssertionFailedException.shouldNotHappen(sqlEx);
/*      */                   } 
/*      */                   
/* 3244 */                   fkresults = null;
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           }).doForAll();
/*      */       } finally {
/* 3250 */         if (stmt != null) {
/* 3251 */           stmt.close();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 3256 */     ResultSet results = buildResultSet(fields, rows);
/*      */     
/* 3258 */     return results;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void getImportKeyResults(String catalog, String importingTable, String keysComment, List<ResultSetRow> tuples) throws SQLException {
/* 3279 */     getResultsImpl(catalog, importingTable, keysComment, tuples, null, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getIndexInfo(String catalog, String schema, final String table, final boolean unique, boolean approximate) throws SQLException {
/* 3334 */     Field[] fields = createIndexInfoFields();
/*      */     
/* 3336 */     final SortedMap<IndexMetaDataKey, ResultSetRow> sortedRows = new TreeMap<IndexMetaDataKey, ResultSetRow>();
/* 3337 */     ArrayList<ResultSetRow> rows = new ArrayList<ResultSetRow>();
/* 3338 */     final Statement stmt = this.conn.getMetadataSafeStatement();
/*      */ 
/*      */     
/*      */     try {
/* 3342 */       (new IterateBlock<String>(getCatalogIterator(catalog))
/*      */         {
/*      */           void forEach(String catalogStr) throws SQLException
/*      */           {
/* 3346 */             ResultSet results = null;
/*      */             
/*      */             try {
/* 3349 */               StringBuilder queryBuf = new StringBuilder("SHOW INDEX FROM ");
/* 3350 */               queryBuf.append(StringUtils.quoteIdentifier(table, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()));
/* 3351 */               queryBuf.append(" FROM ");
/* 3352 */               queryBuf.append(StringUtils.quoteIdentifier(catalogStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()));
/*      */               
/*      */               try {
/* 3355 */                 results = stmt.executeQuery(queryBuf.toString());
/* 3356 */               } catch (SQLException sqlEx) {
/* 3357 */                 int errorCode = sqlEx.getErrorCode();
/*      */ 
/*      */                 
/* 3360 */                 if (!"42S02".equals(sqlEx.getSQLState()))
/*      */                 {
/* 3362 */                   if (errorCode != 1146) {
/* 3363 */                     throw sqlEx;
/*      */                   }
/*      */                 }
/*      */               } 
/*      */               
/* 3368 */               while (results != null && results.next()) {
/* 3369 */                 byte[][] row = new byte[14][];
/* 3370 */                 row[0] = (catalogStr == null) ? new byte[0] : DatabaseMetaData.this.s2b(catalogStr);
/* 3371 */                 row[1] = null;
/* 3372 */                 row[2] = results.getBytes("Table");
/*      */                 
/* 3374 */                 boolean indexIsUnique = (results.getInt("Non_unique") == 0);
/*      */                 
/* 3376 */                 row[3] = !indexIsUnique ? DatabaseMetaData.this.s2b("true") : DatabaseMetaData.this.s2b("false");
/* 3377 */                 row[4] = new byte[0];
/* 3378 */                 row[5] = results.getBytes("Key_name");
/* 3379 */                 short indexType = 3;
/* 3380 */                 row[6] = Integer.toString(indexType).getBytes();
/* 3381 */                 row[7] = results.getBytes("Seq_in_index");
/* 3382 */                 row[8] = results.getBytes("Column_name");
/* 3383 */                 row[9] = results.getBytes("Collation");
/*      */                 
/* 3385 */                 long cardinality = results.getLong("Cardinality");
/*      */ 
/*      */                 
/* 3388 */                 if (!Util.isJdbc42() && cardinality > 2147483647L) {
/* 3389 */                   cardinality = 2147483647L;
/*      */                 }
/*      */                 
/* 3392 */                 row[10] = DatabaseMetaData.this.s2b(String.valueOf(cardinality));
/* 3393 */                 row[11] = DatabaseMetaData.this.s2b("0");
/* 3394 */                 row[12] = null;
/*      */                 
/* 3396 */                 DatabaseMetaData.IndexMetaDataKey indexInfoKey = new DatabaseMetaData.IndexMetaDataKey(!indexIsUnique, indexType, results.getString("Key_name").toLowerCase(), results.getShort("Seq_in_index"));
/*      */ 
/*      */                 
/* 3399 */                 if (unique) {
/* 3400 */                   if (indexIsUnique) {
/* 3401 */                     sortedRows.put(indexInfoKey, new ByteArrayRow(row, DatabaseMetaData.this.getExceptionInterceptor()));
/*      */                   }
/*      */                   continue;
/*      */                 } 
/* 3405 */                 sortedRows.put(indexInfoKey, new ByteArrayRow(row, DatabaseMetaData.this.getExceptionInterceptor()));
/*      */               } 
/*      */             } finally {
/*      */               
/* 3409 */               if (results != null) {
/*      */                 try {
/* 3411 */                   results.close();
/* 3412 */                 } catch (Exception ex) {}
/*      */ 
/*      */                 
/* 3415 */                 results = null;
/*      */               } 
/*      */             } 
/*      */           }
/*      */         }).doForAll();
/*      */       
/* 3421 */       Iterator<ResultSetRow> sortedRowsIterator = sortedRows.values().iterator();
/* 3422 */       while (sortedRowsIterator.hasNext()) {
/* 3423 */         rows.add(sortedRowsIterator.next());
/*      */       }
/*      */       
/* 3426 */       ResultSet indexInfo = buildResultSet(fields, rows);
/*      */       
/* 3428 */       return indexInfo;
/*      */     } finally {
/* 3430 */       if (stmt != null) {
/* 3431 */         stmt.close();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Field[] createIndexInfoFields() {
/* 3437 */     Field[] fields = new Field[13];
/* 3438 */     fields[0] = new Field("", "TABLE_CAT", 1, 255);
/* 3439 */     fields[1] = new Field("", "TABLE_SCHEM", 1, 0);
/* 3440 */     fields[2] = new Field("", "TABLE_NAME", 1, 255);
/* 3441 */     fields[3] = new Field("", "NON_UNIQUE", 16, 4);
/* 3442 */     fields[4] = new Field("", "INDEX_QUALIFIER", 1, 1);
/* 3443 */     fields[5] = new Field("", "INDEX_NAME", 1, 32);
/* 3444 */     fields[6] = new Field("", "TYPE", 5, 32);
/* 3445 */     fields[7] = new Field("", "ORDINAL_POSITION", 5, 5);
/* 3446 */     fields[8] = new Field("", "COLUMN_NAME", 1, 32);
/* 3447 */     fields[9] = new Field("", "ASC_OR_DESC", 1, 1);
/* 3448 */     if (Util.isJdbc42()) {
/* 3449 */       fields[10] = new Field("", "CARDINALITY", -5, 20);
/* 3450 */       fields[11] = new Field("", "PAGES", -5, 20);
/*      */     } else {
/* 3452 */       fields[10] = new Field("", "CARDINALITY", 4, 20);
/* 3453 */       fields[11] = new Field("", "PAGES", 4, 10);
/*      */     } 
/* 3455 */     fields[12] = new Field("", "FILTER_CONDITION", 1, 32);
/* 3456 */     return fields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getJDBCMajorVersion() throws SQLException {
/* 3463 */     return 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getJDBCMinorVersion() throws SQLException {
/* 3470 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxBinaryLiteralLength() throws SQLException {
/* 3480 */     return 16777208;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxCatalogNameLength() throws SQLException {
/* 3490 */     return 32;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxCharLiteralLength() throws SQLException {
/* 3500 */     return 16777208;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxColumnNameLength() throws SQLException {
/* 3510 */     return 64;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxColumnsInGroupBy() throws SQLException {
/* 3520 */     return 64;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxColumnsInIndex() throws SQLException {
/* 3530 */     return 16;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxColumnsInOrderBy() throws SQLException {
/* 3540 */     return 64;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxColumnsInSelect() throws SQLException {
/* 3550 */     return 256;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxColumnsInTable() throws SQLException {
/* 3560 */     return 512;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxConnections() throws SQLException {
/* 3570 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxCursorNameLength() throws SQLException {
/* 3580 */     return 64;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxIndexLength() throws SQLException {
/* 3590 */     return 256;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxProcedureNameLength() throws SQLException {
/* 3600 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxRowSize() throws SQLException {
/* 3610 */     return 2147483639;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxSchemaNameLength() throws SQLException {
/* 3620 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxStatementLength() throws SQLException {
/* 3630 */     return MysqlIO.getMaxBuf() - 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxStatements() throws SQLException {
/* 3640 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxTableNameLength() throws SQLException {
/* 3650 */     return 64;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxTablesInSelect() throws SQLException {
/* 3660 */     return 256;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxUserNameLength() throws SQLException {
/* 3670 */     return 16;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNumericFunctions() throws SQLException {
/* 3680 */     return "ABS,ACOS,ASIN,ATAN,ATAN2,BIT_COUNT,CEILING,COS,COT,DEGREES,EXP,FLOOR,LOG,LOG10,MAX,MIN,MOD,PI,POW,POWER,RADIANS,RAND,ROUND,SIN,SQRT,TAN,TRUNCATE";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getPrimaryKeys(String catalog, String schema, final String table) throws SQLException {
/* 3709 */     Field[] fields = new Field[6];
/* 3710 */     fields[0] = new Field("", "TABLE_CAT", 1, 255);
/* 3711 */     fields[1] = new Field("", "TABLE_SCHEM", 1, 0);
/* 3712 */     fields[2] = new Field("", "TABLE_NAME", 1, 255);
/* 3713 */     fields[3] = new Field("", "COLUMN_NAME", 1, 32);
/* 3714 */     fields[4] = new Field("", "KEY_SEQ", 5, 5);
/* 3715 */     fields[5] = new Field("", "PK_NAME", 1, 32);
/*      */     
/* 3717 */     if (table == null) {
/* 3718 */       throw SQLError.createSQLException("Table not specified.", "S1009", getExceptionInterceptor());
/*      */     }
/*      */     
/* 3721 */     final ArrayList<ResultSetRow> rows = new ArrayList<ResultSetRow>();
/* 3722 */     final Statement stmt = this.conn.getMetadataSafeStatement();
/*      */ 
/*      */     
/*      */     try {
/* 3726 */       (new IterateBlock<String>(getCatalogIterator(catalog))
/*      */         {
/*      */           void forEach(String catalogStr) throws SQLException {
/* 3729 */             ResultSet rs = null;
/*      */ 
/*      */             
/*      */             try {
/* 3733 */               StringBuilder queryBuf = new StringBuilder("SHOW KEYS FROM ");
/* 3734 */               queryBuf.append(StringUtils.quoteIdentifier(table, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()));
/* 3735 */               queryBuf.append(" FROM ");
/* 3736 */               queryBuf.append(StringUtils.quoteIdentifier(catalogStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()));
/*      */               
/* 3738 */               rs = stmt.executeQuery(queryBuf.toString());
/*      */               
/* 3740 */               TreeMap<String, byte[][]> sortMap = (TreeMap)new TreeMap<String, byte>();
/*      */               
/* 3742 */               while (rs.next()) {
/* 3743 */                 String keyType = rs.getString("Key_name");
/*      */                 
/* 3745 */                 if (keyType != null && (
/* 3746 */                   keyType.equalsIgnoreCase("PRIMARY") || keyType.equalsIgnoreCase("PRI"))) {
/* 3747 */                   byte[][] tuple = new byte[6][];
/* 3748 */                   tuple[0] = (catalogStr == null) ? new byte[0] : DatabaseMetaData.this.s2b(catalogStr);
/* 3749 */                   tuple[1] = null;
/* 3750 */                   tuple[2] = DatabaseMetaData.this.s2b(table);
/*      */                   
/* 3752 */                   String columnName = rs.getString("Column_name");
/* 3753 */                   tuple[3] = DatabaseMetaData.this.s2b(columnName);
/* 3754 */                   tuple[4] = DatabaseMetaData.this.s2b(rs.getString("Seq_in_index"));
/* 3755 */                   tuple[5] = DatabaseMetaData.this.s2b(keyType);
/* 3756 */                   sortMap.put(columnName, tuple);
/*      */                 } 
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/* 3762 */               Iterator<byte[][]> sortedIterator = (Iterator)sortMap.values().iterator();
/*      */               
/* 3764 */               while (sortedIterator.hasNext()) {
/* 3765 */                 rows.add(new ByteArrayRow(sortedIterator.next(), DatabaseMetaData.this.getExceptionInterceptor()));
/*      */               }
/*      */             } finally {
/*      */               
/* 3769 */               if (rs != null) {
/*      */                 try {
/* 3771 */                   rs.close();
/* 3772 */                 } catch (Exception ex) {}
/*      */ 
/*      */                 
/* 3775 */                 rs = null;
/*      */               } 
/*      */             } 
/*      */           }
/*      */         }).doForAll();
/*      */     } finally {
/* 3781 */       if (stmt != null) {
/* 3782 */         stmt.close();
/*      */       }
/*      */     } 
/*      */     
/* 3786 */     ResultSet results = buildResultSet(fields, rows);
/*      */     
/* 3788 */     return results;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
/* 3865 */     Field[] fields = createProcedureColumnsFields();
/*      */     
/* 3867 */     return getProcedureOrFunctionColumns(fields, catalog, schemaPattern, procedureNamePattern, columnNamePattern, true, true);
/*      */   }
/*      */   
/*      */   protected Field[] createProcedureColumnsFields() {
/* 3871 */     Field[] fields = new Field[20];
/*      */     
/* 3873 */     fields[0] = new Field("", "PROCEDURE_CAT", 1, 512);
/* 3874 */     fields[1] = new Field("", "PROCEDURE_SCHEM", 1, 512);
/* 3875 */     fields[2] = new Field("", "PROCEDURE_NAME", 1, 512);
/* 3876 */     fields[3] = new Field("", "COLUMN_NAME", 1, 512);
/* 3877 */     fields[4] = new Field("", "COLUMN_TYPE", 1, 64);
/* 3878 */     fields[5] = new Field("", "DATA_TYPE", 5, 6);
/* 3879 */     fields[6] = new Field("", "TYPE_NAME", 1, 64);
/* 3880 */     fields[7] = new Field("", "PRECISION", 4, 12);
/* 3881 */     fields[8] = new Field("", "LENGTH", 4, 12);
/* 3882 */     fields[9] = new Field("", "SCALE", 5, 12);
/* 3883 */     fields[10] = new Field("", "RADIX", 5, 6);
/* 3884 */     fields[11] = new Field("", "NULLABLE", 5, 6);
/* 3885 */     fields[12] = new Field("", "REMARKS", 1, 512);
/* 3886 */     fields[13] = new Field("", "COLUMN_DEF", 1, 512);
/* 3887 */     fields[14] = new Field("", "SQL_DATA_TYPE", 4, 12);
/* 3888 */     fields[15] = new Field("", "SQL_DATETIME_SUB", 4, 12);
/* 3889 */     fields[16] = new Field("", "CHAR_OCTET_LENGTH", 4, 12);
/* 3890 */     fields[17] = new Field("", "ORDINAL_POSITION", 4, 12);
/* 3891 */     fields[18] = new Field("", "IS_NULLABLE", 1, 512);
/* 3892 */     fields[19] = new Field("", "SPECIFIC_NAME", 1, 512);
/* 3893 */     return fields;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected ResultSet getProcedureOrFunctionColumns(Field[] fields, String catalog, String schemaPattern, String procedureOrFunctionNamePattern, String columnNamePattern, boolean returnProcedures, boolean returnFunctions) throws SQLException {
/* 3899 */     List<ComparableWrapper<String, ProcedureType>> procsOrFuncsToExtractList = new ArrayList<ComparableWrapper<String, ProcedureType>>();
/*      */     
/* 3901 */     ResultSet procsAndOrFuncsRs = null;
/*      */     
/* 3903 */     if (supportsStoredProcedures()) {
/*      */       
/*      */       try {
/* 3906 */         String tmpProcedureOrFunctionNamePattern = null;
/*      */         
/* 3908 */         if (procedureOrFunctionNamePattern != null && !procedureOrFunctionNamePattern.equals("%")) {
/* 3909 */           tmpProcedureOrFunctionNamePattern = StringUtils.sanitizeProcOrFuncName(procedureOrFunctionNamePattern);
/*      */         }
/*      */ 
/*      */         
/* 3913 */         if (tmpProcedureOrFunctionNamePattern == null) {
/* 3914 */           tmpProcedureOrFunctionNamePattern = procedureOrFunctionNamePattern;
/*      */         }
/*      */         else {
/*      */           
/* 3918 */           String tmpCatalog = catalog;
/* 3919 */           List<String> parseList = StringUtils.splitDBdotName(tmpProcedureOrFunctionNamePattern, tmpCatalog, this.quotedId, this.conn.isNoBackslashEscapesSet());
/*      */ 
/*      */ 
/*      */           
/* 3923 */           if (parseList.size() == 2) {
/* 3924 */             tmpCatalog = parseList.get(0);
/* 3925 */             tmpProcedureOrFunctionNamePattern = parseList.get(1);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 3931 */         procsAndOrFuncsRs = getProceduresAndOrFunctions(createFieldMetadataForGetProcedures(), catalog, schemaPattern, tmpProcedureOrFunctionNamePattern, returnProcedures, returnFunctions);
/*      */ 
/*      */         
/* 3934 */         boolean hasResults = false;
/* 3935 */         while (procsAndOrFuncsRs.next()) {
/* 3936 */           procsOrFuncsToExtractList.add(new ComparableWrapper<String, ProcedureType>(getFullyQualifiedName(procsAndOrFuncsRs.getString(1), procsAndOrFuncsRs.getString(3)), (procsAndOrFuncsRs.getShort(8) == 1) ? ProcedureType.PROCEDURE : ProcedureType.FUNCTION));
/*      */ 
/*      */           
/* 3939 */           hasResults = true;
/*      */         } 
/*      */ 
/*      */         
/* 3943 */         if (hasResults)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3950 */           Collections.sort(procsOrFuncsToExtractList);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       finally {
/*      */         
/* 3957 */         SQLException rethrowSqlEx = null;
/*      */         
/* 3959 */         if (procsAndOrFuncsRs != null) {
/*      */           try {
/* 3961 */             procsAndOrFuncsRs.close();
/* 3962 */           } catch (SQLException sqlEx) {
/* 3963 */             rethrowSqlEx = sqlEx;
/*      */           } 
/*      */         }
/*      */         
/* 3967 */         if (rethrowSqlEx != null) {
/* 3968 */           throw rethrowSqlEx;
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 3973 */     ArrayList<ResultSetRow> resultRows = new ArrayList<ResultSetRow>();
/* 3974 */     int idx = 0;
/* 3975 */     String procNameToCall = "";
/*      */     
/* 3977 */     for (ComparableWrapper<String, ProcedureType> procOrFunc : procsOrFuncsToExtractList) {
/* 3978 */       String procName = procOrFunc.getKey();
/* 3979 */       ProcedureType procType = procOrFunc.getValue();
/*      */ 
/*      */       
/* 3982 */       if (!" ".equals(this.quotedId)) {
/* 3983 */         idx = StringUtils.indexOfIgnoreCase(0, procName, ".", this.quotedId, this.quotedId, this.conn.isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
/*      */       } else {
/*      */         
/* 3986 */         idx = procName.indexOf(".");
/*      */       } 
/*      */       
/* 3989 */       if (idx > 0) {
/* 3990 */         catalog = StringUtils.unQuoteIdentifier(procName.substring(0, idx), this.quotedId);
/* 3991 */         procNameToCall = procName;
/*      */       } else {
/*      */         
/* 3994 */         procNameToCall = procName;
/*      */       } 
/* 3996 */       getCallStmtParameterTypes(catalog, procNameToCall, procType, columnNamePattern, resultRows, (fields.length == 17));
/*      */     } 
/*      */     
/* 3999 */     return buildResultSet(fields, resultRows);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
/* 4039 */     Field[] fields = createFieldMetadataForGetProcedures();
/*      */     
/* 4041 */     return getProceduresAndOrFunctions(fields, catalog, schemaPattern, procedureNamePattern, true, true);
/*      */   }
/*      */   
/*      */   protected Field[] createFieldMetadataForGetProcedures() {
/* 4045 */     Field[] fields = new Field[9];
/* 4046 */     fields[0] = new Field("", "PROCEDURE_CAT", 1, 255);
/* 4047 */     fields[1] = new Field("", "PROCEDURE_SCHEM", 1, 255);
/* 4048 */     fields[2] = new Field("", "PROCEDURE_NAME", 1, 255);
/* 4049 */     fields[3] = new Field("", "reserved1", 1, 0);
/* 4050 */     fields[4] = new Field("", "reserved2", 1, 0);
/* 4051 */     fields[5] = new Field("", "reserved3", 1, 0);
/* 4052 */     fields[6] = new Field("", "REMARKS", 1, 255);
/* 4053 */     fields[7] = new Field("", "PROCEDURE_TYPE", 5, 6);
/* 4054 */     fields[8] = new Field("", "SPECIFIC_NAME", 1, 255);
/*      */     
/* 4056 */     return fields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ResultSet getProceduresAndOrFunctions(final Field[] fields, String catalog, String schemaPattern, String procedureNamePattern, final boolean returnProcedures, final boolean returnFunctions) throws SQLException {
/* 4070 */     if (procedureNamePattern == null || procedureNamePattern.length() == 0) {
/* 4071 */       if (this.conn.getNullNamePatternMatchesAll()) {
/* 4072 */         procedureNamePattern = "%";
/*      */       } else {
/* 4074 */         throw SQLError.createSQLException("Procedure name pattern can not be NULL or empty.", "S1009", getExceptionInterceptor());
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 4079 */     ArrayList<ResultSetRow> procedureRows = new ArrayList<ResultSetRow>();
/*      */     
/* 4081 */     if (supportsStoredProcedures()) {
/* 4082 */       final String procNamePattern = procedureNamePattern;
/*      */       
/* 4084 */       final List<ComparableWrapper<String, ResultSetRow>> procedureRowsToSort = new ArrayList<ComparableWrapper<String, ResultSetRow>>();
/*      */       
/* 4086 */       (new IterateBlock<String>(getCatalogIterator(catalog))
/*      */         {
/*      */           void forEach(String catalogStr) throws SQLException {
/* 4089 */             String db = catalogStr;
/*      */             
/* 4091 */             ResultSet proceduresRs = null;
/* 4092 */             boolean needsClientFiltering = true;
/*      */             
/* 4094 */             StringBuilder selectFromMySQLProcSQL = new StringBuilder();
/*      */             
/* 4096 */             selectFromMySQLProcSQL.append("SELECT name, type, comment FROM mysql.proc WHERE ");
/* 4097 */             if (returnProcedures && !returnFunctions) {
/* 4098 */               selectFromMySQLProcSQL.append("type = 'PROCEDURE' AND ");
/* 4099 */             } else if (!returnProcedures && returnFunctions) {
/* 4100 */               selectFromMySQLProcSQL.append("type = 'FUNCTION' AND ");
/*      */             } 
/* 4102 */             selectFromMySQLProcSQL.append("name LIKE ? AND db <=> ? ORDER BY name, type");
/*      */             
/* 4104 */             PreparedStatement proceduresStmt = DatabaseMetaData.this.prepareMetaDataSafeStatement(selectFromMySQLProcSQL.toString());
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             try {
/* 4110 */               if (db != null) {
/* 4111 */                 if (DatabaseMetaData.this.conn.lowerCaseTableNames()) {
/* 4112 */                   db = db.toLowerCase();
/*      */                 }
/* 4114 */                 proceduresStmt.setString(2, db);
/*      */               } else {
/* 4116 */                 proceduresStmt.setNull(2, 12);
/*      */               } 
/*      */               
/* 4119 */               int nameIndex = 1;
/*      */               
/* 4121 */               proceduresStmt.setString(1, procNamePattern);
/*      */               
/*      */               try {
/* 4124 */                 proceduresRs = proceduresStmt.executeQuery();
/* 4125 */                 needsClientFiltering = false;
/*      */                 
/* 4127 */                 if (returnProcedures) {
/* 4128 */                   DatabaseMetaData.this.convertToJdbcProcedureList(true, db, proceduresRs, needsClientFiltering, db, procedureRowsToSort, nameIndex);
/*      */                 }
/*      */                 
/* 4131 */                 if (returnFunctions) {
/* 4132 */                   DatabaseMetaData.this.convertToJdbcFunctionList(db, proceduresRs, needsClientFiltering, db, procedureRowsToSort, nameIndex, fields);
/*      */                 }
/*      */               }
/* 4135 */               catch (SQLException sqlEx) {
/* 4136 */                 nameIndex = DatabaseMetaData.this.conn.versionMeetsMinimum(5, 0, 1) ? 2 : 1;
/*      */ 
/*      */ 
/*      */                 
/* 4140 */                 if (returnFunctions) {
/* 4141 */                   proceduresStmt.close();
/*      */                   
/* 4143 */                   proceduresStmt = DatabaseMetaData.this.prepareMetaDataSafeStatement("SHOW FUNCTION STATUS LIKE ?");
/* 4144 */                   proceduresStmt.setString(1, procNamePattern);
/* 4145 */                   proceduresRs = proceduresStmt.executeQuery();
/*      */                   
/* 4147 */                   DatabaseMetaData.this.convertToJdbcFunctionList(db, proceduresRs, needsClientFiltering, db, procedureRowsToSort, nameIndex, fields);
/*      */                 } 
/*      */ 
/*      */                 
/* 4151 */                 if (returnProcedures) {
/* 4152 */                   proceduresStmt.close();
/*      */                   
/* 4154 */                   proceduresStmt = DatabaseMetaData.this.prepareMetaDataSafeStatement("SHOW PROCEDURE STATUS LIKE ?");
/* 4155 */                   proceduresStmt.setString(1, procNamePattern);
/* 4156 */                   proceduresRs = proceduresStmt.executeQuery();
/*      */                   
/* 4158 */                   DatabaseMetaData.this.convertToJdbcProcedureList(false, db, proceduresRs, needsClientFiltering, db, procedureRowsToSort, nameIndex);
/*      */                 } 
/*      */               } 
/*      */             } finally {
/*      */               
/* 4163 */               SQLException rethrowSqlEx = null;
/*      */               
/* 4165 */               if (proceduresRs != null) {
/*      */                 try {
/* 4167 */                   proceduresRs.close();
/* 4168 */                 } catch (SQLException sqlEx) {
/* 4169 */                   rethrowSqlEx = sqlEx;
/*      */                 } 
/*      */               }
/*      */               
/* 4173 */               if (proceduresStmt != null) {
/*      */                 try {
/* 4175 */                   proceduresStmt.close();
/* 4176 */                 } catch (SQLException sqlEx) {
/* 4177 */                   rethrowSqlEx = sqlEx;
/*      */                 } 
/*      */               }
/*      */               
/* 4181 */               if (rethrowSqlEx != null) {
/* 4182 */                 throw rethrowSqlEx;
/*      */               }
/*      */             } 
/*      */           }
/*      */         }).doForAll();
/*      */       
/* 4188 */       Collections.sort(procedureRowsToSort);
/* 4189 */       for (ComparableWrapper<String, ResultSetRow> procRow : procedureRowsToSort) {
/* 4190 */         procedureRows.add(procRow.getValue());
/*      */       }
/*      */     } 
/*      */     
/* 4194 */     return buildResultSet(fields, procedureRows);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getProcedureTerm() throws SQLException {
/* 4205 */     return "PROCEDURE";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getResultSetHoldability() throws SQLException {
/* 4212 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void getResultsImpl(String catalog, String table, String keysComment, List<ResultSetRow> tuples, String fkTableName, boolean isExport) throws SQLException {
/* 4218 */     LocalAndReferencedColumns parsedInfo = parseTableStatusIntoLocalAndReferencedColumns(keysComment);
/*      */     
/* 4220 */     if (isExport && !parsedInfo.referencedTable.equals(table)) {
/*      */       return;
/*      */     }
/*      */     
/* 4224 */     if (parsedInfo.localColumnsList.size() != parsedInfo.referencedColumnsList.size()) {
/* 4225 */       throw SQLError.createSQLException("Error parsing foreign keys definition, number of local and referenced columns is not the same.", "S1000", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 4229 */     Iterator<String> localColumnNames = parsedInfo.localColumnsList.iterator();
/* 4230 */     Iterator<String> referColumnNames = parsedInfo.referencedColumnsList.iterator();
/*      */     
/* 4232 */     int keySeqIndex = 1;
/*      */     
/* 4234 */     while (localColumnNames.hasNext()) {
/* 4235 */       byte[][] tuple = new byte[14][];
/* 4236 */       String lColumnName = StringUtils.unQuoteIdentifier(localColumnNames.next(), this.quotedId);
/* 4237 */       String rColumnName = StringUtils.unQuoteIdentifier(referColumnNames.next(), this.quotedId);
/* 4238 */       tuple[4] = (catalog == null) ? new byte[0] : s2b(catalog);
/* 4239 */       tuple[5] = null;
/* 4240 */       tuple[6] = s2b(isExport ? fkTableName : table);
/* 4241 */       tuple[7] = s2b(lColumnName);
/* 4242 */       tuple[0] = s2b(parsedInfo.referencedCatalog);
/* 4243 */       tuple[1] = null;
/* 4244 */       tuple[2] = s2b(isExport ? table : parsedInfo.referencedTable);
/* 4245 */       tuple[3] = s2b(rColumnName);
/* 4246 */       tuple[8] = s2b(Integer.toString(keySeqIndex++));
/*      */       
/* 4248 */       int[] actions = getForeignKeyActions(keysComment);
/*      */       
/* 4250 */       tuple[9] = s2b(Integer.toString(actions[1]));
/* 4251 */       tuple[10] = s2b(Integer.toString(actions[0]));
/* 4252 */       tuple[11] = s2b(parsedInfo.constraintName);
/* 4253 */       tuple[12] = null;
/* 4254 */       tuple[13] = s2b(Integer.toString(7));
/* 4255 */       tuples.add(new ByteArrayRow(tuple, getExceptionInterceptor()));
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
/*      */   public ResultSet getSchemas() throws SQLException {
/* 4274 */     Field[] fields = new Field[2];
/* 4275 */     fields[0] = new Field("", "TABLE_SCHEM", 1, 0);
/* 4276 */     fields[1] = new Field("", "TABLE_CATALOG", 1, 0);
/*      */     
/* 4278 */     ArrayList<ResultSetRow> tuples = new ArrayList<ResultSetRow>();
/* 4279 */     ResultSet results = buildResultSet(fields, tuples);
/*      */     
/* 4281 */     return results;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSchemaTerm() throws SQLException {
/* 4291 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSearchStringEscape() throws SQLException {
/* 4308 */     return "\\";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSQLKeywords() throws SQLException {
/* 4318 */     if (mysqlKeywords != null) {
/* 4319 */       return mysqlKeywords;
/*      */     }
/*      */     
/* 4322 */     synchronized (DatabaseMetaData.class) {
/*      */       
/* 4324 */       if (mysqlKeywords != null) {
/* 4325 */         return mysqlKeywords;
/*      */       }
/*      */       
/* 4328 */       Set<String> mysqlKeywordSet = new TreeSet<String>();
/* 4329 */       StringBuilder mysqlKeywordsBuffer = new StringBuilder();
/*      */       
/* 4331 */       Collections.addAll(mysqlKeywordSet, MYSQL_KEYWORDS);
/* 4332 */       mysqlKeywordSet.removeAll(Arrays.asList(Util.isJdbc4() ? (Object[])SQL2003_KEYWORDS : (Object[])SQL92_KEYWORDS));
/*      */       
/* 4334 */       for (String keyword : mysqlKeywordSet) {
/* 4335 */         mysqlKeywordsBuffer.append(",").append(keyword);
/*      */       }
/*      */       
/* 4338 */       mysqlKeywords = mysqlKeywordsBuffer.substring(1);
/* 4339 */       return mysqlKeywords;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSQLStateType() throws SQLException {
/* 4347 */     if (this.conn.versionMeetsMinimum(4, 1, 0)) {
/* 4348 */       return 2;
/*      */     }
/*      */     
/* 4351 */     if (this.conn.getUseSqlStateCodes()) {
/* 4352 */       return 2;
/*      */     }
/*      */     
/* 4355 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringFunctions() throws SQLException {
/* 4365 */     return "ASCII,BIN,BIT_LENGTH,CHAR,CHARACTER_LENGTH,CHAR_LENGTH,CONCAT,CONCAT_WS,CONV,ELT,EXPORT_SET,FIELD,FIND_IN_SET,HEX,INSERT,INSTR,LCASE,LEFT,LENGTH,LOAD_FILE,LOCATE,LOCATE,LOWER,LPAD,LTRIM,MAKE_SET,MATCH,MID,OCT,OCTET_LENGTH,ORD,POSITION,QUOTE,REPEAT,REPLACE,REVERSE,RIGHT,RPAD,RTRIM,SOUNDEX,SPACE,STRCMP,SUBSTRING,SUBSTRING,SUBSTRING,SUBSTRING,SUBSTRING_INDEX,TRIM,UCASE,UPPER";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getSuperTables(String arg0, String arg1, String arg2) throws SQLException {
/* 4375 */     Field[] fields = new Field[4];
/* 4376 */     fields[0] = new Field("", "TABLE_CAT", 1, 32);
/* 4377 */     fields[1] = new Field("", "TABLE_SCHEM", 1, 32);
/* 4378 */     fields[2] = new Field("", "TABLE_NAME", 1, 32);
/* 4379 */     fields[3] = new Field("", "SUPERTABLE_NAME", 1, 32);
/*      */     
/* 4381 */     return buildResultSet(fields, new ArrayList<ResultSetRow>());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getSuperTypes(String arg0, String arg1, String arg2) throws SQLException {
/* 4388 */     Field[] fields = new Field[6];
/* 4389 */     fields[0] = new Field("", "TYPE_CAT", 1, 32);
/* 4390 */     fields[1] = new Field("", "TYPE_SCHEM", 1, 32);
/* 4391 */     fields[2] = new Field("", "TYPE_NAME", 1, 32);
/* 4392 */     fields[3] = new Field("", "SUPERTYPE_CAT", 1, 32);
/* 4393 */     fields[4] = new Field("", "SUPERTYPE_SCHEM", 1, 32);
/* 4394 */     fields[5] = new Field("", "SUPERTYPE_NAME", 1, 32);
/*      */     
/* 4396 */     return buildResultSet(fields, new ArrayList<ResultSetRow>());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSystemFunctions() throws SQLException {
/* 4406 */     return "DATABASE,USER,SYSTEM_USER,SESSION_USER,PASSWORD,ENCRYPT,LAST_INSERT_ID,VERSION";
/*      */   }
/*      */   
/*      */   protected String getTableNameWithCase(String table) {
/* 4410 */     String tableNameWithCase = this.conn.lowerCaseTableNames() ? table.toLowerCase() : table;
/*      */     
/* 4412 */     return tableNameWithCase;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
/*      */     // Byte code:
/*      */     //   0: aload_3
/*      */     //   1: ifnonnull -> 37
/*      */     //   4: aload_0
/*      */     //   5: getfield conn : Lcom/mysql/jdbc/MySQLConnection;
/*      */     //   8: invokeinterface getNullNamePatternMatchesAll : ()Z
/*      */     //   13: ifeq -> 23
/*      */     //   16: ldc_w '%'
/*      */     //   19: astore_3
/*      */     //   20: goto -> 37
/*      */     //   23: ldc_w 'Table name pattern can not be NULL or empty.'
/*      */     //   26: ldc_w 'S1009'
/*      */     //   29: aload_0
/*      */     //   30: invokevirtual getExceptionInterceptor : ()Lcom/mysql/jdbc/ExceptionInterceptor;
/*      */     //   33: invokestatic createSQLException : (Ljava/lang/String;Ljava/lang/String;Lcom/mysql/jdbc/ExceptionInterceptor;)Ljava/sql/SQLException;
/*      */     //   36: athrow
/*      */     //   37: bipush #7
/*      */     //   39: anewarray com/mysql/jdbc/Field
/*      */     //   42: astore #4
/*      */     //   44: aload #4
/*      */     //   46: iconst_0
/*      */     //   47: new com/mysql/jdbc/Field
/*      */     //   50: dup
/*      */     //   51: ldc_w ''
/*      */     //   54: ldc_w 'TABLE_CAT'
/*      */     //   57: iconst_1
/*      */     //   58: bipush #64
/*      */     //   60: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;II)V
/*      */     //   63: aastore
/*      */     //   64: aload #4
/*      */     //   66: iconst_1
/*      */     //   67: new com/mysql/jdbc/Field
/*      */     //   70: dup
/*      */     //   71: ldc_w ''
/*      */     //   74: ldc_w 'TABLE_SCHEM'
/*      */     //   77: iconst_1
/*      */     //   78: iconst_1
/*      */     //   79: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;II)V
/*      */     //   82: aastore
/*      */     //   83: aload #4
/*      */     //   85: iconst_2
/*      */     //   86: new com/mysql/jdbc/Field
/*      */     //   89: dup
/*      */     //   90: ldc_w ''
/*      */     //   93: ldc_w 'TABLE_NAME'
/*      */     //   96: iconst_1
/*      */     //   97: bipush #64
/*      */     //   99: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;II)V
/*      */     //   102: aastore
/*      */     //   103: aload #4
/*      */     //   105: iconst_3
/*      */     //   106: new com/mysql/jdbc/Field
/*      */     //   109: dup
/*      */     //   110: ldc_w ''
/*      */     //   113: ldc_w 'GRANTOR'
/*      */     //   116: iconst_1
/*      */     //   117: bipush #77
/*      */     //   119: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;II)V
/*      */     //   122: aastore
/*      */     //   123: aload #4
/*      */     //   125: iconst_4
/*      */     //   126: new com/mysql/jdbc/Field
/*      */     //   129: dup
/*      */     //   130: ldc_w ''
/*      */     //   133: ldc_w 'GRANTEE'
/*      */     //   136: iconst_1
/*      */     //   137: bipush #77
/*      */     //   139: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;II)V
/*      */     //   142: aastore
/*      */     //   143: aload #4
/*      */     //   145: iconst_5
/*      */     //   146: new com/mysql/jdbc/Field
/*      */     //   149: dup
/*      */     //   150: ldc_w ''
/*      */     //   153: ldc_w 'PRIVILEGE'
/*      */     //   156: iconst_1
/*      */     //   157: bipush #64
/*      */     //   159: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;II)V
/*      */     //   162: aastore
/*      */     //   163: aload #4
/*      */     //   165: bipush #6
/*      */     //   167: new com/mysql/jdbc/Field
/*      */     //   170: dup
/*      */     //   171: ldc_w ''
/*      */     //   174: ldc_w 'IS_GRANTABLE'
/*      */     //   177: iconst_1
/*      */     //   178: iconst_3
/*      */     //   179: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;II)V
/*      */     //   182: aastore
/*      */     //   183: ldc_w 'SELECT host,db,table_name,grantor,user,table_priv FROM mysql.tables_priv WHERE db LIKE ? AND table_name LIKE ?'
/*      */     //   186: astore #5
/*      */     //   188: aconst_null
/*      */     //   189: astore #6
/*      */     //   191: new java/util/ArrayList
/*      */     //   194: dup
/*      */     //   195: invokespecial <init> : ()V
/*      */     //   198: astore #7
/*      */     //   200: aconst_null
/*      */     //   201: astore #8
/*      */     //   203: aload_0
/*      */     //   204: aload #5
/*      */     //   206: invokevirtual prepareMetaDataSafeStatement : (Ljava/lang/String;)Ljava/sql/PreparedStatement;
/*      */     //   209: astore #8
/*      */     //   211: aload #8
/*      */     //   213: iconst_1
/*      */     //   214: aload_1
/*      */     //   215: ifnull -> 229
/*      */     //   218: aload_1
/*      */     //   219: invokevirtual length : ()I
/*      */     //   222: ifeq -> 229
/*      */     //   225: aload_1
/*      */     //   226: goto -> 232
/*      */     //   229: ldc_w '%'
/*      */     //   232: invokeinterface setString : (ILjava/lang/String;)V
/*      */     //   237: aload #8
/*      */     //   239: iconst_2
/*      */     //   240: aload_3
/*      */     //   241: invokeinterface setString : (ILjava/lang/String;)V
/*      */     //   246: aload #8
/*      */     //   248: invokeinterface executeQuery : ()Ljava/sql/ResultSet;
/*      */     //   253: astore #6
/*      */     //   255: aload #6
/*      */     //   257: invokeinterface next : ()Z
/*      */     //   262: ifeq -> 609
/*      */     //   265: aload #6
/*      */     //   267: iconst_1
/*      */     //   268: invokeinterface getString : (I)Ljava/lang/String;
/*      */     //   273: astore #9
/*      */     //   275: aload #6
/*      */     //   277: iconst_2
/*      */     //   278: invokeinterface getString : (I)Ljava/lang/String;
/*      */     //   283: astore #10
/*      */     //   285: aload #6
/*      */     //   287: iconst_3
/*      */     //   288: invokeinterface getString : (I)Ljava/lang/String;
/*      */     //   293: astore #11
/*      */     //   295: aload #6
/*      */     //   297: iconst_4
/*      */     //   298: invokeinterface getString : (I)Ljava/lang/String;
/*      */     //   303: astore #12
/*      */     //   305: aload #6
/*      */     //   307: iconst_5
/*      */     //   308: invokeinterface getString : (I)Ljava/lang/String;
/*      */     //   313: astore #13
/*      */     //   315: aload #13
/*      */     //   317: ifnull -> 328
/*      */     //   320: aload #13
/*      */     //   322: invokevirtual length : ()I
/*      */     //   325: ifne -> 333
/*      */     //   328: ldc_w '%'
/*      */     //   331: astore #13
/*      */     //   333: new java/lang/StringBuilder
/*      */     //   336: dup
/*      */     //   337: aload #13
/*      */     //   339: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   342: astore #14
/*      */     //   344: aload #9
/*      */     //   346: ifnull -> 378
/*      */     //   349: aload_0
/*      */     //   350: getfield conn : Lcom/mysql/jdbc/MySQLConnection;
/*      */     //   353: invokeinterface getUseHostsInPrivileges : ()Z
/*      */     //   358: ifeq -> 378
/*      */     //   361: aload #14
/*      */     //   363: ldc_w '@'
/*      */     //   366: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   369: pop
/*      */     //   370: aload #14
/*      */     //   372: aload #9
/*      */     //   374: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   377: pop
/*      */     //   378: aload #6
/*      */     //   380: bipush #6
/*      */     //   382: invokeinterface getString : (I)Ljava/lang/String;
/*      */     //   387: astore #15
/*      */     //   389: aload #15
/*      */     //   391: ifnull -> 606
/*      */     //   394: aload #15
/*      */     //   396: getstatic java/util/Locale.ENGLISH : Ljava/util/Locale;
/*      */     //   399: invokevirtual toUpperCase : (Ljava/util/Locale;)Ljava/lang/String;
/*      */     //   402: astore #15
/*      */     //   404: new java/util/StringTokenizer
/*      */     //   407: dup
/*      */     //   408: aload #15
/*      */     //   410: ldc_w ','
/*      */     //   413: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   416: astore #16
/*      */     //   418: aload #16
/*      */     //   420: invokevirtual hasMoreTokens : ()Z
/*      */     //   423: ifeq -> 606
/*      */     //   426: aload #16
/*      */     //   428: invokevirtual nextToken : ()Ljava/lang/String;
/*      */     //   431: invokevirtual trim : ()Ljava/lang/String;
/*      */     //   434: astore #17
/*      */     //   436: aconst_null
/*      */     //   437: astore #18
/*      */     //   439: aload_0
/*      */     //   440: aload_1
/*      */     //   441: aload_2
/*      */     //   442: aload #11
/*      */     //   444: ldc_w '%'
/*      */     //   447: invokevirtual getColumns : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/sql/ResultSet;
/*      */     //   450: astore #18
/*      */     //   452: aload #18
/*      */     //   454: invokeinterface next : ()Z
/*      */     //   459: ifeq -> 568
/*      */     //   462: bipush #8
/*      */     //   464: anewarray [B
/*      */     //   467: astore #19
/*      */     //   469: aload #19
/*      */     //   471: iconst_0
/*      */     //   472: aload_0
/*      */     //   473: aload #10
/*      */     //   475: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */     //   478: aastore
/*      */     //   479: aload #19
/*      */     //   481: iconst_1
/*      */     //   482: aconst_null
/*      */     //   483: aastore
/*      */     //   484: aload #19
/*      */     //   486: iconst_2
/*      */     //   487: aload_0
/*      */     //   488: aload #11
/*      */     //   490: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */     //   493: aastore
/*      */     //   494: aload #12
/*      */     //   496: ifnull -> 512
/*      */     //   499: aload #19
/*      */     //   501: iconst_3
/*      */     //   502: aload_0
/*      */     //   503: aload #12
/*      */     //   505: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */     //   508: aastore
/*      */     //   509: goto -> 517
/*      */     //   512: aload #19
/*      */     //   514: iconst_3
/*      */     //   515: aconst_null
/*      */     //   516: aastore
/*      */     //   517: aload #19
/*      */     //   519: iconst_4
/*      */     //   520: aload_0
/*      */     //   521: aload #14
/*      */     //   523: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   526: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */     //   529: aastore
/*      */     //   530: aload #19
/*      */     //   532: iconst_5
/*      */     //   533: aload_0
/*      */     //   534: aload #17
/*      */     //   536: invokevirtual s2b : (Ljava/lang/String;)[B
/*      */     //   539: aastore
/*      */     //   540: aload #19
/*      */     //   542: bipush #6
/*      */     //   544: aconst_null
/*      */     //   545: aastore
/*      */     //   546: aload #7
/*      */     //   548: new com/mysql/jdbc/ByteArrayRow
/*      */     //   551: dup
/*      */     //   552: aload #19
/*      */     //   554: aload_0
/*      */     //   555: invokevirtual getExceptionInterceptor : ()Lcom/mysql/jdbc/ExceptionInterceptor;
/*      */     //   558: invokespecial <init> : ([[BLcom/mysql/jdbc/ExceptionInterceptor;)V
/*      */     //   561: invokevirtual add : (Ljava/lang/Object;)Z
/*      */     //   564: pop
/*      */     //   565: goto -> 452
/*      */     //   568: jsr -> 582
/*      */     //   571: goto -> 603
/*      */     //   574: astore #20
/*      */     //   576: jsr -> 582
/*      */     //   579: aload #20
/*      */     //   581: athrow
/*      */     //   582: astore #21
/*      */     //   584: aload #18
/*      */     //   586: ifnull -> 601
/*      */     //   589: aload #18
/*      */     //   591: invokeinterface close : ()V
/*      */     //   596: goto -> 601
/*      */     //   599: astore #22
/*      */     //   601: ret #21
/*      */     //   603: goto -> 418
/*      */     //   606: goto -> 255
/*      */     //   609: jsr -> 623
/*      */     //   612: goto -> 667
/*      */     //   615: astore #23
/*      */     //   617: jsr -> 623
/*      */     //   620: aload #23
/*      */     //   622: athrow
/*      */     //   623: astore #24
/*      */     //   625: aload #6
/*      */     //   627: ifnull -> 645
/*      */     //   630: aload #6
/*      */     //   632: invokeinterface close : ()V
/*      */     //   637: goto -> 642
/*      */     //   640: astore #25
/*      */     //   642: aconst_null
/*      */     //   643: astore #6
/*      */     //   645: aload #8
/*      */     //   647: ifnull -> 665
/*      */     //   650: aload #8
/*      */     //   652: invokeinterface close : ()V
/*      */     //   657: goto -> 662
/*      */     //   660: astore #25
/*      */     //   662: aconst_null
/*      */     //   663: astore #8
/*      */     //   665: ret #24
/*      */     //   667: aload_0
/*      */     //   668: aload #4
/*      */     //   670: aload #7
/*      */     //   672: invokespecial buildResultSet : ([Lcom/mysql/jdbc/Field;Ljava/util/ArrayList;)Ljava/sql/ResultSet;
/*      */     //   675: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #4448	-> 0
/*      */     //   #4449	-> 4
/*      */     //   #4450	-> 16
/*      */     //   #4452	-> 23
/*      */     //   #4457	-> 37
/*      */     //   #4458	-> 44
/*      */     //   #4459	-> 64
/*      */     //   #4460	-> 83
/*      */     //   #4461	-> 103
/*      */     //   #4462	-> 123
/*      */     //   #4463	-> 143
/*      */     //   #4464	-> 163
/*      */     //   #4466	-> 183
/*      */     //   #4468	-> 188
/*      */     //   #4469	-> 191
/*      */     //   #4470	-> 200
/*      */     //   #4473	-> 203
/*      */     //   #4475	-> 211
/*      */     //   #4476	-> 237
/*      */     //   #4478	-> 246
/*      */     //   #4480	-> 255
/*      */     //   #4481	-> 265
/*      */     //   #4482	-> 275
/*      */     //   #4483	-> 285
/*      */     //   #4484	-> 295
/*      */     //   #4485	-> 305
/*      */     //   #4487	-> 315
/*      */     //   #4488	-> 328
/*      */     //   #4491	-> 333
/*      */     //   #4493	-> 344
/*      */     //   #4494	-> 361
/*      */     //   #4495	-> 370
/*      */     //   #4498	-> 378
/*      */     //   #4500	-> 389
/*      */     //   #4501	-> 394
/*      */     //   #4503	-> 404
/*      */     //   #4505	-> 418
/*      */     //   #4506	-> 426
/*      */     //   #4509	-> 436
/*      */     //   #4512	-> 439
/*      */     //   #4514	-> 452
/*      */     //   #4515	-> 462
/*      */     //   #4516	-> 469
/*      */     //   #4517	-> 479
/*      */     //   #4518	-> 484
/*      */     //   #4520	-> 494
/*      */     //   #4521	-> 499
/*      */     //   #4523	-> 512
/*      */     //   #4526	-> 517
/*      */     //   #4527	-> 530
/*      */     //   #4528	-> 540
/*      */     //   #4529	-> 546
/*      */     //   #4530	-> 565
/*      */     //   #4531	-> 568
/*      */     //   #4538	-> 571
/*      */     //   #4532	-> 574
/*      */     //   #4534	-> 589
/*      */     //   #4536	-> 596
/*      */     //   #4535	-> 599
/*      */     //   #4536	-> 601
/*      */     //   #4539	-> 603
/*      */     //   #4541	-> 606
/*      */     //   #4542	-> 609
/*      */     //   #4560	-> 612
/*      */     //   #4543	-> 615
/*      */     //   #4545	-> 630
/*      */     //   #4547	-> 637
/*      */     //   #4546	-> 640
/*      */     //   #4549	-> 642
/*      */     //   #4552	-> 645
/*      */     //   #4554	-> 650
/*      */     //   #4556	-> 657
/*      */     //   #4555	-> 660
/*      */     //   #4558	-> 662
/*      */     //   #4562	-> 667
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   469	96	19	tuple	[[B
/*      */     //   601	0	22	ex	Ljava/lang/Exception;
/*      */     //   436	167	17	privilege	Ljava/lang/String;
/*      */     //   439	164	18	columnResults	Ljava/sql/ResultSet;
/*      */     //   418	188	16	st	Ljava/util/StringTokenizer;
/*      */     //   275	331	9	host	Ljava/lang/String;
/*      */     //   285	321	10	db	Ljava/lang/String;
/*      */     //   295	311	11	table	Ljava/lang/String;
/*      */     //   305	301	12	grantor	Ljava/lang/String;
/*      */     //   315	291	13	user	Ljava/lang/String;
/*      */     //   344	262	14	fullUser	Ljava/lang/StringBuilder;
/*      */     //   389	217	15	allPrivileges	Ljava/lang/String;
/*      */     //   642	0	25	ex	Ljava/lang/Exception;
/*      */     //   662	0	25	ex	Ljava/lang/Exception;
/*      */     //   0	676	0	this	Lcom/mysql/jdbc/DatabaseMetaData;
/*      */     //   0	676	1	catalog	Ljava/lang/String;
/*      */     //   0	676	2	schemaPattern	Ljava/lang/String;
/*      */     //   0	676	3	tableNamePattern	Ljava/lang/String;
/*      */     //   44	632	4	fields	[Lcom/mysql/jdbc/Field;
/*      */     //   188	488	5	grantQuery	Ljava/lang/String;
/*      */     //   191	485	6	results	Ljava/sql/ResultSet;
/*      */     //   200	476	7	grantRows	Ljava/util/ArrayList;
/*      */     //   203	473	8	pStmt	Ljava/sql/PreparedStatement;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   200	476	7	grantRows	Ljava/util/ArrayList<Lcom/mysql/jdbc/ResultSetRow;>;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   203	612	615	finally
/*      */     //   439	571	574	finally
/*      */     //   574	579	574	finally
/*      */     //   589	596	599	java/lang/Exception
/*      */     //   615	620	615	finally
/*      */     //   630	637	640	java/lang/Exception
/*      */     //   650	657	660	java/lang/Exception
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, final String[] types) throws SQLException {
/*      */     final String tableNamePat;
/* 4600 */     if (tableNamePattern == null) {
/* 4601 */       if (this.conn.getNullNamePatternMatchesAll()) {
/* 4602 */         tableNamePattern = "%";
/*      */       } else {
/* 4604 */         throw SQLError.createSQLException("Table name pattern can not be NULL or empty.", "S1009", getExceptionInterceptor());
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 4609 */     final SortedMap<TableMetaDataKey, ResultSetRow> sortedRows = new TreeMap<TableMetaDataKey, ResultSetRow>();
/* 4610 */     ArrayList<ResultSetRow> tuples = new ArrayList<ResultSetRow>();
/*      */     
/* 4612 */     final Statement stmt = this.conn.getMetadataSafeStatement();
/*      */ 
/*      */     
/* 4615 */     String tmpCat = "";
/*      */     
/* 4617 */     if (catalog == null || catalog.length() == 0) {
/* 4618 */       if (this.conn.getNullCatalogMeansCurrent()) {
/* 4619 */         tmpCat = this.database;
/*      */       }
/*      */     } else {
/* 4622 */       tmpCat = catalog;
/*      */     } 
/*      */     
/* 4625 */     List<String> parseList = StringUtils.splitDBdotName(tableNamePattern, tmpCat, this.quotedId, this.conn.isNoBackslashEscapesSet());
/*      */     
/* 4627 */     if (parseList.size() == 2) {
/* 4628 */       tableNamePat = parseList.get(1);
/*      */     } else {
/* 4630 */       tableNamePat = tableNamePattern;
/*      */     } 
/*      */     
/*      */     try {
/* 4634 */       (new IterateBlock<String>(getCatalogIterator(catalog))
/*      */         {
/*      */           void forEach(String catalogStr) throws SQLException {
/* 4637 */             boolean operatingOnSystemDB = ("information_schema".equalsIgnoreCase(catalogStr) || "mysql".equalsIgnoreCase(catalogStr) || "performance_schema".equalsIgnoreCase(catalogStr));
/*      */ 
/*      */             
/* 4640 */             ResultSet results = null;
/*      */ 
/*      */ 
/*      */             
/*      */             try {
/* 4645 */               results = stmt.executeQuery((!DatabaseMetaData.this.conn.versionMeetsMinimum(5, 0, 2) ? "SHOW TABLES FROM " : "SHOW FULL TABLES FROM ") + StringUtils.quoteIdentifier(catalogStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()) + " LIKE " + StringUtils.quoteIdentifier(tableNamePat, "'", true));
/*      */ 
/*      */             
/*      */             }
/* 4649 */             catch (SQLException sqlEx) {
/* 4650 */               if ("08S01".equals(sqlEx.getSQLState())) {
/* 4651 */                 throw sqlEx;
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               return;
/*      */             } finally {
/* 4796 */               if (results != null) {
/*      */                 try {
/* 4798 */                   results.close();
/* 4799 */                 } catch (Exception ex) {}
/*      */ 
/*      */                 
/* 4802 */                 results = null;
/*      */               } 
/*      */             } 
/*      */           }
/*      */         }).doForAll();
/*      */     } finally {
/* 4808 */       if (stmt != null) {
/* 4809 */         stmt.close();
/*      */       }
/*      */     } 
/*      */     
/* 4813 */     tuples.addAll(sortedRows.values());
/* 4814 */     ResultSet tables = buildResultSet(createTablesFields(), tuples);
/*      */     
/* 4816 */     return tables;
/*      */   }
/*      */   
/*      */   protected Field[] createTablesFields() {
/* 4820 */     Field[] fields = new Field[10];
/* 4821 */     fields[0] = new Field("", "TABLE_CAT", 12, 255);
/* 4822 */     fields[1] = new Field("", "TABLE_SCHEM", 12, 0);
/* 4823 */     fields[2] = new Field("", "TABLE_NAME", 12, 255);
/* 4824 */     fields[3] = new Field("", "TABLE_TYPE", 12, 5);
/* 4825 */     fields[4] = new Field("", "REMARKS", 12, 0);
/* 4826 */     fields[5] = new Field("", "TYPE_CAT", 12, 0);
/* 4827 */     fields[6] = new Field("", "TYPE_SCHEM", 12, 0);
/* 4828 */     fields[7] = new Field("", "TYPE_NAME", 12, 0);
/* 4829 */     fields[8] = new Field("", "SELF_REFERENCING_COL_NAME", 12, 0);
/* 4830 */     fields[9] = new Field("", "REF_GENERATION", 12, 0);
/* 4831 */     return fields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getTableTypes() throws SQLException {
/* 4850 */     ArrayList<ResultSetRow> tuples = new ArrayList<ResultSetRow>();
/* 4851 */     Field[] fields = { new Field("", "TABLE_TYPE", 12, 256) };
/*      */     
/* 4853 */     boolean minVersion5_0_1 = this.conn.versionMeetsMinimum(5, 0, 1);
/*      */     
/* 4855 */     tuples.add(new ByteArrayRow(new byte[][] { TableType.LOCAL_TEMPORARY.asBytes() }, getExceptionInterceptor()));
/* 4856 */     tuples.add(new ByteArrayRow(new byte[][] { TableType.SYSTEM_TABLE.asBytes() }, getExceptionInterceptor()));
/* 4857 */     if (minVersion5_0_1) {
/* 4858 */       tuples.add(new ByteArrayRow(new byte[][] { TableType.SYSTEM_VIEW.asBytes() }, getExceptionInterceptor()));
/*      */     }
/* 4860 */     tuples.add(new ByteArrayRow(new byte[][] { TableType.TABLE.asBytes() }, getExceptionInterceptor()));
/* 4861 */     if (minVersion5_0_1) {
/* 4862 */       tuples.add(new ByteArrayRow(new byte[][] { TableType.VIEW.asBytes() }, getExceptionInterceptor()));
/*      */     }
/*      */     
/* 4865 */     return buildResultSet(fields, tuples);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTimeDateFunctions() throws SQLException {
/* 4875 */     return "DAYOFWEEK,WEEKDAY,DAYOFMONTH,DAYOFYEAR,MONTH,DAYNAME,MONTHNAME,QUARTER,WEEK,YEAR,HOUR,MINUTE,SECOND,PERIOD_ADD,PERIOD_DIFF,TO_DAYS,FROM_DAYS,DATE_FORMAT,TIME_FORMAT,CURDATE,CURRENT_DATE,CURTIME,CURRENT_TIME,NOW,SYSDATE,CURRENT_TIMESTAMP,UNIX_TIMESTAMP,FROM_UNIXTIME,SEC_TO_TIME,TIME_TO_SEC";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getTypeInfo() throws SQLException {
/* 4969 */     Field[] fields = new Field[18];
/* 4970 */     fields[0] = new Field("", "TYPE_NAME", 1, 32);
/* 4971 */     fields[1] = new Field("", "DATA_TYPE", 4, 5);
/* 4972 */     fields[2] = new Field("", "PRECISION", 4, 10);
/* 4973 */     fields[3] = new Field("", "LITERAL_PREFIX", 1, 4);
/* 4974 */     fields[4] = new Field("", "LITERAL_SUFFIX", 1, 4);
/* 4975 */     fields[5] = new Field("", "CREATE_PARAMS", 1, 32);
/* 4976 */     fields[6] = new Field("", "NULLABLE", 5, 5);
/* 4977 */     fields[7] = new Field("", "CASE_SENSITIVE", 16, 3);
/* 4978 */     fields[8] = new Field("", "SEARCHABLE", 5, 3);
/* 4979 */     fields[9] = new Field("", "UNSIGNED_ATTRIBUTE", 16, 3);
/* 4980 */     fields[10] = new Field("", "FIXED_PREC_SCALE", 16, 3);
/* 4981 */     fields[11] = new Field("", "AUTO_INCREMENT", 16, 3);
/* 4982 */     fields[12] = new Field("", "LOCAL_TYPE_NAME", 1, 32);
/* 4983 */     fields[13] = new Field("", "MINIMUM_SCALE", 5, 5);
/* 4984 */     fields[14] = new Field("", "MAXIMUM_SCALE", 5, 5);
/* 4985 */     fields[15] = new Field("", "SQL_DATA_TYPE", 4, 10);
/* 4986 */     fields[16] = new Field("", "SQL_DATETIME_SUB", 4, 10);
/* 4987 */     fields[17] = new Field("", "NUM_PREC_RADIX", 4, 10);
/*      */     
/* 4989 */     byte[][] rowVal = (byte[][])null;
/* 4990 */     ArrayList<ResultSetRow> tuples = new ArrayList<ResultSetRow>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4998 */     rowVal = new byte[18][];
/* 4999 */     rowVal[0] = s2b("BIT");
/* 5000 */     rowVal[1] = Integer.toString(-7).getBytes();
/*      */ 
/*      */     
/* 5003 */     rowVal[2] = s2b("1");
/* 5004 */     rowVal[3] = s2b("");
/* 5005 */     rowVal[4] = s2b("");
/* 5006 */     rowVal[5] = s2b("");
/* 5007 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5010 */     rowVal[7] = s2b("true");
/* 5011 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5014 */     rowVal[9] = s2b("false");
/* 5015 */     rowVal[10] = s2b("false");
/* 5016 */     rowVal[11] = s2b("false");
/* 5017 */     rowVal[12] = s2b("BIT");
/* 5018 */     rowVal[13] = s2b("0");
/* 5019 */     rowVal[14] = s2b("0");
/* 5020 */     rowVal[15] = s2b("0");
/* 5021 */     rowVal[16] = s2b("0");
/* 5022 */     rowVal[17] = s2b("10");
/* 5023 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5028 */     rowVal = new byte[18][];
/* 5029 */     rowVal[0] = s2b("BOOL");
/* 5030 */     rowVal[1] = Integer.toString(-7).getBytes();
/*      */ 
/*      */     
/* 5033 */     rowVal[2] = s2b("1");
/* 5034 */     rowVal[3] = s2b("");
/* 5035 */     rowVal[4] = s2b("");
/* 5036 */     rowVal[5] = s2b("");
/* 5037 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5040 */     rowVal[7] = s2b("true");
/* 5041 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5044 */     rowVal[9] = s2b("false");
/* 5045 */     rowVal[10] = s2b("false");
/* 5046 */     rowVal[11] = s2b("false");
/* 5047 */     rowVal[12] = s2b("BOOL");
/* 5048 */     rowVal[13] = s2b("0");
/* 5049 */     rowVal[14] = s2b("0");
/* 5050 */     rowVal[15] = s2b("0");
/* 5051 */     rowVal[16] = s2b("0");
/* 5052 */     rowVal[17] = s2b("10");
/* 5053 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5058 */     rowVal = new byte[18][];
/* 5059 */     rowVal[0] = s2b("TINYINT");
/* 5060 */     rowVal[1] = Integer.toString(-6).getBytes();
/*      */ 
/*      */     
/* 5063 */     rowVal[2] = s2b("3");
/* 5064 */     rowVal[3] = s2b("");
/* 5065 */     rowVal[4] = s2b("");
/* 5066 */     rowVal[5] = s2b("[(M)] [UNSIGNED] [ZEROFILL]");
/* 5067 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5070 */     rowVal[7] = s2b("false");
/* 5071 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5074 */     rowVal[9] = s2b("true");
/* 5075 */     rowVal[10] = s2b("false");
/* 5076 */     rowVal[11] = s2b("true");
/* 5077 */     rowVal[12] = s2b("TINYINT");
/* 5078 */     rowVal[13] = s2b("0");
/* 5079 */     rowVal[14] = s2b("0");
/* 5080 */     rowVal[15] = s2b("0");
/* 5081 */     rowVal[16] = s2b("0");
/* 5082 */     rowVal[17] = s2b("10");
/* 5083 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */     
/* 5085 */     rowVal = new byte[18][];
/* 5086 */     rowVal[0] = s2b("TINYINT UNSIGNED");
/* 5087 */     rowVal[1] = Integer.toString(-6).getBytes();
/*      */ 
/*      */     
/* 5090 */     rowVal[2] = s2b("3");
/* 5091 */     rowVal[3] = s2b("");
/* 5092 */     rowVal[4] = s2b("");
/* 5093 */     rowVal[5] = s2b("[(M)] [UNSIGNED] [ZEROFILL]");
/* 5094 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5097 */     rowVal[7] = s2b("false");
/* 5098 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5101 */     rowVal[9] = s2b("true");
/* 5102 */     rowVal[10] = s2b("false");
/* 5103 */     rowVal[11] = s2b("true");
/* 5104 */     rowVal[12] = s2b("TINYINT UNSIGNED");
/* 5105 */     rowVal[13] = s2b("0");
/* 5106 */     rowVal[14] = s2b("0");
/* 5107 */     rowVal[15] = s2b("0");
/* 5108 */     rowVal[16] = s2b("0");
/* 5109 */     rowVal[17] = s2b("10");
/* 5110 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5115 */     rowVal = new byte[18][];
/* 5116 */     rowVal[0] = s2b("BIGINT");
/* 5117 */     rowVal[1] = Integer.toString(-5).getBytes();
/*      */ 
/*      */     
/* 5120 */     rowVal[2] = s2b("19");
/* 5121 */     rowVal[3] = s2b("");
/* 5122 */     rowVal[4] = s2b("");
/* 5123 */     rowVal[5] = s2b("[(M)] [UNSIGNED] [ZEROFILL]");
/* 5124 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5127 */     rowVal[7] = s2b("false");
/* 5128 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5131 */     rowVal[9] = s2b("true");
/* 5132 */     rowVal[10] = s2b("false");
/* 5133 */     rowVal[11] = s2b("true");
/* 5134 */     rowVal[12] = s2b("BIGINT");
/* 5135 */     rowVal[13] = s2b("0");
/* 5136 */     rowVal[14] = s2b("0");
/* 5137 */     rowVal[15] = s2b("0");
/* 5138 */     rowVal[16] = s2b("0");
/* 5139 */     rowVal[17] = s2b("10");
/* 5140 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */     
/* 5142 */     rowVal = new byte[18][];
/* 5143 */     rowVal[0] = s2b("BIGINT UNSIGNED");
/* 5144 */     rowVal[1] = Integer.toString(-5).getBytes();
/*      */ 
/*      */     
/* 5147 */     rowVal[2] = s2b("20");
/* 5148 */     rowVal[3] = s2b("");
/* 5149 */     rowVal[4] = s2b("");
/* 5150 */     rowVal[5] = s2b("[(M)] [ZEROFILL]");
/* 5151 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5154 */     rowVal[7] = s2b("false");
/* 5155 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5158 */     rowVal[9] = s2b("true");
/* 5159 */     rowVal[10] = s2b("false");
/* 5160 */     rowVal[11] = s2b("true");
/* 5161 */     rowVal[12] = s2b("BIGINT UNSIGNED");
/* 5162 */     rowVal[13] = s2b("0");
/* 5163 */     rowVal[14] = s2b("0");
/* 5164 */     rowVal[15] = s2b("0");
/* 5165 */     rowVal[16] = s2b("0");
/* 5166 */     rowVal[17] = s2b("10");
/* 5167 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5172 */     rowVal = new byte[18][];
/* 5173 */     rowVal[0] = s2b("LONG VARBINARY");
/* 5174 */     rowVal[1] = Integer.toString(-4).getBytes();
/*      */ 
/*      */     
/* 5177 */     rowVal[2] = s2b("16777215");
/* 5178 */     rowVal[3] = s2b("'");
/* 5179 */     rowVal[4] = s2b("'");
/* 5180 */     rowVal[5] = s2b("");
/* 5181 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5184 */     rowVal[7] = s2b("true");
/* 5185 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5188 */     rowVal[9] = s2b("false");
/* 5189 */     rowVal[10] = s2b("false");
/* 5190 */     rowVal[11] = s2b("false");
/* 5191 */     rowVal[12] = s2b("LONG VARBINARY");
/* 5192 */     rowVal[13] = s2b("0");
/* 5193 */     rowVal[14] = s2b("0");
/* 5194 */     rowVal[15] = s2b("0");
/* 5195 */     rowVal[16] = s2b("0");
/* 5196 */     rowVal[17] = s2b("10");
/* 5197 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5202 */     rowVal = new byte[18][];
/* 5203 */     rowVal[0] = s2b("MEDIUMBLOB");
/* 5204 */     rowVal[1] = Integer.toString(-4).getBytes();
/*      */ 
/*      */     
/* 5207 */     rowVal[2] = s2b("16777215");
/* 5208 */     rowVal[3] = s2b("'");
/* 5209 */     rowVal[4] = s2b("'");
/* 5210 */     rowVal[5] = s2b("");
/* 5211 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5214 */     rowVal[7] = s2b("true");
/* 5215 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5218 */     rowVal[9] = s2b("false");
/* 5219 */     rowVal[10] = s2b("false");
/* 5220 */     rowVal[11] = s2b("false");
/* 5221 */     rowVal[12] = s2b("MEDIUMBLOB");
/* 5222 */     rowVal[13] = s2b("0");
/* 5223 */     rowVal[14] = s2b("0");
/* 5224 */     rowVal[15] = s2b("0");
/* 5225 */     rowVal[16] = s2b("0");
/* 5226 */     rowVal[17] = s2b("10");
/* 5227 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5232 */     rowVal = new byte[18][];
/* 5233 */     rowVal[0] = s2b("LONGBLOB");
/* 5234 */     rowVal[1] = Integer.toString(-4).getBytes();
/*      */ 
/*      */     
/* 5237 */     rowVal[2] = Integer.toString(2147483647).getBytes();
/*      */ 
/*      */     
/* 5240 */     rowVal[3] = s2b("'");
/* 5241 */     rowVal[4] = s2b("'");
/* 5242 */     rowVal[5] = s2b("");
/* 5243 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5246 */     rowVal[7] = s2b("true");
/* 5247 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5250 */     rowVal[9] = s2b("false");
/* 5251 */     rowVal[10] = s2b("false");
/* 5252 */     rowVal[11] = s2b("false");
/* 5253 */     rowVal[12] = s2b("LONGBLOB");
/* 5254 */     rowVal[13] = s2b("0");
/* 5255 */     rowVal[14] = s2b("0");
/* 5256 */     rowVal[15] = s2b("0");
/* 5257 */     rowVal[16] = s2b("0");
/* 5258 */     rowVal[17] = s2b("10");
/* 5259 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5264 */     rowVal = new byte[18][];
/* 5265 */     rowVal[0] = s2b("BLOB");
/* 5266 */     rowVal[1] = Integer.toString(-4).getBytes();
/*      */ 
/*      */     
/* 5269 */     rowVal[2] = s2b("65535");
/* 5270 */     rowVal[3] = s2b("'");
/* 5271 */     rowVal[4] = s2b("'");
/* 5272 */     rowVal[5] = s2b("");
/* 5273 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5276 */     rowVal[7] = s2b("true");
/* 5277 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5280 */     rowVal[9] = s2b("false");
/* 5281 */     rowVal[10] = s2b("false");
/* 5282 */     rowVal[11] = s2b("false");
/* 5283 */     rowVal[12] = s2b("BLOB");
/* 5284 */     rowVal[13] = s2b("0");
/* 5285 */     rowVal[14] = s2b("0");
/* 5286 */     rowVal[15] = s2b("0");
/* 5287 */     rowVal[16] = s2b("0");
/* 5288 */     rowVal[17] = s2b("10");
/* 5289 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5294 */     rowVal = new byte[18][];
/* 5295 */     rowVal[0] = s2b("TINYBLOB");
/* 5296 */     rowVal[1] = Integer.toString(-4).getBytes();
/*      */ 
/*      */     
/* 5299 */     rowVal[2] = s2b("255");
/* 5300 */     rowVal[3] = s2b("'");
/* 5301 */     rowVal[4] = s2b("'");
/* 5302 */     rowVal[5] = s2b("");
/* 5303 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5306 */     rowVal[7] = s2b("true");
/* 5307 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5310 */     rowVal[9] = s2b("false");
/* 5311 */     rowVal[10] = s2b("false");
/* 5312 */     rowVal[11] = s2b("false");
/* 5313 */     rowVal[12] = s2b("TINYBLOB");
/* 5314 */     rowVal[13] = s2b("0");
/* 5315 */     rowVal[14] = s2b("0");
/* 5316 */     rowVal[15] = s2b("0");
/* 5317 */     rowVal[16] = s2b("0");
/* 5318 */     rowVal[17] = s2b("10");
/* 5319 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5325 */     rowVal = new byte[18][];
/* 5326 */     rowVal[0] = s2b("VARBINARY");
/* 5327 */     rowVal[1] = Integer.toString(-3).getBytes();
/*      */ 
/*      */     
/* 5330 */     rowVal[2] = s2b(this.conn.versionMeetsMinimum(5, 0, 3) ? "65535" : "255");
/* 5331 */     rowVal[3] = s2b("'");
/* 5332 */     rowVal[4] = s2b("'");
/* 5333 */     rowVal[5] = s2b("(M)");
/* 5334 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5337 */     rowVal[7] = s2b("true");
/* 5338 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5341 */     rowVal[9] = s2b("false");
/* 5342 */     rowVal[10] = s2b("false");
/* 5343 */     rowVal[11] = s2b("false");
/* 5344 */     rowVal[12] = s2b("VARBINARY");
/* 5345 */     rowVal[13] = s2b("0");
/* 5346 */     rowVal[14] = s2b("0");
/* 5347 */     rowVal[15] = s2b("0");
/* 5348 */     rowVal[16] = s2b("0");
/* 5349 */     rowVal[17] = s2b("10");
/* 5350 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5356 */     rowVal = new byte[18][];
/* 5357 */     rowVal[0] = s2b("BINARY");
/* 5358 */     rowVal[1] = Integer.toString(-2).getBytes();
/*      */ 
/*      */     
/* 5361 */     rowVal[2] = s2b("255");
/* 5362 */     rowVal[3] = s2b("'");
/* 5363 */     rowVal[4] = s2b("'");
/* 5364 */     rowVal[5] = s2b("(M)");
/* 5365 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5368 */     rowVal[7] = s2b("true");
/* 5369 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5372 */     rowVal[9] = s2b("false");
/* 5373 */     rowVal[10] = s2b("false");
/* 5374 */     rowVal[11] = s2b("false");
/* 5375 */     rowVal[12] = s2b("BINARY");
/* 5376 */     rowVal[13] = s2b("0");
/* 5377 */     rowVal[14] = s2b("0");
/* 5378 */     rowVal[15] = s2b("0");
/* 5379 */     rowVal[16] = s2b("0");
/* 5380 */     rowVal[17] = s2b("10");
/* 5381 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5386 */     rowVal = new byte[18][];
/* 5387 */     rowVal[0] = s2b("LONG VARCHAR");
/* 5388 */     rowVal[1] = Integer.toString(-1).getBytes();
/*      */ 
/*      */     
/* 5391 */     rowVal[2] = s2b("16777215");
/* 5392 */     rowVal[3] = s2b("'");
/* 5393 */     rowVal[4] = s2b("'");
/* 5394 */     rowVal[5] = s2b("");
/* 5395 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5398 */     rowVal[7] = s2b("false");
/* 5399 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5402 */     rowVal[9] = s2b("false");
/* 5403 */     rowVal[10] = s2b("false");
/* 5404 */     rowVal[11] = s2b("false");
/* 5405 */     rowVal[12] = s2b("LONG VARCHAR");
/* 5406 */     rowVal[13] = s2b("0");
/* 5407 */     rowVal[14] = s2b("0");
/* 5408 */     rowVal[15] = s2b("0");
/* 5409 */     rowVal[16] = s2b("0");
/* 5410 */     rowVal[17] = s2b("10");
/* 5411 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5416 */     rowVal = new byte[18][];
/* 5417 */     rowVal[0] = s2b("MEDIUMTEXT");
/* 5418 */     rowVal[1] = Integer.toString(-1).getBytes();
/*      */ 
/*      */     
/* 5421 */     rowVal[2] = s2b("16777215");
/* 5422 */     rowVal[3] = s2b("'");
/* 5423 */     rowVal[4] = s2b("'");
/* 5424 */     rowVal[5] = s2b("");
/* 5425 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5428 */     rowVal[7] = s2b("false");
/* 5429 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5432 */     rowVal[9] = s2b("false");
/* 5433 */     rowVal[10] = s2b("false");
/* 5434 */     rowVal[11] = s2b("false");
/* 5435 */     rowVal[12] = s2b("MEDIUMTEXT");
/* 5436 */     rowVal[13] = s2b("0");
/* 5437 */     rowVal[14] = s2b("0");
/* 5438 */     rowVal[15] = s2b("0");
/* 5439 */     rowVal[16] = s2b("0");
/* 5440 */     rowVal[17] = s2b("10");
/* 5441 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5446 */     rowVal = new byte[18][];
/* 5447 */     rowVal[0] = s2b("LONGTEXT");
/* 5448 */     rowVal[1] = Integer.toString(-1).getBytes();
/*      */ 
/*      */     
/* 5451 */     rowVal[2] = Integer.toString(2147483647).getBytes();
/*      */ 
/*      */     
/* 5454 */     rowVal[3] = s2b("'");
/* 5455 */     rowVal[4] = s2b("'");
/* 5456 */     rowVal[5] = s2b("");
/* 5457 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5460 */     rowVal[7] = s2b("false");
/* 5461 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5464 */     rowVal[9] = s2b("false");
/* 5465 */     rowVal[10] = s2b("false");
/* 5466 */     rowVal[11] = s2b("false");
/* 5467 */     rowVal[12] = s2b("LONGTEXT");
/* 5468 */     rowVal[13] = s2b("0");
/* 5469 */     rowVal[14] = s2b("0");
/* 5470 */     rowVal[15] = s2b("0");
/* 5471 */     rowVal[16] = s2b("0");
/* 5472 */     rowVal[17] = s2b("10");
/* 5473 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5478 */     rowVal = new byte[18][];
/* 5479 */     rowVal[0] = s2b("TEXT");
/* 5480 */     rowVal[1] = Integer.toString(-1).getBytes();
/*      */ 
/*      */     
/* 5483 */     rowVal[2] = s2b("65535");
/* 5484 */     rowVal[3] = s2b("'");
/* 5485 */     rowVal[4] = s2b("'");
/* 5486 */     rowVal[5] = s2b("");
/* 5487 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5490 */     rowVal[7] = s2b("false");
/* 5491 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5494 */     rowVal[9] = s2b("false");
/* 5495 */     rowVal[10] = s2b("false");
/* 5496 */     rowVal[11] = s2b("false");
/* 5497 */     rowVal[12] = s2b("TEXT");
/* 5498 */     rowVal[13] = s2b("0");
/* 5499 */     rowVal[14] = s2b("0");
/* 5500 */     rowVal[15] = s2b("0");
/* 5501 */     rowVal[16] = s2b("0");
/* 5502 */     rowVal[17] = s2b("10");
/* 5503 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5508 */     rowVal = new byte[18][];
/* 5509 */     rowVal[0] = s2b("TINYTEXT");
/* 5510 */     rowVal[1] = Integer.toString(-1).getBytes();
/*      */ 
/*      */     
/* 5513 */     rowVal[2] = s2b("255");
/* 5514 */     rowVal[3] = s2b("'");
/* 5515 */     rowVal[4] = s2b("'");
/* 5516 */     rowVal[5] = s2b("");
/* 5517 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5520 */     rowVal[7] = s2b("false");
/* 5521 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5524 */     rowVal[9] = s2b("false");
/* 5525 */     rowVal[10] = s2b("false");
/* 5526 */     rowVal[11] = s2b("false");
/* 5527 */     rowVal[12] = s2b("TINYTEXT");
/* 5528 */     rowVal[13] = s2b("0");
/* 5529 */     rowVal[14] = s2b("0");
/* 5530 */     rowVal[15] = s2b("0");
/* 5531 */     rowVal[16] = s2b("0");
/* 5532 */     rowVal[17] = s2b("10");
/* 5533 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5538 */     rowVal = new byte[18][];
/* 5539 */     rowVal[0] = s2b("CHAR");
/* 5540 */     rowVal[1] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5543 */     rowVal[2] = s2b("255");
/* 5544 */     rowVal[3] = s2b("'");
/* 5545 */     rowVal[4] = s2b("'");
/* 5546 */     rowVal[5] = s2b("(M)");
/* 5547 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5550 */     rowVal[7] = s2b("false");
/* 5551 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5554 */     rowVal[9] = s2b("false");
/* 5555 */     rowVal[10] = s2b("false");
/* 5556 */     rowVal[11] = s2b("false");
/* 5557 */     rowVal[12] = s2b("CHAR");
/* 5558 */     rowVal[13] = s2b("0");
/* 5559 */     rowVal[14] = s2b("0");
/* 5560 */     rowVal[15] = s2b("0");
/* 5561 */     rowVal[16] = s2b("0");
/* 5562 */     rowVal[17] = s2b("10");
/* 5563 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */     
/* 5567 */     int decimalPrecision = 254;
/*      */     
/* 5569 */     if (this.conn.versionMeetsMinimum(5, 0, 3)) {
/* 5570 */       if (this.conn.versionMeetsMinimum(5, 0, 6)) {
/* 5571 */         decimalPrecision = 65;
/*      */       } else {
/* 5573 */         decimalPrecision = 64;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5580 */     rowVal = new byte[18][];
/* 5581 */     rowVal[0] = s2b("NUMERIC");
/* 5582 */     rowVal[1] = Integer.toString(2).getBytes();
/*      */ 
/*      */     
/* 5585 */     rowVal[2] = s2b(String.valueOf(decimalPrecision));
/* 5586 */     rowVal[3] = s2b("");
/* 5587 */     rowVal[4] = s2b("");
/* 5588 */     rowVal[5] = s2b("[(M[,D])] [ZEROFILL]");
/* 5589 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5592 */     rowVal[7] = s2b("false");
/* 5593 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5596 */     rowVal[9] = s2b("false");
/* 5597 */     rowVal[10] = s2b("false");
/* 5598 */     rowVal[11] = s2b("true");
/* 5599 */     rowVal[12] = s2b("NUMERIC");
/* 5600 */     rowVal[13] = s2b("-308");
/* 5601 */     rowVal[14] = s2b("308");
/* 5602 */     rowVal[15] = s2b("0");
/* 5603 */     rowVal[16] = s2b("0");
/* 5604 */     rowVal[17] = s2b("10");
/* 5605 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5610 */     rowVal = new byte[18][];
/* 5611 */     rowVal[0] = s2b("DECIMAL");
/* 5612 */     rowVal[1] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5615 */     rowVal[2] = s2b(String.valueOf(decimalPrecision));
/* 5616 */     rowVal[3] = s2b("");
/* 5617 */     rowVal[4] = s2b("");
/* 5618 */     rowVal[5] = s2b("[(M[,D])] [ZEROFILL]");
/* 5619 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5622 */     rowVal[7] = s2b("false");
/* 5623 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5626 */     rowVal[9] = s2b("false");
/* 5627 */     rowVal[10] = s2b("false");
/* 5628 */     rowVal[11] = s2b("true");
/* 5629 */     rowVal[12] = s2b("DECIMAL");
/* 5630 */     rowVal[13] = s2b("-308");
/* 5631 */     rowVal[14] = s2b("308");
/* 5632 */     rowVal[15] = s2b("0");
/* 5633 */     rowVal[16] = s2b("0");
/* 5634 */     rowVal[17] = s2b("10");
/* 5635 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5640 */     rowVal = new byte[18][];
/* 5641 */     rowVal[0] = s2b("INTEGER");
/* 5642 */     rowVal[1] = Integer.toString(4).getBytes();
/*      */ 
/*      */     
/* 5645 */     rowVal[2] = s2b("10");
/* 5646 */     rowVal[3] = s2b("");
/* 5647 */     rowVal[4] = s2b("");
/* 5648 */     rowVal[5] = s2b("[(M)] [UNSIGNED] [ZEROFILL]");
/* 5649 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5652 */     rowVal[7] = s2b("false");
/* 5653 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5656 */     rowVal[9] = s2b("true");
/* 5657 */     rowVal[10] = s2b("false");
/* 5658 */     rowVal[11] = s2b("true");
/* 5659 */     rowVal[12] = s2b("INTEGER");
/* 5660 */     rowVal[13] = s2b("0");
/* 5661 */     rowVal[14] = s2b("0");
/* 5662 */     rowVal[15] = s2b("0");
/* 5663 */     rowVal[16] = s2b("0");
/* 5664 */     rowVal[17] = s2b("10");
/* 5665 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */     
/* 5667 */     rowVal = new byte[18][];
/* 5668 */     rowVal[0] = s2b("INTEGER UNSIGNED");
/* 5669 */     rowVal[1] = Integer.toString(4).getBytes();
/*      */ 
/*      */     
/* 5672 */     rowVal[2] = s2b("10");
/* 5673 */     rowVal[3] = s2b("");
/* 5674 */     rowVal[4] = s2b("");
/* 5675 */     rowVal[5] = s2b("[(M)] [ZEROFILL]");
/* 5676 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5679 */     rowVal[7] = s2b("false");
/* 5680 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5683 */     rowVal[9] = s2b("true");
/* 5684 */     rowVal[10] = s2b("false");
/* 5685 */     rowVal[11] = s2b("true");
/* 5686 */     rowVal[12] = s2b("INTEGER UNSIGNED");
/* 5687 */     rowVal[13] = s2b("0");
/* 5688 */     rowVal[14] = s2b("0");
/* 5689 */     rowVal[15] = s2b("0");
/* 5690 */     rowVal[16] = s2b("0");
/* 5691 */     rowVal[17] = s2b("10");
/* 5692 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5697 */     rowVal = new byte[18][];
/* 5698 */     rowVal[0] = s2b("INT");
/* 5699 */     rowVal[1] = Integer.toString(4).getBytes();
/*      */ 
/*      */     
/* 5702 */     rowVal[2] = s2b("10");
/* 5703 */     rowVal[3] = s2b("");
/* 5704 */     rowVal[4] = s2b("");
/* 5705 */     rowVal[5] = s2b("[(M)] [UNSIGNED] [ZEROFILL]");
/* 5706 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5709 */     rowVal[7] = s2b("false");
/* 5710 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5713 */     rowVal[9] = s2b("true");
/* 5714 */     rowVal[10] = s2b("false");
/* 5715 */     rowVal[11] = s2b("true");
/* 5716 */     rowVal[12] = s2b("INT");
/* 5717 */     rowVal[13] = s2b("0");
/* 5718 */     rowVal[14] = s2b("0");
/* 5719 */     rowVal[15] = s2b("0");
/* 5720 */     rowVal[16] = s2b("0");
/* 5721 */     rowVal[17] = s2b("10");
/* 5722 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */     
/* 5724 */     rowVal = new byte[18][];
/* 5725 */     rowVal[0] = s2b("INT UNSIGNED");
/* 5726 */     rowVal[1] = Integer.toString(4).getBytes();
/*      */ 
/*      */     
/* 5729 */     rowVal[2] = s2b("10");
/* 5730 */     rowVal[3] = s2b("");
/* 5731 */     rowVal[4] = s2b("");
/* 5732 */     rowVal[5] = s2b("[(M)] [ZEROFILL]");
/* 5733 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5736 */     rowVal[7] = s2b("false");
/* 5737 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5740 */     rowVal[9] = s2b("true");
/* 5741 */     rowVal[10] = s2b("false");
/* 5742 */     rowVal[11] = s2b("true");
/* 5743 */     rowVal[12] = s2b("INT UNSIGNED");
/* 5744 */     rowVal[13] = s2b("0");
/* 5745 */     rowVal[14] = s2b("0");
/* 5746 */     rowVal[15] = s2b("0");
/* 5747 */     rowVal[16] = s2b("0");
/* 5748 */     rowVal[17] = s2b("10");
/* 5749 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5754 */     rowVal = new byte[18][];
/* 5755 */     rowVal[0] = s2b("MEDIUMINT");
/* 5756 */     rowVal[1] = Integer.toString(4).getBytes();
/*      */ 
/*      */     
/* 5759 */     rowVal[2] = s2b("7");
/* 5760 */     rowVal[3] = s2b("");
/* 5761 */     rowVal[4] = s2b("");
/* 5762 */     rowVal[5] = s2b("[(M)] [UNSIGNED] [ZEROFILL]");
/* 5763 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5766 */     rowVal[7] = s2b("false");
/* 5767 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5770 */     rowVal[9] = s2b("true");
/* 5771 */     rowVal[10] = s2b("false");
/* 5772 */     rowVal[11] = s2b("true");
/* 5773 */     rowVal[12] = s2b("MEDIUMINT");
/* 5774 */     rowVal[13] = s2b("0");
/* 5775 */     rowVal[14] = s2b("0");
/* 5776 */     rowVal[15] = s2b("0");
/* 5777 */     rowVal[16] = s2b("0");
/* 5778 */     rowVal[17] = s2b("10");
/* 5779 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */     
/* 5781 */     rowVal = new byte[18][];
/* 5782 */     rowVal[0] = s2b("MEDIUMINT UNSIGNED");
/* 5783 */     rowVal[1] = Integer.toString(4).getBytes();
/*      */ 
/*      */     
/* 5786 */     rowVal[2] = s2b("8");
/* 5787 */     rowVal[3] = s2b("");
/* 5788 */     rowVal[4] = s2b("");
/* 5789 */     rowVal[5] = s2b("[(M)] [ZEROFILL]");
/* 5790 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5793 */     rowVal[7] = s2b("false");
/* 5794 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5797 */     rowVal[9] = s2b("true");
/* 5798 */     rowVal[10] = s2b("false");
/* 5799 */     rowVal[11] = s2b("true");
/* 5800 */     rowVal[12] = s2b("MEDIUMINT UNSIGNED");
/* 5801 */     rowVal[13] = s2b("0");
/* 5802 */     rowVal[14] = s2b("0");
/* 5803 */     rowVal[15] = s2b("0");
/* 5804 */     rowVal[16] = s2b("0");
/* 5805 */     rowVal[17] = s2b("10");
/* 5806 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5811 */     rowVal = new byte[18][];
/* 5812 */     rowVal[0] = s2b("SMALLINT");
/* 5813 */     rowVal[1] = Integer.toString(5).getBytes();
/*      */ 
/*      */     
/* 5816 */     rowVal[2] = s2b("5");
/* 5817 */     rowVal[3] = s2b("");
/* 5818 */     rowVal[4] = s2b("");
/* 5819 */     rowVal[5] = s2b("[(M)] [UNSIGNED] [ZEROFILL]");
/* 5820 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5823 */     rowVal[7] = s2b("false");
/* 5824 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5827 */     rowVal[9] = s2b("true");
/* 5828 */     rowVal[10] = s2b("false");
/* 5829 */     rowVal[11] = s2b("true");
/* 5830 */     rowVal[12] = s2b("SMALLINT");
/* 5831 */     rowVal[13] = s2b("0");
/* 5832 */     rowVal[14] = s2b("0");
/* 5833 */     rowVal[15] = s2b("0");
/* 5834 */     rowVal[16] = s2b("0");
/* 5835 */     rowVal[17] = s2b("10");
/* 5836 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */     
/* 5838 */     rowVal = new byte[18][];
/* 5839 */     rowVal[0] = s2b("SMALLINT UNSIGNED");
/* 5840 */     rowVal[1] = Integer.toString(5).getBytes();
/*      */ 
/*      */     
/* 5843 */     rowVal[2] = s2b("5");
/* 5844 */     rowVal[3] = s2b("");
/* 5845 */     rowVal[4] = s2b("");
/* 5846 */     rowVal[5] = s2b("[(M)] [ZEROFILL]");
/* 5847 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5850 */     rowVal[7] = s2b("false");
/* 5851 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5854 */     rowVal[9] = s2b("true");
/* 5855 */     rowVal[10] = s2b("false");
/* 5856 */     rowVal[11] = s2b("true");
/* 5857 */     rowVal[12] = s2b("SMALLINT UNSIGNED");
/* 5858 */     rowVal[13] = s2b("0");
/* 5859 */     rowVal[14] = s2b("0");
/* 5860 */     rowVal[15] = s2b("0");
/* 5861 */     rowVal[16] = s2b("0");
/* 5862 */     rowVal[17] = s2b("10");
/* 5863 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5869 */     rowVal = new byte[18][];
/* 5870 */     rowVal[0] = s2b("FLOAT");
/* 5871 */     rowVal[1] = Integer.toString(7).getBytes();
/*      */ 
/*      */     
/* 5874 */     rowVal[2] = s2b("10");
/* 5875 */     rowVal[3] = s2b("");
/* 5876 */     rowVal[4] = s2b("");
/* 5877 */     rowVal[5] = s2b("[(M,D)] [ZEROFILL]");
/* 5878 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5881 */     rowVal[7] = s2b("false");
/* 5882 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5885 */     rowVal[9] = s2b("false");
/* 5886 */     rowVal[10] = s2b("false");
/* 5887 */     rowVal[11] = s2b("true");
/* 5888 */     rowVal[12] = s2b("FLOAT");
/* 5889 */     rowVal[13] = s2b("-38");
/* 5890 */     rowVal[14] = s2b("38");
/* 5891 */     rowVal[15] = s2b("0");
/* 5892 */     rowVal[16] = s2b("0");
/* 5893 */     rowVal[17] = s2b("10");
/* 5894 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5899 */     rowVal = new byte[18][];
/* 5900 */     rowVal[0] = s2b("DOUBLE");
/* 5901 */     rowVal[1] = Integer.toString(8).getBytes();
/*      */ 
/*      */     
/* 5904 */     rowVal[2] = s2b("17");
/* 5905 */     rowVal[3] = s2b("");
/* 5906 */     rowVal[4] = s2b("");
/* 5907 */     rowVal[5] = s2b("[(M,D)] [ZEROFILL]");
/* 5908 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5911 */     rowVal[7] = s2b("false");
/* 5912 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5915 */     rowVal[9] = s2b("false");
/* 5916 */     rowVal[10] = s2b("false");
/* 5917 */     rowVal[11] = s2b("true");
/* 5918 */     rowVal[12] = s2b("DOUBLE");
/* 5919 */     rowVal[13] = s2b("-308");
/* 5920 */     rowVal[14] = s2b("308");
/* 5921 */     rowVal[15] = s2b("0");
/* 5922 */     rowVal[16] = s2b("0");
/* 5923 */     rowVal[17] = s2b("10");
/* 5924 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5929 */     rowVal = new byte[18][];
/* 5930 */     rowVal[0] = s2b("DOUBLE PRECISION");
/* 5931 */     rowVal[1] = Integer.toString(8).getBytes();
/*      */ 
/*      */     
/* 5934 */     rowVal[2] = s2b("17");
/* 5935 */     rowVal[3] = s2b("");
/* 5936 */     rowVal[4] = s2b("");
/* 5937 */     rowVal[5] = s2b("[(M,D)] [ZEROFILL]");
/* 5938 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5941 */     rowVal[7] = s2b("false");
/* 5942 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5945 */     rowVal[9] = s2b("false");
/* 5946 */     rowVal[10] = s2b("false");
/* 5947 */     rowVal[11] = s2b("true");
/* 5948 */     rowVal[12] = s2b("DOUBLE PRECISION");
/* 5949 */     rowVal[13] = s2b("-308");
/* 5950 */     rowVal[14] = s2b("308");
/* 5951 */     rowVal[15] = s2b("0");
/* 5952 */     rowVal[16] = s2b("0");
/* 5953 */     rowVal[17] = s2b("10");
/* 5954 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5959 */     rowVal = new byte[18][];
/* 5960 */     rowVal[0] = s2b("REAL");
/* 5961 */     rowVal[1] = Integer.toString(8).getBytes();
/*      */ 
/*      */     
/* 5964 */     rowVal[2] = s2b("17");
/* 5965 */     rowVal[3] = s2b("");
/* 5966 */     rowVal[4] = s2b("");
/* 5967 */     rowVal[5] = s2b("[(M,D)] [ZEROFILL]");
/* 5968 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 5971 */     rowVal[7] = s2b("false");
/* 5972 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 5975 */     rowVal[9] = s2b("false");
/* 5976 */     rowVal[10] = s2b("false");
/* 5977 */     rowVal[11] = s2b("true");
/* 5978 */     rowVal[12] = s2b("REAL");
/* 5979 */     rowVal[13] = s2b("-308");
/* 5980 */     rowVal[14] = s2b("308");
/* 5981 */     rowVal[15] = s2b("0");
/* 5982 */     rowVal[16] = s2b("0");
/* 5983 */     rowVal[17] = s2b("10");
/* 5984 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5989 */     rowVal = new byte[18][];
/* 5990 */     rowVal[0] = s2b("VARCHAR");
/* 5991 */     rowVal[1] = Integer.toString(12).getBytes();
/*      */ 
/*      */     
/* 5994 */     rowVal[2] = s2b(this.conn.versionMeetsMinimum(5, 0, 3) ? "65535" : "255");
/* 5995 */     rowVal[3] = s2b("'");
/* 5996 */     rowVal[4] = s2b("'");
/* 5997 */     rowVal[5] = s2b("(M)");
/* 5998 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 6001 */     rowVal[7] = s2b("false");
/* 6002 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 6005 */     rowVal[9] = s2b("false");
/* 6006 */     rowVal[10] = s2b("false");
/* 6007 */     rowVal[11] = s2b("false");
/* 6008 */     rowVal[12] = s2b("VARCHAR");
/* 6009 */     rowVal[13] = s2b("0");
/* 6010 */     rowVal[14] = s2b("0");
/* 6011 */     rowVal[15] = s2b("0");
/* 6012 */     rowVal[16] = s2b("0");
/* 6013 */     rowVal[17] = s2b("10");
/* 6014 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6019 */     rowVal = new byte[18][];
/* 6020 */     rowVal[0] = s2b("ENUM");
/* 6021 */     rowVal[1] = Integer.toString(12).getBytes();
/*      */ 
/*      */     
/* 6024 */     rowVal[2] = s2b("65535");
/* 6025 */     rowVal[3] = s2b("'");
/* 6026 */     rowVal[4] = s2b("'");
/* 6027 */     rowVal[5] = s2b("");
/* 6028 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 6031 */     rowVal[7] = s2b("false");
/* 6032 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 6035 */     rowVal[9] = s2b("false");
/* 6036 */     rowVal[10] = s2b("false");
/* 6037 */     rowVal[11] = s2b("false");
/* 6038 */     rowVal[12] = s2b("ENUM");
/* 6039 */     rowVal[13] = s2b("0");
/* 6040 */     rowVal[14] = s2b("0");
/* 6041 */     rowVal[15] = s2b("0");
/* 6042 */     rowVal[16] = s2b("0");
/* 6043 */     rowVal[17] = s2b("10");
/* 6044 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6049 */     rowVal = new byte[18][];
/* 6050 */     rowVal[0] = s2b("SET");
/* 6051 */     rowVal[1] = Integer.toString(12).getBytes();
/*      */ 
/*      */     
/* 6054 */     rowVal[2] = s2b("64");
/* 6055 */     rowVal[3] = s2b("'");
/* 6056 */     rowVal[4] = s2b("'");
/* 6057 */     rowVal[5] = s2b("");
/* 6058 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 6061 */     rowVal[7] = s2b("false");
/* 6062 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 6065 */     rowVal[9] = s2b("false");
/* 6066 */     rowVal[10] = s2b("false");
/* 6067 */     rowVal[11] = s2b("false");
/* 6068 */     rowVal[12] = s2b("SET");
/* 6069 */     rowVal[13] = s2b("0");
/* 6070 */     rowVal[14] = s2b("0");
/* 6071 */     rowVal[15] = s2b("0");
/* 6072 */     rowVal[16] = s2b("0");
/* 6073 */     rowVal[17] = s2b("10");
/* 6074 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6079 */     rowVal = new byte[18][];
/* 6080 */     rowVal[0] = s2b("DATE");
/* 6081 */     rowVal[1] = Integer.toString(91).getBytes();
/*      */ 
/*      */     
/* 6084 */     rowVal[2] = s2b("0");
/* 6085 */     rowVal[3] = s2b("'");
/* 6086 */     rowVal[4] = s2b("'");
/* 6087 */     rowVal[5] = s2b("");
/* 6088 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 6091 */     rowVal[7] = s2b("false");
/* 6092 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 6095 */     rowVal[9] = s2b("false");
/* 6096 */     rowVal[10] = s2b("false");
/* 6097 */     rowVal[11] = s2b("false");
/* 6098 */     rowVal[12] = s2b("DATE");
/* 6099 */     rowVal[13] = s2b("0");
/* 6100 */     rowVal[14] = s2b("0");
/* 6101 */     rowVal[15] = s2b("0");
/* 6102 */     rowVal[16] = s2b("0");
/* 6103 */     rowVal[17] = s2b("10");
/* 6104 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6109 */     rowVal = new byte[18][];
/* 6110 */     rowVal[0] = s2b("TIME");
/* 6111 */     rowVal[1] = Integer.toString(92).getBytes();
/*      */ 
/*      */     
/* 6114 */     rowVal[2] = s2b("0");
/* 6115 */     rowVal[3] = s2b("'");
/* 6116 */     rowVal[4] = s2b("'");
/* 6117 */     rowVal[5] = s2b("");
/* 6118 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 6121 */     rowVal[7] = s2b("false");
/* 6122 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 6125 */     rowVal[9] = s2b("false");
/* 6126 */     rowVal[10] = s2b("false");
/* 6127 */     rowVal[11] = s2b("false");
/* 6128 */     rowVal[12] = s2b("TIME");
/* 6129 */     rowVal[13] = s2b("0");
/* 6130 */     rowVal[14] = s2b("0");
/* 6131 */     rowVal[15] = s2b("0");
/* 6132 */     rowVal[16] = s2b("0");
/* 6133 */     rowVal[17] = s2b("10");
/* 6134 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6139 */     rowVal = new byte[18][];
/* 6140 */     rowVal[0] = s2b("DATETIME");
/* 6141 */     rowVal[1] = Integer.toString(93).getBytes();
/*      */ 
/*      */     
/* 6144 */     rowVal[2] = s2b("0");
/* 6145 */     rowVal[3] = s2b("'");
/* 6146 */     rowVal[4] = s2b("'");
/* 6147 */     rowVal[5] = s2b("");
/* 6148 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 6151 */     rowVal[7] = s2b("false");
/* 6152 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 6155 */     rowVal[9] = s2b("false");
/* 6156 */     rowVal[10] = s2b("false");
/* 6157 */     rowVal[11] = s2b("false");
/* 6158 */     rowVal[12] = s2b("DATETIME");
/* 6159 */     rowVal[13] = s2b("0");
/* 6160 */     rowVal[14] = s2b("0");
/* 6161 */     rowVal[15] = s2b("0");
/* 6162 */     rowVal[16] = s2b("0");
/* 6163 */     rowVal[17] = s2b("10");
/* 6164 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6169 */     rowVal = new byte[18][];
/* 6170 */     rowVal[0] = s2b("TIMESTAMP");
/* 6171 */     rowVal[1] = Integer.toString(93).getBytes();
/*      */ 
/*      */     
/* 6174 */     rowVal[2] = s2b("0");
/* 6175 */     rowVal[3] = s2b("'");
/* 6176 */     rowVal[4] = s2b("'");
/* 6177 */     rowVal[5] = s2b("[(M)]");
/* 6178 */     rowVal[6] = Integer.toString(1).getBytes();
/*      */ 
/*      */     
/* 6181 */     rowVal[7] = s2b("false");
/* 6182 */     rowVal[8] = Integer.toString(3).getBytes();
/*      */ 
/*      */     
/* 6185 */     rowVal[9] = s2b("false");
/* 6186 */     rowVal[10] = s2b("false");
/* 6187 */     rowVal[11] = s2b("false");
/* 6188 */     rowVal[12] = s2b("TIMESTAMP");
/* 6189 */     rowVal[13] = s2b("0");
/* 6190 */     rowVal[14] = s2b("0");
/* 6191 */     rowVal[15] = s2b("0");
/* 6192 */     rowVal[16] = s2b("0");
/* 6193 */     rowVal[17] = s2b("10");
/* 6194 */     tuples.add(new ByteArrayRow(rowVal, getExceptionInterceptor()));
/*      */     
/* 6196 */     return buildResultSet(fields, tuples);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
/* 6237 */     Field[] fields = new Field[7];
/* 6238 */     fields[0] = new Field("", "TYPE_CAT", 12, 32);
/* 6239 */     fields[1] = new Field("", "TYPE_SCHEM", 12, 32);
/* 6240 */     fields[2] = new Field("", "TYPE_NAME", 12, 32);
/* 6241 */     fields[3] = new Field("", "CLASS_NAME", 12, 32);
/* 6242 */     fields[4] = new Field("", "DATA_TYPE", 4, 10);
/* 6243 */     fields[5] = new Field("", "REMARKS", 12, 32);
/* 6244 */     fields[6] = new Field("", "BASE_TYPE", 5, 10);
/*      */     
/* 6246 */     ArrayList<ResultSetRow> tuples = new ArrayList<ResultSetRow>();
/*      */     
/* 6248 */     return buildResultSet(fields, tuples);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getURL() throws SQLException {
/* 6258 */     return this.conn.getURL();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUserName() throws SQLException {
/* 6268 */     if (this.conn.getUseHostsInPrivileges()) {
/* 6269 */       Statement stmt = null;
/* 6270 */       ResultSet rs = null;
/*      */       
/*      */       try {
/* 6273 */         stmt = this.conn.getMetadataSafeStatement();
/*      */         
/* 6275 */         rs = stmt.executeQuery("SELECT USER()");
/* 6276 */         rs.next();
/*      */         
/* 6278 */         return rs.getString(1);
/*      */       } finally {
/* 6280 */         if (rs != null) {
/*      */           try {
/* 6282 */             rs.close();
/* 6283 */           } catch (Exception ex) {
/* 6284 */             AssertionFailedException.shouldNotHappen(ex);
/*      */           } 
/*      */           
/* 6287 */           rs = null;
/*      */         } 
/*      */         
/* 6290 */         if (stmt != null) {
/*      */           try {
/* 6292 */             stmt.close();
/* 6293 */           } catch (Exception ex) {
/* 6294 */             AssertionFailedException.shouldNotHappen(ex);
/*      */           } 
/*      */           
/* 6297 */           stmt = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 6302 */     return this.conn.getUser();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getVersionColumns(String catalog, String schema, final String table) throws SQLException {
/* 6339 */     if (table == null) {
/* 6340 */       throw SQLError.createSQLException("Table not specified.", "S1009", getExceptionInterceptor());
/*      */     }
/*      */     
/* 6343 */     Field[] fields = new Field[8];
/* 6344 */     fields[0] = new Field("", "SCOPE", 5, 5);
/* 6345 */     fields[1] = new Field("", "COLUMN_NAME", 1, 32);
/* 6346 */     fields[2] = new Field("", "DATA_TYPE", 4, 5);
/* 6347 */     fields[3] = new Field("", "TYPE_NAME", 1, 16);
/* 6348 */     fields[4] = new Field("", "COLUMN_SIZE", 4, 16);
/* 6349 */     fields[5] = new Field("", "BUFFER_LENGTH", 4, 16);
/* 6350 */     fields[6] = new Field("", "DECIMAL_DIGITS", 5, 16);
/* 6351 */     fields[7] = new Field("", "PSEUDO_COLUMN", 5, 5);
/*      */     
/* 6353 */     final ArrayList<ResultSetRow> rows = new ArrayList<ResultSetRow>();
/*      */     
/* 6355 */     final Statement stmt = this.conn.getMetadataSafeStatement();
/*      */ 
/*      */     
/*      */     try {
/* 6359 */       (new IterateBlock<String>(getCatalogIterator(catalog))
/*      */         {
/*      */           void forEach(String catalogStr) throws SQLException
/*      */           {
/* 6363 */             ResultSet results = null;
/* 6364 */             boolean with_where = DatabaseMetaData.this.conn.versionMeetsMinimum(5, 0, 0);
/*      */             
/*      */             try {
/* 6367 */               StringBuilder whereBuf = new StringBuilder(" Extra LIKE '%on update CURRENT_TIMESTAMP%'");
/* 6368 */               List<String> rsFields = new ArrayList<String>();
/*      */ 
/*      */ 
/*      */               
/* 6372 */               if (!DatabaseMetaData.this.conn.versionMeetsMinimum(5, 1, 23)) {
/*      */                 
/* 6374 */                 whereBuf = new StringBuilder();
/* 6375 */                 boolean firstTime = true;
/*      */                 
/* 6377 */                 String query = "SHOW CREATE TABLE " + DatabaseMetaData.this.getFullyQualifiedName(catalogStr, table);
/*      */                 
/* 6379 */                 results = stmt.executeQuery(query);
/* 6380 */                 while (results.next()) {
/* 6381 */                   String createTableString = results.getString(2);
/* 6382 */                   StringTokenizer lineTokenizer = new StringTokenizer(createTableString, "\n");
/*      */                   
/* 6384 */                   while (lineTokenizer.hasMoreTokens()) {
/* 6385 */                     String line = lineTokenizer.nextToken().trim();
/* 6386 */                     if (StringUtils.indexOfIgnoreCase(line, "on update CURRENT_TIMESTAMP") > -1) {
/* 6387 */                       boolean usingBackTicks = true;
/* 6388 */                       int beginPos = line.indexOf(DatabaseMetaData.this.quotedId);
/*      */                       
/* 6390 */                       if (beginPos == -1) {
/* 6391 */                         beginPos = line.indexOf("\"");
/* 6392 */                         usingBackTicks = false;
/*      */                       } 
/*      */                       
/* 6395 */                       if (beginPos != -1) {
/* 6396 */                         int endPos = -1;
/*      */                         
/* 6398 */                         if (usingBackTicks) {
/* 6399 */                           endPos = line.indexOf(DatabaseMetaData.this.quotedId, beginPos + 1);
/*      */                         } else {
/* 6401 */                           endPos = line.indexOf("\"", beginPos + 1);
/*      */                         } 
/*      */                         
/* 6404 */                         if (endPos != -1) {
/* 6405 */                           if (with_where) {
/* 6406 */                             if (!firstTime) {
/* 6407 */                               whereBuf.append(" or");
/*      */                             } else {
/* 6409 */                               firstTime = false;
/*      */                             } 
/* 6411 */                             whereBuf.append(" Field='");
/* 6412 */                             whereBuf.append(line.substring(beginPos + 1, endPos));
/* 6413 */                             whereBuf.append("'"); continue;
/*      */                           } 
/* 6415 */                           rsFields.add(line.substring(beginPos + 1, endPos));
/*      */                         } 
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */ 
/*      */               
/* 6424 */               if (whereBuf.length() > 0 || rsFields.size() > 0) {
/* 6425 */                 StringBuilder queryBuf = new StringBuilder("SHOW COLUMNS FROM ");
/* 6426 */                 queryBuf.append(StringUtils.quoteIdentifier(table, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()));
/* 6427 */                 queryBuf.append(" FROM ");
/* 6428 */                 queryBuf.append(StringUtils.quoteIdentifier(catalogStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.conn.getPedantic()));
/* 6429 */                 if (with_where) {
/* 6430 */                   queryBuf.append(" WHERE");
/* 6431 */                   queryBuf.append(whereBuf.toString());
/*      */                 } 
/*      */                 
/* 6434 */                 results = stmt.executeQuery(queryBuf.toString());
/*      */                 
/* 6436 */                 while (results.next()) {
/* 6437 */                   if (with_where || rsFields.contains(results.getString("Field"))) {
/* 6438 */                     DatabaseMetaData.TypeDescriptor typeDesc = new DatabaseMetaData.TypeDescriptor(results.getString("Type"), results.getString("Null"));
/* 6439 */                     byte[][] rowVal = new byte[8][];
/*      */                     
/* 6441 */                     rowVal[0] = null;
/*      */                     
/* 6443 */                     rowVal[1] = results.getBytes("Field");
/*      */                     
/* 6445 */                     rowVal[2] = Short.toString(typeDesc.dataType).getBytes();
/*      */                     
/* 6447 */                     rowVal[3] = DatabaseMetaData.this.s2b(typeDesc.typeName);
/*      */                     
/* 6449 */                     rowVal[4] = (typeDesc.columnSize == null) ? null : DatabaseMetaData.this.s2b(typeDesc.columnSize.toString());
/*      */                     
/* 6451 */                     rowVal[5] = DatabaseMetaData.this.s2b(Integer.toString(typeDesc.bufferLength));
/*      */                     
/* 6453 */                     rowVal[6] = (typeDesc.decimalDigits == null) ? null : DatabaseMetaData.this.s2b(typeDesc.decimalDigits.toString());
/*      */                     
/* 6455 */                     rowVal[7] = Integer.toString(1).getBytes();
/*      */                     
/* 6457 */                     rows.add(new ByteArrayRow(rowVal, DatabaseMetaData.this.getExceptionInterceptor()));
/*      */                   } 
/*      */                 } 
/*      */               } 
/* 6461 */             } catch (SQLException sqlEx) {
/* 6462 */               if (!"42S02".equals(sqlEx.getSQLState())) {
/* 6463 */                 throw sqlEx;
/*      */               }
/*      */             } finally {
/* 6466 */               if (results != null) {
/*      */                 try {
/* 6468 */                   results.close();
/* 6469 */                 } catch (Exception ex) {}
/*      */ 
/*      */                 
/* 6472 */                 results = null;
/*      */               } 
/*      */             } 
/*      */           }
/*      */         }).doForAll();
/*      */     } finally {
/*      */       
/* 6479 */       if (stmt != null) {
/* 6480 */         stmt.close();
/*      */       }
/*      */     } 
/*      */     
/* 6484 */     return buildResultSet(fields, rows);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean insertsAreDetected(int type) throws SQLException {
/* 6498 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCatalogAtStart() throws SQLException {
/* 6509 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isReadOnly() throws SQLException {
/* 6519 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean locatorsUpdateCopy() throws SQLException {
/* 6526 */     return !this.conn.getEmulateLocators();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean nullPlusNonNullIsNull() throws SQLException {
/* 6537 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean nullsAreSortedAtEnd() throws SQLException {
/* 6547 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean nullsAreSortedAtStart() throws SQLException {
/* 6557 */     return (this.conn.versionMeetsMinimum(4, 0, 2) && !this.conn.versionMeetsMinimum(4, 0, 11));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean nullsAreSortedHigh() throws SQLException {
/* 6567 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean nullsAreSortedLow() throws SQLException {
/* 6577 */     return !nullsAreSortedHigh();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean othersDeletesAreVisible(int type) throws SQLException {
/* 6585 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean othersInsertsAreVisible(int type) throws SQLException {
/* 6593 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean othersUpdatesAreVisible(int type) throws SQLException {
/* 6606 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean ownDeletesAreVisible(int type) throws SQLException {
/* 6614 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean ownInsertsAreVisible(int type) throws SQLException {
/* 6622 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean ownUpdatesAreVisible(int type) throws SQLException {
/* 6635 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LocalAndReferencedColumns parseTableStatusIntoLocalAndReferencedColumns(String keysComment) throws SQLException {
/* 6650 */     String columnsDelimitter = ",";
/*      */     
/* 6652 */     int indexOfOpenParenLocalColumns = StringUtils.indexOfIgnoreCase(0, keysComment, "(", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
/*      */     
/* 6654 */     if (indexOfOpenParenLocalColumns == -1) {
/* 6655 */       throw SQLError.createSQLException("Error parsing foreign keys definition, couldn't find start of local columns list.", "S1000", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 6659 */     String constraintName = StringUtils.unQuoteIdentifier(keysComment.substring(0, indexOfOpenParenLocalColumns).trim(), this.quotedId);
/* 6660 */     keysComment = keysComment.substring(indexOfOpenParenLocalColumns, keysComment.length());
/*      */     
/* 6662 */     String keysCommentTrimmed = keysComment.trim();
/*      */     
/* 6664 */     int indexOfCloseParenLocalColumns = StringUtils.indexOfIgnoreCase(0, keysCommentTrimmed, ")", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
/*      */ 
/*      */     
/* 6667 */     if (indexOfCloseParenLocalColumns == -1) {
/* 6668 */       throw SQLError.createSQLException("Error parsing foreign keys definition, couldn't find end of local columns list.", "S1000", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 6672 */     String localColumnNamesString = keysCommentTrimmed.substring(1, indexOfCloseParenLocalColumns);
/*      */     
/* 6674 */     int indexOfRefer = StringUtils.indexOfIgnoreCase(0, keysCommentTrimmed, "REFER ", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
/*      */     
/* 6676 */     if (indexOfRefer == -1) {
/* 6677 */       throw SQLError.createSQLException("Error parsing foreign keys definition, couldn't find start of referenced tables list.", "S1000", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 6681 */     int indexOfOpenParenReferCol = StringUtils.indexOfIgnoreCase(indexOfRefer, keysCommentTrimmed, "(", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__MRK_COM_WS);
/*      */ 
/*      */     
/* 6684 */     if (indexOfOpenParenReferCol == -1) {
/* 6685 */       throw SQLError.createSQLException("Error parsing foreign keys definition, couldn't find start of referenced columns list.", "S1000", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 6689 */     String referCatalogTableString = keysCommentTrimmed.substring(indexOfRefer + "REFER ".length(), indexOfOpenParenReferCol);
/*      */     
/* 6691 */     int indexOfSlash = StringUtils.indexOfIgnoreCase(0, referCatalogTableString, "/", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__MRK_COM_WS);
/*      */     
/* 6693 */     if (indexOfSlash == -1) {
/* 6694 */       throw SQLError.createSQLException("Error parsing foreign keys definition, couldn't find name of referenced catalog.", "S1000", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 6698 */     String referCatalog = StringUtils.unQuoteIdentifier(referCatalogTableString.substring(0, indexOfSlash), this.quotedId);
/* 6699 */     String referTable = StringUtils.unQuoteIdentifier(referCatalogTableString.substring(indexOfSlash + 1).trim(), this.quotedId);
/*      */     
/* 6701 */     int indexOfCloseParenRefer = StringUtils.indexOfIgnoreCase(indexOfOpenParenReferCol, keysCommentTrimmed, ")", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
/*      */ 
/*      */     
/* 6704 */     if (indexOfCloseParenRefer == -1) {
/* 6705 */       throw SQLError.createSQLException("Error parsing foreign keys definition, couldn't find end of referenced columns list.", "S1000", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 6709 */     String referColumnNamesString = keysCommentTrimmed.substring(indexOfOpenParenReferCol + 1, indexOfCloseParenRefer);
/*      */     
/* 6711 */     List<String> referColumnsList = StringUtils.split(referColumnNamesString, columnsDelimitter, this.quotedId, this.quotedId, false);
/* 6712 */     List<String> localColumnsList = StringUtils.split(localColumnNamesString, columnsDelimitter, this.quotedId, this.quotedId, false);
/*      */     
/* 6714 */     return new LocalAndReferencedColumns(localColumnsList, referColumnsList, constraintName, referCatalog, referTable);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected byte[] s2b(String s) throws SQLException {
/* 6724 */     if (s == null) {
/* 6725 */       return null;
/*      */     }
/*      */     
/* 6728 */     return StringUtils.getBytes(s, this.conn.getCharacterSetMetadata(), this.conn.getServerCharset(), this.conn.parserKnowsUnicode(), this.conn, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean storesLowerCaseIdentifiers() throws SQLException {
/* 6740 */     return this.conn.storesLowerCaseTableName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
/* 6751 */     return this.conn.storesLowerCaseTableName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean storesMixedCaseIdentifiers() throws SQLException {
/* 6762 */     return !this.conn.storesLowerCaseTableName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
/* 6773 */     return !this.conn.storesLowerCaseTableName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean storesUpperCaseIdentifiers() throws SQLException {
/* 6784 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
/* 6795 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsAlterTableWithAddColumn() throws SQLException {
/* 6805 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsAlterTableWithDropColumn() throws SQLException {
/* 6815 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsANSI92EntryLevelSQL() throws SQLException {
/* 6826 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsANSI92FullSQL() throws SQLException {
/* 6836 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsANSI92IntermediateSQL() throws SQLException {
/* 6846 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsBatchUpdates() throws SQLException {
/* 6856 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsCatalogsInDataManipulation() throws SQLException {
/* 6867 */     return this.conn.versionMeetsMinimum(3, 22, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
/* 6878 */     return this.conn.versionMeetsMinimum(3, 22, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
/* 6889 */     return this.conn.versionMeetsMinimum(3, 22, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsCatalogsInProcedureCalls() throws SQLException {
/* 6900 */     return this.conn.versionMeetsMinimum(3, 22, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsCatalogsInTableDefinitions() throws SQLException {
/* 6911 */     return this.conn.versionMeetsMinimum(3, 22, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsColumnAliasing() throws SQLException {
/* 6925 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsConvert() throws SQLException {
/* 6935 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsConvert(int fromType, int toType) throws SQLException {
/* 6951 */     switch (fromType) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case -4:
/*      */       case -3:
/*      */       case -2:
/*      */       case -1:
/*      */       case 1:
/*      */       case 12:
/* 6962 */         switch (toType) {
/*      */           case -6:
/*      */           case -5:
/*      */           case -4:
/*      */           case -3:
/*      */           case -2:
/*      */           case -1:
/*      */           case 1:
/*      */           case 2:
/*      */           case 3:
/*      */           case 4:
/*      */           case 5:
/*      */           case 6:
/*      */           case 7:
/*      */           case 8:
/*      */           case 12:
/*      */           case 91:
/*      */           case 92:
/*      */           case 93:
/*      */           case 1111:
/* 6982 */             return true;
/*      */         } 
/*      */         
/* 6985 */         return false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case -7:
/* 6992 */         return false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case -6:
/*      */       case -5:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/* 7007 */         switch (toType) {
/*      */           case -6:
/*      */           case -5:
/*      */           case -4:
/*      */           case -3:
/*      */           case -2:
/*      */           case -1:
/*      */           case 1:
/*      */           case 2:
/*      */           case 3:
/*      */           case 4:
/*      */           case 5:
/*      */           case 6:
/*      */           case 7:
/*      */           case 8:
/*      */           case 12:
/* 7023 */             return true;
/*      */         } 
/*      */         
/* 7026 */         return false;
/*      */ 
/*      */ 
/*      */       
/*      */       case 0:
/* 7031 */         return false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1111:
/* 7038 */         switch (toType) {
/*      */           case -4:
/*      */           case -3:
/*      */           case -2:
/*      */           case -1:
/*      */           case 1:
/*      */           case 12:
/* 7045 */             return true;
/*      */         } 
/*      */         
/* 7048 */         return false;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 91:
/* 7054 */         switch (toType) {
/*      */           case -4:
/*      */           case -3:
/*      */           case -2:
/*      */           case -1:
/*      */           case 1:
/*      */           case 12:
/* 7061 */             return true;
/*      */         } 
/*      */         
/* 7064 */         return false;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 92:
/* 7070 */         switch (toType) {
/*      */           case -4:
/*      */           case -3:
/*      */           case -2:
/*      */           case -1:
/*      */           case 1:
/*      */           case 12:
/* 7077 */             return true;
/*      */         } 
/*      */         
/* 7080 */         return false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 93:
/* 7088 */         switch (toType) {
/*      */           case -4:
/*      */           case -3:
/*      */           case -2:
/*      */           case -1:
/*      */           case 1:
/*      */           case 12:
/*      */           case 91:
/*      */           case 92:
/* 7097 */             return true;
/*      */         } 
/*      */         
/* 7100 */         return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 7105 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsCoreSQLGrammar() throws SQLException {
/* 7116 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsCorrelatedSubqueries() throws SQLException {
/* 7127 */     return this.conn.versionMeetsMinimum(4, 1, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
/* 7138 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
/* 7148 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsDifferentTableCorrelationNames() throws SQLException {
/* 7160 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsExpressionsInOrderBy() throws SQLException {
/* 7170 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsExtendedSQLGrammar() throws SQLException {
/* 7180 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsFullOuterJoins() throws SQLException {
/* 7190 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsGetGeneratedKeys() {
/* 7197 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsGroupBy() throws SQLException {
/* 7207 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsGroupByBeyondSelect() throws SQLException {
/* 7218 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsGroupByUnrelated() throws SQLException {
/* 7228 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsIntegrityEnhancementFacility() throws SQLException {
/* 7238 */     if (!this.conn.getOverrideSupportsIntegrityEnhancementFacility()) {
/* 7239 */       return false;
/*      */     }
/*      */     
/* 7242 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsLikeEscapeClause() throws SQLException {
/* 7253 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsLimitedOuterJoins() throws SQLException {
/* 7264 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsMinimumSQLGrammar() throws SQLException {
/* 7275 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsMixedCaseIdentifiers() throws SQLException {
/* 7285 */     return !this.conn.lowerCaseTableNames();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
/* 7296 */     return !this.conn.lowerCaseTableNames();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsMultipleOpenResults() throws SQLException {
/* 7303 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsMultipleResultSets() throws SQLException {
/* 7313 */     return this.conn.versionMeetsMinimum(4, 1, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsMultipleTransactions() throws SQLException {
/* 7324 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsNamedParameters() throws SQLException {
/* 7331 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsNonNullableColumns() throws SQLException {
/* 7342 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
/* 7354 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
/* 7366 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
/* 7378 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
/* 7390 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsOrderByUnrelated() throws SQLException {
/* 7400 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsOuterJoins() throws SQLException {
/* 7410 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsPositionedDelete() throws SQLException {
/* 7420 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsPositionedUpdate() throws SQLException {
/* 7430 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
/* 7447 */     switch (type) {
/*      */       case 1004:
/* 7449 */         if (concurrency == 1007 || concurrency == 1008) {
/* 7450 */           return true;
/*      */         }
/* 7452 */         throw SQLError.createSQLException("Illegal arguments to supportsResultSetConcurrency()", "S1009", getExceptionInterceptor());
/*      */ 
/*      */       
/*      */       case 1003:
/* 7456 */         if (concurrency == 1007 || concurrency == 1008) {
/* 7457 */           return true;
/*      */         }
/* 7459 */         throw SQLError.createSQLException("Illegal arguments to supportsResultSetConcurrency()", "S1009", getExceptionInterceptor());
/*      */ 
/*      */       
/*      */       case 1005:
/* 7463 */         return false;
/*      */     } 
/* 7465 */     throw SQLError.createSQLException("Illegal arguments to supportsResultSetConcurrency()", "S1009", getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsResultSetHoldability(int holdability) throws SQLException {
/* 7475 */     return (holdability == 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsResultSetType(int type) throws SQLException {
/* 7489 */     return (type == 1004);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsSavepoints() throws SQLException {
/* 7497 */     return (this.conn.versionMeetsMinimum(4, 0, 14) || this.conn.versionMeetsMinimum(4, 1, 1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsSchemasInDataManipulation() throws SQLException {
/* 7507 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsSchemasInIndexDefinitions() throws SQLException {
/* 7517 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
/* 7527 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsSchemasInProcedureCalls() throws SQLException {
/* 7537 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsSchemasInTableDefinitions() throws SQLException {
/* 7547 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsSelectForUpdate() throws SQLException {
/* 7557 */     return this.conn.versionMeetsMinimum(4, 0, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsStatementPooling() throws SQLException {
/* 7564 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsStoredProcedures() throws SQLException {
/* 7575 */     return this.conn.versionMeetsMinimum(5, 0, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsSubqueriesInComparisons() throws SQLException {
/* 7586 */     return this.conn.versionMeetsMinimum(4, 1, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsSubqueriesInExists() throws SQLException {
/* 7597 */     return this.conn.versionMeetsMinimum(4, 1, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsSubqueriesInIns() throws SQLException {
/* 7608 */     return this.conn.versionMeetsMinimum(4, 1, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsSubqueriesInQuantifieds() throws SQLException {
/* 7619 */     return this.conn.versionMeetsMinimum(4, 1, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsTableCorrelationNames() throws SQLException {
/* 7630 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
/* 7644 */     if (this.conn.supportsIsolationLevel()) {
/* 7645 */       switch (level) {
/*      */         case 1:
/*      */         case 2:
/*      */         case 4:
/*      */         case 8:
/* 7650 */           return true;
/*      */       } 
/*      */       
/* 7653 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 7657 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsTransactions() throws SQLException {
/* 7668 */     return this.conn.supportsTransactions();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsUnion() throws SQLException {
/* 7678 */     return this.conn.versionMeetsMinimum(4, 0, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsUnionAll() throws SQLException {
/* 7688 */     return this.conn.versionMeetsMinimum(4, 0, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean updatesAreDetected(int type) throws SQLException {
/* 7702 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean usesLocalFilePerTable() throws SQLException {
/* 7712 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean usesLocalFiles() throws SQLException {
/* 7722 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getClientInfoProperties() throws SQLException {
/* 7754 */     Field[] fields = new Field[4];
/* 7755 */     fields[0] = new Field("", "NAME", 12, 255);
/* 7756 */     fields[1] = new Field("", "MAX_LEN", 4, 10);
/* 7757 */     fields[2] = new Field("", "DEFAULT_VALUE", 12, 255);
/* 7758 */     fields[3] = new Field("", "DESCRIPTION", 12, 255);
/*      */     
/* 7760 */     return buildResultSet(fields, new ArrayList<ResultSetRow>(), this.conn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException {
/* 7771 */     Field[] fields = createFunctionColumnsFields();
/*      */     
/* 7773 */     return getProcedureOrFunctionColumns(fields, catalog, schemaPattern, functionNamePattern, columnNamePattern, false, true);
/*      */   }
/*      */   
/*      */   protected Field[] createFunctionColumnsFields() {
/* 7777 */     Field[] fields = { new Field("", "FUNCTION_CAT", 12, 512), new Field("", "FUNCTION_SCHEM", 12, 512), new Field("", "FUNCTION_NAME", 12, 512), new Field("", "COLUMN_NAME", 12, 512), new Field("", "COLUMN_TYPE", 12, 64), new Field("", "DATA_TYPE", 5, 6), new Field("", "TYPE_NAME", 12, 64), new Field("", "PRECISION", 4, 12), new Field("", "LENGTH", 4, 12), new Field("", "SCALE", 5, 12), new Field("", "RADIX", 5, 6), new Field("", "NULLABLE", 5, 6), new Field("", "REMARKS", 12, 512), new Field("", "CHAR_OCTET_LENGTH", 4, 32), new Field("", "ORDINAL_POSITION", 4, 32), new Field("", "IS_NULLABLE", 12, 12), new Field("", "SPECIFIC_NAME", 12, 64) };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7784 */     return fields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException {
/* 7832 */     Field[] fields = new Field[6];
/*      */     
/* 7834 */     fields[0] = new Field("", "FUNCTION_CAT", 1, 255);
/* 7835 */     fields[1] = new Field("", "FUNCTION_SCHEM", 1, 255);
/* 7836 */     fields[2] = new Field("", "FUNCTION_NAME", 1, 255);
/* 7837 */     fields[3] = new Field("", "REMARKS", 1, 255);
/* 7838 */     fields[4] = new Field("", "FUNCTION_TYPE", 5, 6);
/* 7839 */     fields[5] = new Field("", "SPECIFIC_NAME", 1, 255);
/*      */     
/* 7841 */     return getProceduresAndOrFunctions(fields, catalog, schemaPattern, functionNamePattern, false, true);
/*      */   }
/*      */   
/*      */   public boolean providesQueryObjectGenerator() throws SQLException {
/* 7845 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
/* 7854 */     Field[] fields = { new Field("", "TABLE_SCHEM", 12, 255), new Field("", "TABLE_CATALOG", 12, 255) };
/*      */     
/* 7856 */     return buildResultSet(fields, new ArrayList<ResultSetRow>());
/*      */   }
/*      */   
/*      */   public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
/* 7860 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected PreparedStatement prepareMetaDataSafeStatement(String sql) throws SQLException {
/* 7871 */     PreparedStatement pStmt = this.conn.clientPrepareStatement(sql);
/*      */     
/* 7873 */     if (pStmt.getMaxRows() != 0) {
/* 7874 */       pStmt.setMaxRows(0);
/*      */     }
/*      */     
/* 7877 */     ((Statement)pStmt).setHoldResultsOpenOverClose(true);
/*      */     
/* 7879 */     return pStmt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
/* 7892 */     Field[] fields = { new Field("", "TABLE_CAT", 12, 512), new Field("", "TABLE_SCHEM", 12, 512), new Field("", "TABLE_NAME", 12, 512), new Field("", "COLUMN_NAME", 12, 512), new Field("", "DATA_TYPE", 4, 12), new Field("", "COLUMN_SIZE", 4, 12), new Field("", "DECIMAL_DIGITS", 4, 12), new Field("", "NUM_PREC_RADIX", 4, 12), new Field("", "COLUMN_USAGE", 12, 512), new Field("", "REMARKS", 12, 512), new Field("", "CHAR_OCTET_LENGTH", 4, 12), new Field("", "IS_NULLABLE", 12, 512) };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7899 */     return buildResultSet(fields, new ArrayList<ResultSetRow>());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean generatedKeyAlwaysReturned() throws SQLException {
/* 7904 */     return true;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\DatabaseMetaData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */