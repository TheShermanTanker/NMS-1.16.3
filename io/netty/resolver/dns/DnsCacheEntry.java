package io.netty.resolver.dns;

import java.net.InetAddress;

public interface DnsCacheEntry {
  InetAddress address();
  
  Throwable cause();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\DnsCacheEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */