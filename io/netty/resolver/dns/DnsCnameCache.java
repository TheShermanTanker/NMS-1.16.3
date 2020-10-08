package io.netty.resolver.dns;

import io.netty.channel.EventLoop;

public interface DnsCnameCache {
  String get(String paramString);
  
  void cache(String paramString1, String paramString2, long paramLong, EventLoop paramEventLoop);
  
  void clear();
  
  boolean clear(String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\DnsCnameCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */