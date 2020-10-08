package com.mysql.jdbc;

import java.sql.SQLException;

public interface Wrapper {
  <T> T unwrap(Class<T> paramClass) throws SQLException;
  
  boolean isWrapperFor(Class<?> paramClass) throws SQLException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\Wrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */