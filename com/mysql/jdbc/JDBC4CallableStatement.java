/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.io.Reader;
/*     */ import java.sql.DatabaseMetaData;
/*     */ import java.sql.NClob;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.RowId;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLXML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDBC4CallableStatement
/*     */   extends CallableStatement
/*     */ {
/*     */   public JDBC4CallableStatement(MySQLConnection conn, CallableStatement.CallableStatementParamInfo paramInfo) throws SQLException {
/*  40 */     super(conn, paramInfo);
/*     */   }
/*     */   
/*     */   public JDBC4CallableStatement(MySQLConnection conn, String sql, String catalog, boolean isFunctionCall) throws SQLException {
/*  44 */     super(conn, sql, catalog, isFunctionCall);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResultSet getParamTypes(String catalog, String routineName) throws SQLException {
/*  49 */     DatabaseMetaData dbmd = this.connection.getMetaData();
/*  50 */     if (this.callingStoredFunction) {
/*  51 */       return dbmd.getFunctionColumns(catalog, null, routineName, "%");
/*     */     }
/*  53 */     boolean getProcRetFuncsCurrentValue = this.connection.getGetProceduresReturnsFunctions();
/*     */     try {
/*  55 */       this.connection.setGetProceduresReturnsFunctions(false);
/*  56 */       return dbmd.getProcedureColumns(catalog, null, routineName, "%");
/*     */     } finally {
/*  58 */       this.connection.setGetProceduresReturnsFunctions(getProcRetFuncsCurrentValue);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setRowId(int parameterIndex, RowId x) throws SQLException {
/*  63 */     JDBC4PreparedStatementHelper.setRowId(this, parameterIndex, x);
/*     */   }
/*     */   
/*     */   public void setRowId(String parameterName, RowId x) throws SQLException {
/*  67 */     JDBC4PreparedStatementHelper.setRowId(this, getNamedParamIndex(parameterName, false), x);
/*     */   }
/*     */   
/*     */   public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
/*  71 */     JDBC4PreparedStatementHelper.setSQLXML(this, parameterIndex, xmlObject);
/*     */   }
/*     */   
/*     */   public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
/*  75 */     JDBC4PreparedStatementHelper.setSQLXML(this, getNamedParamIndex(parameterName, false), xmlObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public SQLXML getSQLXML(int parameterIndex) throws SQLException {
/*  80 */     ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*     */     
/*  82 */     SQLXML retValue = ((JDBC4ResultSet)rs).getSQLXML(mapOutputParameterIndexToRsIndex(parameterIndex));
/*     */     
/*  84 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/*  86 */     return retValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public SQLXML getSQLXML(String parameterName) throws SQLException {
/*  91 */     ResultSetInternalMethods rs = getOutputParameters(0);
/*     */     
/*  93 */     SQLXML retValue = ((JDBC4ResultSet)rs).getSQLXML(fixParameterName(parameterName));
/*     */     
/*  95 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/*  97 */     return retValue;
/*     */   }
/*     */   
/*     */   public RowId getRowId(int parameterIndex) throws SQLException {
/* 101 */     ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*     */     
/* 103 */     RowId retValue = ((JDBC4ResultSet)rs).getRowId(mapOutputParameterIndexToRsIndex(parameterIndex));
/*     */     
/* 105 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/* 107 */     return retValue;
/*     */   }
/*     */   
/*     */   public RowId getRowId(String parameterName) throws SQLException {
/* 111 */     ResultSetInternalMethods rs = getOutputParameters(0);
/*     */     
/* 113 */     RowId retValue = ((JDBC4ResultSet)rs).getRowId(fixParameterName(parameterName));
/*     */     
/* 115 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/* 117 */     return retValue;
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
/*     */   public void setNClob(int parameterIndex, NClob value) throws SQLException {
/* 132 */     JDBC4PreparedStatementHelper.setNClob(this, parameterIndex, value);
/*     */   }
/*     */   
/*     */   public void setNClob(String parameterName, NClob value) throws SQLException {
/* 136 */     JDBC4PreparedStatementHelper.setNClob(this, getNamedParamIndex(parameterName, false), value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNClob(String parameterName, Reader reader) throws SQLException {
/* 141 */     setNClob(getNamedParamIndex(parameterName, false), reader);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
/* 146 */     setNClob(getNamedParamIndex(parameterName, false), reader, length);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNString(String parameterName, String value) throws SQLException {
/* 151 */     setNString(getNamedParamIndex(parameterName, false), value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reader getCharacterStream(int parameterIndex) throws SQLException {
/* 158 */     ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*     */     
/* 160 */     Reader retValue = rs.getCharacterStream(mapOutputParameterIndexToRsIndex(parameterIndex));
/*     */     
/* 162 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/* 164 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reader getCharacterStream(String parameterName) throws SQLException {
/* 171 */     ResultSetInternalMethods rs = getOutputParameters(0);
/*     */     
/* 173 */     Reader retValue = rs.getCharacterStream(fixParameterName(parameterName));
/*     */     
/* 175 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/* 177 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reader getNCharacterStream(int parameterIndex) throws SQLException {
/* 184 */     ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*     */     
/* 186 */     Reader retValue = ((JDBC4ResultSet)rs).getNCharacterStream(mapOutputParameterIndexToRsIndex(parameterIndex));
/*     */     
/* 188 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/* 190 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reader getNCharacterStream(String parameterName) throws SQLException {
/* 197 */     ResultSetInternalMethods rs = getOutputParameters(0);
/*     */     
/* 199 */     Reader retValue = ((JDBC4ResultSet)rs).getNCharacterStream(fixParameterName(parameterName));
/*     */     
/* 201 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/* 203 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NClob getNClob(int parameterIndex) throws SQLException {
/* 210 */     ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*     */     
/* 212 */     NClob retValue = ((JDBC4ResultSet)rs).getNClob(mapOutputParameterIndexToRsIndex(parameterIndex));
/*     */     
/* 214 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/* 216 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NClob getNClob(String parameterName) throws SQLException {
/* 223 */     ResultSetInternalMethods rs = getOutputParameters(0);
/*     */     
/* 225 */     NClob retValue = ((JDBC4ResultSet)rs).getNClob(fixParameterName(parameterName));
/*     */     
/* 227 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/* 229 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNString(int parameterIndex) throws SQLException {
/* 236 */     ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*     */     
/* 238 */     String retValue = ((JDBC4ResultSet)rs).getNString(mapOutputParameterIndexToRsIndex(parameterIndex));
/*     */     
/* 240 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/* 242 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNString(String parameterName) throws SQLException {
/* 249 */     ResultSetInternalMethods rs = getOutputParameters(0);
/*     */     
/* 251 */     String retValue = ((JDBC4ResultSet)rs).getNString(fixParameterName(parameterName));
/*     */     
/* 253 */     this.outputParamWasNull = rs.wasNull();
/*     */     
/* 255 */     return retValue;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\JDBC4CallableStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */