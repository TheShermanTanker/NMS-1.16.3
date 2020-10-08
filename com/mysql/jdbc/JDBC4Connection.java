/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.sql.Array;
/*     */ import java.sql.Blob;
/*     */ import java.sql.Clob;
/*     */ import java.sql.NClob;
/*     */ import java.sql.SQLClientInfoException;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLXML;
/*     */ import java.sql.Struct;
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
/*     */ public class JDBC4Connection
/*     */   extends ConnectionImpl
/*     */   implements JDBC4MySQLConnection
/*     */ {
/*     */   private static final long serialVersionUID = 2877471301981509475L;
/*     */   private JDBC4ClientInfoProvider infoProvider;
/*     */   
/*     */   public JDBC4Connection(String hostToConnectTo, int portToConnectTo, Properties info, String databaseToConnectTo, String url) throws SQLException {
/*  47 */     super(hostToConnectTo, portToConnectTo, info, databaseToConnectTo, url);
/*     */   }
/*     */   
/*     */   public SQLXML createSQLXML() throws SQLException {
/*  51 */     return new JDBC4MysqlSQLXML(getExceptionInterceptor());
/*     */   }
/*     */   
/*     */   public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
/*  55 */     throw SQLError.createSQLFeatureNotSupportedException();
/*     */   }
/*     */   
/*     */   public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
/*  59 */     throw SQLError.createSQLFeatureNotSupportedException();
/*     */   }
/*     */   
/*     */   public Properties getClientInfo() throws SQLException {
/*  63 */     return getClientInfoProviderImpl().getClientInfo(this);
/*     */   }
/*     */   
/*     */   public String getClientInfo(String name) throws SQLException {
/*  67 */     return getClientInfoProviderImpl().getClientInfo(this, name);
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
/*     */   public boolean isValid(int timeout) throws SQLException {
/*  92 */     synchronized (getConnectionMutex()) {
/*  93 */       if (isClosed()) {
/*  94 */         return false;
/*     */       }
/*     */       
/*     */       try {
/*     */         try {
/*  99 */           pingInternal(false, timeout * 1000);
/* 100 */         } catch (Throwable t) {
/*     */           try {
/* 102 */             abortInternal();
/* 103 */           } catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */           
/* 107 */           return false;
/*     */         }
/*     */       
/* 110 */       } catch (Throwable t) {
/* 111 */         return false;
/*     */       } 
/*     */       
/* 114 */       return true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setClientInfo(Properties properties) throws SQLClientInfoException {
/*     */     try {
/* 120 */       getClientInfoProviderImpl().setClientInfo(this, properties);
/* 121 */     } catch (SQLClientInfoException ciEx) {
/* 122 */       throw ciEx;
/* 123 */     } catch (SQLException sqlEx) {
/* 124 */       SQLClientInfoException clientInfoEx = new SQLClientInfoException();
/* 125 */       clientInfoEx.initCause(sqlEx);
/*     */       
/* 127 */       throw clientInfoEx;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setClientInfo(String name, String value) throws SQLClientInfoException {
/*     */     try {
/* 133 */       getClientInfoProviderImpl().setClientInfo(this, name, value);
/* 134 */     } catch (SQLClientInfoException ciEx) {
/* 135 */       throw ciEx;
/* 136 */     } catch (SQLException sqlEx) {
/* 137 */       SQLClientInfoException clientInfoEx = new SQLClientInfoException();
/* 138 */       clientInfoEx.initCause(sqlEx);
/*     */       
/* 140 */       throw clientInfoEx;
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
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 162 */     checkClosed();
/*     */ 
/*     */     
/* 165 */     return iface.isInstance(this);
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
/*     */   public <T> T unwrap(Class<T> iface) throws SQLException {
/*     */     try {
/* 187 */       return iface.cast(this);
/* 188 */     } catch (ClassCastException cce) {
/* 189 */       throw SQLError.createSQLException("Unable to unwrap to " + iface.toString(), "S1009", getExceptionInterceptor());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Blob createBlob() {
/* 197 */     return new Blob(getExceptionInterceptor());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Clob createClob() {
/* 204 */     return new Clob(getExceptionInterceptor());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NClob createNClob() {
/* 211 */     return new JDBC4NClob(getExceptionInterceptor());
/*     */   }
/*     */   
/*     */   public JDBC4ClientInfoProvider getClientInfoProviderImpl() throws SQLException {
/* 215 */     synchronized (getConnectionMutex()) {
/* 216 */       if (this.infoProvider == null) {
/*     */         try {
/*     */           try {
/* 219 */             this.infoProvider = (JDBC4ClientInfoProvider)Util.getInstance(getClientInfoProvider(), new Class[0], new Object[0], 
/* 220 */                 getExceptionInterceptor());
/* 221 */           } catch (SQLException sqlEx) {
/* 222 */             if (sqlEx.getCause() instanceof ClassCastException)
/*     */             {
/* 224 */               this.infoProvider = (JDBC4ClientInfoProvider)Util.getInstance("com.mysql.jdbc." + getClientInfoProvider(), new Class[0], new Object[0], 
/* 225 */                   getExceptionInterceptor());
/*     */             }
/*     */           } 
/* 228 */         } catch (ClassCastException cce) {
/* 229 */           throw SQLError.createSQLException(Messages.getString("JDBC4Connection.ClientInfoNotImplemented", new Object[] { getClientInfoProvider() }), "S1009", 
/* 230 */               getExceptionInterceptor());
/*     */         } 
/*     */         
/* 233 */         this.infoProvider.initialize(this, this.props);
/*     */       } 
/*     */       
/* 236 */       return this.infoProvider;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\JDBC4Connection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */