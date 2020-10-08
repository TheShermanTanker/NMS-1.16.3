/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URL;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.Clob;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.Date;
/*      */ import java.sql.ParameterMetaData;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CallableStatement
/*      */   extends PreparedStatement
/*      */   implements CallableStatement
/*      */ {
/*      */   protected static final Constructor<?> JDBC_4_CSTMT_2_ARGS_CTOR;
/*      */   protected static final Constructor<?> JDBC_4_CSTMT_4_ARGS_CTOR;
/*      */   private static final int NOT_OUTPUT_PARAMETER_INDICATOR = -2147483648;
/*      */   private static final String PARAMETER_NAMESPACE_PREFIX = "@com_mysql_jdbc_outparam_";
/*      */   
/*      */   static {
/*   59 */     if (Util.isJdbc4()) {
/*      */       try {
/*   61 */         String jdbc4ClassName = Util.isJdbc42() ? "com.mysql.jdbc.JDBC42CallableStatement" : "com.mysql.jdbc.JDBC4CallableStatement";
/*   62 */         JDBC_4_CSTMT_2_ARGS_CTOR = Class.forName(jdbc4ClassName).getConstructor(new Class[] { MySQLConnection.class, CallableStatementParamInfo.class });
/*      */         
/*   64 */         JDBC_4_CSTMT_4_ARGS_CTOR = Class.forName(jdbc4ClassName).getConstructor(new Class[] { MySQLConnection.class, String.class, String.class, boolean.class });
/*      */       }
/*   66 */       catch (SecurityException e) {
/*   67 */         throw new RuntimeException(e);
/*   68 */       } catch (NoSuchMethodException e) {
/*   69 */         throw new RuntimeException(e);
/*   70 */       } catch (ClassNotFoundException e) {
/*   71 */         throw new RuntimeException(e);
/*      */       } 
/*      */     } else {
/*   74 */       JDBC_4_CSTMT_4_ARGS_CTOR = null;
/*   75 */       JDBC_4_CSTMT_2_ARGS_CTOR = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected static class CallableStatementParam
/*      */   {
/*      */     int desiredJdbcType;
/*      */     
/*      */     int index;
/*      */     
/*      */     int inOutModifier;
/*      */     
/*      */     boolean isIn;
/*      */     
/*      */     boolean isOut;
/*      */     
/*      */     int jdbcType;
/*      */     
/*      */     short nullability;
/*      */     
/*      */     String paramName;
/*      */     
/*      */     int precision;
/*      */     
/*      */     int scale;
/*      */     String typeName;
/*      */     
/*      */     CallableStatementParam(String name, int idx, boolean in, boolean out, int jdbcType, String typeName, int precision, int scale, short nullability, int inOutModifier) {
/*  104 */       this.paramName = name;
/*  105 */       this.isIn = in;
/*  106 */       this.isOut = out;
/*  107 */       this.index = idx;
/*      */       
/*  109 */       this.jdbcType = jdbcType;
/*  110 */       this.typeName = typeName;
/*  111 */       this.precision = precision;
/*  112 */       this.scale = scale;
/*  113 */       this.nullability = nullability;
/*  114 */       this.inOutModifier = inOutModifier;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Object clone() throws CloneNotSupportedException {
/*  124 */       return super.clone();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class CallableStatementParamInfo
/*      */     implements ParameterMetaData
/*      */   {
/*      */     String catalogInUse;
/*      */ 
/*      */     
/*      */     boolean isFunctionCall;
/*      */ 
/*      */     
/*      */     String nativeSql;
/*      */ 
/*      */     
/*      */     int numParameters;
/*      */ 
/*      */     
/*      */     List<CallableStatement.CallableStatementParam> parameterList;
/*      */ 
/*      */     
/*      */     Map<String, CallableStatement.CallableStatementParam> parameterMap;
/*      */ 
/*      */     
/*      */     boolean isReadOnlySafeProcedure = false;
/*      */ 
/*      */     
/*      */     boolean isReadOnlySafeChecked = false;
/*      */ 
/*      */ 
/*      */     
/*      */     CallableStatementParamInfo(CallableStatementParamInfo fullParamInfo) {
/*  160 */       this.nativeSql = CallableStatement.this.originalSql;
/*  161 */       this.catalogInUse = CallableStatement.this.currentCatalog;
/*  162 */       this.isFunctionCall = fullParamInfo.isFunctionCall;
/*      */       
/*  164 */       int[] localParameterMap = CallableStatement.this.placeholderToParameterIndexMap;
/*  165 */       int parameterMapLength = localParameterMap.length;
/*      */       
/*  167 */       this.isReadOnlySafeProcedure = fullParamInfo.isReadOnlySafeProcedure;
/*  168 */       this.isReadOnlySafeChecked = fullParamInfo.isReadOnlySafeChecked;
/*  169 */       this.parameterList = new ArrayList<CallableStatement.CallableStatementParam>(fullParamInfo.numParameters);
/*  170 */       this.parameterMap = new HashMap<String, CallableStatement.CallableStatementParam>(fullParamInfo.numParameters);
/*      */       
/*  172 */       for (int i = 0; i < parameterMapLength; i++) {
/*  173 */         CallableStatement.CallableStatementParam param = fullParamInfo.parameterList.get(localParameterMap[i]);
/*      */         
/*  175 */         this.parameterList.add(param);
/*  176 */         this.parameterMap.put(param.paramName, param);
/*      */       } 
/*      */       
/*  179 */       this.numParameters = this.parameterList.size();
/*      */     }
/*      */     
/*      */     CallableStatementParamInfo(ResultSet paramTypesRs) throws SQLException {
/*  183 */       boolean hadRows = paramTypesRs.last();
/*      */       
/*  185 */       this.nativeSql = CallableStatement.this.originalSql;
/*  186 */       this.catalogInUse = CallableStatement.this.currentCatalog;
/*  187 */       this.isFunctionCall = CallableStatement.this.callingStoredFunction;
/*      */       
/*  189 */       if (hadRows) {
/*  190 */         this.numParameters = paramTypesRs.getRow();
/*      */         
/*  192 */         this.parameterList = new ArrayList<CallableStatement.CallableStatementParam>(this.numParameters);
/*  193 */         this.parameterMap = new HashMap<String, CallableStatement.CallableStatementParam>(this.numParameters);
/*      */         
/*  195 */         paramTypesRs.beforeFirst();
/*      */         
/*  197 */         addParametersFromDBMD(paramTypesRs);
/*      */       } else {
/*  199 */         this.numParameters = 0;
/*      */       } 
/*      */     }
/*      */     
/*      */     private void addParametersFromDBMD(ResultSet paramTypesRs) throws SQLException {
/*  204 */       int i = 0;
/*      */       
/*  206 */       while (paramTypesRs.next()) {
/*  207 */         int inOutModifier; String paramName = paramTypesRs.getString(4);
/*      */         
/*  209 */         switch (paramTypesRs.getInt(5)) {
/*      */           case 1:
/*  211 */             inOutModifier = 1;
/*      */             break;
/*      */           case 2:
/*  214 */             inOutModifier = 2;
/*      */             break;
/*      */           case 4:
/*      */           case 5:
/*  218 */             inOutModifier = 4;
/*      */             break;
/*      */           default:
/*  221 */             inOutModifier = 0;
/*      */             break;
/*      */         } 
/*  224 */         boolean isOutParameter = false;
/*  225 */         boolean isInParameter = false;
/*      */         
/*  227 */         if (i == 0 && this.isFunctionCall) {
/*  228 */           isOutParameter = true;
/*  229 */           isInParameter = false;
/*  230 */         } else if (inOutModifier == 2) {
/*  231 */           isOutParameter = true;
/*  232 */           isInParameter = true;
/*  233 */         } else if (inOutModifier == 1) {
/*  234 */           isOutParameter = false;
/*  235 */           isInParameter = true;
/*  236 */         } else if (inOutModifier == 4) {
/*  237 */           isOutParameter = true;
/*  238 */           isInParameter = false;
/*      */         } 
/*      */         
/*  241 */         int jdbcType = paramTypesRs.getInt(6);
/*  242 */         String typeName = paramTypesRs.getString(7);
/*  243 */         int precision = paramTypesRs.getInt(8);
/*  244 */         int scale = paramTypesRs.getInt(10);
/*  245 */         short nullability = paramTypesRs.getShort(12);
/*      */         
/*  247 */         CallableStatement.CallableStatementParam paramInfoToAdd = new CallableStatement.CallableStatementParam(paramName, i++, isInParameter, isOutParameter, jdbcType, typeName, precision, scale, nullability, inOutModifier);
/*      */ 
/*      */         
/*  250 */         this.parameterList.add(paramInfoToAdd);
/*  251 */         this.parameterMap.put(paramName, paramInfoToAdd);
/*      */       } 
/*      */     }
/*      */     
/*      */     protected void checkBounds(int paramIndex) throws SQLException {
/*  256 */       int localParamIndex = paramIndex - 1;
/*      */       
/*  258 */       if (paramIndex < 0 || localParamIndex >= CallableStatement.this.parameterCount) {
/*  259 */         throw SQLError.createSQLException(Messages.getString("CallableStatement.11") + paramIndex + Messages.getString("CallableStatement.12") + CallableStatement.this.parameterCount + Messages.getString("CallableStatement.13"), "S1009", CallableStatement.this.getExceptionInterceptor());
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Object clone() throws CloneNotSupportedException {
/*  273 */       return super.clone();
/*      */     }
/*      */     
/*      */     CallableStatement.CallableStatementParam getParameter(int index) {
/*  277 */       return this.parameterList.get(index);
/*      */     }
/*      */     
/*      */     CallableStatement.CallableStatementParam getParameter(String name) {
/*  281 */       return this.parameterMap.get(name);
/*      */     }
/*      */     
/*      */     public String getParameterClassName(int arg0) throws SQLException {
/*  285 */       String mysqlTypeName = getParameterTypeName(arg0);
/*      */       
/*  287 */       boolean isBinaryOrBlob = (StringUtils.indexOfIgnoreCase(mysqlTypeName, "BLOB") != -1 || StringUtils.indexOfIgnoreCase(mysqlTypeName, "BINARY") != -1);
/*      */       
/*  289 */       boolean isUnsigned = (StringUtils.indexOfIgnoreCase(mysqlTypeName, "UNSIGNED") != -1);
/*      */       
/*  291 */       int mysqlTypeIfKnown = 0;
/*      */       
/*  293 */       if (StringUtils.startsWithIgnoreCase(mysqlTypeName, "MEDIUMINT")) {
/*  294 */         mysqlTypeIfKnown = 9;
/*      */       }
/*      */       
/*  297 */       return ResultSetMetaData.getClassNameForJavaType(getParameterType(arg0), isUnsigned, mysqlTypeIfKnown, isBinaryOrBlob, false, CallableStatement.this.connection.getYearIsDateType());
/*      */     }
/*      */ 
/*      */     
/*      */     public int getParameterCount() throws SQLException {
/*  302 */       if (this.parameterList == null) {
/*  303 */         return 0;
/*      */       }
/*      */       
/*  306 */       return this.parameterList.size();
/*      */     }
/*      */     
/*      */     public int getParameterMode(int arg0) throws SQLException {
/*  310 */       checkBounds(arg0);
/*      */       
/*  312 */       return (getParameter(arg0 - 1)).inOutModifier;
/*      */     }
/*      */     
/*      */     public int getParameterType(int arg0) throws SQLException {
/*  316 */       checkBounds(arg0);
/*      */       
/*  318 */       return (getParameter(arg0 - 1)).jdbcType;
/*      */     }
/*      */     
/*      */     public String getParameterTypeName(int arg0) throws SQLException {
/*  322 */       checkBounds(arg0);
/*      */       
/*  324 */       return (getParameter(arg0 - 1)).typeName;
/*      */     }
/*      */     
/*      */     public int getPrecision(int arg0) throws SQLException {
/*  328 */       checkBounds(arg0);
/*      */       
/*  330 */       return (getParameter(arg0 - 1)).precision;
/*      */     }
/*      */     
/*      */     public int getScale(int arg0) throws SQLException {
/*  334 */       checkBounds(arg0);
/*      */       
/*  336 */       return (getParameter(arg0 - 1)).scale;
/*      */     }
/*      */     
/*      */     public int isNullable(int arg0) throws SQLException {
/*  340 */       checkBounds(arg0);
/*      */       
/*  342 */       return (getParameter(arg0 - 1)).nullability;
/*      */     }
/*      */     
/*      */     public boolean isSigned(int arg0) throws SQLException {
/*  346 */       checkBounds(arg0);
/*      */       
/*  348 */       return false;
/*      */     }
/*      */     
/*      */     Iterator<CallableStatement.CallableStatementParam> iterator() {
/*  352 */       return this.parameterList.iterator();
/*      */     }
/*      */     
/*      */     int numberOfParameters() {
/*  356 */       return this.numParameters;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isWrapperFor(Class<?> iface) throws SQLException {
/*  363 */       CallableStatement.this.checkClosed();
/*      */ 
/*      */       
/*  366 */       return iface.isInstance(this);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public <T> T unwrap(Class<T> iface) throws SQLException {
/*      */       try {
/*  375 */         return iface.cast(this);
/*  376 */       } catch (ClassCastException cce) {
/*  377 */         throw SQLError.createSQLException("Unable to unwrap to " + iface.toString(), "S1009", CallableStatement.this.getExceptionInterceptor());
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String mangleParameterName(String origParameterName) {
/*  388 */     if (origParameterName == null) {
/*  389 */       return null;
/*      */     }
/*      */     
/*  392 */     int offset = 0;
/*      */     
/*  394 */     if (origParameterName.length() > 0 && origParameterName.charAt(0) == '@') {
/*  395 */       offset = 1;
/*      */     }
/*      */     
/*  398 */     StringBuilder paramNameBuf = new StringBuilder("@com_mysql_jdbc_outparam_".length() + origParameterName.length());
/*  399 */     paramNameBuf.append("@com_mysql_jdbc_outparam_");
/*  400 */     paramNameBuf.append(origParameterName.substring(offset));
/*      */     
/*  402 */     return paramNameBuf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean callingStoredFunction = false;
/*      */ 
/*      */   
/*      */   private ResultSetInternalMethods functionReturnValueResults;
/*      */ 
/*      */   
/*      */   private boolean hasOutputParams = false;
/*      */ 
/*      */   
/*      */   private ResultSetInternalMethods outputParameterResults;
/*      */ 
/*      */   
/*      */   protected boolean outputParamWasNull = false;
/*      */ 
/*      */   
/*      */   private int[] parameterIndexToRsIndex;
/*      */ 
/*      */   
/*      */   protected CallableStatementParamInfo paramInfo;
/*      */ 
/*      */   
/*      */   private CallableStatementParam returnValueParam;
/*      */ 
/*      */   
/*      */   private int[] placeholderToParameterIndexMap;
/*      */ 
/*      */   
/*      */   public CallableStatement(MySQLConnection conn, CallableStatementParamInfo paramInfo) throws SQLException {
/*  435 */     super(conn, paramInfo.nativeSql, paramInfo.catalogInUse);
/*      */     
/*  437 */     this.paramInfo = paramInfo;
/*  438 */     this.callingStoredFunction = this.paramInfo.isFunctionCall;
/*      */     
/*  440 */     if (this.callingStoredFunction) {
/*  441 */       this.parameterCount++;
/*      */     }
/*      */     
/*  444 */     this.retrieveGeneratedKeys = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static CallableStatement getInstance(MySQLConnection conn, String sql, String catalog, boolean isFunctionCall) throws SQLException {
/*  455 */     if (!Util.isJdbc4()) {
/*  456 */       return new CallableStatement(conn, sql, catalog, isFunctionCall);
/*      */     }
/*      */     
/*  459 */     return (CallableStatement)Util.handleNewInstance(JDBC_4_CSTMT_4_ARGS_CTOR, new Object[] { conn, sql, catalog, Boolean.valueOf(isFunctionCall) }, conn.getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static CallableStatement getInstance(MySQLConnection conn, CallableStatementParamInfo paramInfo) throws SQLException {
/*  471 */     if (!Util.isJdbc4()) {
/*  472 */       return new CallableStatement(conn, paramInfo);
/*      */     }
/*      */     
/*  475 */     return (CallableStatement)Util.handleNewInstance(JDBC_4_CSTMT_2_ARGS_CTOR, new Object[] { conn, paramInfo }, conn.getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void generateParameterMap() throws SQLException {
/*  482 */     synchronized (checkClosed().getConnectionMutex()) {
/*  483 */       if (this.paramInfo == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  489 */       int parameterCountFromMetaData = this.paramInfo.getParameterCount();
/*      */       
/*  491 */       if (this.paramInfo != null && this.parameterCount != parameterCountFromMetaData) {
/*  492 */         this.placeholderToParameterIndexMap = new int[this.parameterCount];
/*      */         
/*  494 */         int startIndex = 0;
/*      */         
/*  496 */         if (this.callingStoredFunction) {
/*  497 */           this.placeholderToParameterIndexMap[0] = 0;
/*  498 */           startIndex = 1;
/*      */         } 
/*      */         
/*  501 */         int startPos = this.callingStoredFunction ? StringUtils.indexOfIgnoreCase(this.originalSql, "SELECT") : StringUtils.indexOfIgnoreCase(this.originalSql, "CALL");
/*      */ 
/*      */         
/*  504 */         if (startPos != -1) {
/*  505 */           int parenOpenPos = this.originalSql.indexOf('(', startPos + 4);
/*      */           
/*  507 */           if (parenOpenPos != -1) {
/*  508 */             int parenClosePos = StringUtils.indexOfIgnoreCase(parenOpenPos, this.originalSql, ")", "'", "'", StringUtils.SEARCH_MODE__ALL);
/*      */             
/*  510 */             if (parenClosePos != -1) {
/*  511 */               List<?> parsedParameters = StringUtils.split(this.originalSql.substring(parenOpenPos + 1, parenClosePos), ",", "'\"", "'\"", true);
/*      */               
/*  513 */               int numParsedParameters = parsedParameters.size();
/*      */               
/*  515 */               int placeholderCount = startIndex;
/*  516 */               for (int i = 0; i < numParsedParameters; i++) {
/*  517 */                 if (((String)parsedParameters.get(i)).equals("?")) {
/*  518 */                   this.placeholderToParameterIndexMap[placeholderCount++] = startIndex + i;
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CallableStatement(MySQLConnection conn, String sql, String catalog, boolean isFunctionCall) throws SQLException {
/*  542 */     super(conn, sql, catalog);
/*      */     
/*  544 */     this.callingStoredFunction = isFunctionCall;
/*      */     
/*  546 */     if (!this.callingStoredFunction) {
/*  547 */       if (!StringUtils.startsWithIgnoreCaseAndWs(sql, "CALL")) {
/*      */         
/*  549 */         fakeParameterTypes(false);
/*      */       } else {
/*  551 */         determineParameterTypes();
/*      */       } 
/*      */       
/*  554 */       generateParameterMap();
/*      */     } else {
/*  556 */       determineParameterTypes();
/*  557 */       this.parameterCount++;
/*      */       
/*  559 */       generateParameterMap();
/*      */     } 
/*      */     
/*  562 */     this.retrieveGeneratedKeys = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addBatch() throws SQLException {
/*  572 */     setOutParams();
/*      */     
/*  574 */     super.addBatch();
/*      */   }
/*      */ 
/*      */   
/*      */   private CallableStatementParam checkIsOutputParam(int paramIndex) throws SQLException {
/*  579 */     synchronized (checkClosed().getConnectionMutex()) {
/*  580 */       if (this.callingStoredFunction && 
/*  581 */         paramIndex == 1) {
/*      */         
/*  583 */         if (this.returnValueParam == null) {
/*  584 */           this.returnValueParam = new CallableStatementParam("", 0, false, true, 12, "VARCHAR", 0, 0, (short)2, 5);
/*      */         }
/*      */ 
/*      */         
/*  588 */         return this.returnValueParam;
/*      */       } 
/*      */ 
/*      */       
/*  592 */       checkParameterIndexBounds(paramIndex);
/*      */       
/*  594 */       int localParamIndex = paramIndex - 1;
/*      */       
/*  596 */       if (this.placeholderToParameterIndexMap != null) {
/*  597 */         localParamIndex = this.placeholderToParameterIndexMap[localParamIndex];
/*      */       }
/*      */       
/*  600 */       CallableStatementParam paramDescriptor = this.paramInfo.getParameter(localParamIndex);
/*      */ 
/*      */ 
/*      */       
/*  604 */       if (this.connection.getNoAccessToProcedureBodies()) {
/*  605 */         paramDescriptor.isOut = true;
/*  606 */         paramDescriptor.isIn = true;
/*  607 */         paramDescriptor.inOutModifier = 2;
/*  608 */       } else if (!paramDescriptor.isOut) {
/*  609 */         throw SQLError.createSQLException(Messages.getString("CallableStatement.9") + paramIndex + Messages.getString("CallableStatement.10"), "S1009", getExceptionInterceptor());
/*      */       } 
/*      */ 
/*      */       
/*  613 */       this.hasOutputParams = true;
/*      */       
/*  615 */       return paramDescriptor;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkParameterIndexBounds(int paramIndex) throws SQLException {
/*  625 */     synchronized (checkClosed().getConnectionMutex()) {
/*  626 */       this.paramInfo.checkBounds(paramIndex);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkStreamability() throws SQLException {
/*  638 */     if (this.hasOutputParams && createStreamingResultSet()) {
/*  639 */       throw SQLError.createSQLException(Messages.getString("CallableStatement.14"), "S1C00", getExceptionInterceptor());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearParameters() throws SQLException {
/*  645 */     synchronized (checkClosed().getConnectionMutex()) {
/*  646 */       super.clearParameters();
/*      */       
/*      */       try {
/*  649 */         if (this.outputParameterResults != null) {
/*  650 */           this.outputParameterResults.close();
/*      */         }
/*      */       } finally {
/*  653 */         this.outputParameterResults = null;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void fakeParameterTypes(boolean isReallyProcedure) throws SQLException {
/*  666 */     synchronized (checkClosed().getConnectionMutex()) {
/*  667 */       Field[] fields = new Field[13];
/*      */       
/*  669 */       fields[0] = new Field("", "PROCEDURE_CAT", 1, 0);
/*  670 */       fields[1] = new Field("", "PROCEDURE_SCHEM", 1, 0);
/*  671 */       fields[2] = new Field("", "PROCEDURE_NAME", 1, 0);
/*  672 */       fields[3] = new Field("", "COLUMN_NAME", 1, 0);
/*  673 */       fields[4] = new Field("", "COLUMN_TYPE", 1, 0);
/*  674 */       fields[5] = new Field("", "DATA_TYPE", 5, 0);
/*  675 */       fields[6] = new Field("", "TYPE_NAME", 1, 0);
/*  676 */       fields[7] = new Field("", "PRECISION", 4, 0);
/*  677 */       fields[8] = new Field("", "LENGTH", 4, 0);
/*  678 */       fields[9] = new Field("", "SCALE", 5, 0);
/*  679 */       fields[10] = new Field("", "RADIX", 5, 0);
/*  680 */       fields[11] = new Field("", "NULLABLE", 5, 0);
/*  681 */       fields[12] = new Field("", "REMARKS", 1, 0);
/*      */       
/*  683 */       String procName = isReallyProcedure ? extractProcedureName() : null;
/*      */       
/*  685 */       byte[] procNameAsBytes = null;
/*      */       
/*      */       try {
/*  688 */         procNameAsBytes = (procName == null) ? null : StringUtils.getBytes(procName, "UTF-8");
/*  689 */       } catch (UnsupportedEncodingException ueEx) {
/*  690 */         procNameAsBytes = StringUtils.s2b(procName, this.connection);
/*      */       } 
/*      */       
/*  693 */       ArrayList<ResultSetRow> resultRows = new ArrayList<ResultSetRow>();
/*      */       
/*  695 */       int numOfParameters = this.callingStoredFunction ? (this.parameterCount + 1) : this.parameterCount;
/*  696 */       for (int i = 0; i < numOfParameters; i++) {
/*  697 */         byte[][] row = new byte[13][];
/*  698 */         row[0] = null;
/*  699 */         row[1] = null;
/*  700 */         row[2] = procNameAsBytes;
/*  701 */         row[3] = StringUtils.s2b(String.valueOf(i), this.connection);
/*      */         
/*  703 */         if (this.callingStoredFunction && i == 0) {
/*      */           
/*  705 */           row[4] = StringUtils.s2b(String.valueOf(4), this.connection);
/*      */         } else {
/*  707 */           row[4] = StringUtils.s2b(String.valueOf(1), this.connection);
/*      */         } 
/*      */         
/*  710 */         row[5] = StringUtils.s2b(String.valueOf(12), this.connection);
/*  711 */         row[6] = StringUtils.s2b("VARCHAR", this.connection);
/*  712 */         row[7] = StringUtils.s2b(Integer.toString(65535), this.connection);
/*  713 */         row[8] = StringUtils.s2b(Integer.toString(65535), this.connection);
/*  714 */         row[9] = StringUtils.s2b(Integer.toString(0), this.connection);
/*  715 */         row[10] = StringUtils.s2b(Integer.toString(10), this.connection);
/*      */         
/*  717 */         row[11] = StringUtils.s2b(Integer.toString(2), this.connection);
/*      */         
/*  719 */         row[12] = null;
/*      */         
/*  721 */         resultRows.add(new ByteArrayRow(row, getExceptionInterceptor()));
/*      */       } 
/*      */       
/*  724 */       ResultSet paramTypesRs = DatabaseMetaData.buildResultSet(fields, resultRows, this.connection);
/*      */       
/*  726 */       convertGetProcedureColumnsToInternalDescriptors(paramTypesRs);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void determineParameterTypes() throws SQLException {
/*  731 */     synchronized (checkClosed().getConnectionMutex()) {
/*  732 */       ResultSet paramTypesRs = null;
/*      */ 
/*      */       
/*      */       try {
/*  736 */         String procName = extractProcedureName();
/*  737 */         String quotedId = "";
/*      */         try {
/*  739 */           quotedId = this.connection.supportsQuotedIdentifiers() ? this.connection.getMetaData().getIdentifierQuoteString() : "";
/*  740 */         } catch (SQLException sqlEx) {
/*      */ 
/*      */           
/*  743 */           AssertionFailedException.shouldNotHappen(sqlEx);
/*      */         } 
/*      */         
/*  746 */         List<?> parseList = StringUtils.splitDBdotName(procName, "", quotedId, this.connection.isNoBackslashEscapesSet());
/*  747 */         String tmpCatalog = "";
/*      */         
/*  749 */         if (parseList.size() == 2) {
/*  750 */           tmpCatalog = (String)parseList.get(0);
/*  751 */           procName = (String)parseList.get(1);
/*      */         } 
/*      */ 
/*      */         
/*  755 */         boolean useCatalog = (tmpCatalog.length() <= 0);
/*      */         
/*  757 */         paramTypesRs = getParamTypes((this.connection.versionMeetsMinimum(5, 0, 2) && useCatalog) ? this.currentCatalog : tmpCatalog, procName);
/*      */         
/*  759 */         boolean hasResults = false;
/*      */         try {
/*  761 */           if (paramTypesRs.next()) {
/*  762 */             paramTypesRs.previous();
/*  763 */             hasResults = true;
/*      */           } 
/*  765 */         } catch (Exception e) {}
/*      */ 
/*      */         
/*  768 */         if (hasResults) {
/*  769 */           convertGetProcedureColumnsToInternalDescriptors(paramTypesRs);
/*      */         } else {
/*  771 */           fakeParameterTypes(true);
/*      */         } 
/*      */       } finally {
/*  774 */         SQLException sqlExRethrow = null;
/*      */         
/*  776 */         if (paramTypesRs != null) {
/*      */           try {
/*  778 */             paramTypesRs.close();
/*  779 */           } catch (SQLException sqlEx) {
/*  780 */             sqlExRethrow = sqlEx;
/*      */           } 
/*      */           
/*  783 */           paramTypesRs = null;
/*      */         } 
/*      */         
/*  786 */         if (sqlExRethrow != null) {
/*  787 */           throw sqlExRethrow;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected ResultSet getParamTypes(String catalog, String routineName) throws SQLException {
/*  794 */     boolean getProcRetFuncsCurrentValue = this.connection.getGetProceduresReturnsFunctions();
/*      */     try {
/*  796 */       this.connection.setGetProceduresReturnsFunctions(this.callingStoredFunction);
/*  797 */       return this.connection.getMetaData().getProcedureColumns(catalog, null, routineName, "%");
/*      */     } finally {
/*  799 */       this.connection.setGetProceduresReturnsFunctions(getProcRetFuncsCurrentValue);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void convertGetProcedureColumnsToInternalDescriptors(ResultSet paramTypesRs) throws SQLException {
/*  804 */     synchronized (checkClosed().getConnectionMutex()) {
/*  805 */       this.paramInfo = new CallableStatementParamInfo(paramTypesRs);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean execute() throws SQLException {
/*  816 */     synchronized (checkClosed().getConnectionMutex()) {
/*  817 */       boolean returnVal = false;
/*      */       
/*  819 */       checkStreamability();
/*      */       
/*  821 */       setInOutParamsOnServer();
/*  822 */       setOutParams();
/*      */       
/*  824 */       returnVal = super.execute();
/*      */       
/*  826 */       if (this.callingStoredFunction) {
/*  827 */         this.functionReturnValueResults = this.results;
/*  828 */         this.functionReturnValueResults.next();
/*  829 */         this.results = null;
/*      */       } 
/*      */       
/*  832 */       retrieveOutParams();
/*      */       
/*  834 */       if (!this.callingStoredFunction) {
/*  835 */         return returnVal;
/*      */       }
/*      */ 
/*      */       
/*  839 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultSet executeQuery() throws SQLException {
/*  850 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/*  852 */       checkStreamability();
/*      */       
/*  854 */       ResultSet execResults = null;
/*      */       
/*  856 */       setInOutParamsOnServer();
/*  857 */       setOutParams();
/*      */       
/*  859 */       execResults = super.executeQuery();
/*      */       
/*  861 */       retrieveOutParams();
/*      */       
/*  863 */       return execResults;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int executeUpdate() throws SQLException {
/*  874 */     return Util.truncateAndConvertToInt(executeLargeUpdate());
/*      */   }
/*      */   
/*      */   private String extractProcedureName() throws SQLException {
/*  878 */     String sanitizedSql = StringUtils.stripComments(this.originalSql, "`\"'", "`\"'", true, false, true, true);
/*      */ 
/*      */     
/*  881 */     int endCallIndex = StringUtils.indexOfIgnoreCase(sanitizedSql, "CALL ");
/*  882 */     int offset = 5;
/*      */     
/*  884 */     if (endCallIndex == -1) {
/*  885 */       endCallIndex = StringUtils.indexOfIgnoreCase(sanitizedSql, "SELECT ");
/*  886 */       offset = 7;
/*      */     } 
/*      */     
/*  889 */     if (endCallIndex != -1) {
/*  890 */       StringBuilder nameBuf = new StringBuilder();
/*      */       
/*  892 */       String trimmedStatement = sanitizedSql.substring(endCallIndex + offset).trim();
/*      */       
/*  894 */       int statementLength = trimmedStatement.length();
/*      */       
/*  896 */       for (int i = 0; i < statementLength; i++) {
/*  897 */         char c = trimmedStatement.charAt(i);
/*      */         
/*  899 */         if (Character.isWhitespace(c) || c == '(' || c == '?') {
/*      */           break;
/*      */         }
/*  902 */         nameBuf.append(c);
/*      */       } 
/*      */ 
/*      */       
/*  906 */       return nameBuf.toString();
/*      */     } 
/*      */     
/*  909 */     throw SQLError.createSQLException(Messages.getString("CallableStatement.1"), "S1000", getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String fixParameterName(String paramNameIn) throws SQLException {
/*  924 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/*  926 */       if ((paramNameIn == null || paramNameIn.length() == 0) && !hasParametersView()) {
/*  927 */         throw SQLError.createSQLException((Messages.getString("CallableStatement.0") + paramNameIn == null) ? Messages.getString("CallableStatement.15") : Messages.getString("CallableStatement.16"), "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  932 */       if (paramNameIn == null && hasParametersView()) {
/*  933 */         paramNameIn = "nullpn";
/*      */       }
/*      */       
/*  936 */       if (this.connection.getNoAccessToProcedureBodies()) {
/*  937 */         throw SQLError.createSQLException("No access to parameters by name when connection has been configured not to access procedure bodies", "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/*  941 */       return mangleParameterName(paramNameIn);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Array getArray(int i) throws SQLException {
/*  949 */     synchronized (checkClosed().getConnectionMutex()) {
/*  950 */       ResultSetInternalMethods rs = getOutputParameters(i);
/*      */       
/*  952 */       Array retValue = rs.getArray(mapOutputParameterIndexToRsIndex(i));
/*      */       
/*  954 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/*  956 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Array getArray(String parameterName) throws SQLException {
/*  964 */     synchronized (checkClosed().getConnectionMutex()) {
/*  965 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/*  967 */       Array retValue = rs.getArray(fixParameterName(parameterName));
/*      */       
/*  969 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/*  971 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
/*  979 */     synchronized (checkClosed().getConnectionMutex()) {
/*  980 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/*  982 */       BigDecimal retValue = rs.getBigDecimal(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/*  984 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/*  986 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
/* 1001 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1002 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1004 */       BigDecimal retValue = rs.getBigDecimal(mapOutputParameterIndexToRsIndex(parameterIndex), scale);
/*      */       
/* 1006 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1008 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigDecimal getBigDecimal(String parameterName) throws SQLException {
/* 1016 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1017 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1019 */       BigDecimal retValue = rs.getBigDecimal(fixParameterName(parameterName));
/*      */       
/* 1021 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1023 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Blob getBlob(int parameterIndex) throws SQLException {
/* 1031 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1032 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1034 */       Blob retValue = rs.getBlob(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1036 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1038 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Blob getBlob(String parameterName) throws SQLException {
/* 1046 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1047 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1049 */       Blob retValue = rs.getBlob(fixParameterName(parameterName));
/*      */       
/* 1051 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1053 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBoolean(int parameterIndex) throws SQLException {
/* 1061 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1062 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1064 */       boolean retValue = rs.getBoolean(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1066 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1068 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBoolean(String parameterName) throws SQLException {
/* 1076 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1077 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1079 */       boolean retValue = rs.getBoolean(fixParameterName(parameterName));
/*      */       
/* 1081 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1083 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getByte(int parameterIndex) throws SQLException {
/* 1091 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1092 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1094 */       byte retValue = rs.getByte(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1096 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1098 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getByte(String parameterName) throws SQLException {
/* 1106 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1107 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1109 */       byte retValue = rs.getByte(fixParameterName(parameterName));
/*      */       
/* 1111 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1113 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytes(int parameterIndex) throws SQLException {
/* 1121 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1122 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1124 */       byte[] retValue = rs.getBytes(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1126 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1128 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytes(String parameterName) throws SQLException {
/* 1136 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1137 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1139 */       byte[] retValue = rs.getBytes(fixParameterName(parameterName));
/*      */       
/* 1141 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1143 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Clob getClob(int parameterIndex) throws SQLException {
/* 1151 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1152 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1154 */       Clob retValue = rs.getClob(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1156 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1158 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Clob getClob(String parameterName) throws SQLException {
/* 1166 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1167 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1169 */       Clob retValue = rs.getClob(fixParameterName(parameterName));
/*      */       
/* 1171 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1173 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Date getDate(int parameterIndex) throws SQLException {
/* 1181 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1182 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1184 */       Date retValue = rs.getDate(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1186 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1188 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
/* 1196 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1197 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1199 */       Date retValue = rs.getDate(mapOutputParameterIndexToRsIndex(parameterIndex), cal);
/*      */       
/* 1201 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1203 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Date getDate(String parameterName) throws SQLException {
/* 1211 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1212 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1214 */       Date retValue = rs.getDate(fixParameterName(parameterName));
/*      */       
/* 1216 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1218 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Date getDate(String parameterName, Calendar cal) throws SQLException {
/* 1226 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1227 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1229 */       Date retValue = rs.getDate(fixParameterName(parameterName), cal);
/*      */       
/* 1231 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1233 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDouble(int parameterIndex) throws SQLException {
/* 1241 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1242 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1244 */       double retValue = rs.getDouble(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1246 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1248 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDouble(String parameterName) throws SQLException {
/* 1256 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1257 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1259 */       double retValue = rs.getDouble(fixParameterName(parameterName));
/*      */       
/* 1261 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1263 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloat(int parameterIndex) throws SQLException {
/* 1271 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1272 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1274 */       float retValue = rs.getFloat(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1276 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1278 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloat(String parameterName) throws SQLException {
/* 1286 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1287 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1289 */       float retValue = rs.getFloat(fixParameterName(parameterName));
/*      */       
/* 1291 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1293 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(int parameterIndex) throws SQLException {
/* 1301 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1302 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1304 */       int retValue = rs.getInt(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1306 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1308 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(String parameterName) throws SQLException {
/* 1316 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1317 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1319 */       int retValue = rs.getInt(fixParameterName(parameterName));
/*      */       
/* 1321 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1323 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLong(int parameterIndex) throws SQLException {
/* 1331 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1332 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1334 */       long retValue = rs.getLong(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1336 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1338 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLong(String parameterName) throws SQLException {
/* 1346 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1347 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1349 */       long retValue = rs.getLong(fixParameterName(parameterName));
/*      */       
/* 1351 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1353 */       return retValue;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected int getNamedParamIndex(String paramName, boolean forOut) throws SQLException {
/* 1358 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1359 */       if (this.connection.getNoAccessToProcedureBodies()) {
/* 1360 */         throw SQLError.createSQLException("No access to parameters by name when connection has been configured not to access procedure bodies", "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1365 */       if (paramName == null || paramName.length() == 0) {
/* 1366 */         throw SQLError.createSQLException(Messages.getString("CallableStatement.2"), "S1009", getExceptionInterceptor());
/*      */       }
/*      */       
/*      */       CallableStatementParam namedParamInfo;
/* 1370 */       if (this.paramInfo == null || (namedParamInfo = this.paramInfo.getParameter(paramName)) == null) {
/* 1371 */         throw SQLError.createSQLException(Messages.getString("CallableStatement.3") + paramName + Messages.getString("CallableStatement.4"), "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/* 1375 */       if (forOut && !namedParamInfo.isOut) {
/* 1376 */         throw SQLError.createSQLException(Messages.getString("CallableStatement.5") + paramName + Messages.getString("CallableStatement.6"), "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/* 1380 */       if (this.placeholderToParameterIndexMap == null) {
/* 1381 */         return namedParamInfo.index + 1;
/*      */       }
/*      */       
/* 1384 */       for (int i = 0; i < this.placeholderToParameterIndexMap.length; i++) {
/* 1385 */         if (this.placeholderToParameterIndexMap[i] == namedParamInfo.index) {
/* 1386 */           return i + 1;
/*      */         }
/*      */       } 
/*      */       
/* 1390 */       throw SQLError.createSQLException("Can't find local placeholder mapping for parameter named \"" + paramName + "\".", "S1009", getExceptionInterceptor());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObject(int parameterIndex) throws SQLException {
/* 1399 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1400 */       CallableStatementParam paramDescriptor = checkIsOutputParam(parameterIndex);
/*      */       
/* 1402 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1404 */       Object retVal = rs.getObjectStoredProc(mapOutputParameterIndexToRsIndex(parameterIndex), paramDescriptor.desiredJdbcType);
/*      */       
/* 1406 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1408 */       return retVal;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
/* 1416 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1417 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1419 */       Object retVal = rs.getObject(mapOutputParameterIndexToRsIndex(parameterIndex), map);
/*      */       
/* 1421 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1423 */       return retVal;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObject(String parameterName) throws SQLException {
/* 1431 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1432 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1434 */       Object retValue = rs.getObject(fixParameterName(parameterName));
/*      */       
/* 1436 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1438 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
/* 1446 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1447 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1449 */       Object retValue = rs.getObject(fixParameterName(parameterName), map);
/*      */       
/* 1451 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1453 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
/* 1459 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1460 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */ 
/*      */       
/* 1463 */       T retVal = ((ResultSetImpl)rs).getObject(mapOutputParameterIndexToRsIndex(parameterIndex), type);
/*      */       
/* 1465 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1467 */       return retVal;
/*      */     } 
/*      */   }
/*      */   
/*      */   public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
/* 1472 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1473 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1475 */       T retValue = ((ResultSetImpl)rs).getObject(fixParameterName(parameterName), type);
/*      */       
/* 1477 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1479 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ResultSetInternalMethods getOutputParameters(int paramIndex) throws SQLException {
/* 1494 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1495 */       this.outputParamWasNull = false;
/*      */       
/* 1497 */       if (paramIndex == 1 && this.callingStoredFunction && this.returnValueParam != null) {
/* 1498 */         return this.functionReturnValueResults;
/*      */       }
/*      */       
/* 1501 */       if (this.outputParameterResults == null) {
/* 1502 */         if (this.paramInfo.numberOfParameters() == 0) {
/* 1503 */           throw SQLError.createSQLException(Messages.getString("CallableStatement.7"), "S1009", getExceptionInterceptor());
/*      */         }
/*      */         
/* 1506 */         throw SQLError.createSQLException(Messages.getString("CallableStatement.8"), "S1000", getExceptionInterceptor());
/*      */       } 
/*      */       
/* 1509 */       return this.outputParameterResults;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public ParameterMetaData getParameterMetaData() throws SQLException {
/* 1515 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1516 */       if (this.placeholderToParameterIndexMap == null) {
/* 1517 */         return this.paramInfo;
/*      */       }
/*      */       
/* 1520 */       return new CallableStatementParamInfo(this.paramInfo);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Ref getRef(int parameterIndex) throws SQLException {
/* 1528 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1529 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1531 */       Ref retValue = rs.getRef(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1533 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1535 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Ref getRef(String parameterName) throws SQLException {
/* 1543 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1544 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1546 */       Ref retValue = rs.getRef(fixParameterName(parameterName));
/*      */       
/* 1548 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1550 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getShort(int parameterIndex) throws SQLException {
/* 1558 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1559 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1561 */       short retValue = rs.getShort(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1563 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1565 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getShort(String parameterName) throws SQLException {
/* 1573 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1574 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1576 */       short retValue = rs.getShort(fixParameterName(parameterName));
/*      */       
/* 1578 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1580 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getString(int parameterIndex) throws SQLException {
/* 1588 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1589 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1591 */       String retValue = rs.getString(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1593 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1595 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getString(String parameterName) throws SQLException {
/* 1603 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1604 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1606 */       String retValue = rs.getString(fixParameterName(parameterName));
/*      */       
/* 1608 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1610 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Time getTime(int parameterIndex) throws SQLException {
/* 1618 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1619 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1621 */       Time retValue = rs.getTime(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1623 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1625 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
/* 1633 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1634 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1636 */       Time retValue = rs.getTime(mapOutputParameterIndexToRsIndex(parameterIndex), cal);
/*      */       
/* 1638 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1640 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Time getTime(String parameterName) throws SQLException {
/* 1648 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1649 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1651 */       Time retValue = rs.getTime(fixParameterName(parameterName));
/*      */       
/* 1653 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1655 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Time getTime(String parameterName, Calendar cal) throws SQLException {
/* 1663 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1664 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1666 */       Time retValue = rs.getTime(fixParameterName(parameterName), cal);
/*      */       
/* 1668 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1670 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Timestamp getTimestamp(int parameterIndex) throws SQLException {
/* 1678 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1679 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1681 */       Timestamp retValue = rs.getTimestamp(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1683 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1685 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
/* 1693 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1694 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1696 */       Timestamp retValue = rs.getTimestamp(mapOutputParameterIndexToRsIndex(parameterIndex), cal);
/*      */       
/* 1698 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1700 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Timestamp getTimestamp(String parameterName) throws SQLException {
/* 1708 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1709 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1711 */       Timestamp retValue = rs.getTimestamp(fixParameterName(parameterName));
/*      */       
/* 1713 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1715 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
/* 1723 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1724 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1726 */       Timestamp retValue = rs.getTimestamp(fixParameterName(parameterName), cal);
/*      */       
/* 1728 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1730 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URL getURL(int parameterIndex) throws SQLException {
/* 1738 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1739 */       ResultSetInternalMethods rs = getOutputParameters(parameterIndex);
/*      */       
/* 1741 */       URL retValue = rs.getURL(mapOutputParameterIndexToRsIndex(parameterIndex));
/*      */       
/* 1743 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1745 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URL getURL(String parameterName) throws SQLException {
/* 1753 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1754 */       ResultSetInternalMethods rs = getOutputParameters(0);
/*      */       
/* 1756 */       URL retValue = rs.getURL(fixParameterName(parameterName));
/*      */       
/* 1758 */       this.outputParamWasNull = rs.wasNull();
/*      */       
/* 1760 */       return retValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected int mapOutputParameterIndexToRsIndex(int paramIndex) throws SQLException {
/* 1766 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1767 */       if (this.returnValueParam != null && paramIndex == 1) {
/* 1768 */         return 1;
/*      */       }
/*      */       
/* 1771 */       checkParameterIndexBounds(paramIndex);
/*      */       
/* 1773 */       int localParamIndex = paramIndex - 1;
/*      */       
/* 1775 */       if (this.placeholderToParameterIndexMap != null) {
/* 1776 */         localParamIndex = this.placeholderToParameterIndexMap[localParamIndex];
/*      */       }
/*      */       
/* 1779 */       int rsIndex = this.parameterIndexToRsIndex[localParamIndex];
/*      */       
/* 1781 */       if (rsIndex == Integer.MIN_VALUE) {
/* 1782 */         throw SQLError.createSQLException(Messages.getString("CallableStatement.21") + paramIndex + Messages.getString("CallableStatement.22"), "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */       
/* 1786 */       return rsIndex + 1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
/* 1794 */     CallableStatementParam paramDescriptor = checkIsOutputParam(parameterIndex);
/* 1795 */     paramDescriptor.desiredJdbcType = sqlType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
/* 1802 */     registerOutParameter(parameterIndex, sqlType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
/* 1809 */     checkIsOutputParam(parameterIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
/* 1816 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1817 */       registerOutParameter(getNamedParamIndex(parameterName, true), sqlType);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
/* 1825 */     registerOutParameter(getNamedParamIndex(parameterName, true), sqlType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
/* 1832 */     registerOutParameter(getNamedParamIndex(parameterName, true), sqlType, typeName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void retrieveOutParams() throws SQLException {
/* 1842 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1843 */       int numParameters = this.paramInfo.numberOfParameters();
/*      */       
/* 1845 */       this.parameterIndexToRsIndex = new int[numParameters];
/*      */       
/* 1847 */       for (int i = 0; i < numParameters; i++) {
/* 1848 */         this.parameterIndexToRsIndex[i] = Integer.MIN_VALUE;
/*      */       }
/*      */       
/* 1851 */       int localParamIndex = 0;
/*      */       
/* 1853 */       if (numParameters > 0) {
/* 1854 */         StringBuilder outParameterQuery = new StringBuilder("SELECT ");
/*      */         
/* 1856 */         boolean firstParam = true;
/* 1857 */         boolean hadOutputParams = false;
/*      */         
/* 1859 */         for (Iterator<CallableStatementParam> paramIter = this.paramInfo.iterator(); paramIter.hasNext(); ) {
/* 1860 */           CallableStatementParam retrParamInfo = paramIter.next();
/*      */           
/* 1862 */           if (retrParamInfo.isOut) {
/* 1863 */             hadOutputParams = true;
/*      */             
/* 1865 */             this.parameterIndexToRsIndex[retrParamInfo.index] = localParamIndex++;
/*      */             
/* 1867 */             if (retrParamInfo.paramName == null && hasParametersView()) {
/* 1868 */               retrParamInfo.paramName = "nullnp" + retrParamInfo.index;
/*      */             }
/*      */             
/* 1871 */             String outParameterName = mangleParameterName(retrParamInfo.paramName);
/*      */             
/* 1873 */             if (!firstParam) {
/* 1874 */               outParameterQuery.append(",");
/*      */             } else {
/* 1876 */               firstParam = false;
/*      */             } 
/*      */             
/* 1879 */             if (!outParameterName.startsWith("@")) {
/* 1880 */               outParameterQuery.append('@');
/*      */             }
/*      */             
/* 1883 */             outParameterQuery.append(outParameterName);
/*      */           } 
/*      */         } 
/*      */         
/* 1887 */         if (hadOutputParams) {
/*      */           
/* 1889 */           Statement outParameterStmt = null;
/* 1890 */           ResultSet outParamRs = null;
/*      */           
/*      */           try {
/* 1893 */             outParameterStmt = this.connection.createStatement();
/* 1894 */             outParamRs = outParameterStmt.executeQuery(outParameterQuery.toString());
/* 1895 */             this.outputParameterResults = ((ResultSetInternalMethods)outParamRs).copy();
/*      */             
/* 1897 */             if (!this.outputParameterResults.next()) {
/* 1898 */               this.outputParameterResults.close();
/* 1899 */               this.outputParameterResults = null;
/*      */             } 
/*      */           } finally {
/* 1902 */             if (outParameterStmt != null) {
/* 1903 */               outParameterStmt.close();
/*      */             }
/*      */           } 
/*      */         } else {
/* 1907 */           this.outputParameterResults = null;
/*      */         } 
/*      */       } else {
/* 1910 */         this.outputParameterResults = null;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
/* 1919 */     setAsciiStream(getNamedParamIndex(parameterName, false), x, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
/* 1926 */     setBigDecimal(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
/* 1933 */     setBinaryStream(getNamedParamIndex(parameterName, false), x, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBoolean(String parameterName, boolean x) throws SQLException {
/* 1940 */     setBoolean(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setByte(String parameterName, byte x) throws SQLException {
/* 1947 */     setByte(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBytes(String parameterName, byte[] x) throws SQLException {
/* 1954 */     setBytes(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
/* 1961 */     setCharacterStream(getNamedParamIndex(parameterName, false), reader, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDate(String parameterName, Date x) throws SQLException {
/* 1968 */     setDate(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
/* 1975 */     setDate(getNamedParamIndex(parameterName, false), x, cal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDouble(String parameterName, double x) throws SQLException {
/* 1982 */     setDouble(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloat(String parameterName, float x) throws SQLException {
/* 1989 */     setFloat(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */   
/*      */   private void setInOutParamsOnServer() throws SQLException {
/* 1993 */     synchronized (checkClosed().getConnectionMutex()) {
/* 1994 */       if (this.paramInfo.numParameters > 0) {
/* 1995 */         for (Iterator<CallableStatementParam> paramIter = this.paramInfo.iterator(); paramIter.hasNext(); ) {
/*      */           
/* 1997 */           CallableStatementParam inParamInfo = paramIter.next();
/*      */ 
/*      */           
/* 2000 */           if (inParamInfo.isOut && inParamInfo.isIn) {
/* 2001 */             if (inParamInfo.paramName == null && hasParametersView()) {
/* 2002 */               inParamInfo.paramName = "nullnp" + inParamInfo.index;
/*      */             }
/*      */             
/* 2005 */             String inOutParameterName = mangleParameterName(inParamInfo.paramName);
/* 2006 */             StringBuilder queryBuf = new StringBuilder(4 + inOutParameterName.length() + 1 + 1);
/* 2007 */             queryBuf.append("SET ");
/* 2008 */             queryBuf.append(inOutParameterName);
/* 2009 */             queryBuf.append("=?");
/*      */             
/* 2011 */             PreparedStatement setPstmt = null;
/*      */             
/*      */             try {
/* 2014 */               setPstmt = ((Wrapper)this.connection.clientPrepareStatement(queryBuf.toString())).<PreparedStatement>unwrap(PreparedStatement.class);
/*      */               
/* 2016 */               if (this.isNull[inParamInfo.index]) {
/* 2017 */                 setPstmt.setBytesNoEscapeNoQuotes(1, "NULL".getBytes());
/*      */               } else {
/*      */                 
/* 2020 */                 byte[] parameterAsBytes = getBytesRepresentation(inParamInfo.index);
/*      */                 
/* 2022 */                 if (parameterAsBytes != null)
/* 2023 */                 { if (parameterAsBytes.length > 8) { if (parameterAsBytes[0] == 95) { if (parameterAsBytes[1] == 98) { if (parameterAsBytes[2] == 105) { if (parameterAsBytes[3] == 110) { if (parameterAsBytes[4] == 97) { if (parameterAsBytes[5] == 114) { if (parameterAsBytes[6] == 121) { if (parameterAsBytes[7] == 39)
/*      */                                   
/*      */                                   { 
/* 2026 */                                     setPstmt.setBytesNoEscapeNoQuotes(1, parameterAsBytes); } else { continue; }  } else { continue; }  } else { continue; }  } else { continue; }  } else { continue; }  } else { continue; }
/*      */                          }
/* 2028 */                       else { int sqlType = inParamInfo.desiredJdbcType;
/*      */                         
/* 2030 */                         switch (sqlType)
/*      */                         { case -7:
/*      */                           case -4:
/*      */                           case -3:
/*      */                           case -2:
/*      */                           case 2000:
/*      */                           case 2004:
/* 2037 */                             setPstmt.setBytes(1, parameterAsBytes);
/*      */                             break;
/*      */                           
/*      */                           default:
/* 2041 */                             setPstmt.setBytesNoEscape(1, parameterAsBytes); break; }  }  } else { continue; }
/*      */                      }
/*      */                   else { continue; }
/*      */                    }
/* 2045 */                 else { setPstmt.setNull(1, 0); }
/*      */               
/*      */               } 
/*      */               
/* 2049 */               setPstmt.executeUpdate();
/*      */             } finally {
/* 2051 */               if (setPstmt != null) {
/* 2052 */                 setPstmt.close();
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInt(String parameterName, int x) throws SQLException {
/* 2065 */     setInt(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLong(String parameterName, long x) throws SQLException {
/* 2072 */     setLong(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNull(String parameterName, int sqlType) throws SQLException {
/* 2079 */     setNull(getNamedParamIndex(parameterName, false), sqlType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
/* 2086 */     setNull(getNamedParamIndex(parameterName, false), sqlType, typeName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObject(String parameterName, Object x) throws SQLException {
/* 2093 */     setObject(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
/* 2100 */     setObject(getNamedParamIndex(parameterName, false), x, targetSqlType);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {}
/*      */ 
/*      */ 
/*      */   
/*      */   private void setOutParams() throws SQLException {
/* 2110 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2111 */       if (this.paramInfo.numParameters > 0) {
/* 2112 */         for (Iterator<CallableStatementParam> paramIter = this.paramInfo.iterator(); paramIter.hasNext(); ) {
/* 2113 */           CallableStatementParam outParamInfo = paramIter.next();
/*      */           
/* 2115 */           if (!this.callingStoredFunction && outParamInfo.isOut) {
/*      */             
/* 2117 */             if (outParamInfo.paramName == null && hasParametersView()) {
/* 2118 */               outParamInfo.paramName = "nullnp" + outParamInfo.index;
/*      */             }
/*      */             
/* 2121 */             String outParameterName = mangleParameterName(outParamInfo.paramName);
/*      */             
/* 2123 */             int outParamIndex = 0;
/*      */             
/* 2125 */             if (this.placeholderToParameterIndexMap == null) {
/* 2126 */               outParamIndex = outParamInfo.index + 1;
/*      */             } else {
/*      */               
/* 2129 */               boolean found = false;
/*      */               
/* 2131 */               for (int i = 0; i < this.placeholderToParameterIndexMap.length; i++) {
/* 2132 */                 if (this.placeholderToParameterIndexMap[i] == outParamInfo.index) {
/* 2133 */                   outParamIndex = i + 1;
/* 2134 */                   found = true;
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/* 2139 */               if (!found) {
/* 2140 */                 throw SQLError.createSQLException(Messages.getString("CallableStatement.21") + outParamInfo.paramName + Messages.getString("CallableStatement.22"), "S1009", getExceptionInterceptor());
/*      */               }
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 2146 */             setBytesNoEscapeNoQuotes(outParamIndex, StringUtils.getBytes(outParameterName, this.charConverter, this.charEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), getExceptionInterceptor()));
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShort(String parameterName, short x) throws SQLException {
/* 2158 */     setShort(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setString(String parameterName, String x) throws SQLException {
/* 2165 */     setString(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTime(String parameterName, Time x) throws SQLException {
/* 2172 */     setTime(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
/* 2179 */     setTime(getNamedParamIndex(parameterName, false), x, cal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
/* 2186 */     setTimestamp(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
/* 2193 */     setTimestamp(getNamedParamIndex(parameterName, false), x, cal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setURL(String parameterName, URL val) throws SQLException {
/* 2200 */     setURL(getNamedParamIndex(parameterName, false), val);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean wasNull() throws SQLException {
/* 2207 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2208 */       return this.outputParamWasNull;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int[] executeBatch() throws SQLException {
/* 2214 */     return Util.truncateAndConvertToInt(executeLargeBatch());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getParameterIndexOffset() {
/* 2220 */     if (this.callingStoredFunction) {
/* 2221 */       return -1;
/*      */     }
/*      */     
/* 2224 */     return super.getParameterIndexOffset();
/*      */   }
/*      */   
/*      */   public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
/* 2228 */     setAsciiStream(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
/* 2233 */     setAsciiStream(getNamedParamIndex(parameterName, false), x, length);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
/* 2238 */     setBinaryStream(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
/* 2243 */     setBinaryStream(getNamedParamIndex(parameterName, false), x, length);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBlob(String parameterName, Blob x) throws SQLException {
/* 2248 */     setBlob(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
/* 2253 */     setBlob(getNamedParamIndex(parameterName, false), inputStream);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
/* 2258 */     setBlob(getNamedParamIndex(parameterName, false), inputStream, length);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
/* 2263 */     setCharacterStream(getNamedParamIndex(parameterName, false), reader);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
/* 2268 */     setCharacterStream(getNamedParamIndex(parameterName, false), reader, length);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setClob(String parameterName, Clob x) throws SQLException {
/* 2273 */     setClob(getNamedParamIndex(parameterName, false), x);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setClob(String parameterName, Reader reader) throws SQLException {
/* 2278 */     setClob(getNamedParamIndex(parameterName, false), reader);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setClob(String parameterName, Reader reader, long length) throws SQLException {
/* 2283 */     setClob(getNamedParamIndex(parameterName, false), reader, length);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
/* 2288 */     setNCharacterStream(getNamedParamIndex(parameterName, false), value);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
/* 2293 */     setNCharacterStream(getNamedParamIndex(parameterName, false), value, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean checkReadOnlyProcedure() throws SQLException {
/* 2304 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2305 */       if (this.connection.getNoAccessToProcedureBodies()) {
/* 2306 */         return false;
/*      */       }
/*      */       
/* 2309 */       if (this.paramInfo.isReadOnlySafeChecked) {
/* 2310 */         return this.paramInfo.isReadOnlySafeProcedure;
/*      */       }
/*      */       
/* 2313 */       ResultSet rs = null;
/* 2314 */       PreparedStatement ps = null;
/*      */       
/*      */       try {
/* 2317 */         String procName = extractProcedureName();
/*      */         
/* 2319 */         String catalog = this.currentCatalog;
/*      */         
/* 2321 */         if (procName.indexOf(".") != -1) {
/* 2322 */           catalog = procName.substring(0, procName.indexOf("."));
/*      */           
/* 2324 */           if (StringUtils.startsWithIgnoreCaseAndWs(catalog, "`") && catalog.trim().endsWith("`")) {
/* 2325 */             catalog = catalog.substring(1, catalog.length() - 1);
/*      */           }
/*      */           
/* 2328 */           procName = procName.substring(procName.indexOf(".") + 1);
/* 2329 */           procName = StringUtils.toString(StringUtils.stripEnclosure(StringUtils.getBytes(procName), "`", "`"));
/*      */         } 
/* 2331 */         ps = this.connection.prepareStatement("SELECT SQL_DATA_ACCESS FROM information_schema.routines WHERE routine_schema = ? AND routine_name = ?");
/* 2332 */         ps.setMaxRows(0);
/* 2333 */         ps.setFetchSize(0);
/*      */         
/* 2335 */         ps.setString(1, catalog);
/* 2336 */         ps.setString(2, procName);
/* 2337 */         rs = ps.executeQuery();
/* 2338 */         if (rs.next()) {
/* 2339 */           String sqlDataAccess = rs.getString(1);
/* 2340 */           if ("READS SQL DATA".equalsIgnoreCase(sqlDataAccess) || "NO SQL".equalsIgnoreCase(sqlDataAccess)) {
/* 2341 */             synchronized (this.paramInfo) {
/* 2342 */               this.paramInfo.isReadOnlySafeChecked = true;
/* 2343 */               this.paramInfo.isReadOnlySafeProcedure = true;
/*      */             } 
/* 2345 */             return true;
/*      */           } 
/*      */         } 
/* 2348 */       } catch (SQLException e) {
/*      */       
/*      */       } finally {
/* 2351 */         if (rs != null) {
/* 2352 */           rs.close();
/*      */         }
/* 2354 */         if (ps != null) {
/* 2355 */           ps.close();
/*      */         }
/*      */       } 
/*      */       
/* 2359 */       this.paramInfo.isReadOnlySafeChecked = false;
/* 2360 */       this.paramInfo.isReadOnlySafeProcedure = false;
/*      */     } 
/* 2362 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean checkReadOnlySafeStatement() throws SQLException {
/* 2368 */     return (super.checkReadOnlySafeStatement() || checkReadOnlyProcedure());
/*      */   }
/*      */   
/*      */   private boolean hasParametersView() throws SQLException {
/* 2372 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/* 2374 */       if (this.connection.versionMeetsMinimum(5, 5, 0)) {
/* 2375 */         DatabaseMetaData dbmd1 = new DatabaseMetaDataUsingInfoSchema(this.connection, this.connection.getCatalog());
/* 2376 */         return ((DatabaseMetaDataUsingInfoSchema)dbmd1).gethasParametersView();
/*      */       } 
/*      */       
/* 2379 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long executeLargeUpdate() throws SQLException {
/* 2391 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2392 */       long returnVal = -1L;
/*      */       
/* 2394 */       checkStreamability();
/*      */       
/* 2396 */       if (this.callingStoredFunction) {
/* 2397 */         execute();
/*      */         
/* 2399 */         return -1L;
/*      */       } 
/*      */       
/* 2402 */       setInOutParamsOnServer();
/* 2403 */       setOutParams();
/*      */       
/* 2405 */       returnVal = super.executeLargeUpdate();
/*      */       
/* 2407 */       retrieveOutParams();
/*      */       
/* 2409 */       return returnVal;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public long[] executeLargeBatch() throws SQLException {
/* 2415 */     if (this.hasOutputParams) {
/* 2416 */       throw SQLError.createSQLException("Can't call executeBatch() on CallableStatement with OUTPUT parameters", "S1009", getExceptionInterceptor());
/*      */     }
/*      */ 
/*      */     
/* 2420 */     return super.executeLargeBatch();
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\CallableStatement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */