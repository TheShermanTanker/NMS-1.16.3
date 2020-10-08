package org.sqlite.jdbc4;

import javax.sql.PooledConnection;
import javax.sql.StatementEventListener;

public abstract class JDBC4PooledConnection implements PooledConnection {
  public void addStatementEventListener(StatementEventListener listener) {}
  
  public void removeStatementEventListener(StatementEventListener listener) {}
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\sqlite\jdbc4\JDBC4PooledConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */