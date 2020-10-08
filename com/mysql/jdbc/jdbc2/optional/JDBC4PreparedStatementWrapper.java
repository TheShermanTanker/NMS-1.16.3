/*     */ package com.mysql.jdbc.jdbc2.optional;
/*     */ 
/*     */ import com.mysql.jdbc.SQLError;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.sql.NClob;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.RowId;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLXML;
/*     */ import java.util.HashMap;
/*     */ import javax.sql.StatementEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDBC4PreparedStatementWrapper
/*     */   extends PreparedStatementWrapper
/*     */ {
/*     */   public JDBC4PreparedStatementWrapper(ConnectionWrapper c, MysqlPooledConnection conn, PreparedStatement toWrap) {
/*  58 */     super(c, conn, toWrap);
/*     */   }
/*     */   
/*     */   public synchronized void close() throws SQLException {
/*  62 */     if (this.pooledConnection == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  67 */     MysqlPooledConnection con = this.pooledConnection;
/*     */     
/*     */     try {
/*  70 */       super.close();
/*     */     } finally {
/*     */       try {
/*  73 */         StatementEvent e = new StatementEvent(con, this);
/*     */         
/*  75 */         if (con instanceof JDBC4MysqlPooledConnection) {
/*  76 */           ((JDBC4MysqlPooledConnection)con).fireStatementEvent(e);
/*  77 */         } else if (con instanceof JDBC4MysqlXAConnection) {
/*  78 */           ((JDBC4MysqlXAConnection)con).fireStatementEvent(e);
/*  79 */         } else if (con instanceof JDBC4SuspendableXAConnection) {
/*  80 */           ((JDBC4SuspendableXAConnection)con).fireStatementEvent(e);
/*     */         } 
/*     */       } finally {
/*  83 */         this.unwrappedInterfaces = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isClosed() throws SQLException {
/*     */     try {
/*  90 */       if (this.wrappedStmt != null) {
/*  91 */         return this.wrappedStmt.isClosed();
/*     */       }
/*  93 */       throw SQLError.createSQLException("Statement already closed", "S1009", this.exceptionInterceptor);
/*     */     }
/*  95 */     catch (SQLException sqlEx) {
/*  96 */       checkAndFireConnectionError(sqlEx);
/*     */ 
/*     */       
/*  99 */       return false;
/*     */     } 
/*     */   }
/*     */   public void setPoolable(boolean poolable) throws SQLException {
/*     */     try {
/* 104 */       if (this.wrappedStmt != null) {
/* 105 */         this.wrappedStmt.setPoolable(poolable);
/*     */       } else {
/* 107 */         throw SQLError.createSQLException("Statement already closed", "S1009", this.exceptionInterceptor);
/*     */       } 
/* 109 */     } catch (SQLException sqlEx) {
/* 110 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isPoolable() throws SQLException {
/*     */     try {
/* 116 */       if (this.wrappedStmt != null) {
/* 117 */         return this.wrappedStmt.isPoolable();
/*     */       }
/* 119 */       throw SQLError.createSQLException("Statement already closed", "S1009", this.exceptionInterceptor);
/*     */     }
/* 121 */     catch (SQLException sqlEx) {
/* 122 */       checkAndFireConnectionError(sqlEx);
/*     */ 
/*     */       
/* 125 */       return false;
/*     */     } 
/*     */   }
/*     */   public void setRowId(int parameterIndex, RowId x) throws SQLException {
/*     */     try {
/* 130 */       if (this.wrappedStmt != null) {
/* 131 */         ((PreparedStatement)this.wrappedStmt).setRowId(parameterIndex, x);
/*     */       } else {
/* 133 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 135 */     } catch (SQLException sqlEx) {
/* 136 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setNClob(int parameterIndex, NClob value) throws SQLException {
/*     */     try {
/* 142 */       if (this.wrappedStmt != null) {
/* 143 */         ((PreparedStatement)this.wrappedStmt).setNClob(parameterIndex, value);
/*     */       } else {
/* 145 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 147 */     } catch (SQLException sqlEx) {
/* 148 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
/*     */     try {
/* 154 */       if (this.wrappedStmt != null) {
/* 155 */         ((PreparedStatement)this.wrappedStmt).setSQLXML(parameterIndex, xmlObject);
/*     */       } else {
/* 157 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 159 */     } catch (SQLException sqlEx) {
/* 160 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setNString(int parameterIndex, String value) throws SQLException {
/*     */     try {
/* 166 */       if (this.wrappedStmt != null) {
/* 167 */         ((PreparedStatement)this.wrappedStmt).setNString(parameterIndex, value);
/*     */       } else {
/* 169 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 171 */     } catch (SQLException sqlEx) {
/* 172 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
/*     */     try {
/* 178 */       if (this.wrappedStmt != null) {
/* 179 */         ((PreparedStatement)this.wrappedStmt).setNCharacterStream(parameterIndex, value, length);
/*     */       } else {
/* 181 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 183 */     } catch (SQLException sqlEx) {
/* 184 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
/*     */     try {
/* 190 */       if (this.wrappedStmt != null) {
/* 191 */         ((PreparedStatement)this.wrappedStmt).setClob(parameterIndex, reader, length);
/*     */       } else {
/* 193 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 195 */     } catch (SQLException sqlEx) {
/* 196 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
/*     */     try {
/* 202 */       if (this.wrappedStmt != null) {
/* 203 */         ((PreparedStatement)this.wrappedStmt).setBlob(parameterIndex, inputStream, length);
/*     */       } else {
/* 205 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 207 */     } catch (SQLException sqlEx) {
/* 208 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
/*     */     try {
/* 214 */       if (this.wrappedStmt != null) {
/* 215 */         ((PreparedStatement)this.wrappedStmt).setNClob(parameterIndex, reader, length);
/*     */       } else {
/* 217 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 219 */     } catch (SQLException sqlEx) {
/* 220 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
/*     */     try {
/* 226 */       if (this.wrappedStmt != null) {
/* 227 */         ((PreparedStatement)this.wrappedStmt).setAsciiStream(parameterIndex, x, length);
/*     */       } else {
/* 229 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 231 */     } catch (SQLException sqlEx) {
/* 232 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
/*     */     try {
/* 238 */       if (this.wrappedStmt != null) {
/* 239 */         ((PreparedStatement)this.wrappedStmt).setBinaryStream(parameterIndex, x, length);
/*     */       } else {
/* 241 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 243 */     } catch (SQLException sqlEx) {
/* 244 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
/*     */     try {
/* 250 */       if (this.wrappedStmt != null) {
/* 251 */         ((PreparedStatement)this.wrappedStmt).setCharacterStream(parameterIndex, reader, length);
/*     */       } else {
/* 253 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 255 */     } catch (SQLException sqlEx) {
/* 256 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
/*     */     try {
/* 262 */       if (this.wrappedStmt != null) {
/* 263 */         ((PreparedStatement)this.wrappedStmt).setAsciiStream(parameterIndex, x);
/*     */       } else {
/* 265 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 267 */     } catch (SQLException sqlEx) {
/* 268 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
/*     */     try {
/* 274 */       if (this.wrappedStmt != null) {
/* 275 */         ((PreparedStatement)this.wrappedStmt).setBinaryStream(parameterIndex, x);
/*     */       } else {
/* 277 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 279 */     } catch (SQLException sqlEx) {
/* 280 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
/*     */     try {
/* 286 */       if (this.wrappedStmt != null) {
/* 287 */         ((PreparedStatement)this.wrappedStmt).setCharacterStream(parameterIndex, reader);
/*     */       } else {
/* 289 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 291 */     } catch (SQLException sqlEx) {
/* 292 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
/*     */     try {
/* 299 */       if (this.wrappedStmt != null) {
/* 300 */         ((PreparedStatement)this.wrappedStmt).setNCharacterStream(parameterIndex, value);
/*     */       } else {
/* 302 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 304 */     } catch (SQLException sqlEx) {
/* 305 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClob(int parameterIndex, Reader reader) throws SQLException {
/*     */     try {
/* 312 */       if (this.wrappedStmt != null) {
/* 313 */         ((PreparedStatement)this.wrappedStmt).setClob(parameterIndex, reader);
/*     */       } else {
/* 315 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 317 */     } catch (SQLException sqlEx) {
/* 318 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
/*     */     try {
/* 325 */       if (this.wrappedStmt != null) {
/* 326 */         ((PreparedStatement)this.wrappedStmt).setBlob(parameterIndex, inputStream);
/*     */       } else {
/* 328 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 330 */     } catch (SQLException sqlEx) {
/* 331 */       checkAndFireConnectionError(sqlEx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setNClob(int parameterIndex, Reader reader) throws SQLException {
/*     */     try {
/* 337 */       if (this.wrappedStmt != null) {
/* 338 */         ((PreparedStatement)this.wrappedStmt).setNClob(parameterIndex, reader);
/*     */       } else {
/* 340 */         throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
/*     */       } 
/* 342 */     } catch (SQLException sqlEx) {
/* 343 */       checkAndFireConnectionError(sqlEx);
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
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 369 */     boolean isInstance = iface.isInstance(this);
/*     */     
/* 371 */     if (isInstance) {
/* 372 */       return true;
/*     */     }
/*     */     
/* 375 */     String interfaceClassName = iface.getName();
/*     */     
/* 377 */     return (interfaceClassName.equals("com.mysql.jdbc.Statement") || interfaceClassName.equals("java.sql.Statement") || interfaceClassName
/* 378 */       .equals("java.sql.PreparedStatement") || interfaceClassName.equals("java.sql.Wrapper"));
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
/* 402 */       if ("java.sql.Statement".equals(iface.getName()) || "java.sql.PreparedStatement".equals(iface.getName()) || "java.sql.Wrapper.class"
/* 403 */         .equals(iface.getName())) {
/* 404 */         return iface.cast(this);
/*     */       }
/*     */       
/* 407 */       if (this.unwrappedInterfaces == null) {
/* 408 */         this.unwrappedInterfaces = new HashMap<Class<?>, Object>();
/*     */       }
/*     */       
/* 411 */       Object cachedUnwrapped = this.unwrappedInterfaces.get(iface);
/*     */       
/* 413 */       if (cachedUnwrapped == null) {
/* 414 */         if (cachedUnwrapped == null) {
/* 415 */           cachedUnwrapped = Proxy.newProxyInstance(this.wrappedStmt.getClass().getClassLoader(), new Class[] { iface }, new WrapperBase.ConnectionErrorFiringInvocationHandler(this, this.wrappedStmt));
/*     */           
/* 417 */           this.unwrappedInterfaces.put(iface, cachedUnwrapped);
/*     */         } 
/* 419 */         this.unwrappedInterfaces.put(iface, cachedUnwrapped);
/*     */       } 
/*     */       
/* 422 */       return iface.cast(cachedUnwrapped);
/* 423 */     } catch (ClassCastException cce) {
/* 424 */       throw SQLError.createSQLException("Unable to unwrap to " + iface.toString(), "S1009", this.exceptionInterceptor);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\JDBC4PreparedStatementWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */