/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.sql.DatabaseMetaData;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.regex.PatternSyntaxException;
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
/*     */ public class Field
/*     */ {
/*     */   private static final int AUTO_INCREMENT_FLAG = 512;
/*     */   private static final int NO_CHARSET_INFO = -1;
/*     */   private byte[] buffer;
/*  42 */   private int collationIndex = 0;
/*     */   
/*  44 */   private String encoding = null;
/*     */   
/*     */   private int colDecimals;
/*     */   
/*     */   private short colFlag;
/*     */   
/*  50 */   private String collationName = null;
/*     */   
/*  52 */   private MySQLConnection connection = null;
/*     */   
/*  54 */   private String databaseName = null;
/*     */   
/*  56 */   private int databaseNameLength = -1;
/*     */ 
/*     */   
/*  59 */   private int databaseNameStart = -1;
/*     */   
/*  61 */   protected int defaultValueLength = -1;
/*     */ 
/*     */   
/*  64 */   protected int defaultValueStart = -1;
/*     */   
/*  66 */   private String fullName = null;
/*     */   
/*  68 */   private String fullOriginalName = null;
/*     */   
/*     */   private boolean isImplicitTempTable = false;
/*     */   
/*     */   private long length;
/*     */   
/*  74 */   private int mysqlType = -1;
/*     */   
/*     */   private String name;
/*     */   
/*     */   private int nameLength;
/*     */   
/*     */   private int nameStart;
/*     */   
/*  82 */   private String originalColumnName = null;
/*     */   
/*  84 */   private int originalColumnNameLength = -1;
/*     */ 
/*     */   
/*  87 */   private int originalColumnNameStart = -1;
/*     */   
/*  89 */   private String originalTableName = null;
/*     */   
/*  91 */   private int originalTableNameLength = -1;
/*     */ 
/*     */   
/*  94 */   private int originalTableNameStart = -1;
/*     */   
/*  96 */   private int precisionAdjustFactor = 0;
/*     */   
/*  98 */   private int sqlType = -1;
/*     */ 
/*     */   
/*     */   private String tableName;
/*     */ 
/*     */   
/*     */   private int tableNameLength;
/*     */ 
/*     */   
/*     */   private int tableNameStart;
/*     */ 
/*     */   
/*     */   private boolean useOldNameMetadata = false;
/*     */   
/*     */   private boolean isSingleBit;
/*     */   
/*     */   private int maxBytesPerChar;
/*     */   
/*     */   private final boolean valueNeedsQuoting;
/*     */ 
/*     */   
/*     */   Field(MySQLConnection conn, byte[] buffer, int databaseNameStart, int databaseNameLength, int tableNameStart, int tableNameLength, int originalTableNameStart, int originalTableNameLength, int nameStart, int nameLength, int originalColumnNameStart, int originalColumnNameLength, long length, int mysqlType, short colFlag, int colDecimals, int defaultValueStart, int defaultValueLength, int charsetIndex) throws SQLException {
/* 120 */     this.connection = conn;
/* 121 */     this.buffer = buffer;
/* 122 */     this.nameStart = nameStart;
/* 123 */     this.nameLength = nameLength;
/* 124 */     this.tableNameStart = tableNameStart;
/* 125 */     this.tableNameLength = tableNameLength;
/* 126 */     this.length = length;
/* 127 */     this.colFlag = colFlag;
/* 128 */     this.colDecimals = colDecimals;
/* 129 */     this.mysqlType = mysqlType;
/*     */ 
/*     */     
/* 132 */     this.databaseNameStart = databaseNameStart;
/* 133 */     this.databaseNameLength = databaseNameLength;
/*     */     
/* 135 */     this.originalTableNameStart = originalTableNameStart;
/* 136 */     this.originalTableNameLength = originalTableNameLength;
/*     */     
/* 138 */     this.originalColumnNameStart = originalColumnNameStart;
/* 139 */     this.originalColumnNameLength = originalColumnNameLength;
/*     */     
/* 141 */     this.defaultValueStart = defaultValueStart;
/* 142 */     this.defaultValueLength = defaultValueLength;
/*     */ 
/*     */     
/* 145 */     this.collationIndex = charsetIndex;
/*     */ 
/*     */     
/* 148 */     this.sqlType = MysqlDefs.mysqlToJavaType(this.mysqlType);
/*     */     
/* 150 */     checkForImplicitTemporaryTable();
/*     */     
/* 152 */     boolean isFromFunction = (this.originalTableNameLength == 0);
/*     */     
/* 154 */     if (this.mysqlType == 252) {
/* 155 */       if (this.connection.getBlobsAreStrings() || (this.connection.getFunctionsNeverReturnBlobs() && isFromFunction)) {
/* 156 */         this.sqlType = 12;
/* 157 */         this.mysqlType = 15;
/* 158 */       } else if (this.collationIndex == 63 || !this.connection.versionMeetsMinimum(4, 1, 0)) {
/* 159 */         if (this.connection.getUseBlobToStoreUTF8OutsideBMP() && shouldSetupForUtf8StringInBlob()) {
/* 160 */           setupForUtf8StringInBlob();
/*     */         } else {
/* 162 */           setBlobTypeBasedOnLength();
/* 163 */           this.sqlType = MysqlDefs.mysqlToJavaType(this.mysqlType);
/*     */         } 
/*     */       } else {
/*     */         
/* 167 */         this.mysqlType = 253;
/* 168 */         this.sqlType = -1;
/*     */       } 
/*     */     }
/*     */     
/* 172 */     if (this.sqlType == -6 && this.length == 1L && this.connection.getTinyInt1isBit())
/*     */     {
/* 174 */       if (conn.getTinyInt1isBit()) {
/* 175 */         if (conn.getTransformedBitIsBoolean()) {
/* 176 */           this.sqlType = 16;
/*     */         } else {
/* 178 */           this.sqlType = -7;
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 184 */     if (!isNativeNumericType() && !isNativeDateTimeType()) {
/* 185 */       this.encoding = this.connection.getEncodingForIndex(this.collationIndex);
/*     */ 
/*     */ 
/*     */       
/* 189 */       if ("UnicodeBig".equals(this.encoding)) {
/* 190 */         this.encoding = "UTF-16";
/*     */       }
/*     */ 
/*     */       
/* 194 */       if (this.mysqlType == 245) {
/* 195 */         this.encoding = "UTF-8";
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 200 */       boolean isBinary = isBinary();
/*     */       
/* 202 */       if (this.connection.versionMeetsMinimum(4, 1, 0) && this.mysqlType == 253 && isBinary && this.collationIndex == 63)
/*     */       {
/* 204 */         if (this.connection.getFunctionsNeverReturnBlobs() && isFromFunction) {
/* 205 */           this.sqlType = 12;
/* 206 */           this.mysqlType = 15;
/* 207 */         } else if (isOpaqueBinary()) {
/* 208 */           this.sqlType = -3;
/*     */         } 
/*     */       }
/*     */       
/* 212 */       if (this.connection.versionMeetsMinimum(4, 1, 0) && this.mysqlType == 254 && isBinary && this.collationIndex == 63)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 219 */         if (isOpaqueBinary() && !this.connection.getBlobsAreStrings()) {
/* 220 */           this.sqlType = -2;
/*     */         }
/*     */       }
/*     */       
/* 224 */       if (this.mysqlType == 16) {
/* 225 */         this.isSingleBit = (this.length == 0L || (this.length == 1L && (this.connection.versionMeetsMinimum(5, 0, 21) || this.connection.versionMeetsMinimum(5, 1, 10))));
/*     */ 
/*     */         
/* 228 */         if (!this.isSingleBit) {
/* 229 */           this.colFlag = (short)(this.colFlag | 0x80);
/* 230 */           this.colFlag = (short)(this.colFlag | 0x10);
/* 231 */           isBinary = true;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 238 */       if (this.sqlType == -4 && !isBinary) {
/* 239 */         this.sqlType = -1;
/* 240 */       } else if (this.sqlType == -3 && !isBinary) {
/* 241 */         this.sqlType = 12;
/*     */       } 
/*     */     } else {
/* 244 */       this.encoding = "US-ASCII";
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     if (!isUnsigned()) {
/* 251 */       switch (this.mysqlType) {
/*     */         case 0:
/*     */         case 246:
/* 254 */           this.precisionAdjustFactor = -1;
/*     */           break;
/*     */         
/*     */         case 4:
/*     */         case 5:
/* 259 */           this.precisionAdjustFactor = 1;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } else {
/* 264 */       switch (this.mysqlType) {
/*     */         case 4:
/*     */         case 5:
/* 267 */           this.precisionAdjustFactor = 1;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 272 */     this.valueNeedsQuoting = determineNeedsQuoting();
/*     */   }
/*     */   
/*     */   private boolean shouldSetupForUtf8StringInBlob() throws SQLException {
/* 276 */     String includePattern = this.connection.getUtf8OutsideBmpIncludedColumnNamePattern();
/* 277 */     String excludePattern = this.connection.getUtf8OutsideBmpExcludedColumnNamePattern();
/*     */     
/* 279 */     if (excludePattern != null && !StringUtils.isEmptyOrWhitespaceOnly(excludePattern)) {
/*     */       try {
/* 281 */         if (getOriginalName().matches(excludePattern)) {
/* 282 */           if (includePattern != null && !StringUtils.isEmptyOrWhitespaceOnly(includePattern)) {
/*     */             try {
/* 284 */               if (getOriginalName().matches(includePattern)) {
/* 285 */                 return true;
/*     */               }
/* 287 */             } catch (PatternSyntaxException pse) {
/* 288 */               SQLException sqlEx = SQLError.createSQLException("Illegal regex specified for \"utf8OutsideBmpIncludedColumnNamePattern\"", "S1009", this.connection.getExceptionInterceptor());
/*     */ 
/*     */               
/* 291 */               if (!this.connection.getParanoid()) {
/* 292 */                 sqlEx.initCause(pse);
/*     */               }
/*     */               
/* 295 */               throw sqlEx;
/*     */             } 
/*     */           }
/*     */           
/* 299 */           return false;
/*     */         } 
/* 301 */       } catch (PatternSyntaxException pse) {
/* 302 */         SQLException sqlEx = SQLError.createSQLException("Illegal regex specified for \"utf8OutsideBmpExcludedColumnNamePattern\"", "S1009", this.connection.getExceptionInterceptor());
/*     */ 
/*     */         
/* 305 */         if (!this.connection.getParanoid()) {
/* 306 */           sqlEx.initCause(pse);
/*     */         }
/*     */         
/* 309 */         throw sqlEx;
/*     */       } 
/*     */     }
/*     */     
/* 313 */     return true;
/*     */   }
/*     */   
/*     */   private void setupForUtf8StringInBlob() {
/* 317 */     if (this.length == 255L || this.length == 65535L) {
/* 318 */       this.mysqlType = 15;
/* 319 */       this.sqlType = 12;
/*     */     } else {
/* 321 */       this.mysqlType = 253;
/* 322 */       this.sqlType = -1;
/*     */     } 
/*     */     
/* 325 */     this.collationIndex = 33;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Field(MySQLConnection conn, byte[] buffer, int nameStart, int nameLength, int tableNameStart, int tableNameLength, int length, int mysqlType, short colFlag, int colDecimals) throws SQLException {
/* 333 */     this(conn, buffer, -1, -1, tableNameStart, tableNameLength, -1, -1, nameStart, nameLength, -1, -1, length, mysqlType, colFlag, colDecimals, -1, -1, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Field(String tableName, String columnName, int jdbcType, int length) {
/* 341 */     this.tableName = tableName;
/* 342 */     this.name = columnName;
/* 343 */     this.length = length;
/* 344 */     this.sqlType = jdbcType;
/* 345 */     this.colFlag = 0;
/* 346 */     this.colDecimals = 0;
/* 347 */     this.valueNeedsQuoting = determineNeedsQuoting();
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
/*     */   Field(String tableName, String columnName, int charsetIndex, int jdbcType, int length) {
/* 367 */     this.tableName = tableName;
/* 368 */     this.name = columnName;
/* 369 */     this.length = length;
/* 370 */     this.sqlType = jdbcType;
/* 371 */     this.colFlag = 0;
/* 372 */     this.colDecimals = 0;
/* 373 */     this.collationIndex = charsetIndex;
/* 374 */     this.valueNeedsQuoting = determineNeedsQuoting();
/*     */     
/* 376 */     switch (this.sqlType) {
/*     */       case -3:
/*     */       case -2:
/* 379 */         this.colFlag = (short)(this.colFlag | 0x80);
/* 380 */         this.colFlag = (short)(this.colFlag | 0x10);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkForImplicitTemporaryTable() {
/* 386 */     this.isImplicitTempTable = (this.tableNameLength > 5 && this.buffer[this.tableNameStart] == 35 && this.buffer[this.tableNameStart + 1] == 115 && this.buffer[this.tableNameStart + 2] == 113 && this.buffer[this.tableNameStart + 3] == 108 && this.buffer[this.tableNameStart + 4] == 95);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncoding() throws SQLException {
/* 397 */     return this.encoding;
/*     */   }
/*     */   
/*     */   public void setEncoding(String javaEncodingName, Connection conn) throws SQLException {
/* 401 */     this.encoding = javaEncodingName;
/*     */     try {
/* 403 */       this.collationIndex = CharsetMapping.getCollationIndexForJavaEncoding(javaEncodingName, conn);
/* 404 */     } catch (RuntimeException ex) {
/* 405 */       SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 406 */       sqlEx.initCause(ex);
/* 407 */       throw sqlEx;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getCollationIndex() throws SQLException {
/* 412 */     return this.collationIndex;
/*     */   }
/*     */   
/*     */   public synchronized String getCollation() throws SQLException {
/* 416 */     if (this.collationName == null && 
/* 417 */       this.connection != null && 
/* 418 */       this.connection.versionMeetsMinimum(4, 1, 0)) {
/* 419 */       if (this.connection.getUseDynamicCharsetInfo()) {
/* 420 */         DatabaseMetaData dbmd = this.connection.getMetaData();
/*     */         
/* 422 */         String quotedIdStr = dbmd.getIdentifierQuoteString();
/*     */         
/* 424 */         if (" ".equals(quotedIdStr)) {
/* 425 */           quotedIdStr = "";
/*     */         }
/*     */         
/* 428 */         String csCatalogName = getDatabaseName();
/* 429 */         String csTableName = getOriginalTableName();
/* 430 */         String csColumnName = getOriginalName();
/*     */         
/* 432 */         if (csCatalogName != null && csCatalogName.length() != 0 && csTableName != null && csTableName.length() != 0 && csColumnName != null && csColumnName.length() != 0) {
/*     */           
/* 434 */           StringBuilder queryBuf = new StringBuilder(csCatalogName.length() + csTableName.length() + 28);
/* 435 */           queryBuf.append("SHOW FULL COLUMNS FROM ");
/* 436 */           queryBuf.append(quotedIdStr);
/* 437 */           queryBuf.append(csCatalogName);
/* 438 */           queryBuf.append(quotedIdStr);
/* 439 */           queryBuf.append(".");
/* 440 */           queryBuf.append(quotedIdStr);
/* 441 */           queryBuf.append(csTableName);
/* 442 */           queryBuf.append(quotedIdStr);
/*     */           
/* 444 */           Statement collationStmt = null;
/* 445 */           ResultSet collationRs = null;
/*     */           
/*     */           try {
/* 448 */             collationStmt = this.connection.createStatement();
/*     */             
/* 450 */             collationRs = collationStmt.executeQuery(queryBuf.toString());
/*     */             
/* 452 */             while (collationRs.next()) {
/* 453 */               if (csColumnName.equals(collationRs.getString("Field"))) {
/* 454 */                 this.collationName = collationRs.getString("Collation");
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } finally {
/* 460 */             if (collationRs != null) {
/* 461 */               collationRs.close();
/* 462 */               collationRs = null;
/*     */             } 
/*     */             
/* 465 */             if (collationStmt != null) {
/* 466 */               collationStmt.close();
/* 467 */               collationStmt = null;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         try {
/* 473 */           this.collationName = CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME[this.collationIndex];
/* 474 */         } catch (RuntimeException ex) {
/* 475 */           SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 476 */           sqlEx.initCause(ex);
/* 477 */           throw sqlEx;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 484 */     return this.collationName;
/*     */   }
/*     */   
/*     */   public String getColumnLabel() throws SQLException {
/* 488 */     return getName();
/*     */   }
/*     */   
/*     */   public String getDatabaseName() throws SQLException {
/* 492 */     if (this.databaseName == null && this.databaseNameStart != -1 && this.databaseNameLength != -1) {
/* 493 */       this.databaseName = getStringFromBytes(this.databaseNameStart, this.databaseNameLength);
/*     */     }
/*     */     
/* 496 */     return this.databaseName;
/*     */   }
/*     */   
/*     */   int getDecimals() {
/* 500 */     return this.colDecimals;
/*     */   }
/*     */   
/*     */   public String getFullName() throws SQLException {
/* 504 */     if (this.fullName == null) {
/* 505 */       StringBuilder fullNameBuf = new StringBuilder(getTableName().length() + 1 + getName().length());
/* 506 */       fullNameBuf.append(this.tableName);
/*     */ 
/*     */       
/* 509 */       fullNameBuf.append('.');
/* 510 */       fullNameBuf.append(this.name);
/* 511 */       this.fullName = fullNameBuf.toString();
/* 512 */       fullNameBuf = null;
/*     */     } 
/*     */     
/* 515 */     return this.fullName;
/*     */   }
/*     */   
/*     */   public String getFullOriginalName() throws SQLException {
/* 519 */     getOriginalName();
/*     */     
/* 521 */     if (this.originalColumnName == null) {
/* 522 */       return null;
/*     */     }
/*     */     
/* 525 */     if (this.fullName == null) {
/* 526 */       StringBuilder fullOriginalNameBuf = new StringBuilder(getOriginalTableName().length() + 1 + getOriginalName().length());
/* 527 */       fullOriginalNameBuf.append(this.originalTableName);
/*     */ 
/*     */       
/* 530 */       fullOriginalNameBuf.append('.');
/* 531 */       fullOriginalNameBuf.append(this.originalColumnName);
/* 532 */       this.fullOriginalName = fullOriginalNameBuf.toString();
/* 533 */       fullOriginalNameBuf = null;
/*     */     } 
/*     */     
/* 536 */     return this.fullOriginalName;
/*     */   }
/*     */   
/*     */   public long getLength() {
/* 540 */     return this.length;
/*     */   }
/*     */   
/*     */   public synchronized int getMaxBytesPerCharacter() throws SQLException {
/* 544 */     if (this.maxBytesPerChar == 0) {
/* 545 */       this.maxBytesPerChar = this.connection.getMaxBytesPerChar(Integer.valueOf(this.collationIndex), getEncoding());
/*     */     }
/* 547 */     return this.maxBytesPerChar;
/*     */   }
/*     */   
/*     */   public int getMysqlType() {
/* 551 */     return this.mysqlType;
/*     */   }
/*     */   
/*     */   public String getName() throws SQLException {
/* 555 */     if (this.name == null) {
/* 556 */       this.name = getStringFromBytes(this.nameStart, this.nameLength);
/*     */     }
/*     */     
/* 559 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getNameNoAliases() throws SQLException {
/* 563 */     if (this.useOldNameMetadata) {
/* 564 */       return getName();
/*     */     }
/*     */     
/* 567 */     if (this.connection != null && this.connection.versionMeetsMinimum(4, 1, 0)) {
/* 568 */       return getOriginalName();
/*     */     }
/*     */     
/* 571 */     return getName();
/*     */   }
/*     */   
/*     */   public String getOriginalName() throws SQLException {
/* 575 */     if (this.originalColumnName == null && this.originalColumnNameStart != -1 && this.originalColumnNameLength != -1) {
/* 576 */       this.originalColumnName = getStringFromBytes(this.originalColumnNameStart, this.originalColumnNameLength);
/*     */     }
/*     */     
/* 579 */     return this.originalColumnName;
/*     */   }
/*     */   
/*     */   public String getOriginalTableName() throws SQLException {
/* 583 */     if (this.originalTableName == null && this.originalTableNameStart != -1 && this.originalTableNameLength != -1) {
/* 584 */       this.originalTableName = getStringFromBytes(this.originalTableNameStart, this.originalTableNameLength);
/*     */     }
/*     */     
/* 587 */     return this.originalTableName;
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
/*     */   public int getPrecisionAdjustFactor() {
/* 599 */     return this.precisionAdjustFactor;
/*     */   }
/*     */   
/*     */   public int getSQLType() {
/* 603 */     return this.sqlType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getStringFromBytes(int stringStart, int stringLength) throws SQLException {
/* 611 */     if (stringStart == -1 || stringLength == -1) {
/* 612 */       return null;
/*     */     }
/*     */     
/* 615 */     if (stringLength == 0) {
/* 616 */       return "";
/*     */     }
/*     */     
/* 619 */     String stringVal = null;
/*     */     
/* 621 */     if (this.connection != null) {
/* 622 */       if (this.connection.getUseUnicode()) {
/* 623 */         String javaEncoding = this.connection.getCharacterSetMetadata();
/*     */         
/* 625 */         if (javaEncoding == null) {
/* 626 */           javaEncoding = this.connection.getEncoding();
/*     */         }
/*     */         
/* 629 */         if (javaEncoding != null) {
/* 630 */           SingleByteCharsetConverter converter = null;
/*     */           
/* 632 */           if (this.connection != null) {
/* 633 */             converter = this.connection.getCharsetConverter(javaEncoding);
/*     */           }
/*     */           
/* 636 */           if (converter != null) {
/* 637 */             stringVal = converter.toString(this.buffer, stringStart, stringLength);
/*     */           } else {
/*     */             
/*     */             try {
/* 641 */               stringVal = StringUtils.toString(this.buffer, stringStart, stringLength, javaEncoding);
/* 642 */             } catch (UnsupportedEncodingException ue) {
/* 643 */               throw new RuntimeException(Messages.getString("Field.12") + javaEncoding + Messages.getString("Field.13"));
/*     */             } 
/*     */           } 
/*     */         } else {
/*     */           
/* 648 */           stringVal = StringUtils.toAsciiString(this.buffer, stringStart, stringLength);
/*     */         } 
/*     */       } else {
/*     */         
/* 652 */         stringVal = StringUtils.toAsciiString(this.buffer, stringStart, stringLength);
/*     */       } 
/*     */     } else {
/*     */       
/* 656 */       stringVal = StringUtils.toAsciiString(this.buffer, stringStart, stringLength);
/*     */     } 
/*     */     
/* 659 */     return stringVal;
/*     */   }
/*     */   
/*     */   public String getTable() throws SQLException {
/* 663 */     return getTableName();
/*     */   }
/*     */   
/*     */   public String getTableName() throws SQLException {
/* 667 */     if (this.tableName == null) {
/* 668 */       this.tableName = getStringFromBytes(this.tableNameStart, this.tableNameLength);
/*     */     }
/*     */     
/* 671 */     return this.tableName;
/*     */   }
/*     */   
/*     */   public String getTableNameNoAliases() throws SQLException {
/* 675 */     if (this.connection.versionMeetsMinimum(4, 1, 0)) {
/* 676 */       return getOriginalTableName();
/*     */     }
/*     */     
/* 679 */     return getTableName();
/*     */   }
/*     */   
/*     */   public boolean isAutoIncrement() {
/* 683 */     return ((this.colFlag & 0x200) > 0);
/*     */   }
/*     */   
/*     */   public boolean isBinary() {
/* 687 */     return ((this.colFlag & 0x80) > 0);
/*     */   }
/*     */   
/*     */   public boolean isBlob() {
/* 691 */     return ((this.colFlag & 0x10) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isImplicitTemporaryTable() {
/* 698 */     return this.isImplicitTempTable;
/*     */   }
/*     */   
/*     */   public boolean isMultipleKey() {
/* 702 */     return ((this.colFlag & 0x8) > 0);
/*     */   }
/*     */   
/*     */   boolean isNotNull() {
/* 706 */     return ((this.colFlag & 0x1) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isOpaqueBinary() throws SQLException {
/* 715 */     if (this.collationIndex == 63 && isBinary() && (getMysqlType() == 254 || getMysqlType() == 253)) {
/*     */ 
/*     */       
/* 718 */       if (this.originalTableNameLength == 0 && this.connection != null && !this.connection.versionMeetsMinimum(5, 0, 25)) {
/* 719 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 724 */       return !isImplicitTemporaryTable();
/*     */     } 
/*     */     
/* 727 */     return (this.connection.versionMeetsMinimum(4, 1, 0) && "binary".equalsIgnoreCase(getEncoding()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPrimaryKey() {
/* 732 */     return ((this.colFlag & 0x2) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isReadOnly() throws SQLException {
/* 742 */     if (this.connection.versionMeetsMinimum(4, 1, 0)) {
/* 743 */       String orgColumnName = getOriginalName();
/* 744 */       String orgTableName = getOriginalTableName();
/*     */       
/* 746 */       return (orgColumnName == null || orgColumnName.length() <= 0 || orgTableName == null || orgTableName.length() <= 0);
/*     */     } 
/*     */     
/* 749 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isUniqueKey() {
/* 753 */     return ((this.colFlag & 0x4) > 0);
/*     */   }
/*     */   
/*     */   public boolean isUnsigned() {
/* 757 */     return ((this.colFlag & 0x20) > 0);
/*     */   }
/*     */   
/*     */   public void setUnsigned() {
/* 761 */     this.colFlag = (short)(this.colFlag | 0x20);
/*     */   }
/*     */   
/*     */   public boolean isZeroFill() {
/* 765 */     return ((this.colFlag & 0x40) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setBlobTypeBasedOnLength() {
/* 773 */     if (this.length == 255L) {
/* 774 */       this.mysqlType = 249;
/* 775 */     } else if (this.length == 65535L) {
/* 776 */       this.mysqlType = 252;
/* 777 */     } else if (this.length == 16777215L) {
/* 778 */       this.mysqlType = 250;
/* 779 */     } else if (this.length == 4294967295L) {
/* 780 */       this.mysqlType = 251;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isNativeNumericType() {
/* 785 */     return ((this.mysqlType >= 1 && this.mysqlType <= 5) || this.mysqlType == 8 || this.mysqlType == 13);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isNativeDateTimeType() {
/* 790 */     return (this.mysqlType == 10 || this.mysqlType == 14 || this.mysqlType == 12 || this.mysqlType == 11 || this.mysqlType == 7);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCharsetApplicableType() {
/* 795 */     return (this.mysqlType == 247 || this.mysqlType == 245 || this.mysqlType == 248 || this.mysqlType == 254 || this.mysqlType == 253 || this.mysqlType == 15);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnection(MySQLConnection conn) {
/* 801 */     this.connection = conn;
/*     */     
/* 803 */     if (this.encoding == null || this.collationIndex == 0) {
/* 804 */       this.encoding = this.connection.getEncoding();
/*     */     }
/*     */   }
/*     */   
/*     */   void setMysqlType(int type) {
/* 809 */     this.mysqlType = type;
/* 810 */     this.sqlType = MysqlDefs.mysqlToJavaType(this.mysqlType);
/*     */   }
/*     */   
/*     */   protected void setUseOldNameMetadata(boolean useOldNameMetadata) {
/* 814 */     this.useOldNameMetadata = useOldNameMetadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     try {
/* 820 */       StringBuilder asString = new StringBuilder();
/* 821 */       asString.append(super.toString());
/* 822 */       asString.append("[");
/* 823 */       asString.append("catalog=");
/* 824 */       asString.append(getDatabaseName());
/* 825 */       asString.append(",tableName=");
/* 826 */       asString.append(getTableName());
/* 827 */       asString.append(",originalTableName=");
/* 828 */       asString.append(getOriginalTableName());
/* 829 */       asString.append(",columnName=");
/* 830 */       asString.append(getName());
/* 831 */       asString.append(",originalColumnName=");
/* 832 */       asString.append(getOriginalName());
/* 833 */       asString.append(",mysqlType=");
/* 834 */       asString.append(getMysqlType());
/* 835 */       asString.append("(");
/* 836 */       asString.append(MysqlDefs.typeToName(getMysqlType()));
/* 837 */       asString.append(")");
/* 838 */       asString.append(",flags=");
/*     */       
/* 840 */       if (isAutoIncrement()) {
/* 841 */         asString.append(" AUTO_INCREMENT");
/*     */       }
/*     */       
/* 844 */       if (isPrimaryKey()) {
/* 845 */         asString.append(" PRIMARY_KEY");
/*     */       }
/*     */       
/* 848 */       if (isUniqueKey()) {
/* 849 */         asString.append(" UNIQUE_KEY");
/*     */       }
/*     */       
/* 852 */       if (isBinary()) {
/* 853 */         asString.append(" BINARY");
/*     */       }
/*     */       
/* 856 */       if (isBlob()) {
/* 857 */         asString.append(" BLOB");
/*     */       }
/*     */       
/* 860 */       if (isMultipleKey()) {
/* 861 */         asString.append(" MULTI_KEY");
/*     */       }
/*     */       
/* 864 */       if (isUnsigned()) {
/* 865 */         asString.append(" UNSIGNED");
/*     */       }
/*     */       
/* 868 */       if (isZeroFill()) {
/* 869 */         asString.append(" ZEROFILL");
/*     */       }
/*     */       
/* 872 */       asString.append(", charsetIndex=");
/* 873 */       asString.append(this.collationIndex);
/* 874 */       asString.append(", charsetName=");
/* 875 */       asString.append(this.encoding);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 883 */       asString.append("]");
/*     */       
/* 885 */       return asString.toString();
/* 886 */     } catch (Throwable t) {
/* 887 */       return super.toString();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isSingleBit() {
/* 892 */     return this.isSingleBit;
/*     */   }
/*     */   
/*     */   protected boolean getvalueNeedsQuoting() {
/* 896 */     return this.valueNeedsQuoting;
/*     */   }
/*     */   
/*     */   private boolean determineNeedsQuoting() {
/* 900 */     boolean retVal = false;
/*     */     
/* 902 */     switch (this.sqlType)
/*     */     { case -7:
/*     */       case -6:
/*     */       case -5:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/* 913 */         retVal = false;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 918 */         return retVal; }  retVal = true; return retVal;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\Field.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */