/*     */ package com.mysql.jdbc.jdbc2.optional;
/*     */ 
/*     */ import com.mysql.jdbc.Connection;
/*     */ import com.mysql.jdbc.Messages;
/*     */ import com.mysql.jdbc.StringUtils;
/*     */ import com.mysql.jdbc.Util;
/*     */ import com.mysql.jdbc.log.Log;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.sql.XAConnection;
/*     */ import javax.transaction.xa.XAException;
/*     */ import javax.transaction.xa.XAResource;
/*     */ import javax.transaction.xa.Xid;
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
/*     */ public class MysqlXAConnection
/*     */   extends MysqlPooledConnection
/*     */   implements XAConnection, XAResource
/*     */ {
/*     */   private static final int MAX_COMMAND_LENGTH = 300;
/*     */   private Connection underlyingConnection;
/*     */   private static final Map<Integer, Integer> MYSQL_ERROR_CODES_TO_XA_ERROR_CODES;
/*     */   private Log log;
/*     */   protected boolean logXaCommands;
/*     */   private static final Constructor<?> JDBC_4_XA_CONNECTION_WRAPPER_CTOR;
/*     */   
/*     */   static {
/*  74 */     HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
/*     */     
/*  76 */     temp.put(Integer.valueOf(1397), Integer.valueOf(-4));
/*  77 */     temp.put(Integer.valueOf(1398), Integer.valueOf(-5));
/*  78 */     temp.put(Integer.valueOf(1399), Integer.valueOf(-7));
/*  79 */     temp.put(Integer.valueOf(1400), Integer.valueOf(-9));
/*  80 */     temp.put(Integer.valueOf(1401), Integer.valueOf(-3));
/*  81 */     temp.put(Integer.valueOf(1402), Integer.valueOf(100));
/*  82 */     temp.put(Integer.valueOf(1440), Integer.valueOf(-8));
/*  83 */     temp.put(Integer.valueOf(1613), Integer.valueOf(106));
/*  84 */     temp.put(Integer.valueOf(1614), Integer.valueOf(102));
/*     */     
/*  86 */     MYSQL_ERROR_CODES_TO_XA_ERROR_CODES = Collections.unmodifiableMap(temp);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     if (Util.isJdbc4()) {
/*     */       try {
/*  94 */         JDBC_4_XA_CONNECTION_WRAPPER_CTOR = Class.forName("com.mysql.jdbc.jdbc2.optional.JDBC4MysqlXAConnection").getConstructor(new Class[] { Connection.class, boolean.class });
/*     */       }
/*  96 */       catch (SecurityException e) {
/*  97 */         throw new RuntimeException(e);
/*  98 */       } catch (NoSuchMethodException e) {
/*  99 */         throw new RuntimeException(e);
/* 100 */       } catch (ClassNotFoundException e) {
/* 101 */         throw new RuntimeException(e);
/*     */       } 
/*     */     } else {
/* 104 */       JDBC_4_XA_CONNECTION_WRAPPER_CTOR = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static MysqlXAConnection getInstance(Connection mysqlConnection, boolean logXaCommands) throws SQLException {
/* 109 */     if (!Util.isJdbc4()) {
/* 110 */       return new MysqlXAConnection(mysqlConnection, logXaCommands);
/*     */     }
/*     */     
/* 113 */     return (MysqlXAConnection)Util.handleNewInstance(JDBC_4_XA_CONNECTION_WRAPPER_CTOR, new Object[] { mysqlConnection, Boolean.valueOf(logXaCommands) }, mysqlConnection.getExceptionInterceptor());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MysqlXAConnection(Connection connection, boolean logXaCommands) throws SQLException {
/* 121 */     super(connection);
/* 122 */     this.underlyingConnection = connection;
/* 123 */     this.log = connection.getLog();
/* 124 */     this.logXaCommands = logXaCommands;
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
/*     */   public XAResource getXAResource() throws SQLException {
/* 137 */     return this;
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
/*     */   public int getTransactionTimeout() throws XAException {
/* 154 */     return 0;
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
/*     */   
/*     */   public boolean setTransactionTimeout(int arg0) throws XAException {
/* 179 */     return false;
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
/*     */   public boolean isSameRM(XAResource xares) throws XAException {
/* 199 */     if (xares instanceof MysqlXAConnection) {
/* 200 */       return this.underlyingConnection.isSameResource(((MysqlXAConnection)xares).underlyingConnection);
/*     */     }
/*     */     
/* 203 */     return false;
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
/*     */   public Xid[] recover(int flag) throws XAException {
/* 244 */     return recover((Connection)this.underlyingConnection, flag);
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
/*     */   
/*     */   protected static Xid[] recover(Connection c, int flag) throws XAException {
/* 269 */     boolean startRscan = ((flag & 0x1000000) > 0);
/* 270 */     boolean endRscan = ((flag & 0x800000) > 0);
/*     */     
/* 272 */     if (!startRscan && !endRscan && flag != 0) {
/* 273 */       throw new MysqlXAException(-5, Messages.getString("MysqlXAConnection.001"), null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 282 */     if (!startRscan) {
/* 283 */       return new Xid[0];
/*     */     }
/*     */     
/* 286 */     ResultSet rs = null;
/* 287 */     Statement stmt = null;
/*     */     
/* 289 */     List<MysqlXid> recoveredXidList = new ArrayList<MysqlXid>();
/*     */ 
/*     */     
/*     */     try {
/* 293 */       stmt = c.createStatement();
/*     */       
/* 295 */       rs = stmt.executeQuery("XA RECOVER");
/*     */       
/* 297 */       while (rs.next()) {
/* 298 */         int formatId = rs.getInt(1);
/* 299 */         int gtridLength = rs.getInt(2);
/* 300 */         int bqualLength = rs.getInt(3);
/* 301 */         byte[] gtridAndBqual = rs.getBytes(4);
/*     */         
/* 303 */         byte[] gtrid = new byte[gtridLength];
/* 304 */         byte[] bqual = new byte[bqualLength];
/*     */         
/* 306 */         if (gtridAndBqual.length != gtridLength + bqualLength) {
/* 307 */           throw new MysqlXAException(105, Messages.getString("MysqlXAConnection.002"), null);
/*     */         }
/*     */         
/* 310 */         System.arraycopy(gtridAndBqual, 0, gtrid, 0, gtridLength);
/* 311 */         System.arraycopy(gtridAndBqual, gtridLength, bqual, 0, bqualLength);
/*     */         
/* 313 */         recoveredXidList.add(new MysqlXid(gtrid, bqual, formatId));
/*     */       } 
/* 315 */     } catch (SQLException sqlEx) {
/* 316 */       throw mapXAExceptionFromSQLException(sqlEx);
/*     */     } finally {
/* 318 */       if (rs != null) {
/*     */         try {
/* 320 */           rs.close();
/* 321 */         } catch (SQLException sqlEx) {
/* 322 */           throw mapXAExceptionFromSQLException(sqlEx);
/*     */         } 
/*     */       }
/*     */       
/* 326 */       if (stmt != null) {
/*     */         try {
/* 328 */           stmt.close();
/* 329 */         } catch (SQLException sqlEx) {
/* 330 */           throw mapXAExceptionFromSQLException(sqlEx);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 335 */     int numXids = recoveredXidList.size();
/*     */     
/* 337 */     Xid[] asXids = new Xid[numXids];
/* 338 */     Object[] asObjects = recoveredXidList.toArray();
/*     */     
/* 340 */     for (int i = 0; i < numXids; i++) {
/* 341 */       asXids[i] = (Xid)asObjects[i];
/*     */     }
/*     */     
/* 344 */     return asXids;
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
/*     */   public int prepare(Xid xid) throws XAException {
/* 366 */     StringBuilder commandBuf = new StringBuilder(300);
/* 367 */     commandBuf.append("XA PREPARE ");
/* 368 */     appendXid(commandBuf, xid);
/*     */     
/* 370 */     dispatchCommand(commandBuf.toString());
/*     */     
/* 372 */     return 0;
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
/*     */   public void forget(Xid xid) throws XAException {}
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
/*     */   public void rollback(Xid xid) throws XAException {
/* 408 */     StringBuilder commandBuf = new StringBuilder(300);
/* 409 */     commandBuf.append("XA ROLLBACK ");
/* 410 */     appendXid(commandBuf, xid);
/*     */     
/*     */     try {
/* 413 */       dispatchCommand(commandBuf.toString());
/*     */     } finally {
/* 415 */       this.underlyingConnection.setInGlobalTx(false);
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
/*     */   public void end(Xid xid, int flags) throws XAException {
/* 448 */     StringBuilder commandBuf = new StringBuilder(300);
/* 449 */     commandBuf.append("XA END ");
/* 450 */     appendXid(commandBuf, xid);
/*     */     
/* 452 */     switch (flags) {
/*     */       case 67108864:
/*     */         break;
/*     */       case 33554432:
/* 456 */         commandBuf.append(" SUSPEND");
/*     */         break;
/*     */       case 536870912:
/*     */         break;
/*     */       default:
/* 461 */         throw new XAException(-5);
/*     */     } 
/*     */     
/* 464 */     dispatchCommand(commandBuf.toString());
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(Xid xid, int flags) throws XAException {
/* 491 */     StringBuilder commandBuf = new StringBuilder(300);
/* 492 */     commandBuf.append("XA START ");
/* 493 */     appendXid(commandBuf, xid);
/*     */     
/* 495 */     switch (flags) {
/*     */       case 2097152:
/* 497 */         commandBuf.append(" JOIN");
/*     */         break;
/*     */       case 134217728:
/* 500 */         commandBuf.append(" RESUME");
/*     */         break;
/*     */       
/*     */       case 0:
/*     */         break;
/*     */       default:
/* 506 */         throw new XAException(-5);
/*     */     } 
/*     */     
/* 509 */     dispatchCommand(commandBuf.toString());
/*     */     
/* 511 */     this.underlyingConnection.setInGlobalTx(true);
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
/*     */   
/*     */   public void commit(Xid xid, boolean onePhase) throws XAException {
/* 536 */     StringBuilder commandBuf = new StringBuilder(300);
/* 537 */     commandBuf.append("XA COMMIT ");
/* 538 */     appendXid(commandBuf, xid);
/*     */     
/* 540 */     if (onePhase) {
/* 541 */       commandBuf.append(" ONE PHASE");
/*     */     }
/*     */     
/*     */     try {
/* 545 */       dispatchCommand(commandBuf.toString());
/*     */     } finally {
/* 547 */       this.underlyingConnection.setInGlobalTx(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ResultSet dispatchCommand(String command) throws XAException {
/* 552 */     Statement stmt = null;
/*     */     
/*     */     try {
/* 555 */       if (this.logXaCommands) {
/* 556 */         this.log.logDebug("Executing XA statement: " + command);
/*     */       }
/*     */ 
/*     */       
/* 560 */       stmt = this.underlyingConnection.createStatement();
/*     */       
/* 562 */       stmt.execute(command);
/*     */       
/* 564 */       ResultSet rs = stmt.getResultSet();
/*     */       
/* 566 */       return rs;
/* 567 */     } catch (SQLException sqlEx) {
/* 568 */       throw mapXAExceptionFromSQLException(sqlEx);
/*     */     } finally {
/* 570 */       if (stmt != null) {
/*     */         try {
/* 572 */           stmt.close();
/* 573 */         } catch (SQLException sqlEx) {}
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static XAException mapXAExceptionFromSQLException(SQLException sqlEx) {
/* 580 */     Integer xaCode = MYSQL_ERROR_CODES_TO_XA_ERROR_CODES.get(Integer.valueOf(sqlEx.getErrorCode()));
/*     */     
/* 582 */     if (xaCode != null) {
/* 583 */       return (XAException)(new MysqlXAException(xaCode.intValue(), sqlEx.getMessage(), null)).initCause(sqlEx);
/*     */     }
/*     */     
/* 586 */     return (XAException)(new MysqlXAException(-7, Messages.getString("MysqlXAConnection.003"), null)).initCause(sqlEx);
/*     */   }
/*     */   
/*     */   private static void appendXid(StringBuilder builder, Xid xid) {
/* 590 */     byte[] gtrid = xid.getGlobalTransactionId();
/* 591 */     byte[] btrid = xid.getBranchQualifier();
/*     */     
/* 593 */     if (gtrid != null) {
/* 594 */       StringUtils.appendAsHex(builder, gtrid);
/*     */     }
/*     */     
/* 597 */     builder.append(',');
/* 598 */     if (btrid != null) {
/* 599 */       StringUtils.appendAsHex(builder, btrid);
/*     */     }
/*     */     
/* 602 */     builder.append(',');
/* 603 */     StringUtils.appendAsHex(builder, xid.getFormatId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Connection getConnection() throws SQLException {
/* 613 */     Connection connToWrap = getConnection(false, true);
/*     */     
/* 615 */     return connToWrap;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\MysqlXAConnection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */