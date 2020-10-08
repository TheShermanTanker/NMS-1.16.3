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
/*     */ 
/*     */ public class JDBC4MultiHostMySQLConnection
/*     */   extends MultiHostMySQLConnection
/*     */   implements JDBC4MySQLConnection
/*     */ {
/*     */   public JDBC4MultiHostMySQLConnection(MultiHostConnectionProxy proxy) throws SQLException {
/*  45 */     super(proxy);
/*     */   }
/*     */   
/*     */   private JDBC4Connection getJDBC4Connection() {
/*  49 */     return (JDBC4Connection)(getThisAsProxy()).currentConnection;
/*     */   }
/*     */   
/*     */   public SQLXML createSQLXML() throws SQLException {
/*  53 */     return getJDBC4Connection().createSQLXML();
/*     */   }
/*     */   
/*     */   public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
/*  57 */     return getJDBC4Connection().createArrayOf(typeName, elements);
/*     */   }
/*     */   
/*     */   public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
/*  61 */     return getJDBC4Connection().createStruct(typeName, attributes);
/*     */   }
/*     */   
/*     */   public Properties getClientInfo() throws SQLException {
/*  65 */     return getJDBC4Connection().getClientInfo();
/*     */   }
/*     */   
/*     */   public String getClientInfo(String name) throws SQLException {
/*  69 */     return getJDBC4Connection().getClientInfo(name);
/*     */   }
/*     */   
/*     */   public boolean isValid(int timeout) throws SQLException {
/*  73 */     synchronized (getThisAsProxy()) {
/*  74 */       return getJDBC4Connection().isValid(timeout);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setClientInfo(Properties properties) throws SQLClientInfoException {
/*  79 */     getJDBC4Connection().setClientInfo(properties);
/*     */   }
/*     */   
/*     */   public void setClientInfo(String name, String value) throws SQLClientInfoException {
/*  83 */     getJDBC4Connection().setClientInfo(name, value);
/*     */   }
/*     */   
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/*  87 */     checkClosed();
/*     */ 
/*     */     
/*  90 */     return iface.isInstance(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T unwrap(Class<T> iface) throws SQLException {
/*     */     try {
/*  96 */       return iface.cast(this);
/*  97 */     } catch (ClassCastException cce) {
/*  98 */       throw SQLError.createSQLException("Unable to unwrap to " + iface.toString(), "S1009", getExceptionInterceptor());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Blob createBlob() {
/* 106 */     return getJDBC4Connection().createBlob();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Clob createClob() {
/* 113 */     return getJDBC4Connection().createClob();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NClob createNClob() {
/* 120 */     return getJDBC4Connection().createNClob();
/*     */   }
/*     */   
/*     */   public JDBC4ClientInfoProvider getClientInfoProviderImpl() throws SQLException {
/* 124 */     synchronized (getThisAsProxy()) {
/* 125 */       return getJDBC4Connection().getClientInfoProviderImpl();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\JDBC4MultiHostMySQLConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */