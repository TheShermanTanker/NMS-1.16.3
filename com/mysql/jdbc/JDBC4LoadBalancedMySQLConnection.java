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
/*     */ public class JDBC4LoadBalancedMySQLConnection
/*     */   extends LoadBalancedMySQLConnection
/*     */   implements JDBC4MySQLConnection
/*     */ {
/*     */   public JDBC4LoadBalancedMySQLConnection(LoadBalancedConnectionProxy proxy) throws SQLException {
/*  45 */     super(proxy);
/*     */   }
/*     */   
/*     */   private JDBC4MySQLConnection getJDBC4Connection() {
/*  49 */     return (JDBC4MySQLConnection)getActiveMySQLConnection();
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
/*  73 */     return getJDBC4Connection().isValid(timeout);
/*     */   }
/*     */   
/*     */   public void setClientInfo(Properties properties) throws SQLClientInfoException {
/*  77 */     getJDBC4Connection().setClientInfo(properties);
/*     */   }
/*     */   
/*     */   public void setClientInfo(String name, String value) throws SQLClientInfoException {
/*  81 */     getJDBC4Connection().setClientInfo(name, value);
/*     */   }
/*     */   
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/*  85 */     checkClosed();
/*     */ 
/*     */     
/*  88 */     return iface.isInstance(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T unwrap(Class<T> iface) throws SQLException {
/*     */     try {
/*  94 */       return iface.cast(this);
/*  95 */     } catch (ClassCastException cce) {
/*  96 */       throw SQLError.createSQLException("Unable to unwrap to " + iface.toString(), "S1009", getExceptionInterceptor());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Blob createBlob() {
/* 104 */     return getJDBC4Connection().createBlob();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Clob createClob() {
/* 111 */     return getJDBC4Connection().createClob();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NClob createNClob() {
/* 118 */     return getJDBC4Connection().createNClob();
/*     */   }
/*     */   
/*     */   public JDBC4ClientInfoProvider getClientInfoProviderImpl() throws SQLException {
/* 122 */     synchronized (getThisAsProxy()) {
/* 123 */       return getJDBC4Connection().getClientInfoProviderImpl();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\JDBC4LoadBalancedMySQLConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */