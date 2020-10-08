package org.apache.logging.log4j.util;

import java.io.Serializable;
import java.util.Map;

public interface ReadOnlyStringMap extends Serializable {
  Map<String, String> toMap();
  
  boolean containsKey(String paramString);
  
  <V> void forEach(BiConsumer<String, ? super V> paramBiConsumer);
  
  <V, S> void forEach(TriConsumer<String, ? super V, S> paramTriConsumer, S paramS);
  
  <V> V getValue(String paramString);
  
  boolean isEmpty();
  
  int size();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4\\util\ReadOnlyStringMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */