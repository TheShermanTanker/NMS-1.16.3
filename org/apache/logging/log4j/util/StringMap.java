package org.apache.logging.log4j.util;

public interface StringMap extends ReadOnlyStringMap {
  void clear();
  
  boolean equals(Object paramObject);
  
  void freeze();
  
  int hashCode();
  
  boolean isFrozen();
  
  void putAll(ReadOnlyStringMap paramReadOnlyStringMap);
  
  void putValue(String paramString, Object paramObject);
  
  void remove(String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4\\util\StringMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */