/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDBC42CallableStatement
/*     */   extends JDBC4CallableStatement
/*     */ {
/*     */   public JDBC42CallableStatement(MySQLConnection conn, CallableStatement.CallableStatementParamInfo paramInfo) throws SQLException {
/*  31 */     super(conn, paramInfo);
/*     */   }
/*     */   
/*     */   public JDBC42CallableStatement(MySQLConnection conn, String sql, String catalog, boolean isFunctionCall) throws SQLException {
/*  35 */     super(conn, sql, catalog, isFunctionCall);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int checkSqlType(int sqlType) throws SQLException {
/*  43 */     return JDBC42Helper.checkSqlType(sqlType, getExceptionInterceptor());
/*     */   }
/*     */   
/*     */   private int translateAndCheckSqlType(SQLType sqlType) throws SQLException {
/*  47 */     return JDBC42Helper.translateAndCheckSqlType(sqlType, getExceptionInterceptor());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerOutParameter(int parameterIndex, SQLType sqlType) throws SQLException {
/*  58 */     registerOutParameter(parameterIndex, translateAndCheckSqlType(sqlType));
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
/*     */   public void registerOutParameter(int parameterIndex, SQLType sqlType, int scale) throws SQLException {
/*  70 */     registerOutParameter(parameterIndex, translateAndCheckSqlType(sqlType), scale);
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
/*     */   public void registerOutParameter(int parameterIndex, SQLType sqlType, String typeName) throws SQLException {
/*  82 */     registerOutParameter(parameterIndex, translateAndCheckSqlType(sqlType), typeName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerOutParameter(String parameterName, SQLType sqlType) throws SQLException {
/*  93 */     registerOutParameter(parameterName, translateAndCheckSqlType(sqlType));
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
/*     */   public void registerOutParameter(String parameterName, SQLType sqlType, int scale) throws SQLException {
/* 105 */     registerOutParameter(parameterName, translateAndCheckSqlType(sqlType), scale);
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
/*     */   public void registerOutParameter(String parameterName, SQLType sqlType, String typeName) throws SQLException {
/* 117 */     registerOutParameter(parameterName, translateAndCheckSqlType(sqlType), typeName);
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
/*     */   public void setObject(int parameterIndex, Object x) throws SQLException {
/* 129 */     synchronized (checkClosed().getConnectionMutex()) {
/* 130 */       super.setObject(parameterIndex, JDBC42Helper.convertJavaTimeToJavaSql(x));
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
/*     */   public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
/* 144 */     synchronized (checkClosed().getConnectionMutex()) {
/* 145 */       super.setObject(parameterIndex, JDBC42Helper.convertJavaTimeToJavaSql(x), checkSqlType(targetSqlType));
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
/*     */   public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
/* 160 */     synchronized (checkClosed().getConnectionMutex()) {
/* 161 */       super.setObject(parameterIndex, JDBC42Helper.convertJavaTimeToJavaSql(x), checkSqlType(targetSqlType), scaleOrLength);
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
/*     */   public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
/* 175 */     synchronized (checkClosed().getConnectionMutex()) {
/* 176 */       super.setObject(parameterIndex, JDBC42Helper.convertJavaTimeToJavaSql(x), translateAndCheckSqlType(targetSqlType));
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
/*     */   public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
/* 191 */     synchronized (checkClosed().getConnectionMutex()) {
/* 192 */       super.setObject(parameterIndex, JDBC42Helper.convertJavaTimeToJavaSql(x), translateAndCheckSqlType(targetSqlType), scaleOrLength);
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
/*     */   public void setObject(String parameterName, Object x, SQLType targetSqlType) throws SQLException {
/* 206 */     synchronized (checkClosed().getConnectionMutex()) {
/* 207 */       setObject(parameterName, JDBC42Helper.convertJavaTimeToJavaSql(x), translateAndCheckSqlType(targetSqlType));
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
/*     */   public void setObject(String parameterName, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
/* 222 */     synchronized (checkClosed().getConnectionMutex()) {
/* 223 */       setObject(parameterName, JDBC42Helper.convertJavaTimeToJavaSql(x), translateAndCheckSqlType(targetSqlType), scaleOrLength);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\JDBC42CallableStatement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */