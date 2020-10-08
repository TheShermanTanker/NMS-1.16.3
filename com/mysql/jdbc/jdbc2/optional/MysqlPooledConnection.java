/*     */ package com.mysql.jdbc.jdbc2.optional;
/*     */ 
/*     */ import com.mysql.jdbc.Connection;
/*     */ import com.mysql.jdbc.ExceptionInterceptor;
/*     */ import com.mysql.jdbc.SQLError;
/*     */ import com.mysql.jdbc.Util;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.sql.ConnectionEvent;
/*     */ import javax.sql.ConnectionEventListener;
/*     */ import javax.sql.PooledConnection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MysqlPooledConnection
/*     */   implements PooledConnection
/*     */ {
/*     */   private static final Constructor<?> JDBC_4_POOLED_CONNECTION_WRAPPER_CTOR;
/*     */   public static final int CONNECTION_ERROR_EVENT = 1;
/*     */   public static final int CONNECTION_CLOSED_EVENT = 2;
/*     */   private Map<ConnectionEventListener, ConnectionEventListener> connectionEventListeners;
/*     */   private Connection logicalHandle;
/*     */   private Connection physicalConn;
/*     */   private ExceptionInterceptor exceptionInterceptor;
/*     */   
/*     */   static {
/*  50 */     if (Util.isJdbc4()) {
/*     */       try {
/*  52 */         JDBC_4_POOLED_CONNECTION_WRAPPER_CTOR = Class.forName("com.mysql.jdbc.jdbc2.optional.JDBC4MysqlPooledConnection").getConstructor(new Class[] { Connection.class });
/*     */       }
/*  54 */       catch (SecurityException e) {
/*  55 */         throw new RuntimeException(e);
/*  56 */       } catch (NoSuchMethodException e) {
/*  57 */         throw new RuntimeException(e);
/*  58 */       } catch (ClassNotFoundException e) {
/*  59 */         throw new RuntimeException(e);
/*     */       } 
/*     */     } else {
/*  62 */       JDBC_4_POOLED_CONNECTION_WRAPPER_CTOR = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static MysqlPooledConnection getInstance(Connection connection) throws SQLException {
/*  67 */     if (!Util.isJdbc4()) {
/*  68 */       return new MysqlPooledConnection(connection);
/*     */     }
/*     */     
/*  71 */     return (MysqlPooledConnection)Util.handleNewInstance(JDBC_4_POOLED_CONNECTION_WRAPPER_CTOR, new Object[] { connection }, connection.getExceptionInterceptor());
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
/*     */   public MysqlPooledConnection(Connection connection) {
/* 100 */     this.logicalHandle = null;
/* 101 */     this.physicalConn = connection;
/* 102 */     this.connectionEventListeners = new HashMap<ConnectionEventListener, ConnectionEventListener>();
/* 103 */     this.exceptionInterceptor = this.physicalConn.getExceptionInterceptor();
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
/*     */   public synchronized void addConnectionEventListener(ConnectionEventListener connectioneventlistener) {
/* 115 */     if (this.connectionEventListeners != null) {
/* 116 */       this.connectionEventListeners.put(connectioneventlistener, connectioneventlistener);
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
/*     */   public synchronized void removeConnectionEventListener(ConnectionEventListener connectioneventlistener) {
/* 129 */     if (this.connectionEventListeners != null) {
/* 130 */       this.connectionEventListeners.remove(connectioneventlistener);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Connection getConnection() throws SQLException {
/* 141 */     return getConnection(true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected synchronized Connection getConnection(boolean resetServerState, boolean forXa) throws SQLException {
/* 146 */     if (this.physicalConn == null) {
/*     */       
/* 148 */       SQLException sqlException = SQLError.createSQLException("Physical Connection doesn't exist", this.exceptionInterceptor);
/* 149 */       callConnectionEventListeners(1, sqlException);
/*     */       
/* 151 */       throw sqlException;
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 156 */       if (this.logicalHandle != null) {
/* 157 */         ((ConnectionWrapper)this.logicalHandle).close(false);
/*     */       }
/*     */       
/* 160 */       if (resetServerState) {
/* 161 */         this.physicalConn.resetServerState();
/*     */       }
/*     */       
/* 164 */       this.logicalHandle = (Connection)ConnectionWrapper.getInstance(this, this.physicalConn, forXa);
/* 165 */     } catch (SQLException sqlException) {
/* 166 */       callConnectionEventListeners(1, sqlException);
/*     */       
/* 168 */       throw sqlException;
/*     */     } 
/*     */     
/* 171 */     return this.logicalHandle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void close() throws SQLException {
/* 182 */     if (this.physicalConn != null) {
/* 183 */       this.physicalConn.close();
/*     */       
/* 185 */       this.physicalConn = null;
/*     */     } 
/*     */     
/* 188 */     if (this.connectionEventListeners != null) {
/* 189 */       this.connectionEventListeners.clear();
/*     */       
/* 191 */       this.connectionEventListeners = null;
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
/*     */   protected synchronized void callConnectionEventListeners(int eventType, SQLException sqlException) {
/* 209 */     if (this.connectionEventListeners == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 214 */     Iterator<Map.Entry<ConnectionEventListener, ConnectionEventListener>> iterator = this.connectionEventListeners.entrySet().iterator();
/*     */     
/* 216 */     ConnectionEvent connectionevent = new ConnectionEvent(this, sqlException);
/*     */     
/* 218 */     while (iterator.hasNext()) {
/*     */       
/* 220 */       ConnectionEventListener connectioneventlistener = (ConnectionEventListener)((Map.Entry)iterator.next()).getValue();
/*     */       
/* 222 */       if (eventType == 2) {
/* 223 */         connectioneventlistener.connectionClosed(connectionevent); continue;
/* 224 */       }  if (eventType == 1) {
/* 225 */         connectioneventlistener.connectionErrorOccurred(connectionevent);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ExceptionInterceptor getExceptionInterceptor() {
/* 231 */     return this.exceptionInterceptor;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\MysqlPooledConnection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */