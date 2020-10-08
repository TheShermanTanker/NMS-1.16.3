/*     */ package com.mysql.jdbc.jdbc2.optional;
/*     */ 
/*     */ import com.mysql.jdbc.Connection;
/*     */ import com.mysql.jdbc.SQLError;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.sql.Array;
/*     */ import java.sql.Blob;
/*     */ import java.sql.Clob;
/*     */ import java.sql.NClob;
/*     */ import java.sql.SQLClientInfoException;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLXML;
/*     */ import java.sql.Struct;
/*     */ import java.util.HashMap;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDBC4ConnectionWrapper
/*     */   extends ConnectionWrapper
/*     */ {
/*     */   public JDBC4ConnectionWrapper(MysqlPooledConnection mysqlPooledConnection, Connection mysqlConnection, boolean forXa) throws SQLException {
/*  64 */     super(mysqlPooledConnection, mysqlConnection, forXa);
/*     */   }
/*     */   
/*     */   public void close() throws SQLException {
/*     */     try {
/*  69 */       super.close();
/*     */     } finally {
/*  71 */       this.unwrappedInterfaces = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public SQLXML createSQLXML() throws SQLException {
/*  76 */     checkClosed();
/*     */     
/*     */     try {
/*  79 */       return this.mc.createSQLXML();
/*  80 */     } catch (SQLException sqlException) {
/*  81 */       checkAndFireConnectionError(sqlException);
/*     */ 
/*     */       
/*  84 */       return null;
/*     */     } 
/*     */   }
/*     */   public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
/*  88 */     checkClosed();
/*     */     
/*     */     try {
/*  91 */       return this.mc.createArrayOf(typeName, elements);
/*  92 */     } catch (SQLException sqlException) {
/*  93 */       checkAndFireConnectionError(sqlException);
/*     */ 
/*     */       
/*  96 */       return null;
/*     */     } 
/*     */   }
/*     */   public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
/* 100 */     checkClosed();
/*     */     
/*     */     try {
/* 103 */       return this.mc.createStruct(typeName, attributes);
/* 104 */     } catch (SQLException sqlException) {
/* 105 */       checkAndFireConnectionError(sqlException);
/*     */ 
/*     */       
/* 108 */       return null;
/*     */     } 
/*     */   }
/*     */   public Properties getClientInfo() throws SQLException {
/* 112 */     checkClosed();
/*     */     
/*     */     try {
/* 115 */       return this.mc.getClientInfo();
/* 116 */     } catch (SQLException sqlException) {
/* 117 */       checkAndFireConnectionError(sqlException);
/*     */ 
/*     */       
/* 120 */       return null;
/*     */     } 
/*     */   }
/*     */   public String getClientInfo(String name) throws SQLException {
/* 124 */     checkClosed();
/*     */     
/*     */     try {
/* 127 */       return this.mc.getClientInfo(name);
/* 128 */     } catch (SQLException sqlException) {
/* 129 */       checkAndFireConnectionError(sqlException);
/*     */ 
/*     */       
/* 132 */       return null;
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
/*     */   public synchronized boolean isValid(int timeout) throws SQLException {
/*     */     try {
/* 159 */       return this.mc.isValid(timeout);
/* 160 */     } catch (SQLException sqlException) {
/* 161 */       checkAndFireConnectionError(sqlException);
/*     */ 
/*     */       
/* 164 */       return false;
/*     */     } 
/*     */   }
/*     */   public void setClientInfo(Properties properties) throws SQLClientInfoException {
/*     */     try {
/* 169 */       checkClosed();
/*     */       
/* 171 */       this.mc.setClientInfo(properties);
/* 172 */     } catch (SQLException sqlException) {
/*     */       try {
/* 174 */         checkAndFireConnectionError(sqlException);
/* 175 */       } catch (SQLException sqlEx2) {
/* 176 */         SQLClientInfoException clientEx = new SQLClientInfoException();
/* 177 */         clientEx.initCause(sqlEx2);
/*     */         
/* 179 */         throw clientEx;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setClientInfo(String name, String value) throws SQLClientInfoException {
/*     */     try {
/* 186 */       checkClosed();
/*     */       
/* 188 */       this.mc.setClientInfo(name, value);
/* 189 */     } catch (SQLException sqlException) {
/*     */       try {
/* 191 */         checkAndFireConnectionError(sqlException);
/* 192 */       } catch (SQLException sqlEx2) {
/* 193 */         SQLClientInfoException clientEx = new SQLClientInfoException();
/* 194 */         clientEx.initCause(sqlEx2);
/*     */         
/* 196 */         throw clientEx;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 222 */     checkClosed();
/*     */     
/* 224 */     boolean isInstance = iface.isInstance(this);
/*     */     
/* 226 */     if (isInstance) {
/* 227 */       return true;
/*     */     }
/*     */     
/* 230 */     return (iface.getName().equals("com.mysql.jdbc.Connection") || iface.getName().equals("com.mysql.jdbc.ConnectionProperties"));
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
/*     */   public synchronized <T> T unwrap(Class<T> iface) throws SQLException {
/*     */     try {
/* 254 */       if ("java.sql.Connection".equals(iface.getName()) || "java.sql.Wrapper.class".equals(iface.getName())) {
/* 255 */         return iface.cast(this);
/*     */       }
/*     */       
/* 258 */       if (this.unwrappedInterfaces == null) {
/* 259 */         this.unwrappedInterfaces = new HashMap<Class<?>, Object>();
/*     */       }
/*     */       
/* 262 */       Object cachedUnwrapped = this.unwrappedInterfaces.get(iface);
/*     */       
/* 264 */       if (cachedUnwrapped == null) {
/* 265 */         cachedUnwrapped = Proxy.newProxyInstance(this.mc.getClass().getClassLoader(), new Class[] { iface }, new WrapperBase.ConnectionErrorFiringInvocationHandler(this, this.mc));
/*     */         
/* 267 */         this.unwrappedInterfaces.put(iface, cachedUnwrapped);
/*     */       } 
/*     */       
/* 270 */       return iface.cast(cachedUnwrapped);
/* 271 */     } catch (ClassCastException cce) {
/* 272 */       throw SQLError.createSQLException("Unable to unwrap to " + iface.toString(), "S1009", this.exceptionInterceptor);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Blob createBlob() throws SQLException {
/* 280 */     checkClosed();
/*     */     
/*     */     try {
/* 283 */       return this.mc.createBlob();
/* 284 */     } catch (SQLException sqlException) {
/* 285 */       checkAndFireConnectionError(sqlException);
/*     */ 
/*     */       
/* 288 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Clob createClob() throws SQLException {
/* 295 */     checkClosed();
/*     */     
/*     */     try {
/* 298 */       return this.mc.createClob();
/* 299 */     } catch (SQLException sqlException) {
/* 300 */       checkAndFireConnectionError(sqlException);
/*     */ 
/*     */       
/* 303 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NClob createNClob() throws SQLException {
/* 310 */     checkClosed();
/*     */     
/*     */     try {
/* 313 */       return this.mc.createNClob();
/* 314 */     } catch (SQLException sqlException) {
/* 315 */       checkAndFireConnectionError(sqlException);
/*     */ 
/*     */       
/* 318 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\JDBC4ConnectionWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */