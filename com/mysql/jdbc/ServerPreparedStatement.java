/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import com.mysql.jdbc.exceptions.MySQLStatementCancelledException;
/*      */ import com.mysql.jdbc.exceptions.MySQLTimeoutException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URL;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Date;
/*      */ import java.sql.ParameterMetaData;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
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
/*      */ public class ServerPreparedStatement
/*      */   extends PreparedStatement
/*      */ {
/*      */   private static final Constructor<?> JDBC_4_SPS_CTOR;
/*      */   protected static final int BLOB_STREAM_READ_BUF_SIZE = 8192;
/*      */   
/*      */   static {
/*   61 */     if (Util.isJdbc4()) {
/*      */       try {
/*   63 */         String jdbc4ClassName = Util.isJdbc42() ? "com.mysql.jdbc.JDBC42ServerPreparedStatement" : "com.mysql.jdbc.JDBC4ServerPreparedStatement";
/*   64 */         JDBC_4_SPS_CTOR = Class.forName(jdbc4ClassName).getConstructor(new Class[] { MySQLConnection.class, String.class, String.class, int.class, int.class });
/*      */       }
/*   66 */       catch (SecurityException e) {
/*   67 */         throw new RuntimeException(e);
/*   68 */       } catch (NoSuchMethodException e) {
/*   69 */         throw new RuntimeException(e);
/*   70 */       } catch (ClassNotFoundException e) {
/*   71 */         throw new RuntimeException(e);
/*      */       } 
/*      */     } else {
/*   74 */       JDBC_4_SPS_CTOR = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static class BatchedBindValues
/*      */   {
/*      */     public ServerPreparedStatement.BindValue[] batchedParameterValues;
/*      */     
/*      */     BatchedBindValues(ServerPreparedStatement.BindValue[] paramVals) {
/*   84 */       int numParams = paramVals.length;
/*      */       
/*   86 */       this.batchedParameterValues = new ServerPreparedStatement.BindValue[numParams];
/*      */       
/*   88 */       for (int i = 0; i < numParams; i++) {
/*   89 */         this.batchedParameterValues[i] = new ServerPreparedStatement.BindValue(paramVals[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static class BindValue
/*      */   {
/*   96 */     public long boundBeforeExecutionNum = 0L;
/*      */     
/*      */     public long bindLength;
/*      */     
/*      */     public int bufferType;
/*      */     
/*      */     public double doubleBinding;
/*      */     
/*      */     public float floatBinding;
/*      */     
/*      */     public boolean isLongData;
/*      */     
/*      */     public boolean isNull;
/*      */     
/*      */     public boolean isSet = false;
/*      */     
/*      */     public long longBinding;
/*      */     
/*      */     public Object value;
/*      */     
/*      */     public Calendar calendar;
/*      */ 
/*      */     
/*      */     BindValue() {}
/*      */     
/*      */     BindValue(BindValue copyMe) {
/*  122 */       this.value = copyMe.value;
/*  123 */       this.isSet = copyMe.isSet;
/*  124 */       this.isLongData = copyMe.isLongData;
/*  125 */       this.isNull = copyMe.isNull;
/*  126 */       this.bufferType = copyMe.bufferType;
/*  127 */       this.bindLength = copyMe.bindLength;
/*  128 */       this.longBinding = copyMe.longBinding;
/*  129 */       this.floatBinding = copyMe.floatBinding;
/*  130 */       this.doubleBinding = copyMe.doubleBinding;
/*  131 */       this.calendar = copyMe.calendar;
/*      */     }
/*      */     
/*      */     void reset() {
/*  135 */       this.isNull = false;
/*  136 */       this.isSet = false;
/*  137 */       this.value = null;
/*  138 */       this.isLongData = false;
/*      */       
/*  140 */       this.longBinding = 0L;
/*  141 */       this.floatBinding = 0.0F;
/*  142 */       this.doubleBinding = 0.0D;
/*      */       
/*  144 */       this.calendar = null;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/*  149 */       return toString(false);
/*      */     }
/*      */     
/*      */     public String toString(boolean quoteIfNeeded) {
/*  153 */       if (this.isLongData) {
/*  154 */         return "' STREAM DATA '";
/*      */       }
/*      */       
/*  157 */       if (this.isNull) {
/*  158 */         return "NULL";
/*      */       }
/*      */       
/*  161 */       switch (this.bufferType) {
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/*      */         case 8:
/*  166 */           return String.valueOf(this.longBinding);
/*      */         case 4:
/*  168 */           return String.valueOf(this.floatBinding);
/*      */         case 5:
/*  170 */           return String.valueOf(this.doubleBinding);
/*      */         case 7:
/*      */         case 10:
/*      */         case 11:
/*      */         case 12:
/*      */         case 15:
/*      */         case 253:
/*      */         case 254:
/*  178 */           if (quoteIfNeeded) {
/*  179 */             return "'" + String.valueOf(this.value) + "'";
/*      */           }
/*  181 */           return String.valueOf(this.value);
/*      */       } 
/*      */       
/*  184 */       if (this.value instanceof byte[]) {
/*  185 */         return "byte data";
/*      */       }
/*  187 */       if (quoteIfNeeded) {
/*  188 */         return "'" + String.valueOf(this.value) + "'";
/*      */       }
/*  190 */       return String.valueOf(this.value);
/*      */     }
/*      */ 
/*      */     
/*      */     long getBoundLength() {
/*  195 */       if (this.isNull) {
/*  196 */         return 0L;
/*      */       }
/*      */       
/*  199 */       if (this.isLongData) {
/*  200 */         return this.bindLength;
/*      */       }
/*      */       
/*  203 */       switch (this.bufferType) {
/*      */         
/*      */         case 1:
/*  206 */           return 1L;
/*      */         case 2:
/*  208 */           return 2L;
/*      */         case 3:
/*  210 */           return 4L;
/*      */         case 8:
/*  212 */           return 8L;
/*      */         case 4:
/*  214 */           return 4L;
/*      */         case 5:
/*  216 */           return 8L;
/*      */         case 11:
/*  218 */           return 9L;
/*      */         case 10:
/*  220 */           return 7L;
/*      */         case 7:
/*      */         case 12:
/*  223 */           return 11L;
/*      */         case 0:
/*      */         case 15:
/*      */         case 246:
/*      */         case 253:
/*      */         case 254:
/*  229 */           if (this.value instanceof byte[]) {
/*  230 */             return ((byte[])this.value).length;
/*      */           }
/*  232 */           return ((String)this.value).length();
/*      */       } 
/*      */       
/*  235 */       return 0L;
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
/*      */   private boolean hasOnDuplicateKeyUpdate = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void storeTime(Buffer intoBuf, Time tm) throws SQLException {
/*  259 */     intoBuf.ensureCapacity(9);
/*  260 */     intoBuf.writeByte((byte)8);
/*  261 */     intoBuf.writeByte((byte)0);
/*  262 */     intoBuf.writeLong(0L);
/*      */     
/*  264 */     Calendar sessionCalendar = getCalendarInstanceForSessionOrNew();
/*      */     
/*  266 */     synchronized (sessionCalendar) {
/*  267 */       Date oldTime = sessionCalendar.getTime();
/*      */       try {
/*  269 */         sessionCalendar.setTime(tm);
/*  270 */         intoBuf.writeByte((byte)sessionCalendar.get(11));
/*  271 */         intoBuf.writeByte((byte)sessionCalendar.get(12));
/*  272 */         intoBuf.writeByte((byte)sessionCalendar.get(13));
/*      */       }
/*      */       finally {
/*      */         
/*  276 */         sessionCalendar.setTime(oldTime);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean detectedLongParameterSwitch = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private int fieldCount;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean invalid = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private SQLException invalidationException;
/*      */ 
/*      */ 
/*      */   
/*      */   private Buffer outByteBuffer;
/*      */ 
/*      */   
/*      */   private BindValue[] parameterBindings;
/*      */ 
/*      */   
/*      */   private Field[] parameterFields;
/*      */ 
/*      */   
/*      */   private Field[] resultFields;
/*      */ 
/*      */   
/*      */   private boolean sendTypesToServer = false;
/*      */ 
/*      */   
/*      */   private long serverStatementId;
/*      */ 
/*      */   
/*  318 */   private int stringTypeCode = 254;
/*      */   
/*      */   private boolean serverNeedsResetBeforeEachExecution;
/*      */   
/*      */   protected boolean isCached;
/*      */   private boolean useAutoSlowLog;
/*      */   private Calendar serverTzCalendar;
/*      */   private Calendar defaultTzCalendar;
/*      */   private boolean hasCheckedRewrite;
/*      */   private boolean canRewrite;
/*      */   private int locationOfOnDuplicateKeyUpdate;
/*      */   
/*      */   protected static ServerPreparedStatement getInstance(MySQLConnection conn, String sql, String catalog, int resultSetType, int resultSetConcurrency) throws SQLException {
/*  331 */     if (!Util.isJdbc4()) {
/*  332 */       return new ServerPreparedStatement(conn, sql, catalog, resultSetType, resultSetConcurrency);
/*      */     }
/*      */     
/*      */     try {
/*  336 */       return (ServerPreparedStatement)JDBC_4_SPS_CTOR.newInstance(new Object[] { conn, sql, catalog, Integer.valueOf(resultSetType), Integer.valueOf(resultSetConcurrency) });
/*      */     }
/*  338 */     catch (IllegalArgumentException e) {
/*  339 */       throw new SQLException(e.toString(), "S1000");
/*  340 */     } catch (InstantiationException e) {
/*  341 */       throw new SQLException(e.toString(), "S1000");
/*  342 */     } catch (IllegalAccessException e) {
/*  343 */       throw new SQLException(e.toString(), "S1000");
/*  344 */     } catch (InvocationTargetException e) {
/*  345 */       Throwable target = e.getTargetException();
/*      */       
/*  347 */       if (target instanceof SQLException) {
/*  348 */         throw (SQLException)target;
/*      */       }
/*      */       
/*  351 */       throw new SQLException(target.toString(), "S1000");
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
/*      */   protected ServerPreparedStatement(MySQLConnection conn, String sql, String catalog, int resultSetType, int resultSetConcurrency) throws SQLException {
/*  369 */     super(conn, catalog);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  547 */     this.isCached = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2540 */     this.hasCheckedRewrite = false;
/* 2541 */     this.canRewrite = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2557 */     this.locationOfOnDuplicateKeyUpdate = -2; checkNullOrEmptyQuery(sql); int startOfStatement = findStartOfStatement(sql); this.firstCharOfStmt = StringUtils.firstAlphaCharUc(sql, startOfStatement); this.hasOnDuplicateKeyUpdate = (this.firstCharOfStmt == 'I' && containsOnDuplicateKeyInString(sql)); if (this.connection.versionMeetsMinimum(5, 0, 0)) { this.serverNeedsResetBeforeEachExecution = !this.connection.versionMeetsMinimum(5, 0, 3); } else { this.serverNeedsResetBeforeEachExecution = !this.connection.versionMeetsMinimum(4, 1, 10); }  this.useAutoSlowLog = this.connection.getAutoSlowLog(); this.useTrueBoolean = this.connection.versionMeetsMinimum(3, 21, 23); String statementComment = this.connection.getStatementComment(); this.originalSql = (statementComment == null) ? sql : ("/* " + statementComment + " */ " + sql); if (this.connection.versionMeetsMinimum(4, 1, 2)) { this.stringTypeCode = 253; } else { this.stringTypeCode = 254; }  try { serverPrepare(sql); } catch (SQLException sqlEx) { realClose(false, true); throw sqlEx; } catch (Exception ex) { realClose(false, true); SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1000", getExceptionInterceptor()); sqlEx.initCause(ex); throw sqlEx; }  setResultSetType(resultSetType); setResultSetConcurrency(resultSetConcurrency); this.parameterTypes = new int[this.parameterCount];
/*      */   }
/*      */   public void addBatch() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.batchedArgs == null) this.batchedArgs = new ArrayList();  this.batchedArgs.add(new BatchedBindValues(this.parameterBindings)); }  }
/*      */   public String asSql(boolean quoteStreamsAndUnknowns) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { PreparedStatement pStmtForSub = null; try { pStmtForSub = PreparedStatement.getInstance(this.connection, this.originalSql, this.currentCatalog); int numParameters = pStmtForSub.parameterCount; int ourNumParameters = this.parameterCount; for (int i = 0; i < numParameters && i < ourNumParameters; i++) { if (this.parameterBindings[i] != null) if ((this.parameterBindings[i]).isNull) { pStmtForSub.setNull(i + 1, 0); } else { BindValue bindValue = this.parameterBindings[i]; switch (bindValue.bufferType) { case 1: pStmtForSub.setByte(i + 1, (byte)(int)bindValue.longBinding); break;case 2: pStmtForSub.setShort(i + 1, (short)(int)bindValue.longBinding); break;case 3: pStmtForSub.setInt(i + 1, (int)bindValue.longBinding); break;case 8: pStmtForSub.setLong(i + 1, bindValue.longBinding); break;case 4: pStmtForSub.setFloat(i + 1, bindValue.floatBinding); break;case 5: pStmtForSub.setDouble(i + 1, bindValue.doubleBinding); break;default: pStmtForSub.setObject(i + 1, (this.parameterBindings[i]).value); break; }  }   }  return pStmtForSub.asSql(quoteStreamsAndUnknowns); } finally { if (pStmtForSub != null) try { pStmtForSub.close(); } catch (SQLException sqlEx) {}  }  }  }
/* 2561 */   protected MySQLConnection checkClosed() throws SQLException { if (this.invalid) throw this.invalidationException;  return super.checkClosed(); } public void clearParameters() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { clearParametersInternal(true); }  } private void clearParametersInternal(boolean clearServerParameters) throws SQLException { boolean hadLongData = false; if (this.parameterBindings != null) for (int i = 0; i < this.parameterCount; i++) { if (this.parameterBindings[i] != null && (this.parameterBindings[i]).isLongData) hadLongData = true;  this.parameterBindings[i].reset(); }   if (clearServerParameters && hadLongData) { serverResetStatement(); this.detectedLongParameterSwitch = false; }  } protected void setClosed(boolean flag) { this.isClosed = flag; } public void close() throws SQLException { MySQLConnection locallyScopedConn = this.connection; if (locallyScopedConn == null) return;  synchronized (locallyScopedConn.getConnectionMutex()) { if (this.isCached && isPoolable() && !this.isClosed) { clearParameters(); this.isClosed = true; this.connection.recachePreparedStatement(this); return; }  this.isClosed = false; realClose(true, true); }  } private void dumpCloseForTestcase() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { StringBuilder buf = new StringBuilder(); this.connection.generateConnectionCommentBlock(buf); buf.append("DEALLOCATE PREPARE debug_stmt_"); buf.append(this.statementId); buf.append(";\n"); this.connection.dumpTestcaseQuery(buf.toString()); }  } private void dumpExecuteForTestcase() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { StringBuilder buf = new StringBuilder(); int i; for (i = 0; i < this.parameterCount; i++) { this.connection.generateConnectionCommentBlock(buf); buf.append("SET @debug_stmt_param"); buf.append(this.statementId); buf.append("_"); buf.append(i); buf.append("="); if ((this.parameterBindings[i]).isNull) { buf.append("NULL"); } else { buf.append(this.parameterBindings[i].toString(true)); }  buf.append(";\n"); }  this.connection.generateConnectionCommentBlock(buf); buf.append("EXECUTE debug_stmt_"); buf.append(this.statementId); if (this.parameterCount > 0) { buf.append(" USING "); for (i = 0; i < this.parameterCount; i++) { if (i > 0) buf.append(", ");  buf.append("@debug_stmt_param"); buf.append(this.statementId); buf.append("_"); buf.append(i); }  }  buf.append(";\n"); this.connection.dumpTestcaseQuery(buf.toString()); }  } private void dumpPrepareForTestcase() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { StringBuilder buf = new StringBuilder(this.originalSql.length() + 64); this.connection.generateConnectionCommentBlock(buf); buf.append("PREPARE debug_stmt_"); buf.append(this.statementId); buf.append(" FROM \""); buf.append(this.originalSql); buf.append("\";\n"); this.connection.dumpTestcaseQuery(buf.toString()); }  } protected long[] executeBatchSerially(int batchTimeout) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { MySQLConnection locallyScopedConn = this.connection; if (locallyScopedConn.isReadOnly()) throw SQLError.createSQLException(Messages.getString("ServerPreparedStatement.2") + Messages.getString("ServerPreparedStatement.3"), "S1009", getExceptionInterceptor());  clearWarnings(); BindValue[] oldBindValues = this.parameterBindings; try { long[] updateCounts = null; if (this.batchedArgs != null) { int nbrCommands = this.batchedArgs.size(); updateCounts = new long[nbrCommands]; if (this.retrieveGeneratedKeys) this.batchedGeneratedKeys = new ArrayList<ResultSetRow>(nbrCommands);  for (int i = 0; i < nbrCommands; i++) updateCounts[i] = -3L;  SQLException sqlEx = null; int commandIndex = 0; BindValue[] previousBindValuesForBatch = null; StatementImpl.CancelTask timeoutTask = null; try { if (locallyScopedConn.getEnableQueryTimeouts() && batchTimeout != 0 && locallyScopedConn.versionMeetsMinimum(5, 0, 0)) { timeoutTask = new StatementImpl.CancelTask(this, this); locallyScopedConn.getCancelTimer().schedule(timeoutTask, batchTimeout); }  for (commandIndex = 0; commandIndex < nbrCommands; commandIndex++) { Object arg = this.batchedArgs.get(commandIndex); try { if (arg instanceof String) { updateCounts[commandIndex] = executeUpdateInternal((String)arg, true, this.retrieveGeneratedKeys); getBatchedGeneratedKeys((this.results.getFirstCharOfQuery() == 'I' && containsOnDuplicateKeyInString((String)arg)) ? 1 : 0); } else { this.parameterBindings = ((BatchedBindValues)arg).batchedParameterValues; if (previousBindValuesForBatch != null) for (int j = 0; j < this.parameterBindings.length; j++) { if ((this.parameterBindings[j]).bufferType != (previousBindValuesForBatch[j]).bufferType) { this.sendTypesToServer = true; break; }  }   try { updateCounts[commandIndex] = executeUpdateInternal(false, true); } finally { previousBindValuesForBatch = this.parameterBindings; }  getBatchedGeneratedKeys(containsOnDuplicateKeyUpdateInSQL() ? 1 : 0); }  } catch (SQLException ex) { updateCounts[commandIndex] = -3L; if (this.continueBatchOnError && !(ex instanceof MySQLTimeoutException) && !(ex instanceof MySQLStatementCancelledException) && !hasDeadlockOrTimeoutRolledBackTx(ex)) { sqlEx = ex; } else { long[] newUpdateCounts = new long[commandIndex]; System.arraycopy(updateCounts, 0, newUpdateCounts, 0, commandIndex); throw SQLError.createBatchUpdateException(ex, newUpdateCounts, getExceptionInterceptor()); }  }  }  } finally { if (timeoutTask != null) { timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); }  resetCancelledState(); }  if (sqlEx != null) throw SQLError.createBatchUpdateException(sqlEx, updateCounts, getExceptionInterceptor());  }  return (updateCounts != null) ? updateCounts : new long[0]; } finally { this.parameterBindings = oldBindValues; this.sendTypesToServer = true; clearBatch(); }  }  } protected ResultSetInternalMethods executeInternal(int maxRowsToRetrieve, Buffer sendPacket, boolean createStreamingResultSet, boolean queryIsSelectOnly, Field[] metadataFromCache, boolean isBatch) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { this.numberOfExecutions++; try { return serverExecute(maxRowsToRetrieve, createStreamingResultSet, metadataFromCache); } catch (SQLException sqlEx) { if (this.connection.getEnablePacketDebug()) this.connection.getIO().dumpPacketRingBuffer();  if (this.connection.getDumpQueriesOnException()) { String extractedSql = toString(); StringBuilder messageBuf = new StringBuilder(extractedSql.length() + 32); messageBuf.append("\n\nQuery being executed when exception was thrown:\n"); messageBuf.append(extractedSql); messageBuf.append("\n\n"); sqlEx = ConnectionImpl.appendMessageToException(sqlEx, messageBuf.toString(), getExceptionInterceptor()); }  throw sqlEx; } catch (Exception ex) { if (this.connection.getEnablePacketDebug()) this.connection.getIO().dumpPacketRingBuffer();  SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1000", getExceptionInterceptor()); if (this.connection.getDumpQueriesOnException()) { String extractedSql = toString(); StringBuilder messageBuf = new StringBuilder(extractedSql.length() + 32); messageBuf.append("\n\nQuery being executed when exception was thrown:\n"); messageBuf.append(extractedSql); messageBuf.append("\n\n"); sqlEx = ConnectionImpl.appendMessageToException(sqlEx, messageBuf.toString(), getExceptionInterceptor()); }  sqlEx.initCause(ex); throw sqlEx; }  }  } protected Buffer fillSendPacket() throws SQLException { return null; } protected Buffer fillSendPacket(byte[][] batchedParameterStrings, InputStream[] batchedParameterStreams, boolean[] batchedIsStream, int[] batchedStreamLengths) throws SQLException { return null; } protected BindValue getBinding(int parameterIndex, boolean forLongData) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.parameterBindings.length == 0) throw SQLError.createSQLException(Messages.getString("ServerPreparedStatement.8"), "S1009", getExceptionInterceptor());  parameterIndex--; if (parameterIndex < 0 || parameterIndex >= this.parameterBindings.length) throw SQLError.createSQLException(Messages.getString("ServerPreparedStatement.9") + (parameterIndex + 1) + Messages.getString("ServerPreparedStatement.10") + this.parameterBindings.length, "S1009", getExceptionInterceptor());  if (this.parameterBindings[parameterIndex] == null) { this.parameterBindings[parameterIndex] = new BindValue(); } else if ((this.parameterBindings[parameterIndex]).isLongData && !forLongData) { this.detectedLongParameterSwitch = true; }  return this.parameterBindings[parameterIndex]; }  } public BindValue[] getParameterBindValues() { return this.parameterBindings; } byte[] getBytes(int parameterIndex) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { BindValue bindValue = getBinding(parameterIndex, false); if (bindValue.isNull) return null;  if (bindValue.isLongData) throw SQLError.createSQLFeatureNotSupportedException();  if (this.outByteBuffer == null) this.outByteBuffer = new Buffer(this.connection.getNetBufferLength());  this.outByteBuffer.clear(); int originalPosition = this.outByteBuffer.getPosition(); storeBinding(this.outByteBuffer, bindValue, this.connection.getIO()); int newPosition = this.outByteBuffer.getPosition(); int length = newPosition - originalPosition; byte[] valueAsBytes = new byte[length]; System.arraycopy(this.outByteBuffer.getByteBuffer(), originalPosition, valueAsBytes, 0, length); return valueAsBytes; }  } public ResultSetMetaData getMetaData() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.resultFields == null) return null;  return new ResultSetMetaData(this.resultFields, this.connection.getUseOldAliasMetadataBehavior(), this.connection.getYearIsDateType(), getExceptionInterceptor()); }  } public ParameterMetaData getParameterMetaData() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.parameterMetaData == null) this.parameterMetaData = new MysqlParameterMetadata(this.parameterFields, this.parameterCount, getExceptionInterceptor());  return this.parameterMetaData; }  } boolean isNull(int paramIndex) { throw new IllegalArgumentException(Messages.getString("ServerPreparedStatement.7")); } protected void realClose(boolean calledExplicitly, boolean closeOpenResults) throws SQLException { MySQLConnection locallyScopedConn = this.connection; if (locallyScopedConn == null) return;  synchronized (locallyScopedConn.getConnectionMutex()) { if (this.connection != null) { if (this.connection.getAutoGenerateTestcaseScript()) dumpCloseForTestcase();  SQLException exceptionDuringClose = null; if (calledExplicitly && !this.connection.isClosed()) synchronized (this.connection.getConnectionMutex()) { try { MysqlIO mysql = this.connection.getIO(); Buffer packet = mysql.getSharedSendPacket(); packet.writeByte((byte)25); packet.writeLong(this.serverStatementId); mysql.sendCommand(25, null, packet, true, null, 0); } catch (SQLException sqlEx) { exceptionDuringClose = sqlEx; }  }   if (this.isCached) { this.connection.decachePreparedStatement(this); this.isCached = false; }  super.realClose(calledExplicitly, closeOpenResults); clearParametersInternal(false); this.parameterBindings = null; this.parameterFields = null; this.resultFields = null; if (exceptionDuringClose != null) throw exceptionDuringClose;  }  }  } protected void rePrepare() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { this.invalidationException = null; try { serverPrepare(this.originalSql); } catch (SQLException sqlEx) { this.invalidationException = sqlEx; } catch (Exception ex) { this.invalidationException = SQLError.createSQLException(ex.toString(), "S1000", getExceptionInterceptor()); this.invalidationException.initCause(ex); }  if (this.invalidationException != null) { this.invalid = true; this.parameterBindings = null; this.parameterFields = null; this.resultFields = null; if (this.results != null) try { this.results.close(); } catch (Exception ex) {}  if (this.generatedKeysResults != null) try { this.generatedKeysResults.close(); } catch (Exception ex) {}  try { closeAllOpenResults(); } catch (Exception e) {} if (this.connection != null && !this.connection.getDontTrackOpenResources()) this.connection.unregisterStatement(this);  }  }  } boolean isCursorRequired() throws SQLException { return (this.resultFields != null && this.connection.isCursorFetchEnabled() && getResultSetType() == 1003 && getResultSetConcurrency() == 1007 && getFetchSize() > 0); } private ResultSetInternalMethods serverExecute(int maxRowsToRetrieve, boolean createStreamingResultSet, Field[] metadataFromCache) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { MysqlIO mysql = this.connection.getIO(); if (mysql.shouldIntercept()) { ResultSetInternalMethods interceptedResults = mysql.invokeStatementInterceptorsPre(this.originalSql, this, true); if (interceptedResults != null) return interceptedResults;  }  if (this.detectedLongParameterSwitch) { boolean firstFound = false; long boundTimeToCheck = 0L; for (int m = 0; m < this.parameterCount - 1; m++) { if ((this.parameterBindings[m]).isLongData) { if (firstFound && boundTimeToCheck != (this.parameterBindings[m]).boundBeforeExecutionNum) throw SQLError.createSQLException(Messages.getString("ServerPreparedStatement.11") + Messages.getString("ServerPreparedStatement.12"), "S1C00", getExceptionInterceptor());  firstFound = true; boundTimeToCheck = (this.parameterBindings[m]).boundBeforeExecutionNum; }  }  serverResetStatement(); }  int i; for (i = 0; i < this.parameterCount; i++) { if (!(this.parameterBindings[i]).isSet) throw SQLError.createSQLException(Messages.getString("ServerPreparedStatement.13") + (i + 1) + Messages.getString("ServerPreparedStatement.14"), "S1009", getExceptionInterceptor());  }  for (i = 0; i < this.parameterCount; i++) { if ((this.parameterBindings[i]).isLongData) serverLongData(i, this.parameterBindings[i]);  }  if (this.connection.getAutoGenerateTestcaseScript()) dumpExecuteForTestcase();  Buffer packet = mysql.getSharedSendPacket(); packet.clear(); packet.writeByte((byte)23); packet.writeLong(this.serverStatementId); if (this.connection.versionMeetsMinimum(4, 1, 2)) { if (isCursorRequired()) { packet.writeByte((byte)1); } else { packet.writeByte((byte)0); }  packet.writeLong(1L); }  int nullCount = (this.parameterCount + 7) / 8; int nullBitsPosition = packet.getPosition(); for (int j = 0; j < nullCount; j++) packet.writeByte((byte)0);  byte[] nullBitsBuffer = new byte[nullCount]; packet.writeByte(this.sendTypesToServer ? 1 : 0); if (this.sendTypesToServer) for (int m = 0; m < this.parameterCount; m++) packet.writeInt((this.parameterBindings[m]).bufferType);   for (int k = 0; k < this.parameterCount; k++) { if (!(this.parameterBindings[k]).isLongData) if (!(this.parameterBindings[k]).isNull) { storeBinding(packet, this.parameterBindings[k], mysql); } else { nullBitsBuffer[k / 8] = (byte)(nullBitsBuffer[k / 8] | 1 << (k & 0x7)); }   }  int endPosition = packet.getPosition(); packet.setPosition(nullBitsPosition); packet.writeBytesNoNull(nullBitsBuffer); packet.setPosition(endPosition); boolean logSlowQueries = this.connection.getLogSlowQueries(); boolean gatherPerformanceMetrics = this.connection.getGatherPerformanceMetrics(); boolean countDuration = (this.profileSQL || logSlowQueries || gatherPerformanceMetrics); long begin = countDuration ? mysql.getCurrentTimeNanosOrMillis() : 0L; resetCancelledState(); StatementImpl.CancelTask timeoutTask = null; try { String queryAsString = countDuration ? asSql(true) : ""; if (this.connection.getEnableQueryTimeouts() && this.timeoutInMillis != 0 && this.connection.versionMeetsMinimum(5, 0, 0)) { timeoutTask = new StatementImpl.CancelTask(this, this); this.connection.getCancelTimer().schedule(timeoutTask, this.timeoutInMillis); }  statementBegins(); Buffer resultPacket = mysql.sendCommand(23, null, packet, false, null, 0); long queryEndTime = countDuration ? (queryEndTime = mysql.getCurrentTimeNanosOrMillis()) : 0L; if (timeoutTask != null) { timeoutTask.cancel(); this.connection.getCancelTimer().purge(); if (timeoutTask.caughtWhileCancelling != null) throw timeoutTask.caughtWhileCancelling;  timeoutTask = null; }  synchronized (this.cancelTimeoutMutex) { if (this.wasCancelled) { MySQLStatementCancelledException mySQLStatementCancelledException; SQLException cause = null; if (this.wasCancelledByTimeout) { MySQLTimeoutException mySQLTimeoutException = new MySQLTimeoutException(); } else { mySQLStatementCancelledException = new MySQLStatementCancelledException(); }  resetCancelledState(); throw mySQLStatementCancelledException; }  }  long elapsedTime = countDuration ? (queryEndTime - begin) : 0L; boolean queryWasSlow = false; if (logSlowQueries) { queryWasSlow = this.useAutoSlowLog ? this.connection.isAbonormallyLongQuery(elapsedTime) : ((elapsedTime > this.connection.getSlowQueryThresholdMillis())); if (queryWasSlow) this.connection.getProfilerEventHandlerInstance().processEvent((byte)6, this.connection, this, null, elapsedTime, new Throwable(), Messages.getString("ServerPreparedStatement.15", (Object[])new String[] { String.valueOf(mysql.getSlowQueryThreshold()), String.valueOf(elapsedTime), this.originalSql, queryAsString }));  }  if (gatherPerformanceMetrics) { this.connection.registerQueryExecutionTime(elapsedTime); this.connection.incrementNumberOfPreparedExecutes(); }  if (this.profileSQL) this.connection.getProfilerEventHandlerInstance().processEvent((byte)4, this.connection, this, null, mysql.getCurrentTimeNanosOrMillis() - begin, new Throwable(), truncateQueryToLog(queryAsString));  ResultSetInternalMethods rs = mysql.readAllResults(this, maxRowsToRetrieve, this.resultSetType, this.resultSetConcurrency, createStreamingResultSet, this.currentCatalog, resultPacket, true, this.fieldCount, metadataFromCache); if (mysql.shouldIntercept()) { ResultSetInternalMethods interceptedResults = mysql.invokeStatementInterceptorsPost(this.originalSql, this, rs, true, null); if (interceptedResults != null) rs = interceptedResults;  }  if (this.profileSQL) this.connection.getProfilerEventHandlerInstance().processEvent((byte)5, this.connection, this, null, mysql.getCurrentTimeNanosOrMillis() - queryEndTime, new Throwable(), null);  if (queryWasSlow && this.connection.getExplainSlowQueries()) mysql.explainSlowQuery(StringUtils.getBytes(queryAsString), queryAsString);  if (!createStreamingResultSet && this.serverNeedsResetBeforeEachExecution) serverResetStatement();  this.sendTypesToServer = false; this.results = rs; if (mysql.hadWarnings()) mysql.scanForAndThrowDataTruncation();  return rs; } catch (SQLException sqlEx) { if (mysql.shouldIntercept()) mysql.invokeStatementInterceptorsPost(this.originalSql, this, null, true, sqlEx);  throw sqlEx; } finally { this.statementExecuting.set(false); if (timeoutTask != null) { timeoutTask.cancel(); this.connection.getCancelTimer().purge(); }  }  }  } private void serverLongData(int parameterIndex, BindValue longData) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { MysqlIO mysql = this.connection.getIO(); Buffer packet = mysql.getSharedSendPacket(); Object value = longData.value; if (value instanceof byte[]) { packet.clear(); packet.writeByte((byte)24); packet.writeLong(this.serverStatementId); packet.writeInt(parameterIndex); packet.writeBytesNoNull((byte[])longData.value); mysql.sendCommand(24, null, packet, true, null, 0); } else if (value instanceof InputStream) { storeStream(mysql, parameterIndex, packet, (InputStream)value); } else if (value instanceof Blob) { storeStream(mysql, parameterIndex, packet, ((Blob)value).getBinaryStream()); } else if (value instanceof Reader) { storeReader(mysql, parameterIndex, packet, (Reader)value); } else { throw SQLError.createSQLException(Messages.getString("ServerPreparedStatement.18") + value.getClass().getName() + "'", "S1009", getExceptionInterceptor()); }  }  } private void serverPrepare(String sql) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { MysqlIO mysql = this.connection.getIO(); if (this.connection.getAutoGenerateTestcaseScript()) dumpPrepareForTestcase();  try { long begin = this.connection.getProfileSql() ? (begin = System.currentTimeMillis()) : 0L; this.isLoadDataQuery = StringUtils.startsWithIgnoreCaseAndWs(sql, "LOAD DATA"); String characterEncoding = null; String connectionEncoding = this.connection.getEncoding(); if (!this.isLoadDataQuery && this.connection.getUseUnicode() && connectionEncoding != null) characterEncoding = connectionEncoding;  Buffer prepareResultPacket = mysql.sendCommand(22, sql, null, false, characterEncoding, 0); if (this.connection.versionMeetsMinimum(4, 1, 1)) { prepareResultPacket.setPosition(1); } else { prepareResultPacket.setPosition(0); }  this.serverStatementId = prepareResultPacket.readLong(); this.fieldCount = prepareResultPacket.readInt(); this.parameterCount = prepareResultPacket.readInt(); this.parameterBindings = new BindValue[this.parameterCount]; for (int i = 0; i < this.parameterCount; i++) this.parameterBindings[i] = new BindValue();  this.connection.incrementNumberOfPrepares(); if (this.profileSQL) this.connection.getProfilerEventHandlerInstance().processEvent((byte)2, this.connection, this, null, mysql.getCurrentTimeNanosOrMillis() - begin, new Throwable(), truncateQueryToLog(sql));  boolean checkEOF = !mysql.isEOFDeprecated(); if (this.parameterCount > 0 && this.connection.versionMeetsMinimum(4, 1, 2) && !mysql.isVersion(5, 0, 0)) { this.parameterFields = new Field[this.parameterCount]; for (int j = 0; j < this.parameterCount; j++) { Buffer metaDataPacket = mysql.readPacket(); this.parameterFields[j] = mysql.unpackField(metaDataPacket, false); }  if (checkEOF) mysql.readPacket();  }  if (this.fieldCount > 0) { this.resultFields = new Field[this.fieldCount]; for (int j = 0; j < this.fieldCount; j++) { Buffer fieldPacket = mysql.readPacket(); this.resultFields[j] = mysql.unpackField(fieldPacket, false); }  if (checkEOF) mysql.readPacket();  }  } catch (SQLException sqlEx) { if (this.connection.getDumpQueriesOnException()) { StringBuilder messageBuf = new StringBuilder(this.originalSql.length() + 32); messageBuf.append("\n\nQuery being prepared when exception was thrown:\n\n"); messageBuf.append(this.originalSql); sqlEx = ConnectionImpl.appendMessageToException(sqlEx, messageBuf.toString(), getExceptionInterceptor()); }  throw sqlEx; } finally { this.connection.getIO().clearInputStream(); }  }  } private String truncateQueryToLog(String sql) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { String query = null; if (sql.length() > this.connection.getMaxQuerySizeToLog()) { StringBuilder queryBuf = new StringBuilder(this.connection.getMaxQuerySizeToLog() + 12); queryBuf.append(sql.substring(0, this.connection.getMaxQuerySizeToLog())); queryBuf.append(Messages.getString("MysqlIO.25")); query = queryBuf.toString(); } else { query = sql; }  return query; }  } private void serverResetStatement() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { MysqlIO mysql = this.connection.getIO(); Buffer packet = mysql.getSharedSendPacket(); packet.clear(); packet.writeByte((byte)26); packet.writeLong(this.serverStatementId); try { mysql.sendCommand(26, null, packet, !this.connection.versionMeetsMinimum(4, 1, 2), null, 0); } catch (SQLException sqlEx) { throw sqlEx; } catch (Exception ex) { SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1000", getExceptionInterceptor()); sqlEx.initCause(ex); throw sqlEx; } finally { mysql.clearInputStream(); }  }  } public void setArray(int i, Array x) throws SQLException { throw SQLError.createSQLFeatureNotSupportedException(); } public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (x == null) { setNull(parameterIndex, -2); } else { BindValue binding = getBinding(parameterIndex, true); resetToType(binding, 252); binding.value = x; binding.isLongData = true; if (this.connection.getUseStreamLengthsInPrepStmts()) { binding.bindLength = length; } else { binding.bindLength = -1L; }  }  }  } public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (x == null) { setNull(parameterIndex, 3); } else { BindValue binding = getBinding(parameterIndex, false); if (this.connection.versionMeetsMinimum(5, 0, 3)) { resetToType(binding, 246); } else { resetToType(binding, this.stringTypeCode); }  binding.value = StringUtils.fixDecimalExponent(StringUtils.consistentToString(x)); }  }  } public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (x == null) { setNull(parameterIndex, -2); } else { BindValue binding = getBinding(parameterIndex, true); resetToType(binding, 252); binding.value = x; binding.isLongData = true; if (this.connection.getUseStreamLengthsInPrepStmts()) { binding.bindLength = length; } else { binding.bindLength = -1L; }  }  }  } public void setBlob(int parameterIndex, Blob x) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (x == null) { setNull(parameterIndex, -2); } else { BindValue binding = getBinding(parameterIndex, true); resetToType(binding, 252); binding.value = x; binding.isLongData = true; if (this.connection.getUseStreamLengthsInPrepStmts()) { binding.bindLength = x.length(); } else { binding.bindLength = -1L; }  }  }  } public void setBoolean(int parameterIndex, boolean x) throws SQLException { setByte(parameterIndex, x ? 1 : 0); } protected int getLocationOfOnDuplicateKeyUpdate() throws SQLException { synchronized (checkClosed().getConnectionMutex())
/* 2562 */     { if (this.locationOfOnDuplicateKeyUpdate == -2) {
/* 2563 */         this.locationOfOnDuplicateKeyUpdate = getOnDuplicateKeyLocation(this.originalSql, this.connection.getDontCheckOnDuplicateKeyUpdateInSQL(), this.connection.getRewriteBatchedStatements(), this.connection.isNoBackslashEscapesSet());
/*      */       }
/*      */ 
/*      */       
/* 2567 */       return this.locationOfOnDuplicateKeyUpdate; }  }
/*      */   public void setByte(int parameterIndex, byte x) throws SQLException { checkClosed(); BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 1); binding.longBinding = x; }
/*      */   public void setBytes(int parameterIndex, byte[] x) throws SQLException { checkClosed(); if (x == null) { setNull(parameterIndex, -2); } else { BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 253); binding.value = x; }  }
/*      */   public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (reader == null) { setNull(parameterIndex, -2); } else { BindValue binding = getBinding(parameterIndex, true); resetToType(binding, 252); binding.value = reader; binding.isLongData = true; if (this.connection.getUseStreamLengthsInPrepStmts()) { binding.bindLength = length; } else { binding.bindLength = -1L; }  }  }  }
/*      */   public void setClob(int parameterIndex, Clob x) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (x == null) { setNull(parameterIndex, -2); } else { BindValue binding = getBinding(parameterIndex, true); resetToType(binding, 252); binding.value = x.getCharacterStream(); binding.isLongData = true; if (this.connection.getUseStreamLengthsInPrepStmts()) { binding.bindLength = x.length(); } else { binding.bindLength = -1L; }  }  }  }
/* 2572 */   public void setDate(int parameterIndex, Date x) throws SQLException { setDate(parameterIndex, x, (Calendar)null); } public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException { if (x == null) { setNull(parameterIndex, 91); } else { BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 10); binding.value = x; if (cal != null) binding.calendar = (Calendar)cal.clone();  }  } public void setDouble(int parameterIndex, double x) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (!this.connection.getAllowNanAndInf() && (x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY || Double.isNaN(x))) throw SQLError.createSQLException("'" + x + "' is not a valid numeric or approximate numeric value", "S1009", getExceptionInterceptor());  BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 5); binding.doubleBinding = x; }  } public void setFloat(int parameterIndex, float x) throws SQLException { checkClosed(); BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 4); binding.floatBinding = x; } public void setInt(int parameterIndex, int x) throws SQLException { checkClosed(); BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 3); binding.longBinding = x; } public void setLong(int parameterIndex, long x) throws SQLException { checkClosed(); BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 8); binding.longBinding = x; } public void setNull(int parameterIndex, int sqlType) throws SQLException { checkClosed(); BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 6); binding.isNull = true; } public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException { checkClosed(); BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 6); binding.isNull = true; } public void setRef(int i, Ref x) throws SQLException { throw SQLError.createSQLFeatureNotSupportedException(); } public void setShort(int parameterIndex, short x) throws SQLException { checkClosed(); BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 2); binding.longBinding = x; } public void setString(int parameterIndex, String x) throws SQLException { checkClosed(); if (x == null) { setNull(parameterIndex, 1); } else { BindValue binding = getBinding(parameterIndex, false); resetToType(binding, this.stringTypeCode); binding.value = x; }  } public void setTime(int parameterIndex, Time x) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { setTimeInternal(parameterIndex, x, (Calendar)null, this.connection.getDefaultTimeZone(), false); }  } public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { setTimeInternal(parameterIndex, x, cal, cal.getTimeZone(), true); }  } private void setTimeInternal(int parameterIndex, Time x, Calendar targetCalendar, TimeZone tz, boolean rollForward) throws SQLException { if (x == null) { setNull(parameterIndex, 92); } else { BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 11); if (!this.useLegacyDatetimeCode) { binding.value = x; if (targetCalendar != null) binding.calendar = (Calendar)targetCalendar.clone();  } else { Calendar sessionCalendar = getCalendarInstanceForSessionOrNew(); binding.value = TimeUtil.changeTimezone(this.connection, sessionCalendar, targetCalendar, x, tz, this.connection.getServerTimezoneTZ(), rollForward); }  }  } public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { setTimestampInternal(parameterIndex, x, (Calendar)null, this.connection.getDefaultTimeZone(), false); }  } public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { setTimestampInternal(parameterIndex, x, cal, cal.getTimeZone(), true); }  } private void setTimestampInternal(int parameterIndex, Timestamp x, Calendar targetCalendar, TimeZone tz, boolean rollForward) throws SQLException { if (x == null) { setNull(parameterIndex, 93); } else { BindValue binding = getBinding(parameterIndex, false); resetToType(binding, 12); if (!this.sendFractionalSeconds) x = TimeUtil.truncateFractionalSeconds(x);  if (!this.useLegacyDatetimeCode) { binding.value = x; } else { Calendar sessionCalendar = this.connection.getUseJDBCCompliantTimezoneShift() ? this.connection.getUtcCalendar() : getCalendarInstanceForSessionOrNew(); sessionCalendar = TimeUtil.setProlepticIfNeeded(sessionCalendar, targetCalendar); binding.value = TimeUtil.changeTimezone(this.connection, sessionCalendar, targetCalendar, x, tz, this.connection.getServerTimezoneTZ(), rollForward); }  if (targetCalendar != null) binding.calendar = (Calendar)targetCalendar.clone();  }  } protected void resetToType(BindValue oldValue, int bufferType) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { oldValue.reset(); if (bufferType != 6 || oldValue.bufferType == 0) if (oldValue.bufferType != bufferType) { this.sendTypesToServer = true; oldValue.bufferType = bufferType; }   oldValue.isSet = true; oldValue.boundBeforeExecutionNum = this.numberOfExecutions; }  } @Deprecated public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException { checkClosed(); throw SQLError.createSQLFeatureNotSupportedException(); } public void setURL(int parameterIndex, URL x) throws SQLException { checkClosed(); setString(parameterIndex, x.toString()); } private void storeBinding(Buffer packet, BindValue bindValue, MysqlIO mysql) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { try { Object value = bindValue.value; switch (bindValue.bufferType) { case 1: packet.writeByte((byte)(int)bindValue.longBinding); return;case 2: packet.ensureCapacity(2); packet.writeInt((int)bindValue.longBinding); return;case 3: packet.ensureCapacity(4); packet.writeLong((int)bindValue.longBinding); return;case 8: packet.ensureCapacity(8); packet.writeLongLong(bindValue.longBinding); return;case 4: packet.ensureCapacity(4); packet.writeFloat(bindValue.floatBinding); return;case 5: packet.ensureCapacity(8); packet.writeDouble(bindValue.doubleBinding); return;case 11: storeTime(packet, (Time)value); return;case 7: case 10: case 12: storeDateTime(packet, (Date)value, mysql, bindValue.bufferType, bindValue.calendar); return;case 0: case 15: case 246: case 253: case 254: if (value instanceof byte[]) { packet.writeLenBytes((byte[])value); } else if (!this.isLoadDataQuery) { packet.writeLenString((String)value, this.charEncoding, this.connection.getServerCharset(), this.charConverter, this.connection.parserKnowsUnicode(), this.connection); } else { packet.writeLenBytes(StringUtils.getBytes((String)value)); }  return; }  } catch (UnsupportedEncodingException uEE) { throw SQLError.createSQLException(Messages.getString("ServerPreparedStatement.22") + this.connection.getEncoding() + "'", "S1000", getExceptionInterceptor()); }  }  } private void storeDateTime412AndOlder(Buffer intoBuf, Date dt, int bufferType) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { Calendar sessionCalendar = null; if (!this.useLegacyDatetimeCode) { if (bufferType == 10) { sessionCalendar = getDefaultTzCalendar(); } else { sessionCalendar = getServerTzCalendar(); }  } else { sessionCalendar = (dt instanceof Timestamp && this.connection.getUseJDBCCompliantTimezoneShift()) ? this.connection.getUtcCalendar() : getCalendarInstanceForSessionOrNew(); }  Date oldTime = sessionCalendar.getTime(); try { intoBuf.ensureCapacity(8); intoBuf.writeByte((byte)7); sessionCalendar.setTime(dt); int year = sessionCalendar.get(1); int month = sessionCalendar.get(2) + 1; int date = sessionCalendar.get(5); intoBuf.writeInt(year); intoBuf.writeByte((byte)month); intoBuf.writeByte((byte)date); if (dt instanceof Date) { intoBuf.writeByte((byte)0); intoBuf.writeByte((byte)0); intoBuf.writeByte((byte)0); } else { intoBuf.writeByte((byte)sessionCalendar.get(11)); intoBuf.writeByte((byte)sessionCalendar.get(12)); intoBuf.writeByte((byte)sessionCalendar.get(13)); }  } finally { sessionCalendar.setTime(oldTime); }  }  } private void storeDateTime(Buffer intoBuf, Date dt, MysqlIO mysql, int bufferType, Calendar cal) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.connection.versionMeetsMinimum(4, 1, 3)) { storeDateTime413AndNewer(intoBuf, dt, bufferType, cal); } else { storeDateTime412AndOlder(intoBuf, dt, bufferType); }  }  } private void storeDateTime413AndNewer(Buffer intoBuf, Date dt, int bufferType, Calendar cal) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { Calendar sessionCalendar = cal; if (cal == null) if (!this.useLegacyDatetimeCode) { if (bufferType == 10) { sessionCalendar = getDefaultTzCalendar(); } else { sessionCalendar = getServerTzCalendar(); }  } else { sessionCalendar = (dt instanceof Timestamp && this.connection.getUseJDBCCompliantTimezoneShift()) ? this.connection.getUtcCalendar() : getCalendarInstanceForSessionOrNew(); }   Date oldTime = sessionCalendar.getTime(); try { sessionCalendar.setTime(dt); if (dt instanceof Date) { sessionCalendar.set(11, 0); sessionCalendar.set(12, 0); sessionCalendar.set(13, 0); }  byte length = 7; if (dt instanceof Timestamp) length = 11;  intoBuf.ensureCapacity(length); intoBuf.writeByte(length); int year = sessionCalendar.get(1); int month = sessionCalendar.get(2) + 1; int date = sessionCalendar.get(5); intoBuf.writeInt(year); intoBuf.writeByte((byte)month); intoBuf.writeByte((byte)date); if (dt instanceof Date) { intoBuf.writeByte((byte)0); intoBuf.writeByte((byte)0); intoBuf.writeByte((byte)0); } else { intoBuf.writeByte((byte)sessionCalendar.get(11)); intoBuf.writeByte((byte)sessionCalendar.get(12)); intoBuf.writeByte((byte)sessionCalendar.get(13)); }  if (length == 11) intoBuf.writeLong((((Timestamp)dt).getNanos() / 1000));  } finally { sessionCalendar.setTime(oldTime); }  }  } private Calendar getServerTzCalendar() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.serverTzCalendar == null) this.serverTzCalendar = new GregorianCalendar(this.connection.getServerTimezoneTZ());  return this.serverTzCalendar; }  } private Calendar getDefaultTzCalendar() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.defaultTzCalendar == null) this.defaultTzCalendar = new GregorianCalendar(TimeZone.getDefault());  return this.defaultTzCalendar; }  } private void storeReader(MysqlIO mysql, int parameterIndex, Buffer packet, Reader inStream) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { String forcedEncoding = this.connection.getClobCharacterEncoding(); String clobEncoding = (forcedEncoding == null) ? this.connection.getEncoding() : forcedEncoding; int maxBytesChar = 2; if (clobEncoding != null) if (!clobEncoding.equals("UTF-16")) { maxBytesChar = this.connection.getMaxBytesPerChar(clobEncoding); if (maxBytesChar == 1) maxBytesChar = 2;  } else { maxBytesChar = 4; }   char[] buf = new char[8192 / maxBytesChar]; int numRead = 0; int bytesInPacket = 0; int totalBytesRead = 0; int bytesReadAtLastSend = 0; int packetIsFullAt = this.connection.getBlobSendChunkSize(); try { packet.clear(); packet.writeByte((byte)24); packet.writeLong(this.serverStatementId); packet.writeInt(parameterIndex); boolean readAny = false; while ((numRead = inStream.read(buf)) != -1) { readAny = true; byte[] valueAsBytes = StringUtils.getBytes(buf, (SingleByteCharsetConverter)null, clobEncoding, this.connection.getServerCharset(), 0, numRead, this.connection.parserKnowsUnicode(), getExceptionInterceptor()); packet.writeBytesNoNull(valueAsBytes, 0, valueAsBytes.length); bytesInPacket += valueAsBytes.length; totalBytesRead += valueAsBytes.length; if (bytesInPacket >= packetIsFullAt) { bytesReadAtLastSend = totalBytesRead; mysql.sendCommand(24, null, packet, true, null, 0); bytesInPacket = 0; packet.clear(); packet.writeByte((byte)24); packet.writeLong(this.serverStatementId); packet.writeInt(parameterIndex); }  }  if (totalBytesRead != bytesReadAtLastSend) mysql.sendCommand(24, null, packet, true, null, 0);  if (!readAny) mysql.sendCommand(24, null, packet, true, null, 0);  } catch (IOException ioEx) { SQLException sqlEx = SQLError.createSQLException(Messages.getString("ServerPreparedStatement.24") + ioEx.toString(), "S1000", getExceptionInterceptor()); sqlEx.initCause(ioEx); throw sqlEx; } finally { if (this.connection.getAutoClosePStmtStreams() && inStream != null) try { inStream.close(); } catch (IOException ioEx) {}  }  }  } private void storeStream(MysqlIO mysql, int parameterIndex, Buffer packet, InputStream inStream) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { byte[] buf = new byte[8192]; int numRead = 0; try { int bytesInPacket = 0; int totalBytesRead = 0; int bytesReadAtLastSend = 0; int packetIsFullAt = this.connection.getBlobSendChunkSize(); packet.clear(); packet.writeByte((byte)24); packet.writeLong(this.serverStatementId); packet.writeInt(parameterIndex); boolean readAny = false; while ((numRead = inStream.read(buf)) != -1) { readAny = true; packet.writeBytesNoNull(buf, 0, numRead); bytesInPacket += numRead; totalBytesRead += numRead; if (bytesInPacket >= packetIsFullAt) { bytesReadAtLastSend = totalBytesRead; mysql.sendCommand(24, null, packet, true, null, 0); bytesInPacket = 0; packet.clear(); packet.writeByte((byte)24); packet.writeLong(this.serverStatementId); packet.writeInt(parameterIndex); }  }  if (totalBytesRead != bytesReadAtLastSend) mysql.sendCommand(24, null, packet, true, null, 0);  if (!readAny) mysql.sendCommand(24, null, packet, true, null, 0);  } catch (IOException ioEx) { SQLException sqlEx = SQLError.createSQLException(Messages.getString("ServerPreparedStatement.25") + ioEx.toString(), "S1000", getExceptionInterceptor()); sqlEx.initCause(ioEx); throw sqlEx; } finally { if (this.connection.getAutoClosePStmtStreams() && inStream != null) try { inStream.close(); } catch (IOException ioEx) {}  }  }  } public String toString() { StringBuilder toStringBuf = new StringBuilder(); toStringBuf.append("com.mysql.jdbc.ServerPreparedStatement["); toStringBuf.append(this.serverStatementId); toStringBuf.append("] - "); try { toStringBuf.append(asSql()); } catch (SQLException sqlEx) { toStringBuf.append(Messages.getString("ServerPreparedStatement.6")); toStringBuf.append(sqlEx); }  return toStringBuf.toString(); } protected long getServerStatementId() { return this.serverStatementId; } public boolean canRewriteAsMultiValueInsertAtSqlLevel() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (!this.hasCheckedRewrite) { this.hasCheckedRewrite = true; this.canRewrite = canRewrite(this.originalSql, isOnDuplicateKeyUpdate(), getLocationOfOnDuplicateKeyUpdate(), 0); this.parseInfo = new PreparedStatement.ParseInfo(this.originalSql, this.connection, this.connection.getMetaData(), this.charEncoding, this.charConverter); }  return this.canRewrite; }  } protected boolean isOnDuplicateKeyUpdate() throws SQLException { synchronized (checkClosed().getConnectionMutex()) {
/* 2573 */       return (getLocationOfOnDuplicateKeyUpdate() != -1);
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long[] computeMaxParameterSetSizeAndBatchSize(int numBatchedArgs) throws SQLException {
/* 2585 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2586 */       long sizeOfEntireBatch = 10L;
/* 2587 */       long maxSizeOfParameterSet = 0L;
/*      */       
/* 2589 */       for (int i = 0; i < numBatchedArgs; i++) {
/* 2590 */         BindValue[] paramArg = ((BatchedBindValues)this.batchedArgs.get(i)).batchedParameterValues;
/*      */         
/* 2592 */         long sizeOfParameterSet = 0L;
/*      */         
/* 2594 */         sizeOfParameterSet += ((this.parameterCount + 7) / 8);
/*      */         
/* 2596 */         sizeOfParameterSet += (this.parameterCount * 2);
/*      */         
/* 2598 */         for (int j = 0; j < this.parameterBindings.length; j++) {
/* 2599 */           if (!(paramArg[j]).isNull) {
/*      */             
/* 2601 */             long size = paramArg[j].getBoundLength();
/*      */             
/* 2603 */             if ((paramArg[j]).isLongData) {
/* 2604 */               if (size != -1L) {
/* 2605 */                 sizeOfParameterSet += size;
/*      */               }
/*      */             } else {
/* 2608 */               sizeOfParameterSet += size;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/* 2613 */         sizeOfEntireBatch += sizeOfParameterSet;
/*      */         
/* 2615 */         if (sizeOfParameterSet > maxSizeOfParameterSet) {
/* 2616 */           maxSizeOfParameterSet = sizeOfParameterSet;
/*      */         }
/*      */       } 
/*      */       
/* 2620 */       (new long[2])[0] = maxSizeOfParameterSet; (new long[2])[1] = sizeOfEntireBatch; return new long[2];
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected int setOneBatchedParameterSet(PreparedStatement batchedStatement, int batchedParamIndex, Object paramSet) throws SQLException {
/* 2626 */     BindValue[] paramArg = ((BatchedBindValues)paramSet).batchedParameterValues;
/*      */     
/* 2628 */     for (int j = 0; j < paramArg.length; j++) {
/* 2629 */       if ((paramArg[j]).isNull) {
/* 2630 */         batchedStatement.setNull(batchedParamIndex++, 0);
/*      */       }
/* 2632 */       else if ((paramArg[j]).isLongData) {
/* 2633 */         Object value = (paramArg[j]).value;
/*      */         
/* 2635 */         if (value instanceof InputStream) {
/* 2636 */           batchedStatement.setBinaryStream(batchedParamIndex++, (InputStream)value, (int)(paramArg[j]).bindLength);
/*      */         } else {
/* 2638 */           batchedStatement.setCharacterStream(batchedParamIndex++, (Reader)value, (int)(paramArg[j]).bindLength);
/*      */         } 
/*      */       } else {
/*      */         Object value;
/* 2642 */         switch ((paramArg[j]).bufferType) {
/*      */           
/*      */           case 1:
/* 2645 */             batchedStatement.setByte(batchedParamIndex++, (byte)(int)(paramArg[j]).longBinding);
/*      */             break;
/*      */           case 2:
/* 2648 */             batchedStatement.setShort(batchedParamIndex++, (short)(int)(paramArg[j]).longBinding);
/*      */             break;
/*      */           case 3:
/* 2651 */             batchedStatement.setInt(batchedParamIndex++, (int)(paramArg[j]).longBinding);
/*      */             break;
/*      */           case 8:
/* 2654 */             batchedStatement.setLong(batchedParamIndex++, (paramArg[j]).longBinding);
/*      */             break;
/*      */           case 4:
/* 2657 */             batchedStatement.setFloat(batchedParamIndex++, (paramArg[j]).floatBinding);
/*      */             break;
/*      */           case 5:
/* 2660 */             batchedStatement.setDouble(batchedParamIndex++, (paramArg[j]).doubleBinding);
/*      */             break;
/*      */           case 11:
/* 2663 */             batchedStatement.setTime(batchedParamIndex++, (Time)(paramArg[j]).value);
/*      */             break;
/*      */           case 10:
/* 2666 */             batchedStatement.setDate(batchedParamIndex++, (Date)(paramArg[j]).value);
/*      */             break;
/*      */           case 7:
/*      */           case 12:
/* 2670 */             batchedStatement.setTimestamp(batchedParamIndex++, (Timestamp)(paramArg[j]).value);
/*      */             break;
/*      */           case 0:
/*      */           case 15:
/*      */           case 246:
/*      */           case 253:
/*      */           case 254:
/* 2677 */             value = (paramArg[j]).value;
/*      */             
/* 2679 */             if (value instanceof byte[]) {
/* 2680 */               batchedStatement.setBytes(batchedParamIndex, (byte[])value);
/*      */             } else {
/* 2682 */               batchedStatement.setString(batchedParamIndex, (String)value);
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 2687 */             if (batchedStatement instanceof ServerPreparedStatement) {
/* 2688 */               BindValue asBound = ((ServerPreparedStatement)batchedStatement).getBinding(batchedParamIndex, false);
/* 2689 */               asBound.bufferType = (paramArg[j]).bufferType;
/*      */             } 
/*      */             
/* 2692 */             batchedParamIndex++;
/*      */             break;
/*      */           
/*      */           default:
/* 2696 */             throw new IllegalArgumentException("Unknown type when re-binding parameter into batched statement for parameter index " + batchedParamIndex);
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/*      */     } 
/* 2703 */     return batchedParamIndex;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean containsOnDuplicateKeyUpdateInSQL() {
/* 2708 */     return this.hasOnDuplicateKeyUpdate;
/*      */   }
/*      */ 
/*      */   
/*      */   protected PreparedStatement prepareBatchedInsertSQL(MySQLConnection localConn, int numBatches) throws SQLException {
/* 2713 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/* 2715 */       PreparedStatement pstmt = ((Wrapper)localConn.prepareStatement(this.parseInfo.getSqlForBatch(numBatches), this.resultSetType, this.resultSetConcurrency)).<PreparedStatement>unwrap(PreparedStatement.class);
/*      */       
/* 2717 */       pstmt.setRetrieveGeneratedKeys(this.retrieveGeneratedKeys);
/*      */       
/* 2719 */       return pstmt;
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
/*      */   public void setPoolable(boolean poolable) throws SQLException {
/* 2732 */     if (!poolable) {
/* 2733 */       this.connection.decachePreparedStatement(this);
/*      */     }
/* 2735 */     super.setPoolable(poolable);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ServerPreparedStatement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */