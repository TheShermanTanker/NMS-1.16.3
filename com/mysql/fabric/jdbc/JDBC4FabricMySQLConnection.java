package com.mysql.fabric.jdbc;

import com.mysql.fabric.ServerGroup;
import com.mysql.jdbc.JDBC4MySQLConnection;
import java.sql.SQLException;
import java.util.Set;

public interface JDBC4FabricMySQLConnection extends JDBC4MySQLConnection {
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\jdbc\JDBC4FabricMySQLConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */