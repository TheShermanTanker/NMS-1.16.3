package com.mysql.jdbc;

import java.sql.SQLException;

public interface ExceptionInterceptor extends Extension {
  SQLException interceptException(SQLException paramSQLException, Connection paramConnection);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ExceptionInterceptor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */