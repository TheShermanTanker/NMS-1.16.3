package com.mysql.jdbc.jmx;

import java.sql.SQLException;

public interface LoadBalanceConnectionGroupManagerMBean {
  int getActiveHostCount(String paramString);
  
  int getTotalHostCount(String paramString);
  
  long getTotalLogicalConnectionCount(String paramString);
  
  long getActiveLogicalConnectionCount(String paramString);
  
  long getActivePhysicalConnectionCount(String paramString);
  
  long getTotalPhysicalConnectionCount(String paramString);
  
  long getTotalTransactionCount(String paramString);
  
  void removeHost(String paramString1, String paramString2) throws SQLException;
  
  void stopNewConnectionsToHost(String paramString1, String paramString2) throws SQLException;
  
  void addHost(String paramString1, String paramString2, boolean paramBoolean);
  
  String getActiveHostsList(String paramString);
  
  String getRegisteredConnectionGroups();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jmx\LoadBalanceConnectionGroupManagerMBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */