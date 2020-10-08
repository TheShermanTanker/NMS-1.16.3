package org.apache.logging.log4j.spi;

public interface CleanableThreadContextMap extends ThreadContextMap2 {
  void removeAll(Iterable<String> paramIterable);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\spi\CleanableThreadContextMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */