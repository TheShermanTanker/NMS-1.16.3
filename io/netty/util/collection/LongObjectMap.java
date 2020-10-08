package io.netty.util.collection;

import java.util.Map;

public interface LongObjectMap<V> extends Map<Long, V> {
  V get(long paramLong);
  
  V put(long paramLong, V paramV);
  
  V remove(long paramLong);
  
  Iterable<PrimitiveEntry<V>> entries();
  
  boolean containsKey(long paramLong);
  
  public static interface PrimitiveEntry<V> {
    long key();
    
    V value();
    
    void setValue(V param1V);
  }
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\nett\\util\collection\LongObjectMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */