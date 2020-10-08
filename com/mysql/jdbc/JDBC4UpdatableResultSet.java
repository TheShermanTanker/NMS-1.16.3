/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.sql.NClob;
/*     */ import java.sql.RowId;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLXML;
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
/*     */ public class JDBC4UpdatableResultSet
/*     */   extends UpdatableResultSet
/*     */ {
/*     */   public JDBC4UpdatableResultSet(String catalog, Field[] fields, RowData tuples, MySQLConnection conn, StatementImpl creatorStmt) throws SQLException {
/*  44 */     super(catalog, fields, tuples, conn, creatorStmt);
/*     */   }
/*     */   
/*     */   public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
/*  48 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
/*  53 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
/*  58 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
/*  63 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
/*  68 */     throw new NotUpdatable();
/*     */   }
/*     */   
/*     */   public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
/*  72 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
/*  77 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
/*  82 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateClob(int columnIndex, Reader reader) throws SQLException {
/*  87 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
/*  92 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
/*  97 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
/* 102 */     updateNCharacterStream(columnIndex, x, (int)length);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateNClob(int columnIndex, Reader reader) throws SQLException {
/* 107 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
/* 112 */     throw new NotUpdatable();
/*     */   }
/*     */   
/*     */   public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
/* 116 */     throw new NotUpdatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateRowId(int columnIndex, RowId x) throws SQLException {
/* 121 */     throw new NotUpdatable();
/*     */   }
/*     */   
/*     */   public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
/* 125 */     updateAsciiStream(findColumn(columnLabel), x);
/*     */   }
/*     */   
/*     */   public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
/* 129 */     updateAsciiStream(findColumn(columnLabel), x, length);
/*     */   }
/*     */   
/*     */   public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
/* 133 */     updateBinaryStream(findColumn(columnLabel), x);
/*     */   }
/*     */   
/*     */   public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
/* 137 */     updateBinaryStream(findColumn(columnLabel), x, length);
/*     */   }
/*     */   
/*     */   public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
/* 141 */     updateBlob(findColumn(columnLabel), inputStream);
/*     */   }
/*     */   
/*     */   public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
/* 145 */     updateBlob(findColumn(columnLabel), inputStream, length);
/*     */   }
/*     */   
/*     */   public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
/* 149 */     updateCharacterStream(findColumn(columnLabel), reader);
/*     */   }
/*     */   
/*     */   public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
/* 153 */     updateCharacterStream(findColumn(columnLabel), reader, length);
/*     */   }
/*     */   
/*     */   public void updateClob(String columnLabel, Reader reader) throws SQLException {
/* 157 */     updateClob(findColumn(columnLabel), reader);
/*     */   }
/*     */   
/*     */   public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
/* 161 */     updateClob(findColumn(columnLabel), reader, length);
/*     */   }
/*     */   
/*     */   public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
/* 165 */     updateNCharacterStream(findColumn(columnLabel), reader);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
/* 170 */     updateNCharacterStream(findColumn(columnLabel), reader, length);
/*     */   }
/*     */   
/*     */   public void updateNClob(String columnLabel, Reader reader) throws SQLException {
/* 174 */     updateNClob(findColumn(columnLabel), reader);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
/* 179 */     updateNClob(findColumn(columnLabel), reader, length);
/*     */   }
/*     */   
/*     */   public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
/* 183 */     updateSQLXML(findColumn(columnLabel), xmlObject);
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
/*     */   public void updateNCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
/* 205 */     synchronized (checkClosed().getConnectionMutex()) {
/* 206 */       String fieldEncoding = this.fields[columnIndex - 1].getEncoding();
/* 207 */       if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
/* 208 */         throw new SQLException("Can not call updateNCharacterStream() when field's character set isn't UTF-8");
/*     */       }
/*     */       
/* 211 */       if (!this.onInsertRow) {
/* 212 */         if (!this.doingUpdates) {
/* 213 */           this.doingUpdates = true;
/* 214 */           syncUpdate();
/*     */         } 
/*     */         
/* 217 */         ((JDBC4PreparedStatement)this.updater).setNCharacterStream(columnIndex, x, length);
/*     */       } else {
/* 219 */         ((JDBC4PreparedStatement)this.inserter).setNCharacterStream(columnIndex, x, length);
/*     */         
/* 221 */         if (x == null) {
/* 222 */           this.thisRow.setColumnValue(columnIndex - 1, null);
/*     */         } else {
/* 224 */           this.thisRow.setColumnValue(columnIndex - 1, STREAM_DATA_MARKER);
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
/*     */   public void updateNCharacterStream(String columnName, Reader reader, int length) throws SQLException {
/* 248 */     updateNCharacterStream(findColumn(columnName), reader, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
/* 255 */     synchronized (checkClosed().getConnectionMutex()) {
/* 256 */       String fieldEncoding = this.fields[columnIndex - 1].getEncoding();
/* 257 */       if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
/* 258 */         throw new SQLException("Can not call updateNClob() when field's character set isn't UTF-8");
/*     */       }
/*     */       
/* 261 */       if (nClob == null) {
/* 262 */         updateNull(columnIndex);
/*     */       } else {
/* 264 */         updateNCharacterStream(columnIndex, nClob.getCharacterStream(), (int)nClob.length());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateNClob(String columnName, NClob nClob) throws SQLException {
/* 273 */     updateNClob(findColumn(columnName), nClob);
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
/*     */   public void updateNString(int columnIndex, String x) throws SQLException {
/* 291 */     synchronized (checkClosed().getConnectionMutex()) {
/* 292 */       String fieldEncoding = this.fields[columnIndex - 1].getEncoding();
/* 293 */       if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
/* 294 */         throw new SQLException("Can not call updateNString() when field's character set isn't UTF-8");
/*     */       }
/*     */       
/* 297 */       if (!this.onInsertRow) {
/* 298 */         if (!this.doingUpdates) {
/* 299 */           this.doingUpdates = true;
/* 300 */           syncUpdate();
/*     */         } 
/*     */         
/* 303 */         ((JDBC4PreparedStatement)this.updater).setNString(columnIndex, x);
/*     */       } else {
/* 305 */         ((JDBC4PreparedStatement)this.inserter).setNString(columnIndex, x);
/*     */         
/* 307 */         if (x == null) {
/* 308 */           this.thisRow.setColumnValue(columnIndex - 1, null);
/*     */         } else {
/* 310 */           this.thisRow.setColumnValue(columnIndex - 1, StringUtils.getBytes(x, this.charConverter, fieldEncoding, this.connection.getServerCharset(), this.connection
/* 311 */                 .parserKnowsUnicode(), getExceptionInterceptor()));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateNString(String columnName, String x) throws SQLException {
/* 332 */     updateNString(findColumn(columnName), x);
/*     */   }
/*     */   
/*     */   public int getHoldability() throws SQLException {
/* 336 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*     */   protected NClob getNativeNClob(int columnIndex) throws SQLException {
/* 351 */     String stringVal = getStringForNClob(columnIndex);
/*     */     
/* 353 */     if (stringVal == null) {
/* 354 */       return null;
/*     */     }
/*     */     
/* 357 */     return getNClobFromString(stringVal, columnIndex);
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
/*     */   public Reader getNCharacterStream(int columnIndex) throws SQLException {
/* 376 */     String fieldEncoding = this.fields[columnIndex - 1].getEncoding();
/* 377 */     if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
/* 378 */       throw new SQLException("Can not call getNCharacterStream() when field's charset isn't UTF-8");
/*     */     }
/*     */     
/* 381 */     return getCharacterStream(columnIndex);
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
/*     */   public Reader getNCharacterStream(String columnName) throws SQLException {
/* 400 */     return getNCharacterStream(findColumn(columnName));
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
/*     */   public NClob getNClob(int columnIndex) throws SQLException {
/* 415 */     String fieldEncoding = this.fields[columnIndex - 1].getEncoding();
/*     */     
/* 417 */     if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
/* 418 */       throw new SQLException("Can not call getNClob() when field's charset isn't UTF-8");
/*     */     }
/*     */     
/* 421 */     if (!this.isBinaryEncoded) {
/* 422 */       String asString = getStringForNClob(columnIndex);
/*     */       
/* 424 */       if (asString == null) {
/* 425 */         return null;
/*     */       }
/*     */       
/* 428 */       return new JDBC4NClob(asString, getExceptionInterceptor());
/*     */     } 
/*     */     
/* 431 */     return getNativeNClob(columnIndex);
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
/*     */   public NClob getNClob(String columnName) throws SQLException {
/* 446 */     return getNClob(findColumn(columnName));
/*     */   }
/*     */   
/*     */   private final NClob getNClobFromString(String stringVal, int columnIndex) throws SQLException {
/* 450 */     return new JDBC4NClob(stringVal, getExceptionInterceptor());
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
/*     */   public String getNString(int columnIndex) throws SQLException {
/* 467 */     String fieldEncoding = this.fields[columnIndex - 1].getEncoding();
/*     */     
/* 469 */     if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
/* 470 */       throw new SQLException("Can not call getNString() when field's charset isn't UTF-8");
/*     */     }
/*     */     
/* 473 */     return getString(columnIndex);
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
/*     */   public String getNString(String columnName) throws SQLException {
/* 491 */     return getNString(findColumn(columnName));
/*     */   }
/*     */   
/*     */   public RowId getRowId(int columnIndex) throws SQLException {
/* 495 */     throw SQLError.createSQLFeatureNotSupportedException();
/*     */   }
/*     */   
/*     */   public RowId getRowId(String columnLabel) throws SQLException {
/* 499 */     return getRowId(findColumn(columnLabel));
/*     */   }
/*     */   
/*     */   public SQLXML getSQLXML(int columnIndex) throws SQLException {
/* 503 */     return new JDBC4MysqlSQLXML(this, columnIndex, getExceptionInterceptor());
/*     */   }
/*     */   
/*     */   public SQLXML getSQLXML(String columnLabel) throws SQLException {
/* 507 */     return getSQLXML(findColumn(columnLabel));
/*     */   }
/*     */   
/*     */   private String getStringForNClob(int columnIndex) throws SQLException {
/* 511 */     String asString = null;
/*     */     
/* 513 */     String forcedEncoding = "UTF-8";
/*     */     
/*     */     try {
/* 516 */       byte[] asBytes = null;
/*     */       
/* 518 */       if (!this.isBinaryEncoded) {
/* 519 */         asBytes = getBytes(columnIndex);
/*     */       } else {
/* 521 */         asBytes = getNativeBytes(columnIndex, true);
/*     */       } 
/*     */       
/* 524 */       if (asBytes != null) {
/* 525 */         asString = new String(asBytes, forcedEncoding);
/*     */       }
/* 527 */     } catch (UnsupportedEncodingException uee) {
/* 528 */       throw SQLError.createSQLException("Unsupported character encoding " + forcedEncoding, "S1009", 
/* 529 */           getExceptionInterceptor());
/*     */     } 
/*     */     
/* 532 */     return asString;
/*     */   }
/*     */   
/*     */   public boolean isClosed() throws SQLException {
/* 536 */     return this.isClosed;
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
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 560 */     checkClosed();
/*     */ 
/*     */     
/* 563 */     return iface.isInstance(this);
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
/*     */   public <T> T unwrap(Class<T> iface) throws SQLException {
/*     */     try {
/* 588 */       return iface.cast(this);
/* 589 */     } catch (ClassCastException cce) {
/* 590 */       throw SQLError.createSQLException("Unable to unwrap to " + iface.toString(), "S1009", getExceptionInterceptor());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\JDBC4UpdatableResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */