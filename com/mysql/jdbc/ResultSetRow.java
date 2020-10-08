/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.sql.Date;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.Locale;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.TimeZone;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class ResultSetRow
/*      */ {
/*      */   protected ExceptionInterceptor exceptionInterceptor;
/*      */   protected Field[] metadata;
/*      */   
/*      */   protected ResultSetRow(ExceptionInterceptor exceptionInterceptor) {
/*   49 */     this.exceptionInterceptor = exceptionInterceptor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void closeOpenStreams();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract InputStream getBinaryInputStream(int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract byte[] getColumnValue(int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final Date getDateFast(int columnIndex, byte[] dateAsBytes, int offset, int length, MySQLConnection conn, ResultSetImpl rs, Calendar targetCalendar) throws SQLException {
/*   92 */     int year = 0;
/*   93 */     int month = 0;
/*   94 */     int day = 0;
/*      */     
/*      */     try {
/*   97 */       if (dateAsBytes == null) {
/*   98 */         return null;
/*      */       }
/*      */       
/*  101 */       boolean allZeroDate = true;
/*      */       
/*  103 */       boolean onlyTimePresent = false;
/*      */       int i;
/*  105 */       for (i = 0; i < length; i++) {
/*  106 */         if (dateAsBytes[offset + i] == 58) {
/*  107 */           onlyTimePresent = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  112 */       for (i = 0; i < length; i++) {
/*  113 */         byte b = dateAsBytes[offset + i];
/*      */         
/*  115 */         if (b == 32 || b == 45 || b == 47) {
/*  116 */           onlyTimePresent = false;
/*      */         }
/*      */         
/*  119 */         if (b != 48 && b != 32 && b != 58 && b != 45 && b != 47 && b != 46) {
/*  120 */           allZeroDate = false;
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/*  127 */       int decimalIndex = -1;
/*  128 */       for (int j = 0; j < length; j++) {
/*  129 */         if (dateAsBytes[offset + j] == 46) {
/*  130 */           decimalIndex = j;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/*  136 */       if (decimalIndex > -1) {
/*  137 */         length = decimalIndex;
/*      */       }
/*      */       
/*  140 */       if (!onlyTimePresent && allZeroDate) {
/*      */         
/*  142 */         if ("convertToNull".equals(conn.getZeroDateTimeBehavior()))
/*      */         {
/*  144 */           return null; } 
/*  145 */         if ("exception".equals(conn.getZeroDateTimeBehavior())) {
/*  146 */           throw SQLError.createSQLException("Value '" + StringUtils.toString(dateAsBytes) + "' can not be represented as java.sql.Date", "S1009", this.exceptionInterceptor);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  151 */         return rs.fastDateCreate(targetCalendar, 1, 1, 1);
/*      */       } 
/*  153 */       if (this.metadata[columnIndex].getMysqlType() == 7) {
/*      */         
/*  155 */         switch (length) {
/*      */           case 19:
/*      */           case 21:
/*      */           case 29:
/*  159 */             year = StringUtils.getInt(dateAsBytes, offset + 0, offset + 4);
/*  160 */             month = StringUtils.getInt(dateAsBytes, offset + 5, offset + 7);
/*  161 */             day = StringUtils.getInt(dateAsBytes, offset + 8, offset + 10);
/*      */             
/*  163 */             return rs.fastDateCreate(targetCalendar, year, month, day);
/*      */ 
/*      */           
/*      */           case 8:
/*      */           case 14:
/*  168 */             year = StringUtils.getInt(dateAsBytes, offset + 0, offset + 4);
/*  169 */             month = StringUtils.getInt(dateAsBytes, offset + 4, offset + 6);
/*  170 */             day = StringUtils.getInt(dateAsBytes, offset + 6, offset + 8);
/*      */             
/*  172 */             return rs.fastDateCreate(targetCalendar, year, month, day);
/*      */ 
/*      */           
/*      */           case 6:
/*      */           case 10:
/*      */           case 12:
/*  178 */             year = StringUtils.getInt(dateAsBytes, offset + 0, offset + 2);
/*      */             
/*  180 */             if (year <= 69) {
/*  181 */               year += 100;
/*      */             }
/*      */             
/*  184 */             month = StringUtils.getInt(dateAsBytes, offset + 2, offset + 4);
/*  185 */             day = StringUtils.getInt(dateAsBytes, offset + 4, offset + 6);
/*      */             
/*  187 */             return rs.fastDateCreate(targetCalendar, year + 1900, month, day);
/*      */ 
/*      */           
/*      */           case 4:
/*  191 */             year = StringUtils.getInt(dateAsBytes, offset + 0, offset + 4);
/*      */             
/*  193 */             if (year <= 69) {
/*  194 */               year += 100;
/*      */             }
/*      */             
/*  197 */             month = StringUtils.getInt(dateAsBytes, offset + 2, offset + 4);
/*      */             
/*  199 */             return rs.fastDateCreate(targetCalendar, year + 1900, month, 1);
/*      */ 
/*      */           
/*      */           case 2:
/*  203 */             year = StringUtils.getInt(dateAsBytes, offset + 0, offset + 2);
/*      */             
/*  205 */             if (year <= 69) {
/*  206 */               year += 100;
/*      */             }
/*      */             
/*  209 */             return rs.fastDateCreate(targetCalendar, year + 1900, 1, 1);
/*      */         } 
/*      */ 
/*      */         
/*  213 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_Date", new Object[] { StringUtils.toString(dateAsBytes), Integer.valueOf(columnIndex + 1) }), "S1009", this.exceptionInterceptor);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  218 */       if (this.metadata[columnIndex].getMysqlType() == 13) {
/*      */         
/*  220 */         if (length == 2 || length == 1) {
/*  221 */           year = StringUtils.getInt(dateAsBytes, offset, offset + length);
/*      */           
/*  223 */           if (year <= 69) {
/*  224 */             year += 100;
/*      */           }
/*      */           
/*  227 */           year += 1900;
/*      */         } else {
/*  229 */           year = StringUtils.getInt(dateAsBytes, offset + 0, offset + 4);
/*      */         } 
/*      */         
/*  232 */         return rs.fastDateCreate(targetCalendar, year, 1, 1);
/*  233 */       }  if (this.metadata[columnIndex].getMysqlType() == 11) {
/*  234 */         return rs.fastDateCreate(targetCalendar, 1970, 1, 1);
/*      */       }
/*  236 */       if (length < 10) {
/*  237 */         if (length == 8) {
/*  238 */           return rs.fastDateCreate(targetCalendar, 1970, 1, 1);
/*      */         }
/*      */ 
/*      */         
/*  242 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_Date", new Object[] { StringUtils.toString(dateAsBytes), Integer.valueOf(columnIndex + 1) }), "S1009", this.exceptionInterceptor);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  248 */       if (length != 18) {
/*  249 */         year = StringUtils.getInt(dateAsBytes, offset + 0, offset + 4);
/*  250 */         month = StringUtils.getInt(dateAsBytes, offset + 5, offset + 7);
/*  251 */         day = StringUtils.getInt(dateAsBytes, offset + 8, offset + 10);
/*      */       } else {
/*      */         
/*  254 */         StringTokenizer st = new StringTokenizer(StringUtils.toString(dateAsBytes, offset, length, "ISO8859_1"), "- ");
/*      */         
/*  256 */         year = Integer.parseInt(st.nextToken());
/*  257 */         month = Integer.parseInt(st.nextToken());
/*  258 */         day = Integer.parseInt(st.nextToken());
/*      */       } 
/*      */ 
/*      */       
/*  262 */       return rs.fastDateCreate(targetCalendar, year, month, day);
/*  263 */     } catch (SQLException sqlEx) {
/*  264 */       throw sqlEx;
/*  265 */     } catch (Exception e) {
/*  266 */       SQLException sqlEx = SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_Date", new Object[] { StringUtils.toString(dateAsBytes), Integer.valueOf(columnIndex + 1) }), "S1009", this.exceptionInterceptor);
/*      */ 
/*      */       
/*  269 */       sqlEx.initCause(e);
/*      */       
/*  271 */       throw sqlEx;
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
/*      */   public abstract Date getDateFast(int paramInt, MySQLConnection paramMySQLConnection, ResultSetImpl paramResultSetImpl, Calendar paramCalendar) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int getInt(int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract long getLong(int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Date getNativeDate(int columnIndex, byte[] bits, int offset, int length, MySQLConnection conn, ResultSetImpl rs, Calendar cal) throws SQLException {
/*  314 */     int year = 0;
/*  315 */     int month = 0;
/*  316 */     int day = 0;
/*      */     
/*  318 */     if (length != 0) {
/*  319 */       year = bits[offset + 0] & 0xFF | (bits[offset + 1] & 0xFF) << 8;
/*      */       
/*  321 */       month = bits[offset + 2];
/*  322 */       day = bits[offset + 3];
/*      */     } 
/*      */     
/*  325 */     if (length == 0 || (year == 0 && month == 0 && day == 0)) {
/*  326 */       if ("convertToNull".equals(conn.getZeroDateTimeBehavior()))
/*  327 */         return null; 
/*  328 */       if ("exception".equals(conn.getZeroDateTimeBehavior())) {
/*  329 */         throw SQLError.createSQLException("Value '0000-00-00' can not be represented as java.sql.Date", "S1009", this.exceptionInterceptor);
/*      */       }
/*      */ 
/*      */       
/*  333 */       year = 1;
/*  334 */       month = 1;
/*  335 */       day = 1;
/*      */     } 
/*      */     
/*  338 */     if (!rs.useLegacyDatetimeCode) {
/*  339 */       return TimeUtil.fastDateCreate(year, month, day, cal);
/*      */     }
/*      */     
/*  342 */     return rs.fastDateCreate((cal == null) ? rs.getCalendarInstanceForSessionOrNew() : cal, year, month, day);
/*      */   }
/*      */ 
/*      */   
/*      */   public abstract Date getNativeDate(int paramInt, MySQLConnection paramMySQLConnection, ResultSetImpl paramResultSetImpl, Calendar paramCalendar) throws SQLException;
/*      */ 
/*      */   
/*      */   protected Object getNativeDateTimeValue(int columnIndex, byte[] bits, int offset, int length, Calendar targetCalendar, int jdbcType, int mysqlType, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/*  350 */     int year = 0;
/*  351 */     int month = 0;
/*  352 */     int day = 0;
/*      */     
/*  354 */     int hour = 0;
/*  355 */     int minute = 0;
/*  356 */     int seconds = 0;
/*      */     
/*  358 */     int nanos = 0;
/*      */     
/*  360 */     if (bits == null)
/*      */     {
/*  362 */       return null;
/*      */     }
/*      */     
/*  365 */     Calendar sessionCalendar = conn.getUseJDBCCompliantTimezoneShift() ? conn.getUtcCalendar() : rs.getCalendarInstanceForSessionOrNew();
/*      */     
/*  367 */     boolean populatedFromDateTimeValue = false;
/*      */     
/*  369 */     switch (mysqlType) {
/*      */       case 7:
/*      */       case 12:
/*  372 */         populatedFromDateTimeValue = true;
/*      */         
/*  374 */         if (length != 0) {
/*  375 */           year = bits[offset + 0] & 0xFF | (bits[offset + 1] & 0xFF) << 8;
/*  376 */           month = bits[offset + 2];
/*  377 */           day = bits[offset + 3];
/*      */           
/*  379 */           if (length > 4) {
/*  380 */             hour = bits[offset + 4];
/*  381 */             minute = bits[offset + 5];
/*  382 */             seconds = bits[offset + 6];
/*      */           } 
/*      */           
/*  385 */           if (length > 7)
/*      */           {
/*  387 */             nanos = (bits[offset + 7] & 0xFF | (bits[offset + 8] & 0xFF) << 8 | (bits[offset + 9] & 0xFF) << 16 | (bits[offset + 10] & 0xFF) << 24) * 1000;
/*      */           }
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 10:
/*  394 */         populatedFromDateTimeValue = true;
/*      */         
/*  396 */         if (length != 0) {
/*  397 */           year = bits[offset + 0] & 0xFF | (bits[offset + 1] & 0xFF) << 8;
/*  398 */           month = bits[offset + 2];
/*  399 */           day = bits[offset + 3];
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 11:
/*  404 */         populatedFromDateTimeValue = true;
/*      */         
/*  406 */         if (length != 0) {
/*      */ 
/*      */           
/*  409 */           hour = bits[offset + 5];
/*  410 */           minute = bits[offset + 6];
/*  411 */           seconds = bits[offset + 7];
/*      */         } 
/*      */         
/*  414 */         year = 1970;
/*  415 */         month = 1;
/*  416 */         day = 1;
/*      */         break;
/*      */       
/*      */       default:
/*  420 */         populatedFromDateTimeValue = false;
/*      */         break;
/*      */     } 
/*  423 */     switch (jdbcType) {
/*      */       case 92:
/*  425 */         if (populatedFromDateTimeValue) {
/*  426 */           if (!rs.useLegacyDatetimeCode) {
/*  427 */             return TimeUtil.fastTimeCreate(hour, minute, seconds, targetCalendar, this.exceptionInterceptor);
/*      */           }
/*      */           
/*  430 */           Time time = TimeUtil.fastTimeCreate(rs.getCalendarInstanceForSessionOrNew(), hour, minute, seconds, this.exceptionInterceptor);
/*      */           
/*  432 */           Time adjustedTime = TimeUtil.changeTimezone(conn, sessionCalendar, targetCalendar, time, conn.getServerTimezoneTZ(), tz, rollForward);
/*      */           
/*  434 */           return adjustedTime;
/*      */         } 
/*      */         
/*  437 */         return rs.getNativeTimeViaParseConversion(columnIndex + 1, targetCalendar, tz, rollForward);
/*      */       
/*      */       case 91:
/*  440 */         if (populatedFromDateTimeValue) {
/*  441 */           if (year == 0 && month == 0 && day == 0) {
/*  442 */             if ("convertToNull".equals(conn.getZeroDateTimeBehavior()))
/*      */             {
/*  444 */               return null; } 
/*  445 */             if ("exception".equals(conn.getZeroDateTimeBehavior())) {
/*  446 */               throw new SQLException("Value '0000-00-00' can not be represented as java.sql.Date", "S1009");
/*      */             }
/*      */             
/*  449 */             year = 1;
/*  450 */             month = 1;
/*  451 */             day = 1;
/*      */           } 
/*      */           
/*  454 */           if (!rs.useLegacyDatetimeCode) {
/*  455 */             return TimeUtil.fastDateCreate(year, month, day, targetCalendar);
/*      */           }
/*      */           
/*  458 */           return rs.fastDateCreate(rs.getCalendarInstanceForSessionOrNew(), year, month, day);
/*      */         } 
/*      */         
/*  461 */         return rs.getNativeDateViaParseConversion(columnIndex + 1);
/*      */       case 93:
/*  463 */         if (populatedFromDateTimeValue) {
/*  464 */           if (year == 0 && month == 0 && day == 0) {
/*  465 */             if ("convertToNull".equals(conn.getZeroDateTimeBehavior()))
/*      */             {
/*  467 */               return null; } 
/*  468 */             if ("exception".equals(conn.getZeroDateTimeBehavior())) {
/*  469 */               throw new SQLException("Value '0000-00-00' can not be represented as java.sql.Timestamp", "S1009");
/*      */             }
/*      */             
/*  472 */             year = 1;
/*  473 */             month = 1;
/*  474 */             day = 1;
/*      */           } 
/*      */           
/*  477 */           if (!rs.useLegacyDatetimeCode) {
/*  478 */             return TimeUtil.fastTimestampCreate(tz, year, month, day, hour, minute, seconds, nanos);
/*      */           }
/*      */           
/*  481 */           boolean useGmtMillis = conn.getUseGmtMillisForDatetimes();
/*      */           
/*  483 */           Timestamp ts = rs.fastTimestampCreate(rs.getCalendarInstanceForSessionOrNew(), year, month, day, hour, minute, seconds, nanos, useGmtMillis);
/*      */ 
/*      */           
/*  486 */           Timestamp adjustedTs = TimeUtil.changeTimezone(conn, sessionCalendar, targetCalendar, ts, conn.getServerTimezoneTZ(), tz, rollForward);
/*      */           
/*  488 */           return adjustedTs;
/*      */         } 
/*      */         
/*  491 */         return rs.getNativeTimestampViaParseConversion(columnIndex + 1, targetCalendar, tz, rollForward);
/*      */     } 
/*      */     
/*  494 */     throw new SQLException("Internal error - conversion method doesn't support this type", "S1000");
/*      */   }
/*      */ 
/*      */   
/*      */   public abstract Object getNativeDateTimeValue(int paramInt1, Calendar paramCalendar, int paramInt2, int paramInt3, TimeZone paramTimeZone, boolean paramBoolean, MySQLConnection paramMySQLConnection, ResultSetImpl paramResultSetImpl) throws SQLException;
/*      */ 
/*      */   
/*      */   protected double getNativeDouble(byte[] bits, int offset) {
/*  502 */     long valueAsLong = (bits[offset + 0] & 0xFF) | (bits[offset + 1] & 0xFF) << 8L | (bits[offset + 2] & 0xFF) << 16L | (bits[offset + 3] & 0xFF) << 24L | (bits[offset + 4] & 0xFF) << 32L | (bits[offset + 5] & 0xFF) << 40L | (bits[offset + 6] & 0xFF) << 48L | (bits[offset + 7] & 0xFF) << 56L;
/*      */ 
/*      */ 
/*      */     
/*  506 */     return Double.longBitsToDouble(valueAsLong);
/*      */   }
/*      */   
/*      */   public abstract double getNativeDouble(int paramInt) throws SQLException;
/*      */   
/*      */   protected float getNativeFloat(byte[] bits, int offset) {
/*  512 */     int asInt = bits[offset + 0] & 0xFF | (bits[offset + 1] & 0xFF) << 8 | (bits[offset + 2] & 0xFF) << 16 | (bits[offset + 3] & 0xFF) << 24;
/*      */     
/*  514 */     return Float.intBitsToFloat(asInt);
/*      */   }
/*      */ 
/*      */   
/*      */   public abstract float getNativeFloat(int paramInt) throws SQLException;
/*      */   
/*      */   protected int getNativeInt(byte[] bits, int offset) {
/*  521 */     int valueAsInt = bits[offset + 0] & 0xFF | (bits[offset + 1] & 0xFF) << 8 | (bits[offset + 2] & 0xFF) << 16 | (bits[offset + 3] & 0xFF) << 24;
/*      */     
/*  523 */     return valueAsInt;
/*      */   }
/*      */   
/*      */   public abstract int getNativeInt(int paramInt) throws SQLException;
/*      */   
/*      */   protected long getNativeLong(byte[] bits, int offset) {
/*  529 */     long valueAsLong = (bits[offset + 0] & 0xFF) | (bits[offset + 1] & 0xFF) << 8L | (bits[offset + 2] & 0xFF) << 16L | (bits[offset + 3] & 0xFF) << 24L | (bits[offset + 4] & 0xFF) << 32L | (bits[offset + 5] & 0xFF) << 40L | (bits[offset + 6] & 0xFF) << 48L | (bits[offset + 7] & 0xFF) << 56L;
/*      */ 
/*      */ 
/*      */     
/*  533 */     return valueAsLong;
/*      */   }
/*      */   
/*      */   public abstract long getNativeLong(int paramInt) throws SQLException;
/*      */   
/*      */   protected short getNativeShort(byte[] bits, int offset) {
/*  539 */     short asShort = (short)(bits[offset + 0] & 0xFF | (bits[offset + 1] & 0xFF) << 8);
/*      */     
/*  541 */     return asShort;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract short getNativeShort(int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Time getNativeTime(int columnIndex, byte[] bits, int offset, int length, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/*  561 */     int hour = 0;
/*  562 */     int minute = 0;
/*  563 */     int seconds = 0;
/*      */     
/*  565 */     if (length != 0) {
/*      */ 
/*      */       
/*  568 */       hour = bits[offset + 5];
/*  569 */       minute = bits[offset + 6];
/*  570 */       seconds = bits[offset + 7];
/*      */     } 
/*      */     
/*  573 */     if (!rs.useLegacyDatetimeCode) {
/*  574 */       return TimeUtil.fastTimeCreate(hour, minute, seconds, targetCalendar, this.exceptionInterceptor);
/*      */     }
/*      */     
/*  577 */     Calendar sessionCalendar = rs.getCalendarInstanceForSessionOrNew();
/*      */     
/*  579 */     Time time = TimeUtil.fastTimeCreate(sessionCalendar, hour, minute, seconds, this.exceptionInterceptor);
/*      */     
/*  581 */     Time adjustedTime = TimeUtil.changeTimezone(conn, sessionCalendar, targetCalendar, time, conn.getServerTimezoneTZ(), tz, rollForward);
/*      */     
/*  583 */     return adjustedTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public abstract Time getNativeTime(int paramInt, Calendar paramCalendar, TimeZone paramTimeZone, boolean paramBoolean, MySQLConnection paramMySQLConnection, ResultSetImpl paramResultSetImpl) throws SQLException;
/*      */ 
/*      */   
/*      */   protected Timestamp getNativeTimestamp(byte[] bits, int offset, int length, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/*  591 */     int year = 0;
/*  592 */     int month = 0;
/*  593 */     int day = 0;
/*      */     
/*  595 */     int hour = 0;
/*  596 */     int minute = 0;
/*  597 */     int seconds = 0;
/*      */     
/*  599 */     int nanos = 0;
/*      */     
/*  601 */     if (length != 0) {
/*  602 */       year = bits[offset + 0] & 0xFF | (bits[offset + 1] & 0xFF) << 8;
/*  603 */       month = bits[offset + 2];
/*  604 */       day = bits[offset + 3];
/*      */       
/*  606 */       if (length > 4) {
/*  607 */         hour = bits[offset + 4];
/*  608 */         minute = bits[offset + 5];
/*  609 */         seconds = bits[offset + 6];
/*      */       } 
/*      */       
/*  612 */       if (length > 7)
/*      */       {
/*  614 */         nanos = (bits[offset + 7] & 0xFF | (bits[offset + 8] & 0xFF) << 8 | (bits[offset + 9] & 0xFF) << 16 | (bits[offset + 10] & 0xFF) << 24) * 1000;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  619 */     if (length == 0 || (year == 0 && month == 0 && day == 0)) {
/*  620 */       if ("convertToNull".equals(conn.getZeroDateTimeBehavior()))
/*      */       {
/*  622 */         return null; } 
/*  623 */       if ("exception".equals(conn.getZeroDateTimeBehavior())) {
/*  624 */         throw SQLError.createSQLException("Value '0000-00-00' can not be represented as java.sql.Timestamp", "S1009", this.exceptionInterceptor);
/*      */       }
/*      */ 
/*      */       
/*  628 */       year = 1;
/*  629 */       month = 1;
/*  630 */       day = 1;
/*      */     } 
/*      */     
/*  633 */     if (!rs.useLegacyDatetimeCode) {
/*  634 */       return TimeUtil.fastTimestampCreate(tz, year, month, day, hour, minute, seconds, nanos);
/*      */     }
/*      */     
/*  637 */     boolean useGmtMillis = conn.getUseGmtMillisForDatetimes();
/*      */     
/*  639 */     Calendar sessionCalendar = conn.getUseJDBCCompliantTimezoneShift() ? conn.getUtcCalendar() : rs.getCalendarInstanceForSessionOrNew();
/*      */     
/*  641 */     Timestamp ts = rs.fastTimestampCreate(sessionCalendar, year, month, day, hour, minute, seconds, nanos, useGmtMillis);
/*      */     
/*  643 */     Timestamp adjustedTs = TimeUtil.changeTimezone(conn, sessionCalendar, targetCalendar, ts, conn.getServerTimezoneTZ(), tz, rollForward);
/*      */     
/*  645 */     return adjustedTs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract Timestamp getNativeTimestamp(int paramInt, Calendar paramCalendar, TimeZone paramTimeZone, boolean paramBoolean, MySQLConnection paramMySQLConnection, ResultSetImpl paramResultSetImpl) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract Reader getReader(int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract String getString(int paramInt, String paramString, MySQLConnection paramMySQLConnection) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getString(String encoding, MySQLConnection conn, byte[] value, int offset, int length) throws SQLException {
/*  695 */     String stringVal = null;
/*      */     
/*  697 */     if (conn != null && conn.getUseUnicode()) {
/*      */       try {
/*  699 */         if (encoding == null) {
/*  700 */           stringVal = StringUtils.toString(value);
/*      */         } else {
/*  702 */           SingleByteCharsetConverter converter = conn.getCharsetConverter(encoding);
/*      */           
/*  704 */           if (converter != null) {
/*  705 */             stringVal = converter.toString(value, offset, length);
/*      */           } else {
/*  707 */             stringVal = StringUtils.toString(value, offset, length, encoding);
/*      */           } 
/*      */         } 
/*  710 */       } catch (UnsupportedEncodingException E) {
/*  711 */         throw SQLError.createSQLException(Messages.getString("ResultSet.Unsupported_character_encoding____101") + encoding + "'.", "0S100", this.exceptionInterceptor);
/*      */       } 
/*      */     } else {
/*      */       
/*  715 */       stringVal = StringUtils.toAsciiString(value, offset, length);
/*      */     } 
/*      */     
/*  718 */     return stringVal;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Time getTimeFast(int columnIndex, byte[] timeAsBytes, int offset, int fullLength, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/*  724 */     int hr = 0;
/*  725 */     int min = 0;
/*  726 */     int sec = 0;
/*  727 */     int nanos = 0;
/*      */     
/*  729 */     int decimalIndex = -1;
/*      */ 
/*      */     
/*      */     try {
/*  733 */       if (timeAsBytes == null) {
/*  734 */         return null;
/*      */       }
/*      */       
/*  737 */       boolean allZeroTime = true;
/*  738 */       boolean onlyTimePresent = false;
/*      */       int i;
/*  740 */       for (i = 0; i < fullLength; i++) {
/*  741 */         if (timeAsBytes[offset + i] == 58) {
/*  742 */           onlyTimePresent = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  747 */       for (i = 0; i < fullLength; i++) {
/*  748 */         if (timeAsBytes[offset + i] == 46) {
/*  749 */           decimalIndex = i;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  754 */       for (i = 0; i < fullLength; i++) {
/*  755 */         byte b = timeAsBytes[offset + i];
/*      */         
/*  757 */         if (b == 32 || b == 45 || b == 47) {
/*  758 */           onlyTimePresent = false;
/*      */         }
/*      */         
/*  761 */         if (b != 48 && b != 32 && b != 58 && b != 45 && b != 47 && b != 46) {
/*  762 */           allZeroTime = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/*  768 */       if (!onlyTimePresent && allZeroTime) {
/*  769 */         if ("convertToNull".equals(conn.getZeroDateTimeBehavior()))
/*  770 */           return null; 
/*  771 */         if ("exception".equals(conn.getZeroDateTimeBehavior())) {
/*  772 */           throw SQLError.createSQLException("Value '" + StringUtils.toString(timeAsBytes) + "' can not be represented as java.sql.Time", "S1009", this.exceptionInterceptor);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  777 */         return rs.fastTimeCreate(targetCalendar, 0, 0, 0);
/*      */       } 
/*      */       
/*  780 */       Field timeColField = this.metadata[columnIndex];
/*      */       
/*  782 */       int length = fullLength;
/*      */       
/*  784 */       if (decimalIndex != -1) {
/*      */         
/*  786 */         length = decimalIndex;
/*      */         
/*  788 */         if (decimalIndex + 2 <= fullLength) {
/*  789 */           nanos = StringUtils.getInt(timeAsBytes, offset + decimalIndex + 1, offset + fullLength);
/*      */           
/*  791 */           int numDigits = fullLength - decimalIndex + 1;
/*      */           
/*  793 */           if (numDigits < 9) {
/*  794 */             int factor = (int)Math.pow(10.0D, (9 - numDigits));
/*  795 */             nanos *= factor;
/*      */           } 
/*      */         } else {
/*  798 */           throw new IllegalArgumentException();
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  807 */       if (timeColField.getMysqlType() == 7) {
/*      */         
/*  809 */         switch (length) {
/*      */           
/*      */           case 19:
/*  812 */             hr = StringUtils.getInt(timeAsBytes, offset + length - 8, offset + length - 6);
/*  813 */             min = StringUtils.getInt(timeAsBytes, offset + length - 5, offset + length - 3);
/*  814 */             sec = StringUtils.getInt(timeAsBytes, offset + length - 2, offset + length);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 12:
/*      */           case 14:
/*  820 */             hr = StringUtils.getInt(timeAsBytes, offset + length - 6, offset + length - 4);
/*  821 */             min = StringUtils.getInt(timeAsBytes, offset + length - 4, offset + length - 2);
/*  822 */             sec = StringUtils.getInt(timeAsBytes, offset + length - 2, offset + length);
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 10:
/*  828 */             hr = StringUtils.getInt(timeAsBytes, offset + 6, offset + 8);
/*  829 */             min = StringUtils.getInt(timeAsBytes, offset + 8, offset + 10);
/*  830 */             sec = 0;
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/*  836 */             throw SQLError.createSQLException(Messages.getString("ResultSet.Timestamp_too_small_to_convert_to_Time_value_in_column__257") + (columnIndex + 1) + "(" + timeColField + ").", "S1009", this.exceptionInterceptor);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  841 */         SQLWarning precisionLost = new SQLWarning(Messages.getString("ResultSet.Precision_lost_converting_TIMESTAMP_to_Time_with_getTime()_on_column__261") + columnIndex + "(" + timeColField + ").");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  849 */       else if (timeColField.getMysqlType() == 12) {
/*  850 */         hr = StringUtils.getInt(timeAsBytes, offset + 11, offset + 13);
/*  851 */         min = StringUtils.getInt(timeAsBytes, offset + 14, offset + 16);
/*  852 */         sec = StringUtils.getInt(timeAsBytes, offset + 17, offset + 19);
/*      */ 
/*      */         
/*  855 */         SQLWarning precisionLost = new SQLWarning(Messages.getString("ResultSet.Precision_lost_converting_DATETIME_to_Time_with_getTime()_on_column__264") + (columnIndex + 1) + "(" + timeColField + ").");
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */         
/*  864 */         if (timeColField.getMysqlType() == 10) {
/*  865 */           return rs.fastTimeCreate(null, 0, 0, 0);
/*      */         }
/*      */ 
/*      */         
/*  869 */         if (length != 5 && length != 8) {
/*  870 */           throw SQLError.createSQLException(Messages.getString("ResultSet.Bad_format_for_Time____267") + StringUtils.toString(timeAsBytes) + Messages.getString("ResultSet.___in_column__268") + (columnIndex + 1), "S1009", this.exceptionInterceptor);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  876 */         hr = StringUtils.getInt(timeAsBytes, offset + 0, offset + 2);
/*  877 */         min = StringUtils.getInt(timeAsBytes, offset + 3, offset + 5);
/*  878 */         sec = (length == 5) ? 0 : StringUtils.getInt(timeAsBytes, offset + 6, offset + 8);
/*      */       } 
/*      */       
/*  881 */       Calendar sessionCalendar = rs.getCalendarInstanceForSessionOrNew();
/*      */       
/*  883 */       if (!rs.useLegacyDatetimeCode) {
/*      */ 
/*      */ 
/*      */         
/*  887 */         if (targetCalendar == null) {
/*  888 */           targetCalendar = Calendar.getInstance(tz, Locale.US);
/*      */         }
/*  890 */         return rs.fastTimeCreate(targetCalendar, hr, min, sec);
/*      */       } 
/*      */       
/*  893 */       return TimeUtil.changeTimezone(conn, sessionCalendar, targetCalendar, rs.fastTimeCreate(sessionCalendar, hr, min, sec), conn.getServerTimezoneTZ(), tz, rollForward);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  898 */     catch (RuntimeException ex) {
/*  899 */       SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", this.exceptionInterceptor);
/*  900 */       sqlEx.initCause(ex);
/*      */       
/*  902 */       throw sqlEx;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public abstract Time getTimeFast(int paramInt, Calendar paramCalendar, TimeZone paramTimeZone, boolean paramBoolean, MySQLConnection paramMySQLConnection, ResultSetImpl paramResultSetImpl) throws SQLException;
/*      */   
/*      */   protected Timestamp getTimestampFast(int columnIndex, byte[] timestampAsBytes, int offset, int length, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs, boolean useGmtMillis, boolean useJDBCCompliantTimezoneShift) throws SQLException {
/*      */     try {
/*      */       boolean hasDash, hasColon;
/*      */       int k;
/*  913 */       Calendar sessionCalendar = useJDBCCompliantTimezoneShift ? conn.getUtcCalendar() : rs.getCalendarInstanceForSessionOrNew();
/*  914 */       sessionCalendar = TimeUtil.setProlepticIfNeeded(sessionCalendar, targetCalendar);
/*      */       
/*  916 */       boolean allZeroTimestamp = true;
/*      */       
/*  918 */       boolean onlyTimePresent = false;
/*      */       int i;
/*  920 */       for (i = 0; i < length; i++) {
/*  921 */         if (timestampAsBytes[offset + i] == 58) {
/*  922 */           onlyTimePresent = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  927 */       for (i = 0; i < length; i++) {
/*  928 */         byte b = timestampAsBytes[offset + i];
/*      */         
/*  930 */         if (b == 32 || b == 45 || b == 47) {
/*  931 */           onlyTimePresent = false;
/*      */         }
/*      */         
/*  934 */         if (b != 48 && b != 32 && b != 58 && b != 45 && b != 47 && b != 46) {
/*  935 */           allZeroTimestamp = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/*  941 */       if (!onlyTimePresent && allZeroTimestamp) {
/*      */         
/*  943 */         if ("convertToNull".equals(conn.getZeroDateTimeBehavior()))
/*      */         {
/*  945 */           return null; } 
/*  946 */         if ("exception".equals(conn.getZeroDateTimeBehavior())) {
/*  947 */           throw SQLError.createSQLException("Value '" + StringUtils.toString(timestampAsBytes) + "' can not be represented as java.sql.Timestamp", "S1009", this.exceptionInterceptor);
/*      */         }
/*      */ 
/*      */         
/*  951 */         if (!rs.useLegacyDatetimeCode) {
/*  952 */           return TimeUtil.fastTimestampCreate(tz, 1, 1, 1, 0, 0, 0, 0);
/*      */         }
/*      */         
/*  955 */         return rs.fastTimestampCreate(null, 1, 1, 1, 0, 0, 0, 0, useGmtMillis);
/*      */       } 
/*  957 */       if (this.metadata[columnIndex].getMysqlType() == 13) {
/*      */         
/*  959 */         if (!rs.useLegacyDatetimeCode) {
/*  960 */           return TimeUtil.fastTimestampCreate(tz, StringUtils.getInt(timestampAsBytes, offset, 4), 1, 1, 0, 0, 0, 0);
/*      */         }
/*      */         
/*  963 */         return TimeUtil.changeTimezone(conn, sessionCalendar, targetCalendar, rs.fastTimestampCreate(sessionCalendar, StringUtils.getInt(timestampAsBytes, offset, 4), 1, 1, 0, 0, 0, 0, useGmtMillis), conn.getServerTimezoneTZ(), tz, rollForward);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  969 */       int year = 0;
/*  970 */       int month = 0;
/*  971 */       int day = 0;
/*  972 */       int hour = 0;
/*  973 */       int minutes = 0;
/*  974 */       int seconds = 0;
/*  975 */       int nanos = 0;
/*      */ 
/*      */       
/*  978 */       int decimalIndex = -1;
/*  979 */       for (int j = 0; j < length; j++) {
/*  980 */         if (timestampAsBytes[offset + j] == 46) {
/*  981 */           decimalIndex = j;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  986 */       if (decimalIndex == offset + length - 1) {
/*      */         
/*  988 */         length--;
/*      */       }
/*  990 */       else if (decimalIndex != -1) {
/*  991 */         if (decimalIndex + 2 <= length) {
/*  992 */           nanos = StringUtils.getInt(timestampAsBytes, offset + decimalIndex + 1, offset + length);
/*      */           
/*  994 */           int numDigits = length - decimalIndex + 1;
/*      */           
/*  996 */           if (numDigits < 9) {
/*  997 */             int factor = (int)Math.pow(10.0D, (9 - numDigits));
/*  998 */             nanos *= factor;
/*      */           } 
/*      */         } else {
/* 1001 */           throw new IllegalArgumentException();
/*      */         } 
/*      */ 
/*      */         
/* 1005 */         length = decimalIndex;
/*      */       } 
/*      */       
/* 1008 */       switch (length) {
/*      */         case 19:
/*      */         case 20:
/*      */         case 21:
/*      */         case 22:
/*      */         case 23:
/*      */         case 24:
/*      */         case 25:
/*      */         case 26:
/*      */         case 29:
/* 1018 */           year = StringUtils.getInt(timestampAsBytes, offset + 0, offset + 4);
/* 1019 */           month = StringUtils.getInt(timestampAsBytes, offset + 5, offset + 7);
/* 1020 */           day = StringUtils.getInt(timestampAsBytes, offset + 8, offset + 10);
/* 1021 */           hour = StringUtils.getInt(timestampAsBytes, offset + 11, offset + 13);
/* 1022 */           minutes = StringUtils.getInt(timestampAsBytes, offset + 14, offset + 16);
/* 1023 */           seconds = StringUtils.getInt(timestampAsBytes, offset + 17, offset + 19);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 14:
/* 1029 */           year = StringUtils.getInt(timestampAsBytes, offset + 0, offset + 4);
/* 1030 */           month = StringUtils.getInt(timestampAsBytes, offset + 4, offset + 6);
/* 1031 */           day = StringUtils.getInt(timestampAsBytes, offset + 6, offset + 8);
/* 1032 */           hour = StringUtils.getInt(timestampAsBytes, offset + 8, offset + 10);
/* 1033 */           minutes = StringUtils.getInt(timestampAsBytes, offset + 10, offset + 12);
/* 1034 */           seconds = StringUtils.getInt(timestampAsBytes, offset + 12, offset + 14);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 12:
/* 1040 */           year = StringUtils.getInt(timestampAsBytes, offset + 0, offset + 2);
/*      */           
/* 1042 */           if (year <= 69) {
/* 1043 */             year += 100;
/*      */           }
/*      */           
/* 1046 */           year += 1900;
/*      */           
/* 1048 */           month = StringUtils.getInt(timestampAsBytes, offset + 2, offset + 4);
/* 1049 */           day = StringUtils.getInt(timestampAsBytes, offset + 4, offset + 6);
/* 1050 */           hour = StringUtils.getInt(timestampAsBytes, offset + 6, offset + 8);
/* 1051 */           minutes = StringUtils.getInt(timestampAsBytes, offset + 8, offset + 10);
/* 1052 */           seconds = StringUtils.getInt(timestampAsBytes, offset + 10, offset + 12);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 10:
/* 1058 */           hasDash = false;
/*      */           
/* 1060 */           for (k = 0; k < length; k++) {
/* 1061 */             if (timestampAsBytes[offset + k] == 45) {
/* 1062 */               hasDash = true;
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/* 1067 */           if (this.metadata[columnIndex].getMysqlType() == 10 || hasDash) {
/* 1068 */             year = StringUtils.getInt(timestampAsBytes, offset + 0, offset + 4);
/* 1069 */             month = StringUtils.getInt(timestampAsBytes, offset + 5, offset + 7);
/* 1070 */             day = StringUtils.getInt(timestampAsBytes, offset + 8, offset + 10);
/* 1071 */             hour = 0;
/* 1072 */             minutes = 0; break;
/*      */           } 
/* 1074 */           year = StringUtils.getInt(timestampAsBytes, offset + 0, offset + 2);
/*      */           
/* 1076 */           if (year <= 69) {
/* 1077 */             year += 100;
/*      */           }
/*      */           
/* 1080 */           month = StringUtils.getInt(timestampAsBytes, offset + 2, offset + 4);
/* 1081 */           day = StringUtils.getInt(timestampAsBytes, offset + 4, offset + 6);
/* 1082 */           hour = StringUtils.getInt(timestampAsBytes, offset + 6, offset + 8);
/* 1083 */           minutes = StringUtils.getInt(timestampAsBytes, offset + 8, offset + 10);
/*      */           
/* 1085 */           year += 1900;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 8:
/* 1092 */           hasColon = false;
/*      */           
/* 1094 */           for (k = 0; k < length; k++) {
/* 1095 */             if (timestampAsBytes[offset + k] == 58) {
/* 1096 */               hasColon = true;
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/* 1101 */           if (hasColon) {
/* 1102 */             hour = StringUtils.getInt(timestampAsBytes, offset + 0, offset + 2);
/* 1103 */             minutes = StringUtils.getInt(timestampAsBytes, offset + 3, offset + 5);
/* 1104 */             seconds = StringUtils.getInt(timestampAsBytes, offset + 6, offset + 8);
/*      */             
/* 1106 */             year = 1970;
/* 1107 */             month = 1;
/* 1108 */             day = 1;
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/* 1113 */           year = StringUtils.getInt(timestampAsBytes, offset + 0, offset + 4);
/* 1114 */           month = StringUtils.getInt(timestampAsBytes, offset + 4, offset + 6);
/* 1115 */           day = StringUtils.getInt(timestampAsBytes, offset + 6, offset + 8);
/*      */           
/* 1117 */           year -= 1900;
/* 1118 */           month--;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 6:
/* 1124 */           year = StringUtils.getInt(timestampAsBytes, offset + 0, offset + 2);
/*      */           
/* 1126 */           if (year <= 69) {
/* 1127 */             year += 100;
/*      */           }
/*      */           
/* 1130 */           year += 1900;
/*      */           
/* 1132 */           month = StringUtils.getInt(timestampAsBytes, offset + 2, offset + 4);
/* 1133 */           day = StringUtils.getInt(timestampAsBytes, offset + 4, offset + 6);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 4:
/* 1139 */           year = StringUtils.getInt(timestampAsBytes, offset + 0, offset + 2);
/*      */           
/* 1141 */           if (year <= 69) {
/* 1142 */             year += 100;
/*      */           }
/*      */           
/* 1145 */           month = StringUtils.getInt(timestampAsBytes, offset + 2, offset + 4);
/*      */           
/* 1147 */           day = 1;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/* 1153 */           year = StringUtils.getInt(timestampAsBytes, offset + 0, offset + 2);
/*      */           
/* 1155 */           if (year <= 69) {
/* 1156 */             year += 100;
/*      */           }
/*      */           
/* 1159 */           year += 1900;
/* 1160 */           month = 1;
/* 1161 */           day = 1;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 1167 */           throw new SQLException("Bad format for Timestamp '" + StringUtils.toString(timestampAsBytes) + "' in column " + (columnIndex + 1) + ".", "S1009");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1172 */       if (!rs.useLegacyDatetimeCode) {
/* 1173 */         return TimeUtil.fastTimestampCreate(tz, year, month, day, hour, minutes, seconds, nanos);
/*      */       }
/*      */       
/* 1176 */       return TimeUtil.changeTimezone(conn, sessionCalendar, targetCalendar, rs.fastTimestampCreate(sessionCalendar, year, month, day, hour, minutes, seconds, nanos, useGmtMillis), conn.getServerTimezoneTZ(), tz, rollForward);
/*      */ 
/*      */     
/*      */     }
/* 1180 */     catch (RuntimeException e) {
/* 1181 */       SQLException sqlEx = SQLError.createSQLException("Cannot convert value '" + getString(columnIndex, "ISO8859_1", conn) + "' from column " + (columnIndex + 1) + " to TIMESTAMP.", "S1009", this.exceptionInterceptor);
/*      */ 
/*      */       
/* 1184 */       sqlEx.initCause(e);
/*      */       
/* 1186 */       throw sqlEx;
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
/*      */   public abstract Timestamp getTimestampFast(int paramInt, Calendar paramCalendar, TimeZone paramTimeZone, boolean paramBoolean1, MySQLConnection paramMySQLConnection, ResultSetImpl paramResultSetImpl, boolean paramBoolean2, boolean paramBoolean3) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract boolean isFloatingPointNumber(int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract boolean isNull(int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract long length(int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void setColumnValue(int paramInt, byte[] paramArrayOfbyte) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSetRow setMetadata(Field[] f) throws SQLException {
/* 1251 */     this.metadata = f;
/*      */     
/* 1253 */     return this;
/*      */   }
/*      */   
/*      */   public abstract int getBytesSize();
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ResultSetRow.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */