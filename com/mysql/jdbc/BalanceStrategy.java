package com.mysql.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BalanceStrategy extends Extension {
  ConnectionImpl pickConnection(LoadBalancedConnectionProxy paramLoadBalancedConnectionProxy, List<String> paramList, Map<String, ConnectionImpl> paramMap, long[] paramArrayOflong, int paramInt) throws SQLException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\BalanceStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */