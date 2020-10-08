package com.mysql.jdbc;

import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;

public interface Statement extends Statement, Wrapper {
  void enableStreamingResults() throws SQLException;
  
  void disableStreamingResults() throws SQLException;
  
  void setLocalInfileInputStream(InputStream paramInputStream);
  
  InputStream getLocalInfileInputStream();
  
  void setPingTarget(PingTarget paramPingTarget);
  
  ExceptionInterceptor getExceptionInterceptor();
  
  void removeOpenResultSet(ResultSetInternalMethods paramResultSetInternalMethods);
  
  int getOpenResultSetCount();
  
  void setHoldResultsOpenOverClose(boolean paramBoolean);
  
  int getId();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\Statement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */