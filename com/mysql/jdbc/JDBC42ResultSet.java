/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.sql.Date;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLType;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
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
/*     */ public class JDBC42ResultSet
/*     */   extends JDBC4ResultSet
/*     */ {
/*     */   public JDBC42ResultSet(long updateCount, long updateID, MySQLConnection conn, StatementImpl creatorStmt) {
/*  46 */     super(updateCount, updateID, conn, creatorStmt);
/*     */   }
/*     */   
/*     */   public JDBC42ResultSet(String catalog, Field[] fields, RowData tuples, MySQLConnection conn, StatementImpl creatorStmt) throws SQLException {
/*  50 */     super(catalog, fields, tuples, conn, creatorStmt);
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
/*  62 */     if (type == null) {
/*  63 */       throw SQLError.createSQLException("Type parameter can not be null", "S1009", getExceptionInterceptor());
/*     */     }
/*     */     
/*  66 */     if (type.equals(LocalDate.class)) {
/*  67 */       Date date = getDate(columnIndex);
/*  68 */       return (date == null) ? null : type.cast(date.toLocalDate());
/*  69 */     }  if (type.equals(LocalDateTime.class)) {
/*  70 */       Timestamp timestamp = getTimestamp(columnIndex);
/*  71 */       return (timestamp == null) ? null : type.cast(timestamp.toLocalDateTime());
/*  72 */     }  if (type.equals(LocalTime.class)) {
/*  73 */       Time time = getTime(columnIndex);
/*  74 */       return (time == null) ? null : type.cast(time.toLocalTime());
/*  75 */     }  if (type.equals(OffsetDateTime.class)) {
/*     */       try {
/*  77 */         String string = getString(columnIndex);
/*  78 */         return (string == null) ? null : type.cast(OffsetDateTime.parse(string));
/*  79 */       } catch (DateTimeParseException dateTimeParseException) {}
/*     */     
/*     */     }
/*  82 */     else if (type.equals(OffsetTime.class)) {
/*     */       try {
/*  84 */         String string = getString(columnIndex);
/*  85 */         return (string == null) ? null : type.cast(OffsetTime.parse(string));
/*  86 */       } catch (DateTimeParseException dateTimeParseException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  91 */     return super.getObject(columnIndex, type);
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
/*     */   public void updateObject(int columnIndex, Object x, SQLType targetSqlType) throws SQLException {
/* 103 */     throw new NotUpdatable();
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
/*     */   public void updateObject(int columnIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
/* 116 */     throw new NotUpdatable();
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
/*     */   public void updateObject(String columnLabel, Object x, SQLType targetSqlType) throws SQLException {
/* 128 */     throw new NotUpdatable();
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
/*     */   public void updateObject(String columnLabel, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
/* 141 */     throw new NotUpdatable();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\JDBC42ResultSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */