package com.mysql.jdbc;

import java.sql.SQLException;
import java.util.Properties;

public interface Extension {
  void init(Connection paramConnection, Properties paramProperties) throws SQLException;
  
  void destroy();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\Extension.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */