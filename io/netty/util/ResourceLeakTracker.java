package io.netty.util;

public interface ResourceLeakTracker<T> {
  void record();
  
  void record(Object paramObject);
  
  boolean close(T paramT);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\nett\\util\ResourceLeakTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */