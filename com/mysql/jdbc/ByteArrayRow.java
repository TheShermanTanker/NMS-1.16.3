/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.sql.Date;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Calendar;
/*     */ import java.util.TimeZone;
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
/*     */ public class ByteArrayRow
/*     */   extends ResultSetRow
/*     */ {
/*     */   byte[][] internalRowData;
/*     */   
/*     */   public ByteArrayRow(byte[][] internalRowData, ExceptionInterceptor exceptionInterceptor) {
/*  46 */     super(exceptionInterceptor);
/*     */     
/*  48 */     this.internalRowData = internalRowData;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getColumnValue(int index) throws SQLException {
/*  53 */     return this.internalRowData[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnValue(int index, byte[] value) throws SQLException {
/*  58 */     this.internalRowData[index] = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString(int index, String encoding, MySQLConnection conn) throws SQLException {
/*  63 */     byte[] columnData = this.internalRowData[index];
/*     */     
/*  65 */     if (columnData == null) {
/*  66 */       return null;
/*     */     }
/*     */     
/*  69 */     return getString(encoding, conn, columnData, 0, columnData.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNull(int index) throws SQLException {
/*  74 */     return (this.internalRowData[index] == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFloatingPointNumber(int index) throws SQLException {
/*  79 */     byte[] numAsBytes = this.internalRowData[index];
/*     */     
/*  81 */     if (this.internalRowData[index] == null || (this.internalRowData[index]).length == 0) {
/*  82 */       return false;
/*     */     }
/*     */     
/*  85 */     for (int i = 0; i < numAsBytes.length; i++) {
/*  86 */       if ((char)numAsBytes[i] == 'e' || (char)numAsBytes[i] == 'E') {
/*  87 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public long length(int index) throws SQLException {
/*  96 */     if (this.internalRowData[index] == null) {
/*  97 */       return 0L;
/*     */     }
/*     */     
/* 100 */     return (this.internalRowData[index]).length;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt(int columnIndex) {
/* 105 */     if (this.internalRowData[columnIndex] == null) {
/* 106 */       return 0;
/*     */     }
/*     */     
/* 109 */     return StringUtils.getInt(this.internalRowData[columnIndex]);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong(int columnIndex) {
/* 114 */     if (this.internalRowData[columnIndex] == null) {
/* 115 */       return 0L;
/*     */     }
/*     */     
/* 118 */     return StringUtils.getLong(this.internalRowData[columnIndex]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Timestamp getTimestampFast(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs, boolean useGmtMillis, boolean useJDBCCompliantTimezoneShift) throws SQLException {
/* 124 */     byte[] columnValue = this.internalRowData[columnIndex];
/*     */     
/* 126 */     if (columnValue == null) {
/* 127 */       return null;
/*     */     }
/*     */     
/* 130 */     return getTimestampFast(columnIndex, this.internalRowData[columnIndex], 0, columnValue.length, targetCalendar, tz, rollForward, conn, rs, useGmtMillis, useJDBCCompliantTimezoneShift);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getNativeDouble(int columnIndex) throws SQLException {
/* 136 */     if (this.internalRowData[columnIndex] == null) {
/* 137 */       return 0.0D;
/*     */     }
/*     */     
/* 140 */     return getNativeDouble(this.internalRowData[columnIndex], 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getNativeFloat(int columnIndex) throws SQLException {
/* 145 */     if (this.internalRowData[columnIndex] == null) {
/* 146 */       return 0.0F;
/*     */     }
/*     */     
/* 149 */     return getNativeFloat(this.internalRowData[columnIndex], 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNativeInt(int columnIndex) throws SQLException {
/* 154 */     if (this.internalRowData[columnIndex] == null) {
/* 155 */       return 0;
/*     */     }
/*     */     
/* 158 */     return getNativeInt(this.internalRowData[columnIndex], 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getNativeLong(int columnIndex) throws SQLException {
/* 163 */     if (this.internalRowData[columnIndex] == null) {
/* 164 */       return 0L;
/*     */     }
/*     */     
/* 167 */     return getNativeLong(this.internalRowData[columnIndex], 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public short getNativeShort(int columnIndex) throws SQLException {
/* 172 */     if (this.internalRowData[columnIndex] == null) {
/* 173 */       return 0;
/*     */     }
/*     */     
/* 176 */     return getNativeShort(this.internalRowData[columnIndex], 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Timestamp getNativeTimestamp(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/* 182 */     byte[] bits = this.internalRowData[columnIndex];
/*     */     
/* 184 */     if (bits == null) {
/* 185 */       return null;
/*     */     }
/*     */     
/* 188 */     return getNativeTimestamp(bits, 0, bits.length, targetCalendar, tz, rollForward, conn, rs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeOpenStreams() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getBinaryInputStream(int columnIndex) throws SQLException {
/* 198 */     if (this.internalRowData[columnIndex] == null) {
/* 199 */       return null;
/*     */     }
/*     */     
/* 202 */     return new ByteArrayInputStream(this.internalRowData[columnIndex]);
/*     */   }
/*     */ 
/*     */   
/*     */   public Reader getReader(int columnIndex) throws SQLException {
/* 207 */     InputStream stream = getBinaryInputStream(columnIndex);
/*     */     
/* 209 */     if (stream == null) {
/* 210 */       return null;
/*     */     }
/*     */     
/*     */     try {
/* 214 */       return new InputStreamReader(stream, this.metadata[columnIndex].getEncoding());
/* 215 */     } catch (UnsupportedEncodingException e) {
/* 216 */       SQLException sqlEx = SQLError.createSQLException("", this.exceptionInterceptor);
/*     */       
/* 218 */       sqlEx.initCause(e);
/*     */       
/* 220 */       throw sqlEx;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Time getTimeFast(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/* 227 */     byte[] columnValue = this.internalRowData[columnIndex];
/*     */     
/* 229 */     if (columnValue == null) {
/* 230 */       return null;
/*     */     }
/*     */     
/* 233 */     return getTimeFast(columnIndex, this.internalRowData[columnIndex], 0, columnValue.length, targetCalendar, tz, rollForward, conn, rs);
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFast(int columnIndex, MySQLConnection conn, ResultSetImpl rs, Calendar targetCalendar) throws SQLException {
/* 238 */     byte[] columnValue = this.internalRowData[columnIndex];
/*     */     
/* 240 */     if (columnValue == null) {
/* 241 */       return null;
/*     */     }
/*     */     
/* 244 */     return getDateFast(columnIndex, this.internalRowData[columnIndex], 0, columnValue.length, conn, rs, targetCalendar);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getNativeDateTimeValue(int columnIndex, Calendar targetCalendar, int jdbcType, int mysqlType, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/* 250 */     byte[] columnValue = this.internalRowData[columnIndex];
/*     */     
/* 252 */     if (columnValue == null) {
/* 253 */       return null;
/*     */     }
/*     */     
/* 256 */     return getNativeDateTimeValue(columnIndex, columnValue, 0, columnValue.length, targetCalendar, jdbcType, mysqlType, tz, rollForward, conn, rs);
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getNativeDate(int columnIndex, MySQLConnection conn, ResultSetImpl rs, Calendar cal) throws SQLException {
/* 261 */     byte[] columnValue = this.internalRowData[columnIndex];
/*     */     
/* 263 */     if (columnValue == null) {
/* 264 */       return null;
/*     */     }
/*     */     
/* 267 */     return getNativeDate(columnIndex, columnValue, 0, columnValue.length, conn, rs, cal);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Time getNativeTime(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/* 273 */     byte[] columnValue = this.internalRowData[columnIndex];
/*     */     
/* 275 */     if (columnValue == null) {
/* 276 */       return null;
/*     */     }
/*     */     
/* 279 */     return getNativeTime(columnIndex, columnValue, 0, columnValue.length, targetCalendar, tz, rollForward, conn, rs);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBytesSize() {
/* 284 */     if (this.internalRowData == null) {
/* 285 */       return 0;
/*     */     }
/*     */     
/* 288 */     int bytesSize = 0;
/*     */     
/* 290 */     for (int i = 0; i < this.internalRowData.length; i++) {
/* 291 */       if (this.internalRowData[i] != null) {
/* 292 */         bytesSize += (this.internalRowData[i]).length;
/*     */       }
/*     */     } 
/*     */     
/* 296 */     return bytesSize;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ByteArrayRow.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */