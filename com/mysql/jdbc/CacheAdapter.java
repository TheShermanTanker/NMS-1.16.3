package com.mysql.jdbc;

import java.util.Set;

public interface CacheAdapter<K, V> {
  V get(K paramK);
  
  void put(K paramK, V paramV);
  
  void invalidate(K paramK);
  
  void invalidateAll(Set<K> paramSet);
  
  void invalidateAll();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\CacheAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */