/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.sql.Date;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Locale;
/*     */ import java.util.Properties;
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
/*     */ public class TimeUtil
/*     */ {
/*  44 */   static final TimeZone GMT_TIMEZONE = TimeZone.getTimeZone("GMT");
/*     */ 
/*     */   
/*  47 */   private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getDefault();
/*     */ 
/*     */   
/*     */   private static final String TIME_ZONE_MAPPINGS_RESOURCE = "/com/mysql/jdbc/TimeZoneMapping.properties";
/*     */   
/*  52 */   private static Properties timeZoneMappings = null;
/*     */ 
/*     */   
/*     */   protected static final Method systemNanoTimeMethod;
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  60 */       method = System.class.getMethod("nanoTime", (Class[])null);
/*  61 */     } catch (SecurityException e) {
/*  62 */       method = null;
/*  63 */     } catch (NoSuchMethodException e) {
/*  64 */       method = null;
/*     */     } 
/*     */     
/*  67 */     systemNanoTimeMethod = method;
/*     */   } static {
/*     */     Method method;
/*     */   } public static boolean nanoTimeAvailable() {
/*  71 */     return (systemNanoTimeMethod != null);
/*     */   }
/*     */   
/*     */   public static final TimeZone getDefaultTimeZone(boolean useCache) {
/*  75 */     return useCache ? (TimeZone)DEFAULT_TIMEZONE.clone() : (TimeZone)TimeZone.getDefault().clone();
/*     */   }
/*     */   
/*     */   public static long getCurrentTimeNanosOrMillis() {
/*  79 */     if (systemNanoTimeMethod != null) {
/*     */       try {
/*  81 */         return ((Long)systemNanoTimeMethod.invoke(null, (Object[])null)).longValue();
/*  82 */       } catch (IllegalArgumentException e) {
/*     */       
/*  84 */       } catch (IllegalAccessException e) {
/*     */       
/*  86 */       } catch (InvocationTargetException e) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  91 */     return System.currentTimeMillis();
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
/*     */   public static Time changeTimezone(MySQLConnection conn, Calendar sessionCalendar, Calendar targetCalendar, Time t, TimeZone fromTz, TimeZone toTz, boolean rollForward) {
/* 110 */     if (conn != null) {
/* 111 */       if (conn.getUseTimezone() && !conn.getNoTimezoneConversionForTimeType()) {
/*     */         
/* 113 */         Calendar fromCal = Calendar.getInstance(fromTz);
/* 114 */         fromCal.setTime(t);
/*     */         
/* 116 */         int fromOffset = fromCal.get(15) + fromCal.get(16);
/* 117 */         Calendar toCal = Calendar.getInstance(toTz);
/* 118 */         toCal.setTime(t);
/*     */         
/* 120 */         int toOffset = toCal.get(15) + toCal.get(16);
/* 121 */         int offsetDiff = fromOffset - toOffset;
/* 122 */         long toTime = toCal.getTime().getTime();
/*     */         
/* 124 */         if (rollForward) {
/* 125 */           toTime += offsetDiff;
/*     */         } else {
/* 127 */           toTime -= offsetDiff;
/*     */         } 
/*     */         
/* 130 */         Time changedTime = new Time(toTime);
/*     */         
/* 132 */         return changedTime;
/* 133 */       }  if (conn.getUseJDBCCompliantTimezoneShift() && 
/* 134 */         targetCalendar != null) {
/*     */         
/* 136 */         Time adjustedTime = new Time(jdbcCompliantZoneShift(sessionCalendar, targetCalendar, t));
/*     */         
/* 138 */         return adjustedTime;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 143 */     return t;
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
/*     */   public static Timestamp changeTimezone(MySQLConnection conn, Calendar sessionCalendar, Calendar targetCalendar, Timestamp tstamp, TimeZone fromTz, TimeZone toTz, boolean rollForward) {
/* 162 */     if (conn != null) {
/* 163 */       if (conn.getUseTimezone()) {
/*     */         
/* 165 */         Calendar fromCal = Calendar.getInstance(fromTz);
/* 166 */         fromCal.setTime(tstamp);
/*     */         
/* 168 */         int fromOffset = fromCal.get(15) + fromCal.get(16);
/* 169 */         Calendar toCal = Calendar.getInstance(toTz);
/* 170 */         toCal.setTime(tstamp);
/*     */         
/* 172 */         int toOffset = toCal.get(15) + toCal.get(16);
/* 173 */         int offsetDiff = fromOffset - toOffset;
/* 174 */         long toTime = toCal.getTime().getTime();
/*     */         
/* 176 */         if (rollForward) {
/* 177 */           toTime += offsetDiff;
/*     */         } else {
/* 179 */           toTime -= offsetDiff;
/*     */         } 
/*     */         
/* 182 */         Timestamp changedTimestamp = new Timestamp(toTime);
/*     */         
/* 184 */         return changedTimestamp;
/* 185 */       }  if (conn.getUseJDBCCompliantTimezoneShift() && 
/* 186 */         targetCalendar != null) {
/*     */         
/* 188 */         Timestamp adjustedTimestamp = new Timestamp(jdbcCompliantZoneShift(sessionCalendar, targetCalendar, tstamp));
/*     */         
/* 190 */         adjustedTimestamp.setNanos(tstamp.getNanos());
/*     */         
/* 192 */         return adjustedTimestamp;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 197 */     return tstamp;
/*     */   }
/*     */   
/*     */   private static long jdbcCompliantZoneShift(Calendar sessionCalendar, Calendar targetCalendar, Date dt) {
/* 201 */     if (sessionCalendar == null) {
/* 202 */       sessionCalendar = new GregorianCalendar();
/*     */     }
/*     */     
/* 205 */     synchronized (sessionCalendar) {
/*     */ 
/*     */       
/* 208 */       Date origCalDate = targetCalendar.getTime();
/* 209 */       Date origSessionDate = sessionCalendar.getTime();
/*     */       
/*     */       try {
/* 212 */         sessionCalendar.setTime(dt);
/*     */         
/* 214 */         targetCalendar.set(1, sessionCalendar.get(1));
/* 215 */         targetCalendar.set(2, sessionCalendar.get(2));
/* 216 */         targetCalendar.set(5, sessionCalendar.get(5));
/*     */         
/* 218 */         targetCalendar.set(11, sessionCalendar.get(11));
/* 219 */         targetCalendar.set(12, sessionCalendar.get(12));
/* 220 */         targetCalendar.set(13, sessionCalendar.get(13));
/* 221 */         targetCalendar.set(14, sessionCalendar.get(14));
/*     */         
/* 223 */         return targetCalendar.getTime().getTime();
/*     */       } finally {
/*     */         
/* 226 */         sessionCalendar.setTime(origSessionDate);
/* 227 */         targetCalendar.setTime(origCalDate);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static final Date fastDateCreate(boolean useGmtConversion, Calendar gmtCalIfNeeded, Calendar cal, int year, int month, int day) {
/* 234 */     Calendar dateCal = cal;
/*     */     
/* 236 */     if (useGmtConversion) {
/*     */       
/* 238 */       if (gmtCalIfNeeded == null) {
/* 239 */         gmtCalIfNeeded = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
/*     */       }
/*     */       
/* 242 */       dateCal = gmtCalIfNeeded;
/*     */     } 
/*     */     
/* 245 */     synchronized (dateCal) {
/* 246 */       Date origCalDate = dateCal.getTime();
/*     */       try {
/* 248 */         dateCal.clear();
/* 249 */         dateCal.set(14, 0);
/*     */ 
/*     */         
/* 252 */         dateCal.set(year, month - 1, day, 0, 0, 0);
/*     */         
/* 254 */         long dateAsMillis = dateCal.getTimeInMillis();
/*     */         
/* 256 */         return new Date(dateAsMillis);
/*     */       } finally {
/* 258 */         dateCal.setTime(origCalDate);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static final Date fastDateCreate(int year, int month, int day, Calendar targetCalendar) {
/* 266 */     Calendar dateCal = (targetCalendar == null) ? new GregorianCalendar() : targetCalendar;
/*     */     
/* 268 */     synchronized (dateCal) {
/* 269 */       Date origCalDate = dateCal.getTime();
/*     */       try {
/* 271 */         dateCal.clear();
/*     */ 
/*     */         
/* 274 */         dateCal.set(year, month - 1, day, 0, 0, 0);
/* 275 */         dateCal.set(14, 0);
/*     */         
/* 277 */         long dateAsMillis = dateCal.getTimeInMillis();
/*     */         
/* 279 */         return new Date(dateAsMillis);
/*     */       } finally {
/* 281 */         dateCal.setTime(origCalDate);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static final Time fastTimeCreate(Calendar cal, int hour, int minute, int second, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/* 287 */     if (hour < 0 || hour > 24) {
/* 288 */       throw SQLError.createSQLException("Illegal hour value '" + hour + "' for java.sql.Time type in value '" + timeFormattedString(hour, minute, second) + ".", "S1009", exceptionInterceptor);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 293 */     if (minute < 0 || minute > 59) {
/* 294 */       throw SQLError.createSQLException("Illegal minute value '" + minute + "' for java.sql.Time type in value '" + timeFormattedString(hour, minute, second) + ".", "S1009", exceptionInterceptor);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 299 */     if (second < 0 || second > 59) {
/* 300 */       throw SQLError.createSQLException("Illegal minute value '" + second + "' for java.sql.Time type in value '" + timeFormattedString(hour, minute, second) + ".", "S1009", exceptionInterceptor);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 305 */     synchronized (cal) {
/* 306 */       Date origCalDate = cal.getTime();
/*     */       try {
/* 308 */         cal.clear();
/*     */ 
/*     */         
/* 311 */         cal.set(1970, 0, 1, hour, minute, second);
/*     */         
/* 313 */         long timeAsMillis = cal.getTimeInMillis();
/*     */         
/* 315 */         return new Time(timeAsMillis);
/*     */       } finally {
/* 317 */         cal.setTime(origCalDate);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static final Time fastTimeCreate(int hour, int minute, int second, Calendar targetCalendar, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/* 323 */     if (hour < 0 || hour > 23) {
/* 324 */       throw SQLError.createSQLException("Illegal hour value '" + hour + "' for java.sql.Time type in value '" + timeFormattedString(hour, minute, second) + ".", "S1009", exceptionInterceptor);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 329 */     if (minute < 0 || minute > 59) {
/* 330 */       throw SQLError.createSQLException("Illegal minute value '" + minute + "' for java.sql.Time type in value '" + timeFormattedString(hour, minute, second) + ".", "S1009", exceptionInterceptor);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 335 */     if (second < 0 || second > 59) {
/* 336 */       throw SQLError.createSQLException("Illegal minute value '" + second + "' for java.sql.Time type in value '" + timeFormattedString(hour, minute, second) + ".", "S1009", exceptionInterceptor);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 341 */     Calendar cal = (targetCalendar == null) ? new GregorianCalendar() : targetCalendar;
/*     */     
/* 343 */     synchronized (cal) {
/* 344 */       Date origCalDate = cal.getTime();
/*     */       try {
/* 346 */         cal.clear();
/*     */ 
/*     */         
/* 349 */         cal.set(1970, 0, 1, hour, minute, second);
/*     */         
/* 351 */         long timeAsMillis = cal.getTimeInMillis();
/*     */         
/* 353 */         return new Time(timeAsMillis);
/*     */       } finally {
/* 355 */         cal.setTime(origCalDate);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static final Timestamp fastTimestampCreate(boolean useGmtConversion, Calendar gmtCalIfNeeded, Calendar cal, int year, int month, int day, int hour, int minute, int seconds, int secondsPart) {
/* 363 */     synchronized (cal) {
/* 364 */       Date origCalDate = cal.getTime();
/*     */       try {
/* 366 */         cal.clear();
/*     */ 
/*     */         
/* 369 */         cal.set(year, month - 1, day, hour, minute, seconds);
/*     */         
/* 371 */         int offsetDiff = 0;
/*     */         
/* 373 */         if (useGmtConversion) {
/* 374 */           int fromOffset = cal.get(15) + cal.get(16);
/*     */           
/* 376 */           if (gmtCalIfNeeded == null) {
/* 377 */             gmtCalIfNeeded = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
/*     */           }
/* 379 */           gmtCalIfNeeded.clear();
/*     */           
/* 381 */           gmtCalIfNeeded.setTimeInMillis(cal.getTimeInMillis());
/*     */           
/* 383 */           int toOffset = gmtCalIfNeeded.get(15) + gmtCalIfNeeded.get(16);
/* 384 */           offsetDiff = fromOffset - toOffset;
/*     */         } 
/*     */         
/* 387 */         if (secondsPart != 0) {
/* 388 */           cal.set(14, secondsPart / 1000000);
/*     */         }
/*     */         
/* 391 */         long tsAsMillis = cal.getTimeInMillis();
/*     */         
/* 393 */         Timestamp ts = new Timestamp(tsAsMillis + offsetDiff);
/*     */         
/* 395 */         ts.setNanos(secondsPart);
/*     */         
/* 397 */         return ts;
/*     */       } finally {
/* 399 */         cal.setTime(origCalDate);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static final Timestamp fastTimestampCreate(TimeZone tz, int year, int month, int day, int hour, int minute, int seconds, int secondsPart) {
/* 405 */     Calendar cal = (tz == null) ? new GregorianCalendar() : new GregorianCalendar(tz);
/* 406 */     cal.clear();
/*     */ 
/*     */     
/* 409 */     cal.set(year, month - 1, day, hour, minute, seconds);
/*     */     
/* 411 */     long tsAsMillis = cal.getTimeInMillis();
/*     */     
/* 413 */     Timestamp ts = new Timestamp(tsAsMillis);
/* 414 */     ts.setNanos(secondsPart);
/*     */     
/* 416 */     return ts;
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
/*     */   public static String getCanonicalTimezone(String timezoneStr, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/* 431 */     if (timezoneStr == null) {
/* 432 */       return null;
/*     */     }
/*     */     
/* 435 */     timezoneStr = timezoneStr.trim();
/*     */ 
/*     */     
/* 438 */     if (timezoneStr.length() > 2 && (
/* 439 */       timezoneStr.charAt(0) == '+' || timezoneStr.charAt(0) == '-') && Character.isDigit(timezoneStr.charAt(1))) {
/* 440 */       return "GMT" + timezoneStr;
/*     */     }
/*     */ 
/*     */     
/* 444 */     synchronized (TimeUtil.class) {
/* 445 */       if (timeZoneMappings == null) {
/* 446 */         loadTimeZoneMappings(exceptionInterceptor);
/*     */       }
/*     */     } 
/*     */     
/*     */     String canonicalTz;
/* 451 */     if ((canonicalTz = timeZoneMappings.getProperty(timezoneStr)) != null) {
/* 452 */       return canonicalTz;
/*     */     }
/*     */     
/* 455 */     throw SQLError.createSQLException(Messages.getString("TimeUtil.UnrecognizedTimezoneId", new Object[] { timezoneStr }), "01S00", exceptionInterceptor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String timeFormattedString(int hours, int minutes, int seconds) {
/* 462 */     StringBuilder buf = new StringBuilder(8);
/* 463 */     if (hours < 10) {
/* 464 */       buf.append("0");
/*     */     }
/*     */     
/* 467 */     buf.append(hours);
/* 468 */     buf.append(":");
/*     */     
/* 470 */     if (minutes < 10) {
/* 471 */       buf.append("0");
/*     */     }
/*     */     
/* 474 */     buf.append(minutes);
/* 475 */     buf.append(":");
/*     */     
/* 477 */     if (seconds < 10) {
/* 478 */       buf.append("0");
/*     */     }
/*     */     
/* 481 */     buf.append(seconds);
/*     */     
/* 483 */     return buf.toString();
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
/*     */   public static Timestamp adjustTimestampNanosPrecision(Timestamp ts, int fsp, boolean serverRoundFracSecs) throws SQLException {
/* 502 */     if (fsp < 0 || fsp > 6) {
/* 503 */       throw SQLError.createSQLException("fsp value must be in 0 to 6 range.", "S1009", null);
/*     */     }
/*     */     
/* 506 */     Timestamp res = (Timestamp)ts.clone();
/* 507 */     int nanos = res.getNanos();
/* 508 */     double tail = Math.pow(10.0D, (9 - fsp));
/*     */     
/* 510 */     if (serverRoundFracSecs) {
/* 511 */       nanos = (int)Math.round(nanos / tail) * (int)tail;
/* 512 */       if (nanos > 999999999) {
/* 513 */         nanos %= 1000000000;
/* 514 */         res.setTime(res.getTime() + 1000L);
/*     */       } 
/*     */     } else {
/* 517 */       nanos = (int)(nanos / tail) * (int)tail;
/*     */     } 
/* 519 */     res.setNanos(nanos);
/*     */     
/* 521 */     return res;
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
/*     */   public static String formatNanos(int nanos, boolean serverSupportsFracSecs, int fsp) throws SQLException {
/* 540 */     if (nanos < 0 || nanos > 999999999) {
/* 541 */       throw SQLError.createSQLException("nanos value must be in 0 to 999999999 range but was " + nanos, "S1009", null);
/*     */     }
/* 543 */     if (fsp < 0 || fsp > 6) {
/* 544 */       throw SQLError.createSQLException("fsp value must be in 0 to 6 range but was " + fsp, "S1009", null);
/*     */     }
/*     */     
/* 547 */     if (!serverSupportsFracSecs || fsp == 0 || nanos == 0) {
/* 548 */       return "0";
/*     */     }
/*     */ 
/*     */     
/* 552 */     nanos = (int)(nanos / Math.pow(10.0D, (9 - fsp)));
/* 553 */     if (nanos == 0) {
/* 554 */       return "0";
/*     */     }
/*     */     
/* 557 */     String nanosString = Integer.toString(nanos);
/* 558 */     String zeroPadding = "000000000";
/*     */     
/* 560 */     nanosString = "000000000".substring(0, fsp - nanosString.length()) + nanosString;
/*     */     
/* 562 */     int pos = fsp - 1;
/*     */     
/* 564 */     while (nanosString.charAt(pos) == '0') {
/* 565 */       pos--;
/*     */     }
/*     */     
/* 568 */     nanosString = nanosString.substring(0, pos + 1);
/*     */     
/* 570 */     return nanosString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void loadTimeZoneMappings(ExceptionInterceptor exceptionInterceptor) throws SQLException {
/* 580 */     timeZoneMappings = new Properties();
/*     */     try {
/* 582 */       timeZoneMappings.load(TimeUtil.class.getResourceAsStream("/com/mysql/jdbc/TimeZoneMapping.properties"));
/* 583 */     } catch (IOException e) {
/* 584 */       throw SQLError.createSQLException(Messages.getString("TimeUtil.LoadTimeZoneMappingError"), "01S00", exceptionInterceptor);
/*     */     } 
/*     */ 
/*     */     
/* 588 */     for (String tz : TimeZone.getAvailableIDs()) {
/* 589 */       if (!timeZoneMappings.containsKey(tz)) {
/* 590 */         timeZoneMappings.put(tz, tz);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Timestamp truncateFractionalSeconds(Timestamp timestamp) {
/* 596 */     Timestamp truncatedTimestamp = new Timestamp(timestamp.getTime());
/* 597 */     truncatedTimestamp.setNanos(0);
/* 598 */     return truncatedTimestamp;
/*     */   }
/*     */   
/*     */   public static SimpleDateFormat getSimpleDateFormat(SimpleDateFormat cachedSimpleDateFormat, String pattern, Calendar cal, TimeZone tz) {
/* 602 */     SimpleDateFormat sdf = (cachedSimpleDateFormat != null) ? cachedSimpleDateFormat : new SimpleDateFormat(pattern, Locale.US);
/*     */     
/* 604 */     if (cal != null) {
/* 605 */       sdf.setCalendar((Calendar)cal.clone());
/*     */     }
/*     */     
/* 608 */     if (tz != null) {
/* 609 */       sdf.setTimeZone(tz);
/*     */     }
/* 611 */     return sdf;
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
/*     */   public static Calendar setProlepticIfNeeded(Calendar origCalendar, Calendar refCalendar) {
/* 625 */     if (origCalendar != null && refCalendar != null && origCalendar instanceof GregorianCalendar && refCalendar instanceof GregorianCalendar && ((GregorianCalendar)refCalendar).getGregorianChange().getTime() == Long.MIN_VALUE) {
/*     */       
/* 627 */       origCalendar = (GregorianCalendar)origCalendar.clone();
/* 628 */       ((GregorianCalendar)origCalendar).setGregorianChange(new Date(Long.MIN_VALUE));
/* 629 */       origCalendar.clear();
/*     */     } 
/* 631 */     return origCalendar;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\TimeUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */