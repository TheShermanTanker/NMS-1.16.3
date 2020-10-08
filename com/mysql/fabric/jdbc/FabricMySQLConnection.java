package com.mysql.fabric.jdbc;

import com.mysql.fabric.ServerGroup;
import com.mysql.jdbc.MySQLConnection;
import java.sql.SQLException;
import java.util.Set;

public interface FabricMySQLConnection extends MySQLConnection {
  void clearServerSelectionCriteria() throws SQLException;
  
  void setShardKey(String paramString) throws SQLException;
  
  String getShardKey();
  
  void setShardTable(String paramString) throws SQLException;
  
  String getShardTable();
  
  void setServerGroupName(String paramString) throws SQLException;
  
  String getServerGroupName();
  
  ServerGroup getCurrentServerGroup();
  
  void clearQueryTables() throws SQLException;
  
  void addQueryTable(String paramString) throws SQLException;
  
  Set<String> getQueryTables();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\jdbc\FabricMySQLConnection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */