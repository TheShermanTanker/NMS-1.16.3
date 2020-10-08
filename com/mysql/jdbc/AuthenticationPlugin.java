package com.mysql.jdbc;

import java.sql.SQLException;
import java.util.List;

public interface AuthenticationPlugin extends Extension {
  String getProtocolPluginName();
  
  boolean requiresConfidentiality();
  
  boolean isReusable();
  
  void setAuthenticationParameters(String paramString1, String paramString2);
  
  boolean nextAuthenticationStep(Buffer paramBuffer, List<Buffer> paramList) throws SQLException;
  
  void reset();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\AuthenticationPlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */