package com.mysql.jdbc;

import java.sql.SQLException;

public interface ReplicationConnection extends MySQLConnection {
  long getConnectionGroupId();
  
  Connection getCurrentConnection();
  
  Connection getMasterConnection();
  
  void promoteSlaveToMaster(String paramString) throws SQLException;
  
  void removeMasterHost(String paramString) throws SQLException;
  
  void removeMasterHost(String paramString, boolean paramBoolean) throws SQLException;
  
  boolean isHostMaster(String paramString);
  
  Connection getSlavesConnection();
  
  void addSlaveHost(String paramString) throws SQLException;
  
  void removeSlave(String paramString) throws SQLException;
  
  void removeSlave(String paramString, boolean paramBoolean) throws SQLException;
  
  boolean isHostSlave(String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ReplicationConnection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */