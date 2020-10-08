package org.slf4j.spi;

import java.util.Map;

public interface MDCAdapter {
  void put(String paramString1, String paramString2);
  
  String get(String paramString);
  
  void remove(String paramString);
  
  void clear();
  
  Map<String, String> getCopyOfContextMap();
  
  void setContextMap(Map<String, String> paramMap);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\slf4j\spi\MDCAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */