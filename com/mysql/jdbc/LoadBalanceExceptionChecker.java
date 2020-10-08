package com.mysql.jdbc;

import java.sql.SQLException;

public interface LoadBalanceExceptionChecker extends Extension {
  boolean shouldExceptionTriggerFailover(SQLException paramSQLException);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\LoadBalanceExceptionChecker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */