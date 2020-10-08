package com.mysql.jdbc.jmx;

import java.sql.SQLException;

public interface ReplicationGroupManagerMBean {
  void addSlaveHost(String paramString1, String paramString2) throws SQLException;
  
  void removeSlaveHost(String paramString1, String paramString2) throws SQLException;
  
  void promoteSlaveToMaster(String paramString1, String paramString2) throws SQLException;
  
  void removeMasterHost(String paramString1, String paramString2) throws SQLException;
  
  String getMasterHostsList(String paramString);
  
  String getSlaveHostsList(String paramString);
  
  String getRegisteredConnectionGroups();
  
  int getActiveMasterHostCount(String paramString);
  
  int getActiveSlaveHostCount(String paramString);
  
  int getSlavePromotionCount(String paramString);
  
  long getTotalLogicalConnectionCount(String paramString);
  
  long getActiveLogicalConnectionCount(String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jmx\ReplicationGroupManagerMBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */