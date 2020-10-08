package com.mysql.jdbc;

import java.sql.SQLException;
import java.util.Properties;

public interface ConnectionPropertiesTransform {
  Properties transformProperties(Properties paramProperties) throws SQLException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ConnectionPropertiesTransform.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */