package com.mysql.jdbc;

import java.sql.SQLException;

public interface LoadBalancedConnection extends MySQLConnection {
  boolean addHost(String paramString) throws SQLException;
  
  void removeHost(String paramString) throws SQLException;
  
  void removeHostWhenNotInUse(String paramString) throws SQLException;
  
  void ping(boolean paramBoolean) throws SQLException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\LoadBalancedConnection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */