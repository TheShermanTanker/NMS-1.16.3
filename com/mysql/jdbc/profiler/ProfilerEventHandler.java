package com.mysql.jdbc.profiler;

import com.mysql.jdbc.Extension;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.ResultSetInternalMethods;
import com.mysql.jdbc.Statement;

public interface ProfilerEventHandler extends Extension {
  void consumeEvent(ProfilerEvent paramProfilerEvent);
  
  void processEvent(byte paramByte, MySQLConnection paramMySQLConnection, Statement paramStatement, ResultSetInternalMethods paramResultSetInternalMethods, long paramLong, Throwable paramThrowable, String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\profiler\ProfilerEventHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */