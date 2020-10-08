/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLType;
/*     */ import java.time.LocalDate;
/*     */ import java.time.LocalDateTime;
/*     */ import java.time.LocalTime;
/*     */ import java.time.OffsetDateTime;
/*     */ import java.time.OffsetTime;
/*     */ import java.time.format.DateTimeParseException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDBC42UpdatableResultSet
/*     */   extends JDBC4UpdatableResultSet
/*     */ {
/*     */   public JDBC42UpdatableResultSet(String catalog, Field[] fields, RowData tuples, MySQLConnection conn, StatementImpl creatorStmt) throws SQLException {
/*  44 */     super(catalog, fields, tuples, conn, creatorStmt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int translateAndCheckSqlType(SQLType sqlType) throws SQLException {
/*  52 */     return JDBC42Helper.translateAndCheckSqlType(sqlType, getExceptionInterceptor());
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
/*     */   public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
/*  64 */     synchronized (checkClosed().getConnectionMutex()) {
/*  65 */       if (type == null) {
/*  66 */         throw SQLError.createSQLException("Type parameter can not be null", "S1009", getExceptionInterceptor());
/*     */       }
/*     */       
/*  69 */       if (type.equals(LocalDate.class))
/*  70 */         return type.cast(getDate(columnIndex).toLocalDate()); 
/*  71 */       if (type.equals(LocalDateTime.class))
/*  72 */         return type.cast(getTimestamp(columnIndex).toLocalDateTime()); 
/*  73 */       if (type.equals(LocalTime.class))
/*  74 */         return type.cast(getTime(columnIndex).toLocalTime()); 
/*  75 */       if (type.equals(OffsetDateTime.class)) {
/*     */         try {
/*  77 */           return type.cast(OffsetDateTime.parse(getString(columnIndex)));
/*  78 */         } catch (DateTimeParseException dateTimeParseException) {}
/*     */       
/*     */       }
/*  81 */       else if (type.equals(OffsetTime.class)) {
/*     */         try {
/*  83 */           return type.cast(OffsetTime.parse(getString(columnIndex)));
/*  84 */         } catch (DateTimeParseException dateTimeParseException) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  89 */       return super.getObject(columnIndex, type);
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
/*     */   public void updateObject(int columnIndex, Object x) throws SQLException {
/* 102 */     super.updateObject(columnIndex, JDBC42Helper.convertJavaTimeToJavaSql(x));
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
/*     */   public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
/* 115 */     super.updateObject(columnIndex, JDBC42Helper.convertJavaTimeToJavaSql(x), scaleOrLength);
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
/*     */   public void updateObject(String columnLabel, Object x) throws SQLException {
/* 127 */     super.updateObject(columnLabel, JDBC42Helper.convertJavaTimeToJavaSql(x));
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
/*     */   public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
/* 140 */     super.updateObject(columnLabel, JDBC42Helper.convertJavaTimeToJavaSql(x), scaleOrLength);
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
/*     */   public void updateObject(int columnIndex, Object x, SQLType targetSqlType) throws SQLException {
/* 153 */     updateObjectInternal(columnIndex, JDBC42Helper.convertJavaTimeToJavaSql(x), Integer.valueOf(translateAndCheckSqlType(targetSqlType)), 0);
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
/*     */   public void updateObject(int columnIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
/* 167 */     updateObjectInternal(columnIndex, JDBC42Helper.convertJavaTimeToJavaSql(x), Integer.valueOf(translateAndCheckSqlType(targetSqlType)), scaleOrLength);
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
/*     */   public void updateObject(String columnLabel, Object x, SQLType targetSqlType) throws SQLException {
/* 180 */     updateObjectInternal(findColumn(columnLabel), JDBC42Helper.convertJavaTimeToJavaSql(x), Integer.valueOf(translateAndCheckSqlType(targetSqlType)), 0);
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
/*     */   public void updateObject(String columnLabel, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
/* 194 */     updateObjectInternal(findColumn(columnLabel), JDBC42Helper.convertJavaTimeToJavaSql(x), Integer.valueOf(translateAndCheckSqlType(targetSqlType)), scaleOrLength);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\JDBC42UpdatableResultSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */