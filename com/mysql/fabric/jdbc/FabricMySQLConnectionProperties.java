package com.mysql.fabric.jdbc;

import com.mysql.jdbc.ConnectionProperties;

public interface FabricMySQLConnectionProperties extends ConnectionProperties {
  void setFabricShardKey(String paramString);
  
  String getFabricShardKey();
  
  void setFabricShardTable(String paramString);
  
  String getFabricShardTable();
  
  void setFabricServerGroup(String paramString);
  
  String getFabricServerGroup();
  
  void setFabricProtocol(String paramString);
  
  String getFabricProtocol();
  
  void setFabricUsername(String paramString);
  
  String getFabricUsername();
  
  void setFabricPassword(String paramString);
  
  String getFabricPassword();
  
  void setFabricReportErrors(boolean paramBoolean);
  
  boolean getFabricReportErrors();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\jdbc\FabricMySQLConnectionProperties.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */