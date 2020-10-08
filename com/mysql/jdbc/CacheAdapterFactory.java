package com.mysql.jdbc;

import java.sql.SQLException;
import java.util.Properties;

public interface CacheAdapterFactory<K, V> {
  CacheAdapter<K, V> getInstance(Connection paramConnection, String paramString, int paramInt1, int paramInt2, Properties paramProperties) throws SQLException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\CacheAdapterFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */