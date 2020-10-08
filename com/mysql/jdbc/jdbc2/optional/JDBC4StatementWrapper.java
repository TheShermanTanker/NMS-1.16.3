/*     */ package com.mysql.jdbc.jdbc2.optional;
/*     */ 
/*     */ import com.mysql.jdbc.SQLError;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDBC4StatementWrapper
/*     */   extends StatementWrapper
/*     */ {
/*     */   public JDBC4StatementWrapper(ConnectionWrapper c, MysqlPooledConnection conn, Statement toWrap) {
/*  55 */     super(c, conn, toWrap);
/*     */   }
/*     */   
/*     */   public void close() throws SQLException {
/*     */     try {
/*  60 */       super.close();
/*     */     } finally {
/*  62 */       this.unwrappedInterfaces = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isClosed() throws SQLException {
/*     */     try {
/*  68 */       if (this.wrappedStmt != null) {
/*  69 */         return this.wrappedStmt.isClosed();
/*     */       }
/*  71 */       throw SQLError.createSQLException("Statement already closed", "S1009", this.exceptionInterceptor);
/*     */     }
/*  73 */     catch (SQLException sqlEx) {
/*  74 */       checkAndFireConnectionError(sqlEx);
/*     */ 
/*     */       
/*  77 */       return false;
/*     */     } 
/*     */   }
/*     */   public void setPoolable(boolean poolable) throws SQLException {
/*     */     try {
/*  82 */       if (this.wrappedStmt != null) {
/*  83 */         this.wrappedStmt.setPoolable(poolable);
/*     */       } else {
/*  85 */         throw SQLError.createSQLException("Statement already closed", "S1009", this.exceptionInterceptor);
/*     */       } 
/*  87 */     } catch (SQLException sqlEx) {
/*  88 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isPoolable() throws SQLException {
/*     */     try {
/*  94 */       if (this.wrappedStmt != null) {
/*  95 */         return this.wrappedStmt.isPoolable();
/*     */       }
/*  97 */       throw SQLError.createSQLException("Statement already closed", "S1009", this.exceptionInterceptor);
/*     */     }
/*  99 */     catch (SQLException sqlEx) {
/* 100 */       checkAndFireConnectionError(sqlEx);
/*     */ 
/*     */       
/* 103 */       return false;
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
/* 128 */     boolean isInstance = iface.isInstance(this);
/*     */     
/* 130 */     if (isInstance) {
/* 131 */       return true;
/*     */     }
/*     */     
/* 134 */     String interfaceClassName = iface.getName();
/*     */     
/* 136 */     return (interfaceClassName.equals("com.mysql.jdbc.Statement") || interfaceClassName.equals("java.sql.Statement") || interfaceClassName
/* 137 */       .equals("java.sql.Wrapper"));
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
/* 161 */       if ("java.sql.Statement".equals(iface.getName()) || "java.sql.Wrapper.class".equals(iface.getName())) {
/* 162 */         return iface.cast(this);
/*     */       }
/*     */       
/* 165 */       if (this.unwrappedInterfaces == null) {
/* 166 */         this.unwrappedInterfaces = new HashMap<Class<?>, Object>();
/*     */       }
/*     */       
/* 169 */       Object cachedUnwrapped = this.unwrappedInterfaces.get(iface);
/*     */       
/* 171 */       if (cachedUnwrapped == null) {
/* 172 */         cachedUnwrapped = Proxy.newProxyInstance(this.wrappedStmt.getClass().getClassLoader(), new Class[] { iface }, new WrapperBase.ConnectionErrorFiringInvocationHandler(this, this.wrappedStmt));
/*     */         
/* 174 */         this.unwrappedInterfaces.put(iface, cachedUnwrapped);
/*     */       } 
/*     */       
/* 177 */       return iface.cast(cachedUnwrapped);
/* 178 */     } catch (ClassCastException cce) {
/* 179 */       throw SQLError.createSQLException("Unable to unwrap to " + iface.toString(), "S1009", this.exceptionInterceptor);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\JDBC4StatementWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */